package com.coresolutions.coreinvent.data.pojos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribedAsset implements Serializable
{

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
    @SerializedName("denomination")
    @Expose
    private Object denomination;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("surface_field")
    @Expose
    private Object surfaceField;
    @SerializedName("surface_build")
    @Expose
    private Object surfaceBuild;
    @SerializedName("length")
    @Expose
    private Object length;
    @SerializedName("catastro_code")
    @Expose
    private Object catastroCode;
    @SerializedName("characteristics")
    @Expose
    private Object characteristics;
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
    private Object oldTagCode;
    @SerializedName("inventory_value")
    @Expose
    private String inventoryValue;
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
    @SerializedName("head_title")
    @Expose
    private String headTitle;
    @SerializedName("localization")
    @Expose
    private String localization;
    @SerializedName("url_photo")
    @Expose
    private String urlPhoto;
    @SerializedName("tag")
    @Expose
    private Tag tag;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("asset_model")
    @Expose
    private AssetModel assetModel;
    @SerializedName("space")
    @Expose
    private Space space;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    private final static long serialVersionUID = 2639795680181819598L;

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

    public Object getDenomination() {
        return denomination;
    }

    public void setDenomination(Object denomination) {
        this.denomination = denomination;
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

    public Object getSurfaceField() {
        return surfaceField;
    }

    public void setSurfaceField(Object surfaceField) {
        this.surfaceField = surfaceField;
    }

    public Object getSurfaceBuild() {
        return surfaceBuild;
    }

    public void setSurfaceBuild(Object surfaceBuild) {
        this.surfaceBuild = surfaceBuild;
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

    public Object getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Object characteristics) {
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

    public Object getOldTagCode() {
        return oldTagCode;
    }

    public void setOldTagCode(Object oldTagCode) {
        this.oldTagCode = oldTagCode;
    }

    public String getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(String inventoryValue) {
        this.inventoryValue = inventoryValue;
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

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}


