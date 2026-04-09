/*
 * Created on 12/04/2005
 */
package ar.org.sicel.persistence.util;

import java.util.Stack;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author pablo
 */
public class StandaloneHibernateStrategy implements HibernateStrategy {

	private static Logger log = Logger
			.getLogger(StandaloneHibernateStrategy.class);

	private SessionFactory factory;

	private ThreadLocal<Stack<Transaction>> tlTransaction = new ThreadLocal<Stack<Transaction>>();

	private ThreadLocal<Session> tlSession = new ThreadLocal<Session>();

	private static StandaloneHibernateStrategy instance;

	public static synchronized StandaloneHibernateStrategy getInstance() {
		if (instance == null)
			instance = new StandaloneHibernateStrategy();
		return instance;
	}

	private StandaloneHibernateStrategy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.org.sicel.proc.HibernateFactory#getCurrentSession()
	 */
	public synchronized Session getCurrentSession() {
		log.debug("Getting current session...");

		if (!isSessionOpen())
			return openNewSession();

		return tlSession.get();

		/*
		 * if (tlSession.get() == null) return openNewSession(); //
		 * tlSession.set(openNewSession()); //return tlSession.get(); return
		 * tlSession.get().peek();
		 */
	}

	public boolean isSessionOpen() {
		return ((tlSession.get() != null) && (tlSession.get() != null));
	}

	public void rollbackCurrentSession() {
		log.debug("Rollback of current session...");
		Transaction currentT = tlTransaction.get().pop();
		Session currentS = tlSession.get();
		tlSession.set(null);
		try {
			currentT.rollback();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				currentS.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Closes the Session local to the thread. No cierra la transaccion, hay que
	 * hacer rollback o commit antes !!!
	 */
	public void closeSession() throws HibernateException {
		
		if(!tlTransaction.get().isEmpty())
			throw new RuntimeException("No se puede cerrar la session porque todavia existen transacciones activas");
		
		Session currentS = tlSession.get();
		
		if(currentS == null)
			throw new RuntimeException("No se puede cerrar la session porque no existe ninguna abierta.");
		
	
		tlSession.get().close();
		tlSession.set(null);
	}

	public void commitCurrentSession() {
		log.debug("Committing current session...");
		Transaction currentT = tlTransaction.get().pop();
		try {
			currentT.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				Session currentS = tlSession.get();
				currentS.close();
				tlSession.set(null);
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void commitCurrentSessionCalif() throws HibernateException {
		log.debug("Committing current session...");
		Transaction currentT = tlTransaction.get().pop();
		try {
			currentT.commit();
			try {
				Session currentS = tlSession.get();
				currentS.close();
				tlSession.set(null);
			} catch (HibernateException e) {
				throw e;
			}
		} catch (HibernateException he) {
			try {
				Session currentS = tlSession.get();
				currentS.close();
				tlSession.set(null);
			} catch (HibernateException er) {
				er.printStackTrace();
			}
			throw he;
		}
	}
	
	public Session openNewSession() {

		if (tlSession.get() != null)
			throw new RuntimeException(
					"No se puede abrir una nueva session porque ya hay una abierta y no fue cerrada");

		try {
			log.debug("Abriendo una nueva session de hibernate");
			if (tlTransaction.get() == null) {
				tlTransaction.set(new Stack<Transaction>());
			}

			tlSession.set(getFactory().openSession());
			Transaction tx = tlSession.get().beginTransaction();
			tlTransaction.get().push(tx);
			return tlSession.get();
		} catch (HibernateException he) {
			log.debug("No se pudo abrir una nueva session de hibernate", he);
			return null;
		}
	}
	public void destroyTransactions() {
		
		Session currentS = tlSession.get();
		tlSession.set(null);
		try {
			currentS.clear();
			currentS.flush();
			tlSession.remove();
			if (tlTransaction.get() != null) {
				tlTransaction.get().removeAllElements();
				tlTransaction.set(new Stack<Transaction>());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}
	public Session destroyTransactionsAndOpenSession() {


		try {
			log.debug("Abriendo una nueva session de hibernate");
			if (tlTransaction.get() != null) {
				tlTransaction.get().removeAllElements();
				tlTransaction.set(new Stack<Transaction>());
			}

			tlSession.set(getFactory().openSession());
			Transaction tx = tlSession.get().beginTransaction();
			tlTransaction.get().push(tx);
			return tlSession.get();
		} catch (HibernateException he) {
			log.debug("No se pudo abrir una nueva session de hibernate", he);
			return null;
		}
	}
	private SessionFactory getFactory() {
		try {
			log.debug("Getting Factory...");
			if (factory == null) {
				log.warn("Getting Factory...---1");
				Configuration cf = new Configuration();
				log.warn("Getting Factory...---2");
				cf.configure();
				log.warn("Getting Factory...---3");
				factory = cf.buildSessionFactory();
				log.warn("Getting Factory...---4");
			}
			return factory;
		} catch (HibernateException he) {
			log
					.error(
							"No se pudo construir la SessionFactory de hibernate",
							he);
			return null;
		}
	}

	public void flushCurrentSession() {
		Session currentS = tlSession.get();
		try {
			currentS.flush();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
