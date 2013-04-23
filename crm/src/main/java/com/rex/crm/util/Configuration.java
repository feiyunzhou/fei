package com.rex.crm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.velocity.app.VelocityEngine;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimaps;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Province;

public class Configuration {

	private static ImmutableMap<Integer,City> cityTable = null;
	private static ImmutableMap<Integer,Province> provinceTable = null;
	private static VelocityEngine ve;

//	public static List<City> getAllCities(){
//		List<City> cities = new ArrayList<>();
//		try {
//			XMLConfiguration config = new XMLConfiguration("country.xml");
//			List<HierarchicalConfiguration> hp = config
//					.configurationsAt("provinces.province.cities.city");
//			
//			for (HierarchicalConfiguration sub : hp) {
//				City city = new City();
//				cities.add(city);
//				
//				city.setName(sub.getString("name"));
//				city.setId(sub.getString("id"));
//				city.setProvinceId((String)sub.getRoot().getParent().getParent().getChildren("id").get(0).getValue());
//				
//			}
//			
//		}catch (ConfigurationException cex) {
//			cex.printStackTrace();
//		}
//		
//		return cities;
//	}
	
//	public static ImmutableMap<Integer,City> getCityTable(){
//		if(cityTable == null || cityTable.size() == 0){
//			synchronized (Configuration.class){
//			  cityTable = categorizeCitiesByIds();
//			}
//		}
//		return cityTable;
//	}
	
//	public static ImmutableMap<String,Province>  getProvinceTable(){
//		if(provinceTable == null || provinceTable.size() == 0){
//			synchronized (Configuration.class){
//				provinceTable = categorizeProvincesByIds();
//			}
//		}
//		return provinceTable;
//	}
//	
	
//	public static ImmutableMap<Integer,Province> categorizeProvincesByIds(){
//		Builder<Integer, Province> mapBuilder =  ImmutableMap.<Integer,Province>builder();
//		try {
//			XMLConfiguration config = new XMLConfiguration("country.xml");
//			List<HierarchicalConfiguration> hp = config
//					.configurationsAt("provinces.province");
//			
//			for (HierarchicalConfiguration sub : hp) {
//				Province prv = new Province();
//				mapBuilder.put(sub.getString("id"),prv);
//				
//				prv.setName(sub.getString("name"));
//				prv.setId(sub.getString("id"));
//				
//			}
//			
//		}catch (ConfigurationException cex) {
//			cex.printStackTrace();
//		}
//		
//		return mapBuilder.build();
//	}
	
//	public static ImmutableMap<String,City> categorizeCitiesByIds(){
//		Builder<String, City> mapBuilder =  ImmutableMap.<String,City>builder();
//		try {
//			XMLConfiguration config = new XMLConfiguration("country.xml");
//			List<HierarchicalConfiguration> hp = config
//					.configurationsAt("provinces.province.cities.city");
//			
//			for (HierarchicalConfiguration sub : hp) {
//				City city = new City();
//				mapBuilder.put(sub.getString("id"),city);
//				
//				city.setName(sub.getString("name"));
//				city.setId(sub.getString("id"));
//				city.setProvinceId((String)sub.getRoot().getParent().getParent().getChildren("id").get(0).getValue());
//				
//			}
//			
//		}catch (ConfigurationException cex) {
//			cex.printStackTrace();
//		}
//		
//		return mapBuilder.build();
//	}
	
//	public static ImmutableMap<String, Province> getAllProvincesByIds(){
//		
//		Builder<String, Province> mapBuilder =  ImmutableMap.<String,Province>builder();
//		
//		try {
//			XMLConfiguration config = new XMLConfiguration("country.xml");
//			List<HierarchicalConfiguration> hp = config
//					.configurationsAt("provinces.province");
//			
//			for (HierarchicalConfiguration sub : hp) {
//				Province province = new Province();
//				mapBuilder.put(sub.getString("id"), province);
//				
//				province.setName(sub.getString("name"));
//				province.setId(sub.getString("id"));
//				
//			}
//			
//		}catch (ConfigurationException cex) {
//			cex.printStackTrace();
//		}
//		
//		return mapBuilder.build();
//		
//	}
	
//	public static ImmutableListMultimap<String, City> getAllCitiesByProvinceIds() {
//		Function<City, String> idFunction = new Function<City, String>() {
//			public String apply(City city) {
//				return city.getProvinceId();
//			}
//		};
//
//		List<City> cities = getAllCities();
//		ImmutableListMultimap<String, City> citiesByProvinceId = Multimaps
//				.index(cities, idFunction);
//		return citiesByProvinceId;
//	}

//	public static List<Province> getProvinceData() {
//		List<Province> provinces = new ArrayList<>();
//
//		try {
//			XMLConfiguration config = new XMLConfiguration("country.xml");
//			// do something with config
//
//			List<HierarchicalConfiguration> hp = config
//					.configurationsAt("provinces.province");
//			for (HierarchicalConfiguration sub : hp) {
//				Province p = new Province();
//				provinces.add(p);
//
//				p.setName(sub.getString("name"));
//				p.setId(sub.getString("id"));
//				List<HierarchicalConfiguration> cities = sub.configurationsAt("cities.city");
//				
//				if (cities != null) {
//					p.setCities(new City[cities.size()]);
//					int i = 0;
//					for(HierarchicalConfiguration sub2:cities){
//						City c = new City();
//						p.getCities()[i] = c;
//						c.setName(sub2.getString("name"));
//						c.setId(sub2.getString("id"));
//						c.setProvinceId(p.getId());
//						i++;
//					}
//				}
//
//			}
//		} catch (ConfigurationException cex) {
//			cex.printStackTrace();
//		}
//
//		return provinces;
//
//	}
	
    
	public static VelocityEngine getVelocityEngine() {

		if (ve == null) {
			synchronized (Configuration.class) {
				Properties properties = new Properties();
				try {
					properties.load(Configuration.class
							.getResourceAsStream("/velocity.properties"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ve = new VelocityEngine();
				ve.init(properties);
			}
		}

		return ve;

	}

}
