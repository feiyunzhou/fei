package com.rex.crm.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.annotations.Expose;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.Reaction;
import com.rex.crm.common.Relation;

public class Configuration {
    private static final Logger logger = Logger.getLogger(Configuration.class);
    private static VelocityEngine ve;
    private static Map<String,Entity> entities = null;
    private static Multimap<String,Relation> relations = null;

    
    public static VelocityEngine getVelocityEngine() {

        if (ve == null) {
            synchronized (Configuration.class) {
                Properties properties = new Properties();
                try {
                    properties.load(Configuration.class.getResourceAsStream("/velocity.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ve = new VelocityEngine();
                ve.init(properties);
            }
        }
        return ve;

    }

   public static Entity getEntityByName(String name){
       Entity entity = null;
       Map<String,Entity> ents = getEntityTable();
       if(ents!=null && ents.size()!=0){
           entity = ents.get(name.toLowerCase());
       }
       return entity;
   } 
    public static Map<String,Entity> getEntityTable(){
        if (entities == null || entities.size() == 0) {
            synchronized (Configuration.class) {
                List<Entity> list = getEntities();
                entities = Maps.newHashMap();
                for(Entity en:list){
                 entities.put(en.getName().toLowerCase(), en);
                }
            }
         }   
        return entities;
    }
    
    private static Class convert2Clazz(String dataType){
        
        if(dataType.equalsIgnoreCase("string")){
            return String.class;
        }else if(dataType.equalsIgnoreCase("long")){
            return long.class;
        }else if(dataType.equalsIgnoreCase("boolean")){
            return boolean.class;
        }else{
            return Object.class;
        }
    }
    
    public static List<Relation> getRelationsByName(String name){
        List<Relation> list = Lists.newArrayList();
        Multimap<String, Relation> rel = getRelationTable();
        if(rel != null){
            Collection<Relation> alist = rel.get(name);
            list.addAll(alist);
        }
        return list;
    }
    
    public static Multimap<String,Relation> getRelationTable(){
        if (relations == null || relations.size() == 0) {
            synchronized (Configuration.class) {
                List<Relation> list = getRelations();
                relations = ArrayListMultimap.create();   
                for(Relation r:list){
                    relations.put(r.getFrom(), r);
                }
            }
         }   
        return relations;
    }

    
    public static List<String> getSortedFieldGroupNames(){
        List<String> list = Lists.newArrayList();
        list.add("关键信息");
        list.add("基本信息");
        list.add("附加信息");
        list.add("评分信息");
        return list;
    }
    
    public static List<Relation> getRelations(){
        List<Relation> relations = new ArrayList<>();
        try {
            XMLConfiguration config = new XMLConfiguration("relations.xml");
            List<HierarchicalConfiguration> hp = config.configurationsAt("relation");

            for (HierarchicalConfiguration sub : hp) {
                Relation relation = new Relation();
                relations.add(relation);
                relation.setName( sub.getString("name"));
                relation.setDisplay(sub.getString("display"));
                relation.setFrom(sub.getString("from"));
                relation.setTo(sub.getString("to"));
                relation.setSql(sub.getString("sql"));
                
                
                List<Reaction> reactions = Lists.newArrayList();
                relation.setReactions(reactions);
                List<HierarchicalConfiguration> hp2 = sub.configurationsAt("reactions.reaction");
                
                for(HierarchicalConfiguration sub2:hp2){
                    Reaction reaction = new Reaction();
                    reactions.add(reaction);              
                    reaction.setName(sub2.getString("name"));
                    reaction.setUrl(sub2.getString("url"));
                    reaction.setDisplay(sub2.getString("display"));
                    reaction.setParamName(sub2.getString("paramName"));
                    
                }
            }

        } catch (ConfigurationException cex) {
            cex.printStackTrace();
        }

        return relations;
    }
    
    public static List<Entity> getEntities() {
        List<Entity> enitites = new ArrayList<>();
        try {
            XMLConfiguration config = new XMLConfiguration("entity.xml");
            List<HierarchicalConfiguration> hp = config.configurationsAt("entity");

            for (HierarchicalConfiguration sub : hp) {
                Entity entity = new Entity();
                enitites.add(entity);
                entity.setName( sub.getString("name"));
                entity.setDisplay(sub.getString("display"));
                entity.setSql(sub.getString("sql"));
                entity.setSql_ent(sub.getString("sql-ent"));
                entity.setSqlAdmin(sub.getString("sql-admin"));
                entity.setSqlManager(sub.getString("sql-manager"));
                entity.setSqlManagerCalendar(sub.getString("sql-manager-calendar"));
                entity.setSqlAdminCalendar(sub.getString("sql-admin-calendar"));
                entity.setSqlCalendar(sub.getString("sql-calendar"));
                entity.setSqlManagerCoaching(sub.getString("sql-manager-coaching"));
                entity.setSqlAdminCoaching(sub.getString("sql-admin-coaching"));
                entity.setSqlCoaching(sub.getString("sql-coaching"));
                entity.setExternalField(sub.getString("externalField"));
                entity.setGlobalsearch(Boolean.parseBoolean(sub.getString("globalsearch"))); 
                String filterField = sub.getString("filterField");
                
                if(filterField !=null){
                    entity.setFilterField(filterField);
                }
                
                List<Field> fields = Lists.newArrayList();
                entity.setFields(fields);
                List<HierarchicalConfiguration> hp2 = sub.configurationsAt("fields.field");
                
                for(HierarchicalConfiguration sub2:hp2){
                    Field field = new Field();
                    fields.add(field);
                    field.setDataType(sub2.getString("data-type"));
                    field.setDisplay(sub2.getString("display"));
                    field.setName(sub2.getString("name"));
                    field.setPriority(Integer.parseInt(sub2.getString("priority")));
                    
                    String picklist = sub2.getString("picklist");
                    if(picklist!=null && picklist.trim().length()!=0){
                        field.setPicklist(picklist);
                    }else{
                        field.setPicklist(null);
                    }
                    
                    
                    String relationTable = sub2.getString("relationTable");
                    if(relationTable!=null && relationTable.trim().length()!=0){
                        field.setRelationTable(relationTable);
                    }else{
                        field.setRelationTable(null);
                    }
                    
                    
                    String alias = sub2.getString("alias");
                    if(alias!=null && alias.trim().length()!=0){
                        field.setAlias(alias);
                    }else{
                        field.setAlias(null);
                    }
                    
                    String formatter = sub2.getString("formatter");
                    if(formatter !=null){
                        field.setFormatter(formatter);
                    }
                    
                    
                    String fieldGroup = sub2.getString("fieldGroup");
                    if(fieldGroup !=null){
                        field.setFieldGroup(fieldGroup);
                    }
                    
                    String default_value = sub2.getString("default-value");
                    if(default_value !=null){
                        field.setDefault_value(default_value);
                        //System.out.println("default_value:"+field.getDefault_value());
                    }
                    
                    String default_value_type = sub2.getString("default-value-type");
                    if(default_value_type !=null){
                        field.setDefault_value_type(default_value_type);
                    }
                    
                    String childNode = sub2.getString("childNode");
                    if(childNode !=null){
                        field.setChildNode(childNode);
                    }
                    
                    String parentNode = sub2.getString("parentNode");
                    if(parentNode !=null){
                        field.setParentNode(parentNode);
                    }
                    
                    String fieldtype = sub2.getString("field-type");
                    if(fieldtype !=null){
                        field.setFieldType(fieldtype);
                    }
                    String tooltip = sub2.getString("tooltip");
                    if(tooltip!=null){
                    	field.setTooltip(Integer.parseInt(tooltip));
                    }
                    String import_field_name = sub2.getString("import_field_name");
                    if(import_field_name !=null){
                        field.setImport_field_name(import_field_name);
                    }
                    
                    String import_external_foreignkey_field_name = sub2.getString("import_external_foreignkey_field_name");
                    if(import_external_foreignkey_field_name!=null){
                        field.setImport_external_foreignkey_field_name(import_external_foreignkey_field_name);
                    }
       
                    field.setPrimaryKey(Boolean.parseBoolean(sub2.getString("isPrimaryKey")));
                    field.setDetailLink(Boolean.parseBoolean(sub2.getString("isDetailLink"))); 
                    field.setVisible(Boolean.parseBoolean(sub2.getString("isVisible")));
                    field.setEditable(Boolean.parseBoolean(sub2.getString("isEditable"))); 
                    field.setRequired(Boolean.parseBoolean(sub2.getString("isRequired"))); 
                    field.setBaseInfo(Boolean.parseBoolean(sub2.getString("isBaseInfo")));
                    field.setSearchable(Boolean.parseBoolean(sub2.getString("isSearchable")));
                    field.setParam(Boolean.parseBoolean(sub2.getString("isParam")));
                    field.setShow(Boolean.parseBoolean(sub2.getString("isShow")));
                    field.setExistsDefaultValue(Boolean.parseBoolean(sub2.getString("isExistsDefaultValue")));
                    
                    
                }
                
            }

        } catch (ConfigurationException cex) {
            cex.printStackTrace();
        }

        return enitites;
    }

}
