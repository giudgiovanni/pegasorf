/**
 *
 */
package rf.pegaso.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import rf.myswing.GregorianCalendarFormat;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.UtilityDBManager;
import rf.pegaso.gui.internalframe.ArchivioInternalFrame;
import rf.pegaso.gui.internalframe.ConfigurazioneInternalFrame;
import rf.pegaso.gui.internalframe.PrimaNotaInternalFrame;
import rf.pegaso.gui.internalframe.VenditaInternalFrame;
import rf.utility.gui.UtilGUI;

/**
 * @author Hunter
 *
 */
public class InitialGUI extends JFrame {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnArchivio) {
				apriArchivio();
			} else if (e.getSource() == btnVendita) {
				apriVendita();
			} else if (e.getSource() == btnPrimaNota) {
				apriPrimaNota();
			} else if (e.getSource() == btnConfigurazione) {
				apriConfigurazione();
			}

		}
	}

	private static final long serialVersionUID = 1L;

	private ArchivioInternalFrame archivioInternalFrame = null;
	private ConfigurazioneInternalFrame confInternalFrame = null;
	private VenditaInternalFrame venditaInternalFrame = null;
	private PrimaNotaInternalFrame primanotaInternalFrame=null;

	private JButton btnArchivio = null;

	private JButton btnConfigurazione = null;

	private JButton btnPrimaNota = null;

	private JButton btnVendita = null;

	//private DBManager dbm; // @jve:decl-index=0:

	private JPanel jContentPane = null;

	private DecoratedDesktopPane jDesktopPane = null;

	private JToolBar jJToolBarBar = null;

	private JLabel lblFooter = null;

	private MyActionListener myActionListener;

	private JPanel pnlCentro = null;

	private JPanel pnlNord = null;

	private JPanel pnlSud = null;

	private InitialGUI padre;  //  @jve:decl-index=0:visual-constraint="141,8"

	private JButton jButton = null;

	/**
	 * This is the default constructor
	 */
	public InitialGUI() {
		super();
		initialize();
	}

	/**
	 *
	 */
	private void apriArchivio() {
		if (!archivioInternalFrame.isVisible()) {

			archivioInternalFrame.setVisible(true);
			try {
				archivioInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e1) {

				e1.printStackTrace();
			}
		} else if (!archivioInternalFrame.isFocusOwner()) {
			try {
				archivioInternalFrame.setSelected(true);
				archivioInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 *
	 */
	private void apriConfigurazione() {
		if (!confInternalFrame.isVisible()) {

			confInternalFrame.setVisible(true);
			try {
				confInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e1) {

				e1.printStackTrace();
			}
		} else if (!confInternalFrame.isFocusOwner()) {
			try {
				confInternalFrame.setSelected(true);
				confInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 *
	 */
	private void apriPrimaNota() {
		if (!primanotaInternalFrame.isVisible()) {

			primanotaInternalFrame.setVisible(true);
			try {
				primanotaInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e1) {

				e1.printStackTrace();
			}
		} else if (!primanotaInternalFrame.isFocusOwner()) {
			try {
				primanotaInternalFrame.setSelected(true);
				primanotaInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 *
	 */
	private void apriVendita() {
		if (!venditaInternalFrame.isVisible()) {

			venditaInternalFrame.setVisible(true);
			try {
				venditaInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e1) {

				e1.printStackTrace();
			}
		} else if (!venditaInternalFrame.isFocusOwner()) {
			try {
				venditaInternalFrame.setSelected(true);
				venditaInternalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method initializes archivioInternalFrame
	 *
	 * @return rf.pegaso.gui.ArchivioInternalFrame
	 */
	private ArchivioInternalFrame getArchivioInternalFrame() {
		if (archivioInternalFrame == null) {
			try {
				archivioInternalFrame = new ArchivioInternalFrame(this);
				archivioInternalFrame
						.setBounds(new Rectangle(260, 28, 369, 236)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return archivioInternalFrame;
	}

	/**
	 * This method initializes btnArchivio
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnArchivio() {
		if (btnArchivio == null) {
			try {
				btnArchivio = new JButton();
				btnArchivio.setText("Archivio"); // Generated
				btnArchivio.setSize(new Dimension(100, 30)); // Generated
				btnArchivio.setPreferredSize(new Dimension(100, 30)); // Generated
				btnArchivio.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnArchivio;
	}

	/**
	 * This method initializes btnConfigurazione
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnConfigurazione() {
		if (btnConfigurazione == null) {
			try {
				btnConfigurazione = new JButton();
				btnConfigurazione.setText("Configurazione"); // Generated
				btnConfigurazione.setPreferredSize(new Dimension(100, 30)); // Generated
				btnConfigurazione.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnConfigurazione;
	}

	/**
	 * This method initializes btnPrimaNota
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnPrimaNota() {
		if (btnPrimaNota == null) {
			try {
				btnPrimaNota = new JButton();
				btnPrimaNota.setText("Prima Nota"); // Generated
				btnPrimaNota.setSize(new Dimension(100, 30)); // Generated
				btnPrimaNota.setPreferredSize(new Dimension(100, 30)); // Generated
				btnPrimaNota.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnPrimaNota;
	}

	/**
	 * This method initializes btnVendita
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnVendita() {
		if (btnVendita == null) {
			try {
				btnVendita = new JButton();
				btnVendita.setText("Vendita"); // Generated
				btnVendita.setPreferredSize(new Dimension(100, 30)); // Generated
				btnVendita.addActionListener(myActionListener);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnVendita;
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
			jContentPane.add(getPnlNord(), BorderLayout.NORTH); // Generated
			jContentPane.add(getPnlCentro(), BorderLayout.CENTER); // Generated
			jContentPane.add(getPnlSud(), BorderLayout.SOUTH); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jDesktopPane
	 *
	 * @return javax.swing.JDesktopPane
	 */
	private DecoratedDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			try {
				jDesktopPane = new DecoratedDesktopPane("resource/erreeffe.jpg");
				jDesktopPane.add(getArchivioInternalFrame(), null);
				jDesktopPane.add(getConfigurazioneInternalFrame(),null);
				jDesktopPane.add(getVenditaInternalFrame(), null);
				jDesktopPane.add(getPrimanotaInternalFrame(), null);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jDesktopPane;
	}

	private Component getPrimanotaInternalFrame() {
		if (primanotaInternalFrame == null) {
			try {
				primanotaInternalFrame = new PrimaNotaInternalFrame(this);
				primanotaInternalFrame
						.setBounds(new Rectangle(260, 28, 369, 236)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return primanotaInternalFrame;
	}

	private ConfigurazioneInternalFrame getConfigurazioneInternalFrame() {
		if (confInternalFrame == null) {
			try {
				confInternalFrame = new ConfigurazioneInternalFrame(this);
				confInternalFrame
						.setBounds(new Rectangle(260, 28, 369, 236)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return confInternalFrame;
	}

	private VenditaInternalFrame getVenditaInternalFrame() {
		if (venditaInternalFrame == null) {
			try {
				venditaInternalFrame = new VenditaInternalFrame(this);
				venditaInternalFrame
						.setBounds(new Rectangle(260, 28, 369, 236)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return venditaInternalFrame;
	}

	/**
	 * This method initializes jJToolBarBar
	 *
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			try {
				jJToolBarBar = new JToolBar();
				jJToolBarBar.add(getBtnArchivio()); // Generated
				jJToolBarBar.add(getBtnVendita()); // Generated
				jJToolBarBar.add(getBtnPrimaNota()); // Generated
				jJToolBarBar.add(getBtnConfigurazione());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes pnlCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			try {
				pnlCentro = new JPanel();
				pnlCentro.setLayout(new BorderLayout()); // Generated
				pnlCentro.add(getJDesktopPane(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlCentro;
	}

	/**
	 * This method initializes pnlNord
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord() {
		if (pnlNord == null) {
			try {
				BorderLayout borderLayout = new BorderLayout();
				borderLayout.setHgap(0); // Generated
				pnlNord = new JPanel();
				pnlNord.setLayout(borderLayout); // Generated
				pnlNord.setPreferredSize(new Dimension(0, 60)); // Generated
				pnlNord.add(getJJToolBarBar(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes pnlSud
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud() {
		if (pnlSud == null) {
			try {
				lblFooter = new JLabel();
				lblFooter.setBounds(new Rectangle(3, 3, 616, 14)); // Generated
				lblFooter.setText(""); // Generated
				pnlSud = new JPanel();
				pnlSud.setLayout(null); // Generated
				pnlSud.setPreferredSize(new Dimension(0, 20)); // Generated
				pnlSud.add(lblFooter, null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlSud;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {

		this.padre = this;
		padre.setSize(new Dimension(513, 115));
		System.out
				.println("--> START APPLICAZIONE ---------------------------------------------------------");
		GregorianCalendarFormat inizio = new GregorianCalendarFormat();
		inizio.setTime(new Date());
		System.out.println("--> DATA E ORA INIZIO : "
				+ inizio.printDataAndOra()
				+ " ----------------------------------");

		// Carico le impostazioni
		Properties prop = new Properties();
		String versione = "";
		String footer = "";
		try {
			prop.load(new FileInputStream("configurazioni.properties"));
			versione = prop.getProperty("versione");
			footer = prop.getProperty("footer");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// Inizializziamo tutti gli ascoltatori
		inizializzaAscoltatori();
		this.setSize(643, 115);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Generated
		this.setContentPane(getJContentPane());
		this.setTitle(versione);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int scelta = JOptionPane
						.showConfirmDialog(padre,
								"Sei sicuro di voler chiudere?", "AVVISO",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (scelta == JOptionPane.YES_OPTION) {
					DBManager.getIstanceSingleton().closeDB();
					System.out
							.println("--> FINE APPLICAZIONE ---------------------------------------------------------");
					GregorianCalendarFormat fine = new GregorianCalendarFormat();
					fine.setTime(new Date());
					System.out.println("--> DATA E ORA FINE : "
							+ fine.printDataAndOra()
							+ " ----------------------------------");
					System.out.println("\n\r");
					System.out
							.println("-------------------------------------------------------------------------------------\n\r");

					System.exit(0);
				}
				return;
			}
		});
		this.lblFooter.setText(footer);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		// Centriamo il frame sullo schermo
		UtilGUI.centraFrame(this);

		//effettuiamo alcune modifiche alla base di dati per adattarla
		File f=new File("modificheok4");
		if(!f.exists()){
			//creiamo la tabella per la stampa delle etichette
			DBManager.getIstanceSingleton().executeQuery("CREATE TABLE tmp_etichette_fornitori(idfornitore bigint NOT NULL,nome character varying,via character varying,cap character varying,  citta character varying,  provincia character varying,CONSTRAINT key_fornitori PRIMARY KEY (idfornitore)) WITHOUT OIDS;");
			DBManager.getIstanceSingleton().executeQuery("ALTER TABLE tmp_etichette_fornitori OWNER TO postgres;");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		//effettuiamo il backup del db all'apertura se impostato si
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.OPEN);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}


	}

	/**
	 *
	 */
	private void inizializzaAscoltatori() {
		myActionListener = new MyActionListener();

	}

}  //  @jve:decl-index=0:visual-constraint="21,79"
