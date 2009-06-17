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
 * Home object for domain model class TotaliModistatc59.
 * @see erreeffe.entity.TotaliModistatc59
 * @author Hibernate Tools
 */
public class TotaliModistatc59Home {

	private static final Log log = LogFactory
			.getLog(TotaliModistatc59Home.class);

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

	public void persist(TotaliModistatc59 transientInstance) {
		log.debug("persisting TotaliModistatc59 instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TotaliModistatc59 instance) {
		log.debug("attaching dirty TotaliModistatc59 instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TotaliModistatc59 instance) {
		log.debug("attaching clean TotaliModistatc59 instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TotaliModistatc59 persistentInstance) {
		log.debug("deleting TotaliModistatc59 instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TotaliModistatc59 merge(TotaliModistatc59 detachedInstance) {
		log.debug("merging TotaliModistatc59 instance");
		try {
			TotaliModistatc59 result = (TotaliModistatc59) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TotaliModistatc59 findById(erreeffe.entity.TotaliModistatc59Id id) {
		log.debug("getting TotaliModistatc59 instance with id: " + id);
		try {
			TotaliModistatc59 instance = (TotaliModistatc59) sessionFactory
					.getCurrentSession().get(
							"erreeffe.entity.TotaliModistatc59", id);
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

	public List<TotaliModistatc59> findByExample(TotaliModistatc59 instance) {
		log.debug("finding TotaliModistatc59 instance by example");
		try {
			List<TotaliModistatc59> results = (List<TotaliModistatc59>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.TotaliModistatc59").add(
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
