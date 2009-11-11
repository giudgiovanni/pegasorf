/**
 * 
 */
package rf.pegaso.gui.viste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.search.ArticoliByFornitoreModel;
import rf.pegaso.db.tabelle.Fornitore;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;

/**
 * @author Hunter
 * 
 */
public class RicercheArchivio extends JDialog {
	class MyKeyListeners extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				int pos = cmbFornitore.getSelectedIndex();
				if (pos == 0)
					return;
				// descrementiamo di uno perchè nel combobox è presente
				// anche un oggetto vuoto
				pos--;
				int cod = new Integer(codFornitori[pos]);
				Fornitore f = new Fornitore();
				ricercaArticoliByFornitore(cod);
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JComboBox cmbFornitore = null;

	private String codFornitori[];

	private DBManager dbm;

	private String descFornitori[];

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel pnlArticoliByFornitore = null;

	private JPanel pnlFornitore = null;

	private JPanel pnlRisultati = null;

	private JXTable tblArtByForn = null;

	/**
	 * @param owner
	 */
	public RicercheArchivio(Frame owner ) {
		super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	private void caricaComboBox(JComboBox combo, Object[] o) {
		combo.removeAllItems();
		combo.addItem("");
		for (int i = 0; i < o.length; i++) {
			combo.addItem(o[i]);
		}
	}

	/**
	 * 
	 */
	private void caricaDatiRicercaArtByForn() {
		Fornitore f = new Fornitore();
		String allFornitori[] = null;

		try {
			allFornitori = (String[]) f.allFornitori();
		} catch (SQLException e2) {
			messaggioErroreCampo("Errore caricamento dati fornitori");
			e2.printStackTrace();
		}
		int size = allFornitori.length;
		codFornitori = new String[size];
		descFornitori = new String[size];

		// Impostiamo e carichiamo i dati nel combobox
		cmbFornitore.setEditable(true);
		cmbFornitore.addItem("");
		for (int i = 0; i < size; i++) {
			String cod = allFornitori[i].substring(0, allFornitori[i]
					.indexOf("-") - 1);
			String des = allFornitori[i]
					.substring(allFornitori[i].indexOf("-") + 2);
			codFornitori[i] = cod;
			descFornitori[i] = des;
			cmbFornitore.addItem(descFornitori[i]);
		}

		JTextComponent editor = (JTextComponent) cmbFornitore.getEditor()
				.getEditorComponent();
		editor.addKeyListener(new MyKeyListeners());
		//new ComboBoxAutoComplete(cmbFornitore);
		AutoCompletion.enable(cmbFornitore);
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
				btnChiudi.setBounds(new Rectangle(460, 12, 85, 33)); // Generated
				btnChiudi.setText("Chiudi"); // Generated
				btnChiudi
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								dispose();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes cmbFornitore
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbFornitore() {
		if (cmbFornitore == null) {
			try {
				cmbFornitore = new JComboBox();
				cmbFornitore.setBounds(new Rectangle(8, 20, 441, 25)); // Generated
				/*
				 * cmbFornitore.addActionListener(new
				 * java.awt.event.ActionListener() { public void
				 * actionPerformed(java.awt.event.ActionEvent e) {
				 * 
				 * int cod=estraiCodice((String)cmbFornitore.getSelectedItem());
				 * ricercaArticoliByFornitore(cod); } });
				 */
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbFornitore;
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
				jScrollPane.setBounds(new Rectangle(8, 24, 649, 329)); // Generated
				jScrollPane.setViewportView(getTblArtByForn()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
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
						.addTab(
								"Ricerca Articoli per Fornitore",
								null,
								getPnlArticoliByFornitore(),
								"Da questa finestra è possibile ricercare tutti gli articoli riferiti al fornitore scelto"); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlArticoliByFornitore
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlArticoliByFornitore() {
		if (pnlArticoliByFornitore == null) {
			try {
				pnlArticoliByFornitore = new JPanel();
				pnlArticoliByFornitore.setLayout(null); // Generated
				pnlArticoliByFornitore.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlArticoliByFornitore.add(getPnlFornitore(), null); // Generated
				pnlArticoliByFornitore.add(getPnlRisultati(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlArticoliByFornitore;
	}

	/**
	 * This method initializes pnlFornitore
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlFornitore() {
		if (pnlFornitore == null) {
			try {
				pnlFornitore = new JPanel();
				pnlFornitore.setLayout(null); // Generated
				pnlFornitore.setBounds(new Rectangle(8, 8, 667, 57)); // Generated
				pnlFornitore.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Fornitore", TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new Font("Dialog",
								Font.BOLD, 12), new Color(51, 51, 51))); // Generated
				pnlFornitore.add(getCmbFornitore(), null); // Generated
				pnlFornitore.add(getBtnChiudi(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlFornitore;
	}

	/**
	 * This method initializes pnlRisultati
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlRisultati() {
		if (pnlRisultati == null) {
			try {
				pnlRisultati = new JPanel();
				pnlRisultati.setLayout(null); // Generated
				pnlRisultati.setBounds(new Rectangle(8, 72, 669, 361)); // Generated
				pnlRisultati.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Risultati", TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new Font("Dialog",
								Font.BOLD, 12), new Color(51, 51, 51))); // Generated
				pnlRisultati.add(getJScrollPane(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlRisultati;
	}

	/**
	 * This method initializes tblArtByForn
	 * 
	 * @return javax.swing.JTable
	 */
	private JXTable getTblArtByForn() {
		if (tblArtByForn == null) {
			try {
				tblArtByForn = new JXTable();
				
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return tblArtByForn;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 500);
		this.setTitle("Ricerche Archivio"); // Generated
		this.setContentPane(getJContentPane());

		// centriamo la finestra al centro schermo
		UtilGUI.centraDialog(this);

		// carichiamo i dati nel combo box per effettuare ricerche
		caricaDatiRicercaArtByForn();
	}

	/**
	 * @param string
	 */
	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE",
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * @param cod
	 */
	private void ricercaArticoliByFornitore(int cod) {
		try {

			ArticoliByFornitoreModel model = new ArticoliByFornitoreModel(dbm,
					cod);
			tblArtByForn.setModel(model);
			configuraTabella();
			
		} catch (SQLException e) {
			messaggioErroreCampo("Errore nella ricerca degli articoli");
			e.printStackTrace();
		}

	}

	private void configuraTabella() {
		TableColumn col = tblArtByForn.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
		
		col = tblArtByForn.getColumn("codice_articolo");
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.LEFT);
		col.setCellRenderer(cellRenderer);
		col.setMinWidth(0);
		col.setMaxWidth(150);
		col.setPreferredWidth(150);
		
		col = tblArtByForn.getColumn("prezzo_acquisto");
		 cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
		col.setCellRenderer(cellRenderer);
		col.setMinWidth(0);
		col.setMaxWidth(100);
		col.setPreferredWidth(100);
		
		
		
		col = tblArtByForn.getColumn("prezzo_dettaglio");
		 cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
		col.setCellRenderer(cellRenderer);
		col.setMinWidth(0);
		col.setMaxWidth(100);
		col.setPreferredWidth(100);
		
		col = tblArtByForn.getColumn("iva");
		 cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		col.setCellRenderer(cellRenderer);
		col.setMinWidth(0);
		col.setMaxWidth(100);
		col.setPreferredWidth(100);
		
		tblArtByForn.packAll();
		
	}

} // @jve:decl-index=0:visual-constraint="10,10"
