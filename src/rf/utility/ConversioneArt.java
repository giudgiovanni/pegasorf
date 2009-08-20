package rf.utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Fornitore;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.CodiceBarreEsistente;
import rf.utility.db.eccezzioni.IDNonValido;

/**
 * 
 */

/**
 * @author hunterbit
 * 
 */
public class ConversioneArt extends JDialog {

	private JButton btnApri = null;
	private JButton btnConverti = null;
	private DBManager dbm;
	private JPanel jContentPane = null;
	private JProgressBar jProgressBar = null;
	private JLabel lblStato = null;
	private JFrame padre;
	private String path;
	private JPanel pnlNord = null;
	private JPanel pnlSud = null;
	private JTextField txtPathClienti = null;

	/**
	 * This is the default constructor
	 */
	public ConversioneArt(JFrame padre) {
		super(padre, true);
		this.padre = padre;
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * @param salvare
	 */
	private void conversione() {

		lblStato.setText("Inizio Conversione");
		// WritableWorkbook write=null;
		Workbook read = null;
		try {
			// write=Workbook.createWorkbook(new
			// File(salvare+"rubricaConvertito.xls"));
			read = Workbook.getWorkbook(new File(this.path));
			Sheet sheet = read.getSheet(0);
			// WritableSheet wSheet=write.createSheet("rubrica", 0);
			int riga = 0;
			int nRighe = sheet.getRows() - 1;

			// inizializzazione barra di stato
			lblStato.setText("riga convertita: 0 di " + nRighe);
			jProgressBar.setVisible(true);
			jProgressBar.setMinimum(0);
			jProgressBar.setMaximum(nRighe);
			for (int i = 0; i < nRighe; i++) {
				int idArticolo = i + 1;
				Cell codBarre = sheet.getCell(0, riga);
				Cell descrizione = sheet.getCell(1, riga);
				Cell idUM = sheet.getCell(2, riga);
				Cell iva = sheet.getCell(3, riga);
				Cell prezzo_ingrosso = sheet.getCell(4, riga);
				Cell prezzo_dettaglio = sheet.getCell(5, riga);
				Cell prezzo_acquisto = sheet.getCell(6, riga);
				Cell codbarreF = sheet.getCell(7, riga);

				Articolo a = new Articolo();

				a.setCaricoIniziale(0);
				a.setCodBarre(codBarre.getContents());
				a.setCodiceUnitaDiMisura(new Integer(idUM.getContents())
						.intValue());
				a.setDescrizione(descrizione.getContents());
				a.setIdArticolo(idArticolo);
				a.setIdFornitore(1);
				a.setIdReparto(1);
				a.setIva(20);
				a.setIdFornitore(Fornitore.getIdFornitoreByCodBarre(codbarreF.getContents()));
				try {
					a.setPrezzoAcquisto(ControlloDati
							.convertPrezzoToDouble(prezzo_acquisto
									.getContents()));
					a.setPrezzoDettaglio(ControlloDati
							.convertPrezzoToDouble(prezzo_dettaglio
									.getContents()));
					a.setPrezzoIngrosso(ControlloDati
							.convertPrezzoToDouble(prezzo_ingrosso
									.getContents()));
					a.insertArticolo();
				} catch (NumberFormatException e1) {

					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IDNonValido e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CodiceBarreEsistente e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CodiceBarreInesistente e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				riga++;
				int r = riga - 1;

				// gestione barra di stato
				lblStato.setText("riga convertita: " + r + " di " + nRighe);
				jProgressBar.setValue(riga);
				jProgressBar.setStringPainted(true);

			}
			read.close();
			lblStato.setText("Conversione completata correttamente");
		} catch (IOException e) {

			e.printStackTrace();
		} catch (BiffException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes btnApri
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnApri() {
		if (btnApri == null) {
			try {
				btnApri = new JButton();
				// btnApri.setIcon(new
				// ImageIcon(getClass().getResource("/image/apri24.png"))); //
				// Generated
				btnApri.setPreferredSize(new Dimension(60, 32)); // Generated
				btnApri.setText("SEL"); // Generated
				btnApri.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apri();
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnApri;
	}

	/**
	 * This method initializes btnConverti
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnConverti() {
		if (btnConverti == null) {
			try {
				btnConverti = new JButton();
				btnConverti.setText("Converti"); // Generated
				btnConverti
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								converti();
							}
						});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnConverti;
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
			jContentPane.add(getPnlSud(), java.awt.BorderLayout.SOUTH); // Generated
			jContentPane.add(getPnlNord(), java.awt.BorderLayout.CENTER); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			try {
				jProgressBar = new JProgressBar();
				jProgressBar.setVisible(false);
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jProgressBar;
	}

	/**
	 * This method initializes pnlNord
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord() {
		if (pnlNord == null) {
			try {
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(java.awt.FlowLayout.LEFT); // Generated
				pnlNord = new JPanel();
				pnlNord.setLayout(flowLayout); // Generated
				pnlNord.setPreferredSize(new java.awt.Dimension(10, 40)); // Generated
				pnlNord.add(getBtnApri(), null); // Generated
				pnlNord.add(getTxtPathClienti(), null); // Generated
				pnlNord.add(getBtnConverti(), null); // Generated
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
				GridLayout gridLayout = new GridLayout();
				gridLayout.setRows(1); // Generated
				gridLayout.setColumns(2); // Generated
				lblStato = new JLabel();
				lblStato.setText(""); // Generated
				lblStato
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Generated
				pnlSud = new JPanel();
				pnlSud.setLayout(gridLayout); // Generated
				pnlSud.setPreferredSize(new java.awt.Dimension(10, 25)); // Generated
				pnlSud.add(lblStato, null); // Generated
				pnlSud.add(getJProgressBar(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlSud;
	}

	/**
	 * This method initializes txtPathClienti
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtPathClienti() {
		if (txtPathClienti == null) {
			try {
				txtPathClienti = new JTextField();
				txtPathClienti
						.setPreferredSize(new java.awt.Dimension(300, 20)); // Generated
				txtPathClienti.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtPathClienti;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(474, 143);
		// this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resource/icona.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Converter Rubrica");
	}

	/**
	 * 
	 */
	protected void apri() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		int scelta = chooser.showOpenDialog(this);
		if (scelta == JFileChooser.APPROVE_OPTION) {
			this.path = chooser.getSelectedFile().getPath();
			txtPathClienti.setText(path);
		} else
			this.path = "";

	}

	/**
	 * 
	 */
	protected void converti() {
		if (this.path == null || this.path.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,
					"Selezionare un file CLIENTI da convertire",
					"Selezionare File", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Thread t = new Thread(new Runnable() {
			public void run() {
				conversione();
			}

		});
		t.start();

	}

} // @jve:decl-index=0:visual-constraint="10,10"
