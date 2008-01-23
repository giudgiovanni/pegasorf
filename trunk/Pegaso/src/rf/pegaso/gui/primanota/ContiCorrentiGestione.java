package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Dialog;
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
import rf.pegaso.db.model.ContiCorrentiModel;
import rf.pegaso.db.tabelle.ContoCorrente;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.gui.UtilGUI;

public class ContiCorrentiGestione extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlNord = null;

	private JPanel pnlCentro = null;

	private JButton btnNuovaBanca = null;

	private JButton btnModificaBanca = null;

	private JButton btnEliminaBanca = null;

	private JScrollPane jScrollPane = null;

	private JXTable tblContiCorrenti = null;

	private int idbanca;

	/**
	 * @param owner
	 */
	public ContiCorrentiGestione(Frame owner,int idbanca) {
		super(owner,true);
		this.idbanca=idbanca;
		initialize();
	}

	public ContiCorrentiGestione(Dialog owner,int idbanca) {
		super(owner,true);
		this.idbanca=idbanca;
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
			btnNuovaBanca.setText("Nuovo Conto");
			btnNuovaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							nuovoConto();
						}
					});
		}
		return btnNuovaBanca;
	}

	protected void nuovoConto() {
		NuovoConto b=new NuovoConto(this,this.idbanca);
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
			btnModificaBanca.setText("Modifica Conto");
			btnModificaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							modificaConto();
						}
					});

		}
		return btnModificaBanca;
	}

	protected void modificaConto() {
		if (tblContiCorrenti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblContiCorrenti.getSelectedRow();
		int id = ((Number) tblContiCorrenti.getValueAt(riga, 0)).intValue();
		NuovoConto b=new NuovoConto(this,id,this.idbanca);
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
			btnEliminaBanca.setText("Elimina Conto");
			btnEliminaBanca
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							eliminaConto();
						}
					});
		}
		return btnEliminaBanca;
	}

	protected void eliminaConto() {
		if (tblContiCorrenti.getSelectedRow() <= -1) {
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

		int riga = tblContiCorrenti.getSelectedRow();
		int id = ((Number) tblContiCorrenti.getValueAt(riga, 0)).intValue();
		ContoCorrente b=new ContoCorrente();
		try {
			b.deleteConto(id);
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblContiCorrenti());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblBanche
	 *
	 * @return javax.swing.JTable
	 */
	private JXTable getTblContiCorrenti() {
		if (tblContiCorrenti == null) {
			ContiCorrentiModel m = null;
			try {
				m = new ContiCorrentiModel(this.idbanca);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tblContiCorrenti = new JXTable(m);
			DBManager.getIstanceSingleton().addDBStateChange(m);
			tblContiCorrenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// impostiamo le varie colonne
			TableColumn col = tblContiCorrenti.getColumnModel().getColumn(0);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);
		}
		return tblContiCorrenti;
	}

}
