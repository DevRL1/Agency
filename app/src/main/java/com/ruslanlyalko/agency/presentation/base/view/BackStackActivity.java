/*
 * Copyright (C) 2016 JetRadar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruslanlyalko.agency.presentation.base.view;

import android.os.Bundle;


import com.ruslanlyalko.agency.presentation.base.navigation.BackStackNavigation;
import com.ruslanlyalko.agency.presentation.base.navigation.Navigation;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;


public abstract class BackStackActivity<P extends BasePresenter> extends BaseActivity<P> implements BaseView<P> {

    @Inject
    @Override
    public void setNavigation(@Named("multistack") final Navigation navigation) {
        super.setNavigation(navigation);
    }

    @Override
    public BackStackNavigation getNavigation() {
        return (BackStackNavigation) super.getNavigation();
    }

    public abstract BaseFragment rootTabFragment(final int tabId);

    public abstract void selectTab(final int id);

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getNavigation().restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        getNavigation().handleBack();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        getNavigation().saveState(outState);
    }

    @Override
    public void onDestroy() {
        getNavigation().release();
        super.onDestroy();
    }
}