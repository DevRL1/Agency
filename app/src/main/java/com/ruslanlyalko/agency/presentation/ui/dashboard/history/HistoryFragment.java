package com.ruslanlyalko.agency.presentation.ui.dashboard.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.presentation.base.view.BackStackFragment;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public class HistoryFragment extends BackStackFragment<HistoryPresenter> implements HistoryView {

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
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
            actionBar.setTitle(R.string.menu_history);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(false);
    }

}