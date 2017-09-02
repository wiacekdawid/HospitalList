package com.wiacek.hospitallist.di.modules;

import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.list.ListViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class ListFragmentModule {

    @FragmentScope
    @Provides
    ListViewModel proviedListViewModel(AttachedHospitalListActivity attachedHospitalListActivity) {
        return new ListViewModel(attachedHospitalListActivity);
    }
}
