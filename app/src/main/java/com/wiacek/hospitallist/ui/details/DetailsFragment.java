package com.wiacek.hospitallist.ui.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiacek.hospitallist.R;
import com.wiacek.hospitallist.databinding.FragmentDetailsBinding;
import com.wiacek.hospitallist.di.components.DetailsFragmentComponent;
import com.wiacek.hospitallist.di.modules.DetailsFragmentModule;
import com.wiacek.hospitallist.ui.activity.HospitalListActivity;

import javax.inject.Inject;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class DetailsFragment extends Fragment {

    public static final String ORGANISATION_ID = "organisationId";

    @Inject
    protected DetailsViewModel detailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        String organisationId = null;
        if(args != null) {
            organisationId = args.getString(ORGANISATION_ID);
        }
        DetailsFragmentComponent component = ((HospitalListActivity)getActivity())
                .getHospitalListActivityComponent().add(new DetailsFragmentModule(organisationId));
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setViewModel(detailsViewModel);
        return binding.getRoot();
    }
}
