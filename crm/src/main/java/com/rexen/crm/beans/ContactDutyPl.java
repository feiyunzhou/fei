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
 *
 * @author Ralf
 */
@Entity
@Table(name = "contact_duty_pl")
@NamedQueries(
{
  @NamedQuery(name = "ContactDutyPl.findAll", query = "SELECT c FROM ContactDutyPl c")
})
public class ContactDutyPl implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;
  @Column(name = "val")
  private String val;

  public ContactDutyPl()
  {
  }

  public ContactDutyPl(int id)
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
