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
package com.rex.crm.common.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.gson.Gson;

/**
 * Concrete, extending panel.
 * 
 * @author Feiyun Zhou
 */
public class TreePanel extends Panel
{
	String jsonTreeData;
	public TreePanel(String id,String jsonTreeData)
	{
		super(id);
		this.jsonTreeData = jsonTreeData;
       
		
	}
	
	public TreePanel(String id, IModel<?> model)
	{
		super(id, model);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		Map<String, Object> map = new HashMap<>();
		map.put("regionData", jsonTreeData);
		 CharSequence pageUrl = urlFor(this.getPage().getClass(),null);
		 map.put("pageUrl", pageUrl.toString());
		PackageTextTemplate ptt = new PackageTextTemplate( getClass(), "treepanel.js" );
		//OnDomReadyHeaderItem onDomReadyHeaderItem = OnDomReadyHeaderItem.forScript( ptt.asString( map ) );
		//response.render(onDomReadyHeaderItem);
		System.out.println(ptt.asString(map));
		response.render(JavaScriptHeaderItem.forScript(ptt.asString(map),null));
		
		try {
			ptt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
