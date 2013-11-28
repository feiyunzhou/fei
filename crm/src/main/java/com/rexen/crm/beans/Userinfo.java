/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "userinfo")
@NamedQueries(
{
  @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u")
})
public class Userinfo implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;
  @Basic(optional = false)
  @Column(name = "name")
  private String name;
  @Column(name = "department")
  private String department;
  @Column(name = "division")
  private String division;
  @Column(name = "cellPhone")
  private String cellPhone;
  @Column(name = "email")
  private String email;
  @Column(name = "employeeNumber")
  private String employeeNumber;
  @Column(name = "photo")
  private String photo;
  @Column(name = "jobTitle")
  private String jobTitle;
  @Column(name = "pl1")
  private int pl1;
  @Column(name = "pl2")
  private int pl2;
  @Column(name = "role")
  private int role;
  @Column(name = "pl4")
  private int pl4;
  @Column(name = "pl5")
  private int pl5;
  @Column(name = "sex")
  private int sex;
  @Column(name = "loginName")
  private String loginName;
  @Column(name = "password")
  private String password;
  @Column(name = "sessionKey")
  private String sessionKey;
  @Column(name = "lastLoginTime")
  private long lastLoginTime;
  @Column(name = "whenadded")
  @Temporal(TemporalType.TIMESTAMP)
  private Date whenadded;
  @Column(name = "parcel")
  private String parcel;
  @Column(name = "modifier")
  private String modifier;
  @Column(name = "modify_datetime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDatetime;
  @Column(name = "owner")
  private String owner;
  @Column(name = "province")
  private int province;
  @Column(name = "city")
  private int city;
  @Column(name = "office_tel")
  private String officeTel;
  @Column(name = "num_of_signIn")
  private int numofsignIn;

  public Userinfo()
  {
  }

  public Userinfo(int id)
  {
    this.id = id;
  }

  public Userinfo(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDepartment()
  {
    return department;
  }

  public void setDepartment(String department)
  {
    this.department = department;
  }

  public String getDivision()
  {
    return division;
  }

  public void setDivision(String division)
  {
    this.division = division;
  }

  public String getCellPhone()
  {
    return cellPhone;
  }

  public void setCellPhone(String cellPhone)
  {
    this.cellPhone = cellPhone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmployeeNumber()
  {
    return employeeNumber;
  }

  public void setEmployeeNumber(String employeeNumber)
  {
    this.employeeNumber = employeeNumber;
  }

  public String getPhoto()
  {
    return photo;
  }

  public void setPhoto(String photo)
  {
    this.photo = photo;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }

  public int getPl1()
  {
    return pl1;
  }

  public void setPl1(int pl1)
  {
    this.pl1 = pl1;
  }

  public int getPl2()
  {
    return pl2;
  }

  public void setPl2(int pl2)
  {
    this.pl2 = pl2;
  }

  public int getRole()
  {
    return role;
  }

  public void setRole(int role)
  {
    this.role = role;
  }

  public int getPl4()
  {
    return pl4;
  }

  public void setPl4(int pl4)
  {
    this.pl4 = pl4;
  }

  public int getPl5()
  {
    return pl5;
  }

  public void setPl5(int pl5)
  {
    this.pl5 = pl5;
  }

  public int getSex()
  {
    return sex;
  }

  public void setSex(int sex)
  {
    this.sex = sex;
  }

  public String getLoginName()
  {
    return loginName;
  }

  public void setLoginName(String loginName)
  {
    this.loginName = loginName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getSessionKey()
  {
    return sessionKey;
  }

  public void setSessionKey(String sessionKey)
  {
    this.sessionKey = sessionKey;
  }

  public long getLastLoginTime()
  {
    return lastLoginTime;
  }

  public void setLastLoginTime(long lastLoginTime)
  {
    this.lastLoginTime = lastLoginTime;
  }

  public Date getWhenadded()
  {
    return whenadded;
  }

  public void setWhenadded(Date whenadded)
  {
    this.whenadded = whenadded;
  }

  public String getParcel()
  {
    return parcel;
  }

  public void setParcel(String parcel)
  {
    this.parcel = parcel;
  }

  public String getModifier()
  {
    return modifier;
  }

  public void setModifier(String modifier)
  {
    this.modifier = modifier;
  }

  public Date getModifyDatetime()
  {
    return modifyDatetime;
  }

  public void setModifyDatetime(Date modifyDatetime)
  {
    this.modifyDatetime = modifyDatetime;
  }

  public String getOwner()
  {
    return owner;
  }

  public void setOwner(String owner)
  {
    this.owner = owner;
  }

  public int getProvince()
  {
    return province;
  }

  public void setProvince(int province)
  {
    this.province = province;
  }

  public int getCity()
  {
    return city;
  }

  public void setCity(int city)
  {
    this.city = city;
  }

  public String getOfficeTel()
  {
    return officeTel;
  }

  public void setOfficeTel(String officeTel)
  {
    this.officeTel = officeTel;
  }

  public int getNumofsignIn()
  {
    return numofsignIn;
  }

  public void setNumofsignIn(int numofsignIn)
  {
    this.numofsignIn = numofsignIn;
  }
}
