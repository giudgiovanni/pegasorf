/**
 *
 */
package rf.pegaso.gui.gestione;


import it.infolabs.hibernate.CategoriaCliente;
import it.infolabs.hibernate.CategoriaClienteHome;
import it.infolabs.hibernate.Clienti;
import it.infolabs.hibernate.ClientiHome;
import it.infolabs.hibernate.ProvinciaHome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import rf.myswing.IDJComboBox;
import rf.pegaso.db.tabelle.Provincia;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperTextDocument;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Hunter
 *
 */
public class ClientiAddMod extends JDialog {
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

	private DBManager dbm;

	private long idCliente;

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

	private IDJComboBox cmbProvincia = null;

	private JTextField txtTel = null;

	private JTextField txtVia = null;

	private JTextField txtWebSite = null;

	private JFrame padre;

	private JButton btnProvincia = null;

	private JLabel lblTel2 = null;

	private JLabel lblCell2 = null;

	private JLabel lblCategoriaCliente = null;

	private IDJComboBox cmbCategoriaCliente;

	private JTextArea txtArea;

	private JTextField txtTel2;

	private JTextField txtCell2;

	/**
	 * @param owner
	 */
	public ClientiAddMod(JDialog owner, DBManager dbm, long cliente) {
		super(owner, true);
		this.dbm = dbm;
		this.idCliente = cliente;
		initialize();
	}

	public ClientiAddMod(JFrame frame, DBManager dbm, long cliente) {
		super(frame, true);
		this.dbm=dbm;
		this.padre=frame;
		this.idCliente = cliente;
		initialize();
	}

	public ClientiAddMod(JDialog dialog, DBManager dbm) {
		new ClientiAddMod(dialog,dbm,-1);
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
	 * This method initializes btnInserisci
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			try {
				btnInserisci = new JButton();
				btnInserisci.setText("Salva"); // Generated
				btnInserisci.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
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
				jPanel.add(getBtnInserisci(), null); // Generated
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
				jScrollPane.setBounds(new Rectangle(282, 145, 140, 19)); // Generated
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
				txtCell2 = new JTextField();
				txtCell2.setBounds(new Rectangle(282, 64, 140, 20));
				txtCell2.setDocument(new UpperTextDocument());
				txtCell2.setPreferredSize(new Dimension(140, 20));
				txtTel2 = new JTextField();
				txtTel2.setBounds(new Rectangle(5, 63, 140, 20));
				txtTel2.setDocument(new UpperTextDocument());
				txtTel2.setPreferredSize(new Dimension(140, 20));
				lblCell2 = new JLabel();
				lblCell2.setBounds(new Rectangle(281, 45, 72, 16));
				lblCell2.setText(" Cellulare 2");
				lblTel2 = new JLabel();
				lblTel2.setBounds(new Rectangle(5, 44, 75, 16));
				lblTel2.setText("Telefono 2");
				lblNote = new JLabel();
				lblNote.setText("Note"); // Generated
				lblNote.setBounds(new Rectangle(282, 129, 26, 16)); // Generated
				lblWebSite = new JLabel();
				lblWebSite.setText("Web Site"); // Generated
				lblWebSite.setBounds(new Rectangle(7, 129, 53, 16)); // Generated
				lblEmail = new JLabel();
				lblEmail.setText("Email"); // Generated
				lblEmail.setBounds(new Rectangle(282, 88, 31, 16)); // Generated
				lblFax = new JLabel();
				lblFax.setText("Fax"); // Generated
				lblFax.setBounds(new Rectangle(7, 88, 20, 16)); // Generated
				lblCell = new JLabel();
				lblCell.setText("Cellulare 1"); // Generated
				lblCell.setBounds(new Rectangle(280, 5, 73, 16)); // Generated
				lblTel = new JLabel();
				lblTel.setText("Telefono 1"); // Generated
				lblTel.setBounds(new Rectangle(5, 5, 74, 16)); // Generated
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
				pnlAltriDati.add(lblTel2, null);
				pnlAltriDati.add(lblCell2, null);
				pnlAltriDati.add(txtTel2, null);
				pnlAltriDati.add(txtCell2, null);
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
				JButton btnCategoria = new JButton();
				btnCategoria.setBounds(new Rectangle(585, 41, 41, 28));
				btnCategoria.setText("...");
				btnCategoria.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apriCategoriaClienti();
					}
				});
				cmbCategoriaCliente = new IDJComboBox();
				cmbCategoriaCliente.setBounds(new Rectangle(387, 49, 182, 20));
				lblCategoriaCliente = new JLabel();
				lblCategoriaCliente.setBounds(new Rectangle(387, 33, 110, 16));
				lblCategoriaCliente.setText("Categoria Cliente");
				JScrollPane jScrollPane1 = new JScrollPane();
				jScrollPane1.setBounds(new Rectangle(6, 208, 323, 118));
				jScrollPane1.setBorder(BorderFactory.createTitledBorder(null, "Intestazione", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
				txtArea=new JTextArea();
				txtArea.setLineWrap(true);
				jScrollPane1.setViewportView(txtArea);
				lblPiva = new JLabel();
				lblPiva.setText("P.iva"); // Generated
				lblPiva.setBounds(new Rectangle(191, 156, 27, 16)); // Generated
				lblCodFisc = new JLabel();
				lblCodFisc.setText("Codice fiscale"); // Generated
				lblCodFisc.setBounds(new Rectangle(6, 156, 80, 16)); // Generated
				lblProvincia = new JLabel();
				lblProvincia.setText("Provincia"); // Generated
				lblProvincia.setBounds(new Rectangle(294, 115, 53, 16)); // Generated
				lblCitta = new JLabel();
				lblCitta.setText("Citt\u00E0"); // Generated
				lblCitta.setBounds(new Rectangle(104, 116, 26, 16)); // Generated
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
				pnlDatiPersonali.add(getCmbProvincia(), null); // Generated
				pnlDatiPersonali.add(lblCodFisc, null); // Generated
				pnlDatiPersonali.add(lblPiva, null); // Generated
				pnlDatiPersonali.add(getTxtCodFisc(), null); // Generated
				pnlDatiPersonali.add(getTxtPiva(), null); // Generated
				pnlDatiPersonali.add(getBtnProvincia(), null);
				pnlDatiPersonali.add(jScrollPane1, null);
				pnlDatiPersonali.add(lblCategoriaCliente, null);
				pnlDatiPersonali.add(cmbCategoriaCliente, null);
				pnlDatiPersonali.add(btnCategoria, null);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlDatiPersonali;
	}

	protected void apriCategoriaClienti() {
		CategoriaClientiGestione ccg=new CategoriaClientiGestione(this);
		ccg.setVisible(true);
		caricaCategorieCliente();
		
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
				txtCitta.setBounds(new Rectangle(104, 132, 180, 20)); // Generated
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
				txtEmail.setBounds(new Rectangle(282, 104, 140, 20)); // Generated
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
				txtFax.setBounds(new Rectangle(7, 104, 140, 20)); // Generated
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
	private IDJComboBox getCmbProvincia() {
		if (cmbProvincia == null) {
			try {
				cmbProvincia = new IDJComboBox();
				cmbProvincia.setPreferredSize(new Dimension(140, 20)); // Generated
				cmbProvincia.setBounds(new Rectangle(294, 131, 180, 20)); // Generated

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbProvincia;
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
				txtWebSite.setBounds(new Rectangle(7, 145, 270, 20)); // Generated
				txtWebSite.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtWebSite;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(650, 443);
		this.setResizable(false); // Generated
		this.setTitle("Aggiungi/Modifa Clienti"); // Generated
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
		caricaProvince(cmbProvincia);
		caricaCategorieCliente();
		caricaCliente();

	}

	private void caricaCliente() {
		ClientiHome.getInstance().begin();
		Clienti cliente=ClientiHome.getInstance().findById(idCliente);
		if(cliente.getIdcliente()!=0){
			impostaCampi(cliente);
		}
		ClientiHome.getInstance().close();
	}
	
	private void impostaCampi(Clienti c) {
		txtNome.setText(c.getNome());
		txtCognome.setText(c.getCognome());
		txtPiva.setText(c.getPiva());
		txtCodFisc.setText(c.getCodfisc());
		txtVia.setText(c.getVia());
		txtCap.setText(c.getCap());
		txtCitta.setText(c.getCitta());
		if(c.getProvincia()!=null){
			Number number=new Long(c.getProvincia().getIdprovincia());
			cmbProvincia.setSelectedItemByID(number.intValue());
		}
		if(c.getCategoriaCliente()!=null){
			Number number=new Long(c.getCategoriaCliente().getId());
			cmbCategoriaCliente.setSelectedItemByID(number.intValue());
		}
		txtTel2.setText(c.getTel2());
		txtCell2.setText(c.getCell2());
		txtArea.setText(c.getIntestazione());
		txtTel.setText(c.getTel());
		txtCell.setText(c.getCell());
		txtFax.setText(c.getFax());
		txtEmail.setText(c.getEmail());
		txtWebSite.setText(c.getWebsite());
		txtNote.setText(c.getNote());

	}

	private void caricaCategorieCliente() {
		
		CategoriaClienteHome.getInstance().begin();
		String as[] = CategoriaClienteHome.getInstance().getAllDisplay();
		// carichiamo tutti i dati in due array
		// da passre al combobox
		((IDJComboBox) cmbCategoriaCliente).caricaNewValueComboBox(as, false);
		AutoCompletion.enable(cmbCategoriaCliente);
		
	}

	private void inserisci() {
		ClientiHome.getInstance().begin();
		Clienti c = ClientiHome.getInstance().findById(idCliente);
		c.setNome(txtNome.getText());
		c.setCognome(txtCognome.getText());
		c.setPiva(txtPiva.getText());
		c.setCodfisc(txtCodFisc.getText());
		c.setVia(txtVia.getText());
		c.setCap(txtCap.getText());
		c.setCitta(txtCitta.getText());
		c.setProvincia(ProvinciaHome.getInstance().findById(new Long(cmbProvincia.getIDSelectedItem())));
		c.setTel(txtTel.getText());
		c.setCell(txtCell.getText());
		c.setFax(txtFax.getText());
		c.setEmail(txtEmail.getText());
		c.setWebsite(txtWebSite.getText());
		c.setNote(txtNote.getText());
		c.setIntestazione(txtArea.getText());
		c.setTel2(txtTel2.getText());
		c.setCell2(txtCell2.getText());
		CategoriaCliente cc=CategoriaClienteHome.getInstance().findById(new Long(cmbCategoriaCliente.getIDSelectedItem()));
		c.setCategoriaCliente(cc);
		ClientiHome.getInstance().attachDirty(c);
		ClientiHome.getInstance().commitAndClose();
		
		dispose();

	}

	private void caricaProvince(JComboBox cmbProvince) {

		Provincia f = new Provincia();
		try {

			String as[] = (String[]) f.allProvincie();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbProvince).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento documenti nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbProvince);
	}

	/**
	 * This method initializes btnProvincia
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnProvincia() {
		if (btnProvincia == null) {
			btnProvincia = new JButton();
			btnProvincia.setBounds(new Rectangle(489, 130, 45, 21));
			btnProvincia.setText("...");
			btnProvincia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestioneProvincia();
				}
			});
		}
		return btnProvincia;
	}

	protected void gestioneProvincia() {
		ProvincieGestione pg=new ProvincieGestione(this);
		pg.setVisible(true);
		cmbProvincia.removeAllItems();
		caricaProvince(cmbProvincia);

	}

} // @jve:decl-index=0:visual-constraint="10,10"
