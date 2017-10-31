package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.presentation.base.view.BackStackActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.ruslanlyalko.agency.presentation.base.view.BaseFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.AgencyFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.costs.CostsFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.ProfileFragment;

import butterknife.BindView;

public class DashboardActivity extends BackStackActivity<DashboardPresenter>
        implements DashboardView, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int TAB_AGENCY = 0;
    private static final int TAB_HISTORY = 1;
    private static final int TAB_PROFILE = 2;

    @BindView(R.id.bottom_navigation) BottomNavigationView mBottomNavigation;


    public static Intent getLaunchIntent(final BaseActivity launchActivity) {
        Intent intent = new Intent(launchActivity, DashboardActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseExtras();
        initBottomNavigation();

        if (savedInstanceState == null) {
            getNavigation().fragmentHostSelected(TAB_AGENCY);
        }

    }

    private void parseExtras() {
//        final Intent intent;
//        if ((intent = getIntent()) == null) {
//            finish();
//            return;
//        }
//
    }

    private void initBottomNavigation() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_dashboard;
    }

    @Override
    @IdRes
    public int getFragmentContainer() {
        return R.id.container;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int newTabId;
        switch (item.getItemId()) {
            case R.id.menu_agency:
                newTabId = TAB_AGENCY;
                break;
            case R.id.menu_costs:
                newTabId = TAB_HISTORY;
                break;
            case R.id.menu_profile:
                newTabId = TAB_PROFILE;
                break;
            default:
                newTabId = TAB_AGENCY;
        }
        getNavigation().fragmentHostSelected(newTabId);
        invalidateOptionsMenu();
        return false;
    }

    @Override
    public BaseFragment rootTabFragment(int tabId) {
        switch (tabId) {
            case TAB_AGENCY:
                return AgencyFragment.newInstance();
            case TAB_HISTORY:
                return CostsFragment.newInstance();
            case TAB_PROFILE:
                return ProfileFragment.newInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void selectTab(int id) {
        mBottomNavigation.getMenu().getItem(id).setChecked(true);
        invalidateOptionsMenu();
    }

}
