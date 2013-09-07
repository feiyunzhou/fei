package com.rex.crm.beans;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Contact implements Serializable  {
	private int id;
	private String name;
	private String duty;
	private String title;
	private String branch;
	private String department;
	private String gender;
	private String birth;
	private String tel_work;
	private String mobilephone;
	private String accountBelongTo;
	private int accountId;

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

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel_work() {
		return tel_work;
	}

	public void setTel_work(String tel_work) {
		this.tel_work = tel_work;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAccountBelongTo() {
		return accountBelongTo;
	}

	public void setAccountBelongTo(String accountBelongTo) {
		this.accountBelongTo = accountBelongTo;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, Contact.class).toString();
	}
}
