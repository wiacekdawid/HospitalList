package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.base.ViewModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable implements ViewModel {

    private AttachedHospitalListActivity attachedHospitalListActivity;
    private AttachedListFragment attachedListFragment;
    private DataManager dataManager;
    private Realm realm;
    private boolean onlyNHSOrganisationsChecked;

    public ListViewModel(AttachedHospitalListActivity attachedHospitalListActivity,
                         AttachedListFragment attachedListFragment,
                         DataManager dataManager,
                         Realm realm) {
        this.setAttachedHospitalListActivity(attachedHospitalListActivity);
        this.attachedListFragment = attachedListFragment;
        this.dataManager = dataManager;
        this.realm = realm;
    }

    public void onRefresh() {
        dataManager.getHospitalList();
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        realm.close();
    }

    @Bindable
    public boolean isOnlyNHSOrganisationsChecked() {
        return onlyNHSOrganisationsChecked;
    }

    public void setOnlyNHSOrganisationsChecked(boolean onlyNHSOrganisationsChecked) {
        this.onlyNHSOrganisationsChecked = onlyNHSOrganisationsChecked;
        notifyPropertyChanged(BR.onlyNHSOrganisationsChecked);
    }

    public void onCheckedOnlyNHSOrganisationsChanged() {
        onlyNHSOrganisationsChecked = !onlyNHSOrganisationsChecked;
        attachedListFragment.onUpdatedData();
    }

    public RealmResults<Organisation> getAdapterDataFromDb() {
        if(onlyNHSOrganisationsChecked) {
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
}
