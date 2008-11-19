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
 * Home object for domain model class Aggiunta.
 * @see erreeffe.entity.Aggiunta
 * @author Hibernate Tools
 */
public class AggiuntaHome extends BusinessObjectHome{

    private static final Log log = LogFactory.getLog(AggiuntaHome.class);

    private static AggiuntaHome instance;
    
    private AggiuntaHome(){
    	super();
    }
    
    public static AggiuntaHome getInstance(){
    	if(instance==null){
    		instance=new AggiuntaHome();
    	}
    	return instance;
    }
    
    public void persist(Aggiunta transientInstance) {
        log.debug("persisting Aggiunta instance");
        try {
            getSessionFactory().getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Aggiunta instance) {
        log.debug("attaching dirty Aggiunta instance");
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Aggiunta instance) {
        log.debug("attaching clean Aggiunta instance");
        try {
            getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Aggiunta persistentInstance) {
        log.debug("deleting Aggiunta instance");
        try {
            getSessionFactory().getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Aggiunta merge(Aggiunta detachedInstance) {
        log.debug("merging Aggiunta instance");
        try {
            Aggiunta result = (Aggiunta) getSessionFactory().getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Aggiunta findById( int id) {
        log.debug("getting Aggiunta instance with id: " + id);
        try {
            Aggiunta instance = (Aggiunta) getSessionFactory().getCurrentSession()
                    .get("erreeffe.entity.Aggiunta", id);
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
    
    public List<Aggiunta> findByExample(Aggiunta instance) {
        log.debug("finding Aggiunta instance by example");
        try {
            List<Aggiunta> results = (List<Aggiunta>) getSessionFactory().getCurrentSession()
                    .createCriteria("erreeffe.entity.Aggiunta")
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

