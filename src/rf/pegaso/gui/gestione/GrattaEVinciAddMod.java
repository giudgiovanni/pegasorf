/**
 *
 */
package rf.pegaso.gui.gestione;

import it.infolabs.hibernate.Articoli;
import it.infolabs.hibernate.ArticoliHome;
import it.infolabs.hibernate.FornitoriHome;
import it.infolabs.hibernate.RepartiHome;
import it.infolabs.hibernate.UmHome;
import it.infolabs.hibernate.exception.FindByNotFoundException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Carico;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.Reparto;
import rf.pegaso.db.tabelle.UnitaDiMisura;
import rf.pegaso.gui.utility.SuggerimentoCodice;
import rf.pegaso.gui.viste.ViewDocCarico;
import rf.utility.Constant;
import rf.utility.ControlloDati;
import rf.utility.MathUtility;
import rf.utility.db.DBManager;
import rf.utility.db.UtilityDBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;
import javax.swing.JRadioButton;

/**
 * @author Hunter
 *
 */
public class GrattaEVinciAddMod extends JFrame {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi) {
				dispose();
			}
			if (e.getSource() == btnInsMod) {
				if (modalita == MOD)
					modifica();
				else
					inserisci();
			} else if (e.getSource() == btnDocCarico) {
				apriDocCarico();
			}else if(e.getSource()==btnSuggerimento){
				suggerimentoCodice();
			} 
			else if ( e.getSource() == btnAddQtaIniziale ){
				inserisciQuantitaIniziale();
			}
			else if ( e.getSource() == rbtnSi ){
				txtCodBarre.setEditable(true);
				txtCodFornitore.setEditable(true);
				btnSuggerimento.setEnabled(true);
			}
			else if ( e.getSource() == rbtnNo ){
				txtCodBarre.setEditable(false);
				txtCodFornitore.setEditable(false);
				btnSuggerimento.setEnabled(false);
			}
		}

	}

	class MyFocusListener implements FocusListener {

		/*
		 * (non-Javadoc)
		 *
		 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
		 */
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
		 */
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtScortaMinima) {
				if (!ControlloDati.isNumero(txtScortaMinima.getText())) {
					messaggioCampoErrato("Scorta Minima Errato");
					((JTextField) e.getComponent()).setText("");
				}
			} 

		}

	}

	public static final int ADD = 0;
	public static final int MOD = 1;

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnDocCarico = null;

	private JButton btnInsMod = null;

	private DBManager dbm;

	private int idArticolo;

	private int idCliente;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane1 = null;

	private JTabbedPane jTabbedPane = null;

	private JLabel lblCodBarre = null;

	private JLabel lblCodFornitore = null;

	private JLabel lblDescrizione = null;

	private JLabel lblNota = null;

	private JLabel lblPrezzoIngrosso = null;

	private JLabel lblQta = null;

	private JLabel lblScortaMinima = null;
	
	private JLabel lblScortaMassima = null;

	private int modalita;

	private JPanel pnlCentrale = null;

	private JPanel pnlDatiPersonali = null;

	private JTextField txtCodBarre = null;

	private JTextField txtCodFornitore = null;

	private JTextField txtDescrizione = null;

	private JTextArea txtNote = null;
	private JFormattedTextField txtPrezzoListino = null;
	private JTextField txtQta = null;
	private JTextField txtScortaMinima = null;
	private JTextField txtScortaMassima = null;
	private String[] codFornitori;
	private String[] descFornitori;
	private String[] codReparti;
	private String[] descReparti;
	private String[] codUnitaDiMisura;
	private String[] descUnitaDiMisura;
	private JButton btnSuggerimento = null;
	private String[] ultimoArticolo;
	private boolean close=false;
	private JFormattedTextField txtFldQtaIniziale;
	private JLabel jLabel = null;
	private JButton btnAddQtaIniziale = null;
	private JLabel lblrButtonSiNo = null;
	private JLabel lblSi = null;
	private JRadioButton rbtnSi = null;
	private JLabel lblNo = null;
	private JRadioButton rbtnNo = null;
	private JLabel lblPrezzoAcquisto = null;
	private JFormattedTextField txtPrezzoAcquisto = null;
	/**
	 * @param owner
	 */
	public GrattaEVinciAddMod(JFrame owner, int modalita) {
		//super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		this.idArticolo = idArticolo;
		this.idCliente = idCliente;
		this.modalita = modalita;
		initialize();
	}

	public GrattaEVinciAddMod(JFrame owner, int idArticolo,int modalita) {
		//super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		this.idArticolo = idArticolo;
		this.idCliente = idCliente;
		this.modalita = modalita;
		this.ultimoArticolo=new String[1];
		initialize();
	}


	// in questo metodo costruttore passiamo oltre i vari parametri
	// che gi� abbiamo inpostato anche un terzo parametro che
	// ci serve per memorizzare l'ultimo codArticolo lavorato
	// questo viene particolarmente usato nel caso di un nuovo articolo
	// quando ci si trova nel carico merce.
	public GrattaEVinciAddMod(JFrame owner,int modalita, String[] codArticolo){
		//super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		this.idArticolo = idArticolo;
		this.idCliente = idCliente;
		this.modalita = modalita;
		initialize();
		this.ultimoArticolo=codArticolo;
	}

	// in questo metodo costruttore passiamo oltre i vari parametri
	// che gi� abbiamo inpostato anche un quarto parametro che
	// ci serve per memorizzare l'ultimo codArticolo lavorato
	// questo viene particolarmente usato nel caso di un nuovo articolo
	// quando ci si trova nel carico merce.
	public GrattaEVinciAddMod(JFrame owner,int idArticolo, int modalita, String[] codArticolo){
		//super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		this.idArticolo = idArticolo;
		this.idCliente = idCliente;
		this.modalita = modalita;
		initialize();
		this.ultimoArticolo=codArticolo;
	}



	public void suggerimentoCodice() {
		// usato per passaggio valore di riferimento
		String[] codice=new String[1];
		codice[0]="";
		SuggerimentoCodice s=new SuggerimentoCodice(this,dbm,codice);
		s.setVisible(true);
		txtCodBarre.setText(codice[0]);
	}

	// questo metodo permette di chiudere
	// se vero la finestra nel momento in cui si preme
	// il pulsante OK e viene appunto effettuata la modifica
	// o l'inserimento di  un articolo.
	public void setCloseOnOk(boolean close){
		this.close=close;
	}

	public boolean getCloseOnOk(){
		return this.close;
	}

	/**
	 *
	 */
	public void apriDocCarico() {
		ViewDocCarico view = new ViewDocCarico(this, dbm, idArticolo);
		view.setVisible(true);
	}

	/**
	 * @return the modalita
	 */
	public int getModalita() {
		return modalita;
	}

	public void messaggioCampoErrato(String messaggio) {
		JOptionPane.showMessageDialog(this, messaggio, "ERRORE",
				JOptionPane.ERROR_MESSAGE);
	}

	

	

	

	

	
	/**
	 * @param cmbFornitori2
	 */
	private void caricaFornitori(JComboBox cmbFornitori) {
		Fornitore f = new Fornitore();
		try {
			// cmbFornitori=new JComboBox(f.allFornitori());
			cmbFornitori.removeAllItems();
			cmbFornitori.addItem("");
			for (String fornitore : (String[]) f.allFornitori()) {
				cmbFornitori.addItem(fornitore);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento fornitori nel combobox", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 *
	 */
	private void caricaQtaMagazzino() {
		int qta = 0;
		try {
			qta = Articolo.caricaQtaMagazzino(idArticolo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		txtQta.setText(new Integer(qta).toString());

	}

	/**
	 * @param cmbReparto2
	 */
	private void caricaReparti(JComboBox cmbReparto) {
		Reparto f = new Reparto();
		try {

			cmbReparto.removeAllItems();
			cmbReparto.addItem("");
			for (String reparto : (String[]) f.allReparti()) {
				cmbReparto.addItem(reparto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento reparti nel combobox", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * @param cmbMisura2
	 */
	private void caricaUnitaMisura(JComboBox cmbMisura) {
		UnitaDiMisura f = new UnitaDiMisura();
		try {
			// cmbFornitori=new JComboBox(f.allFornitori());
			cmbMisura.removeAllItems();
			cmbMisura.addItem("");
			for (String misura : (String[]) f.allUnitaDiMisura()) {
				cmbMisura.addItem(misura);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento unita di misura nel combobox",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * @param string
	 * @return
	 */
	private int estraiCodice(String string) {
		int index = string.indexOf("-");
		if (index != -1) {
			string = string.substring(0, index - 1);
			return Integer.parseInt(string);
		}
		return 0;

	}

	/**
	 * This method initializes btnChiudi
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnChiudi() {
		if (btnChiudi == null) {
			try {
				btnChiudi = new JButton();
				btnChiudi.setText("Chiudi");
				btnChiudi.setPreferredSize(new Dimension(83, 26)); // Generated
				btnChiudi.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {

			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnDocCarico
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDocCarico() {
		if (btnDocCarico == null) {
			try {
				btnDocCarico = new JButton();
				btnDocCarico.setText("Doc. di Carico");
				btnDocCarico.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnDocCarico;
	}

	/**
	 * This method initializes btnInsMod
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInsMod() {
		if (btnInsMod == null) {
			try {
				btnInsMod = new JButton();
				if (modalita == MOD)
					btnInsMod.setText("OK");
				else
					btnInsMod.setText("OK");
				btnInsMod.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnInsMod;
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
			jContentPane.add(getPnlCentrale(), BorderLayout.CENTER); // Generated
			jContentPane.add(getJPanel(), BorderLayout.NORTH); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			try {
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(FlowLayout.LEFT); // Generated
				jPanel = new JPanel();
				jPanel.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel.setLayout(flowLayout); // Generated
				jPanel.setPreferredSize(new Dimension(0, 40)); // Generated
				jPanel.add(getBtnInsMod(), null); // Generated
				jPanel.add(getBtnChiudi(), null); // Generated
				jPanel.add(getBtnDocCarico(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel2
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			try {
				lblQta = new JLabel();
				lblQta.setBounds(new Rectangle(8, 24, 57, 21)); // Generated
				lblQta.setText("Quantit\340"); // Generated
				jPanel2 = new JPanel();
				jPanel2.setLayout(null); // Generated
				jPanel2.setBounds(new Rectangle(408, 106, 201, 65)); // Generated
				jPanel2.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Magazzino", TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new Font("Dialog",
								Font.BOLD, 12), new Color(51, 51, 51))); // Generated
				jPanel2.add(lblQta, null); // Generated
				jPanel2.add(getTxtQta(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			try {
				jScrollPane1 = new JScrollPane();
//				jScrollPane1.setBounds(new Rectangle(8, 233, 297, 41)); // Generated
				jScrollPane1.setBounds(new Rectangle(8, 192, 297, 41));
				jScrollPane1.setViewportView(getTxtNote()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			try {
				jTabbedPane = new JTabbedPane();
				jTabbedPane.addTab("Articolo", null, getPnlDatiPersonali(),
						null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlCentrale
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			try {
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new BorderLayout()); // Generated
				pnlCentrale.add(getJTabbedPane(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes pnlDatiPersonali
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDatiPersonali() {
		if (pnlDatiPersonali == null) {
			try {
				lblPrezzoAcquisto = new JLabel();
				lblPrezzoAcquisto.setBounds(new Rectangle(7, 94, 113, 16));
				lblPrezzoAcquisto.setText("Prezzo Acquisto");
				lblNo = new JLabel();
				lblNo.setBounds(new Rectangle(430, 27, 20, 16));
				lblNo.setText("No");
				lblSi = new JLabel();
				lblSi.setBounds(new Rectangle(370, 27, 20, 16));
				lblSi.setText("Si");
				lblrButtonSiNo = new JLabel();
				lblrButtonSiNo.setBounds(new Rectangle(370, 9, 86, 16));
				lblrButtonSiNo.setText("Modificabili");
				jLabel = new JLabel();
				jLabel.setBounds(new Rectangle(8, 176, 105, 16));
				jLabel.setText("Carico Iniziale");
				jLabel.setVisible(false);
				lblNota = new JLabel();
				lblNota.setText("Note"); // Generated
//				lblNota.setBounds(new Rectangle(8, 217, 26, 16)); // Generated
				lblNota.setBounds(new Rectangle(8, 176, 105, 16));
				lblScortaMinima = new JLabel();
				lblScortaMinima.setText("Scorta Minima"); // Generated
				lblScortaMinima.setBounds(new Rectangle(8, 135, 100, 16)); // Generated
				lblScortaMassima = new JLabel();
				lblScortaMassima.setText("Scorta Massima"); // Generated
				lblScortaMassima.setBounds(new Rectangle(140, 135, 100, 16)); // Generated
				lblPrezzoIngrosso = new JLabel();
				lblPrezzoIngrosso.setText("Prezzo di Listino"); // Generated
				lblPrezzoIngrosso.setBounds(new Rectangle(140, 94, 113, 16)); // Generated
				lblDescrizione = new JLabel();
				lblDescrizione.setText("Descrizione"); // Generated
				lblDescrizione.setBounds(new Rectangle(6, 51, 67, 16)); // Generated
				lblCodFornitore = new JLabel();
				lblCodFornitore.setText("Codice AAMS"); // Generated
				lblCodFornitore.setBounds(new Rectangle(205, 9, 93, 16)); // Generated
				lblCodBarre = new JLabel();
				lblCodBarre.setText("Codice Articolo"); // Generated
				lblCodBarre.setBounds(new Rectangle(6, 10, 96, 16)); // Generated
				pnlDatiPersonali = new JPanel();
				pnlDatiPersonali.setLayout(null); // Generated
				pnlDatiPersonali.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlDatiPersonali.add(getTxtCodBarre(), null); // Generated
				pnlDatiPersonali.add(getTxtCodFornitore(), null); // Generated
				pnlDatiPersonali.add(lblCodBarre, null); // Generated
				pnlDatiPersonali.add(lblCodFornitore, null); // Generated
				pnlDatiPersonali.add(lblDescrizione, null); // Generated
				pnlDatiPersonali.add(getTxtDescrizione(), null); // Generated
				pnlDatiPersonali.add(lblPrezzoIngrosso, null); // Generated
				pnlDatiPersonali.add(getTxtPrezzoIngrosso(), null); // Generated
				pnlDatiPersonali.add(lblScortaMinima, null); // Generated
				pnlDatiPersonali.add(lblScortaMassima, null); // Generated
				pnlDatiPersonali.add(getTxtScortaMinima(), null); // Generated
				pnlDatiPersonali.add(getTxtScortaMassima(), null); // Generated
				pnlDatiPersonali.add(lblNota, null); // Generated
				pnlDatiPersonali.add(getJScrollPane1(), null); // Generated
				pnlDatiPersonali.add(getJPanel2(), null); // Generated
				pnlDatiPersonali.add(getBtnSuggerimento(), null);  // Generated
				pnlDatiPersonali.add(jLabel, null);
				pnlDatiPersonali.add(getTxtFldQtaIniziale(), null);
				pnlDatiPersonali.add(getBtnAddQtaIniziale(), null);
				pnlDatiPersonali.add(lblrButtonSiNo, null);
				pnlDatiPersonali.add(lblSi, null);
				pnlDatiPersonali.add(getRbtnSi(), null);
				pnlDatiPersonali.add(lblNo, null);
				pnlDatiPersonali.add(getRbtnNo(), null);
				pnlDatiPersonali.add(lblPrezzoAcquisto, null);
				pnlDatiPersonali.add(getTxtPrezzoAcquisto(), null);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlDatiPersonali;
	}

	/**
	 * This method initializes txtCodBarre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodBarre() {
		if (txtCodBarre == null) {
			try {
				txtCodBarre = new JTextField();
				txtCodBarre.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCodBarre.setBounds(new Rectangle(6, 26, 154, 20)); // Generated
				txtCodBarre.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCodBarre;
	}

	/**
	 * This method initializes txtCodFornitore
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodFornitore() {
		if (txtCodFornitore == null) {
			try {
				txtCodFornitore = new JTextField();
				txtCodFornitore.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCodFornitore.setBounds(new Rectangle(205, 25, 154, 20)); // Generated
				txtCodFornitore.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCodFornitore;
	}

	/**
	 * This method initializes txtDescrizione
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescrizione() {
		if (txtDescrizione == null) {
			try {
				txtDescrizione = new JTextField();
				txtDescrizione.setPreferredSize(new Dimension(140, 20)); // Generated
				txtDescrizione.setBounds(new Rectangle(6, 67, 541, 20)); // Generated
				txtDescrizione.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtDescrizione;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote() {
		if (txtNote == null) {
			try {
				txtNote = new JTextArea();
				txtNote.setLineWrap(true);
				txtNote.setDocument(new UpperTextDocument());

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNote;
	}

	/**
	 * This method initializes txtPrezzoIngrosso
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPrezzoIngrosso() {
		if (txtPrezzoListino == null) {
			try {
				DecimalFormat formatPrice = new DecimalFormat();
				formatPrice.setMaximumFractionDigits(2);
				formatPrice.setMinimumFractionDigits(2);
				txtPrezzoListino = new JFormattedTextField(formatPrice);
				txtPrezzoListino.setPreferredSize(new Dimension(100, 20)); // Generated
				txtPrezzoListino.setBounds(new Rectangle(140, 110, 113, 20)); // Generated
				/*
				 * txtPrezzoListino.addFocusListener(new
				 * java.awt.event.FocusAdapter() { public void
				 * focusLost(java.awt.event.FocusEvent e) {
				 * calcoloPercentualeRicarico(); calcolaPrezzoPubblico(); } });
				 */
				txtPrezzoListino.setValue(new Double(0));
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtPrezzoListino;
	}

	/**
	 * This method initializes txtQta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtQta() {
		if (txtQta == null) {
			try {
				txtQta = new JTextField();
				txtQta.setBounds(new Rectangle(67, 24, 58, 20)); // Generated
				txtQta.setBackground(Color.green); // Generated
				txtQta.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtQta;
	}

	/**
	 * This method initializes txtScortaMinima
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtScortaMinima() {
		if (txtScortaMinima == null) {
			try {
				txtScortaMinima = new JTextField();
				txtScortaMinima.setPreferredSize(new Dimension(100, 20)); // Generated
				txtScortaMinima.setBounds(new Rectangle(8, 151, 100, 20)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtScortaMinima;
	}
	
	/**
	 * This method initializes txtScortaMassima
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtScortaMassima() {
		if (txtScortaMassima == null) {
			try {
				txtScortaMassima = new JTextField();
				txtScortaMassima.setPreferredSize(new Dimension(100, 20)); // Generated
				txtScortaMassima.setBounds(new Rectangle(140, 151, 100, 20)); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return txtScortaMassima;
	}

	private void impostaCampi(Articolo c) {
		Fornitore f = new Fornitore();
		UnitaDiMisura um = new UnitaDiMisura();
		Reparto r = new Reparto();
		try {
			f.caricaDati(c.getIdFornitore());
			um.caricaDati(c.getUm());
			r.caricaDati(c.getIdReparto());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore caricamento dati",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		ArticoliHome.getInstance().begin();
		Articoli articolo=ArticoliHome.getInstance().findById(c.getIdArticolo());
		this.txtCodBarre.setText(c.getCodBarre());
		this.txtCodFornitore.setText(c.getCodFornitore());
		this.txtDescrizione.setText(c.getDescrizione());
		this.txtNote.setText(c.getNote());
		// this.txtPrezzoAcquisto.setText(new
		// Double(c.getPrezzoAcquisto()).toString());

		// this.txtPrezzoListino.setText(ControlloDati.convertDoubleToPrezzo(c.getPrezzoIngrosso()));
		this.txtPrezzoListino.setValue(new Double(c.getPrezzoIngrosso()));
		this.txtPrezzoAcquisto.setValue(new Double(c.getPrezzoAcquisto()));
		// this.txtRicaricoDettaglio.setText(new
		// Integer(c.getRicaricoDettaglio()).toString());
		this.txtScortaMinima.setText(new Integer(c.getScortaMinima()).toString());
		this.txtScortaMassima.setText(new Integer(c.getScortaMassima()).toString());
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(636, 400);
		this.setResizable(false); // Generated

		this.setContentPane(getJContentPane());
		UtilGUI.centraFrame(this);
		// Modifichiamo l'editor e aggiorniamo
		// Combo box con i relativi dati dal DB
		
		inizializzaRadioButton();

		// se selezionata la modalit� modifica
		// carichiamo i cari dati negli oggetti
		if (modalita == MOD) {
			this.setTitle("Modifica Articolo Gratta e Vinci");
			Articolo c = new Articolo();
			try {
				c.caricaDati(this.idArticolo);
				ultimoArticolo[0]=c.getCodBarre();
				impostaCampi(c);
				caricaQtaMagazzino();
				// Verifichiamo se il codice a barre e' diverso da nullo rendiamo il campo editabile
				if ( c.getCodBarre() != null && !c.getCodBarre().trim().equals("") ){
					rbtnNo.setSelected(true);
					txtCodBarre.setEditable(false);
					txtCodFornitore.setEditable(false);
					btnSuggerimento.setEnabled(false);
				}
				else{
					rbtnSi.setSelected(true);
					txtCodBarre.setEditable(true);
					txtCodFornitore.setEditable(true);
					btnSuggerimento.setEnabled(true);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento dati DB", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		} else {
			this.setTitle("Inserisci Articoli");
			// Si tratta di un nuovo articolo quindi il codice dev'essere editabile
			rbtnSi.setSelected(true);
			txtCodBarre.setEditable(true);
			txtCodFornitore.setEditable(true);
			btnSuggerimento.setEnabled(true);
		}// fine impostazione tipo finestra

	}
	
	private void inizializzaRadioButton() {
		ButtonGroup g = new ButtonGroup();
		g.add(rbtnSi);
		g.add(rbtnNo);
	}

	private void inserisci() {
		try{
			Articoli a = new Articoli();
			boolean ok = recuperaDatiCampi(a);
			if (ok) {
				ArticoliHome.getInstance().begin();
				if ( !ArticoliHome.getInstance().codBarreEsistenteForInsert(a.getCodbarre()) ){
					ArticoliHome.getInstance().begin();
					a.setIdarticolo(dbm.getNewID("articoli","idarticolo"));
					ArticoliHome.getInstance().persist(a);
					ArticoliHome.getInstance().commit();
					svuotaCampi();
				}
				else {
					JOptionPane.showMessageDialog(this, "Codice a Barre gi\u00E0 presente in magazzino.",
							"ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// chiusura della finestra se selezionata
			//opzione closeOnOK
			if(this.close)
				dispose();
		}
		catch(FindByNotFoundException fe){
			JOptionPane.showMessageDialog(this, fe.toString(),
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			fe.printStackTrace();
		}
	}
	
	/**
	 * Metodo che carica la quantita' iniziale per un determinato articolo
	 * 
	 */
	private void inserisciQuantitaIniziale() {
		
		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(
					UtilityDBManager.INSERT);
		} catch (FileNotFoundException e1) {
			JOptionPane
					.showMessageDialog(
							this,
							"File di configurazione per backup\nmancante o danneggiato",
							"ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane
					.showMessageDialog(
							this,
							"File di configurazione per backup\nmancante o danneggiato",
							"ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		double tmp = ((Number) txtFldQtaIniziale.getValue()).doubleValue();
		
		if (tmp == 0.0) {
			messaggioCampoMancante("Inserire la quantit\340 iniziale da caricare.");
			return;
		}
		Carico c = new Carico();
		try {

			Properties props = new Properties();
			// Leggiamo le configurazioni
			props.load(new FileReader("carico.properties"));
			// Salviamo il nuovo id del carico iniziale
			int idCaricoIniziale = Integer.parseInt(props.getProperty("idcarico"));
			c.caricaDati(idCaricoIniziale);
			Articolo a = new Articolo();
			a.caricaDati(idArticolo);
			if ( c.codiceBarrePresenteInScarico(a.getCodBarre(), idCaricoIniziale)){
				Object [] obj = c.getQtaPrezzoArticoloCaricata(idArticolo);
				c.updateArticolo(idArticolo, tmp + (Integer)obj[0], (Double)obj[1]);
			}
			else {
				c.insertArticolo(idArticolo, tmp, a.getPrezzoDettaglio()-MathUtility.percentualeDaAggiungere(a.getPrezzoDettaglio(), 10));
			}
			caricaQtaMagazzino();
			txtFldQtaIniziale.setText("0,00");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Errore nell'inserimento dinumeri", "NUMERO ERRATO", 0);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Errore nel recupero del carico iniziale.", "FILE NON TROVATO", 0);
			e.printStackTrace();
		}

	}
	
	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO", 1);
	}

	private void modifica() {
		if (idArticolo <= 0)
			JOptionPane.showMessageDialog(this, "Codice idArticolo errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler modificare\nl'articolo selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		try{
			ArticoliHome.getInstance().begin();
			Articoli a = ArticoliHome.getInstance().findById(idArticolo);
			String oldCodBarre = a.getCodbarre();
			boolean ok = recuperaDatiCampi(a);
			if ( ok ){
				// Se il codice a barre e' stato modificato
				if ( !a.getCodbarre().equals(oldCodBarre) ){
					// Dobbiamo verificare se quel codice a barre e' utilizzabile
					if ( ArticoliHome.getInstance().codBarreEsistenteForUpdate(a.getCodbarre(), a.getIdarticolo()) ){
						// codice a barre non inseribile
						JOptionPane.showMessageDialog(this, "Codice a Barre gi\u00E0 presente in magazzino.",
								"ERRORE", JOptionPane.ERROR_MESSAGE);
					}
					else {
						// Possiamo persistere le modifiche
						ArticoliHome.getInstance().persist(a);
						ArticoliHome.getInstance().commit();
					}
				}
				// Il codice a barre non e' stato modificato quindi si puo' salvare
				else{
					ArticoliHome.getInstance().persist(a);
					ArticoliHome.getInstance().commit();
				}
			}
			// ultimo articolo appunto lavorato
			this.ultimoArticolo[0]=a.getCodbarre();
			this.dispose();
		}
		catch(FindByNotFoundException fe){
			JOptionPane.showMessageDialog(this, fe.toString(),
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			fe.printStackTrace();
		}
	}

	/**
	 * @throws FindByNotFoundException 
	 *
	 */
	private boolean recuperaDatiCampi(Articoli a) throws FindByNotFoundException {

		if ( txtCodBarre.getText().trim().equals("") || txtCodBarre.getText().length() < 4 ){
			JOptionPane.showMessageDialog(this, "Codice a Barre non valido.",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		a.setCodbarre(txtCodBarre.getText());
		a.setCodfornitore(txtCodFornitore.getText());
		
		// descrementiamo di uno perch� nel combobox � presente
		// anche un oggetto vuoto
		a.setUm(UmHome.getInstance().findById(Constant.UNITA_MISURA_PEZZI));

		a.setColore("");
		a.setDescrizione(txtDescrizione.getText());
		

		// Preleviamo il codice fornitore
		
		//Uno sta per monopoli di stato
		a.setFornitori(FornitoriHome.getInstance().findById(Constant.FORNITORE_TABACCHI));

		//il 3 sta per reparto generale
		a.setReparti(RepartiHome.getInstance().findById(Constant.REPARTO_GRATTA_E_VINCI));

		a.setImballo("");
		try {
			a.setPeso(0.0);
			if (txtPrezzoAcquisto.getText().equalsIgnoreCase("")) {
				a.setPrezzoAcquisto(0.0);
			} else {
				if (txtPrezzoAcquisto.getValue() instanceof Double) {
					a.setPrezzoAcquisto(((Double) txtPrezzoAcquisto.getValue())
							.doubleValue());
				} else {
					long value = ((Long) txtPrezzoAcquisto.getValue())
							.longValue();
					a.setPrezzoAcquisto(new Double(value).doubleValue());
				}
			}
			

			// a.setPrezzoDettaglio((ControlloDati.convertPrezzoToDouble(txtPrezzoDettaglio.getText())));
			if (txtPrezzoListino.getText().equalsIgnoreCase("")) {
				a.setPrezzoIngrosso(0.0);
				a.setPrezzoDettaglio(0.0);
			} else {
				if (txtPrezzoListino.getValue() instanceof Double) {
					a.setPrezzoIngrosso(((Double) txtPrezzoListino.getValue())
							.doubleValue());
					a.setPrezzoDettaglio(((Double) txtPrezzoListino.getValue())
							.doubleValue());
				} else {
					long value = ((Long) txtPrezzoListino.getValue())
							.longValue();
					a.setPrezzoIngrosso(new Double(value).doubleValue());
					a.setPrezzoDettaglio(new Double(value).doubleValue());
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Numero malformato",
					"NUMERO ERRATO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		// impostiamo sconto
		a.setSconto(0L);
		// impostiamo sconto
		if (txtScortaMinima.getText().equalsIgnoreCase(""))
			a.setScortaMinima(0L);
		else
			a.setScortaMinima(Long.parseLong((txtScortaMinima.getText())));
		if (txtScortaMassima.getText().equalsIgnoreCase(""))
			a.setScortaMassima(0L);
		else
			a.setScortaMassima(Long.parseLong((txtScortaMassima.getText())));
		// impostiamo sconto
		a.setCaricoIniziale(0L);
		a.setNote(txtNote.getText());
		return true;

	}

	/**
	 *
	 */
	private void svuotaCampi() {
		Component[] component = pnlDatiPersonali.getComponents();
		for (int i = 0; i < component.length; i++) {
			if (component[i] instanceof JTextField) {
				JTextField tmp = (JTextField) component[i];
				tmp.setText("");
			} else if (component[i] instanceof JComboBox) {
				JComboBox tmp = (JComboBox) component[i];
				tmp.setSelectedIndex(0);
			}
		}
		txtNote.setText("");

	}

	/**
	 * @param string
	 */
	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE",
				JOptionPane.ERROR_MESSAGE);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */



	/**
	 * This method initializes btnSuggerimento
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSuggerimento() {
		if (btnSuggerimento == null) {
			try {
				btnSuggerimento = new JButton();
				btnSuggerimento.setBounds(new Rectangle(161, 25, 37, 21));  // Generated
				btnSuggerimento.setText(". . .");
				btnSuggerimento.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnSuggerimento;
	}
	
	private JFormattedTextField getTxtFldQtaIniziale() {
		if (txtFldQtaIniziale == null)
			try {
				DecimalFormat formatPrice = new DecimalFormat();
				formatPrice.setMaximumFractionDigits(2);
				formatPrice.setMinimumFractionDigits(2);
				txtFldQtaIniziale = new JFormattedTextField(formatPrice);
				txtFldQtaIniziale.setBounds(new Rectangle(8, 192, 100, 20));
				txtFldQtaIniziale.setValue(0.0);
				txtFldQtaIniziale.setVisible(false);
				/*
				 * txtQta.addFocusListener(new FocusAdapter() {
				 *
				 * public void focusLost(FocusEvent e) { String numero =
				 * txtQta.getText(); if (!ControlloDati.isNumero(numero)) {
				 * txtQta.selectAll(); messaggioErroreCampo("Errore campo
				 * quantit\340"); } } });
				 */
			} catch (Throwable throwable) {
			}
		return txtFldQtaIniziale;
	}

	/**
	 * This method initializes btnAddQtaIniziale	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddQtaIniziale() {
		if (btnAddQtaIniziale == null) {
			btnAddQtaIniziale = new JButton();
			btnAddQtaIniziale.setBounds(new Rectangle(140, 188, 199, 29));
			btnAddQtaIniziale.setText("Aggiungi Al Carico Iniziale");
//			btnAddQtaIniziale.setVisible(modalita == MOD);
			btnAddQtaIniziale.setVisible(false);
			btnAddQtaIniziale.addActionListener(new MyActionListener());
		}
		return btnAddQtaIniziale;
	}

	/**
	 * This method initializes rbtnSi	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbtnSi() {
		if (rbtnSi == null) {
			rbtnSi = new JRadioButton();
			rbtnSi.setBounds(new Rectangle(390, 25, 20, 21));
			rbtnSi.addActionListener(new MyActionListener());
		}
		return rbtnSi;
	}

	/**
	 * This method initializes rbtnNo	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbtnNo() {
		if (rbtnNo == null) {
			rbtnNo = new JRadioButton();
			rbtnNo.setBounds(new Rectangle(450, 25, 21, 21));
			rbtnNo.addActionListener(new MyActionListener());
		}
		return rbtnNo;
	}

	/**
	 * This method initializes txtPrezzoAcquisto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPrezzoAcquisto() {
		if (txtPrezzoAcquisto == null) {
			try {
				DecimalFormat formatPrice = new DecimalFormat();
				formatPrice.setMaximumFractionDigits(2);
				formatPrice.setMinimumFractionDigits(2);
				txtPrezzoAcquisto = new JFormattedTextField(formatPrice);
				txtPrezzoAcquisto.setPreferredSize(new Dimension(100, 20)); // Generated
				txtPrezzoAcquisto.setBounds(new Rectangle(7, 110, 113, 20)); // Generated
				/*
				 * txtPrezzoListino.addFocusListener(new
				 * java.awt.event.FocusAdapter() { public void
				 * focusLost(java.awt.event.FocusEvent e) {
				 * calcoloPercentualeRicarico(); calcolaPrezzoPubblico(); } });
				 */
				txtPrezzoAcquisto.setValue(new Double(0));
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtPrezzoAcquisto;
	}

}  //  @jve:decl-index=0:visual-constraint="10,8"
