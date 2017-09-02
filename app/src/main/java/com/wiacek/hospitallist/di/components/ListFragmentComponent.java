package com.wiacek.hospitallist.di.components;

import com.wiacek.hospitallist.di.modules.ListFragmentModule;
import com.wiacek.hospitallist.di.scopes.FragmentScope;
import com.wiacek.hospitallist.ui.list.ListFragment;

import dagger.Subcomponent;

/**
 * Created by wiacek.dawid@gmail.com
 */

@FragmentScope
@Subcomponent(modules = {ListFragmentModule.class})
public interface ListFragmentComponent {
    void inject(ListFragment listFragment);
}
