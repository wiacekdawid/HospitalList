package com.wiacek.hospitallist.data.db;

import javax.inject.Inject;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class Migration implements RealmMigration {

    @Inject
    public Migration() {}

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {}
}
