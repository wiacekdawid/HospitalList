package com.wiacek.hospitallist;

import com.wiacek.hospitallist.data.DataManager;
import com.wiacek.hospitallist.ui.activity.AttachedHospitalListActivity;
import com.wiacek.hospitallist.ui.list.AttachedListFragment;
import com.wiacek.hospitallist.ui.list.ListViewHandler;
import com.wiacek.hospitallist.ui.list.ListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ListViewHandlerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
    public RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();

    @Mock
    ListViewModel listViewModel;
    @Mock
    DataManager dataManager;
    @Mock
    AttachedListFragment attachedListFragment;
    @Mock
    AttachedHospitalListActivity attachedHospitalListActivity;

    private ListViewHandler listViewHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listViewHandler = new ListViewHandler(listViewModel,
                attachedHospitalListActivity, attachedListFragment, dataManager);
    }

    @Test
    public void loaderIsVisibleWhenDownloadDataToList() {
        //given

        //when
        listViewHandler.getData();

        //then
        Mockito.verify(listViewModel).setLoading(true);
    }

    @Test
    public void getDataFromDataManagerWhenWeWantGetData() {
        //given

        //when
        listViewHandler.getData();

        //then
        Mockito.verify(dataManager).getHospitalList();
    }

    @Test
    public void getDataNotCalledWhenCallOnRefreshAndIsLoading() {
        //given
        Mockito.doReturn(true).when(listViewModel).isLoading();
        Mockito.doReturn(false).when(listViewModel).isNoMoreToLoad();

        //when
        listViewHandler.onRefresh();

        //then
        Mockito.verify(dataManager, Mockito.times(0)).getHospitalList();
    }

    @Test
    public void getDataNotCalledWhenCallOnRefreshAndIsNoMoreToLoad() {
        //given
        Mockito.doReturn(false).when(listViewModel).isLoading();
        Mockito.doReturn(true).when(listViewModel).isNoMoreToLoad();

        //when
        listViewHandler.onRefresh();

        //then
        Mockito.verify(dataManager, Mockito.times(0)).getHospitalList();
    }

    @Test
    public void getDataNotCalledWhenCallOnRefreshAndIsNoMoreToLoadAndIsLoading() {
        //given
        Mockito.doReturn(true).when(listViewModel).isLoading();
        Mockito.doReturn(true).when(listViewModel).isNoMoreToLoad();

        //when
        listViewHandler.onRefresh();

        //then
        Mockito.verify(dataManager, Mockito.times(0)).getHospitalList();
    }

    @Test
    public void loaderIsHideWhenSuccessGetData() {
        //given

        //when
        listViewHandler.onSuccessGetData(true);

        //then
        Mockito.verify(listViewModel, Mockito.times(1)).setLoading(false);
    }

    @Test
    public void loaderIsHideWhenErrorGetData() {
        //given

        //when
        listViewHandler.onErrorGetData();

        //then
        Mockito.verify(listViewModel, Mockito.times(1)).setLoading(false);
    }

    @Test
    public void errorMessageIsShownWhenErrorOccursDuringGetData() {
        //given

        //when
        listViewHandler.onErrorGetData();

        //then
        Mockito.verify(attachedListFragment, Mockito.times(1)).showErrorMessage();
    }

    @Test
    public void noMoreToLoadIsSetWhenSuccesfullyGetData() {
        //given
        boolean noMoreToLoad = false;
        //when
        listViewHandler.onSuccessGetData(noMoreToLoad);

        //then
        Mockito.verify(listViewModel, Mockito.times(1)).setNoMoreToLoad(noMoreToLoad);
    }

    @Test
    public void dataAreUpdatedWhenWeChangeFilter() {
        //given

        //when
        listViewHandler.onCheckedOnlyNHSOrganisationsChanged();

        //then
        Mockito.verify(attachedListFragment, Mockito.times(1)).onUpdatedData();
    }
}
