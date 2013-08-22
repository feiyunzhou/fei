package com.rex;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.rex.crm.beans.Account;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.Contact;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.db.DBHelper;

public class LoadData {


	
	@Test
	public void loadContact2DB() throws SQLException {
		String sql =  "INSERT INTO contact (name,branch,department,duty,gender,mobilephone,tel_work,title,accountId) VALUES (?,?,?,?,?,?,?,?,?)";
		
		Connection conn = DBHelper.getConnection();
		QueryRunner run = new QueryRunner();
		int res =0;
		for (int i=0;i<80;i++) {
			try {
			    int random = (int) (Math.random() * 96 + 1);
				res = run.update(conn, sql, "张3"+i,"移动","科技部","公关","男","135010953"+i,"86-849293"+i,"主治医师", random)+1;
				
			} finally {
				// Use this helper method so we don't have to check for null
				//DbUtils.close(conn);
			    System.out.println("result:"+res);
			}
		}
		DbUtils.close(conn);
	}
	
	@Test
	public void modifyAccountTable() throws SQLException {

	    
        List<Account> ats = DAOImpl.getAllAccounts();
	    
	    String sql =  "UPDATE account SET pl1=?,pl2=?,pl3=?,pl4=?,pl5=?,pl6=? where id=?";
        
        Connection conn = DBHelper.getConnection();
        QueryRunner run = new QueryRunner();
        int res =0;
        
        for(Account a:ats){
            try {
                int r1 = (int)(Math.random()*3+1);
                int r2 = (int)(Math.random()*3+1);
                int r3 = (int)(Math.random()*2+1);
                int r4 = (int)(Math.random()*3+1);
                int r5 = (int)(Math.random()*3+1);
                int r6 = (int)(Math.random()*3+1);
                res += run.update(conn, sql, r1,r2,r3,r4,r5,r6,a.getId());
                
            } finally {
                // Use this helper method so we don't have to check for null
                //DbUtils.close(conn);
                System.out.println("result:"+res);
            }
        }
        
        DbUtils.close(conn);
    }
	
	   @Test
	    public void modifyContactTable() throws SQLException {

	        
	        List<Contact> ats = DAOImpl.getAllContacts();
	        
	        String sql =  "UPDATE contact SET sex=?,pl1=?,pl2=?,pl3=?,pl4=?,pl5=? where id=?";
	        
	        Connection conn = DBHelper.getConnection();
	        QueryRunner run = new QueryRunner();
	        int res =0;
	        
	        for(Contact a:ats){
	            try {
	                int sex1 =  (int)(Math.random()*1+1);
	                int r1 = (int)(Math.random()*12+1);
	                int r2 = (int)(Math.random()*7+1);
	                int r3 = (int)(Math.random()*7+1);
	                int r4 = (int)(Math.random()*5+1);
	                int r5 = (int)(Math.random()*1+1);
	                
	                res += run.update(conn, sql, sex1,r1,r2,r3,r4,r5,a.getId());
	                
	            } finally {
	                // Use this helper method so we don't have to check for null
	                //DbUtils.close(conn);
	               
	            }
	        }
	        System.out.println("result:"+res);
	        
	        DbUtils.close(conn);
	    }
	
       @Test
       public void modifyUserTable() throws SQLException {

           
           List<CRMUser> staff = DAOImpl.getAllCRMUsers();
           
           String sql =  "UPDATE crmuser SET pl1=?,pl2=?,role=?,pl4=?,pl5=? where id=?";
           
           Connection conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int res =0;
           
           for(CRMUser a:staff){
               try {
                   
                   int r1 = (int)(Math.random()*1+1);
                   int r2 = (int)(Math.random()*2+1);
                   int role = (int)(Math.random()*4+1);
                   int r4 = (int)(Math.random()*2+1);
                   int r5 = (int)(Math.random()*6+1);
                   
                   res += run.update(conn, sql, r1,r2,role,r4,r5,a.getId());
                   
               } finally {
                   // Use this helper method so we don't have to check for null
                   //DbUtils.close(conn);
                  
               }
           }
           System.out.println("result:"+res);
           
           DbUtils.close(conn);
       }
   
    
       @Test 
       public void loadData() throws SQLException {
       String sql = "INSERT INTO contactcrmuser (contactId,crmuserId) values (?,20)";
           Connection conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int res =0;
           for (int i=7;i<200;i++) {
               try {
                   //sql=String.format("%d医院", i+11);
                   res = run.update(conn, sql,i)+res;
                   //System.out.println(tmpsql);
                   
               } catch(Exception e){
                   e.printStackTrace();
               }finally{
                   System.out.println(res);
               }
           }
           DbUtils.close(conn);
       }
       
       
       @Test
       public void loadAccount2DB() throws SQLException {
           String sql =  "INSERT INTO `contact` VALUES (%d,'2013-08-22 21:02:42','李云%d',%a,'1',1,1988,11,'1','9829222','%d857822','13%d0109765','dbdf@sina.com','北京市朝阳区望京东路%d好',NULL,2,2,'无','本科','无','眼科手术','微创',10,11,'一般','1','眼科',NULL,NULL,1,NULL,NULL,5,NULL);"; 
           Connection conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int res =0;
           for (int i=0;i<588;i++) {
               try {
                   System.out.println(i);
                   String tmpsql = sql.replaceAll("%d", String.valueOf(6+i));
                   String tmp = tmpsql.replace("%a", String.valueOf(10+i%80));
                   
                   //sql=String.format("%d医院", i+11);
                   res = run.update(conn, tmp)+res;
                   System.out.println(tmp);
                   
               } catch(Exception e){
                   e.printStackTrace();
               }finally{
                   System.out.println(res);
               }
           }
           DbUtils.close(conn);
       }
       
       
       @Test
       public void loadDealerAccount2DB() throws SQLException {
           String sql =  "INSERT INTO dealerAccount (name,address,tele,status,pl1,pl2) VALUES (?,?,?,?,?,?)";
           
           Connection conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int res =0;
           for (int i=0;i<88;i++) {
               try {
                   res = run.update(conn, sql, "经销商"+i,"北京市朝阳区望京东路"+i+"号","138119926"+i,(i%2)+1,(i%3)+1,(i%7)+1)+1;
                   
               } finally {
                   // Use this helper method so we don't have to check for null
                   //DbUtils.close(conn);
                   System.out.println("result:"+res);
               }
           }
           DbUtils.close(conn);
       }
       
       @Test
       public void loadDealerContact2DB() throws SQLException {
           String sql =  "INSERT INTO dealerContact (name,sex,tel_work,dealerAccountId,status,pl1) VALUES (?,?,?,?,?,?)";
           
           Connection conn = DBHelper.getConnection();
           QueryRunner run = new QueryRunner();
           int res =0;
           for (int i=0;i<80;i++) {
               try {
                   int sex = (int) (Math.random() * 1 + 1);
                   int dealerAccountId = (int) (Math.random() * 80 + 1);
                   int pl1 = (int) (Math.random() * 3 + 1);
                   
                   int random = (int) (Math.random() * 96 + 1);
                   res = run.update(conn, sql, "张历历"+i,(i%2)+1,"138119926"+i,dealerAccountId,(i%2)+1,pl1)+1;
                   
               } finally {
                   // Use this helper method so we don't have to check for null
                   //DbUtils.close(conn);
                   System.out.println("result:"+res);
               }
           }
           DbUtils.close(conn);
       }
	
}
