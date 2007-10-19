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
public class Fornitore {

	/**
	 * @param codbarreF
	 * @return
	 * @throws SQLException
	 */
	public static int getIdFornitoreByCodBarre(String codbarreF)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton(); 
		ResultSet rs = null;
		String query = "select idfornitore from fornitori where codbarre=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codbarreF);
		rs = st.executeQuery();
		rs.next();
		int id = 0;
		boolean trovato = false;
		if (rs.getRow() < 1) {
			trovato = false;
			id = 1;
		} else {
			trovato = true;
			id = rs.getInt(1);
		}
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return id;
	}

	private String cap;
	private String cellulare;
	private String citta;
	private String codBarre;
	private String codfisc;
	private Date dataInserimento;
	private DBManager dbm;
	private String email;
	private String fax;
	private int idFornitore;
	private String nome;
	private String note;
	private String piva;
	private String provincia;
	private String telefono;

	private String via;

	private String website;

	/**
	 * Costruttore di default
	 * 
	 */
	public Fornitore() {
		this.dbm=DBManager.getIstanceSingleton();
	}

	

	/**
	 * @return
	 * @throws SQLException
	 */
	public Object[] allFornitori() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idFornitore || ' - ' || nome from fornitori order by nome";
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
	 * @param idCliente
	 * @throws SQLException
	 */
	public void caricaDati(int idFornitore) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from fornitori where idFornitore="
				+ idFornitore;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.cap = rs.getString("cap");
		this.cellulare = rs.getString("cell");
		this.citta = rs.getString("citta");
		this.codfisc = rs.getString("codfisc");
		this.nome = rs.getString("nome");
		this.dataInserimento = rs.getDate("data_inserimento");
		this.email = rs.getString("email");
		this.fax = rs.getString("fax");
		this.idFornitore = idFornitore;
		this.note = rs.getString("note");
		this.piva = rs.getString("piva");
		this.provincia = rs.getString("provincia");
		this.telefono = rs.getString("tel");
		this.via = rs.getString("via");
		this.website = rs.getString("website");
		this.codBarre = rs.getString("codbarre");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella un Fornitore dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 * 
	 * @param idFornitore
	 *            è il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteFornitore(int idFornitore) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idFornitore <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM fornitori WHERE idfornitore=" + idFornitore;

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
		return cellulare;
	}

	public String getCitta() {
		return citta;
	}

	public String getCodfisc() {
		return codfisc;
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

	public int getIdFornitore() {
		return idFornitore;
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
		return telefono;
	}

	public String getVia() {
		return via;
	}

	public String getWebsite() {
		return website;
	}

	/**
	 * Il seguente metodo inserisce nuovo fornitore nella tabella corrispondente
	 * 
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertFornitore() throws IDNonValido {

		idFornitore = dbm.getNewID("fornitori", "idFornitore");
		if (idFornitore <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into fornitori values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idFornitore);
			pst.setDate(2, dataInserimento);
			pst.setString(3, nome);
			pst.setString(4, piva);
			pst.setString(5, codfisc);
			pst.setString(6, via);
			pst.setString(7, cap);
			pst.setString(8, citta);
			pst.setString(9, provincia);
			pst.setString(10, telefono);
			pst.setString(11, cellulare);
			pst.setString(12, fax);
			pst.setString(13, email);
			pst.setString(14, website);
			pst.setString(15, note);
			pst.setString(16, codBarre);
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

	public void setCellulare(String cell1) {
		this.cellulare = cell1;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @param contents
	 */
	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;

	}

	public void setCodfisc(String codfisc) {
		this.codfisc = codfisc;
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

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
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

	public void setTelefono(String tel1) {
		this.telefono = tel1;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setWebsite(String website) {
		this.website = website;
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
	public int updateFornitore() throws IDNonValido {

		if (idFornitore <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE fornitori SET idFornitore=?,"
				+ "data_inserimento=?,nome=?,piva=?,codfisc=?,via=?,cap=?,citta=?,provincia=?,tel=?,cell=?,fax=?,email=?,website=?,note=?,codbarre=? WHERE idFornitore=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idFornitore);
			pst.setDate(2, dataInserimento);
			pst.setString(3, nome);
			pst.setString(4, piva);
			pst.setString(5, codfisc);
			pst.setString(6, via);
			pst.setString(7, cap);
			pst.setString(8, citta);
			pst.setString(9, provincia);
			pst.setString(10, telefono);
			pst.setString(11, cellulare);
			pst.setString(12, fax);
			pst.setString(13, email);
			pst.setString(14, website);
			pst.setString(15, note);
			pst.setString(16, codBarre);
			pst.setInt(17, idFornitore);
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

}
