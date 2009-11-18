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

import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author Hunter
 *
 */
public class ScaricoModel extends AbstractTableModel implements DBStateChange {

	private DBManager dbm;
	private int idordine = 0;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	public ScaricoModel(int idordine) throws SQLException {
		this.dbm = DBManager.getIstanceSingleton();
		this.idordine = idordine;
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

	@Override
	public void setValueAt(Object o, int r, int c) {
		if(c!=5)
			return;
		// carichiamo l'articolo in base al codice a barre
		int idArticolo = ((Long)(getValueAt(r, 0))).intValue();
		int idOrdine=this.idordine;
		String query = "update dettaglio_ordini set qta=? where idarticolo=? and idordine=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);
		try {

			pst.setObject(1, o);
			pst.setInt(2, idArticolo);
			pst.setInt(3, idOrdine);
			pst.executeUpdate();
			pst.close();

			recuperaDati();
			dbm.notifyDBStateChange();
			this.fireTableCellUpdated(r, c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object getValueAt(int r, int c) {
		if (rs == null)
			return -1;

		Object o = null;
		try {
			if(getRowCount()>0){
				rs.beforeFirst();
				rs.absolute(r + 1);
				o = rs.getObject(c + 1);
			}

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

	@Override
	public boolean isCellEditable(int r, int c) {

		if (c != 5)
			return false;
		return true;
	}

	@Override
	public Class getColumnClass(int c) {
        if(getRowCount()>0){
        	if(c==5)
            	return Double.class;
    		return getValueAt(0, c).getClass();
        }
        return String.class;

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
		//this.query = "select codbarre as codice_articolo,descrizione,iva,um,qta,prezzo_ingrosso as prezzo_listino from articoli_scaricati_view where idordine="	+ idordine + " order by codbarre";

		this.query = "select a.idarticolo,a.codbarre as codice,a.descrizione,a.iva,a.um,a.qta,g.deposito as disponibili, ar.prezzo_acquisto from articoli as ar, articoli_scaricati_view as a, giacenza_articoli_all_view as g where idordine="
				+ idordine + " and ar.idarticolo=a.idarticolo and g.codice=a.codbarre order by a.codbarre";
		pst = dbm.getNewPreparedStatement(query);
		rs = pst.executeQuery();
		rsmd = rs.getMetaData();

	}

	public void reloadModel(int idCarico) throws SQLException {
		this.idordine=idCarico;
		recuperaDati();

	}

	public String getNomeTabella() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rowStateChange(RowEvent re) {
		// TODO Auto-generated method stub

	}


}
