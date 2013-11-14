/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "contact")
@NamedQueries(
{
  @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
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
  @Column(name = "contactCode")
  private String contactCode;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact")
  private List<Contactcrmuser> contactcrmuserList;
  @JoinColumn(name = "accountId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Account account;
  @OneToMany(mappedBy = "contact")
  private List<Activity> activityList;

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

  public String getContactCode()
  {
    return contactCode;
  }

  public void setContactCode(String contactCode)
  {
    this.contactCode = contactCode;
  }

  public List<Contactcrmuser> getContactcrmuserList()
  {
    return contactcrmuserList;
  }

  public void setContactcrmuserList(List<Contactcrmuser> contactcrmuserList)
  {
    this.contactcrmuserList = contactcrmuserList;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  public List<Activity> getActivityList()
  {
    return activityList;
  }

  public void setActivityList(List<Activity> activityList)
  {
    this.activityList = activityList;
  }
}
