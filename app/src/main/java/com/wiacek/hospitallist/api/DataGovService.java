package com.wiacek.hospitallist.api;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by wiacek.dawid@gmail.com
 */

public interface DataGovService {
    @GET("/data/resource/nhschoices/Hospital.csv")
    Single<ResponseBody> getListOfHospitals();
}
