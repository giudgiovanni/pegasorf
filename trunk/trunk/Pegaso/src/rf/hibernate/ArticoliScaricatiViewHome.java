package rf.hibernate;

// Generated 18-dic-2007 17.22.09 by Hibernate Tools 3.2.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ArticoliScaricatiView.
 * @see rf.hibernate.ArticoliScaricatiView
 * @author Hibernate Tools
 */
public class ArticoliScaricatiViewHome {

	private static final Log log = LogFactory
			.getLog(ArticoliScaricatiViewHome.class);

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

	public void persist(ArticoliScaricatiView transientInstance) {
		log.debug("persisting ArticoliScaricatiView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ArticoliScaricatiView instance) {
		log.debug("attaching dirty ArticoliScaricatiView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ArticoliScaricatiView instance) {
		log.debug("attaching clean ArticoliScaricatiView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ArticoliScaricatiView persistentInstance) {
		log.debug("deleting ArticoliScaricatiView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ArticoliScaricatiView merge(ArticoliScaricatiView detachedInstance) {
		log.debug("merging ArticoliScaricatiView instance");
		try {
			ArticoliScaricatiView result = (ArticoliScaricatiView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ArticoliScaricatiView findById(
			rf.hibernate.ArticoliScaricatiViewId id) {
		log.debug("getting ArticoliScaricatiView instance with id: " + id);
		try {
			ArticoliScaricatiView instance = (ArticoliScaricatiView) sessionFactory
					.getCurrentSession().get(
							"rf.hibernate.ArticoliScaricatiView", id);
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

	public List<ArticoliScaricatiView> findByExample(
			ArticoliScaricatiView instance) {
		log.debug("finding ArticoliScaricatiView instance by example");
		try {
			List<ArticoliScaricatiView> results = (List<ArticoliScaricatiView>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.ArticoliScaricatiView").add(
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
