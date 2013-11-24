package com.rex.crm.dataport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.jumpmind.symmetric.csv.CsvReader;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class DataImporter {
    
    private static final Logger logger = Logger.getLogger(DataImporter.class);

    public void importPositionData(String entityName,String filename) throws IOException{
        //File file = File.createTempFile("tempfile", "tmp");
        String   tmpDir = CRMUtility.createTempDirectory().getAbsolutePath();
        
        BufferedWriter insert_error_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/insert_error.txt"), Charset.forName("utf-8"));
        BufferedWriter update_error_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/update_error.txt"), Charset.forName("utf-8"));
        BufferedWriter success_insert_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/success_insert.txt"), Charset.forName("utf-8"));
        BufferedWriter success_update_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/success_update.txt"), Charset.forName("utf-8"));
        List<String[]> inserts_list =  Lists.newArrayList();
        logger.debug("logger:"+tmpDir);
       try{
       Entity entity = Configuration.getEntityByName(entityName);
       List<Field> importfields = entity.getImportFields();
       Map<String,Field> fieldMap = Maps.newHashMap();
       
       for(Field f:importfields){
           fieldMap.put(f.getName(), f);
       }
       
       List<Field> relationfields = entity.getImportForeignKeyFields();
       
       CsvReader reader = new CsvReader(filename, ',', Charset.forName("UTF-8"));
       reader.readHeaders();
       String[] headers = reader.getHeaders();
       //reader.getHeaders();
       
       Map<String,Map<String,String>>  picklistMap = Maps.newHashMap();
       
       
       Map<String,Map<String,String>> relationTableCache = Maps.newHashMap();
       if (relationfields.size() > 0) {         
           for(Field field:relationfields){
               String relationTable = field.getRelationTable();
               String forignkeyFieldName = field.getImport_external_foreignkey_field_name();
               List<Choice> pairs = DAOImpl.queryExternalIds(relationTable, forignkeyFieldName);
               Map<String,String> map = Maps.newHashMap();
               for(Choice choice:pairs){
                   map.put(choice.getVal(), String.valueOf(choice.getId())) ;  
               }
               relationTableCache.put(relationTable, map);   
           }   
       }
       
       while(reader.readRecord()){
           ArrayList<String> fieldNames = Lists.newArrayList();
           ArrayList<String> fieldValues = Lists.newArrayList();
           
           for(Field f:importfields){
               //if(relationfields.contains(f)) continue;
               
               if(reader.get(f.getImport_field_name()) != null){
                   String value = reader.get(f.getImport_field_name());
                   //if it is a pick list, we need lookup pick value to return the ID
                   if(f.getPicklist() !=null){
                       String picklistTable = f.getPicklist();
                      
                       //create map for  'value to id'
                       if(!picklistMap.containsKey(picklistTable)){
                           List<Choice> picklist = DAOImpl.queryPickList(picklistTable);
                           Map<String,String> value2id = Maps.newHashMap();
                           for(Choice choice:picklist){
                               value2id.put(choice.getVal(), String.valueOf(choice.getId()));
                           }
                           picklistMap.put(picklistTable, value2id);
                       }
                       
                        Map<String, String> value2idMap = picklistMap.get(picklistTable);
                        
                        if(value2idMap.containsKey(value)){
                            fieldNames.add(f.getName());
                            fieldValues.add(value2idMap.get(value));
                        }else{
                            logger.debug("failed to get matched value");
                            insert_error_writter.write("未查到值："+value +"  "+ reader.getRawRecord()+"\n");
                            break;
                        }
                       
                   }else if(f.getRelationTable()!=null){
                           String relationTable = f.getRelationTable();     
                           Map<String, String> externalId2idMap = relationTableCache.get(relationTable);
                           String id = externalId2idMap.get(value);
                           if(id == null){
                               logger.debug("failed to get id:"+id);
                               update_error_writter.write("系统中没有此外部ID " +reader.getRawRecord()+"\n");
                               break;
                           }
                           fieldNames.add(f.getName());
                           fieldValues.add(id);
                           
                   }else{
                       if(f.getDataType().equalsIgnoreCase("password")){
                           value = DigestUtils.md5Hex("12345");
                       }
                       
                       fieldNames.add(f.getName());
                       fieldValues.add("'"+value+"'");
                       
                       
                       
                   }
               }
           }
           
            // fieldNames.add("externalId");
            if (fieldNames.size() > 0) {
                long id = DAOImpl.importRecord(entityName, fieldNames, fieldValues);
                // failed insert record, we guess we need update record
                boolean flag = false;
                if (id < 0) {

                    // we query the record if it exist, if it exist, we think it
                    // duplicated so upated it
                    Field externalField = entity.getFieldByName(entity.getExternalField());
                    String externalId = reader.get(externalField.getImport_field_name());
                    Map data = DAOImpl.queryRecordByField(entity.getName(), externalField.getName(), externalId);
                    if (data != null && externalId != null) {
                        int updates = DAOImpl.updateRecord4Import(entityName, fieldNames, fieldValues, externalField.getName(), externalId);
                        if(updates > 0){
                            success_update_writter.write(reader.getRawRecord()+"\n");
                            inserts_list.add(reader.getValues());
                        }else{
                            update_error_writter.write("更新错误："+reader.getRawRecord()+ "\n");
                        }
                    }
                }else{
                    success_insert_writter.write(reader.getRawRecord()+"\n");
                    inserts_list.add(reader.getValues());
                }
            }
       }
       
       
       //handle foreignkey field
//        if (relationfields.size() > 0) {
//            
//            //Map<String,Map<String,String>> relationTableCache = Maps.newHashMap();
//            
//            for(Field field:relationfields){
//                String relationTable = field.getRelationTable();
//                String forignkeyFieldName = field.getImport_external_foreignkey_field_name();
//                List<Choice> pairs = DAOImpl.queryExternalIds(relationTable, forignkeyFieldName);
//                Map<String,String> map = Maps.newHashMap();
//                for(Choice choice:pairs){
//                    map.put(choice.getVal(), String.valueOf(choice.getId())) ;  
//                }
//                relationTableCache.put(relationTable, map);
//                
//            }
//            
//            
//           // CsvReader reader2 = new CsvReader(filename, ',', Charset.forName("UTF-8"));
//           /// reader2.readHeaders();
//            Map<String,Field> relationFieldMap = Maps.newHashMap();
//            for(Field field:relationfields){
//                relationFieldMap.put(field.getImport_field_name(), field);
//                
//            }
//            
//            Map<String,Integer> headerName2Index = Maps.newHashMap();
//            int i = 0;
//            for(String h:headers){
//                if(relationFieldMap.containsKey(h)){
//                    headerName2Index.put(relationFieldMap.get(h).getName(), i); 
//                }
//                
//                i++;
//            }
//            
//            for (String[] values:inserts_list) {
//                
//                for (Field field:relationfields) {
//                   // if(!relationfields.contains(headers[i])) continue;
//                    
//                    ArrayList<String> relationfieldNames = Lists.newArrayList();
//                    ArrayList<String> relationfieldValues = Lists.newArrayList();
//                    
//                    if(values[headerName2Index.get(field.getName())] != null){
//                        String value = values[headerName2Index.get(field.getName())];
//                        String relationTable = field.getRelationTable();
//                        
//                        Map<String, String> externalId2idMap = relationTableCache.get(relationTable);
//                        String id = externalId2idMap.get(value);
//                        if(id == null){
//                            logger.debug("failed to get id:"+id);
//                            update_error_writter.write("系统中没有此外部ID " +Joiner.on(",").join(values)+"\n");
//                            break;
//                        }
//                        relationfieldNames.add(field.getName());
//                        relationfieldValues.add(id);
//                        
//                        Field externalField = entity.getFieldByName(entity.getExternalField());
//                        String externalId = reader.get(externalField.getName());
//                        DAOImpl.updateRecord4Import(entityName, relationfieldNames, relationfieldValues, externalField.getName(), externalId);
//                    }
//                    
//                }
//
//            }
//        }
        
       }finally{
           insert_error_writter.close();
           update_error_writter.close();
            success_insert_writter.close();
            success_update_writter.close();
            logger.debug("logger:"+tmpDir);
       }
        
    }
    
    public static void main(String args[]) throws JAXBException, IOException, Exception {
        DataImporter importer = new DataImporter();
        //importer.importPositionData("crmuser","/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/crmuser_import.csv");
        importer.importPositionData("userInfo","/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/userinfo_import.csv");
    }

}
