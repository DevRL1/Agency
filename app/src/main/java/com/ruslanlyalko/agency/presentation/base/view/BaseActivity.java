package com.ruslanlyalko.agency.presentation.base.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.di.component.ActivityComponent;
import com.ruslanlyalko.agency.di.component.ConfigPersistentComponent;
import com.ruslanlyalko.agency.di.component.DaggerConfigPersistentComponent;
import com.ruslanlyalko.agency.di.module.ActivityModule;
import com.ruslanlyalko.agency.presentation.AgencyApplication;
import com.ruslanlyalko.agency.presentation.base.navigation.Navigation;
import com.ruslanlyalko.agency.presentation.base.presenter.Presenter;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity implements BaseView<P> {

    @Inject P mPresenter;

    private Navigation mNavigation;

    protected ActivityComponent mActivityComponent;
    protected ProgressDialog mProgressDialog;
    protected Disposable mConnectivityDisposable;
    protected Unbinder mUnbinder;

    @Inject
    public void setNavigation(@Named("simple") final Navigation navigation) {
        mNavigation = navigation;
    }


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int layoutResource = getLayoutResource();
        if (layoutResource != -1)
            setContentView(layoutResource);
        mUnbinder = ButterKnife.bind(this);


        ConfigPersistentComponent persistentComponent = DaggerConfigPersistentComponent
                .builder()
                .applicationComponent(AgencyApplication.get(this).getComponent())
                .build();

        mActivityComponent = persistentComponent.activityComponent(new ActivityModule(this));

        injectToComponent();
        mPresenter.attachView(this);
    }

    protected abstract void injectToComponent();

    public ActivityComponent getComponent() {
        return mActivityComponent;
    }

    @VisibleForTesting
    public void setPresenter(final P presenter) {
        mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * This method provides the layout resource id where the fragment will be attached
     *
     * @return the id of layout
     */
    @IdRes
    public int getFragmentContainer() {
        return 0;
    }

    public Navigation getNavigation() {
        return mNavigation;
    }

    @Override
    public void showMessage(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(final boolean visible) {
        showProgressInternal(visible);
    }

    @Override
    public void showNoInternetError() {
        Toast.makeText(this, R.string.error_no_internet, Toast.LENGTH_LONG).show();
    }

    private void showProgressInternal(final boolean visible) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.alert_loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }

        if (visible) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        mPresenter.detachView();
        super.onDestroy();
    }
}
