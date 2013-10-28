package com.rex.crm.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CRMUser {
	private int id;
	private String name;
	private String cellPhone;
	private String company;
	private String businessUnit;
	private String department;
	private String division;
	private String employeeNumber;
	private String jobTitle;
	private String email;
	private int cityId;
	private String photo;
	private String password;
	private String loginName;
	private String sessionKey;
	private long lastLoginTime;
	private int role;
	private int reportto;
	private int sex;
    private int isActivited;
    private long ts;
    private int pl1;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String emplyeeNumber) {
		this.employeeNumber = emplyeeNumber;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIsActivited() {
		return isActivited;
	}

	public void setIsActivited(int isActivited) {
		this.isActivited = isActivited;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, CRMUser.class).toString();
	}

	@SuppressWarnings("all")
	public Map toMap(){
		Map map = new HashMap();
		map.put("cellPhone", cellPhone);
		map.put("division", division);
		map.put("department", department);
		map.put("email", email);
		map.put("jobTitle", jobTitle);
		map.put("name", name);
		map.put("id", id);
		map.put("photo", photo);
		map.put("isActivited","isActivited");
		return map;
	}
	
	
	   public static Map<String,String> getMappingOfField2ColumnName(){
	        Map<String,String> list = new HashMap<String,String>();
	        list.put("name", "名称");
	        list.put("cellPhone", "电话");
	        list.put("email", "邮箱");
	        list.put("jobTitle", "职位");
	        list.put("division", "部门");
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getReportto() {
            return reportto;
        }

        public void setReportto(int reportto) {
            this.reportto = reportto;
        }

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public long getTs() {
			return ts;
		}

		public void setTs(long ts) {
			this.ts = ts;
		}

		public int getPl1() {
			return pl1;
		}

		public void setPl1(int pl1) {
			this.pl1 = pl1;
		}

		
	
}
