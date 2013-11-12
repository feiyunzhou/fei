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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "record_type")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "RecordType.findAll", query = "SELECT r FROM RecordType r"),
  @NamedQuery(name = "RecordType.findById", query = "SELECT r FROM RecordType r WHERE r.id = :id"),
  @NamedQuery(name = "RecordType.findByVersion", query = "SELECT r FROM RecordType r WHERE r.version = :version"),
  @NamedQuery(name = "RecordType.findByName", query = "SELECT r FROM RecordType r WHERE r.name = :name")
})
public class RecordType implements Serializable
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
  @Column(name = "name")
  private String name;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recordType")
  private List<DataExchangeTeample> dataExchangeTeampleList;

  public RecordType()
  {
  }

  public RecordType(Long id)
  {
    this.id = id;
  }

  public RecordType(Long id, long version, String name)
  {
    this.id = id;
    this.version = version;
    this.name = name;
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

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @XmlTransient
  public List<DataExchangeTeample> getDataExchangeTeampleList()
  {
    return dataExchangeTeampleList;
  }

  public void setDataExchangeTeampleList(List<DataExchangeTeample> dataExchangeTeampleList)
  {
    this.dataExchangeTeampleList = dataExchangeTeampleList;
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
    if (!(object instanceof RecordType))
    {
      return false;
    }
    RecordType other = (RecordType) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.RecordType[ id=" + id + " ]";
  }
  
}
