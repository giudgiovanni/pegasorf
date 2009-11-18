package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Carichi.
 * @see it.infolabs.hibernate.Carichi
 * @author Hibernate Tools
 */
public class CarichiHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CarichiHome.class);

	private static final Log log = LogFactory.getLog(CarichiHome.class);

	private static final CarichiHome instance=new CarichiHome();

	private CarichiHome() {
		super();
	}

	public static CarichiHome getInstance() {
		return instance;
	}

	public void persist(Carichi transientInstance) {
		log.debug("persisting Carichi instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Carichi instance) {
		log.debug("attaching dirty Carichi instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Carichi instance) {
		log.debug("attaching clean Carichi instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Carichi persistentInstance) {
		log.debug("deleting Carichi instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Carichi merge(Carichi detachedInstance) {
		log.debug("merging Carichi instance");
		try {
			Carichi result = (Carichi) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Carichi findById(long id) {
		log.debug("getting Carichi instance with id: " + id);
		try {
			Carichi instance = (Carichi) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Carichi", id);
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

	public List<Carichi> findByExample(Carichi instance) {
		log.debug("finding Carichi instance by example");
		try {
			List<Carichi> results = (List<Carichi>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Carichi").add(
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
