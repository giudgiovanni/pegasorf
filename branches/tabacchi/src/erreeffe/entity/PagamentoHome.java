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
 * Home object for domain model class Pagamento.
 * @see erreeffe.entity.Pagamento
 * @author Hibernate Tools
 */
public class PagamentoHome {

	private static final Log log = LogFactory.getLog(PagamentoHome.class);

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

	public void persist(Pagamento transientInstance) {
		log.debug("persisting Pagamento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Pagamento instance) {
		log.debug("attaching dirty Pagamento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pagamento instance) {
		log.debug("attaching clean Pagamento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Pagamento persistentInstance) {
		log.debug("deleting Pagamento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pagamento merge(Pagamento detachedInstance) {
		log.debug("merging Pagamento instance");
		try {
			Pagamento result = (Pagamento) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pagamento findById(long id) {
		log.debug("getting Pagamento instance with id: " + id);
		try {
			Pagamento instance = (Pagamento) sessionFactory.getCurrentSession()
					.get("erreeffe.entity.Pagamento", id);
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

	public List<Pagamento> findByExample(Pagamento instance) {
		log.debug("finding Pagamento instance by example");
		try {
			List<Pagamento> results = (List<Pagamento>) sessionFactory
					.getCurrentSession().createCriteria(
							"erreeffe.entity.Pagamento").add(create(instance))
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
