package com.wiacek.hospitallist.data.db;

/**
 * Created by wiacek.dawid@gmail.com
 */

import com.wiacek.hospitallist.data.db.model.Address;
import com.wiacek.hospitallist.data.db.model.ContactData;
import com.wiacek.hospitallist.data.db.model.Organisation;

import javax.inject.Inject;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {Organisation.class, Address.class, ContactData.class})
public class ModelsRealmModule {
    @Inject
    ModelsRealmModule() {}
}
