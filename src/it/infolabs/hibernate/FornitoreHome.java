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
 * @see it.infolabs.hibernate.Fornitore
 * @author Hibernate Tools
 */
public class FornitoreHome extends BusinessObjectHome{

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FornitoreHome.class);

	private static final Log log = LogFactory.getLog(FornitoreHome.class);

	private static final FornitoreHome instance = new FornitoreHome();

	private FornitoreHome() {
		super();
	}

	public static FornitoreHome getInstance() {
		return instance;
	}

	public void persist(Fornitore transientInstance) throws PersistEntityException{
		log.debug("persisting Fornitori instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
		}
	}

	public void attachDirty(Fornitore instance) {
		log.debug("attaching dirty Fornitori instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fornitore instance) {
		log.debug("attaching clean Fornitori instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fornitore persistentInstance) throws DeleteEntityException{
		log.debug("deleting Fornitori instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Fornitore merge(Fornitore detachedInstance) throws MergeEntityException {
		log.debug("merging Fornitori instance");
		try {
			Fornitore result = (Fornitore) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Fornitore findById(long id) throws FindByNotFoundException{
		log.debug("getting Fornitori instance with id: " + id);
		try {
			Fornitore instance = (Fornitore) sessionFactory.getCurrentSession()
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

	public List<Fornitore> findByExample(Fornitore instance) throws FindByNotFoundException {
		log.debug("finding Fornitori instance by example");
		try {
			List<Fornitore> results = (List<Fornitore>) sessionFactory
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
