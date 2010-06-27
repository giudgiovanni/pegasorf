package it.infolabs.hibernate;

// Generated 27-giu-2010 12.59.17 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Destinazione.
 * @see it.infolabs.hibernate.Destinazione
 * @author Hibernate Tools
 */
public class DestinazioneHome {

	private static final Log log = LogFactory.getLog(DestinazioneHome.class);

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

	public void persist(Destinazione transientInstance) {
		log.debug("persisting Destinazione instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Destinazione instance) {
		log.debug("attaching dirty Destinazione instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Destinazione instance) {
		log.debug("attaching clean Destinazione instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Destinazione persistentInstance) {
		log.debug("deleting Destinazione instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Destinazione merge(Destinazione detachedInstance) {
		log.debug("merging Destinazione instance");
		try {
			Destinazione result = (Destinazione) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Destinazione findById(long id) {
		log.debug("getting Destinazione instance with id: " + id);
		try {
			Destinazione instance = (Destinazione) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.Destinazione", id);
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

	public List<Destinazione> findByExample(Destinazione instance) {
		log.debug("finding Destinazione instance by example");
		try {
			List<Destinazione> results = (List<Destinazione>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Destinazione").add(
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
