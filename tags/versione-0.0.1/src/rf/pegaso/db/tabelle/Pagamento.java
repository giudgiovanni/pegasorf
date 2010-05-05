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
public class Pagamento {

	private DBManager dbm;
	private int idPagamento;
	private String nome;

	public Pagamento() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allPagamenti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idpagamento || ' - ' || nome from pagamento";
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
		String query = "select * from pagamento where idpagamento=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.nome = rs.getString("nome");
		this.idPagamento = rs.getInt("idpagamento");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella una tipologia di pagamento dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idPagamento
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteUnitaDiMisura(int idPgamento) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idPagamento <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM pagamento WHERE idpagamento=" + idPagamento;

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

	public int getIdPagamento() {
		return idPagamento;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Il seguente metodo inserisce una nuova modalità di pagamento nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idPagamento è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertModalitaPagamento() throws IDNonValido {

		idPagamento = dbm.getNewID("pagamento", "idpagamento");
		if (idPagamento <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into pagamento values (?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idPagamento);
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

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il seguente metodo aggiorna la modalità di pagamento nella tabella
	 * corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idPagamento è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateModalitaPagamento() throws IDNonValido {

		if (idPagamento <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE pagamento SET idpagamento=?,"
				+ "nome=? WHERE idpagamento=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idPagamento);
			pst.setString(2, nome);
			pst.setInt(3, this.idPagamento);

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
