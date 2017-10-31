package com.ruslanlyalko.agency.di.component;

import com.ruslanlyalko.agency.di.annotations.PerActivity;
import com.ruslanlyalko.agency.di.module.ActivityModule;
import com.ruslanlyalko.agency.presentation.ui.dashboard.DashboardActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.AgencyFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.history.HistoryFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.ProfileFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.ManageActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.register.RegisterActivity;
import com.ruslanlyalko.agency.presentation.ui.login.LoginActivity;
import com.ruslanlyalko.agency.presentation.ui.splash.SplashActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {


    void inject(final DashboardActivity dashboardActivity);

    void inject(final SplashActivity splashActivity);

    void inject(final ProfileFragment profileFragment);

    void inject(final HistoryFragment historyFragment);

    void inject(final AgencyFragment agencyFragment);

    void inject(final LoginActivity loginActivity);

    void inject(ManageActivity manageActivity);

    void inject(RegisterActivity createAccountActivity);
}
