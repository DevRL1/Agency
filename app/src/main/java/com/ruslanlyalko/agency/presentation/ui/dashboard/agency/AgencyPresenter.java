package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;

import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;
import com.ruslanlyalko.agency.presentation.ui.dashboard.history.HistoryView;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */


public class AgencyPresenter extends BasePresenter<AgencyView> {


    @Inject
    public AgencyPresenter() {

    }

    public void logoutClicked() {
        getView().showConfirmLogoutDialog();
    }

    public void logout() {

    }


}
