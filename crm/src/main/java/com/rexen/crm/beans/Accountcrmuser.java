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
  private int id;
  @JoinColumn(name = "crmuserId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Crmuser crmuser;
  @JoinColumn(name = "accountId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Account account;

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

  public Crmuser getCrmuser()
  {
    return crmuser;
  }

  public void setCrmuser(Crmuser crmuser)
  {
    this.crmuser = crmuser;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }
}
