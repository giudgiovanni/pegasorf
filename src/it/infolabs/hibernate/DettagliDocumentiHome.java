package it.infolabs.hibernate;

// Generated 21-nov-2009 23.21.45 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DettagliDocumenti.
 * @see it.infolabs.hibernate.DettagliDocumenti
 * @author Hibernate Tools
 */
public class DettagliDocumentiHome {

	private static final Log log = LogFactory
			.getLog(DettagliDocumentiHome.class);

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

	public void persist(DettagliDocumenti transientInstance) {
		log.debug("persisting DettagliDocumenti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettagliDocumenti instance) {
		log.debug("attaching dirty DettagliDocumenti instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettagliDocumenti instance) {
		log.debug("attaching clean DettagliDocumenti instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettagliDocumenti persistentInstance) {
		log.debug("deleting DettagliDocumenti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettagliDocumenti merge(DettagliDocumenti detachedInstance) {
		log.debug("merging DettagliDocumenti instance");
		try {
			DettagliDocumenti result = (DettagliDocumenti) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettagliDocumenti findById(int id) {
		log.debug("getting DettagliDocumenti instance with id: " + id);
		try {
			DettagliDocumenti instance = (DettagliDocumenti) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettagliDocumenti", id);
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

	public List<DettagliDocumenti> findByExample(DettagliDocumenti instance) {
		log.debug("finding DettagliDocumenti instance by example");
		try {
			List<DettagliDocumenti> results = (List<DettagliDocumenti>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettagliDocumenti").add(
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
