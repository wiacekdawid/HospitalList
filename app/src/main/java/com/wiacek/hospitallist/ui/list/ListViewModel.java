package com.wiacek.hospitallist.ui.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.wiacek.hospitallist.BR;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewModel extends BaseObservable implements Parcelable {

    private boolean onlyNHSOrganisationsChecked;
    private boolean noMoreToLoad;
    private boolean loading;
    private boolean reconnectMessageVisible;

    public ListViewModel() {}

    @Bindable
    public boolean isOnlyNHSOrganisationsChecked() {
        return onlyNHSOrganisationsChecked;
    }

    public void setOnlyNHSOrganisationsChecked(boolean onlyNHSOrganisationsChecked) {
        this.onlyNHSOrganisationsChecked = onlyNHSOrganisationsChecked;
        notifyPropertyChanged(BR.onlyNHSOrganisationsChecked);
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public boolean isReconnectMessageVisible() {
        return reconnectMessageVisible;
    }

    public void setReconnectMessageVisible(boolean reconnectMessageVisible) {
        this.reconnectMessageVisible = reconnectMessageVisible;
        notifyPropertyChanged(BR.reconnectMessageVisible);
    }

    public boolean isNoMoreToLoad() {
        return noMoreToLoad;
    }

    public void setNoMoreToLoad(boolean noMoreToLoad) {
        this.noMoreToLoad = noMoreToLoad;
    }

    protected ListViewModel(Parcel in) {
        onlyNHSOrganisationsChecked = in.readByte() != 0x00;
        loading = in.readByte() != 0x00;
        reconnectMessageVisible = in.readByte() != 0x00;
        noMoreToLoad = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (onlyNHSOrganisationsChecked ? 0x01 : 0x00));
        dest.writeByte((byte) (loading ? 0x01 : 0x00));
        dest.writeByte((byte) (reconnectMessageVisible ? 0x01 : 0x00));
        dest.writeByte((byte) (noMoreToLoad ? 0x01 : 0x00));
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
