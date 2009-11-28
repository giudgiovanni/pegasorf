package rf.utility.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class DBManager {
	private LinkedList<DBStateChange> listaAscoltatori = null;

	private ConnectionManager cm;

	private Connection connessione;

	private Statement st = null;

	private ResultSet rs = null;
	private static DBManager istanza=null;


	/**
	 * metodo costruttore al quale viene passato un oggetto ConnectionManager
	 * che altro non è che la connessione al DataBase.
	 *
	 * @param cm
	 */
	private DBManager() {

		this.listaAscoltatori = new LinkedList<DBStateChange>();
		this.cm = new ConnectionManager();
		this.connessione = this.cm.getConnection();
		try {
			st = connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {

			e.printStackTrace();
		}

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
		notifyDBStateChange();
		return i;
	}

	public List getPortate(String tabella, int cat) {
		LinkedList<Object> listaPortate = new LinkedList<Object>();
		String query = "SELECT * FROM " + tabella + " WHERE CAT=" + cat
				+ " ORDER BY descrizione ASC";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				listaPortate.add(rs.getString(2));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaPortate;
	}

	public void notifyDBStateChange() {
		ListIterator<DBStateChange> it = listaAscoltatori.listIterator();

		while (it.hasNext()) {

			it.next().stateChange();

		}
	}

	public void addDBStateChange(DBStateChange dbChange) {
		this.listaAscoltatori.add(dbChange);
	}

	public void removeDBStateChange(DBStateChange dbChange) {
		this.listaAscoltatori.remove(dbChange);
	}

	
	/**
	 * Controlla l'esistenza della tabella ed in caso non esista la crea.
	 */
	public void createTable(String nomeTabella, String query) {

		System.out.println(query);
		try {
			DatabaseMetaData dmd = this.connessione.getMetaData();
			rs = dmd.getTables(null, null, null, null);
			String temp = null;
			while (rs.next()) {
				temp = rs.getString(3);
				if (temp.equalsIgnoreCase(nomeTabella)) {
					System.out.println("Tabella " + nomeTabella + " esistente");
					return;
				}
			}
			st.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getNewID(String tabella) {
		int newID = 0;
		String query = "";
		if (tabella.equalsIgnoreCase("commissioni")) {
			query = "SELECT MAX(N_CONTRATTO) FROM " + tabella;
		} else
			query = "SELECT MAX(ID) FROM " + tabella;

		try {
			rs = st.executeQuery(query);
			rs.next();
			newID = (rs.getInt(1)) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newID;
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

	/**
	 * Il metodo inserisce una descrizione nella relativa tabella utilizzando i
	 * parametri forniti che sono ID la descrizione e la tabella dove inserire
	 * tutti i dati.
	 *
	 * @param newID
	 *            questo è l'ID che è anche chiave della tabella
	 * @param descrizione
	 *            questo parametro è la descrizione
	 * @param tabella
	 *            è il nome della tabella dove inseriri i dati
	 */
	public synchronized void inserisciDescrizione(int newID,
			String descrizione, double prezzo, String info, String tabella,
			int cat) {

		String query = "INSERT INTO " + tabella
				+ " (ID,DESCRIZIONE,PREZZO,INFO,CAT) VALUES (?,?,?,?,?)";
		PreparedStatement pst = getNewPreparedStatement(query);
		try {
			pst.setInt(1, newID);
			pst.setString(2, descrizione);
			pst.setDouble(3, prezzo);
			pst.setString(4, info);
			pst.setInt(5, cat);
			pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		notifyDBStateChange();

	}

	public void rimuovi(int id, String tabella) {

		String query = "DELETE FROM " + tabella + " WHERE id=" + id;

		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		notifyDBStateChange();
	}

	public static DBManager getIstanceSingleton(){
		if(istanza==null){
			istanza =new DBManager();
		}
		return istanza;
	}

	/**
	 * Questo metodo aggiorna il contenuto di una riga precisamente il campo
	 * descrizione attraverso l'uso della sua chiave ID
	 *
	 * @param id -
	 *            è appunto la chiave della riga da aggiornare
	 * @param descrizione -
	 *            è la descrizione aggiornata
	 * @param tabella -
	 *            è la tabella su cui operare l'aggiornamento
	 */
	public synchronized void aggiornaDescrizione(String id, String descrizione,
			double prezzo, String info, String tabella, int cat) {
		PreparedStatement pst = null;
		String query = "UPDATE " + tabella
				+ " SET descrizione=?, prezzo=?,info=? WHERE id=? and cat=?";
		pst = getNewPreparedStatement(query);
		try {
			pst.setString(1, descrizione);
			pst.setDouble(2, prezzo);
			pst.setString(3, info);
			pst.setInt(4, new Integer(id).intValue());
			pst.setInt(5, cat);
			pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		notifyDBStateChange();
	}

	public void commit() {
		executeUpdate("COMMIT");
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
			this.connessione.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo ritorna il numero delle righe presenti in un result set
	 *
	 * @param tabella
	 *            è il nome della tabella su cui operare
	 * @return un intero che è appunto il numero di righe presenti
	 */
	public int getNumeroRighe(String tabella, int cat) {

		String query = "SELECT * FROM " + tabella + " WHERE CAT=" + cat;
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

	/**
	 * Questo metodo ritorna il numero delle colonne presenti in un DataBase
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

	/**
	 * Questo metodo in base al numero di riga e colonna ritorna il valore
	 * presente in quella posizione.
	 *
	 * @param r
	 *            è il numero di riga
	 * @param c
	 *            è il numero di colonna
	 * @param tabella
	 *            è il numero della tabella su cui effettuare le operazioni
	 * @return un Object
	 */
	public Object getValueAt(int r, int c, String tabella, int cat) {

		Object o = null;
		String query = "SELECT * FROM " + tabella + " WHERE CAT=" + cat;
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

	public Connection getConnessione() {
		return connessione;
	}

	/**
	 * @param query
	 * @return
	 */
	public synchronized ResultSet executeQuery(String query) {
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





	/**
	 *
	 */
	public void notifyDBStateChange(String tabella) {
		ListIterator<DBStateChange> it = listaAscoltatori.listIterator();

		while (it.hasNext()) {
			DBStateChange db = it.next();
			if (db.getNomeTabella() != null) {
				if (db.getNomeTabella().equalsIgnoreCase(tabella)) {
					db.stateChange();
				}
			}
		}
	}

	public void notifyDBStateChange(DBEvent dbe) {
		ListIterator<DBStateChange> it = listaAscoltatori.listIterator();

		while (it.hasNext()) {
			DBStateChange db = it.next();
			if (db.getNomeTabella() != null) {
				if (db.getNomeTabella().equalsIgnoreCase(dbe.getTabella())) {
					db.stateChange(dbe);
				}
			}
		}
	}

	public void notifyRowStateChanged(int nRow) {
		notificaRowStateChanged(nRow);
	}

	/**
	 * @param row
	 */
	private void notificaRowStateChanged(int row) {
		RowEvent re = new RowEvent(row);
		ListIterator<DBStateChange> it = listaAscoltatori.listIterator();

		while (it.hasNext()) {
			it.next().rowStateChange(re);
		}

	}

}
