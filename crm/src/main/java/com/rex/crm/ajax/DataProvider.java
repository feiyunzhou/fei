package com.rex.crm.ajax;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Contact;
import com.rex.crm.beans.Province;
import com.rex.crm.beans.Resp;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.html.Node;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

/**
 *
 * @author Feiyun
 */
public class DataProvider {
	private static final Logger logger = Logger.getLogger(DataProvider.class);
	
    public DataProvider() {
    }

    
    public static String getAllCRMUsers(String[] args){
    	List<CRMUser> users = DAOImpl.getAllCRMUsers();
    	Gson gson = new GsonBuilder().create();
    	String result = gson.toJson(users.toArray(new CRMUser[0]),CRMUser[] .class);
    	return result;
    }
    
    public static String getAllAccounts(String[] args){
    	List<Account> accounts = DAOImpl.getAllAccounts();
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	String result = gson.toJson(accounts.toArray(new Account[0]),Account[].class);
    	return result;
    }
    
	public static String addUserOfTeam(String[] args) {
		int accountId = Integer.parseInt(args[0]);
		int userId = Integer.parseInt(args[1]);
		Gson gson = new GsonBuilder().create();
		Resp resp = new Resp();
		resp.setCode(0);
		try {
			DAOImpl.insertRelationOfAccountIDCRMUserID(accountId, userId);
		} catch (Exception e) {
			resp.setCode(1);
			resp.setMessage(e.getMessage());
		}
		return gson.toJson(resp,Resp.class);
	}
    
    public static String getAccountDetailsById(String[] args){
    	Account account = DAOImpl.getAccountById(Integer.parseInt(args[0]));
    	Gson gson = new Gson();
    	String result = gson.toJson(account,Account.class);
    	//System.out.println(result);
    	return result;
    }
    
    
    private static Node createCityNode(City city){
    	Node node = new Node();
    	if(city!=null){
    	  node.setTitle(city.getName());
    	  node.setKey(String.valueOf(city.getId()));
    	  node.setFolder(true);
    	}
    	return node;
    }
    
    private static Node createCityNode(String cityId){
    	Node node = new Node();
    	ImmutableMap<Integer, City> cities = DAOImpl.getCityTable();
    	City city = cities.get(cityId);
    	return createCityNode(city);
    }
    
    private static Node createAccountNode(String accountId){
    	Account account = DAOImpl.getAccountById(Integer.parseInt(accountId));
    	return createAccountNode(account);
    }
    
    private static Node createAccountNode(Account account){
    	Node node = new Node();
    	if(account!=null){
    	    node.setTitle(account.getName());
    	    node.setKey(String.valueOf(account.getId()));
    	}
    	return node;
    }
    
    private static Node createProvinceNode(int provinceId){
    	Node node = new Node();
    	Province prv = DAOImpl.getProvinceById(provinceId);
    	if(prv!=null){
    	  node.setTitle(prv.getName());
    	  node.setKey(String.valueOf(prv.getId()));
    	  node.setFolder(true);
    	}
    	return node;
    }
    
    private static Node createUserNode(CRMUser crmUser) {
    	Node node = new Node();
    	if(crmUser!=null){
    	    node.setTitle(crmUser.getName());
    	    node.setKey(String.valueOf(crmUser.getId()));
    	}
    	return node;
 	}

    
    /***
     * create a tree like this
     * province1------>city1----->crmuser1
     *          |          |---->crmuser2
     *          |          |---->crmuser3
     *          |---->city2
     * @return
     */
    public static String categorizeCRMUserByProvinceAndCity(){
    	//prepare for data
    	List<CRMUser> allusers  = DAOImpl.getAllCRMUsers();
    	ImmutableMap<Integer, City> cityTable = DAOImpl.getCityTable();
    	//classify accounts by city Ids, that is to say, find mapping cityId->[account1,account2,account3...]
    	ImmutableListMultimap<Integer, CRMUser> usersByCityId = CRMUtility.categorizeCRMUsersByCityIds(allusers);
    	ImmutableMultiset<Integer> cityIds = usersByCityId.keys();

    	// filter cities using city Ids, we find cities by using the provided city Ids
    	Map<Integer, City> filteredCityTable = CRMUtility.filterCitiesWithIds(cityTable,cityIds);
    	
    	//categorize the mappings provinceId->cities, that is to say find mapping provinceId->[city1,city2,city3...]
    	ImmutableListMultimap<Integer, City> citiesByProvinceId = CRMUtility.categorizeCitiesByProvinceIds(Lists.newArrayList(filteredCityTable.values()));
    	
    	ImmutableSet<Integer> provinceIds = citiesByProvinceId.keySet();
    	//crate province nodes
    	Node[] provinceNodes = new Node[provinceIds.size()];
    	int i=0;
    	for(int pid:provinceIds){
			provinceNodes[i] = createProvinceNode(pid);
    		provinceNodes[i].setFolder(true);
    		ImmutableList<City> cities = citiesByProvinceId.get(pid);
    		provinceNodes[i].setChildren(new Node[cities.size()]);
    		for(int j=0;j < cities.size();j++){
    			Node cityNode = createCityNode(cities.get(j));
    			cityNode.setFolder(true);
    			provinceNodes[i].getChildren()[j] = cityNode;
    			ImmutableList<CRMUser> users = usersByCityId.get(cities.get(j).getId());
    			cityNode.setChildren(new Node[users.size()]);
    			for(int k=0;k<users.size();k++){
    				Node userNode = createUserNode(users.get(k));
    				userNode.setType("usr");
    				cityNode.getChildren()[k] =  userNode;
    			}
    			
    		}
    		i++;
    	}	
    	
    	Gson gson = new Gson();
    	String result = gson.toJson(provinceNodes,Node[].class);
    	logger.debug("result of categorizeCRMUserByProvinceAndCity:"+result);
    	return result;
    }
    
 
	/***
     * create a tree like this
     * province1------>city1----->account1
     *          |          |---->account2
     *          |          |---->account3
     *          |---->city2
     * @return
     */
    public static String categorizeAccountsByProvinceAndCity(){
    	//prepare for data
    	 List<Account> allAccounts = DAOImpl.getAllAccounts();
    	ImmutableMap<Integer, City> cityTable = DAOImpl.getCityTable();
    	
    	
    	//classify accounts by city Ids, that is to say, find mapping cityId->[account1,account2,account3...]
    	ImmutableListMultimap<Integer, Account> accountsByCityId = CRMUtility.categorizeAccountsByCityIds(allAccounts);
    	ImmutableMultiset<Integer> cityIds = accountsByCityId.keys();
    	
    	
    	// filter cities using city Ids, we find cities by using the provided city Ids
    	Map<Integer, City> filteredCityTable = CRMUtility.filterCitiesWithIds(cityTable,cityIds);
    	
    	//categorize the mappings provinceId->cities, that is to say find mapping provinceId->[city1,city2,city3...]
    	ImmutableListMultimap<Integer, City> citiesByProvinceId = CRMUtility.categorizeCitiesByProvinceIds(Lists.newArrayList(filteredCityTable.values()));
    	
    	ImmutableSet<Integer> provinceIds = citiesByProvinceId.keySet();
    	//crate province nodes
    	Node[] provinceNodes = new Node[provinceIds.size()];
    	int i=0;
    	for(int pid:provinceIds){
			provinceNodes[i] = createProvinceNode(pid);
    		provinceNodes[i].setFolder(true);
    		ImmutableList<City> cities = citiesByProvinceId.get(pid);
    		provinceNodes[i].setChildren(new Node[cities.size()]);
    		for(int j=0;j < cities.size();j++){
    			Node cityNode = createCityNode(cities.get(j));
    			cityNode.setFolder(true);
    			provinceNodes[i].getChildren()[j] = cityNode;
    			ImmutableList<Account> acccounts = accountsByCityId.get(cities.get(j).getId());
    			cityNode.setChildren(new Node[acccounts.size()]);
    			for(int k=0;k<acccounts.size();k++){
    				Node accountNode = createAccountNode(acccounts.get(k));
    				accountNode.setType("act");
    				cityNode.getChildren()[k] =  accountNode;
    			}
    			
    		}
    		i++;
    	}	
    	
    	Gson gson = new Gson();
    	String result = gson.toJson(provinceNodes,Node[].class);
    	return result;
    }
    
//    public static String getRegionDataStringify(){   
//	   List<Province> regionData = DAOImpl.getAllRegionData("");
//	   Node[] root = new Node[regionData.size()];
//	   int i=0;
//	   for(Province p:regionData){
//		   root[i] = new Node();
//		   root[i].setTitle(p.getName());
//		   root[i].setType("prv");
//		   root[i].setFolder(true);
//		   City[] cities = p.getCities();
//		   Node[] cityNodes =  new Node[cities.length];
//		   root[i].setChildren(cityNodes);
//		   int j=0;
//		   for(City c: cities){
//			   cityNodes[j] = new Node();
//			   cityNodes[j].setTitle(c.getName());
//			   cityNodes[j].setType("cty");
//			   Account[] accounts = c.getAccounts();
//			   if(accounts!=null && accounts.length != 0){
//				   cityNodes[j].setFolder(true);
//				   Node[] acctNodes =  new Node[accounts.length];
//				   cityNodes[j].setChildren(acctNodes);
//				   int k=0;
//				   for(Account a:accounts){
//					   acctNodes[k] = new Node();
//					   acctNodes[k].setTitle(a.getName());
//					   acctNodes[k].setType("act");
//					   acctNodes[k].setKey(String.valueOf(a.getId()));
//					   k++;
//				   }
//			   }
//			   j++;
//		   }
//		   i++;
//	   }
//	
//    	Gson gson = new Gson();
//    	String result = gson.toJson(root,Node[].class);
//    	return result;
//    }
    
    public static String getCRMUserInfoById(String[] args){
    	CRMUser user = DAOImpl.getCRMUserInfoById(Integer.parseInt(args[0]));
    	Gson gson = new Gson();
    	String result = gson.toJson(user,CRMUser.class);
    	return result;
    }
    
    public static String getUsersByAccountId(String[] accountId){
    	List<CRMUser> users = DAOImpl.getUsersByAccountId(Integer.parseInt(accountId[0]));
    	CRMUser[] userarry = users.toArray(new CRMUser[0]);
    	Gson gson = new Gson();
    	String jsonString = gson.toJson(userarry,CRMUser[].class);
    	return jsonString;
    }

    public static String getAccountsByUserId(String[] userId){
    	List<Account> accounts = DAOImpl.getAccountsByUserId(Integer.parseInt(userId[0]));
    	Account[] accountarry = accounts.toArray(new Account[0]);
    	Gson gson = new Gson();
    	String jsonString = gson.toJson(accountarry,Account[].class);
    	return jsonString;
    }
    
    @SuppressWarnings("all")
    public  String getAccountsByUserIdHtmlOutput(String[] userId){
    	List<Account> accounts = DAOImpl.getAccountsByUserId(Integer.parseInt(userId[0]));	
    	
		ArrayList list = new ArrayList();
    	for(Account acct:accounts){
    		list.add(acct.toMap());
    	}
    	
    	VelocityContext context = new VelocityContext();
        context.put("accountList", list);  
        Template t = Configuration.getVelocityEngine().getTemplate("accountsTableVM.html","UTF-8");
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        
        Gson gson = new Gson();
        logger.debug(writer.toString());
    	String jsonString = gson.toJson(writer.toString(),String.class);
    	return jsonString;
    }
    
    
    @SuppressWarnings("all")
    public  String getUsersByAccountIdHtmlOutput(String[] accountId){
    	List<CRMUser> users = DAOImpl.getUsersByAccountId(Integer.parseInt(accountId[0]));	
    	
		ArrayList list = new ArrayList();
    	for(CRMUser acct:users){
    		list.add(acct.toMap());
    	}
    	
    	VelocityContext context = new VelocityContext();
        context.put("list", list);  
        Template t = Configuration.getVelocityEngine().getTemplate("crmUserTableVM.html","UTF-8");
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        
        Gson gson = new Gson();
    	String jsonString = gson.toJson(writer.toString(),String.class);
    	return jsonString;
    }
    
    public static String getAccountDetailsByIdHtmlOutput(String[] args){
    	Account account = DAOImpl.getAccountById(Integer.parseInt(args[0]));
    	VelocityContext context = new VelocityContext();
        context.put("data", account.toMap());  
        Template t = Configuration.getVelocityEngine().getTemplate("accountInfoListVM.html","UTF-8");
        StringWriter writer = new StringWriter();
        t.merge( context, writer );

    	Gson gson = new Gson();
    	String result = gson.toJson(writer.toString(),String.class);
    	//System.out.println(result);
    	return result;
    }
    
    public static String getCRMUserInfoByIdHtmlOutput(String[] args){
    	CRMUser user = DAOImpl.getCRMUserInfoById(Integer.parseInt(args[0]));
    	System.out.println("AAA:"+user);
    	VelocityContext context = new VelocityContext();
        context.put("data", user.toMap());  
        Template t = Configuration.getVelocityEngine().getTemplate("userInfoVM.html","UTF-8");
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
    
    	Gson gson = new Gson();
    	String result = gson.toJson(writer.toString(),String.class);
    	return result;
    }
    
    public static String getEventsByUserId(String[] userId){
    	List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(userId[0]));
    	Gson gson = new Gson();
     	String jsonString = gson.toJson(events.toArray(new CalendarEvent[0]),CalendarEvent[].class);
     	return jsonString;
    }

   public static String ping(String[] args){
       return "ok";
   }
   
   public static String queryContactsByUserId(String[] args){
       String id = args[0];
       Map<String, Entity> entities = Configuration.getEntityTable();
       Entity entity = entities.get("contact");
       List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), id);
       List<Contact> contacts = Lists.newArrayList();
       
       for(Map map:(List<Map>)mapList){
           Contact ct = new Contact();
           ct.setId(Integer.parseInt(String.valueOf(map.get("id"))));
           ct.setName(String.valueOf(map.get("name")));
           ct.setAccountId(Integer.parseInt(String.valueOf(map.get("accountId"))));
           contacts.add(ct);
       }
       
       ImmutableListMultimap<Integer, Contact> accountId2ContactsMap = CRMUtility.categorizeContactsByAccountId(contacts);
       
       Gson gson = new Gson();
       
       JsonObject ja = new JsonObject();
       
       ImmutableSet<Integer> keys = accountId2ContactsMap.keySet();
       
       for(Integer key:keys){
           List<Contact> cts = accountId2ContactsMap.get(key);
           
           if(cts!=null && cts.size()!=0){
               ja.add(String.valueOf(key), gson.toJsonTree(cts));
               
           }
       }
       
      
       return ja.toString();
   }
   
   
   public static String getContactData(String[] args){
       String id = args[0];
       //String sql = "select * from (select a.name as contactName,a.id,b.name as accountName from contact as a,account as b where a.accountId = b.id) as c";
       
       Map<String, Entity> entities = Configuration.getEntityTable();
       Entity entity = entities.get("contact");
       List list = DAOImpl.queryEntityRelationList(entity.getSql(), id);
       
       List<Contact> contacts = Lists.newArrayList();
       for(Map map:(List<Map>)list){
           String contactName = String.valueOf(map.get("name"));
           String contactId = String.valueOf(map.get("id"));
           
           Contact contact = new Contact();
           contact.setId(Integer.parseInt(contactId));
           contact.setName(contactName);
           contacts.add(contact);
       }
       
       Gson gson = new Gson();
       String jsonString = gson.toJson(contacts.toArray(new Contact[0]),Contact[].class);
       return jsonString;
   }
   
   
   public static String queryAccountsOfUser(String[] args){
       String uid = args[0];
       //String sql = "select * from (select a.name as contactName,a.id,b.name as accountName from contact as a,account as b where a.accountId = b.id) as c";
       
       Map<String, Entity> entities = Configuration.getEntityTable();
       Entity entity = entities.get("account");
       List list = DAOImpl.queryEntityRelationList(entity.getSql(), uid);
       
       List<Account> accounts = Lists.newArrayList();
       for(Map map:(List<Map>)list){
           String name = String.valueOf(map.get("name"));
           String id = String.valueOf(map.get("id"));
           
           Account acct = new Account();
           acct.setId(Integer.parseInt(id));
           acct.setName(name);
           accounts.add(acct);
       }
       
       Gson gson = new Gson();
       String jsonString = gson.toJson(accounts.toArray(new Account[0]),Account[].class);
       return jsonString;
   }
   
   
   public String getAccountTable(String[] args) {
       return getEntityTable("account",args[0]);
       
   }
   
   public String getContactTable(String[] args){
       return getEntityTable("contact",args[0]);
   }
   
   private String getEntityTable(String entityName,String userId){
       DataTable dt = new DataTable();
       Map<String, Entity> entities = Configuration.getEntityTable();
       Entity entity = entities.get(entityName);
       
       List<Field> fields = entity.getFields();
       for(Field f:fields){
           if(!f.isVisible()) continue;
           ColumnDescription cd1 = new ColumnDescription(f.getDisplay());
           dt.addColumn(cd1);
       }
       
       List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
       for(Map map:(List<Map>)mapList){
           TableRow tr = new TableRow();
           for(Field f:fields){
               if(!f.isVisible()) continue;
               String fieldValue = "";
               if(f.getPicklist() !=null){
                   fieldValue = DAOImpl.queryPickListById(f.getPicklist(), String.valueOf(map.get(f.getName())));
               }else{
                   fieldValue = String.valueOf(map.get(f.getName()));
               }
               
               tr.addCell(fieldValue);
           }
           
           dt.addRow(tr);
       }
       return dt.stringify();
   }
   
   
   public static String setEvent(String[] args){
       Resp resp = new Resp();
       int crmuserId = Integer.parseInt(args[0]);
       String type = args[1];
       String title = args[2];
       String start = args[3];
       String end = args[4];
       resp.setCode(0);
       
       try {
           DAOImpl.setEvent(crmuserId,type,title,start,end);
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
           resp.setCode(-1);
           resp.setMessage(e.getMessage());
       }
       
       
       Gson gson = new Gson();
       String jsonString = gson.toJson(resp,Resp.class);
       return jsonString;
       
   }
   
   
}

