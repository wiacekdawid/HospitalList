package com.wiacek.hospitallist.data;

import android.content.Context;

import com.google.common.io.Files;
import com.wiacek.hospitallist.api.DataGovService;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.util.CsvFileToRealmObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DataManager {
    private static final String TEMPORARY_CSV_FILE_NAME = "tempHospitalList.csv";
    private DataGovService dataGovService;
    private File cacheDir;

    public DataManager(Context context, DataGovService dataGovService) {
        this.cacheDir = context.getCacheDir();
        this.dataGovService = dataGovService;
    }

    public void getHospitalList() {
        dataGovService.getListOfHospitals()
                .doOnError(t -> Timber.e(t.getMessage()))
                .doOnSuccess(responseBody -> {
                    File file = saveCSVFileLocally(responseBody);
                    if(file != null) {
                        saveCSVFileDataToDb(file.getPath());
                        try {
                            saveCSVFileDataToDb(file.getPath());
                        }
                        catch (IOException e) {
                            Timber.e(e.getMessage());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private File saveCSVFileLocally(ResponseBody responseBody) {
        File file = null;
        try {
            file = new File(cacheDir, TEMPORARY_CSV_FILE_NAME);
            file.createNewFile();
            Files.asByteSink(file).write(responseBody.bytes());
        }
        catch (IOException e) {
            Timber.e(e.getMessage());
        }
        return file;
    }

    private void saveCSVFileDataToDb(String filePath) throws IOException {
        BufferedReader bufferedReader = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String inputLine = bufferedReader.readLine();

            while ((inputLine = bufferedReader.readLine()) != null) {
                try {
                    OrganisationDbHelper.add(realm, CsvFileToRealmObjectMapper.transform(inputLine))
                            .doOnError(t -> Timber.e(t.getMessage()))
                            .subscribe();
                }
                catch(Exception e) {
                    Timber.e(e.getMessage());
                }
            }
        }
        finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException e) {
                Timber.e(e.getMessage());
            }
            realm.close();
        }
    }
}
