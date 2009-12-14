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
 * Home object for domain model class ImmagineArticolo.
 * @see it.infolabs.hibernate.ImmagineArticolo
 * @author Hibernate Tools
 */
public class ImmagineArticoloHome extends BusinessObjectHome{

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ImmagineArticoloHome.class);

	private static final Log log = LogFactory.getLog(ImmagineArticoloHome.class);

	private static final ImmagineArticoloHome instance=new ImmagineArticoloHome();

	private ImmagineArticoloHome() {
		super();
	}

	public static ImmagineArticoloHome getInstance() {
		return instance;
	}

	public void persist(ImmagineArticolo transientInstance) {
		log.debug("persisting ImmagineArticolo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ImmagineArticolo instance) {
		log.debug("attaching dirty ImmagineArticolo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImmagineArticolo instance) {
		log.debug("attaching clean ImmagineArticolo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ImmagineArticolo persistentInstance) {
		log.debug("deleting ImmagineArticolo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImmagineArticolo merge(ImmagineArticolo detachedInstance) {
		log.debug("merging ImmagineArticolo instance");
		try {
			ImmagineArticolo result = (ImmagineArticolo) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ImmagineArticolo findById(long id) {
		log.debug("getting ImmagineArticolo instance with id: " + id);
		try {
			ImmagineArticolo instance = (ImmagineArticolo) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.ImmagineArticolo", id);
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

	public List<ImmagineArticolo> findByExample(ImmagineArticolo instance) {
		log.debug("finding ImmagineArticolo instance by example");
		try {
			List<ImmagineArticolo> results = (List<ImmagineArticolo>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.ImmagineArticolo").add(
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
