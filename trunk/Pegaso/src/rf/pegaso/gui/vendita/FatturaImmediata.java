package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import rf.myswing.IDJComboBox;
import rf.myswing.util.MyTableCellRendererAlignment;
import rf.myswing.util.QuantitaDisponibileEditor;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.model.FatturaViewModel;
import rf.pegaso.db.model.VenditeModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Aspetto;
import rf.pegaso.db.tabelle.Causale;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.DettaglioVendita;
import rf.pegaso.db.tabelle.Pagamento;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.Vendita;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.pegaso.gui.gestione.ClientiAdd;
import rf.utility.ControlloDati;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperAutoCompleteDocument;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
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
import java.util.Vector;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

public class FatturaImmediata extends JFrame{

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
	private JDateChooser dataRicercaDa = null;
	private JDateChooser dataRicercaA = null;
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
	private IDJComboBox cmbClientiR = null;
	private JButton btnNuovoCliente = null;
	private JLabel lblPagamento = null;
	private IDJComboBox cmbPagamento = null;
	private IDJComboBox cmbPagamentoR = null;
	private JTextField txtCodice = null;
	private JComboBox cmbProdotti = null;
	private JLabel lblUtile = null;
	private JTextField txtUtile = null;
	private Vector<DettaglioVendita> carrello = null;
	private Vector<String> colonne = null;  //  @jve:decl-index=0:
	private VenditeModel model = null;
	private JDateChooser dataTrasporto = null;
	private double utile = 0.00;
	private int scontoTotale = 0;
	private double imponibile = 0.00;
	private double imposta = 0.00;
	private JButton btnElimina = null;
	private JLabel lblDestinazione = null;
	private JLabel lblSpeseInc = null;
	private JLabel lblSpeseTr = null;
	private JTextField txtDestinazione = null;
	private JFormattedTextField txtSpeseInc = null;
	private JFormattedTextField txtSpeseTr = null;
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
	private JLabel lblPorto = null;
	private JComboBox cmbPorto = null;
	private JButton btnNuovoPagamento = null;
	private JButton btnNuovaCausale = null;
	private JButton btnNuovoAspetto = null;
	private JFormattedTextField txtOraTr = null;
	private JLabel lblPuntini = null;
	private JFormattedTextField txtMinTr = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel pnlFattura = null;
	private JPanel pnlViewFattura = null;
	private JPanel pnlRicerca = null;
	private JButton btnModifica = null;
	private JButton btnEliminaFattura = null;
	private JButton btnStampaFattura = null;
	private JScrollPane jScrollPane1 = null;
	private JTable tblViewFatture = null;
	private Vendita vendita = null;  //  @jve:decl-index=0:
	private JLabel lblRicerca = null;
	private JLabel lblDa = null;
	private JLabel lblA = null;
	private JPanel pnlRicercaData = null;
	private JPanel pnlRicercaCliente = null;
	private JPanel pnlRicercaPagamento = null;
	private JButton btnRicercaData = null;
	private JButton btnRicercaCliente = null;
	private JButton btnRicercaPagamento = null;
	private JPanel pnlPulsanti = null;
	private boolean saved;

	public FatturaImmediata(){
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		carrello = new Vector<DettaglioVendita>();
		DettaglioVendita dv = new DettaglioVendita();
		carrello.add(dv);
		vendita = new Vendita();
		colonne = new Vector<String>();
		caricaVettoreColonne();
		this.setSize(new Dimension(800, 600));
		this.setTitle("Fattura Immediata");
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
		caricaPagamento();
		caricaCausale();
		caricaAspetto();
		caricaVettoreColonne();
		txtNumero.setText(String.valueOf(dbm.getNewID("fattura", "idfattura")));

		Calendar c=Calendar.getInstance();
		txtOraTr.setText(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
		txtMinTr.setText(String.valueOf(c.get(Calendar.MINUTE)));
		saved = false;
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
			else if ( e.getSource() == btnNuovoPagamento ){
				nuovoPagamento();
				caricaPagamento();
			}
			else if ( e.getSource() == btnNuovoAspetto ){
				nuovoAspetto();
				caricaAspetto();
			}
			else if ( e.getSource() == btnNuovaCausale ){
				nuovaCausale();
				caricaCausale();
			}
			else if ( e.getSource() == btnEliminaFattura )
				eliminaFattura();
			else if ( e.getSource() == btnModifica ){
				modificaFattura();
				saved = true;
			}
			else if ( e.getSource() == btnStampaFattura ){
				stampa();
			}
			else if ( e.getSource() == btnRicercaData ){
				try {
					FatturaViewModel modelView = new FatturaViewModel(dbm, new java.sql.Date(dataRicercaDa.getDate().getTime()), new java.sql.Date(dataRicercaA.getDate().getTime()), 2);
					tblViewFatture.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
					TableColumn col=tblViewFatture.getColumnModel().getColumn(0);
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
					FatturaViewModel modelView = new FatturaViewModel(dbm, "idcliente", cliente, 1);
					tblViewFatture.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
					TableColumn col=tblViewFatture.getColumnModel().getColumn(0);
					col.setMinWidth(0);
					col.setMaxWidth(0);
					col.setPreferredWidth(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if ( e.getSource() == btnRicercaPagamento ){
				try {
					int pagamento = Integer.parseInt(cmbPagamentoR.getIDSelectedItem());
					FatturaViewModel modelView = new FatturaViewModel(dbm, "pagamento", pagamento, 1);
					tblViewFatture.setModel(modelView);
					DBManager.getIstanceSingleton().addDBStateChange(modelView);
					TableColumn col=tblViewFatture.getColumnModel().getColumn(0);
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

	private void inserisci(DettaglioVendita dv){
		dv.setIdVendita(dbm.getNewID("fattura", "idfattura"));
		carrello.add(dv);
		DBManager.getIstanceSingleton().notifyDBStateChange();
		calcoliBarraInferiore();
	}

	/**
	 * Questo metodo inizializza il nome delle colonne che compongono la tabella
	 *
	 */
	private void caricaVettoreColonne(){
		colonne.add("idArticolo");
		colonne.add("codice");
		colonne.add("descrizione");
		colonne.add("UM");
		colonne.add("quantita'");
		colonne.add("prezzo");
		colonne.add("importo");
		colonne.add("sconto");
		colonne.add("iva");
	}

	private void deleteArticolo(){
		if (jTable.getSelectedRow() <= -1) {
			messaggioCampoMancante("Selezionare un righa", "AVVISO");
			return;
		}
		int riga = jTable.getSelectedRow();
		if ( riga == 0)
			return;
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
//			jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
//			jContentPane.add(getJPanelCentro(), BorderLayout.CENTER);
//			jContentPane.add(getJPanelSud(), BorderLayout.SOUTH);
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
			lblPuntini.setBounds(new Rectangle(280, 184, 10, 16));
			lblPuntini.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPuntini.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntini.setVerticalAlignment(SwingConstants.CENTER);
			lblPuntini.setText(":");
			lblPorto = new JLabel();
			lblPorto.setBounds(new Rectangle(665, 184, 40, 16));
			lblPorto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPorto.setHorizontalAlignment(SwingConstants.CENTER);
			lblPorto.setVerticalAlignment(SwingConstants.CENTER);
			lblPorto.setText("Porto");
			lblConsegna = new JLabel();
			lblConsegna.setBounds(new Rectangle(495, 184, 70, 16));
			lblConsegna.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
			lblConsegna.setVerticalAlignment(SwingConstants.CENTER);
			lblConsegna.setText("Consegna");
			lblAspetto = new JLabel();
			lblAspetto.setBounds(new Rectangle(495, 141, 70, 16));
			lblAspetto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblAspetto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAspetto.setVerticalAlignment(SwingConstants.CENTER);
			lblAspetto.setText("Aspetto");
			lblCausale = new JLabel();
			lblCausale.setBounds(new Rectangle(495, 98, 70, 16));
			lblCausale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCausale.setHorizontalAlignment(SwingConstants.CENTER);
			lblCausale.setVerticalAlignment(SwingConstants.CENTER);
			lblCausale.setText("Causale");
			lblPeso = new JLabel();
			lblPeso.setBounds(new Rectangle(355, 184, 60, 16));
			lblPeso.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setVerticalAlignment(SwingConstants.CENTER);
			lblPeso.setText("Peso (Kg)");
			lblColli = new JLabel();
			lblColli.setBounds(new Rectangle(355, 141, 60, 16));
			lblColli.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblColli.setHorizontalAlignment(SwingConstants.CENTER);
			lblColli.setVerticalAlignment(SwingConstants.CENTER);
			lblColli.setText("Colli (Nr)");
			lblOraTr = new JLabel();
			lblOraTr.setBounds(new Rectangle(175, 184, 50, 16));
			lblOraTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblOraTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblOraTr.setVerticalAlignment(SwingConstants.CENTER);
			lblOraTr.setText("Ora Tr");
			lblDataTr = new JLabel();
			lblDataTr.setBounds(new Rectangle(175, 141, 50, 16));
			lblDataTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDataTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblDataTr.setVerticalAlignment(SwingConstants.CENTER);
			lblDataTr.setText("Data Tr");
			lblSpeseTr = new JLabel();
			lblSpeseTr.setBounds(new Rectangle(10, 184, 80, 16));
			lblSpeseTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setVerticalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setText("Spese Tr");
			lblSpeseInc = new JLabel();
			lblSpeseInc.setBounds(new Rectangle(10, 141, 80, 16));
			lblSpeseInc.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseInc.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseInc.setText("Spese Inc");
			lblDestinazione = new JLabel();
			lblDestinazione.setBounds(new Rectangle(10, 98, 80, 16));
			lblDestinazione.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDestinazione.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinazione.setText("Destinazione");
			lblPagamento = new JLabel();
			lblPagamento.setBounds(new Rectangle(495, 55, 70, 16));
			lblPagamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPagamento.setHorizontalAlignment(SwingConstants.CENTER);
			lblPagamento.setText("Pagamento");
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
			lblFattura.setFont(new Font("Dialog", Font.BOLD, 18));
			lblFattura.setText("FATTURA");
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 230)); // Generated
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
			jPanelNord.add(lblPagamento, null);
			jPanelNord.add(getCmbPagamento(), null);
			jPanelNord.add(lblDestinazione, null);
			jPanelNord.add(lblSpeseInc, null);
			jPanelNord.add(lblSpeseTr, null);
			jPanelNord.add(getTxtDestinazione(), null);
			jPanelNord.add(getTxtSpeseInc(), null);
			jPanelNord.add(getTxtSpeseTr(), null);
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
			jPanelNord.add(lblPorto, null);
			jPanelNord.add(getCmbPorto(), null);
			jPanelNord.add(getBtnNuovoPagamento(), null);
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
				dataTrasporto.setBounds(new Rectangle(235, 137, 100, 24));
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
//				column = jTable.getColumnModel().getColumn(4);
//				column.setCellEditor(new QuantitaDisponibileEditor());
				jTable.setDefaultEditor(Integer.class, new QuantitaDisponibileEditor());
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
		int idCliente = 0;
		if ( cmbClienti.getIDSelectedItem() != null )
			idCliente = Integer.parseInt(cmbClienti.getIDSelectedItem());
		int idPagamento = 0;
		if ( cmbPagamento.getIDSelectedItem() != null )
			idPagamento = Integer.parseInt(cmbPagamento.getIDSelectedItem());
		int idCausale = 0;
		if ( cmbCausale.getIDSelectedItem() != null )
			idCausale = Integer.parseInt(cmbCausale.getIDSelectedItem());
		int idAspetto = 0;
		if ( cmbAspetto.getIDSelectedItem() != null )
			idAspetto = Integer.parseInt(cmbAspetto.getIDSelectedItem());
		double speseInc = 0.00;
		double speseTr = 0.00;
		int colli = 0;
		double peso = 0.00;
		int sconto = 0;
		try {
			if ( !txtSpeseInc.getText().equals("") )
				speseInc = ControlloDati.convertPrezzoToDouble(txtSpeseInc.getText());
			if ( !txtSpeseTr.getText().equals("") )
				speseTr = ControlloDati.convertPrezzoToDouble(txtSpeseTr.getText());
			if ( !txtColli.getText().equals("") )
				colli = Integer.parseInt(txtColli.getText());
			if ( !txtPeso.getText().equals("") )
				peso = ControlloDati.convertPrezzoToDouble(txtPeso.getText());
			if ( !txtSconto.getText().equals("") )
				sconto = Integer.parseInt(txtSconto.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final Time oraTr = new Time(ora, min, 0);

		Vendita v = new Vendita();
		v.setIdVendita(dbm.getNewID("fattura", "idfattura"));
		v.setData_vendita(new java.sql.Date(dataCorrente.getDate().getTime()));
		v.setOra_vendita(new Time(dataCorrente.getDate().getTime()));
		v.setCliente(idCliente);
		v.setIdPagamento(idPagamento);
		v.setNumVendita(num_fattura);
		v.setIdCausale(idCausale);
		v.setSpeseIncasso(speseInc);
		v.setSpeseTrasporto(speseTr);
		v.setDataTrasporto(new java.sql.Date(dataTrasporto.getDate().getTime()));
		v.setOraTrasporto(oraTr);
		v.setN_colli(colli);
		v.setPeso(peso);
		v.setConsegna((String)cmbConsegna.getSelectedItem());
		v.setPorto((String)cmbPorto.getSelectedItem());
		v.setDestinazione(txtDestinazione.getText());
		v.setAspetto(idAspetto);
		v.setSconto(sconto);
		if( saved )
			v.updateDatiInFattura();
		else
			v.salvaDatiInFattura();

		//salviamo i dettagli della fattura
		carrello.remove(0);
		for (DettaglioVendita dv : carrello) {
			dv.salvaInDb("dettaglio_fattura");
			//carrello.remove(dv);
		}
		//----------ROCCO-----------------------------------------------
		//una volta inserita la fattura aggiorniamo anche la tabella
		//ordine che serve per tenere traccia delle quantità disponibili
		//in magazzino
		// il costruttore accetta una vendita dalla quale preleva tutti i dati
		// per effettuare le operazioni
		v.setTipoDocumento(1);
		Scarico sc=new Scarico();
		try {
			sc.insertScarico(v);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nell'inserimento del dettaglio vendita", "ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		//---------FINE ROCCO-----------------------------------------
		dbm.notifyDBStateChange();
		resetCampi();
	}

	private void resetCampi(){
		//txtNumero.setText(String.valueOf(dbm.getNewID("banco", "idvendita")));
		carrello.removeAllElements();
		DettaglioVendita v = new DettaglioVendita();
		carrello.add(v);
		calcoliBarraInferiore();
	}

	private void nuovoCliente(){
		ClientiAdd add = new ClientiAdd(this, dbm);
		add.setVisible(true);
	}

	private void nuovoPagamento(){
		PagamentoAdd add = new PagamentoAdd(this);
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
							if ( c.getProvincia() != null )
								dest += c.getProvincia();
							txtDestinazione.setText(dest);
						}
					}
				});
			} catch (Throwable throwable) {
			}
		return cmbClienti;
	}

	private IDJComboBox getCmbClientiRicerca(){
		if ( cmbClientiR == null )
			try {
				cmbClientiR = new IDJComboBox();
				cmbClientiR.setBounds(new Rectangle(8, 36, 200, 26));
			} catch (Throwable throwable) {
			}
		return cmbClientiR;
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

	/**
	 * This method initializes cmbPagamento
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPagamento() {
		if (cmbPagamento == null) {
			cmbPagamento = new IDJComboBox();
			cmbPagamento.setBounds(new Rectangle(575, 50, 150, 26));
		}
		return cmbPagamento;
	}

	/**
	 * This method initializes cmbPagamentoR
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPagamentoRicerca() {
		if (cmbPagamentoR == null) {
			cmbPagamentoR = new IDJComboBox();
			cmbPagamentoR.setBounds(new Rectangle(8, 36, 200, 26));
		}
		return cmbPagamentoR;
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
			messaggioCampoMancante("Errore caricamento clienti nel combobox", "ERRORE");
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
			messaggioCampoMancante("Errore caricamento fornitori nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbProdotti);
	}

	private void caricaPagamento(){
		Pagamento p = new Pagamento();
		try {

			String as[] = (String[]) p.allPagamenti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbPagamento).caricaNewValueComboBox(as, true);
			((IDJComboBox) cmbPagamentoR).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			messaggioCampoMancante("Errore caricamento pagamenti nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbPagamento);
		AutoCompletion.enable(cmbPagamentoR);
	}

	private void caricaCausale(){
		Causale c = new Causale();
		try {

			String as[] = (String[]) c.allCausali();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbCausale).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			messaggioCampoMancante("Errore caricamento causale nel combobox", "ERRORE");
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
			messaggioCampoMancante("Errore caricamento aspetto nel combobox", "ERRORE");
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
							DettaglioVendita dv = new DettaglioVendita();
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

//	private void avvisoCodBarreInesistente() {
//		messaggioCampoMancante("Codice barre articolo inesistente", "Codice inesistente");
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
							DettaglioVendita dv = new DettaglioVendita();
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
						DettaglioVendita dv = new DettaglioVendita();
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
		//for( Vendita v : carrello ) {
		for (int i = 0; i < carrello.size(); i++ ){
			DettaglioVendita v = (DettaglioVendita)carrello.get(i);
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
	 * This method initializes txtSpeseInc
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtSpeseInc() {
		if (txtSpeseInc == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtSpeseInc = new JFormattedTextField(notaz);
			txtSpeseInc.setBounds(new Rectangle(100, 139, 55, 20));
		}
		return txtSpeseInc;
	}

	/**
	 * This method initializes txtSpeseTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtSpeseTr() {
		if (txtSpeseTr == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtSpeseTr = new JFormattedTextField(notaz);
			txtSpeseTr.setBounds(new Rectangle(100, 182, 55, 20));
		}
		return txtSpeseTr;
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
			txtColli.setBounds(new Rectangle(425, 139, 50, 20));
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
			txtPeso.setBounds(new Rectangle(425, 182, 50, 20));
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
			cmbCausale.setBounds(new Rectangle(575, 93, 150, 26));
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
			cmbAspetto.setBounds(new Rectangle(575, 136, 150, 26));
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
			cmbConsegna.setBounds(new Rectangle(575, 179, 80, 26));
		}
		return cmbConsegna;
	}

	/**
	 * This method initializes cmbPorto
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPorto() {
		if (cmbPorto == null) {
			Vector<String> v = new Vector<String>();
			v.add("");
			v.add("Franco");
			v.add("Assegnato");
			cmbPorto = new JComboBox(v);
			cmbPorto.setBounds(new Rectangle(715, 179, 67, 26));
		}
		return cmbPorto;
	}

	/**
	 * This method initializes btnNuovoPagamento
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoPagamento() {
		if (btnNuovoPagamento == null) {
			btnNuovoPagamento = new JButton();
			btnNuovoPagamento.setBounds(new Rectangle(740, 50, 42, 26));
			btnNuovoPagamento.setToolTipText("Nuovo Pagamento");
			String userDir = System.getProperty("user.dir");
			btnNuovoPagamento.setIcon(new ImageIcon(userDir+File.separator+"\\resource\\nuovo.png"));
			btnNuovoPagamento.addActionListener(new MyButtonListener());
			//btnNuovoPagamento.setIcon(new ImageIcon("C:/Documents and Settings/Administrator/Documenti/workspace3/Pegaso/resource/nuovo.png"));
		}
		return btnNuovoPagamento;
	}

	/**
	 * This method initializes btnNuovaCausale
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovaCausale() {
		if (btnNuovaCausale == null) {
			btnNuovaCausale = new JButton();
			btnNuovaCausale.setBounds(new Rectangle(740, 93, 42, 26));
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
			btnNuovoAspetto.setBounds(new Rectangle(740, 136, 42, 26));
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
			txtOraTr.setBounds(new Rectangle(235, 182, 40, 20));
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
			txtMinTr.setBounds(new Rectangle(295, 182, 40, 20));
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
			jTabbedPane.addTab("Registra Fatture", null, getPnlFattura(), null);
			jTabbedPane.addTab("Visualizza Fatture", null, getPnlViewFattura(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlFattura
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlFattura() {
		if (pnlFattura == null) {
			pnlFattura = new JPanel();
			pnlFattura.setLayout(new BorderLayout());
			pnlFattura.add(getJPanelNord(), BorderLayout.NORTH);
			pnlFattura.add(getJPanelCentro(), BorderLayout.CENTER);
			pnlFattura.add(getJPanelSud(), BorderLayout.SOUTH);
		}
		return pnlFattura;
	}

	/**
	 * This method initializes pnlViewFattura
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlViewFattura() {
		if (pnlViewFattura == null) {
			pnlViewFattura = new JPanel();
			pnlViewFattura.setLayout(new BorderLayout());
			pnlViewFattura.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Generated
			pnlViewFattura.add(getPnlRicerca(), BorderLayout.NORTH);
			pnlViewFattura.add(getJScrollPane1(), BorderLayout.CENTER);
			pnlViewFattura.add(getPnlPulsanti(), BorderLayout.SOUTH);
		}
		return pnlViewFattura;
	}

	/**
	 * This method initializes pnlRicerca
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicerca() {
		if (pnlRicerca == null) {
			lblA = new JLabel();
			lblA.setText("a");
			lblA.setBounds(new Rectangle(125, 20, 38, 16));
			lblDa = new JLabel();
			lblDa.setText("da");
			lblDa.setBounds(new Rectangle(5, 20, 20, 16));
			lblRicerca = new JLabel();
			lblRicerca.setBounds(new Rectangle(8, 8, 80, 16));
			lblRicerca.setText("Ricerca per..");
			pnlRicerca = new JPanel();
			pnlRicerca.setLayout(null);
			pnlRicerca.setPreferredSize(new Dimension(0,110));
//			pnlRicerca.add(getBtnModifica(), BorderLayout.EAST);
//			pnlRicerca.add(getBtnStampaFattura(), BorderLayout.CENTER); // Generated
//			pnlRicerca.add(getBtnEliminaFattura(), BorderLayout.WEST); // Generated
			pnlRicerca.add(lblRicerca, null);
			pnlRicerca.add(getPnlRicercaData(), null);
			pnlRicerca.add(getPnlRicercaCliente(), null);
			pnlRicerca.add(getPnlRicercaPagamento(), null);
		}
		return pnlRicerca;
	}

	/**
	 * This method initializes btnModifica
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnModifica() {
		if (btnModifica == null) {
			try {
				btnModifica = new JButton();
				btnModifica.setText("Modifica"); // Generated
				btnModifica.addActionListener(new MyButtonListener());
			} catch (java.lang.Throwable e) {
			}
		}
		return btnModifica;
	}

	/**
	 * This method initializes btnStampa
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampaFattura() {
		if (btnStampaFattura == null) {
			try {
				btnStampaFattura = new JButton();
				btnStampaFattura.setEnabled(false); // Generated
				btnStampaFattura.setText("Stampa"); // Generated
			} catch (java.lang.Throwable e) {
			}
		}
		return btnStampaFattura;
	}

	/**
	 * This method initializes btnEliminaCarico
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEliminaFattura() {
		if (btnEliminaFattura == null) {
			btnEliminaFattura = new JButton();
			btnEliminaFattura.setText("Elimina"); // Generated
			btnEliminaFattura.addActionListener(new MyButtonListener());
		}
		return btnEliminaFattura;
	}

	private void eliminaFattura(){
		if (tblViewFatture.getSelectedRow() <= -1) {
			messaggioCampoMancante("Selezionare una fattura da eliminare", "AVVISO");
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare la fattura selezionata?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblViewFatture.getSelectedRow();
		int idfattura = ((Long) tblViewFatture.getValueAt(riga, 0)).intValue();
		vendita.setIdVendita(idfattura);
		try {
			vendita.rimuoviDaDb("fattura", "idfattura");
		} catch (IDNonValido e) {
			e.printStackTrace();
		}
		vendita = new Vendita();
	}

	private void visualizzaFattura(){

	}

	private void modificaFattura(){
		if (tblViewFatture.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this,
					"Selezionare una fattura da modificare", "AVVISO",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare la fattura selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblViewFatture.getSelectedRow();
		int idfattura = ((Long) tblViewFatture.getValueAt(riga, 0)).intValue();
		DettaglioVendita dv = new DettaglioVendita();
		try {
			Vector<DettaglioVendita> c2 = (Vector<DettaglioVendita>)dv.caricaDatiByDB(idfattura, "dettaglio_fattura", "idfattura");
			carrello.removeAllElements();
			for ( DettaglioVendita d : c2 ){
				carrello.add(d);
			}
			vendita.caricaDatiDaFattura(idfattura);
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
		txtSpeseInc.setText(String.valueOf(vendita.getSpeseIncasso()));
		txtSpeseTr.setText(String.valueOf(vendita.getSpeseTrasporto()));
		dataTrasporto.setDate(vendita.getDataTrasporto());
		txtOraTr.setText(String.valueOf(vendita.getOraTrasporto().getHours()));
		txtMinTr.setText(String.valueOf(vendita.getOraTrasporto().getMinutes()));
		txtColli.setText(String.valueOf(vendita.getN_colli()));
		txtPeso.setText(String.valueOf(vendita.getPeso()));
		cmbPagamento.setSelectedItemByID(vendita.getIdPagamento());
		cmbCausale.setSelectedItemByID(vendita.getIdCausale());
		cmbAspetto.setSelectedItemByID(vendita.getAspetto());
		cmbConsegna.setSelectedItem(vendita.getConsegna());
		cmbPorto.setSelectedItem(vendita.getPorto());
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
			tblViewFatture = new JTable();
		}
		return tblViewFatture;
	}

	private JDateChooser getDataRicercaDa() {
		if (dataRicercaDa == null)
			try {
				dataRicercaDa = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataRicercaDa.setDate(new java.util.Date());
				dataRicercaDa.setBounds(new Rectangle(5, 38, 112, 24));
				dataRicercaDa.setName("Da");
			} catch (Throwable throwable) {
			}
		return dataRicercaDa;
	}

	private JDateChooser getDataRicercaA() {
		if (dataRicercaA == null)
			try {
				dataRicercaA = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataRicercaA.setDate(new java.util.Date());
				dataRicercaA.setBounds(new Rectangle(125, 38, 112, 24));
			} catch (Throwable throwable) {
			}
		return dataRicercaA;
	}

	/**
	 * This method initializes pnlRicercaData
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicercaData() {
		if (pnlRicercaData == null) {
			pnlRicercaData = new JPanel();
			pnlRicercaData.setLayout(null);
			pnlRicercaData.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Data", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			pnlRicercaData.setBounds(new Rectangle(5, 30, 275, 70));
			pnlRicercaData.add(getDataRicercaA(), null);
			pnlRicercaData.add(getDataRicercaDa(), null);
			pnlRicercaData.add(lblA, null);
			pnlRicercaData.add(lblDa, null);
			pnlRicercaData.add(getBtnRicercaData(), null);
		}
		return pnlRicercaData;
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
			pnlRicercaCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Cliente", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			pnlRicercaCliente.setBounds(new Rectangle(285, 30, 245, 70));
			pnlRicercaCliente.add(getCmbClientiRicerca(), null);
			pnlRicercaCliente.add(getBtnRicercaCliente(), null);
		}
		return pnlRicercaCliente;
	}

	/**
	 * This method initializes pnlRicercaPagamento
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRicercaPagamento() {
		if (pnlRicercaPagamento == null) {
			pnlRicercaPagamento = new JPanel();
			pnlRicercaPagamento.setLayout(null);
			pnlRicercaPagamento.setBounds(new Rectangle(535, 30, 245, 70));
			pnlRicercaPagamento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Tipo Pagamento", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			pnlRicercaPagamento.add(getCmbPagamentoRicerca(), null);
			pnlRicercaPagamento.add(getBtnRicercaPagamento(), null);
		}
		return pnlRicercaPagamento;
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

	/**
	 * This method initializes btnRicercaPagamento
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRicercaPagamento() {
		if (btnRicercaPagamento == null) {
			btnRicercaPagamento = new JButton();
			btnRicercaPagamento.setBounds(new Rectangle(215, 36, 20, 26));
			btnRicercaPagamento.setText("...");
			btnRicercaPagamento.setToolTipText("Ricerca");
			btnRicercaPagamento.addActionListener(new MyButtonListener());
		}
		return btnRicercaPagamento;
	}

	/**
	 * This method initializes pnlPulsanti
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlPulsanti() {
		if (pnlPulsanti == null) {
			pnlPulsanti = new JPanel();
			pnlPulsanti.setLayout(new GridBagLayout());
			pnlPulsanti.add(getBtnEliminaFattura(),null);
			pnlPulsanti.add(getBtnModifica(), null);
			pnlPulsanti.add(getBtnStampaFattura(), null);
		}
		return pnlPulsanti;
	}
}