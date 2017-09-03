package com.wiacek.hospitallist.ui.list;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiacek.hospitallist.R;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.databinding.FragmentListItemBinding;
import com.wiacek.hospitallist.ui.list.item.ItemViewModel;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListAdapter extends RealmRecyclerViewAdapter<Organisation,
        ListAdapter.ItemViewHolder> {

    public ListAdapter(@Nullable OrderedRealmCollection<Organisation> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.fragment_list_item, parent, false);

        ItemViewModel githubItemViewModel = new ItemViewModel();

        FragmentListItemBinding binding = FragmentListItemBinding.bind(view);
        binding.setViewModel(githubItemViewModel);

        return new ItemViewHolder(view, binding, githubItemViewModel);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setItem(getItem(position));
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemViewModel itemViewModel;
        private final ViewDataBinding viewDataBinding;

        public ItemViewHolder(View itemView, ViewDataBinding viewDataBinding,
                              ItemViewModel itemViewModel) {
            super(itemView);
            this.itemViewModel = itemViewModel;
            this.viewDataBinding = viewDataBinding;
        }

        void setItem(Organisation organisation) {
            itemViewModel.setItem(organisation);
            viewDataBinding.executePendingBindings();
        }
    }
}
