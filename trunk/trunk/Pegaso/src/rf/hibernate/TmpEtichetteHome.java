package rf.hibernate;

// Generated 18-dic-2007 17.22.09 by Hibernate Tools 3.2.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class TmpEtichette.
 * @see rf.hibernate.TmpEtichette
 * @author Hibernate Tools
 */
public class TmpEtichetteHome {

	private static final Log log = LogFactory.getLog(TmpEtichetteHome.class);

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

	public void persist(TmpEtichette transientInstance) {
		log.debug("persisting TmpEtichette instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TmpEtichette instance) {
		log.debug("attaching dirty TmpEtichette instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TmpEtichette instance) {
		log.debug("attaching clean TmpEtichette instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TmpEtichette persistentInstance) {
		log.debug("deleting TmpEtichette instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TmpEtichette merge(TmpEtichette detachedInstance) {
		log.debug("merging TmpEtichette instance");
		try {
			TmpEtichette result = (TmpEtichette) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TmpEtichette findById(rf.hibernate.TmpEtichetteId id) {
		log.debug("getting TmpEtichette instance with id: " + id);
		try {
			TmpEtichette instance = (TmpEtichette) sessionFactory
					.getCurrentSession().get("rf.hibernate.TmpEtichette", id);
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

	public List<TmpEtichette> findByExample(TmpEtichette instance) {
		log.debug("finding TmpEtichette instance by example");
		try {
			List<TmpEtichette> results = (List<TmpEtichette>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.TmpEtichette").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
