/**
 *
 */
package rf.pegaso.gui.utility;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 *
 */
public class ModificaQuantitaRiga extends JDialog {

	private JPanel jPanel = null;
	private JLabel lblQta = null;
	private JFormattedTextField txtQta = null;
	private JButton btnOK = null;
	private double[] qta;

	/**
	 * This method initializes
	 *
	 */


	public ModificaQuantitaRiga(double[] qta, JFrame padre) {
		super(padre, true);
		this.qta = qta;

		initialize();
	}

	/**
	 * This method initializes this
	 *
	 */
	private void initialize() {
		try {
			this.setSize(new Dimension(219, 142)); // Generated
			this.setTitle("Modifica Quantit\u00E0 Riga"); // Generated
			this.setContentPane(getJPanel());
			UtilGUI.centraDialog(this);

		} catch (java.lang.Throwable e) {
			// Do Something
		}
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			try {
				lblQta = new JLabel();
				lblQta.setBounds(new Rectangle(18, 48, 91, 28)); // Generated
				lblQta.setFont(new Font("Dialog", Font.PLAIN, 12)); // Generated
				lblQta.setText("Nuova Quantit\u00E0"); // Generated
				jPanel = new JPanel();
				jPanel.setLayout(null); // Generated
				jPanel.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel.add(lblQta, null); // Generated
				jPanel.add(getTxtQta(), null); // Generated
				jPanel.add(getBtnOK(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel;
	}

	/**
	 * This method initializes txtQta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtQta() {
		if (txtQta == null) {
			try {
				NumberFormat format = NumberFormat.getIntegerInstance();
				txtQta = new JFormattedTextField(format);
				txtQta.setBounds(new Rectangle(112, 48, 57, 29)); // Generated
				txtQta.setFont(new Font("Dialog", Font.PLAIN, 14));
				txtQta.setValue(new Double(qta[0]));
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtQta;
	}

	/**
	 * This method initializes btnOK
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			try {
				btnOK = new JButton();
				btnOK.setBounds(new Rectangle(16, 12, 85, 29)); // Generated
				btnOK.setText("OK"); // Generated
				btnOK.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						modifica();
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnOK;
	}

	/**
	 *
	 */
	protected void modifica() {
		qta[0] = ((Double) txtQta.getValue()).doubleValue();
		this.dispose();

	}

} // @jve:decl-index=0:visual-constraint="10,10"
