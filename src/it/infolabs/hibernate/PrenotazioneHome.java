package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Prenotazione.
 * @see it.infolabs.hibernate.Prenotazione
 * @author Hibernate Tools
 */
public class PrenotazioneHome {

	private static final Log log = LogFactory.getLog(PrenotazioneHome.class);

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

	public void persist(Prenotazione transientInstance) {
		log.debug("persisting Prenotazione instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Prenotazione instance) {
		log.debug("attaching dirty Prenotazione instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Prenotazione instance) {
		log.debug("attaching clean Prenotazione instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Prenotazione persistentInstance) {
		log.debug("deleting Prenotazione instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Prenotazione merge(Prenotazione detachedInstance) {
		log.debug("merging Prenotazione instance");
		try {
			Prenotazione result = (Prenotazione) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Prenotazione findById(int id) {
		log.debug("getting Prenotazione instance with id: " + id);
		try {
			Prenotazione instance = (Prenotazione) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.Prenotazione", id);
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

	public List<Prenotazione> findByExample(Prenotazione instance) {
		log.debug("finding Prenotazione instance by example");
		try {
			List<Prenotazione> results = (List<Prenotazione>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Prenotazione").add(
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
