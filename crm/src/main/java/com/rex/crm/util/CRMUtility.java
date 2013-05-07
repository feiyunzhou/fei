package com.rex.crm.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.City;
import com.rex.crm.common.IFormatter;

public class CRMUtility {

    private static final Logger logger = Logger.getLogger(CRMUtility.class);
    
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
	    
}
