package erreeffe.entity;

// Generated 28-giu-2009 12.52.21 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Progressivi.
 * @see erreeffe.entity.Progressivi
 * @author Hibernate Tools
 */
public class ProgressiviHome {

	private static final Log log = LogFactory.getLog(ProgressiviHome.class);

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

	public void persist(Progressivi transientInstance) {
		log.debug("persisting Progressivi instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Progressivi instance) {
		log.debug("attaching dirty Progressivi instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Progressivi instance) {
		log.debug("attaching clean Progressivi instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Progressivi persistentInstance) {
		log.debug("deleting Progressivi instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Progressivi merge(Progressivi detachedInstance) {
		log.debug("merging Progressivi instance");
		try {
			Progressivi result = (Progressivi) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Progressivi findById(int id) {
		log.debug("getting Progressivi instance with id: " + id);
		try {
			Progressivi instance = (Progressivi) sessionFactory
					.getCurrentSession().get("erreeffe.entity.Progressivi", id);
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

	public List<Progressivi> findByExample(Progressivi instance) {
		log.debug("finding Progressivi instance by example");
		try {
			List<Progressivi> results = (List<Progressivi>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.Progressivi")
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
