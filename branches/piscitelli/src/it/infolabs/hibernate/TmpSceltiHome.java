package it.infolabs.hibernate;

// Generated 21-lug-2009 1.40.00 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TmpScelti.
 * @see it.infolabs.hibernate.TmpScelti
 * @author Hibernate Tools
 */
public class TmpSceltiHome {

	private static final Log log = LogFactory.getLog(TmpSceltiHome.class);

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

	public void persist(TmpScelti transientInstance) {
		log.debug("persisting TmpScelti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TmpScelti instance) {
		log.debug("attaching dirty TmpScelti instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TmpScelti instance) {
		log.debug("attaching clean TmpScelti instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TmpScelti persistentInstance) {
		log.debug("deleting TmpScelti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TmpScelti merge(TmpScelti detachedInstance) {
		log.debug("merging TmpScelti instance");
		try {
			TmpScelti result = (TmpScelti) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TmpScelti findById(it.infolabs.hibernate.TmpSceltiId id) {
		log.debug("getting TmpScelti instance with id: " + id);
		try {
			TmpScelti instance = (TmpScelti) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.TmpScelti", id);
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

	public List<TmpScelti> findByExample(TmpScelti instance) {
		log.debug("finding TmpScelti instance by example");
		try {
			List<TmpScelti> results = (List<TmpScelti>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.TmpScelti").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
