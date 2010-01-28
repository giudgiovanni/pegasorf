package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.DeleteEntityException;
import it.infolabs.hibernate.exception.FindByNotFoundException;
import it.infolabs.hibernate.exception.MergeEntityException;
import it.infolabs.hibernate.exception.PersistEntityException;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aspetto.
 * @see it.infolabs.hibernate.Aspetto
 * @author Hibernate Tools
 */
public class AspettoHome extends BusinessObjectHome{

	private static final Log log = LogFactory.getLog(AspettoHome.class);

	private static final AspettoHome instance = new AspettoHome();

	private AspettoHome() {
		super();
	}

	public static AspettoHome getInstance() {
		return instance;
	}

	public void persist(Aspetto transientInstance) throws PersistEntityException{
		log.debug("persisting Aspetto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
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

	public void delete(Aspetto persistentInstance) throws DeleteEntityException{
		log.debug("deleting Aspetto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Aspetto merge(Aspetto detachedInstance) throws MergeEntityException{
		log.debug("merging Aspetto instance");
		try {
			Aspetto result = (Aspetto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Aspetto findById(long id) throws FindByNotFoundException{
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
			throw new FindByNotFoundException();
		}
	}

	public List<Aspetto> findByExample(Aspetto instance) throws FindByNotFoundException{
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
			throw new FindByNotFoundException();
		}
	}
}
