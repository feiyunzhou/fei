package com.rex.crm.userlog;

public class LogInOut
{
  private String loginName;
  public String getLoginName()
  {
    return loginName;
  }
  public void setLoginName(String loginName)
  {
    this.loginName = loginName;
  }
  public String getSessionId()
  {
    return sessionId;
  }
  public void setSessionId(String sessionId)
  {
    this.sessionId = sessionId;
  }
  private long logints;
  public void setLogints(long logints)
  {
    this.logints = logints;
  }
  private String sessionId;
 
}
