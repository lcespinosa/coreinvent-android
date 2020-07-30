
package com.coresolutions.coreinvent.data.pojos;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard implements Serializable {

    @SerializedName("subscription")
    @Expose
    private Subscription subscription;
    @SerializedName("unsubscription")
    @Expose
    private UnsubscriptionInfo unsubscriptionInfo;
    @SerializedName("investments")
    @Expose
    private Investments investments;
    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;
    @SerializedName("risks")
    @Expose
    private Risks risks;
    private final static long serialVersionUID = -8180175584905432449L;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public UnsubscriptionInfo getUnsubscriptionInfo() {
        return unsubscriptionInfo;
    }

    public void setUnsubscriptionInfo(UnsubscriptionInfo unsubscriptionInfo) {
        this.unsubscriptionInfo = unsubscriptionInfo;
    }

    public Investments getInvestments() {
        return investments;
    }

    public void setInvestments(Investments investments) {
        this.investments = investments;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Risks getRisks() {
        return risks;
    }

    public void setRisks(Risks risks) {
        this.risks = risks;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}






