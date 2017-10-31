package com.ruslanlyalko.agency.presentation.ui.splash;

import android.view.WindowManager;

import com.ruslanlyalko.agency.presentation.base.view.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.DashboardActivity;
import com.ruslanlyalko.agency.presentation.ui.login.LoginActivity;

/**
 * Created by Ruslan Lyalko
 * on 23.10.2017.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @Override
    protected void injectToComponent() {
        getComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return -1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getActiveUser();
    }

    @Override
    public void startLoginScreen() {
        getNavigation().startActivity(LoginActivity.getLaunchIntent(this), true);
    }

    @Override
    public void startDashboardScreen() {
        removeFullscreenFlag();
        getNavigation().startActivity(DashboardActivity.getLaunchIntent(this), true);
    }

    /**
     * This method should be called before you navigate from a
     * fullscreen activity {@link android.R.attr#windowFullscreen} to a new activity which
     * has no this attribute
     */
    private void removeFullscreenFlag() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // for activity animation.
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}
