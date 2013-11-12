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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "account_comprehensive_or_specialized_pl")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "AccountComprehensiveOrSpecializedPl.findAll", query = "SELECT a FROM AccountComprehensiveOrSpecializedPl a"),
  @NamedQuery(name = "AccountComprehensiveOrSpecializedPl.findById", query = "SELECT a FROM AccountComprehensiveOrSpecializedPl a WHERE a.id = :id"),
  @NamedQuery(name = "AccountComprehensiveOrSpecializedPl.findByVal", query = "SELECT a FROM AccountComprehensiveOrSpecializedPl a WHERE a.val = :val")
})
public class AccountComprehensiveOrSpecializedPl implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Column(name = "val")
  private String val;

  public AccountComprehensiveOrSpecializedPl()
  {
  }

  public AccountComprehensiveOrSpecializedPl(Integer id)
  {
    this.id = id;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
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
    if (!(object instanceof AccountComprehensiveOrSpecializedPl))
    {
      return false;
    }
    AccountComprehensiveOrSpecializedPl other = (AccountComprehensiveOrSpecializedPl) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.AccountComprehensiveOrSpecializedPl[ id=" + id + " ]";
  }
  
}
