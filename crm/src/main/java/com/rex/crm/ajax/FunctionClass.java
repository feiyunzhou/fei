/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rex.crm.ajax;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author Feiyun
 */
public class FunctionClass {
    private String f;
    private String[] p;
    private SessionInfo s;
    private JsonElement cp;//complex parameters in form of json
    
    public String getF() {
        return f;
    }

    public String[] getP() {
        return p;
    }
    
    public void setF(String f){
       this.f = f;
    }
    
    public void setP(String[] p){
      this.p = p;
    }
   
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (p != null) {
            for (String pa : p) {
                sb.append("," + pa);
            }
            
            if(s!=null){
                sb.append(" session id:"+s.getId());
                sb.append(" session key:"+s.getKey());
            }
        }
        return String.format("function:%s params:%s", f,sb);
    }

    public SessionInfo getS() {
        return s;
    }

    public void setS(SessionInfo s) {
        this.s = s;
    }

    public JsonElement getCp() {
        return cp;
    }

    public void setCp(JsonElement cp) {
        this.cp = cp;
    }

    
}
