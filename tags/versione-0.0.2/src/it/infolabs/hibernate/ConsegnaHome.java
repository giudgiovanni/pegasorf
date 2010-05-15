package it.infolabs.hibernate;

// Generated 6-mag-2010 0.12.01 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Consegna.
 * @see it.infolabs.hibernate.Consegna
 * @author Hibernate Tools
 */
public class ConsegnaHome {

	private static final Log log = LogFactory.getLog(ConsegnaHome.class);

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

	public void persist(Consegna transientInstance) {
		log.debug("persisting Consegna instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Consegna instance) {
		log.debug("attaching dirty Consegna instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Consegna instance) {
		log.debug("attaching clean Consegna instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Consegna persistentInstance) {
		log.debug("deleting Consegna instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Consegna merge(Consegna detachedInstance) {
		log.debug("merging Consegna instance");
		try {
			Consegna result = (Consegna) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Consegna findById(long id) {
		log.debug("getting Consegna instance with id: " + id);
		try {
			Consegna instance = (Consegna) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Consegna", id);
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

	public List<Consegna> findByExample(Consegna instance) {
		log.debug("finding Consegna instance by example");
		try {
			List<Consegna> results = (List<Consegna>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Consegna").add(
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
