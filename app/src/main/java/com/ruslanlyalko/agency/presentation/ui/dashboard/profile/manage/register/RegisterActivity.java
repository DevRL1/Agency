package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.text_name) TextView mNameText;
    @BindView(R.id.text_role) TextView mRoleText;
    @BindView(R.id.text_phone) TextView mPhoneText;
    @BindView(R.id.text_email) TextView mEmailText;
    @BindView(R.id.text_start_date) TextView mStartDateText;
    @BindView(R.id.text_password) TextView mPasswordText;


    public static Intent getLaunchIntent(BaseActivity launchActivity) {
        return new Intent(launchActivity, RegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_create_account;
    }


    @OnClick(R.id.button_create)
    void onCreateClicked() {
        //TODO validating !!!
        String name = mNameText.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.name_cant_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        UserItem user = new UserItem();
        user.setName(name);
        user.setRole(mRoleText.getText().toString().trim());
        user.setPhone(mPhoneText.getText().toString().trim());
        user.setEmail(mEmailText.getText().toString().trim());
        getPresenter().registerUser(user, password);
    }

    @Override
    public void userRegistered() {
        Toast.makeText(this, R.string.user_created, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void wrongLoginData() {
        Toast.makeText(this, R.string.wrong_login_data, Toast.LENGTH_SHORT).show();

    }
}
