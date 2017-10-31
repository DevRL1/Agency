package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;

import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.data.listeners.OrdersListener;
import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */


public class AgencyPresenter extends BasePresenter<AgencyView> implements OrdersListener {

    AgencyRepository mAgencyRepository;

    @Inject
    AgencyPresenter(AgencyRepository agencyRepository) {
        mAgencyRepository = agencyRepository;
    }


    void fetchAllOrders() {
        mAgencyRepository.getOrders(this);
    }

    @Override
    public void updateOrders(List<OrderItem> orders) {
        if (!isViewAttached()) return;
        getView().updateOrders(orders);
    }
}
