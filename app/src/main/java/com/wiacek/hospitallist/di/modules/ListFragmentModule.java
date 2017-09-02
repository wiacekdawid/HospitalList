package com.wiacek.hospitallist.di.modules;

import com.wiacek.hospitallist.api.DataGovService;
import com.wiacek.hospitallist.data.DataManager;
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
    ListViewModel provideListViewModel(AttachedHospitalListActivity attachedHospitalListActivity,
                                       DataManager dataManager) {
        return new ListViewModel(attachedHospitalListActivity, dataManager);
    }

    @FragmentScope
    @Provides
    DataManager provideDataManager(DataGovService dataGovService) {
        return new DataManager(dataGovService);
    }
}
