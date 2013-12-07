package com.rex.crm.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.gson.Gson;
import com.rex.crm.common.Entity;
import com.rex.crm.common.EntityDetailContainerPanel;
import com.rex.crm.common.EntityDetailPage;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.common.tree.Node;
import com.rex.crm.common.tree.ProductTreePanel;
import com.rex.crm.common.tree.TreeFactory;
import com.rex.crm.common.tree.TreePanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import java.util.List;
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
      StringValue entityName = getRequest().getRequestParameters().getParameterValue("entityName");
      
//      System.out.println("StringValue:"+positionId);
//      System.out.println("entityName:"+entityName);
      
     if(positionId.isEmpty() || positionId.isNull()){
         initPage(null,null);
     }else{
         initPage(positionId.toString(),entityName.toString());
     }
     
     urlFor(ProductTreePage.class, null);

  }
  
  public ProductTreePage(final String id, final String entityName){
    	initPage(id,entityName);
    }
  
  private void initPage(final String positionId,final String entityName){
      Gson gson = new Gson();
      String result = gson.toJson(TreeFactory.createProductTree(),Node.class);
      add(new ProductTreePanel("treePanel",result));
      
      if(positionId !=null){
          PageParameters pp = new PageParameters();
          pp.add("positionId", positionId);
          if(positionId.equals("-1")){
              Map<String, Entity> entities = Configuration.getEntityTable();
              final Entity entity = entities.get("productline");
              List tdata = DAOImpl.queryEntityRelationList(entity.getSql());
              add(new PageableTablePanel("datalist", entity, tdata, null));
              //setResponsePage(new ProductPage());
          }else{
              EntityDetailContainerPanel panel = new EntityDetailContainerPanel("datalist",entityName,positionId,this.getClass(),pp);
              add(panel);
          }
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