package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Investments implements Serializable {

    @SerializedName("investment_items")
    @Expose
    private Integer investmentItems;
    @SerializedName("investment_value")
    @Expose
    private Integer investmentValue;
    @SerializedName("budget_percent")
    @Expose
    private Integer budgetPercent;
    private final static long serialVersionUID = 806661589900496642L;

    public Integer getInvestmentItems() {
        return investmentItems;
    }

    public void setInvestmentItems(Integer investmentItems) {
        this.investmentItems = investmentItems;
    }

    public Integer getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(Integer investmentValue) {
        this.investmentValue = investmentValue;
    }

    public Integer getBudgetPercent() {
        return budgetPercent;
    }

    public void setBudgetPercent(Integer budgetPercent) {
        this.budgetPercent = budgetPercent;
    }

}
