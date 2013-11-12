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
@Table(name = "data_exchange_status")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "DataExchangeStatus.findAll", query = "SELECT d FROM DataExchangeStatus d"),
  @NamedQuery(name = "DataExchangeStatus.findById", query = "SELECT d FROM DataExchangeStatus d WHERE d.id = :id"),
  @NamedQuery(name = "DataExchangeStatus.findByVersion", query = "SELECT d FROM DataExchangeStatus d WHERE d.version = :version"),
  @NamedQuery(name = "DataExchangeStatus.findByVal", query = "SELECT d FROM DataExchangeStatus d WHERE d.val = :val")
})
public class DataExchangeStatus implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Basic(optional = false)
  @Column(name = "version")
  private long version;
  @Basic(optional = false)
  @Column(name = "val")
  private String val;

  public DataExchangeStatus()
  {
  }

  public DataExchangeStatus(Long id)
  {
    this.id = id;
  }

  public DataExchangeStatus(Long id, long version, String val)
  {
    this.id = id;
    this.version = version;
    this.val = val;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public long getVersion()
  {
    return version;
  }

  public void setVersion(long version)
  {
    this.version = version;
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
    if (!(object instanceof DataExchangeStatus))
    {
      return false;
    }
    DataExchangeStatus other = (DataExchangeStatus) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.DataExchangeStatus[ id=" + id + " ]";
  }
  
}
