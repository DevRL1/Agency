package com.ruslanlyalko.agency.presentation.base.presenter;

import android.support.annotation.NonNull;

import com.ruslanlyalko.agency.presentation.base.view.View;

import io.reactivex.disposables.Disposable;

public interface Presenter<V extends View> {

    void attachView(final V view);

    V getView();

    boolean isViewAttached();


    void disposeOnDestroy(@NonNull final Disposable disposable);

    void detachView();
}
