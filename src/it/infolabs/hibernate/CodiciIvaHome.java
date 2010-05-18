package it.infolabs.hibernate;

// Generated 16-mag-2010 11.17.52 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindAllEntityException;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CodiciIva.
 * @see it.infolabs.hibernate.CodiciIva
 * @author Hibernate Tools
 */
public class CodiciIvaHome extends BusinessObjectHome {

	private static final Logger logger = Logger.getLogger(CodiciIvaHome.class);

	private static final CodiciIvaHome instance = new CodiciIvaHome();

	private CodiciIvaHome() {
		super();
	}

	public static CodiciIvaHome getInstance() {
		return instance;
	}

	public void persist(CodiciIva transientInstance) {
		log.debug("persisting CodiciIva instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CodiciIva instance) {
		log.debug("attaching dirty CodiciIva instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CodiciIva instance) {
		log.debug("attaching clean CodiciIva instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CodiciIva persistentInstance) {
		log.debug("deleting CodiciIva instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CodiciIva merge(CodiciIva detachedInstance) {
		log.debug("merging CodiciIva instance");
		try {
			CodiciIva result = (CodiciIva) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CodiciIva findById(long id) {
		log.debug("getting CodiciIva instance with id: " + id);
		try {
			CodiciIva instance = (CodiciIva) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.CodiciIva", id);
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
	
	public CodiciIva findByCodice(String codice) {
		log.debug("getting CodiciIva instance with codice: " + codice);
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CodiciIva.class);
			crit.add(Restrictions.eq("codice", codice));
			CodiciIva instance = (CodiciIva)crit.uniqueResult();
			
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

	public List<CodiciIva> findByExample(CodiciIva instance) {
		log.debug("finding CodiciIva instance by example");
		try {
			List<CodiciIva> results = (List<CodiciIva>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.CodiciIva").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<CodiciIva> findAll() throws FindAllEntityException {
		log.debug("finding All CodiciIva instance");
		try {
			List<CodiciIva> results = (List<CodiciIva>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.CodiciIva").addOrder(Order.asc("id")).list();
			log.debug("find All CodiciIva successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All CodiciIva failed", re);
			throw re;
		}
	}
}
