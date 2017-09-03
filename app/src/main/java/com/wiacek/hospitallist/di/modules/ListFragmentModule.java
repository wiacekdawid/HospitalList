package com.wiacek.hospitallist.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.wiacek.hospitallist.api.DataGovService;
import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.list.ListAdapter;
import com.wiacek.hospitallist.ui.list.ListViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class ListFragmentModule {

    @FragmentScope
    @Provides
    ListViewModel provideListViewModel(AttachedHospitalListActivity attachedHospitalListActivity,
                                       DataManager dataManager,
                                       LinearLayoutManager linearLayoutManager,
                                       ListAdapter listAdapter,
                                       Realm realm) {
        return new ListViewModel(attachedHospitalListActivity, dataManager, linearLayoutManager, listAdapter, realm);
    }

    @FragmentScope
    @Provides
    DataManager provideDataManager(DataGovService dataGovService,
                                   @Named("ApplicationContext") Context context) {
        return new DataManager(context, dataGovService);
    }

    @FragmentScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager(
            @Named("ApplicationContext") Context context) {
        return new LinearLayoutManager(context);
    }

    @FragmentScope
    @Provides
    ListAdapter provideListAdapter(Realm realm,
                                   AttachedHospitalListActivity attachedHospitalListActivity) {
        RealmResults<Organisation> results;
        results = OrganisationDbHelper.getOrganisations(realm);
        return new ListAdapter(results, true, attachedHospitalListActivity);
    }

    @FragmentScope
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
