package it.infolabs.hibernate;

// Generated 21-nov-2009 23.21.45 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Provincia.
 * @see it.infolabs.hibernate.Provincia
 * @author Hibernate Tools
 */
public class ProvinciaHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(ProvinciaHome.class);

	private static final ProvinciaHome instance=new ProvinciaHome();

	private ProvinciaHome() {
		super();
	}

	public static ProvinciaHome getInstance() {
		return instance;
	}

	public void persist(Provincia transientInstance) {
		log.debug("persisting Provincia instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
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

	public void delete(Provincia persistentInstance) {
		log.debug("deleting Provincia instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Provincia merge(Provincia detachedInstance) {
		log.debug("merging Provincia instance");
		try {
			Provincia result = (Provincia) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Provincia findById(long id) {
		log.debug("getting Provincia instance with id: " + id);
		try {
			Provincia instance = (Provincia) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Provincia", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			if(instance==null){
				return new Provincia();
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Provincia> findByExample(Provincia instance) {
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
			throw re;
		}
	}
}
