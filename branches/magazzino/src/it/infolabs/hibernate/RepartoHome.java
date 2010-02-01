package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Reparto.
 * @see it.infolabs.hibernate.Reparto
 * @author Hibernate Tools
 */
public class RepartoHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepartoHome.class);

	private static final RepartoHome instance=new RepartoHome();

	private RepartoHome() {
		super();
	}

	public static RepartoHome getInstance() {
		return instance;
	}

	public void persist(Reparto transientInstance) {
		log.debug("persisting Reparto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Reparto instance) {
		log.debug("attaching dirty Reparto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reparto instance) {
		log.debug("attaching clean Reparto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Reparto persistentInstance) {
		log.debug("deleting Reparto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reparto merge(Reparto detachedInstance) {
		log.debug("merging Reparto instance");
		try {
			Reparto result = (Reparto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Reparto findById(long id) {
		log.debug("getting Reparto instance with id: " + id);
		try {
			Reparto instance = (Reparto) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Reparto", id);
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

	public List<Reparto> findByExample(Reparto instance) {
		log.debug("finding Reparto instance by example");
		try {
			List<Reparto> results = (List<Reparto>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Reparto").add(
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
