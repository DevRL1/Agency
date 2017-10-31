package com.ruslanlyalko.agency.presentation.ui.dashboard.profile.manage.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.UserItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.text_name) TextView mNameText;
    @BindView(R.id.text_role) TextView mRoleText;
    @BindView(R.id.text_phone) TextView mPhoneText;
    @BindView(R.id.text_email) TextView mEmailText;
    @BindView(R.id.text_start_date) TextView mStartDateText;
    @BindView(R.id.text_password) TextView mPasswordText;

    private Calendar mDate = Calendar.getInstance();

    public static Intent getLaunchIntent(BaseActivity launchActivity) {
        return new Intent(launchActivity, RegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
    }

    private void initDate() {
        mStartDateText.setText(String.format(Locale.getDefault(), "%02d.%02d.%d",
                mDate.get(Calendar.DAY_OF_MONTH),
                mDate.get(Calendar.MONTH) + 1,
                mDate.get(Calendar.YEAR)));
    }

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.text_order_date)
    void onDatePickerClicked() {
        final DatePickerDialog dpd = DatePickerDialog.newInstance(this,
                mDate.get(Calendar.YEAR),
                mDate.get(Calendar.MONTH),
                mDate.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override
    public void onDateSet(final DatePickerDialog view, final int year, final int monthOfYear, final int dayOfMonth) {
        mDate.set(year, monthOfYear, dayOfMonth);
        mStartDateText.setText(String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, monthOfYear + 1, year));
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
        user.setStartDate(mDate.getTime());
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
