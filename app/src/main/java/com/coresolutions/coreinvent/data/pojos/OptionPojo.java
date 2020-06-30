package com.coresolutions.coreinvent.data.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OptionPojo implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("edifices")
    @Expose
    private List<Edifice> edifices = null;
    @SerializedName("installation")
    @Expose
    private String installation;
    @SerializedName("tag_type_id")
    @Expose
    private Integer tagTypeId;
    @SerializedName("generic_asset_id")
    @Expose
    private Object genericAssetId;
    @SerializedName("tag_type")
    @Expose
    private TagType tagType;
    @SerializedName("manufacturer_id")
    @Expose
    private Integer manufacturerId;
    @SerializedName("asset_models")
    @Expose
    private List<AssetModel> assetModels = null;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    private final static long serialVersionUID = 2604884895258544769L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Edifice> getEdifices() {
        return edifices;
    }

    public void setEdifices(List<Edifice> edifices) {
        this.edifices = edifices;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public Integer getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Integer tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public Object getGenericAssetId() {
        return genericAssetId;
    }

    public void setGenericAssetId(Object genericAssetId) {
        this.genericAssetId = genericAssetId;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public List<AssetModel> getAssetModels() {
        return assetModels;
    }

    public void setAssetModels(List<AssetModel> assetModels) {
        this.assetModels = assetModels;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}



