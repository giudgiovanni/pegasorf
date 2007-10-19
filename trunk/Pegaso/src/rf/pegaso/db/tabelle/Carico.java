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
import rf.utility.db.MyResultSet;

/**
 * @author Hunter
 * 
 */
public class Carico {
	/**
	 * @param dbm2
	 * @param text
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	public static boolean codiceBarrePresenteInScarico(String codbarre, int idcarico) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		String query = "select codbarre from articoli_caricati_view where codbarre=? and idcarico=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codbarre);
		st.setInt(2, idcarico);
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

	private Date dataCarico;

	private Date dataDocumento;

	private DBManager dbm;
	private int idCarico;
	private int idDocumento;
	private int idFornitore;

	private String note;

	private String numDocumento;

	private Time oraCarico;

	public Carico() {
		this.dbm =DBManager.getIstanceSingleton(); 
	}

	public void caricaDati(int idCarico) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from carichi where idcarico=" + idCarico;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.idCarico = rs.getInt("idcarico");
		this.idFornitore = rs.getInt("idfornitore");
		this.dataCarico = rs.getDate("data_carico");
		this.oraCarico = rs.getTime("ora_carico");
		this.note = rs.getString("note");
		this.dataDocumento = rs.getDate("data_documento");
		this.idDocumento = rs.getInt("iddocumento");
		this.numDocumento = rs.getString("num_documento");
		if (st != null)
			st.close();
	}

	public void deleteAllArticoliCaricati() throws SQLException {
		String query = "delete from dettaglio_carichi where idcarico="
				+ idCarico;
		Statement st = dbm.getNewStatement();
		st.executeUpdate(query);
		if (st != null)
			st.close();
		dbm.notifyDBStateChange();
	}

	public void deleteArticolo(int idArticolo) throws SQLException {
		String query = "delete from dettaglio_carichi where idArticolo=? and idCarico=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		pst.setInt(2, idCarico);
		// inserimento
		pst.executeUpdate();
		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int deleteCarico(int idCarico) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idCarico <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM carichi WHERE idcarico=" + idCarico;

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

	public Object[][] getAllArticoliCaricati() throws SQLException {
		String query = "SELECT A.codBarre, A.descrizione, A.iva, A.um, D.qta, D.prezzo_Acquisto "
				+ "FROM Articoli AS A, Carichi AS C, Dettaglio_Carichi AS D, Fornitori AS F "
				+ "WHERE A.idArticolo=D.idArticolo AND C.idCarico=D.idCarico AND C.idFornitore=F.idFornitore";

		Statement pst = dbm.getNewStatement();
		ResultSet rs = pst.executeQuery(query);
		MyResultSet mrs = new MyResultSet(rs);
		Object[][] o = mrs.getAllObject();
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return o;
	}

	/**
	 * @return the dataCarico
	 */
	public Date getDataCarico() {
		return dataCarico;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	/**
	 * @return the idCarico
	 */
	public int getIdCarico() {
		return idCarico;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @return the idFornitore
	 */
	public int getIdFornitore() {
		return idFornitore;
	}

	/**
	 * 
	 */
	public int getNewID() {
		return dbm.getNewID("carichi", "idCarico");

	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @return the oraCarico
	 */
	public Time getOraCarico() {
		return oraCarico;
	}

	/**
	 * @param idArticolo
	 * @return
	 * @throws SQLException
	 * @throws IDNonValido
	 * @throws ResultSetVuoto
	 */
	public int getQuantitaCaricata(int idArticolo) throws SQLException,
			IDNonValido, ResultSetVuoto {
		if (this.idCarico <= 0)
			throw new IDNonValido();
		String query = "select qta from dettaglio_carichi where idarticolo=? and idcarico=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		pst.setInt(2, this.idCarico);
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

	public void insertArticolo(int idArticolo, int qta, double prezzoAcquisto)
			throws SQLException {

		String query = "insert into dettaglio_carichi values(?,?,?,?)";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		pst.setInt(2, idCarico);
		pst.setInt(3, qta);
		pst.setDouble(4, prezzoAcquisto);

		// inserimento
		pst.executeUpdate();

		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int insertCarico() {

		idCarico = dbm.getNewID("carichi", "idCarico");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into carichi values (?,?,?,?,?,?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		java.util.Date data = new java.util.Date();
		// this.dataCarico = new Date(data.getTime());
		this.dataDocumento = dataDocumento;
		this.oraCarico = new Time(data.getTime());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idCarico);
			pst.setInt(2, idFornitore);
			pst.setDate(3, dataCarico);
			pst.setTime(4, oraCarico);
			pst.setString(5, note);
			pst.setInt(6, idDocumento);
			pst.setString(7, numDocumento);
			pst.setDate(8, this.dataDocumento);

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
	public boolean isInsert(int idcarico) throws SQLException {
		Statement st = dbm.getNewStatement();
		ResultSet rs = null;
		String query = "select * from carichi where idcarico=" + idcarico;
		rs = st.executeQuery(query);
		rs.last();
		int nRow = rs.getRow();
		if (nRow <= 0)
			return false;
		return true;
	}

	/**
	 * @param dataCarico
	 *            the dataCarico to set
	 */
	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	/**
	 * @param idCarico
	 *            the idCarico to set
	 */
	public void setIdCarico(int idCarico) {
		this.idCarico = idCarico;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @param idFornitore
	 *            the idFornitore to set
	 */
	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @param oraCarico
	 *            the oraCarico to set
	 */
	public void setOraCarico(Time oraCarico) {
		this.oraCarico = oraCarico;
	}

	public void updateArticolo(int idArticolo, int qta, double prezzoAcquisto)
			throws SQLException {

		String query = "update dettaglio_carichi set qta=?,prezzo_acquisto=? where idcarico=? and idarticolo=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);

		pst.setInt(1, qta);
		pst.setDouble(2, prezzoAcquisto);
		pst.setInt(3, idCarico);
		pst.setInt(4, idArticolo);

		// inserimento
		pst.executeUpdate();

		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

	public int updateCarico() throws IDNonValido {

		if (idCarico <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE carichi SET idcarico=?,"
				+ "idfornitore=?,data_carico=?,ora_carico=?,note=?,iddocumento=?,num_documento=?,data_documento=? WHERE idcarico=?";
		// dataCarico = new Date(new java.util.Date().getTime());
		// oraCarico = new Time(new java.util.Date().getTime());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idCarico);
			pst.setInt(2, idFornitore);
			pst.setDate(3, dataCarico);
			pst.setTime(4, oraCarico);
			pst.setString(5, this.note);
			pst.setInt(6, idDocumento);
			pst.setString(7, numDocumento);
			pst.setDate(8, dataDocumento);
			pst.setInt(9, this.idCarico);

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
