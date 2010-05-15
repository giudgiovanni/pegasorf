/**
 *
 */
package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import rf.pegaso.db.tabelle.Documento;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

/**
 * @author Hunter
 *
 */
public class DocumentoAddMod extends JDialog {

	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi) {
				dispose();
			}
			if (e.getSource() == btnInsMod) {
				if (modalita == MOD)
					modifica();
				else
					inserisci();
			}

		}

	}

	public static final int ADD = 0;
	public static final int MOD = 1;

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnInsMod = null;

	private DBManager dbm;

	private String descrizione;

	private int iddocumento;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel lblDescrizione = null;
	private JLabel lblTipo = null;
	private int modalita;
	private JPanel pnlCentrale = null;
	private String tipo; // @jve:decl-index=0:
	private JTextField txtDescrizione = null;
	private JTextField txtTipo = null;

	/**
	 * @param owner
	 */
	public DocumentoAddMod(JDialog owner, DBManager dbm, int modalita) {
		super(owner, true);
		this.dbm = dbm;
		this.modalita = modalita;
		initialize();
	}

	public DocumentoAddMod(JDialog owner, DBManager dbm, int iddocumento,
			int modalita) {
		super(owner, true);
		this.dbm = dbm;
		this.iddocumento = iddocumento;
		this.modalita = modalita;
		initialize();
	}

	public DocumentoAddMod(Frame padre, DBManager dbm, int modalita) {
		super(padre,true);
		this.dbm=dbm;
		this.modalita=modalita;
		initialize();
	}

	/**
	 * @return the modalita
	 */
	public int getModalita() {
		return modalita;
	}

	public void messaggioCampoErrato(String messaggio) {
		JOptionPane.showMessageDialog(this, messaggio, "ERRORE",
				JOptionPane.ERROR_MESSAGE);
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
	 * This method initializes btnInsMod
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInsMod() {
		if (btnInsMod == null) {
			try {
				btnInsMod = new JButton();
				if (modalita == MOD)
					btnInsMod.setText("Modifica");
				else
					btnInsMod.setText("Inserisci");
				btnInsMod.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnInsMod;
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
				jPanel.add(getBtnInsMod(), null); // Generated
				jPanel.add(getBtnChiudi(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
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
				lblDescrizione = new JLabel();
				lblDescrizione.setBounds(new Rectangle(224, 12, 125, 21)); // Generated
				lblDescrizione.setText("Descrizione"); // Generated
				lblTipo = new JLabel();
				lblTipo.setBounds(new Rectangle(4, 12, 53, 21)); // Generated
				lblTipo.setText("Tipo"); // Generated
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(null); // Generated
				pnlCentrale.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlCentrale.add(lblTipo, null); // Generated
				pnlCentrale.add(getTxtTipo(), null); // Generated
				pnlCentrale.add(lblDescrizione, null); // Generated
				pnlCentrale.add(getTxtDescrizione(), null);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes txtDescrizione
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescrizione() {
		if (txtDescrizione == null) {
			try {
				txtDescrizione = new JTextField();
				txtDescrizione.setBounds(new Rectangle(224, 36, 281, 21)); // Generated
				txtDescrizione.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtDescrizione;
	}

	/**
	 * This method initializes txtTipo
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTipo() {
		if (txtTipo == null) {
			try {
				txtTipo = new JTextField();
				txtTipo.setBounds(new Rectangle(4, 36, 177, 21)); // Generated
				txtTipo.setDocument(new UpperTextDocument());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtTipo;
	}

	private void impostaCampi(Documento c) {
		Documento f = new Documento();
		try {
			f.caricaDati(c.getIddocumento());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore caricamento dati",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		this.iddocumento = c.getIddocumento();
		this.tipo = c.getTipo();
		this.descrizione = c.getDescrizione();
		txtTipo.setText(tipo);
		txtDescrizione.setText(descrizione);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(537, 161);
		this.setResizable(false); // Generated

		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
		if (modalita == MOD) {
			this.setTitle("Modifica Documento");
			Documento c = new Documento();
			try {
				c.caricaDati(this.iddocumento);
				impostaCampi(c);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento dati DB", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		} else {
			this.setTitle("Inserisci Documento");
		}

	}

	private void inserisci() {
		Documento a = new Documento();
		recuperaDatiCampi(a);

		try {
			a.insertDocumento();
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idCliente errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			try {
				e
						.printStackTrace(new PrintWriter(
								"inserimento_idnonvalido.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		svuotaCampi();

	}

	private void modifica() {
		if (iddocumento <= 0)
			JOptionPane.showMessageDialog(this, "Codice idDocumento errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler modificare\nil documento selezionato?",
				"AVVISO", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		Documento a = new Documento();
		a.setIddocumento(iddocumento);
		recuperaDatiCampi(a);
		try {
			a.updateDocumento();
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idDocumento errato",
					"ERRORE", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 *
	 */
	private void recuperaDatiCampi(Documento a) {

		a.setDescrizione(txtDescrizione.getText());
		a.setIddocumento(iddocumento);
		a.setTipo(txtTipo.getText());

	}

	/**
	 *
	 */
	private void svuotaCampi() {
		Component[] component = pnlCentrale.getComponents();
		for (int i = 0; i < component.length; i++) {
			if (component[i] instanceof JTextField) {
				JTextField tmp = (JTextField) component[i];
				tmp.setText("");
			}
		}

	}

} // @jve:decl-index=0:visual-constraint="10,10"
