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
@Table(name = "activity")
@NamedQueries(
{
  @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")
})
public class Activity implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;
  @Column(name = "name")
  private String name;
  @Basic(optional = false)
  @Column(name = "event_type")
  private int eventType;
  @Column(name = "endtime")
  private int endtime;
  @Basic(optional = false)
  @Column(name = "starttime")
  private long starttime;
  @Column(name = "title")
  private String title;
  @Column(name = "participants")
  private String participants;
  @Column(name = "activity_type")
  private int activityType;
  @Column(name = "coacheeId")
  private int coacheeId;
  @Column(name = "status")
  private int status;
  @Column(name = "visiting_purpose")
  private int visitingPurpose;
  @Column(name = "feature_product")
  private int featureProduct;
  @Column(name = "act_endtime")
  @Temporal(TemporalType.TIMESTAMP)
  private Date actEndtime;
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
  @Column(name = "coach")
  private int coach;
  @Column(name = "location")
  private String location;
  @Column(name = "total_score")
  private int totalScore;
  @Column(name = "planing")
  private int planing;
  @Column(name = "openling")
  private int openling;
  @Column(name = "enquery_listening")
  private int enqueryListening;
  @Column(name = "deliverable")
  private int deliverable;
  @Column(name = "objection_handing")
  private int objectionHanding;
  @Column(name = "summary")
  private int summary;
  @Column(name = "whetherCoach")
  private int whetherCoach;
  @Column(name = "coachTime")
  private int coachTime;
  @Column(name = "accountId")
  private int accountId;
  @Column(name = "department")
  private int department;
  @Column(name = "whether_coach")
  private String whetherCoach1;
  @Column(name = "activity_daypart")
  private int activityDaypart;

  public Activity()
  {
  }

  public Activity(int id)
  {
    this.id = id;
  }

  public Activity(int id, int eventType, long starttime)
  {
    this.id = id;
    this.eventType = eventType;
    this.starttime = starttime;
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

  public int getEventType()
  {
    return eventType;
  }

  public void setEventType(int eventType)
  {
    this.eventType = eventType;
  }

  public int getEndtime()
  {
    return endtime;
  }

  public void setEndtime(int endtime)
  {
    this.endtime = endtime;
  }

  public long getStarttime()
  {
    return starttime;
  }

  public void setStarttime(long starttime)
  {
    this.starttime = starttime;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getParticipants()
  {
    return participants;
  }

  public void setParticipants(String participants)
  {
    this.participants = participants;
  }

  public int getActivityType()
  {
    return activityType;
  }

  public void setActivityType(int activityType)
  {
    this.activityType = activityType;
  }

  public int getCoacheeId()
  {
    return coacheeId;
  }

  public void setCoacheeId(int coacheeId)
  {
    this.coacheeId = coacheeId;
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public int getVisitingPurpose()
  {
    return visitingPurpose;
  }

  public void setVisitingPurpose(int visitingPurpose)
  {
    this.visitingPurpose = visitingPurpose;
  }

  public int getFeatureProduct()
  {
    return featureProduct;
  }

  public void setFeatureProduct(int featureProduct)
  {
    this.featureProduct = featureProduct;
  }

  public Date getActEndtime()
  {
    return actEndtime;
  }

  public void setActEndtime(Date actEndtime)
  {
    this.actEndtime = actEndtime;
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

  public int getCoach()
  {
    return coach;
  }

  public void setCoach(int coach)
  {
    this.coach = coach;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public int getTotalScore()
  {
    return totalScore;
  }

  public void setTotalScore(int totalScore)
  {
    this.totalScore = totalScore;
  }

  public int getPlaning()
  {
    return planing;
  }

  public void setPlaning(int planing)
  {
    this.planing = planing;
  }

  public int getOpenling()
  {
    return openling;
  }

  public void setOpenling(int openling)
  {
    this.openling = openling;
  }

  public int getEnqueryListening()
  {
    return enqueryListening;
  }

  public void setEnqueryListening(int enqueryListening)
  {
    this.enqueryListening = enqueryListening;
  }

  public int getDeliverable()
  {
    return deliverable;
  }

  public void setDeliverable(int deliverable)
  {
    this.deliverable = deliverable;
  }

  public int getObjectionHanding()
  {
    return objectionHanding;
  }

  public void setObjectionHanding(int objectionHanding)
  {
    this.objectionHanding = objectionHanding;
  }

  public int getSummary()
  {
    return summary;
  }

  public void setSummary(int summary)
  {
    this.summary = summary;
  }

  public int getWhetherCoach()
  {
    return whetherCoach;
  }

  public void setWhetherCoach(int whetherCoach)
  {
    this.whetherCoach = whetherCoach;
  }

  public int getCoachTime()
  {
    return coachTime;
  }

  public void setCoachTime(int coachTime)
  {
    this.coachTime = coachTime;
  }

  public int getAccountId()
  {
    return accountId;
  }

  public void setAccountId(int accountId)
  {
    this.accountId = accountId;
  }

  public int getDepartment()
  {
    return department;
  }

  public void setDepartment(int department)
  {
    this.department = department;
  }

  public String getWhetherCoach1()
  {
    return whetherCoach1;
  }

  public void setWhetherCoach1(String whetherCoach1)
  {
    this.whetherCoach1 = whetherCoach1;
  }

  public int getActivityDaypart()
  {
    return activityDaypart;
  }

  public void setActivityDaypart(int activityDaypart)
  {
    this.activityDaypart = activityDaypart;
  }
}
