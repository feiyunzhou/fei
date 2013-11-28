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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ralf
 */
@Entity
@Table(name = "call_export")
@XmlRootElement
public class CallExport implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "activity_id")
  private int activityId;

  public int getActivityId()
  {
    return activityId;
  }

  public void setActivityId(int activityId)
  {
    this.activityId = activityId;
  }

  @Column(name = "Activity_title")
  private String activitytitle;
  @Column(name = "Activity_whetherCoach")
  private String activitywhetherCoach;
  @Column(name = "Activity_feature_product")
  private String activityfeatureproduct;
  @Column(name = "Activity_types")
  private String activitytypes;
  @Column(name = "Activity_addTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date   activityaddTime;
  @Column(name = "Activity_modifyTime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date   activitymodifyTime;
  @Column(name = "Activity_start")
  @Temporal(TemporalType.TIMESTAMP)
  private Date   activitystart;
  @Column(name = "Activity_end")
  @Temporal(TemporalType.TIMESTAMP)
  private Date   activityend;
  @Column(name = "Position_name")
  private String positionname;
  @Column(name = "Region_level_1")
  private String regionlevel1;
  @Column(name = "Region_level_2")
  private String regionlevel2;
  @Column(name = "position_code")
  private String positionCode;
  @Column(name = "position_role")
  private String positionRole;
  @Column(name = "Contact_name")
  private String contactname;
  @Column(name = "Contact_grade")
  private String contactgrade;
  @Column(name = "Contact_department")
  private String contactdepartment;
  @Column(name = "Contact_sex")
  private String contactsex;
  @Column(name = "Contact_email")
  private String contactemail;
  @Column(name = "Contact_duty")
  private String contactduty;
  @Column(name = "Contact_job_title")
  private String contactjobtitle;
  @Column(name = "Account_name")
  private String accountname;
  @Column(name = "Account_BDMCode")
  private String accountBDMCode;
  @Column(name = "Account_garde")
  private String accountgarde;
  @Column(name = "Account_type")
  private String accounttype;
  @Column(name = "Account_administrative_level")
  private String accountadministrativelevel;
  @Column(name = "local_or_army")
  private String localOrArmy;
  @Column(name = "comprehensive_or_specialized")
  private String comprehensiveOrSpecialized;
  @Column(name = "isKey")
  private String isKey;
  @Column(name = "Account_status")
  private String accountstatus;

  public CallExport()
  {
  }

  public String getActivitytitle()
  {
    return activitytitle;
  }

  public void setActivitytitle(String activitytitle)
  {
    this.activitytitle = activitytitle;
  }

  public String getActivitywhetherCoach()
  {
    return activitywhetherCoach;
  }

  public void setActivitywhetherCoach(String activitywhetherCoach)
  {
    this.activitywhetherCoach = activitywhetherCoach;
  }

  public String getActivityfeatureproduct()
  {
    return activityfeatureproduct;
  }

  public void setActivityfeatureproduct(String activityfeatureproduct)
  {
    this.activityfeatureproduct = activityfeatureproduct;
  }

  public String getActivitytypes()
  {
    return activitytypes;
  }

  public void setActivitytypes(String activitytypes)
  {
    this.activitytypes = activitytypes;
  }

  public Date getActivityaddTime()
  {
    return activityaddTime;
  }

  public void setActivityaddTime(Date activityaddTime)
  {
    this.activityaddTime = activityaddTime;
  }

  public Date getActivitymodifyTime()
  {
    return activitymodifyTime;
  }

  public void setActivitymodifyTime(Date activitymodifyTime)
  {
    this.activitymodifyTime = activitymodifyTime;
  }

  public Date getActivitystart()
  {
    return activitystart;
  }

  public void setActivitystart(Date activitystart)
  {
    this.activitystart = activitystart;
  }

  public Date getActivityend()
  {
    return activityend;
  }

  public void setActivityend(Date activityend)
  {
    this.activityend = activityend;
  }

  public String getPositionname()
  {
    return positionname;
  }

  public void setPositionname(String positionname)
  {
    this.positionname = positionname;
  }

  public String getRegionlevel1()
  {
    return regionlevel1;
  }

  public void setRegionlevel1(String regionlevel1)
  {
    this.regionlevel1 = regionlevel1;
  }

  public String getRegionlevel2()
  {
    return regionlevel2;
  }

  public void setRegionlevel2(String regionlevel2)
  {
    this.regionlevel2 = regionlevel2;
  }

  public String getPositionCode()
  {
    return positionCode;
  }

  public void setPositionCode(String positionCode)
  {
    this.positionCode = positionCode;
  }

  public String getPositionRole()
  {
    return positionRole;
  }

  public void setPositionRole(String positionRole)
  {
    this.positionRole = positionRole;
  }

  public String getContactname()
  {
    return contactname;
  }

  public void setContactname(String contactname)
  {
    this.contactname = contactname;
  }

  public String getContactgrade()
  {
    return contactgrade;
  }

  public void setContactgrade(String contactgrade)
  {
    this.contactgrade = contactgrade;
  }

  public String getContactdepartment()
  {
    return contactdepartment;
  }

  public void setContactdepartment(String contactdepartment)
  {
    this.contactdepartment = contactdepartment;
  }

  public String getContactsex()
  {
    return contactsex;
  }

  public void setContactsex(String contactsex)
  {
    this.contactsex = contactsex;
  }

  public String getContactemail()
  {
    return contactemail;
  }

  public void setContactemail(String contactemail)
  {
    this.contactemail = contactemail;
  }

  public String getContactduty()
  {
    return contactduty;
  }

  public void setContactduty(String contactduty)
  {
    this.contactduty = contactduty;
  }

  public String getContactjobtitle()
  {
    return contactjobtitle;
  }

  public void setContactjobtitle(String contactjobtitle)
  {
    this.contactjobtitle = contactjobtitle;
  }

  public String getAccountname()
  {
    return accountname;
  }

  public void setAccountname(String accountname)
  {
    this.accountname = accountname;
  }

  public String getAccountBDMCode()
  {
    return accountBDMCode;
  }

  public void setAccountBDMCode(String accountBDMCode)
  {
    this.accountBDMCode = accountBDMCode;
  }

  public String getAccountgarde()
  {
    return accountgarde;
  }

  public void setAccountgarde(String accountgarde)
  {
    this.accountgarde = accountgarde;
  }

  public String getAccounttype()
  {
    return accounttype;
  }

  public void setAccounttype(String accounttype)
  {
    this.accounttype = accounttype;
  }

  public String getAccountadministrativelevel()
  {
    return accountadministrativelevel;
  }

  public void setAccountadministrativelevel(String accountadministrativelevel)
  {
    this.accountadministrativelevel = accountadministrativelevel;
  }

  public String getLocalOrArmy()
  {
    return localOrArmy;
  }

  public void setLocalOrArmy(String localOrArmy)
  {
    this.localOrArmy = localOrArmy;
  }

  public String getComprehensiveOrSpecialized()
  {
    return comprehensiveOrSpecialized;
  }

  public void setComprehensiveOrSpecialized(String comprehensiveOrSpecialized)
  {
    this.comprehensiveOrSpecialized = comprehensiveOrSpecialized;
  }

  public String getIsKey()
  {
    return isKey;
  }

  public void setIsKey(String isKey)
  {
    this.isKey = isKey;
  }

  public String getAccountstatus()
  {
    return accountstatus;
  }

  public void setAccountstatus(String accountstatus)
  {
    this.accountstatus = accountstatus;
  }

}
