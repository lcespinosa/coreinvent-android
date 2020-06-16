
package com.coresolutions.coreinvent.ui.alta.pojos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FieldPojo implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("column_name")
    @Expose
    private String columnName;
    @SerializedName("column_group")
    @Expose
    private String columnGroup;
    @SerializedName("empty_text")
    @Expose
    private String emptyText;
    @SerializedName("css_classes")
    @Expose
    private Object cssClasses;
    @SerializedName("data_type")
    @Expose
    private String dataType;
    @SerializedName("field_type")
    @Expose
    private String fieldType;
    @SerializedName("dependency")
    @Expose
    private Object dependency;
    @SerializedName("editable")
    @Expose
    private Boolean editable;
    @SerializedName("constrain")
    @Expose
    private Object constrain;
    @SerializedName("options")
    @Expose
    private List<OptionPojo> optionPojos = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnGroup() {
        return columnGroup;
    }

    public void setColumnGroup(String columnGroup) {
        this.columnGroup = columnGroup;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public Object getCssClasses() {
        return cssClasses;
    }

    public void setCssClasses(Object cssClasses) {
        this.cssClasses = cssClasses;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Object getDependency() {
        return dependency;
    }

    public void setDependency(Object dependency) {
        this.dependency = dependency;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Object getConstrain() {
        return constrain;
    }

    public void setConstrain(Object constrain) {
        this.constrain = constrain;
    }

    public List<OptionPojo> getOptionPojos() {
        return optionPojos;
    }

    public void setOptionPojos(List<OptionPojo> optionPojos) {
        this.optionPojos = optionPojos;
    }

}


