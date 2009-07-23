package it.infolabs.hibernate;

import org.apache.log4j.Logger;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import rf.pegaso.db.tabelle.Articolo;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Articoli.
 * @see it.infolabs.hibernate.Articoli
 * @author Hibernate Tools
 */
public class ArticoliHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ArticoliHome.class);

	private static final Log log = LogFactory.getLog(ArticoliHome.class);

	private static final ArticoliHome instance=new ArticoliHome();

	private ArticoliHome() {
		super();
	}

	public static ArticoliHome getInstance() {
		return instance;
	}

	public void persist(Articoli transientInstance) {
		log.debug("persisting Articoli instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Articoli instance) {
		log.debug("attaching dirty Articoli instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Articoli instance) {
		log.debug("attaching clean Articoli instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Articoli persistentInstance) {
		log.debug("deleting Articoli instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Articoli merge(Articoli detachedInstance) {
		log.debug("merging Articoli instance");
		try {
			Articoli result = (Articoli) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Articoli findById(long id) {
		log.debug("getting Articoli instance with id: " + id);
		try {
			Articoli instance = (Articoli) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Articoli", id);
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

	public List<Articoli> findByExample(Articoli instance) {
		log.debug("finding Articoli instance by example");
		try {
			List<Articoli> results = (List<Articoli>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Articoli").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public Double getGiacenza(long articolo){
		if (logger.isDebugEnabled()) {
			logger.debug("getGiacenza(long) - start");
		}

		Articolo a=new Articolo();
		double giacenza=0.0;
		try {
			a.caricaDati(new Long(articolo).intValue());
			giacenza= a.getGiacenza2();
		} catch (SQLException e) {
			logger.error("getGiacenza(long)", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getGiacenza(long) - end");
		}
		return giacenza;
	}
	
	
	public String getQtaRiordino(long articolo){
		Articoli a=ArticoliHome.getInstance().findById(articolo);
		int qtaOrdinare=(int)(getGiacenza(articolo)- a.getScortaMinima());
		int diff=0;
		if(a.getNumeroPacchetti()!=0){
			diff=qtaOrdinare%a.getNumeroPacchetti();
		}
		
		if(a.getNumeroPacchetti()<=10){
			if(diff>=5){
				qtaOrdinare+=(a.getNumeroPacchetti()-diff);
			}
		}else if(a.getNumeroPacchetti()>10){
			if(diff>=10){
				qtaOrdinare+=(a.getNumeroPacchetti()-diff);
			}
		}
		
		Double riordino=0.0;
		if(a.getNumeroPacchetti()!=0){
			riordino=(qtaOrdinare/a.getNumeroPacchetti())*a.getPeso();
		}
		
		return riordino.toString();
	}
	
}