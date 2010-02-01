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
 * Home object for domain model class DettaglioCarico.
 * @see it.infolabs.hibernate.DettaglioCarico
 * @author Hibernate Tools
 */
public class DettaglioCaricoHome {

	private static final Log log = LogFactory.getLog(DettaglioCaricoHome.class);

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

	public void persist(DettaglioCarico transientInstance) {
		log.debug("persisting DettaglioCarico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioCarico instance) {
		log.debug("attaching dirty DettaglioCarico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioCarico instance) {
		log.debug("attaching clean DettaglioCarico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioCarico persistentInstance) {
		log.debug("deleting DettaglioCarico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioCarico merge(DettaglioCarico detachedInstance) {
		log.debug("merging DettaglioCarico instance");
		try {
			DettaglioCarico result = (DettaglioCarico) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioCarico findById(it.infolabs.hibernate.DettaglioCaricoId id) {
		log.debug("getting DettaglioCarico instance with id: " + id);
		try {
			DettaglioCarico instance = (DettaglioCarico) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettaglioCarico", id);
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

	public List<DettaglioCarico> findByExample(DettaglioCarico instance) {
		log.debug("finding DettaglioCarico instance by example");
		try {
			List<DettaglioCarico> results = (List<DettaglioCarico>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioCarico").add(
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
