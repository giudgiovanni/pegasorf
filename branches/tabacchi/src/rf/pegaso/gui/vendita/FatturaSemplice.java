package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.SwingConstants;
import rf.myswing.IDJComboBox;
import javax.swing.JFormattedTextField;
import java.text.DecimalFormat;
import javax.swing.JComboBox;
import java.util.Vector;
import java.lang.String;
import java.lang.System;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Dimension;

public class FatturaSemplice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanelNord = null;
	private JButton btnChiudi = null;
	private JButton btnSalva = null;
	private JButton btnStampa = null;
	private JLabel lblFattura = null;
	private JLabel lblNumero = null;
	private JTextField txtNumero = null;
	private JLabel lblData = null;
	private JDateChooser dataCorrente = null;
	private JLabel lblCliente = null;
	private IDJComboBox cmbClienti = null;
	private JButton btnNuovoCliente = null;
	private JLabel lblPagamento = null;
	private IDJComboBox cmbPagamento = null;
	private JLabel lblDestinazione = null;
	private JLabel lblSpeseInc = null;
	private JLabel lblSpeseTr = null;
	private JTextField txtDestinazione = null;
	private JFormattedTextField txtSpeseInc = null;
	private DecimalFormat notaz = null;
	private JFormattedTextField txtSpeseTr = null;
	private DecimalFormat notaz1 = null;
	private JLabel lblDataTr = null;
	private JLabel lblOraTr = null;
	private JDateChooser dataTrasporto = null;
	private JLabel lblColli = null;
	private JLabel lblPeso = null;
	private JFormattedTextField txtColli = null;
	private DecimalFormat notaz2 = null;
	private JFormattedTextField txtPeso = null;
	private DecimalFormat notaz3 = null;
	private JLabel lblCausale = null;
	private JLabel lblAspetto = null;
	private JLabel lblConsegna = null;
	private IDJComboBox cmbCausale = null;
	private IDJComboBox cmbAspetto = null;
	private JComboBox cmbConsegna = null;
	private Vector v = null;
	private JLabel lblPorto = null;
	private JComboBox cmbPorto = null;
	private Vector v1 = null;
	private JButton btnNuovoPagamento = null;
	private String userDir = null;
	private JButton btnNuovaCausale = null;
	private String userDir1 = null;
	private JButton btnNuovoAspetto = null;
	private String userDir2 = null;
	private JFormattedTextField txtOraTr = null;
	private DecimalFormat notaz4 = null;
	private JLabel lblPuntini = null;
	private JFormattedTextField txtMinTr = null;
	private DecimalFormat notaz5 = null;
	private JButton btnAzzera = null;

	/**
	 * This is the default constructor
	 */
	public FatturaSemplice() {
		super();
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
		this.setTitle("JFrame");
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
			jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelNord	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelNord() {
		if (jPanelNord == null) {
			lblPuntini = new JLabel();
			lblPuntini.setBounds(new Rectangle(280, 184, 10, 16));
			lblPuntini.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntini.setText(":");
			lblPuntini.setVerticalAlignment(SwingConstants.CENTER);
			lblPuntini.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPorto = new JLabel();
			lblPorto.setBounds(new Rectangle(665, 184, 40, 16));
			lblPorto.setHorizontalAlignment(SwingConstants.CENTER);
			lblPorto.setText("Porto");
			lblPorto.setVerticalAlignment(SwingConstants.CENTER);
			lblPorto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblConsegna = new JLabel();
			lblConsegna.setBounds(new Rectangle(495, 184, 70, 16));
			lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
			lblConsegna.setText("Consegna");
			lblConsegna.setVerticalAlignment(SwingConstants.CENTER);
			lblConsegna.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblAspetto = new JLabel();
			lblAspetto.setBounds(new Rectangle(495, 141, 70, 16));
			lblAspetto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAspetto.setText("Aspetto");
			lblAspetto.setVerticalAlignment(SwingConstants.CENTER);
			lblAspetto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCausale = new JLabel();
			lblCausale.setBounds(new Rectangle(495, 98, 70, 16));
			lblCausale.setHorizontalAlignment(SwingConstants.CENTER);
			lblCausale.setText("Causale");
			lblCausale.setVerticalAlignment(SwingConstants.CENTER);
			lblCausale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPeso = new JLabel();
			lblPeso.setBounds(new Rectangle(355, 184, 60, 16));
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setText("Peso (Kg)");
			lblPeso.setVerticalAlignment(SwingConstants.CENTER);
			lblPeso.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblColli = new JLabel();
			lblColli.setBounds(new Rectangle(355, 141, 60, 16));
			lblColli.setHorizontalAlignment(SwingConstants.CENTER);
			lblColli.setText("Colli (Nr)");
			lblColli.setVerticalAlignment(SwingConstants.CENTER);
			lblColli.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblOraTr = new JLabel();
			lblOraTr.setBounds(new Rectangle(175, 184, 50, 16));
			lblOraTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblOraTr.setText("Ora Tr");
			lblOraTr.setVerticalAlignment(SwingConstants.CENTER);
			lblOraTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDataTr = new JLabel();
			lblDataTr.setBounds(new Rectangle(175, 141, 50, 16));
			lblDataTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblDataTr.setText("Data Tr");
			lblDataTr.setVerticalAlignment(SwingConstants.CENTER);
			lblDataTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseTr = new JLabel();
			lblSpeseTr.setBounds(new Rectangle(10, 184, 80, 16));
			lblSpeseTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setText("Spese Tr");
			lblSpeseTr.setVerticalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseInc = new JLabel();
			lblSpeseInc.setBounds(new Rectangle(10, 141, 80, 16));
			lblSpeseInc.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseInc.setText("Spese Inc");
			lblSpeseInc.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDestinazione = new JLabel();
			lblDestinazione.setBounds(new Rectangle(10, 98, 80, 16));
			lblDestinazione.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinazione.setText("Destinazione");
			lblDestinazione.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPagamento = new JLabel();
			lblPagamento.setBounds(new Rectangle(495, 55, 70, 16));
			lblPagamento.setHorizontalAlignment(SwingConstants.CENTER);
			lblPagamento.setText("Pagamento");
			lblPagamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(10, 55, 80, 16));
			lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCliente.setText("Cliente");
			lblCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblData = new JLabel();
			lblData.setBounds(new Rectangle(310, 12, 30, 16));
			lblData.setText("Data");
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(130, 12, 50, 16));
			lblNumero.setText("Numero");
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblFattura = new JLabel();
			lblFattura.setBounds(new Rectangle(10, 5, 90, 30));
			lblFattura.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			lblFattura.setText("FATTURA");
			lblFattura.setFont(new Font("Dialog", Font.BOLD, 18));
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 230));
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(getBtnChiudi(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getBtnStampa(), null);
			jPanelNord.add(lblFattura, null);
			jPanelNord.add(lblNumero, null);
			jPanelNord.add(getTxtNumero(), null);
			jPanelNord.add(lblData, null);
			jPanelNord.add(getDataCorrente(), null);
			jPanelNord.add(lblCliente, null);
			jPanelNord.add(getCmbClienti(), null);
			jPanelNord.add(getBtnNuovoCliente(), null);
			jPanelNord.add(lblPagamento, null);
			jPanelNord.add(getCmbPagamento(), null);
			jPanelNord.add(lblDestinazione, null);
			jPanelNord.add(lblSpeseInc, null);
			jPanelNord.add(lblSpeseTr, null);
			jPanelNord.add(getTxtDestinazione(), null);
			jPanelNord.add(getTxtSpeseInc(), null);
			jPanelNord.add(getTxtSpeseTr(), null);
			jPanelNord.add(lblDataTr, null);
			jPanelNord.add(lblOraTr, null);
			jPanelNord.add(getDataTrasporto(), null);
			jPanelNord.add(lblColli, null);
			jPanelNord.add(lblPeso, null);
			jPanelNord.add(getTxtColli(), null);
			jPanelNord.add(getTxtPeso(), null);
			jPanelNord.add(lblCausale, null);
			jPanelNord.add(lblAspetto, null);
			jPanelNord.add(lblConsegna, null);
			jPanelNord.add(getCmbCausale(), null);
			jPanelNord.add(getCmbAspetto(), null);
			jPanelNord.add(getCmbConsegna(), null);
			jPanelNord.add(lblPorto, null);
			jPanelNord.add(getCmbPorto(), null);
			jPanelNord.add(getBtnNuovoPagamento(), null);
			jPanelNord.add(getBtnNuovaCausale(), null);
			jPanelNord.add(getBtnNuovoAspetto(), null);
			jPanelNord.add(getTxtOraTr(), null);
			jPanelNord.add(lblPuntini, null);
			jPanelNord.add(getTxtMinTr(), null);
			jPanelNord.add(getBtnAzzera(), null);
		}
		return jPanelNord;
	}

	/**
	 * This method initializes btnChiudi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnChiudi() {
		if (btnChiudi == null) {
			btnChiudi = new JButton();
			btnChiudi.setBounds(new Rectangle(704, 7, 78, 26));
			btnChiudi.setText("Chiudi");
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnSalva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setBounds(new Rectangle(546, 7, 78, 26));
			btnSalva.setText("Salva");
		}
		return btnSalva;
	}

	/**
	 * This method initializes btnStampa	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnStampa() {
		if (btnStampa == null) {
			btnStampa = new JButton();
			btnStampa.setBounds(new Rectangle(625, 7, 78, 26));
			btnStampa.setText("Stampa");
		}
		return btnStampa;
	}

	/**
	 * This method initializes txtNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(190, 10, 100, 20));
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
			dataCorrente = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataCorrente.setBounds(new Rectangle(350, 8, 112, 24));
			dataCorrente.setDate(new Date());
		}
		return dataCorrente;
	}

	/**
	 * This method initializes cmbClienti	
	 * 	
	 * @return rf.myswing.IDJComboBox	
	 */
	private IDJComboBox getCmbClienti() {
		if (cmbClienti == null) {
			cmbClienti = new IDJComboBox();
			cmbClienti.setBounds(new Rectangle(100, 50, 270, 26));
		}
		return cmbClienti;
	}

	/**
	 * This method initializes btnNuovoCliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNuovoCliente() {
		if (btnNuovoCliente == null) {
			btnNuovoCliente = new JButton();
			btnNuovoCliente.setBounds(new Rectangle(393, 50, 82, 26));
			btnNuovoCliente.setText("Nuovo");
		}
		return btnNuovoCliente;
	}

	/**
	 * This method initializes cmbPagamento	
	 * 	
	 * @return rf.myswing.IDJComboBox	
	 */
	private IDJComboBox getCmbPagamento() {
		if (cmbPagamento == null) {
			cmbPagamento = new IDJComboBox();
			cmbPagamento.setBounds(new Rectangle(575, 50, 150, 26));
		}
		return cmbPagamento;
	}

	/**
	 * This method initializes txtDestinazione	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDestinazione() {
		if (txtDestinazione == null) {
			txtDestinazione = new JTextField();
			txtDestinazione.setBounds(new Rectangle(100, 96, 375, 20));
		}
		return txtDestinazione;
	}

	/**
	 * This method initializes notaz	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz() {
		if (notaz == null) {
			notaz = new DecimalFormat("#,##0.00");
		}
		return notaz;
	}

	/**
	 * This method initializes txtSpeseInc	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtSpeseInc() {
		if (txtSpeseInc == null) {
			txtSpeseInc = new JFormattedTextField(getNotaz());
			txtSpeseInc.setBounds(new Rectangle(100, 139, 55, 20));
		}
		return txtSpeseInc;
	}

	/**
	 * This method initializes notaz1	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz1() {
		if (notaz1 == null) {
			notaz1 = new DecimalFormat("#,##0.00");
		}
		return notaz1;
	}

	/**
	 * This method initializes txtSpeseTr	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtSpeseTr() {
		if (txtSpeseTr == null) {
			txtSpeseTr = new JFormattedTextField(getNotaz1());
			txtSpeseTr.setBounds(new Rectangle(100, 182, 55, 20));
		}
		return txtSpeseTr;
	}

	/**
	 * This method initializes dataTrasporto	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	private JDateChooser getDataTrasporto() {
		if (dataTrasporto == null) {
			dataTrasporto = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataTrasporto.setBounds(new Rectangle(235, 137, 100, 24));
			dataTrasporto.setDate(new Date());
		}
		return dataTrasporto;
	}

	/**
	 * This method initializes notaz2	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz2() {
		if (notaz2 == null) {
			notaz2 = new DecimalFormat("#");
		}
		return notaz2;
	}

	/**
	 * This method initializes txtColli	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtColli() {
		if (txtColli == null) {
			txtColli = new JFormattedTextField(getNotaz2());
			txtColli.setBounds(new Rectangle(425, 139, 50, 20));
		}
		return txtColli;
	}

	/**
	 * This method initializes notaz3	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz3() {
		if (notaz3 == null) {
			notaz3 = new DecimalFormat("#,##0.00");
		}
		return notaz3;
	}

	/**
	 * This method initializes txtPeso	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtPeso() {
		if (txtPeso == null) {
			txtPeso = new JFormattedTextField(getNotaz3());
			txtPeso.setBounds(new Rectangle(425, 182, 50, 20));
		}
		return txtPeso;
	}

	/**
	 * This method initializes cmbCausale	
	 * 	
	 * @return rf.myswing.IDJComboBox	
	 */
	private IDJComboBox getCmbCausale() {
		if (cmbCausale == null) {
			cmbCausale = new IDJComboBox();
			cmbCausale.setBounds(new Rectangle(575, 93, 150, 26));
		}
		return cmbCausale;
	}

	/**
	 * This method initializes cmbAspetto	
	 * 	
	 * @return rf.myswing.IDJComboBox	
	 */
	private IDJComboBox getCmbAspetto() {
		if (cmbAspetto == null) {
			cmbAspetto = new IDJComboBox();
			cmbAspetto.setBounds(new Rectangle(575, 136, 150, 26));
		}
		return cmbAspetto;
	}

	/**
	 * This method initializes v	
	 * 	
	 * @return java.util.Vector	
	 */
	private Vector getV() {
		if (v == null) {
			v = new Vector();
		}
		return v;
	}

	/**
	 * This method initializes cmbConsegna	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbConsegna() {
		if (cmbConsegna == null) {
			cmbConsegna = new JComboBox(getV());
			cmbConsegna.setBounds(new Rectangle(575, 179, 80, 26));
		}
		return cmbConsegna;
	}

	/**
	 * This method initializes v1	
	 * 	
	 * @return java.util.Vector	
	 */
	private Vector getV1() {
		if (v1 == null) {
			v1 = new Vector();
		}
		return v1;
	}

	/**
	 * This method initializes cmbPorto	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbPorto() {
		if (cmbPorto == null) {
			cmbPorto = new JComboBox(getV1());
			cmbPorto.setBounds(new Rectangle(715, 179, 67, 26));
		}
		return cmbPorto;
	}

	/**
	 * This method initializes btnNuovoPagamento	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNuovoPagamento() {
		if (btnNuovoPagamento == null) {
			ImageIcon imageIcon = new ImageIcon(getUserDir() + File.separator + "\\resource\\nuovo.png");
			btnNuovoPagamento = new JButton();
			btnNuovoPagamento.setBounds(new Rectangle(740, 50, 42, 26));
			btnNuovoPagamento.setIcon(imageIcon);
			btnNuovoPagamento.setToolTipText("Nuovo Pagamento");
		}
		return btnNuovoPagamento;
	}

	/**
	 * This method initializes userDir	
	 * 	
	 * @return java.lang.String	
	 */
	private String getUserDir() {
		if (userDir == null) {
			userDir = System.getProperty("user.dir");
		}
		return userDir;
	}

	/**
	 * This method initializes btnNuovaCausale	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNuovaCausale() {
		if (btnNuovaCausale == null) {
			ImageIcon imageIcon1 = new ImageIcon(getUserDir1() + File.separator + "\\resource\\nuovo.png");
			btnNuovaCausale = new JButton();
			btnNuovaCausale.setBounds(new Rectangle(740, 93, 42, 26));
			btnNuovaCausale.setIcon(imageIcon1);
			btnNuovaCausale.setToolTipText("Nuova Causale");
		}
		return btnNuovaCausale;
	}

	/**
	 * This method initializes userDir1	
	 * 	
	 * @return java.lang.String	
	 */
	private String getUserDir1() {
		if (userDir1 == null) {
			userDir1 = System.getProperty("user.dir");
		}
		return userDir1;
	}

	/**
	 * This method initializes btnNuovoAspetto	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNuovoAspetto() {
		if (btnNuovoAspetto == null) {
			ImageIcon imageIcon2 = new ImageIcon(getUserDir2() + File.separator + "\\resource\\nuovo.png");
			btnNuovoAspetto = new JButton();
			btnNuovoAspetto.setBounds(new Rectangle(740, 136, 42, 26));
			btnNuovoAspetto.setIcon(imageIcon2);
			btnNuovoAspetto.setToolTipText("Nuovo Aspetto");
		}
		return btnNuovoAspetto;
	}

	/**
	 * This method initializes userDir2	
	 * 	
	 * @return java.lang.String	
	 */
	private String getUserDir2() {
		if (userDir2 == null) {
			userDir2 = System.getProperty("user.dir");
		}
		return userDir2;
	}

	/**
	 * This method initializes notaz4	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz4() {
		if (notaz4 == null) {
			notaz4 = new DecimalFormat("##00");
		}
		return notaz4;
	}

	/**
	 * This method initializes txtOraTr	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtOraTr() {
		if (txtOraTr == null) {
			txtOraTr = new JFormattedTextField(getNotaz4());
			txtOraTr.setBounds(new Rectangle(235, 182, 40, 20));
		}
		return txtOraTr;
	}

	/**
	 * This method initializes notaz5	
	 * 	
	 * @return java.text.DecimalFormat	
	 */
	private DecimalFormat getNotaz5() {
		if (notaz5 == null) {
			notaz5 = new DecimalFormat("##00");
		}
		return notaz5;
	}

	/**
	 * This method initializes txtMinTr	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtMinTr() {
		if (txtMinTr == null) {
			txtMinTr = new JFormattedTextField(getNotaz5());
			txtMinTr.setBounds(new Rectangle(295, 182, 40, 20));
		}
		return txtMinTr;
	}

	/**
	 * This method initializes btnAzzera	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAzzera() {
		if (btnAzzera == null) {
			btnAzzera = new JButton();
			btnAzzera.setBounds(new Rectangle(467, 7, 78, 26));
			btnAzzera.setText("Azzera");
		}
		return btnAzzera;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
