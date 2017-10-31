package com.ruslanlyalko.agency.di.component;

import android.app.Application;
import android.content.Context;

import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.di.annotations.ApplicationContext;
import com.ruslanlyalko.agency.di.module.ApplicationModule;
import com.ruslanlyalko.agency.di.module.RepositoryModule;
import com.ruslanlyalko.agency.presentation.AgencyApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class,})
public interface ApplicationComponent {

    void inject(final AgencyApplication application);

    @ApplicationContext
    Context context();

    Application application();

    AgencyRepository agencyRepository();
}