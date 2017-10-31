package com.ruslanlyalko.agency.presentation.base.view;


import com.ruslanlyalko.agency.presentation.base.navigation.BackStackNavigation;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

public abstract class BackStackFragment<P extends BasePresenter> extends BaseFragment<P> {

    @Override
    public BackStackNavigation getNavigation() {
        return (BackStackNavigation) super.getNavigation();
    }
}

