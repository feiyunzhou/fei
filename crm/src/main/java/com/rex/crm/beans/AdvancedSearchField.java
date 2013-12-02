package com.rex.crm.beans;

import java.io.Serializable;

public class AdvancedSearchField  implements Serializable  {
    
    private String type;
    private String id;
    private String label;
    private AdvancedSearchField[] list;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public AdvancedSearchField[] getList() {
        return list;
    }
    public void setList(AdvancedSearchField[] list) {
        this.list = list;
    }

}
