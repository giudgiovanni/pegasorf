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
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Clienti.
 * @see it.infolabs.hibernate.Clienti
 * @author Hibernate Tools
 */
public class ClientiHome extends BusinessObjectHome {

	private static final Log log = LogFactory.getLog(ClientiHome.class);

	private static final ClientiHome instance = new ClientiHome();

	private ClientiHome() {
		super();
	}

	public static ClientiHome getInstance() {
		return instance;
	}

	public void persist(Clienti transientInstance) throws PersistEntityException{
		log.debug("persisting Clienti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
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

	public void delete(Clienti persistentInstance) throws DeleteEntityException{
		log.debug("deleting Clienti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Clienti merge(Clienti detachedInstance) throws MergeEntityException{
		log.debug("merging Clienti instance");
		try {
			Clienti result = (Clienti) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Clienti findById(long id) throws FindByNotFoundException{
		log.debug("getting Clienti instance with id: " + id);
		try {
			Clienti instance = (Clienti) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Clienti", id);
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

	public List<Clienti> findByExample(Clienti instance) throws FindByNotFoundException{
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
			throw new FindByNotFoundException();
		}
	}
	
	public List<Clienti> allClienti() throws FindAllEntityException {
		log.debug("finding allClienti instance");
		try {
			List<Clienti> results = (List<Clienti>) sessionFactory
			.getCurrentSession().createCriteria("select idcliente || ' - ' || nome || ' ' || cognome from "+
			"it.infolabs.hibernate.Clienti order by nome").add(
					create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw new FindAllEntityException();
		}
	}
}
