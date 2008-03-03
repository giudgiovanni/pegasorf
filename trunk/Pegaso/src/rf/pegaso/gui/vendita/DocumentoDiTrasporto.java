package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
import rf.pegaso.db.tabelle.Aspetto;
import rf.pegaso.db.tabelle.Causale;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.db.tabelle.Vendita;
import rf.pegaso.gui.gestione.ClientiAdd;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperAutoCompleteDocument;

import com.toedter.calendar.JDateChooser;

public class DocumentoDiTrasporto extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DBManager dbm = null;
	private JPanel jContentPane = null;
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
	private JLabel lblSconto = null;
	private JTextField txtSconto = null;
	private JTextField txtImponibile = null;
	private JTextField txtImposta = null;
	private JTextField txtTotale = null;
	private JLabel lblImponibile = null;
	private JLabel lblImposta = null;
	private JLabel lblTotale = null;
	private JLabel lblCliente = null;
	private IDJComboBox cmbClienti = null;
	private JButton btnNuovoCliente = null;
	private JTextField txtCodice = null;
	private JComboBox cmbProdotti = null;
	private JLabel lblUtile = null;
	private JTextField txtUtile = null;
	private Vector<DettaglioOrdine> carrello = null;  //  @jve:decl-index=0:
	private Vector<String> colonne = null;  //  @jve:decl-index=0:
	private Vendita vendita = null;
	private VenditeModel model = null;
	private JDateChooser dataTrasporto = null;
	private double utile = 0.00;
	private int scontoTotale = 0;
	private double imponibile = 0.00;
	private double imposta = 0.00;
	private JButton btnElimina = null;
	private JLabel lblDestinazione = null;
	private JTextField txtDestinazione = null;
	private JLabel lblDataTr = null;
	private JLabel lblOraTr = null;
	private JLabel lblColli = null;
	private JLabel lblPeso = null;
	private JFormattedTextField txtColli = null;
	private JFormattedTextField txtPeso = null;
	private JLabel lblCausale = null;
	private JLabel lblAspetto = null;
	private JLabel lblConsegna = null;
	private IDJComboBox cmbCausale = null;
	private IDJComboBox cmbAspetto = null;
	private JComboBox cmbConsegna = null;
	private JButton btnNuovaCausale = null;
	private JButton btnNuovoAspetto = null;
	private JFormattedTextField txtOraTr = null;
	private JLabel lblPuntini = null;
	private JFormattedTextField txtMinTr = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel pnlViewDdt = null;
	private JPanel pnlPulsanti = null;
	private JButton btnModifica = null;
	private JButton btnStampaFattura = null;
	private JButton btnEliminaDdt = null;
	private JScrollPane jScrollPane1 = null;
	private JTable tblViewDdt = null;
	private JPanel pnlDdt = null;
	private JPanel pnlRicerca = null;
	private JLabel lblRicerca = null;
	private JPanel pnlRicercaData = null;
	private JDateChooser dataRicercaA = null;
	private JDateChooser dataRicercaDa = null;
	private JLabel lblA = null;
	private JLabel lblDa = null;
	private JButton btnRicercaData = null;
	private JPanel pnlRicercaCliente = null;
	private IDJComboBox cmbClientiR = null;
	private JButton btnRicercaCliente = null;
	public DocumentoDiTrasporto(){
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
		this.setTitle("Documento di Trasporto");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}
		});
		UtilGUI.centraFrame(this);
		caricaClienti();
		caricaDescrizione();
		caricaCausale();
		caricaAspetto();
		txtNumero.setText(String.valueOf(dbm.getNewID("ddt", "idddt")));

		Calendar c=Calendar.getInstance();
		txtOraTr.setText(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
		txtMinTr.setText(String.valueOf(c.get(Calendar.MINUTE)));
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
			else if ( e.getSource() == btnNuovoCliente ){
				nuovoCliente();
				caricaClienti();
			}
			else if ( e.getSource() == btnNuovoAspetto ){
				nuovoAspetto();
				caricaAspetto();
			}
			else if ( e.getSource() == btnNuovaCausale ){
				nuovaCausale();
				caricaCausale();
			}
			else if ( e.getSource() == btnModifica )
				modificaFattura();
			else if ( e.getSource() == btnEliminaDdt )
				eliminaDdt();
			else if ( e.getSource() == btnRicercaData ){
				try {
					FatturaViewModel modelView = new FatturaViewModel(dbm, new java.sql.Date(dataRicercaDa.getDate().getTime()), new java.sql.Date(dataRicercaA.getDate().getTime()), 4);
					tblViewDdt.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
					TableColumn col=tblViewDdt.getColumnModel().getColumn(0);
					col.setMinWidth(0);
					col.setMaxWidth(0);
					col.setPreferredWidth(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if ( e.getSource() == btnRicercaCliente ){
				try {
					int cliente = Integer.parseInt(cmbClientiR.getIDSelectedItem());
					FatturaViewModel modelView = new FatturaViewModel(dbm, "idcliente", cliente, 3);
					tblViewDdt.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
					TableColumn col=tblViewDdt.getColumnModel().getColumn(0);
					col.setMinWidth(0);
					col.setMaxWidth(0);
					col.setPreferredWidth(0);
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
		dv.setIdVendita(dbm.getNewID("ddt", "idddt"));
		carrello.add(dv);
		DBManager.getIstanceSingleton().notifyDBStateChange();
		calcoliBarraInferiore();

//		Vendita v  = new Vendita();
//		Articolo a = new Articolo();
//		int spinQta = 1;
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
//		v.setCodiceVendita(dbm.getNewID("ddt", "idddt"));
//		v.setDescrizione(a.getDescrizione());
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
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelNord() {
		if (jPanelNord == null) {
			lblPuntini = new JLabel();
			lblPuntini.setBounds(new Rectangle(432, 141, 10, 16));
			lblPuntini.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPuntini.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntini.setVerticalAlignment(SwingConstants.CENTER);
			lblPuntini.setText(":");
			lblConsegna = new JLabel();
			lblConsegna.setBounds(new Rectangle(495, 141, 70, 16));
			lblConsegna.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
			lblConsegna.setVerticalAlignment(SwingConstants.CENTER);
			lblConsegna.setText("Consegna");
			lblAspetto = new JLabel();
			lblAspetto.setBounds(new Rectangle(495, 98, 70, 16));
			lblAspetto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblAspetto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAspetto.setVerticalAlignment(SwingConstants.CENTER);
			lblAspetto.setText("Aspetto");
			lblCausale = new JLabel();
			lblCausale.setBounds(new Rectangle(495, 55, 70, 16));
			lblCausale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCausale.setHorizontalAlignment(SwingConstants.CENTER);
			lblCausale.setVerticalAlignment(SwingConstants.CENTER);
			lblCausale.setText("Causale");
			lblPeso = new JLabel();
			lblPeso.setBounds(new Rectangle(10, 141, 80, 16));
			lblPeso.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setVerticalAlignment(SwingConstants.CENTER);
			lblPeso.setText("Peso (Kg)");
			lblColli = new JLabel();
			lblColli.setBounds(new Rectangle(665, 141, 55, 16));
			lblColli.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblColli.setHorizontalAlignment(SwingConstants.CENTER);
			lblColli.setVerticalAlignment(SwingConstants.CENTER);
			lblColli.setText("Colli (Nr)");
			lblOraTr = new JLabel();
			lblOraTr.setBounds(new Rectangle(340, 141, 50, 16));
			lblOraTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblOraTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblOraTr.setVerticalAlignment(SwingConstants.CENTER);
			lblOraTr.setText("Ora Tr");
			lblDataTr = new JLabel();
			lblDataTr.setBounds(new Rectangle(165, 141, 50, 16));
			lblDataTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDataTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblDataTr.setVerticalAlignment(SwingConstants.CENTER);
			lblDataTr.setText("Data Tr");
			lblDestinazione = new JLabel();
			lblDestinazione.setBounds(new Rectangle(10, 98, 80, 16));
			lblDestinazione.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDestinazione.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinazione.setText("Destinazione");
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(10, 55, 80, 16));
			lblCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCliente.setText("Cliente");
			lblData = new JLabel();
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblData.setBounds(new Rectangle(310, 12, 30, 16));
			lblData.setText("Data");
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(130, 12, 50, 16));
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero.setText("Numero");
			lblFattura = new JLabel();
			lblFattura.setBounds(new Rectangle(10, 5, 90, 30));
			lblFattura.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			lblFattura.setHorizontalAlignment(SwingConstants.CENTER);
			lblFattura.setVerticalAlignment(SwingConstants.CENTER);
			lblFattura.setFont(new Font("Dialog", Font.BOLD, 18));
			lblFattura.setText("DDT");
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 180)); // Generated
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(getBtnChiudi(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getBtnStampa(), null);
			jPanelNord.add(lblFattura, null);
			jPanelNord.add(lblNumero, null);
			jPanelNord.add(getTxtNumero(), null);
			jPanelNord.add(lblData, null);
			jPanelNord.add(getDataCorrente(), null);
			jPanelNord.add(lblCliente, null);
			jPanelNord.add(getCmbClienti(), null);
			jPanelNord.add(getBtnNuovoCliente(), null);
			jPanelNord.add(lblDestinazione, null);
			jPanelNord.add(getTxtDestinazione(), null);
			jPanelNord.add(lblDataTr, null);
			jPanelNord.add(lblOraTr, null);
			jPanelNord.add(getDataTrasporto(), null);
			jPanelNord.add(lblColli, null);
			jPanelNord.add(lblPeso, null);
			jPanelNord.add(getTxtColli(), null);
			jPanelNord.add(getTxtPeso(), null);
			jPanelNord.add(lblCausale, null);
			jPanelNord.add(lblAspetto, null);
			jPanelNord.add(lblConsegna, null);
			jPanelNord.add(getCmbCausale(), null);
			jPanelNord.add(getCmbAspetto(), null);
			jPanelNord.add(getCmbConsegna(), null);
			jPanelNord.add(getBtnNuovaCausale(), null);
			jPanelNord.add(getBtnNuovoAspetto(), null);
			jPanelNord.add(getTxtOraTr(), null);
			jPanelNord.add(lblPuntini, null);
			jPanelNord.add(getTxtMinTr(), null);
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

	private JDateChooser getDataTrasporto() {
		if (dataTrasporto == null)
			try {
				dataTrasporto = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataTrasporto.setDate(new java.util.Date());
				dataTrasporto.setBounds(new Rectangle(225, 137, 100, 24));
			} catch (Throwable throwable) {
			}
		return dataTrasporto;
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
				jTable.setDefaultRenderer(Object.class, new MyTableCellRendererAlignment());
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
					e.printStackTrace();
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
		String num_fattura = txtNumero.getText();
		if (num_fattura.equalsIgnoreCase("")) {
			messaggioCampoMancante("Numero Fattura non presente.", "CAMPO VUOTO");
			return;
		}
		int ora = Integer.parseInt(txtOraTr.getText());
		int min = Integer.parseInt(txtMinTr.getText());
		if ( ora < 0 || ora >24 || min < 0 || min > 60 ){
			messaggioCampoMancante("Ora Trasporto mal formata.", "AVVISO");
			return;
		}
		int idfattura = dbm.getNewID("ddt", "idddt");
		try {

			java.sql.Date d = new java.sql.Date(dataCorrente.getDate().getTime());
			java.sql.Time t = new Time(dataCorrente.getDate().getTime());
			int idCliente = 0;
			if ( cmbClienti.getIDSelectedItem() != null )
				idCliente = Integer.parseInt(cmbClienti.getIDSelectedItem());
			int idCausale = 0;
			if ( cmbCausale.getIDSelectedItem() != null )
				idCausale = Integer.parseInt(cmbCausale.getIDSelectedItem());
			int idAspetto = 0;
			if ( cmbAspetto.getIDSelectedItem() != null )
				idAspetto = Integer.parseInt(cmbAspetto.getIDSelectedItem());
			int colli = 0;
			if ( !txtColli.getText().equals("") )
				colli = Integer.parseInt(txtColli.getText());
			double peso = 0.00;
			if ( !txtPeso.getText().equals("") )
				peso = ControlloDati.convertPrezzoToDouble(txtPeso.getText());
			int sconto = 0;
			if ( !txtSconto.getText().equals("") )
				sconto = Integer.parseInt(txtSconto.getText());
			final Time oraTr = new Time(ora, min, 0);

			vendita.setIdVendita(idfattura);
			vendita.setNumVendita(num_fattura);
			vendita.setData_vendita(d);
			vendita.setOra_vendita(t);
			vendita.setCliente(idCliente);
			vendita.setDestinazione(txtDestinazione.getText());
			vendita.setPeso(peso);
			vendita.setDataTrasporto(new java.sql.Date(dataTrasporto.getDate().getTime()));
			vendita.setOraTrasporto(oraTr);
			vendita.setIdCausale(idCausale);
			vendita.setConsegna((String)cmbConsegna.getSelectedItem());
			vendita.setN_colli(colli);
			vendita.setAspetto(idAspetto);
			vendita.setSconto(sconto);

			vendita.salvaDatiInDdt();


		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//salviamo i dettagli della fattura
		carrello.remove(0);
		for (DettaglioOrdine dv : carrello) {
			dv.salvaInDb("dettaglio_ddt");
		}
		resetCampi();
		dbm.notifyDBStateChange();
	}

	private void resetCampi(){
		txtNumero.setText(String.valueOf(dbm.getNewID("ddt", "idddt")));
		carrello.removeAllElements();
		DettaglioOrdine v = new DettaglioOrdine();
		carrello.add(v);
		calcoliBarraInferiore();
	}

	private void nuovoCliente(){
		ClientiAdd add = new ClientiAdd(this, dbm);
		add.setVisible(true);
	}

	private void nuovoAspetto(){
		AspettoAdd add = new AspettoAdd(this);
		add.setVisible(true);
	}

	private void nuovaCausale(){
		CausaleAdd add = new CausaleAdd(this);
		add.setVisible(true);
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method initializes jPanelSud
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelSud() {
		if (jPanelSud == null) {
			lblUtile = new JLabel();
			lblUtile.setBounds(new Rectangle(155, 10, 30, 16));
			lblUtile.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblUtile.setText("Utile");
			lblTotale = new JLabel();
			lblTotale.setBounds(new Rectangle(675, 10, 40, 16));
			lblTotale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblTotale.setText("Totale");
			lblImposta = new JLabel();
			lblImposta.setBounds(new Rectangle(570, 10, 50, 16));
			lblImposta.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblImposta.setText("Imposta");
			lblImponibile = new JLabel();
			lblImponibile.setBounds(new Rectangle(460, 10, 65, 16));
			lblImponibile.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblImponibile.setText("Imponibile");
			lblSconto = new JLabel();
			lblSconto.setBounds(new Rectangle(295, 10, 60, 16));
			lblSconto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSconto.setText("Sconto %");
			jPanelSud = new JPanel();
			jPanelSud.setLayout(null);
			jPanelSud.setPreferredSize(new Dimension(0,60));
			jPanelSud.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelSud.add(lblSconto, null);
			jPanelSud.add(getTxtSconto(), null);
			jPanelSud.add(getTxtUtile(), null);
			jPanelSud.add(getTxtNPezzi(), null);
			jPanelSud.add(getTxtTotale(), null);
			jPanelSud.add(lblImponibile, null);
			jPanelSud.add(lblImposta, null);
			jPanelSud.add(lblTotale, null);
			jPanelSud.add(lblUtile, null);
			jPanelSud.add(getTxtUtile2(), null);
			jPanelSud.add(getBtnElimina(), null);
		}
		return jPanelSud;
	}

	/**
	 * This method initializes txtSconto
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtSconto() {
		if (txtSconto == null) {
			txtSconto = new JTextField();
			txtSconto.setBounds(new Rectangle(295, 30, 90, 20));
			txtSconto.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if ( e.getKeyCode() == KeyEvent.VK_ENTER ){
						calcoliBarraInferiore();
					System.out.println("invio");
					}
				}
			});
		}
		return txtSconto;
	}

	/**
	 * This method initializes txtUtile
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUtile() {
		if (txtImponibile == null) {
			txtImponibile = new JTextField();
			txtImponibile.setBounds(new Rectangle(460, 30, 90, 20));
			txtImponibile.setEditable(false);
		}
		return txtImponibile;
	}

	/**
	 * This method initializes txtNPezzi
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNPezzi() {
		if (txtImposta == null) {
			txtImposta = new JTextField();
			txtImposta.setBounds(new Rectangle(570, 30, 90, 20));
			txtImposta.setEditable(false);
		}
		return txtImposta;
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

	private IDJComboBox getCmbClienti(){
		if ( cmbClienti == null )
			try {
				cmbClienti = new IDJComboBox();
				cmbClienti.setBounds(new Rectangle(100, 50, 270, 26));
				cmbClienti.getEditor().getEditorComponent().addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusLost(java.awt.event.FocusEvent e) {
						if ( cmbClienti.getIDSelectedItem() != null ){
							Cliente c = new Cliente();
							try {
								c.caricaDati(Integer.parseInt(cmbClienti.getIDSelectedItem()));
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							String dest = "";
							if ( c.getVia() != null )
								dest += c.getVia()+" ";
							if ( c.getCap() != null )
								dest += c.getCap()+" ";
							if ( c.getCitta() != null )
								dest += c.getCitta()+" ";
							try {
								if ( c.getProvinciaToString() != null )
									dest += c.getProvincia();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							txtDestinazione.setText(dest);
						}
					}
				});
			} catch (Throwable throwable) {
			}
		return cmbClienti;
	}

	/**
	 * This method initializes btnNuovoCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoCliente() {
		if (btnNuovoCliente == null) {
			btnNuovoCliente = new JButton();
			btnNuovoCliente.setBounds(new Rectangle(393, 50, 82, 26));
			btnNuovoCliente.setText("Nuovo");
			btnNuovoCliente.addActionListener(new MyButtonListener());
		}
		return btnNuovoCliente;
	}

	private void caricaClienti(){
		Cliente c = new Cliente();
		try {

			String as[] = (String[]) c.allClienti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbClienti).caricaNewValueComboBox(as, true);
			((IDJComboBox) cmbClientiR).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento clienti nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbClienti);
		AutoCompletion.enable(cmbClientiR);
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

	private void caricaCausale(){
		Causale c = new Causale();
		try {

			String as[] = (String[]) c.allCausali();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbCausale).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento causale nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbCausale);
	}

	private void caricaAspetto(){
		Aspetto a = new Aspetto();
		try {

			String as[] = (String[]) a.allAspetti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbAspetto).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento aspetto nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbAspetto);
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
				txtCodice.setBounds(new Rectangle(15, 120, 140, 24)); // Generated
//				txtCodice.addFocusListener(new java.awt.event.FocusAdapter() {
//					@Override
//					public void focusLost(java.awt.event.FocusEvent e) {
//						caricaArticoloByCodBarre(txtCodice.getText());
//
//					}
//				});
				txtCodice.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							DettaglioOrdine dv = new DettaglioOrdine();
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
//		String codBarre = txtCodice.getText();
//		if (cod.equalsIgnoreCase(""))
//			return;
//		Articolo a = new Articolo();
//		try {
//			if (a.findByCodBarre(cod)) {
//				//cmbProdotti.setSelectedItem(a.getDescrizione());
//				prezzoAcquisto = a.getPrezzoAcquisto();
//				prezzoVendita = a.getPrezzoIngrosso();
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
//		Articolo a = new Articolo();
//		try {
//			a.caricaDati(cod);
//			prezzoAcquisto = a.getPrezzoAcquisto();
//			prezzoVendita = a.getPrezzoIngrosso();
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
				cmbProdotti.setBounds(new Rectangle(155, 120, 400, 23)); // Generated
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
			txtUtile.setBounds(new Rectangle(155, 30, 90, 20));
			txtUtile.setEditable(false);
		}
		return txtUtile;
	}

	private void azzeraCampi(){
		utile = 0.00;
		//scontoTotale = 0;
		imponibile = 0.00;
		imposta = 0.00;
	}

	private void calcoliBarraInferiore() {
		azzeraCampi();
		for( DettaglioOrdine v : carrello ) {
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
		if ( !txtSconto.getText().equals("") ){
			scontoTotale = Integer.parseInt(txtSconto.getText().trim());
			utile = utile - (utile*scontoTotale/100);
			imponibile = imponibile - (imponibile*scontoTotale/100);
			imposta = imposta - (imposta*scontoTotale/100);
		}

		txtUtile.setText(ControlloDati.convertDoubleToPrezzo(utile));
		txtImponibile.setText(ControlloDati.convertDoubleToPrezzo(imponibile));
		txtImposta.setText(ControlloDati.convertDoubleToPrezzo(imposta));
		txtTotale.setText(ControlloDati.convertDoubleToPrezzo(imponibile+imposta));
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
	 * This method initializes txtDestinazione
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDestinazione() {
		if (txtDestinazione == null) {
			txtDestinazione = new JTextField();
			txtDestinazione.setBounds(new Rectangle(100, 96, 375, 20));
		}
		return txtDestinazione;
	}

	/**
	 * This method initializes txtColli
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtColli() {
		if (txtColli == null) {
			DecimalFormat notaz = new DecimalFormat( "#");
			txtColli = new JFormattedTextField(notaz);
			txtColli.setBounds(new Rectangle(730, 139, 50, 20));
		}
		return txtColli;
	}

	/**
	 * This method initializes txtPeso
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtPeso() {
		if (txtPeso == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtPeso = new JFormattedTextField(notaz);
			txtPeso.setBounds(new Rectangle(100, 139, 50, 20));
		}
		return txtPeso;
	}

	/**
	 * This method initializes cmbCausale
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbCausale() {
		if (cmbCausale == null) {
			cmbCausale = new IDJComboBox();
			cmbCausale.setBounds(new Rectangle(575, 50, 150, 26));
		}
		return cmbCausale;
	}

	/**
	 * This method initializes cmbAspetto
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbAspetto() {
		if (cmbAspetto == null) {
			cmbAspetto = new IDJComboBox();
			cmbAspetto.setBounds(new Rectangle(575, 93, 150, 26));
		}
		return cmbAspetto;
	}

	/**
	 * This method initializes cmbConsegna
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbConsegna() {
		if (cmbConsegna == null) {
			Vector<String> v = new Vector<String>();
			v.add("Mittente");
			v.add("Destinatario");
			v.add("Vettore");
			cmbConsegna = new JComboBox(v);
			cmbConsegna.setBounds(new Rectangle(575, 136, 80, 26));
		}
		return cmbConsegna;
	}

	/**
	 * This method initializes btnNuovaCausale
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovaCausale() {
		if (btnNuovaCausale == null) {
			btnNuovaCausale = new JButton();
			btnNuovaCausale.setBounds(new Rectangle(740, 50, 42, 26));
			btnNuovaCausale.setToolTipText("Nuova Causale");
			String userDir = System.getProperty("user.dir");
			btnNuovaCausale.setIcon(new ImageIcon(userDir+File.separator+"\\resource\\nuovo.png"));
			btnNuovaCausale.addActionListener(new MyButtonListener());
			//btnNuovoAspetto.setIcon(new ImageIcon("C:/Documents and Settings/Administrator/Documenti/workspace3/Pegaso/resource/nuovo.png"));
		}
		return btnNuovaCausale;
	}

	/**
	 * This method initializes btnNuovoAspetto
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoAspetto() {
		if (btnNuovoAspetto == null) {
			btnNuovoAspetto = new JButton();
			btnNuovoAspetto.setBounds(new Rectangle(740, 93, 42, 26));
			btnNuovoAspetto.setToolTipText("Nuovo Aspetto");
			String userDir = System.getProperty("user.dir");
			btnNuovoAspetto.setIcon(new ImageIcon(userDir+File.separator+"\\resource\\nuovo.png"));
			btnNuovoAspetto.addActionListener(new MyButtonListener());
			//btnNuovoAspetto.setIcon(new ImageIcon("C:/Documents and Settings/Administrator/Documenti/workspace3/Pegaso/resource/nuovo.png"));
		}
		return btnNuovoAspetto;
	}

	/**
	 * This method initializes txtOraTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtOraTr() {
		if (txtOraTr == null) {
			DecimalFormat notaz = new DecimalFormat( "##00");
			txtOraTr = new JFormattedTextField(notaz);
			txtOraTr.setBounds(new Rectangle(400, 139, 28, 20));
		}
		return txtOraTr;
	}

	/**
	 * This method initializes txtMinTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtMinTr() {
		if (txtMinTr == null) {
			DecimalFormat notaz = new DecimalFormat( "##00");
			txtMinTr = new JFormattedTextField(notaz);
			txtMinTr.setBounds(new Rectangle(447, 139, 28, 20));
		}
		return txtMinTr;
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Registra ddt", null, getPnlDdt(), null);
			jTabbedPane.addTab("Visualizza ddt", null, getPnlViewDdt(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlViewFattura
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlViewDdt() {
		if (pnlViewDdt == null) {
			pnlViewDdt = new JPanel();
			pnlViewDdt.setLayout(new BorderLayout());
			pnlViewDdt.add(getPnlPulsanti(), java.awt.BorderLayout.SOUTH);
			pnlViewDdt.add(getJScrollPane1(), BorderLayout.CENTER);
			pnlViewDdt.add(getPnlRicerca(), BorderLayout.NORTH);
		}
		return pnlViewDdt;
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
			btnModifica.addActionListener(new MyButtonListener());
		}
		return btnModifica;
	}

	private void modificaFattura(){
		if (tblViewDdt.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this,
					"Selezionare un ddt da modificare", "AVVISO",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare il ddt selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblViewDdt.getSelectedRow();
		int idddt = ((Long) tblViewDdt.getValueAt(riga, 0)).intValue();
		DettaglioOrdine dv = new DettaglioOrdine();
		try {
			Vector<DettaglioOrdine> c2 = (Vector<DettaglioOrdine>)dv.caricaDatiByDB(idddt, "dettaglio_ddt", "idddt");
			carrello.removeAllElements();
			for ( DettaglioOrdine d : c2 ){
				carrello.add(d);
			}
			vendita.caricaDatiDaDdt(idddt);
			visualizzaVendita();
			DBManager.getIstanceSingleton().notifyDBStateChange();
			calcoliBarraInferiore();
			jTabbedPane.setSelectedIndex(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void visualizzaVendita(){
		txtNumero.setText(vendita.getNumVendita());
		dataCorrente.setDate(vendita.getData_vendita());
		cmbClienti.setSelectedItemByID(vendita.getCliente());
		txtDestinazione.setText(vendita.getDestinazione());
//		txtSpeseInc.setText(String.valueOf(vendita.getSpeseIncasso()));
//		txtSpeseTr.setText(String.valueOf(vendita.getSpeseTrasporto()));
		dataTrasporto.setDate(vendita.getDataTrasporto());
		txtOraTr.setText(String.valueOf(vendita.getOraTrasporto().getHours()));
		txtMinTr.setText(String.valueOf(vendita.getOraTrasporto().getMinutes()));
		txtColli.setText(String.valueOf(vendita.getN_colli()));
		txtPeso.setText(String.valueOf(vendita.getPeso()));
//		cmbPagamento.setSelectedItemByID(vendita.getIdPagamento());
		cmbCausale.setSelectedItemByID(vendita.getIdCausale());
		cmbAspetto.setSelectedItemByID(vendita.getAspetto());
		cmbConsegna.setSelectedItem(vendita.getConsegna());
//		cmbPorto.setSelectedItem(vendita.getPorto());
	}

	private void eliminaDdt(){
		if (tblViewDdt.getSelectedRow() <= -1) {
			messaggioCampoMancante("Selezionare un ddt da eliminare", "AVVISO");
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare il ddt selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblViewDdt.getSelectedRow();
		int idfattura = ((Long) tblViewDdt.getValueAt(riga, 0)).intValue();
		vendita.setIdVendita(idfattura);
		try {
			vendita.rimuoviDaDb("ddt", "idddt");
		} catch (rf.utility.db.eccezzioni.IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vendita = new Vendita();
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
		if (btnEliminaDdt == null) {
			btnEliminaDdt = new JButton();
			btnEliminaDdt.setText("Elimina");
			btnEliminaDdt.addActionListener(new MyButtonListener());
		}
		return btnEliminaDdt;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTblViewDdt());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tblViewFatture
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblViewDdt() {
		if (tblViewDdt == null) {
			tblViewDdt = new JTable();
		}
		return tblViewDdt;
	}

	/**
	 * This method initializes pnlDdt
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDdt() {
		if (pnlDdt == null) {
			pnlDdt = new JPanel();
			pnlDdt.setLayout(new BorderLayout());
			pnlDdt.add(getJPanelNord(), BorderLayout.NORTH);
			pnlDdt.add(getJPanelCentro(), BorderLayout.CENTER);
			pnlDdt.add(getJPanelSud(), BorderLayout.SOUTH);
		}
		return pnlDdt;
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
			pnlRicerca.add(getPnlRicercaCliente(), null);
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
	 * This method initializes pnlRicercaCliente
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicercaCliente() {
		if (pnlRicercaCliente == null) {
			pnlRicercaCliente = new JPanel();
			pnlRicercaCliente.setLayout(null);
			pnlRicercaCliente.setBounds(new Rectangle(285, 30, 245, 70));
			pnlRicercaCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Cliente", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			pnlRicercaCliente.add(getCmbClientiR(), null);
			pnlRicercaCliente.add(getBtnRicercaCliente(), null);
		}
		return pnlRicercaCliente;
	}

	/**
	 * This method initializes cmbClientiR
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbClientiR() {
		if (cmbClientiR == null) {
			cmbClientiR = new IDJComboBox();
			cmbClientiR.setBounds(new Rectangle(8, 36, 200, 26));
		}
		return cmbClientiR;
	}

	/**
	 * This method initializes btnRicercaCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRicercaCliente() {
		if (btnRicercaCliente == null) {
			btnRicercaCliente = new JButton();
			btnRicercaCliente.setBounds(new Rectangle(215, 36, 20, 26));
			btnRicercaCliente.setText("...");
			btnRicercaCliente.setToolTipText("Ricerca");
			btnRicercaCliente.addActionListener(new MyButtonListener());
		}
		return btnRicercaCliente;
	}
}