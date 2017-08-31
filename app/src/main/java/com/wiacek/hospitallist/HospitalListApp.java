package com.wiacek.hospitallist;

import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;
import com.wiacek.hospitallist.di.components.AppComponent;
import com.wiacek.hospitallist.di.components.DaggerAppComponent;
import com.wiacek.hospitallist.di.modules.AppModule;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class HospitalListApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
        initializeLeakCanary();
    }

    private void initializeDI() {
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
