/**
 *
 */
package rf.pegaso.gui.gestione;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.BorderFactory;
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
import javax.swing.text.JTextComponent;

import rf.pegaso.db.exception.ResultSetVuoto;
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
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperTextDocument;
import rf.utility.number.Arrays;

/**
 * @author Hunter
 *
 */
public class TabacchiAddMod extends JFrame implements PropertyChangeListener {
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

	private JComboBox cmbMisura = null;

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

	private JLabel lblIva = null;

	private JLabel lblNota = null;

	private JLabel lblPrezzoIngrosso = null;

	private JLabel lblQta = null;

	private JLabel lblScortaMinima = null;
	
	private JLabel lblScortaMassima = null;

	private JLabel lblUnitaMisura = null;

	private int modalita;

	private JPanel pnlCentrale = null;

	private JPanel pnlDatiPersonali = null;

	private JTextField txtCodBarre = null;

	private JTextField txtCodFornitore = null;

	private JTextField txtDescrizione = null;

	private JTextField txtIva = null;
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
	/**
	 * @param owner
	 */
	public TabacchiAddMod(JFrame owner, int modalita) {
		//super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		this.idArticolo = idArticolo;
		this.idCliente = idCliente;
		this.modalita = modalita;
		initialize();
	}

	public TabacchiAddMod(JFrame owner, int idArticolo,int modalita) {
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
	public TabacchiAddMod(JFrame owner,int modalita, String[] codArticolo){
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
	public TabacchiAddMod(JFrame owner,int idArticolo, int modalita, String[] codArticolo){
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
	private void calcolaPrezzoPubblico() {
		int iva = 0;
		double pListino = 0.0;
		double sconto = 0.0;
		try {
			if (txtPrezzoListino.getText().equals("")) {
				pListino = ControlloDati.convertPrezzoToDouble("0.00");
			} else {
				if (txtPrezzoListino.getValue() instanceof Double) {
					pListino = ((Double) txtPrezzoListino.getValue())
							.doubleValue();
				} else {
					long value = ((Long) txtPrezzoListino.getValue())
							.longValue();
					pListino = new Double(value).doubleValue();
				}
			}
			// pListino = ((Double)txtPrezzoListino.getValue()).doubleValue();

			if (txtIva.getText().equals("")) {
				iva = 0;
			} else
				iva = Integer.parseInt(txtIva.getText());

			sconto=0;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Numero malformato",
					"NUMERO ERRATO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Campo prezzo errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}

		double ptot = pListino + (pListino / 100 * iva);
		ptot = ptot - (ptot / 100 * sconto);

	}



	/**
	 *
	 */
	public void apriDocCarico() {
		ViewDocCarico view = new ViewDocCarico(this, dbm, idArticolo);
		view.setVisible(true);

	}

	/**
	 *
	 */
	

	

	/**
	 *
	 */
	public void apriNuovoUM() {
		UnitaMisuraAdd add = new UnitaMisuraAdd(this, dbm);
		add.setVisible(true);
		caricaCmbUnitaDiMisura();
		// caricaUnitaMisura(cmbMisura);

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
	 * This method initializes cmbMisura
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbMisura() {
		if (cmbMisura == null) {
			try {
				cmbMisura = new JComboBox();
				cmbMisura.setPreferredSize(new Dimension(70, 25)); // Generated
				cmbMisura.setBounds(new Rectangle(281, 105, 70, 25)); // Generated
				// caricaUnitaMisura(cmbMisura);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbMisura;
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
				lblQta.setText("Quantit�"); // Generated
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
				jScrollPane1.setBounds(new Rectangle(8, 233, 297, 41)); // Generated
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
				jLabel = new JLabel();
				jLabel.setBounds(new Rectangle(8, 176, 105, 16));
				jLabel.setText("Carico Iniziale");
				lblNota = new JLabel();
				lblNota.setText("Note"); // Generated
				lblNota.setBounds(new Rectangle(8, 217, 26, 16)); // Generated
				lblScortaMinima = new JLabel();
				lblScortaMinima.setText("Scorta Minima"); // Generated
				lblScortaMinima.setBounds(new Rectangle(8, 135, 100, 16)); // Generated
				lblScortaMassima = new JLabel();
				lblScortaMassima.setText("Scorta Massima"); // Generated
				lblScortaMassima.setBounds(new Rectangle(140, 135, 100, 16)); // Generated
				lblUnitaMisura = new JLabel();
				lblUnitaMisura.setText("UM"); // Generated
				lblUnitaMisura.setBounds(new Rectangle(281, 89, 18, 16)); // Generated
				lblIva = new JLabel();
				lblIva.setText("% Iva"); // Generated
				lblIva.setBounds(new Rectangle(165, 91, 28, 16)); // Generated
				lblPrezzoIngrosso = new JLabel();
				lblPrezzoIngrosso.setText("Prezzo di Listino"); // Generated
				lblPrezzoIngrosso.setBounds(new Rectangle(7, 94, 113, 16)); // Generated
				lblDescrizione = new JLabel();
				lblDescrizione.setText("Descrizione"); // Generated
				lblDescrizione.setBounds(new Rectangle(6, 51, 67, 16)); // Generated
				lblCodFornitore = new JLabel();
				lblCodFornitore.setText("Codice Fornitore"); // Generated
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
				pnlDatiPersonali.add(lblIva, null); // Generated
				pnlDatiPersonali.add(getTxtIva(), null); // Generated
				pnlDatiPersonali.add(lblUnitaMisura, null); // Generated
				pnlDatiPersonali.add(getCmbMisura(), null); // Generated
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
	 * This method initializes txtIva
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtIva() {
		if (txtIva == null) {
			try {
				NumberFormat f = NumberFormat
						.getIntegerInstance(Locale.ITALIAN);
				f.setMaximumIntegerDigits(2);
				txtIva = new JFormattedTextField(f);
				txtIva.setPreferredSize(new Dimension(40, 20)); // Generated
				txtIva.setBounds(new Rectangle(165, 107, 40, 20)); // Generated
				txtIva.setText("0");
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtIva;
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
				txtPrezzoListino.setBounds(new Rectangle(7, 110, 113, 20)); // Generated
				/*
				 * txtPrezzoListino.addFocusListener(new
				 * java.awt.event.FocusAdapter() { public void
				 * focusLost(java.awt.event.FocusEvent e) {
				 * calcoloPercentualeRicarico(); calcolaPrezzoPubblico(); } });
				 */
				txtPrezzoListino.addPropertyChangeListener("value", this);
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

		
		this.txtCodBarre.setText(c.getCodBarre());
		this.txtCodFornitore.setText(c.getCodFornitore());
		
		this.txtDescrizione.setText(c.getDescrizione());
		this.txtIva.setText(new Integer(c.getIva()).toString());
		this.txtNote.setText(c.getNote());
		// this.txtPrezzoAcquisto.setText(new
		// Double(c.getPrezzoAcquisto()).toString());

		// this.txtPrezzoListino.setText(ControlloDati.convertDoubleToPrezzo(c.getPrezzoIngrosso()));
		this.txtPrezzoListino.setValue(new Double(c.getPrezzoIngrosso()));
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
		caricaComboBox();

		// se selezionata la modalit� modifica
		// carichiamo i cari dati negli oggetti
		if (modalita == MOD) {
			this.setTitle("Modifica Articolo Tabacchi");
			Articolo c = new Articolo();
			try {
				c.caricaDati(this.idArticolo);
				ultimoArticolo[0]=c.getCodBarre();
				impostaCampi(c);
				calcolaPrezzoPubblico();
				caricaQtaMagazzino();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento dati DB", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		} else {
			this.setTitle("Inserisci Articoli");
		}// fine impostazione tipo finestra

	}

	/**
	 *
	 */
	private void caricaComboBox() {
		caricaCmbUnitaDiMisura();

	}

	/**
	 *
	 */
	private void caricaCmbUnitaDiMisura() {
		cmbMisura.removeAllItems();
		UnitaDiMisura f = new UnitaDiMisura();
		String allUnita[] = null;

		try {
			allUnita = (String[]) f.allUnitaDiMisura();
		} catch (SQLException e2) {
			messaggioErroreCampo("Errore caricamento dati fornitori");
			e2.printStackTrace();
		}

		// questi due array li usiamo per tenere
		// traccia dei codici del fornitore in
		// base alla posizione che si trovano nel combo
		int size = allUnita.length;
		codUnitaDiMisura = new String[size];
		descUnitaDiMisura = new String[size];

		// Impostiamo e carichiamo i dati nel combobox
		cmbMisura.setEditable(true);
		cmbMisura.addItem("");
		for (int i = 0; i < size; i++) {
			String cod = allUnita[i].substring(0, allUnita[i].indexOf("-") - 1);
			String des = allUnita[i].substring(allUnita[i].indexOf("-") + 2);
			codUnitaDiMisura[i] = cod;
			descUnitaDiMisura[i] = des;
			cmbMisura.addItem(descUnitaDiMisura[i]);
		}
		// cambiamo l'edito del combo
		JTextComponent editor = (JTextComponent) cmbMisura.getEditor()
				.getEditorComponent();
		//new ComboBoxAutoComplete(cmbMisura);
		AutoCompletion.enable(cmbMisura);

	}

	/**
	 *
	 */
	

	

	private void inserisci() {
		Articolo a = new Articolo();
		boolean ok = recuperaDatiCampi(a);
		if (ok) {
			try {
				a.insertArticolo();
				idArticolo=a.getIdArticolo();
				inserisciQuantitaIniziale();
			} catch (IDNonValido e) {
				JOptionPane.showMessageDialog(this, "Valore idCliente errato",
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				try {
					e.printStackTrace(new PrintWriter(
							"inserimento_idnonvalido.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			svuotaCampi();
		}

		// chiusura della finestra se selezionata
		//opzione closeOnOK
		if(this.close)
			dispose();

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

			c.caricaDati(Constant.CARICO_INIZIALE);
			Articolo a = new Articolo();
			a.caricaDati(idArticolo);
			c.insertArticolo(idArticolo, tmp, a.getPrezzoDettaglio()-MathUtility.percentualeDaAggiungere(a.getPrezzoDettaglio(), 10));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Errore nell'inserimento dinumeri", "NUMERO ERRATO", 0);
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
		Articolo a = new Articolo();
		a.setIdArticolo(idArticolo);
		recuperaDatiCampi(a);
		try {
			a.updateArticolo();
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idFornitore errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		// ultimo articolo appunto lavorato
		this.ultimoArticolo[0]=a.getCodBarre();
		if(txtFldQtaIniziale.getText()!=""){
			inserisciQuantitaIniziale();
		}
		this.dispose();

	}

	/**
	 *
	 */
	private boolean recuperaDatiCampi(Articolo a) {

		a.setCodBarre(txtCodBarre.getText());
		a.setCodFornitore(txtCodFornitore.getText());
		// Controllo se � stato selezionato l'unit� di misura
		if (((String) cmbMisura.getSelectedItem()).equalsIgnoreCase("")) {
			messaggioCampoErrato("Selezionare l'unit� di misura");
			return false;
		}

		// Preleviamo il codice unit� di misura
		int pos = cmbMisura.getSelectedIndex();
		if (pos == 0)
			return false;
		// descrementiamo di uno perch� nel combobox � presente
		// anche un oggetto vuoto
		pos--;
		int cod = new Integer(codUnitaDiMisura[pos]);
		a.setCodiceUnitaDiMisura(cod);

		a.setColore("");
		a.setDescrizione(txtDescrizione.getText());
		

		// Preleviamo il codice fornitore
		
		//Uno sta per monopoli di stato
		a.setIdFornitore(Constant.FORNITORE_TABACCHI);

		//il 3 sta per reparto generale
		a.setIdReparto(Constant.REPARTO_TABACCHI);

		a.setImballo("");
		// impostiamoiva
		if (txtIva.getText().equalsIgnoreCase(""))
			a.setIva(0);
		else
			a.setIva(Integer.parseInt(txtIva.getText()));

		try {
			a.setPeso(0.0);
			a.setPrezzoAcquisto(0.00);
			

			// a.setPrezzoDettaglio((ControlloDati.convertPrezzoToDouble(txtPrezzoDettaglio.getText())));
			if (txtPrezzoListino.getText().equalsIgnoreCase("")) {
				a.setPrezzoIngrosso(0.0);
			} else {
				if (txtPrezzoListino.getValue() instanceof Double) {
					a.setPrezzoIngrosso(((Double) txtPrezzoListino.getValue())
							.doubleValue());
				} else {
					long value = ((Long) txtPrezzoListino.getValue())
							.longValue();
					a.setPrezzoIngrosso(new Double(value).doubleValue());
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Numero malformato",
					"NUMERO ERRATO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		// impostiamo sconto
		a.setSconto(0);
		// impostiamo sconto
		if (txtScortaMinima.getText().equalsIgnoreCase(""))
			a.setScortaMinima(0);
		else
			a.setScortaMinima(Integer.parseInt((txtScortaMinima.getText())));
		if (txtScortaMassima.getText().equalsIgnoreCase(""))
			a.setScortaMassima(0);
		else
			a.setScortaMassima(Integer.parseInt((txtScortaMassima.getText())));
		// impostiamo sconto
		a.setCaricoIniziale(0);
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

	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == txtPrezzoListino) {
			calcolaPrezzoPubblico();
		}  
	}

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

}  //  @jve:decl-index=0:visual-constraint="10,8"
