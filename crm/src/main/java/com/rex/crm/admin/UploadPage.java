package com.rex.crm.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.util.file.File;

import com.rex.crm.util.CRMUtility;
import com.rexen.crm.integration.DataImportDelegate;

/**
 * @author Feiyun Zhou
 */
public class UploadPage extends AdminTemplatePage
{

  public UploadPage()
  {
    init();
  }

  public void init()
  {

    final FileUploadField account = new FileUploadField("account");

    final FileUploadField contact = new FileUploadField("contact");

    final FileUploadField position = new FileUploadField("position");

    final FileUploadField accountTeam = new FileUploadField("accountTeam");

//    final FileUploadField userInfo = new FileUploadField("userInfo");
    Form form = new Form("form")
    {
      @Override
      protected void onSubmit()
      {
        DataImportDelegate da = new DataImportDelegate();
        FileUpload accountFileUpload = account.getFileUpload();
        CRMUtility util = new CRMUtility();
        StringBuffer FileName = new StringBuffer(util.readFileAttribure("uploadpath"));
        
        if (accountFileUpload != null)
        {
          String template = "Account Full Import Template 1.0";
          FileName.append(accountFileUpload.getClientFileName());
          try
          {
            accountFileUpload.writeTo(new File(FileName.toString()));
            da.load(template, FileName.toString());
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload contactFileUpload = contact.getFileUpload();
        if (contactFileUpload != null)
        {
          FileName.append(contactFileUpload.getClientFileName());
          String template = "Contact Import Template 1.0";
          try
          {
            contactFileUpload.writeTo(new File(FileName.toString()));
            da.load(template, FileName.toString());
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload positionFileUpload = position.getFileUpload();
        if (positionFileUpload != null)
        {
          FileName.append(positionFileUpload.getClientFileName());
          String template = "Position Import Template 1.0";
          try
          {
            positionFileUpload.writeTo(new File(FileName.toString()));
            da.load(template, FileName.toString());
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload accountTeamyFileUpload = accountTeam.getFileUpload();
        if (accountTeamyFileUpload != null)
        {
    		FileName.append(accountTeamyFileUpload.getClientFileName());
	        String template = "Account Team Import Template 1.0";
            try
           {
            accountTeamyFileUpload.writeTo(new File(FileName.toString()));
            da.load(template, FileName.toString());
           }
		   catch (Exception e)
		   {
		       e.printStackTrace();
		   }
        }

      }
    };
    add(form);
    form.add(account);
    form.add(contact);
    form.add(position);
    form.add(accountTeam);
//    form.add(userInfo);
    form.setMultiPart(true);


  }
}