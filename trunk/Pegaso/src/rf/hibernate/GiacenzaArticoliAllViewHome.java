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
 * Home object for domain model class GiacenzaArticoliAllView.
 * @see rf.hibernate.GiacenzaArticoliAllView
 * @author Hibernate Tools
 */
public class GiacenzaArticoliAllViewHome {

	private static final Log log = LogFactory
			.getLog(GiacenzaArticoliAllViewHome.class);

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

	public void persist(GiacenzaArticoliAllView transientInstance) {
		log.debug("persisting GiacenzaArticoliAllView instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GiacenzaArticoliAllView instance) {
		log.debug("attaching dirty GiacenzaArticoliAllView instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GiacenzaArticoliAllView instance) {
		log.debug("attaching clean GiacenzaArticoliAllView instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GiacenzaArticoliAllView persistentInstance) {
		log.debug("deleting GiacenzaArticoliAllView instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GiacenzaArticoliAllView merge(
			GiacenzaArticoliAllView detachedInstance) {
		log.debug("merging GiacenzaArticoliAllView instance");
		try {
			GiacenzaArticoliAllView result = (GiacenzaArticoliAllView) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GiacenzaArticoliAllView findById(
			rf.hibernate.GiacenzaArticoliAllViewId id) {
		log.debug("getting GiacenzaArticoliAllView instance with id: " + id);
		try {
			GiacenzaArticoliAllView instance = (GiacenzaArticoliAllView) sessionFactory
					.getCurrentSession().get(
							"rf.hibernate.GiacenzaArticoliAllView", id);
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

	public List<GiacenzaArticoliAllView> findByExample(
			GiacenzaArticoliAllView instance) {
		log.debug("finding GiacenzaArticoliAllView instance by example");
		try {
			List<GiacenzaArticoliAllView> results = (List<GiacenzaArticoliAllView>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.GiacenzaArticoliAllView").add(
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
