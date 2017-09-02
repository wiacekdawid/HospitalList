package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;

import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable {

    private AttachedHospitalListActivity attachedHospitalListActivity;
    private DataManager dataManager;

    public ListViewModel(AttachedHospitalListActivity attachedHospitalListActivity,
                         DataManager dataManager) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
        this.dataManager = dataManager;
    }

    public void onRefresh() {
        //attachedHospitalListActivity.selectItem();
        dataManager.getHospitalList();
    }
}
