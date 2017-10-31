package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create;


import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
@ConfigPersistent
public class CreateOrderPresenter extends BasePresenter<CreateOrderView> {

    private final AgencyRepository mAgencyRepository;

    @Inject
    CreateOrderPresenter(AgencyRepository agencyRepository) {
        mAgencyRepository = agencyRepository;
    }

    void createOrder(OrderItem order) {
        mAgencyRepository.createOrder(order).addOnCompleteListener(task -> {
            getView().showProgress(false);
            if (!isViewAttached()) return;
            getView().orderCreated();
        });
        getView().showProgress(true);
    }
}
