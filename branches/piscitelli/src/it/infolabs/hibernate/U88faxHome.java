package it.infolabs.hibernate;

// Generated 10-lug-2009 2.12.17 by Hibernate Tools 3.2.4.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class U88fax.
 * @see it.infolabs.hibernate.U88fax
 * @author Hibernate Tools
 */
public class U88faxHome extends BusinessObjectHome{

	private static final Log log = LogFactory.getLog(U88faxHome.class);

	private static final U88faxHome instance=new U88faxHome();

	private U88faxHome() {
		super();
	}

	public static U88faxHome getInstance() {
		return instance;
	}

	public void persist(U88fax transientInstance) {
		log.debug("persisting U88fax instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(U88fax instance) {
		log.debug("attaching dirty U88fax instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(U88fax instance) {
		log.debug("attaching clean U88fax instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(U88fax persistentInstance) {
		log.debug("deleting U88fax instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public U88fax merge(U88fax detachedInstance) {
		log.debug("merging U88fax instance");
		try {
			U88fax result = (U88fax) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public U88fax findById(long id) {
		log.debug("getting U88fax instance with id: " + id);
		try {
			U88fax instance = (U88fax) sessionFactory.getCurrentSession().get(
					"it.infolabs.hibernate.U88fax", id);
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

	public List<U88fax> findByExample(U88fax instance) {
		log.debug("finding U88fax instance by example");
		try {
			List<U88fax> results = (List<U88fax>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.U88fax").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void deleteAll() {
		Query query=this.current().createSQLQuery("delete from u88fax");
		query.executeUpdate();
		
	}
}
