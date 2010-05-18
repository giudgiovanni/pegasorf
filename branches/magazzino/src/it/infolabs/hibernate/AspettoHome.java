package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindAllEntityException;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aspetto.
 * @see it.infolabs.hibernate.Aspetto
 * @author Hibernate Tools
 */
public class AspettoHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AspettoHome.class);

	private static final AspettoHome instance=new AspettoHome();

	private AspettoHome() {
		super();
	}

	public static AspettoHome getInstance() {
		return instance;
	}

	public void persist(Aspetto transientInstance) {
		log.debug("persisting Aspetto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Aspetto instance) {
		log.debug("attaching dirty Aspetto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Aspetto instance) {
		log.debug("attaching clean Aspetto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Aspetto persistentInstance) {
		log.debug("deleting Aspetto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Aspetto merge(Aspetto detachedInstance) {
		log.debug("merging Aspetto instance");
		try {
			Aspetto result = (Aspetto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Aspetto findById(long id) {
		log.debug("getting Aspetto instance with id: " + id);
		try {
			Aspetto instance = (Aspetto) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Aspetto", id);
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

	public List<Aspetto> findByExample(Aspetto instance) {
		log.debug("finding Aspetto instance by example");
		try {
			List<Aspetto> results = (List<Aspetto>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Aspetto").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Aspetto> findAll() throws FindAllEntityException {
		log.debug("finding All Aspetto instance");
		try {
			List<Aspetto> results = (List<Aspetto>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Aspetto").addOrder(Order.asc("nome")).list();
			log.debug("find All Aspetto successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All Aspetto failed", re);
			throw re;
		}
	}
}
