package erreeffe.entity;

// Generated 30-giu-2009 3.07.24 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DettaglioCarichi.
 * @see erreeffe.entity.DettaglioCarichi
 * @author Hibernate Tools
 */
public class DettaglioCarichiHome {

	private static final Log log = LogFactory
			.getLog(DettaglioCarichiHome.class);

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

	public DettaglioCarichi findById(erreeffe.entity.DettaglioCarichiId id) {
		log.debug("getting DettaglioCarichi instance with id: " + id);
		try {
			DettaglioCarichi instance = (DettaglioCarichi) sessionFactory
					.getCurrentSession().get(
							"erreeffe.entity.DettaglioCarichi", id);
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
							"erreeffe.entity.DettaglioCarichi").add(
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
