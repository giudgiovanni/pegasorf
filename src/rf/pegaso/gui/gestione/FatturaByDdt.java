package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.model.DdtCaricoModel;
import rf.pegaso.db.tabelle.Carico;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;

import com.toedter.calendar.JDateChooser;

public class FatturaByDdt extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlDdtFt = null;

	private JPanel jPanelNord = null;

	private JLabel lblFattura = null;

	private JLabel lblNumero = null;

	private JTextField txtNumero = null;

	private JLabel lblData = null;

	private JDateChooser dataCorrente = null;

	private GregorianCalendar time = null;

	private JButton btnChiudi1 = null;

	private JButton btnSalva = null;

	private JPanel jPanel11 = null;

	private JLabel lblSi1 = null;

	private JRadioButton rbtnSi1 = null;

	private JLabel lblNO1 = null;

	private JRadioButton rbtnNo1 = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane3 = null;

	private JXTable tblDDT = null;

	private DdtCaricoModel modelloDdtCarico = null;

	/**
	 * @param owner
	 */
	public FatturaByDdt(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			jContentPane.add(getPnlDdtFt(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlDdtFt
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlDdtFt() {
		if (pnlDdtFt == null) {
			pnlDdtFt = new JPanel();
			pnlDdtFt.setLayout(new BorderLayout());
			pnlDdtFt.add(getJPanelNord(), java.awt.BorderLayout.NORTH);
			pnlDdtFt.add(getJPanel2(), java.awt.BorderLayout.CENTER);
		}
		return pnlDdtFt;
	}

	/**
	 * This method initializes jPanelNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelNord() {
		if (jPanelNord == null) {
			lblData = new JLabel();
			lblData.setBounds(new Rectangle(268, 11, 30, 20));
			lblData.setText("Data");
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(109, 11, 49, 20));
			lblNumero.setText("Numero");
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblFattura = new JLabel();
			lblFattura.setBounds(new Rectangle(15, 7, 89, 28));
			lblFattura.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			lblFattura.setText("FATTURA");
			lblFattura.setFont(new Font("Dialog", Font.BOLD, 18));
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 80));
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(lblFattura, null);
			jPanelNord.add(lblNumero, null);
			jPanelNord.add(getTxtNumero(), null);
			jPanelNord.add(lblData, null);
			jPanelNord.add(getDataCorrente(), null);
			jPanelNord.add(getBtnChiudi1(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getJPanel11(), null);
		}
		return jPanelNord;
	}

	/**
	 * This method initializes txtNumero
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(163, 11, 100, 20));
			txtNumero.setPreferredSize(new Dimension(100, 20));
		}
		return txtNumero;
	}

	/**
	 * This method initializes dataCorrente
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataCorrente() {
		if (dataCorrente == null) {
			Date date = getTime().getTime();
			dataCorrente = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataCorrente.setBounds(new Rectangle(303, 11, 100, 20));
			dataCorrente.setDate(date);
			dataCorrente.setPreferredSize(new Dimension(100, 20));
		}
		return dataCorrente;
	}

	/**
	 * This method initializes time
	 *
	 * @return java.util.GregorianCalendar
	 */
	private GregorianCalendar getTime() {
		if (time == null) {
			time = (GregorianCalendar) Calendar.getInstance();
		}
		return time;
	}

	/**
	 * This method initializes btnChiudi1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnChiudi1() {
		if (btnChiudi1 == null) {
			btnChiudi1 = new JButton();
			btnChiudi1.setBounds(new Rectangle(408, 8, 69, 26));
			btnChiudi1.setText("Chiudi");
			btnChiudi1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnChiudi1;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setBounds(new Rectangle(482, 8, 65, 26));
			btnSalva.setText("Salva");
			btnSalva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					salva(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnSalva;
	}

	protected void salva() {
		if (tblDDT.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		if (txtNumero.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Numero Fattura obbligatorio.",
					"AVVISO", 1);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nsalvare da ddt a fattura?",
				"AVVISO", 0, 1);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		int riga = tblDDT.getSelectedRow();
		int idcarico = ((Long) tblDDT.getValueAt(riga, 0)).intValue();
		Carico c = new Carico();
		Carico n=new Carico();
		try {
			c.caricaDati(idcarico);
			n.setDataCarico(c.getDataCarico());
			n.setDataDocumento(new java.sql.Date(dataCorrente.getDate().getTime()));
			n.setIdCarico(DBManager.getIstanceSingleton().getNewID("carichi", "idcarico"));
			n.setIdDocumento(1);
			n.setIdFornitore(c.getIdFornitore());
			n.setNote(c.getNote());
			n.setNumDocumento(txtNumero.getText());
			n.setOraCarico(c.getOraCarico());
			if (rbtnNo1.isSelected())
				n.setSospeso(0);
			else
				n.setSospeso(1);
			n.setTotaleDocumentoIvato(c.getTotaleIvato());
			n.setRiferimentoDoc(c.getIdCarico());
			n.insertCarico();

			c.setRiferimentoDoc(n.getIdCarico());
			c.updateCarico();

			txtNumero.setText("");
			rbtnNo1.setSelected(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE", 2);
			e.printStackTrace();
		} catch (IDNonValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes jPanel11
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel11() {
		if (jPanel11 == null) {
			lblNO1 = new JLabel();
			lblNO1.setText("NO");
			lblSi1 = new JLabel();
			lblSi1.setText("SI");
			jPanel11 = new JPanel();
			jPanel11.setLayout(new FlowLayout());
			jPanel11.setBounds(new Rectangle(560, 6, 113, 54));
			jPanel11.setBorder(BorderFactory.createTitledBorder(null, "Sospeso", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel11.add(lblSi1, null);
			jPanel11.add(getRbtnSi1(), null);
			jPanel11.add(lblNO1, null);
			jPanel11.add(getRbtnNo1(), null);
			ButtonGroup bg=new ButtonGroup();
			bg.add(rbtnNo1);
			bg.add(rbtnSi1);
		}
		return jPanel11;
	}

	/**
	 * This method initializes rbtnSi1
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbtnSi1() {
		if (rbtnSi1 == null) {
			rbtnSi1 = new JRadioButton();
		}
		return rbtnSi1;
	}

	/**
	 * This method initializes rbtnNo1
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getRbtnNo1() {
		if (rbtnNo1 == null) {
			rbtnNo1 = new JRadioButton();
		}
		rbtnNo1.setSelected(true);
		return rbtnNo1;
	}

	/**
	 * This method initializes jPanel2
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJScrollPane3(), java.awt.BorderLayout.CENTER);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane3
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getTblDDT());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes tblDDT
	 *
	 * @return javax.swing.JTable
	 */
	private JXTable getTblDDT() {
		if (tblDDT == null) {
			try {
				modelloDdtCarico = new DdtCaricoModel();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tblDDT = new JXTable(modelloDdtCarico);
			DBManager.getIstanceSingleton().addDBStateChange(modelloDdtCarico);

			TableColumn col=tblDDT.getColumnModel().getColumn(0);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);
		}
		return tblDDT;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
