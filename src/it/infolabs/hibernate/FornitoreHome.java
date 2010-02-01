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
 * Home object for domain model class Fornitore.
 * @see it.infolabs.hibernate.Fornitore
 * @author Hibernate Tools
 */
public class FornitoreHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FornitoreHome.class);

	private static final FornitoreHome instance=new FornitoreHome();

	private FornitoreHome() {
		super();
	}

	public static FornitoreHome getInstance() {
		return instance;
	}

	public void persist(Fornitore transientInstance) {
		log.debug("persisting Fornitore instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fornitore instance) {
		log.debug("attaching dirty Fornitore instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fornitore instance) {
		log.debug("attaching clean Fornitore instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fornitore persistentInstance) {
		log.debug("deleting Fornitore instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fornitore merge(Fornitore detachedInstance) {
		log.debug("merging Fornitore instance");
		try {
			Fornitore result = (Fornitore) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fornitore findById(long id) {
		log.debug("getting Fornitore instance with id: " + id);
		try {
			Fornitore instance = (Fornitore) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Fornitore", id);
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

	public List<Fornitore> findByExample(Fornitore instance) {
		log.debug("finding Fornitore instance by example");
		try {
			List<Fornitore> results = (List<Fornitore>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Fornitore").add(
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
