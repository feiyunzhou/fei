/*
 * Copyright 2013 Ralf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rexen.crm.beans;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "userinfo")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
  @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
  @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
  @NamedQuery(name = "User.findByDepartment", query = "SELECT u FROM User u WHERE u.department = :department"),
  @NamedQuery(name = "User.findByDivision", query = "SELECT u FROM User u WHERE u.division = :division"),
  @NamedQuery(name = "User.findByCellPhone", query = "SELECT u FROM User u WHERE u.cellPhone = :cellPhone"),
  @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
  @NamedQuery(name = "User.findByEmployeeNumber", query = "SELECT u FROM User u WHERE u.employeeNumber = :employeeNumber"),
  @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo"),
  @NamedQuery(name = "User.findByJobTitle", query = "SELECT u FROM User u WHERE u.jobTitle = :jobTitle"),
  @NamedQuery(name = "User.findByPl1", query = "SELECT u FROM User u WHERE u.pl1 = :pl1"),
  @NamedQuery(name = "User.findByPl2", query = "SELECT u FROM User u WHERE u.pl2 = :pl2"),
  @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
  @NamedQuery(name = "User.findByPl4", query = "SELECT u FROM User u WHERE u.pl4 = :pl4"),
  @NamedQuery(name = "User.findByPl5", query = "SELECT u FROM User u WHERE u.pl5 = :pl5"),
  @NamedQuery(name = "User.findBySex", query = "SELECT u FROM User u WHERE u.sex = :sex"),
  @NamedQuery(name = "User.findByLoginName", query = "SELECT u FROM User u WHERE u.loginName = :loginName"),
  @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
  @NamedQuery(name = "User.findBySessionKey", query = "SELECT u FROM User u WHERE u.sessionKey = :sessionKey"),
  @NamedQuery(name = "User.findByLastLoginTime", query = "SELECT u FROM User u WHERE u.lastLoginTime = :lastLoginTime"),
  @NamedQuery(name = "User.findByWhenadded", query = "SELECT u FROM User u WHERE u.whenadded = :whenadded"),
  @NamedQuery(name = "User.findByParcel", query = "SELECT u FROM User u WHERE u.parcel = :parcel"),
  @NamedQuery(name = "User.findByModifier", query = "SELECT u FROM User u WHERE u.modifier = :modifier"),
  @NamedQuery(name = "User.findByModifyDatetime", query = "SELECT u FROM User u WHERE u.modifyDatetime = :modifyDatetime"),
  @NamedQuery(name = "User.findByOwner", query = "SELECT u FROM User u WHERE u.owner = :owner"),
  @NamedQuery(name = "User.findByProvince", query = "SELECT u FROM User u WHERE u.province = :province"),
  @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
  @NamedQuery(name = "User.findByIsActivited", query = "SELECT u FROM User u WHERE u.isActivited = :isActivited"),
  @NamedQuery(name = "User.findByTs", query = "SELECT u FROM User u WHERE u.ts = :ts"),
  @NamedQuery(name = "User.findByPositionId", query = "SELECT u FROM User u WHERE u.positionId = :positionId")
})
public class User implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
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
  private Integer pl1;
  @Column(name = "pl2")
  private Integer pl2;
  @Column(name = "role")
  private Integer role;
  @Column(name = "pl4")
  private Integer pl4;
  @Column(name = "pl5")
  private Integer pl5;
  @Column(name = "sex")
  private Integer sex;
  @Column(name = "loginName")
  private String loginName;
  @Column(name = "password")
  private String password;
  @Column(name = "sessionKey")
  private String sessionKey;
  @Column(name = "lastLoginTime")
  private BigInteger lastLoginTime;
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
  private Integer province;
  @Column(name = "city")
  private Integer city;
  @Column(name = "isActivited")
  private Integer isActivited;
  @Column(name = "ts")
  private BigInteger ts;
  @Column(name = "positionId")
  private Integer positionId;

  public User()
  {
  }

  public User(Integer id)
  {
    this.id = id;
  }

  public User(Integer id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
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

  public Integer getPl1()
  {
    return pl1;
  }

  public void setPl1(Integer pl1)
  {
    this.pl1 = pl1;
  }

  public Integer getPl2()
  {
    return pl2;
  }

  public void setPl2(Integer pl2)
  {
    this.pl2 = pl2;
  }

  public Integer getRole()
  {
    return role;
  }

  public void setRole(Integer role)
  {
    this.role = role;
  }

  public Integer getPl4()
  {
    return pl4;
  }

  public void setPl4(Integer pl4)
  {
    this.pl4 = pl4;
  }

  public Integer getPl5()
  {
    return pl5;
  }

  public void setPl5(Integer pl5)
  {
    this.pl5 = pl5;
  }

  public Integer getSex()
  {
    return sex;
  }

  public void setSex(Integer sex)
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

  public BigInteger getLastLoginTime()
  {
    return lastLoginTime;
  }

  public void setLastLoginTime(BigInteger lastLoginTime)
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

  public Integer getProvince()
  {
    return province;
  }

  public void setProvince(Integer province)
  {
    this.province = province;
  }

  public Integer getCity()
  {
    return city;
  }

  public void setCity(Integer city)
  {
    this.city = city;
  }

  public Integer getIsActivited()
  {
    return isActivited;
  }

  public void setIsActivited(Integer isActivited)
  {
    this.isActivited = isActivited;
  }

  public BigInteger getTs()
  {
    return ts;
  }

  public void setTs(BigInteger ts)
  {
    this.ts = ts;
  }

  public Integer getPositionId()
  {
    return positionId;
  }

  public void setPositionId(Integer positionId)
  {
    this.positionId = positionId;
  }

  @Override
  public int hashCode()
  {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object)
  {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User))
    {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.User[ id=" + id + " ]";
  }
  
}
