package com.rex;

import java.util.List;

import org.junit.Test;

import com.rex.crm.common.Entity;
import com.rex.crm.util.Configuration;

public class CRMTest {

    @Test
    public void testConfig(){
        List<Entity> entities = Configuration.getEntities();
        for(Entity en:entities){
            System.out.println(en);
        }
    }
}
