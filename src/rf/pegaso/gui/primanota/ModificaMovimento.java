package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import rf.myswing.IDJComboBox;
import rf.pegaso.db.model.PrimaNotaModelUscite;
import rf.pegaso.db.tabelle.Banca;
import rf.pegaso.db.tabelle.ContoCorrente;
import rf.pegaso.db.tabelle.MovimentoBanca;
import rf.utility.DateManager;
import rf.utility.db.UtilityDBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;

import com.toedter.calendar.JDateChooser;

public class ModificaMovimento extends JDialog {

	public static final int USCITA = 0;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private DecimalFormat formatPrice = null;

	private JPanel jPanel1 = null;

	private JButton jButton = null;

	private int id;

	private int modalita;

	private NumberFormat formatPrezzoDocumento = null;

	private PrimaNotaModelUscite modelloUscite = null;

	private JPanel jPanel2 = null;

	private JLabel jLabel = null;

	private JDateChooser dataScadenza = null;

	private JLabel jLabel1 = null;

	private JTextField txtDescrizione = null;

	private JLabel jLabel2 = null;

	private JFormattedTextField txtEntrate = null;

	private DecimalFormat f1 = null;

	private JLabel jLabel3 = null;

	private JFormattedTextField txtUscite = null;

	private DecimalFormat f2 = null;

	private JPanel jPanel21 = null;

	private JScrollPane jScrollPane4 = null;

	private JTextArea txtNoteMovimenti = null;

	private JLabel jLabel4 = null;

	private IDJComboBox cmbBanche = null;

	private JLabel jLabel5 = null;

	private IDJComboBox cmbConto = null;

	/**
	 * This method initializes
	 *
	 */
	public ModificaMovimento(int id, Frame padre) {
		super(padre, true);
		this.id = id;
		initialize();
	}

	public ModificaMovimento(int ID, Frame padre, int modalita) {
		super(padre, true);
		this.id = id;
		this.modalita = modalita;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 */
	private void initialize() {
		this.setSize(new Dimension(707, 215));

		this.setTitle("Modifica Entrata");
		this.setContentPane(getJContentPane());

		UtilGUI.centraDialog(this);
		caricaDati();

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

	private void caricaCmbConto() {
		cmbConto.removeAllItems();
		ContoCorrente f = new ContoCorrente();
		try {
			String as[] = (String[]) f.allContiCorrenti();
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

	private void caricaDati() {
		caricaCmbBanche();
		caricaCmbConto();
		MovimentoBanca s = new MovimentoBanca();
		try {
			s.caricaDati(id);

			ContoCorrente cc=new ContoCorrente();
			cc.caricaDati(s.getIdconto());
			cmbConto.setSelectedItemByID(cc.getIdconto());

			Banca b=new Banca();
			b.caricaDati(cc.getIdbanca());
			cmbBanche.setSelectedItemByID(b.getIdbanca());

			dataScadenza.setDate(s.getDataScadenza());
			txtNoteMovimenti.setText(s.getNote());
			txtDescrizione.setText(s.getDescrizione());
			txtEntrate.setValue(s.getEntrate());
			txtUscite.setValue(s.getUscite());


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
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
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJPanel1(), BorderLayout.NORTH);
			jPanel.add(getJPanel2(), BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
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

	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.setPreferredSize(new Dimension(0, 40));
			jPanel1.add(getJButton(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Modifica");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					modifica();
				}
			});
		}
		return jButton;
	}

	protected void modifica() {

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(
					UtilityDBManager.UPDATE);
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
				c.caricaDati(id);
				c.setIdconto(new Integer(cmbConto.getIDSelectedItem()).intValue());
				c.setDataInserimento(DateManager.convertDateToSqlDate(dataScadenza.getDate()));
				c.setDataScadenza(DateManager.convertDateToSqlDate(dataScadenza.getDate()));
				c.setDescrizione(txtDescrizione.getText());
				c.setEntrate(((Number)txtEntrate.getValue()).doubleValue());
				c.setUscite(((Number)txtUscite.getValue()).doubleValue());
				c.setNote(txtNoteMovimenti.getText());
				c.insertMovimento();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


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
	 * This method initializes jPanel2
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
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
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setPreferredSize(new Dimension(0, 150));
			jPanel2.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jPanel2.add(jLabel, null);
			jPanel2.add(getDataScadenza(), null);
			jPanel2.add(jLabel1, null);
			jPanel2.add(getTxtDescrizione(), null);
			jPanel2.add(jLabel2, null);
			jPanel2.add(getTxtEntrate(), null);
			jPanel2.add(jLabel3, null);
			jPanel2.add(getTxtUscite(), null);
			jPanel2.add(getJPanel21(), null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(getCmbBanche(), null);
			jPanel2.add(jLabel5, null);
			jPanel2.add(getCmbConto(), null);
		}
		return jPanel2;
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
			dataScadenza.setDate(new Date());
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
		}
		return txtDescrizione;
	}

	/**
	 * This method initializes f1
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getF1() {
		if (f1 == null) {
			f1 = new DecimalFormat();
			f1.setMinimumFractionDigits(2);
			f1.setMaximumFractionDigits(2);
		}
		return f1;
	}

	/**
	 * This method initializes txtEntrate
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtEntrate() {
		if (txtEntrate == null) {
			txtEntrate = new JFormattedTextField(getF1());
			txtEntrate.setBounds(new Rectangle(60, 105, 91, 26));
			txtEntrate.setForeground(Color.green);
			txtEntrate.setHorizontalAlignment(JTextField.RIGHT);
			txtEntrate.setValue(0);
			txtEntrate.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return txtEntrate;
	}

	/**
	 * This method initializes f2
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getF2() {
		if (f2 == null) {
			f2 = new DecimalFormat();
			f2.setMinimumFractionDigits(2);
			f2.setMaximumFractionDigits(2);
		}
		return f2;
	}

	/**
	 * This method initializes txtUscite
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtUscite() {
		if (txtUscite == null) {
			txtUscite = new JFormattedTextField(getF2());
			txtUscite.setBounds(new Rectangle(205, 105, 96, 26));
			txtUscite.setForeground(Color.red);
			txtUscite.setHorizontalAlignment(JTextField.RIGHT);
			txtUscite.setValue(0);
			txtUscite.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return txtUscite;
	}

	/**
	 * This method initializes jPanel21
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel21() {
		if (jPanel21 == null) {
			jPanel21 = new JPanel();
			jPanel21.setLayout(new BorderLayout());
			jPanel21.setBounds(new Rectangle(450, 45, 241, 91));
			jPanel21.setBorder(BorderFactory.createTitledBorder(null, "Note",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel21.add(getJScrollPane4(), java.awt.BorderLayout.CENTER);
		}
		return jPanel21;
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
		}
		return txtNoteMovimenti;
	}

	/**
	 * This method initializes cmbBanche
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbBanche() {
		if (cmbBanche == null) {
			cmbBanche = new IDJComboBox();
			cmbBanche.setBounds(new Rectangle(115, 5, 216, 31));
		}
		return cmbBanche;
	}

	/**
	 * This method initializes cmbConto
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbConto() {
		if (cmbConto == null) {
			cmbConto = new IDJComboBox();
			cmbConto.setBounds(new Rectangle(370, 5, 226, 31));
		}
		return cmbConto;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
