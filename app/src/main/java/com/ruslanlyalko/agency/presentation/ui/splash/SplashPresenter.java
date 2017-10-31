package com.ruslanlyalko.agency.presentation.ui.splash;

import com.google.firebase.auth.FirebaseAuth;
import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
@ConfigPersistent
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    SplashPresenter() {
    }

    void getActiveUser() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            getView().startLoginScreen();
        else
            getView().startDashboardScreen();

    }
}
