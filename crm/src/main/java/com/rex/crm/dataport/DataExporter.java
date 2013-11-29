package com.rex.crm.dataport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.jumpmind.symmetric.csv.CsvWriter;

import com.google.common.collect.Lists;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class DataExporter {

    
    
    public static String export(final String entityName, final List mapList) throws IOException{
        final Entity entity = Configuration.getEntityByName(entityName);
        if (mapList != null && entity !=null) {
           
           final List<Field> fields = entity.getFields();
           final List<Field> validFields = Lists.newArrayList();
           File tmpFile = File.createTempFile("crm_exported_file_", ".zip");
           FileOutputStream fos = new FileOutputStream(tmpFile);
           ZipOutputStream zos = new ZipOutputStream(fos);
           ZipEntry ze= new ZipEntry(entityName+"_export.csv");
           zos.putNextEntry(ze);      
           
           CsvWriter writer =  new CsvWriter(zos, ',', Charset.forName("UTF-8"));
           try{
           List<String> heads = Lists.newArrayList();
          
           for (Field f : fields) {
               if (!f.isVisible())  continue;
               validFields.add(f);
               heads.add(f.getDisplay());
           }
           
           String[] tests = heads.toArray(new String[0]);
           writer.writeRecord(tests);
           
            for (Map map : (List<Map>) mapList) {
                String key = String.valueOf(map.get("id"));
                
                List<String> record = Lists.newArrayList();
                for (Field f : validFields) {
                        if (f.getPicklist() != null) {
                            // get option from picklist
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryPickListByIdCached(f.getPicklist(), String.valueOf(map.get(f.getName()))));
                            if(value.equals("null")||value.isEmpty()){
                              value = "";
                            }
                            record.add(value);
                        } else if(f.getRelationTable() != null){
                            String value = CRMUtility.formatValue(f.getFormatter(), DAOImpl.queryCachedRelationDataById(f.getRelationTable(), String.valueOf(map.get(f.getName()))));
                            if(value.equals("null")||value.isEmpty()){
                              value = "";
                            }
                        
                            record.add(value);
                        }else {
                             String value = CRMUtility.formatValue(f.getFormatter(), String.valueOf(map.get(f.getName())));
                            
                            if(f.getDataType().equalsIgnoreCase("downloadlink")){
                                record.add("");
                            }else{
                              if(value.equals("null")||value.isEmpty()){
                                 value = "";
                               }
                              record.add(value);
                            }
                        }
                }   
                writer.writeRecord(record.toArray(new String[0]));
                
            }
             writer.flush();

            }finally{
              
                zos.closeEntry();
                zos.close();    
                fos.close();
                writer.close();
               
            }
           
            return tmpFile.getAbsolutePath();
        }
        return null;

    }
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

        final Entity entity = Configuration.getEntityByName("crmuser");
        List mapList = DAOImpl.queryEntityRelationList(entity.getSqlAdmin());
        System.out.println("filename:"+DataExporter.export("crmuser", mapList ));

    }

}
