package com.wiacek.hospitallist.ui.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wiacek.hospitallist.R;
import com.wiacek.hospitallist.databinding.FragmentListBinding;
import com.wiacek.hospitallist.di.components.ListFragmentComponent;
import com.wiacek.hospitallist.di.modules.ListFragmentModule;
import com.wiacek.hospitallist.ui.activity.HospitalListActivity;

import javax.inject.Inject;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListFragment extends Fragment {
    private static final String BUNDLE_LIST_VIEW_MODEL = "BUNDLE_LIST_VIEW_MODEL";
    private static final String BUNDLE_LINEAR_LAYOUT_MANAGER_STATE = "BUNDLE_LINEAR_LAYOUT_MANAGER_STATE";
    @Inject
    protected ListViewHandler listViewHandler;

    private FragmentListBinding fragmentListBinding;
    private ListAdapter listAdapter;
    public LinearLayoutManager linearLayoutManager;

    OnListItemSelectedListener onListItemSelectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onListItemSelectedListener = (OnListItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnListItemSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListFragmentComponent component = ((HospitalListActivity)getActivity())
                .getHospitalListActivityComponent().add(new ListFragmentModule(this));
        component.inject(this);
        if(savedInstanceState != null) {
            listViewHandler.setListViewModel(savedInstanceState.getParcelable(BUNDLE_LIST_VIEW_MODEL));
        }
        listViewHandler.onAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listViewHandler.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        if(savedInstanceState != null) {
            linearLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(BUNDLE_LINEAR_LAYOUT_MANAGER_STATE));
        }
        fragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        fragmentListBinding.setViewModel(listViewHandler.getListViewModel());
        fragmentListBinding.setViewHandler(listViewHandler);
        setupRecyclerView();
        return fragmentListBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_LINEAR_LAYOUT_MANAGER_STATE, linearLayoutManager.onSaveInstanceState());
        outState.putParcelable(BUNDLE_LIST_VIEW_MODEL, listViewHandler.getListViewModel());
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = fragmentListBinding.fragmentListRecyclerView;
        listAdapter = new ListAdapter(listViewHandler.getAdapterDataFromDb(), true, listViewHandler.getAttachedHospitalListActivity());
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()) {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        int position = parent.getChildAdapterPosition(view);
                        // hide the divider for the last child
                        if (position == parent.getAdapter().getItemCount() - 1) {
                            outRect.setEmpty();
                        } else {
                            super.getItemOffsets(outRect, view, parent, state);
                        }
                    }
                }
        );

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(totalItemCount <= (lastVisibleItem + 2)){
                    listViewHandler.onRefresh();
                }
            }
        });
    }

    public void onUpdatedData() {
        listAdapter.updateData(listViewHandler.getAdapterDataFromDb());
    }

    public void showErrorMessage() {
        Toast.makeText(getActivity(), R.string.hospital_list_error_message, Toast.LENGTH_SHORT).show();
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected(String organisationId);
    }
}
