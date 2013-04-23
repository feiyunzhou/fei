package com.rex.crm.beans;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Province  implements Serializable {

	private static final long serialVersionUID = -4347708252049971152L;

    private String name;
    private City[] cities;
    private int id;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public City[] getCities() {
		return cities;
	}
	public void setCities(City[] cities) {
		this.cities = cities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, Province.class).toString();
	}
	
	
}
