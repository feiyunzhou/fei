package com.rex.crm.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.util.file.File;

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
        if (accountFileUpload != null)
        {
          String template = "Account Full Import Template 1.0";
          String accountFileName = "/root/crm/" + accountFileUpload.getClientFileName();
          try
          {
            accountFileUpload.writeTo(new File(accountFileName));
            da.load(template, accountFileName);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload contactFileUpload = contact.getFileUpload();
        if (contactFileUpload != null)
        {
          String contactFileName = "/root/crm/" + contactFileUpload.getClientFileName();
          String template = "Contact Import Template 1.0";
          try
          {
            contactFileUpload.writeTo(new File(contactFileName));
            da.load(template, contactFileName);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload positionFileUpload = position.getFileUpload();
        if (positionFileUpload != null)
        {
          String positionFileName = "/root/crm/" + positionFileUpload.getClientFileName();
          String template = "Position Import Template 1.0";
          try
          {
            positionFileUpload.writeTo(new File(positionFileName));
            da.load(template, positionFileName);
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        FileUpload accountTeamyFileUpload = accountTeam.getFileUpload();
        if (accountTeamyFileUpload != null)
        {
          String accountTeamFileName = "/root/crm/" + accountTeamyFileUpload.getClientFileName();
          String template = "Account Team Import Template 1.0";
          try
          {
            accountTeamyFileUpload.writeTo(new File(accountTeamFileName));
            da.load(template, accountTeamFileName);
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