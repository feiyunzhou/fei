package com.rex.crm.beans;

import java.io.Serializable;

public class AdvancedSearchFilter  implements Serializable {

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


    
    
