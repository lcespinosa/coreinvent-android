package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("emisor")
    @Expose
    private Integer emisor;
    @SerializedName("observations")
    @Expose
    private String observations;
    @SerializedName("asset")
    @Expose
    private Integer asset;
    private final static long serialVersionUID = 1593134578049182368L;

    public Integer getEmisor() {
        return emisor;
    }

    public void setEmisor(Integer emisor) {
        this.emisor = emisor;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getAsset() {
        return asset;
    }

    public void setAsset(Integer asset) {
        this.asset = asset;
    }

}
