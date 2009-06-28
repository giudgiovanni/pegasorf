package erreeffe.entity;

// Generated 28-giu-2009 12.52.21 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TmpEtichetteFornitori.
 * @see erreeffe.entity.TmpEtichetteFornitori
 * @author Hibernate Tools
 */
public class TmpEtichetteFornitoriHome {

	private static final Log log = LogFactory
			.getLog(TmpEtichetteFornitoriHome.class);

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

	public void persist(TmpEtichetteFornitori transientInstance) {
		log.debug("persisting TmpEtichetteFornitori instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TmpEtichetteFornitori instance) {
		log.debug("attaching dirty TmpEtichetteFornitori instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TmpEtichetteFornitori instance) {
		log.debug("attaching clean TmpEtichetteFornitori instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TmpEtichetteFornitori persistentInstance) {
		log.debug("deleting TmpEtichetteFornitori instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TmpEtichetteFornitori merge(TmpEtichetteFornitori detachedInstance) {
		log.debug("merging TmpEtichetteFornitori instance");
		try {
			TmpEtichetteFornitori result = (TmpEtichetteFornitori) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TmpEtichetteFornitori findById(long id) {
		log.debug("getting TmpEtichetteFornitori instance with id: " + id);
		try {
			TmpEtichetteFornitori instance = (TmpEtichetteFornitori) sessionFactory
					.getCurrentSession().get(
							"erreeffe.entity.TmpEtichetteFornitori", id);
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

	public List<TmpEtichetteFornitori> findByExample(
			TmpEtichetteFornitori instance) {
		log.debug("finding TmpEtichetteFornitori instance by example");
		try {
			List<TmpEtichetteFornitori> results = (List<TmpEtichetteFornitori>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.TmpEtichetteFornitori").add(
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
