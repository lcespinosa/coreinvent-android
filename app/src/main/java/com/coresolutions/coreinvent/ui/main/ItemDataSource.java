package com.coresolutions.coreinvent.ui.main;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.coresolutions.coreinvent.data.RetrofitClient;
import com.coresolutions.coreinvent.data.pojos.FindAssetPojo;
import com.coresolutions.coreinvent.data.pojos.FindResponse;
import com.coresolutions.coreinvent.data.pojos.Search;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, FindAssetPojo> {

    public static final int PAGE_SIZE = 15;
    private static final int FIRST_PAGE = 1;
    private String token;
    private Search search;

    public ItemDataSource(String token, Search search) {
        this.token = token;
        this.search = search;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, FindAssetPojo> callback) {
        RetrofitClient.getInstance()
                .getAltasApi().findAsset(token, search, FIRST_PAGE)
                .enqueue(new Callback<FindResponse>() {
                    @Override
                    public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<FindResponse> call, Throwable t) {
                        String sss = "";
                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, FindAssetPojo> callback) {
        RetrofitClient.getInstance()
                .getAltasApi().findAsset(token, search, params.key)
                .enqueue(new Callback<FindResponse>() {
                    @Override
                    public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key 
                            callback.onResult(response.body().getData(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<FindResponse> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, FindAssetPojo> callback) {
        RetrofitClient.getInstance()
                .getAltasApi().findAsset(token, search, FIRST_PAGE)
                .enqueue(new Callback<FindResponse>() {
                    @Override
                    public void onResponse(Call<FindResponse> call, Response<FindResponse> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().getCurrentPage() < response.body().getLastPage() ? params.key + 1 : null;

                            //passing the loaded data and next page value 
                            callback.onResult(response.body().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<FindResponse> call, Throwable t) {

                    }
                });
    }
}