/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.jpa.JpaHelper;

/**
 *
 * @author Kamala
 */
public class DataAccessObject
{

  private JpaEntityManager jpa;
  private boolean transaction = true;
  static Logger logger = Logger.getLogger("dataloader");

  public DataAccessObject(String unitName)
  {
    try
    {
      jpa = JpaHelper.getEntityManager(Persistence.createEntityManagerFactory(unitName).createEntityManager());
      jpa.setFlushMode(FlushModeType.COMMIT);
    }
    catch (Exception e)
    {
      logger.log(Priority.ERROR, "DataAccessObject(String unitName:" + unitName + ")", e);
      throw e;
    }
  }

  public void update(Object[] data, String[] keys) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
  {
    if (data != null && data.length > 0)
    {
      try
      {
        start();

        for (Object o : data)
        {
          Object example = o.getClass().newInstance();

          this.copy(o, example, keys);

          DataObject entity = (DataObject) find(example);

          if (entity != null)
          {
            entity.assign(o);

            jpa.merge(entity);

            data = null;
          }
        }

        commit();
      }
      catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e)
      {
        rollback();
        logger.log(Priority.ERROR, "DataObjectAccess.update(Object o, String[] keys)", e);
        throw e;
      }
    }
  }

  public void delete(Object[] data) throws Exception
  {
    if (data != null && data.length > 0)
    {
      try
      {
        start();

        for (Object o : data)
        {
          jpa.remove(o);
        }

        commit();
      }
      catch (Exception e)
      {
        rollback();

        throw e;
      }
    }
  }

  public void append(Object[] data)
  {
    if (data != null && data.length > 0)
    {
      try
      {
        start();

        for (Object o : data)
        {
          jpa.persist(o);
        }

        commit();
      }
      catch (Exception e)
      {
        rollback();
        logger.log(Priority.ERROR, "DataObjectAccess.append(Object[] data)", e);
        throw e;
      }
    }
  }

  public Object[] find(Object example, int count)
  {
    Query q = jpa.createQueryByExample(example);

    q.setMaxResults(count);

    List l = q.getResultList();

    if (l != null && l.size() > 0)
    {
      return l.toArray();
    }
    else
    {
      return null;
    }
  }

  public Object find(Object example) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
  {
    Object[] data = find(example, 1);

    if (data != null && data.length > 0)
    {
      return data[0];
    }
    else
    {
      return null;
    }
  }

  public void merge(Object[] objects, String[] keys) throws Exception
  {
    if (objects != null && objects.length > 0)
    {

      ArrayList<Object> buffer1 = new ArrayList<>();
      ArrayList<Object> buffer2 = new ArrayList<>();

      for (Object o : objects)
      {
        Object example = o.getClass().newInstance();

        copy(o, example, keys);

        if (find(example) != null)
        {
          buffer2.add(o);
        }
        else
        {
          buffer1.add(o);
        }
      }

      if (buffer1.size() > 0)
      {
        try
        {
          append(buffer1.toArray());
        }
        catch (Exception e)
        {
          logger.log(Priority.ERROR, "DataObjectAccess.merge(Object[] objects, String[] keys)", e);
          throw e;
        }
      }

      if (buffer2.size() > 0)
      {
        try
        {
          update(buffer2.toArray(), keys);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e)
        {
          logger.log(Priority.ERROR, "DataObjectAccess.merge(Object[] objects, String[] keys)", e);
          throw e;
        }
      }
    }
  }

  public void start()
  {
    if (!jpa.getTransaction().isActive())
    {
      jpa.getTransaction().begin();
    }
  }

  public void commit()
  {
    if (jpa.getTransaction().isActive())
    {
      jpa.getTransaction().commit();
    }
  }

  public void rollback()
  {
    if (jpa.getTransaction().isActive())
    {
      jpa.getTransaction().rollback();
    }
  }

  public void execute(String sql) throws Exception
  {
    start();
    try
    {
      Query q = jpa.createNativeQuery(sql);

      q.executeUpdate();
      commit();
    }
    catch (Exception e)
    {
      rollback();

      logger.log(Priority.ERROR, "DataObjectAccess.execute(String sql)", e);

      throw e;
    }
  }

  public void transaction(boolean auto)
  {
    this.transaction = auto;
  }

  private void copy(Object o1, Object o2, String[] properties) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    HashMap<String, Method> o1m = new HashMap<>();

    for (Method m : o1.getClass().getMethods())
    {
      o1m.put(m.getName(), m);
    }

    HashMap<String, Method> o2m = new HashMap<>();

    for (Method m : o2.getClass().getMethods())
    {
      o2m.put(m.getName(), m);
    }

    for (String property : properties)
    {
      String getterName = "get" + property;
      String setterName = "set" + property;

      try
      {
        Method getter = o1m.get(getterName);
        Object value = getter.invoke(o1, new Object[]
        {
        });

        Method setter = o2m.get(setterName);
        setter.invoke(o2, new Object[]
        {
          value
        });
      }
      catch (Exception e)
      {
        System.out.println("getter name: " + getterName);
        System.out.println("setter name: " + setterName);
        e.printStackTrace();
      }
    }
  }
}
