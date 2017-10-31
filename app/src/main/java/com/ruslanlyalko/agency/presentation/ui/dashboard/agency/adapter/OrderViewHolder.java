package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.OrderItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class OrderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_title) TextView mDescriptionText;

    private final OnItemClickListener mOnItemClickListener;

    OrderViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mOnItemClickListener = onItemClickListener;
    }

    void bindData(final OrderItem order) {
        mDescriptionText.setText(order.getDescription());
    }

    @OnClick(R.id.text_title)
    void onItemClicked(final View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClicked(view, getAdapterPosition());
        }
    }
}
