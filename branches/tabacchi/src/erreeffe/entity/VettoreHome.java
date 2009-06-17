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
 * Home object for domain model class Vettore.
 * @see erreeffe.entity.Vettore
 * @author Hibernate Tools
 */
public class VettoreHome {

    private static final Log log = LogFactory.getLog(VettoreHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(Vettore transientInstance) {
        log.debug("persisting Vettore instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Vettore instance) {
        log.debug("attaching dirty Vettore instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Vettore instance) {
        log.debug("attaching clean Vettore instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Vettore persistentInstance) {
        log.debug("deleting Vettore instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Vettore merge(Vettore detachedInstance) {
        log.debug("merging Vettore instance");
        try {
            Vettore result = (Vettore) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Vettore findById( erreeffe.entity.VettoreId id) {
        log.debug("getting Vettore instance with id: " + id);
        try {
            Vettore instance = (Vettore) sessionFactory.getCurrentSession()
                    .get("erreeffe.entity.Vettore", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<Vettore> findByExample(Vettore instance) {
        log.debug("finding Vettore instance by example");
        try {
            List<Vettore> results = (List<Vettore>) sessionFactory.getCurrentSession()
                    .createCriteria("erreeffe.entity.Vettore")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
}

