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
	
	
}
