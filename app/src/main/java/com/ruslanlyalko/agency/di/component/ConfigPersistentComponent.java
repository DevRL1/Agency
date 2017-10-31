package com.ruslanlyalko.agency.di.component;

import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.di.module.ActivityModule;
import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(final ActivityModule activityModule);
}