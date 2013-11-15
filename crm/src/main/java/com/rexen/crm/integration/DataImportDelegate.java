/*
 * Copyright 2013 Ralf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rexen.crm.integration;

import com.rexen.crm.beans.DataExchangeTeample;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ralf
 */
public class DataImportDelegate
{

  private DataAccessObject dao;

  public DataImportDelegate()
  {
    dao = new DataAccessObject("crm_mysql");
  }

  public Configuration getConfiguration(String template) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
  {
    Configuration config = null;

    DataExchangeTeample example = new DataExchangeTeample();
    example.setName(template);

    example = (DataExchangeTeample) dao.find(example);

    if (example != null)
    {
      try
      {
        StringReader buffer = new StringReader(example.getTemplate());

        JAXBContext context = JAXBContext.newInstance(Configuration.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        config = (Configuration) unmarshaller.unmarshal(buffer);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    return config;
  }

  public void load(String template, String filePath) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException, IOException, Exception 
  {//模板名 文件路径
    Configuration config = getConfiguration(template);

    if (config != null)
    {
      config.setFileName(filePath);
      DataImport dataImport = new DataImport(config, dao);
      dataImport.load();
    }
  }
}
