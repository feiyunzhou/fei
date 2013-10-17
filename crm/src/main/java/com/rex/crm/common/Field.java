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
    private boolean isRequired;
    @Expose
    private String display;
    private String dataType;
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
    private boolean isSearchable;
    @Expose
    private String formatter;
    @Expose
    private int priority;
    @Expose
    private String relationTable = null;
    @Expose
    private String fieldGroup = "基本信息";
    
    @Expose
    private String alias = null;
    @Expose 
    private boolean isBaseInfo = false;
    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRelationTable() {
        return relationTable;
    }

    public void setRelationTable(String relationTable) {
        this.relationTable = relationTable;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFieldGroup() {
        return fieldGroup;
    }

    public void setFieldGroup(String fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

	public boolean isBaseInfo() {
		return isBaseInfo;
	}

	public void setBaseInfo(boolean isBaseInfo) {
		this.isBaseInfo = isBaseInfo;
	}

    public boolean isSearchable() {
        return isSearchable;
    }

    public void setSearchable(boolean isSearchable) {
        this.isSearchable = isSearchable;
    }
    

}
