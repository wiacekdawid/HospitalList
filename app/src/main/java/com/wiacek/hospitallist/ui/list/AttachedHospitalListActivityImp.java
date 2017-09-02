package com.wiacek.hospitallist.ui.list;

import com.wiacek.hospitallist.ui.HospitalListActivity;

import java.lang.ref.WeakReference;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class AttachedHospitalListActivityImp implements AttachedHospitalListActivity {

    private WeakReference<HospitalListActivity> hospitalListActivity;

    public AttachedHospitalListActivityImp(WeakReference<HospitalListActivity> hospitalListActivity) {
        this.hospitalListActivity = hospitalListActivity;
    }

    @Override
    public void selectItem() {
        hospitalListActivity.get().onListItemSelected();
    }
}
