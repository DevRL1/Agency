package com.ruslanlyalko.agency.presentation.base.view;


import com.ruslanlyalko.agency.presentation.base.presenter.Presenter;

public interface BaseView<P extends Presenter> extends View<P> {
    void showMessage(final String message);
    void showProgress(final boolean visible);
    void showNoInternetError();
}
