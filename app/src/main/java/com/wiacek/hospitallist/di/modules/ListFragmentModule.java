package com.wiacek.hospitallist.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.wiacek.hospitallist.api.DataGovService;
import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.list.AttachedListFragment;
import com.wiacek.hospitallist.ui.list.AttachedListFragmentImp;
import com.wiacek.hospitallist.ui.list.ListAdapter;
import com.wiacek.hospitallist.ui.list.ListFragment;
import com.wiacek.hospitallist.ui.list.ListViewHandler;
import com.wiacek.hospitallist.ui.list.ListViewModel;

import java.lang.ref.WeakReference;

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

    private final ListFragment listFragment;

    public ListFragmentModule(ListFragment listFragment) {
        this.listFragment = listFragment;
    }

    @FragmentScope
    @Provides
    AttachedListFragment provideAttachedListFragment() {
        return new AttachedListFragmentImp(new WeakReference<ListFragment>(listFragment));
    }

    @FragmentScope
    @Provides
    ListViewModel provideListViewModel() {
        return new ListViewModel();
    }

    @FragmentScope
    @Provides
    ListViewHandler provideListViewHandler(ListViewModel listViewModel,
                                            AttachedHospitalListActivity attachedHospitalListActivity,
                                            AttachedListFragment attachedListFragment,
                                            DataManager dataManager,
                                            Realm realm) {
        return new ListViewHandler(listViewModel,
                attachedHospitalListActivity, attachedListFragment, dataManager, realm);
    }

    @FragmentScope
    @Provides
    DataManager provideDataManager(DataGovService dataGovService,
                                   @Named("ApplicationContext") Context context) {
        return new DataManager(context, dataGovService);
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
