package com.rex.crm.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.PageFactory;
import com.rex.crm.SelectEntryPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.Choice;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import com.rex.crm.util.SMTPAuthenticator;
import com.rex.crm.util.SendEmail;
import com.sun.mail.smtp.SMTPTransport;

public class NewDataFormPanel extends Panel {

    private static final Logger logger = Logger
            .getLogger(NewDataFormPanel.class);
    private Properties server = new Properties();
    private String email = "";
    private int port = 25;
    private Map<String, List<Field>> addFieldGroupMap = Maps.newHashMap();
    private static int NUM_OF_COLUMN = 1;
    private Map<String, IModel> parentModels = Maps.newHashMap();
    private Map<String, DropDownChoiceFragment> childDropDownComponent = Maps.newHashMap();
    final String user = ((SignIn2Session) getSession()).getUser();
    public NewDataFormPanel(String id, final Entity entity,final Map<String,Object> params) {
        super(id);
        //add prompt 
        final RepeatingView div = new RepeatingView("promptDiv");
        final AbstractItem divitem = new AbstractItem(div.newChildId());
        final Label promptButton = new Label("promptButton","X");
        divitem.add(promptButton);
        final Label promptLabel = new Label("prompt","红色输入框是必填字段不能为空，请输入!");
        divitem.add(promptLabel);
        div.add(new AttributeAppender("style",new Model("display:none"),";"));
        divitem.add(new AttributeAppender("style",new Model("display:none"),";"));
        div.add(divitem);
        add(div);	
        final Map<String, IModel> models = Maps.newHashMap();
        final String userId = ((SignIn2Session) getSession()).getUserId();
        List<Field> fields = entity.getFields();
        
        for (Field f : fields) {
            if (addFieldGroupMap.get(f.getFieldGroup()) != null) {
                addFieldGroupMap.get(f.getFieldGroup()).add(f);
            } else {
                List<Field> fs = Lists.newArrayList();
                fs.add(f);
                addFieldGroupMap.put(f.getFieldGroup(), fs);
            }

        }
        List<String> groupNames = Configuration.getSortedFieldGroupNames();
        RepeatingView fieldGroupRepeater = new RepeatingView("fieldGroupRepeater");
        add(fieldGroupRepeater);
        for (String gn : groupNames) {
            List<Field> groupfields = addFieldGroupMap.get(gn);
            if (groupfields == null) {
                continue;
            }
            RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
            AbstractItem groupitem = new AbstractItem(fieldGroupRepeater.newChildId());
            groupitem.setOutputMarkupId(true);
            fieldGroupRepeater.add(groupitem);
            groupitem.add(dataRowRepeater);
            int numOfField = 0;
            List<Field> visibleFields = Lists.newArrayList();
            for (Field f : groupfields) {
                if (!f.isVisible()) {
                    continue;
                }
                numOfField++;
                visibleFields.add(f);
            }
            groupitem.add(new Label("groupname", gn));
            int num_of_row = (numOfField / NUM_OF_COLUMN) + 1;
            for (int i = 0; i < num_of_row; i++) {
                AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
                dataRowRepeater.add(item);
                RepeatingView columnRepeater = new RepeatingView("columnRepeater");
                item.add(columnRepeater);
                for (int j = 0; j < 2 * NUM_OF_COLUMN; j++) {
                    AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model());
                    if ((i * NUM_OF_COLUMN + j / 2) >= visibleFields.size()) {
                        if ((i * NUM_OF_COLUMN + j / 2) >= visibleFields.size()) {
                            continue;
                        }
                        columnitem.add(new LayoutFragment("celldatafield", "layoutFragment", this, " ").setEscapeModelStrings(false));
                        columnRepeater.add(columnitem);

                        continue;
                    }
                   final Field currentField = visibleFields.get(i * NUM_OF_COLUMN + j / 2);
                    if (currentField.getPicklist() != null) {

                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            
                            long default_key = 1L;
                            if (currentField.getDefault_value_type()!=null && currentField.getDefault_value_type().equalsIgnoreCase("var")){
                                if (params != null) {
                                    
                                    String choiceId = currentField.getDefault_value();
                                    System.out.println("choiceId::::::"+choiceId);
                                    if(choiceId!=null && choiceId!=null && params.get(choiceId.trim())!=null){
                                        default_key = Long.parseLong(String.valueOf(params.get(choiceId.trim())));  
                                        System.out.println("default_key::::::"+default_key);
                                    }
                                 
                                }
                            }
                            
                            
                            if (currentField.getParentNode() != null || currentField.getChildNode() != null) {
                                 IModel<Choice> selected_model =  new Model(new Choice(default_key,""));
                                IModel<List<? extends Choice>> choices_models = null;
                                
                                if(currentField.getChildNode()!=null && currentField.getParentNode() == null ){
                                    choices_models = Model.ofList(DAOImpl.queryPickList(currentField.getPicklist()));
                                  }
                                
                                if(currentField.getParentNode()!=null){
                                    choices_models = new AbstractReadOnlyModel<List<? extends Choice>>(){
                                        List<Choice> choices;

                                        @Override
                                        public List<? extends Choice> getObject() {
                                            IModel pm = parentModels.get(currentField.getParentNode());
                                            if(pm!=null){
                                              choices =  DAOImpl.queryPickListByFilter(currentField.getPicklist(), "parentId",String.valueOf(((Choice)pm.getObject()).getId()));
                                             
                                            }else{
                                                choices = Lists.newArrayList();
                                            }
                                            return choices;
                                        }
                                        
                                        @Override
                                        public void detach() {
                                            choices = null;
                                            //System.out.println("detached");
                                        }
   
                                      };
                                }
                                
                                
                                DropDownChoiceFragment  selector = new DropDownChoiceFragment("celldatafield", "dropDownFragment", this, choices_models, selected_model,entity,currentField);
                                columnitem.add(selector);
                                selector.setOutputMarkupId(true);
                               
                                if(currentField.getParentNode()!=null){
                                   
                                   childDropDownComponent.put(currentField.getName(),selector);
                                }
                                
                                models.put(currentField.getName(), selected_model);
                                
                                columnitem.add(selector);
                                
                               

                            } else {

                                List<Choice> pickList = DAOImpl.queryPickList(currentField.getPicklist());
                                Map<Long, String> list = Maps.newHashMap();
                                List<Long> ids = Lists.newArrayList();
                                for (Choice p : pickList) {
                                    list.put(p.getId(), p.getVal());
                                    ids.add(p.getId());
                                }
                                IModel choiceModel = new Model(1L);
                                models.put(currentField.getName(), choiceModel);
                                columnitem.add(new DropDownChoiceFragment("celldatafield", "dropDownFragment", this, ids, list, choiceModel));
                            }

                        }
                    } else if (currentField.getRelationTable() != null) {
                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
//                            List<Choice> pickLista = DAOImpl.queryRelationDataList(currentField.getRelationTable(), userId);
//                            Map<Long, String> list = Maps.newHashMap();
//                            List<Long> idsa = Lists.newArrayList();
//                            for (Choice p : pickList) {
//                                list.put(p.getId(), p.getVal());
//                                ids.add(p.getId());
//                            }
                            long foreignKey = -1L;
                            String defaultValue = "";
                            if (currentField.getDefault_value_type()!=null && currentField.getDefault_value_type().equalsIgnoreCase("var")){
                                if (params != null) {
                                    Iterable<String> splits = Splitter.on(",").split(currentField.getDefault_value());
                                    Iterator<String> it = splits.iterator();
                                    String choiceId = it.next();
                                    String choiceValue = it.next();
                                    if(choiceId!=null && choiceId!=null && params.get(choiceId.trim())!=null && params.get(choiceValue.trim())!=null){
                                       foreignKey = Long.parseLong(String.valueOf(params.get(choiceId.trim())));
                                       defaultValue = String.valueOf(params.get(choiceValue.trim()));
                                    }
                                 
                                }
                            }
                           
                            IModel choiceModel = new Model(foreignKey);
                            String fn = "";
                            if (currentField.getAlias() != null) {
                                fn = currentField.getAlias();
                            } else {
                                fn = currentField.getName();
                            }
                            models.put(fn, choiceModel);
                            //columnitem.add(new DropDownChoiceFragment("celldatafield", "dropDownFragment", this,ids, list, choiceModel));

                            columnitem.add(new RelationTableSearchFragment("celldatafield", "relationTableSearchFragment", this, currentField.getRelationTable(), defaultValue, choiceModel));
                        }
                    } else {
                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            if (currentField.getName().equals("address")) {
                                IModel<String> textModel = new Model<String>("");
                                models.put(currentField.getName(), textModel);
                                columnitem.add(new TextareaFrag("celldatafield", "textAreaFragment", this, textModel));
                            } else {
                                IModel<String> textModel = new Model<String>("");
                                models.put(currentField.getName(), textModel);
                                columnitem.add(new TextInputFragment("celldatafield", "textInputFragment", this, textModel, currentField));
                            }
                        }
                    }
                    columnRepeater.add(columnitem);

                }
            }
        }


        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.debug("the form was submitted!");
                logger.debug(models);
                List<String> fieldNames = Lists.newArrayList();
                List<String> values = Lists.newArrayList();
                //创建一个标识，判断非空验证是否通过
                boolean flag = true;
                //判断属性设置
                for (String key : models.keySet()) {
                    fieldNames.add(key);
                    Field field = entity.getFieldByName(key);
                    logger.debug("currentField:"+field);
                    //判断filed是否能为空，若为空则给出提示，不执行保存事件，若不为空在执行保存事件
                    if(field.isRequired()){
                    	if(null==(String) models.get(key).getObject()){
                    		flag = false;
                    		logger.debug("value:"+(String) models.get(key).getObject());
                    		//如果为空写出提示信息
                    		logger.debug("fieldName:"+field.getName()+"不能为空");
                    		div.add(new AttributeAppender("style",new Model("display:block"),";"));
                    		divitem.add(new AttributeAppender("style",new Model("display:block"),";"));
                    		promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
                    		promptButton.add(new AttributeAppender("style",new Model("display:block"),";"));
                    	}else{
                    		flag = true;
                    	}
                	}
                    System.out.println(fieldNames);
                    if (models.get(key).getObject() instanceof String) {
                        values.add("'" + (String) models.get(key).getObject()
                                + "'");
                    }else if(models.get(key).getObject() instanceof Choice){
                        values.add(String.valueOf(((Choice) models.get(key).getObject()).getId()));
                    }else {
                        values.add(String.valueOf(models.get(key).getObject()));
                    }
                }
                if(flag){
            		//if entity is crmuser  add loginName
                    if ("crmuser".equals(entity.getName())) {
                        long crmuserkey = -1;
                        crmuserkey= DAOImpl.createNewCrmUser(entity.getName(), fieldNames, values, userId);
                        if (crmuserkey >0 ) {
                        	CRMUser crmuser = DAOImpl.getCrmUserById((int)crmuserkey);
                            //此时需发送邮件
                            long crmUserCode = crmuser.getTs();
                            String sendEmail = String.valueOf(models.get("email").getObject());
                            //创建激活码 getUserByuserCode
                            //传递邮箱地址，用户code.
                            SendEmail.sendMail(String.valueOf(crmUserCode) + "_"+ crmuser.getId(), sendEmail);
                            
                        }
                    } else {
                        long generatedId = DAOImpl.createNewRecord(entity.getName(), fieldNames, values, userId);
                        if (generatedId > 0) {
                            DAOImpl.insert2UserRelationTable(entity.getName(), userId,
                                    String.valueOf(generatedId));
                        }
                    }
                    setResponsePage(PageFactory.createPage(entity.getName()));
                }
            }
        };
        form.add(fieldGroupRepeater);
        add(form);
    }

    public NewDataFormPanel(String id, IModel<?> model) {
        super(id, model);
    }

    private class TextFragment extends Fragment {

        public TextFragment(String id, String markupId,
                MarkupContainer markupProvider, String label) {
            super(id, markupId, markupProvider);
            add(new Label("celldata", label));
            // TODO Auto-generated constructor stub
        }
    }

    private class LayoutFragment extends Fragment {

        public LayoutFragment(String id, String markupId,
                MarkupContainer markupProvider, String label) {
            super(id, markupId, markupProvider);
            add(new Label("add", label));
            // TODO Auto-generated constructor stub
        }
    }

    private class DropDownChoiceFragment extends Fragment {

        public DropDownChoiceFragment(String id, String markupId,
                MarkupContainer markupProvider, final List<Long> ids,
                final Map<Long, String> list, IModel model) {
            super(id, markupId, markupProvider);

            add(new DropDownChoice<Long>("dropDownInput", model, ids,
                    new IChoiceRenderer<Long>() {
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
        
        public DropDownChoiceFragment(String id, String markupId,MarkupContainer markupProvider,
                IModel choices,IModel default_model,final Entity entity, final Field currentField){
            super(id, markupId, markupProvider);
            DropDownChoice dropDown = createDropDownListFromPickList("dropDownInput",choices,default_model);
            add(dropDown);
            
            if(currentField.getChildNode()!=null){
                parentModels.put(currentField.getName(), default_model);
                //childDropDownComponent.put(field.get("child-pl"), null);
                
                dropDown.add(new AjaxFormComponentUpdatingBehavior("onchange"){

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        //System.out.println("TTTT:"+field.get("name"));
                        //System.out.println("childDropDownComponent:"+childDropDownComponent);
                        target.add(childDropDownComponent.get( currentField.getChildNode()));
                        //System.out.println("currentfield code:"+String.valueOf(((Choice)parentModels.get(field.get("name")).getObject()).getCode()));
                            //System.out.println("DDDD:"+table.get(field.get("childNode")).get("childNode"));
                            if( entity.getFieldByName(currentField.getChildNode()).getChildNode() != null ){
                              
                               parentModels.get(entity.getFieldByName(currentField.getChildNode()).getName()).setObject(new Choice(-1L,""));
                               target.add(childDropDownComponent.get(entity.getFieldByName(currentField.getChildNode()).getChildNode()));
                               // target.
                            }
                        
                    }
                    
                    
                   
                    
                 });
             }
            
        } 
        
    }


    private DropDownChoice createDropDownListFromPickList(String markupId,IModel choices,IModel default_model) {
        return new DropDownChoice<Choice>(markupId, default_model, choices,
                new IChoiceRenderer<Choice>() {
                    @Override
                    public Object getDisplayValue(Choice choice) {
                        // TODO Auto-generated method stub
                        return choice.getVal();
                    }
                    @Override
                    public String getIdValue(Choice choice, int index) {
                        // TODO Auto-generated method stub
                        return String.valueOf(choice.getId());
                    }
                    
                    
                });
    }
    private class TextareaFrag extends Fragment {

        public TextareaFrag(String id, String markupId, MarkupContainer markupProvider, IModel model) {
            super(id, markupId, markupProvider);
            // TODO Auto-generated constructor stub
            add(new TextArea<String>("address", model));
        }
    }

    private class RelationTableSearchFragment extends Fragment {

        public RelationTableSearchFragment(String id, String markupId,
                MarkupContainer markupProvider, final String entityName, final String defaultValue, final IModel defaultModel) {
            super(id, markupId, markupProvider);

            PageParameters params = new PageParameters();
            params.set("en", entityName);
            params.set("target", (Long)defaultModel.getObject());
            add(new BookmarkablePageLink<Void>("search_btn", SelectEntryPage.class, params));
            HiddenField<?> hidden = new HiddenField<String>("selected_id_hidden", defaultModel);
            hidden.add(new AttributeAppender("id", entityName + "_id"));
            add(hidden);
            TextField<String> text = new TextField<String>("selected_value_input", new Model(defaultValue));
            text.add(new AttributeAppender("id", entityName + "_name"));
            add(text);
        }
    }

    private class TextInputFragment extends Fragment {

        public TextInputFragment(String id, String markupId,
                MarkupContainer markupProvider, IModel model, Field currentField) {
            super(id, markupId, markupProvider);
            TextField<String> text = new TextField<String>("input", model);
            if (!currentField.isEditable()) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
                Date time = (Date) calendar.getTime();
                if (currentField.getName().equals("accountId")) {
                    add(text);
                } else {
                    if (currentField.getName().equals("modify_datetime")) {
                        String modify_datetime = dateformat.format(time);
                        text.add(new AttributeAppender("value", new Model(modify_datetime), ";"));
                    } else if (currentField.getName().equals("whenadded")) {
                        String whenadded = dateformat.format(time);
                        text.add(new AttributeAppender("value", new Model(whenadded), ";"));
                    } else {
                        text.add(new AttributeAppender("value", new Model(user), ";"));
                    }

                }
                text.add(new AttributeAppender("readonly", new Model("realonly"), ";"));
            }
            if (currentField.getDataType().equals("tel") || currentField.getName().equals("fax") || currentField.getName().equals("office_fax")) {
                text.add(new AttributeAppender("pattern", new Model("^((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)"), ";"));
            }
            if (currentField.isRequired()) {
                text.add(new AttributeAppender("style", new Model("border:1px solid red;"), ";"));
                //text.add(new AttributeAppender("required", new Model("required"), ";"));
            }
            add(text);
            text.add(new AttributeAppender("type", new Model(currentField.getDataType()), ";"));
            text.add(new AttributeAppender("id", new Model(currentField.getName()), ";"));
        }
    }
    

}
