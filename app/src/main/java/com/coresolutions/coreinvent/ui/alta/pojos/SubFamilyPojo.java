package com.coresolutions.coreinvent.ui.alta.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SubFamilyPojo implements Serializable {

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
    @SerializedName("in_app")
    @Expose
    private Boolean inApp;
    @SerializedName("prefered_account")
    @Expose
    private Integer preferedAccount;
    @SerializedName("can_amortize")
    @Expose
    private Boolean canAmortize;
    @SerializedName("useful_life")
    @Expose
    private Integer usefulLife;
    @SerializedName("amortize_percent")
    @Expose
    private String amortizePercent;
    @SerializedName("family_id")
    @Expose
    private Integer familyId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("types")
    @Expose
    private List<TypePojo> typePojos = null;

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

    public Boolean getInApp() {
        return inApp;
    }

    public void setInApp(Boolean inApp) {
        this.inApp = inApp;
    }

    public Integer getPreferedAccount() {
        return preferedAccount;
    }

    public void setPreferedAccount(Integer preferedAccount) {
        this.preferedAccount = preferedAccount;
    }

    public Boolean getCanAmortize() {
        return canAmortize;
    }

    public void setCanAmortize(Boolean canAmortize) {
        this.canAmortize = canAmortize;
    }

    public Integer getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(Integer usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getAmortizePercent() {
        return amortizePercent;
    }

    public void setAmortizePercent(String amortizePercent) {
        this.amortizePercent = amortizePercent;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
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

    public List<TypePojo> getTypePojos() {
        return typePojos;
    }

    public void setTypePojos(List<TypePojo> typePojos) {
        this.typePojos = typePojos;
    }


    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
