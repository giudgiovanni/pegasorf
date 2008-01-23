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
public class ContoCorrente {

	private String iban;
	private DBManager dbm;
	private int cab;
	private int abi;
	private int cc;
	private int idconto;
	private int idbanca;


	/**
	 * Costruttore di default
	 *
	 */
	public ContoCorrente() {
		this.dbm=DBManager.getIstanceSingleton();
	}

	/**
	 * Costruttore di default
	 *
	 */
	public ContoCorrente(int idbanca) {
		this.dbm=DBManager.getIstanceSingleton();
		this.idbanca=idbanca;
	}

	public ContoCorrente(int idconto,int idbanca) {
		this.dbm=DBManager.getIstanceSingleton();
		this.idconto=idconto;
		this.idbanca=idbanca;
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allContiCorrenti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idconto || ' - ' || cc from conto_bancario";
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
	public void caricaDati(int id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from conto_bancario where idconto=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.idconto=id;
		this.abi = rs.getInt("abi");
		this.cab = rs.getInt("cab");
		this.cc = rs.getInt("cc");
		this.idbanca = rs.getInt("idbanca");
		this.iban = rs.getString("iban");
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
	public int deleteConto(int id) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (id <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM conto_bancario WHERE idconto=" + id;

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



	/**
	 * Il seguente metodo inserisce il reparto nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertConto() {

		idconto = dbm.getNewID("conto_bancario", "idconto");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into conto_bancario values (?,?,?,?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idconto);
			pst.setInt(2, this.idbanca);
			pst.setInt(3, this.abi);
			pst.setInt(4, this.cab);
			pst.setInt(5, this.cc);
			pst.setString(6, this.iban);


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


	/**
	 * Il seguente metodo aggiorna il reparto nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateConto() throws IDNonValido {

		if (idconto <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE conto_bancario SET idconto=?,idbanca=?,abi=?,cab=?,cc=?,iban=? WHERE idconto=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idconto);
			pst.setInt(2, this.idbanca);
			pst.setInt(3, this.abi);
			pst.setInt(4, this.cab);
			pst.setInt(5, this.cc);
			pst.setString(6, this.iban);

			pst.setInt(7, idconto);

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



	public int getIdbanca() {
		return idbanca;
	}



	public void setIdbanca(int idbanca) {
		this.idbanca = idbanca;
	}

	public int getAbi() {
		return abi;
	}

	public void setAbi(int abi) {
		this.abi = abi;
	}

	public int getCab() {
		return cab;
	}

	public void setCab(int cab) {
		this.cab = cab;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public DBManager getDbm() {
		return dbm;
	}

	public void setDbm(DBManager dbm) {
		this.dbm = dbm;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getIdconto() {
		return idconto;
	}

	public void setIdconto(int idconto) {
		this.idconto = idconto;
	}

	public String[] allContiCorrentiByIDBanca(int id) throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idconto || ' - ' || cc from conto_bancario where idbanca="+id;
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


}
