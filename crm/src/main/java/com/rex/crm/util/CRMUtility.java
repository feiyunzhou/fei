package com.rex.crm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.gson.Gson;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Contact;
import com.rex.crm.common.CRUDPanel;
import com.rex.crm.common.IFormatter;
import com.rex.crm.common.NewDataFormPanel;
import com.rex.crm.common.CRUDPanel.Permissions;
import com.rex.crm.userlog.LogInOut;
import com.rex.crm.userlog.LogObj;

public class CRMUtility {
       private static final Logger logger = Logger.getLogger(CRMUtility.class);
       private static String configPath = null;
       private static Properties commonProps = null;
       public static final String STAT_LOG_IN_OUT = "logInOut";
       public static ThreadPoolExecutor executorPool;
       
       
       public static ThreadPoolExecutor getThreadPoolExecutor() {

           if (executorPool == null) {
               synchronized (Configuration.class) {
                   ThreadFactory threadFactory = Executors.defaultThreadFactory();
                   
                   //creating the ThreadPoolExecutor 
                   executorPool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), threadFactory);
               }
           }
           return executorPool;

       }
    
	   public static ImmutableListMultimap<Integer, Account> categorizeAccountsByCityIds(List<Account> accounts){
	    	Function<Account, Integer> idFunction = new Function<Account, Integer>() {
				public Integer apply(Account account) {
					return account.getCityId();
				}
			};
			
			ImmutableListMultimap<Integer, Account> accountsByCityIds = Multimaps.index(accounts, idFunction);
	       
			return accountsByCityIds;
	    }
	   
	   public static ImmutableListMultimap<Integer, CRMUser> categorizeCRMUsersByCityIds(List<CRMUser> users){
	    	Function<CRMUser, Integer> idFunction = new Function<CRMUser, Integer>() {
				public Integer apply(CRMUser user) {
					return user.getCityId();
				}
			};
			
			ImmutableListMultimap<Integer, CRMUser> usersByCityIds = Multimaps.index(users, idFunction);
	       
			return usersByCityIds;
	    }
	   
	     
	    public static ImmutableListMultimap<Integer,City> categorizeCitiesByProvinceIds(List<City> cities){
	    	Function<City, Integer> idFunction = new Function<City, Integer>() {
				public Integer apply(City city) {
					return city.getProvinceId();
				}
			};

			
			ImmutableListMultimap<Integer, City> citiesByProvinceId = Multimaps
					.index(cities, idFunction);
			return citiesByProvinceId;
	    }
	    
	    
	    public static ImmutableListMultimap<Integer,Contact> categorizeContactsByAccountId(List<Contact> contacts){
	            Function<Contact, Integer> idFunction = new Function<Contact, Integer>() {
	                public Integer apply(Contact contact) {
	                    return contact.getAccountId();
	                }
	            };

	            
	            ImmutableListMultimap<Integer, Contact> contactsByAccountId = Multimaps.index(contacts, idFunction);
	            return contactsByAccountId;
	    }
	    
	    
	  public static ImmutableListMultimap<Integer,Pair<Integer,Integer>> categorizeEntitiesByExternalId(List<Pair<Integer,Integer>> pairs){
               Function<Pair<Integer,Integer>, Integer> idFunction = new Function<Pair<Integer,Integer>, Integer>() {
                   public Integer apply(Pair<Integer,Integer> pair) {
                       return pair.getRight();
                   }
               };

               
               ImmutableListMultimap<Integer, Pair<Integer,Integer>> entitiesByExternalId = Multimaps.index(pairs, idFunction);
               return entitiesByExternalId;
       }
	    
	    
	    
	    public static ImmutableListMultimap<Integer,CalendarEvent> categorizeEventsByUserIds(List<CalendarEvent> events){
	    	Function<CalendarEvent, Integer> idFunction = new Function<CalendarEvent, Integer>() {
				public Integer apply(CalendarEvent event) {
					return event.getCrmUserId();
				}
			};

			ImmutableListMultimap<Integer, CalendarEvent> eventsByUserId = Multimaps.index(events, idFunction);
			return eventsByUserId;
	    }
	    
	    
	    public static List<Account> getAccountsByIds(Map<String,Account> accountTable, final List<String> accountIds){
	    	Predicate<String> filters = new Predicate<String>() {
				  public boolean apply(String id) {
					  return accountIds.contains(id);
				  }
				};
				
			return Lists.newArrayList(Maps.filterKeys(accountTable, filters).values());
	    }
	    
	    
	    public static List<CRMUser> getCRMUsersByIds(Map<String,CRMUser> crmUserTable, final List<String> userIds){
	    	Predicate<String> filters = new Predicate<String>() {
				  public boolean apply(String id) {
					  return userIds.contains(id);
				  }
				};
				
			return Lists.newArrayList(Maps.filterKeys(crmUserTable, filters).values());
	    }
	    
	    
	    public static List filterObjectsByIds(Map idMappingTable, final List<Integer> ids){
	    	Predicate<String> filters = new Predicate<String>() {
				  public boolean apply(String id) {
					  return ids.contains(id);
				  }
				};
				
			return Lists.newArrayList(Maps.filterKeys(idMappingTable, filters).values());
	    }
	    
	    public static Map<Integer, City> filterCitiesWithIds(ImmutableMap<Integer, City> cityTable,final ImmutableMultiset<Integer> cityIds){
			Predicate<Integer> filters = new Predicate<Integer>() {
				  public boolean apply(Integer id) {
					  return cityIds.contains(id);
				  }
				};
			
			return Maps.filterKeys(cityTable, filters);
	    }

	    
	    public static List<CalendarEvent> filterEventsByUserIds(Map<Integer,CalendarEvent> idMappingTable, final List<Integer> userIds){
	    	Predicate<CalendarEvent> filters = new Predicate<CalendarEvent>() {
				  public boolean apply(CalendarEvent event) {
					  
					  return userIds.contains(event.getCrmUserId());
				  }
				};
			return Lists.newArrayList(Maps.filterValues(idMappingTable, filters).values());
	    }
	    
	    public static String formatValue(String formatter, String value) {
	        String res = value;
	        try {
	            if (formatter != null) {
	                Class clazz = Class.forName(formatter);
	                IFormatter formatterImplement = (IFormatter) clazz.newInstance();
	                res = formatterImplement.format(value);
	            }
	        } catch (Exception e) {
	            logger.error("failed to format value",e);
	        }
	        return res;

	    }
	   
	
	    public static EnumSet<Permissions> getPermissionForEntity(int roleId, String entityName) {

	        EnumSet<Permissions> permission = null;
	        if (entityName.equalsIgnoreCase("account")||entityName.equalsIgnoreCase("crmuser")||entityName.equalsIgnoreCase("user_position")) {
	            if (roleId == 1) {
	                permission = EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT);
	            }
	        }else if(entityName.equalsIgnoreCase("contact") || entityName.equalsIgnoreCase("calendar")|| entityName.equalsIgnoreCase("product")){
	        	 if(roleId==1){
	        		 permission = EnumSet.of(CRUDPanel.Permissions.EDIT,CRUDPanel.Permissions.DEL);
	        	 }else{
	        		 permission = EnumSet.of(CRUDPanel.Permissions.EDIT);
	        	 }
	        }else if(entityName.equalsIgnoreCase("activity")){
	        	if (roleId == 3) {
		            permission = EnumSet.of(CRUDPanel.Permissions.EDIT,CRUDPanel.Permissions.DONE,CRUDPanel.Permissions.noExecute);
	        	}else if(roleId == 1){
	        	  permission = EnumSet.of(CRUDPanel.Permissions.DEL);
	        	}
	        }else if(entityName.equalsIgnoreCase("coaching")||entityName.equalsIgnoreCase("willcoaching")){
	          if (roleId == 2){
	            permission = EnumSet.of(CRUDPanel.Permissions.EDIT,CRUDPanel.Permissions.DONE,CRUDPanel.Permissions.noExecute);
	          }else if(roleId == 1){
	            permission = EnumSet.of(CRUDPanel.Permissions.DEL);
	          }
	        }else if(entityName.equalsIgnoreCase("userInfo")){
	            if (roleId == 1) {
	                permission = EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT,CRUDPanel.Permissions.RESETPWD);
	            }
	        }else if(entityName.equalsIgnoreCase("alert")){
	        	if(roleId == 1){
	        		permission = EnumSet.of(CRUDPanel.Permissions.DEL,CRUDPanel.Permissions.EDIT);
	        	}
	        }
	        
	        return permission;
	    }
	    
	    
    public static EnumSet<Permissions> getPermissionOfEntityList(int roleId, String entityName) {

        EnumSet<Permissions> permission = null;
        if (entityName.equalsIgnoreCase("account")||entityName.equalsIgnoreCase("product")) {
            if (roleId == 1) {
                permission = EnumSet.of(CRUDPanel.Permissions.ADD,CRUDPanel.Permissions.DOWNLOAD);
            }
            //||entityName.equalsIgnoreCase("data_exchange_teample")
        }else if(entityName.equalsIgnoreCase("contact") || entityName.equalsIgnoreCase("calendar")){
        	permission = EnumSet.of(CRUDPanel.Permissions.ADD,CRUDPanel.Permissions.DOWNLOAD);
        }else if(entityName.equalsIgnoreCase("activity")){
        	if(roleId == 3){
        		permission = EnumSet.of(CRUDPanel.Permissions.ADD);
        	}else if(roleId == 1){
        		permission = EnumSet.of(CRUDPanel.Permissions.DOWNLOAD);
        	}else{
        		permission = null;
        	}
        }else if(entityName.equalsIgnoreCase("coaching")){
          if(roleId==1){
        	  permission = EnumSet.of(CRUDPanel.Permissions.DOWNLOAD);
          }else if(roleId ==2){
        	  permission = EnumSet.of(CRUDPanel.Permissions.ADD);
          }
        }else if(entityName.equalsIgnoreCase("crmuser")||entityName.equalsIgnoreCase("userInfo")){
            
            if (roleId == 1) {
                permission = EnumSet.of(CRUDPanel.Permissions.ADD,CRUDPanel.Permissions.DOWNLOAD);
            }
        }else if(entityName.equalsIgnoreCase("alert")){
        	if(roleId == 1){
        		permission = EnumSet.of(CRUDPanel.Permissions.ADD);
        	}
        }else if(entityName.equalsIgnoreCase("user_position")){
        	if(roleId == 1){
        		permission = EnumSet.of(CRUDPanel.Permissions.ADD);
        	}
        }
//        else if(entityName.equalsIgnoreCase("upLoad")){
//          
//          if (roleId == 1) {
//              permission = EnumSet.of(CRUDPanel.Permissions.DOWNLOAD);
//          }
//      }
        return permission;
    }
	    public static String MD5Base64(String src) {
	        if (src == null || src.isEmpty())
	            return null;

	        java.security.MessageDigest digest = null;
	        try {
	            digest = java.security.MessageDigest.getInstance("MD5");
	        } catch (Throwable ex) {
	            logger.fatal("Exception", ex);
	        }
	        if (null == digest) {
	            logger.fatal("No MD5 digest");
	            return src;
	        }
	        // Convert
	        digest.update(src.getBytes());
	        final byte[] in = digest.digest();
	        if (in == null || in.length <= 0) {
	            logger.fatal("MD5 returned empty digest");
	            return src;
	        }

	        // encode with Base64
	        final String hash = new String(Base64.encodeBase64(in));

	        return hash;
	    }
	    
	    
	    public static String getToolTipById(String id) {
	        Properties systemPeroperties = new Properties();
	          try {
	                systemPeroperties.load(new InputStreamReader(CRMUtility.class.getResourceAsStream("/tooltipMessage.properties"),"UTF-8"));
	            } catch (FileNotFoundException e1) {
	                logger.error(e1);
	            } catch (IOException e1) {
	                logger.error(e1);
	            }
	          String message="";
	          try {
	              message =  String.valueOf(systemPeroperties.getProperty(id));
	           } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	          }
	        return message;
	    }
	    
	   
	    public static void printStat(String type, LogObj object,Type typeOfSrc){
	        
	        UUID uuid  =  UUID.randomUUID(); 
	        object.setUuid(uuid.toString());
	        Gson gson = new Gson();   
            String js = gson.toJson(object, typeOfSrc);
	        logger.info("STAT."+type +"="+js);
	    }

	    public static String readFileAttribure(String fieldName){
			 Properties systemPeroperties = new Properties();
	     	 try {
				systemPeroperties.load(NewDataFormPanel.class.getResourceAsStream("/crm.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	return systemPeroperties.getProperty(fieldName);
		}
	    
	    

	    public static String getConfigPath() {
	        if (configPath == null) {
	            synchronized (Configuration.class) {
	                String OS = System.getProperty("os.name").toLowerCase();
	                if (OS.indexOf("win") >= 0) {
	                    logger.debug("This is a Windows");
	                    configPath = "C:\\var\\crm\\config";
	                } else {
	                    logger.debug("This is not a Windows");
	                    configPath = "/var/crm/config";
	                }

	            }
	        }

	        return configPath;
	    }
	    
	    
	    
	    public static Properties getCommProps(){
	        if (commonProps == null) {
	            synchronized (Configuration.class) {
	                commonProps = new Properties();
	                try {
	                    String cfgPath = getConfigPath();
	                    File file = new File(cfgPath + File.separator + "common.cfg");
	                    if(file.exists()){
	                        commonProps.load(new FileReader(cfgPath + File.separator + "common.cfg"));
	                    }else{
	                        commonProps.loadFromXML(Configuration.class.getResourceAsStream("/common.cfg"));
	                    }
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	          }
	        }

	        return commonProps;
	    }
	      
	    
	    public static String getJCRHost(){
	         Properties prop= getCommProps();
	         String value = (String) prop.get("jcr_host");
	         return value;
	    }
	    
	     public static String getPicturesPath(){
	         Properties prop= getCommProps();
	         String media_files_path = (String) prop.get("media_files_path");
	         return media_files_path;
	     }
	     
	     public static String getPictureHost(){
	            Properties prop= getCommProps();
	             String value = (String) prop.get("picture_host");
	             return value;
	        }
	     
	     public static String getReportEngineHome(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_engine_home");
	          return value;
	     }
	     
	     public static String getReportLog(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_engine_log");
	          return value;
	     }
	     
	     public static String getReportDesignFolder(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_design_folder");
	          return value;
	     }
	     
	     public static String getReportOutputFolder(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_output_folder");
	          return value;
	     }

	     public static String getReportImagesFolder(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_images_folder");
	          return value;
	     }
	     
	     
	     public static String getReportBaseImgURL(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_base_img_url");
	          return value;
	     }
	     
	     public static String getReportBaseURL(){
	         Properties prop= getCommProps();
	          String value = (String) prop.get("report_base_url");
	          return value;
	     }

	     
	     public static File createTempDirectory()
	             throws IOException
	         {
	             final File temp;

	             temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

	             if(!(temp.delete()))
	             {
	                 throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
	             }

	             if(!(temp.mkdir()))
	             {
	                 throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
	             }

	             return (temp);
	         }
	    
}
