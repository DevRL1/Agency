package com.ruslanlyalko.agency.di.module;

import android.content.Context;

import com.ruslanlyalko.agency.common.exception.NotImplementedException;
import com.ruslanlyalko.agency.di.annotations.ActivityContext;
import com.ruslanlyalko.agency.di.annotations.PerActivity;
import com.ruslanlyalko.agency.presentation.base.navigation.BackStackNavigationImpl;
import com.ruslanlyalko.agency.presentation.base.navigation.Navigation;
import com.ruslanlyalko.agency.presentation.base.navigation.NavigationImpl;
import com.ruslanlyalko.agency.presentation.base.view.BackStackActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity mActivity;

    public ActivityModule(final BaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity() {
        return mActivity;
    }

    @PerActivity
    @Provides
    @Named("simple")
    Navigation provideNavigation(final BaseActivity baseActivity) {
        return new NavigationImpl(baseActivity);
    }

    @PerActivity
    @Provides
    @Named("multistack")
    Navigation provideMultiStackNavigation(@Named("simple") final Navigation navigation,
                                           final BaseActivity baseActivity) {
        if (!(baseActivity instanceof BackStackActivity))
            throw new NotImplementedException("To use MultiBackStack navigator, your component should extend BackStackView", BackStackActivity.class);
        return new BackStackNavigationImpl(navigation, (BackStackActivity) baseActivity);
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
