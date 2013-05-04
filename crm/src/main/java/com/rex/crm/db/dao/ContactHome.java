package com.rex.crm.db.dao;

// Generated 2013-5-4 16:44:50 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.rex.crm.db.model.Contact;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Contact.
 * @see com.rex.crm.db.dao.Contact
 * @author Hibernate Tools
 */
public class ContactHome  extends BaseHibernateDao {

	private static final Log log = LogFactory.getLog(ContactHome.class);


	public void persist(Contact transientInstance) {
		log.debug("persisting Contact instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Contact instance) {
		log.debug("attaching dirty Contact instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contact instance) {
		log.debug("attaching clean Contact instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Contact persistentInstance) {
		log.debug("deleting Contact instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contact merge(Contact detachedInstance) {
		log.debug("merging Contact instance");
		try {
			Contact result = (Contact) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Contact findById(java.lang.Integer id) {
		log.debug("getting Contact instance with id: " + id);
		try {
			Contact instance = (Contact) getSession()
					.get("com.rex.crm.db.dao.Contact", id);
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

	public List<Contact> findByExample(Contact instance) {
		log.debug("finding Contact instance by example");
		try {
			List<Contact> results = (List<Contact>) getSession()
					.createCriteria("com.rex.crm.db.dao.Contact")
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
