/**
 * @author Hunter
 *
 */

package rf.pegaso.db.tabelle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

public class Articolo {
	/**
	 * @param dbm2
	 * @param idArticolo2
	 * @return
	 * @throws SQLException
	 */
	public static int caricaQtaMagazzino(int idArticolo)
			throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton();
		ResultSet rs = null;
		String query = "select deposito from giacenza_articoli_view where idarticolo=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setInt(1, idArticolo);
		rs = st.executeQuery();
		rs.next();
		int qta = 0;
		boolean trovato = false;
		if (rs.getRow() < 1) {
			trovato = false;
			qta = 0;
		} else {
			trovato = true;
			qta = rs.getInt(1);
		}
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return qta;
	}

	private int caricoIniziale;

	private String codBarre;

	private String codFornitore;

	private String colore;

	private Date dataInserimento;

	private DBManager dbm;

	private String descrizione;

	// campi corrispondenti alla tabella Articolo
	private int idArticolo;

	private int idFornitore;

	private int idReparto;

	private String imballo;

	private int iva;

	private String note;

	private double peso;

	private double prezzoAcquisto;

	private double prezzoDettaglio;

	private double prezzoIngrosso;

	private int ricaricoDettaglio;

	private int ricaricoIngrosso;

	private int sconto;

	// ---------------------------------------------

	private int scortaMinima;

	private int um;

	/**
	 * Metodo Costruttore di default
	 */
	public Articolo() {
		this.dbm=DBManager.getIstanceSingleton();
	}



	/**
	 * @return
	 * @throws SQLException
	 */
	public Object[] allArticoli() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idarticolo || ' - ' || descrizione from articoli order by descrizione";
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
	 * Ritorna tutti gli articoli selezionati per categoria merceologica e ordinati per quantità venduta
	 * @return
	 * @throws SQLException
	 */
	public LinkedList<Integer> allArticoliByCategoria(int categoria) throws SQLException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		ResultSet rs = null;
		Statement pst = null;
		String query = "select a.idarticolo from articoli as a, giacenza_articoli_all_view as g where a.idarticolo=g.idarticolo and a.idreparto="+categoria+" group by g.scarico, a.idarticolo order by g.scarico DESC";
		pst = dbm.getNewStatement();
		rs = pst.executeQuery(query);
		while (rs.next()) {
			list.add(rs.getInt(1));
		}
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return list;
	}

	public String[] allCodiciBarre() throws SQLException{
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select codbarre from articoli order by codbarre";
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
	 * @param idfornitore2
	 * @return
	 * @throws SQLException
	 */
	public String[] allArticoliByFornitore(int idfornitore) throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idarticolo || ' - ' || descrizione from articoli where idfornitore="
				+ idfornitore + " order by descrizione";
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
	public void caricaDati(int idArticolo) throws SQLException {
		if(idArticolo<0)
			return;
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from articoli where idArticolo=" + idArticolo;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		//if ( rs.next() ){
		rs.next();
			this.caricoIniziale = rs.getInt("carico_Iniziale");
			this.codBarre = rs.getString("codBarre");
			this.codFornitore = rs.getString("codFornitore");
			this.colore = rs.getString("colore");
			this.dataInserimento = rs.getDate("data_Inserimento");
			this.descrizione = rs.getString("descrizione");
			this.idArticolo = rs.getInt("idArticolo");
			this.idFornitore = rs.getInt("idFornitore");
			this.idReparto = rs.getInt("idReparto");
			this.imballo = rs.getString("imballo");
			this.iva = rs.getInt("iva");
			this.note = rs.getString("note");
			this.peso = rs.getDouble("peso");
			this.prezzoAcquisto = rs.getDouble("prezzo_Acquisto");
			this.prezzoDettaglio = rs.getDouble("prezzo_Dettaglio");
			this.prezzoIngrosso = rs.getDouble("prezzo_Ingrosso");
			this.sconto = rs.getInt("sconto");
			this.scortaMinima = rs.getInt("scorta_Minima");
			this.um = rs.getInt("um");
			if (st != null)
				st.close();
		//}
	}

	/**
	 * @param text
	 * @throws SQLException
	 */
	public void caricaDatiByCodBarre(String codBarre) throws SQLException,
			IDNonValido {
		int idarticolo = getIdArticoloByCodBarre(codBarre);
		if (idarticolo < 0)
			throw new IDNonValido();
		caricaDati(idarticolo);

	}

	public boolean codBarreEsistente(String codBarre) throws SQLException,
			CodiceBarreInesistente {

		String query = "select codbarre from articoli where codbarre="
				+ codBarre;
		Statement st = dbm.getNewStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		int nRow = rs.getRow();
		if (st != null)
			st.close();
		if (nRow > 0)
			return true;
		return false;
	}

	/**
	 * Questo metodo cancella un articolo dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 *
	 * @param idArticolo
	 *            il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteArticolo(int idArticolo) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idArticolo <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM articoli WHERE idArticolo=" + idArticolo;

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

	public boolean findByCodBarre(String codBarre) throws SQLException,
			CodiceBarreInesistente {

		String query = "select idArticolo from articoli where codbarre=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codBarre);
		ResultSet rs = st.executeQuery();
		rs.last();
		if (rs.getRow() < 1)
			throw new CodiceBarreInesistente();
		rs.beforeFirst();
		rs.next();
		this.idArticolo = rs.getInt(1);
		caricaDati(idArticolo);
		if (st != null)
			st.close();
		if (idArticolo > 0)
			return true;
		return false;
	}

	/**
	 * @return the caricoIniziale
	 */
	public int getCaricoIniziale() {
		return caricoIniziale;
	}

	public String getCodBarre() {
		return codBarre;
	}

	public String getCodFornitore() {
		return codFornitore;
	}

	public int getCodiceUnitaDiMisura() {
		return um;
	}

	public String getColore() {
		return colore;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public int getIdFornitore() {
		return idFornitore;
	}

	public int getIdReparto() {
		return idReparto;
	}

	public String getImballo() {
		return imballo;
	}

	public int getIva() {
		return iva;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	public double getPeso() {
		return peso;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public double getPrezzoDettaglio() {
		return prezzoDettaglio;
	}

	public double getPrezzoIngrosso() {
		return prezzoIngrosso;
	}

	/**
	 * @return the ricaricoDettaglio
	 */
	public int getRicaricoDettaglio() {
		return ricaricoDettaglio;
	}

	/**
	 * @return the ricaricoIngrosso
	 */
	public int getRicaricoIngrosso() {
		return ricaricoIngrosso;
	}

	public int getSconto() {
		return sconto;
	}

	public int getScortaMinima() {
		return scortaMinima;
	}

	/**
	 * @return the um
	 */
	public int getUm() {
		return um;
	}

	/**
	 * Il seguente metodo inserisce l'articolo nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertArticolo() throws IDNonValido {

		idArticolo = dbm.getNewID("articoli", "idArticolo");
		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String insert = "insert into articoli values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		dataInserimento = new Date(new java.util.Date().getTime());
		pst = dbm.getNewPreparedStatement(insert);
		try {
			pst.setInt(1, this.idArticolo);
			pst.setString(2, codFornitore);
			pst.setString(3, codBarre);
			pst.setString(4, this.descrizione);
			pst.setDouble(5, this.prezzoAcquisto);
			pst.setInt(6, this.iva);
			pst.setInt(7, this.um);
			pst.setDouble(8, this.prezzoDettaglio);
			pst.setDouble(9, this.prezzoIngrosso);
			pst.setString(10, imballo);
			pst.setDouble(11, peso);
			pst.setInt(12, sconto);
			pst.setInt(13, idReparto);
			pst.setString(14, colore);
			pst.setInt(15, scortaMinima);
			pst.setString(16, note);
			pst.setDate(17, dataInserimento);
			pst.setInt(18, idFornitore);
			pst.setInt(19, caricoIniziale);
			ok = pst.executeUpdate();
			dbm.notifyDBStateChange();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				try {
					e.printStackTrace(new PrintWriter("articolo_inserimento"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Scarico.insertScaricoInizialeZero(idArticolo);
		return ok;
	}

	/**
	 * @param caricoIniziale
	 *            the caricoIniziale to set
	 */
	public void setCaricoIniziale(int caricoIniziale) {
		this.caricoIniziale = caricoIniziale;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public void setCodiceUnitaDiMisura(int um) {
		this.um = um;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public void setIdReparto(int idReparto) {
		this.idReparto = idReparto;
	}

	public void setImballo(String imballo) {
		this.imballo = imballo;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public void setPrezzoDettaglio(double prezzoDettaglio) {
		this.prezzoDettaglio = prezzoDettaglio;
	}

	public void setPrezzoIngrosso(double prezzoIngrosso) {
		this.prezzoIngrosso = prezzoIngrosso;
	}

	/**
	 * @param ricaricoDettaglio
	 *            the ricaricoDettaglio to set
	 */
	public void setRicaricoDettaglio(int ricaricoDettaglio) {
		this.ricaricoDettaglio = ricaricoDettaglio;
	}

	/**
	 * @param ricaricoIngrosso
	 *            the ricaricoIngrosso to set
	 */
	public void setRicaricoIngrosso(int ricaricoIngrosso) {
		this.ricaricoIngrosso = ricaricoIngrosso;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public void setScortaMinima(int scortaMinima) {
		this.scortaMinima = scortaMinima;
	}

	/**
	 * @param um
	 *            the um to set
	 */
	public void setUm(int um) {
		this.um = um;
	}

	/**
	 * Il seguente metodo aggiorna l'articolo nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateArticolo() throws IDNonValido {

		if (idArticolo <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE articoli SET idArticolo=?,"
				+ "codFornitore=?,codBarre=?,descrizione=?,prezzo_acquisto=?,"
				+ "iva=?,um=?,prezzo_dettaglio=?,prezzo_ingrosso=?,imballo=?,"
				+ "peso=?,sconto=?,idReparto=?,colore=?,scorta_minima=?,note=?,"
				+ "data_inserimento=?,idFornitore=?,carico_iniziale=? WHERE idArticolo=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idArticolo);
			pst.setString(2, codFornitore);
			pst.setString(3, codBarre);
			pst.setString(4, this.descrizione);
			pst.setDouble(5, this.prezzoAcquisto);
			pst.setInt(6, this.iva);
			pst.setInt(7, this.um);
			pst.setDouble(8, this.prezzoDettaglio);
			pst.setDouble(9, this.prezzoIngrosso);
			pst.setString(10, imballo);
			pst.setDouble(11, peso);
			pst.setInt(12, sconto);
			pst.setInt(13, idReparto);
			pst.setString(14, colore);
			pst.setInt(15, scortaMinima);
			pst.setString(16, note);
			pst.setDate(17, dataInserimento);
			pst.setInt(18, idFornitore);
			pst.setInt(19, caricoIniziale);
			pst.setInt(20, idArticolo);
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
	 * @param codBarre
	 * @return
	 * @throws SQLException
	 */
	private int getIdArticoloByCodBarre(String codBarre) throws SQLException {
		String query = "select idArticolo from articoli where codbarre=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codBarre);

		ResultSet rs = null;
		rs = st.executeQuery();
		rs.last();
		int row = rs.getRow();
		if (row > 0) {
			rs.beforeFirst();
			rs.next();
			return rs.getInt(1);
		}
		return -1;

	}

	public int duplicaArticolo(int idArticolo) throws SQLException, IDNonValido {
		this.caricaDati(idArticolo);
		this.insertArticolo();
		dbm.notifyDBStateChange();
		return this.idArticolo;
	}

	public double getGiacenza() throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		String query="select deposito from giacenza_articoli_all_view where idarticolo=?";
		pst=dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		rs=pst.executeQuery();
		rs.last();
		int row=rs.getRow();
		if(row<1){
			return -1;
		}
		int g=rs.getInt(1);
		if(rs!=null)
			rs.close();
		if(pst!=null)
			pst.close();
		return g;
	}
	
	public double getGiacenza2() throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		String query="select carico,scarico from giacenza_articoli_all_view where idarticolo=?";
		pst=dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		rs=pst.executeQuery();
		rs.last();
		int row=rs.getRow();
		if(row<1){
			return -1;
		}
		int carico = rs.getInt(1);
		int scarico = rs.getInt(2);
		//int g=rs.getInt(1);
		int g = carico - scarico;
		if(rs!=null)
			rs.close();
		if(pst!=null)
			pst.close();
		return g;
	}

	public static int idByCodiceBarre(String codbarre) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton();
		ResultSet rs = null;
		String query = "select idarticolo from articoli where codbarre=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setString(1, codbarre);
		rs = st.executeQuery();
		rs.next();
		int qta = 0;
		boolean trovato = false;
		if (rs.getRow() < 1) {
			trovato = false;
			qta = -1;
		} else {
			trovato = true;
			qta = rs.getInt(1);
		}
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return qta;
	}

	public static double prezzoAcquistoByID(int id) throws SQLException {
		DBManager dbm=DBManager.getIstanceSingleton();
		ResultSet rs = null;
		String query = "select prezzo_acquisto from articoli where idarticolo=?";
		PreparedStatement st = dbm.getNewPreparedStatement(query);
		st.setDouble(1, id);
		rs = st.executeQuery();
		rs.next();
		double qta = 0;
		boolean trovato = false;
		if (rs.getRow() < 1) {
			trovato = false;
			qta = -1;
		} else {
			trovato = true;
			qta = rs.getDouble(1);
		}
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
		return qta;
	}

}
