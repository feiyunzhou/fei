package com.rex.crm.ajax;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.rex.crm.SignIn2Session;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Contact;
import com.rex.crm.beans.Province;
import com.rex.crm.beans.Resp;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.dao.UserRole;
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

    public static String getAllCRMUsers(String[] args) {
        List<CRMUser> users = DAOImpl.getAllCRMUsers();
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(users.toArray(new CRMUser[0]), CRMUser[].class);
        return result;
    }

    public static String getAllAccounts(String[] args) {
        List<Account> accounts = DAOImpl.getAllAccounts();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String result = gson.toJson(accounts.toArray(new Account[0]), Account[].class);
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
        return gson.toJson(resp, Resp.class);
    }

    public static String getAccountDetailsById(String[] args) {
        Account account = DAOImpl.getAccountById(Integer.parseInt(args[0]));
        Gson gson = new Gson();
        String result = gson.toJson(account, Account.class);
        // System.out.println(result);
        return result;
    }

    private static Node createCityNode(City city) {
        Node node = new Node();
        if (city != null) {
            node.setTitle(city.getName());
            node.setKey(String.valueOf(city.getId()));
            node.setFolder(true);
        }
        return node;
    }

    private static Node createCityNode(String cityId) {
        Node node = new Node();
        ImmutableMap<Integer, City> cities = DAOImpl.getCityTable();
        City city = cities.get(cityId);
        return createCityNode(city);
    }

    private static Node createAccountNode(String accountId) {
        Account account = DAOImpl.getAccountById(Integer.parseInt(accountId));
        return createAccountNode(account);
    }

    private static Node createAccountNode(Account account) {
        Node node = new Node();
        if (account != null) {
            node.setTitle(account.getName());
            node.setKey(String.valueOf(account.getId()));
        }
        return node;
    }

    private static Node createProvinceNode(int provinceId) {
        Node node = new Node();
        Province prv = DAOImpl.getProvinceById(provinceId);
        if (prv != null) {
            node.setTitle(prv.getName());
            node.setKey(String.valueOf(prv.getId()));
            node.setFolder(true);
        }
        return node;
    }

    private static Node createUserNode(CRMUser crmUser) {
        Node node = new Node();
        if (crmUser != null) {
            node.setTitle(crmUser.getName());
            node.setKey(String.valueOf(crmUser.getId()));
        }
        return node;
    }

    /***
     * create a tree like this province1------>city1----->crmuser1 |
     * |---->crmuser2 | |---->crmuser3 |---->city2
     * 
     * @return
     */
    public static String categorizeCRMUserByProvinceAndCity() {
        // prepare for data
        List<CRMUser> allusers = DAOImpl.getAllCRMUsers();
        ImmutableMap<Integer, City> cityTable = DAOImpl.getCityTable();
        // classify accounts by city Ids, that is to say, find mapping
        // cityId->[account1,account2,account3...]
        ImmutableListMultimap<Integer, CRMUser> usersByCityId = CRMUtility.categorizeCRMUsersByCityIds(allusers);
        ImmutableMultiset<Integer> cityIds = usersByCityId.keys();

        // filter cities using city Ids, we find cities by using the provided
        // city Ids
        Map<Integer, City> filteredCityTable = CRMUtility.filterCitiesWithIds(cityTable, cityIds);

        // categorize the mappings provinceId->cities, that is to say find
        // mapping provinceId->[city1,city2,city3...]
        ImmutableListMultimap<Integer, City> citiesByProvinceId = CRMUtility.categorizeCitiesByProvinceIds(Lists.newArrayList(filteredCityTable.values()));

        ImmutableSet<Integer> provinceIds = citiesByProvinceId.keySet();
        // crate province nodes
        Node[] provinceNodes = new Node[provinceIds.size()];
        int i = 0;
        for (int pid : provinceIds) {
            provinceNodes[i] = createProvinceNode(pid);
            provinceNodes[i].setFolder(true);
            ImmutableList<City> cities = citiesByProvinceId.get(pid);
            provinceNodes[i].setChildren(new Node[cities.size()]);
            for (int j = 0; j < cities.size(); j++) {
                Node cityNode = createCityNode(cities.get(j));
                cityNode.setFolder(true);
                provinceNodes[i].getChildren()[j] = cityNode;
                ImmutableList<CRMUser> users = usersByCityId.get(cities.get(j).getId());
                cityNode.setChildren(new Node[users.size()]);
                for (int k = 0; k < users.size(); k++) {
                    Node userNode = createUserNode(users.get(k));
                    userNode.setType("usr");
                    cityNode.getChildren()[k] = userNode;
                }

            }
            i++;
        }

        Gson gson = new Gson();
        String result = gson.toJson(provinceNodes, Node[].class);
        logger.debug("result of categorizeCRMUserByProvinceAndCity:" + result);
        return result;
    }

    /***
     * create a tree like this province1------>city1----->account1 |
     * |---->account2 | |---->account3 |---->city2
     * 
     * @return
     */
    public static String categorizeAccountsByProvinceAndCity() {
        // prepare for data
        List<Account> allAccounts = DAOImpl.getAllAccounts();
        ImmutableMap<Integer, City> cityTable = DAOImpl.getCityTable();

        // classify accounts by city Ids, that is to say, find mapping
        // cityId->[account1,account2,account3...]
        ImmutableListMultimap<Integer, Account> accountsByCityId = CRMUtility.categorizeAccountsByCityIds(allAccounts);
        ImmutableMultiset<Integer> cityIds = accountsByCityId.keys();

        // filter cities using city Ids, we find cities by using the provided
        // city Ids
        Map<Integer, City> filteredCityTable = CRMUtility.filterCitiesWithIds(cityTable, cityIds);

        // categorize the mappings provinceId->cities, that is to say find
        // mapping provinceId->[city1,city2,city3...]
        ImmutableListMultimap<Integer, City> citiesByProvinceId = CRMUtility.categorizeCitiesByProvinceIds(Lists.newArrayList(filteredCityTable.values()));

        ImmutableSet<Integer> provinceIds = citiesByProvinceId.keySet();
        // crate province nodes
        Node[] provinceNodes = new Node[provinceIds.size()];
        int i = 0;
        for (int pid : provinceIds) {
            provinceNodes[i] = createProvinceNode(pid);
            provinceNodes[i].setFolder(true);
            ImmutableList<City> cities = citiesByProvinceId.get(pid);
            provinceNodes[i].setChildren(new Node[cities.size()]);
            for (int j = 0; j < cities.size(); j++) {
                Node cityNode = createCityNode(cities.get(j));
                cityNode.setFolder(true);
                provinceNodes[i].getChildren()[j] = cityNode;
                ImmutableList<Account> acccounts = accountsByCityId.get(cities.get(j).getId());
                cityNode.setChildren(new Node[acccounts.size()]);
                for (int k = 0; k < acccounts.size(); k++) {
                    Node accountNode = createAccountNode(acccounts.get(k));
                    accountNode.setType("act");
                    cityNode.getChildren()[k] = accountNode;
                }

            }
            i++;
        }

        Gson gson = new Gson();
        String result = gson.toJson(provinceNodes, Node[].class);
        return result;
    }

    // public static String getRegionDataStringify(){
    // List<Province> regionData = DAOImpl.getAllRegionData("");
    // Node[] root = new Node[regionData.size()];
    // int i=0;
    // for(Province p:regionData){
    // root[i] = new Node();
    // root[i].setTitle(p.getName());
    // root[i].setType("prv");
    // root[i].setFolder(true);
    // City[] cities = p.getCities();
    // Node[] cityNodes = new Node[cities.length];
    // root[i].setChildren(cityNodes);
    // int j=0;
    // for(City c: cities){
    // cityNodes[j] = new Node();
    // cityNodes[j].setTitle(c.getName());
    // cityNodes[j].setType("cty");
    // Account[] accounts = c.getAccounts();
    // if(accounts!=null && accounts.length != 0){
    // cityNodes[j].setFolder(true);
    // Node[] acctNodes = new Node[accounts.length];
    // cityNodes[j].setChildren(acctNodes);
    // int k=0;
    // for(Account a:accounts){
    // acctNodes[k] = new Node();
    // acctNodes[k].setTitle(a.getName());
    // acctNodes[k].setType("act");
    // acctNodes[k].setKey(String.valueOf(a.getId()));
    // k++;
    // }
    // }
    // j++;
    // }
    // i++;
    // }
    //
    // Gson gson = new Gson();
    // String result = gson.toJson(root,Node[].class);
    // return result;
    // }

    public static String getCRMUserInfoById(String[] args) {
        CRMUser user = DAOImpl.getCRMUserInfoById(Integer.parseInt(args[0]));
        Gson gson = new Gson();
        String result = gson.toJson(user, CRMUser.class);
        return result;
    }

    public static String getUsersByAccountId(String[] accountId) {
        List<CRMUser> users = DAOImpl.getUsersByAccountId(Integer.parseInt(accountId[0]));
        CRMUser[] userarry = users.toArray(new CRMUser[0]);
        Gson gson = new Gson();
        String jsonString = gson.toJson(userarry, CRMUser[].class);
        return jsonString;
    }

    public static String getAccountsByUserId(String[] userId) {
        List<Account> accounts = DAOImpl.getAccountsByUserId(Integer.parseInt(userId[0]));
        Account[] accountarry = accounts.toArray(new Account[0]);
        Gson gson = new Gson();
        String jsonString = gson.toJson(accountarry, Account[].class);
        return jsonString;
    }

    @SuppressWarnings("all")
    public String getAccountsByUserIdHtmlOutput(String[] userId) {
        List<Account> accounts = DAOImpl.getAccountsByUserId(Integer.parseInt(userId[0]));

        ArrayList list = new ArrayList();
        for (Account acct : accounts) {
            list.add(acct.toMap());
        }

        VelocityContext context = new VelocityContext();
        context.put("accountList", list);
        Template t = Configuration.getVelocityEngine().getTemplate("accountsTableVM.html", "UTF-8");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        Gson gson = new Gson();
        logger.debug(writer.toString());
        String jsonString = gson.toJson(writer.toString(), String.class);
        return jsonString;
    }

    @SuppressWarnings("all")
    public String getUsersByAccountIdHtmlOutput(String[] accountId) {
        List<CRMUser> users = DAOImpl.getUsersByAccountId(Integer.parseInt(accountId[0]));

        ArrayList list = new ArrayList();
        for (CRMUser acct : users) {
            list.add(acct.toMap());
        }

        VelocityContext context = new VelocityContext();
        context.put("list", list);
        Template t = Configuration.getVelocityEngine().getTemplate("crmUserTableVM.html", "UTF-8");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        Gson gson = new Gson();
        String jsonString = gson.toJson(writer.toString(), String.class);
        return jsonString;
    }

    public static String getAccountDetailsByIdHtmlOutput(String[] args) {
        Account account = DAOImpl.getAccountById(Integer.parseInt(args[0]));
        VelocityContext context = new VelocityContext();
        context.put("data", account.toMap());
        Template t = Configuration.getVelocityEngine().getTemplate("accountInfoListVM.html", "UTF-8");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        Gson gson = new Gson();
        String result = gson.toJson(writer.toString(), String.class);
        // System.out.println(result);
        return result;
    }

    public static String getCRMUserInfoByIdHtmlOutput(String[] args) {
        CRMUser user = DAOImpl.getCRMUserInfoById(Integer.parseInt(args[0]));
        System.out.println("AAA:" + user);
        VelocityContext context = new VelocityContext();
        context.put("data", user.toMap());
        Template t = Configuration.getVelocityEngine().getTemplate("userInfoVM.html", "UTF-8");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        Gson gson = new Gson();
        String result = gson.toJson(writer.toString(), String.class);
        return result;
    }

    public static String getEventsByUserId(String[] userId) {
        List<CalendarEvent> events = DAOImpl.getEventsByUserId(Integer.parseInt(userId[0]));
        Gson gson = new Gson();
        String jsonString = gson.toJson(events.toArray(new CalendarEvent[0]), CalendarEvent[].class);
        return jsonString;
    }

    public static String ping(String[] args) {
        return "ok";
    }

    
    public static String getContactIdsOfAccountIdByUserId(String[] args){
        String id= args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), id);
        List<Pair<Integer,Integer>> pairs = Lists.newArrayList();

        for (Map map : (List<Map>) mapList) {
            Pair<Integer,Integer> pair = Pair.of(Integer.parseInt(String.valueOf(map.get("id"))), Integer.parseInt(String.valueOf(map.get("accountId"))));
            pairs.add(pair);
        }

        ImmutableListMultimap<Integer, Pair<Integer, Integer>> entityIdsByExternalId = CRMUtility.categorizeEntitiesByExternalId(pairs);
        
        JsonObject jsTableData = new JsonObject();
   
        Gson gson = new Gson();
       // Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),userId);
        for(Integer key:entityIdsByExternalId.keySet()){
             ImmutableList<Pair<Integer, Integer>> collection = entityIdsByExternalId.get(key);
            JsonArray js_rows = new JsonArray();
            for(Pair<Integer, Integer> p:collection){
                js_rows.add( new JsonPrimitive(p.getLeft()));  
            }
            jsTableData.add(String.valueOf(key), js_rows);
        }
        
        return jsTableData.toString();
        
    }
    
    
    public static String getActivityIdsOfContactIdByUserId(String[] args){
        String id= args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), id);
        List<Pair<Integer,Integer>> pairs = Lists.newArrayList();

        for (Map map : (List<Map>) mapList) {
            Pair<Integer,Integer> pair = Pair.of(Integer.parseInt(String.valueOf(map.get("id"))), Integer.parseInt(String.valueOf(map.get("contactId"))));
            pairs.add(pair);
        }

        ImmutableListMultimap<Integer, Pair<Integer, Integer>> entityIdsByExternalId = CRMUtility.categorizeEntitiesByExternalId(pairs);
        
        JsonObject jsTableData = new JsonObject();
   
        Gson gson = new Gson();
       // Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),userId);
        for(Integer key:entityIdsByExternalId.keySet()){
             ImmutableList<Pair<Integer, Integer>> collection = entityIdsByExternalId.get(key);
            JsonArray js_rows = new JsonArray();
            for(Pair<Integer, Integer> p:collection){
                js_rows.add( new JsonPrimitive(p.getLeft()));  
            }
            jsTableData.add(String.valueOf(key), js_rows);
        }
        return jsTableData.toString();
        
    }
    
    
    
    public static String queryContactsByUserId(String[] args) {
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), id);
        List<Contact> contacts = Lists.newArrayList();

        for (Map map : (List<Map>) mapList) {
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

        for (Integer key : keys) {
            List<Contact> cts = accountId2ContactsMap.get(key);

            if (cts != null && cts.size() != 0) {
                ja.add(String.valueOf(key), gson.toJsonTree(cts));

            }
        }

        return ja.toString();
    }

    public static String getContactData(String[] args) {
        String id = args[0];
        // String sql =
        // "select * from (select a.name as contactName,a.id,b.name as accountName from contact as a,account as b where a.accountId = b.id) as c";

        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        List list = DAOImpl.queryEntityRelationList(entity.getSql(), id);

        List<Contact> contacts = Lists.newArrayList();
        for (Map map : (List<Map>) list) {
            String contactName = String.valueOf(map.get("name"));
            String contactId = String.valueOf(map.get("id"));

            Contact contact = new Contact();
            contact.setId(Integer.parseInt(contactId));
            contact.setName(contactName);
            contacts.add(contact);
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(contacts.toArray(new Contact[0]), Contact[].class);
        return jsonString;
    }

    
    public static String queryAccountsOfUser(String[] args) {
        String uid = args[0];
        String sql = "select * from (select b.id as id,b.name from contact as a,account as b where a.accountId = b.id AND ? is not NULL) as c group by name";

        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        List list = DAOImpl.queryEntityRelationList(sql, uid);

        List<Account> accounts = Lists.newArrayList();
        for (Map map : (List<Map>) list) {
            String name = String.valueOf(map.get("name"));
            String id = String.valueOf(map.get("id"));

            Account acct = new Account();
            acct.setId(Integer.parseInt(id));
            acct.setName(name);
            accounts.add(acct);
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(accounts.toArray(new Account[0]), Account[].class);
        return jsonString;
    }

    public String getAccountTable(String[] args) {
        DataTable dt = new DataTable();
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");

        List<Field> fields = entity.getFields();
        for (Field f : fields) {
            if (!f.isVisible())
                continue;
            ColumnDescription cd1 = new ColumnDescription(f.getDisplay());
            dt.addColumn(cd1);
        }

        
        String userId = args[0];
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        for (Map map : (List<Map>) mapList) {
            TableRow tr = new TableRow();
            for (Field f : fields) {
                if (!f.isVisible())
                    continue;
                String fieldValue = "";
                if (f.getPicklist() != null) {
                    fieldValue = DAOImpl.queryPickListById(f.getPicklist(), String.valueOf(map.get(f.getName())));
                }else if(f.getRelationTable() != null){
                    fieldValue = DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName())));
                } else {
                    fieldValue = String.valueOf(map.get(f.getName()));
                }

                tr.addCell(fieldValue);
            }

            dt.addRow(tr);
        }
        return dt.stringify();

    }

    public String getContactTable(String[] args) {
        String userId = args[0];
        DataTable dt = new DataTable();
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");

        List<Field> fields = entity.getFields();
        for (Field f : fields) {
            if (!f.isVisible())
                continue;
            ColumnDescription cd1 = new ColumnDescription(f.getDisplay());
            dt.addColumn(cd1);
        }
        //dt.addColumn(new ColumnDescription("拜访操作"));

        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        for (Map map : (List<Map>) mapList) {
            TableRow tr = new TableRow();
            for (Field f : fields) {
                if (!f.isVisible())
                    continue;
                String fieldValue = "";
                if (f.getPicklist() != null) {
                    fieldValue = DAOImpl.queryPickListById(f.getPicklist(), String.valueOf(map.get(f.getName())));
                }else if(f.getRelationTable() !=null){
                    fieldValue = DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName())));
                } else {
                    fieldValue = String.valueOf(map.get(f.getName()));
                }

                tr.addCell(fieldValue);
            }

            //String id = String.valueOf(map.get("id"));
            //String accountId = String.valueOf(map.get("accountId"));

            //tr.addCell("<a data-contact-id=\"" + id + "\" data-account-id=\"" + accountId + "\" class=\"btn btn-small btn-primary visit_link\" herf=\"#\">拜访</a>");

            dt.addRow(tr);
        }
        return dt.stringify();
    }

    private String getEntityTable(String entityName, String userId) {
        DataTable dt = new DataTable();
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get(entityName);

        List<Field> fields = entity.getFields();
        for (Field f : fields) {
            if (!f.isVisible())
                continue;
            ColumnDescription cd1 = new ColumnDescription(f.getDisplay());
            dt.addColumn(cd1);
        }

        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        for (Map map : (List<Map>) mapList) {
            TableRow tr = new TableRow();
            for (Field f : fields) {
                if (!f.isVisible())
                    continue;
                String fieldValue = "";
                if (f.getPicklist() != null) {
                    fieldValue = DAOImpl.queryPickListById(f.getPicklist(), String.valueOf(map.get(f.getName())));
                }else if(f.getRelationTable() != null){
                    fieldValue = DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName())));
                } else {
                    fieldValue = String.valueOf(map.get(f.getName()));
                }

                tr.addCell(fieldValue);
            }

            dt.addRow(tr);
        }
        return dt.stringify();
    }

    public static String addCalendarEvent(String[] args) {
        Resp resp = new Resp();
        int crmuserId = Integer.parseInt(args[0]);
        int contactId = Integer.parseInt(args[1]);
        String type = args[2];
        String title = args[3];
        String start = args[4];
        String end = args[5];
        int status = Integer.parseInt(args[6]);
        String owner = args[7];
       String modifier = args[8];
        String responsible_person = args[9];
        resp.setCode(0);
        Date modify_datetime =new Date(System.currentTimeMillis());
        logger.debug("time:"+start+"   :"+end);
        try {
            CRMUser userInfo = DAOImpl.getCRMUserInfoById(crmuserId);
            DAOImpl.addCalendarEvent(crmuserId, contactId, type, title, start, end, status,
                    owner, modifier, responsible_person, null, null, 1,userInfo.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage(e.getMessage());
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(resp, Resp.class);
        return jsonString;

    }
    
    public static String addMeeting(String[] args){
        Gson gson = new Gson();
        String para = args[0];
        logger.debug(para);
        JsonElement jelement = new JsonParser().parse(para);
       
        JsonObject  jobject = jelement.getAsJsonObject();
        int crmuserId = jobject.getAsJsonPrimitive("crmUserId").getAsInt();
        int[] contactIds = gson.fromJson(jobject.getAsJsonArray("contactIds"),int[].class);
        String title = jobject.getAsJsonPrimitive("title").getAsString();
        long start = jobject.getAsJsonPrimitive("startt").getAsLong();
        long end = jobject.getAsJsonPrimitive("endt").getAsLong();
        int status = jobject.getAsJsonPrimitive("status").getAsInt();
        String meeting_type = jobject.getAsJsonPrimitive("activity_type").getAsString();
        JsonPrimitive coachObj = jobject.getAsJsonPrimitive("coachId");
        String coachId = "0";
        if (coachObj !=null){ 
           coachId = jobject.getAsJsonPrimitive("coachId").getAsString();
        }
        
        Resp resp = new Resp();

        resp.setCode(0);
        logger.debug("time:"+start+"   :"+end);
        try {  
            DAOImpl.addExternalMeeting(crmuserId, contactIds, title, start, end, status,meeting_type,coachId);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage(e.getMessage());
        }

        
        String jsonString = gson.toJson(resp, Resp.class);
        return jsonString;
        
        
        
    }

    public static String updateStatusOfCalendarEvent(String[] args){
        Resp resp = new Resp();
        resp.setCode(0);
        int eventId = Integer.parseInt(args[0]);
        int status = Integer.parseInt(args[1]);
        
        try {
            DAOImpl.updateStatusOfCalendarEvent(eventId, status, new Date());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage(e.getMessage());
        }
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(resp, Resp.class);
        return jsonString; 
    }
    
    
    public static String updateStatusOfExternalMeetingRemotely(String[] args){
        Resp resp = new Resp();
        resp.setCode(0);
        int eventId = Integer.parseInt(args[0]);
        int status = Integer.parseInt(args[1]);
        
        try {
            DAOImpl.updateStatusOfExternalMeeting(eventId, status);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage(e.getMessage());
        }
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(resp, Resp.class);
        return jsonString; 
    }
    
    public static String updateStatusOfInternalMeetingRemotely(String[] args){
        Resp resp = new Resp();
        resp.setCode(0);
        int eventId = Integer.parseInt(args[0]);
        int status = Integer.parseInt(args[1]);
        
        try {
            DAOImpl.updateStatusOfInternalMeeting(eventId, status);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resp.setCode(-1);
            resp.setMessage(e.getMessage());
        }
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(resp, Resp.class);
        return jsonString; 
    }
    
    
    public static String getActivitiesTableDataByUserId(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        CRMUser user = DAOImpl.getCrmUserById(Integer.parseInt(id));
        int roleId = user.getRole();
        String sql = null;
        switch(roleId){
         case UserRole.USER_ROLE_ADMINISTRATOR:
            sql = entity.getSqlCalendar();
            break;
         case UserRole.USER_ROLE_MANAGER:
             sql = entity.getSqlManagerCalendar();
            break;
         case UserRole.USER_ROLE_SALES:
             sql = entity.getSqlCalendar();
            break;
        }
        Multimap<Integer, Map> multiMap = null;
        switch(roleId){
          case UserRole.USER_ROLE_ADMINISTRATOR:
          multiMap = getEntityListByIdOfUserId(sql,id);
          break;
        case UserRole.USER_ROLE_MANAGER:
        multiMap = getEntityListByIdOfUserId(sql,id);
            break;
        case UserRole.USER_ROLE_SALES:
        multiMap = getEntityListByIdOfUserId(sql, id);
        }
        return getIndexTable(entity,multiMap,id);
    }
    
    public static String queryRemoteCoachingEventsByUserId(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        String sql = "select * from (select activity.*,activity.activity_type as"+
            "act_type,activity.status as act_status, activity.contactId as"+
            "contactName, contact.accountId as accountName from activity,"+
            "contact where contact.id= activity.contactId AND crmuserId=?) as aActivity";
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(sql,id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    
    public static String getVisitedContactsTableByUserId(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId("select contact.* from activity,contact where crmuserId= ? AND contact.id=activity.contactId group by id",id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    public static String getExternalMeetingTableDataByUserId(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("externalMeeting");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    public static String getAccountIndexTable(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),id);
        return getIndexTable(entity,multiMap,id);
    }
    
    public static String getContactIndexTable(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("contact");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    
    public static String getCRMUserIndexTable(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("crmuser");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    public static String getCoachIndexTable(String[] args){
        String id = args[0];
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("coach");
        Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),id);
        
        return getIndexTable(entity,multiMap,id);
    }
    
    
    public static String getIndexTable(Entity entity, Multimap<Integer, Map> multiMap, String userId){
        Map<String, Entity> entities = Configuration.getEntityTable();
        //Entity entity = entities.get(entityName);
        List<Field> fields = entity.getFields();
        
        JsonObject jo = new JsonObject();
        
        JsonArray js_cols = new JsonArray();
        jo.add("cols", js_cols);
        for(Field f:fields){
            //if(!f.isVisible() && !f.isPrimaryKey()) continue;
            JsonObject js_field = new JsonObject();
            js_field.addProperty("display", f.getDisplay());
            js_field.addProperty("isVisible", f.isVisible());
            js_field.addProperty("priority", f.getPriority());
            js_field.addProperty("name", f.getName());
            js_cols.add(js_field);
        }
      
        JsonObject jsTableData = new JsonObject();
        jo.add("tData", jsTableData);
        Gson gson = new Gson();
       // Multimap<Integer, Map> multiMap = getEntityListByIdOfUserId(entity.getSql(),userId);
        for(Integer key:multiMap.keySet()){
            Collection<Map> collection = multiMap.get(key);
            JsonArray js_rows = new JsonArray();
            for(Map map:collection){
                JsonArray js_row = new JsonArray();
                js_rows.add(js_row);
                for(Field f:fields){
                    //if(!f.isVisible() && !f.isPrimaryKey()) continue;
                    
                    String fieldValue = "";
                    if (f.getPicklist() != null) {
                        fieldValue = DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName())));
                    } else if(f.getRelationTable() != null){
                        fieldValue = DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName())));
                    } else {
                        fieldValue = String.valueOf(map.get(f.getName()));
                    }         
                    JsonPrimitive jp = new JsonPrimitive(fieldValue);
                    js_row.add(jp);
                }
            }
            jsTableData.add(String.valueOf(key), js_rows);
        }
        
        
        return jo.toString();
    }
    
    public static String login(String[] args){
        String loginName = args[0];
        String password = args[1];
         UserInfo user = DAOImpl.login(loginName,password);
        if(user != null){
           Gson gson = new Gson();
           return gson.toJson(user, UserInfo.class);
        }else{
            return "{}";
        }
        
        
    }
    
    private static Multimap<Integer,Map> getEntityListByIdOfUserId(String sql, String... ids){
        Multimap<Integer,Map> multimap =  LinkedHashMultimap.create();
        List maplist = DAOImpl.queryEntityRelationList(sql, ids);
        
        for(Map map:(List<Map>)maplist){
            Integer id = Integer.parseInt(String.valueOf(map.get("id"))); 
            multimap.put(id, map);
        } 
        return multimap;
    }
    
    
    public static String getInferiorsByManagerId(String[] args){
        String managerId = args[0];
        
        List<CRMUser> inferiors = DAOImpl.getInferiorsByManagerId(managerId);
        
        CRMUser[] array = inferiors.toArray(new CRMUser[0]);
        Gson gson = new Gson();
        String jsonString = gson.toJson(array, CRMUser[].class);
        return jsonString;
        
    }
}
