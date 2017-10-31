package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.data.listeners.UsersListener;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
@ConfigPersistent
public class ManagePresenter extends BasePresenter<ManageView> implements UsersListener {

    private final AgencyRepository mAgencyRepository;

    @Inject
    ManagePresenter(AgencyRepository agencyRepository) {
        mAgencyRepository = agencyRepository;
    }


    void fetchUser() {
        mAgencyRepository.getUsers(this);
    }

    @Override
    public void updateUsers(List<UserItem> users) {
        if (!isViewAttached()) return;
        getView().showUsers(users);
    }

    void updateUser(UserItem user) {
        mAgencyRepository.updateUser(user).addOnCompleteListener(task -> {
            if (!isViewAttached()) return;
            getView().showProgress(false);
            getView().saved();
        });
        getView().showProgress(true);
    }
}
