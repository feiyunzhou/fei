package com.rex.crm;

import org.apache.wicket.Page;

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
            return new UserPage();
        }else if(name.equalsIgnoreCase("dealerAccount")){
            return new DealerAccountPage();
        }else if(name.equalsIgnoreCase("dealerContact")){
            return new DealerContactPage();
        }else{
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
