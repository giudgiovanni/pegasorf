package rf.hibernate;

// Generated 18-dic-2007 17.22.09 by Hibernate Tools 3.2.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DettaglioOrdini.
 * @see rf.hibernate.DettaglioOrdini
 * @author Hibernate Tools
 */
public class DettaglioOrdiniHome {

	private static final Log log = LogFactory.getLog(DettaglioOrdiniHome.class);

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

	public void persist(DettaglioOrdini transientInstance) {
		log.debug("persisting DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioOrdini instance) {
		log.debug("attaching dirty DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioOrdini instance) {
		log.debug("attaching clean DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioOrdini persistentInstance) {
		log.debug("deleting DettaglioOrdini instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioOrdini merge(DettaglioOrdini detachedInstance) {
		log.debug("merging DettaglioOrdini instance");
		try {
			DettaglioOrdini result = (DettaglioOrdini) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioOrdini findById(rf.hibernate.DettaglioOrdiniId id) {
		log.debug("getting DettaglioOrdini instance with id: " + id);
		try {
			DettaglioOrdini instance = (DettaglioOrdini) sessionFactory
					.getCurrentSession()
					.get("rf.hibernate.DettaglioOrdini", id);
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

	public List<DettaglioOrdini> findByExample(DettaglioOrdini instance) {
		log.debug("finding DettaglioOrdini instance by example");
		try {
			List<DettaglioOrdini> results = (List<DettaglioOrdini>) sessionFactory
					.getCurrentSession().createCriteria(
							"rf.hibernate.DettaglioOrdini").add(
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
