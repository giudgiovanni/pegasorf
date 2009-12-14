/**
 *
 */
package rf.pegaso.db.tabelle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.pegaso.db.tabelle.exception.IDClienteNonImpostato;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

/**
 * @author Hunter
 *
 */
public class Provincia {

	private int idprovincia;
	private String provincia;
	private String targa;
	private DBManager dbm;


	/**
	 * Costruttore di default
	 *
	 */
	public Provincia() {
		this.dbm=DBManager.getIstanceSingleton();
	}



	/**
	 * @param idCliente2
	 * @throws SQLException
	 */
	public void caricaDati(int idprovincia) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from provincia where idprovincia=" + idprovincia;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.idprovincia = rs.getInt("idprovincia");
		this.provincia = rs.getString("provincia");
		this.targa = rs.getString("targa");

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
	public int deleteProvincia(int id) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (id <= 0)
			throw new IDNonValido();
		delete = "DELETE FROM provincia WHERE idprovincia=" + id;

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


	public String getProvincia() {
		return provincia;
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
	public int insertProvincia() throws IDNonValido {

		idprovincia = dbm.getNewID("provincia", "idprovincia");
		if (idprovincia <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into provincia values(?,?,?)";
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idprovincia);
			pst.setString(2, provincia);
			pst.setString(3, targa);
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



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Provincia(int id, String provincia,String targa) {
		this.idprovincia = id;
		this.provincia = provincia;
		this.targa =targa;
		this.dbm = DBManager.getIstanceSingleton();
	}

	public int deleteProvincia() throws IDClienteNonImpostato, IDNonValido {
		if(idprovincia<1)
			return -1;
		return deleteProvincia(this.idprovincia);
	}

	public Provincia getProvincia(int id) {
		// ConnectionManager con = new ConnectionManager();
		// DBManager dbm = new DBManager(con);
		ResultSet rs = dbm.executeQuery("SELECT * FROM provincia WHERE idprovincia="
				+ id);
		Provincia c = new Provincia();
		try {
			if (!rs.next())
				return null;
			c.setProvincia(rs.getString("provincia"));
			c.setTarga(rs.getString("targa"));
			c.setIdprovincia(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
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
	public int updateProvincia() throws IDNonValido {

		if (idprovincia <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE provincia SET idprovincia=?,"
				+ "provincia=?,targa=? WHERE idprovincia=?";
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idprovincia);
			pst.setString(2, provincia);
			pst.setString(3, targa);
			pst.setInt(4, idprovincia);
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
	public Object[] allProvincie() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idprovincia || ' - ' || provincia from provincia order by provincia";
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



	public int getIdProvincia() {
		return idprovincia;
	}



	public void setIdprovincia(int idprovincia) {
		this.idprovincia = idprovincia;
	}



	public String getTarga() {
		return targa;
	}



	public void setTarga(String targa) {
		this.targa = targa;
	}



	public void updateProvincia(int idprovincia) throws IDNonValido {
		// TODO Auto-generated method stub
		this.idprovincia=idprovincia;
		if(idprovincia<0)
			return;
		updateProvincia();
	}

}
