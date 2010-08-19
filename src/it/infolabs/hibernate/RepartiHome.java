package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.math.BigInteger;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Reparti.
 * @see it.infolabs.hibernate.Reparti
 * @author Hibernate Tools
 */
public class RepartiHome extends BusinessObjectHome {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepartiHome.class);

	private static final Log log = LogFactory.getLog(RepartiHome.class);

	private static final RepartiHome instance = new RepartiHome();

	private RepartiHome() {
		super();
	}

	public static RepartiHome getInstance() {
		return instance;
	}

	public void persist(Reparti transientInstance) {
		log.debug("persisting Reparti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Reparti instance) {
		log.debug("attaching dirty Reparti instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reparti instance) {
		log.debug("attaching clean Reparti instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Reparti persistentInstance) {
		log.debug("deleting Reparti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reparti merge(Reparti detachedInstance) {
		log.debug("merging Reparti instance");
		try {
			Reparti result = (Reparti) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Reparti findById(long id) {
		log.debug("getting Reparti instance with id: " + id);
		try {
			Reparti instance = (Reparti) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Reparti", id);
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
	
	public long findRepartoByArticolo(long id) {
		log.debug("getting Reparto by Articolo: " + id);
		try {
			List result = (List)sessionFactory.getCurrentSession().createSQLQuery("select rep.idreparto from reparti rep left join articoli art on rep.idreparto = art.idreparto where art.idarticolo = " + id).list();
			if (result.size() > 0)
				return ((BigInteger)result.get(0)).longValue();
			log.debug("finding Reparto by Articolo successful");
			return 0;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Reparti> findByExample(Reparti instance) {
		log.debug("finding Reparti instance by example");
		try {
			List<Reparti> results = (List<Reparti>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Reparti").add(
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
