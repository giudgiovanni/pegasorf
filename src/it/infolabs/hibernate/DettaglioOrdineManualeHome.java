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
 * Home object for domain model class DettaglioOrdineManuale.
 * @see it.infolabs.hibernate.DettaglioOrdineManuale
 * @author Hibernate Tools
 */
public class DettaglioOrdineManualeHome {

	private static final Log log = LogFactory
			.getLog(DettaglioOrdineManualeHome.class);

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

	public void persist(DettaglioOrdineManuale transientInstance) {
		log.debug("persisting DettaglioOrdineManuale instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioOrdineManuale instance) {
		log.debug("attaching dirty DettaglioOrdineManuale instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioOrdineManuale instance) {
		log.debug("attaching clean DettaglioOrdineManuale instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioOrdineManuale persistentInstance) {
		log.debug("deleting DettaglioOrdineManuale instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioOrdineManuale merge(DettaglioOrdineManuale detachedInstance) {
		log.debug("merging DettaglioOrdineManuale instance");
		try {
			DettaglioOrdineManuale result = (DettaglioOrdineManuale) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioOrdineManuale findById(
			it.infolabs.hibernate.DettaglioOrdineManualeId id) {
		log.debug("getting DettaglioOrdineManuale instance with id: " + id);
		try {
			DettaglioOrdineManuale instance = (DettaglioOrdineManuale) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettaglioOrdineManuale", id);
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

	public List<DettaglioOrdineManuale> findByExample(
			DettaglioOrdineManuale instance) {
		log.debug("finding DettaglioOrdineManuale instance by example");
		try {
			List<DettaglioOrdineManuale> results = (List<DettaglioOrdineManuale>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioOrdineManuale")
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
