package com.wiacek.hospitallist.ui.list;

import java.lang.ref.WeakReference;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class AttachedListFragmentImp implements AttachedListFragment {

    private final WeakReference<ListFragment> listFragment;

    public AttachedListFragmentImp(WeakReference<ListFragment> listFragment) {
        this.listFragment = listFragment;
    }

    @Override
    public void onUpdatedData() {
        listFragment.get().onUpdatedData();
    }
}
