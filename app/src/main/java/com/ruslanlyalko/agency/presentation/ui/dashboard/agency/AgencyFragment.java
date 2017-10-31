package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.presentation.base.view.BackStackFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.adapter.OnItemClickListener;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.adapter.OrderAdapter;
import com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create.CreateOrderActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.ManageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public class AgencyFragment extends BackStackFragment<AgencyPresenter> implements AgencyView, OnItemClickListener {

    @BindView(R.id.list_orders) RecyclerView mOrdersList;
    @BindView(R.id.text_orders_done) TextView mOrdersDoneText;
    @BindView(R.id.text_hrs_done) TextView mHrsDoneText;
    @BindView(R.id.text_remains) TextView mRemainsText;

    private List<OrderItem> mDataSource = new ArrayList<>();
    private RecyclerView.Adapter mOrdersAdapter;

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
        initRecyclerView();
        getPresenter().fetchAllOrders();
    }

    private void initRecyclerView() {
        mOrdersAdapter = new OrderAdapter(mDataSource, this);
        mOrdersList.setItemAnimator(new DefaultItemAnimator());
        mOrdersList.setLayoutManager(new LinearLayoutManager(getContext()));
        mOrdersList.setAdapter(mOrdersAdapter);
    }

    private void initActionBar() {
        final ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.menu_agency);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_agency, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            getNavigation().startActivity(CreateOrderActivity.getLaunchIntent(getBaseActivity()));
            return true;
        }
        return false;
    }

    @Override
    public void onItemClicked(View view, int position) {
        // order clicked
    }

    @Override
    public void updateOrders(List<OrderItem> orders) {
        mDataSource.clear();
        mDataSource.addAll(orders);
        mOrdersAdapter.notifyDataSetChanged();
        updateStatistic();
    }

    private void updateStatistic() {
        //TODO implement this logic right !!!
        mOrdersDoneText.setText(String.valueOf(mDataSource.size()));
        mHrsDoneText.setText(String.valueOf(mDataSource.size()));
        mRemainsText.setText(String.valueOf(mDataSource.size()));
    }
}