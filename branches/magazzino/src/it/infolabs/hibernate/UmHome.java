package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindByNotFoundException;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Um.
 * @see it.infolabs.hibernate.Um
 * @author Hibernate Tools
 */
public class UmHome extends BusinessObjectHome{

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmHome.class);

	private static final Log log = LogFactory.getLog(UmHome.class);

	private static final UmHome instance = new UmHome();

	private UmHome() {
		super();
	}

	public static UmHome getInstance() {
		return instance;
	}

	public void persist(Um transientInstance) {
		log.debug("persisting Um instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Um instance) {
		log.debug("attaching dirty Um instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Um instance) {
		log.debug("attaching clean Um instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Um persistentInstance) {
		log.debug("deleting Um instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Um merge(Um detachedInstance) {
		log.debug("merging Um instance");
		try {
			Um result = (Um) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Um findById(long id) throws FindByNotFoundException{
		log.debug("getting Um instance with id: " + id);
		try {
			Um instance = (Um) sessionFactory.getCurrentSession().get(
					"it.infolabs.hibernate.Um", id);
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

	public List<Um> findByExample(Um instance) {
		log.debug("finding Um instance by example");
		try {
			List<Um> results = (List<Um>) sessionFactory.getCurrentSession()
					.createCriteria("it.infolabs.hibernate.Um").add(
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
