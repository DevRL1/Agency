package com.ruslanlyalko.agency.presentation.base.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ruslanlyalko.agency.di.annotations.PerActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseFragment;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;

import javax.inject.Inject;

@PerActivity
public class NavigationImpl implements Navigation {

    private BaseActivity mBaseActivity;

    @Override
    public void showFragment(final BaseFragment fragment) {
        final int fragmentContainerId = mBaseActivity.getFragmentContainer();

        if (fragmentContainerId == 0)
            throw new RuntimeException("Layout resource id should be provided. Check the method description");

        final FragmentManager fragmentManager = mBaseActivity.getSupportFragmentManager();

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.getFragments() == null || fragmentManager.getFragments().size() == 0) {
            fragmentTransaction.replace(fragmentContainerId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        } else {
            fragmentTransaction.addToBackStack(null)
                    .replace(fragmentContainerId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @SuppressWarnings("RestrictedApi")
    @Override
    public void showFragment(final BaseFragment fragment, boolean addToBackStack) {
        final int fragmentContainerId = mBaseActivity.getFragmentContainer();
        if (fragmentContainerId == 0)
            throw new RuntimeException("Layout resource id should be provided. Check the method description");
        final FragmentManager fragmentManager = mBaseActivity.getSupportFragmentManager();

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.getFragments() == null || fragmentManager.getFragments().size() == 0) {
            fragmentTransaction.replace(fragmentContainerId, fragment)
                    .commit();
        } else {
            if (addToBackStack) {
                fragmentTransaction
                        .replace(fragmentContainerId, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            } else {
                fragmentTransaction
                        .replace(fragmentContainerId, fragment, fragment.getClass().getSimpleName())
                        .commit();
            }
        }
    }

    @Inject
    public NavigationImpl(final BaseActivity context) {
        mBaseActivity = context;
    }

    @Override
    public void startActivity(final Intent launchIntent) {
        mBaseActivity.startActivity(launchIntent);
    }

    @Override
    public void startActivityForResult(final BaseFragment baseFragment, final Intent launchIntent, final int requestCode) {
        baseFragment.startActivityForResult(launchIntent, requestCode);
    }

    @Override
    public void startActivity(final Intent launchIntent, final boolean finishCurrent) {
        startActivity(launchIntent);
        mBaseActivity.finish();
    }

    @Override
    public void startActivity(final Intent launchIntent, final Bundle bundle) {
        ActivityCompat.startActivity(mBaseActivity, launchIntent, bundle);
    }

    @Override
    public void startActivityForResult(final Intent launchIntent, final int requestCode) {
        mBaseActivity.startActivityForResult(launchIntent, requestCode);
    }

    @Override
    public void startActivityForResult(final BaseView baseView, final Intent launchIntent, final int requestCode) {
        if (baseView instanceof BaseActivity) {
            ((BaseActivity) baseView).startActivityForResult(launchIntent, requestCode);
        } else if (baseView instanceof BaseFragment) {
            ((BaseFragment) baseView).startActivityForResult(launchIntent, requestCode);
        } else {
            startActivityForResult(launchIntent, requestCode);
        }
    }

    @Override
    public void release() {
        mBaseActivity = null;
    }
}