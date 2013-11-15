/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jumpmind.symmetric.csv.CsvReader;

/**
 * <p/>
 *
 * @author Kamala
 */
public class DataImport
{

  private DataAccessObject dao;
  private Configuration config = null;

  public DataImport(String configuration) throws JAXBException
  {
    File file = new File(configuration);
    JAXBContext context = JAXBContext.newInstance(Configuration.class);

    Unmarshaller unmarshaller = context.createUnmarshaller();

    config = (Configuration) unmarshaller.unmarshal(file);

    dao = new DataAccessObject(config.getDatabase());
  }

  public DataImport(Configuration config, DataAccessObject dao) throws JAXBException
  {
    this.config = config;

    this.dao = dao;
  }

  public void load() throws IOException, Exception
  {
    CsvReader reader = new CsvReader(config.getFileName(), ',', Charset.forName("UTF-8"));

    reader.readHeaders();

    ArrayList<Object> buffer = new ArrayList<>();

    while (reader.readRecord())
    {
      try
      {
        Object o = convert(reader);
        buffer.add(o);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

      if (buffer.size() > config.getBatchSize())
      {
        dao.merge(buffer.toArray(), new String[]
        {
          config.getExternalId()
        });
        
        System.out.println("commit " + buffer.size());
        
        buffer.clear();
      }
    }

    if (buffer.size() > 0)
    {
      dao.merge(buffer.toArray(), new String[]
      {
        config.getExternalId()
      });
      System.out.println("commit " + buffer.size());
      buffer.clear();
    }
  }

  public Object convert(CsvReader reader) throws IOException,
                                                 IllegalAccessException, IllegalArgumentException,
                                                 InvocationTargetException, ClassNotFoundException, InstantiationException
  {
    Object record = null;

    String class_name = "com.rexen.crm.beans." + config.getEntityName();
    Class c = Class.forName(class_name);
    record = c.newInstance();

    for (FieldConfiguration field : config.getFields())
    {
      String s = reader.get(field.getColumnName());

      if (s != null && s.length() > 0)
      {
        s = s.trim();
      }

      Method setter = queryMethod(c, "set" + field.getFieldName());
      
      if (setter != null)
      {
        switch (field.getDataType())
        {
          /**
           * *****************************************************************************************************************************************************************************************************
           */
          case "String":
          {
            if (s != null && s.length() > 0)
            {
              setter.invoke(record, new Object[]
              {
                s
              });
            }
            break;
          }
          /**
           * *****************************************************************************************************************************************************************************************************
           */
          case "Integer":
          {
            int i = 0;

            try
            {
              i = Integer.valueOf(s);
            }
            catch (Exception e)
            {
              i = 0;
            }
            setter.invoke(record, new Object[]
            {
              i
            });
            break;
          }
          /**
           * *****************************************************************************************************************************************************************************************************
           */
          case "Date":
          {
            Date date = null;
            if (s != null && s.length() >= field.getFormat().length() - 2)
            {
              try
              {
                DateFormat format = new SimpleDateFormat(field.getFormat());
                date = format.parse(s);
                setter.invoke(record, new Object[]
                {
                  date
                });
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
            }
            break;
          }
          /**
           * *****************************************************************************************************************************************************************************************************
           */
          case "Lookup":
          {
            if (s != null && s.length() > 0)
            {
              try
              {
                Class lookup_entiry_class = Class.forName("com.rexen.crm.beans." + field.getLookupEntityName());
                Object example = lookup_entiry_class.newInstance();

                String method_name = "set" + field.getLookupFieldName();

                Method method = queryMethod(lookup_entiry_class, method_name);

                method.invoke(example, new Object[]
                {
                  s
                });

                Object result = dao.find(example);

                if (result != null)
                {
                  Method getter = queryMethod(lookup_entiry_class, "get" + field.getTargetFieldName());

                  int lookup_value = (Integer) getter.invoke(result, new Object[]
                  {
                  });

                  setter.invoke(record, new Object[]
                  {
                    lookup_value
                  });
                }
                else
                {
                  System.out.println(field.getFieldName() +  " isAutoReference: " + field.isAutoReference());
                  if(field.isAutoReference())
                  {
                    try
                    {
                      System.out.println("isAutoReference");
                      
                      Object lookup_object = lookup_entiry_class.newInstance();

                      Method lookup_setter = queryMethod(c, "set" + field.getLookupFieldName());
                      lookup_setter.invoke(lookup_object, new Object[]{s});

                      dao.append(new Object[]{lookup_object});

                      lookup_object = dao.find(lookup_object);

                      Method getter = queryMethod(c, "get" + field.getTargetFieldName());
                      int lookup_value = (Integer)getter.invoke(lookup_object, new Object[]{});

                      setter.invoke(record, new Object[]{lookup_value});
                    }
                    catch(Exception e)
                    {
                      e.printStackTrace();
                    }
                  }
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
            }
            break;
          }
          /**
           * *****************************************************************************************************************************************************************************************************
           */
        }
      }
      else
      {
        System.out.println("method not found: " + c.getName() + ".set" + field.getFieldName() + "\n");
      }
    }

    return record;
  }

  public static void main(String args[]) throws JAXBException, IOException,
                                                Exception
  {
    System.out.println("template: " + args[0]);
    DataImport loader = new DataImport(args[0]);
    loader.load();
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
