package rf.pegaso.gui.vendita.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.db.tabelle.Scarico;
import rf.utility.Constant;
import rf.utility.MathUtility;
import rf.utility.db.DBManager;

public class JPanelRiepilogoVendita extends JPanel {
	
	class MyArrayList extends ArrayList<DettaglioOrdine>{
		
		@Override
		public boolean contains(Object o) {
			for ( DettaglioOrdine ord : carrello ){
				if ( ord.getIdArticolo() == ((DettaglioOrdine)o).getIdArticolo() )
					return true;
			}
			return false;
		}
		
		@Override
		public int indexOf(Object o) {
			if ( o == null ) {
			    for (int i = 0; i < carrello.size(); i++)
				if ( carrello.get(i) == null )
				    return i;
			} else {
			    for (int i = 0; i < carrello.size(); i++)
				if ( ((DettaglioOrdine)o).getIdArticolo() == carrello.get(i).getIdArticolo() )
				    return i;
			}
			return -1;
		}
	}

	class PrezzoCellRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;
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
		private static final long serialVersionUID = 1L;
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
		private static final long serialVersionUID = 1L;
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
				// TODO Auto-generated method stub	o = new Double(riga.getPrezzoVendita());
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
	private MyArrayList carrello = null; // @jve:decl-index=0:
	private VenditaTableModel modello;
	private int idSelectedItem=-1;
	private double totaleCarrello = 0.0;
	private Scarico scarico = null;

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
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(getJScrollPane(), BorderLayout.CENTER);
		scarico = new Scarico();
		this.carrello = new MyArrayList();
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
			col.setMaxWidth(90);
			col.setPreferredWidth(90);

			//formattiamo la colonna totale
			col = tblVendite.getColumnModel().getColumn(4);
			col.setCellRenderer(prezzoRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(100);
			col.setPreferredWidth(100);

			//formattiamo la colonna quantita'
			col = tblVendite.getColumnModel().getColumn(2);
			col.setCellRenderer(qtaRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(50);
			col.setPreferredWidth(50);

			//formattiamo la colonna iva
			col = tblVendite.getColumnModel().getColumn(3);
			col.setCellRenderer(ivaRenderer);
			col.setMinWidth(0);
			col.setMaxWidth(60);
			col.setPreferredWidth(60);


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
	 * e se gi� presente aggiorna la quantit�
	 * ed eventuali altri parametri cambiati
	 *
	 * @param e
	 * @return
	 */
	public int addDettaglioOrdine(DettaglioOrdine ord) {
		int contiene = carrello.indexOf(ord);
		if( !carrello.contains(ord) )
			if ( ord.getDisponibilita() < ord.getQta() ){
				return -1;
			}
			else{
				carrello.add(ord);
			}
		else{
			DettaglioOrdine tmp= carrello.get(contiene);
			// Verifichiamo se la quantita' richiesta e' disponibile
			if ( ord.getDisponibilita() < (tmp.getQta() + ord.getQta()) ){
				return -1;
			}
			else{
				//aggiungiamo alla quantita' gia' presente la nuova quantita' da aggiungere
				tmp.setQta(tmp.getQta()+ord.getQta());
			}
		}
		//notifichiamo che e' stata aggiunta/modificata una riga;
//		modello.fireTableRowsInserted(carrello.size(), carrello.size());
		double tot=ord.getPrezzoVendita() * ord.getQta();
		tot=tot+MathUtility.percentualeDaAggiungere(tot, ord.getIva());
		totaleCarrello = totaleCarrello + tot;
		modello.fireTableDataChanged();
		return 1;
	}
	
	public boolean registraScarico(){
		try{
			//Salviamo i dati della fattura
			Date d = new Date();
			scarico.setIdScarico(DBManager.getIstanceSingleton().getNewID("ordini", "idordine"));
			scarico.setIdCliente(0);
			scarico.setOraScarico(new Time(d.getTime()));
			scarico.setDataScarico(new java.sql.Date(d.getTime()));
			scarico.setNote("Vendita al banco");
			scarico.setDataDocumento(new java.sql.Date(d.getTime()));
			scarico.setNumDocumento(Constant.getNumeroDocScaricoAlBanco());
			scarico.setIdDocumento(0);
			scarico.setTotaleDocumentoIvato(totaleCarrello);
			
					
			System.out.println("ricordati di settare il tipo di prezzo nella fattura");
			//scarico.setTipoPrezzo((String)cmbTipoPagamento.getSelectedItem());
			scarico.setDocFiscale(Constant.getScarico() );
			int ok = scarico.insertScarico();

			if ( ok == -1 ){
				return false;
			}
			//salviamo i dettagli della fattura
			for (DettaglioOrdine dv : carrello) {
				dv.setIdVendita(scarico.getIdScarico());
				ok = dv.insert();
				if ( ok == -1 ){
					return false;
				}
			}
			azzeraCarrello();
			return true;
		}
		catch( Exception e ){
			e.printStackTrace();
			return false;
		}
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
		totaleCarrello = 0.0;
		idSelectedItem = -1;
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
	 * Interroga se il carrello � vuoto oppure no
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
	
	
	//gestire i casi di aggiornamento negativo
	public void aggiornaQtaSelectedItem(int qta){
		for(DettaglioOrdine dv : carrello){
			if (dv.getIdArticolo() == idSelectedItem )
				dv.setQta(qta + dv.getQta());
		}
		modello.fireTableDataChanged();
	}
	
	/**
	 * Metodo che si occupa di eliminare un articolo dal carrello,
	 * se una riga e' stata selezionata viene eliminata, altrimenti si elimina l'ultima riga inserita
	 * 
	 */
	public void stornoArticolo(){
		if ( carrello.size() != 0 ){
			if ( idSelectedItem == -1 ){
				DettaglioOrdine ord = carrello.get(carrello.size() -1);
				carrello.remove(ord);
				double tot = ord.getPrezzoVendita() * ord.getQta();
				totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
			}
			else{
				for( DettaglioOrdine ord : carrello ){
					if ( ord.getIdArticolo() == idSelectedItem ){
						carrello.remove(ord);
						double tot = ord.getPrezzoVendita() * ord.getQta();
						totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
					}
				}
			}
			modello.fireTableDataChanged();
		}
	}
	
	/**
	 * Metodo che si occupa di ridurre di una unita' la quantita' della riga selezionata,
	 * se nessuna linea e' selezionata diminuisce la quantita' dell'ultima riga
	 * 
	 */
	public void stornoQtaArticolo(){
		if ( carrello.size() != 0 ){
			if ( idSelectedItem == -1 ){
				DettaglioOrdine ord = carrello.get(carrello.size()-1);
				if ( ord.getQta() == 1 ){
					carrello.remove(ord);
					double tot = ord.getPrezzoVendita() * ord.getQta();
					totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
				}
				else{
					ord.setQta(ord.getQta() - 1);
					double tot = ord.getPrezzoVendita() * ord.getQta();
					totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
				}
			}
			else{
				for( DettaglioOrdine ord : carrello ){
					if ( ord.getIdArticolo() == idSelectedItem ){
						if ( ord.getQta() == 1 ){
							carrello.remove(ord);
							double tot = ord.getPrezzoVendita() * ord.getQta();
							totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
						}
						else{
							ord.setQta(ord.getQta() - 1);
							double tot = ord.getPrezzoVendita() * ord.getQta();
							totaleCarrello = totaleCarrello - (tot + MathUtility.percentualeDaAggiungere(tot, ord.getIva()));
						}
					}
				}
			}
			modello.fireTableDataChanged();
		}
	}
	

	/**
	 * Ritorna id dell'oggetto selezionato nella tabella
	 * @return ritorna id se � selezionato un oggetto nella tabella
	 * 			oppure ritorna -1 se non c'� nessuna selezione
	 */
	public int getIdSelectedItem(){
		return idSelectedItem;
	}

	public void clearSelectionItem(){
		tblVendite.clearSelection();
		idSelectedItem=-1;
	}

	public Double getTotaleCarrello() {
		return totaleCarrello;
	}

	public void setTotaleCarrello(Double totaleCarrello) {
		this.totaleCarrello = totaleCarrello;
	}


}
