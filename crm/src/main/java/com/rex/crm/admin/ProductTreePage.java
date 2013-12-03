package com.rex.crm.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailContainerPanel;
import com.rex.crm.common.Field;
import com.rex.crm.common.FilterPanel;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.Relation;
import com.rex.crm.common.RelationDataPanel;
import com.rex.crm.common.tree.Node;
import com.rex.crm.common.tree.TreeFactory;
import com.rex.crm.common.tree.TreePanel;
import com.rex.crm.db.DAOImpl;
import com.rexen.crm.beans.UserRole;
import com.rex.crm.util.Configuration;

/**
 * @author Feiyun Zhou
 */
public class ProductTreePage extends AdminTemplatePage
{
  private String search_target = "";

  /**
   * Constructor
   */
  public ProductTreePage()
  {
	  setPageTitle("系统管理-产品树");
      StringValue positionId = getRequest().getRequestParameters().getParameterValue("positionId");
     if(positionId.isEmpty() || positionId.isNull()){
         initPage(null);
     }else{
         initPage(positionId.toString());
     }
     
     urlFor(AdminTreePage.class, null);

  }

  
  
  private void initPage(final String positionId){
      Gson gson = new Gson();
      String result = gson.toJson(TreeFactory.createProductTree(),Node.class);
      add(new TreePanel("treePanel",result));
      
      if(positionId !=null){
          PageParameters pp = new PageParameters();
          pp.add("positionId", positionId);
          EntityDetailContainerPanel panel = new EntityDetailContainerPanel("datalist","product",positionId,this.getClass(),pp);
          add(panel);
      }else{
          add(new EmptyPanel("datalist"));
      }
      
  }
  //监听请求 Ajax 
  // 级联 
  @Override
  public void renderHead(IHeaderResponse response) {
      super.renderHead(response);
      StringValue act_key = getRequest().getRequestParameters().getParameterValue("positionId");
      Map<String, Object> map = new HashMap<>();
      
      map.put("act_key", act_key.toString());
      PackageTextTemplate ptt = new PackageTextTemplate( getClass(), "adminpage.js" );
      //OnDomReadyHeaderItem onDomReadyHeaderItem = OnDomReadyHeaderItem.forScript( ptt.asString( map ) );
      //response.render(onDomReadyHeaderItem);
      System.out.println(ptt.asString(map));
      response.render(JavaScriptHeaderItem.forScript(ptt.asString(map),null));
      
      try {
          ptt.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
}