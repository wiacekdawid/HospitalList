package com.wiacek.hospitallist.ui.list.item;

import android.content.ClipData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ItemViewModel extends BaseObservable {

    private AttachedHospitalListActivity attachedHospitalListActivity;
    private String organisationId;
    private String organisationName;

    public ItemViewModel(AttachedHospitalListActivity attachedHospitalListActivity) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
    }

    @Bindable
    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
        notifyPropertyChanged(BR.organisationName);
    }

    public void setItem(Organisation organisation) {
        this.organisationId = organisation.getOrganisationID();
        this.organisationName = organisation.getOrganisationName();
        notifyChange();
    }

    public void onClickItem() {
        attachedHospitalListActivity.selectItem(organisationId);
    }
}
