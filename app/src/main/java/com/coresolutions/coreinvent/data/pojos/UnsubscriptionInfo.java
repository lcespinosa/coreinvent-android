package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnsubscriptionInfo implements Serializable {

    @SerializedName("asset_count")
    @Expose
    private Integer assetCount;
    @SerializedName("asset_value")
    @Expose
    private Integer assetValue;
    @SerializedName("last_record")
    @Expose
    private SubscribedAsset lastRecord;
    private final static long serialVersionUID = -8833682518606567268L;

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }

    public Integer getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(Integer assetValue) {
        this.assetValue = assetValue;
    }

    public SubscribedAsset getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(SubscribedAsset lastRecord) {
        this.lastRecord = lastRecord;
    }
}
