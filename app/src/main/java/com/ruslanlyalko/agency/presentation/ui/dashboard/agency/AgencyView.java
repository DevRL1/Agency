package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;


import com.ruslanlyalko.agency.presentation.base.view.BaseView;
import com.ruslanlyalko.agency.presentation.ui.dashboard.history.HistoryPresenter;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
public interface AgencyView extends BaseView<AgencyPresenter> {
    void showConfirmLogoutDialog();

    void showLoginScreen();
}
