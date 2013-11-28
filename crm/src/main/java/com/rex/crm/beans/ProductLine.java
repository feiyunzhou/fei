package com.rex.crm.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProductLine {
	private int id;
	private String val;
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, ProductLine.class).toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@SuppressWarnings("all")
	public Map toMap(){
		Map map = new HashMap();
		map.put("val", val);
		return map;
	}
	
	
	   public static Map<String,String> getMappingOfField2ColumnName(){
	        Map<String,String> list = new HashMap<String,String>();
	        list.put("val", "名称");
	        return list;
	    }
	    
	    public static List<String> getFieldNames(){
	        List<String> list = new ArrayList<String>();
	        Field[] fields = CRMUser.class.getDeclaredFields(); 
	        for(Field f:fields){
	            list.add(f.getName());
	        }
	        return list;
	    }

        
		
	
}
