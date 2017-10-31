package com.ruslanlyalko.agency.presentation.ui.login;

import com.ruslanlyalko.agency.presentation.base.view.BaseView;
import com.ruslanlyalko.agency.presentation.ui.splash.SplashPresenter;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public interface LoginView extends BaseView<LoginPresenter> {

    void startDashboardScreen();

    void showErrorWrongLogin();
}
