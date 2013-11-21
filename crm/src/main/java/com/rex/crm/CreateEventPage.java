package com.rex.crm;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.Choice;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.common.Entity;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

/**
 * @author Feiyun Zhou
 */
public class CreateEventPage extends TemplatePage {

    private static final Logger logger = Logger.getLogger(CreateEventPage.class);
    public Date startDate;
    protected String endDate;
    protected String endTime;
    protected String startTime;
    protected String contact_select;
    protected String hidden_contact_select;
    protected String hidden_select_user;
    protected Choice visiting_purpose = new Choice(1L, "");
    protected Choice feature_product = new Choice(1L, "");
    protected Choice activity_type = new Choice(1L, "");
    protected Choice selected_event_type = new Choice(1L, "拜访");
    protected String act_title_input = "";
    protected Integer event_type = 1;
    protected String selected_user = "";
    protected String status = "计划中"; /*new Choice(1L,"计划中");*/
    protected String location = "";
    protected int total_score;
    protected Choice planing = new Choice(0L, "");
    protected Choice openling = new Choice(0L, "");
    protected Choice enquery_listening = new Choice(0L, "");
    protected Choice deliverable = new Choice(0L, "");
    protected Choice objection_handing = new Choice(0L, "");
    protected Choice summary = new Choice(0L, "");
    protected String owner = "";
    protected String whenadded = "";
    protected String crmuserId = "";
    /**
     * Constructor
     */
    public CreateEventPage() {
        initPage(0);
    }

    public CreateEventPage(int type) {
        initPage(type);
    }

    public void initPage(final int type) {
        Map<String, Entity> entities = Configuration.getEntityTable();
        Entity entity = entities.get("activity");
        setPageTitle(entity.getDisplay());
        final String posId = ((SignIn2Session) getSession()).getPositionId();
        final String user = ((SignIn2Session) getSession()).getUser();
        final String userId = ((SignIn2Session) getSession()).getUserId();
        final int roleId = ((SignIn2Session) getSession()).getRoleId();
        CRMUser crmUser = DAOImpl.getCRMUserInfoById(Integer.parseInt(posId));
        final String crmUserName = crmUser.getName();
        //add prompt 
        final RepeatingView div = new RepeatingView("promptDiv");
        final AbstractItem divitem = new AbstractItem(div.newChildId());
        final Label promptButton = new Label("promptButton","X");
        divitem.add(promptButton);
        final Label promptLabel = new Label("prompt","红色字体字段为必填项，请输入!");
        divitem.add(promptLabel);
        div.add(new AttributeAppender("style",new Model("display:none"),";"));
        divitem.add(new AttributeAppender("style",new Model("display:none"),";"));
        div.add(divitem);
        add(div);
        //set event type         
        RadioGroup event_type_group = new RadioGroup("event_type_group", new PropertyModel(this, "event_type"));
        final Radio callRadio = new Radio("radio1", new Model(1L));
        event_type_group.add(callRadio);
        final Radio coachingRadio = new Radio("radio2", new Model(2L));
        if (roleId == 3) {
            coachingRadio.add(new AttributeAppender("disabled", "true"));
        }
        final AttributeModifier redioModifier = new AttributeModifier("checked","checked");
        //辅导名称拼接字段
        final StringBuffer concahName = new StringBuffer();
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                logger.debug("this form was submitted!");
                String sd = getRequest().getPostParameters().getParameterValue("start_date").toString();
                String ed = getRequest().getPostParameters().getParameterValue("end_date").toString();
                String visit_type = String.valueOf(activity_type);
                logger.debug("opening:" + String.valueOf(openling.getId()));
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                String sdt = sd;
                String edt = ed;
                int contactId = 0;
                int coacheeId = 0;
                Date startdt = null;
                Date enddt = null;
                boolean flag = true;
                try {
                    startdt = dateformat.parse(sdt);
                    enddt = dateformat.parse(edt);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               
                if(event_type==1){
                	 if (null == hidden_contact_select) {
                        contactId = 0;
              			div.add(new AttributeAppender("style",new Model("display:block"),";"));
                 		divitem.add(new AttributeAppender("style",new Model("display:block"),";"));
                 		promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
                 		promptButton.add(new AttributeAppender("style",new Model("display:block"),";"));
                 		callRadio.add(redioModifier);
                 		coachingRadio.add(redioModifier.remove("checked"));
                 		flag = false;
                     } else {
                         contactId = Integer.parseInt(hidden_contact_select);
                     }
                }else{
                	if(null==hidden_select_user){
                    	coacheeId = 0;
                    	div.add(new AttributeAppender("style",new Model("display:block"),";"));
                		divitem.add(new AttributeAppender("style",new Model("display:block"),";"));
                		promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
                		promptButton.add(new AttributeAppender("style",new Model("display:block"),";"));
                		coachingRadio.add(redioModifier);
                		callRadio.add(redioModifier.remove("checked"));
                		flag = false;
                    }else{
                    	coacheeId = Integer.parseInt(hidden_select_user);
                    }
                }
                if(flag){
                	if (null == act_title_input) {
                        if (event_type == 1) {
                            concahName.append("拜访:");
                            //根据医生Id获取医生对象
                            act_title_input = concahName.append(DAOImpl.getContactById(contactId).getName()).toString();
                        } else {
                            concahName.append(crmUserName);
                            concahName.append(sd);
                            concahName.append("拜访辅导");
                            act_title_input = concahName.toString();
                            
                        }
                    }
                    try {
                        int crmuserId = 0;
                        String participants = user;
                        if (event_type == 1) {
                            //visiting
                            crmuserId = Integer.parseInt(posId);
                        } else if (event_type == 2) {
                            //coaching
                            crmuserId = Integer.parseInt(posId);;
                            participants = participants + ", " + selected_user;
                            contactId = -1;
                            concahName.append("拜访辅导");
                            visiting_purpose = new Choice(8L, "");
                        }
                        System.out.println("拜访类型："+String.valueOf(visiting_purpose.getId()));
                        //insert the event, and return the generated id of the event
                        long generatedkey = DAOImpl.addCalendarEventForCoach(crmuserId, contactId, String.valueOf(activity_type.getId()),
                                act_title_input, String.valueOf(startdt.getTime() / 1000),
                                String.valueOf(enddt.getTime() / 1000), 1, user, user, user,
                                String.valueOf(visiting_purpose.getId()),
                                String.valueOf(feature_product.getId()), event_type.intValue(), participants,
                                coacheeId, location, total_score, String.valueOf(planing.getId()), String.valueOf(openling.getId()), String.valueOf(enquery_listening.getId()), String.valueOf(deliverable.getId()), String.valueOf(objection_handing.getId()), String.valueOf(summary.getId()));
                        logger.debug("generatedkey:" + generatedkey);
                        if (generatedkey > 0) {
                            if (event_type == 1) {
                                //if it is a visiting, we only send this event to the owner;
                                DAOImpl.insert2UserRelationTable("activity",userId, posId ,String.valueOf(coacheeId),String.valueOf(generatedkey));
                            } else if (event_type == 2) {
                                //if it is a coaching, we need send this event to the manager and sales
                                //add new record for the manager
                                logger.debug("key:" + String.valueOf(generatedkey));
                                DAOImpl.insert2UserRelationTable("activity",userId, posId,String.valueOf(coacheeId), String.valueOf(generatedkey));
                                //add new record for the sales
                                DAOImpl.insert2UserRelationTable("activity", userId,hidden_select_user,String.valueOf(coacheeId), String.valueOf(generatedkey));
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (type == 0) {
                        setResponsePage(PageFactory.createPage("calendar"));
                    } else {
                        setResponsePage(new ActivityPage());
                    }
                }
                
            }
        };
        add(form);
        //辅导者
        TextField coach = new TextField("crmuserId", new PropertyModel(this, "crmuserId"));
        coach.add(new AttributeModifier("value", crmUser.getName()));
        form.add(coach);
        //创建人
        TextField owner = new TextField("owner", new PropertyModel(this, "owner"));
        owner.add(new AttributeModifier("value", crmUser.getName()));
        form.add(owner);
        //创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        TextField whenadded = new TextField("whenadded", new PropertyModel(this, "whenadded"));
        whenadded.add(new AttributeModifier("value", df.format(new Date())));
        form.add(whenadded);
        //状态默认为计划中
        TextField status = new TextField("status", new PropertyModel(this, "status"));
        form.add(status);
        //计划地点
        TextField location = new TextField("location", new PropertyModel(this, "location"));
        form.add(location);
        //分数
        TextField total_score = new TextField("total_score", new PropertyModel(this, "total_score"));
        form.add(total_score);
        StringValue startdateValue = this.getRequest().getRequestParameters().getParameterValue("startdate");

        String startdate = null;
        long current = System.currentTimeMillis();
        Date current_date_time = new Date(current);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //set default time to current time
        if (startdateValue != null && !startdateValue.isEmpty() && !startdateValue.isNull()) {
            startdate = startdateValue.toString();
        } else {
            startdate = dateformat.format(current_date_time);
        }
        SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
        WebMarkupContainer start_date_input = new WebMarkupContainer("start_date_input");
        form.add(start_date_input);
        start_date_input.add(new AttributeModifier("value", startdate.substring(0, 10) + "T" + timeformatter.format(current_date_time)));
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date sDate = current_date_time;
        try {
            sDate = dateformat2.parse(startdate + "T" + timeformatter.format(current_date_time));
        } catch (ParseException e) {
            logger.warn("failed to parse date:", e);
        }
        Date next_date_time = new Date(sDate.getTime() + 3600 * 1000);
        WebMarkupContainer end_date_input = new WebMarkupContainer("end_date_input");
        form.add(end_date_input);
        end_date_input.add(new AttributeModifier("value", dateformat.format(next_date_time)));
//        PopupSettings popupSettings = new PopupSettings("查找").setLeft(150).setTop(200);
        form.add(new BookmarkablePageLink<Void>("search_btn", SearchContactPage.class));
        form.add(new HiddenField("hidden_contact_select", new PropertyModel<String>(this, "hidden_contact_select")));
        form.add(new TextField("contact_select", new Model("")));
        PageParameters params = new PageParameters();
        params.set("mid", posId);
        form.add(new BookmarkablePageLink<Void>("search_user_btn", SelectCRMUserPage.class, params));
        form.add(new HiddenField<String>("hidden_select_user", new PropertyModel<String>(this, "hidden_select_user")));
        form.add(new TextField<String>("selected_user", new PropertyModel<String>(this, "selected_user")));
        IModel<List> visiting_purpose_choices_model = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {
                List<Choice> choices = new ArrayList<Choice>();
                return DAOImpl.queryPickListByFilter("activity_visiting_purpose_pl", "activity_type", String.valueOf(activity_type.getId()));
            }
        };
        //visiting purpose choice
        final DropDownChoice visiting_purpose_choice = createDropDownListFromPickList("visiting_purpose_input", "com_visiting_purpose_pl", visiting_purpose_choices_model, new PropertyModel(this, "visiting_purpose"));
        visiting_purpose_choice.setOutputMarkupId(true);
        form.add(visiting_purpose_choice);
        //event type choice
        DropDownChoice activity_type_choice = createDropDownListFromPickList("activity_type_input", "activity_activity_types_pl", null, new PropertyModel(this, "activity_type"));
        activity_type_choice.setOutputMarkupId(true);
        form.add(activity_type_choice);
        activity_type_choice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(visiting_purpose_choice);
            }
        });
        
        event_type_group.add(coachingRadio);
        form.add(event_type_group);
        form.add(createDropDownListFromPickList("feature_product_input", "activity_feature_product_pl", null, new PropertyModel(this, "feature_product")));
        form.add(createDropDownListFromPickList("planing_input", "score1_pl", null, new PropertyModel(this, "planing")));
        form.add(createDropDownListFromPickList("openling_input", "score1_pl", null, new PropertyModel(this, "openling")));
        form.add(createDropDownListFromPickList("enquery_listening_input", "score1_pl", null, new PropertyModel(this, "enquery_listening")));
        form.add(createDropDownListFromPickList("deliverable_input", "score2_pl", null, new PropertyModel(this, "deliverable")));
        form.add(createDropDownListFromPickList("objection_handing_input", "score1_pl", null, new PropertyModel(this, "objection_handing")));
        form.add(createDropDownListFromPickList("summary_input", "score2_pl", null, new PropertyModel(this, "summary")));
        form.add(new TextField("act_title_input", new PropertyModel(this, "act_title_input")));
        //拜访辅导。辅导者,状态，
    }

    private DropDownChoice createDropDownListFromPickList(String markupId, String picklistName, IModel choices, PropertyModel default_model) {
        if (choices == null) {
            choices = Model.ofList(DAOImpl.queryPickList(picklistName));
        }
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

    class SelectOption implements Serializable {

        private int key;
        private String value;

        public SelectOption(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public SelectOption() {
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
