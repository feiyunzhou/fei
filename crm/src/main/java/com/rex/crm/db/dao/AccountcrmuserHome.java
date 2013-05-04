package com.rex.crm.db.dao;

// Generated 2013-5-4 16:44:50 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.rex.crm.db.model.Accountcrmuser;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Accountcrmuser.
 * @see com.rex.crm.db.dao.Accountcrmuser
 * @author Hibernate Tools
 */
public class AccountcrmuserHome extends BaseHibernateDao{

	private static final Log log = LogFactory.getLog(AccountcrmuserHome.class);
/*
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
*/
	public void persist(Accountcrmuser transientInstance) {
		log.debug("persisting Accountcrmuser instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Accountcrmuser instance) {
		log.debug("attaching dirty Accountcrmuser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accountcrmuser instance) {
		log.debug("attaching clean Accountcrmuser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Accountcrmuser persistentInstance) {
		log.debug("deleting Accountcrmuser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accountcrmuser merge(Accountcrmuser detachedInstance) {
		log.debug("merging Accountcrmuser instance");
		try {
			Accountcrmuser result = (Accountcrmuser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Accountcrmuser findById(java.lang.Integer id) {
		log.debug("getting Accountcrmuser instance with id: " + id);
		try {
			Accountcrmuser instance = (Accountcrmuser) getSession().get(
							"com.rex.crm.db.dao.Accountcrmuser", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Accountcrmuser> findByExample(Accountcrmuser instance) {
		log.debug("finding Accountcrmuser instance by example");
		try {
			List<Accountcrmuser> results = (List<Accountcrmuser>) getSession()
					.createCriteria("com.rex.crm.db.dao.Accountcrmuser")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
