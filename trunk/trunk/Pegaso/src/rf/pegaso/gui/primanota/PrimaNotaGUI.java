package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public class PrimaNotaGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlCentrale = null;

	private JPanel pnlNord = null;

	private JPanel pnlCentro = null;

	private JPanel pnlNord1 = null;

	private JPanel pnlCentro1 = null;

	private JButton btnInserisci = null;

	private JButton Modifica = null;

	private JButton btnElimina = null;

	private JButton btnEsci = null;

	/**
	 * This is the default constructor
	 */
	public PrimaNotaGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Prima Nota");
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
			jContentPane.add(getPnlCentrale(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlCentrale	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			pnlCentrale = new JPanel();
			pnlCentrale.setLayout(new BorderLayout());
			pnlCentrale.add(getPnlNord(), BorderLayout.NORTH);
			pnlCentrale.add(getPnlCentro(), BorderLayout.CENTER);
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes pnlNord	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlNord() {
		if (pnlNord == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlNord = new JPanel();
			pnlNord.setLayout(flowLayout);
			pnlNord.setPreferredSize(new Dimension(0, 40));
			pnlNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlNord.add(getBtnInserisci(), null);
			pnlNord.add(getModifica(), null);
			pnlNord.add(getBtnElimina(), null);
			pnlNord.add(getBtnEsci(), null);
		}
		return pnlNord;
	}

	/**
	 * This method initializes pnlCentro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(new BorderLayout());
			pnlCentro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlCentro.add(getPnlNord1(), BorderLayout.NORTH);
			pnlCentro.add(getPnlCentro1(), BorderLayout.CENTER);
		}
		return pnlCentro;
	}

	/**
	 * This method initializes pnlNord1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlNord1() {
		if (pnlNord1 == null) {
			pnlNord1 = new JPanel();
			pnlNord1.setLayout(null);
			pnlNord1.setPreferredSize(new Dimension(0, 100));
			pnlNord1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		return pnlNord1;
	}

	/**
	 * This method initializes pnlCentro1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlCentro1() {
		if (pnlCentro1 == null) {
			pnlCentro1 = new JPanel();
			pnlCentro1.setLayout(new BorderLayout());
			pnlCentro1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		return pnlCentro1;
	}

	/**
	 * This method initializes btnInserisci	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnInserisci() {
		if (btnInserisci == null) {
			btnInserisci = new JButton();
			btnInserisci.setText("Inserisci (Invio)");
		}
		return btnInserisci;
	}

	/**
	 * This method initializes Modifica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifica() {
		if (Modifica == null) {
			Modifica = new JButton();
			Modifica.setText("Modifica");
		}
		return Modifica;
	}

	/**
	 * This method initializes btnElimina	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnElimina() {
		if (btnElimina == null) {
			btnElimina = new JButton();
			btnElimina.setText("Elimina (Canc)");
		}
		return btnElimina;
	}

	/**
	 * This method initializes btnEsci	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEsci() {
		if (btnEsci == null) {
			btnEsci = new JButton();
			btnEsci.setText("Esci (Esc)");
		}
		return btnEsci;
	}

}
