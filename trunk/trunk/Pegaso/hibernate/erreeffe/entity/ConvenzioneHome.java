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
 * Home object for domain model class Convenzione.
 * @see erreeffe.entity.Convenzione
 * @author Hibernate Tools
 */
public class ConvenzioneHome {

    private static final Log log = LogFactory.getLog(ConvenzioneHome.class);

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
    
    public void persist(Convenzione transientInstance) {
        log.debug("persisting Convenzione instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Convenzione instance) {
        log.debug("attaching dirty Convenzione instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Convenzione instance) {
        log.debug("attaching clean Convenzione instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Convenzione persistentInstance) {
        log.debug("deleting Convenzione instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Convenzione merge(Convenzione detachedInstance) {
        log.debug("merging Convenzione instance");
        try {
            Convenzione result = (Convenzione) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Convenzione findById( int id) {
        log.debug("getting Convenzione instance with id: " + id);
        try {
            Convenzione instance = (Convenzione) sessionFactory.getCurrentSession()
                    .get("erreeffe.entity.Convenzione", id);
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
    
    public List<Convenzione> findByExample(Convenzione instance) {
        log.debug("finding Convenzione instance by example");
        try {
            List<Convenzione> results = (List<Convenzione>) sessionFactory.getCurrentSession()
                    .createCriteria("erreeffe.entity.Convenzione")
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

