/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rexen.crm.integration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author Kamala
 */
public class DataObject
{

  public void assign(Object o) 
  {
    HashMap<String, Method> target_methods = new HashMap<>();

    for (Method m : this.getClass().getMethods())
    {
      target_methods.put(m.getName(), m);
    }

    HashMap<String, Method> source_methods = new HashMap<>();
    for (Method m : o.getClass().getMethods())
    {
      source_methods.put(m.getName(), m);
    }

    for (String get_method_name : source_methods.keySet())
    {
      String set_method_name = null;
      if (get_method_name.substring(0, 3).equals("get"))
      {
        if (!get_method_name.equals("getId"))
        {
          Method get_method = source_methods.get(get_method_name);
          try
          {
            Object value = get_method.invoke(o, new Object[]
            {
            });

            set_method_name = "set" + get_method_name.substring(3, get_method_name.length());

            Method setter = target_methods.get(set_method_name);
            setter.invoke(this, new Object[]
            {
              value
            });
          }
          catch (Exception e)
          {
          }
        }
      }
    }
  }
}
