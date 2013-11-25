package com.rex.crm.dataport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.jumpmind.symmetric.csv.CsvReader;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.rex.crm.WicketApplication;
import com.rex.crm.beans.Choice;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.CRMUtility;
import com.rex.crm.util.Configuration;

public class DataImporter {
    
    private static final Logger logger = Logger.getLogger(DataImporter.class);

    public static ImportMeta _importData(String entityName,String filename) throws IOException{
        if(entityName.equalsIgnoreCase("user_position") || entityName.equalsIgnoreCase("accountcrmuser")){
            DAOImpl.deleteAllRecords(entityName);
        }
       // String   tmpDir = CRMUtility.createTempDirectory().getAbsolutePath();
        ImportMeta importMeta = new ImportMeta();
        
        int num_of_total_record = 0;
        int num_of_imported = 0;
        int num_of_failed = 0;
        int num_of_updated = 0;
        int result = 0;
        File logfile = File.createTempFile("crm_error", "file.log");
        importMeta.setLogfilename(logfile.getAbsolutePath());
        importMeta.setEntityName(entityName);
        importMeta.setImportfilename(filename);
        
        
        BufferedWriter error_writter = com.google.common.io.Files.newWriter(logfile, Charset.forName("utf-8"));
//        BufferedWriter update_error_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/update_error.txt"), Charset.forName("utf-8"));
//        BufferedWriter success_insert_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/success_insert.txt"), Charset.forName("utf-8"));
//        BufferedWriter success_update_writter = com.google.common.io.Files.newWriter(new File(tmpDir+"/success_update.txt"), Charset.forName("utf-8"));
        List<String[]> inserts_list =  Lists.newArrayList();
        logger.debug("logger:"+logfile.getAbsolutePath());
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
       int line = 0;
       boolean flag = true;
       while(reader.readRecord()){
           line++;
           num_of_total_record++;   
           ArrayList<String> fieldNames = Lists.newArrayList();
           ArrayList<String> fieldValues = Lists.newArrayList();
           flag = true;
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
                            error_writter.write(f.getImport_field_name()+ " 未查到值："+value +"  "+  reader.getRawRecord()+"\n");
                            num_of_failed++;
                            result = 1;
                            flag = false;
                            break;
                        }
                       
                   }else if(f.getRelationTable()!=null){
                           
                           String relationTable = f.getRelationTable();     
                           Map<String, String> externalId2idMap = relationTableCache.get(relationTable);
                           String id = externalId2idMap.get(value);
                           logger.debug("line:"+line);
                           if(id == null){
                               logger.debug(relationTable+"中没有此外部ID:"+value);
                               error_writter.write(relationTable+"中没有此外部ID:"+value +" === " +reader.getRawRecord()+"\n");
                               
                               if(entityName.equalsIgnoreCase("accountcrmuser")){
                                   num_of_failed++;
                                   result = 1;
                                   flag = false;
                                    break;
                               }
                               
                           }else{
                               fieldNames.add(f.getName());
                               fieldValues.add(id);
                               logger.debug("added:"+f.getName() + "=" +id);
                           }
                          
                           
                   }else{
                       fieldNames.add(f.getName());
                       
                       if(f.getDataType().equalsIgnoreCase("password")){
                           value = DigestUtils.md5Hex("12345");
                           fieldValues.add("'"+value+"'");
                       }else if(f.getDataType().equalsIgnoreCase("number")){
                           fieldValues.add(value);
                       }else{
                           fieldValues.add("'"+value+"'");
                       }
                       
                       
                       
                      
                      
                       
                       
                       
                   }
               }
           }
           
            // fieldNames.add("externalId");
            if (fieldNames.size() > 0 && flag) {
                
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                fieldNames.add("modify_datetime");
                fieldValues.add("'"+dateformat.format(new Date())+"'");
                fieldNames.add("whenadded");
                fieldValues.add("'"+dateformat.format(new Date())+"'");
                fieldNames.add("modifier");
                fieldValues.add("'数据导入模块'");

                if(entityName.equalsIgnoreCase("user_position")){
                    fieldNames.add("isPrimary");
                    fieldValues.add("1");
                }
                
                long id = DAOImpl.importRecord(entityName, fieldNames, fieldValues);
                // failed insert record, we guess we need update record
                //boolean flag = false;
                if (id < 0) {

                    // we query the record if it exist, if it exist, we think it
                    // duplicated so upated it
                    Field externalField = entity.getFieldByName(entity.getExternalField());
                    String externalId = reader.get(externalField.getImport_field_name());
                    Map data = DAOImpl.queryRecordByField(entity.getName(), externalField.getName(), externalId);
                    if (data != null && externalId != null) {
                        int updates = DAOImpl.updateRecord4Import(entityName, fieldNames, fieldValues, externalField.getName(), externalId);
                        if(updates > 0){
                            //success_update_writter.write(reader.getRawRecord()+"\n");
                            inserts_list.add(reader.getValues());
                            //num_of_imported++;
                            num_of_updated++;
                        }else{
                            error_writter.write("更新错误："+reader.getRawRecord()+ "\n");
                            num_of_failed++;
                            result = 1;
                        }
                    }
                }else{
                    //success_insert_writter.write(reader.getRawRecord()+"\n");
                    inserts_list.add(reader.getValues());
                    num_of_imported++;
                }
            }
       }
       
        
       }finally{
           //insert_error_writter.close();
          // update_error_writter.close();
           // success_insert_writter.close();
            error_writter.close();
            logger.debug("logger:"+logfile.getAbsolutePath());
       }
       
       
       importMeta.setNum_of_failed(num_of_failed);
       importMeta.setNum_of_imported(num_of_imported);
       importMeta.setNum_of_total_record(num_of_total_record);
       importMeta.setNum_of_updated(num_of_updated);
       importMeta.setResult(result);
       return importMeta;
        
    }
    
    public static void importDataOnBackground(final String entityName, final String filename) throws IOException{
        
     //Get the ThreadFactory implementation to use
        
              
                DataImporter importer = new DataImporter();
                DataImporter.WorkerThread thread = importer.new WorkerThread(entityName,filename);
                CRMUtility.getThreadPoolExecutor().execute(thread);
     
    }
    
    public static void importDataOnSync(final String entityName, final String filename) throws IOException{
        
        //Get the ThreadFactory implementation to use
           
                 
                   DataImporter importer = new DataImporter();
                   DataImporter.WorkerThread worker = importer.new WorkerThread(entityName,filename);
                   worker.processCommand();
                   //CRMUtility.getThreadPoolExecutor().execute(thread);
        
       }
    
    public static void main(String args[]) throws JAXBException, IOException, Exception {
        DataImporter importer = new DataImporter();
        //importer.importPositionData("crmuser","/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/crmuser_import.csv");
       importer.importDataOnBackground("user_position","/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/user_position_import.csv");
        //importer.importDataOnBackground("userinfo","/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/userinfo_import.csv");
    
    }
    
    private class WorkerThread implements Runnable {
        private String entityName;
        private String filename;
        
        public WorkerThread(final String entityName, final String filename) {
            this.entityName = entityName;
            this.filename = filename;
        }

        @Override
        public void run() {
            processCommand();
            
        }

        public void processCommand() {
            try {

                long id = DAOImpl.insertImportMetaInfo(entityName, filename);
                if(id>0){
                   ImportMeta metaInfo = DataImporter._importData(entityName, filename);
                   DAOImpl.updateImportMetaInfoById(metaInfo.getLogfilename(), metaInfo.getNum_of_total_record(), 
                           metaInfo.getNum_of_imported(), metaInfo.getNum_of_failed(),metaInfo.getNum_of_updated(), metaInfo.getResult(), id);
                }
                

            } catch (Exception e) {

                e.printStackTrace();

            }

        }


    }

}
