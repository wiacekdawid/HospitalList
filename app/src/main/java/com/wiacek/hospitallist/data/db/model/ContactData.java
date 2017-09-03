package com.wiacek.hospitallist.data.db.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ContactData extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String phone;
    private String email;
    private String website;
    private String fax;

    public ContactData() {}

    public ContactData(String phone, String email,
                       String website, String fax) {
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.fax = fax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
