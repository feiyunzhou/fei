package com.rex.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.common.Entity;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class AccountPage extends TemplatePage
{
    
    public AccountPage(Map<String,Boolean> map){
        initPage(map);
    }
    
    private void initPage(Map<String,Boolean> filter){
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("account");
        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        String userId = "20";
        List mapList = null;
        if(filter == null){
            mapList = DAOImpl.queryEntityRelationList(entity.getSql(), userId);
        }else{
            
            List<String> ft = Lists.newArrayList();
            for (String k : filter.keySet()) {
                if(filter.get(k)) ft.add(k);
            }
            mapList = DAOImpl.queryEntityWithFilter(entity.getSql(), entity.getFilterField(), ft, userId);
                    
                    
        }
        add(new PageableTablePanel("datalist",entity,mapList));
        
        
        //for the side bar

        Form form = new Form("side_bar_form");
        add(form);
        CheckGroup group= new CheckGroup("group",new ArrayList());
        form.add(group);
        //query from DAO and prepare data for query locally
        final List<Pair<String, Map<String, Object>>> types = DAOImpl.getNumberOfTypeOfAccount(userId);
        List<String> ids = Lists.newArrayList();
        long total = 0;
        final Map<String,Map<String, Object>> numberMap =  Maps.newHashMap();
        for(Pair<String, Map<String, Object>> t:types){
            ids.add(t.getKey());
            numberMap.put(t.getKey(), t.getValue());
            total=total+java.math.BigDecimal.class.cast(t.getValue().get("sum")).longValue();
        }
        
        
        final Map<String,IModel> models = Maps.newHashMap();
        //prepare models for the checkbox
        
        if (filter == null) {
            for(Pair<String, Map<String, Object>> t:types){
                models.put(t.getKey(), Model.of(Boolean.TRUE));   
            }
        } else {
            for (String k : filter.keySet()) {
                models.put(k, Model.of(filter.get(k)));
            }
        }
        
        
        ListView<String> listview = new ListView<String>("rowRepeater",ids) {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<String> item) {
                final String key = item.getModelObject();
                String value = String.valueOf(numberMap.get(key).get("val"));
                String num = String.valueOf(numberMap.get(key).get("sum"));
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
        group.add(new Label("total",String.valueOf(total)));
    }
    
	/**
	 * Constructor
	 */
	public AccountPage()
	{
       initPage(null);
		
	}
}