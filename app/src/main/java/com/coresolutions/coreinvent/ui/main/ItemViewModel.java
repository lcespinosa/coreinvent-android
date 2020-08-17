package com.coresolutions.coreinvent.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.coresolutions.coreinvent.data.Constants;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;
import com.coresolutions.coreinvent.utils.NetworkState;

public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    LiveData<PagedList<FindAssetPojo>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, FindAssetPojo>> liveDataSource;

    private LiveData<NetworkState> networkState;

    private String token;
    //constructor

    public ItemViewModel(String token) {
        this.token = token;
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(token);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        networkState = Transformations.switchMap(itemDataSourceFactory.getMutableLiveData(),
                dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}