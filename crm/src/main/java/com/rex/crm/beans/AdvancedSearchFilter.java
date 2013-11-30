package com.rex.crm.beans;

public class AdvancedSearchFilter {

    private FilterMeta field;
    private FilterMeta value;
    private FilterMeta operator;
    
    
    public FilterMeta getField() {
        return field;
    }
    public void setField(FilterMeta field) {
        this.field = field;
    }
    public FilterMeta getValue() {
        return value;
    }
    public void setValue(FilterMeta value) {
        this.value = value;
    }
    public FilterMeta getOperator() {
        return operator;
    }
    public void setOperator(FilterMeta operator) {
        this.operator = operator;
    }
    


}

class FilterMeta{
    private String label;
    private String value;
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    
}