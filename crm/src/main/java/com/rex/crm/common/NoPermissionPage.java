package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.rex.crm.TemplatePage;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class NoPermissionPage extends TemplatePage {
    private static final Logger logger = Logger.getLogger(NoPermissionPage.class);
    private static final long serialVersionUID = -2613412283023068638L;
    
    public NoPermissionPage(){
        this.setPageTitle("无权限读取");
       
    }

}
