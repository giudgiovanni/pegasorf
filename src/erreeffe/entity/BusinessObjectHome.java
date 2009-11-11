package erreeffe.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class BusinessObjectHome {
	
	private static SessionFactory sessionFactory = getSessionFactory();

	private static final Log log = LogFactory.getLog(BusinessObjectHome.class);
	
	private static Configuration configuration;

	protected static SessionFactory getSessionFactory() {
		try {

			if(configuration==null){
				configuration=new Configuration();
				sessionFactory=(SessionFactory)configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			}
			return sessionFactory;
			//return (SessionFactory)new InitialContext().lookup("BookingsSessionFactory");
		} catch (Exception e) {
			log.error("Could not create SessionFactory", e);
			throw new IllegalStateException("Could not locate SessionFactory");
		}
	}
	
	public BusinessObjectHome(){
		
	}
	
	/**
	 * TODO documentation
	 * 
	 * @throws DAOException
	 */
	public void begin() {
		if (log.isDebugEnabled()) {
			log.debug("begin() - start");
		}
		if (!current().getTransaction().isActive()) {
		
				this.current().beginTransaction();
			
		} else {
			log.warn("begin() - transaction already started");
		}
	}

	/**
	 * TODO documentation
	 * 
	 * @throws DAOException
	 */
	public void commit(){
		if (log.isDebugEnabled()) {
			log.debug("commit() - start");
		}
		Transaction transaction = (Transaction)current().getTransaction();
		
			transaction.commit();
		
	}

	/**
	 * TODO documentation
	 * 
	 * @throws DAOException
	 */
	public void rollback() {
		if (log.isDebugEnabled()) {
			log.debug("rollback() - start");
		}
		Transaction transaction = (Transaction)current().getTransaction();
		
			transaction.rollback();
		
	}
	
	/**
	 * Returns the current Hibernate Session. If a Session was previously
	 * established during the same request the already established instance is
	 * returned otherwise a new instance is retriven from the Session Factory.
	 * 
	 * @return the current connection to the persistence layer or a new one if
	 *         not previously established.
	 * @throws DAOException if an error occurs trying to establish the
	 *         connection.
	 */
	protected Session current() {
		if (log.isDebugEnabled()) {
			log.debug("current() - start");
		}
		Session session = getSessionFactory().getCurrentSession();
		if ( !session.isOpen() ){
			session = getSessionFactory().openSession();
		}
		return session;
	}

}
