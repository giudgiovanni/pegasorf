package it.infolabs.hibernate;

import org.apache.log4j.Logger;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindByNotFoundException;
import it.infolabs.hibernate.exception.PersistEntityException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

import rf.pegaso.db.tabelle.Articolo;
import rf.utility.Constant;
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

	public void persist(Articoli transientInstance) throws PersistEntityException{
		log.debug("persisting Articoli instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new PersistEntityException();
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

	public Articoli findById(long id) throws FindByNotFoundException{
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
			throw new FindByNotFoundException();
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
	
	
	public Double getQtaRiordino(long articolo, double qtaOrdinare){
		Articoli a;
		try {
			a = ArticoliHome.getInstance().findById(articolo);
		} catch (FindByNotFoundException e) {
			return 0.0;
		}
//		double qtaOrdinare=(int)(getGiacenza(articolo)- a.getScortaMinima());
		int numeroPacchetti=a.getNumeroPacchetti()==null?0:a.getNumeroPacchetti();
		double diff=0;
		if(numeroPacchetti !=0){
			diff=qtaOrdinare%numeroPacchetti;
		}
		
		if(numeroPacchetti <=10){
			if(diff>=5){
				qtaOrdinare+=(numeroPacchetti-diff);
			}
			else{
				qtaOrdinare-=diff;
			}
		}else if(numeroPacchetti>10){
			if(diff>=10){
				qtaOrdinare+=(numeroPacchetti-diff);
			}
			else{
				qtaOrdinare-=diff;
			}
		}
		if ( qtaOrdinare < numeroPacchetti ){
			qtaOrdinare = numeroPacchetti;
		}
		
		Double riordino=0.0;
		if(numeroPacchetti!=0){
			riordino=(qtaOrdinare/numeroPacchetti)*a.getPeso();
		}
		
		return riordino;
	}
	
	/**
	 * Ritorna tutti gli articoli che sono scesi sotto la soglia minima
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Object[]> allArticoliSottoSogliaMinima() {		
		log.debug("finding all Articoli sotto soglia minima.");
		try {
			ArrayList<Object[]> results = (ArrayList<Object[]>) sessionFactory.getCurrentSession().createSQLQuery("select a.idarticolo, (v.carico-v.scarico) from giacenza_articoli_all_view v, articoli a where v.idarticolo=a.idarticolo and (v.carico-v.scarico) < a.scorta_minima").list();
			
			log.debug("finding all Articoli sotto soglia minima successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("finding all Articoli sotto soglia minima failed", re);
			throw re;
		}
	}
	
	public boolean codBarreEsistenteForInsert(String codBarre) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria("it.infolabs.hibernate.Articoli");
		crit.add(Restrictions.eq("codbarre", codBarre));				
		if ( crit.list().size() > 0)
			return true;
		return false;
	}
	
	public boolean codBarreEsistenteForUpdate(String codBarre, long idArticolo) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria("it.infolabs.hibernate.Articoli");
		crit.add(Restrictions.and(Restrictions.eq("codbarre", codBarre),Restrictions.not(Restrictions.idEq(idArticolo))));
		if ( crit.list().size() > 0 )
			return true;
		return false;
	}
	
	public Object[] findByCodBarreWithPrezzoAcquisto(String codBarre){
		log.debug("finding Articoli instance by codiceBarreWithPrezzoAcquisto");
		try {
			Articoli art = null;
			Object[] obj = new Object[2];
			List<Object[]> results = null;
			StringBuilder query = new StringBuilder();
			query.append("select a, d.qta, d.prezzoAcquisto, c.dataCarico, c.oraCarico, (carico - scarico) as giacenza ");
			query.append("from it.infolabs.hibernate.Carichi c ");
			query.append("inner join c.dettaglioCarichis d ");
			query.append("inner join d.articoli a, ");
			query.append("it.infolabs.hibernate.GiacenzaArticoliAllView v ");
			query.append("where v.id.idarticolo = a.idarticolo ");
			query.append("and a.codbarre = '"+codBarre+"') ");
			query.append("order by c.dataCarico desc, c.oraCarico desc");
			
			results = (List<Object[]>) sessionFactory.getCurrentSession().createQuery(query.toString()).list();
			log.debug("find by codiceBarreWithPrezzoAcquisto successful");
			// Se la lista e' vuota ritorniamo null
			if ( results.size() == 0 ){
				return null;
			}
			else{
				art = (Articoli)results.get(0)[0];
				obj[0] = art;
				obj[1] = results.get(0)[5];
				if ( art.isQtaInfinita() ){					
					return obj;
				}
				else if ( (Double)results.get(0)[5] <= 0 ) {
					return null;
				}
				else{
					int qtaC = 0;
					for (int i = 0; i < results.size(); i++ ){
						if ( (Double)results.get(i)[5] <= ((Double)results.get(i)[1] + qtaC) ){
							obj[0] = (Articoli)results.get(i)[0];
							obj[1] = results.get(i)[5];
							return obj;
						}
						else{
							qtaC = (int) (qtaC + (Double)results.get(i)[1]);
						}
					}
				}
				return null;
			}
			
		} catch (RuntimeException re) {
			log.error("find by codiceBarreWithPrezzoAcquisto failed", re);
			throw re;
		}
	}
	
}
