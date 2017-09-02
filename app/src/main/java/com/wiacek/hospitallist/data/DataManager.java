package com.wiacek.hospitallist.data;

import com.wiacek.hospitallist.api.DataGovService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DataManager {
    private DataGovService dataGovService;

    public DataManager(DataGovService dataGovService) {
        this.dataGovService = dataGovService;
    }

    public void getHospitalList() {
        dataGovService.getListOfHospitals()
                .doOnError(t -> Timber.e("tag", t.getMessage()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
