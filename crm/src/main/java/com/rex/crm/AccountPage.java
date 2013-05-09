package com.rex.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.rex.crm.account.AccountListPanel;
import com.rex.crm.common.Entity;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.TableDataPanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class AccountPage extends TemplatePage
{
	/**
	 * Constructor
	 */
	public AccountPage()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        String userId = "20";
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
		add(new PageableTablePanel("datalist",entity,mapList));
		
		
		//for the side bar
		CheckGroup group= new CheckGroup("group",new ArrayList());
		add(group);
		final Map<String, Pair<String, String>> numberMap = DAOImpl.getNumberOfTypeOfAccount(userId);
		List<String> ids = Lists.newArrayList(numberMap.keySet());
		
		ListView<String> listview = new ListView<String>("rowRepeater",ids) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String key = item.getModelObject();
                String value = numberMap.get(key).getKey();
                String num = numberMap.get(key).getValue();
                item.add(new CheckBox("type", Model.of(Boolean.TRUE)));
                item.add(new Label("name", new Model(value)));
                item.add(new Label("num", new Model(num)));
            }
		   

		};
		
		group.add(listview);
	    //  CheckBoxMultipleChoice<String> siteChoice = new CheckBoxMultipleChoice<String>("sites",SITES);
		
		
		
	}
}