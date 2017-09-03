package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.base.ViewModel;

import io.realm.Realm;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable implements ViewModel {

    private AttachedHospitalListActivity attachedHospitalListActivity;
    private DataManager dataManager;
    private ListAdapter listAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Realm realm;

    public ListViewModel(AttachedHospitalListActivity attachedHospitalListActivity,
                         DataManager dataManager,
                         LinearLayoutManager linearLayoutManager,
                         ListAdapter listAdapter,
                         Realm realm) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
        this.dataManager = dataManager;
        this.listAdapter = listAdapter;
        this.realm = realm;
    }

    public void onRefresh() {
        dataManager.getHospitalList();
    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        realm.close();
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

}
