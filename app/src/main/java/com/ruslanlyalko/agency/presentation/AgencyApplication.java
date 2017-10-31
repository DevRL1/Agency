package com.ruslanlyalko.agency.presentation;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.FirebaseDatabase;
import com.ruslanlyalko.agency.data.Memory;
import com.ruslanlyalko.agency.di.component.ApplicationComponent;
import com.ruslanlyalko.agency.di.component.DaggerApplicationComponent;
import com.ruslanlyalko.agency.di.module.ApplicationModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */
public class AgencyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        getComponent().inject(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Memory.init(this, "agency");
    }

    public static AgencyApplication get(final Context context) {
        return (AgencyApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
