package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create;


import com.ruslanlyalko.agency.data.AgencyRepository;
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


    void registerUser(UserItem user, String password) {
        mAgencyRepository.createUser(user, password).addOnCompleteListener(task -> {
            getView().showProgress(false);
            if (!isViewAttached()) return;
            if (task.isSuccessful())
                getView().userRegistered();
            else
                getView().wrongLoginData();
        });
        getView().showProgress(true);
    }


}
