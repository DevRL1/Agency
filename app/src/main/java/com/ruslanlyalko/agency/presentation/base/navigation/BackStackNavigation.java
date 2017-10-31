package com.ruslanlyalko.agency.presentation.base.navigation;

import android.os.Bundle;

import com.ruslanlyalko.agency.presentation.base.view.BaseFragment;


public interface BackStackNavigation extends Navigation {

    int NO_HOST_ID = -1;

    void restoreState(final Bundle savedState);

    void saveState(final Bundle outState);

    void showFragment(final BaseFragment fragment,
                      final int fragmentHostId);

    void showFragment(final BaseFragment fragment,
                      final int fragmentHostId,
                      final boolean addToBackStack);

    void fragmentHostSelected(final int fragmentHostId);

    BaseFragment getCurrentFragment();

    boolean handleBack();

    void release();

    void backToRoot();
}