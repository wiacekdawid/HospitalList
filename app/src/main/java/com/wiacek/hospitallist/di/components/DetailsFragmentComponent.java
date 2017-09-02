package com.wiacek.hospitallist.di.components;

import com.wiacek.hospitallist.di.modules.DetailsFragmentModule;
import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.details.DetailsFragment;

import dagger.Subcomponent;

/**
 * Created by wiacek.dawid@gmail.com
 */
@FragmentScope
@Subcomponent(modules = {DetailsFragmentModule.class})
public interface DetailsFragmentComponent {
    void inject(DetailsFragment detailsFragment);
}
