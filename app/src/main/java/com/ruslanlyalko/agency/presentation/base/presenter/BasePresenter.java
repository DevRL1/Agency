package com.ruslanlyalko.agency.presentation.base.presenter;

import android.support.annotation.NonNull;

import com.ruslanlyalko.agency.presentation.base.view.View;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends View> implements Presenter<V> {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private WeakReference<V> mBaseView;

    @Override
    public void attachView(final V view) {
        mBaseView = new WeakReference<>(view);
    }

    @Override
    public V getView() {
        return mBaseView.get();
    }

    @Override
    public boolean isViewAttached() {
        return mBaseView != null && mBaseView.get() != null;
    }


    @Override
    public void disposeOnDestroy(@NonNull final Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
        if (mBaseView != null)
            mBaseView.clear();
        mBaseView = null;
    }
}
