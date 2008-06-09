package rf.pegaso.gui.internalframe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXLoginPanel;

import rf.myswing.security.MyJDBCLoginService;
import rf.pegaso.gui.primanota.BancheGestione;
import rf.pegaso.gui.primanota.PrimaNotaGUI;
import rf.utility.db.DBManager;

public class PrimaNotaInternalFrame extends JInternalFrame {

	private JPanel jContentPane = null;
	private JButton btnPrimaNota = null;
	private JFrame padre;
	private JButton btnBanca = null;

	public PrimaNotaInternalFrame(JFrame padre) {
		this.padre=padre;
		initialize();
	}

	private void initialize() {
		this.setSize(new Dimension(693, 355)); // Generated
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setContentPane(getJContentPane()); // Generated
		this.setTitle("Prima Nota"); // Generated
		this.setMaximizable(true); // Generated
		this.setIconifiable(true); // Generated
		this.setClosable(true); // Generated
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			try {
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 1;
				gridBagConstraints1.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints1.gridy = 0;
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints.gridy = 0; // Generated
				jContentPane = new JPanel();
				jContentPane.setLayout(new GridBagLayout()); // Generated
				jContentPane.add(getBtnConfigAdmin(), gridBagConstraints); // Generated
				jContentPane.add(getBtnBanca(), gridBagConstraints1);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnConfigAdmin
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnConfigAdmin() {
		if (btnPrimaNota == null) {
			try {
				btnPrimaNota = new JButton();
				btnPrimaNota.setPreferredSize(new Dimension(120, 70)); // Generated
				btnPrimaNota
						.setText("<html><center>Prima Nota</html>"); // Generated
				btnPrimaNota
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								apriPrimaNota();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnPrimaNota;
	}

	protected void apriPrimaNota() {
		MyJDBCLoginService jdbc=null;
		try {
			jdbc = new MyJDBCLoginService(DBManager
					.getIstanceSingleton());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JXLoginPanel.Status stato = JXLoginPanel.showLoginDialog(getParent(),
				jdbc);
		if (stato == JXLoginPanel.Status.SUCCEEDED) {
			PrimaNotaGUI pn=new PrimaNotaGUI();
			pn.setVisible(true);
		}
		
		
		

	}

	/**
	 * This method initializes btnBanca
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnBanca() {
		if (btnBanca == null) {
			btnBanca = new JButton();
			btnBanca.setPreferredSize(new Dimension(120, 70));
			btnBanca.setText("Banche");
			btnBanca.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apriGestioneBanche(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnBanca;
	}

	protected void apriGestioneBanche() {
		BancheGestione b=new BancheGestione(this.padre);
		b.setVisible(true);

	}

} // @jve:decl-index=0:visual-constraint="10,10"
