/**
 *
 */
package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.myswing.IDJComboBox;
import rf.myswing.exception.LunghezzeArrayDiverse;
import rf.myswing.util.ModalFrameUtil;
import rf.myswing.util.MyTableCellRendererAlignment;
import rf.myswing.util.QuantitaDisponibileEditorSQL;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.UtilityDBManager;
import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.model.ArticoliScaricatiViewModel;
import rf.pegaso.db.model.ScarichiViewModel;
import rf.pegaso.db.model.ScaricoModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.pegaso.gui.utility.ModificaQuantitaRiga;
import rf.utility.ControlloDati;
import rf.utility.db.DBEvent;
import rf.utility.db.DBStateChange;
import rf.utility.gui.ComboBoxUtil;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperAutoCompleteDocument;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

/**
 * @author Hunter
 *
 */
public class ScaricoGui extends JFrame implements TableModelListener {

	class MyButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 *
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnInserisci) {
				try {
					inserisci();
				} catch (Exception e1) {
					e1.printStackTrace();

				}

			} else if (e.getSource() == btnElimina) {
				elimina();
			} else if (e.getSource() == btnChiudi) {
				dispose();
			} else if (e.getSource() == btnApriArticoli) {
				apriGestioneArticoli();
			}

		}

	}

	class MyComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cmbClienti) {
				int idfornitore = ComboBoxUtil.estraiCodice((String) cmbClienti
						.getSelectedItem());
				caricaArticoliByIdFornitore(idfornitore);
				azzeraCampi();
			}
		}
	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnElimina = null;

	private JButton btnInserisci = null;

	private JCheckBox chkInsRapido = null;

	private JComboBox cmbClienti = null;

	private JComboBox cmbProdotti = null;

	private DBManager dbm;

	private int idcarico = 0;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JScrollPane jScrollPane1 = null;

	private JLabel lblCodBarre = null;

	private JLabel lblDataCarico = null;

	private JLabel lblDescrizioneProdotto = null;

	private JLabel lblFornitore = null;

	private JLabel lblIngImponibile = null;

	private JLabel lblIngImposta = null;

	private JLabel lblInsRapido = null;

	private JLabel lblNote = null;

	private JLabel lblNumeroCarico = null;

	private JLabel lblQta = null;

	private JLabel lblTotIng = null;

	private JLabel lblUm = null;

	private MyButtonListener myButtonListener; // @jve:decl-index=0:

	private MyComboBoxListener myComboBoxListener;

	private JPanel pnlNord = null;

	private JPanel pnlProdotto = null;

	private JPanel pnlSud = null;

	private JXTable tblScarico = null;

	private JTextField txtCodBarre = null;

	private JTextField txtImponibileIng = null;

	private JTextField txtImpostaIng = null;

	private JTextArea txtNote = null;

	private JTextField txtNumeroScarico = null;

	private JTextField txtQta = null;

	private JTextField txtTotaleIng = null;

	private JTextField txtUm = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel pnlScarico = null;

	private JPanel pnlViewScarichi = null;

	private JButton btnModifica = null;

	private JButton btnEliminaCarico = null;

	private JScrollPane jScrollPane2 = null;

	private JXTable tblViewScarichi = null;

	private JButton btnStampa = null;

	private JLabel lblTipoDocumento = null;

	private JLabel lblNumDocumento = null;

	private JTextField txtNumDocumento = null;

	private JLabel lblDataDocumento = null;

	private JDateChooser dataDocumento = null;

	private JDateChooser dataScarico = null;

	private JLabel jLabel = null;

	private Frame padre;

	private ScarichiViewModel scaricoViewModel;

	private ScaricoModel modello;

	private JPanel pnlArticoliScaricati = null;

	private JScrollPane jScrollPane3 = null;

	private JXTable tblArticoliScaricati = null;

	private ArticoliScaricatiViewModel articoliScaricatiView; // @jve:decl-index=0:

	private JPanel jPanel1 = null;

	private JLabel lblImponibile = null;

	private JTextField txtImponibileTot = null;

	private JLabel lblImpostaTot = null;

	private JTextField txtImpostaTot = null;

	private JLabel lblTot = null;

	private JTextField txtTot = null;

	private JPanel pnlBottoni = null;

	private JButton btnApriArticoli = null;

	/**
	 * @param frame
	 * @param dbm
	 */
	public ScaricoGui() {
		super();
		// setModale();
		this.dbm = DBManager.getIstanceSingleton();
		initialize();

	}

	public void modificaQta() {
		if (tblScarico.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblScarico.getSelectedRow();
		String codBarre = ((String) tblScarico.getValueAt(riga, 0));
		Long n = ((Long) tblScarico.getValueAt(riga, 4));
		int[] qta = new int[1];
		qta[0] = n.intValue();
		ModificaQuantitaRiga mod = new ModificaQuantitaRiga(qta, this);
		mod.setVisible(true);

		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
			Scarico c = new Scarico();
			c.caricaDati(new Integer(txtNumeroScarico.getText()).intValue());

			c.updateArticolo(a.getIdArticolo(), qta[0], 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// effettuiamo i calcoli
		calcoli(Scarico.getMaxID());

	}

	/**
	 *
	 */
	public void azzeraCampi() {
		txtCodBarre.setText("");
		txtUm.setText("");
		txtQta.setText("");
		cmbProdotti.setSelectedIndex(0);

	}

	/**
	 * @param idfornitore
	 */
	public void caricaArticoliByIdFornitore(int idfornitore) {
		Articolo f = new Articolo();
		try {
			cmbProdotti.removeAllItems();
			cmbProdotti.addItem("");
			for (String fornitore : f.allArticoliByFornitore(idfornitore)) {
				cmbProdotti.addItem(fornitore);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 *
	 */
	public void erroreCaricamentoDatiDB() {
		JOptionPane.showMessageDialog(this, "Errore caricamento dati db",
				"ERRORE", JOptionPane.ERROR_MESSAGE);

	}

	private void avvisoCodBarreInesistente() {
		JOptionPane.showMessageDialog(this,
				"Codice barre articolo inesistente", "Codice inesistente",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 *
	 */
	private void calcoli(int idScarico) {
		// Calcoliamo tutte le somme e impostiamo i campi
		// cominciamo prima a calcolare il dettaglio
		double imponibile;
		double imposta;
		double tot;
		int id = idScarico;

		// Calcoliamo ora la parte all'ingrosso
		try {

			imponibile = Scarico.getTotAcquistoImponibileByOrder(id);
			imposta = Scarico.getTotAcquistoImpostaByOrder(idScarico);
			tot = imponibile + imposta;

			// impostiamo i campi
			txtImponibileIng.setText(ControlloDati
					.convertDoubleToPrezzo(imponibile));
			txtImpostaIng.setText(ControlloDati.convertDoubleToPrezzo(imposta));
			txtTotaleIng.setText(ControlloDati.convertDoubleToPrezzo(tot));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Probabile errore nei calcoli all'ingrosso", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		// calcoliamo i totali di tutti gli articoli scaricati
		calcolaTotaliArticoliScaricati();

	}

	private void calcolaTotaliArticoliScaricati() {
		double imponibile = 0, imposta = 0, tot = 0;

		// Calcoliamo ora la parte totale dello scarico di tutti gli articoli.
		try {

			imponibile = Scarico.getTotAcquistoImponibileAllOrders();
			imposta = Scarico.getTotAcquistoImpostaAllOrders();
			tot = imponibile + imposta;

			// impostiamo i campi
			txtImponibileTot.setText(ControlloDati
					.convertDoubleToPrezzo(imponibile));
			txtImpostaTot.setText(ControlloDati.convertDoubleToPrezzo(imposta));
			txtTot.setText(ControlloDati.convertDoubleToPrezzo(tot));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Probabile errore nei calcoli all'ingrosso", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * @param cmbProdotti2
	 */
	private void caricaArticoli(JComboBox cmbProdotti) {
		// Articolo f = new Articolo();
		// String tmpArticoli[] = null;
		// String tmpCodici[] = null;
		// try {
		// cmbProdotti.removeAllItems();
		// cmbProdotti.addItem("");
		// String as[] = (String[]) f.allArticoli();
		// tmpArticoli = new String[as.length];
		// tmpCodici = new String[as.length];
		// // carichiamo tutti i dati in due array
		// // da passre al combobox
		// for (int i = 0; i < as.length; i++) {
		// String tmp[] = as[i].split("-",2);
		// tmpArticoli[i] = tmp[1].trim();
		// tmpCodici[i] = tmp[0].trim();
		// }
		// ((IDJComboBox) cmbProdotti).caricaIDAndOggetti(tmpCodici,
		// tmpArticoli);
		//
		// } catch (SQLException e) {
		// JOptionPane.showMessageDialog(this,
		// "Errore caricamento articoli nel combobox", "ERRORE", 0);
		// e.printStackTrace();
		// } catch (LunghezzeArrayDiverse e) {
		// JOptionPane.showMessageDialog(this, "Errore lunghezza array",
		// "ERRORE LUNGHEZZA", 0);
		// e.printStackTrace();
		// }
		// AutoCompletion.enable(cmbProdotti);

		Articolo f = new Articolo();
		try {

			String as[] = (String[]) f.allArticoli();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbProdotti).caricaNewValueComboBox(as, true);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbProdotti);

	}

	/**
	 *
	 */
	private void caricaArticoloByCodBarre() {
		String codBarre = txtCodBarre.getText();
		if (codBarre.equalsIgnoreCase(""))
			return;
		Articolo a = new Articolo();
		try {
			if (a.findByCodBarre(codBarre)) {
				Fornitore f = new Fornitore();
				f.caricaDati(a.getIdFornitore());
				cmbClienti.setSelectedItem(a.getIdFornitore() + " - "
						+ f.getNome());
				cmbProdotti.setSelectedItem(a.getDescrizione());
				txtUm.setText(new Integer(a.getUm()).toString());
				txtQta.setText(new Integer(1).toString());
				txtCodBarre.setText(codBarre);
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (CodiceBarreInesistente e1) {
			avvisoCodBarreInesistente();
			e1.printStackTrace();
		}

	}

	private void caricaClienti(JComboBox cmbFornitori) {

		// nello scarico manuale usiamo solo il cliente
		// banco come scarico in quanto il resto si deve fare
		// dalle vendite.
		cmbFornitori.removeAll();
		cmbFornitori.addItem("0 - BANCO");

	}

	/**
	 *
	 */
	private void caricaDatiArticolo() {
		int idarticolo = ComboBoxUtil.estraiCodice((String) cmbProdotti
				.getSelectedItem());
		// carichiamo i dati nel db
		Articolo a = new Articolo();
		try {
			a.caricaDati(idarticolo);

			// impostiamo i vari campi
			txtCodBarre.setText(a.getCodBarre());
			txtUm.setText(new Integer(a.getUm()).toString());
			txtQta.setText("1");
		} catch (SQLException e1) {
			erroreCaricamentoDatiDB();
			e1.printStackTrace();
		}

	}

	/**
	 *
	 */
	private void elimina() {
		if (tblScarico.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(
					UtilityDBManager.DELETE);
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

		int riga = tblScarico.getSelectedRow();
		String codBarre = ((String) tblScarico.getValueAt(riga, 1));
		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IDNonValido e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scarico c = new Scarico();
		c.setIdScarico(new Integer(txtNumeroScarico.getText()).intValue());
		try {
			c.deleteArticolo(a.getIdArticolo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calcoli(new Integer(txtNumeroScarico.getText()).intValue());

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
				btnChiudi.setBounds(new Rectangle(528, 4, 108, 29)); // Generated
				btnChiudi.setText("Chiudi (ESC)"); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnElimina
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			try {
				btnElimina = new JButton();
				btnElimina.setBounds(new Rectangle(535, 103, 126, 26)); // Generated
				btnElimina.setText("Elimina (F3)");
				// btnElimina.addActionListener(myButtonListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnElimina;
	}

	/**
	 * This method initializes btnInserisci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			try {
				btnInserisci = new JButton();
				btnInserisci.setBounds(new Rectangle(535, 37, 126, 26)); // Generated
				btnInserisci.setText("Inserisci (F1)"); // Generated
				// btnInserisci.addActionListener(myButtonListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnInserisci;
	}

	/**
	 * This method initializes chkInsRapido
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getChkInsRapido() {
		if (chkInsRapido == null) {
			try {
				chkInsRapido = new JCheckBox();
				chkInsRapido.setBounds(new Rectangle(437, 184, 23, 24)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return chkInsRapido;
	}

	/**
	 * This method initializes cmbClienti
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbClienti() {
		if (cmbClienti == null) {
			try {
				cmbClienti = new JComboBox();
				cmbClienti.setBounds(new Rectangle(68, 84, 521, 21)); // Generated
				// cmbClienti.addActionListener(new MyComboBoxListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbClienti;
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
				cmbProdotti.setBounds(new Rectangle(148, 40, 378, 21)); // Generated
				// cmbProdotti.addActionListener(new MyComboBoxListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbProdotti;
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
			jContentPane.setPreferredSize(new Dimension(700, 650)); // Generated
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			try {
				jScrollPane = new JScrollPane();
				jScrollPane.setPreferredSize(new Dimension(453, 300)); // Generated
				jScrollPane.setViewportView(getTblScarico()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
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
				jScrollPane1.setBounds(new Rectangle(8, 109, 371, 102)); // Generated
				jScrollPane1.setViewportView(getTxtNote()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes pnlNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord() {
		if (pnlNord == null) {
			try {
				jLabel = new JLabel();
				jLabel.setBounds(new Rectangle(112, 36, 241, 21)); // Generated
				jLabel.setText("Nessun documento - Scarico Manuale"); // Generated
				lblDataDocumento = new JLabel();
				lblDataDocumento.setBounds(new Rectangle(228, 60, 97, 21)); // Generated
				lblDataDocumento.setText("Data Documento"); // Generated
				lblNumDocumento = new JLabel();
				lblNumDocumento.setBounds(new Rectangle(8, 60, 89, 21)); // Generated
				lblNumDocumento.setText("N� Documento"); // Generated
				lblTipoDocumento = new JLabel();
				lblTipoDocumento.setBounds(new Rectangle(8, 36, 101, 21)); // Generated
				lblTipoDocumento.setText("Tipo Documento"); // Generated
				lblFornitore = new JLabel();
				lblFornitore.setBounds(new Rectangle(8, 84, 57, 21)); // Generated
				lblFornitore.setText("Cliente"); // Generated
				lblDataCarico = new JLabel();
				lblDataCarico.setBounds(new Rectangle(145, 8, 83, 25)); // Generated
				lblDataCarico.setText("Data Scarico"); // Generated
				lblNumeroCarico = new JLabel();
				lblNumeroCarico.setBounds(new Rectangle(9, 8, 65, 25)); // Generated
				lblNumeroCarico.setText("N� Scarico"); // Generated
				pnlNord = new JPanel();
				pnlNord.setLayout(null); // Generated
				pnlNord.setPreferredSize(new Dimension(1, 330)); // Generated
				pnlNord.add(lblNumeroCarico, null); // Generated
				pnlNord.add(getTxtNumeroScarico(), null); // Generated
				pnlNord.add(lblDataCarico, null); // Generated
				pnlNord.add(lblFornitore, null); // Generated
				pnlNord.add(getCmbClienti(), null); // Generated
				pnlNord.add(getPnlProdotto(), null); // Generated
				pnlNord.add(getBtnChiudi(), null); // Generated
				pnlNord.add(lblTipoDocumento, null); // Generated
				pnlNord.add(lblNumDocumento, null); // Generated
				pnlNord.add(getTxtNumDocumento(), null); // Generated
				pnlNord.add(lblDataDocumento, null); // Generated
				pnlNord.add(getDataDocumento(), null); // Generated
				pnlNord.add(getDataScarico(), null); // Generated
				pnlNord.add(jLabel, null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes pnlProdotto
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlProdotto() {
		if (pnlProdotto == null) {
			try {
				lblInsRapido = new JLabel();
				lblInsRapido.setBounds(new Rectangle(464, 184, 189, 25)); // Generated
				lblInsRapido.setText("Inserimento Rapido"); // Generated
				lblNote = new JLabel();
				lblNote.setBounds(new Rectangle(267, 88, 111, 20)); // Generated
				lblNote.setText("Note"); // Generated
				lblQta = new JLabel();
				lblQta.setBounds(new Rectangle(59, 65, 60, 16)); // Generated
				lblQta.setHorizontalTextPosition(SwingConstants.CENTER); // Generated
				lblQta.setHorizontalAlignment(SwingConstants.CENTER); // Generated
				lblQta.setText("qt�"); // Generated
				lblUm = new JLabel();
				lblUm.setBounds(new Rectangle(8, 65, 44, 16)); // Generated
				lblUm.setHorizontalAlignment(SwingConstants.CENTER); // Generated
				lblUm.setText("U.M."); // Generated
				lblDescrizioneProdotto = new JLabel();
				lblDescrizioneProdotto
						.setBounds(new Rectangle(148, 24, 82, 13)); // Generated
				lblDescrizioneProdotto.setText("Descrizione"); // Generated
				lblCodBarre = new JLabel();
				lblCodBarre.setBounds(new Rectangle(8, 24, 69, 13)); // Generated
				lblCodBarre.setText("Codice"); // Generated
				pnlProdotto = new JPanel();
				pnlProdotto.setLayout(null); // Generated
				pnlProdotto.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Dati prodotto", TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new Font("Dialog",
								Font.BOLD, 12), new Color(51, 51, 51))); // Generated
				pnlProdotto.setLocation(new Point(8, 108)); // Generated
				pnlProdotto.setSize(new Dimension(671, 218)); // Generated
				pnlProdotto.add(lblCodBarre, null); // Generated
				pnlProdotto.add(getTxtCodBarre(), null); // Generated
				pnlProdotto.add(lblDescrizioneProdotto, null); // Generated
				pnlProdotto.add(getCmbProdotti(), null); // Generated
				pnlProdotto.add(getBtnInserisci(), null); // Generated
				pnlProdotto.add(getTxtUm(), null); // Generated
				pnlProdotto.add(lblUm, null); // Generated
				pnlProdotto.add(lblQta, null); // Generated
				pnlProdotto.add(getTxtQta(), null); // Generated
				pnlProdotto.add(getBtnElimina(), null); // Generated
				pnlProdotto.add(getJScrollPane1(), null); // Generated
				pnlProdotto.add(lblNote, null); // Generated
				pnlProdotto.add(getChkInsRapido(), null); // Generated
				pnlProdotto.add(lblInsRapido, null); // Generated
				pnlProdotto.add(getBtnApriArticoli(), null);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlProdotto;
	}

	/**
	 * This method initializes pnlSud
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud() {
		if (pnlSud == null) {
			try {
				lblTotIng = new JLabel();
				lblTotIng.setBounds(new Rectangle(424, 8, 85, 25)); // Generated
				lblTotIng.setText("Totale �."); // Generated
				lblIngImposta = new JLabel();
				lblIngImposta.setBounds(new Rectangle(236, 8, 89, 25)); // Generated
				lblIngImposta.setText("Imposta �."); // Generated
				lblIngImponibile = new JLabel();
				lblIngImponibile.setBounds(new Rectangle(32, 8, 105, 25)); // Generated
				lblIngImponibile.setText("Imponibile �."); // Generated
				pnlSud = new JPanel();
				pnlSud.setLayout(null); // Generated
				pnlSud.setPreferredSize(new Dimension(0, 50)); // Generated
				pnlSud.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlSud.add(lblIngImponibile, null); // Generated
				pnlSud.add(getTxtImponibileIng(), null); // Generated
				pnlSud.add(lblIngImposta, null); // Generated
				pnlSud.add(getTxtImpostaIng(), null); // Generated
				pnlSud.add(lblTotIng, null); // Generated
				pnlSud.add(getTxtTotaleIng(), null); // Generated

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlSud;
	}

	/**
	 * This method initializes tblScarico
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblScarico() {
		if (tblScarico == null) {
			try {
				modello = new ScaricoModel(this.idcarico);
				modello.addTableModelListener(this);
				dbm.addDBStateChange(modello);
				tblScarico = new JXTable(modello);
				tblScarico
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// impostiamo l'editor di default per il controllo sulla
				// quantit�
				tblScarico.setDefaultEditor(Integer.class,
						new QuantitaDisponibileEditorSQL());
				// impostiamo il cell renderer per una impostazione centrale
				// tblScarico.setDefaultRenderer(String.class, new
				// MyTableCellRendererAlignment());

				// impostiamo le varie colonne
				TableColumn col = tblScarico.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);

				col = tblScarico.getColumn("codice");
				DefaultTableCellRenderer colFormatoRenderer = new DefaultTableCellRenderer();
				colFormatoRenderer.setHorizontalAlignment(JLabel.LEFT);
				col.setCellRenderer(colFormatoRenderer);

				col = tblScarico.getColumn("descrizione");
				DefaultTableCellRenderer ColTipoRenderer = new DefaultTableCellRenderer();
				ColTipoRenderer.setHorizontalAlignment(JLabel.LEFT);
				col.setCellRenderer(ColTipoRenderer);

				col = tblScarico.getColumn("iva");
				DefaultTableCellRenderer ivaColumnRenderer = new DefaultTableCellRenderer();
				ivaColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(ivaColumnRenderer);
				col.setPreferredWidth(40);

				col = tblScarico.getColumn("um");
				DefaultTableCellRenderer umColumnRenderer = new DefaultTableCellRenderer();
				umColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(umColumnRenderer);
				col.setPreferredWidth(40);

				col = tblScarico.getColumn("qta");
				DefaultTableCellRenderer qtaColumnRenderer = new DefaultTableCellRenderer();
				qtaColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(qtaColumnRenderer);
				col.setPreferredWidth(40);

				col = tblScarico.getColumn("disponibili");
				DefaultTableCellRenderer dispColumnRenderer = new DefaultTableCellRenderer();
				dispColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(dispColumnRenderer);
				col.setPreferredWidth(40);

				col = tblScarico.getColumn("prezzo_acquisto");
				DefaultTableCellRenderer prezzoColumnRenderer = new DefaultTableCellRenderer();
				prezzoColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);
				col.setCellRenderer(prezzoColumnRenderer);
				col.setPreferredWidth(40);

				tblScarico.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblScarico.packAll();
				// per evitare che si possano spostare le colonne dalla
				// posizione originaria
				tblScarico.getTableHeader().setReorderingAllowed(false);

			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblScarico;
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
				AutoCompleteTextComponent complete = new AutoCompleteTextComponent(
						txtCodBarre, dbm, "articoli", "codbarre");
				dbm.addDBStateChange(complete);

				txtCodBarre.setDocument(new UpperAutoCompleteDocument(complete,
						true));
				txtCodBarre.setBounds(new Rectangle(8, 40, 137, 21)); // Generated
				txtCodBarre.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						caricaArticoloByCodBarre();

					}
				});
				txtCodBarre.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							caricaArticoloByCodBarre();
							if (chkInsRapido.isSelected())
								inserisci();
						}
					}
				});

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCodBarre;
	}

	/**
	 * This method initializes txtImponibileIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileIng() {
		if (txtImponibileIng == null) {
			try {
				txtImponibileIng = new JTextField();
				txtImponibileIng.setBounds(new Rectangle(136, 8, 93, 25)); // Generated
				txtImponibileIng.setEditable(false); // Generated
				txtImponibileIng.setEnabled(false); // Generated
				txtImponibileIng.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImponibileIng.setDisabledTextColor(Color.black); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImponibileIng;
	}

	/**
	 * This method initializes txtImpostaIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImpostaIng() {
		if (txtImpostaIng == null) {
			try {
				txtImpostaIng = new JTextField();
				txtImpostaIng.setBounds(new Rectangle(324, 8, 93, 25)); // Generated
				txtImpostaIng.setEditable(false); // Generated
				txtImpostaIng.setEnabled(false); // Generated
				txtImpostaIng.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImpostaIng.setDisabledTextColor(Color.black); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImpostaIng;
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
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNote;
	}

	/**
	 * This method initializes txtNumeroScarico
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumeroScarico() {
		if (txtNumeroScarico == null) {
			try {
				txtNumeroScarico = new JTextField();
				txtNumeroScarico.setBounds(new Rectangle(77, 8, 65, 25)); // Generated
				txtNumeroScarico.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNumeroScarico;
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
				txtQta.setBounds(new Rectangle(59, 82, 60, 20)); // Generated
				txtQta.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						String numero = txtQta.getText();
						if (!ControlloDati.isNumero(numero)) {
							txtQta.selectAll();
							messaggioErroreCampo("Errore campo quantit�");
						}
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtQta;
	}

	/**
	 * This method initializes txtTotaleIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotaleIng() {
		if (txtTotaleIng == null) {
			try {
				txtTotaleIng = new JTextField();
				txtTotaleIng.setBounds(new Rectangle(508, 8, 93, 25)); // Generated
				txtTotaleIng.setEditable(false); // Generated
				txtTotaleIng.setEnabled(false); // Generated
				txtTotaleIng.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtTotaleIng.setDisabledTextColor(Color.red); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtTotaleIng;
	}

	/**
	 * This method initializes txtUm
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUm() {
		if (txtUm == null) {
			try {
				txtUm = new JTextField();
				txtUm.setBounds(new Rectangle(8, 82, 44, 20)); // Generated
				txtUm.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtUm;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {

		// impostiamo la finestra per ascoltare i tasti funzione da F1 in su
		// ed altri pulsanti
		InputMap im = this.getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");

		this.getRootPane().getActionMap().put("F1", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisci();
			}

		});
		this.getRootPane().getActionMap().put("F2", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				apriGestioneArticoli();
			}

		});
		this.getRootPane().getActionMap().put("F3", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				elimina();
			}

		});
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				dispose();
			}

		});


		Scarico c = new Scarico();
		this.idcarico = c.getNewID();
		this.setResizable(true); // Generated
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Generated
		this.setTitle("Scarico Merce");
		this.setResizable(true);
		this.setContentPane(getJContentPane());

		this.setSize(new Dimension(700, 591));
		UtilGUI.centraFrame(this);

		// Imposto il campo codice id che verr� poi inserito
		txtNumeroScarico.setText(new Integer(idcarico).toString());

		// carico tutti i dati nei combo box
		caricaClienti(this.cmbClienti);
		caricaArticoli(this.cmbProdotti);

		// inizializzo tutti i listener
		inizializzaListeners();

		// calcoliamo i totali di tutti gli articoli scaricati e
		// li inseriamo negli appositi textbox
		calcolaTotaliArticoliScaricati();

	}

	/**
	 *
	 */
	private void inizializzaListeners() {
		myComboBoxListener = new MyComboBoxListener();
		myButtonListener = new MyButtonListener();

		// aggiungo il listener nei suoi oggetti.
		cmbClienti.addActionListener(myComboBoxListener);
		btnInserisci.addActionListener(myButtonListener);
		btnElimina.addActionListener(myButtonListener);
		btnChiudi.addActionListener(myButtonListener);
		btnApriArticoli.addActionListener(myButtonListener);

	}

	/**
	 * @throws SQLException
	 * @throws NumberFormatException
	 *
	 */
	private void inserisci() {

		String codBarre = txtCodBarre.getText();
		if (codBarre.equalsIgnoreCase("")) {
			messaggioCampoMancante("Codice a barre non presente \nselezionare un prodotto");
			return;
		}

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

		Scarico c = new Scarico();
		try {
			c.setIdScarico(new Integer(txtNumeroScarico.getText()).intValue());
			c.setIdCliente(new Integer(ComboBoxUtil
					.estraiCodice((String) cmbClienti.getSelectedItem())));
			c
					.setDataScarico(new java.sql.Date(dataScarico.getDate()
							.getTime()));
			c.setOraScarico((new Time(dataScarico.getDate().getTime())));
			c.setNote(txtNote.getText());
			c.setDataDocumento(new java.sql.Date(dataDocumento.getDate()
					.getTime()));
			c.setNumDocumento(txtNumDocumento.getText());
			c.setIdDocumento(0);

			if (!c.isInsert(new Integer(txtNumeroScarico.getText()).intValue())) {

				c.insertScarico();
			} else {
				try {
					c.updateScarico();
				} catch (IDNonValido e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Articolo a = new Articolo();
			try {
				a.caricaDatiByCodBarre(txtCodBarre.getText());
			} catch (IDNonValido e) {
				e.printStackTrace();
			}
			c.setIdScarico(new Integer(txtNumeroScarico.getText()).intValue());
			if (Scarico.codiceBarrePresenteInScarico(txtCodBarre.getText(),
					Integer.parseInt(txtNumeroScarico.getText()))) {
				c
						.caricaDati(new Integer(txtNumeroScarico.getText())
								.intValue());
				int qta = 0;
				try {
					qta = c.getQuantitaScaricata(a.getIdArticolo());
				} catch (IDNonValido e) {
					JOptionPane.showMessageDialog(this,
							"Probabile Errore nel codice dell'Articolo",
							"ERRORE", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (ResultSetVuoto e) {
					JOptionPane
							.showMessageDialog(
									this,
									"Il ResultSet probabilmente non \ncontiene informazioni",
									"ERRORE", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				int giacenza = a.getGiacenza();
				int qta1 = Integer.parseInt(txtQta.getText());
				if ((giacenza - qta1) < 0) {
					JOptionPane.showMessageDialog(this,
							"Quantit� richiesta non disponibile\nDisponibilit� magazzino = "
									+ giacenza, "AVVISO",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					c.updateArticolo(a.getIdArticolo(), qta
							+ Integer.parseInt(txtQta.getText()), 0);
					// c.insertArticolo(a.getIdArticolo(),new
					// Integer(txtQta.getText()).intValue(),0);
				}

			} else {
				int giacenza = a.getGiacenza();
				int qta = Integer.parseInt(txtQta.getText());

				if ((giacenza - qta) < 0) {
					JOptionPane.showMessageDialog(this,
							"Quantit� non disponibile", "AVVISO",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					c.insertArticolo(a.getIdArticolo(), new Integer(txtQta
							.getText()).intValue(), 0);

				}

			}

			calcoli(c.getIdScarico());
			azzeraCampi();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO",
				JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * @param string
	 */
	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE",
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * @param text
	 */
	private boolean prezzoUguale(String pNuovo, int idArticolo) {
		Articolo a = new Articolo();
		try {
			a.caricaDati(idArticolo);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		double pAttuale = a.getPrezzoAcquisto();
		double prezzoNuovo = 0.0;
		try {
			prezzoNuovo = ControlloDati.convertPrezzoToDouble(pNuovo);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Numero malformato",
					"NUMERO ERRATO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Campo prezzo errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}
		if (pAttuale == prezzoNuovo)
			return true;
		return false;

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
				jTabbedPane
						.addTab("Scarico merce", null, getPnlScarico(), null); // Generated
				jTabbedPane.addTab("Visualizza scarichi", null,
						getPnlViewScarichi(), null); // Generated
				jTabbedPane.addTab("Visualizza Articoli Scaricati", null,
						getPnlArticoliScaricati(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlScarico
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlScarico() {
		if (pnlScarico == null) {
			try {
				pnlScarico = new JPanel();
				pnlScarico.setLayout(new BorderLayout()); // Generated
				pnlScarico.add(getPnlSud(), BorderLayout.SOUTH); // Generated
				pnlScarico.add(getJScrollPane(), BorderLayout.CENTER); // Generated
				pnlScarico.add(getPnlNord(), BorderLayout.NORTH); // Generated

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlScarico;
	}

	/**
	 * This method initializes pnlViewScarichi
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlViewScarichi() {
		if (pnlViewScarichi == null) {
			try {
				pnlViewScarichi = new JPanel();
				pnlViewScarichi.setLayout(new BorderLayout()); // Generated
				pnlViewScarichi.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlViewScarichi.add(getJScrollPane2(), BorderLayout.CENTER); // Generated
				pnlViewScarichi.add(getPnlBottoni(), BorderLayout.NORTH); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlViewScarichi;
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
				btnModifica
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								modifica();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnModifica;
	}

	protected void modifica() {
		if (tblViewScarichi.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this,
					"Selezionare uno scarico da modificare", "AVVISO",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare lo scarico selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblViewScarichi.getSelectedRow();
		int idcarico = ((Long) tblViewScarichi.getValueAt(riga, 0)).intValue();
		Scarico c = new Scarico();

		try {
			c.caricaDati(idcarico);
			caricaDati(c);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

	}

	private void caricaDati(Scarico c) {
		txtNumeroScarico.setText(new Integer(c.getIdScarico()).toString());
		dataScarico.setDate(c.getDataScarico());
		txtNumDocumento.setText(c.getNumDocumento());
		dataDocumento.setDate(c.getDataDocumento());
		ricaricaTableCarico(c.getIdScarico());
		jTabbedPane.setSelectedIndex(0);
		calcoli(c.getIdScarico());
	}

	private void ricaricaTableCarico(int idCarico) {

		try {
			modello.reloadModel(idCarico);
		} catch (SQLException e) {
			messaggioErroreCampo("Errore caricamento dati dal db");
			e.printStackTrace();
		}
		dbm.addDBStateChange(modello);
		tblScarico.setModel(modello);
		// TableColumn col=tblScarico.getColumnModel().getColumn(0);
		// col.setMinWidth(0);
		// col.setMaxWidth(0);
		// col.setPreferredWidth(0);
		tblScarico.packAll();

	}

	/**
	 * This method initializes btnEliminaCarico
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEliminaCarico() {
		if (btnEliminaCarico == null) {
			try {
				btnEliminaCarico = new JButton();
				btnEliminaCarico.setText("Elimina"); // Generated
				btnEliminaCarico
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								eliminaScarico();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnEliminaCarico;
	}

	protected void eliminaScarico() {
		if (tblViewScarichi.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare lo scarico selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(
					UtilityDBManager.DELETE);
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

		int riga = tblViewScarichi.getSelectedRow();
		int idscarico = ((Long) tblViewScarichi.getValueAt(riga, 0)).intValue();
		Scarico c = new Scarico();

		try {
			c.caricaDati(idscarico);
			c.deleteAllArticoliScaricati();
			c.deleteScarico(idscarico);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idCliente errato",
					"ERRORE", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		calcolaTotaliArticoliScaricati();

	}

	/**
	 * This method initializes jScrollPane2
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			try {
				jScrollPane2 = new JScrollPane();
				jScrollPane2.setViewportView(getTblViewScarichi()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes tblViewCarichi
	 *
	 * @return org.jdesktop.swingx.JXTable
	 */
	private JXTable getTblViewScarichi() {
		if (tblViewScarichi == null) {
			try {
				scaricoViewModel = new ScarichiViewModel(dbm);
				tblViewScarichi = new JXTable();
				tblViewScarichi
						.setAutoResizeMode(getTblViewScarichi().AUTO_RESIZE_ALL_COLUMNS); // Generated
				tblViewScarichi
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Generated
				// scrivere tutta la parte di codice per lo scarico e con il
				// controllo se la merce �
				// finita oppure no

				tblViewScarichi.setModel(scaricoViewModel);
				tblViewScarichi.packAll();
				dbm.addDBStateChange(scaricoViewModel);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return tblViewScarichi;
	}

	/**
	 * This method initializes btnStampa
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampa() {
		if (btnStampa == null) {
			try {
				btnStampa = new JButton();
				btnStampa.setEnabled(false); // Generated
				btnStampa.setText("Stampa"); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnStampa;
	}

	/**
	 * This method initializes txtNumDocumento
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumDocumento() {
		if (txtNumDocumento == null) {
			try {
				txtNumDocumento = new JTextField();
				txtNumDocumento.setBounds(new Rectangle(100, 60, 125, 21)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNumDocumento;
	}

	/**
	 * This method initializes dataDocumento
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataDocumento() {
		if (dataDocumento == null) {
			try {

				dataDocumento = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataDocumento.setDate(new Date());
				dataDocumento.setBounds(new Rectangle(328, 60, 117, 21)); // Generated
				JTextFieldDateEditor f = (JTextFieldDateEditor) dataDocumento
						.getDateEditor();
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent e) {
						JTextFieldDateEditor s = (JTextFieldDateEditor) dataDocumento
								.getDateEditor();
						s.setCaretPosition(0);
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return dataDocumento;
	}

	/**
	 * This method initializes dataScarico
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataScarico() {
		if (dataScarico == null) {
			try {
				dataScarico = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataScarico.setDate(new Date());
				dataScarico.setBounds(new Rectangle(232, 8, 121, 25)); // Generated
				JTextFieldDateEditor f = (JTextFieldDateEditor) dataScarico
						.getDateEditor();
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent e) {
						JTextFieldDateEditor s = (JTextFieldDateEditor) dataScarico
								.getDateEditor();
						s.setCaretPosition(0);
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return dataScarico;
	}

	/**
	 * This method initializes pnlArticoliScaricati
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlArticoliScaricati() {
		if (pnlArticoliScaricati == null) {
			try {
				pnlArticoliScaricati = new JPanel();
				pnlArticoliScaricati.setLayout(new BorderLayout()); // Generated
				pnlArticoliScaricati.setBorder(BorderFactory.createEmptyBorder(
						0, 0, 0, 0)); // Generated
				pnlArticoliScaricati
						.add(getJScrollPane3(), BorderLayout.CENTER); // Generated
				pnlArticoliScaricati.add(getJPanel1(), BorderLayout.SOUTH); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlArticoliScaricati;
	}

	/**
	 * This method initializes jScrollPane3
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			try {
				jScrollPane3 = new JScrollPane();
				jScrollPane3.setPreferredSize(new Dimension(100, 100)); // Generated
				jScrollPane3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
						0)); // Generated
				jScrollPane3.setViewportView(getTblArticoliScaricati()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes tblArticoliScaricati
	 *
	 * @return javax.swing.JTable
	 */
	private JXTable getTblArticoliScaricati() {
		if (tblArticoliScaricati == null) {
			try {
				articoliScaricatiView = new ArticoliScaricatiViewModel(dbm);
				tblArticoliScaricati = new JXTable();
				tblArticoliScaricati
						.setAutoResizeMode(JXTable.AUTO_RESIZE_ALL_COLUMNS); // Generated
				tblArticoliScaricati
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Generated

				// scrivere tutta la parte di codice per lo scarico e con il
				// controllo se la merce �
				// finita oppure no

				tblArticoliScaricati.setModel(articoliScaricatiView);
				tblArticoliScaricati.packAll();
				dbm.addDBStateChange(articoliScaricatiView);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return tblArticoliScaricati;
	}

	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			try {
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				gridBagConstraints5.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints5.gridx = 5; // Generated
				gridBagConstraints5.gridy = 0; // Generated
				gridBagConstraints5.weightx = 1.0; // Generated
				gridBagConstraints5.weighty = 0.0; // Generated
				gridBagConstraints5.fill = GridBagConstraints.VERTICAL; // Generated
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.anchor = GridBagConstraints.CENTER; // Generated
				gridBagConstraints4.gridx = 4; // Generated
				gridBagConstraints4.gridy = 0; // Generated
				gridBagConstraints4.insets = new Insets(0, 20, 0, 0); // Generated
				lblTot = new JLabel();
				lblTot.setText("Totale �."); // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints3.insets = new Insets(0, 2, 0, 0); // Generated
				gridBagConstraints3.gridx = 3; // Generated
				gridBagConstraints3.gridy = 0; // Generated
				gridBagConstraints3.weightx = 0.0; // Generated
				gridBagConstraints3.fill = GridBagConstraints.NONE; // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints2.gridx = 2; // Generated
				gridBagConstraints2.gridy = 0; // Generated
				gridBagConstraints2.insets = new Insets(0, 20, 0, 0); // Generated
				lblImpostaTot = new JLabel();
				lblImpostaTot.setText("Imposta tot. �."); // Generated
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints1.insets = new Insets(0, 2, 0, 0); // Generated
				gridBagConstraints1.gridx = 1; // Generated
				gridBagConstraints1.gridy = 0; // Generated
				gridBagConstraints1.ipadx = 0; // Generated
				gridBagConstraints1.weightx = 0.0; // Generated
				gridBagConstraints1.fill = GridBagConstraints.VERTICAL; // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(0, 10, 0, 0); // Generated
				gridBagConstraints.gridy = 0; // Generated
				lblImponibile = new JLabel();
				lblImponibile.setText("Imponibile tot. �."); // Generated
				jPanel1 = new JPanel();
				jPanel1.setLayout(new GridBagLayout()); // Generated
				jPanel1.setPreferredSize(new Dimension(0, 50)); // Generated
				jPanel1.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel1.add(lblImponibile, gridBagConstraints); // Generated
				jPanel1.add(getTxtImponibileTot(), gridBagConstraints1); // Generated
				jPanel1.add(lblImpostaTot, gridBagConstraints2); // Generated
				jPanel1.add(getTxtImpostaTot(), gridBagConstraints3); // Generated
				jPanel1.add(lblTot, gridBagConstraints4); // Generated
				jPanel1.add(getTxtTot(), gridBagConstraints5); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel1;
	}

	/**
	 * This method initializes txtImponibileTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileTot() {
		if (txtImponibileTot == null) {
			try {
				txtImponibileTot = new JTextField();
				txtImponibileTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtImponibileTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImponibileTot.setEnabled(false); // Generated
				txtImponibileTot.setDisabledTextColor(Color.black); // Generated
				txtImponibileTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImponibileTot;
	}

	/**
	 * This method initializes txtImpostaTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImpostaTot() {
		if (txtImpostaTot == null) {
			try {
				txtImpostaTot = new JTextField();
				txtImpostaTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtImpostaTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImpostaTot.setEnabled(false); // Generated
				txtImpostaTot.setDisabledTextColor(Color.black); // Generated
				txtImpostaTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImpostaTot;
	}

	/**
	 * This method initializes txtTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTot() {
		if (txtTot == null) {
			try {
				txtTot = new JTextField();
				txtTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtTot.setEnabled(false); // Generated
				txtTot.setDisabledTextColor(Color.red); // Generated
				txtTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtTot;
	}

	public void setModale() {
		// super.setModale();
		// addFocusListener(this);
	}

	/**
	 * This method initializes pnlBottoni
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBottoni() {
		if (pnlBottoni == null) {
			try {
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(FlowLayout.LEFT); // Generated
				pnlBottoni = new JPanel();
				pnlBottoni.setLayout(flowLayout); // Generated
				pnlBottoni.add(getBtnModifica(), null);
				pnlBottoni.add(getBtnStampa(), null); // Generated
				pnlBottoni.add(getBtnEliminaCarico(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlBottoni;
	}

	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		calcoli(this.idcarico);
	}

	private void apriGestioneArticoli() {
		ArticoliGestione ag = new ArticoliGestione();
		ag.setVisible(true);

	}

	/**
	 * This method initializes btnApriArticoli
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnApriArticoli() {
		if (btnApriArticoli == null) {
			btnApriArticoli = new JButton();
			btnApriArticoli.setBounds(new Rectangle(535, 70, 126, 26));
			btnApriArticoli.setText("Vis. Articoli (F2)");
		}
		return btnApriArticoli;
	}

} // @jve:decl-index=0:visual-constraint="10,10"