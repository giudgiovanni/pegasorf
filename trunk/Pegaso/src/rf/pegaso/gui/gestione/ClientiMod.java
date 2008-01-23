/**
 *
 */
package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

/**
 * @author Hunter
 *
 */
public class ClientiMod extends JDialog {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi) {
				dispose();
			}
			if (e.getSource() == btnModifica) {
				modifica();
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnModifica = null;

	private DBManager dbm;

	private int idCliente;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JScrollPane jScrollPane = null;

	private JTabbedPane jTabbedPane = null;

	private JLabel lblCap = null;

	private JLabel lblCell = null;

	private JLabel lblCitta = null;

	private JLabel lblCodFisc = null;

	private JLabel lblCognome = null;

	private JLabel lblEmail = null;

	private JLabel lblFax = null;

	private JLabel lblNome = null;

	private JLabel lblNote = null;

	private JLabel lblPiva = null;

	private JLabel lblProvincia = null;

	private JLabel lblTel = null;

	private JLabel lblVia = null;

	private JLabel lblWebSite = null;

	private JPanel pnlAltriDati = null;

	private JPanel pnlCentrale = null;

	private JPanel pnlDatiPersonali = null;

	private JTextField txtCap = null;

	private JTextField txtCell = null;

	private JTextField txtCitta = null;

	private JTextField txtCodFisc = null;

	private JTextField txtCognome = null;

	private JTextField txtEmail = null;

	private JTextField txtFax = null;

	private JTextField txtNome = null;

	private JTextArea txtNote = null;

	private JTextField txtPiva = null;

	private JTextField txtProvincia = null;

	private JTextField txtTel = null;

	private JTextField txtVia = null;

	private JTextField txtWebSite = null;

	/**
	 * @param owner
	 */
	public ClientiMod(JDialog owner, DBManager dbm, int idCliente) {
		super(owner, true);
		this.dbm = dbm;
		this.idCliente = idCliente;
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
				btnChiudi.setText("Chiudi");
				btnChiudi.setPreferredSize(new Dimension(83, 26)); // Generated
				btnChiudi.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {

			}
		}
		return btnChiudi;
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
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPnlCentrale(), BorderLayout.CENTER); // Generated
			jContentPane.add(getJPanel(), BorderLayout.NORTH); // Generated
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
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(FlowLayout.LEFT); // Generated
				jPanel = new JPanel();
				jPanel.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel.setLayout(flowLayout); // Generated
				jPanel.setPreferredSize(new Dimension(0, 40)); // Generated
				jPanel.add(getBtnModifica(), null); // Generated
				jPanel.add(getBtnChiudi(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel;
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
				jScrollPane.setBounds(new Rectangle(280, 103, 272, 112)); // Generated
				jScrollPane.setViewportView(getTxtNote()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			try {
				jTabbedPane = new JTabbedPane();
				jTabbedPane.addTab("Dati personali", null,
						getPnlDatiPersonali(), null); // Generated
				jTabbedPane.addTab("Altri dati", null, getPnlAltriDati(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes pnlAltriDati
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlAltriDati() {
		if (pnlAltriDati == null) {
			try {
				lblNote = new JLabel();
				lblNote.setText("Note"); // Generated
				lblNote.setBounds(new Rectangle(280, 87, 26, 16)); // Generated
				lblWebSite = new JLabel();
				lblWebSite.setText("Web Site"); // Generated
				lblWebSite.setBounds(new Rectangle(5, 87, 53, 16)); // Generated
				lblEmail = new JLabel();
				lblEmail.setText("Email"); // Generated
				lblEmail.setBounds(new Rectangle(280, 46, 31, 16)); // Generated
				lblFax = new JLabel();
				lblFax.setText("Fax"); // Generated
				lblFax.setBounds(new Rectangle(5, 46, 20, 16)); // Generated
				lblCell = new JLabel();
				lblCell.setText("Cellulare"); // Generated
				lblCell.setBounds(new Rectangle(280, 5, 50, 16)); // Generated
				lblTel = new JLabel();
				lblTel.setText("Telefono"); // Generated
				lblTel.setBounds(new Rectangle(5, 5, 49, 16)); // Generated
				pnlAltriDati = new JPanel();
				pnlAltriDati.setLayout(null); // Generated
				pnlAltriDati.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlAltriDati.add(lblTel, null); // Generated
				pnlAltriDati.add(lblCell, null); // Generated
				pnlAltriDati.add(getTxtTel(), null); // Generated
				pnlAltriDati.add(getTxtCell(), null); // Generated
				pnlAltriDati.add(lblFax, null); // Generated
				pnlAltriDati.add(lblEmail, null); // Generated
				pnlAltriDati.add(getTxtFax(), null); // Generated
				pnlAltriDati.add(getTxtEmail(), null); // Generated
				pnlAltriDati.add(lblWebSite, null); // Generated
				pnlAltriDati.add(lblNote, null); // Generated
				pnlAltriDati.add(getTxtWebSite(), null); // Generated
				pnlAltriDati.add(getJScrollPane(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlAltriDati;
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
				pnlCentrale.add(getJTabbedPane(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes pnlDatiPersonali
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDatiPersonali() {
		if (pnlDatiPersonali == null) {
			try {
				lblPiva = new JLabel();
				lblPiva.setText("P.iva"); // Generated
				lblPiva.setBounds(new Rectangle(191, 156, 27, 16)); // Generated
				lblCodFisc = new JLabel();
				lblCodFisc.setText("Codice fiscale"); // Generated
				lblCodFisc.setBounds(new Rectangle(6, 156, 80, 16)); // Generated
				lblProvincia = new JLabel();
				lblProvincia.setText("Provincia"); // Generated
				lblProvincia.setBounds(new Rectangle(377, 115, 53, 16)); // Generated
				lblCitta = new JLabel();
				lblCitta.setText("Città"); // Generated
				lblCitta.setBounds(new Rectangle(191, 115, 26, 16)); // Generated
				lblCap = new JLabel();
				lblCap.setText("Cap"); // Generated
				lblCap.setBounds(new Rectangle(6, 115, 22, 16)); // Generated
				lblVia = new JLabel();
				lblVia.setText("Via"); // Generated
				lblVia.setBounds(new Rectangle(6, 74, 18, 16)); // Generated
				lblCognome = new JLabel();
				lblCognome.setText("Cognome"); // Generated
				lblCognome.setBounds(new Rectangle(191, 33, 54, 16)); // Generated
				lblNome = new JLabel();
				lblNome.setText("Nome"); // Generated
				lblNome.setBounds(new Rectangle(6, 33, 33, 16)); // Generated
				pnlDatiPersonali = new JPanel();
				pnlDatiPersonali.setLayout(null); // Generated
				pnlDatiPersonali.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlDatiPersonali.add(getTxtNome(), null); // Generated
				pnlDatiPersonali.add(getTxtCognome(), null); // Generated
				pnlDatiPersonali.add(lblNome, null); // Generated
				pnlDatiPersonali.add(lblCognome, null); // Generated
				pnlDatiPersonali.add(lblVia, null); // Generated
				pnlDatiPersonali.add(lblCap, null); // Generated
				pnlDatiPersonali.add(getTxtVia(), null); // Generated
				pnlDatiPersonali.add(getTxtCap(), null); // Generated
				pnlDatiPersonali.add(lblCitta, null); // Generated
				pnlDatiPersonali.add(lblProvincia, null); // Generated
				pnlDatiPersonali.add(getTxtCitta(), null); // Generated
				pnlDatiPersonali.add(getTxtProvincia(), null); // Generated
				pnlDatiPersonali.add(lblCodFisc, null); // Generated
				pnlDatiPersonali.add(lblPiva, null); // Generated
				pnlDatiPersonali.add(getTxtCodFisc(), null); // Generated
				pnlDatiPersonali.add(getTxtPiva(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlDatiPersonali;
	}

	/**
	 * This method initializes txtCap
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCap() {
		if (txtCap == null) {
			try {
				txtCap = new JTextField();
				txtCap.setPreferredSize(new Dimension(80, 20)); // Generated
				txtCap.setBounds(new Rectangle(6, 131, 81, 20)); // Generated
				txtCap.setDocument(new UpperTextDocument());

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCap;
	}

	/**
	 * This method initializes txtCell
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCell() {
		if (txtCell == null) {
			try {
				txtCell = new JTextField();
				txtCell.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCell.setBounds(new Rectangle(280, 21, 140, 20)); // Generated
				txtCell.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCell;
	}

	/**
	 * This method initializes txtCitta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCitta() {
		if (txtCitta == null) {
			try {
				txtCitta = new JTextField();
				txtCitta.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCitta.setBounds(new Rectangle(191, 131, 180, 20)); // Generated
				txtCitta.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCitta;
	}

	/**
	 * This method initializes txtCodFisc
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodFisc() {
		if (txtCodFisc == null) {
			try {
				txtCodFisc = new JTextField();
				txtCodFisc.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCodFisc.setBounds(new Rectangle(6, 172, 140, 20)); // Generated
				txtCodFisc.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCodFisc;
	}

	/**
	 * This method initializes txtCognome
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCognome() {
		if (txtCognome == null) {
			try {
				txtCognome = new JTextField();
				txtCognome.setPreferredSize(new Dimension(140, 20)); // Generated
				txtCognome.setBounds(new Rectangle(191, 49, 181, 20)); // Generated
				txtCognome.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCognome;
	}

	/**
	 * This method initializes txtEmail
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			try {
				txtEmail = new JTextField();
				txtEmail.setPreferredSize(new Dimension(140, 20)); // Generated
				txtEmail.setBounds(new Rectangle(280, 62, 277, 20)); // Generated
				txtEmail.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtEmail;
	}

	/**
	 * This method initializes txtFax
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFax() {
		if (txtFax == null) {
			try {
				txtFax = new JTextField();
				txtFax.setPreferredSize(new Dimension(140, 20)); // Generated
				txtFax.setBounds(new Rectangle(5, 62, 140, 20)); // Generated
				txtFax.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtFax;
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
				txtNome.setPreferredSize(new Dimension(140, 20)); // Generated
				txtNome.setBounds(new Rectangle(6, 49, 180, 20)); // Generated
				txtNome.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNome;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote() {
		if (txtNote == null) {
			try {
				txtNote = new JTextArea();
				txtNote.setDocument(new UpperTextDocument());
				txtNote.setLineWrap(true);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtNote;
	}

	/**
	 * This method initializes txtPiva
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPiva() {
		if (txtPiva == null) {
			try {
				txtPiva = new JTextField();
				txtPiva.setPreferredSize(new Dimension(140, 20)); // Generated
				txtPiva.setBounds(new Rectangle(191, 172, 140, 20)); // Generated
				txtPiva.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtPiva;
	}

	/**
	 * This method initializes txtProvincia
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtProvincia() {
		if (txtProvincia == null) {
			try {
				txtProvincia = new JTextField();
				txtProvincia.setPreferredSize(new Dimension(140, 20)); // Generated
				txtProvincia.setBounds(new Rectangle(377, 131, 180, 20)); // Generated
				txtProvincia.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtProvincia;
	}

	/**
	 * This method initializes txtTel
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTel() {
		if (txtTel == null) {
			try {
				txtTel = new JTextField();
				txtTel.setPreferredSize(new Dimension(140, 20)); // Generated
				txtTel.setBounds(new Rectangle(5, 21, 140, 20)); // Generated
				txtTel.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtTel;
	}

	/**
	 * This method initializes txtVia
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtVia() {
		if (txtVia == null) {
			try {
				txtVia = new JTextField();
				txtVia.setPreferredSize(new Dimension(140, 20)); // Generated
				txtVia.setBounds(new Rectangle(6, 90, 366, 20)); // Generated
				txtVia.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtVia;
	}

	/**
	 * This method initializes txtWebSite
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtWebSite() {
		if (txtWebSite == null) {
			try {
				txtWebSite = new JTextField();
				txtWebSite.setPreferredSize(new Dimension(270, 20)); // Generated
				txtWebSite.setBounds(new Rectangle(5, 103, 270, 20)); // Generated
				txtWebSite.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtWebSite;
	}

	/**
	 *
	 */
	private void impostaCampi(Cliente c) {
		txtNome.setText(c.getNome());
		txtCognome.setText(c.getCognome());
		txtPiva.setText(c.getPiva());
		txtCodFisc.setText(c.getCodfisc());
		txtVia.setText(c.getVia());
		txtCap.setText(c.getCap());
		txtCitta.setText(c.getCitta());
		txtProvincia.setText(c.getProvincia());
		txtTel.setText(c.getTelefono());
		txtCell.setText(c.getCellulare());
		txtFax.setText(c.getFax());
		txtEmail.setText(c.getEmail());
		txtWebSite.setText(c.getWebsite());
		txtNote.setText(c.getNote());

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(569, 320);
		this.setResizable(false); // Generated
		this.setTitle("Modifica Clienti"); // Generated
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
		Cliente c = new Cliente();
		try {
			c.caricaDati(this.idCliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		impostaCampi(c);

	}

	private void modifica() {
		if (idCliente <= 0)
			JOptionPane.showMessageDialog(this, "Codice idCliente errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare i dati del cliente?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		Cliente c = new Cliente();
		c.setIdCliente(this.idCliente);
		c.setNome(txtNome.getText());
		c.setCognome(txtCognome.getText());
		c.setPiva(txtPiva.getText());
		c.setCodfisc(txtCodFisc.getText());
		c.setVia(txtVia.getText());
		c.setCap(txtCap.getText());
		c.setCitta(txtCitta.getText());
		c.setProvincia(txtProvincia.getText());
		c.setTelefono(txtTel.getText());
		c.setCellulare(txtCell.getText());
		c.setFax(txtFax.getText());
		c.setEmail(txtEmail.getText());
		c.setWebsite(txtWebSite.getText());
		c.setNote(txtNote.getText());
		try {
			c.updateCliente();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();

	}

} // @jve:decl-index=0:visual-constraint="10,10"
