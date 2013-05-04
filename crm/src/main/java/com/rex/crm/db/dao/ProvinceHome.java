package com.rex.crm.db.dao;

// Generated 2013-5-4 16:44:50 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.rex.crm.db.model.Province;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Province.
 * @see com.rex.crm.db.dao.Province
 * @author Hibernate Tools
 */
public class ProvinceHome  extends BaseHibernateDao  {

	private static final Log log = LogFactory.getLog(ProvinceHome.class);


	public void persist(Province transientInstance) {
		log.debug("persisting Province instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Province instance) {
		log.debug("attaching dirty Province instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Province instance) {
		log.debug("attaching clean Province instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Province persistentInstance) {
		log.debug("deleting Province instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Province merge(Province detachedInstance) {
		log.debug("merging Province instance");
		try {
			Province result = (Province) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Province findById(java.lang.Integer id) {
		log.debug("getting Province instance with id: " + id);
		try {
			Province instance = (Province) getSession()
					.get("com.rex.crm.db.dao.Province", id);
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

	public List<Province> findByExample(Province instance) {
		log.debug("finding Province instance by example");
		try {
			List<Province> results = (List<Province>) getSession()
					.createCriteria("com.rex.crm.db.dao.Province")
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
