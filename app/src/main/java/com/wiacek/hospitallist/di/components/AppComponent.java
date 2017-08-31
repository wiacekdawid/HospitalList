package com.wiacek.hospitallist.di.components;

import com.wiacek.hospitallist.HospitalListApp;
import com.wiacek.hospitallist.di.modules.AppModule;
import com.wiacek.hospitallist.di.scopes.ApplicationScope;

import dagger.Component;

/**
 * Created by wiacek.dawid@gmail.com
 */

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(HospitalListApp hospitalListApp);
}
