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
@Table(name = "contactcrmuser")
@XmlRootElement
@NamedQueries(
{
  @NamedQuery(name = "ContactTeam.findAll", query = "SELECT c FROM ContactTeam c"),
  @NamedQuery(name = "ContactTeam.findById", query = "SELECT c FROM ContactTeam c WHERE c.id = :id")
})
public class ContactTeam implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @JoinColumn(name = "contactId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Contact contact;
  @JoinColumn(name = "crmuserId", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Position position;

  public ContactTeam()
  {
  }

  public ContactTeam(Integer id)
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

  public Contact getContact()
  {
    return contact;
  }

  public void setContact(Contact contact)
  {
    this.contact = contact;
  }

  public Position getPosition()
  {
    return position;
  }

  public void setPosition(Position position)
  {
    this.position = position;
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
    if (!(object instanceof ContactTeam))
    {
      return false;
    }
    ContactTeam other = (ContactTeam) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "com.rexen.crm.beans.ContactTeam[ id=" + id + " ]";
  }
  
}
