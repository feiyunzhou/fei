package com.rex.crm.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.PageFactory;
import com.rex.crm.SelectEntryPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.beans.Choice;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import com.rex.crm.util.SMTPAuthenticator;
import com.sun.mail.smtp.SMTPTransport;

public class NewDataFormPanel extends Panel {

    private static final Logger logger = Logger
            .getLogger(NewDataFormPanel.class);
    private Properties server = new Properties();
    private String email = "";
    private int port = 25;
    private Map<String, List<Field>> addFieldGroupMap = Maps.newHashMap();
    private static int NUM_OF_COLUMN = 1;
    final String user = ((SignIn2Session) getSession()).getUser();

    public NewDataFormPanel(String id, final Entity entity, final Map<String, String> relationIds) {
        super(id);
        final Map<String, IModel> models = Maps.newHashMap();
//		final Map<String, IModel> fieldNameToModel = Maps.newHashMap();
        final String userId = ((SignIn2Session) getSession()).getUserId();
        List<Field> fields = entity.getFields();
        // List<String> fn = schema.getFieldNames();
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
                    Field currentField = visibleFields.get(i * NUM_OF_COLUMN + j / 2);
                    if (currentField.getPicklist() != null) {

                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            List<Choice> pickList = DAOImpl
                                    .queryPickList(currentField.getPicklist());
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
                    } else if (currentField.getRelationTable() != null) {
                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            List<Choice> pickList = DAOImpl.queryRelationDataList(currentField.getRelationTable(), userId);
                            Map<Long, String> list = Maps.newHashMap();
                            List<Long> ids = Lists.newArrayList();
                            for (Choice p : pickList) {
                                list.put(p.getId(), p.getVal());
                                ids.add(p.getId());
                            }
                            long foreignKey = -1L;
                            if (relationIds != null && relationIds.containsKey(currentField.getAlias())) {
                                foreignKey = Long.parseLong(relationIds.get(currentField.getAlias()));
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

                            columnitem.add(new RelationTableSearchFragment("celldatafield", "relationTableSearchFragment", this, currentField.getRelationTable(), "", choiceModel));
                        }
                    } else {
                        if (j % 2 == 0) {
                            columnitem.add(new TextFragment("celldatafield", "textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style", new Model("font-weight:bold;"), ";")));
                            columnitem.add(new AttributeAppender("class", new Model("tag"), " "));
                        } else {
                            if (currentField.getName().equals("address")) {
                                IModel<String> textModel = new Model<String>("");
                                models.put(currentField.getName(), textModel);
                                columnitem.add(new Textarea("celldatafield", "textAreaFragment", this, textModel));
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
                for (String key : models.keySet()) {
                    fieldNames.add(key);
                    System.out.println(fieldNames);
                    // models.get(key).getObject()
                    if (models.get(key).getObject() instanceof String) {
                        values.add("'" + (String) models.get(key).getObject()
                                + "'");
                    } else {
                        values.add(String.valueOf(models.get(key).getObject()));
                    }
                }

                //if entity is crmuser  add loginName
                if ("crmuser".equals(entity.getName())) {
                    String random = "";
                    random = DAOImpl.createNewCrmUser(entity.getName(), fieldNames, values, userId);
                    if (!"".equals(random)) {
                        //此时需发送邮件
                        String crmUserCode = String.valueOf(models.get("loginName").getObject());
                        String sendEmail = String.valueOf(models.get("email").getObject());
                        //创建激活码 getUserByuserCode
                        //传递邮箱地址，用户code.
                        sendMail(crmUserCode, sendEmail);
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
        };
        form.add(fieldGroupRepeater);
        add(form);

        // form.add(new AjaxSubmitLink("save") {
        // @Override
        // protected void onSubmit(AjaxRequestTarget target, Form form) {
        // System.out.println("form" + form);
        // for (IModel m : models) {
        // System.out.println("value:" + m.getObject());
        // }
        //
        // }
        // });
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
    }

    private class Textarea extends Fragment {

        public Textarea(String id, String markupId, MarkupContainer markupProvider, IModel model) {
            super(id, markupId, markupProvider);
            // TODO Auto-generated constructor stub
            add(new TextArea<String>("address", model));
        }
    }

    private class RelationTableSearchFragment extends Fragment {

        public RelationTableSearchFragment(String id, String markupId,
                MarkupContainer markupProvider, final String entityName, final String value, IModel model) {
            super(id, markupId, markupProvider);

            PageParameters params = new PageParameters();
            params.set("en", entityName);
            params.set("target", (long) model.getObject());
            PopupSettings popupSettings = new PopupSettings("查找").setHeight(470)
                    .setWidth(850).setLeft(150).setTop(200);
            add(new BookmarkablePageLink<Void>("search_btn", SelectEntryPage.class, params).setPopupSettings(popupSettings));
            HiddenField<?> hidden = new HiddenField<String>("selected_id_hidden", model);
            hidden.add(new AttributeAppender("id", entityName + "_id"));
            add(hidden);
            TextField<String> text = new TextField<String>("selected_value_input", new Model(value));
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
                text.add(new AttributeAppender("required", new Model("required"), ";"));
            }
            add(text);
            text.add(new AttributeAppender("type", new Model(currentField.getDataType()), ";"));
            text.add(new AttributeAppender("id", new Model(currentField.getName()), ";"));
        }
    }
    //发送有邮件方法

    public void sendMail(String getUserByLoginName, String sendEmail) {
        Session sendMailSession = null;
        SMTPTransport transport = null;
        StringBuilder emailContent = new StringBuilder("请点击连接激活用户:");
        Properties systemPeroperties = new Properties();
        try {
            systemPeroperties.load(NewDataFormPanel.class.getResourceAsStream("/system.properties"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        emailContent.append(systemPeroperties.getProperty("http"));
        emailContent.append(systemPeroperties.getProperty("url"));
        emailContent.append("/");
        emailContent.append(systemPeroperties.getProperty("project"));
        emailContent.append("/");
        emailContent.append(systemPeroperties.getProperty("jumpage"));
        emailContent.append("?");
        emailContent.append(systemPeroperties.getProperty("parameter"));
        emailContent.append("=");
        emailContent.append(getUserByLoginName);
        logger.debug(emailContent);
        Properties props = new Properties();
        // 与服务器建立Session的参数设置
        props.put("mail.smtp.host", "smtp.163.com"); // 写上你的SMTP服务器。
        props.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证。
        SMTPAuthenticator auth = new SMTPAuthenticator("accpcui@163.com", "992041099"); // 用户名，密码。
        sendMailSession = Session.getInstance(props, auth); // 建立连接。
        // SMTPTransport用来发送邮件。
        try {
            transport = (SMTPTransport) sendMailSession.getTransport("smtp");
            transport.connect();
            // 创建邮件。
            Message newMessage = new MimeMessage(sendMailSession);
            newMessage.setFrom(new InternetAddress("accpcui@163.com"));
            newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail));
            newMessage.setSubject("用户激活！");
            newMessage.setSentDate(new Date());
            newMessage.setText(emailContent.toString());
            Transport.send(newMessage);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
