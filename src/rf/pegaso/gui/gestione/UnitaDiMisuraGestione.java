/**
 * 
 */
package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.UnitaDiMisuraModel;
import rf.pegaso.db.tabelle.UnitaDiMisura;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 * 
 */
public class UnitaDiMisuraGestione extends JDialog {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNuovo) {
				nuovoUnitaMisura();
			} else if (e.getSource() == btnModifica) {
				modificaUnitaMisura();
			} else if (e.getSource() == btnElimina) {
				eliminaUnitaMisura();
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

	private JXTable tblUM = null;

	/**
	 * @param owner
	 */
	public UnitaDiMisuraGestione(Frame owner) {
		super(owner, true);
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * Elimina il reparto dalla base di dati
	 */
	private void eliminaUnitaMisura() {
		if (tblUM.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int scelta = JOptionPane
				.showConfirmDialog(
						this,
						"Sei sicuro di voler eliminare\nl'unit\u00E0 di misura selezionata?",
						"AVVISO", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
		if (scelta == JOptionPane.NO_OPTION)
			return;
		int riga = tblUM.getSelectedRow();
		int idUm = ((Long) tblUM.getValueAt(riga, 0)).intValue();
		UnitaDiMisura um = new UnitaDiMisura();
		try {
			um.deleteUnitaDiMisura(idUm);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idUm errato", "ERRORE",
					JOptionPane.WARNING_MESSAGE);
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
				btnElimina.setBounds(new Rectangle(365, 7, 82, 26)); // Generated
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
				btnModifica.setBounds(new Rectangle(263, 7, 82, 26)); // Generated
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
				btnNuovo.setBounds(new Rectangle(161, 7, 82, 26)); // Generated
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
				jScrollPane.setViewportView(getTblUnitaMisura()); // Generated
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
				jSeparator.setBounds(new Rectangle(0, 0, 0, 0)); // Generated
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
				pnlNord = new JPanel();
				pnlNord.setLayout(null); // Generated
				pnlNord.setPreferredSize(new Dimension(0, 40)); // Generated
				pnlNord.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlNord.add(getBtnNuovo(), null); // Generated
				pnlNord.add(getBtnModifica(), null); // Generated
				pnlNord.add(getBtnElimina(), null); // Generated
				pnlNord.add(getJSeparator(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes tblReparti
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTblUnitaMisura() {
		if (tblUM == null) {
			try {
				UnitaDiMisuraModel modello = new UnitaDiMisuraModel(dbm);
				dbm.addDBStateChange(modello);
				tblUM = new JXTable();
				tblUM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblUM.setModel(modello);
				tblUM.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblUM.packAll();
				tblUM.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblUM.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblUM;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(617, 316);
		this.setTitle("Gestione Unit\u00E0 di Misura"); // Generated
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
	}

	/**
	 * 
	 */
	private void modificaUnitaMisura() {
		// Apre la Dialog delegata alla modifica
		// di un reparto
		if (tblUM.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblUM.getSelectedRow();
		int idUm = ((Long) tblUM.getValueAt(riga, 0)).intValue();
		UnitaMisuraMod mod = new UnitaMisuraMod(this, dbm, idUm);
		mod.setVisible(true);

	}

	/**
	 * 
	 */
	private void nuovoUnitaMisura() {
		// Apre la finestra delegata all'inserimento
		// del nuovo reparto
		UnitaMisuraAdd add = new UnitaMisuraAdd(this, dbm);
		add.setVisible(true);

	}

} // @jve:decl-index=0:visual-constraint="10,10"
