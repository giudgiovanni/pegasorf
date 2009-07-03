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
import rf.utility.Constant;
import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author Hunter
 *
 */
public class OrdiniTabacchiViewModel extends AbstractTableModel implements
		DBStateChange {

	private DBManager dbm;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public OrdiniTabacchiViewModel(DBManager dbm) throws SQLException {
		this.dbm = dbm;
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
		return "carichi";
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
			}else if(o instanceof Double){
				Double d = (Double) o;
				DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
				numberFormatter.setMaximumFractionDigits(2);
				numberFormatter.setMinimumFractionDigits(2);
				return numberFormatter.format(d);
			}
			if(c==7){
				Integer i=(Integer)o;
				if(i==null)
					return "PAGATO";
				else if(i.intValue()==0)
					return "PAGATO";
				else return "SOSPESO";
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
		this.query = "select c.idcarico as id,c.data_documento,c.num_documento as numero, d.descrizione,f.nome as fornitore,c.note,c.totale_documento as totale,c.sospeso from carichi as c,tipo_documento as d,  fornitori as f  where c.idcarico=c.idcarico and c.idfornitore=f.idfornitore and c.iddocumento=d.iddocumento and d.iddocumento="+Constant.ORDINE+"order by c.data_documento desc";
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
