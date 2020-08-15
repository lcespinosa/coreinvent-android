package com.coresolutions.coreinvent.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;

public class ItemDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, FindAssetPojo>> itemLiveDataSource = new MutableLiveData<>();
    private String token;
    private Search search;

    public ItemDataSourceFactory(String token, Search search) {
        this.token = token;
        this.search = search;
    }

    @Override
    public DataSource<Integer, FindAssetPojo> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource(token, search);
        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, FindAssetPojo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}