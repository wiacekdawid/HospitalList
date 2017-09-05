package com.wiacek.hospitallist.ui.list;

import android.databinding.Bindable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.base.ViewHandler;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewHandler implements ViewHandler {

    private ListViewModel listViewModel;
    private AttachedHospitalListActivity attachedHospitalListActivity;
    private AttachedListFragment attachedListFragment;
    private DataManager dataManager;
    private Realm realm;

    public ListViewHandler(ListViewModel listViewModel,
                           AttachedHospitalListActivity attachedHospitalListActivity,
                           AttachedListFragment attachedListFragment,
                           DataManager dataManager,
                           Realm realm) {
        this.listViewModel = listViewModel;
        this.setAttachedHospitalListActivity(attachedHospitalListActivity);
        this.attachedListFragment = attachedListFragment;
        this.dataManager = dataManager;
        this.realm = realm;
    }

    public void onRefresh() {
        dataManager.getHospitalList();
    }

    @Override
    public void onAttach() {}

    @Override
    public void onDetach() {
        realm.close();
    }


    public void onCheckedOnlyNHSOrganisationsChanged() {
        listViewModel.setOnlyNHSOrganisationsChecked(!listViewModel.isOnlyNHSOrganisationsChecked());
        attachedListFragment.onUpdatedData();
    }

    public RealmResults<Organisation> getAdapterDataFromDb() {
        if(listViewModel.isOnlyNHSOrganisationsChecked()) {
            return OrganisationDbHelper.getNHSOrganisations(realm);
        }
        return OrganisationDbHelper.getOrganisations(realm);
    }

    public AttachedHospitalListActivity getAttachedHospitalListActivity() {
        return attachedHospitalListActivity;
    }

    public void setAttachedHospitalListActivity(AttachedHospitalListActivity attachedHospitalListActivity) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
    }

    public ListViewModel getListViewModel() {
        return listViewModel;
    }

    public void setListViewModel(ListViewModel listViewModel) {
        this.listViewModel = listViewModel;
    }
}
