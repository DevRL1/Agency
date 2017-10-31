package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.register;

import com.ruslanlyalko.agency.presentation.base.view.BaseView;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

interface RegisterView extends BaseView<RegisterPresenter> {

    void userRegistered();

    void wrongLoginData();
}
