/**
 *
 */
package rf.pegaso.db.model;

import java.sql.Date;
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
	private String colonna = "";
	private int valore = 0;
	private Date da;
	private Date a;

	/*
	 * se tabella == 1 la ricerca va fatta in fattura
	 * se tabella == 2 la ricerca va fatta per data nella fattura
	 * se tabella == 3 la ricerca va fatta in ddt
	 * se tabella == 4 la ricerca va fatta per data in ddt
	 */

	public FatturaViewModel(DBManager dbm, String colonna, int valore, int tab) throws SQLException {
		this.dbm = dbm;
		this.tabella = tab;
		this.colonna = colonna;
		this.valore = valore;
		recuperaDati();
	}

	public FatturaViewModel(DBManager dbm, Date da, Date a, int tab) throws SQLException{
		this.dbm = dbm;
		this.da = da;
		this.a = a;
		this.tabella = tab;
		recuperaDati();
	}

	public FatturaViewModel(DBManager dbm2, int i) {
		// TODO Auto-generated constructor stub
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
			this.query = "select f.idfattura,c.cognome,c.nome,f.num_fattura as numero,f.data_vendita as data from fattura as f,clienti as c, ordini as o where f.idfattura=o.idordini and c.idcliente=f.idcliente and f."+colonna+"="+valore+" order by f.data_vendita";
		else if ( tabella == 2 )
			this.query = "select f.idfattura,c.cognome,c.nome,f.num_fattura as numero,f.data_vendita as data from fattura as f,clienti as c, ordini as o where f.idfattura=o.idordini and c.idcliente=f.idcliente and f.data_vendita>='"+da+"' and f.data_vendita<='"+a+"' order by f.data_vendita";
		else if ( tabella == 3 )
			this.query = "select d.idddt,c.cognome,c.nome,d.num_ddt as numero,d.data_ddt as data from ddt as d,clienti as c, ordini as o where d.idddt=o.idordini and c.idcliente=d.idcliente and d."+colonna+"="+valore+" order by d.data_ddt";
		else if ( tabella == 4 )
			this.query = "select d.idddt,c.cognome,c.nome,d.num_ddt as numero,d.data_ddt as data from ddt as d,clienti as c, ordini as o where d.idddt=o.idordini and c.idcliente=d.idcliente and d.data_ddt>='"+da+"' and d.data_ddt<='"+a+"' order by d.data_ddt";
		else if ( tabella == 5 )
			this.query = "select b.idordine as numero,b.data_ordine as data from ordini as b where b.data_ordine>='"+da+"' and b.data_ordine<='"+a+"' order by b.data_ordine";
		pst = dbm.getNewPreparedStatement(query);
		rs = pst.executeQuery();
		rsmd = rs.getMetaData();

	}

}
