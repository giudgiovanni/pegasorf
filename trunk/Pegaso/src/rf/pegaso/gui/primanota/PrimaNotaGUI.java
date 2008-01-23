package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
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
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.myswing.IDJComboBox;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.UtilityDBManager;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.model.MovimentiContoModel;
import rf.pegaso.db.model.PrimaNotaModelUscite;
import rf.pegaso.db.model.PrimanotaModelEntrate;
import rf.pegaso.db.tabelle.Banca;
import rf.pegaso.db.tabelle.Carico;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.ContoCorrente;
import rf.pegaso.db.tabelle.Documento;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.MovimentoBanca;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.pegaso.gui.gestione.DocumentoAddMod;
import rf.utility.ControlloDati;
import rf.utility.date.DateManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperTextDocument;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class PrimaNotaGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlCentrale = null;

	private JPanel pnlNord = null;

	private JPanel pnlCentro = null;

	private JPanel pnlCentro1 = null;

	private JButton btnInserisci = null;

	private JButton Modifica = null;

	private JButton btnElimina = null;

	private JButton btnEsci = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel pnlEntrate = null;

	private JPanel pnlUscite = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblEntrate = null;

	private PrimanotaModelEntrate modello;

	private DecimalFormat formatPrice = null;

	private JScrollPane jScrollPane2 = null;

	private JXTable tblUscite = null;

	private PrimaNotaModelUscite modelloUscite = null;

	private JPanel pnlNord1 = null;

	private JDateChooser dataDocumento = null;

	private JLabel lblDataDocumento = null;

	private JLabel lblTipoDocumento = null;

	private IDJComboBox cmbTipoDocumento = null;

	private JLabel lblCliente = null;

	private IDJComboBox cmbClienti = null;

	private JPanel pnlNote = null;

	private JScrollPane jScrollPane1 = null;

	private JTextArea txtNote = null;

	private JLabel lblTot = null;

	private JFormattedTextField txtTotale = null;

	private DecimalFormat formatPrice1 = null;

	private JLabel lblNumDocumento = null;

	private JTextField txtNumDocumento = null;

	private JPanel pnlNord11 = null;

	private JDateChooser dataDocumento1 = null;

	private JLabel lblDataDocumento1 = null;

	private JLabel lblTipoDocumento1 = null;

	private IDJComboBox cmbTipoDocumento1 = null;

	private JLabel lblCliente1 = null;

	private IDJComboBox cmbClienti1 = null;

	private JPanel pnlNote1 = null;

	private JScrollPane jScrollPane11 = null;

	private JTextArea txtNote1 = null;

	private JLabel lblTot1 = null;

	private JFormattedTextField txtTotale1 = null;

	private DecimalFormat formatPrice11 = null;

	private JLabel lblNumDocumento1 = null;

	private JTextField txtNumDocumento1 = null;

	private JPanel pnlSud1 = null;

	private JLabel lblIngImponibile = null;

	private JTextField txtImponibileIng = null;

	private JLabel lblIngImposta = null;

	private JTextField txtImpostaIng = null;

	private JLabel lblTotIng = null;

	private JTextField txtTotaleIng = null;

	private JLabel lblIvaDocumento = null;

	private JFormattedTextField txtIvaDocumento = null;

	private NumberFormat formatPrezzoDocumento = null;

	private JPanel pnlSudUscite = null;

	private JLabel lblImponibileUscite = null;

	private JTextField txtImponibileUscite = null;

	private JLabel lblImpostaUscite = null;

	private JTextField txtImpostaUscite = null;

	private JLabel lblTotUscite = null;

	private JTextField txtTotaleUscite = null;

	private JLabel lblIvaDocUscite = null;

	private JFormattedTextField txtIvaDocUscita = null;

	private JPanel pnlTotali = null;

	private JPanel pnlTotEntrate = null;

	private JPanel pnlTotUscite = null;

	private JLabel lblImpEntrate = null;

	private JFormattedTextField txtImpEntrate = null;

	private JLabel lblImpostaEntrate = null;

	private JFormattedTextField txtImpostaEntrate = null;

	private JLabel lblTotaleEntrate = null;

	private JFormattedTextField txtTotaleEntrate = null;

	private JLabel lblImpUscite = null;

	private JFormattedTextField txtImpUscite = null;

	private JLabel lblImpostaUscite1 = null;

	private JFormattedTextField txtImpostaUscite1 = null;

	private JLabel lblTotaleUscite = null;

	private JFormattedTextField txtTotaleUscite1 = null;

	private JPanel pnlCalcoli = null;

	private JPanel pnlDifferenze = null;

	private JLabel lblDiffImp = null;

	private JFormattedTextField txtDiffImp = null;

	private JLabel lblDiffImposta = null;

	private JFormattedTextField txtDiffImposta = null;

	private JLabel lblDiffTotale = null;

	private JFormattedTextField txtDiffTotale = null;

	private JPanel pnlBanche = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JScrollPane jScrollPane3 = null;

	private JTable tblMovimentiBanca = null;

	private JLabel jLabel = null;

	private JDateChooser dataScadenza = null;

	private JLabel jLabel1 = null;

	private JTextField txtDescrizione = null;

	private JLabel jLabel2 = null;

	private JFormattedTextField txtEntrate = null;

	private JLabel jLabel3 = null;

	private JFormattedTextField txtUscite = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane4 = null;

	private JTextArea txtNoteMovimenti = null;

	private JLabel jLabel4 = null;

	private IDJComboBox cmbBanche = null;

	private JLabel jLabel5 = null;

	private IDJComboBox cmbConto = null;

	private int idconto;

	private MovimentiContoModel m;

	/**
	 * This is the default constructor
	 */
	public PrimaNotaGUI() {
		super();
		initialize();
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
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_CANCEL, 0), "CANC");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT + KeyEvent.VK_M, 0),
				"MOD");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");

		this.getRootPane().getActionMap().put("ENTER", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				int tabbed = jTabbedPane.getSelectedIndex();
				if (tabbed == 0)
					inserisciEntrata();
				// else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("CANC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				int tabbed = jTabbedPane.getSelectedIndex();
				if (tabbed == 0)
					eliminaEntrata();
				// else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("MOD", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				int tabbed = jTabbedPane.getSelectedIndex();
				if (tabbed == 0)
					modificaEntrata();
				// else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				dispose();
			}

		});

		this.setSize(716, 600);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Prima Nota");
		caricaClienti(cmbClienti);
		caricaDocumenti(cmbTipoDocumento);
		caricaFornitori(cmbClienti1);
		caricaDocumenti(cmbTipoDocumento1);
		UtilGUI.centraFrame(this);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		calcolaTotaliEntrate();
		calcolaTotaliUscite();
		caricaCmbBanche();
	}

	private void caricaCmbBanche() {
		cmbBanche.removeAllItems();
		Banca f = new Banca();
		try {
			String as[] = (String[]) f.allBanche();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbBanche).caricaNewValueComboBox(as, false);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento banche nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		// per abilitare autocompletamento
		AutoCompletion.enable(cmbBanche);

	}

	private void caricaCmbConto(int id) {
		cmbConto.removeAllItems();
		ContoCorrente f = new ContoCorrente();
		try {
			String as[] = (String[]) f.allContiCorrentiByIDBanca(id);
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbConto).caricaNewValueComboBox(as, false);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento conto nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		// per abilitare autocompletamento
		AutoCompletion.enable(cmbConto);

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
			jContentPane.add(getPnlCentrale(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlCentrale
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			pnlCentrale = new JPanel();
			pnlCentrale.setLayout(new BorderLayout());
			pnlCentrale.add(getPnlNord(), BorderLayout.NORTH);
			pnlCentrale.add(getPnlCentro(), BorderLayout.CENTER);
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes pnlNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord() {
		if (pnlNord == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlNord = new JPanel();
			pnlNord.setLayout(flowLayout);
			pnlNord.setPreferredSize(new Dimension(0, 40));
			pnlNord.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlNord.add(getBtnInserisci(), null);
			pnlNord.add(getModifica(), null);
			pnlNord.add(getBtnElimina(), null);
			pnlNord.add(getBtnEsci(), null);
		}
		return pnlNord;
	}

	/**
	 * This method initializes pnlCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(new BorderLayout());
			pnlCentro.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlCentro.add(getPnlCentro1(), BorderLayout.CENTER);
		}
		return pnlCentro;
	}

	/**
	 * This method initializes pnlCentro1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro1() {
		if (pnlCentro1 == null) {
			pnlCentro1 = new JPanel();
			pnlCentro1.setLayout(new BorderLayout());
			pnlCentro1.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlCentro1.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return pnlCentro1;
	}

	protected void eliminaEntrata() {
		if (tblEntrate.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare la riga selezionata?",
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

		int riga = tblEntrate.getSelectedRow();
		int idscarico = ((Long) tblEntrate.getValueAt(riga, 0)).intValue();
		Scarico c = new Scarico();

		try {
			c.caricaDati(idscarico);
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
		calcolaTotaliEntrate();
	}

	/**
	 * This method initializes btnInserisci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			btnInserisci = new JButton();
			btnInserisci.setText("Inserisci (Invio)");
			btnInserisci.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int tabbed = jTabbedPane.getSelectedIndex();
					if (tabbed == 0)
						inserisciEntrata();
					else if(tabbed==1)
						inserisciUscita();
					else inserisciMovimento();
				}
			});
		}
		return btnInserisci;
	}

	protected void inserisciUscita() {

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

		Carico c = new Carico();
		try {
			c.setIdCarico(DBManager.getIstanceSingleton().getNewID("ordini",
					"idordine"));
			c.setIdFornitore((new Integer(cmbClienti1.getIDSelectedItem()))
					.intValue());
			c.setDataCarico(new java.sql.Date(dataDocumento1.getDate()
					.getTime()));
			c.setOraCarico((new Time(dataDocumento1.getDate().getTime())));
			c.setNote(txtNote1.getText());
			c.setDataDocumento(new java.sql.Date(dataDocumento1.getDate()
					.getTime()));
			c.setNumDocumento(txtNumDocumento1.getText());
			c
					.setIdDocumento((new Integer(cmbTipoDocumento
							.getIDSelectedItem())).intValue());
			Object t = txtTotale1.getValue();
			if (t instanceof Double)
				c.setTotaleDocumentoIvato(((Double) txtTotale1.getValue())
						.doubleValue());
			else {
				long tmp = ((Long) txtTotale1.getValue()).longValue();
				c.setTotaleDocumentoIvato(new Double(tmp).doubleValue());
			}

			c
					.setIdDocumento((new Integer(cmbTipoDocumento1
							.getIDSelectedItem())).intValue());
			c.setInsertByPN(1);
			c.setIva_doc(((Number) txtIvaDocUscita.getValue()).intValue());
			c.insertCarico();
			txtIvaDocUscita.setValue(20);
			txtTotale1.setValue(0.0);
			calcolaTotaliUscite();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	protected void inserisciMovimento() {

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

		MovimentoBanca c = new MovimentoBanca();
		try {
			c.setIdconto(new Integer(cmbConto.getIDSelectedItem()).intValue());
			c.setDataInserimento(DateManager.convertDateToSqlDate(dataScadenza.getDate()));
			c.setDataScadenza(DateManager.convertDateToSqlDate(dataScadenza.getDate()));
			c.setDescrizione(txtDescrizione.getText());
			c.setEntrate(((Number)txtEntrate.getValue()).doubleValue());
			c.setUscite(((Number)txtUscite.getValue()).doubleValue());
			c.setNote(txtNoteMovimenti.getText());
			c.insertMovimento();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes Modifica
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getModifica() {
		if (Modifica == null) {
			Modifica = new JButton();
			Modifica.setText("Modifica");
			Modifica.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int tabbed = jTabbedPane.getSelectedIndex();
					if (tabbed == 0)
						modificaEntrata();
					else if (tabbed == 1)
						modificaUscita();
					else
						modificaMovimento();
				}
			});
		}
		return Modifica;
	}

	protected void modificaEntrata() {
		if (tblEntrate.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int riga = tblEntrate.getSelectedRow();
		int idscarico = ((Long) tblEntrate.getValueAt(riga, 0)).intValue();
		ModificaEntrata m = new ModificaEntrata(idscarico, this);
		m.setVisible(true);
		calcolaTotaliEntrate();

	}

	protected void modificaUscita() {
		if (tblUscite.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int riga = tblUscite.getSelectedRow();
		int idscarico = ((Long) tblUscite.getValueAt(riga, 0)).intValue();
		ModificaUscita m = new ModificaUscita(idscarico, this);
		m.setVisible(true);
		calcolaTotaliUscite();

	}

	protected void modificaMovimento() {
		if (tblMovimentiBanca.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int riga = tblMovimentiBanca.getSelectedRow();
		int idscarico = ((Number) tblMovimentiBanca.getValueAt(riga, 0)).intValue();
		ModificaMovimento m = new ModificaMovimento(idscarico, this);
		m.setVisible(true);

	}

	/**
	 * This method initializes btnElimina
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			btnElimina = new JButton();
			btnElimina.setText("Elimina (Canc)");
			btnElimina.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int tabbed = jTabbedPane.getSelectedIndex();
					if (tabbed == 0)
						eliminaEntrata();
					else if (tabbed == 1)
						eliminaUscita();
					eliminaMovimento();
				}
			});
		}
		return btnElimina;
	}

	protected void eliminaUscita() {
		if (tblUscite.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare la riga selezionata?",
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

		int riga = tblUscite.getSelectedRow();
		int idscarico = ((Long) tblUscite.getValueAt(riga, 0)).intValue();
		Carico c = new Carico();

		try {
			c.caricaDati(idscarico);
			c.deleteCarico(idscarico);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idCliente errato",
					"ERRORE", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		calcolaTotaliUscite();
	}




	protected void eliminaMovimento() {
		if (tblMovimentiBanca.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare la riga selezionata?",
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

		int riga = tblMovimentiBanca.getSelectedRow();
		int idscarico = ((Number) tblMovimentiBanca.getValueAt(riga, 0)).intValue();
		MovimentoBanca c = new MovimentoBanca();

		try {
			c.caricaDati(idscarico);
			c.deleteMovimento(idscarico);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idCliente errato",
					"ERRORE", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes btnEsci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEsci() {
		if (btnEsci == null) {
			btnEsci = new JButton();
			btnEsci.setText("Esci (Esc)");
			btnEsci.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return btnEsci;
	}

	private void caricaFornitori(JComboBox cmbFornitori) {
		cmbFornitori.removeAllItems();
		Fornitore f = new Fornitore();
		try {

			String as[] = (String[]) f.allFornitori();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbFornitori).caricaNewValueComboBox(as, false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbFornitori);
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Entrate", null, getPnlEntrate(), null);
			jTabbedPane.addTab("Uscite", null, getPnlUscite(), null);

			jTabbedPane.addTab("Totali", null, getPnlTotali(), null);
			jTabbedPane.addTab("Movimenti Banca", null, getPnlBanche(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlEntrate
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlEntrate() {
		if (pnlEntrate == null) {
			pnlEntrate = new JPanel();
			pnlEntrate.setLayout(new BorderLayout());
			pnlEntrate.add(getJScrollPane(), BorderLayout.CENTER);
			pnlEntrate.add(getPnlNord1(), BorderLayout.NORTH);
			pnlEntrate.add(getPnlSud1(), BorderLayout.SOUTH);
		}
		return pnlEntrate;
	}

	/**
	 * This method initializes pnlUscite
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlUscite() {
		if (pnlUscite == null) {
			pnlUscite = new JPanel();
			pnlUscite.setLayout(new BorderLayout());
			pnlUscite.add(getJScrollPane2(), BorderLayout.CENTER);
			pnlUscite.add(getPnlNord11(), BorderLayout.NORTH);
			pnlUscite.add(getPnlSudUscite(), BorderLayout.SOUTH);
		}
		return pnlUscite;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jScrollPane.setViewportView(getTblEntrate());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblEntrate
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblEntrate() {
		if (tblEntrate == null) {

			try {
				modello = new PrimanotaModelEntrate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBManager.getIstanceSingleton().addDBStateChange(modello);
			tblEntrate = new JXTable(modello);
			tblEntrate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// impostiamo le varie colonne
			TableColumn col = tblEntrate.getColumnModel().getColumn(0);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);

			col = tblEntrate.getColumn("totale_documento");
			DefaultTableCellRenderer prezzoColumnRenderer = new DefaultTableCellRenderer();
			prezzoColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);
			col.setCellRenderer(prezzoColumnRenderer);
			col.setPreferredWidth(40);

			col = tblEntrate.getColumn("data_documento");
			DefaultTableCellRenderer dataDoc = new DefaultTableCellRenderer();
			dataDoc.setHorizontalAlignment(JLabel.CENTER);
			col.setCellRenderer(dataDoc);
			col.setPreferredWidth(40);

			col = tblEntrate.getColumn("num_documento");
			DefaultTableCellRenderer numDoc = new DefaultTableCellRenderer();
			numDoc.setHorizontalAlignment(JLabel.CENTER);
			col.setCellRenderer(numDoc);
			col.setPreferredWidth(40);

			tblEntrate.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblEntrate.packAll();
			// per evitare che si possano spostare le colonne dalla
			// posizione originaria
			tblEntrate.getTableHeader().setReorderingAllowed(false);

		}
		return tblEntrate;
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void caricaClienti(JComboBox cmbFornitori) {
		cmbFornitori.removeAllItems();
		Cliente c = new Cliente();
		try {

			String as[] = (String[]) c.allClienti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbClienti).caricaNewValueComboBox(as, false);
		} catch (SQLException e) {
			messaggioCampoMancante("Errore caricamento clienti nel combobox",
					"ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbClienti);
	}

	private void caricaDocumenti(JComboBox cmbDocumenti) {

		cmbDocumenti.removeAllItems();
		Documento f = new Documento();
		try {

			String as[] = (String[]) f.allDocumentiConDescrizione();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbDocumenti).caricaNewValueComboBox(as, false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento documenti nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbDocumenti);
	}

	protected void nuovoDocumento() {
		DocumentoAddMod doc = new DocumentoAddMod(this, DBManager
				.getIstanceSingleton(), DocumentoAddMod.ADD);
		doc.setVisible(true);
		caricaDocumenti(cmbTipoDocumento);

	}

	/**
	 * This method initializes formatPrice
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getFormatPrice() {
		if (formatPrice == null) {
			formatPrice = new DecimalFormat();
			formatPrice.setMinimumFractionDigits(2);
			formatPrice.setMaximumFractionDigits(2);
		}
		return formatPrice;
	}

	protected void inserisciEntrata() {

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
			c.setIdScarico(DBManager.getIstanceSingleton().getNewID("ordini",
					"idordine"));
			c.setIdCliente((new Integer(cmbClienti.getIDSelectedItem()))
					.intValue());
			c.setDataScarico(new java.sql.Date(dataDocumento.getDate()
					.getTime()));
			c.setOraScarico((new Time(dataDocumento.getDate().getTime())));
			c.setNote(txtNote.getText());
			c.setDataDocumento(new java.sql.Date(dataDocumento.getDate()
					.getTime()));
			c.setNumDocumento(txtNumDocumento.getText());
			c
					.setIdDocumento((new Integer(cmbTipoDocumento
							.getIDSelectedItem())).intValue());
			Object t = txtTotale.getValue();
			if (t instanceof Double)
				c.setTotaleDocumentoIvato(((Double) txtTotale.getValue())
						.doubleValue());
			else {
				long tmp = ((Long) txtTotale.getValue()).longValue();
				c.setTotaleDocumentoIvato(new Double(tmp).doubleValue());
			}

			c
					.setIdDocumento((new Integer(cmbTipoDocumento
							.getIDSelectedItem())).intValue());
			c.setIvaDocumento(((Number) txtIvaDocumento.getValue()).intValue());
			c.setInsertByPN(1);
			c.insertScarico();
			txtIvaDocumento.setValue(10);
			txtTotale.setValue(0.0);
			calcolaTotaliEntrate();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes jScrollPane2
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jScrollPane2.setViewportView(getTblUscite());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes tblUscite
	 *
	 * @return org.jdesktop.swingx.JXTable
	 */
	private JXTable getTblUscite() {
		if (tblUscite == null) {
			try {
				modelloUscite = new PrimaNotaModelUscite();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tblUscite = new JXTable(modelloUscite);
			tblUscite.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			DBManager.getIstanceSingleton().addDBStateChange(modelloUscite);
			tblUscite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tblUscite;
	}

	/**
	 * This method initializes pnlNord1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord1() {
		if (pnlNord1 == null) {
			lblIvaDocumento = new JLabel();
			lblIvaDocumento.setBounds(new Rectangle(5, 95, 91, 21));
			lblIvaDocumento.setText("Iva Documento");
			lblNumDocumento = new JLabel();
			lblNumDocumento.setBounds(new Rectangle(6, 66, 91, 25));
			lblNumDocumento.setText("N° Documento");
			lblTot = new JLabel();
			lblTot.setBounds(new Rectangle(192, 66, 43, 25));
			lblTot.setText("Totale");
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(6, 36, 43, 25));
			lblCliente.setText("Cliente");
			lblTipoDocumento = new JLabel();
			lblTipoDocumento.setBounds(new Rectangle(234, 6, 97, 25));
			lblTipoDocumento.setText("Tipo Documento");
			lblTipoDocumento.setHorizontalAlignment(2);
			lblDataDocumento = new JLabel();
			lblDataDocumento.setBounds(new Rectangle(6, 6, 103, 25));
			lblDataDocumento.setText("Data Documento");
			pnlNord1 = new JPanel();
			pnlNord1.setLayout(null);
			pnlNord1.setPreferredSize(new Dimension(0, 130));
			pnlNord1.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlNord1.add(getDataDocumento(), null);
			pnlNord1.add(lblDataDocumento, null);
			pnlNord1.add(lblTipoDocumento, null);
			pnlNord1.add(getCmbTipoDocumento(), null);
			pnlNord1.add(lblCliente, null);
			pnlNord1.add(getCmbClienti(), null);
			pnlNord1.add(getPnlNote(), null);
			pnlNord1.add(lblTot, null);
			pnlNord1.add(getTxtTotale(), null);
			pnlNord1.add(lblNumDocumento, null);
			pnlNord1.add(getTxtNumDocumento(), null);
			pnlNord1.add(lblIvaDocumento, null);
			pnlNord1.add(getTxtIvaDocumento(), null);
		}
		return pnlNord1;
	}

	/**
	 * This method initializes dataDocumento
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataDocumento() {
		if (dataDocumento == null) {
			dataDocumento = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataDocumento.setBounds(new Rectangle(114, 6, 115, 25));
			dataDocumento.setDate(new Date());
		}
		return dataDocumento;
	}

	/**
	 * This method initializes cmbTipoDocumento
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbTipoDocumento() {
		if (cmbTipoDocumento == null) {
			cmbTipoDocumento = new IDJComboBox();
			cmbTipoDocumento.setBounds(new Rectangle(336, 6, 347, 25));
		}
		return cmbTipoDocumento;
	}

	/**
	 * This method initializes cmbClienti
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbClienti() {
		if (cmbClienti == null) {
			cmbClienti = new IDJComboBox();
			cmbClienti.setBounds(new Rectangle(54, 36, 283, 25));
		}
		return cmbClienti;
	}

	/**
	 * This method initializes pnlNote
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNote() {
		if (pnlNote == null) {
			pnlNote = new JPanel();
			pnlNote.setLayout(new BorderLayout());
			pnlNote.setBounds(new Rectangle(342, 36, 343, 79));
			pnlNote.setBorder(BorderFactory.createTitledBorder(null, "Note",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			pnlNote.add(getJScrollPane1(), java.awt.BorderLayout.CENTER);
		}
		return pnlNote;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTxtNote());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote() {
		if (txtNote == null) {
			txtNote = new JTextArea();
		}
		return txtNote;
	}

	/**
	 * This method initializes formatPrice1
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getFormatPrice1() {
		if (formatPrice1 == null) {
			formatPrice1 = new DecimalFormat();
			formatPrice1.setMinimumFractionDigits(2);
			formatPrice1.setMaximumFractionDigits(2);
		}
		return formatPrice1;
	}

	/**
	 * This method initializes txtTotale
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtTotale() {
		if (txtTotale == null) {
			txtTotale = new JFormattedTextField(getFormatPrice1());
			txtTotale.setBounds(new Rectangle(240, 66, 97, 25));
			txtTotale.setDocument(new UpperTextDocument());
			txtTotale.setHorizontalAlignment(JTextField.RIGHT);
			txtTotale.setValue(new Double(0));
			txtTotale.setPreferredSize(new Dimension(100, 20));
		}
		return txtTotale;
	}

	/**
	 * This method initializes txtNumDocumento
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumDocumento() {
		if (txtNumDocumento == null) {
			txtNumDocumento = new JTextField();
			txtNumDocumento.setBounds(new Rectangle(102, 66, 85, 25));
		}
		return txtNumDocumento;
	}

	/**
	 * This method initializes pnlNord11
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord11() {
		if (pnlNord11 == null) {
			lblIvaDocUscite = new JLabel();
			lblIvaDocUscite.setBounds(new Rectangle(5, 95, 91, 21));
			lblIvaDocUscite.setText("Iva Documento");
			lblNumDocumento1 = new JLabel();
			lblNumDocumento1.setBounds(new Rectangle(6, 66, 91, 25));
			lblNumDocumento1.setText("N° Documento");
			lblTot1 = new JLabel();
			lblTot1.setBounds(new Rectangle(192, 66, 43, 25));
			lblTot1.setText("Totale");
			lblCliente1 = new JLabel();
			lblCliente1.setBounds(new Rectangle(6, 36, 61, 25));
			lblCliente1.setText("Fornitore");
			lblTipoDocumento1 = new JLabel();
			lblTipoDocumento1.setBounds(new Rectangle(234, 6, 97, 25));
			lblTipoDocumento1.setText("Tipo Documento");
			lblTipoDocumento1.setHorizontalAlignment(2);
			lblDataDocumento1 = new JLabel();
			lblDataDocumento1.setBounds(new Rectangle(6, 6, 103, 25));
			lblDataDocumento1.setText("Data Documento");
			pnlNord11 = new JPanel();
			pnlNord11.setLayout(null);
			pnlNord11.setPreferredSize(new Dimension(0, 130));
			pnlNord11.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlNord11.add(getDataDocumento1(), null);
			pnlNord11.add(lblDataDocumento1, null);
			pnlNord11.add(lblTipoDocumento1, null);
			pnlNord11.add(getCmbTipoDocumento1(), null);
			pnlNord11.add(lblCliente1, null);
			pnlNord11.add(getCmbClienti1(), null);
			pnlNord11.add(getPnlNote1(), null);
			pnlNord11.add(lblTot1, null);
			pnlNord11.add(getTxtTotale1(), null);
			pnlNord11.add(lblNumDocumento1, null);
			pnlNord11.add(getTxtNumDocumento1(), null);
			pnlNord11.add(lblIvaDocUscite, null);
			pnlNord11.add(getTxtIvaDocUscita(), null);
		}
		return pnlNord11;
	}

	/**
	 * This method initializes dataDocumento1
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataDocumento1() {
		if (dataDocumento1 == null) {
			dataDocumento1 = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataDocumento1.setBounds(new Rectangle(114, 6, 115, 25));
			dataDocumento1.setDate(new Date());
		}
		return dataDocumento1;
	}

	/**
	 * This method initializes cmbTipoDocumento1
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbTipoDocumento1() {
		if (cmbTipoDocumento1 == null) {
			cmbTipoDocumento1 = new IDJComboBox();
			cmbTipoDocumento1.setBounds(new Rectangle(336, 6, 347, 25));
		}
		return cmbTipoDocumento1;
	}

	/**
	 * This method initializes cmbClienti1
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbClienti1() {
		if (cmbClienti1 == null) {
			cmbClienti1 = new IDJComboBox();
			cmbClienti1.setBounds(new Rectangle(73, 35, 308, 25));
		}
		return cmbClienti1;
	}

	/**
	 * This method initializes pnlNote1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNote1() {
		if (pnlNote1 == null) {
			pnlNote1 = new JPanel();
			pnlNote1.setLayout(new BorderLayout());
			pnlNote1.setBounds(new Rectangle(388, 36, 297, 79));
			pnlNote1.setBorder(BorderFactory.createTitledBorder(null, "Note",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			pnlNote1.add(getJScrollPane11(), java.awt.BorderLayout.CENTER);
		}
		return pnlNote1;
	}

	/**
	 * This method initializes jScrollPane11
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane11() {
		if (jScrollPane11 == null) {
			jScrollPane11 = new JScrollPane();
			jScrollPane11.setViewportView(getTxtNote1());
		}
		return jScrollPane11;
	}

	/**
	 * This method initializes txtNote1
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote1() {
		if (txtNote1 == null) {
			txtNote1 = new JTextArea();
		}
		return txtNote1;
	}

	/**
	 * This method initializes formatPrice11
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getFormatPrice11() {
		if (formatPrice11 == null) {
			formatPrice11 = new DecimalFormat();
			formatPrice11.setMinimumFractionDigits(2);
			formatPrice11.setMaximumFractionDigits(2);
		}
		return formatPrice11;
	}

	/**
	 * This method initializes txtTotale1
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtTotale1() {
		if (txtTotale1 == null) {
			txtTotale1 = new JFormattedTextField(getFormatPrice11());
			txtTotale1.setBounds(new Rectangle(240, 66, 97, 25));
			txtTotale1.setDocument(new UpperTextDocument());
			txtTotale1.setHorizontalAlignment(JTextField.RIGHT);
			txtTotale1.setValue(new Double(0));
			txtTotale1.setPreferredSize(new Dimension(100, 20));
		}
		return txtTotale1;
	}

	/**
	 * This method initializes txtNumDocumento1
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumDocumento1() {
		if (txtNumDocumento1 == null) {
			txtNumDocumento1 = new JTextField();
			txtNumDocumento1.setBounds(new Rectangle(102, 66, 85, 25));
		}
		return txtNumDocumento1;
	}

	private void calcolaTotaliEntrate() {
		Thread t = new Thread(new Runnable() {

			public void run() {
				double imponibile = 0, imposta = 0, tot = 0;

				// Calcoliamo ora la parte totale dello scarico di tutti gli
				// articoli.
				try {

					imponibile = Scarico.getTotVenditeImponibileByPrimaNota();
					imposta = Scarico.getTotVenditeImpostaByPrimaNota();
					tot = imponibile + imposta;

					// impostiamo i campi
					txtImponibileIng.setText(ControlloDati
							.convertDoubleToPrezzo(imponibile));
					txtImpostaIng.setText(ControlloDati
							.convertDoubleToPrezzo(imposta));
					txtTotaleIng.setText(ControlloDati
							.convertDoubleToPrezzo(tot));

					txtImpEntrate.setValue(imponibile);
					txtImpostaEntrate.setValue(imposta);
					txtTotaleEntrate.setValue(tot);

					calcolaDifferenzeTotali();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(getParent(),
							"Probabile errore nei calcoli", "ERRORE",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

			}

		});
		t.start();

	}

	protected void calcolaDifferenzeTotali() {
		double eImponibile = ((Number) txtImpEntrate.getValue()).doubleValue();
		double eImposta = ((Number) txtImpostaEntrate.getValue()).doubleValue();
		double eTotEntrate = ((Number) txtTotaleEntrate.getValue())
				.doubleValue();
		double uImponibile = ((Number) txtImpUscite.getValue()).doubleValue();
		double uImposta = ((Number) txtImpostaUscite1.getValue()).doubleValue();
		double uTotUscite = ((Number) txtTotaleUscite1.getValue())
				.doubleValue();

		txtDiffTotale.setValue(eTotEntrate - uTotUscite);
		txtDiffImp.setValue(eImponibile - uImponibile);
		txtDiffImposta.setValue(eImposta - uImposta);

	}

	private void calcolaTotaliUscite() {

		Thread t = new Thread(new Runnable() {

			public void run() {

				double imponibile = 0, imposta = 0, tot = 0;

				// Calcoliamo ora la parte totale dello scarico di tutti gli
				// articoli.
				try {

					imponibile = Carico.getTotAcquistoImponibileByPrimaNota();
					imposta = Carico.getTotAcquistoImpostaByPrimaNota();
					tot = imponibile + imposta;

					// impostiamo i campi
					txtImponibileUscite.setText(ControlloDati
							.convertDoubleToPrezzo(imponibile));
					txtImpostaUscite.setText(ControlloDati
							.convertDoubleToPrezzo(imposta));
					txtTotaleUscite.setText(ControlloDati
							.convertDoubleToPrezzo(tot));

					txtImpUscite.setValue(imponibile);
					txtImpostaUscite1.setValue(imposta);
					txtTotaleUscite1.setValue(tot);

					calcolaDifferenzeTotali();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(getParent(),
							"Probabile errore nei calcoli", "ERRORE",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (IDNonValido e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ResultSetVuoto e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		t.start();

	}

	/**
	 * This method initializes pnlSud1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud1() {
		if (pnlSud1 == null) {
			lblTotIng = new JLabel();
			lblTotIng.setBounds(new Rectangle(424, 8, 85, 25));
			lblTotIng.setText("Totale \u20ac.");
			lblIngImposta = new JLabel();
			lblIngImposta.setBounds(new Rectangle(236, 8, 89, 25));
			lblIngImposta.setText("Imposta \u20ac.");
			lblIngImponibile = new JLabel();
			lblIngImponibile.setBounds(new Rectangle(32, 8, 105, 25));
			lblIngImponibile.setText("Imponibile \u20ac.");
			pnlSud1 = new JPanel();
			pnlSud1.setLayout(null);
			pnlSud1.setPreferredSize(new Dimension(0, 40));
			pnlSud1.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlSud1.add(lblIngImponibile, null);
			pnlSud1.add(getTxtImponibileIng(), null);
			pnlSud1.add(lblIngImposta, null);
			pnlSud1.add(getTxtImpostaIng(), null);
			pnlSud1.add(lblTotIng, null);
			pnlSud1.add(getTxtTotaleIng(), null);
		}
		return pnlSud1;
	}

	/**
	 * This method initializes txtImponibileIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileIng() {
		if (txtImponibileIng == null) {
			txtImponibileIng = new JTextField();
			txtImponibileIng.setBounds(new Rectangle(136, 8, 93, 25));
			txtImponibileIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImponibileIng.setDisabledTextColor(Color.black);
			txtImponibileIng.setEditable(false);
			txtImponibileIng.setEnabled(false);
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
			txtImpostaIng = new JTextField();
			txtImpostaIng.setBounds(new Rectangle(324, 8, 93, 25));
			txtImpostaIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpostaIng.setDisabledTextColor(Color.black);
			txtImpostaIng.setEditable(false);
			txtImpostaIng.setEnabled(false);
		}
		return txtImpostaIng;
	}

	/**
	 * This method initializes txtTotaleIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotaleIng() {
		if (txtTotaleIng == null) {
			txtTotaleIng = new JTextField();
			txtTotaleIng.setBounds(new Rectangle(508, 8, 93, 25));
			txtTotaleIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtTotaleIng.setDisabledTextColor(Color.red);
			txtTotaleIng.setEditable(false);
			txtTotaleIng.setEnabled(false);
		}
		return txtTotaleIng;
	}

	/**
	 * This method initializes formatPrezzoDocumento
	 *
	 * @return java.text.DecimalFormat
	 */
	private NumberFormat getFormatPrezzoDocumento() {
		if (formatPrezzoDocumento == null) {
			formatPrezzoDocumento = NumberFormat.getInstance();
			formatPrezzoDocumento.setMinimumFractionDigits(0);
			formatPrezzoDocumento.setMaximumFractionDigits(0);
		}
		return formatPrezzoDocumento;
	}

	/**
	 * This method initializes txtIvaDocumento
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtIvaDocumento() {
		if (txtIvaDocumento == null) {
			txtIvaDocumento = new JFormattedTextField(
					getFormatPrezzoDocumento());
			txtIvaDocumento.setBounds(new Rectangle(100, 95, 86, 21));
			txtIvaDocumento.setDocument(new UpperTextDocument());
			txtIvaDocumento.setHorizontalAlignment(JTextField.RIGHT);
			txtIvaDocumento.setValue(new Double(0));
			txtIvaDocumento.setPreferredSize(new Dimension(100, 20));
		}
		return txtIvaDocumento;
	}

	/**
	 * This method initializes pnlSudUscite
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSudUscite() {
		if (pnlSudUscite == null) {
			lblTotUscite = new JLabel();
			lblTotUscite.setBounds(new Rectangle(424, 8, 85, 25));
			lblTotUscite.setText("Totale \u20ac.");
			lblImpostaUscite = new JLabel();
			lblImpostaUscite.setBounds(new Rectangle(236, 8, 89, 25));
			lblImpostaUscite.setText("Imposta \u20ac.");
			lblImponibileUscite = new JLabel();
			lblImponibileUscite.setBounds(new Rectangle(32, 8, 105, 25));
			lblImponibileUscite.setText("Imponibile \u20ac.");
			pnlSudUscite = new JPanel();
			pnlSudUscite.setLayout(null);
			pnlSudUscite.setPreferredSize(new Dimension(0, 40));
			pnlSudUscite.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlSudUscite.add(lblImponibileUscite, null);
			pnlSudUscite.add(getTxtImponibileUscite(), null);
			pnlSudUscite.add(lblImpostaUscite, null);
			pnlSudUscite.add(getTxtImpostaUscite(), null);
			pnlSudUscite.add(lblTotUscite, null);
			pnlSudUscite.add(getTxtTotaleUscite(), null);
		}
		return pnlSudUscite;
	}

	/**
	 * This method initializes txtImponibileUscite
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileUscite() {
		if (txtImponibileUscite == null) {
			txtImponibileUscite = new JTextField();
			txtImponibileUscite.setBounds(new Rectangle(136, 8, 93, 25));
			txtImponibileUscite.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImponibileUscite.setDisabledTextColor(Color.black);
			txtImponibileUscite.setEditable(false);
			txtImponibileUscite.setEnabled(false);
		}
		return txtImponibileUscite;
	}

	/**
	 * This method initializes txtImpostaUscite
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImpostaUscite() {
		if (txtImpostaUscite == null) {
			txtImpostaUscite = new JTextField();
			txtImpostaUscite.setBounds(new Rectangle(324, 8, 93, 25));
			txtImpostaUscite.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpostaUscite.setDisabledTextColor(Color.black);
			txtImpostaUscite.setEditable(false);
			txtImpostaUscite.setEnabled(false);
		}
		return txtImpostaUscite;
	}

	/**
	 * This method initializes txtTotaleUscite
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotaleUscite() {
		if (txtTotaleUscite == null) {
			txtTotaleUscite = new JTextField();
			txtTotaleUscite.setBounds(new Rectangle(508, 8, 93, 25));
			txtTotaleUscite.setFont(new Font("Dialog", Font.BOLD, 12));
			txtTotaleUscite.setDisabledTextColor(Color.red);
			txtTotaleUscite.setEditable(false);
			txtTotaleUscite.setEnabled(false);
		}
		return txtTotaleUscite;
	}

	/**
	 * This method initializes txtIvaDocUscita
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtIvaDocUscita() {
		if (txtIvaDocUscita == null) {
			NumberFormat f = NumberFormat.getIntegerInstance();
			txtIvaDocUscita = new JFormattedTextField(f);
			txtIvaDocUscita.setBounds(new Rectangle(105, 95, 81, 21));
			txtIvaDocUscita.setValue(20);
		}
		return txtIvaDocUscita;
	}

	/**
	 * This method initializes pnlTotali
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlTotali() {
		if (pnlTotali == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(3);
			pnlTotali = new JPanel();
			pnlTotali.setLayout(gridLayout);
			pnlTotali.add(getPnlCalcoli(), null);
		}
		return pnlTotali;
	}

	/**
	 * This method initializes pnlTotEntrate
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlTotEntrate() {
		if (pnlTotEntrate == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setVgap(1);
			lblTotaleEntrate = new JLabel();
			lblTotaleEntrate.setText("Totale");
			lblImpostaEntrate = new JLabel();
			lblImpostaEntrate.setText("Imposta");
			lblImpEntrate = new JLabel();
			lblImpEntrate.setText("Imponibile");
			pnlTotEntrate = new JPanel();
			pnlTotEntrate.setLayout(flowLayout1);
			pnlTotEntrate.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.RAISED),
					"Entrate", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 14), Color.green));
			pnlTotEntrate.add(lblImpEntrate, null);
			pnlTotEntrate.add(getTxtImpEntrate(), null);
			pnlTotEntrate.add(lblImpostaEntrate, null);
			pnlTotEntrate.add(getTxtImpostaEntrate(), null);
			pnlTotEntrate.add(lblTotaleEntrate, null);
			pnlTotEntrate.add(getTxtTotaleEntrate(), null);
		}
		return pnlTotEntrate;
	}

	/**
	 * This method initializes pnlTotUscite
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlTotUscite() {
		if (pnlTotUscite == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setVgap(1);
			lblTotaleUscite = new JLabel();
			lblTotaleUscite.setText("Totale");
			lblImpostaUscite1 = new JLabel();
			lblImpostaUscite1.setText("Imposta");
			lblImpUscite = new JLabel();
			lblImpUscite.setText("Imponibile");
			pnlTotUscite = new JPanel();
			pnlTotUscite.setLayout(flowLayout2);
			pnlTotUscite.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.RAISED),
					"Uscite", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 14), Color.red));
			pnlTotUscite.add(lblImpUscite, null);
			pnlTotUscite.add(getTxtImpUscite(), null);
			pnlTotUscite.add(lblImpostaUscite1, null);
			pnlTotUscite.add(getTxtImpostaUscite1(), null);
			pnlTotUscite.add(lblTotaleUscite, null);
			pnlTotUscite.add(getTxtTotaleUscite1(), null);
		}
		return pnlTotUscite;
	}

	/**
	 * This method initializes txtImpEntrate
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtImpEntrate() {
		if (txtImpEntrate == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtImpEntrate = new JFormattedTextField(d);
			txtImpEntrate.setPreferredSize(new Dimension(100, 20));
			txtImpEntrate.setHorizontalAlignment(JTextField.RIGHT);
			txtImpEntrate.setEditable(false);
			txtImpEntrate.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpEntrate.setForeground(Color.green);
			txtImpEntrate.setValue(0);
		}
		return txtImpEntrate;
	}

	/**
	 * This method initializes txtImpostaEntrate
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtImpostaEntrate() {
		if (txtImpostaEntrate == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtImpostaEntrate = new JFormattedTextField(d);
			txtImpostaEntrate.setPreferredSize(new Dimension(100, 20));
			txtImpostaEntrate.setHorizontalAlignment(JTextField.RIGHT);
			txtImpostaEntrate.setEditable(false);
			txtImpostaEntrate.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpostaEntrate.setForeground(Color.green);
			txtImpostaEntrate.setValue(0);
		}
		return txtImpostaEntrate;
	}

	/**
	 * This method initializes txtTotaleEntrate
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtTotaleEntrate() {
		if (txtTotaleEntrate == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtTotaleEntrate = new JFormattedTextField(d);
			txtTotaleEntrate.setPreferredSize(new Dimension(100, 20));
			txtTotaleEntrate.setHorizontalAlignment(JTextField.RIGHT);
			txtTotaleEntrate.setEditable(false);
			txtTotaleEntrate.setFont(new Font("Dialog", Font.BOLD, 12));
			txtTotaleEntrate.setForeground(Color.green);
			txtTotaleEntrate.setValue(0);
		}
		return txtTotaleEntrate;
	}

	/**
	 * This method initializes txtImpUscite
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtImpUscite() {
		if (txtImpUscite == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtImpUscite = new JFormattedTextField(d);
			txtImpUscite.setPreferredSize(new Dimension(100, 20));
			txtImpUscite.setHorizontalAlignment(JTextField.RIGHT);
			txtImpUscite.setEditable(false);
			txtImpUscite.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpUscite.setForeground(Color.red);
			txtImpUscite.setValue(0);
		}
		return txtImpUscite;
	}

	/**
	 * This method initializes txtImpostaUscite1
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtImpostaUscite1() {
		if (txtImpostaUscite1 == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtImpostaUscite1 = new JFormattedTextField(d);
			txtImpostaUscite1.setPreferredSize(new Dimension(100, 20));
			txtImpostaUscite1.setHorizontalAlignment(JTextField.RIGHT);
			txtImpostaUscite1.setEditable(false);
			txtImpostaUscite1.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpostaUscite1.setForeground(Color.red);
			txtImpostaUscite1.setValue(0);
		}
		return txtImpostaUscite1;
	}

	/**
	 * This method initializes txtTotaleUscite1
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtTotaleUscite1() {
		if (txtTotaleUscite1 == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtTotaleUscite1 = new JFormattedTextField(d);
			txtTotaleUscite1.setPreferredSize(new Dimension(100, 20));
			txtTotaleUscite1.setHorizontalAlignment(JTextField.RIGHT);
			txtTotaleUscite1.setEditable(false);
			txtTotaleUscite1.setFont(new Font("Dialog", Font.BOLD, 12));
			txtTotaleUscite1.setForeground(Color.red);
			txtTotaleUscite1.setValue(0);
		}
		return txtTotaleUscite1;
	}

	/**
	 * This method initializes pnlCalcoli
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCalcoli() {
		if (pnlCalcoli == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(3);
			pnlCalcoli = new JPanel();
			pnlCalcoli.setLayout(gridLayout1);
			pnlCalcoli.add(getPnlTotEntrate(), null);
			pnlCalcoli.add(getPnlTotUscite(), null);
			pnlCalcoli.add(getPnlDifferenze(), null);
		}
		return pnlCalcoli;
	}

	/**
	 * This method initializes pnlDifferenze
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDifferenze() {
		if (pnlDifferenze == null) {
			FlowLayout flowLayout3 = new FlowLayout();
			flowLayout3.setVgap(1);
			lblDiffTotale = new JLabel();
			lblDiffTotale.setText("Totale");
			lblDiffImposta = new JLabel();
			lblDiffImposta.setText("Imposta");
			lblDiffImp = new JLabel();
			lblDiffImp.setText("Imponibile");
			pnlDifferenze = new JPanel();
			pnlDifferenze.setLayout(flowLayout3);
			pnlDifferenze.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.RAISED),
					"Differenze", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 14), Color.black));
			pnlDifferenze.add(lblDiffImp, null);
			pnlDifferenze.add(getTxtDiffImp(), null);
			pnlDifferenze.add(lblDiffImposta, null);
			pnlDifferenze.add(getTxtDiffImposta(), null);
			pnlDifferenze.add(lblDiffTotale, null);
			pnlDifferenze.add(getTxtDiffTotale(), null);
		}
		return pnlDifferenze;
	}

	/**
	 * This method initializes txtDiffImp
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtDiffImp() {
		if (txtDiffImp == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtDiffImp = new JFormattedTextField(d);
			txtDiffImp.setPreferredSize(new Dimension(100, 20));
			txtDiffImp.setHorizontalAlignment(JTextField.RIGHT);
			txtDiffImp.setEditable(false);
			txtDiffImp.setFont(new Font("Dialog", Font.BOLD, 12));
			txtDiffImp.setValue(0);
		}
		return txtDiffImp;
	}

	/**
	 * This method initializes txtDiffImposta
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtDiffImposta() {
		if (txtDiffImposta == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtDiffImposta = new JFormattedTextField(d);
			txtDiffImposta.setPreferredSize(new Dimension(100, 20));
			txtDiffImposta.setHorizontalAlignment(JTextField.RIGHT);
			txtDiffImposta.setEditable(false);
			txtDiffImposta.setFont(new Font("Dialog", Font.BOLD, 12));
			txtDiffImposta.setValue(0);
		}
		return txtDiffImposta;
	}

	/**
	 * This method initializes txtDiffTotale
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtDiffTotale() {
		if (txtDiffTotale == null) {
			DecimalFormat d = new DecimalFormat();
			d.setMaximumFractionDigits(2);
			d.setMinimumFractionDigits(2);
			txtDiffTotale = new JFormattedTextField(d);
			txtDiffTotale.setPreferredSize(new Dimension(100, 20));
			txtDiffTotale.setHorizontalAlignment(JTextField.RIGHT);
			txtDiffTotale.setEditable(false);
			txtDiffTotale.setFont(new Font("Dialog", Font.BOLD, 12));
			txtDiffTotale.setValue(0);
		}
		return txtDiffTotale;
	}

	/**
	 * This method initializes pnlBanche
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBanche() {
		if (pnlBanche == null) {
			pnlBanche = new JPanel();
			pnlBanche.setLayout(new BorderLayout());
			pnlBanche.add(getJPanel(), BorderLayout.NORTH);
			pnlBanche.add(getJPanel1(), BorderLayout.CENTER);
		}
		return pnlBanche;
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(340, 5, 26, 31));
			jLabel5.setText("C/C");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(5, 5, 106, 31));
			jLabel4.setText("Seleziona Banca");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(155, 105, 46, 26));
			jLabel3.setText("Uscite");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(5, 105, 51, 26));
			jLabel2.setText("Entrate");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(5, 80, 141, 21));
			jLabel1.setText("Descrizione Movimento");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(5, 45, 91, 26));
			jLabel.setText("Data Scadenza");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(0, 150));
			jPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jPanel.add(jLabel, null);
			jPanel.add(getDataScadenza(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getTxtDescrizione(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getTxtEntrate(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(getTxtUscite(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(getCmbBanche(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(getCmbConto(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BorderLayout());
			jPanel1.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jPanel1.add(getJScrollPane3(), BorderLayout.CENTER);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane3
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getTblMovimentiBanca());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes tblBanche
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblMovimentiBanca() {
		if (tblMovimentiBanca == null) {

			try {
				m = new MovimentiContoModel(this.idconto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tblMovimentiBanca = new JTable(m);
			tblMovimentiBanca.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			DBManager.getIstanceSingleton().addDBStateChange(m);
			tblMovimentiBanca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//			 impostiamo le varie colonne
			TableColumn col = tblMovimentiBanca.getColumnModel().getColumn(0);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);

//			 impostiamo le varie colonne
			col = tblMovimentiBanca.getColumnModel().getColumn(1);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);

//			 impostiamo le varie colonne
			col = tblMovimentiBanca.getColumnModel().getColumn(2);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);
		}
		return tblMovimentiBanca;
	}

	/**
	 * This method initializes dataScadenza
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataScadenza() {
		if (dataScadenza == null) {
			dataScadenza = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataScadenza.setBounds(new Rectangle(100, 45, 131, 26));
			dataScadenza.setDate(new java.util.Date());
			JTextFieldDateEditor f = (JTextFieldDateEditor) dataScadenza
					.getDateEditor();
			f.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					JTextFieldDateEditor s = (JTextFieldDateEditor) dataScadenza
							.getDateEditor();
					s.setCaretPosition(0);
				}
			});
		}
		return dataScadenza;
	}

	/**
	 * This method initializes txtDescrizione
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescrizione() {
		if (txtDescrizione == null) {
			txtDescrizione = new JTextField();
			txtDescrizione.setBounds(new Rectangle(150, 80, 296, 21));
			txtDescrizione.setDocument(new UpperTextDocument());
		}
		return txtDescrizione;
	}

	/**
	 * This method initializes txtEntrate
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtEntrate() {
		if (txtEntrate == null) {
			DecimalFormat f=new DecimalFormat();
			f.setMaximumFractionDigits(2);
			f.setMinimumFractionDigits(2);
			txtEntrate = new JFormattedTextField(f);
			txtEntrate.setBounds(new Rectangle(60, 105, 91, 26));
			txtEntrate.setFont(new Font("Dialog", Font.BOLD, 14));
			txtEntrate.setForeground(Color.green);
			txtEntrate.setHorizontalAlignment(JTextField.RIGHT);
			txtEntrate.setValue(0);
		}
		return txtEntrate;
	}

	/**
	 * This method initializes txtUscite
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtUscite() {
		if (txtUscite == null) {
			DecimalFormat f=new DecimalFormat();
			f.setMaximumFractionDigits(2);
			f.setMinimumFractionDigits(2);
			txtUscite = new JFormattedTextField(f);

			txtUscite.setBounds(new Rectangle(205, 105, 96, 26));
			txtUscite.setForeground(Color.red);
			txtUscite.setHorizontalAlignment(JTextField.RIGHT);
			txtUscite.setFont(new Font("Dialog", Font.BOLD, 14));
			txtUscite.setValue(0);
		}
		return txtUscite;
	}

	/**
	 * This method initializes jPanel2
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.setBounds(new Rectangle(450, 45, 241, 91));
			jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel2.add(getJScrollPane4(), BorderLayout.CENTER);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane4
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setViewportView(getTxtNoteMovimenti());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes txtNoteMovimenti
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNoteMovimenti() {
		if (txtNoteMovimenti == null) {
			txtNoteMovimenti = new JTextArea();
			txtNoteMovimenti.setDocument(new UpperTextDocument());
		}
		return txtNoteMovimenti;
	}

	/**
	 * This method initializes cmbBanche
	 *
	 * @return javax.swing.JComboBox
	 */
	private IDJComboBox getCmbBanche() {
		if (cmbBanche == null) {
			cmbBanche = new IDJComboBox();
			cmbBanche.setBounds(new Rectangle(115, 5, 216, 31));
			cmbBanche.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ricaricaCmbCC(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return cmbBanche;
	}

	protected void ricaricaCmbCC() {
		int id=new Integer(cmbBanche.getIDSelectedItem()).intValue();
		caricaCmbConto(id);
		this.idconto=new Integer(cmbConto.getIDSelectedItem()).intValue();
		m.setIdconto(this.idconto);
		m.stateChange();

	}

	/**
	 * This method initializes cmbConto
	 *
	 * @return javax.swing.JComboBox
	 */
	private IDJComboBox getCmbConto() {
		if (cmbConto == null) {
			cmbConto = new IDJComboBox();
			cmbConto.setBounds(new Rectangle(370, 5, 226, 31));
		}
		return cmbConto;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
