package com.wiacek.hospitallist.ui.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiacek.hospitallist.R;
import com.wiacek.hospitallist.databinding.FragmentListBinding;
import com.wiacek.hospitallist.di.components.ListFragmentComponent;
import com.wiacek.hospitallist.di.modules.ListFragmentModule;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivityImp;
import com.wiacek.hospitallist.ui.activity.HospitalListActivity;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListFragment extends Fragment {

    @Inject
    protected ListViewModel listViewModel;

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
                .getHospitalListActivityComponent().add(new ListFragmentModule());
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        binding.setViewModel(listViewModel);
        return binding.getRoot();
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected();
    }
}
