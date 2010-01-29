package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.myswing.IDJComboBox;
import rf.myswing.util.MyTableCellRendererAlignment;
import rf.myswing.util.QuantitaDisponibileEditorSQL;
import rf.pegaso.db.model.DdtFatturaModel;
import rf.pegaso.db.model.VenditeModel;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.DettaglioScarico;
import rf.pegaso.db.tabelle.Pagamento;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.Vendita;
import rf.pegaso.gui.gestione.ClientiAdd;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;

import com.toedter.calendar.JDateChooser;

/**
 * @author Administrator
 *
 */
public class Fattura extends JFrame{

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
	private JLabel lblPagamento = null;
	private IDJComboBox cmbPagamento = null;
	private JLabel lblUtile = null;
	private JTextField txtUtile = null;
	private Vector<DettaglioScarico> carrello = null;  //  @jve:decl-index=0:
	private Vector<String> colonne = null;  //  @jve:decl-index=0:
	private VenditeModel model = null;
	private DdtFatturaModel ddtModel = null;
	private double utile = 0.00;
	private int scontoTotale = 0;
	private double imponibile = 0.00;
	private double imposta = 0.00;
	private JPanel jPanelOvest = null;
	private JScrollPane jScrollPane1 = null;
	private JXTable jTableDdt = null;
	private JLabel lblSpeseIncasso = null;
	private JFormattedTextField txtSpeseIncasso = null;
	private JLabel lblSpeseTr = null;
	private JFormattedTextField txtSpeseTr = null;
	private int id_ddt = 0;
	private Vendita vendita = null;

	public Fattura(){
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		carrello = new Vector<DettaglioScarico>();
		colonne = new Vector<String>();
		caricaVettoreColonne();

		vendita = new Vendita();
		this.setSize(new Dimension(800, 600));
		this.setTitle("Fattura");
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
		//setExtendedState(MAXIMIZED_BOTH);
		UtilGUI.centraFrame(this);
		operazioniIniziali();
	}

	private void operazioniIniziali(){

		caricaClienti();
		caricaPagamento();
		caricaVettoreColonne();
		txtNumero.setText(String.valueOf(dbm.getNewID("fattura", "idfattura")));
	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnChiudi )
				dispose();
			else if ( e.getSource() == btnSalva ){
				int er = salva();
				if ( er == 1 )
					deleteDdt();
				else if( er == 0 )
					messaggioCampoMancante("Si è verificato un errore durante il salvataggio. Riprovare.", "ERRORE");
			}
			else if ( e.getSource() == btnStampa )
				stampa();
			else if ( e.getSource() == btnNuovoCliente ){
				nuovoCliente();
				caricaClienti();
			}
		}
	}

	class MyTableModelListener implements TableModelListener{


		public void tableChanged(TableModelEvent arg0) {
			calcoliBarraInferiore();
		}
	}

	/**
	 * questo metodo rimuove il ddt dal db quando si effettua la registrazione della fattura
	 */
	private void deleteDdt(){
		Statement st = dbm.getNewStatement();
		String query = "DELETE FROM dettaglio_ddt WHERE idddt=" + id_ddt;
		String query2 = "DELETE FROM ddt WHERE idddt=" + id_ddt;
		try {
			st.executeUpdate(query);
			st.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.notifyDBStateChange();
	}

	private void visualizzaDdt(){
		int riga = jTableDdt.getSelectedRow();
		if ( riga == -1 ){
			messaggioCampoMancante("Selezionare la riga da visualizzare", "AVVISO");
			return;
		}
		id_ddt = ((Long)jTableDdt.getValueAt(riga, 0)).intValue();
		carrello.removeAllElements();
		azzeraCampi();
		vendita.caricaDatiDaDdt(id_ddt);
		visualizzaVendita();
		DettaglioScarico dv = new DettaglioScarico();
		try {
			carrello.removeAllElements();
			for ( DettaglioScarico d : dv.caricaDatiByDB(id_ddt) ){
				carrello.add(d);
			}
			DBManager.getIstanceSingleton().notifyDBStateChange();
			calcoliBarraInferiore();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void visualizzaVendita(){
		dataCorrente.setDate(vendita.getData_vendita());
		cmbClienti.setSelectedItemByID(vendita.getCliente());
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

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
			jContentPane.add(getJPanelCentro(), BorderLayout.CENTER);
			jContentPane.add(getJPanelSud(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelOvest(), BorderLayout.WEST);
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
			lblSpeseTr = new JLabel();
			lblSpeseTr.setBounds(new Rectangle(225, 98, 100, 16));
			lblSpeseTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseTr.setText("Spese Trasporto");
			lblSpeseIncasso = new JLabel();
			lblSpeseIncasso.setBounds(new Rectangle(10, 98, 95, 16));
			lblSpeseIncasso.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseIncasso.setText(" Spese Incasso");
			lblPagamento = new JLabel();
			lblPagamento.setBounds(new Rectangle(445, 98, 70, 16));
			lblPagamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPagamento.setText("Pagamento");
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(10, 55, 50, 16));
			lblCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCliente.setText(" Cliente");
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
			jPanelNord.setPreferredSize(new Dimension(0, 130)); // Generated
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
			jPanelNord.add(lblSpeseIncasso, null);
			jPanelNord.add(getTxtSpeseIncasso(), null);
			jPanelNord.add(lblSpeseTr, null);
			jPanelNord.add(getTxtSpeseTr(), null);
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

	/**
	 * This method initializes jPanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCentro() {
		if (jPanelCentro == null) {
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(new BorderLayout());
			jPanelCentro.setPreferredSize(new Dimension(500, 3));
			jPanelCentro.add(getJScrollPane(), BorderLayout.CENTER);
			jPanelCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Dettaglio del Documenti di Trasporto", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(new BorderLayout());
			jPanelCentro.setPreferredSize(new Dimension(500, 3));
			jPanelCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "Dettaglio Documento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelCentro.add(getJScrollPane(), BorderLayout.CENTER);
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
//				TableColumn column = jTable.getColumnModel().getColumn(2);
//				column.setCellEditor(new DefaultCellEditor(getCmbProdotti()));
//				column = jTable.getColumnModel().getColumn(1);
//				column.setCellEditor(new DefaultCellEditor(getTxtCodice()));
				jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				jTable.setDefaultEditor(Long.class, new QuantitaDisponibileEditorSQL());
				jTable.setDefaultRenderer(Object.class, new MyTableCellRendererAlignment());
				jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				jTable.packAll();
				jTable.getTableHeader().setReorderingAllowed(false);
				jTable.setEditable(false);
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

	private int salva(){
//		Salviamo i dati della fattura
		Vendita v = new Vendita();
		try {

			String num_fattura = txtNumero.getText();
			if (num_fattura.equalsIgnoreCase("")) {
				messaggioCampoMancante("Numero DDT non presente.", "CAMPO VUOTO");
				return -1;
			}
			if ( carrello.size() == 0 ){
				messaggioCampoMancante("Selezionare il documento da salvare.", "AVVISO");
				return -1;
			}
			//PreparedStatement pst = null;
			v.setIdVendita(dbm.getNewID("fattura", "idfattura"));
			v.setNumVendita(txtNumero.getText());
			v.setData_vendita(new java.sql.Date(dataCorrente.getDate().getTime()));
			v.setOra_vendita(new Time(dataCorrente.getDate().getTime()));
			int idCliente = 0;
			if ( cmbClienti.getIDSelectedItem() != null )
				idCliente = Integer.parseInt(cmbClienti.getIDSelectedItem());
			double speseInc = 0.00;
			if ( !txtSpeseIncasso.getText().equals("") )
				speseInc = ControlloDati.convertPrezzoToDouble(txtSpeseIncasso.getText());
			double speseTr = 0.00;
			if ( !txtSpeseTr.getText().equals("") )
				speseTr = ControlloDati.convertPrezzoToDouble(txtSpeseTr.getText());
			int idPagamento = 0;
			if ( cmbPagamento.getIDSelectedItem() != null )
				idPagamento = Integer.parseInt(cmbPagamento.getIDSelectedItem());
			v.setCliente(idCliente);
			v.setDestinazione(vendita.getDestinazione());
			v.setSpeseIncasso(speseInc);
			v.setSpeseTrasporto(speseTr);
			v.setDataTrasporto(vendita.getDataTrasporto());
			v.setOraTrasporto(vendita.getOraTrasporto());
			v.setN_colli(vendita.getN_colli());
			v.setPeso(vendita.getPeso());
			v.setIdPagamento(idPagamento);
			v.setIdCausale(vendita.getIdCausale());
			v.setAspetto(vendita.getAspetto());
			v.setConsegna(vendita.getConsegna());
			v.setPorto(vendita.getPorto());
			v.setSconto(vendita.getSconto());
			v.salvaDatiInFattura();

			//salviamo i dettagli della fattura
			//ROCCO rimuoviamo questo codice in quanto in questa scermata
			//non c'è la riga vuota come in fattura immediata e altre
			//altrimenti elimina la prima riga che è cmq buona
			//carrello.remove(0);
			for (DettaglioScarico dettaglio : carrello) {
				//dato che in questo dettaglio è presente il codice del ddt
				//mentre come codice vendita dobbiamo ora usare quello della
				//fattura lo sostituiamo con il corrente idfattura prima di effettuare
				//l'inserimento
				dettaglio.setIdVendita(v.getIdVendita());
				dettaglio.insert();
				//dettaglio.salvaInDb("dettaglio_fattura");
				//carrello.remove(dettaglio);
			}
			carrello.removeAllElements();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//----------ROCCO-----------------------------------------------
		//una volta inserita la fattura aggiorniamo anche la tabella
		//ordine che serve per tenere traccia delle quantità disponibili
		//in magazzino
		// il costruttore accetta una vendita dalla quale preleva tutti i dati
		// per effettuare le operazioni
		//codice 4 corrisponde allo scontrino fiscale
		v.setTipoDocumento(1);
		Scarico sc=new Scarico();
		sc.insertScarico();
		//---------FINE ROCCO-----------------------------------------
		dbm.notifyDBStateChange();
		txtSpeseIncasso.setText("");
		txtSpeseTr.setText("");
		return 1;
	}

//	public void updateArticolo(int idArticolo, int qta)
//	throws SQLException {
//
//		String query = "update dettaglio_carichi set qta=? where idarticolo=?";
//		PreparedStatement pst = dbm.getNewPreparedStatement(query);
//
//		pst.setInt(1, qta);
//		pst.setInt(2, idArticolo);
//
//		// inserimento
//		pst.executeUpdate();
//
//		if (pst != null)
//			pst.close();
//		dbm.notifyDBStateChange();
//		calcoliBarraInferiore();
//	}

	private void nuovoCliente(){
		//prendiamo l'id dell'ultimo cliente inserito
		int oldId = (dbm.getNewID("clienti", "idcliente") - 1);
		//apriamo l'iterfaccia per l'inserimento di un nuovo cliente
		ClientiAdd add = new ClientiAdd(this, dbm);
		add.setVisible(true);
		int newId = (dbm.getNewID("clienti", "idcliente") - 1);
		if ( oldId != newId )
			cmbClienti.setSelectedIndex(newId);
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
					if ( e.getKeyCode() == KeyEvent.VK_ENTER )
						calcoliBarraInferiore();
					System.out.println("invio");
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
				cmbClienti.setBounds(new Rectangle(80, 50, 280, 26));
				//dbm.getIstanceSingleton().addDBStateChange(cmbClienti);
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
			btnNuovoCliente.setBounds(new Rectangle(390, 50, 82, 26));
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
	private IDJComboBox getCmbPagamento() {
		if (cmbPagamento == null) {
			cmbPagamento = new IDJComboBox();
			cmbPagamento.setBounds(new Rectangle(535, 93, 140, 26));
		}
		return cmbPagamento;
	}

	private void caricaClienti(){
//		Cliente c = new Cliente();
//		String tmpClienti[] = null;
//		String tmpCodici[] = null;
//		try {
//			cmbClienti.removeAllItems();
//			cmbClienti.addItem("");
//			String as[] = (String[]) c.allClienti();
//			tmpClienti = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpClienti[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbClienti).caricaIDAndOggetti(tmpCodici,
//					tmpClienti);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}

		Cliente c = new Cliente();
		try {

			String as[] = (String[]) c.allClienti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbClienti).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			messaggioCampoMancante("Errore caricamento fornitori nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbClienti);
	}

	private void caricaPagamento(){
		Pagamento p = new Pagamento();
		try {

			String ps[] = (String[]) p.allPagamenti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbPagamento).caricaNewValueComboBox(ps, true);
		} catch (SQLException e) {
			messaggioCampoMancante("Errore caricamento dati pagamento nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbPagamento);
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
//		for (int i = 0; i < carrello.size(); i++ ){
		for ( DettaglioScarico v : carrello ) {
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
	 * This method initializes jPanelOvest
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelOvest() {
		if (jPanelOvest == null) {
			jPanelOvest = new JPanel();
			jPanelOvest.setLayout(new BorderLayout());
			jPanelOvest.add(getJScrollPane1(), BorderLayout.CENTER);
			jPanelOvest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(0), "Documenti di Trasporto", 0,
					0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
		}
		return jPanelOvest;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTableDdt());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTableDdt
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTableDdt() {
		if (jTableDdt == null) {
			try {
				ddtModel = new DdtFatturaModel(dbm);
				jTableDdt = new JXTable(ddtModel);
				DBManager.getIstanceSingleton().addDBStateChange(ddtModel);
				jTableDdt.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						visualizzaDdt();
					}
				});
				TableColumn col=jTableDdt.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
				col = jTableDdt.getColumnModel().getColumn(1);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jTableDdt;
	}

	/**
	 * This method initializes txtSpeseIncasso
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtSpeseIncasso() {
		if (txtSpeseIncasso == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtSpeseIncasso = new JFormattedTextField(notaz);
			txtSpeseIncasso.setBounds(new Rectangle(125, 96, 60, 20));
		}
		return txtSpeseIncasso;
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
			txtSpeseTr.setBounds(new Rectangle(345, 96, 60, 20));
		}
		return txtSpeseTr;
	}
}