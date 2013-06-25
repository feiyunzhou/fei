/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rex.crm.ajax;

import java.util.List;

/**
 *
 * @author Feiyun
 */
public class FunctionClass {
    private String f;
    private String[] p;
    private SessionInfo s;
    
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
    
}
