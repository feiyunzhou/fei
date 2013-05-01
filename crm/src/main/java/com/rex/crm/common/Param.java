package com.rex.crm.common;

import java.io.Serializable;

public class Param implements Serializable{
    private String id;
    private String entityName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}
