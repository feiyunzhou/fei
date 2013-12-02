package com.rex.crm.common.advancedSearch;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.rex.crm.TemplatePage;
import com.rex.crm.beans.AdvancedSearchFilter;
import com.rex.crm.common.Entity;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.advancedSearch.AdvancedSearchPanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;
import com.rex.crm.SignIn2Session;

/**
 * @author Feiyun Zhou
 */
public class AdvancedSearchPage extends TemplatePage {

    private static final Logger logger = Logger.getLogger(AdvancedSearchPage.class);
    private String search_target = "";

    /**
     * Constructor
     */
    public AdvancedSearchPage() {
        // PageParameters pp = new PageParameters();
        // IRequestParameters params =
        // this.getRequestCycle().getRequest().getRequestParameters();
        initPage(null, null);
    }

    public AdvancedSearchPage(String entityName,AdvancedSearchFilter[] filters) {
        initPage(entityName,filters);
    }

    private void initPage(String entityName, AdvancedSearchFilter[] filters) {
        final Entity entity = Configuration.getEntityByName(entityName);
        setPageTitle(entity.getDisplay());
        final String positionId = ((SignIn2Session) getSession()).getPositionId();
        final int roleId = ((SignIn2Session) getSession()).getRoleId();

        String sql = entity.getSql();
        switch (roleId) {
        case 1:
            sql = entity.getSqlAdmin();
            break;
        case 2:
            sql = entity.getSqlManager();
            break;
        case 3:
            sql = entity.getSql();
            break;
        }

        
        AdvancedSearchPanel searchPanel = new AdvancedSearchPanel("advancedSearch", entityName, filters);
        add(searchPanel);
        
        List<Map> tdata = Lists.newArrayList();
        if (filters  != null) {
            sql = sql + " where " + searchPanel.buildFilterSQL(entity, filters);
            switch (roleId) {
            case UserRole.USER_ROLE_ADMINISTRATOR:
                tdata = DAOImpl.queryEntityRelationList(sql);
                break;
            case UserRole.USER_ROLE_MANAGER:
                tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId, positionId);
                break;
            case UserRole.USER_ROLE_SALES:
                tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
            }
        }

      
       

        add(new PageableTablePanel("datalist", entity, tdata, null));

    }

}