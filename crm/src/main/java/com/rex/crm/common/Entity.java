package com.rex.crm.common;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Entity {
    @Expose
    private String name;
    @Expose
    private String display;
    @Expose
    private List<Field> fields;
    @Expose
    private String sql;
    @Expose
    private String filterField;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        GsonBuilder gb = new GsonBuilder(); 
        StringBuffer sb = new StringBuffer();
        sb.append(gb.excludeFieldsWithoutExposeAnnotation().create().toJson(this));
        return sb.toString();
    }
    
    public List<String> getFieldNames(){
        List<String> fd = Lists.newArrayList();
        if(fields !=null){
            for(Field f:fields){
                fd.add(f.getName());
            }
        }
        return fd;
    }
    
    public String getPrimaryKeyName(){
        if(fields !=null){
            for(Field f:fields){
               if(f.isPrimaryKey()) return f.getName();
            }
        }
        return null;
    }
    
    public List<String> getDisplayNames(){
        List<String> fd = Lists.newArrayList();
        if(fields !=null){
            for(Field f:fields){
                fd.add(f.getDisplay());
            }
        }
        return fd;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getFilterField() {
        return filterField;
    }

    public void setFilterField(String filterField) {
        this.filterField = filterField;
    }

}
