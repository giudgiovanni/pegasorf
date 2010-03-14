package rf.pegaso.db.model;

import it.infolabs.hibernate.Scarico;
import it.infolabs.hibernate.ScaricoHome;
import it.infolabs.hibernate.exception.FindAllEntityException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

public class SospesiModel extends AbstractTableModel implements DBStateChange {

	private static final long serialVersionUID = 1L;
	private List<Object> resultList;
	private List<String> colonne;

	public SospesiModel() {
		colonne = creaColonne();
		resultList = new ArrayList<Object>();
		try {
			recuperaDati();
		} catch (FindAllEntityException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> creaColonne(){
		ArrayList<String> col = new ArrayList<String>();
		col.add("Nome");
		col.add("Cognome");
		col.add("Totale");		
		col.add("Data");
		col.add("Saldo");
		col.add("Imponibile");
		col.add("Imposta");
		col.add("Agio");
		return col;
	}

	public int getColumnCount() {
		return colonne.size();
	}

	@Override
	public String getColumnName(int col) {
		return colonne.get(col);
	}

	public int getRowCount() {
		return resultList.size();
	}

	public String getTableName() {
		return "articoli";
	}

	public Object getValueAt(int r, int c) {
		Object [] tmp = (Object[])resultList.get(r);
		if ( c == 0 ){
			return tmp[1];
		}
		else if ( c == 1 ){
			return tmp[2];
		}
		else if ( c == 2 ){
			Double d = (Double)tmp[4]+(Double)tmp[5];
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}		
		else if ( c == 3 ){
			return tmp[3];
		}
		else if ( c == 4 ){
			return "";
		}
		else if ( c == 5 ){
			Double d = (Double)tmp[4];
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		else if ( c == 6 ){
			Double d = (Double)tmp[5];
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		else if ( c == 7 ){
			Double d = (Double)tmp[6];
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		else {
			return "";
		}
	}

	public void stateChange() {
		try {
			recuperaDati();
		} catch (FindAllEntityException e) {
			e.printStackTrace();
		}
		this.fireTableDataChanged();
	}

	public void stateChange(DBEvent arg0) {
		stateChange();

	}

	/**
	 * @throws FindAllEntityException 
	 *
	 */
	private void recuperaDati() throws FindAllEntityException {
		ScaricoHome.getInstance().begin();
		resultList = ScaricoHome.getInstance().allSospesi();
	}

	public String getNomeTabella() {
		return "Scarico";
	}

	public void rowStateChange(RowEvent re) {
	}


}
