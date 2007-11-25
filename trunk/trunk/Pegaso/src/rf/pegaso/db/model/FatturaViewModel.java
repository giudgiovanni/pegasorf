/**
 * 
 */
package rf.pegaso.db.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

import rf.pegaso.db.DBManager;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;

/**
 * @author Hunter
 * 
 */
public class FatturaViewModel extends AbstractTableModel implements DBStateChange {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DBManager dbm;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private int tabella = 0;

	public FatturaViewModel(DBManager dbm, int tab) throws SQLException {
		this.dbm = dbm;
		this.tabella = tab;
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
		return "fatture";
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

		if (o instanceof Double) {
			Double d = (Double) o;
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
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
	
	public Class<?> getColumnClass(int columnIndex) {
		if ( columnIndex == 0 || columnIndex == 3 )
			return Integer.class;
		else if ( columnIndex == 1 || columnIndex == 2 )
			return String.class;
		else 
			return java.sql.Date.class;
	}

	/**
	 * @throws SQLException
	 * 
	 */
	private void recuperaDati() throws SQLException {
		if ( tabella == 1)
			this.query = "select f.idfattura,c.cognome,c.nome,f.num_fattura as numero,f.data_vendita as data from fattura as f,clienti as c where c.idcliente=f.idcliente order by f.data_vendita";
		else if ( tabella == 2 )
			this.query = "select d.idddt,c.cognome,c.nome,d.num_ddt as numero,d.data_ddt as data from ddt as d,clienti as c where c.idcliente=d.idcliente order by d.data_ddt";
		pst = dbm.getNewPreparedStatement(query);
		rs = pst.executeQuery();
		rsmd = rs.getMetaData();

	}

}
