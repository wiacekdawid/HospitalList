package com.wiacek.hospitallist.data.db;

import com.wiacek.hospitallist.data.db.model.Organisation;

import io.reactivex.Completable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class OrganisationDbHelper {

    public static Completable add(Realm realm, Organisation organisation) {
        return Completable.fromAction(() -> realm.executeTransaction(
                innerRealm -> innerRealm.insertOrUpdate(organisation)
        ));
    }

    public static RealmResults<Organisation> getOrganisations(Realm realm) {
        return realm.where(Organisation.class)
                .findAll();
    }
}
