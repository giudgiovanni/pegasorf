package rf.pegaso.gui.vendita.panel;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rf.myswing.GregorianCalendarFormat;
import rf.pegaso.db.model.VenditeModel;
import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.utility.MathUtility;
import rf.utility.db.DBEvent;
import rf.utility.db.DBManager;
import rf.utility.db.RowEvent;

public class JPanelRiepilogoVendita extends JPanel {

	class PrezzoCellRenderer extends DefaultTableCellRenderer{
		private DecimalFormat numberFormatter;
		private Double d;

		public void setValue(Object value) {
			if (numberFormatter == null) {

				numberFormatter = (DecimalFormat) DecimalFormat
						.getCurrencyInstance(Locale.ITALY);
				// numberFormatter = new DecimalFormat("#,##0.00");
				// numberFormatter.setMaximumFractionDigits(2);
				// numberFormatter.setMinimumFractionDigits(2);
			}
			setHorizontalAlignment(JLabel.RIGHT);
			d = (Double) value;
			setText((value == null) ? "" : numberFormatter
					.format(d));
		}
	}

	class QtaCellRenderer extends DefaultTableCellRenderer{
		private DecimalFormat numberFormatter;
		private Double d;

		public void setValue(Object value) {
			if (numberFormatter == null) {

				//numberFormatter = (DecimalFormat) DecimalFormat.getInstance();
				numberFormatter = new DecimalFormat("#,##0.00");
				numberFormatter.setMaximumFractionDigits(2);
				numberFormatter.setMinimumFractionDigits(2);
			}
			setHorizontalAlignment(JLabel.CENTER);
			d = (Double) value;
			setText((value == null) ? "" : numberFormatter
					.format(d));
		}
	}

	class IntegerCellRenderer extends DefaultTableCellRenderer{
		private NumberFormat numberFormatter;
		private Integer d;

		public void setValue(Object value) {
			if (numberFormatter == null) {
				numberFormatter =  NumberFormat.getIntegerInstance();
			}
			setHorizontalAlignment(JLabel.CENTER);
			d = (Integer) value;
			setText((value == null) ? "" : numberFormatter
					.format(d));
		}
	}

	class VenditaTableModel extends AbstractTableModel {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private String nomiColonne[] = { "DESCRIZIONE", "PREZZO", "QTA'",
				"IVA", "TOTALE" };

		public VenditaTableModel() {

		}

		public int getColumnCount() {
			// le colonne sono 5 e sono
			// DESCRIZIONE - PREZZO UNIT - QTA - IVA - TOTALE
			return 5;

		}

		@Override
		public String getColumnName(int col) {
			return nomiColonne[col];
		}

		public int getRowCount() {
			return carrello.size();
		}

		public Object getValueAt(int r, int c) {
			DettaglioOrdine riga = carrello.get(r);
			Object o = null;

			switch (c) {
			case 0:{
				o = riga.getDescrizione().toUpperCase();
				break;
			}
			case 1:{
				o = new Double(riga.getPrezzoVendita());
				break;
			}
			case 2:{
				o = new Double(riga.getQta());
				break;
			}
			case 3:{
				o = new Integer(riga.getIva());
				break;
			}
			case 4:{
				double tot=riga.getPrezzoVendita() * riga.getQta();
				tot=tot+MathUtility.percentualeDaAggiungere(tot, riga.getIva());
				o = new Double(tot);
				break;
			}

			}
			return o;
		}
	}

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable tblVendite = null;
	private ArrayList<DettaglioOrdine> carrello = null; // @jve:decl-index=0:
	private VenditaTableModel modello;
	private int idSelectedItem=-1;

	/**
	 * This is the default constructor
	 */
	public JPanelRiepilogoVendita() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(getJScrollPane(), BorderLayout.CENTER);

		this.carrello = new ArrayList<DettaglioOrdine>();
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblVendite());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblVendite
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblVendite() {
		if (tblVendite == null) {
			PrezzoCellRenderer prezzoRenderer = new PrezzoCellRenderer();
			QtaCellRenderer qtaRenderer=new QtaCellRenderer();
			IntegerCellRenderer ivaRenderer=new IntegerCellRenderer();

			modello =new VenditaTableModel();
			tblVendite = new JTable(modello);

			//formattiamo le colonne prezzo
			TableColumn col = tblVendite.getColumnModel().getColumn(1);
			col.setCellRenderer(prezzoRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(100);
			col.setPreferredWidth(100);

			//formattiamo la colonna totale
			col = tblVendite.getColumnModel().getColumn(4);
			col.setCellRenderer(prezzoRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(100);
			col.setPreferredWidth(100);

			//formattiamo la colonna quantità
			col = tblVendite.getColumnModel().getColumn(2);
			col.setCellRenderer(qtaRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(100);
			col.setPreferredWidth(100);

			//formattiamo la colonna iva
			col = tblVendite.getColumnModel().getColumn(3);
			col.setCellRenderer(ivaRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(100);
			col.setPreferredWidth(100);


			tblVendite.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					 if(e.getSource()==tblVendite){
						 int row=tblVendite.getSelectedRow();
						 if(row==-1)
							 idSelectedItem=-1;
						 else{
							 DettaglioOrdine o=carrello.get(row);
							 idSelectedItem=o.getIdArticolo();
						 }
					 }
				}
			});
		}
		return tblVendite;
	}

	/**
	 * Aggiunge un dettaglio ordine al carrello
	 * e se già presente aggiorna la quantità
	 * ed eventuali altri parametri cambiati
	 *
	 * @param e
	 * @return
	 */
	public boolean addDettaglioOrdine(DettaglioOrdine ord) {
		int contiene=carrello.indexOf(ord);
		boolean ok=true;
		if(contiene==-1)
			ok=carrello.add(ord);
		else{
			DettaglioOrdine tmp= carrello.get(contiene);
			tmp.setDescrizione(ord.getDescrizione());
			tmp.setPrezzoVendita(ord.getPrezzoVendita());
			//aggiungiamo alla quantità già presente la nuova
			//quantità da aggiungere
			tmp.setQta(tmp.getQta()+ord.getQta());
			tmp.setIva(ord.getIva());
		}
		//notifichiamo che è stata aggiunta una riga;
		modello.fireTableRowsInserted(carrello.size(), carrello.size());
		return ok;
	}

	/**
	 * sostituisce il carrello corrente con
	 * il carrello che gli viene passato
	 * come parametro
	 * @param c
	 * @return
	 */
	public boolean addAllDettaglioOrdine(Collection<DettaglioOrdine> c) {
		carrello.clear();
		boolean ok=carrello.addAll(c);
		modello.fireTableDataChanged();
		return ok;
	}

	/**
	 * Cancella tutto il contenuto di un carrello
	 */
	public void azzeraCarrello() {
		carrello.clear();
		modello.fireTableDataChanged();
	}

	/**
	 *
	 * @param o
	 * @return
	 */
	public boolean containsDettaglioOrdine(DettaglioOrdine ord) {
		return carrello.contains(ord);
	}

	/**
	 * Interroga se il carrello è vuoto oppure no
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return carrello.isEmpty();
	}

	/**
	 * Ritorna un iteratore al carrello per scorrere tutti gli oggetti
	 * DettaglioOrdine
	 *
	 * @return
	 */
	public Iterator<DettaglioOrdine> iterator() {
		return carrello.iterator();
	}

	/**
	 * Rimuove un oggetto DettaglioOrdine dal carrello
	 *
	 * @param o
	 * @return
	 */
	public boolean removeDettaglioOrdine(DettaglioOrdine ord) {
		boolean ok=carrello.remove(ord);
		modello.fireTableRowsDeleted(carrello.size()+1, carrello.size()+1);
		return ok;
	}

	/**
	 * rimuove tutti gli oggetti passati nella collection c
	 *
	 * @param c
	 * @return
	 */
	public boolean removeAllDettaglioOrdine(Collection<DettaglioOrdine> c) {
		boolean ok=carrello.removeAll(c);
		modello.fireTableDataChanged();
		return ok;
	}

	/**
	 * ritorna la grandezza del carrello e quindi il numero degli oggetti in
	 * elenco che corrisponde in modo semplice alle righe della tabella.
	 *
	 * @return
	 */
	public int getNumeroItemNelCarrello() {
		return carrello.size();
	}

	/**
	 * Restituisce un array di DettaglioOrdine
	 *
	 * @return
	 */
	public DettaglioOrdine[] toArray() {
		return (DettaglioOrdine[]) carrello.toArray();
	}

	/**
	 * Ritorna id dell'oggetto selezionato nella tabella
	 * @return ritorna id se è selezionato un oggetto nella tabella
	 * 			oppure ritorna -1 se non c'è nessuna selezione
	 */
	public int getIdSelectedItem(){
		return idSelectedItem;
	}

	public void clearSelectionItem(){
		tblVendite.clearSelection();
		idSelectedItem=-1;
	}

}
