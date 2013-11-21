/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.beans;

import com.rexen.crm.integration.DataObject;
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
 *
 * @author Ralf
 */
@Entity
@Table(name = "user_position")
@NamedQueries(
{
  @NamedQuery(name = "UserPosition.findAll", query = "SELECT u FROM UserPosition u")
})
public class UserPosition extends DataObject implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private int id;
  @Column(name = "userId")
  private int userId;
  @Column(name = "positionId")
  private int positionId;
  @Column(name = "status")
  private int status;
  @Column(name = "isPrimary")
  private int isPrimary;
  @Column(name = "external_id")
  private String externalId;

  public UserPosition()
  {
  }

  public UserPosition(int id)
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

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public int getPositionId()
  {
    return positionId;
  }

  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public int getIsPrimary()
  {
    return isPrimary;
  }

  public void setIsPrimary(int isPrimary)
  {
    this.isPrimary = isPrimary;
  }

  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId(String externalId)
  {
    this.externalId = externalId;
  }
}
