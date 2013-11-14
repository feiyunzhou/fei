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
 *
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
  @JoinColumn(name = "contactId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Contact contact;
  @JoinColumn(name = "crmuserId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Crmuser crmuser;

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

  public Contact getContact()
  {
    return contact;
  }

  public void setContact(Contact contact)
  {
    this.contact = contact;
  }

  public Crmuser getCrmuser()
  {
    return crmuser;
  }

  public void setCrmuser(Crmuser crmuser)
  {
    this.crmuser = crmuser;
  }
}
