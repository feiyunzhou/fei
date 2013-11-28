/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.beans;

import com.rexen.crm.integration.DataObject;

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
 * @author Ralf
 */

@Entity
@Table(name = "crmuser")
@NamedQueries(
  {
    @NamedQuery(name = "Crmuser.findAll", query = "SELECT c FROM Crmuser c")
  })
public class Crmuser extends DataObject implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int    id;
  @Basic(optional = false)
  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;
  @Column(name = "reportto")
  private int    reportto;
  @Column(name = "role")
  private int    role;
  @Column(name = "pl1")
  private int    pl1;
  @Column(name = "pl2")
  private int    pl2;
  @Column(name = "pl4")
  private int    pl4;
  @Column(name = "pl5")
  private int    pl5;
  @Column(name = "city")
  private int    city;
  @Column(name = "department")
  private String department;
  @Column(name = "whenadded")
  @Temporal(TemporalType.TIMESTAMP)
  private Date   whenadded;
  @Column(name = "modifier")
  private String modifier;
  @Column(name = "modify_datetime")
  @Temporal(TemporalType.DATE)
  private Date   modifyDatetime;
  @Column(name = "owner")
  private String owner;
  @Column(name = "level")
  private int    level;

  public Crmuser()
  {
  }

  public Crmuser(int id)
  {
    this.id = id;
  }

  public Crmuser(int id, String name)
  {
    this.id = id;
    this.name = name;
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

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public int getReportto()
  {
    return reportto;
  }

  public void setReportto(int reportto)
  {
    this.reportto = reportto;
  }

  public int getRole()
  {
    return role;
  }

  public void setRole(int role)
  {
    this.role = role;
  }

  public int getPl1()
  {
    return pl1;
  }

  public void setPl1(int pl1)
  {
    this.pl1 = pl1;
  }

  public int getPl2()
  {
    return pl2;
  }

  public void setPl2(int pl2)
  {
    this.pl2 = pl2;
  }

  public int getPl4()
  {
    return pl4;
  }

  public void setPl4(int pl4)
  {
    this.pl4 = pl4;
  }

  public int getPl5()
  {
    return pl5;
  }

  public void setPl5(int pl5)
  {
    this.pl5 = pl5;
  }

  public int getCity()
  {
    return city;
  }

  public void setCity(int city)
  {
    this.city = city;
  }

  public String getDepartment()
  {
    return department;
  }

  public void setDepartment(String department)
  {
    this.department = department;
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

  public String getOwner()
  {
    return owner;
  }

  public void setOwner(String owner)
  {
    this.owner = owner;
  }

  public int getLevel()
  {
    return level;
  }

  public void setLevel(int level)
  {
    this.level = level;
  }
}
