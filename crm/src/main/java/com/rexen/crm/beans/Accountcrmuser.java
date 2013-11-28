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
@Table(name = "accountcrmuser")
@NamedQueries(
  {
    @NamedQuery(name = "Accountcrmuser.findAll", query = "SELECT a FROM Accountcrmuser a")
  })
public class Accountcrmuser implements Serializable
{

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int    id;
  @Column(name = "crmuserId")
  private int    crmuserId;
  @Column(name = "accountId")
  private int    accountId;
  @Column(name = "externalId")
  private String externalId;

  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId(String externalId)
  {
    this.externalId = externalId;
  }

  public int getCrmuserId()
  {
    return crmuserId;
  }

  public void setCrmuserId(int crmuserId)
  {
    this.crmuserId = crmuserId;
  }

  public int getAccountId()
  {
    return accountId;
  }

  public void setAccountId(int accountId)
  {
    this.accountId = accountId;
  }

  public Accountcrmuser()
  {
  }

  public Accountcrmuser(int id)
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
