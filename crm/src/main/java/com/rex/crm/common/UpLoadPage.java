package com.rex.crm.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.DataPage;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rexen.crm.integration.DataExportDelegate;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class UpLoadPage extends DataPage{
    public UpLoadPage(){
       init();
    }

	public void init()
	{
		Map<String, Entity> entities = Configuration.getEntityTable();
        final Entity entity = entities.get("upLoad");
//        setPageTitle(entity.getDisplay());
        //List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
        //TODO get userId from request's session
        
        ICRUDActionListener actionListener = new DefaultCRUDActionListener(){
          @Override
          public void downLoadBtn() throws Exception { 
            DataExportDelegate dataExport = new  DataExportDelegate();
//            String template = DAOImpl.selectTemplate();
            String teample = "Account Full Import Template 1.0";
           HttpServletResponse response = (HttpServletResponse)getRequestCycle().getResponse().getContainerResponse();
           dataExport.export(teample, response);
            setResponsePage(new UpLoadPage());
          }    
      };
      final RadioGroup group = new RadioGroup("group", new Model());
        Form form = new Form("form"){

            @Override
            protected void onSubmit() {

                String sql =  entity.getSqlAdmin();
            }
        };
        add(form);
        form.add(group);
        List<Field> fields =  (List<Field>)entity.getFields();
        int i = 0;
       
        
        final Map<String, IModel> models = Maps.newHashMap();
//       final Field models = fields.get(i);
//        ListView<Field> target = new ListView<Field>("target",models)
//        {
//            @Override
//            protected void populateItem(ListItem item)
//            {
//                item.add(new Radio("radio", item.getModel()));
//                item.add(new Label("name",new PropertyModel<String>(item.getDefaultModel(), "name")));
//            }
//        };

   //     group.add(target);

        add(new FeedbackPanel("feedback"));
//    }
//        fields =  entity.getFields();
//            //new Radio("radio");
//            Model visibleModel = new Model();
//            List lsVisible = Arrays.asList(new String[]{"否", "是"});
//            RadioChoice<String> raVisible = new RadioChoice("celldatafield", visibleModel, lsVisible).setSuffix(" "); 
//            raVisible.setModelValue(new String[]{"0", "1"});
//            visibleModel.setObject("否");
//            models.put(currentField.getName(),visibleModel);
//            columnitem.add(raVisible);
//          }
        
	}
}