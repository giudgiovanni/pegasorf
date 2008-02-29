/**
 *
 */
package rf.pegaso.db.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import rf.myswing.GregorianCalendarFormat;
import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author Hunter
 *
 */
public class PrimanotaModelEntrate extends AbstractTableModel implements
		DBStateChange {

	private DBManager dbm;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public PrimanotaModelEntrate() throws SQLException {
		this.dbm = DBManager.getIstanceSingleton();
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
		return "ordini";
	}

	public Object getValueAt(int r, int c) {
		if (rs == null)
			return -1;

		Object o = null;
		try {
			rs.beforeFirst();
			rs.absolute(r + 1);
			o = rs.getObject(c + 1);
			if (o instanceof Date) {
				GregorianCalendarFormat gcf = new GregorianCalendarFormat();
				gcf.setTime((Date) o);
				return gcf;
			}else if(o instanceof Double) {
					Double d = (Double) o;
					DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
					numberFormatter.setMaximumFractionDigits(2);
					numberFormatter.setMinimumFractionDigits(2);
					return numberFormatter.format(d);
				}

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
		//this.query = "select o.idordine as id,o.data_documento, d.descrizione,o.num_documento,c.nome,c.cognome, t.tot as tot_ivato,o.note from ordini as o,tipo_documento as d,  clienti as c, (select idordine,sum((prezzo_acquisto*qta)+((prezzo_acquisto/100*iva)*qta)) as tot from articoli_scaricati_view group by idordine) as t where o.idordine>0 and o.idcliente=c.idcliente and o.tipo_documento=d.iddocumento and t.idordine=o.idordine order by o.data_documento desc";
		this.query = "select o.idordine as id,o.data_documento, d.descrizione,o.num_documento,c.nome,c.cognome, o.totale_documento,o.note from ordini as o,tipo_documento as d,  clienti as c where o.idordine>0 and o.idcliente=c.idcliente and o.tipo_documento=d.iddocumento and (tipo_documento=1 or tipo_documento=3 or tipo_documento=4) order by o.data_documento desc";
		pst = dbm.getNewPreparedStatement(query);
		rs = pst.executeQuery();
		rsmd = rs.getMetaData();

	}

	public String getNomeTabella() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rowStateChange(RowEvent re) {
		// TODO Auto-generated method stub

	}

}
