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
import rf.pegaso.gui.gestione.SospesiGui;
import rf.pegaso.gui.gestione.StampeEtichette;
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
			} else if (e.getSource() == btnClienti) {
				apriClienti();
			} else if (e.getSource() == btnFornitori) {
				apriFornitori();
			} else if (e.getSource() == btnCarico) {
				apriCaricoMerce();
			} else if (e.getSource() == btnVendite) {
				apriVenditeGUI();
			} else if (e.getSource() == btnInventario) {
				apriGiacenze();
			} else if (e.getSource() == btnStampe) {
				apriStampe();
			} else if (e.getSource() == btnRicerche) {
				apriRicerche();
			}else if (e.getSource() == btnScarico) {
				apriScarico();
			}else if ( e.getSource() == btnSospesi ) {
				apriSospesi();
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
	private JButton btnVendite = null;
	private JButton btnStampe = null;
	private DBManager dbm;
	private JPanel jContentPane = null;
	private MyActionListener myActionListener; // @jve:decl-index=0:
	private JFrame padre;
	private JPanel pnlCentrale = null;
	private JButton jButton = null;
	private JButton btnFtByDdt = null;
	private JButton btnScarico = null;
	private JButton btnSospesi = null;
	/**
	 * This is the xxx default constructor
	 */
	public ArchivioInternalFrame(JFrame padre) {
		super("Gestione Archivio");
		this.dbm=DBManager.getIstanceSingleton();
		this.padre = padre;
		initialize();
	}

	public void apriScarico() {
		ScaricoGui ordine = new ScaricoGui();
		//Apriamo il Frame in modo modale
		ModalFrameUtil.showAsModal(ordine, padre);
		//ordine.setVisible(true);
		//ordine.setVisible(true);		 
	}
	
	private void apriSospesi() {
		SospesiGui sospesi = new SospesiGui();
		ModalFrameUtil.showAsModal(sospesi, padre);
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
				btnClienti.setVisible(true);
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
				btnFornitori.setVisible(true);
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
	private JButton getBtnVendite() {
		if (btnVendite == null) {
			try {
				btnVendite = new JButton();
				btnVendite.setText("Vendite"); // Generated
				btnVendite.setPreferredSize(new Dimension(120, 70)); // Generated
//				btnScarico.setVisible(false);
				btnVendite.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnVendite;
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
				GridBagConstraints gridBagConstraints00 = new GridBagConstraints();
				gridBagConstraints00.gridx = 0;
				gridBagConstraints00.gridy = 0;
				GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
				gridBagConstraints10.gridx = 1;
				gridBagConstraints10.gridy = 0;
				GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
				gridBagConstraints20.gridx = 2;
				gridBagConstraints20.gridy = 0;
				GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
				gridBagConstraints30.gridx = 3;
				gridBagConstraints30.gridy = 0;
				GridBagConstraints gridBagConstraints01 = new GridBagConstraints();
				gridBagConstraints01.gridx = 0;
				gridBagConstraints01.gridy = 1;
				GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
				gridBagConstraints11.gridx = 1;
				gridBagConstraints11.gridy = 1;
				GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
				gridBagConstraints21.gridx = 2;
				gridBagConstraints21.gridy = 1;
				GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
				gridBagConstraints31.gridx = 3;
				gridBagConstraints31.gridy = 1;
				GridBagConstraints gridBagConstraints02 = new GridBagConstraints();
				gridBagConstraints02.gridx = 0;
				gridBagConstraints02.gridy = 2;
				GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
				gridBagConstraints12.gridx = 1;
				gridBagConstraints12.gridy = 2;
				GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
				gridBagConstraints22.gridx = 2;
				gridBagConstraints22.gridy = 2;
				
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new GridBagLayout()); // Generated
				pnlCentrale.add(getBtnArticoli(), gridBagConstraints00);
				pnlCentrale.add(getBtnCarico(), gridBagConstraints10);
				pnlCentrale.add(getBtnScarico(), gridBagConstraints20);
				pnlCentrale.add(getBtnInventario(), gridBagConstraints30);
				pnlCentrale.add(getBtnSospesi(), gridBagConstraints01);
				pnlCentrale.add(getBtnVendite(), gridBagConstraints11);
				pnlCentrale.add(getBtnClienti(), gridBagConstraints21);
				pnlCentrale.add(getBtnFornitori(), gridBagConstraints31);
				pnlCentrale.add(getBtnFtByDdt(), gridBagConstraints02);				
				pnlCentrale.add(getBtnRicerche(), gridBagConstraints12);				
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
			btnFtByDdt.setVisible(true);
			btnFtByDdt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apriDdtAFattura();
				}
			});
		}
		return btnFtByDdt;
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

	/**
	 * This method initializes btnSospesi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSospesi() {
		if (btnSospesi == null) {
			try {
				btnSospesi = new JButton();
				btnSospesi.setText("Sospesi");
				btnSospesi.setPreferredSize(new Dimension(120, 70));
				btnSospesi.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return btnSospesi;
	}

	/**
	 * This method initializes btnScarico1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnScarico() {
		if (btnScarico == null) {
			btnScarico = new JButton();
			btnScarico.setPreferredSize(new Dimension(120, 70));
			btnScarico.setText("Scarico");
			btnScarico.setVisible(true);
			btnScarico.addActionListener(myActionListener);
		}
		return btnScarico;
	}
} // @jve:decl-index=0:visual-constraint="10,10"