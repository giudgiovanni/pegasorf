package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import it.infolabs.hibernate.exception.FindAllEntityException;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Ordini.
 * @see it.infolabs.hibernate.Scarico
 * @author Hibernate Tools
 */
public class ScaricoHome extends BusinessObjectHome{

	private static final Log log = LogFactory.getLog(ScaricoHome.class);

	private static final ScaricoHome instance=new ScaricoHome();

	private ScaricoHome() {
		super();
	}

	public static ScaricoHome getInstance() {
		return instance;
	}

	public void persist(Scarico transientInstance) {
		log.debug("persisting Ordini instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Scarico instance) {
		log.debug("attaching dirty Ordini instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Scarico instance) {
		log.debug("attaching clean Ordini instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Scarico persistentInstance) {
		log.debug("deleting Ordini instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Scarico merge(Scarico detachedInstance) {
		log.debug("merging Ordini instance");
		try {
			Scarico result = (Scarico) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Scarico findById(long id) {
		log.debug("getting Ordini instance with id: " + id);
		try {
			Scarico instance = (Scarico) sessionFactory.getCurrentSession().get(
					"it.infolabs.hibernate.Scarico", id);
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

	@SuppressWarnings("unchecked")
	public List<Scarico> findByExample(Scarico instance) {
		log.debug("finding Ordini instance by example");
		try {
			List<Scarico> results = (List<Scarico>) sessionFactory
					.getCurrentSession().createCriteria(
							"it.infolabs.hibernate.Scarico").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Ritorna la lista di tutti gli scarichi con pagamento in sospeso
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> allSospesi() throws FindAllEntityException {
		log.debug("finding All Sospesi instance by example");
		try {
			StringBuilder sb = new StringBuilder();
//			sb.append("select sc.* "
//					+ "from scarico sc "
//					+ "left join pagamento p on sc.idpagamento = p.idpagamento "
//					+ "left join cliente c on sc.idcliente = c.idcliente "
//					+ "where p.nome = 'SOSPESO'");
			sb.append("select sc.idordine, c.nome, c.cognome, sc.data_documento, ");
			sb.append("sum(asvi.prezzo_vendita*asvi.qta) as imponibile,  ");
			sb.append("sum(asvi.prezzo_vendita*asvi.qta*asvi.iva/100) as imposta, ");
			sb.append("sum(((asvi.prezzo_vendita+asvi.prezzo_vendita*asvi.iva/100) - (asvi.prezzo_acquisto+asvi.prezzo_acquisto*asvi.iva/100))*asvi.qta) as agio ");
			sb.append("from scarico sc  ");
			sb.append("left join pagamento p on sc.idpagamento = p.idpagamento  ");
			sb.append("left join cliente c on sc.idcliente = c.idcliente ");
			sb.append("left join articoli_scaricati_view asvi on sc.idordine = asvi.idordine ");
			sb.append("where p.nome = 'SOSPESO' ");
			sb.append("group by sc.idordine, c.nome, c.cognome, sc.data_documento ");
			List<Object> results = (List<Object>) sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).list();
//				.addEntity("sc", Scarico.class).list();
			log.debug("find All Sospesi successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find All Sospesi failed", re);
			throw re;
		}
	}
}
