package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DettaglioScaricoManuale.
 * @see it.infolabs.hibernate.DettaglioScaricoManuale
 * @author Hibernate Tools
 */
public class DettaglioScaricoManualeHome {

	private static final Log log = LogFactory
			.getLog(DettaglioScaricoManualeHome.class);

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

	public void persist(DettaglioScaricoManuale transientInstance) {
		log.debug("persisting DettaglioScaricoManuale instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioScaricoManuale instance) {
		log.debug("attaching dirty DettaglioScaricoManuale instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioScaricoManuale instance) {
		log.debug("attaching clean DettaglioScaricoManuale instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioScaricoManuale persistentInstance) {
		log.debug("deleting DettaglioScaricoManuale instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioScaricoManuale merge(
			DettaglioScaricoManuale detachedInstance) {
		log.debug("merging DettaglioScaricoManuale instance");
		try {
			DettaglioScaricoManuale result = (DettaglioScaricoManuale) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioScaricoManuale findById(
			it.infolabs.hibernate.DettaglioScaricoManualeId id) {
		log.debug("getting DettaglioScaricoManuale instance with id: " + id);
		try {
			DettaglioScaricoManuale instance = (DettaglioScaricoManuale) sessionFactory
					.getCurrentSession()
					.get("it.infolabs.hibernate.DettaglioScaricoManuale", id);
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

	public List<DettaglioScaricoManuale> findByExample(
			DettaglioScaricoManuale instance) {
		log.debug("finding DettaglioScaricoManuale instance by example");
		try {
			List<DettaglioScaricoManuale> results = (List<DettaglioScaricoManuale>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioScaricoManuale")
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