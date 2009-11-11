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
import rf.pegaso.gui.gestione.CaricaAggiornaTabacchiGui;
import rf.pegaso.gui.gestione.CaricoGui;
import rf.pegaso.gui.gestione.CaricoTabacchiGui;
import rf.pegaso.gui.gestione.ClientiGestione;
import rf.pegaso.gui.gestione.DocumentiGestione;
import rf.pegaso.gui.gestione.FatturaByDdt;
import rf.pegaso.gui.gestione.FornitoriGestione;
import rf.pegaso.gui.gestione.GrattaVinciGestione;
import rf.pegaso.gui.gestione.RepartiGestione;
import rf.pegaso.gui.gestione.ScaricoGui;
import rf.pegaso.gui.gestione.StampeEtichette;
import rf.pegaso.gui.gestione.TabacchiGestione;
import rf.pegaso.gui.gestione.UnitaDiMisuraGestione;
import rf.pegaso.gui.gestione.VenditeGui;
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
			if (e.getSource() == btnArticoli) {
				apriArticoli();
			} else if (e.getSource() == btnTabacchi) {
				apriTabacchi();
			} else if (e.getSource() == btnClienti) {
				apriClienti();
			} else if (e.getSource() == btnFornitori) {
				apriFornitori();
			} else if (e.getSource() == btnCarico) {
				apriCaricoMerce();
			} else if (e.getSource() == btnCaricoTabacchi) {
				apriCaricoMerceTabacchi();
			} else if (e.getSource() == btnScarico) {
				apriVenditeGUI();
			} else if (e.getSource() == btnInventario) {
				apriGiacenze();
			} else if (e.getSource() == btnStampe) {
				apriStampe();
			} else if (e.getSource() == btnRicerche) {
				apriRicerche();
			}else if (e.getSource() == btnAggiornaTabacchi) {
				apriAggTabacchi();
			}else if (e.getSource() == btnGrattaVinci) {
				apriGrattaVinci();
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
	private JButton btnFornitori = null;
	private JButton btnInventario = null;
	private JButton btnRicerche = null;
	private JButton btnScarico = null;
	private JButton btnStampe = null;
	private DBManager dbm;
	private JPanel jContentPane = null;
	private MyActionListener myActionListener; // @jve:decl-index=0:
	private JFrame padre;
	private JPanel pnlCentrale = null;
	private JButton jButton = null;
	private JButton btnFtByDdt = null;
	private JButton btnTabacchi = null;
	private JButton btnCaricoTabacchi = null;
	private JButton btnAggiornaTabacchi = null;
	private JButton btnGrattaVinci = null;
	/**
	 * This is the xxx default constructor
	 */
	public ArchivioInternalFrame(JFrame padre) {
		super("Gestione Archivio");
		this.dbm=DBManager.getIstanceSingleton();
		this.padre = padre;
		initialize();
	}

	public void apriTabacchi() {
		TabacchiGestione cg = new TabacchiGestione();
		//Apriamo la finestra in modo modale e
		//la massimizziamo
		//ModalFrameUtil.showAsModal(cg, padre);
		cg.setVisible(true);
		cg.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
	public void apriVenditeGUI() {
		VenditeGui ordine = new VenditeGui();
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
	
	private void apriCaricoMerceTabacchi() {
		CaricoTabacchiGui carico = new CaricoTabacchiGui(padre);
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
	
	private void apriAggTabacchi(){
		CaricaAggiornaTabacchiGui cat = new CaricaAggiornaTabacchiGui(this.padre);
		cat.setVisible(true);
	}
	
	private void apriGrattaVinci(){
		GrattaVinciGestione gvg = new GrattaVinciGestione();
		gvg.setVisible(true);
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
				btnArticoli.setVisible(true);
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
				btnCarico.setVisible(true);
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
				btnClienti.setVisible(false);
				btnClienti.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnClienti;
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
				btnFornitori.setVisible(false);
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
				btnScarico.setText("Vendite"); // Generated
				btnScarico.setPreferredSize(new Dimension(120, 70)); // Generated
//				btnScarico.setVisible(false);
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
				gridBagConstraints22.gridx = 2;
				gridBagConstraints22.gridy = 1;
				GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
				gridBagConstraints13.gridx = 3;
				gridBagConstraints13.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints13.gridy = 0;
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 2;
				gridBagConstraints.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints.gridy = 0;
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 0;
				gridBagConstraints1.gridy = 0;
				GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
				gridBagConstraints15.gridx = 0;
				gridBagConstraints15.gridy = 2;
				GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
				gridBagConstraints14.gridx = 4;
				gridBagConstraints14.gridy = 2;
				GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
				gridBagConstraints18.gridx = 2; // Generated
				gridBagConstraints18.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints18.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
				gridBagConstraints17.gridx = 3; // Generated
				gridBagConstraints17.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints17.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
				gridBagConstraints21.gridx = 0; // Generated
				gridBagConstraints21.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints21.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
				gridBagConstraints12.gridx = 4; // Generated
				gridBagConstraints12.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints12.gridy = 0; // Generated
				GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
				gridBagConstraints31.gridx = 3; // Generated
				gridBagConstraints31.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.gridx = 4; // Generated
				gridBagConstraints4.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints4.gridwidth = 1; // Generated
				gridBagConstraints4.fill = GridBagConstraints.NONE; // Generated
				gridBagConstraints4.gridheight = 1; // Generated
				gridBagConstraints4.ipadx = 0; // Generated
				gridBagConstraints4.weightx = 0.0; // Generated
				gridBagConstraints4.gridy = 1; // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridx = 3; // Generated
				gridBagConstraints3.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints3.gridy = 2; // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.gridx = 0; // Generated
				gridBagConstraints2.insets = new Insets(10, 10, 10, 10); // Generated
				gridBagConstraints2.gridy = 1; // Generated
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new GridBagLayout()); // Generated
				pnlCentrale.add(getBtnArticoli(), gridBagConstraints2);
				pnlCentrale.add(getBtnClienti(), gridBagConstraints3);
				pnlCentrale.add(getBtnFornitori(), gridBagConstraints4);
				pnlCentrale.add(getBtnCarico(), gridBagConstraints31);
				pnlCentrale.add(getBtnScarico(), gridBagConstraints12);
				pnlCentrale.add(getBtnInventario(), gridBagConstraints21);
				pnlCentrale.add(getBtnStampe(), gridBagConstraints17);
				pnlCentrale.add(getBtnRicerche(), gridBagConstraints18);
				pnlCentrale.add(getBtnFtByDdt(), gridBagConstraints15);
				pnlCentrale.add(getBtnTabacchi(), gridBagConstraints1);
				pnlCentrale.add(getBtnCaricoTabacchi(), gridBagConstraints);
				pnlCentrale.add(getBtnAggiornaTabacchi(), gridBagConstraints13);
				pnlCentrale.add(getBtnGrattaVinci(), gridBagConstraints22);
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
			btnFtByDdt.setVisible(false);
			btnFtByDdt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apriDdtAFattura();
				}
			});
		}
		return btnFtByDdt;
	}

	/**
	 * This method initializes btnTabacchi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnTabacchi() {
		if (btnTabacchi == null) {
			btnTabacchi = new JButton();
			btnTabacchi.setPreferredSize(new Dimension(120, 70));
			btnTabacchi.setText("Tabacchi");
			btnTabacchi.addActionListener(myActionListener);
		}
		return btnTabacchi;
	}

	/**
	 * This method initializes btnCaricoTabacchi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCaricoTabacchi() {
		if (btnCaricoTabacchi == null) {
			btnCaricoTabacchi = new JButton();
			btnCaricoTabacchi.setPreferredSize(new Dimension(120, 70));
			btnCaricoTabacchi.setText("<html>Ordine<P>Tabacchi</html>");
			btnCaricoTabacchi.addActionListener(myActionListener);
		}
		return btnCaricoTabacchi;
	}

	/**
	 * This method initializes btnAggiornaTabacchi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAggiornaTabacchi() {
		if (btnAggiornaTabacchi == null) {
			btnAggiornaTabacchi = new JButton();
			btnAggiornaTabacchi.setText("<html>Aggiorna<P>Tabacchi</html>");
			btnAggiornaTabacchi.setPreferredSize(new Dimension(120, 70));
			btnAggiornaTabacchi.addActionListener(myActionListener);
		}
		return btnAggiornaTabacchi;
	}

	/**
	 * This method initializes btnGrattaVinci	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGrattaVinci() {
		if (btnGrattaVinci == null) {
			try {
				btnGrattaVinci = new JButton();
				btnGrattaVinci.setText("Gratta e Vinci"); // Generated
				btnGrattaVinci.setPreferredSize(new Dimension(120, 70)); // Generated
				btnGrattaVinci.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnGrattaVinci;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
