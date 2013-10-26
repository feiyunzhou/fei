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
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PopupSettings;
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
import com.rex.crm.common.Entity;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;

public class EventCoachEditorPage extends TemplatePage{
	private static final Logger logger = Logger.getLogger(EventCoachEditorPage.class);
	public Date startDate;
    protected String endDate;
    protected String endTime;
    protected String startTime;
    protected String crmuserId = "";
    protected String location = "";
    protected int total_score;
    protected Choice planing = new Choice(0L,"");
    protected Choice openling = new Choice(0L,"");
    protected Choice enquery_listening = new Choice(0L,"");
    protected Choice deliverable = new Choice(0L,"");
    protected Choice objection_handing = new Choice(0L,"");
    protected Choice summary = new Choice(0L,"");
    protected String owner = "";
    protected String whenadded = "";
    protected String modifier = "";
    protected String modifier_time = "";
    protected String act_name_input = "";
    protected String selected_user = "";
	protected Choice feature_product = new Choice(1L, "");
	protected String status = "计划中";
	protected String hidden_select_user;
    /**
	 * Constructor
	 */
	public EventCoachEditorPage(final long eventId) {
		Map<String, Entity> entities = Configuration.getEntityTable();
		Entity entity = entities.get("activity");
		setPageTitle(entity.getDisplay());
		final String uid = ((SignIn2Session) getSession()).getUserId();
		final String user = ((SignIn2Session) getSession()).getUser();
		CRMUser crmUser = DAOImpl.getCRMUserInfoById(Integer.parseInt(uid));
		Map entity_data = DAOImpl.queryEntityById(entity.getSql_ent(),String.valueOf(eventId));
		//add prompt 
        final RepeatingView div = new RepeatingView("promptDiv");
        final AbstractItem divitem = new AbstractItem(div.newChildId());
        final Label promptButton = new Label("promptButton","X");
        divitem.add(promptButton);
        final Label promptLabel = new Label("prompt","红色输入框为必填项，请输入!");
        divitem.add(promptLabel);
        div.add(new AttributeAppender("style",new Model("display:none"),";"));
        divitem.add(new AttributeAppender("style",new Model("display:none"),";"));
        div.add(divitem);
        add(div);
		//辅导名称拼接字段
		final String crmuserName = crmUser.getName();
        final StringBuffer concahName = new StringBuffer();
		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				String sd = getRequest().getPostParameters()
						.getParameterValue("start_date").toString();
				String st = getRequest().getPostParameters()
						.getParameterValue("start_time").toString();
				String ed = getRequest().getPostParameters()
						.getParameterValue("end_date").toString();
				String et = getRequest().getPostParameters()
						.getParameterValue("end_time").toString();
				String visit_type = String.valueOf(2);
				SimpleDateFormat dateformat = new SimpleDateFormat(
						"yyyy-MM-ddTHH:mm");
				Date act_endtime = new Date(System.currentTimeMillis());
				String sdt = sd + " " + st;
				String edt = ed + " " + et;
				Date startdt = null;
				Date enddt = null;
				int coachId = 0;
				try {
					startdt = dateformat.parse(sdt);
					enddt = dateformat.parse(edt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(null==act_name_input){
					concahName.append(crmuserName);
	                concahName.append(sd);
	                concahName.append("拜访辅导");
	                act_name_input = concahName.toString();
				}
				 if(null==hidden_select_user){
                	coachId = 0;
                 }else{
                	coachId = Integer.parseInt(hidden_select_user);
                 }
				 boolean flag = true;
	                //验证字段是否为空如果为空则做页面提示
	                if(coachId==0){
	                	div.add(new AttributeAppender("style",new Model("display:block"),";"));
	            		divitem.add(new AttributeAppender("style",new Model("display:block"),";"));
	            		promptLabel.add(new AttributeAppender("style",new Model("display:block"),";"));
	            		promptButton.add(new AttributeAppender("style",new Model("display:block"),";"));
	            		flag = false;
	                }
	             if(flag){
	            	 try {
	 					DAOImpl.updateCalendarEventForCoach(String.valueOf(eventId),hidden_select_user,
	 							String.valueOf(startdt.getTime() / 1000),
                                String.valueOf(enddt.getTime() / 1000),user,coachId,location,total_score,String.valueOf(planing.getId()),String.valueOf(openling.getId()),String.valueOf(enquery_listening.getId()),String.valueOf(deliverable.getId()),String.valueOf(objection_handing.getId()),String.valueOf(summary.getId()),act_name_input);

	 				} catch (NumberFormatException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} catch (Exception e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 				setResponsePage(PageFactory.createPage("calendar"));
	             }
			}
		};
		add(form);

		StringValue startdateValue = this.getRequest().getRequestParameters()
				.getParameterValue("startdate");

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date(
				((Number) entity_data.get("starttime")).longValue());
		String startdate = dateformat.format(startDate);

		logger.debug("startdate:" + startdate);

		WebMarkupContainer start_date_input = new WebMarkupContainer(
				"start_date_input");
		// startDate = startdate;
		// DateTextField start_date_input = new
		// DateTextField("start_date_input", new
		// PropertyModel<Date>(this,"startDate"));
		form.add(start_date_input);
		start_date_input.add(new AttributeModifier("value", startdate));

		SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
		WebMarkupContainer start_time_input = new WebMarkupContainer(
				"start_time_input");
		form.add(start_time_input);
		start_time_input.add(new AttributeModifier("value", timeformatter
				.format(startDate)));

		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date endDate = new Date(
				((Number) entity_data.get("endtime")).longValue());
		WebMarkupContainer end_date_input = new WebMarkupContainer(
				"end_date_input");
		form.add(end_date_input);
		end_date_input.add(new AttributeModifier("value", dateformat
				.format(endDate)));
		WebMarkupContainer end_time_input = new WebMarkupContainer(
				"end_time_input", new Model(""));
		form.add(end_time_input);
		end_time_input.add(new AttributeModifier("value", timeformatter
				.format(endDate)));
		feature_product.setId(((Number) entity_data.get("feature_product"))
				.longValue());
		form.add(createDropDownListFromPickList("feature_product",
				"activity_feature_product_pl", null, new PropertyModel(this,
						"feature_product")));
		//辅导者
		//获取辅导者对象
		CRMUser crmuser = DAOImpl.getCrmUserById((int) entity_data.get("crmuserId"));
		crmuserId = crmuser.getName();
		form.add(new TextField("crmuserId", new PropertyModel(this,
				"crmuserId")));
		
		 
	 	hidden_select_user = String.valueOf(entity_data.get("crmuserId"));
		form.add(new HiddenField("hidden_select_user",
				new PropertyModel<String>(this, "hidden_select_user")));
		//被辅导者
		int selected_userId = (int)entity_data.get("coacheeId");
		selected_user = DAOImpl.getCrmUserById(selected_userId).getName();
		form.add(new TextField<String>("selected_user" ,new PropertyModel<String>(this,"selected_user")));

		 
		 PopupSettings popupSettings = new PopupSettings("查找").setHeight(470)
                 .setWidth(850).setLeft(150).setTop(200);
         PageParameters params = new PageParameters();
         params.set("mid", uid);
		 form.add(new BookmarkablePageLink<Void>("search_user_btn", SelectCRMUserPage.class,params ).setPopupSettings(popupSettings));
	     //form.add(new HiddenField<String>("hidden_select_user" ,new PropertyModel<String>(this,"hidden_select_user")));
		//地址
		location = (String)entity_data.get("location");
		form.add(new TextField("location", new PropertyModel(this,
				"location")));
		//评分一系列分数
		total_score = (int)entity_data.get("total_score");
		form.add(new TextField("total_score", new PropertyModel(this,
				"total_score")));
		
		planing.setId(((Number) entity_data.get("planing"))
				.longValue());
		form.add(createDropDownListFromPickList("planing_input",
				"score1_pl", null, new PropertyModel(this,
						"planing")));
		openling.setId(((Number) entity_data.get("openling"))
				.longValue());
		form.add(createDropDownListFromPickList("openling_input",
				"score1_pl", null, new PropertyModel(this,
						"openling")));
		enquery_listening.setId(((Number) entity_data.get("enquery_listening"))
				.longValue());
		form.add(createDropDownListFromPickList("enquery_listening_input",
				"score1_pl", null, new PropertyModel(this,
						"enquery_listening")));
		deliverable.setId(((Number) entity_data.get("deliverable"))
				.longValue());
		form.add(createDropDownListFromPickList("deliverable_input",
				"score2_pl", null, new PropertyModel(this,
						"deliverable")));
		objection_handing.setId(((Number) entity_data.get("objection_handing"))
				.longValue());
		form.add(createDropDownListFromPickList("objection_handing_input",
				"score2_pl", null, new PropertyModel(this,
						"objection_handing")));
		summary.setId(((Number) entity_data.get("summary"))
				.longValue());
		form.add(createDropDownListFromPickList("summary_input",
				"score2_pl", null, new PropertyModel(this,
						"summary")));
		owner = (String) entity_data.get("owner");
		form.add(new TextField("owner", new PropertyModel(this,
				"owner")));
		Date createTime = (Date)entity_data.get("whenadded");
		whenadded = createTime.toString();
		form.add(new TextField("whenadded", new PropertyModel(this,
				"whenadded")));
		act_name_input = (String) entity_data.get("title");
		
		form.add(new TextField("act_name_input", new PropertyModel(this,
				"act_name_input")));
		TextField modifier = new TextField("modifier", new PropertyModel(this,"modifier"));
		modifier.add(new AttributeModifier("value",crmUser.getName()));
        form.add(modifier);
        //创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        TextField modifier_time = new TextField("modifier_time", new PropertyModel(this,"modifier_time"));
        modifier_time.add(new AttributeModifier("value",df.format(new Date())));
        form.add(modifier_time);
        TextField statusField = new TextField("status", new PropertyModel(this,"status"));
        form.add(statusField);
	}
	private DropDownChoice createDropDownListFromPickList(String markupId,
			String picklistName, IModel choices, PropertyModel default_model) {

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
