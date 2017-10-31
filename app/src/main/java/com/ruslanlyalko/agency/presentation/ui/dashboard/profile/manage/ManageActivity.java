package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.common.DateUtils;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.register.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManageActivity extends BaseActivity<ManagePresenter> implements ManageView, AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinner_accounts) Spinner mAccountSpinner;
    @BindView(R.id.text_name) TextView mNameText;
    @BindView(R.id.text_role) TextView mRoleText;
    @BindView(R.id.text_phone) TextView mPhoneText;
    @BindView(R.id.text_email) TextView mEmailText;
    @BindView(R.id.text_start_date) TextView mStartDateText;


    private List<UserItem> mUsers = new ArrayList<>();
    private UserItem mUser;

    public static Intent getLaunchIntent(BaseActivity launchActivity) {
        return new Intent(launchActivity, ManageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSpinner();
        if (savedInstanceState == null)
            getPresenter().fetchUser();
        //else restore

    }

    private void initSpinner() {
        mAccountSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_manage;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            getNavigation().startActivity(RegisterActivity.getLaunchIntent(this));
            return true;
        }
        return false;
    }

    @Override
    public void showUsers(List<UserItem> users) {
        mUsers.clear();
        mUsers.addAll(users);

        updateSpinner();
    }

    @Override
    public void saved() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }

    private void updateSpinner() {
        List<String> list = new ArrayList<>();
        for (UserItem user : mUsers) {
            list.add(user.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountSpinner.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateFields(mUsers.get(position));
    }

    private void updateFields(UserItem user) {
        mUser = user;
        if (user == null) {
            mNameText.setText("-");
            mRoleText.setText("-");
            mPhoneText.setText("-");
            mEmailText.setText("-");
            mStartDateText.setText("-");
            return;
        }
        mNameText.setText(user.getName());
        mRoleText.setText(user.getRole());
        mPhoneText.setText(user.getPhone());
        mEmailText.setText(user.getEmail());
        mStartDateText.setText(DateUtils.getDate(user.getStartDate()));
    }

    @OnClick(R.id.button_update)
    void onUpdateClicked() {
        if (mUser == null) return;
        String name = mNameText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.name_cant_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        mUser.setName(name);
        mUser.setRole(mRoleText.getText().toString().trim());
        mUser.setPhone(mPhoneText.getText().toString().trim());
        mUser.setEmail(mEmailText.getText().toString().trim());
        getPresenter().updateUser(mUser);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
