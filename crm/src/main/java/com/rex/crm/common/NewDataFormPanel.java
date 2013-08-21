package com.rex.crm.common;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.PageFactory;
import com.rex.crm.SignIn2Session;
import com.rex.crm.beans.Choice;
import com.rex.crm.db.DAOImpl;

public class NewDataFormPanel extends Panel {
    private static final Logger logger = Logger.getLogger(NewDataFormPanel.class);

    public NewDataFormPanel(String id, final Entity entity,final Map<String,String> relationIds) {
        super(id);
        List<Field> fields = entity.getFields();
        RepeatingView repeater = new RepeatingView("repeater");
      
        final Map<String,IModel> models = Maps.newHashMap();
        final String userId = ((SignIn2Session)getSession()).getUserId();
        for (Field f : fields) {
            if (!f.isEditable())
                continue;
            AbstractItem item = new AbstractItem(repeater.newChildId());
            item.setOutputMarkupId(true);
            // AbstractItem item = new AbstractItem(repeater.newChildId());
            repeater.add(item);

            item.add(new Label("fieldlabel", f.getDisplay()));
            if (f.getPicklist() != null) {
                
                List<Choice> pickList = DAOImpl.queryPickList(f.getPicklist());
                Map<Long, String> list = Maps.newHashMap();
                List<Long> ids = Lists.newArrayList();
                for (Choice p : pickList) {
                    list.put(p.getId(), p.getVal());
                    ids.add(p.getId());
                }
                IModel choiceModel = new Model(1L);
                
                
                models.put(f.getName(),choiceModel);
                item.add(new DropDownChoiceFragment("inputField", "dropDownFragment", this, ids, list, choiceModel));
                
            } else if(f.getRelationTable() != null){
                
                
                List<Choice> pickList = DAOImpl.queryRelationDataList(f.getRelationTable(), userId);
                Map<Long, String> list = Maps.newHashMap();
                List<Long> ids = Lists.newArrayList();
                for (Choice p : pickList) {
                    list.put(p.getId(), p.getVal());
                    ids.add(p.getId());
                }
                long foreignKey = 1L;
                if(relationIds!=null && relationIds.containsKey(f.getAlias())){
                    foreignKey = Long.parseLong(relationIds.get(f.getAlias()));
                }
                IModel choiceModel = new Model(foreignKey);
                
                String fn = "";
                if(f.getAlias()!=null){
                    fn = f.getAlias();
                }else{
                    fn = f.getName();
                }
                
                models.put(fn,choiceModel);
                item.add(new DropDownChoiceFragment("inputField", "dropDownFragment", this, ids, list, choiceModel));
                
            }else {
                
                IModel<String> textModel = new Model<String>("");
                models.put(f.getName(),textModel);
                
                item.add(new TextInputFragment("inputField", "textInputFragment", this, textModel));
                
                
             
            }
        }

        
        Form form = new Form("form"){
            @Override
            protected void onSubmit()
            {
                logger.debug("the form was submitted!");
                logger.debug(models);
                List<String> fieldNames = Lists.newArrayList();
                List<String> values = Lists.newArrayList();
                for(String key: models.keySet()){
                    fieldNames.add(key);
                    //models.get(key).getObject()
                    if(models.get(key).getObject() instanceof String){
                        values.add("'"+(String)models.get(key).getObject()+"'");
                    }else{
                        values.add(String.valueOf(models.get(key).getObject()));
                    }
                    
                }
                long generatedId = DAOImpl.createNewRecord(entity.getName(), fieldNames, values);
                if(generatedId>0){
                    logger.debug("generateId is:"+generatedId);
                 DAOImpl.insert2UserRelationTable(entity.getName(), userId, String.valueOf(generatedId));
                }
                setResponsePage(PageFactory.createPage(entity.getName()));
            }
        };
        form.add(repeater);
        add(form);
        
//        form.add(new AjaxSubmitLink("save") {
//            @Override
//            protected void onSubmit(AjaxRequestTarget target, Form form) {
//                System.out.println("form" + form);
//                for (IModel m : models) {
//                    System.out.println("value:" + m.getObject());
//                }
//
//            }
//        });
    }

    public NewDataFormPanel(String id, IModel<?> model) {
        super(id, model);
    }

    private class DropDownChoiceFragment extends Fragment {
        public DropDownChoiceFragment(String id, String markupId, MarkupContainer markupProvider, final List<Long> ids, final Map<Long, String> list, IModel model) {
            super(id, markupId, markupProvider);

            add(new DropDownChoice<Long>("dropDownInput", model, ids, new IChoiceRenderer<Long>() {

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
        }
    }

    private class TextInputFragment extends Fragment {
        public TextInputFragment(String id, String markupId, MarkupContainer markupProvider, IModel model) {
            super(id, markupId, markupProvider);

            add(new TextField<String>("input", model));
        }
    }

}
