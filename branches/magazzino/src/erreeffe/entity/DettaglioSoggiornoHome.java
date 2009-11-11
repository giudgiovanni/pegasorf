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
 * Home object for domain model class DettaglioSoggiorno.
 * @see erreeffe.entity.DettaglioSoggiorno
 * @author Hibernate Tools
 */
public class DettaglioSoggiornoHome {

	private static final Log log = LogFactory
			.getLog(DettaglioSoggiornoHome.class);

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

	public void persist(DettaglioSoggiorno transientInstance) {
		log.debug("persisting DettaglioSoggiorno instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DettaglioSoggiorno instance) {
		log.debug("attaching dirty DettaglioSoggiorno instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DettaglioSoggiorno instance) {
		log.debug("attaching clean DettaglioSoggiorno instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DettaglioSoggiorno persistentInstance) {
		log.debug("deleting DettaglioSoggiorno instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DettaglioSoggiorno merge(DettaglioSoggiorno detachedInstance) {
		log.debug("merging DettaglioSoggiorno instance");
		try {
			DettaglioSoggiorno result = (DettaglioSoggiorno) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DettaglioSoggiorno findById(long id) {
		log.debug("getting DettaglioSoggiorno instance with id: " + id);
		try {
			DettaglioSoggiorno instance = (DettaglioSoggiorno) sessionFactory
					.getCurrentSession().get(
							"erreeffe.entity.DettaglioSoggiorno", id);
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

	public List<DettaglioSoggiorno> findByExample(DettaglioSoggiorno instance) {
		log.debug("finding DettaglioSoggiorno instance by example");
		try {
			List<DettaglioSoggiorno> results = (List<DettaglioSoggiorno>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.DettaglioSoggiorno").add(
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
