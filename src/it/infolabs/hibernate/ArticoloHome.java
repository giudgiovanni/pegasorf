package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindAllEntityException;

import java.util.List;
import java.util.Random;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import rf.utility.Constant;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Articolo.
 * @see it.infolabs.hibernate.Articolo
 * @author Hibernate Tools
 */
public class ArticoloHome extends BusinessObjectHome{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ArticoloHome.class);

	private static final ArticoloHome instance=new ArticoloHome();

	private ArticoloHome() {
		super();
	}

	public static ArticoloHome getInstance() {
		return instance;
	}

	
	public Object[] findByCodBarreWithPrezzoAcquisto(String codBarre){
		log.debug("finding Articoli instance by codiceBarreWithPrezzoAcquisto");
		try {
			Articolo art = null;
			Object[] obj = new Object[2];
			List<Object[]> results = null;
			StringBuilder query = new StringBuilder();
			query.append("select a, d.qta, d.prezzoAcquisto, c.dataCarico, c.oraCarico, (carico - scarico) as giacenza ");
			query.append("from it.infolabs.hibernate.Carico c ");
			query.append("inner join c.dettaglioCaricos d ");
			query.append("inner join d.articolo a, ");
			query.append("it.infolabs.hibernate.GiacenzaArticoliAllView v ");
			query.append("where v.id.idarticolo = a.idarticolo ");
			query.append("and ((a.codbarre = '"+codBarre.substring(0, 4)+"' ");
			query.append("and a.reparto" +
					"" +
					".idreparto = "+Constant.REPARTO_GRATTA_E_VINCI+") ");
			query.append("or a.codbarre = '"+codBarre+"') ");
			query.append("order by c.dataCarico desc, c.oraCarico desc");
			
			results = (List<Object[]>) sessionFactory.getCurrentSession().createQuery(query.toString()).list();
			log.debug("find by codiceBarreWithPrezzoAcquisto successful");
			// Se la lista e' vuota ritorniamo null
			if ( results.size() == 0 ){
				return null;
			}
			else{
				art = (Articolo)results.get(0)[0];
				obj[0] = art;
				obj[1] = results.get(0)[5];
				if ( art.getQtaInfinita() ){					
					return obj;
				}
				else if ( (Double)results.get(0)[5] <= 0 ) {
					return null;
				}
				else{
					int qtaC = 0;
					for (int i = 0; i < results.size(); i++ ){
						if ( (Double)results.get(i)[5] <= ((Double)results.get(i)[1] + qtaC) ){
							obj[0] = (Articolo)results.get(i)[0];
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
	
	
	public boolean codBarreEsistenteForInsert(String codBarre) {
		if(codBarre==null){
			return false;
		}
		Criteria crit = sessionFactory.getCurrentSession().createCriteria("it.infolabs.hibernate.Articolo");
		crit.add(Restrictions.or(
				Restrictions.and(
						Restrictions.ilike("codbarre", codBarre.substring(0, 4)+"%"), 
						Restrictions.eq("reparto.idreparto", ((long)Constant.REPARTO_GRATTA_E_VINCI))), 
						Restrictions.eq("codbarre", codBarre)));
		if ( crit.list().size() > 0)
			return true;
		return false;
	}
	
	public boolean codBarreEsistenteForUpdate(String codBarre, long idArticolo) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria("it.infolabs.hibernate.Articolo");
		crit.add(Restrictions.and(Restrictions.or(
				Restrictions.eq("codbarre", codBarre), 
				Restrictions.like("codbarre", codBarre.substring(0, 4)+"%")), 
				Restrictions.not(Restrictions.idEq(idArticolo))));
		if ( crit.list().size() > 0 )
			return true;
		return false;
	}
	
	
	
	public void persist(Articolo transientInstance) {
		log.debug("persisting Articolo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Articolo instance) {
		log.debug("attaching dirty Articolo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Articolo instance) {
		log.debug("attaching clean Articolo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Articolo persistentInstance) {
		log.debug("deleting Articolo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Articolo merge(Articolo detachedInstance) {
		log.debug("merging Articolo instance");
		try {
			Articolo result = (Articolo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Articolo findById(long id) {
		log.debug("getting Articolo instance with id: " + id);
		try {
			Articolo instance = (Articolo) sessionFactory.getCurrentSession()
					.get("it.infolabs.hibernate.Articolo", id);
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

	public List<Articolo> findByExample(Articolo instance) {
		log.debug("finding Articolo instance by example");
		try {
			List<Articolo> results = (List<Articolo>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Articolo").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Articolo> findAll() throws FindAllEntityException {
		log.debug("finding All Articoli instance");
		try {
			List<Articolo> results = (List<Articolo>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Articolo").addOrder(Order.asc("descrizione")).list();
			log.debug("find All Articoli successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All Articoli failed", re);
			throw re;
		}
	}
	
	public String getRandomBarcode(){
		if (logger.isDebugEnabled()) {
			logger.debug("getRandomBarcode() - start");
		}

		StringBuffer sb=new StringBuffer();
		Random random=null;
		int START = 0;
	    int END = 9;

		do{
			//eseguiamo un ciclo 10volte per inserire i numeri random
			//del codice a barre...e nel caso non esiste lo ritorniamo
			for(int i=0;i<10;i++){
				random=new Random();
				int n=getRandomInteger(START, END, random);
				sb.append(n);
			}
			// stringa finale che sta per personal barcode
			sb.append("PBC");
		}while(existBarcode(sb.toString()));
		String returnString = sb.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("getRandomBarcode() - end");
		}
		return returnString;
	}
	
	private static int getRandomInteger(int aStart, int aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart); 
	    return randomNumber;
	  }


	private boolean existBarcode(String barcode) {
		if (logger.isDebugEnabled()) {
			logger.debug("existBarcode(String) - start");
		}

		if(barcode==null){
			if (logger.isDebugEnabled()) {
				logger.debug("existBarcode(String) - end");
			}
			return false;
		}
		Articolo articolo=new Articolo();
		articolo.setCodbarre(barcode);
		List articoli=findByExample(articolo);
		if(articoli.size()>0){
			if (logger.isDebugEnabled()) {
				logger.debug("existBarcode(String) - end");
			}
			return true;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("existBarcode(String) - end");
		}
		return false;
	}
}
