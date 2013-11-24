/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rex.crm.dataport;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.util.io.IClusterable;


/**
 * Domain class for the new user wizard example.
 * 
 * @author Eelco Hillenius
 */
public final class ImportMeta implements IClusterable
{
    private String entityName;
    private String importfilename;
    private String logfilename;
    private int num_of_total_record;
    private int num_of_imported;
    private int num_of_failed;
    private int num_of_updated;
    private int result;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getImportfilename() {
        return importfilename;
    }

    public void setImportfilename(String importfilename) {
        this.importfilename = importfilename;
    }

    public String getLogfilename() {
        return logfilename;
    }

    public void setLogfilename(String logfilename) {
        this.logfilename = logfilename;
    }

    public int getNum_of_total_record() {
        return num_of_total_record;
    }

    public void setNum_of_total_record(int num_of_total_record) {
        this.num_of_total_record = num_of_total_record;
    }

    public int getNum_of_imported() {
        return num_of_imported;
    }

    public void setNum_of_imported(int num_of_imported) {
        this.num_of_imported = num_of_imported;
    }

    public int getNum_of_failed() {
        return num_of_failed;
    }

    public void setNum_of_failed(int num_of_failed) {
        this.num_of_failed = num_of_failed;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getNum_of_updated() {
        return num_of_updated;
    }

    public void setNum_of_updated(int num_of_updated) {
        this.num_of_updated = num_of_updated;
    }
   
   
   
}
