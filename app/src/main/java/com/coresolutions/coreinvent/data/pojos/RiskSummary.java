package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RiskSummary implements Serializable {

    @SerializedName("none")
    @Expose
    private Integer none;
    @SerializedName("low")
    @Expose
    private Integer low;
    @SerializedName("medium")
    @Expose
    private Integer medium;
    @SerializedName("high")
    @Expose
    private Integer high;
    @SerializedName("critical")
    @Expose
    private Integer critical;
    private final static long serialVersionUID = 8983149023434005298L;

    public Integer getNone() {
        return none;
    }

    public void setNone(Integer none) {
        this.none = none;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getMedium() {
        return medium;
    }

    public void setMedium(Integer medium) {
        this.medium = medium;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

}
