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
 * Home object for domain model class DocumentoCliente.
 * @see it.infolabs.hibernate.DocumentoCliente
 * @author Hibernate Tools
 */
public class DocumentoClienteHome {

	private static final Log log = LogFactory
			.getLog(DocumentoClienteHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(DocumentoCliente transientInstance) {
		log.debug("persisting DocumentoCliente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentoCliente instance) {
		log.debug("attaching dirty DocumentoCliente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentoCliente instance) {
		log.debug("attaching clean DocumentoCliente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DocumentoCliente persistentInstance) {
		log.debug("deleting DocumentoCliente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentoCliente merge(DocumentoCliente detachedInstance) {
		log.debug("merging DocumentoCliente instance");
		try {
			DocumentoCliente result = (DocumentoCliente) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DocumentoCliente findById(long id) {
		log.debug("getting DocumentoCliente instance with id: " + id);
		try {
			DocumentoCliente instance = (DocumentoCliente) sessionFactory
					.getCurrentSession().get(
							"it.infolabs.hibernate.DocumentoCliente", id);
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

	public List<DocumentoCliente> findByExample(DocumentoCliente instance) {
		log.debug("finding DocumentoCliente instance by example");
		try {
			List<DocumentoCliente> results = (List<DocumentoCliente>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.DocumentoCliente").add(
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
