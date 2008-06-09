/**
 *
 */
package rf.pegaso.db.model;

import javax.swing.table.AbstractTableModel;

import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author Hunter
 *
 */
public class DocumentoModel extends AbstractTableModel implements DBStateChange {

	private DBManager dbm;

	public DocumentoModel(DBManager dbm) {
		this.dbm = dbm;
	}

	public int getColumnCount() {
		int nColonne = 0;
		nColonne = dbm.getNumeroColonne(getTableName());
		return nColonne;
	}

	@Override
	public String getColumnName(int col) {
		return dbm.getColumnName(col, getTableName());
	}

	public int getRowCount() {
		int nRighe = 0;
		nRighe = dbm.getNumeroRighe(getTableName());
		return nRighe;
	}

	public String getTableName() {
		return "tipo_documento";
	}

	public Object getValueAt(int r, int c) {
		Object o = dbm.getValueAt(r, c, getTableName());
		return o;
	}

	public void stateChange() {
		this.fireTableDataChanged();

	}

	public void stateChange(DBEvent arg0) {
		stateChange();

	}

	public String getNomeTabella() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rowStateChange(RowEvent re) {
		// TODO Auto-generated method stub

	}


}
