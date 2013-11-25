package com.rex.crm.admin;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;

import com.google.common.collect.Lists;
import com.rex.crm.SignIn2Session;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.dataport.ImportDataWizard;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;
import com.rexen.crm.beans.UserRole;
import com.rexen.crm.integration.DataImportDelegate;

/**
 * @author Feiyun Zhou
 */
public class ImportLogPage extends AdminTemplatePage {
    private String search_target = "";

    public ImportLogPage() {
        initPage(null, null);
    }

    public void initPage(final Map<String, Boolean> filter, List tdata) {
        final Entity entity = Configuration.getEntityByName("importlog");
        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        final String positionId = ((SignIn2Session) getSession()).getPositionId();
        setPageTitle(entity.getDisplay());

        String sql = entity.getSql();
        switch (roleId) {
        case UserRole.USER_ROLE_ADMINISTRATOR:
            sql = entity.getSqlAdmin();
            break;
        case UserRole.USER_ROLE_MANAGER:
            sql = entity.getSqlManager();
            break;
        case UserRole.USER_ROLE_SALES:
            sql = entity.getSql();
            break;
        }
        // List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        if (tdata == null || tdata.size() == 0) {
            switch (roleId) {
            case UserRole.USER_ROLE_ADMINISTRATOR:
                tdata = DAOImpl.queryEntityRelationList(sql);
                break;
            case UserRole.USER_ROLE_MANAGER:
                tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
                break;
            case UserRole.USER_ROLE_SALES:
                tdata = DAOImpl.queryEntityRelationList(sql, positionId);
                break;
            }

            if (filter == null) {
                switch (roleId) {
                case UserRole.USER_ROLE_ADMINISTRATOR:
                    tdata = DAOImpl.queryEntityRelationList(sql);
                    break;
                case UserRole.USER_ROLE_MANAGER:
                    tdata = DAOImpl.queryEntityRelationList(sql, positionId, positionId);
                    break;
                case UserRole.USER_ROLE_SALES:
                    tdata = DAOImpl.queryEntityRelationList(sql, positionId);
                    break;
                }

            } else {
                List<String> ft = Lists.newArrayList();
                for (String k : filter.keySet()) {
                    if (filter.get(k))
                        ft.add(k);
                }

                switch (roleId) {
                case UserRole.USER_ROLE_ADMINISTRATOR:
                    tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft);
                    break;
                case UserRole.USER_ROLE_MANAGER:
                    tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, positionId, positionId);
                    break;
                case UserRole.USER_ROLE_SALES:
                    tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft, positionId);
                    break;
                }

            }

        }
        add(new PageableTablePanel("datalist", entity, tdata, null));

        add(new BookmarkablePageLink("refresh_btn", ImportLogPage.class));
        add(new BookmarkablePageLink("import_btn", DataImportPage.class));

    }
}