package com.ruslanlyalko.agency.presentation.ui.dashboard.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BackStackFragment;
import com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.ManageActivity;
import com.ruslanlyalko.agency.presentation.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

public class ProfileFragment extends BackStackFragment<ProfilePresenter> implements ProfileView {

    @BindView(R.id.text_user_name) TextView mUserNameText;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_profile;
    }


    @Override
    public void setupView(@Nullable final Bundle savedInstanceState) {
        initActionBar();
        getPresenter().fetchCurrentUserData();
    }

    @OnClick(R.id.button_logout)
    void onLogoutClicked() {
        getPresenter().logoutClicked();
    }

    private void initActionBar() {
        final ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.menu_profile);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_manage) {
            getNavigation().startActivity(ManageActivity.getLaunchIntent(getBaseActivity()));
            return true;
        }
        return false;
    }

    @Override
    public void showConfirmLogoutDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.logout_confirmation);
        builder.setPositiveButton(R.string.logout, (dialogInterface, i) -> getPresenter().logout());
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

    @Override
    public void showLoginScreen() {
        getNavigation().startActivity(LoginActivity.getLaunchIntent(getBaseActivity()), true);
    }

    @Override
    public void updateUI(UserItem user) {
        if (user == null) {
            mUserNameText.setText("-");
            return;
        }
        mUserNameText.setText(user.getName());
    }
}