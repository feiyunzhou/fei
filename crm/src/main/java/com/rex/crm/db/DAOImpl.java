package com.rex.crm.db;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.apache.wicket.util.string.StringValue;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.Choice;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Contact;
import com.rex.crm.beans.Province;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.beans.UserPosition;
import com.rex.crm.common.Entity;
import com.rex.crm.db.model.Activity;
import com.rex.crm.util.Configuration;

public class DAOImpl 
{
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
    
    public static long getNumOfContactOfUser(String userId) {
        long size = 0;
        Connection conn = null;
        String sql = "select count(distinct contact.id) as num_of_contact from " +
        		"contactcrmuser,contact where  contactcrmuser.crmuserId=? AND contact.id=contactcrmuser.contactId";
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, sql, new MapHandler(), userId);
            size = (long) map.get("num_of_contact");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
    }
    
    public static long getNumOfAccountOfUser(String userId) {
        long size = 0;
        Connection conn = null;
        String sql = "select count(distinct account.id) as num_of_account from " +
                "accountcrmuser,account where  accountcrmuser.crmuserId=? AND account.id=accountcrmuser.accountId";
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, sql, new MapHandler(), userId);
            size = (long) map.get("num_of_account");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
    }
    
    public static long getNumOfActivityOfUser(String userId) {
        long size = 0;
        Connection conn = null;
        String sql = "select count(activityId) as num_of_activity from activitycrmuser where crmuserId=?";
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, sql, new MapHandler(), userId);
            size = (long) map.get("num_of_activity");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return size;
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
    
    
    public static void insertRelationOfEntityIDCRMUserID(String entityName, String cId, String userId ,int type) throws Exception {
        int contactId = Integer.parseInt(cId);
        int uid = Integer.parseInt(userId);
        if (contactId != 0 && uid != 0) {
            String sql = "";
            long ts= System.currentTimeMillis();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_value = dateformat.format(ts);
            if(entityName.equalsIgnoreCase("contact")){
                sql = "INSERT INTO contactcrmuser (contactId,crmuserId) VALUES (?,?)";
            }else if(entityName.equalsIgnoreCase("account")){
                sql = "INSERT INTO accountcrmuser (accountId,crmuserId) VALUES (?,?)";
            }else if(entityName.equalsIgnoreCase("userInfo")){
                sql = "INSERT INTO user_position (positionId,userId,whenadded,isPrimary) VALUES (?,?,?,?)";
            }else if(entityName.equalsIgnoreCase("crmuser")){
                if(type == 0){
                    sql = "INSERT INTO accountcrmuser (crmuserId,accountId) VALUES (?,?)";
                }else if(type == 1){
                    sql = "INSERT INTO contactcrmuser (crmuserId,contactId) VALUES (?,?)";
                }else if(type == 2){
                    sql = "INSERT INTO user_position (userId,positionId) VALUES (?,?)";
                }else{
                    sql = "update  crmuser set reportto = ?  where id = "+userId+" ";
                }
            }
            int isPrimary = 1;
            Connection conn = null;
            try {
                conn = DBHelper.getConnection();
                QueryRunner run = new QueryRunner();
                int inserts = 0;
                if(entityName.equalsIgnoreCase("userinfo")){
                  inserts = run.update(conn, sql, userId,cId,date_value,isPrimary);
                }else if(type == 3){
                	inserts = run.update(conn, sql, cId);
                }else {
                  inserts = run.update(conn, sql, cId,userId);
                }

                logger.info(String.format("%s row inserted into insertRelationOfEntityIDCRMUserID!", inserts));

            } catch (SQLException e) {
                logger.error("failed to insertRelationOfEntityIDCRMUserID", e);
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

            events = run.query(conn, "SELECT * FROM activitycrmuser where crmuserId=?", h, userId);
            logger.debug("events:"+events.size());
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
    

    public static List<String> getMenuByRole(int roleId) {
        List<String> menulist = Lists.newArrayList();
        menulist.add("home");
        menulist.add("calendar");
        menulist.add("account");
        menulist.add("contact");
        menulist.add("activity");
        menulist.add("coaching");
     if(roleId == 1){
       //menulist.add("data_exchange_teample");
       //menulist.add("callreport");
       //menulist.add("DownLoadPage");
       //menulist.add("admintree");
     }
         //menulist.add("crmuser");
         //menulist.add("userInfo");
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
        logger.debug("sql语句："+sql);
        System.out.println("fuck you: " + sql);
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
    
    public static String queryEntityByName(String name) {
      Connection conn = null;
      int id = 0;
      try {
          conn = DBHelper.getConnection();
          QueryRunner run = new QueryRunner();
          Statement st = conn.createStatement();
          ResultSet rs = st.executeQuery("select id from crmdb.account where name = '" + name + "'");
          rs.next();
          id = rs.getInt(1);
          rs.close();
          st.close();
      } catch (SQLException e) {
          logger.error("failed to get user", e);
      } finally {
          DBHelper.closeConnection(conn);
      }
      return String.valueOf(id);
  }
    
    /***
     * 获得目标医生拜访频率统计
     * @param userId
     * @return
     */
    public static  Map<String,Map> stat4ContactVisitingFrequencyByUserId(String userId) {
        String query = "select * from (select  * from contact_grade_pl as grade_pl " +
        		"left join (select grade, count(contact.id) as num_of_contact from contactcrmuser,contact " +
        		"where contactcrmuser.crmuserId=? AND contact.id=contactcrmuser.contactId group by grade) as countact_count" +
        		"  on grade_pl.id = countact_count.grade) as joined_countact_count left " +
        		"join (select grade, count(activity.id) as num_of_activity from activity,contact where " +
        		"activity.contactId=contact.id AND activity.status=2 AND activity.crmuserId=? group by contact.grade) as " +
        		"activity_count on activity_count.grade = joined_countact_count.grade";
        logger.debug(query);
        Connection conn = null;
        List<Map<String, Object>> list  = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
             list = run.query(conn, query, new MapListHandler(),userId,userId);

        } catch (SQLException e) {
            logger.error("failed to getStat4ContactVisitingFrequencyByUserId:"+userId, e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        Map<String,Map> map = Maps.newLinkedHashMap();
        if(list!=null){
            for(Map<String,Object> m:list){
                map.put((String)m.get("val"),m);
            }
        }
        return map;

    }

    
    /***
     * 获得目标医生拜访覆盖率统计
     * @param userId
     * @return
     */
    public static  Map<String,Map> stat4ContactVisitingCoverRateByUserId(String userId) {
         String query = "select * from (select  * from contact_grade_pl as " +
         		"grade_pl left join (select grade, count(contact.id) as num_of_contact " +
         		"from contactcrmuser,contact where contactcrmuser.crmuserId=? AND " +
         		"contact.id=contactcrmuser.contactId group by grade) as countact_count  " +
         		"on grade_pl.id = countact_count.grade) as joined_countact_count " +
         		"left join (select grade, count(distinct contactId) as " +
         		"num_of_visited_contact from activity,contact where " +
         		"activity.contactId=contact.id AND activity.crmuserId=? group by contact.grade) " +
         		"as visited_contact_count on visited_contact_count.grade = joined_countact_count.grade";
        logger.debug(query);
        Connection conn = null;
        List<Map<String, Object>> list  = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            list = run.query(conn, query, new MapListHandler(),userId,userId);

        } catch (SQLException e) {
            logger.error("failed to stat4ContactVisitingCoverRateByUserId:"+userId, e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        Map<String,Map> map = Maps.newLinkedHashMap();
        if(list!=null){
            for(Map<String,Object> m:list){
                map.put((String)m.get("val"),m);
            }
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
        String query = null;
        if(picklist.equalsIgnoreCase("product")){
          query =  "select id, name from " + picklist;
        }else{
          query =  "select id, val from " + picklist;
        }
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
    
    public static List<Choice> queryPickListByFilter(String picklist,String filterName, String filterValue) {
      String query = null;
      if(picklist.equalsIgnoreCase("product")){
         query = "select id, name from " + picklist + " where "+filterName+"=?";
      }else{
        query = "select id, val from " + picklist + " where "+filterName+"=?"; 
      }
        List<Choice> choices = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<Choice>> h = new BeanListHandler<Choice>(Choice.class);
            choices = run.query(conn, query, h,filterValue);

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
    
    public static List<Choice> queryExternalIds(String tablename,String externalIdFieldName) {
        String sql = "select * from "+ tablename;
        List<Choice> choices = Lists.newArrayList();
        List list = DAOImpl.queryEntityRelationList(sql);
         
         for(Map map:(List<Map>)list){
             Choice c = new Choice();
             c.setId(((Number)map.get("id")).longValue());
             c.setVal((String)map.get(externalIdFieldName));
             choices.add(c);
         }
         
        return choices;

    }
    

    
    
    public static List<Pair<String, Map<String, Object>>> queryFilters(String sourceTableSQL, String filterField, String filterbyTable, String... param) {
        //sourceTableSQL = sourceTableSQL.replaceAll("?", user_id);
        List<Choice> choices = queryPickList(filterbyTable);
        List<Pair<String, Map<String, Object>>> res = Lists.newArrayList();
       // Connection conn = null;
       // String query  = null;
        try {
           // conn = DBHelper.getConnection();
            for (Choice ch : choices) {
             
                //query = "select count(a.id) as sum from (" + sourceTableSQL + " where " + filterField + " = " + ch.getId() + ") as a";
               // logger.debug("query is:" + query);
              //  QueryRunner run = new QueryRunner();
               /*Map<String, Object> map = run.query(conn, query, new MapHandler(), param);
                if (map.get("sum") == null) {
                	map.put("sum", 0L);
                }*/
                Map<String, Object> map = Maps.newHashMap();
                map.put("val", ch.getVal());
                res.add(Pair.of(String.valueOf(ch.getId()), map));
            }
        } catch (Exception e) {
            logger.error("failed to queryFilters", e);
        } finally {
           // DBHelper.closeConnection(conn);
        }
        return res;
    }
    
    public static long addCalendarEvent(int crmuserId, int contactId, String type, String title, String start, String end,int status,
            String owner,String modifier,String responsible_person,String visiting_purpose,String feature_product,int event_type,String participants) throws Exception {
        int type_id = Integer.parseInt(type);
        //logger.debug("modified date time:"+modify_datetime);
        String sql = "INSERT INTO activity (crmuserId,contactId,endtime,starttime,title,activity_type," +
        		"status,owner,whenadded,modifier,modify_datetime ,responsible_person,visiting_purpose,feature_product,event_type,participants) " +
        		"VALUES (?,?,?,?,?,?,?,?,now(),?,now(),?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        long key = -1;
        try {
            conn = DBHelper.getConnection();
            //QueryRunner run = new QueryRunner();
            //int inserts = 0;
            //inserts += run.update(conn, sql, crmuserId, contactId, Long.parseLong(end) * 1000L, Long.parseLong(start) * 1000L, title, type_id,
             ///       status,owner,modifier,responsible_person,visiting_purpose,feature_product,event_type);
           //logger.debug("inserted:" + inserts);
            
            statement  = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fillStatement(statement,crmuserId, contactId, Long.parseLong(end) * 1000L, Long.parseLong(start) * 1000L, title, type_id,
                          status,owner,modifier,responsible_person,visiting_purpose,feature_product,event_type,participants);
                
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
            DBHelper.closeConnection(conn);
        }
        
        return key;
    }
    
    public static long addCalendarEventForCoach(int crmuserId, int contactId, String type, String title, String start, String end,int status,
            String owner,String modifier,String responsible_person,String visiting_purpose,String feature_product,int event_type,String participants,
            int coach,String location,int total_score,String planing,String openling,String enquery_listening,String deliverable,String objection_handing,String summary) throws Exception {
        int type_id =Integer.valueOf(type).intValue();
        if(event_type==2){
        	type_id= 3;
        }
        //logger.debug("modified date time:"+modify_datetime);
        String sql = "INSERT INTO activity (crmuserId,contactId,endtime,starttime,title,activity_type," +
        		"status,owner,whenadded,modifier,modify_datetime ,responsible_person,visiting_purpose," +
        		"feature_product,event_type,participants,coacheeId,location,total_score,planing,openling,enquery_listening,deliverable,objection_handing,summary) " +
        		"VALUES (?,?,?,?,?,?,?,?,now(),?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        long key = -1;
        try {
            conn = DBHelper.getConnection();
            statement  = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fillStatement(statement,crmuserId, contactId, Long.parseLong(end) * 1000L, Long.parseLong(start) * 1000L, title, type_id,
                          status,owner,modifier,responsible_person,visiting_purpose,feature_product,event_type,participants
                          ,coach,location,total_score,planing,openling,enquery_listening,deliverable,objection_handing,summary);
            System.out.println("visiting_purpose:"+visiting_purpose);    
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Failed to insert data");
                return -1;
            }
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                 key = generatedKeys.getLong(1);
            } else {
                logger.error("failed to insert data");
                return -1;
            }
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        return key;
    }
    
    
    private   static void fillStatement(PreparedStatement stmt, Object... params)
            throws SQLException {

            if (params == null) {
                return;
            }
            ParameterMetaData pmd = stmt.getParameterMetaData();
            for (int i = 0; i < params.length; i++) {
            	stmt.setObject(i + 1, params[i]);
                /*if (params[i] != null) {
                    stmt.setObject(i + 1, params[i]);
                } else {
                    stmt.setNull(i + 1, pmd.getParameterType(i + 1));
                }
*/            }
        }
    
    
    public static void updateCalendarEvent(String entityId, String contactId, String type, String title, String start, String end,int status,
            String modifier,String visiting_purpose,String feature_product) throws Exception {
        int type_id = Integer.parseInt(type);
        String sql = "update activity SET contactId=?,endtime=?,starttime=?,title=?,activity_type=?,status=?,"+
                     "modifier=?,modify_datetime=?,visiting_purpose=?,feature_product=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, contactId, Long.parseLong(end) * 1000L, Long.parseLong(start) * 1000L, title, type_id,
                    status,modifier,new Date(),visiting_purpose,feature_product,entityId);
           logger.debug("updated ok:" + inserts);
        } catch (Exception e) {
            logger.error("failed to updateCalendarEvent", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    
    public static void updateCalendarEventForCoach(String entityId,String crmuserId,String start, String end,
            String modifier,int coach,String location,int total_score,String planing,String openling,String enquery_listening,String deliverable,String objection_handing,String summary,String name) throws Exception {
        String sql = "update activity SET crmuserID =?,endtime=?,starttime=?,"+
                     "modifier=?,modify_datetime=?,coachId=?,location=?,total_score=?,planing=?,openling=?,enquery_listening=?,deliverable=?,objection_handing=?,summary=?,title=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql,crmuserId,Long.parseLong(end) * 1000L, Long.parseLong(start) * 1000L,
                    modifier,new Date(),coach,location,total_score,planing,openling,enquery_listening,deliverable,objection_handing,summary,name,entityId);
           logger.debug("updated ok:" + inserts);
        } catch (Exception e) {
            logger.error("failed to updateCalendarEvent", e);
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
    public static long createNewCrmUser(String entityName,List<String> fieldNames,List<String> values,String userId){
    	String fieldssql = Joiner.on(",").join(fieldNames);
        String valuesql = Joiner.on(",").join(values);
        fieldssql = fieldssql + ",num_of_signIn,password";
   	 	valuesql =  valuesql + ",0,'"+DigestUtils.md5Hex("12345")+"'";
   	 	logger.debug("fieldssql sql is:"+fieldssql);
   	 	logger.debug("valuesql sql is:"+valuesql);
   	 	String sql = "INSERT INTO "+entityName+" ("+fieldssql+") VALUES ("+valuesql+")";
    
   	 	logger.debug("insert sql is:"+sql);

	    Connection conn = null;
	    //PreparedStatement statement = null;
	    ResultSet generatedKeys = null;
	    PreparedStatement statement = null;
	    long key=-1;
        try {
			conn = DBHelper.getConnection();
			statement  = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        int affectedRows = statement.executeUpdate();
	     
	        generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                //user.setId(generatedKeys.getLong(1));
                 key = generatedKeys.getLong(1);
            } else {
                logger.error("failed to insert data");
                return -1;
            }
	        System.out.println("add crmuser is True");
	        return key;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
        }
        return -1L;
    }
    
    public static int updateRecord4Import(String table,List<String> fieldNames,List<String> values, String byfieldname, String fieldvalue){
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
         sql = "UPDATE  "+table+ " SET "+sql+" where "+byfieldname+" = ?" ;
       
       logger.debug("UPDATE sql is:"+sql);
       Connection conn = null;
       int inserts = 0;
       try {
           conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
          
           inserts += run.update(conn, sql,fieldvalue);

           System.out.println("inserted:" + inserts);
       } catch (Exception e) {
           logger.error("failed to add new calendar event", e);
       } finally {
           DBHelper.closeConnection(conn);
       }
       
       return inserts;
    }
    
    public static Map queryRecordByField(String table, String fieldName, String fieldValue){
        
        String query = "select * from "+ table + " where "+fieldName + " = ?";

        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map  =  run.query(conn, query, new MapHandler(),fieldValue);

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        return map;

    }
    
    public static long importRecord(String table, List<String> fieldNames, List<String> values){        
        String fieldssql = Joiner.on(",").join(fieldNames);
        String valuesql = Joiner.on(",").join(values);
        
        logger.debug("fieldssql sql is:"+fieldssql);
        logger.debug("valuesql sql is:"+valuesql);
        String sql = "INSERT INTO "+table+" ("+fieldssql+") VALUES ("+valuesql+")";
       
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
    
    
    public static long insertImportMetaInfo(String entityName,String import_file_name){        

        String sql = "INSERT INTO importMetaInfo (name,entity_name,importfilename,whenadded,result) VALUES ('fakename','"+entityName+"','"+import_file_name+"',now(),NULL)";
       
       logger.debug("insert sql XXXXX is:"+sql);

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
           logger.error("failed to insertImportMetaInfo", e);
       } finally {
           if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
           if (statement != null) try { statement.close(); } catch (SQLException logOrIgnore) {}
           if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
       }
       
       return key;

   }
    
    public static boolean updateImportMetaInfoById( String logfilename,int num_of_total_record, int num_of_imported,int num_of_failed,int num_of_updated,int result,long id){
        String sql = "UPDATE importMetaInfo SET logfilename=?,num_of_total_record=?,num_of_imported=?,num_of_failed=?,result=?,status=?,num_of_updated=? where id=?";
        logger.debug("sql:"+sql);
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            inserts += run.update(conn, sql,logfilename,num_of_total_record,num_of_imported,num_of_failed,result,1,num_of_updated,id);
        } catch (Exception e) {
            logger.error("failed to activity", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        if(inserts>0){
            return true;
        }
        return false;
    }
    
    public static long createNewRecord(String entityName, List<String> fieldNames, List<String> values,String userId){        
         String fieldssql = Joiner.on(",").join(fieldNames);
         String valuesql = Joiner.on(",").join(values);
         if(entityName.equals("coaching")){
           entityName= "activity";
           fieldssql = fieldssql + ",event_type";
           valuesql = valuesql + "," +2;
           fieldssql = fieldssql + ",status";
           valuesql = valuesql + "," +1;
         }else if(entityName.equals("activity")){
        	 fieldssql = fieldssql.replaceAll("accountId,","").trim();
        	 fieldssql = fieldssql + ",crmuserId";
        	 valuesql = valuesql + "," +userId;
        	 
        	 fieldssql = fieldssql + ",event_type";
             valuesql = valuesql + "," +1;
             fieldssql = fieldssql + ",status";
             valuesql = valuesql + "," +1;
         }else if(entityName.equals("willcoaching")){
        	 entityName= "activity";
             fieldssql = fieldssql + ",event_type";
             valuesql = valuesql + "," +3;
             fieldssql = fieldssql + ",status";
             valuesql = valuesql + "," +1;
         }
         logger.debug("fieldssql sql is:"+fieldssql);
         logger.debug("valuesql sql is:"+valuesql);
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
    

    public static void insert2UserRelationTable(String entityName,String userId,String positionId,String coacheePositionId,String entityId){
        String sql = null;
        long ts= System.currentTimeMillis();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_value = dateformat.format(ts);
        if(entityName.equalsIgnoreCase("account_delect")){//暂不需要
            sql = "INSERT INTO accountcrmuser ( accountId, crmuserId) VALUES ("+entityId+","+positionId+")";
        }else if(entityName.equalsIgnoreCase("contact_delect")){//暂不需要
            sql = "INSERT INTO contactcrmuser ( contactId, crmuserId) VALUES ("+entityId+","+positionId+")";
        }else if(entityName.equalsIgnoreCase("activity")){
            sql = "INSERT INTO activitycrmuser ( activityId, crmuserId) VALUES ("+entityId+","+positionId+")";
        }else if (entityName.equalsIgnoreCase("coaching")||entityName.equalsIgnoreCase("willCoaching")){
          sql = "INSERT INTO activitycrmuser ( activityId,crmuserId) VALUES ("+entityId+","+positionId+")";
          insertActivityCrmuserTable(entityId,coacheePositionId);
        }else if (entityName.equalsIgnoreCase("userinfo")){
          sql = "INSERT INTO user_position ( userId,positionId,status,isPrimary,whenadded) VALUES ("+userId+","+positionId+",1,1,'"+date_value+"')";
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
    
    public static void insertActivityCrmuserTable(String activityId,String crmuserId){
      String sql  = "INSERT INTO activitycrmuser ( activityId,crmuserId) VALUES ("+activityId+","+crmuserId+")";
        
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
         sql = sql.replaceAll("accountName","accountId").trim();
        if(entityName.equalsIgnoreCase("coaching")){
          sql = "UPDATE  activity SET "+sql+" where id = " + id;
        }else{
          sql = "UPDATE  "+entityName+ " SET "+sql+" where id = " + id;
        }
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
    
    public static void updateUserInfoPositionByUserId( String userId) {
      String sql = "";
         sql = "UPDATE  user_position SET status = 2 where userId = " + userId;
       logger.debug("UPDATE sql is:"+sql);
       Connection conn = null;
       try {
           conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int inserts = 0;
           inserts += run.update(conn, sql);
       } catch (Exception e) {
           logger.error("failed to add new calendar event", e);
       } finally {
           DBHelper.closeConnection(conn);
       }
   }
    public static void insertUserPositionRelationTable(String entityName,String userId,String positionId, String entityId) {
      String sql = "";
      long ts= System.currentTimeMillis();
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String date_value = dateformat.format(ts);
      sql = "INSERT INTO user_position ( userId,positionId,status,isPrimary,createtime) VALUES ("+userId+","+positionId+",1,1,'"+date_value+"')";
       logger.debug("UPDATE sql is:"+sql);
       Connection conn = null;
       try {
           conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int inserts = 0;
           inserts += run.update(conn, sql);
       } catch (Exception e) {
           logger.error("failed to add new calendar event", e);
       } finally {
           DBHelper.closeConnection(conn);
       }
   }
    public static void updatePositionByUserId( String userId) {
      String sql = "";
         sql = "UPDATE  userInfo SET positionId = -1 where id = " + userId;
       logger.debug("UPDATE sql is:"+sql);
       Connection conn = null;
       try {
           conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int inserts = 0;
           inserts += run.update(conn, sql);
       } catch (Exception e) {
           logger.error("failed to add new calendar event", e);
       } finally {
           DBHelper.closeConnection(conn);
       }
   }
    
    public static void updateUserInfoPositionByPositionId( String positionId) {
      String sql = "";
         sql = "UPDATE  user_position SET status = 2 where positionId = " + positionId;
       logger.debug("UPDATE sql is:"+sql);
       Connection conn = null;
       try {
           conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int inserts = 0;
           inserts += run.update(conn, sql);
       } catch (Exception e) {
           logger.error("failed to add new calendar event", e);
       } finally {
           DBHelper.closeConnection(conn);
       }
   }
    public static int getLevelByPositionId(int positionId) {
        Connection conn = null;
        int level = 0;
        try {
            conn = DBHelper.getConnection(); 
            QueryRunner run = new QueryRunner();
            Map<String, Object> map = run.query(conn, "SELECT level FROM crmuser where id=?", new MapHandler(), positionId);
            if(map != null && map.get("level") !=null){
              level = (int) map.get("level");
            }
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return level;
    }
//    public static void updatePositionByPositionId( String positionId) {
//      String sql = "";
//         sql = "UPDATE  userInfo SET positionId = -1 where positionId = " + positionId;
//       logger.debug("UPDATE sql is:"+sql);
//       Connection conn = null;
//       try {
//           conn = DBHelper.getConnection();
//           QueryRunner run = new QueryRunner();
//           int inserts = 0;
//           inserts += run.update(conn, sql);
//       } catch (Exception e) {
//           logger.error("failed to add new calendar event", e);
//       } finally {
//           DBHelper.closeConnection(conn);
//       }
//   }
    public static void doneRecord(String id,String entityName, String time ) {
    	String sql = "";
      
    	sql = "UPDATE  activity SET status= 2 ,act_endtime = '"+ time +"'  where id = " + id;
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
    
    public static int deleteRecord(String entityId,String entityName) {
        String sql = "";
        if(entityName.equals("coaching")){
          sql = "DELETE from activity where id = " + entityId;
        }else{
          sql = "DELETE from " + entityName +" where id = " + entityId;
        }
        logger.debug("delte record sql:"+ sql);
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
          
            inserts += run.update(conn, sql);
            
        } catch (Exception e) {
            logger.error("failed to delete  calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return inserts;
    }
    
    
    public static int deleteAllRecords(String entityName) {
        String sql = "DELETE from " + entityName + " where id != 1" ;
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
          
            inserts += run.update(conn, sql);
            
        } catch (Exception e) {
            logger.error("failed to delete  deleteAllRecords", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return inserts;
    }
    
    public static int deleteAccountTeamWithPositionId(int positionId) {
        String sql = "DELETE from accountcrmuser where crmuserid = " + positionId;
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
          
            inserts += run.update(conn, sql);
            
        } catch (Exception e) {
            logger.error("failed to delete  deleteAllRecords", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return inserts;
    }
    
    public static void deleteaAcountCrmuserRecord(String entityId) {
        String sql = "";
        sql = "DELETE from " + "accountcrmuser" + " where accountId = " + entityId;
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
    public static void deleteaCountactRecord(String entityId) {
        String sql = "";
        sql = "DELETE from " + "contact" + " where accountId = " + entityId;
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
    public static void updateStatusOfCalendarEvent(int eventId, int status, Date act_endtime) {
        String sql = "UPDATE activity SET status=?, act_endtime=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, status, act_endtime,eventId);

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to add new calendar event", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    //拜访辅导点击删除触发的事件
    public static void updateStatusOfActivity(int eventId, int status) {
        String sql = "UPDATE activity SET status=? where id=?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql, status,eventId);

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

    public static UserInfo login(String loginName, String password) {
    	System.out.println("登录");
        Connection conn = null;
        UserInfo user = new UserInfo();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            password = DigestUtils.md5Hex(password);
            logger.debug("MD5 password is:" + password);
            user = run.query(conn, "SELECT * FROM userInfo where loginName=? AND password=?", h, loginName, password);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return user;
    }
    
    public static CRMUser getPositionInfoByUserId(int uid) {
        System.out.println("登录"+uid);
        Connection conn = null;
        CRMUser user = new CRMUser();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);
            user = run.query(conn, "SELECT * FROM crmuser where id=? ", h,  uid);

        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return user;
    }
    
    public static UserPosition getActivityPositionInfoByUserId(int uid) {
      Connection conn = null;
      UserPosition user = new UserPosition();
      try {
          conn = DBHelper.getConnection();
          QueryRunner run = new QueryRunner();
          ResultSetHandler<UserPosition> h = new BeanHandler<UserPosition>(UserPosition.class);
          user = run.query(conn, "SELECT *,whenadded FROM user_position  where userId=? group by whenadded desc limit 1 ", h,  uid);

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
        if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
        }
        String sql = "select * from (select contact.id as cid,contact.name as cname,account.name as aname from contact,account,contactcrmuser where contactcrmuser.crmuserId="+userId+" AND contactcrmuser.contactId = contact.id AND contact.accountId=account.id AND (contact.name like '%"+search_target+"%' OR account.name like '%"+search_target+"%')) as a";
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
    
    
    public static List searchAccount(String userId, String search_target,int roleId) {
        if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
        }
        String sql = "select * from account where (? is not null) AND account.id !=-1 AND name like '%"+search_target+"%' order by whenadded DESC";
        if(roleId != 1){
           sql = "SELECT * from (select account.* from accountcrmuser,account"+
            " where accountcrmuser.crmuserId=? AND account.id != -1 AND " +
            "accountcrmuser.accountId=account.id AND (name like '%"+search_target+"%' ) order by whenadded DESC) as aAccount";
        }
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, sql, new MapListHandler(),userId);

        } catch (SQLException e) {
            logger.error("failed to searchAccount", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return lMap;
    }
    
    public static List searchCRMUserOfManager(String managerId,String search_target) {
        if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
        }
        String sql = "select * from crmuser";
        if(managerId!=null){
           sql = "select * from (select * from crmuser where crmuser.id > 0 AND reportto="+managerId+" AND (name like '%"+search_target+"%' OR email like '%"+search_target+"%' OR cellPhone like '%"+search_target+"%')) as a";
        }else{
            sql = "select * from (select * from crmuser where crmuser.id > 0 AND  (name like '%"+search_target+"%' OR email like '%"+search_target+"%' OR cellPhone like '%"+search_target+"%')) as a"; 
        }
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

    public static List searchCRMUserByManager(String managerId,String search_target) {
        if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
        }
        String sql = "select * from crmuser";
           sql = "select * from (select * from crmuser where crmuser.id > 0 AND reportto="+managerId+") as a";
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
    	if(search_target == null|| search_target.equalsIgnoreCase("*")){
	          search_target = "";
    	}
        String sql = "select * from (select * from crmuser where (crmuser.id !=-1) AND (name like '%"+search_target+"%' OR code like '%"+search_target+"%' OR reportto like '%"+search_target+"%')) as a";
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
    
    public static List searchUserPosition(String uid,String search_target) {
    	if(search_target == null|| search_target.equalsIgnoreCase("*")){
	          search_target = "";
    	}
        String sql = "select * from (  select * from crmuser where id not in (select positionid from user_position where userId = "+uid+") and (crmuser.id !=-1) AND (name like '%"+search_target+"%' OR code like '%"+search_target+"%' OR reportto like '%"+search_target+"%')) as a";
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
    
    
    public static List searchPositionCRMUser(String search_target) {
      if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
      }
      
        String sql = "SELECT * FROM (select * from (select  crmuser.*, user_position.positionId from  crmuser  left join user_position ON crmuser.id = user_position.positionId where user_position.positionId is null ) as crmuserposition where (crmuserposition.id !=-1 )AND (name like '%"+search_target+"%' OR code like '%"+search_target+"%' OR reportto like '%"+search_target+"%')) as a";
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
    
    public static List searchUser(String search_target) {
      if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
      }
        String sql = "select * from (select * from userinfo where (userinfo.id !=-1) AND (name like '%"+search_target+"%' OR sex like '%"+search_target+"%' )) as a";
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
    public static List searchManager(String search_target,String excludeId) {
        if(search_target == null|| search_target.equalsIgnoreCase("*")){
              search_target = "";
        }
        if(excludeId == null) excludeId = "-1";
        
        String sql = "select * from (select * from crmuser where (crmuser.id !="+excludeId+") AND (crmuser.id !=-1) AND (name like '%"+search_target+"%' OR reportto like '%"+search_target+"%' )) as a";
        logger.debug("searchManager:"+ sql );
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
    
    public static List searchCoachee(String search_target,String excludeId,String userId) {
      if(search_target == null|| search_target.equalsIgnoreCase("*")){
            search_target = "";
      }
      if(excludeId == null) excludeId = "-1";
      
      String sql = "select * from (select * from userInfo where (userInfo.id !="+excludeId+") AND (userInfo.id !=-1)  AND (name like '%"+search_target+"%' )) as a";
      logger.debug("searchManager:"+ sql );
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
    
    public static List searchCRMAccount(String search_target) {
      if(search_target == null|| search_target.equalsIgnoreCase("*")){
        search_target = "";
    }
        String sql = "select * from (select * from account where (account.id > 0 ) AND  (name like '%"+search_target+"%' OR tel like '%"+search_target+"%' OR fax like '%"+search_target+"%')) as a";
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
    
    public static List searchCRMContact(String search_target) {
      if(search_target == null|| search_target.equalsIgnoreCase("*")){
        search_target = "";
    }
      String sql = "select * from (select * from contact where (contact.id > 0) AND (name like '%"+search_target+"%' OR office_tel like '%"+search_target+"%' OR cellphone like '%"+search_target+"%')) as a";
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
    public static void removeEntityFromTeam(String teamtable, String id) {
        String sql = "delete from "+teamtable+" where id="+id;
        if(teamtable.equalsIgnoreCase("crmuser")){
        	sql = "update  "+teamtable+" set reportto = 0 where id="+id;
        }
        logger.debug("sserserserser"+ sql);
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

    public static List<CRMUser> getInferiorsByManagerId(String managerId) {
        List<CRMUser> inferiors = Lists.newArrayList();
        List<CRMUser> users = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);

            users = run.query(conn, "SELECT crmuser.*,userInfo.name as userInfoName FROM crmuser left join user_position on crmuser.id = user_position.positionId left join  userInfo  on user_position.userId = userInfo.id where crmuser.reportto=?", h,managerId);

        } catch (SQLException e) {
            logger.error("failed to get all crm users", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        
        for(CRMUser user:users){
            CRMUser u = new CRMUser();
            u.setName(user.getName()+"--"+user.getUserInfoName());
            u.setCellPhone(user.getCellPhone());
            u.setEmail(user.getEmail());
            u.setId(user.getId());
            u.setRole(user.getRole());
            u.setLoginName(user.getLoginName());
            u.setCode(user.getCode());
            inferiors.add(u);
        }
        
        return inferiors;
    }
    
    public static List<CRMUser> getCRMUserWithoutSuperior() {
       
        List<CRMUser> users = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);

            users = run.query(conn, "SELECT * FROM crmuser where (reportto=0 OR reportto=-1 OR (reportto is NULL)) AND id != -1", h);

        } catch (SQLException e) {
            logger.error("failed to get all crm users", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        return users;
    }
    
    public static List<Province> getRegionWithPosition() {
        
        List<Province> users = Lists.newArrayList();
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<Province>> h = new BeanListHandler<Province>(Province.class);

            users = run.query(conn, "SELECT * FROM crmuser where (reportto=0 OR reportto=-1 OR (reportto is NULL)) AND id != -1", h);

        } catch (SQLException e) {
            logger.error("failed to get all crm users", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        return users;
    }
    
    // update crmuser baseInfo
    public static boolean updateStatusOfInternalMeeting(int userId,String userName,String cellphone,String email,String photo,int sex,String loginName) {
    	String sql = "UPDATE userinfo SET name=?,cellphone=?,email=?,photo=?,sex=?,loginName=? where id=?";
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            inserts += run.update(conn, sql, userName, cellphone,email,photo,sex,loginName,userId);
            System.out.println("updateCrmUser:" + inserts);
        } catch (Exception e) {
            logger.error("failed to updateStatusOfInternalMeeting", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        if(inserts>0){
    		return true;
    	}
    	return false;
    }
    //update user Password
    public static boolean updateCrmUserPassword(int userId,String password){
    	String  sql=" UPDATE userinfo SET password=? where id =?";
    	Connection conn = null;
    	String newPassword = DigestUtils.md5Hex(password);
    	int updates = 0;
    	try{
    		conn = DBHelper.getConnection();
    		QueryRunner run = new QueryRunner();
    		updates += run.update(conn, sql, newPassword,userId);
    		logger.debug("update password success!");
    	} catch (Exception e){
    		logger.error("failed to updatecrmUser Password",e);
    	}finally{
    		DBHelper.closeConnection(conn);
    	}
    	if(updates>0){
    		return true;
    	}
    	return false;
    }
    public  static UserInfo getUserByActivation(int userID,long createTime){
    	System.out.println("根据激活码Code获取用户");
        Connection conn = null;
        UserInfo user = new UserInfo();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            user = run.query(conn, "SELECT * FROM userinfo where ts=? and id=?", h, createTime,userID);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return user;
    }
    
    //reset password
    public static int  resetUserPassword(int entityId){
    	System.out.println("reset password");
    	String  sql=" UPDATE userinfo SET password= ?, num_of_signIn = 0 where id =?";
        Connection conn = null;
        int insert = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            insert = run.update(conn, sql,DigestUtils.md5Hex("12345"),entityId);
    		logger.debug("reset password success!");
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return insert;
    }
    //修改用户激活状态
    public static void updateUserActivited(int entityId){
    	String  sql=" UPDATE userinfo SET isActivited=? where id =?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            run.update(conn, sql,1,entityId);
    		logger.debug("update activited success!");
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    
    //update user reportto from someone to someone
    public static boolean updateCrmUserReport(String from, String to){
        String  sql=" UPDATE crmuser SET reportto=? where reportto =?";
        Connection conn = null;
        
        int updates = 0;
        try{
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            updates += run.update(conn, sql, to, from);
            logger.debug("updateCrmUserReport success!");
        } catch (Exception e){
            logger.error("failed to updateCrmUserReport",e);
        }finally{
            DBHelper.closeConnection(conn);
        }
        if(updates>0){
            return true;
        }
        return false;
    }
    
    
    public static UserInfo getUserById(int entityId){
      System.out.println("根据crmuserID获取用户" + entityId);
        Connection conn = null;
        UserInfo userInfo = new UserInfo();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            userInfo = run.query(conn, "SELECT * FROM userInfo where id=?", h, entityId);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return userInfo;
    }
    public static CRMUser getCrmUserById(String entityId){
        int id = Integer.parseInt(entityId);
        return getCrmUserById(id);
    }
    
    //根据crmuserid获取crm对象
    public static CRMUser getCrmUserById(int entityId){
    	System.out.println("根据crmuserID获取用户" + entityId);
        Connection conn = null;
        CRMUser crmuser = new CRMUser();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);
            crmuser = run.query(conn, "SELECT * FROM crmuser where id=?", h, entityId);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return crmuser;
    }
    //根据医生ID获取医生对象
    public static Contact getContactById(int entityId){
    	System.out.println("根据crmuserID获取用户");
        Connection conn = null;
        Contact contact = new Contact();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Contact> h = new BeanHandler<Contact>(Contact.class);
            contact = run.query(conn, "SELECT * FROM contact where id=?", h, entityId);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return contact;
    }
    public  static UserInfo getUserByLoginName(String loginName){
        Connection conn = null;
        UserInfo user = new UserInfo();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            user = run.query(conn, "SELECT * FROM userInfo where loginName=?", h, loginName);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return user;
    }
    
    public  static List<UserInfo> getUserByPositionId(int userId){
      Connection conn = null;
      List<UserInfo> inferiors = Lists.newArrayList();
      List<UserInfo> users = Lists.newArrayList();
      try {
          conn = DBHelper.getConnection();
          QueryRunner run = new QueryRunner();
          ResultSetHandler<List<UserInfo>> h = new BeanListHandler<UserInfo>(UserInfo.class);
          users = run.query(conn, "SELECT * FROM userInfo left  join user_position on userInfo.id = user_position.userId where (user_position.status=1) and (user_position.positionId = ?)", h, userId);
      } catch (SQLException e) {
          logger.error("failed to get all accounts", e);
      } finally {
          DBHelper.closeConnection(conn);
      }
      for(UserInfo userInfo:users){
        UserInfo u = new UserInfo();
        u.setName(userInfo.getName());
        inferiors.add(u);
      }
      return inferiors;
  }
    
    public static Activity getActivityById(int entityId){
    	System.out.println("根据活动ID获取用户");
        Connection conn = null;
        Activity activity = new Activity();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Activity> h = new BeanHandler<Activity>(Activity.class);
            activity = run.query(conn, "SELECT * FROM activity where id=?", h, entityId);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        return activity; 
    }
    
    public static UserInfo getUserInfoById(int id) {
        Connection conn = null;
        UserInfo user = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);

            user = run.query(conn, "SELECT * FROM userinfo where id=?", h, id);

        } catch (SQLException e) {
            logger.error("failed to get user", e);
        } finally {
            DBHelper.closeConnection(conn);
        }

        return user;
    }
    
    
    public static void insertLogInfor(String sessionId,String loginName , int type){
        String sql = null;
        if(type == 0){
            sql = "INSERT INTO loginhistory ( sessionId, loginName,loginTime) VALUES (?,?,?)";
        }else if(type == 1){
            sql = "INSERT INTO loginhistory ( sessionId, loginName,logoutTime) VALUES (?,?,?)";
        }
      
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            int inserts = 0;
            inserts += run.update(conn, sql,sessionId,loginName,new Date());

            System.out.println("inserted:" + inserts);
        } catch (Exception e) {
            logger.error("failed to insertLogInfor", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    //update signIn number
    public static void addSignInNumber(int userId,int number){
    	String  sql=" UPDATE userinfo SET num_of_signIn=? where id =?";
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<UserInfo> h = new BeanHandler<UserInfo>(UserInfo.class);
            run.update(conn, sql,number,userId);
        } catch (SQLException e) {
            logger.error("failed to get all accounts", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    }
    public static List<UserPosition>  getPositionsByUserId(int userId){
    	 List<UserPosition> users = Lists.newArrayList();
         ResultSetHandler<List<UserPosition>> h = new BeanListHandler<UserPosition>(UserPosition.class);
         Connection conn = null;
         try {
             QueryRunner run = new QueryRunner();
             conn = DBHelper.getConnection();
             users = run.query(conn, "select * from user_position where status =1 and userId=?", h, userId);
         } catch (Exception e) {
             logger.error("failed to get userPostion table data", e);
         } finally {
             DBHelper.closeConnection(conn);
         }

         return users;
    }
    //用户初次登录修改关键信息
    public static boolean updateKeyUserInfoMessage(String phone,String email,String password,int userId){
    	String sql = "UPDATE userinfo SET cellphone=?,email=?,password=? where id=?";
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            inserts += run.update(conn, sql, phone,email,DigestUtils.md5Hex(password),userId);
        } catch (Exception e) {
            logger.error("failed to userInfo", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        if(inserts>0){
    		return true;
    	}
    	return false;
    } 
    //修改活动状态为未执行
    public static boolean updateActivityStatusById(int entityId){
    	String sql = "UPDATE activity SET status=3 where id=?";
        Connection conn = null;
        int inserts = 0;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            inserts += run.update(conn, sql,entityId);
        } catch (Exception e) {
            logger.error("failed to activity", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        if(inserts>0){
    		return true;
    	}
    	return false;
    }
    
}
