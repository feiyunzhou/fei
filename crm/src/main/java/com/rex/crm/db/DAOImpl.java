package com.rex.crm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.CalendarEvent;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Province;

public class DAOImpl {
	private static final Logger logger = Logger.getLogger(DAOImpl.class);
	 public static ListMultimap<Integer,Integer> accountIdsByUserId; 
	
	public static ImmutableMap<Integer, City> getCityTable() {
		com.google.common.collect.ImmutableMap.Builder<Integer, City> mapBuilder = ImmutableMap
				.<Integer, City> builder();
		
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
	
    public static long getSizeOfAccountByUserId(int userId){
        long size  =0;
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, "select count(*) as ct from account as a inner join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?", new MapHandler(),userId);
            size = (long)map.get("ct");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    
        return size;
    }
    
    public static long getSizeOfUsersByAccountId(int accountId){
        long size  =0;
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            Map<String, Object> map = run.query(conn, "select count(*) as ct from crmuser as a inner join accountcrmuser as b on a.id=b.crmuserId where b.accountId=?", new MapHandler(),accountId);
            size = (long)map.get("ct");
        } catch (Exception e) {
            logger.error("failed to get size of account", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
    
        return size;
    }
    
    public static List<Account> getAccountsByIds(List<Integer> accountIds){
        List<Account> accounts = Lists.newArrayList();
        ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);
       
        List params = new ArrayList<>();
        for(int id:accountIds){
            params.add(id);
        }
        Connection conn = null;
        try {
            QueryRunner run = new QueryRunner();
           
            conn = DBHelper.getConnection();
            accounts = run.query(conn, "select * from account where id=?",params.toArray(new Object[0]), h);
            

        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
        
        return accounts;
    }
    
    public static Province getProvinceById(int provinceId){
        Connection conn = null;
        Province prov = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Province> h = new BeanHandler<Province>(Province.class);
            
            prov = run.query(conn, "SELECT * FROM province where id=?", h, provinceId);
            
        } catch (SQLException e) {
            logger.error("failed to get province",e);
        }finally {
            DBHelper.closeConnection(conn);
        }
        
        return prov;
    }
	 public static List<Account> getAccountsByUserId(int userId){
		List<Account> accounts = Lists.newArrayList();
		ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);
		Connection conn = null;
		try {
            QueryRunner run = new QueryRunner();
            conn = DBHelper.getConnection();
            accounts = run.query(conn, "select a.* from account as a inner join accountcrmuser as b on a.id=b.accountId where b.crmuserId=?", h,userId);
        } catch (Exception e) {
            logger.error("failed to get city table data", e);
        } finally {
            DBHelper.closeConnection(conn);
        }
	
		return accounts;
	 }
	 
	 public static List<CRMUser> getUsersByAccountId(int accountId){
	       List<CRMUser> users = Lists.newArrayList();
	        ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);
	        Connection conn = null;
	        try {
	            QueryRunner run = new QueryRunner();
	            conn = DBHelper.getConnection();
	            users = run.query(conn, "select a.* from crmuser as a inner join accountcrmuser as b on a.id=b.crmuserId where b.accountId=?", h,accountId);
	        } catch (Exception e) {
	            logger.error("failed to get city table data", e);
	        } finally {
	            DBHelper.closeConnection(conn);
	        }
	    
	        return users;
		 }
	 
	public static List<Account> getAllAccounts() {
		List<Account> accounts  = Lists.newArrayList();
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			QueryRunner run = new QueryRunner();
			ResultSetHandler<List<Account>> h = new BeanListHandler<Account>(Account.class);
			
			accounts = run.query(conn,"SELECT * FROM account", h);
			
		} catch (SQLException e) {
			logger.error("failed to get all accounts",e);
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("failed to close connection",e);
			}
		}

		return accounts;
	}
	
//     public static List<Account> getAllAccountsByUserId(String crm_user_id){    	
//    	return fakeAccounts;
//     }
//     
//     public static List<Province> getAllRegionData(String crm_user_id){
//    	 return fakeRegionData;
//     }
     
     public static Account getAccountById(int id){
         Connection conn = null;
         Account account = null;
         try {
             conn = DBHelper.getConnection();
             QueryRunner run = new QueryRunner();
             ResultSetHandler<Account> h = new BeanHandler<Account>(Account.class);
             
             account = run.query(conn, "SELECT * FROM account where id=?", h, id);
             
         } catch (SQLException e) {
             logger.error("failed to get all accounts",e);
         }finally {
             DBHelper.closeConnection(conn);
         }
         
         return account;
     }
     
     public static CRMUser[] getAllCRMUsersArray(){
    	 return getAllCRMUsers().toArray(new CRMUser[0]);
     }
     
     public static List<CRMUser> getAllCRMUsers(){
         List<CRMUser> users  = Lists.newArrayList();
         Connection conn = null;
         try {
             conn = DBHelper.getConnection();
             QueryRunner run = new QueryRunner();
             ResultSetHandler<List<CRMUser>> h = new BeanListHandler<CRMUser>(CRMUser.class);
             
             users = run.query(conn,"SELECT * FROM crmuser", h);
             
         } catch (SQLException e) {
             logger.error("failed to get all crm users",e);
         }finally {
             DBHelper.closeConnection(conn);
         }
         return users;
     }
     
     public static CRMUser getCRMUserInfoById(int id){
         Connection conn = null;
         CRMUser user = null;
         try {
             conn = DBHelper.getConnection();
             QueryRunner run = new QueryRunner();
             ResultSetHandler<CRMUser> h = new BeanHandler<CRMUser>(CRMUser.class);
             
             user = run.query(conn,"SELECT * FROM crmuser where id=?", h, id);
             
         } catch (SQLException e) {
             logger.error("failed to get user",e);
         }finally {
             DBHelper.closeConnection(conn);
         }
         
         return user;
     }
 
	public static void insertRelationOfAccountIDCRMUserID(int accountId, int userId) throws Exception{
		//Account account = accountIndexTable.get(accountId);
		CRMUser user = DAOImpl.getCRMUserInfoById(userId);
		Account account = DAOImpl.getAccountById(accountId);
		if(account != null && account.getId() !=0 && user!=null && user.getId() !=0){
		     Connection conn = null;
	         try {
	             conn = DBHelper.getConnection();
	             QueryRunner run = new QueryRunner();
	             int inserts = run.update( conn, "INSERT INTO accountcrmuser (accountId,crmuserId) VALUES (?,?)",
	                     account.getId(), user.getId() );
	             
	             logger.info(String.format("%s row inserted into insertRelationOfAccountIDCRMUserID!", inserts));
	             
	         } catch (SQLException e) {
	             logger.error("failed to insertRelationOfAccountIDCRMUserID",e);
	         }finally {
	             DBHelper.closeConnection(conn);
	         }
		    
		}
		
	}
	
	public static List<CalendarEvent> getEventsByUserId(int userId){
	    List<CalendarEvent> events  = Lists.newArrayList();
        Connection conn = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
     
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<List<CalendarEvent>> h = new BeanListHandler<CalendarEvent>(CalendarEvent.class);
            
            events = run.query(conn,"SELECT * FROM activity where crmuserId=?", h,userId);
            for(CalendarEvent e:events){
                e.setStart(sd.format(new Date(e.getStarttime())));
                e.setEnd(sd.format(new Date(e.getEndtime())));
            }
        } catch (SQLException e) {
            logger.error("failed to getEventsByUserId:"+userId,e);
        }finally {
            DBHelper.closeConnection(conn);
        }

        return events;
	}

    public static List<String> getMenuByUserId(String id) {
        List<String> menulist = Lists.newArrayList();
        menulist.add("home");
        menulist.add("account");
        menulist.add("contact");
        menulist.add("activity");
        menulist.add("user");
        return menulist;
    }
	

	public static List getTableData(String tableName, List<String> colunmNames,int first , int count ){
	    String queryColunm = Joiner.on(",").join(colunmNames);
	    String query = "select " + queryColunm + " from "+ tableName + " limit " + first + ", " + count;
	    
	    Connection conn = null;
	    List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, query, new MapListHandler());
            
        } catch (SQLException e) {
            logger.error("failed to get user",e);
        }finally {
            DBHelper.closeConnection(conn);
        }
        
        return lMap;
	    
	}
	
	
	   public static List queryEntityRelationList(String sql,String... params ){
	        logger.debug(sql);
	        Connection conn = null;
	        List lMap = Lists.newArrayList();
	        try {
	            conn = DBHelper.getConnection();
	            QueryRunner run = new QueryRunner();
	            lMap = (List) run.query(conn, sql, new MapListHandler(),params);
	            
	        } catch (SQLException e) {
	            logger.error("failed to get user",e);
	        }finally {
	            DBHelper.closeConnection(conn);
	        }
	        
	        return lMap;
	        
	    }
	
	public static List queryEntityList(String sql,int first , int count ){
        String query = sql + " limit " + first + ", " + count;
        Connection conn = null;
        List lMap = Lists.newArrayList();
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            lMap = (List) run.query(conn, query, new MapListHandler());
            
        } catch (SQLException e) {
            logger.error("failed to get user",e);
        }finally {
            DBHelper.closeConnection(conn);
        }
        
        return lMap;
        
    }
	
	public static Map queryEntityById(String sql,String id){
        String query = sql.replace("?", id);
        
        Connection conn = null;
        Map map = null;
        try {
            conn = DBHelper.getConnection();
            QueryRunner run = new QueryRunner();
            map = (Map) run.query(conn, query, new MapHandler());
            
        } catch (SQLException e) {
            logger.error("failed to get user",e);
        }finally {
            DBHelper.closeConnection(conn);
        }
        
        return map;
        
    }
	
	   public static Map getEntityData(String tableName, List<String> colunmNames,long id){
	        String queryColunm = Joiner.on(",").join(colunmNames);
	        String query = "select " + queryColunm + " from "+ tableName + " where id= " + id;
	        
	        Connection conn = null;
	        Map map = null;
	        try {
	            conn = DBHelper.getConnection();
	            QueryRunner run = new QueryRunner();
	            map = (Map) run.query(conn, query, new MapHandler());
	            
	        } catch (SQLException e) {
	            logger.error("failed to get user",e);
	        }finally {
	            DBHelper.closeConnection(conn);
	        }
	        
	        return map;
	        
	    }
    
}
