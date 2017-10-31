package com.ruslanlyalko.agency.presentation.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ruslanlyalko.agency.di.component.ActivityComponent;
import com.ruslanlyalko.agency.presentation.base.navigation.Navigation;
import com.ruslanlyalko.agency.presentation.base.presenter.Presenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment<P extends Presenter> extends Fragment implements BaseView<P> {

    @Inject P mPresenter;

    private Unbinder mUnbinder;
    private Disposable mConnectivityDisposable;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments(getArguments());
    }

    protected void initArguments(final Bundle argument) {
    }

    @Nullable
    @Override
    public android.view.View onCreateView(final LayoutInflater inflater,
                                          @Nullable final ViewGroup container,
                                          @Nullable final Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(getLayoutResource(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectToComponent();
        mPresenter.attachView(this);
    }

    @Override
    public final void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        setupView(savedInstanceState);
    }

    public ActivityComponent getComponent() {
        return getBaseActivity().getComponent();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    public Navigation getNavigation() {
        return getBaseActivity().getNavigation();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected abstract void injectToComponent();

    protected void setupView(final Bundle savedInstanceState) {
    }

    @Override
    public void showNoInternetError() {
        if (!isActivityAlive()) return;
        ((BaseView) getActivity()).showNoInternetError();
    }

    @Override
    public void showMessage(String message) {
        if (!isActivityAlive()) return;
        ((BaseView) getActivity()).showMessage(message);
    }

    //we delegate the job to the base activity
    @Override
    public void showProgress(final boolean visible) {
        if (!isActivityAlive()) return;
        ((BaseView) getActivity()).showProgress(visible);
    }


    /**
     * Check if the activity is not destroyed
     *
     * @return
     */
    private boolean isActivityAlive() {
        return getActivity() != null && !getActivity().isFinishing();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.detachView();
    }
}