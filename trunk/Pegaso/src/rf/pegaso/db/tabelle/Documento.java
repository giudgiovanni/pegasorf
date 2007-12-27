/**
 * 
 */
package rf.pegaso.db.tabelle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class Documento {
	private DBManager dbm;
	private String descrizione;
	private int iddocumento;
	private String tipo;

	public Documento() {
		this.dbm=DBManager.getIstanceSingleton(); 
	}


	public Object[] allDocumenti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select iddocumento || ' - ' || tipo from tipo_documento order by tipo";
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

	public void caricaDati(int iddocumento) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from tipo_documento where iddocumento="
				+ iddocumento;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.descrizione = rs.getString("descrizione");
		this.iddocumento = rs.getInt("iddocumento");
		this.tipo = rs.getString("tipo");
		if (st != null)
			st.close();
	}

	public int deleteDocumento(int iddocumento) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (iddocumento <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM tipo_documento WHERE iddocumento=" + iddocumento;

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

	public String getDescrizione() {
		return descrizione;
	}

	public int getIddocumento() {
		return iddocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public int insertDocumento() throws IDNonValido {

		iddocumento = dbm.getNewID("tipo_documento", "iddocumento");
		if (iddocumento <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String insert = "insert into tipo_documento values (?,?,?)";

		pst = dbm.getNewPreparedStatement(insert);
		try {
			pst.setInt(1, this.iddocumento);
			pst.setString(2, tipo);
			pst.setString(3, descrizione);
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
		return ok;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIddocumento(int iddocumento) {
		this.iddocumento = iddocumento;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int updateDocumento() throws IDNonValido {

		if (iddocumento <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE tipo_documento SET iddocumento=?,"
				+ "tipo=?,descrizione=? WHERE iddocumento=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.iddocumento);
			pst.setString(2, tipo);
			pst.setString(3, descrizione);
			pst.setInt(4, this.iddocumento);
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
