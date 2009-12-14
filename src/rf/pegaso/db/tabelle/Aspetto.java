/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

/**
 * @author Hunter
 * 
 */
public class Aspetto {

	private DBManager dbm;
	private int idAspetto;
	private String nome;

	public Aspetto() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allAspetti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idaspetto || ' - ' || nome from aspetto";
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
		String query = "select * from aspetto where idaspetto=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.nome = rs.getString("nome");
		this.idAspetto = rs.getInt("idaspetto");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella un aspetto dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idAspetto
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteAspetto(int idAspetto) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idAspetto <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM aspetto WHERE idaspetto=" + idAspetto;

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

	public int getIdAspetto() {
		return idAspetto;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Il seguente metodo inserisce un nuovo aspetto nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idAspetto è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertAspetto() throws IDNonValido {

		idAspetto = dbm.getNewID("aspetto", "idaspetto");
		if (idAspetto <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into aspetto values (?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idAspetto);
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

	public void setIdCVoid(int idAspetto) {
		this.idAspetto = idAspetto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il seguente metodo aggiorna l'aspetto nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idAspetto è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateAspetto() throws IDNonValido {

		if (idAspetto <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE aspetto SET idaspetto=?,"
				+ "nome=? WHERE idaspetto=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idAspetto);
			pst.setString(2, nome);
			pst.setInt(3, this.idAspetto);

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
