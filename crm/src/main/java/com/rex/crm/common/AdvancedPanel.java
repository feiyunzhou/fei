package com.rex.crm.common;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Maps;


public class AdvancedPanel extends Panel {
    
	private static final long serialVersionUID = 2501105233172820074L;
    private static final Logger logger = Logger.getLogger(PageableTablePanel.class);
    
    public AdvancedPanel(String id,Entity entity) {
		super(id);
		// TODO Auto-generated constructor stub
		
		RepeatingView dataRowRepeater = new RepeatingView("datarowrepeater"); 
		add(dataRowRepeater);
		
		List<Field> fields = entity.getSearchableFields();
		List<String> fieldNames = new ArrayList<String>();
		Map<Long, String> map = Maps.newHashMap();
		for(Field field :fields){
			String name = field.getDisplay();
			fieldNames.add(name);
			Long i = (long) 0;
			map.put(i++ , field.getName());
		}
		
		for(String name :fieldNames ){
			AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
			dataRowRepeater.add(item);
			RepeatingView dataColumeRepeater = new RepeatingView("datacolumnrepeater");
			item.add(dataColumeRepeater);
			AbstractItem columename = new AbstractItem(dataColumeRepeater.newChildId());
			IModel choiceModel = new Model(name);
			 
			columename.add(new DropDownChoiceFragment("columnName", "dropDownFragment", this, map, choiceModel));
			dataColumeRepeater.add(columename);
   }
		
		 Form form = new Form("searchform"){
				@Override
				protected void onSubmit(){
					
				}
			};
			form.add(dataRowRepeater);
			add(form);
		}

private class DropDownChoiceFragment extends Fragment {
    public DropDownChoiceFragment(String id, String markupId, MarkupContainer markupProvider,  final Map<Long,String> list, IModel model) {
        super(id, markupId, markupProvider);
        DropDownChoice dropDown = (new DropDownChoice<Long>("dropDownInput", model,  new IChoiceRenderer<Long>() {
            @Override
            public Object getDisplayValue(Long id) {
                // TODO Auto-generated method stub
                return list.get(id);
            }

            @Override
            public String getIdValue(Long id, int index) {
                return String.valueOf(id);
            }
        }));
        add(dropDown);
    }
    }
}
