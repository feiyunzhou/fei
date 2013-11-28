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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Ralf
 */
@Entity
@Table(name = "contactcrmuser")
@NamedQueries(
  {
    @NamedQuery(name = "Contactcrmuser.findAll", query = "SELECT c FROM Contactcrmuser c")
  })
public class Contactcrmuser implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;

  @Column(name = "contactId")
  private int contactId;
  @Column(name = "crmuserId")
  private int crmuserId;

  public int getContactId()
  {
    return contactId;
  }

  public void setContactId(int contactId)
  {
    this.contactId = contactId;
  }

  public int getCrmuserId()
  {
    return crmuserId;
  }

  public void setCrmuserId(int crmuserId)
  {
    this.crmuserId = crmuserId;
  }

  public Contactcrmuser()
  {
  }

  public Contactcrmuser(int id)
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
}
