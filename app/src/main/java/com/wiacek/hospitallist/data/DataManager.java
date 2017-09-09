package com.wiacek.hospitallist.data;

import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by wiacek.dawid@gmail.com
 */

public interface DataManager {
    Single<Boolean> getHospitalList();
    long getOrganisationsInDbCount(Realm realm);
}
