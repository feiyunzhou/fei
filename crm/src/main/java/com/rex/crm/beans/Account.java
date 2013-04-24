package com.rex.crm.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Account {
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String tele;
	@Expose
	private String level;
	@Expose
	private String classification;
	@Expose
	private boolean isKeyAccount;
	@Expose
	private String status;
	@Expose
	private String address;
	private Contact[] contacts;
	private CRMUser[] team;
	@Expose
	private int cityId;
	private String photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public boolean isKeyAccount() {
		return isKeyAccount;
	}

	public void setKeyAccount(boolean isKeyAccount) {
		this.isKeyAccount = isKeyAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, Account.class).toString();
	}
	
	public String htmlOutput(){
		StringBuffer sb= new StringBuffer();
		sb.append("名称：").append(this.name).append("\n");
		sb.append("电话：").append(this.tele).append("\n");
		sb.append("类型：").append(this.classification).append("\n");
		sb.append("地址：").append(this.address).append("\n");
		return sb.toString();
	}

	public Contact[] getContacts() {
		return contacts;
	}

	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}

	public CRMUser[] getTeam() {
		return team;
	}

	public void setTeam(CRMUser[] team) {
		this.team = team;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@SuppressWarnings("all")
	public Map toMap(){
		Map map = new HashMap();
		map.put("name", getName());
		map.put("address", getAddress());
		map.put("classification", getClassification());
		map.put("id", getId());
		map.put("tele", getTele());
		map.put("photo", photo);
		return map;
	}

	public static Map<String,String> getMappingOfField2ColumnName(){
	    Map<String,String> list = new HashMap<String,String>();
	    list.put("name", "名称");
	    list.put("address", "地址");
	    list.put("classification", "类型");
	    list.put("tele", "电话");
	    return list;
	}
	
	public static List<String> getFieldNames(){
	    List<String> list = new ArrayList<String>();
	    Field[] fields = Account.class.getDeclaredFields(); 
	    for(Field f:fields){
	        list.add(f.getName());
	    }
	    return list;
	}
}
