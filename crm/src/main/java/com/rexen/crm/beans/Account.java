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
@Table(name = "account")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
  @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
  @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
  @NamedQuery(name = "Account.findByBdmCode", query = "SELECT a FROM Account a WHERE a.bdmCode = :bdmCode"),
  @NamedQuery(name = "Account.findByAdministrativLevel", query = "SELECT a FROM Account a WHERE a.administrativLevel = :administrativLevel"),
  @NamedQuery(name = "Account.findByGrade", query = "SELECT a FROM Account a WHERE a.grade = :grade"),
  @NamedQuery(name = "Account.findByHospitalType", query = "SELECT a FROM Account a WHERE a.hospitalType = :hospitalType"),
  @NamedQuery(name = "Account.findByLocalOrArmy", query = "SELECT a FROM Account a WHERE a.localOrArmy = :localOrArmy"),
  @NamedQuery(name = "Account.findByComprehensiveOrSpecialized", query = "SELECT a FROM Account a WHERE a.comprehensiveOrSpecialized = :comprehensiveOrSpecialized"),
  @NamedQuery(name = "Account.findByKeyType", query = "SELECT a FROM Account a WHERE a.keyType = :keyType"),
  @NamedQuery(name = "Account.findByStatus", query = "SELECT a FROM Account a WHERE a.status = :status"),
  @NamedQuery(name = "Account.findByDutyOfficer", query = "SELECT a FROM Account a WHERE a.dutyOfficer = :dutyOfficer"),
  @NamedQuery(name = "Account.findByNumOfDoctors", query = "SELECT a FROM Account a WHERE a.numOfDoctors = :numOfDoctors"),
  @NamedQuery(name = "Account.findByNumOfAssistantDoctors", query = "SELECT a FROM Account a WHERE a.numOfAssistantDoctors = :numOfAssistantDoctors"),
  @NamedQuery(name = "Account.findByNumOfStaff", query = "SELECT a FROM Account a WHERE a.numOfStaff = :numOfStaff"),
  @NamedQuery(name = "Account.findByNumOfTreatPerYear", query = "SELECT a FROM Account a WHERE a.numOfTreatPerYear = :numOfTreatPerYear"),
  @NamedQuery(name = "Account.findByNumOfOutpatient", query = "SELECT a FROM Account a WHERE a.numOfOutpatient = :numOfOutpatient"),
  @NamedQuery(name = "Account.findByTotalNumOfSickbed", query = "SELECT a FROM Account a WHERE a.totalNumOfSickbed = :totalNumOfSickbed"),
  @NamedQuery(name = "Account.findByNumOfAnesthesiaDoctor", query = "SELECT a FROM Account a WHERE a.numOfAnesthesiaDoctor = :numOfAnesthesiaDoctor"),
  @NamedQuery(name = "Account.findByNumOfPainDoctor", query = "SELECT a FROM Account a WHERE a.numOfPainDoctor = :numOfPainDoctor"),
  @NamedQuery(name = "Account.findByNumOfSurgeryPerYear", query = "SELECT a FROM Account a WHERE a.numOfSurgeryPerYear = :numOfSurgeryPerYear"),
  @NamedQuery(name = "Account.findByNumOfSurgeryRoom", query = "SELECT a FROM Account a WHERE a.numOfSurgeryRoom = :numOfSurgeryRoom"),
  @NamedQuery(name = "Account.findByNumOfUsingOpiatesMedicine", query = "SELECT a FROM Account a WHERE a.numOfUsingOpiatesMedicine = :numOfUsingOpiatesMedicine"),
  @NamedQuery(name = "Account.findByNumOfUsingOpiatesInjection", query = "SELECT a FROM Account a WHERE a.numOfUsingOpiatesInjection = :numOfUsingOpiatesInjection"),
  @NamedQuery(name = "Account.findByDateOfEstablish", query = "SELECT a FROM Account a WHERE a.dateOfEstablish = :dateOfEstablish"),
  @NamedQuery(name = "Account.findByRegisteredCapital", query = "SELECT a FROM Account a WHERE a.registeredCapital = :registeredCapital"),
  @NamedQuery(name = "Account.findByTel", query = "SELECT a FROM Account a WHERE a.tel = :tel"),
  @NamedQuery(name = "Account.findByFax", query = "SELECT a FROM Account a WHERE a.fax = :fax"),
  @NamedQuery(name = "Account.findByMarketClassification", query = "SELECT a FROM Account a WHERE a.marketClassification = :marketClassification"),
  @NamedQuery(name = "Account.findByProvince", query = "SELECT a FROM Account a WHERE a.province = :province"),
  @NamedQuery(name = "Account.findByCity", query = "SELECT a FROM Account a WHERE a.city = :city"),
  @NamedQuery(name = "Account.findByDistricts", query = "SELECT a FROM Account a WHERE a.districts = :districts"),
  @NamedQuery(name = "Account.findByAddress", query = "SELECT a FROM Account a WHERE a.address = :address"),
  @NamedQuery(name = "Account.findByOwner", query = "SELECT a FROM Account a WHERE a.owner = :owner"),
  @NamedQuery(name = "Account.findByWhenadded", query = "SELECT a FROM Account a WHERE a.whenadded = :whenadded"),
  @NamedQuery(name = "Account.findByModifier", query = "SELECT a FROM Account a WHERE a.modifier = :modifier"),
  @NamedQuery(name = "Account.findByModifyDatetime", query = "SELECT a FROM Account a WHERE a.modifyDatetime = :modifyDatetime"),
  @NamedQuery(name = "Account.findByResponsiblePerson", query = "SELECT a FROM Account a WHERE a.responsiblePerson = :responsiblePerson")
})
public class Account implements Serializable
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
  @Column(name = "bdm_code")
  private String bdmCode;
  @Column(name = "administrativ_level")
  private String administrativLevel;
  @Column(name = "grade")
  private String grade;
  @Column(name = "hospital_type")
  private int hospitalType;
  @Column(name = "local_or_army")
  private int localOrArmy;
  @Column(name = "comprehensive_or_specialized")
  private int comprehensiveOrSpecialized;
  @Column(name = "key_type")
  private int keyType;
  @Column(name = "status")
  private int status;
  @Column(name = "duty_officer")
  private String dutyOfficer;
  @Column(name = "num_of_doctors")
  private int numOfDoctors;
  @Column(name = "num_of_assistant_doctors")
  private int numOfAssistantDoctors;
  @Column(name = "num_of_staff")
  private int numOfStaff;
  @Column(name = "num_of_treat_per_year")
  private int numOfTreatPerYear;
  @Column(name = "num_of_outpatient")
  private int numOfOutpatient;
  @Column(name = "total_num_of_sickbed")
  private int totalNumOfSickbed;
  @Column(name = "num_of_anesthesia_doctor")
  private int numOfAnesthesiaDoctor;
  @Column(name = "num_of_pain_doctor")
  private int numOfPainDoctor;
  @Column(name = "num_of_surgery_per_year")
  private int numOfSurgeryPerYear;
  @Column(name = "num_of_surgery_room")
  private int numOfSurgeryRoom;
  @Column(name = "num_of_using_opiates_medicine")
  private int numOfUsingOpiatesMedicine;
  @Column(name = "num_of_using_opiates_injection")
  private int numOfUsingOpiatesInjection;
  @Column(name = "date_of_establish")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfEstablish;
  @Column(name = "registered_capital")
  private int registeredCapital;
  @Column(name = "tel")
  private String tel;
  @Column(name = "fax")
  private String fax;
  @Column(name = "market_classification")
  private int marketClassification;
  @Column(name = "province")
  private int province;
  @Column(name = "city")
  private int city;
  @Column(name = "districts")
  private String districts;
  @Column(name = "address")
  private String address;
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<Contact> contactList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<AccountTeam> accountTeamList;

  public Account()
  {
  }

  public Account(int id)
  {
    this.id = id;
  }

  public Account(int id, String name)
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

  public String getBdmCode()
  {
    return bdmCode;
  }

  public void setBdmCode(String bdmCode)
  {
    this.bdmCode = bdmCode;
  }

  public String getAdministrativLevel()
  {
    return administrativLevel;
  }

  public void setAdministrativLevel(String administrativLevel)
  {
    this.administrativLevel = administrativLevel;
  }

  public String getGrade()
  {
    return grade;
  }

  public void setGrade(String grade)
  {
    this.grade = grade;
  }

  public int getHospitalType()
  {
    return hospitalType;
  }

  public void setHospitalType(int hospitalType)
  {
    this.hospitalType = hospitalType;
  }

  public int getLocalOrArmy()
  {
    return localOrArmy;
  }

  public void setLocalOrArmy(int localOrArmy)
  {
    this.localOrArmy = localOrArmy;
  }

  public int getComprehensiveOrSpecialized()
  {
    return comprehensiveOrSpecialized;
  }

  public void setComprehensiveOrSpecialized(int comprehensiveOrSpecialized)
  {
    this.comprehensiveOrSpecialized = comprehensiveOrSpecialized;
  }

  public int getKeyType()
  {
    return keyType;
  }

  public void setKeyType(int keyType)
  {
    this.keyType = keyType;
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public String getDutyOfficer()
  {
    return dutyOfficer;
  }

  public void setDutyOfficer(String dutyOfficer)
  {
    this.dutyOfficer = dutyOfficer;
  }

  public int getNumOfDoctors()
  {
    return numOfDoctors;
  }

  public void setNumOfDoctors(int numOfDoctors)
  {
    this.numOfDoctors = numOfDoctors;
  }

  public int getNumOfAssistantDoctors()
  {
    return numOfAssistantDoctors;
  }

  public void setNumOfAssistantDoctors(int numOfAssistantDoctors)
  {
    this.numOfAssistantDoctors = numOfAssistantDoctors;
  }

  public int getNumOfStaff()
  {
    return numOfStaff;
  }

  public void setNumOfStaff(int numOfStaff)
  {
    this.numOfStaff = numOfStaff;
  }

  public int getNumOfTreatPerYear()
  {
    return numOfTreatPerYear;
  }

  public void setNumOfTreatPerYear(int numOfTreatPerYear)
  {
    this.numOfTreatPerYear = numOfTreatPerYear;
  }

  public int getNumOfOutpatient()
  {
    return numOfOutpatient;
  }

  public void setNumOfOutpatient(int numOfOutpatient)
  {
    this.numOfOutpatient = numOfOutpatient;
  }

  public int getTotalNumOfSickbed()
  {
    return totalNumOfSickbed;
  }

  public void setTotalNumOfSickbed(int totalNumOfSickbed)
  {
    this.totalNumOfSickbed = totalNumOfSickbed;
  }

  public int getNumOfAnesthesiaDoctor()
  {
    return numOfAnesthesiaDoctor;
  }

  public void setNumOfAnesthesiaDoctor(int numOfAnesthesiaDoctor)
  {
    this.numOfAnesthesiaDoctor = numOfAnesthesiaDoctor;
  }

  public int getNumOfPainDoctor()
  {
    return numOfPainDoctor;
  }

  public void setNumOfPainDoctor(int numOfPainDoctor)
  {
    this.numOfPainDoctor = numOfPainDoctor;
  }

  public int getNumOfSurgeryPerYear()
  {
    return numOfSurgeryPerYear;
  }

  public void setNumOfSurgeryPerYear(int numOfSurgeryPerYear)
  {
    this.numOfSurgeryPerYear = numOfSurgeryPerYear;
  }

  public int getNumOfSurgeryRoom()
  {
    return numOfSurgeryRoom;
  }

  public void setNumOfSurgeryRoom(int numOfSurgeryRoom)
  {
    this.numOfSurgeryRoom = numOfSurgeryRoom;
  }

  public int getNumOfUsingOpiatesMedicine()
  {
    return numOfUsingOpiatesMedicine;
  }

  public void setNumOfUsingOpiatesMedicine(int numOfUsingOpiatesMedicine)
  {
    this.numOfUsingOpiatesMedicine = numOfUsingOpiatesMedicine;
  }

  public int getNumOfUsingOpiatesInjection()
  {
    return numOfUsingOpiatesInjection;
  }

  public void setNumOfUsingOpiatesInjection(int numOfUsingOpiatesInjection)
  {
    this.numOfUsingOpiatesInjection = numOfUsingOpiatesInjection;
  }

  public Date getDateOfEstablish()
  {
    return dateOfEstablish;
  }

  public void setDateOfEstablish(Date dateOfEstablish)
  {
    this.dateOfEstablish = dateOfEstablish;
  }

  public int getRegisteredCapital()
  {
    return registeredCapital;
  }

  public void setRegisteredCapital(int registeredCapital)
  {
    this.registeredCapital = registeredCapital;
  }

  public String getTel()
  {
    return tel;
  }

  public void setTel(String tel)
  {
    this.tel = tel;
  }

  public String getFax()
  {
    return fax;
  }

  public void setFax(String fax)
  {
    this.fax = fax;
  }

  public int getMarketClassification()
  {
    return marketClassification;
  }

  public void setMarketClassification(int marketClassification)
  {
    this.marketClassification = marketClassification;
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

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
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
  public List<Contact> getContactList()
  {
    return contactList;
  }

  public void setContactList(List<Contact> contactList)
  {
    this.contactList = contactList;
  }

  @XmlTransient
  public List<AccountTeam> getAccountTeamList()
  {
    return accountTeamList;
  }

  public void setAccountTeamList(List<AccountTeam> accountTeamList)
  {
    this.accountTeamList = accountTeamList;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.Account[ id=" + id + " ]";
  }
  
}
