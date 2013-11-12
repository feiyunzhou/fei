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
import javax.xml.bind.annotation.XmlRootElement;

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
  private Integer id;
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
  private Integer hospitalType;
  @Column(name = "local_or_army")
  private Integer localOrArmy;
  @Column(name = "comprehensive_or_specialized")
  private Integer comprehensiveOrSpecialized;
  @Column(name = "key_type")
  private Integer keyType;
  @Column(name = "status")
  private Integer status;
  @Column(name = "duty_officer")
  private String dutyOfficer;
  @Column(name = "num_of_doctors")
  private Integer numOfDoctors;
  @Column(name = "num_of_assistant_doctors")
  private Integer numOfAssistantDoctors;
  @Column(name = "num_of_staff")
  private Integer numOfStaff;
  @Column(name = "num_of_treat_per_year")
  private Integer numOfTreatPerYear;
  @Column(name = "num_of_outpatient")
  private Integer numOfOutpatient;
  @Column(name = "total_num_of_sickbed")
  private Integer totalNumOfSickbed;
  @Column(name = "num_of_anesthesia_doctor")
  private Integer numOfAnesthesiaDoctor;
  @Column(name = "num_of_pain_doctor")
  private Integer numOfPainDoctor;
  @Column(name = "num_of_surgery_per_year")
  private Integer numOfSurgeryPerYear;
  @Column(name = "num_of_surgery_room")
  private Integer numOfSurgeryRoom;
  @Column(name = "num_of_using_opiates_medicine")
  private Integer numOfUsingOpiatesMedicine;
  @Column(name = "num_of_using_opiates_injection")
  private Integer numOfUsingOpiatesInjection;
  @Column(name = "date_of_establish")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfEstablish;
  @Column(name = "registered_capital")
  private Integer registeredCapital;
  @Column(name = "tel")
  private String tel;
  @Column(name = "fax")
  private String fax;
  @Column(name = "market_classification")
  private Integer marketClassification;
  @Column(name = "province")
  private Integer province;
  @Column(name = "city")
  private Integer city;
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

  public Account()
  {
  }

  public Account(Integer id)
  {
    this.id = id;
  }

  public Account(Integer id, String name)
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

  public Integer getHospitalType()
  {
    return hospitalType;
  }

  public void setHospitalType(Integer hospitalType)
  {
    this.hospitalType = hospitalType;
  }

  public Integer getLocalOrArmy()
  {
    return localOrArmy;
  }

  public void setLocalOrArmy(Integer localOrArmy)
  {
    this.localOrArmy = localOrArmy;
  }

  public Integer getComprehensiveOrSpecialized()
  {
    return comprehensiveOrSpecialized;
  }

  public void setComprehensiveOrSpecialized(Integer comprehensiveOrSpecialized)
  {
    this.comprehensiveOrSpecialized = comprehensiveOrSpecialized;
  }

  public Integer getKeyType()
  {
    return keyType;
  }

  public void setKeyType(Integer keyType)
  {
    this.keyType = keyType;
  }

  public Integer getStatus()
  {
    return status;
  }

  public void setStatus(Integer status)
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

  public Integer getNumOfDoctors()
  {
    return numOfDoctors;
  }

  public void setNumOfDoctors(Integer numOfDoctors)
  {
    this.numOfDoctors = numOfDoctors;
  }

  public Integer getNumOfAssistantDoctors()
  {
    return numOfAssistantDoctors;
  }

  public void setNumOfAssistantDoctors(Integer numOfAssistantDoctors)
  {
    this.numOfAssistantDoctors = numOfAssistantDoctors;
  }

  public Integer getNumOfStaff()
  {
    return numOfStaff;
  }

  public void setNumOfStaff(Integer numOfStaff)
  {
    this.numOfStaff = numOfStaff;
  }

  public Integer getNumOfTreatPerYear()
  {
    return numOfTreatPerYear;
  }

  public void setNumOfTreatPerYear(Integer numOfTreatPerYear)
  {
    this.numOfTreatPerYear = numOfTreatPerYear;
  }

  public Integer getNumOfOutpatient()
  {
    return numOfOutpatient;
  }

  public void setNumOfOutpatient(Integer numOfOutpatient)
  {
    this.numOfOutpatient = numOfOutpatient;
  }

  public Integer getTotalNumOfSickbed()
  {
    return totalNumOfSickbed;
  }

  public void setTotalNumOfSickbed(Integer totalNumOfSickbed)
  {
    this.totalNumOfSickbed = totalNumOfSickbed;
  }

  public Integer getNumOfAnesthesiaDoctor()
  {
    return numOfAnesthesiaDoctor;
  }

  public void setNumOfAnesthesiaDoctor(Integer numOfAnesthesiaDoctor)
  {
    this.numOfAnesthesiaDoctor = numOfAnesthesiaDoctor;
  }

  public Integer getNumOfPainDoctor()
  {
    return numOfPainDoctor;
  }

  public void setNumOfPainDoctor(Integer numOfPainDoctor)
  {
    this.numOfPainDoctor = numOfPainDoctor;
  }

  public Integer getNumOfSurgeryPerYear()
  {
    return numOfSurgeryPerYear;
  }

  public void setNumOfSurgeryPerYear(Integer numOfSurgeryPerYear)
  {
    this.numOfSurgeryPerYear = numOfSurgeryPerYear;
  }

  public Integer getNumOfSurgeryRoom()
  {
    return numOfSurgeryRoom;
  }

  public void setNumOfSurgeryRoom(Integer numOfSurgeryRoom)
  {
    this.numOfSurgeryRoom = numOfSurgeryRoom;
  }

  public Integer getNumOfUsingOpiatesMedicine()
  {
    return numOfUsingOpiatesMedicine;
  }

  public void setNumOfUsingOpiatesMedicine(Integer numOfUsingOpiatesMedicine)
  {
    this.numOfUsingOpiatesMedicine = numOfUsingOpiatesMedicine;
  }

  public Integer getNumOfUsingOpiatesInjection()
  {
    return numOfUsingOpiatesInjection;
  }

  public void setNumOfUsingOpiatesInjection(Integer numOfUsingOpiatesInjection)
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

  public Integer getRegisteredCapital()
  {
    return registeredCapital;
  }

  public void setRegisteredCapital(Integer registeredCapital)
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

  public Integer getMarketClassification()
  {
    return marketClassification;
  }

  public void setMarketClassification(Integer marketClassification)
  {
    this.marketClassification = marketClassification;
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
    if (!(object instanceof Account))
    {
      return false;
    }
    Account other = (Account) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.bean.Account[ id=" + id + " ]";
  }
  
}
