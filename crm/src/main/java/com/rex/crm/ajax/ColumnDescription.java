package com.rex.crm.ajax;

import com.google.gson.JsonObject;

/**
 *
 * @author Feiyun
 */
public class ColumnDescription {
    public JsonObject columnDes;
    
    
    public ColumnDescription(String title){
      columnDes = new JsonObject();
      columnDes.addProperty("sTitle", title);
    }
}
