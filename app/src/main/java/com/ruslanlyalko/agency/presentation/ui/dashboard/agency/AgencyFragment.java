package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.common.Keys;
import com.ruslanlyalko.agency.presentation.base.view.BackStackFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.history.HistoryPresenter;
import com.ruslanlyalko.agency.presentation.ui.dashboard.history.HistoryView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public class AgencyFragment extends BackStackFragment<AgencyPresenter> implements AgencyView {


    public static AgencyFragment newInstance() {
        return new AgencyFragment();
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_agency;
    }

    @Override
    public void setupView(@Nullable final Bundle savedInstanceState) {
        initActionBar();
    }

    private void initActionBar() {
        final ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.menu_agency);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(false);
    }

    @Override
    public void showConfirmLogoutDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.logout_confirmation);
        builder.setPositiveButton(R.string.logout, (dialogInterface, i) -> getPresenter().logout());
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

    @Override
    public void showLoginScreen() {
        //  getNavigation().startActivity(LoginActivity.getLaunchIntent(getBaseActivity()), true);
    }
}