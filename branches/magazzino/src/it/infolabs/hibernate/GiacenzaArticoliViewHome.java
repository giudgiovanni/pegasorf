package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class GiacenzaArticoliView.
 * @see it.infolabs.hibernate.GiacenzaArticoliView
 * @author Hibernate Tools
 */
public class GiacenzaArticoliViewHome {

	private static final Log log = LogFactory
			.getLog(GiacenzaArticoliViewHome.class);

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

	public void persist(GiacenzaArticoliView transientInstance) {
		log.debug("persisting GiacenzaArticoliView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GiacenzaArticoliView instance) {
		log.debug("attaching dirty GiacenzaArticoliView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GiacenzaArticoliView instance) {
		log.debug("attaching clean GiacenzaArticoliView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GiacenzaArticoliView persistentInstance) {
		log.debug("deleting GiacenzaArticoliView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GiacenzaArticoliView merge(GiacenzaArticoliView detachedInstance) {
		log.debug("merging GiacenzaArticoliView instance");
		try {
			GiacenzaArticoliView result = (GiacenzaArticoliView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GiacenzaArticoliView findById(
			it.infolabs.hibernate.GiacenzaArticoliViewId id) {
		log.debug("getting GiacenzaArticoliView instance with id: " + id);
		try {
			GiacenzaArticoliView instance = (GiacenzaArticoliView) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.GiacenzaArticoliView", id);
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

	public List<GiacenzaArticoliView> findByExample(
			GiacenzaArticoliView instance) {
		log.debug("finding GiacenzaArticoliView instance by example");
		try {
			List<GiacenzaArticoliView> results = (List<GiacenzaArticoliView>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.GiacenzaArticoliView").add(
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
