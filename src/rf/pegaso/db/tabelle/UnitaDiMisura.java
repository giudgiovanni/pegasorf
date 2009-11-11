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
public class UnitaDiMisura {

	private DBManager dbm;
	private String descrizione;
	private int idUm;

	private String nome;

	public UnitaDiMisura() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allUnitaDiMisura() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idUm || ' - ' || nome from um";
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
	public void caricaDati(int idUm) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from um where idUm=" + idUm;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		if ( rs.next() ){
			this.descrizione = rs.getString("descrizione");
			this.nome = rs.getString("nome");
			this.idUm = rs.getInt("idum");
			if (st != null)
				st.close();
		}

	}

	/**
	 * Questo metodo cancella una unità di misura dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idUm
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteUnitaDiMisura(int idUm) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idUm <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM um WHERE idum=" + idUm;

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

	public String getDescrizione() {
		return descrizione;
	}

	public int getIdUm() {
		return idUm;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Il seguente metodo inserisce una nuova unità di misura nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertUnitaDiMisura() throws IDNonValido {

		idUm = dbm.getNewID("um", "idUm");
		if (idUm <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into um values (?,?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idUm);
			pst.setString(2, nome);
			pst.setString(3, descrizione);

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

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIdUm(int idUm) {
		this.idUm = idUm;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il seguente metodo aggiorna l'unità di misura nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateUnitaDiMisura() throws IDNonValido {

		if (idUm <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE um SET idUm=?,"
				+ "nome=?,descrizione=? WHERE idUm=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idUm);
			pst.setString(2, nome);
			pst.setString(3, this.descrizione);
			pst.setInt(4, this.idUm);

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
