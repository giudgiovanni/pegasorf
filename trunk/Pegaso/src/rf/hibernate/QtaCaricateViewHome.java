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
 * Home object for domain model class QtaCaricateView.
 * @see rf.hibernate.QtaCaricateView
 * @author Hibernate Tools
 */
public class QtaCaricateViewHome {

	private static final Log log = LogFactory.getLog(QtaCaricateViewHome.class);

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

	public void persist(QtaCaricateView transientInstance) {
		log.debug("persisting QtaCaricateView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(QtaCaricateView instance) {
		log.debug("attaching dirty QtaCaricateView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(QtaCaricateView instance) {
		log.debug("attaching clean QtaCaricateView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(QtaCaricateView persistentInstance) {
		log.debug("deleting QtaCaricateView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public QtaCaricateView merge(QtaCaricateView detachedInstance) {
		log.debug("merging QtaCaricateView instance");
		try {
			QtaCaricateView result = (QtaCaricateView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public QtaCaricateView findById(rf.hibernate.QtaCaricateViewId id) {
		log.debug("getting QtaCaricateView instance with id: " + id);
		try {
			QtaCaricateView instance = (QtaCaricateView) sessionFactory
					.getCurrentSession()
					.get("rf.hibernate.QtaCaricateView", id);
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

	public List<QtaCaricateView> findByExample(QtaCaricateView instance) {
		log.debug("finding QtaCaricateView instance by example");
		try {
			List<QtaCaricateView> results = (List<QtaCaricateView>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.QtaCaricateView").add(
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
