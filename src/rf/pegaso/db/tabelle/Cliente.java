/**
 *
 */
package rf.pegaso.db.tabelle;

import it.infolabs.hibernate.Clienti;
import it.infolabs.hibernate.ClientiHome;
import it.infolabs.hibernate.exception.FindByNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import rf.pegaso.db.tabelle.exception.IDClienteNonImpostato;
import rf.utility.DateManager;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

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
	private int provincia;
	private String tel;
	private String via;

	private String website;
	private Date dataNascita;
	private String documento;
	private String numDoc;
	private Date rilasciatoIl;
	private String rilasciatoDa;
	private String nazionalita;
	private String natoa;
	private String intestazione;

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
		this.provincia = rs.getInt("provincia");
		this.tel = rs.getString("tel");
		this.via = rs.getString("via");
		this.website = rs.getString("website");
		this.dataNascita = rs.getDate("data_nascita");
		this.documento = rs.getString("documento");
		this.numDoc = rs.getString("num_doc");
		this.rilasciatoIl = rs.getDate("rilasciato_il");
		this.rilasciatoDa = rs.getString("rilasciato_da");
		this.nazionalita = rs.getString("nazionalita");
		this.natoa = rs.getString("nato_a");
		this.intestazione = rs.getString("intestazione");

		if (st != null)
			st.close();

	}


	public Date getDataNascita() {
		return dataNascita;
	}



	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}



	public String getDocumento() {
		return documento;
	}



	public void setDocumento(String documento) {
		this.documento = documento;
	}



	public String getIntestazione() {
		return intestazione;
	}



	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}



	public String getNatoa() {
		return natoa;
	}



	public void setNatoa(String natoa) {
		this.natoa = natoa;
	}



	public String getNazionalita() {
		return nazionalita;
	}



	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}



	public String getNumDoc() {
		return numDoc;
	}



	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}



	public String getRilasciatoDa() {
		return rilasciatoDa;
	}



	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}



	public Date getRilasciatoIl() {
		return rilasciatoIl;
	}



	public void setRilasciatoIl(Date rilasciatoIl) {
		this.rilasciatoIl = rilasciatoIl;
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
	 * @throws SQLException
	 */
	public int deleteCliente(int idCliente) throws IDNonValido, SQLException {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idCliente <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM clienti WHERE idCliente=" + idCliente;


			cancellati = st.executeUpdate(delete);
			dbm.notifyDBStateChange();

				if (st != null)
					st.close();

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

	public int getProvincia() {
		return provincia;
	}

	public String getProvinciaToString() throws SQLException{
		Provincia p=new Provincia();
		p.caricaDati(this.provincia);
		return p.getProvincia();
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
	 * @throws FindByNotFoundException 
	 */
	public int insertCliente() throws IDNonValido, FindByNotFoundException {

		idCliente = dbm.getNewID("clienti", "idCliente");
		if (idCliente <= -1)
			throw new IDNonValido();
		
		ClientiHome.getInstance().begin();
		Clienti cliente=new Clienti();
		cliente.setCap(cap);
		cliente.setCell(cell);
		cliente.setCitta(citta);
		cliente.setCodfisc(codfisc);
		cliente.setCognome(cognome);
		cliente.setDataInserimento(new java.util.Date());
		cliente.setDataNascita(dataNascita);
		cliente.setRilasciatoIl(rilasciatoIl);
		cliente.setDocumentoCliente(null);
		cliente.setEmail(email);
		cliente.setEnte(null);
		it.infolabs.hibernate.Provincia provincia=it.infolabs.hibernate.ProvinciaHome.getInstance().findById(this.provincia);
		cliente.setProvincia(provincia);
		cliente.setFax(fax);
		cliente.setIntestazione(intestazione);
		cliente.setNatoA(this.natoa);
		cliente.setNazionalita(null);
		cliente.setNome(nome);
		cliente.setNote(note);
		cliente.setPiva(piva);
		cliente.setTel(tel);
		cliente.setVia(via);
		cliente.setWebsite(website);
		ClientiHome.getInstance().attachDirty(cliente);
		ClientiHome.getInstance().commitAndClose();
		
//		int ok = 0;
//		PreparedStatement pst = null;
//		String update = "insert into clienti values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		Calendar c = Calendar.getInstance();
//		dataInserimento = new Date(c.getTimeInMillis());
//		pst = dbm.getNewPreparedStatement(update);
//		try {
//			pst.setInt(1, idCliente);
//			pst.setDate(2, dataInserimento);
//			pst.setString(3, nome);
//			pst.setString(4, cognome);
//			pst.setString(5, piva);
//			pst.setString(6, codfisc);
//			pst.setString(7, via);
//			pst.setString(8, cap);
//			pst.setString(9, citta);
//			pst.setString(10, tel);
//			pst.setString(11, cell);
//			pst.setString(12, fax);
//			pst.setString(13, email);
//			pst.setString(14, website);
//			pst.setString(15, note);
//			pst.setDate(16, DateManager.convertDateToSqlDate(this.dataNascita));
//			pst.setString(17, documento);
//			pst.setString(18, numDoc);
//			pst.setDate(19, DateManager.convertDateToSqlDate(rilasciatoIl));
//			pst.setString(20, rilasciatoDa);
//			pst.setString(21, natoa);
//			pst.setString(22, intestazione);
//			pst.setString(23, nazionalita);
//			pst.setInt(24, provincia);
//			ok = pst.executeUpdate();
//			dbm.notifyDBStateChange();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pst != null)
//					pst.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		return 1;
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

	public void setProvincia(int provincia) {
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
	 * @param dbm2
	 */
	public Cliente(DBManager dbm) {
		this.dbm = dbm;
	}

	public Cliente(String nome, String cognome, DBManager dbm) {
		this.nome = nome;
		this.cognome = cognome;
		this.idCliente = -1;
		this.dbm = dbm;
	}

	public int deleteCliente() throws IDClienteNonImpostato, IDNonValido, SQLException {
		if(idCliente==-1)
			return -1;
		return deleteCliente(this.idCliente);
	}

	public Cliente getCliente(int idCliente) {
		// ConnectionManager con = new ConnectionManager();
		// DBManager dbm = new DBManager(con);
		ResultSet rs = dbm.executeQuery("SELECT * FROM clienti WHERE IDcliente="
				+ idCliente);
		Cliente c = new Cliente();
		try {
			if (!rs.next())
				return null;
			c.setCognome(rs.getString("cognome"));
			c.setEmail(rs.getString("email"));
			c.setIdCliente(idCliente);
			c.setNome(rs.getString("nome"));
			c.setTelefono(rs.getString("tel"));
			c.setVia(rs.getString("via"));
			c.setCellulare(rs.getString("cell"));
			c.setCodfisc(rs.getString("codfisc"));
			c.setDataNascita(rs.getDate("data_nascita"));
			c.setDocumento(rs.getString("documento"));
			c.setNazionalita(rs.getString("nazionalita"));
			c.setNumDoc(rs.getString("num_doc"));
			c.setPiva(rs.getString("piva"));
			c.setRilasciatoDa(rs.getString("rilasciato_da"));
			c.setRilasciatoIl(rs.getDate("rilasciato_il"));
			c.setCap(rs.getString("cap"));
			c.setDataInserimento(rs.getDate("data_inserimento"));
			c.setFax(rs.getString("fax"));
			c.setNote(rs.getString("note"));
			c.setWebsite(rs.getString("website"));
			c.setProvincia(rs.getInt("provincia"));


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public String getNomeCliente(int id_cliente) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s = "select nome from clienti where idcliente=?";
		String nome = "";
		pst = dbm.getNewPreparedStatement(s);
		try {
			pst.setInt(1, id_cliente);
			rs = pst.executeQuery();
			rs.next();
			nome = rs.getString("NOME");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return nome;
	}

	/**
	 * @param id_cliente
	 * @return
	 */
	public String getCognomeCliente(int id_cliente) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s = "select cognome from clienti where idcliente=?";
		String nome = "";
		pst = dbm.getNewPreparedStatement(s);
		try {
			pst.setInt(1, id_cliente);
			rs = pst.executeQuery();
			rs.next();
			nome = rs.getString("COGNOME");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return nome;
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
				+ "data_inserimento=?,nome=?,cognome=?,piva=?,codfisc=?,via=?,cap=?,citta=?,tel=?,cell=?,fax=?,email=?,website=?,note=?,data_nascita=?,documento=?,num_doc=?,rilasciato_il=?,rilasciato_da=?,nato_a=?,intestazione=?,nazionalita=?,provincia=? WHERE idCliente=?";
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
			pst.setString(10, tel);
			pst.setString(11, cell);
			pst.setString(12, fax);
			pst.setString(13, email);
			pst.setString(14, website);
			pst.setString(15, note);
			pst.setDate(16, dataNascita);
			pst.setString(17, documento);
			pst.setString(18, numDoc);
			pst.setDate(19, rilasciatoIl);
			pst.setString(20, rilasciatoDa);
			pst.setString(21, natoa);
			pst.setString(22, intestazione);
			pst.setString(23, nazionalita);
			pst.setInt(24, provincia);
			pst.setInt(25, idCliente);
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
