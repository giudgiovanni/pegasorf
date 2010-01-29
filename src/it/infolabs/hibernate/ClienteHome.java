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
public class ClienteHome extends BusinessObjectHome {

	private static final Log log = LogFactory.getLog(ClienteHome.class);

	private static final ClienteHome instance = new ClienteHome();

	private ClienteHome() {
		super();
	}

	public static ClienteHome getInstance() {
		return instance;
	}

	public void persist(Cliente transientInstance) throws PersistEntityException{
		log.debug("persisting Cliente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
		}
	}

	public void attachDirty(Cliente instance) {
		log.debug("attaching dirty Cliente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cliente instance) {
		log.debug("attaching clean Cliente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cliente persistentInstance) throws DeleteEntityException{
		log.debug("deleting Cliente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw new DeleteEntityException();
		}
	}

	public Cliente merge(Cliente detachedInstance) throws MergeEntityException{
		log.debug("merging Cliente instance");
		try {
			Cliente result = (Cliente) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw new MergeEntityException();
		}
	}

	public Cliente findById(long id) throws FindByNotFoundException{
		log.debug("getting Cliente instance with id: " + id);
		try {
			Cliente instance = (Cliente) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Cliente", id);
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

	public List<Cliente> findByExample(Cliente instance) throws FindByNotFoundException{
		log.debug("finding Cliente instance by example");
		try {
			List<Cliente> results = (List<Cliente>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Cliente").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw new FindByNotFoundException();
		}
	}
	
	public List<Cliente> allClienti() throws FindAllEntityException {
		log.debug("finding allCliente instance");
		try {
			List<Cliente> results = (List<Cliente>) sessionFactory
			.getCurrentSession().createCriteria("select idcliente || ' - ' || nome || ' ' || cognome from "+
			"it.infolabs.hibernate.Cliente order by nome").add(
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
