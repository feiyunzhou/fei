package com.rex.crm.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.PageFactory;
import com.rex.crm.SignIn2Session;
import com.rex.crm.TemplatePage;
import com.rex.crm.beans.Choice;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class EditDataFormPanel extends Panel {
	private static final Logger logger = Logger.getLogger(EditDataFormPanel.class);
	private static final long serialVersionUID = -2613412283023068638L;
	private final String user = ((SignIn2Session) getSession()).getUser();
	private Map<String, List<Field>> fieldGroupMap = Maps.newHashMap(); 

	private static int NUM_OF_COLUMN = 3;

	/**
	 * 
	 * @param id
	 * @param schema
	 * @param data
	 * @param entityId
	 * @param relationIds
	 */
	public EditDataFormPanel(String id, final Entity schema, final Map data,final String entityId ,final Map<String, String> relationIds) {
		super(id);
		final Map<String, IModel> models = Maps.newHashMap();
		final Map<String, IModel> fieldNameToModel = Maps.newHashMap();
		final String userId = ((SignIn2Session) getSession()).getUserId();
		String primaryKeyName = schema.getPrimaryKeyName();
		List<Field> fields = schema.getFields();// 得到所有fields
		final List<String> fieldNames = Lists.newArrayList();
		// List<String> fn = schema.getFieldNames();
		for (Field f : fields) {
			if (fieldGroupMap.get(f.getFieldGroup()) != null) {
				fieldGroupMap.get(f.getFieldGroup()).add(f);// 以fieldgroup为条件查询，并将其添加入新的集合中
			} else {
				List<Field> fs = Lists.newArrayList();
				fs.add(f);
				fieldGroupMap.put(f.getFieldGroup(), fs);
			}
		}
		List<String> groupNames = Configuration.getSortedFieldGroupNames();// 得到分组信息
		RepeatingView fieldGroupRepeater = new RepeatingView("fieldGroupRepeater");
		add(fieldGroupRepeater);
		for (String gn : groupNames) {
			logger.info("gn = "+gn+ "groupNames = "+groupNames);

			List<Field> groupfields = fieldGroupMap.get(gn);
			if (groupfields == null)
				continue;
			AbstractItem groupitem = new AbstractItem(fieldGroupRepeater.newChildId());
			fieldGroupRepeater.add(groupitem);
			RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");

			groupitem.add(dataRowRepeater);
			int numOfField = 0;
			List<Field> visibleFields = Lists.newArrayList();
			for (Field f : groupfields) {
				if (!f.isVisible() )
					continue;
				numOfField++;
				visibleFields.add(f);
			}

			groupitem.add(new Label("groupname", gn));
			int num_of_row = (numOfField / NUM_OF_COLUMN) + 1;
			
			for (int i = 0; i < num_of_row; i++) {
				logger.debug("for i = "+i+",dataRowRepeater.newChildId() = "+dataRowRepeater.newChildId()+",value = "+dataRowRepeater.get(dataRowRepeater.newChildId()));
				AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
				dataRowRepeater.add(item);
				RepeatingView columnRepeater = new RepeatingView("columnRepeater");
				item.add(columnRepeater);

				for (int j = 0; j < 2 * NUM_OF_COLUMN; j++) {

					AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model(String.valueOf(data.get(primaryKeyName))));

					if ((i * NUM_OF_COLUMN + j / 2) >= visibleFields.size()) {
						columnitem.add(new LayoutFragment("editdata","layoutFragment", this, "&nbsp;"));
						columnRepeater.add(columnitem);
						continue;
					}
					Field currentField = visibleFields.get(i * NUM_OF_COLUMN + j / 2);
					if (currentField.getPicklist() != null) {
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("editdata","textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
							fieldNames.add(currentField.getName());
						} else {
							List<Choice> pickList = DAOImpl.queryPickList(currentField.getPicklist());
							Map<Long, String> list = Maps.newHashMap();
							List<Long> ids = Lists.newArrayList();
							for (Choice p : pickList) {
								list.put(p.getId(), p.getVal());
								ids.add(p.getId());
							}
							String filedName = data.get(currentField.getName()).toString();
							IModel choiceModel =  new Model(Long.parseLong(filedName));
					
							models.put(currentField.getName(), choiceModel);
							fieldNameToModel.put(currentField.getName(), choiceModel);
							columnitem.add(new DropDownChoiceFragment("editdata", "dropDownFragment", this, ids,list, choiceModel));
						}
					}
					else if (currentField.getRelationTable() != null) {
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("editdata","textFragment",this, currentField.getDisplay() +":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
							fieldNames.add(currentField.getName());
						} else {
							List<Choice> pickList = DAOImpl.queryRelationDataList(currentField.getRelationTable(),userId);
							Map<Long, String> list = Maps.newHashMap();
							List<Long> ids = Lists.newArrayList();
							for (Choice p : pickList) {
								list.put(p.getId(), p.getVal());
								ids.add(p.getId());
							}
							long foreignKey = 1L;
							if (relationIds != null&& relationIds.containsKey(currentField.getAlias())) {
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
								fieldNameToModel.put(currentField.getName(), choiceModel);
								columnitem.add(new DropDownChoiceFragment("editdata", "dropDownFragment", this,ids, list, choiceModel));
						}
					} 
						else {
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("editdata","textFragment", this, currentField.getDisplay() + ":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
							fieldNames.add(currentField.getName());
						} else {

							Object rawvalue = data.get(currentField.getName());
							rawvalue = (rawvalue == null) ? "" : rawvalue;
							 String value =
							 CRMUtility.formatValue(currentField.getFormatter(),
							 String.valueOf(rawvalue));
							 value = (value == null) ? "" : value;
							IModel<String> textModel = new Model<String>("");
							models.put(CRMUtility.formatValue(currentField.getFormatter(),String.valueOf(rawvalue)),textModel);
							fieldNameToModel.put(currentField.getName(), textModel);
							columnitem.add(new TextInputFragment("editdata","textInputFragment", this, textModel,value,currentField));
						}
					}
					columnRepeater.add(columnitem);

				}

			}// end of set the detailed info
		}// end of groupNames loop
		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				logger.debug("the form was submitted!");
				logger.debug(models);
				List<String> values = Lists.newArrayList();
				List<String> names = Lists.newArrayList();
				for (String k : fieldNameToModel.keySet()) {
					names.add(k);
					String value = fieldNameToModel.get(k).getObject() == null? null:"'"+String.valueOf(fieldNameToModel.get(k).getObject())+"'";
				    values.add(value);

				}
				DAOImpl.updateRecord(entityId,schema.getName(),names, values);
				setResponsePage(new EntityDetailPage(schema.getName(),entityId));
			}
		};
		form.add(fieldGroupRepeater);
		add(form);
		// set navigation bar active
		add(new AbstractAjaxBehavior() {

			@Override
			public void onRequest() {
			}

			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				super.renderHead(component, response);
				response.render(OnDomReadyHeaderItem.forScript("$(\"#navitem-"
						+ schema.getName() + "\").addClass(\"active\");"));
			}

		});

	}

	public EditDataFormPanel(String id, IModel<?> model) {
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
			add(new Label("add", label).setEscapeModelStrings(false));
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

	private class TextInputFragment extends Fragment {
		public TextInputFragment(String id, String markupId,
				MarkupContainer markupProvider, IModel model,String value,Field currentField) {
			super(id, markupId, markupProvider);
			TextField<String> text = new TextField<String>("input", model);
							if(!currentField.isEditable()){
								if(currentField.getName().equals("accountId")){
									text.add(new AttributeAppender("disabled",new Model("disabled"),";"));
								}else if(currentField.getName().equals("modifier")){
									text.add(new AttributeAppender("value", new Model(user), ";"));
									text.add(new AttributeAppender("readonly",new Model("readonly"),";"));
								}else{
									text.add(new AttributeAppender("value", new Model(value), ";"));
									text.add(new AttributeAppender("readonly",new Model("readonly"),";"));
								}
							}else{
								text.add(new AttributeAppender("value", new Model(value), ";"));
							}
							add(text);
		}
	}

}
