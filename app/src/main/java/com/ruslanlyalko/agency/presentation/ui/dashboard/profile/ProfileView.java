package com.ruslanlyalko.agency.presentation.ui.dashboard.profile;


import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
public interface ProfileView extends BaseView<ProfilePresenter> {

    void showConfirmLogoutDialog();

    void showLoginScreen();

    void updateUI(UserItem user);
}
