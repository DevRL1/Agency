package com.ruslanlyalko.agency.presentation.ui.dashboard.agency.create;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.common.DateUtils;
import com.ruslanlyalko.agency.data.models.OrderItem;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateOrderActivity extends BaseActivity<CreateOrderPresenter> implements CreateOrderView, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.text_client_name) TextView mNameText;
    @BindView(R.id.text_phone) TextView mPhoneText;
    @BindView(R.id.text_description) TextView mDescriptionText;
    @BindView(R.id.text_order_date) TextView mOrderDateText;

    private Calendar mDate = Calendar.getInstance();

    public static Intent getLaunchIntent(BaseActivity launchActivity) {
        return new Intent(launchActivity, CreateOrderActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
    }

    private void initDate() {
        mOrderDateText.setText(String.format(Locale.getDefault(), "%02d.%02d.%d",
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
        return R.layout.activity_create_order;
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
        mOrderDateText.setText(String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, monthOfYear + 1, year));
    }

    @OnClick(R.id.button_create)
    void onCreateClicked() {
        //TODO validating !!!
        String name = mNameText.getText().toString().trim();
        String phone = mPhoneText.getText().toString().trim();
        String desc = mDescriptionText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.name_cant_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        OrderItem order = new OrderItem();
        order.setClientName(name);
        order.setClientPhone(phone);
        order.setDescription(desc);
        order.setOrderDate(mDate.getTime());

        getPresenter().createOrder(order);
    }

    @Override
    public void orderCreated() {
        Toast.makeText(this, R.string.order_created, Toast.LENGTH_SHORT).show();
        finish();
    }


}
