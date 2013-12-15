package com.rex.crm.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.beans.AdvancedSearchFilter;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.advancedSearch.AdvancedSearchPage;
import com.rex.crm.common.advancedSearch.AdvancedSearchPanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;
import com.rex.crm.SelectEntryPage;
import com.rex.crm.SignIn2Session;

/**
 * @author Feiyun Zhou
 */
public class MergePage extends AdminTemplatePage {
    private static final Logger logger = Logger.getLogger(MergePage.class);
    private String search_target = "";
    private Map<String,IModel<Choice>> fieldName_model =  Maps.newHashMap();
    /**
     * Constructor
     */
    public MergePage() {
        // PageParameters pp = new PageParameters();
        // IRequestParameters params =
        // this.getRequestCycle().getRequest().getRequestParameters();
        initPage("contact", "4", "9");
    }

    private void initPage(final String entityName, final String entityId_A, final String entityId_B) {

        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        final Entity entity = Configuration.getEntityByName(entityName);

        final Map datalistA = DAOImpl.queryEntityById(entity.getSql_ent(), entityId_A);
        final Map datalistB = DAOImpl.queryEntityById(entity.getSql_ent(), entityId_B);

        Form form = new Form("form"){

            @Override
            protected void onSubmit() {
                List<String> names = Lists.newArrayList();
                List<String> values = Lists.newArrayList();
                logger.debug("fieldName_model:::"+fieldName_model);
                for(String fieldName: fieldName_model.keySet()){
                    IModel<Choice> model = fieldName_model.get(fieldName);
                    Choice choice = model.getObject();
                    if (choice != null) {
                        names.add(fieldName);

                        Field field = entity.getFieldByName(fieldName);

                        if (field.getPicklist() != null) {
                             values.add(choice.getKey());
                        } else if(field.getDataType().equalsIgnoreCase("number")) {
                            values.add(choice.getKey());
                        }else{
                            values.add("'"+ choice.getKey() + "'"); 
                        }
                    }
                    
                }
                logger.debug("names:"+names);
                logger.debug("values:"+values);
                DAOImpl.updateRecord(entityId_B, entity.getName(), names, values);
            }
            
        };
        add(form);
        
        
        RepeatingView row_repeater = new RepeatingView("row_repeater");
        form.add(row_repeater);
        
        for (Field field : entity.getFields()) {
            if(field.isPrimaryKey()) continue;
            AbstractItem item = new AbstractItem(row_repeater.newChildId());
            row_repeater.add(item);
            String field_name = field.getDisplay();
            item.add(new Label("field_name", field_name));
            String value_a = getFieldDisplayValueFromMap(datalistA, field);
            String value_b = getFieldDisplayValueFromMap(datalistB, field);  
            String raw_value_a = getFieldRawValueFromMap(datalistA,field);
            String raw_value_b = getFieldRawValueFromMap(datalistB,field);
            
            //add field_value_a
            item.add(new Label("field_value_a",value_a));
            
            //add field_value_b
            item.add(new Label("field_value_b",value_b));
            
            if ((!value_a.isEmpty() && !value_a.isEmpty())&&(!value_a.equalsIgnoreCase(value_b)) ) {
                
                if (field.getFieldType() != null && field.getFieldType().equalsIgnoreCase("auto")) {

                    item.add(new Label("value_picker", value_b));
                } else {
                    // add the picker
                    List<Choice> choices = Lists.newArrayList();
                    Choice choice = new Choice();
                    choice.setKey(raw_value_a);
                    choice.setVal(value_a);
                    choices.add(choice);

                    choice = new Choice();
                    choice.setKey(raw_value_b);
                    choice.setVal(value_b);
                    choices.add(choice);

                    IModel<Choice> model = new Model<Choice>();
                    fieldName_model.put(field.getName(), model);
                    IModel chocies_model = Model.ofList(choices);
                    item.add(new DropDownChoiceFragment("value_picker", "dropDownFragment", this, chocies_model, model));
                }
            } else {
                item.add(new WebMarkupContainer("value_picker"));
            }
         
        }

    }
    
    private class DropDownChoiceFragment extends Fragment {
        public DropDownChoiceFragment(String id, String markupId, MarkupContainer markupProvider, IModel choices,IModel default_model) {
            super(id, markupId, markupProvider);
            DropDownChoice dropDownInput = createDropDownListFromPickList("dropDownInput",choices,default_model);
            add(dropDownInput);
            
        }
        
    }
    
    private DropDownChoice createDropDownListFromPickList(String markupId,IModel choices,IModel default_model) {
        
        DropDownChoice<Choice> choice = new DropDownChoice<Choice>(markupId, default_model, choices,
                new IChoiceRenderer<Choice>() {
            @Override
            public Object getDisplayValue(Choice choice) {
                // TODO Auto-generated method stub
                return choice.getVal();
            }
            @Override
            public String getIdValue(Choice choice, int index) {
                // TODO Auto-generated method stub
                return choice.getKey();
            }
            
            
        });
        
        choice.setNullValid(true);
        return choice;
    }
    
    
    private class RelationTableSearchFragment extends Fragment {
        public RelationTableSearchFragment(String id, String markupId, MarkupContainer markupProvider, final String entityName, String excludeEntityName ,final String value, IModel model, final String eid) {
            super(id, markupId, markupProvider);

            PageParameters params = new PageParameters();
            params.set("en", entityName);
            params.set("excludeName", excludeEntityName);
            params.set("target", (long) model.getObject());
            params.set("eid", eid);

            PopupSettings popupSettings = new PopupSettings("查找");
            add(new BookmarkablePageLink<Void>("search_btn", SelectEntryPage.class, params).setPopupSettings(popupSettings));
            HiddenField<?> hidden = new HiddenField<String>("selected_id_hidden", model);
            hidden.add(new AttributeAppender("id", entityName + "_id"));
            add(hidden);
            TextField<String> text = new TextField<String>("selected_value_input", new Model(value));
            text.add(new AttributeAppender("id", entityName + "_name"));
            add(text);
        }
    }
    
    private String getFieldRawValueFromMap(Map data, Field field){
        Object rawvalue = data.get(field.getName());
        rawvalue = (rawvalue == null) ? "" : rawvalue;
        String value = CRMUtility.formatValue(field.getFormatter(), String.valueOf(rawvalue));
        value = (value == null) ? "" : value;
        
        return value;
    }
    private String getFieldDisplayValueFromMap(Map data, Field field) {
        String value = null;

        if (field.getPicklist() != null) {

            value = CRMUtility.formatValue(field.getFormatter(), DAOImpl.queryPickListByIdCached(field.getPicklist(), String.valueOf(data.get(field.getName()))));
            value = (value == null) ? "" : value;

        } else if (field.getRelationTable() != null) {
            value = CRMUtility.formatValue(field.getFormatter(), DAOImpl.queryCachedRelationDataById(field.getRelationTable(), String.valueOf(data.get(field.getName()))));
            value = (value == null) ? "" : value;

        } else {
            Object rawvalue = data.get(field.getName());
            rawvalue = (rawvalue == null) ? "" : rawvalue;
            value = CRMUtility.formatValue(field.getFormatter(), String.valueOf(rawvalue));
            value = (value == null) ? "" : value;

        }

        return value;

    }

}