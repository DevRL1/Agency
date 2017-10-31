package com.ruslanlyalko.agency.presentation.base.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.util.Log;

import com.ruslanlyalko.agency.di.annotations.PerActivity;
import com.ruslanlyalko.agency.presentation.base.view.BackStackActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseFragment;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class BackStackNavigationImpl implements BackStackNavigation {

    private static final String STATE_BACK_STACK_MANAGER = "back_stack_manager";
    private static final String STATE_HOST_ID = "host_id";
    private final BackStackActivity mBackStackActivity;
    private final Navigation mDefaultNavigation;

    private BackStackManager mBackStackManager;
    private BaseFragment mCurrentFragment;
    private int mCurrentHostId = NO_HOST_ID;

    @Inject
    public BackStackNavigationImpl(final @Named("simple") Navigation navigation,
                                   final BackStackActivity backStackActivity) {
        mBackStackActivity = backStackActivity;
        mDefaultNavigation = navigation;
        mBackStackManager = new BackStackManager();
    }

    @Override
    public void restoreState(final Bundle savedState) {
        mCurrentHostId = savedState.getInt(STATE_HOST_ID);
        mBackStackManager.restoreState(savedState.getParcelable(STATE_BACK_STACK_MANAGER));
        mCurrentFragment = (BaseFragment) mBackStackActivity.getSupportFragmentManager()
                .findFragmentById(mBackStackActivity.getFragmentContainer());
        mBackStackActivity.selectTab(mCurrentHostId);
    }

    @Override
    public void saveState(final Bundle outState) {
        outState.putParcelable(STATE_BACK_STACK_MANAGER, mBackStackManager.saveState());
        outState.putInt(STATE_HOST_ID, mCurrentHostId);
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return (BaseFragment) mBackStackActivity.getSupportFragmentManager()
                .findFragmentById(mBackStackActivity.getFragmentContainer());
    }

    protected boolean pushFragmentToBackStack(final int hostId,
                                              @NonNull final BaseFragment fragment) {
        try {
            final BackStackEntry entry = BackStackEntry.create(mBackStackActivity.getSupportFragmentManager(), fragment);
            mBackStackManager.push(hostId, entry);
            return true;
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to addItems fragment to back stack", e);
            return false;
        }
    }

    @Nullable
    protected BaseFragment popFragmentFromBackStack(final int hostId) {
        BackStackEntry entry = mBackStackManager.pop(hostId);
        return entry != null ? entry.toFragment(mBackStackActivity) : null;
    }

    @Nullable
    protected Pair<Integer, BaseFragment> popFragmentFromBackStack() {
        Pair<Integer, BackStackEntry> pair = mBackStackManager.pop();
        return pair != null ? Pair.create(pair.first, pair.second.toFragment(mBackStackActivity)) : null;
    }

    /**
     * @return false if back stack is missing.
     */
    protected boolean clearBackStack(final int hostId) {
        return mBackStackManager.clear(hostId);
    }

    private void replaceFragment(@NonNull final BaseFragment fragment) {
        final FragmentManager fm = mBackStackActivity.getSupportFragmentManager();
        final FragmentTransaction tr = fm.beginTransaction();
        tr.replace(mBackStackActivity.getFragmentContainer(), fragment);
        tr.commitAllowingStateLoss();
        mCurrentFragment = fragment;
    }

    @Override
    public void startActivity(final Intent launchIntent) {
        mDefaultNavigation.startActivity(launchIntent);
    }

    @Override
    public void startActivityForResult(BaseFragment baseFragment, Intent launchIntent, int requestCode) {
        mDefaultNavigation.startActivityForResult(baseFragment, launchIntent, requestCode);
    }

    @Override
    public void startActivity(final Intent launchIntent, final boolean finishCurrent) {
        mDefaultNavigation.startActivity(launchIntent, finishCurrent);
    }

    @Override
    public void startActivity(final Intent launchIntent, final Bundle bundle) {
        mDefaultNavigation.startActivity(launchIntent, bundle);
    }

    @Override
    public void startActivityForResult(final Intent launchIntent, final int requestCode) {
        mDefaultNavigation.startActivityForResult(launchIntent, requestCode);
    }

    @Override
    public void startActivityForResult(final BaseView baseView, final Intent launchIntent, final int requestCode) {
        mDefaultNavigation.startActivityForResult(baseView, launchIntent, requestCode);
    }

    @Override
    public void showFragment(final BaseFragment fragment) {
        showFragment(fragment, mCurrentHostId);
    }

    @Override
    public void showFragment(final BaseFragment fragment, final boolean addToBackStack) {
        showFragment(fragment, mCurrentHostId, addToBackStack);
    }

    @Override
    public void showFragment(final BaseFragment fragment, final int hostId) {
        showFragment(fragment, hostId, true);
    }

    @Override
    public void showFragment(final BaseFragment fragment,
                             final int hostId,
                             final boolean addToBackStack) {
        if (mCurrentFragment != null && addToBackStack) {
            pushFragmentToBackStack(mCurrentHostId, mCurrentFragment);
        }
        mCurrentHostId = hostId;
        replaceFragment(fragment);
    }

    @Override
    public void fragmentHostSelected(final int fragmentHostId) {
        if (mCurrentFragment != null) {
            pushFragmentToBackStack(mCurrentHostId, mCurrentFragment);
        }
        mCurrentHostId = fragmentHostId;
        BaseFragment baseFragment = popFragmentFromBackStack(mCurrentHostId);
        if (baseFragment == null) {
            baseFragment = mBackStackActivity.rootTabFragment(mCurrentHostId);
        }
        mBackStackActivity.selectTab(mCurrentHostId);
        replaceFragment(baseFragment);
    }

    /**
     * @return true if back event handled, if false returned the activiy/fragment
     * should handle the back press
     */
    @Override
    public boolean handleBack() {
        final Pair<Integer, BaseFragment> pair = popFragmentFromBackStack();
        if (pair != null) {
            backTo(pair.first, pair.second);
            return true;
        } else {
            mBackStackActivity.finish();
            return false;
        }
    }

    private boolean isRootTabFragment(@NonNull final BaseFragment baseFragment,
                                      final int fragmentHostId) {
        //TODO try to optimize - remove unnecessary fragment instantiation
        //TODO of fragments in method mBackStackActivity.rootTabFragment(fragmentHostId)
        return baseFragment.getClass() ==
                mBackStackActivity.rootTabFragment(fragmentHostId).getClass();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void backToRoot() {
        if (isRootTabFragment(mCurrentFragment, mCurrentHostId)) {
            return;
        }
        mBackStackManager.resetToRoot(mCurrentHostId);
        final BaseFragment rootFragment = popFragmentFromBackStack(mCurrentHostId);
        backTo(mCurrentHostId, rootFragment);
    }

    private void backTo(final int hostId, @NonNull final BaseFragment fragment) {
        if (hostId != mCurrentHostId) {
            mCurrentHostId = hostId;
            mBackStackActivity.selectTab(mCurrentHostId);
        }
        replaceFragment(fragment);
        mBackStackActivity.getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void release() {
        mBackStackManager = null;
        mCurrentFragment = null;
    }
}