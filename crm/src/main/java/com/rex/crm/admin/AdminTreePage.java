package com.rex.crm.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.gson.Gson;
import com.rex.crm.common.EntityDetailContainerPanel;
import com.rex.crm.common.tree.Node;
import com.rex.crm.common.tree.TreeFactory;
import com.rex.crm.common.tree.TreePanel;

/**
 * @author Feiyun Zhou
 */
public class AdminTreePage extends AdminTemplatePage
{
  private String search_target = "";

  /**
   * Constructor
   */
  public AdminTreePage()
  {
      setPageTitle("系统管理-岗位树");
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
      String result = gson.toJson(TreeFactory.createPositionTree(),Node.class);
      add(new TreePanel("treePanel",result));
      
      if(positionId !=null){
        PageParameters pp = new PageParameters();
        pp.add("positionId", positionId);
        EntityDetailContainerPanel panel = new EntityDetailContainerPanel("datalist","crmuser",positionId,this.getClass(),pp);
        add(panel);
      }else{
          add(new EmptyPanel("datalist"));
      }
  }
  
  
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