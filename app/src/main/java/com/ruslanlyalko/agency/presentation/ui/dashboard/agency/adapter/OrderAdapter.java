package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.OrderItem;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private final List<OrderItem> mDataSource;
    private final OnItemClickListener mOnItemClickListener;

    public OrderAdapter(final List<OrderItem> dataSource,
                        final OnItemClickListener onItemClickListener) {
        mDataSource = dataSource;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(itemView, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, final int position) {
        holder.bindData(mDataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
}
