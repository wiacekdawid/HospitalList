package com.wiacek.hospitallist.di.modules;

import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.details.DetailsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class DetailsFragmentModule {

    @FragmentScope
    @Provides
    DetailsViewModel provideDetailsViewModel() {
        return new DetailsViewModel();
    }
}
