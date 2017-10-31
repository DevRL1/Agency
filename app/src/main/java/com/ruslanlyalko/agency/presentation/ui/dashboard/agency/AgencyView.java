package com.ruslanlyalko.agency.presentation.ui.dashboard.agency;


import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
public interface AgencyView extends BaseView<AgencyPresenter> {

    void updateOrders(List<OrderItem> orders);

}
