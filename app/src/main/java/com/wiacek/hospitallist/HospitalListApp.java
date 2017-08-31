package com.wiacek.hospitallist;

import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class HospitalListApp extends MultiDexApplication {

    @Override
    public void onCreate() {
      super.onCreate();
      initializeLeakCanary();
    }

    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
