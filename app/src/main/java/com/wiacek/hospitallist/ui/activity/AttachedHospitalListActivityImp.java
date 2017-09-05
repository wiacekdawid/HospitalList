package com.wiacek.hospitallist.ui.activity;

import java.lang.ref.WeakReference;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class AttachedHospitalListActivityImp implements AttachedHospitalListActivity {

    private final WeakReference<HospitalListActivity> hospitalListActivity;

    public AttachedHospitalListActivityImp(WeakReference<HospitalListActivity> hospitalListActivity) {
        this.hospitalListActivity = hospitalListActivity;
    }

    @Override
    public void selectItem(String organisationId) {
        hospitalListActivity.get().onListItemSelected(organisationId);
    }
}
