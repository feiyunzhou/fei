package com.rex.crm.admin;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.rex.crm.admin.AdminTemplatePage;
import com.rex.crm.beans.Choice;
import com.rex.crm.dataport.DataImporter;
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
        choiceList.add(new Choice(0L, "导入医院"));
        choiceList.add(new Choice(1L, "导入医生"));
        choiceList.add(new Choice(2L, "导入用户"));
        choiceList.add(new Choice(3L, "导入岗位"));
        choiceList.add(new Choice(4L, "导入岗位汇报关系"));
        choiceList.add(new Choice(5L, "导入岗位医院关系"));
        choiceList.add(new Choice(6L, "导入用户和岗位关系"));
    }
    private FileUploadField fileUploadField;
    private Choice selected_entity = choiceList.get(0);
    BookmarkablePageLink view_log_btn ;

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
              if(selected_entity ==null) return;
            logger.debug("selected_entity:"+ selected_entity.getId());
            
            //DataImportDelegate da = new DataImportDelegate();
            FileUpload fileupload = fileUploadField.getFileUpload();
            String outputfolder = CRMUtility.readFileAttribure("uploadpath");
 
            java.io.File tmpDir = null;
            tmpDir = Files.createTempDir();
            if (fileupload != null)
            {
            
            
              try
              {
                  
                  
                  String tmpFileName = tmpDir.getAbsolutePath() + File.separator+ fileupload.getClientFileName();
                  fileupload.writeTo(new File(tmpFileName));
                  logger.debug("selected_entity:"+selected_entity.getVal());
                  String entityName = "";
                  int entityId = (int)selected_entity.getId();
                  switch(entityId){
                  case 0:
                         entityName = "account";
                         break;
                  case 1:
                      entityName = "contact";
                       break;
                  case 2:
                      entityName = "userinfo";
                      break;
                  case 3:
                      entityName = "crmuser";
                      break;
                  case 4:
                      entityName = "crmuser";
                      break;
                  case 5:
                      entityName = "accountcrmuser";
                      break;
                  case 6:
                      entityName = "user_position";
                      break;
                  
                  }
                  
                  if(entityName.isEmpty()) {
                      logger.debug("entity name is null");
                      return;
                      }
                 
                  
                  DataImporter.importDataOnBackground(entityName, tmpFileName);
                  //da.load(template, FileName.toString());
                  //this.setResponsePage(ImportLogPage.class);
                  view_log_btn.setVisible(true);
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
      
      view_log_btn = new BookmarkablePageLink("view_log_btn",ImportLogPage.class );
      form.add(view_log_btn);
      view_log_btn.setVisible(false);
      
     File account_template = new File(DataImportPage.class.getResource("/templates/account.csv").getPath());
     add(new DownloadLink("account_template", account_template));
     
     File contact_template = new File(DataImportPage.class.getResource("/templates/contact.csv").getPath());
     add(new DownloadLink("contact_template", contact_template));
     
     File user_template = new File(DataImportPage.class.getResource("/templates/userinfo.csv").getPath());
     add(new DownloadLink("user_template", user_template));
     
     File crmuser_template = new File(DataImportPage.class.getResource("/templates/crmuser.csv").getPath());
     add(new DownloadLink("crmuser_template", crmuser_template));
     
     File crmuser_report_template = new File(DataImportPage.class.getResource("/templates/crmuser_reportto.csv").getPath());
     add(new DownloadLink("crmuser_report_template", crmuser_report_template));
     
     File accountcrmuser_template = new File(DataImportPage.class.getResource("/templates/accountcrmuser.csv").getPath());
     add(new DownloadLink("accountcrmuser_template", accountcrmuser_template));
     
     File user_position_template= new File(DataImportPage.class.getResource("/templates/user_position.csv").getPath());
     add(new DownloadLink("user_position_template", user_position_template));
      
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