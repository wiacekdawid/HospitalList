package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wiacek.hospitallist.BR;
import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.base.ViewHandler;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable implements Parcelable {

    private boolean onlyNHSOrganisationsChecked;

    public ListViewModel() {}

    @Bindable
    public boolean isOnlyNHSOrganisationsChecked() {
        return onlyNHSOrganisationsChecked;
    }

    public void setOnlyNHSOrganisationsChecked(boolean onlyNHSOrganisationsChecked) {
        this.onlyNHSOrganisationsChecked = onlyNHSOrganisationsChecked;
        notifyPropertyChanged(BR.onlyNHSOrganisationsChecked);
    }

    protected ListViewModel(Parcel in) {
        onlyNHSOrganisationsChecked = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (onlyNHSOrganisationsChecked ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ListViewModel> CREATOR = new Parcelable.Creator<ListViewModel>() {
        @Override
        public ListViewModel createFromParcel(Parcel in) {
            return new ListViewModel(in);
        }

        @Override
        public ListViewModel[] newArray(int size) {
            return new ListViewModel[size];
        }
    };
}
