package com.ruslanlyalko.agency.presentation.ui.login;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;
import com.ruslanlyalko.agency.presentation.ui.splash.SplashView;

import javax.inject.Inject;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginView> implements OnCompleteListener<AuthResult> {

    @Inject
    LoginPresenter() {
    }

    void login(String login, String password) {
        getView().showProgress(true);
        FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        getView().showProgress(false);
        if (task.isSuccessful()) {
            getView().startDashboardScreen();
        } else {
            getView().showErrorWrongLogin();
        }
    }
}
