package com.rex.crm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.TableDataPanel;

public class HomePage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
	    super();
	    Model<String> clock = new Model<String>() {
            private static final long serialVersionUID = 6765223786331155854L;

            @Override
            public String getObject() {
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                String time = df.format(new Date());
                 return time;
            }
       };
	    
	    add(new Label("clock", clock));
	    
	   
    }
}
