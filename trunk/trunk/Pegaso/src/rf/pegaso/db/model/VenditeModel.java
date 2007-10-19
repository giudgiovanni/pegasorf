/**
 * 
 */
package rf.pegaso.db.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Vendita;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;

/**
 * @author Hunter
 * 
 */
public class VenditeModel extends AbstractTableModel implements DBStateChange {

	private DBManager dbm;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private Vector<Vendita> vendita = null;
	private Vector<String> colonne = null;

	public VenditeModel(Vector<Vendita> vendita, Vector<String> colonne) throws SQLException {
		this.vendita = vendita;
		this.colonne = colonne;
		//this.dbm = dbm;
		//recuperaDati();

	}

	public int getColumnCount() {
		return colonne.size();//codiceArticolo, descrizione, qta, prezzo, importo, sconto, iva
	}

	@Override
	public String getColumnName(int col) {
		/*String nome = "";
		try {
			nome = rsmd.getColumnLabel(col + 1);
		} catch (SQLException e) {

			e.printStackTrace();
		}*/
		return colonne.get(col);
	}

	public int getRowCount() {
		/*if (rs == null)
			return -1;
		int nRighe = 0;
		try {
			rs.last();
			nRighe = rs.getRow();
		} catch (SQLException e) {

			e.printStackTrace();
		}*/

		return vendita.size();
	}

	public String getTableName() {
		return "vendita";
	}

	/*public Object getValueAt(int r, int c) {
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
	}*/
	
	public Object getValueAt(int r, int c) {
		if ( vendita.size() == 0 )
			return -1;
		Vendita v = vendita.get(r);
		Vector v1 = v.trasformaInArray();
		Object o = null;
		o = (Object)v1.get(c);
		
		if (o instanceof Double) {
			Double d = (Double) o;
			DecimalFormat numberFormatter = new DecimalFormat();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		return o;
	}
	
	public boolean isCellEditable(int r, int c) {
		if ( c == 2 || c == 3 || c == 5 )
			return true;
		return false;
	}

	public void stateChange() {
		this.fireTableDataChanged();
	}

	public void stateChange(DBEvent arg0) {
		stateChange();

	}

	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if ( columnIndex == 3 || columnIndex == 4 )
			return Double.class;
		else if ( columnIndex == 0 || columnIndex == 2 || columnIndex == 5 || columnIndex == 6 )
			return Integer.class;
		else return String.class;
	}

	@Override
	public void setValueAt(Object o, int r, int c) {
		Vendita v = vendita.get(r);
		switch(c) {
			case 2 :
				v.setQta((Integer)o);//.parseInt((String)o));
				break;
			case 3 :{
				v.setPrezzoVendita(Double.parseDouble((String)o));
				//aggiornare importo
				break;
			}
			case 5 :
				v.setSconto((Integer)o);//(Integer.parseInt((String)o));
				break;
		}
		this.fireTableCellUpdated(r, c);
	}

}
