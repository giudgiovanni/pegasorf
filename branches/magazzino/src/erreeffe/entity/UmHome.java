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
 * Home object for domain model class Um.
 * 
 * @see erreeffe.entity.Um
 * @author Hibernate Tools
 */
public class UmHome extends BusinessObjectHome {

	private static final Log log = LogFactory.getLog(UmHome.class);

	private static UmHome instance;

	private UmHome() {
		super();
	}

	public static UmHome getInstance() {
		if (instance == null) {
			instance = new UmHome();
		}
		return instance;
	}

	public void persist(Um transientInstance) {
		log.debug("persisting Um instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Um instance) {
		log.debug("attaching dirty Um instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Um instance) {
		log.debug("attaching clean Um instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Um persistentInstance) {
		log.debug("deleting Um instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Um merge(Um detachedInstance) {
		log.debug("merging Um instance");
		try {
			Um result = (Um) getSessionFactory().getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Um findById(long id) {
		log.debug("getting Um instance with id: " + id);
		try {
			Um instance = (Um) getSessionFactory().getCurrentSession().get(
					"erreeffe.entity.Um", id);
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

	public List<Um> findByExample(Um instance) {
		log.debug("finding Um instance by example");
		try {
			List<Um> results = (List<Um>) getSessionFactory().getCurrentSession()
					.createCriteria("erreeffe.entity.Um").add(create(instance))
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
