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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
    
    public AccountPage(Map<String,Boolean> map){
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        String userId = "20";
        List mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        add(new PageableTablePanel("datalist",entity,mapList));
        
        
        //for the side bar
        final Map<String,IModel> models = Maps.newHashMap();
        for(String k:map.keySet()){
            models.put(k, Model.of(map.get(k)));
        }
        Form form = new Form("side_bar_form"){
            protected void onSubmit() {
                System.out.println("on submitted");
              for(String key:models.keySet()){
                  System.out.println(key+":"+(boolean)models.get(key).getObject());
              }
            }
        };
        add(form);
        CheckGroup group= new CheckGroup("group",new ArrayList());
        form.add(group);
        final Map<String, Pair<String, String>> numberMap = DAOImpl.getNumberOfTypeOfAccount(userId);
        List<String> ids = Lists.newArrayList(numberMap.keySet());
        
        ListView<String> listview = new ListView<String>("rowRepeater",ids) {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<String> item) {
                final String key = item.getModelObject();
                String value = numberMap.get(key).getKey();
                String num = numberMap.get(key).getValue();
                item.add(new CheckBox("type", models.get(key)){
                    
                    
                    @Override
                    protected void onSelectionChanged(Boolean newSelection) {
                        super.onSelectionChanged(newSelection);
                        Map<String,Boolean> map =  Maps.newHashMap();
                        for(String s:models.keySet()){
                            //System.out.println(s+":"+models.get(s));
                            map.put(s, (boolean)models.get(s).getObject());
                        }
                        
                        setResponsePage(new AccountPage(map));
                    }

                    @Override
                    protected boolean wantOnSelectionChangedNotifications() {
                        return true;
                    }
                    
                });
                item.add(new Label("name", new Model(value)));
                item.add(new Label("num", new Model(num)));
            }
           

        };
        
        group.add(listview);
        
    }
    
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
		final Map<String,IModel> models = Maps.newHashMap();
		Form form = new Form("side_bar_form"){
		    protected void onSubmit() {
		        System.out.println("on submitted");
		      for(String key:models.keySet()){
		          System.out.println(key+":"+(boolean)models.get(key).getObject());
		      }
		    }
		};
		add(form);
		CheckGroup group= new CheckGroup("group",new ArrayList());
		form.add(group);
		final Map<String, Pair<String, String>> numberMap = DAOImpl.getNumberOfTypeOfAccount(userId);
		List<String> ids = Lists.newArrayList(numberMap.keySet());
		
        ListView<String> listview = new ListView<String>("rowRepeater",ids) {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<String> item) {
                final String key = item.getModelObject();
                String value = numberMap.get(key).getKey();
                String num = numberMap.get(key).getValue();
                IModel check = Model.of(Boolean.TRUE);
                models.put(key, check);
                item.add(new CheckBox("type", check){
                    
                    
                    @Override
                    protected void onSelectionChanged(Boolean newSelection) {
                        super.onSelectionChanged(newSelection);
                        Map<String,Boolean> map =  Maps.newHashMap();
                        for(String s:models.keySet()){
                            //System.out.println(s+":"+models.get(s));
                            map.put(s, (boolean)models.get(s).getObject());
                        }
                        
                        setResponsePage(new AccountPage(map));
                    }

                    @Override
                    protected boolean wantOnSelectionChangedNotifications() {
                        return true;
                    }
                    
                });
                item.add(new Label("name", new Model(value)));
                item.add(new Label("num", new Model(num)));
            }
		   

		};
		
		group.add(listview);
		
	}
}