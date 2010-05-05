/**
 *
 */
package rf.pegaso.db.model;

import org.apache.log4j.Logger;

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
public class ArticoliScaricatiViewModel extends AbstractTableModel implements
		DBStateChange {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ArticoliScaricatiViewModel.class);

	private DBManager dbm;
	private PreparedStatement pst = null;
	private String query = "";
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private Date dal;
	private Date al;

	public ArticoliScaricatiViewModel() throws SQLException {
		this.dbm = DBManager.getIstanceSingleton();
		this.dal=dal;
		this.al=al;
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
			}else if (o instanceof Double) {
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
	
	public void setDate(Date dal, Date al) throws SQLException{
		this.dal=dal;
		this.al=al;
		recuperaDati();
	}

	/**
	 * @throws SQLException
	 *
	 */
	private void recuperaDati() throws SQLException {
//		this.query = "select a.codbarre as codice,a.descrizione,a.qta as qta, a.prezzo_dettaglio as prezzo_pubblico,(articoli.prezzo_acquisto* a.qta) as totale, o.data_documento as data, a.idordine as n_vendita from articoli_scaricati_view as a, articoli,giacenza_articoli_all_view as g, ordini as o where articoli.idarticolo=a.idarticolo and o.idordine=a.idordine and g.codice=a.codbarre and a.qta>0  order by a.codbarre";
		this.query = "select a.codbarre as codice,a.descrizione,a.qta as qta, a.prezzo_dettaglio as prezzo_pubblico, (a.prezzo_dettaglio* a.qta) as totale, o.data_documento as data, a.idordine as n_vendita from articoli_scaricati_view as a, articolo, scarico as o where articoli.idarticolo=a.idarticolo and o.idordine=a.idordine and a.qta>0 order by a.codbarre";
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

	@Override
	public void fireTableDataChanged() {
		if (logger.isDebugEnabled()) {
			logger.debug("fireTableDataChanged() - start");
		}

		try {
			recuperaDati();
		} catch (SQLException e) {
			logger.error("fireTableDataChanged()", e);
		}
		super.fireTableDataChanged();

		if (logger.isDebugEnabled()) {
			logger.debug("fireTableDataChanged() - end");
		}
	}


}
