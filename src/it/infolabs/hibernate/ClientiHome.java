package it.infolabs.hibernate;

// Generated 21-nov-2009 23.21.45 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Clienti.
 * @see it.infolabs.hibernate.Clienti
 * @author Hibernate Tools
 */
public class ClientiHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(ClientiHome.class);

	private static final ClientiHome instance=new ClientiHome();

	private ClientiHome() {
		super();
	}

	public static ClientiHome getInstance() {
		return instance;
	}

	public void persist(Clienti transientInstance) {
		log.debug("persisting Clienti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Clienti instance) {
		log.debug("attaching dirty Clienti instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clienti instance) {
		log.debug("attaching clean Clienti instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Clienti persistentInstance) {
		log.debug("deleting Clienti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Clienti merge(Clienti detachedInstance) {
		log.debug("merging Clienti instance");
		try {
			Clienti result = (Clienti) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Clienti findById(long id) {
		log.debug("getting Clienti instance with id: " + id);
		try {
			Clienti instance = (Clienti) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Clienti", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance==null?new Clienti():instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Clienti> findByExample(Clienti instance) {
		log.debug("finding Clienti instance by example");
		try {
			List<Clienti> results = (List<Clienti>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Clienti").add(
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
