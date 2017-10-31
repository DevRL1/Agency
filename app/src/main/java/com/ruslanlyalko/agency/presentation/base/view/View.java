package com.ruslanlyalko.agency.presentation.base.view;

import android.support.annotation.LayoutRes;

import com.ruslanlyalko.agency.presentation.base.presenter.Presenter;


public interface View<P extends Presenter> {
    @LayoutRes
    int getLayoutResource();
    P getPresenter();
}
