package com.ruslanlyalko.agency.presentation.ui.dashboard;

import com.ruslanlyalko.agency.data.AgencyRepository;
import com.ruslanlyalko.agency.di.annotations.ConfigPersistent;
import com.ruslanlyalko.agency.presentation.base.presenter.BasePresenter;


import javax.inject.Inject;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */

@ConfigPersistent
class DashboardPresenter extends BasePresenter<DashboardView> {

    AgencyRepository mAgencyRepository;

    @Inject
    DashboardPresenter(AgencyRepository agencyRepository) {
        mAgencyRepository = agencyRepository;
    }

}
