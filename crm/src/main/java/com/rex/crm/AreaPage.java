package com.rex.crm;

import java.util.List;
import java.util.Map;


import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import com.google.common.collect.Lists;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.PageableTablePanel;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import com.rex.crm.admin.AdminTemplatePage;
/**
 * @author Sam sun
 */
public class AreaPage  extends AdminTemplatePage{
     private String search_target = "";

  public AreaPage()
  {
    initPage(null, null);
  }

  public AreaPage(final Map<String, Boolean> filter, List tdata)
  {
    initPage(filter, tdata);
  }
  public void initPage(final Map<String, Boolean> filter, List tdata)
  {
    Map<String, Entity> entities = Configuration.getEntityTable();
    final Entity entity = entities.get("province");
    setPageTitle(entity.getDisplay());
    
    
    Form form = new Form("form")
    {

      @Override
      protected void onSubmit()
      {
        String sql = entity.getSql();
      

        search_target = (search_target == null || search_target.equalsIgnoreCase("*")) ? "" : search_target;

        // sql = sql + " AND name like '%"+search_target+"%'";
        List<Field> searchableFields = entity.getSearchableFields();
        String joint = " like '%" + search_target + "%'";
        String likequery = "";
        for (Field sf : searchableFields)
        {
          likequery = likequery + " OR " + sf.getName() + joint;
        }
        
        sql = sql + " where val like '%" + search_target + "%' " + likequery;
        List datalist = null;
          datalist = DAOImpl.queryEntityRelationList(sql);
        setResponsePage(new AreaPage(filter, datalist));

      }

    };
    add(form);

    TextField search_input = new TextField("search_input", new PropertyModel(this, "search_target"));
    form.add(search_input);

    String sql = entity.getSql();
    // List mapList = DAOImpl.queryEntityList(entity.getSql(), 0, 1000);
    if (tdata == null || tdata.size() == 0)
    {
        tdata = DAOImpl.queryEntityRelationList(sql);
      
      if (filter == null)
      {
          tdata = DAOImpl.queryEntityRelationList(sql);
      }
      else
      {
        List<String> ft = Lists.newArrayList();
        for (String k : filter.keySet())
        {
          if (filter.get(k))
            ft.add(k);
        }
          tdata = DAOImpl.queryEntityWithFilter(sql, entity.getFilterField(), ft);
      }

    }
    add(new PageableTablePanel("datalist", entity, tdata, null));


  }
}
