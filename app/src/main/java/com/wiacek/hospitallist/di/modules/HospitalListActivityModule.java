package com.wiacek.hospitallist.di.modules;

import com.wiacek.hospitallist.di.scopes.ActivityScope;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivityImp;
import com.wiacek.hospitallist.ui.activity.HospitalListActivity;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class HospitalListActivityModule {

    private final HospitalListActivity hospitalListActivity;

    public HospitalListActivityModule(HospitalListActivity hospitalListActivity) {
        this.hospitalListActivity = hospitalListActivity;
    }

    @ActivityScope
    @Provides
    AttachedHospitalListActivity proviedAttachedHospitalListActivity() {
        return new AttachedHospitalListActivityImp(new WeakReference<>(hospitalListActivity));
    }
}
