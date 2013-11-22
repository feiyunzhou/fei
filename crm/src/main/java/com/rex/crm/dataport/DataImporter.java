package com.rex.crm.dataport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.jumpmind.symmetric.csv.CsvReader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.db.DAOImpl;
import com.rex.crm.util.Configuration;
import com.rexen.crm.integration.DataImport;

public class DataImporter {

    public void importPositionData(String filename) throws IOException{
       Entity entity = Configuration.getEntityByName("account");
       List<Field> fields = entity.getFields();
       
       CsvReader reader = new CsvReader(filename, ',', Charset.forName("UTF-8"));

       boolean header = reader.readHeaders();
       //reader.getHeaders();
       ArrayList<String> fieldNames = Lists.newArrayList();
       ArrayList<String> fieldValues = Lists.newArrayList();
       String[] headers = reader.getHeaders();
       Map<String,String> externalIdmap = Maps.newHashMap();
       while(reader.readRecord()){
         
           for(String h:headers){
               if(!fields.contains(h)) continue;
               fieldNames.add(h);
               String value =null;
               if(reader.get(h) != null){
                   value = "'"+reader.get(h)+"'";
//                   if(h.equalsIgnoreCase("externalId")){
//                       externalIdmap.put(reader.get(h),);
//                   }
               }
                  
               fieldValues.add(value);
      
           }
           //fieldNames.add("externalId");
           
           DAOImpl.createNewRecord("crmuser", fieldNames, fieldValues, null);
       }
       
    }
    
    public static void main(String args[]) throws JAXBException, IOException, Exception {
        DataImporter importer = new DataImporter();
        importer.importPositionData("/Users/feiyunzhou/git/tiger/tigerp/crm/src/main/doc/account_import.csv");
    }

}
