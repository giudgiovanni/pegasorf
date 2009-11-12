/**
 *
 */
package rf.pegaso.db.model;

import it.infolabs.hibernate.Articoli;
import it.infolabs.hibernate.ArticoliHome;
import it.infolabs.hibernate.exception.FindAllEntityException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import rf.utility.Constant;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;
import rf.utility.db.RowEvent;

/**
 * @author Hunter
 *
 */
public class ArticoloModel extends AbstractTableModel implements DBStateChange {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Articoli> resultList;
	private List<String> colonne;

	public ArticoloModel() {
		colonne = creaColonne();
		resultList = new ArrayList<Articoli>();
		try {
			recuperaDati();
		} catch (FindAllEntityException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> creaColonne(){
		ArrayList<String> col = new ArrayList<String>();
		col.add("IdArticolo");
		col.add("Codice");
		col.add("Descrizione");
		col.add("Prezzo Acquisto");
		col.add("Prezzo Listino");
		col.add("Fornitore");
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
		Articoli tmp = resultList.get(r);
		if ( c == 0 ){
			return tmp.getIdarticolo();
		}
		else if ( c == 1 ){
			return tmp.getCodbarre();
		}
		else if ( c == 2 ){
			return tmp.getDescrizione();
		}
		else if ( c == 3 ){
			Double d = tmp.getPrezzoAcquisto();
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		else if ( c == 4 ){
			Double d = tmp.getPrezzoDettaglio();
			DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(d);
		}
		else{
			if ( tmp.getFornitori() != null ){
				return tmp.getFornitori().getNome();
			}
			else{
				return "";
			}
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
		ArticoliHome.getInstance().begin();
		Criteria crit = ArticoliHome.getInstance().getSessionFactory().getCurrentSession().createCriteria("it.infolabs.hibernate.Articoli");
		resultList = crit.list();
	}

	public String getNomeTabella() {
		return null;
	}

	public void rowStateChange(RowEvent re) {
	}


}
