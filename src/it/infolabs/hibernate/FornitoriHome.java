package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.DeleteEntityException;
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
 * Home object for domain model class Fornitori.
 * @see it.infolabs.hibernate.Fornitori
 * @author Hibernate Tools
 */
public class FornitoriHome extends BusinessObjectHome{

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FornitoriHome.class);

	private static final Log log = LogFactory.getLog(FornitoriHome.class);

	private static final FornitoriHome instance = new FornitoriHome();

	private FornitoriHome() {
		super();
	}

	public static FornitoriHome getInstance() {
		return instance;
	}

	public void persist(Fornitori transientInstance) throws PersistEntityException{
		log.debug("persisting Fornitori instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
		}
	}

	public void attachDirty(Fornitori instance) {
		log.debug("attaching dirty Fornitori instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fornitori instance) {
		log.debug("attaching clean Fornitori instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fornitori persistentInstance) throws DeleteEntityException{
		log.debug("deleting Fornitori instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Fornitori merge(Fornitori detachedInstance) throws MergeEntityException {
		log.debug("merging Fornitori instance");
		try {
			Fornitori result = (Fornitori) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Fornitori findById(long id) throws FindByNotFoundException{
		log.debug("getting Fornitori instance with id: " + id);
		try {
			Fornitori instance = (Fornitori) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Fornitori", id);
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

	public List<Fornitori> findByExample(Fornitori instance) throws FindByNotFoundException {
		log.debug("finding Fornitori instance by example");
		try {
			List<Fornitori> results = (List<Fornitori>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Fornitori").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw new FindByNotFoundException();
		}
	}
}
