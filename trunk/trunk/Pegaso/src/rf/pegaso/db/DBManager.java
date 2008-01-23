package rf.pegaso.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;

import rf.utility.db.DBEvent;
import rf.utility.db.DBObserved;
import rf.utility.db.DBStateChange;

//Implementazione del pattern Singleton
public class DBManager implements DBObserved {

	private static DBManager istanza=null;

	private LinkedList<DBStateChange> ascoltatori = null;

	private ConnectionManager cm;

	private Connection connessione;

	private ResultSet rs = null;

	private Statement st = null;


	public static DBManager getIstanceSingleton(){
		if(istanza==null){
			istanza =new DBManager();
		}
		return istanza;
	}


	/**
	 * metodo costruttore al quale viene passato un oggetto ConnectionManager
	 * che altro non è che la connessione al DataBase.
	 *
	 * @param cm
	 */
	private DBManager() {

		ascoltatori = new LinkedList<DBStateChange>();
		this.cm = new ConnectionManager();
		this.connessione = this.cm.getConnection();
		try {
			st = connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void addDBStateChange(DBStateChange dbChange) {
		ascoltatori.add(dbChange);

	}

	/**
	 * Questo metodo chiude la connessione con il DB.
	 */
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null)
				st.close();
			connessione.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @param query
	 * @return
	 */
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {
			st = connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;
	}

	public synchronized int executeUpdate(String query) {

		int i = 0;
		try {
			i = st.executeUpdate(query);
			if (i == -1) {
				System.out.println("db error : " + query);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return i;
	}

	/**
	 * Ritorna il nome della colonna in base alla sua posizione
	 *
	 * @param col
	 *            è la posizione della colonna
	 * @param tabella
	 *            è il nome della tabella su cui effettuare le operazioni
	 * @return una stringa che è il nome della colonna
	 */
	public String getColumnName(int col, String tabella) {

		ResultSetMetaData rsmd = null;
		String nome = "";
		String query = "SELECT * FROM " + tabella;
		try {
			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			nome = rsmd.getColumnLabel(col + 1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nome;
	}

	public Connection getConnessione() {
		return connessione;
	}

	public int getNewID(String tabella, String colonna) {
		int newID = 0;
		String query = "";
		query = "SELECT MAX(" + colonna + ") FROM " + tabella;
		try {
			rs = st.executeQuery(query);
			rs.next();
			newID = (rs.getInt(1)) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newID;
	}

	public PreparedStatement getNewPreparedStatement(String query) {
		PreparedStatement st = null;
		try {
			st = connessione.prepareStatement(query,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;
	}

	public Statement getNewStatement() {
		Statement st = null;
		try {
			st = connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return st;

	}

	/**
	 * Questo metodo ritorna il numero delle colonne presenti in una tabella
	 *
	 * @param tabella
	 *            è il nome della tabella su cui effettuare le operazioni
	 * @return un intero che è il numero delle colonne
	 */
	public int getNumeroColonne(String tabella) {

		ResultSetMetaData rsmd = null;
		int nColonne = 0;
		String query = "SELECT * FROM " + tabella;
		try {
			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			nColonne = rsmd.getColumnCount();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nColonne;
	}

	public int getNumeroRighe(String tabella) {

		String query = "SELECT * FROM " + tabella;
		int nRighe = 0;
		try {
			rs = st.executeQuery(query);
			rs.last();
			nRighe = rs.getRow();
		} catch (SQLException e) {
			System.out.println("Errore " + e);
			return 0;
		}
		return nRighe;
	}

	public Object getValueAt(int r, int c, String tabella) {

		Object o = null;
		String query = "SELECT * FROM " + tabella;
		try {
			rs = st.executeQuery(query);
			rs.absolute(r + 1);
			o = rs.getObject(c + 1);
		} catch (SQLException e) {
			System.out.println("Errore " + e);
			return null;
		}
		return o;
	}

	public void notifyDBStateChange() {
		ListIterator<DBStateChange> it = ascoltatori.listIterator();

		while (it.hasNext()) {

			it.next().stateChange();

		}

	}

	public void notifyDBStateChange(DBEvent dbe) {
		ListIterator<DBStateChange> it = ascoltatori.listIterator();

		while (it.hasNext()) {
			DBStateChange db = it.next();
			if (db.getTableName() != null) {
				if (db.getTableName().equalsIgnoreCase(dbe.getTabella())) {
					db.stateChange(dbe);
				}
			}
		}

	}

	public void removeDBStateChange(DBStateChange dbChange) {
		ascoltatori.remove(dbChange);

	}

	public void rimuovi(int id, String tabella) {

		String query = "DELETE FROM " + tabella + " WHERE id=" + id;

		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
