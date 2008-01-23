package rf.hibernate;

// Generated 18-dic-2007 17.22.09 by Hibernate Tools 3.2.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class QtaScaricateView.
 * @see rf.hibernate.QtaScaricateView
 * @author Hibernate Tools
 */
public class QtaScaricateViewHome {

	private static final Log log = LogFactory
			.getLog(QtaScaricateViewHome.class);

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

	public void persist(QtaScaricateView transientInstance) {
		log.debug("persisting QtaScaricateView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(QtaScaricateView instance) {
		log.debug("attaching dirty QtaScaricateView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(QtaScaricateView instance) {
		log.debug("attaching clean QtaScaricateView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(QtaScaricateView persistentInstance) {
		log.debug("deleting QtaScaricateView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public QtaScaricateView merge(QtaScaricateView detachedInstance) {
		log.debug("merging QtaScaricateView instance");
		try {
			QtaScaricateView result = (QtaScaricateView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public QtaScaricateView findById(rf.hibernate.QtaScaricateViewId id) {
		log.debug("getting QtaScaricateView instance with id: " + id);
		try {
			QtaScaricateView instance = (QtaScaricateView) sessionFactory
					.getCurrentSession().get("rf.hibernate.QtaScaricateView",
							id);
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

	public List<QtaScaricateView> findByExample(QtaScaricateView instance) {
		log.debug("finding QtaScaricateView instance by example");
		try {
			List<QtaScaricateView> results = (List<QtaScaricateView>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.QtaScaricateView").add(
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
