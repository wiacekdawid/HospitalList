package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;

import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable {

    private AttachedHospitalListActivity attachedHospitalListActivity;

    public ListViewModel(AttachedHospitalListActivity attachedHospitalListActivity) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
    }

    public void onRefresh() {
        attachedHospitalListActivity.selectItem();
    }
}
