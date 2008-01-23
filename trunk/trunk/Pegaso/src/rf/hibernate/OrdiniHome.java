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
 * Home object for domain model class Ordini.
 * @see rf.hibernate.Ordini
 * @author Hibernate Tools
 */
public class OrdiniHome {

	private static final Log log = LogFactory.getLog(OrdiniHome.class);

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

	public void persist(Ordini transientInstance) {
		log.debug("persisting Ordini instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ordini instance) {
		log.debug("attaching dirty Ordini instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ordini instance) {
		log.debug("attaching clean Ordini instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Ordini persistentInstance) {
		log.debug("deleting Ordini instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ordini merge(Ordini detachedInstance) {
		log.debug("merging Ordini instance");
		try {
			Ordini result = (Ordini) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ordini findById(long id) {
		log.debug("getting Ordini instance with id: " + id);
		try {
			Ordini instance = (Ordini) sessionFactory.getCurrentSession().get(
					"rf.hibernate.Ordini", id);
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

	public List<Ordini> findByExample(Ordini instance) {
		log.debug("finding Ordini instance by example");
		try {
			List<Ordini> results = (List<Ordini>) sessionFactory
					.getCurrentSession().createCriteria("rf.hibernate.Ordini")
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
