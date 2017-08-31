package com.wiacek.hospitallist.di.modules;

import android.content.Context;

import com.wiacek.hospitallist.HospitalListApp;
import com.wiacek.hospitallist.di.scopes.ApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class AppModule {
    private HospitalListApp hospitalListApp;

    public AppModule(HospitalListApp hospitalListApp) {
        this.hospitalListApp = hospitalListApp;
    }

    @Provides
    @Named("ApplicationContext")
    @ApplicationScope
    Context provideApplicationContext() {
        return hospitalListApp.getApplicationContext();
    }
}
