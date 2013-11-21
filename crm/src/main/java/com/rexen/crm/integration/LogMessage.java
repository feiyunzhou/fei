/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Ralf
 */
@Entity
@Table(name = "des_log")
public class LogMessage implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "object_name", length = 256)
  private String objectName;
  @Column(name = "method_name", length = 256)
  private String methodName;
  @Column(name = "stack_trace", length = 4000)
  private String stackTrace;
  @Column(name = "message", length = 4000)
  private String message;
  @Column(name = "created")
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date created;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getObjectName()
  {
    return objectName;
  }

  public void setObjectName(String objectName)
  {
    this.objectName = objectName;
  }

  public String getMethodName()
  {
    return methodName;
  }

  public void setMethodName(String methodName)
  {
    this.methodName = methodName;
  }

  public String getStackTrace()
  {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace)
  {
    this.stackTrace = stackTrace;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public Date getCreated()
  {
    return created;
  }

  public void setCreated(Date created)
  {
    this.created = created;
  }
}
