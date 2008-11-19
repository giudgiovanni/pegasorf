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
 * Home object for domain model class CamPrenotata.
 * @see erreeffe.entity.CamPrenotata
 * @author Hibernate Tools
 */
public class CamPrenotataHome {

    private static final Log log = LogFactory.getLog(CamPrenotataHome.class);

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
    
    public void persist(CamPrenotata transientInstance) {
        log.debug("persisting CamPrenotata instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(CamPrenotata instance) {
        log.debug("attaching dirty CamPrenotata instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CamPrenotata instance) {
        log.debug("attaching clean CamPrenotata instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(CamPrenotata persistentInstance) {
        log.debug("deleting CamPrenotata instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CamPrenotata merge(CamPrenotata detachedInstance) {
        log.debug("merging CamPrenotata instance");
        try {
            CamPrenotata result = (CamPrenotata) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public CamPrenotata findById( erreeffe.entity.CamPrenotataId id) {
        log.debug("getting CamPrenotata instance with id: " + id);
        try {
            CamPrenotata instance = (CamPrenotata) sessionFactory.getCurrentSession()
                    .get("erreeffe.entity.CamPrenotata", id);
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
    
    public List<CamPrenotata> findByExample(CamPrenotata instance) {
        log.debug("finding CamPrenotata instance by example");
        try {
            List<CamPrenotata> results = (List<CamPrenotata>) sessionFactory.getCurrentSession()
                    .createCriteria("erreeffe.entity.CamPrenotata")
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

