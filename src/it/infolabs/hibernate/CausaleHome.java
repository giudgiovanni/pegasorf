package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindAllEntityException;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Causale.
 * @see it.infolabs.hibernate.Causale
 * @author Hibernate Tools
 */
public class CausaleHome extends BusinessObjectHome {

	private static final Logger logger = Logger.getLogger(CausaleHome.class);

	private static final CausaleHome instance = new CausaleHome();

	private CausaleHome() {
		super();
	}

	public static CausaleHome getInstance() {
		return instance;
	}

	public void persist(Causale transientInstance) {
		log.debug("persisting Causale instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Causale instance) {
		log.debug("attaching dirty Causale instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Causale instance) {
		log.debug("attaching clean Causale instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Causale persistentInstance) {
		log.debug("deleting Causale instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Causale merge(Causale detachedInstance) {
		log.debug("merging Causale instance");
		try {
			Causale result = (Causale) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Causale findById(long id) {
		log.debug("getting Causale instance with id: " + id);
		try {
			Causale instance = (Causale) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Causale", id);
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

	public List<Causale> findByExample(Causale instance) {
		log.debug("finding Causale instance by example");
		try {
			List<Causale> results = (List<Causale>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Causale").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Causale> findAll() throws FindAllEntityException {
		log.debug("finding All Causale instance");
		try {
			List<Causale> results = (List<Causale>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Causale").addOrder(Order.asc("nome")).list();
			log.debug("find All Causale successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All Causale failed", re);
			throw re;
		}
	}
}
