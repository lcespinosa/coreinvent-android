package com.coresolutions.coreinvent.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.Search;

public class ItemDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, FindAssetPojo>> itemLiveDataSource = new MutableLiveData<>();
    private MutableLiveData<ItemDataSource> mutableLiveData;
    private String token;

    public ItemDataSourceFactory(String token) {
        this.token = token;
        this.mutableLiveData = new MutableLiveData<ItemDataSource>();
    }

    @Override
    public DataSource<Integer, FindAssetPojo> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource(token);
        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);
        mutableLiveData.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }

    public MutableLiveData<ItemDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, FindAssetPojo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}