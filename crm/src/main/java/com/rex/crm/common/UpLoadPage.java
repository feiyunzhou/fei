package com.rex.crm.common;

import java.awt.Button;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.AccountPage;
import com.rex.crm.ActivitySelectPage;
import com.rex.crm.DataPage;
import com.rex.crm.SignIn2Session;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.CRUDPanel.Permissions;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rexen.crm.integration.DataExportDelegate;
import com.rexen.crm.integration.DataImportDelegate;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;


/**
 * @author Feiyun Zhou 
 */
public class UpLoadPage extends DataPage{
    public UpLoadPage(){
       init();
    }

	public void init()
	{
   
	  final FileUploadField accountFF = new FileUploadField("account");

    final FileUploadField contact = new FileUploadField("contact");

    final FileUploadField activity = new FileUploadField("activity");

    final FileUploadField position = new FileUploadField("position");

	  Form form = new Form("form") {
      @Override
      protected void onSubmit() {
        DataImportDelegate da = new DataImportDelegate();
          FileUpload accountFileUpload = accountFF.getFileUpload();
          
          if(accountFileUpload != null){
            String template = "Account Export Full Template 1.0";
            String accountFileName = "D:\\tmp\\"+accountFileUpload.getClientFileName();
            try{ 
              accountFileUpload.writeTo(new File(accountFileName));
              System.out.println("file path: " + accountFileName);
              da.load("Account Full Import Template 1.0", accountFileName);
            } catch(Exception e){
                e.printStackTrace();
            }
          }
          FileUpload contactFileUpload = contact.getFileUpload();
          if(contactFileUpload != null){
            String contactFileName = "D:\\tmp\\"+accountFileUpload.getClientFileName();
            String template = "Account Export Full Template 1.0";
            try{ 
              contactFileUpload.writeTo(new File(contactFileName));
              da.load(template, contactFileName);
            } catch(Exception e){
                e.printStackTrace();
            }
          }
         
      }
     
    };

          add(form);
          form.add(accountFF);
          form.add(contact);
          form.add(activity);
          form.add(position);
        
          
          
	}
}