package com.rex.crm.util;

import java.io.IOException;
import java.util.Properties;

import com.rex.crm.common.NewDataFormPanel;

public class ReadFileUtil {
	public static String readFileAttribure(String fieldName){
		 Properties systemPeroperties = new Properties();
     	 try {
			systemPeroperties.load(NewDataFormPanel.class.getResourceAsStream("/crm.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	return systemPeroperties.getProperty(fieldName);
	}
}
