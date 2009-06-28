package erreeffe.entity;

// Generated 28-giu-2009 12.52.21 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class MovimentoBanca.
 * @see erreeffe.entity.MovimentoBanca
 * @author Hibernate Tools
 */
public class MovimentoBancaHome {

	private static final Log log = LogFactory.getLog(MovimentoBancaHome.class);

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

	public void persist(MovimentoBanca transientInstance) {
		log.debug("persisting MovimentoBanca instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(MovimentoBanca instance) {
		log.debug("attaching dirty MovimentoBanca instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MovimentoBanca instance) {
		log.debug("attaching clean MovimentoBanca instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(MovimentoBanca persistentInstance) {
		log.debug("deleting MovimentoBanca instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MovimentoBanca merge(MovimentoBanca detachedInstance) {
		log.debug("merging MovimentoBanca instance");
		try {
			MovimentoBanca result = (MovimentoBanca) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MovimentoBanca findById(long id) {
		log.debug("getting MovimentoBanca instance with id: " + id);
		try {
			MovimentoBanca instance = (MovimentoBanca) sessionFactory
					.getCurrentSession().get("erreeffe.entity.MovimentoBanca",
							id);
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

	public List<MovimentoBanca> findByExample(MovimentoBanca instance) {
		log.debug("finding MovimentoBanca instance by example");
		try {
			List<MovimentoBanca> results = (List<MovimentoBanca>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.MovimentoBanca").add(
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
