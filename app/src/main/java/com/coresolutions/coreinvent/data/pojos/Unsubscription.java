package com.coresolutions.coreinvent.data.pojos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unsubscription implements Serializable
{

    @SerializedName("realTags")
    @Expose
    private List<Tag> realTags = null;
    @SerializedName("madeupTags")
    @Expose
    private List<Tag> madeupTags = null;
    @SerializedName("unsubscriptionVars")
    @Expose
    private List<UnsubscriptionVar> unsubscriptionVars = null;
    @SerializedName("subscribedAssets")
    @Expose
    private List<SubscribedAsset> subscribedAssets = null;
    private final static long serialVersionUID = 2204163635465483904L;

    public List<Tag> getRealTags() {
        return realTags;
    }

    public void setRealTags(List<Tag> realTags) {
        this.realTags = realTags;
    }

    public List<Tag> getMadeupTags() {
        return madeupTags;
    }

    public void setMadeupTags(List<Tag> madeupTags) {
        this.madeupTags = madeupTags;
    }

    public List<UnsubscriptionVar> getUnsubscriptionVars() {
        return unsubscriptionVars;
    }

    public void setUnsubscriptionVars(List<UnsubscriptionVar> unsubscriptionVars) {
        this.unsubscriptionVars = unsubscriptionVars;
    }

    public List<SubscribedAsset> getSubscribedAssets() {
        return subscribedAssets;
    }

    public void setSubscribedAssets(List<SubscribedAsset> subscribedAssets) {
        this.subscribedAssets = subscribedAssets;
    }

}
