package com.rex.crm;

import org.apache.wicket.Page;

import com.rex.crm.admin.PositionPage;
import com.rex.crm.admin.ProductPage;
import com.rex.crm.admin.ProductTreePage;
import com.rex.crm.admin.UserPage;
import com.rex.crm.common.EntityDetailPage;

public class PageFactory {
  
    public static Page createPage(String name){
        if(name.equalsIgnoreCase("account")){
            return new AccountPage();
        }else if(name.equalsIgnoreCase("contact")){
            return new ContactPage();
        }else if(name.equalsIgnoreCase("calendar")){
            return new CalendarPage();
        }else if(name.equalsIgnoreCase("activity")){
            return new ActivityPage();
        }else if(name.equalsIgnoreCase("crmuser")){
            return new PositionPage();
        }else if(name.equalsIgnoreCase("coaching")||name.equalsIgnoreCase("willCoaching")){
          return new CoachingPage();
        }else if(name.equalsIgnoreCase("userInfo")){
          return new UserPage();
        }else if(name.equalsIgnoreCase("alert")){
        	return new AlertPage();
        }else if(name.equalsIgnoreCase("productline")||name.equalsIgnoreCase("product")||name.equalsIgnoreCase("productcategory")){
        	return new ProductPage();
        }else if(name.equalsIgnoreCase("province")||name.equalsIgnoreCase("city")){
        	return new AreaPage();
        }
        else{
            return new HomePage();
        }
    }
     public static Page createPageToDetail(String EntityName,String id){
            return new EntityDetailPage(EntityName,id);
     }
    public static Page createPageTree(String EntityName,String id){
        
            return new ProductTreePage(id,EntityName);
     }
     
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
