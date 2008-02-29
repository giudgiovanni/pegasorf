/**
 * 
 */
package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.DocumentoModel;
import rf.pegaso.db.tabelle.Documento;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 * 
 */
public class DocumentiGestione extends JDialog {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNuovo) {
				nuovoDocumento();
			} else if (e.getSource() == btnModifica) {
				modificaDocumento();
			} else if (e.getSource() == btnElimina) {
				eliminaDocumento();
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnElimina = null;

	private JButton btnModifica = null;

	private JButton btnNuovo = null;

	private DBManager dbm;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JSeparator jSeparator = null;

	private JPanel pnlCentrale = null;

	private JPanel pnlNord = null;

	private JXTable tblDocumenti = null;

	/**
	 * @param owner
	 */
	public DocumentiGestione(Frame owner) {
		super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * Elimina il reparto dalla base di dati
	 */
	private void eliminaDocumento() {
		if (tblDocumenti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare il documento selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblDocumenti.getSelectedRow();
		int iddocumento = ((Long) tblDocumenti.getValueAt(riga, 0)).intValue();
		Documento r = new Documento();
		try {
			r.deleteDocumento(iddocumento);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idDocumento errato",
					"ERRORE", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

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
				btnElimina.setText("Elimina"); // Generated
				btnElimina.setPreferredSize(new Dimension(82, 26)); // Generated
				btnElimina.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnElimina;
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
				btnModifica.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnModifica;
	}

	/**
	 * This method initializes btnNuovo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovo() {
		if (btnNuovo == null) {
			try {
				btnNuovo = new JButton();
				btnNuovo.setText("Nuovo"); // Generated
				btnNuovo.setText("Nuovo"); // Generated
				btnNuovo.setPreferredSize(new Dimension(82, 26)); // Generated
				btnNuovo.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnNuovo;
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
			jContentPane.add(getPnlNord(), BorderLayout.NORTH); // Generated
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
				jScrollPane.setViewportView(getTblDocumenti()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jSeparator
	 * 
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			try {
				jSeparator = new JSeparator();
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jSeparator;
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
				pnlCentrale.add(getJScrollPane(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
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
			try {
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridx = 3; // Generated
				gridBagConstraints3.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.gridx = 2; // Generated
				gridBagConstraints2.insets = new Insets(0, 10, 0, 10); // Generated
				gridBagConstraints2.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 1; // Generated
				gridBagConstraints1.insets = new Insets(0, 10, 0, 10); // Generated
				gridBagConstraints1.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(0, 10, 0, 10); // Generated
				gridBagConstraints.anchor = GridBagConstraints.CENTER; // Generated
				gridBagConstraints.fill = GridBagConstraints.NONE; // Generated
				gridBagConstraints.gridy = 0; // Generated
				pnlNord = new JPanel();
				pnlNord.setLayout(new GridBagLayout()); // Generated
				pnlNord.setPreferredSize(new Dimension(0, 40)); // Generated
				pnlNord.add(getBtnNuovo(), gridBagConstraints); // Generated
				pnlNord.add(getBtnModifica(), gridBagConstraints1); // Generated
				pnlNord.add(getBtnElimina(), gridBagConstraints2); // Generated
				pnlNord.add(getJSeparator(), gridBagConstraints3); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes tblDocumenti
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTblDocumenti() {
		if (tblDocumenti == null) {
			try {
				DocumentoModel modello = new DocumentoModel(dbm);
				dbm.addDBStateChange(modello);
				tblDocumenti = new JXTable();
				tblDocumenti
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblDocumenti.setModel(modello);
				tblDocumenti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblDocumenti.packAll();
				tblDocumenti.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblDocumenti.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblDocumenti;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(617, 316);
		this.setTitle("Gestione Documenti"); // Generated
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
	}

	/**
	 * 
	 */
	private void modificaDocumento() {
		// Apre la Dialog delegata alla modifica
		// di un reparto
		if (tblDocumenti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblDocumenti.getSelectedRow();
		int idDocumenti = ((Long) tblDocumenti.getValueAt(riga, 0)).intValue();
		DocumentoAddMod mod = new DocumentoAddMod(this, dbm, idDocumenti,
				DocumentoAddMod.MOD);
		mod.setVisible(true);

	}

	/**
	 * 
	 */
	private void nuovoDocumento() {
		// Apre la finestra delegata all'inserimento
		// del nuovo reparto
		DocumentoAddMod add = new DocumentoAddMod(this, dbm,
				DocumentoAddMod.ADD);
		add.setVisible(true);

	}

} // @jve:decl-index=0:visual-constraint="10,10"
