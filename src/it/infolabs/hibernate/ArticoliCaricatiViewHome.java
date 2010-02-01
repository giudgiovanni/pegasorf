package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ArticoliCaricatiView.
 * @see it.infolabs.hibernate.ArticoliCaricatiView
 * @author Hibernate Tools
 */
public class ArticoliCaricatiViewHome {

	private static final Log log = LogFactory
			.getLog(ArticoliCaricatiViewHome.class);

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

	public void persist(ArticoliCaricatiView transientInstance) {
		log.debug("persisting ArticoliCaricatiView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ArticoliCaricatiView instance) {
		log.debug("attaching dirty ArticoliCaricatiView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ArticoliCaricatiView instance) {
		log.debug("attaching clean ArticoliCaricatiView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ArticoliCaricatiView persistentInstance) {
		log.debug("deleting ArticoliCaricatiView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ArticoliCaricatiView merge(ArticoliCaricatiView detachedInstance) {
		log.debug("merging ArticoliCaricatiView instance");
		try {
			ArticoliCaricatiView result = (ArticoliCaricatiView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ArticoliCaricatiView findById(
			it.infolabs.hibernate.ArticoliCaricatiViewId id) {
		log.debug("getting ArticoliCaricatiView instance with id: " + id);
		try {
			ArticoliCaricatiView instance = (ArticoliCaricatiView) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.ArticoliCaricatiView", id);
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

	public List<ArticoliCaricatiView> findByExample(
			ArticoliCaricatiView instance) {
		log.debug("finding ArticoliCaricatiView instance by example");
		try {
			List<ArticoliCaricatiView> results = (List<ArticoliCaricatiView>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.ArticoliCaricatiView").add(
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
