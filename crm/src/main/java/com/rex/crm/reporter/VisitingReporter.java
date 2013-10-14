package com.rex.crm.reporter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.db.DAOImpl;

public class VisitingReporter {

    
    public void generateReporting(String year_month){
        
        Date today = new Date(); 
        DateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
        
        if(year_month != null && !year_month.isEmpty()){
            try {
                today = sdf.parse(year_month);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       
      
        //compute the first day of month, and the last day of month
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  

        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        //calendar.set(Calendar., 1); 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstDayOfMonth = calendar.getTime();  
        

        calendar.add(Calendar.MONTH, 1);   
        calendar.add(Calendar.MILLISECOND, -1);  
        Date lastDayOfMonth = calendar.getTime();  
         
        DateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.out.println("firstDayOfMonth            : " + datetimeFormatter.format(firstDayOfMonth));  
        System.out.println("lastDayOfMonth             : " + datetimeFormatter.format(lastDayOfMonth));
        
        
        
    }
    
    
    public static List<Map> generateVisitingPerDay(List<Map> activityData, List<Map> workingDayData){
        List<Map> res = Lists.newArrayList();
        Map map = Maps.newHashMap();
        res.add(map);
        
        int num_of_activity = 0;
        int num_of_working_day = 1;
        
        if(activityData!=null && activityData.size()>0){
            num_of_activity = ((Number)activityData.get(0).get("num_of_activity")).intValue();
        }
        
        if(workingDayData!=null && workingDayData.size()>0){
            num_of_working_day = ((Number)workingDayData.get(0).get("num_of_working_day")).intValue();
        }
        int metric_of_visiting_per_day = 12;
        map.put("metric_of_visiting_per_day", metric_of_visiting_per_day);
        int num_of_visiting_per_day = num_of_activity/num_of_working_day;
        map.put("num_of_visiting_per_day", num_of_visiting_per_day);
        
        
        int rate_of_achieving = num_of_visiting_per_day*100/metric_of_visiting_per_day;
        
        if(rate_of_achieving<100){
            map.put("rate_of_achieving", "<span style=\"color:#F37E7E\"><strong>"+rate_of_achieving+"%</strong></span>");
        }else{
            map.put("rate_of_achieving", rate_of_achieving+"%");
        }
       
        return res;
    }
    
    public static List<Map> generateVisitingFrequencyReportingByUserId(String userId){
        float num_of_target_of_visiting_frequency = 3.7f;
        String sql = "select num_of_contact,num_of_activity, "+num_of_target_of_visiting_frequency+" as num_of_target_of_visiting_frequency," +
        		"(num_of_activity/num_of_contact) as num_of_visiting_frequency,  ((num_of_activity/num_of_contact)*100/"+num_of_target_of_visiting_frequency+") " +
        		"as rate_of_achieving from (select crmuserId, count(distinct contact.id) as num_of_contact from contactcrmuser," +
        		"contact where  crmuserId=? AND contact.id=contactcrmuser.contactId) as a, (SELECT crmuserId, count(activity.id)" +
        		" as num_of_activity FROM activity,contact WHERE crmuserId=? AND activity.status=2 AND activity.contactId=contact.id)" +
        		" as b where a.crmuserId=b.crmuserId";
       
       
        List<Map> dataList = DAOImpl.queryEntityRelationList(sql, userId,userId);
        
        //format the data
        for(Map mapData:dataList){
        	float rate_of_achieving =0;
        	if(null==mapData.get("rate_of_achieving")){
        		rate_of_achieving = 0;
        	}else{
        		rate_of_achieving = ((Number)mapData.get("rate_of_achieving")).floatValue();
        	}
        	int int_rate_of_achieving = (int)rate_of_achieving;
            if(int_rate_of_achieving<100){
                mapData.put("rate_of_achieving", "<span style=\"color:#F37E7E\"><strong>"+int_rate_of_achieving+"%</strong></span>");
            }else{
                mapData.put("rate_of_achieving", int_rate_of_achieving+"%");
            }
            
        }

        return dataList;
        
    }
    
    public static List<Map> generateVisitingCoverReportingByUserId(String userId){
        //float num_of_target_of_visiting_frequency = 3.7f;
        String sql = "select " +
        		"num_of_visited_contact," +
        		"num_of_contact, " +
        		"90 as metric_of_visiting_cover, " +
        		"(num_of_visited_contact*100/num_of_contact) as rate_of_visiting_cover, " +
        		"((num_of_visited_contact*100*100)/(90*num_of_contact)) as rate_of_achieving " +
        		"FROM (select crmuserId, count(distinct contact.id) as num_of_contact from contactcrmuser,contact " +
        		"      where  contactcrmuser.crmuserId=? AND contact.id=contactcrmuser.contactId ) as a, " +
        		"     (SELECT crmuserId, count(distinct contact.id) as num_of_visited_contact FROM activity,contact " +
        		"WHERE activity.status=2 AND activity.contactId=contact.id AND crmuserId=?) as b WHERE a.crmuserId = b.crmuserId";
       
       System.out.println(sql);
        List<Map> dataList = DAOImpl.queryEntityRelationList(sql, userId,userId);
        
        //format the data
        for(Map mapData:dataList){
        	int rate_of_achieving = 0;
        	if(null==mapData.get("rate_of_achieving")){
        		rate_of_achieving = 0;
        	}else{
        		rate_of_achieving = ((Number)mapData.get("rate_of_achieving")).intValue();
            //int int_rate_of_achieving = (int)rate_of_achieving;
        	}
            if(rate_of_achieving<100){
                mapData.put("rate_of_achieving", "<span style=\"color:#F37E7E\"><strong>"+rate_of_achieving+"%</strong></span>");
            }else{
                mapData.put("rate_of_achieving", rate_of_achieving+"%");
            }
            int rate_of_visiting_cover = 0;
            if(null==mapData.get("rate_of_visiting_cover")){
            	rate_of_visiting_cover = 0;
        	}else{
        		rate_of_visiting_cover = ((Number)mapData.get("rate_of_visiting_cover")).intValue();
            //int int_rate_of_achieving = (int)rate_of_achieving;
        	}
            //int rate_of_visiting_cover = ((Number)mapData.get("rate_of_visiting_cover")).intValue();
            mapData.put("rate_of_visiting_cover", rate_of_visiting_cover+"%");
        }
        return dataList;        
    }
    
    /**
     * 区域内工作天数
     * @param userId
     * @return
     */
    public static List<Map> generateWorkingDayReportingByUserId(String userId){
        //float num_of_target_of_visiting_frequency = 3.7f;
        String sql = "select 21 as metric_of_working_day, " +
        		"count(crmuserId) as num_of_working_day, " +
        		"count(crmuserId)*100/21 as rate_of_achieving from " +
        		"(SELECT crmuserId FROM activity where crmuserId=? " +
        		"    AND status=2 group by crmuserId,DATE_FORMAT(act_endtime,'%Y-%m-%d')) " +
        		"as a group by crmuserId";
       
       System.out.println(sql);
        List<Map> dataList = DAOImpl.queryEntityRelationList(sql, userId);
        
        //format the data
        for(Map mapData:dataList){
            int rate_of_achieving = ((Number)mapData.get("rate_of_achieving")).intValue();
            //int int_rate_of_achieving = (int)rate_of_achieving;
           // mapData.put("rate_of_achieving", rate_of_achieving+"%");
            
            if(rate_of_achieving<100){
                mapData.put("rate_of_achieving", "<span style=\"color:#F37E7E\"><strong>"+rate_of_achieving+"%</strong></span>");
            }else{
                mapData.put("rate_of_achieving", rate_of_achieving+"%");
            }

        }
        return dataList;        
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        VisitingReporter test = new VisitingReporter();
       // test.generateReporting("2013-07");
        test.generateVisitingFrequencyReportingByUserId("99");

    }

}
