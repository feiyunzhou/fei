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

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;


import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.File;



import com.google.common.collect.Lists;
import com.rex.crm.beans.Choice;
import com.rex.crm.util.CRMUtility;


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
    private static final Logger logger = Logger.getLogger(ImportDataWizard.class);
    private static final  List<Choice> choiceList = Lists.newArrayList();
    static {
        choiceList.add(new Choice(0L, "contact"));
        choiceList.add(new Choice(1L, "account"));
        choiceList.add(new Choice(2L, "accountTeam"));
        choiceList.add(new Choice(3L, "position"));
        choiceList.add(new Choice(4L, "userInfo"));
        
    }
    private FileUploadField fileUpload;
    private String entityName;
    private Choice selected_entity = choiceList.get(0);

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
			//add( new Label("entityName", new PropertyModel(this,"entityName")));
		   
		    IModel choices = Model.ofList(choiceList);
		    
		    DropDownChoice dropdown = createDropDownListFromPickList("entityName", choices, new Model(selected_entity));
		    add(dropdown);
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
		    //final FileUploadField position = new FileUploadField("position");
		    fileUpload = new FileUploadField("fileUpload");
		    
		    add(fileUpload);
		    
		}
	}


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
		getForm().setMultiPart(true);
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
		logger.debug("selecte entity:"+ selected_entity.getId());
		
		
		List<FileUpload> uploadedFile = fileUpload.getFileUploads();
		
        if (uploadedFile != null && uploadedFile.size()>0) {

            // write to a new file
            File newFile = new File(CRMUtility.readFileAttribure("uploadpath") + uploadedFile.get(0).getClientFileName());

            if (newFile.exists()) {
                newFile.delete();
            }

            try {
                newFile.createNewFile();
                uploadedFile.get(0).writeTo(newFile);

                info("saved file: " + uploadedFile.get(0).getClientFileName());
            } catch (Exception e) {
                throw new IllegalStateException("Error");
            }
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
	                return String.valueOf(choice.getId());
	            }
	            
	            
	        });
	        
	        choice.setNullValid(true);
	        return choice;
	    }
		
	

}