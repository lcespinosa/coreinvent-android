

package com.coresolutions.coreinvent.data.pojos;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetPojo implements Serializable {

    @SerializedName("subfamily")
    @Expose
    private Integer subfamily;
    @SerializedName("center")
    @Expose
    private String center;
    @SerializedName("edifice_id")
    @Expose
    private String edificeId;
    @SerializedName("space")
    @Expose
    private String space;
    @SerializedName("tag_type")
    @Expose
    private String tagType;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("old_tag")
    @Expose
    private String oldTag;
    @SerializedName("in_use_date")
    @Expose
    private String inUseDate;
    @SerializedName("useful_life")
    @Expose
    private String usefulLife;
    @SerializedName("measures")
    @Expose
    private String measures;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("surface")
    @Expose
    private String surface;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("start_assigment_date")
    @Expose
    private String startAssigmentDate;
    @SerializedName("end_assigment_date")
    @Expose
    private String endAssigmentDate;
    @SerializedName("characteristics")
    @Expose
    private String characteristics;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("asset_model")
    @Expose
    private String assetModel;
    @SerializedName("serial_number")
    @Expose
    private String serialNumber;
    @SerializedName("plate")
    @Expose
    private String plate;
    @SerializedName("frame_number")
    @Expose
    private String frameNumber;
    @SerializedName("observations")
    @Expose
    private String observations;
    @Expose(deserialize = false, serialize = false)
    private File image;
    @SerializedName("notify_users")
    @Expose
    private List<Integer> notifyUsers = null;
    @SerializedName("notify_text")
    @Expose
    private String notifyText;
    private final static long serialVersionUID = 6673953449430446904L;

    public AssetPojo() {
        notifyUsers = new ArrayList<>();
    }


    public List<Integer> getNotifyUsers() {
        return notifyUsers;
    }

    public void setNotifyUsers(List<Integer> notifyUsers) {
        this.notifyUsers = notifyUsers;
    }

    public String getNotifyText() {
        return notifyText;
    }

    public void addNotifyUser(int userId) {
        this.notifyUsers.add(userId);
    }

    public void clearNotification() {
        this.notifyUsers.clear();
        this.notifyText = "";
    }

    public void setNotifyText(String notifyText) {
        this.notifyText = notifyText;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getSubfamily() {
        return subfamily;
    }

    public void setSubfamily(Integer subfamily) {
        this.subfamily = subfamily;
    }

    public String getEdificeId() {
        return edificeId;
    }

    public void setEdificeId(String edificeId) {
        this.edificeId = edificeId;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOldTag() {
        return oldTag;
    }

    public void setOldTag(String oldTag) {
        this.oldTag = oldTag;
    }

    public String getInUseDate() {
        return inUseDate;
    }

    public void setInUseDate(String inUseDate) {
        this.inUseDate = inUseDate;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStartAssigmentDate() {
        return startAssigmentDate;
    }

    public void setStartAssigmentDate(String startAssigmentDate) {
        this.startAssigmentDate = startAssigmentDate;
    }

    public String getEndAssigmentDate() {
        return endAssigmentDate;
    }

    public void setEndAssigmentDate(String endAssigmentDate) {
        this.endAssigmentDate = endAssigmentDate;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}