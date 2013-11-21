package com.rex.crm;

import org.apache.wicket.Page;

import com.rex.crm.admin.PositionPage;
import com.rex.crm.admin.UserPage;

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
        }else if(name.equals("alert")){
        	return new AlertPage();
        }
        else{
            return new HomePage();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
