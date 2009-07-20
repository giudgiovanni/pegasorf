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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;

import rf.myswing.IDJComboBox;
import rf.myswing.util.QuantitaEditorSql;
import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.model.ArticoliScaricatiViewModel;
import rf.pegaso.db.model.ScarichiViewModel;
import rf.pegaso.db.model.ScaricoModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.exception.NumeroOrdineEsistente;
import rf.pegaso.gui.utility.ModificaQuantitaRiga;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.db.UtilityDBManager;
import rf.utility.db.eccezzioni.IDNonValido;
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
public class VenditeGui extends JFrame implements TableModelListener {

	

	

	
	
	private static final long serialVersionUID = 1L;

	private DBManager dbm;

	private int idcarico = 0;

	private JPanel jContentPane = null;


	private JTabbedPane jTabbedPane = null;

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

	//cliente banco per scarichi manuali
	private int idCliente=0;
	private JTextField txtImpostaTot = null;

	private JLabel lblTot = null;

	private JTextField txtTot = null;


	/**
	 * @param frame
	 * @param dbm
	 */
	public VenditeGui() {
		super();
		// setModale();
		this.dbm = DBManager.getIstanceSingleton();
		initialize();

	}

	



	/**
	 * @param idfornitore
	 */
	
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

		
		this.getRootPane().getActionMap().put("F2", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				apriGestioneArticoli();
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
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setTitle("Scarico Merce");
		this.setResizable(true);
		this.setContentPane(getJContentPane());

		this.setSize(new Dimension(700, 591));
		UtilGUI.centraFrame(this);


		// calcoliamo i totali di tutti gli articoli scaricati e
		// li inseriamo negli appositi textbox
		calcolaTotaliArticoliScaricati();

	}

	

	/**
	 * @throws SQLException
	 * @throws NumberFormatException
	 *
	 */
	

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
				jTabbedPane.addTab("Visualizza Articoli Scaricati", null,
						getPnlArticoliScaricati(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTabbedPane;
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
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblArticoliScaricati.setHighlighters(new AlternateRowHighlighter());// Generated

				// scrivere tutta la parte di codice per lo scarico e con il
				// controllo se la merce è
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
				lblTot.setText("Totale \u20AC"); // Generated
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
				lblImpostaTot.setText("Imposta tot. \u20AC"); // Generated
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
				lblImponibile.setText("Imponibile tot. \u20AC"); // Generated
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

	private void apriGestioneArticoli() {
		ArticoliGestione ag = new ArticoliGestione();
		ag.setVisible(true);

	}





	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

} // @jve:decl-index=0:visual-constraint="10,10"
