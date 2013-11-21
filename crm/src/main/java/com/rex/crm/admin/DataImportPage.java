package com.rex.crm.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.util.file.File;

import com.rex.crm.dataport.ImportDataWizard;
import com.rex.crm.util.CRMUtility;
import com.rexen.crm.integration.DataImportDelegate;

/**
 * @author Feiyun Zhou
 */
public class DataImportPage extends AdminTemplatePage
{

  public DataImportPage()
  {
    init();
  }

  public void init()
  {

     ImportDataWizard wizard = new ImportDataWizard("wizard");
     add(wizard);

  }
}