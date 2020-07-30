package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Investments implements Serializable {

    @SerializedName("investment_items")
    @Expose
    private Double investmentItems;
    @SerializedName("investment_value")
    @Expose
    private Double investmentValue;
    @SerializedName("budget_percent")
    @Expose
    private Double budgetPercent;
    private final static long serialVersionUID = 806661589900496642L;

    public Double getInvestmentItems() {
        return investmentItems;
    }

    public void setInvestmentItems(Double investmentItems) {
        this.investmentItems = investmentItems;
    }

    public Double getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(Double investmentValue) {
        this.investmentValue = investmentValue;
    }

    public Double getBudgetPercent() {
        return budgetPercent;
    }

    public void setBudgetPercent(Double budgetPercent) {
        this.budgetPercent = budgetPercent;
    }

}
