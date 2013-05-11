package com.rex.crm.common;

import java.io.Serializable;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Field implements Serializable {

    @Expose
    private boolean isPrimaryKey;
    @Expose
    private String id;
    @Expose
    private String display;
    private Class dataType;
    @Expose
    private String name;
    @Expose
    private boolean isDetailLink;
    @Expose
    private boolean isVisible;
    @Expose
    private String picklist = null;
    @Expose
    private boolean isEditable;
    @Expose
    private String formatter;
    

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        GsonBuilder gb = new GsonBuilder(); 
       
        return  gb.excludeFieldsWithoutExposeAnnotation().create().toJson(this);
    }

    public boolean isDetailLink() {
        return isDetailLink;
    }

    public void setDetailLink(boolean isDetailLink) {
        this.isDetailLink = isDetailLink;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getPicklist() {
        return picklist;
    }

    public void setPicklist(String picklist) {
        this.picklist = picklist;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
    

}
