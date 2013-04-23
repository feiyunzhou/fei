package com.rex.crm.beans;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class City implements Serializable {

	private static final long serialVersionUID = 943058094848141611L;
	
	private String name;
	private Account[] accounts;
	private int id;
	private int provinceId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Account[] getAccounts() {
		return accounts;
	}
	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, City.class).toString();
		
	}

	

}
