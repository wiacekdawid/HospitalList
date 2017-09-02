package com.wiacek.hospitallist.data.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class Organisation extends RealmObject {
    @PrimaryKey
    private String organisationID;
}
