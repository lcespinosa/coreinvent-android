package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subscription implements Serializable {

    @SerializedName("asset_count")
    @Expose
    private Integer assetCount;
    @SerializedName("asset_value")
    @Expose
    private Double assetValue;
    @SerializedName("last_record")
    @Expose
    private SubscribedAsset lastRecord;
    private final static long serialVersionUID = 2722350514623815728L;

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }

    public Double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(Double assetValue) {
        this.assetValue = assetValue;
    }

    public SubscribedAsset getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(SubscribedAsset lastRecord) {
        this.lastRecord = lastRecord;
    }
}
