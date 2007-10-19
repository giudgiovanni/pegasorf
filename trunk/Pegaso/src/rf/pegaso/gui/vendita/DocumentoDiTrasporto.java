package rf.pegaso.gui.vendita;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.DBManager;
import rf.utility.gui.UtilGUI;

import com.toedter.calendar.JDateChooser;

public class DocumentoDiTrasporto extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DBManager dbm = null;
	private JPanel jContentPane = null;
	private JPanel jPanelNord = null;
	private JButton btnChiudi = null;
	private JButton btnSalva = null;
	private JButton btnStampa = null;
	private JLabel lblDDT = null;
	private JLabel lblNumero = null;
	private JTextField txtNumero = null;
	private JLabel lblData = null;
	private JDateChooser dataCorrente = null;
	private JPanel jPanelCentro = null;
	private JScrollPane jScrollPane = null;
	private JXTable jTable = null;
	private JPanel jPanelSud = null;
	private JButton btnElimina = null;
	private JLabel lblTipoPrezzo = null;
	private JComboBox comboPrezzo = null;
	private JTextField txtUtile = null;
	private JTextField txtNPezzi = null;
	private JTextField txtTotale = null;
	private JLabel lblUtile = null;
	private JLabel lblNPezzi = null;
	private JLabel lblTotale = null;
	
	public DocumentoDiTrasporto(){
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(800, 500));
		this.setTitle("Vendita al Banco");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}
		});
		setExtendedState(MAXIMIZED_BOTH);
		UtilGUI.centraFrame(this);
	}
	
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnChiudi )
				dispose();
			else if ( e.getSource() == btnSalva )
				salva();
			else if ( e.getSource() == btnStampa )
				stampa();
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
			jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
			jContentPane.add(getJPanelCentro(), BorderLayout.CENTER);
			jContentPane.add(getJPanelSud(), BorderLayout.SOUTH);
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
			lblData = new JLabel();
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblData.setBounds(new Rectangle(310, 12, 30, 16));
			lblData.setText("Data");
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(130, 12, 50, 16));
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero.setText("Numero");
			lblDDT = new JLabel();
			lblDDT.setBounds(new Rectangle(10, 5, 50, 30));
			lblDDT.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			lblDDT.setFont(new Font("Dialog", Font.BOLD, 18));
			lblDDT.setText(" DDT");
			jPanelNord = new JPanel();
			jPanelNord.setLayout(null);
			jPanelNord.setPreferredSize(new Dimension(0, 40)); // Generated
			jPanelNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelNord.add(getBtnChiudi(), null);
			jPanelNord.add(getBtnSalva(), null);
			jPanelNord.add(getBtnStampa(), null);
			jPanelNord.add(lblDDT, null);
			jPanelNord.add(lblNumero, null);
			jPanelNord.add(getTxtNumero(), null);
			jPanelNord.add(lblData, null);
			jPanelNord.add(getDataCorrente(), null);
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
			btnChiudi.setText("Chiudi");
			btnChiudi.setBounds(new Rectangle(700, 7, 82, 26));
			btnChiudi.addActionListener(new MyButtonListener());
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
			btnSalva.setText("Salva");
			btnSalva.setBounds(new Rectangle(514, 7, 82, 26));
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
			btnStampa.setText("Stampa");
			btnStampa.setBounds(new Rectangle(607, 7, 82, 26));
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
	
	private JDateChooser getDataCorrente() {
		if (dataCorrente == null)
			try {
				dataCorrente = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataCorrente.setDate(new java.util.Date());
				dataCorrente.setBounds(new Rectangle(350, 8, 112, 24));
			} catch (Throwable throwable) {
			}
		return dataCorrente;
	}

	/**
	 * This method initializes jPanelCentro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCentro() {
		if (jPanelCentro == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(new GridBagLayout());
			jPanelCentro.add(getJScrollPane(), gridBagConstraints);
		}
		return jPanelCentro;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JXTable getJTable() {
		if (jTable == null) {
			jTable = new JXTable();
		}
		return jTable;
	}
	
	private void stampa(){
		
	}
	
	private void salva(){
		
	}

	/**
	 * This method initializes jPanelSud	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSud() {
		if (jPanelSud == null) {
			lblTotale = new JLabel();
			lblTotale.setBounds(new Rectangle(605, 10, 40, 16));
			lblTotale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblTotale.setText("Totale");
			lblNPezzi = new JLabel();
			lblNPezzi.setBounds(new Rectangle(495, 10, 50, 16));
			lblNPezzi.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNPezzi.setText("n° Pezzi");
			lblUtile = new JLabel();
			lblUtile.setBounds(new Rectangle(385, 10, 30, 16));
			lblUtile.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblUtile.setText("Utile");
			lblTipoPrezzo = new JLabel();
			lblTipoPrezzo.setBounds(new Rectangle(160, 22, 70, 16));
			lblTipoPrezzo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblTipoPrezzo.setText("Tipo prezzo");
			jPanelSud = new JPanel();
			jPanelSud.setLayout(null);
			jPanelSud.setPreferredSize(new Dimension(0,60));
			jPanelSud.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanelSud.add(getBtnElimina(), null);
			jPanelSud.add(lblTipoPrezzo, null);
			jPanelSud.add(getComboPrezzo(), null);
			jPanelSud.add(getTxtUtile(), null);
			jPanelSud.add(getTxtNPezzi(), null);
			jPanelSud.add(getTxtTotale(), null);
			jPanelSud.add(lblUtile, null);
			jPanelSud.add(lblNPezzi, null);
			jPanelSud.add(lblTotale, null);
		}
		return jPanelSud;
	}

	/**
	 * This method initializes btnElimina	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			btnElimina = new JButton();
			btnElimina.setBounds(new Rectangle(15, 17, 82, 26));
			btnElimina.setText("Elimina");
		}
		return btnElimina;
	}

	/**
	 * This method initializes comboPrezzo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboPrezzo() {
		if (comboPrezzo == null) {
			Vector<String> v = new Vector<String>();
			v.add("Dettaglio");
			v.add("Ingrosso");
			comboPrezzo = new JComboBox(v);
			comboPrezzo.setBounds(new Rectangle(245, 18, 80, 24));
		}
		return comboPrezzo;
	}

	/**
	 * This method initializes txtUtile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUtile() {
		if (txtUtile == null) {
			txtUtile = new JTextField();
			txtUtile.setBounds(new Rectangle(385, 30, 90, 20));
		}
		return txtUtile;
	}

	/**
	 * This method initializes txtNPezzi	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNPezzi() {
		if (txtNPezzi == null) {
			txtNPezzi = new JTextField();
			txtNPezzi.setBounds(new Rectangle(495, 30, 90, 20));
		}
		return txtNPezzi;
	}

	/**
	 * This method initializes txtTotale	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTotale() {
		if (txtTotale == null) {
			txtTotale = new JTextField();
			txtTotale.setBounds(new Rectangle(605, 30, 90, 20));
		}
		return txtTotale;
	}
}
