package com.wiacek.hospitallist.data.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class Organisation extends RealmObject {
    @PrimaryKey
    private String id;
    private String code;
    private String type;
    private String subType;
    private String sector;
    private String status;
    private boolean isPimsManaged;
    private String name;
    private String parentName;
    private String parentODSCode;
    private Address address;
    private ContactData contactData;

    public Organisation() {}

    public Organisation(String organisationID, String organisationCode,
                        String organisationType, String subType, String sector,
                        String organisationStatus, boolean isPimsManaged,
                        String organisationName, String parentName,
                        String parentODSCode, Address address,
                        ContactData contactData) {
        this.id = organisationID;
        this.code = organisationCode;
        this.type = organisationType;
        this.subType = subType;
        this.sector = sector;
        this.status = organisationStatus;
        this.isPimsManaged = isPimsManaged;
        this.name = organisationName;
        this.setParentName(parentName);
        this.setParentODSCode(parentODSCode);
        this.address = address;
        this.contactData = contactData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentODSCode() {
        return parentODSCode;
    }

    public void setParentODSCode(String parentODSCode) {
        this.parentODSCode = parentODSCode;
    }
}
