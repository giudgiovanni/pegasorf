package it.infolabs.hibernate;

import org.apache.log4j.Logger;

// Generated 22-nov-2009 2.09.37 by Hibernate Tools 3.2.4.GA

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class CategoriaCliente.
 * @see it.infolabs.hibernate.CategoriaCliente
 * @author Hibernate Tools
 */
public class CategoriaClienteHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CategoriaClienteHome.class);

	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(CategoriaClienteHome.class);

	private static final CategoriaClienteHome instance=new CategoriaClienteHome();

	private CategoriaClienteHome() {
		super();
	}

	public static CategoriaClienteHome getInstance() {
		return instance;
	}

	public void persist(CategoriaCliente transientInstance) {
		log.debug("persisting CategoriaCliente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CategoriaCliente instance) {
		log.debug("attaching dirty CategoriaCliente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CategoriaCliente instance) {
		log.debug("attaching clean CategoriaCliente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CategoriaCliente persistentInstance) {
		log.debug("deleting CategoriaCliente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CategoriaCliente merge(CategoriaCliente detachedInstance) {
		log.debug("merging CategoriaCliente instance");
		try {
			CategoriaCliente result = (CategoriaCliente) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CategoriaCliente findById(long id) {
		log.debug("getting CategoriaCliente instance with id: " + id);
		try {
			CategoriaCliente instance = (CategoriaCliente) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.CategoriaCliente", id);
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

	public List<CategoriaCliente> findByExample(CategoriaCliente instance) {
		log.debug("finding CategoriaCliente instance by example");
		try {
			List<CategoriaCliente> results = (List<CategoriaCliente>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.CategoriaCliente").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * Il metodo restituisce tutte le descrizione delle 
	 * categorie utente che sono salvate
	 * @return un array di oggetti che sono delle stringhe
	 */
	public String[] getAllDisplay() {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllDisplay() - start");
		}

		Query query=current().createSQLQuery("select id || '-' || descrizione from categoria_cliente");
		ArrayList<String> elenco = new ArrayList<String>(query.list());
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllDisplay() - end");
		}
		
		return elenco.toArray(new String[0]);
	}
}
