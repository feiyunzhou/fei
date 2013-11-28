/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Ralf
 */
@Entity
@Table(name = "activity_visiting_purpose_pl")
@NamedQueries(
  {
    @NamedQuery(name = "ActivityVisitingPurposePl.findAll", query = "SELECT a FROM ActivityVisitingPurposePl a")
  })
public class ActivityVisitingPurposePl implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int    id;
  @Column(name = "val")
  private String val;
  @Column(name = "activity_type")
  private int    activityType;
  @Column(name = "parentId")
  private int    parentId;

  public ActivityVisitingPurposePl()
  {
  }

  public ActivityVisitingPurposePl(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getVal()
  {
    return val;
  }

  public void setVal(String val)
  {
    this.val = val;
  }

  public int getActivityType()
  {
    return activityType;
  }

  public void setActivityType(int activityType)
  {
    this.activityType = activityType;
  }

  public int getParentId()
  {
    return parentId;
  }

  public void setParentId(int parentId)
  {
    this.parentId = parentId;
  }
}
