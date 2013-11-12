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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "crmuser")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
  @NamedQuery(name = "Position.findById", query = "SELECT p FROM Position p WHERE p.id = :id"),
  @NamedQuery(name = "Position.findByName", query = "SELECT p FROM Position p WHERE p.name = :name"),
  @NamedQuery(name = "Position.findByCode", query = "SELECT p FROM Position p WHERE p.code = :code"),
  @NamedQuery(name = "Position.findByReportto", query = "SELECT p FROM Position p WHERE p.reportto = :reportto"),
  @NamedQuery(name = "Position.findByRole", query = "SELECT p FROM Position p WHERE p.role = :role"),
  @NamedQuery(name = "Position.findByPl1", query = "SELECT p FROM Position p WHERE p.pl1 = :pl1"),
  @NamedQuery(name = "Position.findByPl2", query = "SELECT p FROM Position p WHERE p.pl2 = :pl2"),
  @NamedQuery(name = "Position.findByPl4", query = "SELECT p FROM Position p WHERE p.pl4 = :pl4"),
  @NamedQuery(name = "Position.findByPl5", query = "SELECT p FROM Position p WHERE p.pl5 = :pl5"),
  @NamedQuery(name = "Position.findByCity", query = "SELECT p FROM Position p WHERE p.city = :city"),
  @NamedQuery(name = "Position.findByDepartment", query = "SELECT p FROM Position p WHERE p.department = :department"),
  @NamedQuery(name = "Position.findByWhenadded", query = "SELECT p FROM Position p WHERE p.whenadded = :whenadded"),
  @NamedQuery(name = "Position.findByModifier", query = "SELECT p FROM Position p WHERE p.modifier = :modifier"),
  @NamedQuery(name = "Position.findByModifyDatetime", query = "SELECT p FROM Position p WHERE p.modifyDatetime = :modifyDatetime"),
  @NamedQuery(name = "Position.findByOwner", query = "SELECT p FROM Position p WHERE p.owner = :owner"),
  @NamedQuery(name = "Position.findByLevel", query = "SELECT p FROM Position p WHERE p.level = :level")
})
public class Position implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;
  @Column(name = "reportto")
  private Integer reportto;
  @Column(name = "role")
  private Integer role;
  @Column(name = "pl1")
  private Integer pl1;
  @Column(name = "pl2")
  private Integer pl2;
  @Column(name = "pl4")
  private Integer pl4;
  @Column(name = "pl5")
  private Integer pl5;
  @Column(name = "city")
  private String city;
  @Column(name = "department")
  private String department;
  @Column(name = "whenadded")
  @Temporal(TemporalType.TIMESTAMP)
  private Date whenadded;
  @Column(name = "modifier")
  private String modifier;
  @Column(name = "modify_datetime")
  @Temporal(TemporalType.DATE)
  private Date modifyDatetime;
  @Column(name = "owner")
  private String owner;
  @Column(name = "level")
  private Integer level;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
  private List<ContactTeam> contactTeamList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
  private List<AccountTeam> accountTeamList;

  public Position()
  {
  }

  public Position(Integer id)
  {
    this.id = id;
  }

  public Position(Integer id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
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

  public Integer getReportto()
  {
    return reportto;
  }

  public void setReportto(Integer reportto)
  {
    this.reportto = reportto;
  }

  public Integer getRole()
  {
    return role;
  }

  public void setRole(Integer role)
  {
    this.role = role;
  }

  public Integer getPl1()
  {
    return pl1;
  }

  public void setPl1(Integer pl1)
  {
    this.pl1 = pl1;
  }

  public Integer getPl2()
  {
    return pl2;
  }

  public void setPl2(Integer pl2)
  {
    this.pl2 = pl2;
  }

  public Integer getPl4()
  {
    return pl4;
  }

  public void setPl4(Integer pl4)
  {
    this.pl4 = pl4;
  }

  public Integer getPl5()
  {
    return pl5;
  }

  public void setPl5(Integer pl5)
  {
    this.pl5 = pl5;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
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

  public Integer getLevel()
  {
    return level;
  }

  public void setLevel(Integer level)
  {
    this.level = level;
  }

  @XmlTransient
  public List<ContactTeam> getContactTeamList()
  {
    return contactTeamList;
  }

  public void setContactTeamList(List<ContactTeam> contactTeamList)
  {
    this.contactTeamList = contactTeamList;
  }

  @XmlTransient
  public List<AccountTeam> getAccountTeamList()
  {
    return accountTeamList;
  }

  public void setAccountTeamList(List<AccountTeam> accountTeamList)
  {
    this.accountTeamList = accountTeamList;
  }

  @Override
  public int hashCode()
  {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object)
  {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Position))
    {
      return false;
    }
    Position other = (Position) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.Position[ id=" + id + " ]";
  }
  
}
