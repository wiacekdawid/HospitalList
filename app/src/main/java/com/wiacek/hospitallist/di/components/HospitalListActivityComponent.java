package com.wiacek.hospitallist.di.components;

import com.wiacek.hospitallist.di.modules.DetailsFragmentModule;
import com.wiacek.hospitallist.di.modules.HospitalListActivityModule;
import com.wiacek.hospitallist.di.modules.ListFragmentModule;
import com.wiacek.hospitallist.di.scopes.ActivityScope;
import com.wiacek.hospitallist.ui.activity.HospitalListActivity;

import dagger.Subcomponent;

/**
 * Created by wiacek.dawid@gmail.com
 */

@ActivityScope
@Subcomponent(modules = {HospitalListActivityModule.class})
public interface HospitalListActivityComponent {
    void inject(HospitalListActivity hospitalListActivity);
    ListFragmentComponent add(ListFragmentModule listFragmentModule);
    DetailsFragmentComponent add(DetailsFragmentModule detailsFragmentModule);
}
