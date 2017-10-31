package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage;

import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;
import com.ruslanlyalko.agency.presentation.ui.login.LoginPresenter;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public interface ManageView extends BaseView<ManagePresenter> {

    void showUsers(List<UserItem> users);

    void saved();
}
