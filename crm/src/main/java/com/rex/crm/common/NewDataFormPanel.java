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
import org.apache.wicket.behavior.AttributeAppender;
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
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class NewDataFormPanel extends Panel {
	private static final Logger logger = Logger
			.getLogger(NewDataFormPanel.class);
	private Map<String, List<Field>> addFieldGroupMap = Maps.newHashMap();
	private static int NUM_OF_COLUMN = 3;

	public NewDataFormPanel(String id, final Entity entity,final Map<String, String> relationIds) {
		super(id);

		final Map<String, IModel> models = Maps.newHashMap();
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
			if (groupfields == null)
				continue;
			RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
			AbstractItem groupitem = new AbstractItem(fieldGroupRepeater.newChildId());
			groupitem.setOutputMarkupId(true);
			fieldGroupRepeater.add(groupitem);
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
				AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
				dataRowRepeater.add(item);
				RepeatingView columnRepeater = new RepeatingView("columnRepeater");
				item.add(columnRepeater);
				for (int j = 0; j < 2 * NUM_OF_COLUMN; j++) {
					AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model());
					if ((i * NUM_OF_COLUMN + j / 2) >= visibleFields.size()) {
						columnitem.add(new LayoutFragment("celldatafield", "layoutFragment",this," ").setEscapeModelStrings(false));
						columnRepeater.add(columnitem);
						
						continue;
					}
					Field currentField = visibleFields.get(i * NUM_OF_COLUMN+ j / 2);
					
					if (currentField.getPicklist() != null) {
						
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("celldatafield","textFragment",this, currentField.getDisplay() + ":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
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
							columnitem.add(new DropDownChoiceFragment("celldatafield", "dropDownFragment", this,ids, list, choiceModel));
						}
					} else if (currentField.getRelationTable() != null) {
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("celldatafield","textFragment",this, currentField.getDisplay() +":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
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
							columnitem.add(new DropDownChoiceFragment("celldatafield", "dropDownFragment", this,ids, list, choiceModel));
						}
					} else {
						if (j % 2 == 0) {
							columnitem.add(new TextFragment("celldatafield","textFragment",this, currentField.getDisplay() + ":").add(new AttributeAppender("style",new Model("font-weight:bold;"),";")));
							columnitem.add(new AttributeAppender("style",new Model("text-align:right;width:200px"),";"));
						} else {
							IModel<String> textModel = new Model<String>("");
							models.put(currentField.getName(), textModel);
							columnitem.add(new TextInputFragment("celldatafield","textInputFragment", this, textModel));
						}
					}
					columnRepeater.add(columnitem);
					
				}System.out.println("+++++++++++++++++++++++++++" + entity.toString());
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
					if(!key.equals("accountId")){
					fieldNames.add(key);}
					System.out.println(fieldNames);
					// models.get(key).getObject()
					if (models.get(key).getObject() instanceof String) {
						values.add("'" + (String) models.get(key).getObject()
								+ "'");
					} else {
						values.add(String.valueOf(models.get(key).getObject()));
					}

				}
				long generatedId = DAOImpl.createNewRecord(entity.getName(),fieldNames, values,userId);
				if (generatedId > 0) {
					DAOImpl.insert2UserRelationTable(entity.getName(), userId,
							String.valueOf(generatedId));
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
	
	private class TextFragment extends Fragment{

		public TextFragment(String id, String markupId,
				MarkupContainer markupProvider,  String label) {
			super(id, markupId, markupProvider);
			add(new Label("celldata", label));
			// TODO Auto-generated constructor stub
		}
	}
	
	private class LayoutFragment extends Fragment{

		public LayoutFragment(String id, String markupId,
				MarkupContainer markupProvider,  String label) {
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

	private class TextInputFragment extends Fragment {
		public TextInputFragment(String id, String markupId,
				MarkupContainer markupProvider, IModel model) {
			super(id, markupId, markupProvider);

			add(new TextField<String>("input", model));
		}
	}

}
