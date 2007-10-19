/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.exception.IDNonValido;

/**
 * @author Hunter
 * 
 */
public class Reparto {

	private Date dataCreazione;
	private DBManager dbm;
	private String descrizione;
	private int idReparto;

	private String nome;

	/**
	 * Costruttore di default
	 * 
	 */
	public Reparto() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}

	

	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allReparti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idreparto || ' - ' || nome from reparti";
		pst = dbm.getNewStatement();
		rs = pst.executeQuery(query);
		rs.last();
		o = new String[rs.getRow()];
		rs.beforeFirst();
		int pos = 0;
		while (rs.next()) {
			o[pos] = rs.getString(1);
			pos++;
		}
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return o;
	}

	/**
	 * @param idReparto2
	 * @throws SQLException
	 */
	public void caricaDati(int idReparto) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from reparti where idreparto=" + idReparto;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.descrizione = rs.getString("descrizione");
		this.nome = rs.getString("nome");
		this.idReparto = rs.getInt("idreparto");
		this.dataCreazione = rs.getDate("data_creazione");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella un reparto dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idArticolo
	 *            il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteCliente(int idReparto) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idReparto <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM reparti WHERE idreparto=" + idReparto;

		try {
			cancellati = st.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return cancellati;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public DBManager getDBManager() {
		return dbm;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getIdReparto() {
		return idReparto;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Il seguente metodo inserisce il reparto nella tabella corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertReparto() {

		idReparto = dbm.getNewID("reparti", "idreparto");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into reparti values (?,?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		java.util.Date data = new java.util.Date();
		this.dataCreazione = new Date(data.getTime());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idReparto);
			pst.setDate(2, dataCreazione);
			pst.setString(3, nome);
			pst.setString(4, this.descrizione);

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public void setDBManager(DBManager dbm) {
		this.dbm = dbm;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIdReparto(int idReparto) {
		this.idReparto = idReparto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il seguente metodo aggiorna il reparto nella tabella corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateReparto() throws IDNonValido {

		if (idReparto <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE reparti SET idreparto=?,"
				+ "data_creazione=?,nome=?,descrizione=? WHERE idreparto=?";
		dataCreazione = new Date(new java.util.Date().getTime());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idReparto);
			pst.setDate(2, dataCreazione);
			pst.setString(3, nome);
			pst.setString(4, this.descrizione);
			pst.setInt(5, this.idReparto);

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}
}
