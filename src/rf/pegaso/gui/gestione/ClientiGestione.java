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
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.ClienteModel;
import rf.pegaso.db.tabelle.Cliente;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 *
 */
public class ClientiGestione extends JDialog {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNuovo) {
				nuovoCliente();
			} else if (e.getSource() == btnModifica) {
				modificaCliente();
			} else if (e.getSource() == btnElimina) {
				eliminaCliente();
			} else if (e.getSource() == btnStampa) {
				stampaClienti();
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

	private JXTable tblClienti = null;

	private JButton btnStampa = null;

	private Frame padre;

	/**
	 * @param owner
	 */
	public ClientiGestione(Frame owner) {
		super(owner);
		this.padre = owner;
		this.padre.setEnabled(false);
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	protected void stampaClienti() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'elenco clienti?", "AVVISO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			JasperViewer.viewReport(JasperFillManager.fillReport(
					"report/elenco_clienti.jasper", null, this.dbm
							.getConnessione()), false);

		} catch (JRException e) {
			//
			e.printStackTrace();
		}

	}

	/**
	 * Elimina il reparto dalla base di dati
	 */
	private void eliminaCliente() {
		if (tblClienti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare il cliente selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblClienti.getSelectedRow();
		int idCliente = ((Number) tblClienti.getValueAt(riga, 0)).intValue();
		Cliente c = new Cliente();
		try {
			c.deleteCliente(idCliente);
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
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
				btnElimina.setBounds(new Rectangle(320, 8, 82, 26)); // Generated
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
				btnModifica.setBounds(new Rectangle(110, 8, 82, 26)); // Generated
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
				btnNuovo.setBounds(new Rectangle(8, 8, 82, 26)); // Generated
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
				jScrollPane.setViewportView(getTblClienti()); // Generated
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
				pnlNord.add(getBtnStampa(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes tblClienti
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblClienti() {
		if (tblClienti == null) {
			try {
				ClienteModel modello = new ClienteModel(dbm);
				dbm.addDBStateChange(modello);
				tblClienti = new JXTable();
				tblClienti
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblClienti.setModel(modello);
				tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblClienti.packAll();
				tblClienti.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblClienti.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblClienti;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setResizable(false); // Generated
		this.setTitle("Gestione Clienti"); // Generated
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				padre.setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				padre.setEnabled(true);
			}
		});
		UtilGUI.centraDialog(this);
	}

	/**
	 *
	 */
	private void modificaCliente() {
		// Apre la Dialog delegata alla modifica
		// di un reparto
		if (tblClienti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblClienti.getSelectedRow();
		int idCliente = ((Number) tblClienti.getValueAt(riga, 0)).intValue();
		ClientiMod mod = new ClientiMod(this, dbm, idCliente);
		mod.setVisible(true);

	}

	/**
	 *
	 */
	private void nuovoCliente() {
		ClientiAdd add = new ClientiAdd(this, dbm);
		add.setVisible(true);

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
				btnStampa.setBounds(new Rectangle(212, 8, 85, 26)); // Generated
				btnStampa.setText("Stampa");
				btnStampa.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnStampa;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
