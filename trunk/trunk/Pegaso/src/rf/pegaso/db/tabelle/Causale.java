/**
 * 
 */
package rf.pegaso.db.tabelle;

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
public class Causale {

	private DBManager dbm;
	private int idCausale;
	private String nome;

	public Causale() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allCausali() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idcausale || ' - ' || nome from causale";
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
	 * @param idUm2
	 * @throws SQLException
	 */
	public void caricaDati(int id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from causale where idcausale=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.nome = rs.getString("nome");
		this.idCausale = rs.getInt("idcausale");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella una causale dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idPagamento
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteCausale(int idCausale) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idCausale <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM causale WHERE idcausale=" + idCausale;

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

	public DBManager getDBManager() {
		return dbm;
	}

	public int getIdCausale() {
		return idCausale;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Il seguente metodo inserisce una nuova causale nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idCausale è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertCausale() throws IDNonValido {

		idCausale = dbm.getNewID("causale", "idcausale");
		if (idCausale <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into causale values (?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idCausale);
			pst.setString(2, nome);

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

	public void setDBManager(DBManager dbm) {
		this.dbm = dbm;
	}

	public void setIdCVoid(int idCausale) {
		this.idCausale = idCausale;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il seguente metodo aggiorna la causale nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idCausale è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateCausale() throws IDNonValido {

		if (idCausale <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE causale SET idcausale=?,"
				+ "nome=? WHERE idcausale=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idCausale);
			pst.setString(2, nome);
			pst.setInt(3, this.idCausale);

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
