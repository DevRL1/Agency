package com.ruslanlyalko.agency.di.module;

import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.data.AgencyRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    static AgencyRepository provideAgencyRepository() {
        return new AgencyRepositoryImpl();
    }
}
