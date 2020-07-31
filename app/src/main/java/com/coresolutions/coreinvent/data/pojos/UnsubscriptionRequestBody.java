
package com.coresolutions.coreinvent.data.pojos;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnsubscriptionRequestBody implements Serializable {

    @SerializedName("asset_id")
    @Expose
    private String assetId;
    @SerializedName("unsubscription_var_id")
    @Expose
    private String unsubscriptionVarId;
    @SerializedName("notify_users")
    @Expose
    private List<Integer> notifyUsers = null;
    @SerializedName("notify_text")
    @Expose
    private String notifyText;
    private final static long serialVersionUID = -4835191888500751383L;

    public UnsubscriptionRequestBody(String assetId, String unsubscriptionVarId, List<Integer> notifyUsers, String notifyText) {
        this.assetId = assetId;
        this.unsubscriptionVarId = unsubscriptionVarId;
        this.notifyUsers = notifyUsers;
        this.notifyText = notifyText;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getUnsubscriptionVarId() {
        return unsubscriptionVarId;
    }

    public void setUnsubscriptionVarId(String unsubscriptionVarId) {
        this.unsubscriptionVarId = unsubscriptionVarId;
    }

    public List<Integer> getNotifyUsers() {
        return notifyUsers;
    }

    public void setNotifyUsers(List<Integer> notifyUsers) {
        this.notifyUsers = notifyUsers;
    }

    public String getNotifyText() {
        return notifyText;
    }

    public void setNotifyText(String notifyText) {
        this.notifyText = notifyText;
    }

}