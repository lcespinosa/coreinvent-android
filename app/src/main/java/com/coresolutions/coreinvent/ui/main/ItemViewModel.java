package com.coresolutions.coreinvent.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;

public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    LiveData<PagedList<FindAssetPojo>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, FindAssetPojo>> liveDataSource;
    private String token;
    private Search search;

    //constructor

    public ItemViewModel(String token, Search search) {
        this.token = token;
        this.search = search;
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(token, search);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}