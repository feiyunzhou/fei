package com.rex.crm.ajax;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunctionInvoker {
   private  Class cls;
   public FunctionInvoker(Class cls){
	   this.cls = cls;
   }
   
   public Object invoke(String func,String[] params) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
	   Object instance = cls.newInstance();
	   Method method = cls.getDeclaredMethod(func, new Class[]{String[].class});
	   Object result = method.invoke(instance, new Object[]{params});
	   return result;
   }
}
