/**
 *
 */
package rf.pegaso.gui.gestione;

import it.infolabs.hibernate.Clienti;
import it.infolabs.hibernate.ClientiHome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.jdesktop.swingx.JXTable;


import rf.pegaso.db.model.ArticoloModel;
import rf.pegaso.db.model.ArticoloModelRidotto;
import rf.pegaso.db.model.ClienteModel;
import rf.pegaso.db.model.search.ArticoliSearchCodiceModel;
import rf.pegaso.db.model.search.ArticoliSearchDescModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.gui.print.StartLabelPrint;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

/**
 * @author Hunter
 *
 */
public class StampeEtichette extends JFrame {
	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnEtichetta24) {
				stampaEtichetta(24);
			} else if (e.getSource() == btnChiudi) {
				dispose();
			} else if (e.getSource() == btnEtichetta48)
				stampaEtichetta(48);

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnEtichetta24 = null;

	private DBManager dbm;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JSeparator jSeparator = null;

	private Frame padre;

	private JPanel pnlCentrale = null;

	private JPanel pnlNord = null;

	private JXTable tblClienti = null;

	private JButton btnEtichetta48 = null;

	/**
	 * @param owner
	 */
	public StampeEtichette(Frame padre) {
		this.padre = padre;
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	/**
	 * This method initializes btnChiudi
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnChiudi() {
		if (btnChiudi == null) {
			try {
				btnChiudi = new JButton();
				btnChiudi.setBounds(new Rectangle(328, 8, 81, 41)); // Generated
				btnChiudi.setText("Chiudi");
				btnChiudi.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnStampaEtichetta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampaEtichetta() {
		if (btnEtichetta24 == null) {
			try {
				btnEtichetta24 = new JButton();
				btnEtichetta24.setBounds(new Rectangle(12, 8, 153, 41)); // Generated
				btnEtichetta24
						.setText("<html><div align=\"center\">Stampa Etichetta<br/>(24 etichette 70x36)</html>"); // Generated
				btnEtichetta24.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnEtichetta24;
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
			jContentPane.add(getPnlNord(), BorderLayout.NORTH); // Generated
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			try {
				jScrollPane = new JScrollPane();
				jScrollPane.setViewportView(getTblArticoli()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jSeparator
	 *
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			try {
				jSeparator = new JSeparator();
				jSeparator.setBounds(new Rectangle(0, 0, 0, 0)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jSeparator;
	}

	/**
	 * This method initializes pnlCentrale
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCentrale() {
		if (pnlCentrale == null) {
			try {
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new BorderLayout()); // Generated
				pnlCentrale.add(getJScrollPane(), BorderLayout.CENTER); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
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
			try {
				pnlNord = new JPanel();
				pnlNord.setLayout(null); // Generated
				pnlNord.setPreferredSize(new Dimension(0, 60)); // Generated
				pnlNord.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlNord.add(getJSeparator(), null); // Generated
				pnlNord.add(getBtnChiudi(), null); // Generated
				pnlNord.add(getBtnStampaEtichetta(), null); // Generated
				pnlNord.add(getBtnEtichetta48(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes tblArticoli
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblArticoli() {
		if (tblClienti == null) {
			try {
				ClienteModel modello = new ClienteModel(dbm);
				dbm.addDBStateChange(modello);
				tblClienti = new JXTable();
				// tblArticoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblClienti.setModel(modello);
				tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblClienti.packAll();
				tblClienti.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblClienti.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);

			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblClienti;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.setTitle("Gestione Stampe Etichette");

		this.setContentPane(getJContentPane());
		this.setSize(padre.getWidth(), padre.getHeight() - 50);
		UtilGUI.centraFrame(this);
		this.setExtendedState(MAXIMIZED_BOTH);

	}

	/**
	 * @param string
	 */
	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE",
				JOptionPane.ERROR_MESSAGE);

	}

	private void ricaricaAllArticoli() {
		ArticoloModel modello = null;
		//try {
			modello = new ArticoloModel();
		//} catch (SQLException e1) {
			//messaggioErroreCampo("Errore ricerca di tutte le giacenze");
			//e1.printStackTrace();
		//}
		tblClienti.setModel(modello);
		tblClienti.packAll();
	}

	/**
	 *
	 */
	private void stampaEtichetta(int nEtichette) {
		// Apre la Dialog delegata alla modifica
		// di un reparto
		if (tblClienti.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare una o più righe",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'etichetta?", "AVVISO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION) {
			return;
		}
		// utilizzato per simulare il passaggio per riferimento
		int startPos[] = { 1 };
		int nCopie[] = { 1 };

		// scelta della posizione da cui iniziare a stampare
		if (nEtichette == 24) {
			StartLabelPrint start = new StartLabelPrint(this, 8, 3, startPos,
					nCopie);
			start.setVisible(true);
		} else if (nEtichette == 48) {
			StartLabelPrint start = new StartLabelPrint(this, 12, 4, startPos,
					nCopie);
			start.setVisible(true);
		}

		// impostiamo il cursore per visualizzare all'utente
		// che sta avvenendo una elaborazione
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

		// prendiamo il numero di righe selezionate con i loro id
		int riga[] = tblClienti.getSelectedRows();

		// dopo di che creiamo un altro array
		// della dimensione di riga.length + la pos di partenza
		// che abbiamo selezionato.
		LinkedList<Long> list=new LinkedList<Long>();

		// copiamo tutti i codici degli articoli per ritrovare
		// i vari dati da poi stampare sulle etichette
		// QUINDI PER PRIMA COSA INSERIAMO I CODICI FITTIZI PER LE
		// ETICHETTE IN BIANCO.
		boolean fine = false;
		if (startPos[0] > 1 && !fine) {
			// questo for inserisce le prime etichette vuote
			// facendo partire così la stampa dall'etichetta
			// esatta senza stampare sugli spazi non corretti
			for (int k = 0; k < startPos[0] - 1; k++) {
				//idArticolo[k] = k * -1;
				list.add(new Long(k * -1));
			}
			fine = true;
		}
		// DA QUI INSERIAMO INVECE I CODICI REALI DELLE ETICHETTE DA STAMPARE
		//qui calcoliamo il numero di cicli che dobbiamo fare tenendo ovviamente
		//conto delle etichette vuote e del fatto che servono eventuali copie
		//multiple di ogni etichetta.
		int cicli=((((riga.length * nCopie[0])+ (startPos[0] - 1))-(startPos[0] - 1))/nCopie[0]);
		for (int i = startPos[0] - 1,n=0; n < cicli; i++,n++) {
			long cod = ((Long) tblClienti.getValueAt(riga[i-(startPos[0] - 1)], 0)).intValue();
			for(int k=0;k<nCopie[0];k++){
				list.add(new Long(cod));
			}
		}

		// prepariamo la stampa delle etichette riversando tutti i
		// dati in una tabella temporanea da dove attingere le info
		preparareEtichetteDaStampare(startPos[0], list);

		// stampa dell'etichetta
		try {

			if (nEtichette == 24) {
				JasperPrintManager.printReport(JasperFillManager.fillReport(
						"report/etichette70x36.jasper", null, this.dbm
								.getConnessione()), true);
			} else if (nEtichette == 48) {
				JasperPrintManager.printReport(JasperFillManager.fillReport(
						"report/etichette51x24.jasper", null, this.dbm
								.getConnessione()), true);
			}

			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} catch (JRException e) {
			//
			e.printStackTrace();
		}

	}

	/**
	 * @param idArticolo
	 */
	private void preparareEtichetteDaStampare(int pos, LinkedList<Long> list) {
		// prima cancelliamo eventuali residui di dati rimasti
		dbm.executeQuery("delete from tmp_etichette");

		// ora prepariamo l'inserimento dei nuovi dati da stampare
		String query = "insert into tmp_etichette values(?,?,?,?,?)";
		PreparedStatement pst = dbm.getNewPreparedStatement(query);

		// se la posizione è maggiore di 1 vuol dire che dobbiamo aggiungere
		// dati fittizi per la stampa delle etichette altrimenti passiamo
		// direttamente alle etichette reali inserendole nella tabella
		// temporanea.
		if (pos > 1) {
			for (int i = 0; i < pos - 1; i++) {
				try {

					pst.setInt(1, i);
					pst.setString(2, "");
					pst.setString(3, "");
					pst.setDouble(4, 0.0);
					pst.setString(5, "");
					pst.execute();
				} catch (SQLException e) {
					messaggioErroreCampo("Errore inserimento dati temporanei per etichette");
					e.printStackTrace();
				}
			}
		}
		for (int i = pos; i <= list.size(); i++) {
			Clienti cliente = new Clienti();
			cliente=ClientiHome.getInstance().findById(list.get(i - 1).intValue());
			try {
				pst.setInt(1, i);
				pst.setString(2, cliente.getCognome());
				pst.setString(3, cliente.getNome());
				pst.setDouble(4, new Double(0));
				pst.setString(5, cliente.getCodfisc());
				pst.execute();
			} catch (SQLException e) {
				messaggioErroreCampo("Errore caricamento dati per etichette");
				e.printStackTrace();
			}

		}

	}

	/**
	 * This method initializes btnEtichetta48
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnEtichetta48() {
		if (btnEtichetta48 == null) {
			try {
				btnEtichetta48 = new JButton();
				btnEtichetta48.setBounds(new Rectangle(172, 8, 147, 42)); // Generated
				btnEtichetta48
						.setText("<html><div align=\"center\">Stampa Etichetta<br/>(48 etichette 51x24)</html>"); // Generated
				btnEtichetta48.addActionListener(new MyActionListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnEtichetta48;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
