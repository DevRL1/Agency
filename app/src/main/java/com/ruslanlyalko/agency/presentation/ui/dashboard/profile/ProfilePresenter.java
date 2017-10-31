package com.ruslanlyalko.agency.presentation.ui.dashboard.profile;

import com.google.firebase.auth.FirebaseAuth;
import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.data.listeners.UserListener;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */


public class ProfilePresenter extends BasePresenter<ProfileView> implements UserListener {

    private final AgencyRepository mAgencyRepository;

    @Inject
    ProfilePresenter(AgencyRepository agencyRepository) {
        mAgencyRepository = agencyRepository;
    }

    void logoutClicked() {
        getView().showConfirmLogoutDialog();
    }

    void logout() {
        mAgencyRepository.logout();
        getView().showLoginScreen();
    }


    void fetchCurrentUserData() {
        mAgencyRepository.getCurrentUserData(this);
    }

    @Override
    public void updateUsers(UserItem user) {
        getView().updateUI(user);
    }

}
