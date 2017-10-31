package com.ruslanlyalko.agency.di.component;

import com.ruslanlyalko.agency.di.annotations.PerActivity;
import com.ruslanlyalko.agency.di.module.ActivityModule;
import com.ruslanlyalko.agency.presentation.ui.dashboard.DashboardActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.AgencyFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create.CreateOrderActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.costs.CostsFragment;
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

    void inject(final CostsFragment historyFragment);

    void inject(final AgencyFragment agencyFragment);

    void inject(final LoginActivity loginActivity);

    void inject(ManageActivity manageActivity);

    void inject(RegisterActivity createAccountActivity);

    void inject(CreateOrderActivity createOrderActivity);
}
