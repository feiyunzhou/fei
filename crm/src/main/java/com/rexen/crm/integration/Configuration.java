/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p/>
 * @author Kamala
 */
@XmlRootElement(name = "Configuration")
public class Configuration
{
  @XmlElement(name = "FileName")
  public String getFileName()
  {
    return fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  @XmlElement(name = "EntityName")
  public String getEntityName()
  {
    return entityName;
  }

  public void setEntityName(String entityName)
  {
    this.entityName = entityName;
  }
  private ArrayList<FieldConfiguration> fields;

  @XmlElement(name = "Fields")
  public ArrayList<FieldConfiguration> getFields()
  {
    return fields;
  }

  public void setFields(ArrayList<FieldConfiguration> fields)
  {
    this.fields = fields;
  }
  private String fileName;
  private String entityName;
  private String externalId;

  @XmlElement(name = "ExternalId")
  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId(String externalIdName)
  {
    this.externalId = externalIdName;
  }

  private int batchSize;

  @XmlElement(name = "BatchSize")
  public int getBatchSize()
  {
    return batchSize;
  }

  public void setBatchSize(int batchSize)
  {
    this.batchSize = batchSize;
  }

  private String database;

  @XmlElement(name = "Database")
  public String getDatabase()
  {
    return database;
  }
  
  public void setDatabase(String database)
  {
    this.database = database;
  }
  
  private int max = 1000000;

  @XmlElement(name = "Max")
  public int getMax()
  {
    return max;
  }

  public void setMax(int max)
  {
    this.max = max;
  }
  
  private String encoded;
  
  @XmlElement(name = "Encoded")
  public String getEncoded()
  {
    return encoded;
  }

  public void setEncoded(String encoded)
  {
    this.encoded = encoded;
  }
}
