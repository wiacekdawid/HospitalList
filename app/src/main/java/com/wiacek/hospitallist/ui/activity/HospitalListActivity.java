package com.wiacek.hospitallist.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wiacek.hospitallist.HospitalListApp;
import com.wiacek.hospitallist.R;
import com.wiacek.hospitallist.di.components.HospitalListActivityComponent;
import com.wiacek.hospitallist.di.modules.HospitalListActivityModule;
import com.wiacek.hospitallist.ui.details.DetailsFragment;
import com.wiacek.hospitallist.ui.list.ListFragment;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class HospitalListActivity extends AppCompatActivity
            implements ListFragment.OnListItemSelectedListener{

    private HospitalListActivityComponent component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = ((HospitalListApp)getApplicationContext())
                .getAppComponent()
                .add(new HospitalListActivityModule(this));
        component.inject(this);

        setContentView(R.layout.activity_hospital_list);

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }

            ListFragment listFragment = new ListFragment();
            listFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment).commit();
        }
    }

    @Override
    public void onListItemSelected() {
        DetailsFragment detailsFragment = (DetailsFragment)
                getSupportFragmentManager().findFragmentByTag(DetailsFragment.class.getName());

        if(detailsFragment != null) {

        }
        else {
            DetailsFragment newDetailsFragment = new DetailsFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newDetailsFragment, DetailsFragment.class.getName());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public HospitalListActivityComponent getHospitalListActivityComponent() {
        return component;
    }
}