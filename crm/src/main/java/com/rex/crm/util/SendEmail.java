package com.rex.crm.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.sun.mail.smtp.SMTPTransport;

public class SendEmail {
	private static final Logger logger = Logger.getLogger(SendEmail.class);
	  //发送有邮件方法
  	public static boolean sendMail(String code,String sendEmail){
  		Session sendMailSession = null;
  		SMTPTransport transport = null;
  		String str = "请点击链接激活用户:";
		try {
			str = new String(str.getBytes("UTF-8"),"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuilder emailContent = new StringBuilder(str);
		CRMUtility readFileUtil = new CRMUtility();
		emailContent.append(readFileUtil.readFileAttribure("http"));
		emailContent.append(readFileUtil.readFileAttribure("url"));
		emailContent.append("/");
		emailContent.append(readFileUtil.readFileAttribure("project"));
		emailContent.append("/");
		emailContent.append(readFileUtil.readFileAttribure("jumpage"));
		emailContent.append("?");
		emailContent.append(readFileUtil.readFileAttribure("parameter"));
		emailContent.append("=");
		emailContent.append(code);
		Properties props = new Properties();
		// 与服务器建立Session的参数设置
		props.put("mail.smtp.host", "smtp.163.com"); // 写上你的SMTP服务器。
		props.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证。
		SMTPAuthenticator auth = new SMTPAuthenticator("accpcui@163.com", "992041099"); // 用户名，密码。
		sendMailSession = Session.getInstance(props, auth); // 建立连接。
		// SMTPTransport用来发送邮件。
		try {
			transport = (SMTPTransport) sendMailSession.getTransport("smtp");
			transport.connect();
		// 创建邮件。
		Message newMessage = new MimeMessage(sendMailSession);
		newMessage.setFrom(new InternetAddress("accpcui@163.com"));
		newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail));
		newMessage.setSubject("Your crm Activation Email ");
		newMessage.setSentDate(new Date());
		newMessage.setText(emailContent.toString());
		Transport.send(newMessage);
		transport.close();  
		logger.info("accpcui@163.com发送邮件到"+sendEmail+",已发送成功！");
		return  true;  
		} catch (Exception e) {
			System.err.println("邮件发送失败！"+e);  
		    return  false; 
		}
  	}
}
