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
 * Home object for domain model class DettaglioDocumento.
 * @see it.infolabs.hibernate.DettaglioDocumento
 * @author Hibernate Tools
 */
public class DettaglioDocumentoHome {

	private static final Log log = LogFactory
			.getLog(DettaglioDocumentoHome.class);

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

	public void persist(DettaglioDocumento transientInstance) {
		log.debug("persisting DettaglioDocumento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioDocumento instance) {
		log.debug("attaching dirty DettaglioDocumento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioDocumento instance) {
		log.debug("attaching clean DettaglioDocumento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioDocumento persistentInstance) {
		log.debug("deleting DettaglioDocumento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioDocumento merge(DettaglioDocumento detachedInstance) {
		log.debug("merging DettaglioDocumento instance");
		try {
			DettaglioDocumento result = (DettaglioDocumento) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioDocumento findById(long id) {
		log.debug("getting DettaglioDocumento instance with id: " + id);
		try {
			DettaglioDocumento instance = (DettaglioDocumento) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettaglioDocumento", id);
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

	public List<DettaglioDocumento> findByExample(DettaglioDocumento instance) {
		log.debug("finding DettaglioDocumento instance by example");
		try {
			List<DettaglioDocumento> results = (List<DettaglioDocumento>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioDocumento").add(
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
