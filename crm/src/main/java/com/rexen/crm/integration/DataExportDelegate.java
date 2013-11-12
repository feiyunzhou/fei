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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ralf
 */

public class DataExportDelegate
{

  private DataAccessObject dao;

  public DataExportDelegate()
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

  public void export(String template, HttpServletResponse response) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException, IOException, IllegalArgumentException, ClassNotFoundException
  {
    Configuration config = getConfiguration(template);

    if (config != null)
    {
      DataExport exporter = new DataExport(config, dao);

      byte[] buffer = exporter.export();

      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment;filename=" + config.getEntityName() + ".zip");
      ServletOutputStream out = response.getOutputStream();
      out.write(buffer);

      out.flush();
      out.close();
    }
  }
}
