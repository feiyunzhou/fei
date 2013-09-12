package com.rex.crm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.Choice;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Contact;
import com.rex.crm.beans.Province;
import com.rex.crm.common.Entity;
import com.rex.crm.util.Configuration;

public class DAOImpl {
    private static final Logger logger = Logger.getLogger(DAOImpl.class);
    public static ListMultimap<Integer, Integer> accountIdsByUserId;
    
    private static Cache<String, String> pickListCache = CacheBuilder.newBuilder()
            .maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
            .build();
    
    private static Cache<String, String> relationDataCache = CacheBuilder.newBuilder()
            .maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
            .build();


    public static ImmutableMap<Integer, City> getCityTable() {
        com.google.common.collect.ImmutableMap.Builder<Integer, City> mapBuilder = ImmutableMap.<Integer, City> builder();

        ResultSetHandler<List<City>> h = new BeanListHandler<City>(City.class);
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            List<City> result = run.query(conn, "SELECT * FROM city", h);
            for (City c : result) {
                mapBuilder.put(c.getId(), c);
            }

        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return mapBuilder.build();
    }

    public static ImmutableMap<Integer, Province> getProvinceTable() {
        com.google.common.collect.ImmutableMap.Builder<Integer, Province> mapBuilder = ImmutableMap.<Integer, Province> builder();

        Connection conn = null;
        try {
            ResultSetHandler<List<Province>> h = new BeanListHandler<Province>(Province.class);
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            List<Province> result = run.query(conn, "SELECT * FROM province", h);
            for (Province p : result) {
                mapBuilder.put(p.getId(), p);
            }
        } catch (Exception e) {
            logger.error("faield to get provinces data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return mapBuilder.build();
    }

    public static long getSizeOfAccountByUserId(int userId) {
        long size = 0;
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, "select count(*) as ct from account as a inner join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?", new MapHandler(), userId);
            size = (long) map.get("ct");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
    }

    public static long getSizeOfUsersByAccountId(int accountId) {
        long size = 0;
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, "select count(*) as ct from crmuser as a inner join accountcrmuser as b on a.id=b.crmuserId where b.accountId=?", new MapHandler(), accountId);
            size = (long) map.get("ct");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
    }

    public static List<Account> getAccountsByIds(List<Integer> accountIds) {
        List<Account> accounts = Lists.newArrayList();
        ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);

        List params = new ArrayList<>();
        for (int id : accountIds) {
            params.add(id);
        }
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();

            conn = DBHelper.getConnection();
            accounts = run.query(conn, "select * from account where id=?", params.toArray(new Object[0]), h);

        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return accounts;
    }

    public static Province getProvinceById(int provinceId) {
        Connection conn = null;
        Province prov = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Province> h = new BeanHandler<Province>(Province.class);

            prov = run.query(conn, "SELECT * FROM province where id=?", h, provinceId);

        } catch (SQLException e) {
            logger.error("failed to get province", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return prov;
    }

    public static List<Account> getAccountsByUserId(int userId) {
        List<Account> accounts = Lists.newArrayList();
        ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            accounts = run.query(conn, "select a.* from account as a inner join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?", h, userId);
        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return accounts;
    }

    public static List<CRMUser> getUsersByAccountId(int accountId) {
        List<CRMUser> users = Lists.newArrayList();
        ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            users = run.query(conn, "select a.* from crmuser as a inner join accountcrmuser as b on a.id=b.crmuserId where b.accountId=?", h, accountId);
        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return users;
    }

    public static List<Account> getAllAccounts() {
        List<Account> accounts = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);

            accounts = run.query(conn, "SELECT * FROM account", h);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("failed to close connection", e);
            }
        }

        return accounts;
    }

    public static List<Contact> getAllContacts() {
        List<Contact> contacts = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<Contact>> h = new BeanListHandler<Contact>(Contact.class);

            contacts = run.query(conn, "SELECT * FROM contact", h);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("failed to close connection", e);
            }
        }

        return contacts;
    }

    public static List<Pair<String, Map<String, Object>>> getNumberOfTypeOfAccount(String user_id) {
        String query = "select b.id as id,b.val as val,sum(a.id) as sum from (SELECT account.* FROM account,accountcrmuser WHERE account.id=accountcrmuser.accountId AND accountcrmuser.crmuserId=?) as a, account_pl6 as b where a.pl6=b.id group by b.id order by sum DESC";
        List<Pair<String, Map<String, Object>>> res = Lists.newArrayList();
        Connection conn = null;
        List<Map<String, Object>> lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = run.query(conn, query, new MapListHandler(), user_id);
            if (lMap != null) {
                for (Map<String, Object> map : lMap) {
                    Pair<String, Map<String, Object>> value = Pair.of(String.valueOf(map.get("id")), map);
                    res.add(value);
                }
            }

        } catch (SQLException e) {
            logger.error("failed to get list from picklist", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return res;
    }

    public static Account getAccountById(int id) {
        Connection conn = null;
        Account account = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Account> h = new BeanHandler<Account>(Account.class);

            account = run.query(conn, "SELECT * FROM account where id=?", h, id);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return account;
    }

    public static CRMUser[] getAllCRMUsersArray() {
        return getAllCRMUsers().toArray(new CRMUser[0]);
    }

    public static List<CRMUser> getAllCRMUsers() {
        List<CRMUser> users = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);

            users = run.query(conn, "SELECT * FROM crmuser", h);

        } catch (SQLException e) {
            logger.error("failed to get all crm users", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return users;
    }

    public static CRMUser getCRMUserInfoById(int id) {
        Connection conn = null;
        CRMUser user = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);

            user = run.query(conn, "SELECT * FROM crmuser where id=?", h, id);

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return user;
    }

    public static void insertRelationOfAccountIDCRMUserID(int accountId, int userId) throws Exception {
        // Account account = accountIndexTable.get(accountId);
        CRMUser user = DAOImpl.getCRMUserInfoById(userId);
        Account account = DAOImpl.getAccountById(accountId);
        if (account != null && account.getId() != 0 && user != null && user.getId() != 0) {
            Connection conn = null;
            try {
                conn = DBHelper.getConnection();
                QueryRunner run = new QueryRunner();
                int inserts = run.update(conn, "INSERT INTO accountcrmuser (accountId,crmuserId) VALUES (?,?)", account.getId(), user.getId());

                logger.info(String.format("%s row inserted into insertRelationOfAccountIDCRMUserID!", inserts));

            } catch (SQLException e) {
                logger.error("failed to insertRelationOfAccountIDCRMUserID", e);
            } finally {
                DBHelper.closeConnection(conn);
            }

        }

    }
    
    
    public static void insertRelationOfContactIDCRMUserID(String cId, String userId) throws Exception {
        int contactId = Integer.parseInt(cId);
        int uid = Integer.parseInt(userId);
        if (contactId != 0 && uid != 0) {
            Connection conn = null;
            try {
                conn = DBHelper.getConnection();
                QueryRunner run = new QueryRunner();
                int inserts = run.update(conn, "INSERT INTO contactcrmuser (contactId,crmuserId) VALUES (?,?)", cId, userId);

                logger.info(String.format("%s row inserted into insertRelationOfAccountIDCRMUserID!", inserts));

            } catch (SQLException e) {
                logger.error("failed to insertRelationOfContactIDCRMUserID", e);
            } finally {
                DBHelper.closeConnection(conn);
            }

        }

    }
    

    public static List<CalendarEvent> getEventsByUserId(int userId) {
        List<CalendarEvent> events = Lists.newArrayList();
        Connection conn = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CalendarEvent>> h = new BeanListHandler<CalendarEvent>(CalendarEvent.class);

            events = run.query(conn, "SELECT * FROM activity where crmuserId=?", h, userId);
            for (CalendarEvent e : events) {
                e.setStart(sd.format(new Date(e.getStarttime())));
                e.setEnd(sd.format(new Date(e.getEndtime())));
            }
        } catch (SQLException e) {
            logger.error("failed to getEventsByUserId:" + userId, e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return events;
    }
    
    
    public static List<CalendarEvent> getEventsByEventId(int id) {
        List<CalendarEvent> events = Lists.newArrayList();
        Connection conn = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CalendarEvent>> h = new BeanListHandler<CalendarEvent>(CalendarEvent.class);

            events = run.query(conn, "SELECT * FROM activity where id=?", h, id);
            for (CalendarEvent e : events) {
                e.setStart(sd.format(new Date(e.getStarttime())));
                e.setEnd(sd.format(new Date(e.getEndtime())));
            }
        } catch (SQLException e) {
            logger.error("failed to getEventsByEventId:" + id, e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return events;
    }
    

    public static List<String> getMenuByUserId(String id) {
        List<String> menulist = Lists.newArrayList();
        menulist.add("home");
        menulist.add("calendar");
        menulist.add("account");
        menulist.add("contact");
        menulist.add("activity");
//        menulist.add("dealerAccount");
//        menulist.add("dealerContact");
        menulist.add("user");
        return menulist;
    }

    public static List getTableData(String tableName, List<String> colunmNames, int first, int count) {
        String queryColunm = Joiner.on(",").join(colunmNames);
        String query = "select " + queryColunm + " from " + tableName + " limit " + first + ", " + count;

        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, query, new MapListHandler());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;

    }

    public static List queryEntityRelationList(String sql, String... params) {
        logger.debug(sql);
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, sql, new MapListHandler(), params);

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;

    }

    public static List queryEntityWithFilter(String sql, String filterField, List<String> filters, String... params) {

        List<String> joinedFilter = Lists.newArrayList();
        if (filters.size() > 0) {
            for (String f : filters) {
                joinedFilter.add(filterField + " = " + f);
            }

            sql = sql + " where (" + Joiner.on(" OR ").join(joinedFilter) + ")";
        } else {
            sql = sql + " where " + filterField + " = -1";
        }

        logger.debug(sql);
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, sql, new MapListHandler(), params);

        } catch (SQLException e) {
            logger.error("failed to get entity", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;

    }

    public static List queryEntityList(String sql, int first, int count) {
        String query = sql + " limit " + first + ", " + count;
        logger.debug(query);
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, query, new MapListHandler());
            List<Map> maplist = (List<Map>) lMap;
            logger.debug(maplist.get(0).keySet());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;

    }

    public static Map queryEntityById(String sql, String id) {
        String query = sql.replace("?", id);

        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map = (Map) run.query(conn, query, new MapHandler());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return map;

    }

    public static Map getEntityData(String tableName, List<String> colunmNames, long id) {
        String queryColunm = Joiner.on(",").join(colunmNames);
        String query = "select * from " + tableName + " where id= " + id;
        logger.debug("query entity:" + query);
        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map = (Map) run.query(conn, query, new MapHandler());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return map;

    }

    public static String queryCachedRelationDataById(final String tableName, final String id){
        String value = "";
        try{
            value =  relationDataCache.get(tableName + "_" + id, new Callable<String>() {
                @Override
                public String call() throws Exception {                   
                    return queryRelationDataById(tableName,id);
                }
          });
        }catch(Exception e){
            logger.error("Failed to get data from cache",e);
        }
        
        //logger.debug("hitRate:"+pickListCache.stats().hitRate() + " size:"+ pickListCache.size());
        return value;
    }
    
    
    public static String queryRelationDataById(final String tableName, final String id){
        String query = "select id, name from " + tableName + " where id=? ";
        // logger.debug(query);
        String result = "";
        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map = (Map) run.query(conn, query, new MapHandler(), id);
            if (map != null) {
                Object value = map.get("name");
                if (value != null) {
                    result = (String) value;
                }
            }

        } catch (SQLException e) {
            logger.error("failed to get queryPickListById", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return result;
    }
    
    public static String queryPickListByIdCached(final String picklist, final String id){
        String value = "";
        try{
            value =  pickListCache.get(picklist + "_" + id, new Callable<String>() {
                @Override
                public String call() throws Exception {                   
                    return queryPickListById(picklist,id);
                }
          });
        }catch(Exception e){
            logger.error("Failed to get data from cache",e);
        }
        
        //logger.debug("hitRate:"+pickListCache.stats().hitRate() + " size:"+ pickListCache.size());
        return value;
    }
    
    public static String queryPickListById(String picklist, String id) {
        String query = "select id, val from " + picklist + " where id=? ";
        // logger.debug(query);
        String result = "";
        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map = (Map) run.query(conn, query, new MapHandler(), id);
            if (map != null) {
                Object value = map.get("val");
                if (value != null) {
                    result = (String) value;
                }
            }

        } catch (SQLException e) {
            logger.error("failed to get queryPickListById", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return result;

    }

    public static List<Choice> queryPickList(String picklist) {
        String query = "select id, val from " + picklist;
        List<Choice> choices = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<Choice>> h = new BeanListHandler<Choice>(Choice.class);
            choices = run.query(conn, "SELECT * FROM " + picklist, h);

        } catch (SQLException e) {
            logger.error("failed to get queryPickListById", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return choices;

    }
    
    public static List<Choice> queryRelationDataList(String tablename,String userId) {
        Entity entity = Configuration.getEntityByName(tablename);
        List<Choice> choices = Lists.newArrayList();
        List list = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
         
         for(Map map:(List<Map>)list){
             Choice c = new Choice();
             c.setId(((Number)map.get("id")).longValue());
             c.setVal((String)map.get("name"));
             choices.add(c);
         }
         
        return choices;

    }
    

    public static List<Pair<String, Map<String, Object>>> queryFilters(String sourceTableSQL, String filterField, String filterbyTable, String user_id) {
        List<Choice> choices = queryPickList(filterbyTable);
        List<Pair<String, Map<String, Object>>> res = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            for (Choice ch : choices) {
                String query = "select sum(a.id) as sum from (" + sourceTableSQL + " where " + filterField + " = " + ch.getId() + ") as a";
                logger.debug("query is:" + query);

                QueryRunner run = new QueryRunner();
                Map<String, Object> map = run.query(conn, query, new MapHandler(), user_id);
                if (map.get("sum") == null) {
                    map.put("sum", new java.math.BigDecimal(0));
                }
                map.put("val", ch.getVal());
                res.add(Pair.of(String.valueOf(ch.getId()), map));

            }

        } catch (Exception e) {
            logger.error("failed to queryFilters", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return res;
    }

    public static void addCalendarEvent(int crmuserId, int contactId, String type, String title, String start, String end, int status) throws Exception {
        int type_id = Integer.parseInt(type);
        String sql = "INSERT INTO activity (crmuserId,contactId,endtime,starttime,title,activity_type,status,whenadded) VALUES (?,?,?,?,?,?,?,now())";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            CalendarEvent e = new CalendarEvent();
            e.setTitle("拜访");
            e.setStarttime(Long.parseLong(start) * 1000L);
            e.setEndtime(Long.parseLong(end) * 1000L);
            e.setCrmUserId(crmuserId);
            e.setActivity_type(type_id);
            e.setContactId(contactId);
            e.setStatus(status);
            inserts += run.update(conn, sql, e.getCrmUserId(), e.getContactId(), e.getEndtime(), e.getStarttime(), e.getTitle(), e.getActivity_type(), e.getStatus());

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    
    
    public static void addExternalMeeting(int crmuserId, int[] contactIds, String title, long start, long end, int status,String meeting_type,String coachId) throws Exception {
        //int type_id = Integer.parseInt(type);
        
        String sql = "INSERT INTO externalMeeting (crmuserId,contactIds,endtime,starttime,title,status,activity_type,coachId) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            CalendarEvent e = new CalendarEvent();
            e.setTitle("拜访");
            e.setStarttime(start * 1000L);
            e.setEndtime(end * 1000L);
            e.setCrmUserId(crmuserId);
            //e.setActivity_type(type_id);
            //e.setContactId(contactId);
            //e.setContactIds(contactIds);
            e.setStatus(status);
            Gson gson = new Gson();
            inserts += run.update(conn, sql, e.getCrmUserId(), gson.toJson(contactIds, int[].class), e.getEndtime(), e.getStarttime(), e.getTitle(), e.getStatus(),Integer.parseInt(meeting_type),Integer.parseInt(coachId));

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    
    
    public static long createNewRecord(String entityName, List<String> fieldNames, List<String> values){        
         String fieldssql = Joiner.on(",").join(fieldNames);
         fieldssql = fieldssql + ",whenadded";
         
         String valuesql = Joiner.on(",").join(values);
         valuesql =  valuesql + ", now()";
    
        String sql = "INSERT INTO "+entityName+" ("+fieldssql+") VALUES ("+valuesql+")";
        
        logger.debug("insert sql is:"+sql);
 
        Connection conn = null;
        //PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        PreparedStatement statement = null;
        long key = -1;
        try {
            conn = DBHelper.getConnection();
            statement  = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Failed to insert data");
                return -1;
            }
            
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                //user.setId(generatedKeys.getLong(1));
                 key = generatedKeys.getLong(1);
            } else {
                logger.error("failed to insert data");
                return -1;
            }
            
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
        }
        
        return key;

    }
    

    public static void insert2UserRelationTable(String entityName, String userId, String entityId){
        String sql = null;
        if(entityName.equalsIgnoreCase("account")){
            sql = "INSERT INTO accountcrmuser ( accountId, crmuserId) VALUES ("+entityId+","+userId+")";
        }else if(entityName.equalsIgnoreCase("contact")){
            sql = "INSERT INTO contactcrmuser ( contactId, crmuserId) VALUES ("+entityId+","+userId+")";
        }
        
        if(sql == null) {
            logger.error("entityName error");
            return;
        }
        
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    
    public static void updateRecord(String id,String entityName, List<String> fieldNames, List<String> values ) {
    	 String sql = "";
    	 int i=0;
         for(String name:fieldNames){
             if(i==0){
            	 sql = name +" = "+ values.get(i)  ;
            	
            	
             }else{
        	    sql = sql + "," + name +" = "+ values.get(i)  ;	 
        	    
             }
        	 i++;
         }
         
         sql = sql + ",whenadded = now()";
  
    
         sql = "UPDATE  "+entityName+ " SET "+sql+" where id = " + id;
        
        logger.debug("UPDATE sql is:"+sql);
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    public static void deleteRecord(String entityId,String entityName) {
        String sql = "";
        sql = "DELETE from " + entityName +" where id = " + entityId;
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to delete  calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    public static void updateStatusOfCalendarEvent(int eventId, int status) {
        String sql = "UPDATE activity SET status=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, status, eventId);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    
    
    public static void updateStatusOfExternalMeeting(int eventId, int status) {
        String sql = "UPDATE externalMeeting SET status=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, status, eventId);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to updateStatusOfExternalMeeting", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }
    
    public static void updateStatusOfInternalMeeting(int eventId, int status) {
        String sql = "UPDATE internalMeeting SET status=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, status, eventId);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to updateStatusOfInternalMeeting", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

    }

    public static CRMUser login(String loginName, String password) {
        Connection conn = null;
        CRMUser user = new CRMUser();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);
            password = DigestUtils.md5Hex(password);
            logger.debug("MD5 password is:" + password);
            user = run.query(conn, "SELECT * FROM crmuser where loginName=? AND password=?", h, loginName, password);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return user;
    }

    public static boolean isSessionValid(String sessionId, String sessionKey) {      
        boolean res = false;
        Connection conn = null;
        CRMUser user = new CRMUser();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);
            user = run.query(conn, "SELECT * FROM crmuser where id=? AND password=?", h, sessionId, sessionKey);
            if(user !=null){
               res = true;
            }
            
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        } 
        return res;
    }

    public static List searchContact(String userId, String search_target) {
        String sql = "select * from (select contact.id as cid,contact.name as cname,account.name as aname from contact,account,contactcrmuser where contactcrmuser.crmuserId="+userId+" AND contactcrmuser.contactId = contact.id AND contact.accountId=account.id AND (contact.name like '%"+search_target+"%' OR account.name like '%"+search_target+"%')) as a";
        logger.debug(sql );
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, sql, new MapListHandler());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;
    }

    
    
    public static List searchCRMUser(String search_target) {
        String sql = "select * from (select * from crmuser where name like '%"+search_target+"%' OR email like '%"+search_target+"%' OR cellPhone like '%"+search_target+"%') as a";
        logger.debug(sql );
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, sql, new MapListHandler());

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;
    }
    
    
    public static void removeContactFromTeam(String id) {
        String sql = "delete from contactcrmuser where id="+id;
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql);

            System.out.println("removed:" + inserts);
        } catch (Exception e) {
            logger.error("removeContactFromTeam", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
    }
}
