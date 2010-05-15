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
 * Home object for domain model class Ricevuta.
 * @see it.infolabs.hibernate.Ricevuta
 * @author Hibernate Tools
 */
public class RicevutaHome {

	private static final Log log = LogFactory.getLog(RicevutaHome.class);

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

	public void persist(Ricevuta transientInstance) {
		log.debug("persisting Ricevuta instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ricevuta instance) {
		log.debug("attaching dirty Ricevuta instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ricevuta instance) {
		log.debug("attaching clean Ricevuta instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Ricevuta persistentInstance) {
		log.debug("deleting Ricevuta instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ricevuta merge(Ricevuta detachedInstance) {
		log.debug("merging Ricevuta instance");
		try {
			Ricevuta result = (Ricevuta) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ricevuta findById(long id) {
		log.debug("getting Ricevuta instance with id: " + id);
		try {
			Ricevuta instance = (Ricevuta) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Ricevuta", id);
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

	public List<Ricevuta> findByExample(Ricevuta instance) {
		log.debug("finding Ricevuta instance by example");
		try {
			List<Ricevuta> results = (List<Ricevuta>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Ricevuta").add(
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
