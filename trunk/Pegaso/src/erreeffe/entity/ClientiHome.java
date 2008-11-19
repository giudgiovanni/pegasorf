package erreeffe.entity;

// Generated 19-nov-2008 20.32.15 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Clienti.
 * 
 * @see erreeffe.entity.Clienti
 * @author Hibernate Tools
 */
public class ClientiHome extends BusinessObjectHome{

	private static final Log log = LogFactory.getLog(ClientiHome.class);

	private static ClientiHome instance;

	private ClientiHome() {
		super();
	}

	public static ClientiHome getInstance() {
		if (instance == null) {
			instance = new ClientiHome();
		}
		return instance;
	}

	public void persist(Clienti transientInstance) {
		log.debug("persisting Clienti instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Clienti instance) {
		log.debug("attaching dirty Clienti instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clienti instance) {
		log.debug("attaching clean Clienti instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Clienti persistentInstance) {
		log.debug("deleting Clienti instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Clienti merge(Clienti detachedInstance) {
		log.debug("merging Clienti instance");
		try {
			Clienti result = (Clienti) getSessionFactory().getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Clienti findById(long id) {
		log.debug("getting Clienti instance with id: " + id);
		try {
			Clienti instance = (Clienti) getSessionFactory().getCurrentSession()
					.get("erreeffe.entity.Clienti", id);
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

	public List<Clienti> findByExample(Clienti instance) {
		log.debug("finding Clienti instance by example");
		try {
			List<Clienti> results = (List<Clienti>) getSessionFactory()
					.getCurrentSession().createCriteria(
							"erreeffe.entity.Clienti").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
