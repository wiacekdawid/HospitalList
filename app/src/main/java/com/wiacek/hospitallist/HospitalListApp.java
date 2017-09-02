package com.wiacek.hospitallist;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.wiacek.hospitallist.data.db.DbConstants;
import com.wiacek.hospitallist.data.db.Migration;
import com.wiacek.hospitallist.data.db.ModelsRealmModule;
import com.wiacek.hospitallist.di.components.AppComponent;
import com.wiacek.hospitallist.di.components.DaggerAppComponent;
import com.wiacek.hospitallist.di.modules.AppModule;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class HospitalListApp extends MultiDexApplication {

    @Inject
    protected Migration migration;
    @Inject
    protected ModelsRealmModule modelsRealmModule;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
        initializeDb();
        initializeStetho();
        initializeLeakCanary();
    }

    private void initializeDI() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    private void initializeDb() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .migration(migration)
                .modules(modelsRealmModule)
                .schemaVersion(DbConstants.REALM_SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initializeStetho() {
        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                            .build())
                    .build());
        }
    }

    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
