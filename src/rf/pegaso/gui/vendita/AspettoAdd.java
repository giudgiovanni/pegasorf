package rf.pegaso.gui.vendita;

import it.infolabs.hibernate.Aspetto;
import it.infolabs.hibernate.AspettoHome;
import it.infolabs.hibernate.exception.PersistEntityException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

/**
 * @author Hunter
 *
 */
public class AspettoAdd extends JDialog {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi) {
				dispose();
			}
			if (e.getSource() == btnInserisci) {
				inserisci();
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnInserisci = null;

	//private DBManager dbm;

	private JPanel jContentPane = null;

	private JLabel jLabel1 = null;

	private JPanel jPanel = null;

	private JPanel pnlCentrale = null;

	private JTextField txtNome = null;

	/**
	 * @param owner
	 */
	public AspettoAdd(JDialog owner){//, DBManager dbm) {
		super(owner, true);
		//this.dbm = dbm;
		initialize();
	}


	public AspettoAdd(JFrame owner){//, DBManager dbm) {
		super(owner, true);
		//this.dbm = dbm;
		initialize();
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
				btnChiudi.setText("Chiudi"); // Generated
				btnChiudi.setBounds(new Rectangle(160, 7, 83, 26));
				btnChiudi.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnInserisci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			try {
				btnInserisci = new JButton();
				btnInserisci.setText("Inserisci"); // Generated
				btnInserisci.setBounds(new Rectangle(47, 7, 83, 26));
				btnInserisci.addActionListener(new MyActionListener());
				;
			} catch (java.lang.Throwable e) {
			}
		}
		return btnInserisci;
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
			jContentPane.add(getJPanel(), BorderLayout.SOUTH); // Generated
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
			try {
				jPanel = new JPanel();
				jPanel.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel.setLayout(null); // Generated
				jPanel.setPreferredSize(new Dimension(0, 40)); // Generated
				jPanel.add(getBtnInserisci(), null);
				jPanel.add(getBtnChiudi(), null);
			} catch (java.lang.Throwable e) {
			}
		}
		return jPanel;
	}

	/**
	 * This method initializes pnlCentrale
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			try {
				jLabel1 = new JLabel();
				jLabel1.setText("Inserisci la voce"); // Generated
				jLabel1.setBounds(new Rectangle(30, 26, 110, 16)); // Generated
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(null); // Generated
				pnlCentrale.add(jLabel1, null); // Generated
				pnlCentrale.add(getTxtNome(), null); // Generated
			} catch (java.lang.Throwable e) {
			}
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes txtNome
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNome() {
		if (txtNome == null) {
			try {
				txtNome = new JTextField();
				txtNome.setPreferredSize(new Dimension(200, 20)); // Generated
				txtNome.setBounds(new Rectangle(30, 42, 230, 20)); // Generated
				txtNome.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
			}
		}
		return txtNome;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 162);
		this.setTitle("Inserimento Nuovo Aspetto"); // Generated
		this.setContentPane(getJContentPane());

		UtilGUI.centraDialog(this);
	}

	private void inserisci() {
		Aspetto a = new Aspetto();
		a.setNome(txtNome.getText());
		AspettoHome.getInstance().begin();
		AspettoHome.getInstance().persist(a);
		dispose();

	}

}
