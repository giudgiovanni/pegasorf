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
 * Home object for domain model class DettaglioOrdini.
 * @see it.infolabs.hibernate.DettaglioScarico
 * @author Hibernate Tools
 */
public class DettaglioScaricoHome {

	private static final Log log = LogFactory.getLog(DettaglioScaricoHome.class);

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

	public void persist(DettaglioScarico transientInstance) {
		log.debug("persisting DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioScarico instance) {
		log.debug("attaching dirty DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioScarico instance) {
		log.debug("attaching clean DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioScarico persistentInstance) {
		log.debug("deleting DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioScarico merge(DettaglioScarico detachedInstance) {
		log.debug("merging DettaglioOrdini instance");
		try {
			DettaglioScarico result = (DettaglioScarico) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioScarico findById(it.infolabs.hibernate.DettaglioScaricoId id) {
		log.debug("getting DettaglioOrdini instance with id: " + id);
		try {
			DettaglioScarico instance = (DettaglioScarico) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettaglioOrdini", id);
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

	public List<DettaglioScarico> findByExample(DettaglioScarico instance) {
		log.debug("finding DettaglioOrdini instance by example");
		try {
			List<DettaglioScarico> results = (List<DettaglioScarico>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioOrdini").add(
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
