package rf.pegaso.gui.gestione;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.model.DdtCaricoModel;
import rf.pegaso.db.model.DdtFatturaModel;

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
		this.setContentPane(getJContentPane());
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
		}
		return btnSalva;
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
			jPanel11.setBounds(new Rectangle(555, 10, 122, 46));
			jPanel11.setBorder(BorderFactory.createTitledBorder(null, "Sospeso", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel11.add(lblSi1, null);
			jPanel11.add(getRbtnSi1(), null);
			jPanel11.add(lblNO1, null);
			jPanel11.add(getRbtnNo1(), null);
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
			col = tblDDT.getColumnModel().getColumn(1);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);
		}
		return tblDDT;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
