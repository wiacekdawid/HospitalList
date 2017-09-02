package com.wiacek.hospitallist.data.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class Organisation extends RealmObject {
    @PrimaryKey
    private String organisationID;
    private String organisationCode;
    private String organisationType;
    private String subType;
    private String sector;
    private String organisationStatus;
    private boolean isPimsManaged;
    private String organisationName;
    private Address address;
    private ContactData contactData;

    public Organisation() {}

    public Organisation(String organisationID, String organisationCode,
                        String organisationType, String subType, String sector,
                        String organisationStatus, boolean isPimsManaged,
                        String organisationName, Address address,
                        ContactData contactData) {
        this.organisationID = organisationID;
        this.organisationCode = organisationCode;
        this.organisationType = organisationType;
        this.subType = subType;
        this.sector = sector;
        this.organisationStatus = organisationStatus;
        this.isPimsManaged = isPimsManaged;
        this.organisationName = organisationName;
        this.address = address;
        this.contactData = contactData;
    }

    public String getOrganisationID() {
        return organisationID;
    }

    public void setOrganisationID(String organisationID) {
        this.organisationID = organisationID;
    }

    public String getOrganisationCode() {
        return organisationCode;
    }

    public void setOrganisationCode(String organisationCode) {
        this.organisationCode = organisationCode;
    }

    public String getOrganisationType() {
        return organisationType;
    }

    public void setOrganisationType(String organisationType) {
        this.organisationType = organisationType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getOrganisationStatus() {
        return organisationStatus;
    }

    public void setOrganisationStatus(String organisationStatus) {
        this.organisationStatus = organisationStatus;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public boolean isPimsManaged() {
        return isPimsManaged;
    }

    public void setPimsManaged(boolean pimsManaged) {
        isPimsManaged = pimsManaged;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }
}
