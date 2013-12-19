package com.rex.crm.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.common.AccountPositionPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailContainerPanel;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.tree.Node;
import com.rex.crm.common.tree.TreeFactory;
import com.rex.crm.common.tree.TreePanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;

/**
 * @author Feiyun Zhou
 */
public class PositionTreePage extends AdminTemplatePage
{

    private String search_target = "";

    /**
     * Constructor
     */
    public PositionTreePage()
    {
        setPageTitle("系统管理-区域管理");
        StringValue positionId = getRequest().getRequestParameters()
          .getParameterValue("positionId");

        int level = 0;
        if (!positionId.isNull() && !positionId.isEmpty())
        {
            level = DAOImpl.getLevelByPositionId(positionId.toInt());
        }
        if (positionId.isEmpty() || positionId.isNull())
        {
            initPage(null, 0);
        }
        else
        {
            initPage(positionId.toString(), level);
        }

        urlFor(PositionTreePage.class, null);

    }

    public PositionTreePage(String id, String level)
    {
        setPageTitle("系统管理-区域管理");
        int lev = Integer.parseInt(level);
        initPage(id, lev);
    }

    private void initPage(final String positionId, int level)
    {
        Gson gson = new Gson();
        String result = gson.toJson(TreeFactory.createAccountPositionTree(),Node.class);
        add(new TreePanel("treePanel", result));
         Map<String, Entity> entities = Configuration.getEntityTable();

        if (positionId != null)
        {
            if (positionId.equals("-1"))
            {
                List tdata = DAOImpl.queryEntityRelationList("select * from user_position_query");
               add(new PageableTablePanel("datalist", entities.get("user_position_query"), tdata, null));
            }
            else
            {
                AccountPositionPanel panel = new AccountPositionPanel("datalist",
                  "crmuser", positionId, level);
                add(panel);
            }
        }
        else
        {
            add(new EmptyPanel("datalist"));
        }
    }

    @Override
    public void renderHead(IHeaderResponse response)
    {
        super.renderHead(response);
        StringValue act_key = getRequest().getRequestParameters()
          .getParameterValue("positionId");
        Map<String, Object> map = new HashMap<>();

        map.put("act_key", act_key.toString());
        PackageTextTemplate ptt = new PackageTextTemplate(getClass(),
          "adminpage.js");
		// OnDomReadyHeaderItem onDomReadyHeaderItem =
        // OnDomReadyHeaderItem.forScript( ptt.asString( map ) );
        // response.render(onDomReadyHeaderItem);
        System.out.println(ptt.asString(map));
        response.render(JavaScriptHeaderItem.forScript(ptt.asString(map), null));

        try
        {
            ptt.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
