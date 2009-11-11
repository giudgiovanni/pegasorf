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
 * Home object for domain model class ItalianiTot.
 * @see erreeffe.entity.ItalianiTot
 * @author Hibernate Tools
 */
public class ItalianiTotHome {

	private static final Log log = LogFactory.getLog(ItalianiTotHome.class);

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

	public void persist(ItalianiTot transientInstance) {
		log.debug("persisting ItalianiTot instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItalianiTot instance) {
		log.debug("attaching dirty ItalianiTot instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItalianiTot instance) {
		log.debug("attaching clean ItalianiTot instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItalianiTot persistentInstance) {
		log.debug("deleting ItalianiTot instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItalianiTot merge(ItalianiTot detachedInstance) {
		log.debug("merging ItalianiTot instance");
		try {
			ItalianiTot result = (ItalianiTot) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItalianiTot findById(erreeffe.entity.ItalianiTotId id) {
		log.debug("getting ItalianiTot instance with id: " + id);
		try {
			ItalianiTot instance = (ItalianiTot) sessionFactory
					.getCurrentSession().get("erreeffe.entity.ItalianiTot", id);
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

	public List<ItalianiTot> findByExample(ItalianiTot instance) {
		log.debug("finding ItalianiTot instance by example");
		try {
			List<ItalianiTot> results = (List<ItalianiTot>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.ItalianiTot")
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
