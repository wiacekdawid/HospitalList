package com.wiacek.hospitallist.ui.details;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;

import io.realm.Realm;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DetailsViewModel extends BaseObservable {

    private String organisationIdDetails;
    private String organisationNameDetails;

    public DetailsViewModel(String organisationId) {
        this.organisationIdDetails = organisationId;
        Realm realm = Realm.getDefaultInstance();
        try {
            Organisation organisation = OrganisationDbHelper
                    .getOrganisationById(realm, organisationIdDetails);
            this.organisationNameDetails = organisation.getOrganisationName();
        }
        finally {
            realm.close();
        }
    }

    @Bindable
    public String getOrganisationNameDetails() {
        return organisationNameDetails;
    }

    public void setOrganisationNameDetails(String organisationNameDetails) {
        this.organisationNameDetails = organisationNameDetails;
        notifyPropertyChanged(BR.organisationNameDetails);
    }
}
