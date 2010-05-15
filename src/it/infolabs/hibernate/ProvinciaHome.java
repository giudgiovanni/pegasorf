package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.DeleteEntityException;
import it.infolabs.hibernate.exception.FindAllEntityException;
import it.infolabs.hibernate.exception.FindByNotFoundException;
import it.infolabs.hibernate.exception.MergeEntityException;
import it.infolabs.hibernate.exception.PersistEntityException;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Provincia.
 * @see it.infolabs.hibernate.Provincia
 * @author Hibernate Tools
 */
public class ProvinciaHome extends BusinessObjectHome {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProvinciaHome.class);

	private static final Log log = LogFactory.getLog(ProvinciaHome.class);

	private static final ProvinciaHome instance = new ProvinciaHome();

	private ProvinciaHome() {
		super();
	}

	public static ProvinciaHome getInstance() {
		return instance;
	}

	public void persist(Provincia transientInstance) throws PersistEntityException{
		log.debug("persisting Provincia instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
		}
	}

	public void attachDirty(Provincia instance) {
		log.debug("attaching dirty Provincia instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Provincia instance) {
		log.debug("attaching clean Provincia instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Provincia persistentInstance) throws DeleteEntityException{
		log.debug("deleting Provincia instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Provincia merge(Provincia detachedInstance) throws MergeEntityException{
		log.debug("merging Provincia instance");
		try {
			Provincia result = (Provincia) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Provincia findById(long id) throws FindByNotFoundException{
		log.debug("getting Provincia instance with id: " + id);
		try {
			Provincia instance = (Provincia) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Provincia", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw new FindByNotFoundException();
		}
	}

	public List<Provincia> findByExample(Provincia instance) throws FindByNotFoundException{
		log.debug("finding Provincia instance by example");
		try {
			List<Provincia> results = (List<Provincia>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Provincia").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw new FindByNotFoundException();
		}
	}
	
	public List findAllProvince() throws FindAllEntityException{
		log.debug("finding All Province");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"it.infolabs.hibernate.Provincia").list();
			log.debug("find All Province successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All Province failed", re);
			throw new FindAllEntityException();
		}
	}
}
