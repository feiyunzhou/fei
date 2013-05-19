
package com.rex.crm.ajax;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Feiyun Zhou
 */
public class DataTable {

    private List<ColumnDescription> columnsDes = new ArrayList<>();
    private List<TableRow> tableRows = new ArrayList<>();

    public void addRow(TableRow row) {
        tableRows.add(row);
    }

    public void addColumn(ColumnDescription column) {
        columnsDes.add(column);
    }

    public String stringify() {
        JsonObject jo = new JsonObject();

        //populate table data
        JsonArray data = new JsonArray();
        for (TableRow row : tableRows) {
            data.add(row.row);
        }
        jo.add("aaData", data);

        //populate column descritons
        JsonArray columns = new JsonArray();
        for (ColumnDescription col : columnsDes) {
            columns.add(col.columnDes);
        }

        jo.add("aoColumns", columns);
        jo.addProperty("sPaginationType", "bootstrap");
        //jo.addProperty("sDom", "<'row-fluid'<'span8'l><'span4'f>r>t<'row-fluid'<'span8'i><'span4'p>>");
        jo.addProperty("sDom", "<'row-fluid'<'span8'f>r>t<'row-fluid'<'span4'i><'span8'p>>");
        jo.add("oLanguage", getChineseConfig());

        return jo.toString();
    }
    /**
     * Get the language configuration for the jQuery dataTable
     * @return 
     */
    private static JsonObject getChineseConfig(){
      JsonObject jo = new JsonObject();
      
      jo.addProperty("sProcessing", "正在处理中...");
      jo.addProperty("sLengthMenu", "每页显示_MENU_条记录");
      jo.addProperty("sZeroRecords", "没有数据");
      jo.addProperty("sInfo", "此页显示从第_START_条记录到第_END_记录，总共_TOTAL_条记录");
      jo.addProperty("sInfoEmpty", "0页");
      jo.addProperty("sInfoFiltered", "");
      jo.addProperty("sInfoPostFix", "");
      jo.addProperty("sSearch", "查找");
      jo.addProperty("sUrl", "");
      
      JsonObject page = new JsonObject();
      page.addProperty("sFirst", "第一页");
      page.addProperty("sPrevious", "上一页");
      page.addProperty("sNext", "下一页");
      page.addProperty("sLast", "最后一页");
      jo.add("oPaginate", page);
      
      return jo;
    }
}
