/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Ralf
 */
@Entity
@Table(name = "activity_whethercoach_pl")
@NamedQueries(
  {
    @NamedQuery(name = "ActivityWhethercoachPl.findAll", query = "SELECT a FROM ActivityWhethercoachPl a")
  })
public class ActivityWhethercoachPl implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "id")
  private int    id;
  @Column(name = "val")
  private String val;

  public ActivityWhethercoachPl()
  {
  }

  public ActivityWhethercoachPl(int id)
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
}
