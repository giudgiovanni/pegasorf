package rf.pegaso.gui.gestione;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Carico;
import rf.utility.Constant;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.db.UtilityDBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTextField;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import javax.swing.JLabel;

public class CaricaAggiornaTabacchiGui extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlBottoni = null;

	private JButton btnSalva = null;

	private JButton btnChiudi = null;

	private JTextField txtFldPercorso = null;

	private JButton btnApri = null;

	private String path = "";

	private JLabel lblPercorso = null;


	public CaricaAggiornaTabacchiGui(Frame owner) {
		super(owner,true);

		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 250);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Aggiornamento Listino Tabacchi");
		this.setContentPane(getJContentPane());

		UtilGUI.centraDialog(this);
	}

	protected void apri() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		int scelta = chooser.showOpenDialog(this);
		if (scelta == JFileChooser.APPROVE_OPTION) {
			this.path = chooser.getSelectedFile().getPath();
			txtFldPercorso.setText(path);
		} else
			this.path = "";
	}

	protected void salva(){
		if ( path.equals("") ){
			JOptionPane.showMessageDialog(this, "Inserire il percorso del file da caricare.", "WARN",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else if ( !path.contains(".xls") ){
			JOptionPane.showMessageDialog(this, "Il documento selezionato non e' di tipo XLS.", "WARN",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			try {
				//apro il file
				Workbook workbook = Workbook.getWorkbook(new File(path));
				//prendo il primo foglio
				Sheet sheet = workbook.getSheet(0);
				int riga = 4;
				boolean flag = true;
				while ( flag ){
					Articolo a = new Articolo();
					a.setDescrizione((sheet.getCell(2, riga).getContents()).replaceAll("'", ""));
					a.setCodFornitore(sheet.getCell(1, riga).getContents());
					// Verifichiamo se il prodotto e' gia' presente e nel caso lo aggiorniamo
					if ( a.tabacchiEsistente() ){
						if ( Double.compare(a.getPrezzoDettaglio(), ControlloDati.convertPrezzoToDouble(sheet.getCell(4, riga).getContents())) != 0
								|| !a.getNote().equals(sheet.getCell(5, riga).getContents()) ) {
							a.setPrezzoDettaglio(ControlloDati.convertPrezzoToDouble(sheet.getCell(4, riga).getContents()));
							a.setPrezzoIngrosso(ControlloDati.convertPrezzoToDouble(sheet.getCell(4, riga).getContents()));
							a.setNote(sheet.getCell(5, riga).getContents());
							a.updateArticolo();
						}
					}
					// Altrimenti lo inseriamo come nuovo
					else{
						a.setIdArticolo(DBManager.getIstanceSingleton().getNewID("articoli", "idArticolo"));
						a.setIdFornitore(Constant.getFornitoreTabacchi());
						a.setIdReparto(Constant.getRepartoTabacchi());
						a.setUm(Constant.getUnitaMisuraPezzi());
						a.setDataInserimento(new java.sql.Date(new Date().getTime()));
						a.setIva(0);
						
						a.setPrezzoDettaglio(ControlloDati.convertPrezzoToDouble(sheet.getCell(4, riga).getContents()));
						a.setPrezzoIngrosso(ControlloDati.convertPrezzoToDouble(sheet.getCell(4, riga).getContents()));
						a.setNote(sheet.getCell(5, riga).getContents());

						System.out.println("Settare il codice a barre, codice fornitore");

						a.insertArticolo();
					}
					try{
						sheet.getCell(0, riga+1);
					}
					catch (Exception e) {
						flag = false;
					}
					riga ++;
				}
				creaCaricoIniziale();
				JOptionPane.showMessageDialog(this, "Elaborazione effettuata con successo.", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (BiffException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ParseException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IDNonValido e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Si e' verificato un errore durante la lettura del file xls.", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void creaCaricoIniziale() throws Exception, IOException{

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(
					UtilityDBManager.INSERT);
		} catch (FileNotFoundException e1) {
			JOptionPane
			.showMessageDialog(
					this,
					"File di configurazione per backup\nmancante o danneggiato",
					"ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane
			.showMessageDialog(
					this,
					"File di configurazione per backup\nmancante o danneggiato",
					"ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP

		Carico c = new Carico();

		//			c.setIdCarico(c.getNewID());
		c.setIdFornitore(Constant.FORNITORE_TABACCHI);
		java.sql.Date data = new java.sql.Date(new Date().getTime()); 
		c.setDataCarico(data);
		c.setDataDocumento(data);
		// c.setNumDocumento(txtNumDocumento.getText());
		c.setIdDocumento(Constant.ORDINE);
		c.setOraCarico(new Time((new java.util.Date()).getTime()));
		c.setSconto(0);
		c.setNote("Aggiornamento listino prezzi.");
		c.setSospeso(0);
		c.insertCarico();
		Properties props = new Properties();
		// Leggiamo le configurazioni
		props.load(new FileReader("carico.properties"));
		// Salviamo il nuovo id del carico iniziale
		props.setProperty("idcarico", String.valueOf(c.getIdCarico()));
		props.store(new PrintWriter(new File("carico.properties")), "id carico iniziale");
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
			jContentPane.add(getPnlBottoni(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlBottoni
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBottoni() {
		if (pnlBottoni == null) {
			lblPercorso = new JLabel();
			lblPercorso.setBounds(new Rectangle(50, 20, 150, 16));
			lblPercorso.setText("Percorso File xls");
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlBottoni = new JPanel();
			pnlBottoni.setLayout(null);
			pnlBottoni.setPreferredSize(new Dimension(0, 40));
			pnlBottoni.add(getBtnSalva(), null);
			pnlBottoni.add(getBtnChiudi(), null);
			pnlBottoni.add(getTxtFldPercorso(), null);
			pnlBottoni.add(getBtnApri(), null);
			pnlBottoni.add(lblPercorso, null);
		}
		return pnlBottoni;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Elabora Dati");
			btnSalva.setBounds(new Rectangle(100, 140, 120, 29));
			btnSalva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					salva();
				}
			});
		}
		return btnSalva;
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
			btnChiudi.setBounds(new Rectangle(281, 140, 90, 29));
			btnChiudi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return btnChiudi;
	}

	/**
	 * This method initializes txtFldPercorso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldPercorso() {
		if (txtFldPercorso == null) {
			txtFldPercorso = new JTextField();
			txtFldPercorso.setBounds(new Rectangle(50, 45, 250, 30));
			txtFldPercorso.setEditable(false);
		}
		return txtFldPercorso;
	}

	/**
	 * This method initializes btnApri	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnApri() {
		if (btnApri == null) {
			btnApri = new JButton();
			btnApri.setBounds(new Rectangle(330, 45, 120, 30));
			btnApri.setText("Apri File xls");
			btnApri.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apri();
				}
			});
		}
		return btnApri;
	}
}
