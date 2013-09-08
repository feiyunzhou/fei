package com.rex.crm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.velocity.app.VelocityEngine;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.rex.crm.beans.City;
import com.rex.crm.beans.Province;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Field;
import com.rex.crm.common.Reaction;
import com.rex.crm.common.Relation;

public class Configuration {
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
                    // TODO Auto-generated catch block
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
           entity = ents.get(name);
       }
       return entity;
   } 
    public static Map<String,Entity> getEntityTable(){
        if (entities == null || entities.size() == 0) {
            synchronized (Configuration.class) {
                List<Entity> list = getEntities();
                entities = Maps.newHashMap();
                for(Entity en:list){
                 entities.put(en.getName(), en);
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
        list.add("基本信息");
        list.add("地址信息");
        list.add("其他信息");
        
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
                    field.setDataType(convert2Clazz(sub2.getString("data-type")));
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
                    
                    field.setPrimaryKey(Boolean.parseBoolean(sub2.getString("isPrimaryKey")));
                    field.setDetailLink(Boolean.parseBoolean(sub2.getString("isDetailLink"))); 
                    field.setVisible(Boolean.parseBoolean(sub2.getString("isVisible")));
                    field.setEditable(Boolean.parseBoolean(sub2.getString("isEditable"))); 
               
                }
                
            }

        } catch (ConfigurationException cex) {
            cex.printStackTrace();
        }

        return enitites;
    }

}
