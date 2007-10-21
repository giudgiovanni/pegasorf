/**
 * 
 */
package rf.pegaso.db.model;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import rf.pegaso.db.tabelle.Vendita;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;

/**
 * @author Hunter
 * 
 */
public class VenditeModel extends AbstractTableModel implements DBStateChange {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Vendita> vendita = null;
	private Vector<String> colonne = null;

	public VenditeModel(Vector<Vendita> vendita, Vector<String> colonne) throws SQLException {
		this.vendita = vendita;
		this.colonne = colonne;
	}

	public int getColumnCount() {
		return colonne.size();//idArticolo, codiceBarre, descrizione, qta, prezzo, importo, sconto, iva
	}

	@Override
	public String getColumnName(int col) {
		return colonne.get(col);
	}

	public int getRowCount() {
		return vendita.size();
	}

	public String getTableName() {
		return "vendita";
	}
	
	public Object getValueAt(int r, int c) {
		if ( vendita.size() == 0 )
			return -1;
		Vendita v = vendita.get(r);
		Vector<Object> v1 = v.trasformaInArray();
		Object o = null;
		o = (Object)v1.get(c);
		
//		if (o instanceof Double) {
//			Double d = (Double) o;
//			DecimalFormat numberFormatter = new DecimalFormat();
//			numberFormatter.setMaximumFractionDigits(2);
//			numberFormatter.setMinimumFractionDigits(2);
//			return numberFormatter.format(d);
//		}
		return o;
	}
	
	public boolean isCellEditable(int r, int c) {
		if ( c == 3 || c == 4 || c == 6 )
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
		if ( columnIndex == 4 || columnIndex == 5 )
			return Double.class;
		else if ( columnIndex == 0 || columnIndex == 6 || columnIndex == 7 )
			return Integer.class;
		else if ( columnIndex == 3 )
			return Long.class;
		else return String.class;
	}

	@Override
	public void setValueAt(Object o, int r, int c) {
		Vendita v = vendita.get(r);
		switch(c) {
			case 3 :{
				if( o instanceof Long) 
					v.setQta((Long)o);
				else
					v.setQta(new Long((Integer)o));
				break;
			}
			case 4 :
				v.setPrezzoVendita((Double)o);
				break;
			case 6 :
				v.setSconto((Integer)o);
				break;
		}
		this.fireTableCellUpdated(r, c);
	}

}
