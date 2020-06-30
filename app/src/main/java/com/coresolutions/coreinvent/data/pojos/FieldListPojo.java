package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FieldListPojo implements Serializable {

    @SerializedName("fields")
    @Expose
    private List<FieldPojo> fields;
    private final static long serialVersionUID = 5827417775112473659L;

    public List<FieldPojo> getFields() {
        return fields;
    }

    public void setFields(List<FieldPojo> fields) {
        this.fields = fields;
    }
}
