package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Risks implements Serializable {

    @SerializedName("risk_summary")
    @Expose
    private RiskSummary riskSummary;
    @SerializedName("risk_level")
    @Expose
    private String riskLevel;
    private final static long serialVersionUID = -3911486473332711469L;

    public RiskSummary getRiskSummary() {
        return riskSummary;
    }

    public void setRiskSummary(RiskSummary riskSummary) {
        this.riskSummary = riskSummary;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

}
