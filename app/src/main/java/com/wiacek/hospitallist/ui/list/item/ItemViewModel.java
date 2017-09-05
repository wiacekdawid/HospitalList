package com.wiacek.hospitallist.ui.list.item;

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
    private String organisationType;
    private String sector;
    private String address;
    private String phone;

    public ItemViewModel(AttachedHospitalListActivity attachedHospitalListActivity) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
    }

    public void setItem(Organisation organisation) {
        organisationId = organisation.getOrganisationID();
        organisationName = organisation.getOrganisationName();
        organisationType = organisation.getOrganisationType();
        sector = organisation.getSector();

        String city = organisation.getAddress().getCity();
        String county = organisation.getAddress().getCounty();
        address = city.isEmpty() ? "" : city + ", ";
        address += county.isEmpty() ? "" : county;
        if(address.endsWith(", ")) {
            address = address.substring(0, address.length() - 2);
        }

        phone = organisation.getContactData().getPhone();
        notifyChange();
    }

    @Bindable
    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
        notifyPropertyChanged(BR.organisationName);
    }

    @Bindable
    public String getOrganisationType() {
        return organisationType;
    }

    public void setOrganisationType(String organisationType) {
        this.organisationType = organisationType;
        notifyPropertyChanged(BR.organisationType);
    }

    @Bindable
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
        notifyPropertyChanged(BR.sector);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    public void onClickItem() {
        attachedHospitalListActivity.selectItem(organisationId);
    }

    public void onClickCall() { attachedHospitalListActivity.call(phone);}
}
