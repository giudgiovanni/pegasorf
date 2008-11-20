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
 * Home object for domain model class Modistatc59.
 * @see erreeffe.entity.Modistatc59
 * @author Hibernate Tools
 */
public class Modistatc59Home {

	private static final Log log = LogFactory.getLog(Modistatc59Home.class);

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

	public void persist(Modistatc59 transientInstance) {
		log.debug("persisting Modistatc59 instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Modistatc59 instance) {
		log.debug("attaching dirty Modistatc59 instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Modistatc59 instance) {
		log.debug("attaching clean Modistatc59 instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Modistatc59 persistentInstance) {
		log.debug("deleting Modistatc59 instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Modistatc59 merge(Modistatc59 detachedInstance) {
		log.debug("merging Modistatc59 instance");
		try {
			Modistatc59 result = (Modistatc59) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Modistatc59 findById(long id) {
		log.debug("getting Modistatc59 instance with id: " + id);
		try {
			Modistatc59 instance = (Modistatc59) sessionFactory
					.getCurrentSession().get("erreeffe.entity.Modistatc59", id);
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

	public List<Modistatc59> findByExample(Modistatc59 instance) {
		log.debug("finding Modistatc59 instance by example");
		try {
			List<Modistatc59> results = (List<Modistatc59>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.Modistatc59")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
