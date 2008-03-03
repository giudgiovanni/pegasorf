package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.myswing.IDJComboBox;
import rf.myswing.util.MyTableCellRendererAlignment;
import rf.myswing.util.QuantitaDisponibileEditor;
import rf.pegaso.db.model.FatturaViewModel;
import rf.pegaso.db.model.VenditeModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.Vendita;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperAutoCompleteDocument;

import com.toedter.calendar.JDateChooser;

public class AlBanco extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DBManager dbm = null;
	private JPanel jPanelNord = null;
	private JButton btnChiudi = null;
	private JButton btnSalva = null;
	private JButton btnStampa = null;
	private JLabel lblFattura = null;
	private JLabel lblNumero = null;
	private JTextField txtNumero = null;
	private JLabel lblData = null;
	private JDateChooser dataCorrente = null;
	private JPanel jPanelCentro = null;
	private JScrollPane jScrollPane = null;
	private JXTable jTable = null;
	private JPanel jPanelSud = null;
	private JTextField txtPezzi = null;
	private JTextField txtTotale = null;
	private JLabel lblPezzi = null;
	private JLabel lblTotale = null;
	private JLabel lblTipoPrezzo = null;
	private JComboBox cmbTipoPagamento = null;
	private JTextField txtCodice = null;
	private JComboBox cmbProdotti = null;
	private JLabel lblUtile = null;
	private JTextField txtUtile = null;
	private Vector<DettaglioOrdine> carrello = null;
	private Vector<String> colonne = null;  //  @jve:decl-index=0:
	private VenditeModel model = null;
	private double utile = 0.00;
	private double imponibile = 0.00;
	private double imposta = 0.00;
	private JButton btnElimina = null;
	private int qta = 0;
	private Vendita vendita = null;  //  @jve:decl-index=0:
	private JTabbedPane jTabbedPane = null;
	private JPanel pnlVendita = null;
	private JPanel pnlViewVendita = null;
	private JPanel pnlPulsanti = null;
	private JButton btnModifica = null;
	private JButton btnStampaFattura = null;
	private JButton btnEliminaFattura = null;
	private JScrollPane jScrollPane1 = null;
	private JXTable tblViewFatture = null;
	private JPanel pnlRicerca = null;
	private JLabel lblRicerca = null;
	private JPanel pnlRicercaData = null;
	private JDateChooser dataRicercaA = null;
	private JDateChooser dataRicercaDa = null;
	private JLabel lblA = null;
	private JLabel lblDa = null;
	private JButton btnRicercaData = null;
	private JPanel pnlDettaglioVendita = null;
	private JScrollPane jScrollPane2 = null;
	private JXTable tblDettaglioVendita = null;
	private JPanel pnlVendite = null;
	private JPanel jContentPane1 = null;
	private JPanel pnlCentrale = null;
	public AlBanco(){
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		carrello = new Vector<DettaglioOrdine>();
		DettaglioOrdine v = new DettaglioOrdine();
		carrello.add(v);
		vendita = new Vendita();
		colonne = new Vector<String>();
		caricaVettoreColonne();
		this.setSize(new Dimension(800, 600));
		//this.setContentPane(getJContentPane());
		this.setContentPane(getJContentPane1());
		
		this.setTitle("Al Banco");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}
		});
		//setExtendedState(MAXIMIZED_BOTH);
		UtilGUI.centraFrame(this);

		caricaDescrizione();
		caricaVettoreColonne();
		txtNumero.setText(String.valueOf(dbm.getNewID("banco", "idvendita")));
	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnChiudi )
				dispose();
			else if ( e.getSource() == btnSalva )
				salva();
			else if ( e.getSource() == btnStampa )
				stampa();
			else if ( e.getSource() == btnElimina )
				deleteArticolo();
			else if ( e.getSource() == btnRicercaData ){
				try {
					FatturaViewModel modelView = new FatturaViewModel(dbm, new java.sql.Date(dataRicercaDa.getDate().getTime()), new java.sql.Date(dataRicercaA.getDate().getTime()), 5);
					tblViewFatture.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	class MyTableModelListener implements TableModelListener{


		public void tableChanged(TableModelEvent arg0) {
			calcoliBarraInferiore();
		}


	}

	private void inserisci(DettaglioOrdine dv) {
		dv.setIdVendita(dbm.getNewID("banco", "idvendita"));
		carrello.add(dv);
		DBManager.getIstanceSingleton().notifyDBStateChange();
		calcoliBarraInferiore();

//		Vendita v  = new Vendita();
//		Articolo a = new Articolo();
//		int spinQta = 1;
//
//		try {
//			a.caricaDatiByCodBarre(txtCodice.getText());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch (IDNonValido e) {
//			e.printStackTrace();
//		}
//		try{
//			if ( a.getGiacenza() < spinQta ){
//				JOptionPane.showMessageDialog(this,
//						"Quantità richiesta non disponibile\nDisponibilità magazzino = "+a.getGiacenza(), "AVVISO",
//						JOptionPane.INFORMATION_MESSAGE);
//				return;
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		v.setCodiceArticolo(a.getIdArticolo());
//		for ( Vendita v1 : carrello){
//			if ( v1.getCodiceArticolo() == v.getCodiceArticolo() )
//				try{
//					if ( a.getGiacenza() < (spinQta + v1.getQta()) ){
//						JOptionPane.showMessageDialog(this,
//								"Quantità richiesta non disponibile\nDisponibilità magazzino = "+a.getGiacenza(), "AVVISO",
//								JOptionPane.INFORMATION_MESSAGE);
//						return;
//					}
//					else{
//						long oldQta = v1.getQta();
//						v1.setQta(oldQta + spinQta);
//						dbm.notifyDBStateChange();
//						return;
//					}
//				}
//				catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//		v.setCodiceBarre(txtCodice.getText());
//		v.setCodiceVendita(dbm.getNewID("fattura", "idfattura"));
//		v.setDescrizione(a.getDescrizione());//String.valueOf(cmbProdotti.getSelectedItem()));
//		v.setQta(Long.valueOf(spinQta));
//		v.setPrezzoAcquisto(prezzoAcquisto);
//		v.setPrezzoVendita(prezzoVendita);
//		v.setIva(iva);
//		carrello.add(v);
//		DBManager.getIstanceSingleton().notifyDBStateChange();
//		calcoliBarraInferiore();
	}

	/**
	 * Questo metodo inizializza il nome delle colonne che compongono la tabella
	 *
	 */
	private void caricaVettoreColonne(){
		colonne.add("idArticolo");
		colonne.add("codice");
		colonne.add("descrizione");
		colonne.add("um");
		colonne.add("quantita'");
		colonne.add("prezzo");
		colonne.add("importo");
		colonne.add("sconto");
		colonne.add("iva");
	}

	private void deleteArticolo(){
		if (jTable.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = jTable.getSelectedRow();
		carrello.remove(riga);
		dbm.notifyDBStateChange();
	}

	/**
	 * This method initializes jPanelNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelNord() {
		if (jPanelNord == null) {
			lblData = new JLabel();
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblData.setBounds(new Rectangle(310, 12, 30, 16));
			lblData.setText("Data");
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(130, 12, 50, 16));
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero.setText("Numero");
			lblFattura = new JLabel();
			lblFattura.setBounds(new Rectangle(10, 5, 70, 30));
			lblFattura.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			lblFattura.setFont(new Font("Dialog", Font.BOLD, 18));
			lblFattura.setText("BANCO");
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 50)); // Generated
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(getBtnChiudi(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getBtnStampa(), null);
			jPanelNord.add(lblFattura, null);
			jPanelNord.add(lblNumero, null);
			jPanelNord.add(getTxtNumero(), null);
			jPanelNord.add(lblData, null);
			jPanelNord.add(getDataCorrente(), null);
		}
		return jPanelNord;
	}

	/**
	 * This method initializes btnChiudi
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnChiudi() {
		if (btnChiudi == null) {
			btnChiudi = new JButton();
			btnChiudi.setText("Chiudi");
			btnChiudi.setBounds(new Rectangle(700, 7, 82, 26));
			btnChiudi.addActionListener(new MyButtonListener());
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Salva");
			btnSalva.setBounds(new Rectangle(514, 7, 82, 26));
			btnSalva.addActionListener(new MyButtonListener());
		}
		return btnSalva;
	}

	/**
	 * This method initializes btnStampa
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampa() {
		if (btnStampa == null) {
			btnStampa = new JButton();
			btnStampa.setText("Stampa");
			btnStampa.setBounds(new Rectangle(607, 7, 82, 26));
			btnStampa.addActionListener(new MyButtonListener());
		}
		return btnStampa;
	}

	/**
	 * This method initializes txtNumero
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(190, 10, 100, 20));
			txtNumero.setEditable(false);

		}
		return txtNumero;
	}

	private JDateChooser getDataCorrente() {
		if (dataCorrente == null)
			try {
				dataCorrente = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataCorrente.setDate(new java.util.Date());
				dataCorrente.setBounds(new Rectangle(350, 8, 112, 24));
			} catch (Throwable throwable) {
			}
		return dataCorrente;
	}

	/**
	 * This method initializes jPanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCentro() {
		if (jPanelCentro == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(new GridBagLayout());
			jPanelCentro.add(getJScrollPane(), gridBagConstraints);
		}
		return jPanelCentro;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			try {
				jScrollPane.setViewportView(getJTable());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 *
	 * @return javax.swing.JTable
	 * @throws SQLException
	 */
	private JXTable getJTable() throws SQLException {
		if (jTable == null) {
			try{
				model = new VenditeModel(carrello, colonne);
				jTable = new JXTable(model);
				DBManager.getIstanceSingleton().addDBStateChange(model);
				TableColumn col=jTable.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
				TableColumn column = jTable.getColumnModel().getColumn(2);
				column.setCellEditor(new DefaultCellEditor(getCmbProdotti()));
				column = jTable.getColumnModel().getColumn(1);
				column.setCellEditor(new DefaultCellEditor(getTxtCodice()));
				jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				column = jTable.getColumnModel().getColumn(4);
				column.setCellEditor(new QuantitaDisponibileEditor());
				//jTable.setDefaultEditor(Long.class, new QuantitaDisponibileEditorSQL());
				jTable.setDefaultRenderer(String.class, new MyTableCellRendererAlignment());
				jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				jTable.packAll();
				jTable.getTableHeader().setReorderingAllowed(false);
				jTable.getModel().addTableModelListener(new MyTableModelListener());
			}
			catch (java.lang.Throwable e) {
				try {
					PrintWriter p = new PrintWriter("errore.txt");
					e.printStackTrace(p);
					p.flush();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		return jTable;
	}

	private void stampa(){

	}

	private void salva(){
		//Salviamo i dati della fattura
		vendita.setIdVendita(dbm.getNewID("banco", "idvendita"));
		vendita.setData_vendita( new java.sql.Date(dataCorrente.getDate().getTime()));
		vendita.setOra_vendita(new Time(dataCorrente.getDate().getTime()));
		vendita.setTipo_prezzo((String)cmbTipoPagamento.getSelectedItem());
		vendita.salvaDatiInBanco();

		//salviamo i dettagli della fattura
		carrello.remove(0);
		for (DettaglioOrdine dv : carrello) {
			dv.salvaInDb("dettaglio_banco");
		}
//		----------ROCCO-----------------------------------------------
		//una volta inserita la fattura aggiorniamo anche la tabella
		//ordine che serve per tenere traccia delle quantità disponibili
		//in magazzino
		// il costruttore accetta una vendita dalla quale preleva tutti i dati
		// per effettuare le operazioni
		//codice 4 corrisponde allo scontrino fiscale
		vendita.setTipoDocumento(4);
		Scarico sc=new Scarico();
		try {
			sc.insertScarico(vendita);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nell'inserimento del dettaglio vendita al banco", "ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//---------FINE ROCCO-----------------------------------------
		resetCampi();
		dbm.notifyDBStateChange();
	}

	private void resetCampi(){
		txtNumero.setText(String.valueOf(dbm.getNewID("banco", "idvendita")));
		carrello.removeAllElements();
		DettaglioOrdine v = new DettaglioOrdine();
		carrello.add(v);
		calcoliBarraInferiore();
	}

	/**
	 * This method initializes jPanelSud
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelSud() {
		if (jPanelSud == null) {
			lblUtile = new JLabel();
			lblUtile.setBounds(new Rectangle(460, 10, 30, 16));
			lblUtile.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblUtile.setText("Utile");
			lblTotale = new JLabel();
			lblTotale.setBounds(new Rectangle(675, 10, 40, 16));
			lblTotale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblTotale.setText("Totale");
			lblPezzi = new JLabel();
			lblPezzi.setBounds(new Rectangle(570, 10, 50, 16));
			lblPezzi.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPezzi.setText("n. Pezzi");
			lblTipoPrezzo = new JLabel();
			lblTipoPrezzo.setBounds(new Rectangle(200, 10, 70, 16));
			lblTipoPrezzo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblTipoPrezzo.setText("Tipo Prezzo");
			jPanelSud = new JPanel();
			jPanelSud.setLayout(null);
			jPanelSud.setPreferredSize(new Dimension(0,60));
			jPanelSud.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelSud.add(getTxtNPezzi(), null);
			jPanelSud.add(getTxtTotale(), null);
			jPanelSud.add(lblPezzi, null);
			jPanelSud.add(lblTotale, null);
			jPanelSud.add(lblUtile, null);
			jPanelSud.add(lblTipoPrezzo, null);
			jPanelSud.add(getTxtUtile2(), null);
			jPanelSud.add(getBtnElimina(), null);
			jPanelSud.add(getCmbPagamento(), null);
		}
		return jPanelSud;
	}

	/**
	 * This method initializes txtNPezzi
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNPezzi() {
		if (txtPezzi == null) {
			txtPezzi = new JTextField();
			txtPezzi.setBounds(new Rectangle(570, 30, 90, 20));
			txtPezzi.setEditable(false);
		}
		return txtPezzi;
	}

	/**
	 * This method initializes txtTotale
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotale() {
		if (txtTotale == null) {
			txtTotale = new JTextField();
			txtTotale.setBounds(new Rectangle(675, 30, 90, 20));
			txtTotale.setEditable(false);
		}
		return txtTotale;
	}

	/**
	 * This method initializes cmbPagamento
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPagamento() {
		if (cmbTipoPagamento == null) {
			Vector<String> v = new Vector<String>();
			v.add("");
			v.add("Ingrosso");
			v.add("Dettaglio");
			cmbTipoPagamento = new JComboBox(v);
			cmbTipoPagamento.setBounds(new Rectangle(200, 29, 140, 22));
		}
		return cmbTipoPagamento;
	}

	private void caricaDescrizione(){
		Articolo a = new Articolo();
		try {

			String as[] = (String[]) a.allArticoli();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbProdotti).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbProdotti);
	}


	/**
	 * This method initializes txtCodice
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodice() {
		if (txtCodice == null) {
			try {
				txtCodice = new JTextField();
				AutoCompleteTextComponent complete = new AutoCompleteTextComponent(
						txtCodice, dbm, "articoli", "codbarre");
				dbm.addDBStateChange(complete);
				txtCodice.setDocument(new UpperAutoCompleteDocument(complete,
						true));
				txtCodice.setBounds(new Rectangle(15, 80, 140, 24)); // Generated
//				txtCodice.addFocusListener(new java.awt.event.FocusAdapter() {
//					@Override
//					public void focusLost(java.awt.event.FocusEvent e) {
//						caricaArticoloByCodBarre(txtCodice.getText());
//						System.out.println("1");
//
//					}
//				});
				txtCodice.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							DettaglioOrdine dv = new DettaglioOrdine();
							dv.caricaDatiByCodiceBarre(txtCodice.getText());
							int er = dv.caricaDatiByCodiceBarre(txtCodice.getText());
							if ( er == 0 )
								messaggioCampoMancante("Articolo non disponibile", "AVVISO");
							else if ( er == -1 )
								messaggioCampoMancante("Articolo non trovato", "AVVISO");
							else
								inserisci(dv);
						}
					}
				});
			} catch (java.lang.Throwable e) {
			}
		}
		return txtCodice;
	}

	/**
	 *
	 */
//	private void caricaArticoloByCodBarre(String cod) {
//		if ( cmbTipoPagamento.getSelectedItem().equals("") ){
//			JOptionPane.showMessageDialog(this, "Selezionare il tipo di prezzo da applicare",
//					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
//
//			return;
//		}
//		String codBarre = txtCodice.getText();
//		if (cod.equalsIgnoreCase(""))
//			return;
//		Articolo a = new Articolo();
//		try {
//			if (a.findByCodBarre(cod)) {
//				prezzoAcquisto = a.getPrezzoAcquisto();
//				if ( cmbTipoPagamento.getSelectedItem().equals("Ingrosso") )
//					prezzoVendita = a.getPrezzoIngrosso();
//				else
//					prezzoVendita = a.getPrezzoDettaglio();
//				txtCodice.setText(codBarre);
//				iva = a.getIva();
//				inserisci();
//			}
//		} catch (SQLException e1) {
//
//			e1.printStackTrace();
//		} catch (CodiceBarreInesistente e1) {
//			avvisoCodBarreInesistente();
//			e1.printStackTrace();
//		}
//
//	}

	/**
	 *
	 */
//	private void caricaArticoloByID(int cod) {
//		if ( cod == 0 )
//			return;
//		if ( cmbTipoPagamento.getSelectedItem().equals("") ){
//			JOptionPane.showMessageDialog(this, "Selezionare il tipo di prezzo da applicare",
//					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
//			return;
//		}
//		Articolo a = new Articolo();
//		try {
//			a.caricaDati(cod);
//			prezzoAcquisto = a.getPrezzoAcquisto();
//			if ( cmbTipoPagamento.getSelectedItem().equals("Ingrosso") )
//				prezzoVendita = a.getPrezzoIngrosso();
//			else
//				prezzoVendita = a.getPrezzoDettaglio();
//			txtCodice.setText(a.getCodBarre());
//			iva = a.getIva();
//			inserisci();
//		} catch (SQLException e1) {
//
//			e1.printStackTrace();
//		}
//
//	}
//
//	private void avvisoCodBarreInesistente() {
//		JOptionPane.showMessageDialog(this,
//				"Codice barre articolo inesistente", "Codice inesistente",
//				JOptionPane.INFORMATION_MESSAGE);
//	}

	/**
	 * This method initializes cmbProdotti
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbProdotti() {
		if (cmbProdotti == null) {
			try {
				cmbProdotti = new IDJComboBox();
				cmbProdotti.setBounds(new Rectangle(155, 80, 400, 23)); // Generated
				//cmbProdotti.setVisible(false);
				cmbProdotti.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter(){
					public void keyPressed(java.awt.event.KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							int id = Integer.parseInt(((IDJComboBox)cmbProdotti).getIDSelectedItem());
							DettaglioOrdine dv = new DettaglioOrdine();
							int er = dv.caricaDatiById(id);
							if ( er == 0 )
								messaggioCampoMancante("Articolo non disponibile", "AVVISO");
							else if ( er == -1 )
								messaggioCampoMancante("Articolo non trovato", "AVVISO");
							else
								inserisci(dv);
						}
					}
				});
				cmbProdotti.getEditor().getEditorComponent().addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						int id = Integer.parseInt(((IDJComboBox)cmbProdotti).getIDSelectedItem());
						DettaglioOrdine dv = new DettaglioOrdine();
						int er = dv.caricaDatiById(id);
						if ( er == 0 )
							messaggioCampoMancante("Articolo non disponibile", "AVVISO");
						else if ( er == -1 )
							messaggioCampoMancante("Articolo non trovato", "AVVISO");
						else
							inserisci(dv);
					}
				});
			} catch (java.lang.Throwable e) {
			}
		}
		return cmbProdotti;
	}

	/**
	 * This method initializes txtUtile
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUtile2() {
		if (txtUtile == null) {
			txtUtile = new JTextField();
			txtUtile.setBounds(new Rectangle(460, 30, 90, 20));
			txtUtile.setEditable(false);
		}
		return txtUtile;
	}

	private void azzeraCampi(){
		utile = 0.00;
		imponibile = 0.00;
		imposta = 0.00;
		qta = 0;
	}

	private void calcoliBarraInferiore() {
		azzeraCampi();
		for( DettaglioOrdine v : carrello ) {
			qta += v.getQta();
			double prezzoV = 0.00;
			if(v.getSconto() == 0)
				prezzoV = v.getPrezzoVendita();
				//imponibile += v.getPrezzoVendita()*v.getQta();
			else
				prezzoV = v.getPrezzoVendita()-(v.getPrezzoVendita()*v.getSconto()/100);
				//imponibile += (v.getPrezzoVendita()*v.getQta()) - (v.getPrezzoVendita()*v.getQta()*v.getSconto()/100);
			imponibile += prezzoV*v.getQta();
			imposta += (prezzoV*v.getQta()*v.getIva())/100;
			utile += (prezzoV-v.getPrezzoAcquisto())*v.getQta();
		}
		//applica sconto

		txtUtile.setText(ControlloDati.convertDoubleToPrezzo(utile));
		//txtImponibile.setText(ControlloDati.convertDoubleToPrezzo(imponibile));
		txtPezzi.setText(String.valueOf(qta));
		txtTotale.setText(ControlloDati.convertDoubleToPrezzo(imponibile+imposta));
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method initializes btnElimina
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			btnElimina = new JButton();
			btnElimina.setBounds(new Rectangle(13, 10, 82, 26));
			btnElimina.setText("Elimina");
			btnElimina.addActionListener(new MyButtonListener());
		}
		return btnElimina;
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Vendita", null, getPnlVendita(), null);
			jTabbedPane.addTab("Visualizza Vendite", null, getPnlViewVendita(), null);
			jTabbedPane.addTab("Registra Vendita", null, getPnlVendita(), null);
			jTabbedPane.addTab("Visualizza Vendita", null, getPnlViewVendita(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlVendita
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlVendita() {
		if (pnlVendita == null) {
			pnlVendita = new JPanel();
			pnlVendita.setLayout(new BorderLayout());
			pnlVendita.add(getJPanelNord(), BorderLayout.NORTH);
			pnlVendita.add(getJPanelCentro(), BorderLayout.CENTER);
			pnlVendita.add(getJPanelSud(), BorderLayout.SOUTH);
		}
		return pnlVendita;
	}

	/**
	 * This method initializes pnlViewVendita
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlViewVendita() {
		if (pnlViewVendita == null) {
			pnlViewVendita = new JPanel();
			pnlViewVendita.setLayout(new BorderLayout());
			pnlViewVendita.add(getPnlPulsanti(), BorderLayout.SOUTH);
			pnlViewVendita.add(getPnlRicerca(), BorderLayout.NORTH);
			pnlViewVendita.add(getPnlCentrale(), BorderLayout.CENTER);
		}
		return pnlViewVendita;
	}

	/**
	 * This method initializes pnlPulsanti
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlPulsanti() {
		if (pnlPulsanti == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlPulsanti = new JPanel();
			pnlPulsanti.setLayout(flowLayout);
			pnlPulsanti.add(getBtnModifica(), null);
			pnlPulsanti.add(getBtnStampaFattura(), null);
			pnlPulsanti.add(getBtnEliminaFattura(), null);
		}
		return pnlPulsanti;
	}

	/**
	 * This method initializes btnModifica
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnModifica() {
		if (btnModifica == null) {
			btnModifica = new JButton();
			btnModifica.setText("Modifica");
		}
		return btnModifica;
	}

	/**
	 * This method initializes btnStampaFattura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampaFattura() {
		if (btnStampaFattura == null) {
			btnStampaFattura = new JButton();
			btnStampaFattura.setEnabled(false);
			btnStampaFattura.setText("Stampa");
		}
		return btnStampaFattura;
	}

	/**
	 * This method initializes btnEliminaFattura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEliminaFattura() {
		if (btnEliminaFattura == null) {
			btnEliminaFattura = new JButton();
			btnEliminaFattura.setText("Elimina");
		}
		return btnEliminaFattura;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTblViewFatture());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tblViewFatture
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblViewFatture() {
		if (tblViewFatture == null) {
//			try {
//				BancoViewModel bancoModel = new BancoViewModel(dbm);
//				tblViewFatture = new JXTable(bancoModel);
//				DBManager.getIstanceSingleton().addDBStateChange(bancoModel);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			tblViewFatture = new JXTable();
		}
		return tblViewFatture;
	}

	/**
	 * This method initializes pnlRicerca
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicerca() {
		if (pnlRicerca == null) {
			lblRicerca = new JLabel();
			lblRicerca.setBounds(new Rectangle(8, 8, 80, 16));
			lblRicerca.setText("Ricerca per..");
			pnlRicerca = new JPanel();
			pnlRicerca.setLayout(null);
			pnlRicerca.setPreferredSize(new Dimension(0, 110));
			pnlRicerca.add(lblRicerca, null);
			pnlRicerca.add(getPnlRicercaData(), null);
		}
		return pnlRicerca;
	}

	/**
	 * This method initializes pnlRicercaData
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicercaData() {
		if (pnlRicercaData == null) {
			lblDa = new JLabel();
			lblDa.setBounds(new Rectangle(5, 20, 20, 16));
			lblDa.setText("da");
			lblA = new JLabel();
			lblA.setBounds(new Rectangle(125, 20, 38, 16));
			lblA.setText("a");
			pnlRicercaData = new JPanel();
			pnlRicercaData.setLayout(null);
			pnlRicercaData.setBounds(new Rectangle(5, 30, 275, 70));
			pnlRicercaData.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Data", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			pnlRicercaData.add(getDataRicercaA(), null);
			pnlRicercaData.add(getDataRicercaDa(), null);
			pnlRicercaData.add(lblA, null);
			pnlRicercaData.add(lblDa, null);
			pnlRicercaData.add(getBtnRicercaData(), null);
		}
		return pnlRicercaData;
	}

	/**
	 * This method initializes dataRicercaA
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataRicercaA() {
		if (dataRicercaA == null) {
			dataRicercaA = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataRicercaA.setBounds(new Rectangle(125, 38, 112, 24));
			dataRicercaA.setDate(new Date());
		}
		return dataRicercaA;
	}

	/**
	 * This method initializes dataRicercaDa
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataRicercaDa() {
		if (dataRicercaDa == null) {
			dataRicercaDa = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataRicercaDa.setBounds(new Rectangle(5, 38, 112, 24));
			dataRicercaDa.setDate(new Date());
			dataRicercaDa.setName("Da");
		}
		return dataRicercaDa;
	}

	/**
	 * This method initializes btnRicercaData
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRicercaData() {
		if (btnRicercaData == null) {
			btnRicercaData = new JButton();
			btnRicercaData.setBounds(new Rectangle(245, 38, 20, 24));
			btnRicercaData.setText("...");
			btnRicercaData.setToolTipText("Ricerca");
			btnRicercaData.addActionListener(new MyButtonListener());
		}
		return btnRicercaData;
	}

	/**
	 * This method initializes pnlDettaglioVendita
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDettaglioVendita() {
		if (pnlDettaglioVendita == null) {
			pnlDettaglioVendita = new JPanel();
			pnlDettaglioVendita.setLayout(new BorderLayout());
			pnlDettaglioVendita.add(getJScrollPane2(), BorderLayout.CENTER);
			pnlDettaglioVendita.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Dettaglio Vendita", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
		}
		return pnlDettaglioVendita;
	}

	/**
	 * This method initializes jScrollPane2
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getTblDettaglioVendita());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes tblDettaglioVendita
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblDettaglioVendita() {
		if (tblDettaglioVendita == null) {
			tblDettaglioVendita = new JXTable();
		}
		return tblDettaglioVendita;
	}

	/**
	 * This method initializes pnlVendite
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlVendite() {
		if (pnlVendite == null) {
			pnlVendite = new JPanel();
			pnlVendite.setLayout(new BorderLayout());
			pnlVendite.add(getJScrollPane1(), BorderLayout.CENTER);
			pnlVendite.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Vendite", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
		}
		return pnlVendite;
	}

	/**
	 * This method initializes jContentPane1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(new BorderLayout());
			jContentPane1.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane1;
	}

	/**
	 * This method initializes pnlCentrale	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setColumns(2);
			pnlCentrale = new JPanel();
			pnlCentrale.setLayout(gridLayout);
			pnlCentrale.add(getPnlVendite(), null);
			pnlCentrale.add(getPnlDettaglioVendita(), null);
		}
		return pnlCentrale;
	}
}