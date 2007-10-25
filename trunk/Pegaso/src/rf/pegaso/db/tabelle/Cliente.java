/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.exception.IDNonValido;

/**
 * @author Hunter
 * 
 */
public class Cliente {

	private String cap;
	private String cell;
	private String citta;
	private String codfisc;
	private String cognome;
	private Date dataInserimento;
	private DBManager dbm;
	private String email;
	private String fax;
	private int idCliente;
	private String nome;
	private String note;
	private String piva;
	private String provincia;
	private String tel;
	private String via;

	private String website;

	/**
	 * Costruttore di default
	 * 
	 */
	public Cliente() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}

	

	/**
	 * @param idCliente2
	 * @throws SQLException
	 */
	public void caricaDati(int idCliente) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from clienti where idCliente=" + idCliente;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.cap = rs.getString("cap");
		this.cell = rs.getString("cell");
		this.citta = rs.getString("citta");
		this.codfisc = rs.getString("codfisc");
		this.cognome = rs.getString("cognome");
		this.nome = rs.getString("nome");
		this.dataInserimento = rs.getDate("data_inserimento");
		this.email = rs.getString("email");
		this.fax = rs.getString("fax");
		this.idCliente = idCliente;
		this.note = rs.getString("note");
		this.piva = rs.getString("piva");
		this.provincia = rs.getString("provincia");
		this.tel = rs.getString("tel");
		this.via = rs.getString("via");
		this.website = rs.getString("website");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella un Cliente dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idFornitore
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteCliente(int idCliente) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idCliente <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM clienti WHERE idCliente=" + idCliente;

		try {
			cancellati = st.executeUpdate(delete);
			dbm.notifyDBStateChange();
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
		return cancellati;
	}

	public String getCap() {
		return cap;
	}

	public String getCellulare() {
		return cell;
	}

	public String getCitta() {
		return citta;
	}

	public String getCodfisc() {
		return codfisc;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public DBManager getDBManager() {
		return dbm;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getNote() {
		return note;
	}

	public String getPiva() {
		return piva;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getTelefono() {
		return tel;
	}

	public String getVia() {
		return via;
	}

	public String getWebsite() {
		return website;
	}

	/**
	 * Il seguente metodo inserisce nuovo Cliente nella tabella corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertCliente() throws IDNonValido {

		idCliente = dbm.getNewID("clienti", "idCliente");
		if (idCliente <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into clienti values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Calendar c = Calendar.getInstance();
		dataInserimento = new Date(c.getTimeInMillis());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idCliente);
			pst.setDate(2, dataInserimento);
			pst.setString(3, nome);
			pst.setString(4, cognome);
			pst.setString(5, piva);
			pst.setString(6, codfisc);
			pst.setString(7, via);
			pst.setString(8, cap);
			pst.setString(9, citta);
			pst.setString(10, provincia);
			pst.setString(11, tel);
			pst.setString(12, cell);
			pst.setString(13, fax);
			pst.setString(14, email);
			pst.setString(15, website);
			pst.setString(16, note);
			ok = pst.executeUpdate();
			dbm.notifyDBStateChange();
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
		return ok;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setCellulare(String cell) {
		this.cell = cell;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCodfisc(String codfisc) {
		this.codfisc = codfisc;
	}

	/**
	 * @param cognome
	 *            the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setDBManager(DBManager dbm) {
		this.dbm = dbm;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setTelefono(String tel) {
		this.tel = tel;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Il seguente metodo aggiorna il Cliente nella tabella corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateCliente() throws IDNonValido {

		if (idCliente <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE clienti SET idCliente=?,"
				+ "data_inserimento=?,nome=?,cognome=?,piva=?,codfisc=?,via=?,cap=?,citta=?,provincia=?,tel=?,cell=?,fax=?,email=?,website=?,note=? WHERE idCliente=?";
		Calendar c = Calendar.getInstance();
		dataInserimento = new Date(c.getTimeInMillis());
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idCliente);
			pst.setDate(2, dataInserimento);
			pst.setString(3, nome);
			pst.setString(4, cognome);
			pst.setString(5, piva);
			pst.setString(6, codfisc);
			pst.setString(7, via);
			pst.setString(8, cap);
			pst.setString(9, citta);
			pst.setString(10, provincia);
			pst.setString(11, tel);
			pst.setString(12, cell);
			pst.setString(13, fax);
			pst.setString(14, email);
			pst.setString(15, website);
			pst.setString(16, note);
			pst.setInt(17, idCliente);
			ok = pst.executeUpdate();
			dbm.notifyDBStateChange();
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
		return ok;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public Object[] allClienti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idCliente || ' - ' || nome || ' ' ||cognome from clienti order by nome";
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
