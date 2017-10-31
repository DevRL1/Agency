package com.ruslanlyalko.agency.presentation.ui.dashboard.costs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.presentation.base.view.BackStackFragment;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public class CostsFragment extends BackStackFragment<CostsPresenter> implements CostsView {

    public static CostsFragment newInstance() {
        return new CostsFragment();
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_history;
    }


    @Override
    public void setupView(@Nullable final Bundle savedInstanceState) {
        initActionBar();
    }

    private void initActionBar() {
        final ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.menu_costs);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(false);
    }

}