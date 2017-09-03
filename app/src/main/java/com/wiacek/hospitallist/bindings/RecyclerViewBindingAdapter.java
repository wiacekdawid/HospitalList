package com.wiacek.hospitallist.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.wiacek.hospitallist.ui.list.ListViewModel;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class RecyclerViewBindingAdapter {
    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                ListViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}
