package com.ruslanlyalko.agency.presentation.base.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.ruslanlyalko.agency.presentation.base.view.BaseFragment;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;


public interface Navigation {

    void showFragment(final BaseFragment fragment);

    void showFragment(final BaseFragment fragment, boolean addToBackStack);

    void startActivity(final Intent launchIntent);

    void startActivityForResult(final BaseFragment baseFragment, final Intent launchIntent, final int requestCode);

    void startActivity(final Intent launchIntent, final boolean finishCurrent);

    void startActivity(final Intent launchIntent, final Bundle bundle);

    void startActivityForResult(final Intent launchIntent, final int requestCode);

    void startActivityForResult(final BaseView baseView, final Intent launchIntent, final int requestCode);

    void release();
}