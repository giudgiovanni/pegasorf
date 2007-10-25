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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.model.ArticoloModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 * 
 */
public class ArticoliGestione extends JFrame {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNuovo) {
				nuovoArticolo();
			} else if (e.getSource() == btnModifica) {
				modificaArticolo();
			} else if (e.getSource() == btnElimina) {
				eliminaArticolo();
			} else if (e.getSource() == btnChiudi) {
				dispose();
			} else if (e.getSource() == btnDuplica) {
				duplicaArticolo();
			} else if (e.getSource() == btnStampa) {
				stampaArticoli();
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnElimina = null;

	private JButton btnModifica = null;

	private JButton btnNuovo = null;

	private DBManager dbm;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JSeparator jSeparator = null;

	//private Frame padre;

	private JPanel pnlCentrale = null;

	private JPanel pnlNord = null;

	private JXTable tblArticoli = null;

	private JButton btnDuplica = null;

	private JButton btnStampa = null;

	/**
	 * @param owner
	 */
	public ArticoliGestione() {
		//this.padre = padre;
		//this.padre.setEnabled(false);
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	protected void stampaArticoli() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'elenco articoli?", "AVVISO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			JasperViewer.viewReport(JasperFillManager.fillReport(
					"report/elenco_articoli.jasper", null, this.dbm
							.getConnessione()), false);

		} catch (JRException e) {
			//
			e.printStackTrace();
		}

	}

	public void duplicaArticolo() {
		if (tblArticoli.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un Articolo",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler duplicare\nl'articolo selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;

		int riga = tblArticoli.getSelectedRow();
		int idArticolo = ((Long) tblArticoli.getValueAt(riga, 0)).intValue();
		Articolo r = new Articolo();
		int idNewArticolo = 0;
		try {
			idNewArticolo = r.duplicaArticolo(idArticolo);

			// ora apriamo la finestra per modificare i dati
			ArticoliAddMod mod = new ArticoliAddMod((JFrame)this, idNewArticolo,ArticoliAddMod.MOD);
			mod.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Elimina il reparto dalla base di dati
	 */
	private void eliminaArticolo() {
		if (tblArticoli.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler eliminare\nl'articolo selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int nSel = tblArticoli.getSelectedRowCount();
		int riga = 0;
		int righe[] = null;
		if (nSel == 1) {
			riga = tblArticoli.getSelectedRow();
			int idArticolo = ((Long) tblArticoli.getValueAt(riga, 0))
					.intValue();
			Articolo r = new Articolo();
			try {
				r.deleteArticolo(idArticolo);
			} catch (IDNonValido e) {
				JOptionPane.showMessageDialog(this, "Valore idArticolo errato",
						"ERRORE", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		} else {
			righe = tblArticoli.getSelectedRows();
			int idRighe[] = new int[righe.length];
			// Prendiamo tutti gli id degli articoli
			// che sono stati selezionati
			for (int i = 0; i < righe.length; i++) {
				int idArticolo = ((Long) tblArticoli.getValueAt(righe[i], 0))
						.intValue();
				idRighe[i] = idArticolo;
			}
			// Cancelliamo tutte le righe
			// che sono state selezionate
			for (int i = 0; i < idRighe.length; i++) {
				int idArticolo = idRighe[i];
				Articolo r = new Articolo();
				try {
					r.deleteArticolo(idArticolo);
				} catch (IDNonValido e) {
					JOptionPane.showMessageDialog(this,
							"Valore idArticolo errato", "ERRORE",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}
		}

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
				btnChiudi.setBounds(new Rectangle(471, 5, 77, 25)); // Generated
				btnChiudi.setText("Chiudi");
				btnChiudi.addActionListener(new MyActionListener());
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
				btnElimina.setText("Elimina"); // Generated
				btnElimina.setBounds(new Rectangle(378, 5, 82, 26)); // Generated
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
				btnModifica.setBounds(new Rectangle(102, 5, 82, 26)); // Generated
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
				btnNuovo.setBounds(new Rectangle(9, 5, 82, 26)); // Generated
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
				jScrollPane.setViewportView(getTblArticoli()); // Generated
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
				pnlNord.add(getBtnChiudi(), null); // Generated
				pnlNord.add(getBtnDuplica(), null); // Generated
				pnlNord.add(getBtnStampa(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes tblArticoli
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTblArticoli() {
		if (tblArticoli == null) {
			try {
				ArticoloModel modello = new ArticoloModel(dbm);
				dbm.addDBStateChange(modello);
				tblArticoli = new JXTable();
				// tblArticoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblArticoli.setModel(modello);
				tblArticoli.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblArticoli.packAll();
				tblArticoli.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblArticoli.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblArticoli;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(700, 500));
		this.setTitle("Gestione Articoli");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}
		});
		setExtendedState(MAXIMIZED_BOTH);
		UtilGUI.centraFrame(this);

	}

	/**
	 * 
	 */
	private void modificaArticolo() {
		// Apre la Dialog delegata alla modifica
		// di un reparto
		if (tblArticoli.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblArticoli.getSelectedRow();
		int idArticolo = ((Long) tblArticoli.getValueAt(riga, 0)).intValue();
		ArticoliAddMod mod = new ArticoliAddMod(this,idArticolo,ArticoliAddMod.MOD);
		mod.setVisible(true);
		tblArticoli.setRowSelectionInterval(riga, riga);

	}

	/**
	 * 
	 */
	private void nuovoArticolo() {
		// Apre la finestra delegata all'inserimento
		// del nuovo reparto
		ArticoliAddMod add = new ArticoliAddMod(this, ArticoliAddMod.ADD);
		add.setVisible(true);

	}

	/**
	 * This method initializes btnDuplica
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDuplica() {
		if (btnDuplica == null) {
			try {
				btnDuplica = new JButton();
				btnDuplica.setBounds(new Rectangle(195, 5, 80, 26)); // Generated
				btnDuplica.setText("Duplica");
				btnDuplica.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnDuplica;
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
				btnStampa.setBounds(new Rectangle(286, 5, 81, 25)); // Generated
				btnStampa.setText("Stampa");
				btnStampa.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnStampa;
	}

} // @jve:decl-index=0:visual-constraint="10,10"