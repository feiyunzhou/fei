package com.rex.crm.common;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class ErrorPromptPage extends WebPage{
	public ErrorPromptPage(){
		add(new Label("prompt","此链接已失效！"));
	}

}
