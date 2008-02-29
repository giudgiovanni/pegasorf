package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import rf.pegaso.db.tabelle.Banca;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

public class NuovaBanca extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private boolean saved=false;
	private int id=-1;
	private JPanel jPanel = null;

	private JButton btnSalva = null;

	private JPanel jPanel1 = null;

	private JLabel lblRagSociale = null;

	private JTextField txtRagSociale = null;

	private JLabel lblNote = null;

	private JScrollPane jScrollPane = null;

	private JTextArea txtNote = null;

	/**
	 * @param gestione
	 */
	public NuovaBanca(JDialog gestione) {
		super(gestione,true);
		initialize();
	}

	public NuovaBanca(JDialog gestione,int id) {
		super(gestione,true);
		this.id=id;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(505, 288);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Nuova Banca");
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);

		if(id>0){
			Banca b=new Banca();
			try {
				b.caricaDati(id);
				txtNote.setText(b.getNote());
				txtRagSociale.setText(b.getRagioneSociale());
				saved=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
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
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
			jContentPane.add(getJPanel1(), BorderLayout.CENTER);
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
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.setPreferredSize(new Dimension(0, 45));
			jPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanel.add(getBtnSalva(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Salva");
			btnSalva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					salva();// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnSalva;
	}

	protected void salva() {
		Banca b=new Banca();
		b.setRagioneSociale(txtRagSociale.getText());
		b.setNote(txtNote.getText());
		if(!saved){
			b.insertBanca();
			id=b.getIdbanca();
		} else
			try {
				b.setIdbanca(id);
				b.updateBanca();
			} catch (IDNonValido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		saved=true;




	}

	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			lblNote = new JLabel();
			lblNote.setBounds(new Rectangle(10, 65, 96, 26));
			lblNote.setText("Note");
			lblRagSociale = new JLabel();
			lblRagSociale.setBounds(new Rectangle(10, 5, 91, 26));
			lblRagSociale.setText("Ragione Sociale");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanel1.add(lblRagSociale, null);
			jPanel1.add(getTxtRagSociale(), null);
			jPanel1.add(lblNote, null);
			jPanel1.add(getJScrollPane(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes txtRagSociale
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtRagSociale() {
		if (txtRagSociale == null) {
			txtRagSociale = new JTextField();
			txtRagSociale.setBounds(new Rectangle(10, 35, 476, 26));
			txtRagSociale.setDocument(new UpperTextDocument());
		}
		return txtRagSociale;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 95, 476, 106));
			jScrollPane.setViewportView(getTxtNote());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote() {
		if (txtNote == null) {
			txtNote = new JTextArea();
			txtNote.setDocument(new UpperTextDocument());
		}
		return txtNote;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
