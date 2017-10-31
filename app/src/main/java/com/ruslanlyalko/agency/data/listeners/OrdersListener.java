package com.ruslanlyalko.agency.data.listeners;

import com.ruslanlyalko.agency.data.models.OrderItem;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 31.10.2017.
 */

public interface OrdersListener {

    void updateOrders(List<OrderItem> orders);
}
