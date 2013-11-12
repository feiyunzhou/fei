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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
  @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
  @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
  @NamedQuery(name = "Contact.findByDepartment", query = "SELECT c FROM Contact c WHERE c.department = :department"),
  @NamedQuery(name = "Contact.findBySex", query = "SELECT c FROM Contact c WHERE c.sex = :sex"),
  @NamedQuery(name = "Contact.findByNativePlace", query = "SELECT c FROM Contact c WHERE c.nativePlace = :nativePlace"),
  @NamedQuery(name = "Contact.findByOfficeTel", query = "SELECT c FROM Contact c WHERE c.officeTel = :officeTel"),
  @NamedQuery(name = "Contact.findByOfficeFax", query = "SELECT c FROM Contact c WHERE c.officeFax = :officeFax"),
  @NamedQuery(name = "Contact.findByCellphone", query = "SELECT c FROM Contact c WHERE c.cellphone = :cellphone"),
  @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
  @NamedQuery(name = "Contact.findByStatus", query = "SELECT c FROM Contact c WHERE c.status = :status"),
  @NamedQuery(name = "Contact.findByMarketClassification", query = "SELECT c FROM Contact c WHERE c.marketClassification = :marketClassification"),
  @NamedQuery(name = "Contact.findByGrade", query = "SELECT c FROM Contact c WHERE c.grade = :grade"),
  @NamedQuery(name = "Contact.findByProvince", query = "SELECT c FROM Contact c WHERE c.province = :province"),
  @NamedQuery(name = "Contact.findByCity", query = "SELECT c FROM Contact c WHERE c.city = :city"),
  @NamedQuery(name = "Contact.findByDistricts", query = "SELECT c FROM Contact c WHERE c.districts = :districts"),
  @NamedQuery(name = "Contact.findByDuty", query = "SELECT c FROM Contact c WHERE c.duty = :duty"),
  @NamedQuery(name = "Contact.findByJobTitle", query = "SELECT c FROM Contact c WHERE c.jobTitle = :jobTitle"),
  @NamedQuery(name = "Contact.findByVisitingTarget", query = "SELECT c FROM Contact c WHERE c.visitingTarget = :visitingTarget"),
  @NamedQuery(name = "Contact.findByProductTarget", query = "SELECT c FROM Contact c WHERE c.productTarget = :productTarget"),
  @NamedQuery(name = "Contact.findByOwner", query = "SELECT c FROM Contact c WHERE c.owner = :owner"),
  @NamedQuery(name = "Contact.findByWhenadded", query = "SELECT c FROM Contact c WHERE c.whenadded = :whenadded"),
  @NamedQuery(name = "Contact.findByModifier", query = "SELECT c FROM Contact c WHERE c.modifier = :modifier"),
  @NamedQuery(name = "Contact.findByModifyDatetime", query = "SELECT c FROM Contact c WHERE c.modifyDatetime = :modifyDatetime"),
  @NamedQuery(name = "Contact.findByResponsiblePerson", query = "SELECT c FROM Contact c WHERE c.responsiblePerson = :responsiblePerson")
})
public class Contact implements Serializable
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
  @Column(name = "sex")
  private int sex;
  @Column(name = "native_place")
  private String nativePlace;
  @Column(name = "office_tel")
  private String officeTel;
  @Column(name = "office_fax")
  private String officeFax;
  @Column(name = "cellphone")
  private String cellphone;
  @Column(name = "email")
  private String email;
  @Column(name = "status")
  private String status;
  @Column(name = "market_classification")
  private int marketClassification;
  @Column(name = "grade")
  private int grade;
  @Column(name = "province")
  private int province;
  @Column(name = "city")
  private int city;
  @Column(name = "districts")
  private String districts;
  @Column(name = "duty")
  private int duty;
  @Column(name = "job_title")
  private int jobTitle;
  @Column(name = "visiting_target")
  private int visitingTarget;
  @Column(name = "product_target")
  private String productTarget;
  @Column(name = "owner")
  private String owner;
  @Column(name = "whenadded")
  @Temporal(TemporalType.TIMESTAMP)
  private Date whenadded;
  @Column(name = "modifier")
  private String modifier;
  @Column(name = "modify_datetime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDatetime;
  @Column(name = "responsible_person")
  private String responsiblePerson;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
  private List<ContactTeam> contactTeamList;
  @JoinColumn(name = "accountId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Account account;

  public Contact()
  {
  }

  public Contact(int id)
  {
    this.id = id;
  }

  public Contact(int id, String name)
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

  public int getSex()
  {
    return sex;
  }

  public void setSex(int sex)
  {
    this.sex = sex;
  }

  public String getNativePlace()
  {
    return nativePlace;
  }

  public void setNativePlace(String nativePlace)
  {
    this.nativePlace = nativePlace;
  }

  public String getOfficeTel()
  {
    return officeTel;
  }

  public void setOfficeTel(String officeTel)
  {
    this.officeTel = officeTel;
  }

  public String getOfficeFax()
  {
    return officeFax;
  }

  public void setOfficeFax(String officeFax)
  {
    this.officeFax = officeFax;
  }

  public String getCellphone()
  {
    return cellphone;
  }

  public void setCellphone(String cellphone)
  {
    this.cellphone = cellphone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public int getMarketClassification()
  {
    return marketClassification;
  }

  public void setMarketClassification(int marketClassification)
  {
    this.marketClassification = marketClassification;
  }

  public int getGrade()
  {
    return grade;
  }

  public void setGrade(int grade)
  {
    this.grade = grade;
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

  public String getDistricts()
  {
    return districts;
  }

  public void setDistricts(String districts)
  {
    this.districts = districts;
  }

  public int getDuty()
  {
    return duty;
  }

  public void setDuty(int duty)
  {
    this.duty = duty;
  }

  public int getJobTitle()
  {
    return jobTitle;
  }

  public void setJobTitle(int jobTitle)
  {
    this.jobTitle = jobTitle;
  }

  public int getVisitingTarget()
  {
    return visitingTarget;
  }

  public void setVisitingTarget(int visitingTarget)
  {
    this.visitingTarget = visitingTarget;
  }

  public String getProductTarget()
  {
    return productTarget;
  }

  public void setProductTarget(String productTarget)
  {
    this.productTarget = productTarget;
  }

  public String getOwner()
  {
    return owner;
  }

  public void setOwner(String owner)
  {
    this.owner = owner;
  }

  public Date getWhenadded()
  {
    return whenadded;
  }

  public void setWhenadded(Date whenadded)
  {
    this.whenadded = whenadded;
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

  public String getResponsiblePerson()
  {
    return responsiblePerson;
  }

  public void setResponsiblePerson(String responsiblePerson)
  {
    this.responsiblePerson = responsiblePerson;
  }

  @XmlTransient
  public List<ContactTeam> getContactTeamList()
  {
    return contactTeamList;
  }

  public void setContactTeamList(List<ContactTeam> contactTeamList)
  {
    this.contactTeamList = contactTeamList;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.Contact[ id=" + id + " ]";
  }
  
}
