package com.rex.crm.ajax;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author Feiyun
 */
public class TableRow {
    public JsonArray row = new JsonArray();
    
    public void addCell(String str){
       JsonPrimitive jp = new JsonPrimitive(str);
       row.add(jp);
    }
    
    public void addCell(Number num){
       JsonPrimitive jp = new JsonPrimitive(num);
       row.add(jp);
    }
}
