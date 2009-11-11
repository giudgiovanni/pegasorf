package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DettaglioCarichi.
 * @see it.infolabs.hibernate.DettaglioCarichi
 * @author Hibernate Tools
 */
public class DettaglioCarichiHome extends BusinessObjectHome{

	private static final Logger logger = Logger.getLogger(DettaglioCarichiHome.class);

	private static final Log log = LogFactory.getLog(DettaglioCarichiHome.class);

	private static final DettaglioCarichiHome instance = new DettaglioCarichiHome();

	private DettaglioCarichiHome() {
		super();
	}

	public static DettaglioCarichiHome getInstance() {
		return instance;
	}

	public void persist(DettaglioCarichi transientInstance) {
		log.debug("persisting DettaglioCarichi instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioCarichi instance) {
		log.debug("attaching dirty DettaglioCarichi instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioCarichi instance) {
		log.debug("attaching clean DettaglioCarichi instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioCarichi persistentInstance) {
		log.debug("deleting DettaglioCarichi instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioCarichi merge(DettaglioCarichi detachedInstance) {
		log.debug("merging DettaglioCarichi instance");
		try {
			DettaglioCarichi result = (DettaglioCarichi) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioCarichi findById(it.infolabs.hibernate.DettaglioCarichiId id) {
		log.debug("getting DettaglioCarichi instance with id: " + id);
		try {
			DettaglioCarichi instance = (DettaglioCarichi) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DettaglioCarichi", id);
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
	
	public DettaglioCarichi findByIdArticolo(long idArticolo){
		log.debug("getting DettaglioCarichi instance with idArticolo: " + idArticolo);
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria("it.infolabs.hibernate.DettaglioCarichi");
			crit.add(Restrictions.eq("articoli.idarticolo", idArticolo));
			DettaglioCarichi instance = (DettaglioCarichi) crit.uniqueResult();
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

	public List<DettaglioCarichi> findByExample(DettaglioCarichi instance) {
		log.debug("finding DettaglioCarichi instance by example");
		try {
			List<DettaglioCarichi> results = (List<DettaglioCarichi>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DettaglioCarichi").add(
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
