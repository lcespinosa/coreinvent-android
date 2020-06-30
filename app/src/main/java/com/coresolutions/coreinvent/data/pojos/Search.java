package com.coresolutions.coreinvent.data.pojos;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search implements Serializable {

    @SerializedName("search")
    @Expose
    private String search;
    private final static long serialVersionUID = -5534576224865624751L;


    public Search(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}