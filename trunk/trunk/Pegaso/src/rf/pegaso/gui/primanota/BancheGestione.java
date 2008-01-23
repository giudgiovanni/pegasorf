package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.model.BancheModel;
import rf.pegaso.db.tabelle.Banca;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.gui.UtilGUI;

public class BancheGestione extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlNord = null;

	private JPanel pnlCentro = null;

	private JButton btnNuovaBanca = null;

	private JButton btnModificaBanca = null;

	private JButton btnEliminaBanca = null;

	private JButton btnVisualizzaConti = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblBanche = null;

	/**
	 * @param owner
	 */
	public BancheGestione(Frame owner) {
		super(owner,true);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 650);
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
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
			jContentPane.add(getNlNord(), BorderLayout.NORTH);
			jContentPane.add(getPnlCentro(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes nlNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getNlNord() {
		if (pnlNord == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlNord = new JPanel();
			pnlNord.setLayout(flowLayout);
			pnlNord.setPreferredSize(new Dimension(0, 50));
			pnlNord.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			pnlNord.add(getBtnNuovaBanca(), null);
			pnlNord.add(getBtnModificaBanca(), null);
			pnlNord.add(getBtnVisualizzaConti(), null);
			pnlNord.add(getBtnEliminaBanca(), null);
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
			pnlCentro.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return pnlCentro;
	}

	/**
	 * This method initializes btnNuovaBanca
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovaBanca() {
		if (btnNuovaBanca == null) {
			btnNuovaBanca = new JButton();
			btnNuovaBanca.setText("Nuova Banca");
			btnNuovaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							nuovaBanca();
						}
					});
		}
		return btnNuovaBanca;
	}

	protected void nuovaBanca() {
		NuovaBanca b=new NuovaBanca(this);
		b.setVisible(true);

	}

	/**
	 * This method initializes btnModificaBanca
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnModificaBanca() {
		if (btnModificaBanca == null) {
			btnModificaBanca = new JButton();
			btnModificaBanca.setText("Modifica Banca");
			btnModificaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							modificaBanca();
						}
					});

		}
		return btnModificaBanca;
	}

	protected void modificaBanca() {
		if (tblBanche.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblBanche.getSelectedRow();
		int id = ((Number) tblBanche.getValueAt(riga, 0)).intValue();
		NuovaBanca b=new NuovaBanca(this,id);
		b.setVisible(true);

	}

	/**
	 * This method initializes btnEliminaBanca
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEliminaBanca() {
		if (btnEliminaBanca == null) {
			btnEliminaBanca = new JButton();
			btnEliminaBanca.setText("Elimina Banca");
			btnEliminaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							eliminaBanca();
						}
					});
		}
		return btnEliminaBanca;
	}

	protected void eliminaBanca() {
		if (tblBanche.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler eliminare\nla banca selezionata?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;

		int riga = tblBanche.getSelectedRow();
		int id = ((Number) tblBanche.getValueAt(riga, 0)).intValue();
		Banca b=new Banca();
		try {
			b.deleteBanca(id);
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes btnVisualizzaConti
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnVisualizzaConti() {
		if (btnVisualizzaConti == null) {
			btnVisualizzaConti = new JButton();
			btnVisualizzaConti.setText("Gestione Conti");
			btnVisualizzaConti
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							gestioneConti();
						}
					});
		}
		return btnVisualizzaConti;
	}

	protected void gestioneConti() {
		if (tblBanche.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int riga = tblBanche.getSelectedRow();
		int id = ((Number) tblBanche.getValueAt(riga, 0)).intValue();
		ContiCorrentiGestione g=new ContiCorrentiGestione(this,id);
		g.setVisible(true);

	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblBanche());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblBanche
	 *
	 * @return javax.swing.JTable
	 */
	private JXTable getTblBanche() {
		if (tblBanche == null) {
			BancheModel m = null;
			try {
				m = new BancheModel();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tblBanche = new JXTable(m);
			DBManager.getIstanceSingleton().addDBStateChange(m);
			tblBanche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// impostiamo le varie colonne
			TableColumn col = tblBanche.getColumnModel().getColumn(0);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);
		}
		return tblBanche;
	}

}
