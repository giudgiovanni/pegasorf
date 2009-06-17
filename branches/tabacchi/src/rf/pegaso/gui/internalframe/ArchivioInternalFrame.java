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
import rf.pegaso.gui.gestione.ArticoliGestione;
import rf.pegaso.gui.gestione.CaricoGui;
import rf.pegaso.gui.gestione.ClientiGestione;
import rf.pegaso.gui.gestione.DocumentiGestione;
import rf.pegaso.gui.gestione.FatturaByDdt;
import rf.pegaso.gui.gestione.FornitoriGestione;
import rf.pegaso.gui.gestione.RepartiGestione;
import rf.pegaso.gui.gestione.ScaricoGui;
import rf.pegaso.gui.gestione.StampeEtichette;
import rf.pegaso.gui.gestione.UnitaDiMisuraGestione;
import rf.pegaso.gui.viste.GiacenzeGUI;
import rf.pegaso.gui.viste.RicercheArchivio;
import rf.utility.ConversioneArt;
import rf.utility.ConversioneForn;
import rf.utility.db.DBManager;

/**
 * @author Hunter
 *
 */
public class ArchivioInternalFrame extends JInternalFrame {
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReparti) {
				apriReparti();
			} else if (e.getSource() == btnUnitaMisura) {
				apriUnitadiMisura();
			} else if (e.getSource() == btnArticoli) {
				apriArticoli();
			} else if (e.getSource() == btnClienti) {
				apriClienti();
			} else if (e.getSource() == btnFornitori) {
				apriFornitori();
			} else if (e.getSource() == btnCarico) {
				apriCaricoMerce();
			} else if (e.getSource() == btnScarico) {
				apriScaricoMerce();
			} else if (e.getSource() == btnInventario) {
				apriGiacenze();
			} else if (e.getSource() == btnDocumento) {
				apriDocumenti();
			} else if (e.getSource() == btnStampe) {
				apriStampe();
			} else if (e.getSource() == btnRicerche) {
				apriRicerche();
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

	private JButton btnArticoli = null;
	private JButton btnCarico = null;
	private JButton btnClienti = null;
	private JButton btnDocumento = null;
	private JButton btnFornitori = null;
	private JButton btnInventario = null;
	private JButton btnReparti = null;
	private JButton btnRicerche = null;
	private JButton btnScarico = null;
	private JButton btnStampe = null;
	private JButton btnUnitaMisura = null;
	private DBManager dbm;
	private JPanel jContentPane = null;
	private MyActionListener myActionListener; // @jve:decl-index=0:
	private JFrame padre;
	private JPanel pnlCentrale = null;
	private JButton jButton = null;
	private JButton btnFtByDdt = null;

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
	public void apriGiacenze() {
		GiacenzeGUI inventario = new GiacenzeGUI(padre);
		inventario.setVisible(true);

	}

	/**
	 *
	 */
	public void apriScaricoMerce() {
		ScaricoGui ordine = new ScaricoGui();
		//Apriamo il Frame in modo modale
		ModalFrameUtil.showAsModal(ordine, padre);
		//ordine.setVisible(true);
		//ordine.setVisible(true);
	}

	public void apriDdtAFattura(){
		FatturaByDdt f=new FatturaByDdt(padre);
		f.setVisible(true);
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
	private void apriArticoli() {
		ArticoliGestione cg = new ArticoliGestione();
		//Apriamo la finestra in modo modale e
		//la massimizziamo
		//ModalFrameUtil.showAsModal(cg, padre);
		cg.setVisible(true);
		cg.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	/**
	 *
	 */
	private void apriCaricoMerce() {
		CaricoGui carico = new CaricoGui(padre);
		carico.setVisible(true);

	}

	/**
	 *
	 */
	private void apriClienti() {
		ClientiGestione cg = new ClientiGestione(padre);
		cg.setVisible(true);

	}

	/**
	 *
	 */
	private void apriFornitori() {
		FornitoriGestione cg = new FornitoriGestione(padre);
		cg.setVisible(true);

	}

	/**
	 * Apre il pannello per la gestione dei reparti
	 */
	private void apriReparti() {
		RepartiGestione rg = new RepartiGestione(padre);
		rg.setVisible(true);

	}

	/**
	 *
	 */
	private void apriRicerche() {
		RicercheArchivio ric = new RicercheArchivio(this.padre);
		ric.setVisible(true);

	}

	/**
	 *
	 */
	private void apriUnitadiMisura() {
		UnitaDiMisuraGestione rg = new UnitaDiMisuraGestione(padre);
		rg.setVisible(true);

	}

	/**
	 *
	 */
	private void conversioneArchivio() {
		ConversioneArt c = new ConversioneArt(padre);
		c.setVisible(true);

	}

	/**
	 *
	 */
	private void conversioneArchivioForn() {
		ConversioneForn c = new ConversioneForn(padre);
		c.setVisible(true);

	}

	/**
	 * This method initializes btnArticoli
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnArticoli() {
		if (btnArticoli == null) {
			try {
				btnArticoli = new JButton();
				btnArticoli.setText("Articoli"); // Generated
				btnArticoli.setPreferredSize(new Dimension(120, 70)); // Generated
				btnArticoli.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnArticoli;
	}

	/**
	 * This method initializes btnCarico
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnCarico() {
		if (btnCarico == null) {
			try {
				btnCarico = new JButton();
				btnCarico.setText("Carico"); // Generated
				btnCarico.setPreferredSize(new Dimension(120, 70)); // Generated
				btnCarico.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnCarico;
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
				btnClienti.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnClienti;
	}

	/**
	 * This method initializes btnDocumento
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDocumento() {
		if (btnDocumento == null) {
			try {
				btnDocumento = new JButton();
				btnDocumento.setPreferredSize(new Dimension(120, 70)); // Generated
				btnDocumento.setText("Documenti");
				btnDocumento.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnDocumento;
	}

	/**
	 * This method initializes btnFornitori
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnFornitori() {
		if (btnFornitori == null) {
			try {
				btnFornitori = new JButton();
				btnFornitori.setText("Fornitori"); // Generated
				btnFornitori.setPreferredSize(new Dimension(120, 70)); // Generated
				btnFornitori.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnFornitori;
	}

	/**
	 * This method initializes btnInventario
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnInventario() {
		if (btnInventario == null) {
			try {
				btnInventario = new JButton();
				btnInventario.setText("Inventario"); // Generated
				btnInventario.setPreferredSize(new Dimension(120, 70)); // Generated
				btnInventario.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnInventario;
	}

	/**
	 * This method initializes btnReparti
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnReparti() {
		if (btnReparti == null) {
			try {
				btnReparti = new JButton();
				btnReparti.setText("Categoria"); // Generated
				btnReparti.setPreferredSize(new Dimension(120, 70)); // Generated
				btnReparti.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnReparti;
	}

	/**
	 * This method initializes btnRicerche
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRicerche() {
		if (btnRicerche == null) {
			try {
				btnRicerche = new JButton();
				btnRicerche.setText("Ricerche"); // Generated
				btnRicerche.setPreferredSize(new Dimension(120, 70)); // Generated
				btnRicerche.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnRicerche;
	}

	/**
	 * This method initializes btnScarico
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnScarico() {
		if (btnScarico == null) {
			try {
				btnScarico = new JButton();
				btnScarico.setText("Scarico"); // Generated
				btnScarico.setPreferredSize(new Dimension(120, 70)); // Generated
				btnScarico.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnScarico;
	}

	/**
	 * This method initializes btnStampe
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
				// TODO: Something
			}
		}
		return btnStampe;
	}

	/**
	 * This method initializes btnUnitaMisura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnUnitaMisura() {
		if (btnUnitaMisura == null) {
			try {
				btnUnitaMisura = new JButton();
				btnUnitaMisura.setText("Unita di Misura"); // Generated
				btnUnitaMisura.setPreferredSize(new Dimension(120, 70)); // Generated
				btnUnitaMisura.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnUnitaMisura;
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
				GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
				gridBagConstraints15.gridx = 3;
				gridBagConstraints15.gridy = 2;
				GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
				gridBagConstraints14.gridx = 4;
				gridBagConstraints14.gridy = 2;
				GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
				gridBagConstraints18.gridx = 4; // Generated
				gridBagConstraints18.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
				gridBagConstraints17.gridx = 2; // Generated
				gridBagConstraints17.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
				gridBagConstraints13.gridx = 2; // Generated
				gridBagConstraints13.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints13.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
				gridBagConstraints21.gridx = 0; // Generated
				gridBagConstraints21.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints21.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
				gridBagConstraints12.gridx = 4; // Generated
				gridBagConstraints12.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints12.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
				gridBagConstraints11.gridx = 3; // Generated
				gridBagConstraints11.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.gridx = 3; // Generated
				gridBagConstraints4.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints4.gridwidth = 1; // Generated
				gridBagConstraints4.fill = GridBagConstraints.NONE; // Generated
				gridBagConstraints4.gridheight = 1; // Generated
				gridBagConstraints4.ipadx = 0; // Generated
				gridBagConstraints4.weightx = 0.0; // Generated
				gridBagConstraints4.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridx = 4; // Generated
				gridBagConstraints3.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints3.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.gridx = 0; // Generated
				gridBagConstraints2.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints2.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 2; // Generated
				gridBagConstraints1.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints1.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints.gridy = 0; // Generated
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new GridBagLayout()); // Generated
				pnlCentrale.add(getBtnReparti(), gridBagConstraints); // Generated
				pnlCentrale.add(getBtnUnitaMisura(), gridBagConstraints1); // Generated
				pnlCentrale.add(getBtnArticoli(), gridBagConstraints2); // Generated
				pnlCentrale.add(getBtnClienti(), gridBagConstraints3); // Generated
				pnlCentrale.add(getBtnFornitori(), gridBagConstraints4); // Generated
				pnlCentrale.add(getBtnCarico(), gridBagConstraints11); // Generated
				pnlCentrale.add(getBtnScarico(), gridBagConstraints12); // Generated
				pnlCentrale.add(getBtnInventario(), gridBagConstraints21); // Generated
				pnlCentrale.add(getBtnDocumento(), gridBagConstraints13); // Generated
				pnlCentrale.add(getBtnStampe(), gridBagConstraints17); // Generated
				pnlCentrale.add(getBtnRicerche(), gridBagConstraints18);
				pnlCentrale.add(getBtnFtByDdt(), gridBagConstraints15);
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
		this.setTitle("Gestione Archivi"); // Generated
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
	 * This method initializes btnFtByDdt
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnFtByDdt() {
		if (btnFtByDdt == null) {
			btnFtByDdt = new JButton();
			btnFtByDdt.setPreferredSize(new Dimension(120, 70));
			btnFtByDdt.setText("Fattura da DDT");
			btnFtByDdt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apriDdtAFattura();
				}
			});
		}
		return btnFtByDdt;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
