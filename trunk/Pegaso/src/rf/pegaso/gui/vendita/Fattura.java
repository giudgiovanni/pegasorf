package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import rf.myswing.IDJComboBox;
import rf.myswing.util.MyTableCellRendererCentral;
import rf.myswing.util.QuantitaDisponibileEditorSQL;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.pegaso.db.model.DdtFatturaModel;
import rf.pegaso.db.model.VenditeModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.Fornitore;
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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTable;
import javax.swing.JComboBox;

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
	private JPanel jPanelEst = null;
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
	private JComboBox cmbPagamento = null;
	private JLabel lblCodice = null;
	private JTextField txtCodice = null;
	private JComboBox cmbProdotti = null;
	private JLabel lblDescrizione = null;
	private JLabel lblQta = null;
	private JSpinField spinQta = null;
	private JButton btnInserisci = null;
	private JLabel lblUtile = null;
	private JTextField txtUtile = null;
	private Vector<Vendita> carrello = null;
	private Vector<String> colonne = null;  //  @jve:decl-index=0:
	private VenditeModel model = null;
	private double prezzoAcquisto = 0.00;
	private double prezzoVendita = 0.00;
	private int iva = 0;
	private DdtFatturaModel ddtModel = null;

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
		carrello = new Vector<Vendita>();
		colonne = new Vector<String>();
		caricaVettoreColonne();
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

		caricaClienti();
		caricaDescrizione();
		caricaVettoreColonne();
	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnChiudi )
				dispose();
			else if ( e.getSource() == btnSalva )
				salva();
			else if ( e.getSource() == btnStampa )
				stampa();
			else if ( e.getSource() == btnInserisci )
				inserisci();
			else if ( e.getSource() == btnElimina )
				deleteArticolo();
			else if ( e.getSource() == btnNuovoCliente ){
				nuovoCliente();
				caricaClienti();
			}
			else if ( e.getSource() == btnInserisciDdt )
				try {
					inserisciDdt();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}

	class MyTableModelListener implements TableModelListener{


		public void tableChanged(TableModelEvent arg0) {
			calcoliBarraInferiore();
		}
	}

	private void inserisciDdt() throws SQLException{
		if (jTableDdt.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare la righa da inserire",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = jTableDdt.getSelectedRow();
		Long idDdt = (Long) jTableDdt.getValueAt(riga, 0);
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from dettaglio_ddt where idddt=" + idDdt;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		while ( rs.next() ){
			Vendita v = new Vendita();
			Articolo a = new Articolo();
			a.caricaDati(rs.getInt(1));
			v.setCodiceArticolo(rs.getInt(1));
			v.setCodiceVendita(rs.getInt(2));
			v.setQta(rs.getLong(3));
			v.setPrezzoAcquisto(rs.getDouble(4));
			v.setPrezzoVendita(rs.getDouble(5));
			v.setIva(a.getIva());
			v.setDescrizione(a.getDescrizione());
			v.setCodiceBarre(a.getCodBarre());
			carrello.add(v);
			DBManager.getIstanceSingleton().notifyDBStateChange();
			calcoliBarraInferiore();
		}
		if (st != null)
			st.close();
		
	}
	
//	public void caricaDati(int id) throws SQLException {
//		Statement st = null;
//		ResultSet rs = null;
//		String query = "select * from dettaglio_ddt where idddt=" + id;
//		st = dbm.getNewStatement();
//		rs = st.executeQuery(query);
//		while ( rs.next() ){
//			Vendita v = new Vendita();
//			v.setCodiceArticolo(rs.getInt(1));
//			v.setCodiceVendita(rs.getInt(2));
//			v.setQta(rs.getLong(3));
//			v.setPrezzoAcquisto(rs.getDouble(4));
//			v.setPrezzoVendita(rs.getDouble(5));
//			carrello.add(v);
//		}
//		if (st != null)
//			st.close();
//	}

	/**
	 * Questo metodo inscerisce gli articoli all'interno del carrello
	 *
	 */
	private void inserisci() {
		Vendita v  = new Vendita();
		Articolo a = new Articolo();

		try {
			a.caricaDatiByCodBarre(txtCodice.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (IDNonValido e) {
			e.printStackTrace();
		}
		try{
			if ( a.getGiacenza() < spinQta.getValue() ){
				JOptionPane.showMessageDialog(this,
						"Quantità richiesta non disponibile\nDisponibilità magazzino = "+a.getGiacenza(), "AVVISO",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		v.setCodiceArticolo(a.getIdArticolo());
		for ( Vendita v1 : carrello){
			if ( v1.getCodiceArticolo() == v.getCodiceArticolo() )
				try{
					if ( a.getGiacenza() < (spinQta.getValue() + v1.getQta()) ){
						JOptionPane.showMessageDialog(this,
								"Quantità richiesta non disponibile\nDisponibilità magazzino = "+a.getGiacenza(), "AVVISO",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					else{
						long oldQta = v1.getQta();
						v1.setQta(oldQta + spinQta.getValue());
						dbm.notifyDBStateChange();
						return;
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		v.setCodiceBarre(txtCodice.getText());
		v.setCodiceVendita(dbm.getNewID("fattura", "idfattura"));
		v.setDescrizione(String.valueOf(cmbProdotti.getSelectedItem()));
		v.setQta(Long.valueOf(spinQta.getValue()));
		v.setPrezzoAcquisto(prezzoAcquisto);
		v.setPrezzoVendita(prezzoVendita);
		v.setIva(iva);
		carrello.add(v);
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
			jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
			jContentPane.add(getJPanelEst(), BorderLayout.EAST);
			jContentPane.add(getJPanelSud(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelOvest(), BorderLayout.WEST);
			jContentPane.add(getJPanelCentro(), BorderLayout.CENTER);
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
			lblQta = new JLabel();
			lblQta.setBounds(new Rectangle(555, 100, 55, 16));
			lblQta.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblQta.setText("Quantita'");
			lblDescrizione = new JLabel();
			lblDescrizione.setBounds(new Rectangle(155, 100, 75, 16));
			lblDescrizione.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDescrizione.setText("Descrizione");
			lblCodice = new JLabel();
			lblCodice.setBounds(new Rectangle(15, 100, 45, 16));
			lblCodice.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCodice.setText("Codice");
			lblPagamento = new JLabel();
			lblPagamento.setBounds(new Rectangle(505, 55, 70, 16));
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
			jPanelNord.setPreferredSize(new Dimension(0, 170)); // Generated
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(getBtnChiudi(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getBtnStampa(), null);
			jPanelNord.add(lblCodice, null);
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
			jPanelNord.add(getTxtCodice(), null);
			jPanelNord.add(getCmbProdotti(), null);
			jPanelNord.add(lblDescrizione, null);
			jPanelNord.add(lblQta, null);
			jPanelNord.add(getSpinQta(), null);
			jPanelNord.add(getBtnInserisci(), null);
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
	 * This method initializes jPanelEst
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelEst() {
		if (jPanelEst == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			jPanelEst = new JPanel();
			jPanelEst.setLayout(new GridBagLayout());
			jPanelEst.add(getJScrollPane(), gridBagConstraints);
		}
		return jPanelEst;
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
				jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				jTable.setDefaultEditor(Long.class, new QuantitaDisponibileEditorSQL());
				jTable.setDefaultRenderer(Object.class, new MyTableCellRendererCentral());
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
		String num_fattura = txtNumero.getText();
		if (num_fattura.equalsIgnoreCase("")) {
			messaggioCampoMancante("Numero Fattura non presente.");
			return;
		}
		PreparedStatement pst = null;
		int idfattura = dbm.getNewID("fattura", "idfattura");
		String insertF = "insert into fattura values (?,?,?,?,?,?)";
		pst = dbm.getNewPreparedStatement(insertF);
		java.sql.Date d = new java.sql.Date(dataCorrente.getDate().getTime());
		java.sql.Time t = new Time(dataCorrente.getDate().getTime());
		try {
			pst.setInt(1, idfattura);
			pst.setString(2, num_fattura);
			pst.setDate(3, d);
			pst.setTime(4, t);
			pst.setInt(5, 1);
			System.out.println((String)cmbClienti.getIDSelectedItem());
			pst.setString(6, (String)cmbPagamento.getSelectedItem());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//salviamo i dettagli della fattura
		String insertD = "insert into dettaglio_fattura values (?,?,?,?,?)";
		pst = dbm.getNewPreparedStatement(insertD);
		try {
			for (Vendita v : carrello) {
				pst.setInt(1, v.getCodiceArticolo());
				pst.setInt(2, idfattura);
				pst.setLong(3, v.getQta());
				pst.setDouble(4, v.getPrezzoAcquisto());
				pst.setDouble(5, v.getPrezzoVendita());

				pst.executeUpdate();
				//Articolo c = new Articolo();
				//c.caricaDati(v.getCodiceArticolo());
				updateArticolo(v.getCodiceArticolo(), (int) v.getQta());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		try {
			if (pst != null)
				pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	dbm.notifyDBStateChange();

	}

	public void updateArticolo(int idArticolo, int qta)
	throws SQLException {

		String query = "update articoli set qta=? where idarticolo=?";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);

		pst.setInt(1, qta);
		pst.setInt(2, idArticolo);

		// inserimento
		pst.executeUpdate();

		if (pst != null)
			pst.close();
		dbm.notifyDBStateChange();
	}

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
	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO",
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
	private JComboBox getCmbPagamento() {
		if (cmbPagamento == null) {
			Vector<String> v = new Vector<String>();
			v.add("");
			v.add("Per Contanti");
			v.add("Assegno Circolare");
			v.add("Assegno Post-Datato");
			cmbPagamento = new JComboBox(v);
			cmbPagamento.setBounds(new Rectangle(595, 50, 140, 26));
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
			JOptionPane.showMessageDialog(this,
					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbClienti);
	}

	private void caricaDescrizione(){
//		Articolo a = new Articolo();
//		String tmpArticoli[] = null;
//		String tmpCodici[] = null;
//		try {
//			cmbProdotti.removeAllItems();
//			cmbProdotti.addItem("");
//			String as[] = (String[]) a.allArticoli();
//			tmpArticoli = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpArticoli[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbProdotti).caricaIDAndOggetti(tmpCodici,
//					tmpArticoli);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}

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
				txtCodice.setBounds(new Rectangle(15, 120, 140, 24)); // Generated
				txtCodice.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						caricaArticoloByCodBarre(txtCodice.getText());

					}
				});
				txtCodice.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							caricaArticoloByCodBarre(txtCodice.getText());
								//inserisci();
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
	private void caricaArticoloByCodBarre(String cod) {
		String codBarre = txtCodice.getText();
		if (cod.equalsIgnoreCase(""))
			return;
		Articolo a = new Articolo();
		try {
			if (a.findByCodBarre(cod)) {
				Fornitore f = new Fornitore();
				f.caricaDati(a.getIdFornitore());
				cmbProdotti.setSelectedItem(a.getDescrizione());
				//txtUm.setText(new Integer(a.getUm()).toString());
				prezzoAcquisto = a.getPrezzoAcquisto();
				prezzoVendita = a.getPrezzoIngrosso();
				spinQta.setValue(1);
				txtCodice.setText(codBarre);
				iva = a.getIva();
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (CodiceBarreInesistente e1) {
			avvisoCodBarreInesistente();
			e1.printStackTrace();
		}

	}

	/**
	 *
	 */
	private void caricaArticoloByID(int cod) {
		if ( cod == 0 )
			return;
		Articolo a = new Articolo();
		try {
			a.caricaDati(cod);
			prezzoAcquisto = a.getPrezzoAcquisto();
			prezzoVendita = a.getPrezzoIngrosso();
			spinQta.setValue(1);
			txtCodice.setText(a.getCodBarre());
			iva = a.getIva();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	private void avvisoCodBarreInesistente() {
		JOptionPane.showMessageDialog(this,
				"Codice barre articolo inesistente", "Codice inesistente",
				JOptionPane.INFORMATION_MESSAGE);
	}

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
							System.out.println("ok");
							caricaArticoloByID(id);
						}
					}
				});
			} catch (java.lang.Throwable e) {
			}
		}
		return cmbProdotti;
	}

	private JSpinField getSpinQta() {
		if( spinQta == null ) {
			try {
				spinQta = new JSpinField();
				spinQta.setBounds(new Rectangle(555, 120, 40, 23));
			}
			catch (java.lang.Throwable e) {
			}
		}
		return spinQta;
	}

	/**
	 * This method initializes btnInserisci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			btnInserisci = new JButton();
			btnInserisci.setBounds(new Rectangle(637, 118, 90, 26));
			btnInserisci.setText("Inserisci");
			btnInserisci.addActionListener(new MyButtonListener());
		}
		return btnInserisci;
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

	private double utile = 0.00;
	private int scontoTotale = 0;
	private double imponibile = 0.00;
	private double imposta = 0.00;
	//private double totale = 0.00;
	private JButton btnElimina = null;
	private JPanel jPanelOvest = null;
	private JScrollPane jScrollPane1 = null;
	private JXTable jTableDdt = null;
	private JPanel jPanelCentro = null;
	private JButton btnInserisciDdt = null;

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
			Vendita v = (Vendita)carrello.get(i);
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
	 * This method initializes jPanelOvest
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelOvest() {
		if (jPanelOvest == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.weightx = 1.0;
			jPanelOvest = new JPanel();
			jPanelOvest.setLayout(new GridBagLayout());
			jPanelOvest.add(getJScrollPane1(), gridBagConstraints1);
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
				TableColumn col=jTableDdt.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
				col = jTableDdt.getColumnModel().getColumn(1);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jTableDdt;
	}

	/**
	 * This method initializes jPanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCentro() {
		if (jPanelCentro == null) {
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(null);
			jPanelCentro.setPreferredSize(new Dimension(100,200));
			jPanelCentro.add(getBtnInserisciDdt(), null);
		}
		return jPanelCentro;
	}

	/**
	 * This method initializes btnInserisciDdt
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisciDdt() {
		if (btnInserisciDdt == null) {
			btnInserisciDdt = new JButton();
			btnInserisciDdt.setPreferredSize(new Dimension(90,26));
			btnInserisciDdt.setBounds(new Rectangle(0, 176, 90, 26));
			btnInserisciDdt.setText("Aggiungi");
			btnInserisciDdt.addActionListener(new MyButtonListener());
		}
		return btnInserisciDdt;
	}
}