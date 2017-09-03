package com.wiacek.hospitallist.ui.list.item;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.db.model.Organisation;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ItemViewModel extends BaseObservable {

    private String organisationName;

    @Bindable
    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
        notifyPropertyChanged(BR.organisationName);
    }

    public void setItem(Organisation organisation) {
        this.organisationName = organisation.getOrganisationName();
        notifyChange();
    }
}
