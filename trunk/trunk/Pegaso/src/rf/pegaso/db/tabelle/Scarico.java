/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.tabelle.exception.IDNonValido;

/**
 * @author Hunter
 * 
 */
public class Scarico {
	public static boolean codiceBarrePresenteInScarico(String codbarre, int idordine) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		String query = "select codbarre from articoli_scaricati_view where codbarre=? and idordine=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codbarre);
		st.setInt(2, idordine);
		rs = st.executeQuery();
		rs.next();
		boolean trovato = false;
		if (rs.getRow() < 1)
			trovato = false;
		else
			trovato = true;
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return trovato;
	}

	public static int getMaxID() {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		return dbm.getNewID("ordini", "idordine") - 1;
	}

	public static double getTotDettaglioImponibile(int idScarico2)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum(prezzo_dettaglio*qta) from articoli_scaricati_view where idordine="
				+ idScarico2;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotDettaglioImposta(int idScarico2)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum((prezzo_dettaglio/100*iva)*qta) from articoli_scaricati_view where idordine="
				+ idScarico2;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotIngrossoImponibile(int idScarico2)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum(prezzo_ingrosso*qta) from articoli_scaricati_view where idordine="
				+ idScarico2;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotAcquistoImponibileByOrder(int idScarico2) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum(prezzo_acquisto*qta) from articoli_scaricati_view where idordine="
				+ idScarico2;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotAcquistoImponibileAllOrders()
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum(prezzo_acquisto*qta) from articoli_scaricati_view";
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotAcquistoImpostaByOrder(int idScarico) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum((prezzo_acquisto/100*iva)*qta) from articoli_scaricati_view where idordine="
				+ idScarico;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotAcquistoImpostaAllOrders()
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum((prezzo_acquisto/100*iva)*qta) from articoli_scaricati_view";
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static double getTotIngrossoImposta(int idScarico2)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		Statement st = dbm.getNewStatement();
		String query = "select sum((prezzo_ingrosso/100*iva)*qta) from articoli_scaricati_view where idordine="
				+ idScarico2;
		rs = st.executeQuery(query);
		rs.next();
		double tot = rs.getDouble(1);
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return tot;
	}

	public static int insertScaricoInizialeZero(int idArticolo) {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		// Inseriamo lo scarico all'interno del db
		int idScarico = 0;
		int idCliente = 0;
		String note = "";
		int ok = 0;
		PreparedStatement pst = null;
		try {
			if (!Scarico.isOrderExsist(idScarico)) {

				String update = "insert into ordini values (?,?,?,?,?)";
				// preleviamo la data di inserimento
				// e la impostiamo nelle proprietà
				java.util.Date data = new java.util.Date();
				Date dataScarico = new Date(data.getTime());
				Time oraScarico = new Time(data.getTime());
				pst = dbm.getNewPreparedStatement(update);
				try {
					pst.setInt(1, idScarico);
					pst.setInt(2, idCliente);
					pst.setDate(3, dataScarico);
					pst.setTime(4, oraScarico);
					pst.setString(5, note);
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

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Inseriamo l'articolo all'interno del db
		// con qta 0 per effettuare il calcolo della
		// giacenza
		int qta = 0;
		int sconto = 0;
		String query = "insert into dettaglio_ordini values(?,?,?,?)";
		pst = dbm.getNewPreparedStatement(query);
		try {
			pst.setInt(1, idScarico);
			pst.setInt(2, idArticolo);
			pst.setInt(3, qta);
			pst.setInt(4, sconto);
			// inserimento
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pst != null)
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		dbm.notifyDBStateChange();
		return ok;
	}

	/**
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	public static boolean isOrderExsist(int idordine)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		Statement st = dbm.getNewStatement();
		ResultSet rs = null;
		String query = "select * from ordini where idordine=" + idordine;
		rs = st.executeQuery(query);
		rs.last();
		int nRow = rs.getRow();
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		if (nRow <= 0)
			return false;
		return true;
	}

	private Date dataScarico;

	private DBManager dbm;

	private int idCliente;

	private int idScarico;

	private String note;

	private Time oraScarico;

	private Date dataDocumento;

	private String numDocumento;

	private int idDocumento;

	public Scarico() {
		this.dbm = DBManager.getIstanceSingleton(); 
	}

	public void caricaDati(int id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from ordini where idordine=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.idScarico = rs.getInt("idordine");
		this.idCliente = rs.getInt("idcliente");
		this.dataScarico = rs.getDate("data_ordine");
		this.oraScarico = rs.getTime("ora_ordine");
		this.note = rs.getString("note");
		this.dataDocumento = rs.getDate("data_documento");
		this.numDocumento = rs.getString("num_documento");
		this.idDocumento = rs.getInt("iddocumento");
		if (st != null)
			st.close();
	}

	public void deleteArticolo(int idArticolo) throws SQLException {
		String query = "delete from dettaglio_ordini where idArticolo=? and idordine=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		pst.setInt(2, idScarico);
		// inserimento
		pst.executeUpdate();
		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int deleteScarico(int idScarico) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idScarico <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM ordini WHERE idordine=" + idScarico;

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
	 * @return the dataScarico
	 */
	public Date getDataScarico() {
		return dataScarico;
	}

	/**
	 * @return the idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * @return the idScarico
	 */
	public int getIdScarico() {
		return idScarico;
	}

	/**
	 * 
	 */
	public int getNewID() {
		return dbm.getNewID("ordini", "idordine");

	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @return the oraScarico
	 */
	public Time getOraScarico() {
		return oraScarico;
	}

	public int getQuantitaScaricata(int idArticolo) throws IDNonValido,
			SQLException, ResultSetVuoto {
		if (this.idScarico <= 0)
			throw new IDNonValido();
		String query = "select qta from dettaglio_ordini where idarticolo=? and idordine=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		pst.setInt(2, this.idScarico);
		ResultSet rs = pst.executeQuery();
		rs.next();
		if (rs.getRow() < 1)
			throw new ResultSetVuoto();
		int qta = rs.getInt(1);
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return qta;
	}

	public void insertArticolo(int idArticolo, int qta, int sconto)
			throws SQLException {

		String query = "insert into dettaglio_ordini values(?,?,?,?)";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idScarico);
		pst.setInt(2, idArticolo);
		pst.setInt(3, qta);
		pst.setInt(4, sconto);

		// inserimento
		pst.executeUpdate();

		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int insertScarico() {

		idScarico = dbm.getNewID("ordini", "idordine");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into ordini values (?,?,?,?,?,?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		java.util.Date data = new java.util.Date();
		this.dataScarico = new Date(data.getTime());
		this.oraScarico = new Time(data.getTime());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idScarico);
			pst.setInt(2, idCliente);
			pst.setDate(3, dataScarico);
			pst.setTime(4, oraScarico);
			pst.setString(5, note);
			pst.setInt(6, this.idDocumento);
			pst.setString(7, numDocumento);
			pst.setDate(8, dataDocumento);

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
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	public boolean isInsert(int idordine) throws SQLException {
		Statement st = dbm.getNewStatement();
		ResultSet rs = null;
		String query = "select * from ordini where idordine=" + idordine;
		rs = st.executeQuery(query);
		rs.last();
		int nRow = rs.getRow();
		if (nRow <= 0)
			return false;
		return true;
	}

	/**
	 * @param dataScarico
	 *            the dataScarico to set
	 */
	public void setDataScarico(Date dataCarico) {
		this.dataScarico = dataCarico;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(int idFornitore) {
		this.idCliente = idFornitore;
	}

	/**
	 * @param idScarico
	 *            the idScarico to set
	 */
	public void setIdScarico(int idCarico) {
		this.idScarico = idCarico;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @param oraScarico
	 *            the oraScarico to set
	 */
	public void setOraScarico(Time oraCarico) {
		this.oraScarico = oraCarico;
	}

	public void updateArticolo(int idArticolo, int qta, int sconto)
			throws SQLException {

		String query = "update dettaglio_ordini set qta=?,sconto=? where idordine=? and idarticolo=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);

		pst.setInt(1, qta);
		pst.setDouble(2, sconto);
		pst.setInt(3, idScarico);
		pst.setInt(4, idArticolo);

		// inserimento
		pst.executeUpdate();

		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int updateScarico() throws IDNonValido {

		if (idScarico <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE ordini SET idordine=?,"
				+ "idcliente=?,data_ordine=?,ora_ordine=?,note=?, iddocumento=?,num_documento=?,data_documento=? WHERE idordine=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idScarico);
			pst.setInt(2, idCliente);
			pst.setDate(3, dataScarico);
			pst.setTime(4, oraScarico);
			pst.setString(5, this.note);
			pst.setInt(6, idDocumento);
			pst.setString(7, numDocumento);
			pst.setDate(8, dataDocumento);
			pst.setInt(9, idScarico);
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

	public void setDataDocumento(Date date) {
		this.dataDocumento = date;

	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setNumDocumento(String text) {
		this.numDocumento = text;

	}

	public void deleteAllArticoliScaricati() throws SQLException {
		String query = "delete from dettaglio_ordini where idordine="
				+ idScarico;
		Statement st = dbm.getNewStatement();
		st.executeUpdate(query);
		if (st != null)
			st.close();
		dbm.notifyDBStateChange();

	}

	public String getNumDocumento() {
		return this.numDocumento;
	}

	public java.util.Date getDataDocumento() {
		return this.dataDocumento;
	}

}
