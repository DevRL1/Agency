package com.ruslanlyalko.agency.presentation.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.common.Keys;
import com.ruslanlyalko.agency.data.Memory;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.edit_login) EditText mLoginEdit;
    @BindView(R.id.edit_password) EditText mPasswordEdit;


    public static Intent getLaunchIntent(BaseActivity launchActivity) {
        return new Intent(launchActivity, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginEdit.setText(Memory.remindString(Keys.Memory.LOGIN, ""));
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.button_login)
    void onLoginClicked() {

        mLoginEdit.setError(null);
        mPasswordEdit.setError(null);

        String login = mLoginEdit.getText().toString().trim();
        String password = mPasswordEdit.getText().toString().trim();

        boolean checked = true;
        if (password.isEmpty()) {
            mPasswordEdit.setError(getString(R.string.login_error_empty_field));
            mPasswordEdit.requestFocus();
            checked = false;
        }
        if (login.isEmpty()) {
            mLoginEdit.setError(getString(R.string.login_error_empty_field));
            mLoginEdit.requestFocus();
            checked = false;
        }

        if (checked)
            getPresenter().login(login, password);
    }

    @Override
    public void startDashboardScreen() {
        Memory.rememberString(Keys.Memory.LOGIN, mLoginEdit.getText().toString().trim());
        removeFullscreenFlag();
        getNavigation().startActivity(DashboardActivity.getLaunchIntent(this));
    }

    @Override
    public void showErrorWrongLogin() {
        Toast.makeText(this, R.string.login_wrong_email_password, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method should be called before you navigate from a
     * fullscreen activity {@link android.R.attr#windowFullscreen} to a new activity which
     * has no this attribute
     */
    private void removeFullscreenFlag() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // for activity animation.
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
