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

import io.reactivex.Single;
import io.realm.Realm;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DataManager {
    private static final int NUM_OF_SAVED_ORGANISATION_AT_ONCE = 10;
    private static final String TEMPORARY_CSV_FILE_NAME = "tempHospitalList.csv";
    private DataGovService dataGovService;
    private File cacheDir;

    public DataManager(Context context, DataGovService dataGovService) {
        this.cacheDir = context.getCacheDir();
        this.dataGovService = dataGovService;
    }

    public Single<Boolean> getHospitalList() {
        File csvDataFile = new File(cacheDir, TEMPORARY_CSV_FILE_NAME);
        if(!csvDataFile.exists()) {
            return dataGovService.getListOfHospitals()
                    .doOnError(t -> Timber.e(t.getMessage()))
                    .map(responseBody -> {
                        boolean noMoreToLoad = false;
                        File file = saveCSVFileLocally(responseBody);
                        if (file != null) {
                            try {
                                noMoreToLoad = saveCSVFileDataToDb(file.getPath());
                            } catch (IOException e) {
                                Timber.e(e.getMessage());
                            }
                        }
                        return noMoreToLoad;
                    });
        }
        else {
            return Single.just(csvDataFile)
                    .doOnError(throwable -> Timber.e(throwable.getMessage()))
                    .map(file -> {
                        Boolean noMoreToLoad = saveCSVFileDataToDb(file.getPath());
                        return noMoreToLoad;
                    });
        }
    }

    public long getOrganisationsInDbCount(Realm realm) {
        return OrganisationDbHelper.getOrganisationsCount(realm);
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

    private Boolean saveCSVFileDataToDb(String filePath) throws IOException {
        Boolean noMoreToLoad = false;
        BufferedReader bufferedReader = null;
        Realm realm = Realm.getDefaultInstance();
        try {
            long numOfSavedOrganisations = OrganisationDbHelper.getOrganisationsCount(realm);
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String inputLine;

            for(int i=0 ; i<= numOfSavedOrganisations ; i++) {
                bufferedReader.readLine();
            }

            int savingCounter = 0;
            while ((inputLine = bufferedReader.readLine()) != null &&
                    savingCounter < NUM_OF_SAVED_ORGANISATION_AT_ONCE) {
                try {
                    savingCounter++;
                    OrganisationDbHelper.add(realm, CsvFileToRealmObjectMapper.transform(inputLine))
                            .doOnError(t -> Timber.e(t.getMessage()))
                            .subscribe();
                }
                catch(Exception e) {
                    Timber.e(e.getMessage());
                }
            }

            if(numOfSavedOrganisations == OrganisationDbHelper.getOrganisationsCount(realm)) {
                noMoreToLoad = true;
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
        return noMoreToLoad;
    }
}
