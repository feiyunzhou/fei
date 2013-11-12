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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "data_exchange_teample")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "DataExchangeTeample.findAll", query = "SELECT d FROM DataExchangeTeample d"),
  @NamedQuery(name = "DataExchangeTeample.findById", query = "SELECT d FROM DataExchangeTeample d WHERE d.id = :id"),
  @NamedQuery(name = "DataExchangeTeample.findByVersion", query = "SELECT d FROM DataExchangeTeample d WHERE d.version = :version"),
  @NamedQuery(name = "DataExchangeTeample.findByName", query = "SELECT d FROM DataExchangeTeample d WHERE d.name = :name")
})
public class DataExchangeTeample implements Serializable
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
  @Basic(optional = false)
  @Lob
  @Column(name = "template")
  private String template;
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private RecordType typeId;
  @JoinColumn(name = "operation_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private DataExchangeOperation operationId;

  public DataExchangeTeample()
  {
  }

  public DataExchangeTeample(Long id)
  {
    this.id = id;
  }

  public DataExchangeTeample(Long id, long version, String name, String template)
  {
    this.id = id;
    this.version = version;
    this.name = name;
    this.template = template;
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

  public String getTemplate()
  {
    return template;
  }

  public void setTemplate(String template)
  {
    this.template = template;
  }

  public RecordType getTypeId()
  {
    return typeId;
  }

  public void setTypeId(RecordType typeId)
  {
    this.typeId = typeId;
  }

  public DataExchangeOperation getOperationId()
  {
    return operationId;
  }

  public void setOperationId(DataExchangeOperation operationId)
  {
    this.operationId = operationId;
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
    if (!(object instanceof DataExchangeTeample))
    {
      return false;
    }
    DataExchangeTeample other = (DataExchangeTeample) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.DataExchangeTeample[ id=" + id + " ]";
  }
  
}
