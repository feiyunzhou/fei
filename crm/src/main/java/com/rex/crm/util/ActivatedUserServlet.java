package com.rex.crm.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rex.crm.PageFactory;
import com.rex.crm.SearchCRMUserPage;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.db.DAOImpl;

public class ActivatedUserServlet extends HttpServlet{
	private String getUserByuserCode = "";
	//根据客户code获取状态为未激活的用户，如果存在则跳转界面
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");//设置参数解码类型，必须和页面中一致
		    String activation=request.getParameter("getUserByuserCode");
		    System.out.println("userCode:"+activation);
		    CRMUser user = DAOImpl.getUserByActivation(activation);
		    //判断如果user不等于null，则跳转页面，设置密码
		    if(null!=user){
		    	response.sendRedirect("main/java/UserActivated.html");
		    	//setResponsePage(PageFactory.createPage(entity.getName()));
		    	//mountPage("/mount/searchCRMUser", SearchCRMUserPage.class);
		    }
		    
	}
	public String getGetUserByuserCode() {
		return getUserByuserCode;
	}
	public void setGetUserByuserCode(String getUserByuserCode) {
		this.getUserByuserCode = getUserByuserCode;
	}
	
	
}
