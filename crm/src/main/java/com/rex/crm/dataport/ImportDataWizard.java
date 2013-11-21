/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rex.crm.dataport;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.wizard.StaticContentStep;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardModel.ICondition;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.form.validation.EqualInputValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;


/**
 * This wizard shows some basic form use. It uses custom panels for the form elements, and a single
 * domain object ({@link User}) as it's subject. Also, the user roles step}is an optional step, that
 * will only be executed when assignRoles is true (and that value is edited in the user details
 * step).
 * 
 * @author Eelco Hillenius
 */
public class ImportDataWizard extends Wizard
{


	/**
	 * The user details step.
	 */
	private final class SelectEntityNameStep extends WizardStep
	{
		/**
		 * Construct.
		 */
		public SelectEntityNameStep()
		{
			//add(new )
		}
	}

	/**
	 * The user name step.
	 */
	private final class SelectFileStep extends WizardStep
	{
		/**
		 * Construct.
		 */
		public SelectFileStep()
		{
			
		}
	}


	/** cheap ass roles database. */
	private static final List<String> allRoles = Arrays.asList("admin", "user", "moderator",
		"joker", "slacker");
	
	/** The user we are editing. */
	private String entityName;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 */
	public ImportDataWizard(String id)
	{
		super(id);

		setDefaultModel(new CompoundPropertyModel<ImportDataWizard>(this));
		WizardModel model = new WizardModel();
		model.add(new SelectEntityNameStep());
		model.add(new SelectFileStep());

		// initialize the wizard with the wizard model we just built
		init(model);
	}


	/**
	 * @see org.apache.wicket.extensions.wizard.Wizard#onCancel()
	 */
	@Override
	public void onCancel()
	{
	    System.out.println("Canceled");
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.Wizard#onFinish()
	 */
	@Override
	public void onFinish()
	{
		System.out.println("Finished");
	}

}