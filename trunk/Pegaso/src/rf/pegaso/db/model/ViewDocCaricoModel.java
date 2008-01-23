/**
 *
 */
package rf.pegaso.db.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import rf.pegaso.db.DBManager;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;

/**
 * @author Hunter
 *
 */
public class ViewDocCaricoModel extends AbstractTableModel implements
		DBStateChange {

	private DBManager dbm;
	private int idArticolo = -1;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public ViewDocCaricoModel(DBManager dbm, int idArticolo)
			throws SQLException {
		this.dbm = dbm;
		this.idArticolo = idArticolo;
		recuperaDati();

	}

	public int getColumnCount() {
		int nColonne = 0;
		try {
			nColonne = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nColonne;
	}

	@Override
	public String getColumnName(int col) {
		String nome = "";
		try {
			nome = rsmd.getColumnLabel(col + 1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nome;
	}

	public int getRowCount() {
		if (rs == null)
			return -1;
		int nRighe = 0;
		try {
			rs.last();
			nRighe = rs.getRow();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return nRighe;
	}

	public String getTableName() {
		return "articoli";
	}

	public Object getValueAt(int r, int c) {
		if (rs == null)
			return -1;

		Object o = null;
		try {
			rs.beforeFirst();
			rs.absolute(r + 1);
			o = rs.getObject(c + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public void stateChange() {
		try {
			recuperaDati();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.fireTableDataChanged();

	}

	public void stateChange(DBEvent arg0) {
		stateChange();

	}

	/**
	 * @throws SQLException
	 *
	 */
	private void recuperaDati() throws SQLException {
		// this.query = "select c.idcarico as Num_Carico,c.data_documento as
		// Data_Documento,doc.descrizione, c.num_documento as Num_Documento from
		// articoli as a, carichi as c,dettaglio_carichi as d,documento as doc
		// where a.idarticolo=d.idarticolo and c.idcarico=d.idcarico and
		// doc.iddocumento=c.idcarico and a.idarticolo=?";
		this.query = "select d.descrizione, num_documento,data_documento,a.idcarico as num_carico, c.data_carico from articoli_caricati_view as a, carichi AS C,tipo_documento as d where a.idcarico=c.idcarico and c.iddocumento=d.iddocumento and a.idarticolo=? order by data_documento desc";
		pst = dbm.getNewPreparedStatement(query);
		pst.setInt(1, idArticolo);
		rs = pst.executeQuery();
		rsmd = rs.getMetaData();

	}

	public String getNomeTabella() {
		// TODO Auto-generated method stub
		return null;
	}


}
