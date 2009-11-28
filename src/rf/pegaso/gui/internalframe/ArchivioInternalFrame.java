/**
 *
 */
package rf.pegaso.gui.internalframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import rf.myswing.util.ModalFrameUtil;
import rf.pegaso.gui.gestione.ClientiGestione;
import rf.pegaso.gui.gestione.DocumentiGestione;
import rf.pegaso.gui.gestione.StampeEtichette;
import rf.utility.db.DBManager;

/**
 * @author Hunter
 *
 */
public class ArchivioInternalFrame extends JInternalFrame {
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnClienti) {
				apriClienti();
			} else if (e.getSource() == btnStampe) {
				apriStampe();
			} 
		}

		/**
		 *
		 */
		private void apriDocumenti() {
			DocumentiGestione documenti = new DocumentiGestione(padre);
			documenti.setVisible(true);

		}
	}

	private JButton btnClienti = null;
	private JButton btnStampe = null;
	private DBManager dbm;
	private JPanel jContentPane = null;
	private MyActionListener myActionListener; // @jve:decl-index=0:
	private JFrame padre;
	private JPanel pnlCentrale = null;
	private JButton jButton = null;
	private JButton btnScarico = null;
	/**
	 * This is the xxx default constructor
	 */
	public ArchivioInternalFrame(JFrame padre) {
		super("Gestione Archivio");
		this.dbm=DBManager.getIstanceSingleton();
		this.padre = padre;
		initialize();
	}

	

	

	

	/**
	 *
	 */
	public void apriStampe() {
		StampeEtichette stampe = new StampeEtichette(padre);
		stampe.setVisible(true);

	}



	

	

	/**
	 *
	 */
	private void apriClienti() {
		ClientiGestione cg = new ClientiGestione(padre);
		cg.setVisible(true);

	}

	

	

	

	

	

	

	/**
	 * This method initializes btnClienti
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnClienti() {
		if (btnClienti == null) {
			try {
				btnClienti = new JButton();
				btnClienti.setText("Clienti"); // Generated
				btnClienti.setPreferredSize(new Dimension(120, 70)); // Generated
				btnClienti.setVisible(true);
				btnClienti.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnClienti;
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
			try {
				GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
				gridBagConstraints22.gridx = 3;
				gridBagConstraints22.gridy = 1;
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 4;
				gridBagConstraints1.gridy = 1;
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 3;
				gridBagConstraints.gridy = 1;
				GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
				gridBagConstraints14.gridx = 4;
				gridBagConstraints14.gridy = 2;
				GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
				gridBagConstraints17.gridx = 3; // Generated
				gridBagConstraints17.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints17.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridx = 0; // Generated
				gridBagConstraints3.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints3.gridy = 1; // Generated
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new GridBagLayout()); // Generated
				pnlCentrale.add(getBtnClienti(), gridBagConstraints3);
				pnlCentrale.add(getBtnStampe(), gridBagConstraints22);
				
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCentrale;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		// Inzializziamo tutti gli
		// ascoltatori
		inizializeAscoltatori();

		this.setSize(628, 364);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // Generated
		this.setTitle("Gestione"); // Generated
		this.setMaximizable(true); // Generated
		this.setIconifiable(true); // Generated
		this.setClosable(true); // Generated
		this.setContentPane(getJContentPane());

	}

	/**
	 *
	 */
	private void inizializeAscoltatori() {
		myActionListener = new MyActionListener();

	}

	/**
	 * This method initializes btnStampe1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnStampe() {
		if (btnStampe == null) {
			try {
				btnStampe = new JButton();
				btnStampe.setPreferredSize(new Dimension(120, 70)); // Generated
				btnStampe.setText("Stampe");
				btnStampe.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				
			}
			
		}
		return btnStampe;
	}

	

} // @jve:decl-index=0:visual-constraint="10,10"
