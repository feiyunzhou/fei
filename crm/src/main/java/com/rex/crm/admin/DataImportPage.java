package com.rex.crm.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;

import com.google.common.collect.Lists;
import com.rex.crm.beans.Choice;
import com.rex.crm.dataport.ImportDataWizard;
import com.rex.crm.util.CRMUtility;
import com.rexen.crm.integration.DataImportDelegate;

/**
 * @author Feiyun Zhou
 */
public class DataImportPage extends AdminTemplatePage
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
    private FileUploadField fileUploadField;
    private Choice selected_entity = choiceList.get(0);

  public DataImportPage()
  {
    init();
  }

  public void init()
  {
      fileUploadField = new FileUploadField("fileUpload");
      
      Form form = new Form("form"){
          
          protected void onSubmit()
          {
            logger.debug("selected_entity:"+ selected_entity.getId());
            
            DataImportDelegate da = new DataImportDelegate();
            FileUpload fileupload = fileUploadField.getFileUpload();
            String outputfolder = CRMUtility.readFileAttribure("uploadpath");
            
            if (fileupload != null)
            {
            
            
              try
              {
                  
                  fileupload.writeTo(new File(outputfolder+File.separator+fileupload.getClientFileName()));
                //da.load(template, FileName.toString());
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
            }
          }
      };

      add(form);
      form.add(fileUploadField);
      form.setMultiPart(true);
      IModel choices = Model.ofList(choiceList);
      
      DropDownChoice dropdown = createDropDownListFromPickList("entityName", choices, new PropertyModel(this,"selected_entity"));
      form.add(dropdown);
      
      
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