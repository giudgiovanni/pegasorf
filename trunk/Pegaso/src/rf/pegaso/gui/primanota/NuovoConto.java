package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import rf.pegaso.db.tabelle.ContoCorrente;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

public class NuovoConto extends JDialog {


	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private boolean saved=false;
	private int idconto=-1;
	private JPanel jPanel = null;

	private JButton btnSalva = null;

	private JPanel jPanel1 = null;

	private JLabel lblRagSociale = null;

	private JFormattedTextField txtAbi = null;

	private JLabel lblNote = null;

	private JTextField txtIban = null;

	private JLabel jLabel = null;

	private JFormattedTextField txtCab = null;

	private JLabel jLabel1 = null;

	private JFormattedTextField txtCc = null;

	private int idbanca=-1;

	/**
	 * @param ges tione
	 */
	public NuovoConto(JDialog gestione) {
		super(gestione,true);
		initialize();
	}

	public NuovoConto(JDialog gestione,int idbanca) {
		super(gestione,true);
		this.idbanca=idbanca;
		initialize();
	}


	public NuovoConto(JDialog gestione,int idconto,int idbanca) {
		super(gestione,true);
		this.idconto=idconto;
		this.idbanca=idbanca;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(307, 221);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Conto");
		this.setContentPane(getJContentPane());
		UtilGUI.centraDialog(this);
		if(idconto>0){
			ContoCorrente b=new ContoCorrente();
			try {
				b.caricaDati(idconto);
				txtIban.setText(b.getIban());
				txtAbi.setValue(b.getAbi());
				txtCab.setValue(b.getCab());
				txtCc.setValue(b.getCc());
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
		ContoCorrente b=new ContoCorrente();
		b.setAbi(((Number) txtAbi.getValue()).intValue());
		b.setCab(((Number) txtCab.getValue()).intValue());
		b.setCc(((Number) txtCc.getValue()).intValue());
		b.setIban(txtIban.getText());
		b.setIdbanca(this.idbanca);
		if(!saved){
			b.insertConto();
			idconto=b.getIdconto();
		} else
			try {
				b.setIdconto(idconto);
				b.updateConto();
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(200, 5, 86, 26));
			jLabel1.setText("C/C");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(110, 6, 81, 25));
			jLabel.setText("CAB");
			lblNote = new JLabel();
			lblNote.setBounds(new Rectangle(10, 65, 96, 26));
			lblNote.setText("Codice IBAN");
			lblRagSociale = new JLabel();
			lblRagSociale.setBounds(new Rectangle(10, 5, 91, 26));
			lblRagSociale.setText("ABI");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanel1.add(lblRagSociale, null);
			jPanel1.add(getTxtAbi(), null);
			jPanel1.add(lblNote, null);
			jPanel1.add(jLabel, null);
			jPanel1.add(getTxtCab(), null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(getTxtCc(), null);
			jPanel1.add(getTxtIban(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes txtRagSociale
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtAbi() {
		if (txtAbi == null) {
			txtAbi = new JFormattedTextField();
			txtAbi.setBounds(new Rectangle(10, 35, 91, 26));
			txtAbi.setValue(0);
		}
		return txtAbi;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextField getTxtIban() {
		if (txtIban == null) {
			txtIban = new JTextField();
			txtIban.setBounds(new Rectangle(10, 95, 276, 26));
			txtIban.setDocument(new UpperTextDocument());
		}
		return txtIban;
	}

	/**
	 * This method initializes txtCab
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtCab() {
		if (txtCab == null) {
			txtCab = new JFormattedTextField();
			txtCab.setBounds(new Rectangle(110, 35, 81, 26));
			txtCab.setValue(0);
		}
		return txtCab;
	}

	/**
	 * This method initializes txtCc
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtCc() {
		if (txtCc == null) {
			txtCc = new JFormattedTextField();
			txtCc.setBounds(new Rectangle(200, 35, 86, 26));
			txtCc.setValue(0);
		}
		return txtCc;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
