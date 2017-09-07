package com.wiacek.hospitallist.ui.list;

import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.data.db.OrganisationDbHelper;
import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.base.ViewHandler;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewHandler implements ViewHandler {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ListViewModel listViewModel;
    private AttachedHospitalListActivity attachedHospitalListActivity;
    private AttachedListFragment attachedListFragment;
    private DataManager dataManager;
    private Realm realm;

    public ListViewHandler(ListViewModel listViewModel,
                           AttachedHospitalListActivity attachedHospitalListActivity,
                           AttachedListFragment attachedListFragment,
                           DataManager dataManager,
                           Realm realm) {
        this.listViewModel = listViewModel;
        this.setAttachedHospitalListActivity(attachedHospitalListActivity);
        this.attachedListFragment = attachedListFragment;
        this.dataManager = dataManager;
        this.realm = realm;
    }

    @Override
    public void onAttach() {
        listViewModel.setLoading(false);
        if(dataManager.getOrganisationsInDbCount(realm) == 0) {
            onRefresh();
        }
    }

    @Override
    public void onDetach() {
        realm.close();
        compositeDisposable.clear();
    }

    public void onRefresh() {
        if(!listViewModel.isNoMoreToLoad() && !listViewModel.isLoading()) {
            Disposable disposable = getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(value -> onSuccessGetData(value),
                            throwable -> {
                                onErrorGetData();
                                Timber.e(throwable.getMessage());
                            }
                    );
            compositeDisposable.add(disposable);
        }
    }

    public Single<Boolean> getData() {
        listViewModel.setLoading(true);
        return dataManager.getHospitalList();
    }

    private void onSuccessGetData(boolean noMoreToLoad) {
        listViewModel.setLoading(false);
        listViewModel.setNoMoreToLoad(noMoreToLoad);
    }

    private void onErrorGetData() {
        listViewModel.setLoading(false);
    }

    public void onCheckedOnlyNHSOrganisationsChanged() {
        listViewModel.setOnlyNHSOrganisationsChecked(!listViewModel.isOnlyNHSOrganisationsChecked());
        attachedListFragment.onUpdatedData();
    }

    public RealmResults<Organisation> getAdapterDataFromDb() {
        RealmResults<Organisation> realmResults;
        if(listViewModel.isOnlyNHSOrganisationsChecked()) {
            realmResults = OrganisationDbHelper.getNHSOrganisations(realm);
        }
        else {
            realmResults = OrganisationDbHelper.getOrganisations(realm);
        }
        realmResults.addChangeListener((organisations, changeSet) -> onListUpdate(organisations));
        onListUpdate(realmResults);
        return realmResults;
    }

    private void onListUpdate(RealmResults<Organisation> organisations) {
        if(organisations.size() == 0) {
            listViewModel.setReconnectMessageVisible(true);
        }
        else {
            listViewModel.setReconnectMessageVisible(false);
        }
    }

    public AttachedHospitalListActivity getAttachedHospitalListActivity() {
        return attachedHospitalListActivity;
    }

    public void setAttachedHospitalListActivity(AttachedHospitalListActivity attachedHospitalListActivity) {
        this.attachedHospitalListActivity = attachedHospitalListActivity;
    }

    public ListViewModel getListViewModel() {
        return listViewModel;
    }

    public void setListViewModel(ListViewModel listViewModel) {
        this.listViewModel = listViewModel;
    }
}
