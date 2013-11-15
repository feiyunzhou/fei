/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p/>
 * @author Kamala
 */
@XmlRootElement(name = "Field")
public class FieldConfiguration
{

  private String columnName;
  private String fieldName;
  private String dataType;

  @XmlElement(name = "ColumnName")
  public String getColumnName()
  {
    return columnName;
  }

  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }

  @XmlElement(name = "FieldName")
  public String getFieldName()
  {
    return fieldName;
  }

  public void setFieldName(String fieldName)
  {
    this.fieldName = fieldName;
  }

  @XmlElement(name = "DataType")
  public String getDataType()
  {
    return dataType;
  }

  public void setDataType(String dataType)
  {
    this.dataType = dataType;
  }

  private String format;

  @XmlElement(name = "Format")
  public String getFormat()
  {
    return format;
  }

  public void setFormat(String format)
  {
    this.format = format;
  }

  private String lookupEntityName;

  @XmlElement(name = "LookupEntityName")
  public String getLookupEntityName()
  {
    return lookupEntityName;
  }

  public void setLookupEntityName(String lookupEntityName)
  {
    this.lookupEntityName = lookupEntityName;
  }

  private String lookupFieldName;

  private String targetFieldName;

  @XmlElement(name = "LookupFieldName")
  public String getLookupFieldName()
  {
    return lookupFieldName;
  }

  public void setLookupFieldName(String lookupFieldName)
  {
    this.lookupFieldName = lookupFieldName;
  }

  @XmlElement(name = "TargetFieldName")
  public String getTargetFieldName()
  {
    return targetFieldName;
  }

  public void setTargetFieldName(String targetFieldName)
  {
    this.targetFieldName = targetFieldName;
  }
  
  private boolean autoReference;
  
  @XmlElement(name = "AutoReference")
  public boolean isAutoReference()
  {
    return autoReference;
  }

  public void setAutoReference(boolean autoReference)
  {
    this.autoReference = autoReference;
  }
}
