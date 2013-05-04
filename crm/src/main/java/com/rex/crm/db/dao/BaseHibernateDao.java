package com.rex.crm.db.dao;

import org.hibernate.Session;

public class BaseHibernateDao implements IBaseHibernateDAO {
    private Session session=null;
    public static int DEFAULT_PAGESIZE = 20;
    public BaseHibernateDao(){
    	super();
    }
    public BaseHibernateDao(Session session){
    	super();
    	this.session = session;
    }
	public Session getSession() {
		if(session==null){
			session = HibernateSessionFactory.getSession();
		}
		return session;
	}
    public void setSession(Session session){
    	this.session = session;
    }
	
}
