package com.wiacek.hospitallist.ui.details;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;

import io.realm.Realm;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DetailsViewModel extends BaseObservable implements Parcelable {

    private String organisationIdDetails;
    private String organisationNameDetails;
    private String organisationTypeDetails;
    private String organisationSubtypeDetails;
    private String organisationSectorDetails;
    private boolean organisationIsPimsManagedDetails;
    private String organisationAddressDetails;
    private String organisationAddress1Details;
    private String organisationAddress2Details;
    private String organisationAddress3Details;
    private String parentNameDetails;
    private String parentOdsCodeDetails;
    private String phoneDetails;
    private String emailDetails;
    private String websiteDetails;
    private String faxDetails;

    public DetailsViewModel(String organisationId) {
        this.organisationIdDetails = organisationId;
        Realm realm = Realm.getDefaultInstance();
        try {
            Organisation organisation = OrganisationDbHelper
                    .getOrganisationById(realm, organisationIdDetails);
            organisationNameDetails = organisation.getOrganisationName();
            organisationTypeDetails = organisation.getOrganisationType();
            organisationSubtypeDetails = organisation.getSubType();
            organisationSectorDetails = organisation.getSector();
            organisationIsPimsManagedDetails = organisation.isPimsManaged();

            organisationAddressDetails = organisation.getAddress().getCity().isEmpty() ?
                    "" : organisation.getAddress().getCity() + ", ";
            organisationAddressDetails += organisation.getAddress().getCounty().isEmpty() ?
                    "" : organisation.getAddress().getCounty() + ", ";
            organisationAddressDetails += organisation.getAddress().getPostCode().isEmpty() ?
                    "" : organisation.getAddress().getPostCode();
            if(organisationAddressDetails.endsWith(", ")) {
                organisationAddressDetails = organisationAddressDetails.substring(0, organisationAddressDetails.length()-2);
            }

            organisationAddress1Details = organisation.getAddress().getAddress1();
            organisationAddress2Details = organisation.getAddress().getAddress2();
            organisationAddress3Details = organisation.getAddress().getAddress3();
            parentNameDetails = organisation.getParentName();
            parentOdsCodeDetails = organisation.getParentODSCode();
            phoneDetails = organisation.getContactData().getPhone();
            emailDetails = organisation.getContactData().getEmail();
            websiteDetails = organisation.getContactData().getWebsite();
            faxDetails = organisation.getContactData().getFax();
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

    @Bindable
    public String getOrganisationTypeDetails() {
        return organisationTypeDetails;
    }

    public void setOrganisationTypeDetails(String organisationTypeDetails) {
        this.organisationTypeDetails = organisationTypeDetails;
        notifyPropertyChanged(BR.organisationTypeDetails);
    }

    @Bindable
    public String getOrganisationSubtypeDetails() {
        return organisationSubtypeDetails;
    }

    public void setOrganisationSubtypeDetails(String organisationSubtypeDetails) {
        this.organisationSubtypeDetails = organisationSubtypeDetails;
        notifyPropertyChanged(BR.organisationSubtypeDetails);
    }

    @Bindable
    public String getOrganisationSectorDetails() {
        return organisationSectorDetails;
    }

    public void setOrganisationSectorDetails(String organisationSectorDetails) {
        this.organisationSectorDetails = organisationSectorDetails;
        notifyPropertyChanged(BR.organisationSectorDetails);
    }

    @Bindable
    public boolean getOrganisationIsPimsManagedDetails() {
        return organisationIsPimsManagedDetails;
    }

    public void setOrganisationIsPimsManagedDetails(boolean organisationIsPimsManagedDetails) {
        this.organisationIsPimsManagedDetails = organisationIsPimsManagedDetails;
        notifyPropertyChanged(BR.organisationIsPimsManagedDetails);
    }

    @Bindable
    public String getOrganisationAddressDetails() {
        return organisationAddressDetails;
    }

    public void setOrganisationAddressDetails(String organisationAddressDetails) {
        this.organisationAddressDetails = organisationAddressDetails;
        notifyPropertyChanged(BR.organisationAddressDetails);
    }

    @Bindable
    public String getOrganisationAddress1Details() {
        return organisationAddress1Details;
    }

    public void setOrganisationAddress1Details(String organisationAddress1Details) {
        this.organisationAddress1Details = organisationAddress1Details;
        notifyPropertyChanged(BR.organisationAddress1Details);
    }

    @Bindable
    public String getOrganisationAddress2Details() {
        return organisationAddress2Details;
    }

    public void setOrganisationAddress2Details(String organisationAddress2Details) {
        this.organisationAddress2Details = organisationAddress2Details;
        notifyPropertyChanged(BR.organisationAddress2Details);
    }

    @Bindable
    public String getOrganisationAddress3Details() {
        return organisationAddress3Details;
    }

    public void setOrganisationAddress3Details(String organisationAddress3Details) {
        this.organisationAddress3Details = organisationAddress3Details;
        notifyPropertyChanged(BR.organisationAddress3Details);
    }

    @Bindable
    public String getParentNameDetails() {
        return parentNameDetails;
    }

    public void setParentNameDetails(String parentNameDetails) {
        this.parentNameDetails = parentNameDetails;
        notifyPropertyChanged(BR.parentNameDetails);
    }

    @Bindable
    public String getParentOdsCodeDetails() {
        return parentOdsCodeDetails;
    }

    public void setParentOdsCodeDetails(String parentOdsCodeDetails) {
        this.parentOdsCodeDetails = parentOdsCodeDetails;
        notifyPropertyChanged(BR.parentOdsCodeDetails);
    }

    @Bindable
    public String getPhoneDetails() {
        return phoneDetails;
    }

    public void setPhoneDetails(String phoneDetails) {
        this.phoneDetails = phoneDetails;
        notifyPropertyChanged(BR.phoneDetails);
    }

    @Bindable
    public String getEmailDetails() {
        return emailDetails;
    }

    public void setEmailDetails(String emailDetails) {
        this.emailDetails = emailDetails;
        notifyPropertyChanged(BR.emailDetails);
    }

    @Bindable
    public String getWebsiteDetails() {
        return websiteDetails;
    }

    public void setWebsiteDetails(String websiteDetails) {
        this.websiteDetails = websiteDetails;
        notifyPropertyChanged(BR.websiteDetails);
    }

    @Bindable
    public String getFaxDetails() {
        return faxDetails;
    }

    public void setFaxDetails(String faxDetails) {
        this.faxDetails = faxDetails;
        notifyPropertyChanged(BR.faxDetails);
    }

    protected DetailsViewModel(Parcel in) {
        organisationIdDetails = in.readString();
        organisationNameDetails = in.readString();
        organisationTypeDetails = in.readString();
        organisationSubtypeDetails = in.readString();
        organisationSectorDetails = in.readString();
        organisationIsPimsManagedDetails = in.readByte() != 0x00;
        organisationAddressDetails = in.readString();
        organisationAddress1Details = in.readString();
        organisationAddress2Details = in.readString();
        organisationAddress3Details = in.readString();
        parentNameDetails = in.readString();
        parentOdsCodeDetails = in.readString();
        phoneDetails = in.readString();
        emailDetails = in.readString();
        websiteDetails = in.readString();
        faxDetails = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(organisationIdDetails);
        dest.writeString(organisationNameDetails);
        dest.writeString(organisationTypeDetails);
        dest.writeString(organisationSubtypeDetails);
        dest.writeString(organisationSectorDetails);
        dest.writeByte((byte) (organisationIsPimsManagedDetails ? 0x01 : 0x00));
        dest.writeString(organisationAddressDetails);
        dest.writeString(organisationAddress1Details);
        dest.writeString(organisationAddress2Details);
        dest.writeString(organisationAddress3Details);
        dest.writeString(parentNameDetails);
        dest.writeString(parentOdsCodeDetails);
        dest.writeString(phoneDetails);
        dest.writeString(emailDetails);
        dest.writeString(websiteDetails);
        dest.writeString(faxDetails);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DetailsViewModel> CREATOR = new Parcelable.Creator<DetailsViewModel>() {
        @Override
        public DetailsViewModel createFromParcel(Parcel in) {
            return new DetailsViewModel(in);
        }

        @Override
        public DetailsViewModel[] newArray(int size) {
            return new DetailsViewModel[size];
        }
    };
}
