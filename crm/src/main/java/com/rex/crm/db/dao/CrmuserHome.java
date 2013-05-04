package com.rex.crm.db.dao;

// Generated 2013-5-4 16:44:50 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.rex.crm.db.model.Crmuser;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Crmuser.
 * @see com.rex.crm.db.dao.Crmuser
 * @author Hibernate Tools
 */
public class CrmuserHome  extends BaseHibernateDao  {

	private static final Log log = LogFactory.getLog(CrmuserHome.class);

	

	public void persist(Crmuser transientInstance) {
		log.debug("persisting Crmuser instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Crmuser instance) {
		log.debug("attaching dirty Crmuser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Crmuser instance) {
		log.debug("attaching clean Crmuser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Crmuser persistentInstance) {
		log.debug("deleting Crmuser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Crmuser merge(Crmuser detachedInstance) {
		log.debug("merging Crmuser instance");
		try {
			Crmuser result = (Crmuser) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Crmuser findById(java.lang.Integer id) {
		log.debug("getting Crmuser instance with id: " + id);
		try {
			Crmuser instance = (Crmuser) getSession()
					.get("com.rex.crm.db.dao.Crmuser", id);
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

	public List<Crmuser> findByExample(Crmuser instance) {
		log.debug("finding Crmuser instance by example");
		try {
			List<Crmuser> results = (List<Crmuser>) getSession()
					.createCriteria("com.rex.crm.db.dao.Crmuser")
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
