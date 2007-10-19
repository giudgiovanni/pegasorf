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

import org.jdesktop.swingx.JXLoginPanel;

import rf.myswing.security.MyJDBCLoginService;
import rf.myswing.util.ModalFrameUtil;
import rf.pegaso.db.DBManager;
import rf.pegaso.gui.configurazioni.RootConfigGUI;

public class ConfigurazioneInternalFrame extends JInternalFrame {

	private JPanel jContentPane = null;
	private JButton btnConfigAdmin = null;
	private JButton btnConfigAppl = null;
	private JFrame padre;

	public ConfigurazioneInternalFrame(JFrame padre) {
		this.padre=padre;
		initialize();
	}

	private void initialize() {
		this.setSize(new Dimension(693, 355)); // Generated
		this.setContentPane(getJContentPane()); // Generated
		this.setTitle("Configurazione applicazione"); // Generated
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
				gridBagConstraints1.gridx = 1; // Generated
				gridBagConstraints1.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints1.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints.gridy = 0; // Generated
				jContentPane = new JPanel();
				jContentPane.setLayout(new GridBagLayout()); // Generated
				jContentPane.add(getBtnConfigAdmin(), gridBagConstraints); // Generated
				jContentPane.add(getBtnConfigAppl(), gridBagConstraints1); // Generated
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
		if (btnConfigAdmin == null) {
			try {
				btnConfigAdmin = new JButton();
				btnConfigAdmin.setPreferredSize(new Dimension(120, 70)); // Generated
				btnConfigAdmin
						.setText("<html><center>Configurazione<br>Amministratore</html>"); // Generated
				btnConfigAdmin
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								apriAdminConf();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnConfigAdmin;
	}

	protected void apriAdminConf() {


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
			System.out.println("ok");
			RootConfigGUI root=new RootConfigGUI();
			ModalFrameUtil.showAsModal(root, padre);
			
			
		}

	}

	/**
	 * This method initializes btnConfigAppl
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnConfigAppl() {
		if (btnConfigAppl == null) {
			try {
				btnConfigAppl = new JButton();
				btnConfigAppl.setPreferredSize(new Dimension(120, 70)); // Generated
				btnConfigAppl
						.setText("<html><center>Configurazione<br>Applicazione</html>"); // Generated

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnConfigAppl;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
