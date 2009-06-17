package erreeffe.entity;

// Generated 20-nov-2008 2.05.45 by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aggiunta.
 * @see erreeffe.entity.Aggiunta
 * @author Hibernate Tools
 */
public class AggiuntaHome {

	private static final Log log = LogFactory.getLog(AggiuntaHome.class);

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

	public void persist(Aggiunta transientInstance) {
		log.debug("persisting Aggiunta instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Aggiunta instance) {
		log.debug("attaching dirty Aggiunta instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Aggiunta instance) {
		log.debug("attaching clean Aggiunta instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Aggiunta persistentInstance) {
		log.debug("deleting Aggiunta instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Aggiunta merge(Aggiunta detachedInstance) {
		log.debug("merging Aggiunta instance");
		try {
			Aggiunta result = (Aggiunta) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Aggiunta findById(int id) {
		log.debug("getting Aggiunta instance with id: " + id);
		try {
			Aggiunta instance = (Aggiunta) sessionFactory.getCurrentSession()
					.get("erreeffe.entity.Aggiunta", id);
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

	public List<Aggiunta> findByExample(Aggiunta instance) {
		log.debug("finding Aggiunta instance by example");
		try {
			List<Aggiunta> results = (List<Aggiunta>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.Aggiunta").add(create(instance))
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
