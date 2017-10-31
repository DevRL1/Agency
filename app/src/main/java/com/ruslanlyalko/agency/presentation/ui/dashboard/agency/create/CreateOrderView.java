package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create;

import com.ruslanlyalko.agency.presentation.base.view.BaseView;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

interface CreateOrderView extends BaseView<CreateOrderPresenter> {

    void userRegistered();

    void wrongLoginData();
}
