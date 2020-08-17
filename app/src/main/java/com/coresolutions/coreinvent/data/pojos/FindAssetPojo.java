package com.coresolutions.coreinvent.data.pojos;


import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FindAssetPojo implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subscription_date")
    @Expose
    private String subscriptionDate;
    @SerializedName("unsubscription_date")
    @Expose
    private Object unsubscriptionDate;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("internal_item_code")
    @Expose
    private Object internalItemCode;
    @SerializedName("observations")
    @Expose
    private String observations;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("surface")
    @Expose
    private Object surface;
    @SerializedName("length")
    @Expose
    private Object length;
    @SerializedName("catastro_code")
    @Expose
    private Object catastroCode;
    @SerializedName("characteristics")
    @Expose
    private Characteristics characteristics;
    @SerializedName("width")
    @Expose
    private Object width;
    @SerializedName("height")
    @Expose
    private Object height;
    @SerializedName("depth")
    @Expose
    private Object depth;
    @SerializedName("serial_number")
    @Expose
    private String serialNumber;
    @SerializedName("plate")
    @Expose
    private Object plate;
    @SerializedName("frame_number")
    @Expose
    private Object frameNumber;
    @SerializedName("old_tag_code")
    @Expose
    private String oldTagCode;
    @SerializedName("risk_level")
    @Expose
    private String riskLevel;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("space_id")
    @Expose
    private Integer spaceId;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("acquisition_title_id")
    @Expose
    private Object acquisitionTitleId;
    @SerializedName("asset_model_id")
    @Expose
    private Integer assetModelId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("inventory_value")
    @Expose
    private String inventoryValue;
    @SerializedName("head_title")
    @Expose
    private String headTitle;
    @SerializedName("localization")
    @Expose
    private String localization;
    @SerializedName("tag")
    @Expose
    private Tag tag;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("economic_aspect")
    @Expose
    private EconomicAspect economicAspect;
    @SerializedName("asset_model")
    @Expose
    private AssetModel assetModel;
    @SerializedName("space")
    @Expose
    private Space space;
    @SerializedName("url_photo")
    @Expose
    private String url_photo;
    private final static long serialVersionUID = -8946538409396176729L;


    public static DiffUtil.ItemCallback<FindAssetPojo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FindAssetPojo>() {
                @Override
                public boolean areItemsTheSame(FindAssetPojo oldItem, FindAssetPojo newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(FindAssetPojo oldItem, FindAssetPojo newItem) {
                    return oldItem.getCode().equals(newItem.getCode());
                }
            };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public Object getUnsubscriptionDate() {
        return unsubscriptionDate;
    }

    public void setUnsubscriptionDate(Object unsubscriptionDate) {
        this.unsubscriptionDate = unsubscriptionDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getInternalItemCode() {
        return internalItemCode;
    }

    public void setInternalItemCode(Object internalItemCode) {
        this.internalItemCode = internalItemCode;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getSurface() {
        return surface;
    }

    public void setSurface(Object surface) {
        this.surface = surface;
    }

    public Object getLength() {
        return length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

    public Object getCatastroCode() {
        return catastroCode;
    }

    public void setCatastroCode(Object catastroCode) {
        this.catastroCode = catastroCode;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public Object getDepth() {
        return depth;
    }

    public void setDepth(Object depth) {
        this.depth = depth;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Object getPlate() {
        return plate;
    }

    public void setPlate(Object plate) {
        this.plate = plate;
    }

    public Object getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Object frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getOldTagCode() {
        return oldTagCode;
    }

    public void setOldTagCode(String oldTagCode) {
        this.oldTagCode = oldTagCode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Object getAcquisitionTitleId() {
        return acquisitionTitleId;
    }

    public void setAcquisitionTitleId(Object acquisitionTitleId) {
        this.acquisitionTitleId = acquisitionTitleId;
    }

    public Integer getAssetModelId() {
        return assetModelId;
    }

    public void setAssetModelId(Integer assetModelId) {
        this.assetModelId = assetModelId;
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

    public String getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(String inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public EconomicAspect getEconomicAspect() {
        return economicAspect;
    }

    public void setEconomicAspect(EconomicAspect economicAspect) {
        this.economicAspect = economicAspect;
    }

    public AssetModel getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(AssetModel assetModel) {
        this.assetModel = assetModel;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

}



