package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EconomicAspect implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("in_use_date")
    @Expose
    private Object inUseDate;
    @SerializedName("start_assigment_date")
    @Expose
    private Object startAssigmentDate;
    @SerializedName("end_assigment_date")
    @Expose
    private Object endAssigmentDate;
    @SerializedName("acquisition_cost")
    @Expose
    private String acquisitionCost;
    @SerializedName("type_cost_value")
    @Expose
    private Integer typeCostValue;
    @SerializedName("fixed_asset")
    @Expose
    private Object fixedAsset;
    @SerializedName("useful_life")
    @Expose
    private String usefulLife;
    @SerializedName("can_count")
    @Expose
    private Boolean canCount;
    @SerializedName("generic_asset_id")
    @Expose
    private Integer genericAssetId;
    @SerializedName("exercise_year_id")
    @Expose
    private Integer exerciseYearId;
    @SerializedName("acquisition_type_id")
    @Expose
    private Integer acquisitionTypeId;
    @SerializedName("category_id")
    @Expose
    private Object categoryId;
    @SerializedName("purchase_order_id")
    @Expose
    private Integer purchaseOrderId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = -9065086792791542067L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getInUseDate() {
        return inUseDate;
    }

    public void setInUseDate(Object inUseDate) {
        this.inUseDate = inUseDate;
    }

    public Object getStartAssigmentDate() {
        return startAssigmentDate;
    }

    public void setStartAssigmentDate(Object startAssigmentDate) {
        this.startAssigmentDate = startAssigmentDate;
    }

    public Object getEndAssigmentDate() {
        return endAssigmentDate;
    }

    public void setEndAssigmentDate(Object endAssigmentDate) {
        this.endAssigmentDate = endAssigmentDate;
    }

    public String getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(String acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }

    public Integer getTypeCostValue() {
        return typeCostValue;
    }

    public void setTypeCostValue(Integer typeCostValue) {
        this.typeCostValue = typeCostValue;
    }

    public Object getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(Object fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public Boolean getCanCount() {
        return canCount;
    }

    public void setCanCount(Boolean canCount) {
        this.canCount = canCount;
    }

    public Integer getGenericAssetId() {
        return genericAssetId;
    }

    public void setGenericAssetId(Integer genericAssetId) {
        this.genericAssetId = genericAssetId;
    }

    public Integer getExerciseYearId() {
        return exerciseYearId;
    }

    public void setExerciseYearId(Integer exerciseYearId) {
        this.exerciseYearId = exerciseYearId;
    }

    public Integer getAcquisitionTypeId() {
        return acquisitionTypeId;
    }

    public void setAcquisitionTypeId(Integer acquisitionTypeId) {
        this.acquisitionTypeId = acquisitionTypeId;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
