package com.rex;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.rex.crm.ajax.DataProvider;
import com.rex.crm.common.Entity;
import com.rex.crm.common.Relation;
import com.rex.crm.util.Configuration;

public class CRMTest {

    @Test
    public void testConfig(){
        List<Entity> entities = Configuration.getEntities();
        for(Entity en:entities){
            System.out.println(en);
        }
    }
    
    @Test
    public void testRelation(){
        Collection<Relation> list = Configuration.getRelationsByName("contact");
        for(Relation r:list){
            System.out.println(r.getSql());
            
        }
    }
    
    @Test
    public void testjson(){
       System.out.println(DataProvider.getActivityIdsOfContactIdByUserId(new String[]{"20"}));
   
    }
    
 
}
