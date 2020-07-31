package com.coresolutions.coreinvent.data.pojos;

import androidx.annotation.NonNull;

import com.coresolutions.coreinvent.data.pojos.Edifice;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Center implements Serializable {

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
    @SerializedName("catastro")
    @Expose
    private Object catastro;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("contact_name")
    @Expose
    private Object contactName;
    @SerializedName("contact_email")
    @Expose
    private Object contactEmail;
    @SerializedName("contact_phone")
    @Expose
    private Object contactPhone;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("edifices")
    @Expose
    private List<Edifice> edifices = null;
    private final static long serialVersionUID = -6097099959234694830L;

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

    public Object getCatastro() {
        return catastro;
    }

    public void setCatastro(Object catastro) {
        this.catastro = catastro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getContactName() {
        return contactName;
    }

    public void setContactName(Object contactName) {
        this.contactName = contactName;
    }

    public Object getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(Object contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Object getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(Object contactPhone) {
        this.contactPhone = contactPhone;
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

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}