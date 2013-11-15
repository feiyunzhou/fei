/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jumpmind.symmetric.csv.CsvWriter;

import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;

/**
 *
 * @author Ralf
 */
public class DataExport
{

  private DataAccessObject dao;
  private Configuration config = null;
  private Entity entity;
  private java.util.List<Entity> maplist;

  public DataExport(String configuration) throws JAXBException
  {
    File file = new File(configuration);
    JAXBContext context = JAXBContext.newInstance(Configuration.class);
//
    Unmarshaller unmarshaller = context.createUnmarshaller();
//
    config = (Configuration) unmarshaller.unmarshal(file);

    dao = new DataAccessObject(config.getDatabase());
  }
  public DataExport(Entity entity ,java.util.List maplist) throws JAXBException
  {
    this.entity = entity;

    this.maplist = maplist;
  }

  public DataExport(Configuration config, DataAccessObject dao) throws JAXBException
  {
    this.config = config;

    this.dao = dao;
  }

  public byte[] export() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException
  {
    Object example = null;

    String class_name = "com.rexen.crm.beans." + config.getEntityName();
    Class c = Class.forName(class_name);
    example = c.newInstance();

    Object[] data = dao.find(example, config.getMax());
    
    System.out.println("record count " + data.length);
    
    if (data != null && data.length > 0)
    {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

      CsvWriter csv = new CsvWriter(buffer, ',', Charset.forName("UTF-8"));

      ArrayList<String> heads = new ArrayList<>();

      for (FieldConfiguration field : config.getFields())
      {
        heads.add(field.getColumnName());
      }

      csv.writeRecord(heads.toArray(new String[config.getFields().size()]));

      for (Object o : data)
      {
        csv.writeRecord(convert(o));
      }

      csv.flush();

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      
      //FileOutputStream file_output_stream = new FileOutputStream(config.getEntityName() + ".zip");

      ZipOutputStream zip_output_stream = new ZipOutputStream(out);
      zip_output_stream.setLevel(9);
      ZipEntry zip_entry = new ZipEntry(config.getEntityName() + ".csv");

      zip_output_stream.putNextEntry(zip_entry);

      zip_output_stream.write(buffer.toByteArray());

      zip_output_stream.flush();
      zip_output_stream.close();

      //file_output_stream.flush();
      //file_output_stream.close();

      return out.toByteArray();
    }

    return null;
  }
  
  public byte[] exportData() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException
  {
    Object example = null;

    String class_name = entity.getName();
    Class c = Class.forName(class_name);
    example = c.newInstance();

    Object[] data = dao.find(example, config.getMax());
    
    System.out.println("record count " + data.length);
    
    if (data != null && data.length > 0)
    {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

      CsvWriter csv = new CsvWriter(buffer, ',', Charset.forName("UTF-8"));

      ArrayList<String> heads = new ArrayList<>();

      for (Field field : entity.getFields())
      {
        heads.add(field.getName());
      }

      csv.writeRecord(heads.toArray(new String[entity.getFields().size()]));

      for (Object o : data)
      {
        csv.writeRecord(convertData(o,entity));
      }

      csv.flush();

      FileOutputStream file_output_stream = new FileOutputStream(entity.getName() + ".zip");

      ZipOutputStream zip_output_stream = new ZipOutputStream(file_output_stream);
      zip_output_stream.setLevel(9);
      ZipEntry zip_entry = new ZipEntry(entity.getName() + ".csv");

      zip_output_stream.putNextEntry(zip_entry);

      zip_output_stream.write(buffer.toByteArray());

      zip_output_stream.flush();
      zip_output_stream.close();

      file_output_stream.flush();
      file_output_stream.close();

      return buffer.toByteArray();
    }

    return null;
  }
  

  private String[] convert(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    ArrayList<String> buffer = new ArrayList<>();

    for (FieldConfiguration field : config.getFields())
    {
      String method_name = "get" + field.getFieldName();

      Method m = queryMethod(o.getClass(), method_name);

      Object value = m.invoke(o, new Object[]
      {
      });

      if (value != null)
      {
        switch (field.getDataType())
        {
          case "String":
          {
            buffer.add((String) value);
            break;
          }

          case "Integer":
          {
            switch (value.getClass().getName())
            {
              case "java.lang.Integer":
              {
                Integer i = (Integer) value;
                buffer.add(i.toString());
                break;
              }
              case "java.lang.String":
              {
                buffer.add((String) value);
              }
            }
            break;
          }

          case "Date":
          {
            switch (value.getClass().getName())
            {
              case "java.lang.Integer":
              {
                try
                {
                  Integer i = (Integer) value;
                  Date d = new Date();
                  d.setTime(i);

                  DateFormat format = new SimpleDateFormat(field.getFormat());
                  buffer.add(format.format(d));
                }
                catch (Exception e)
                {
                  buffer.add("");
                }
                break;
              }
              case "java.util.Date":
              {
                try
                {
                  Date d = (Date) value;
                  DateFormat format = new SimpleDateFormat(field.getFormat());
                  buffer.add(format.format(d));
                }
                catch (Exception e)
                {
                  buffer.add("");
                }
                break;
              }
              case "java.lang.String":
              {
                buffer.add((String) value);
              }
            }
            break;
          }
          case "Lookup":
          {
            try
            {
              int id = ((Integer) value);

              //System.out.println(field.getTargetFieldName())

              Class c = Class.forName("com.rexen.crm.beans." + field.getLookupEntityName());
              Object example = c.newInstance();
              
              String setter_name = "set" + field.getTargetFieldName();
              Method setter = queryMethod(c, setter_name);

              setter.invoke(example, new Object[]
              {
                id
              });

              example = dao.find(example);

              if (example != null)
              {
                String getter_name = "get" + field.getLookupFieldName();
                
                Method getter = queryMethod(c, getter_name);
                
                String lookup_value = (String) getter.invoke(example, new Object[]
                {
                });

                if (lookup_value != null)
                {
                  buffer.add(lookup_value);
                }
                else
                {
                  buffer.add("");
                }
              }
              else
              {
                buffer.add("");
              }

            }
            catch (Exception e)
            {
              System.out.println("error: " + field.getColumnName() + ", " + field.getTargetFieldName() + ", " + field.getLookupEntityName() + ", " + field.getLookupFieldName());
              e.printStackTrace();
              buffer.add("");
            }
          }
        }
      }
      else
      {
        buffer.add("");
      }
    }

    return buffer.toArray(new String[buffer.size()]);
  }
  
  private String[] convertData(Object o,Entity entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    ArrayList<String> buffer = new ArrayList<>();

    for (Field field : entity.getFields())
    {
      String method_name = "get" + field.getName();

      Method m = queryMethod(o.getClass(), method_name);

      Object value = m.invoke(o, new Object[]
      {
      });

      if (value != null)
      {
            buffer.add((String) value);
      }
      else
      {
        buffer.add("");
      }
    }

    return buffer.toArray(new String[buffer.size()]);
  }

  public static void main(String args[]) throws JAXBException, IOException, Exception
  {
    DataExport exporter = new DataExport(args[0]);
    exporter.export();
  }

  public Method queryMethod(Class c, String methodName)
  {
    
    System.out.println("query method: " + c.getName() + "." + methodName);
    HashMap<String, Method> methods = new HashMap<>();

    for (Method m : c.getMethods())
    {
      methods.put(m.getName().toLowerCase(), m);
    }

    return methods.get(methodName.toLowerCase());
  }
}
