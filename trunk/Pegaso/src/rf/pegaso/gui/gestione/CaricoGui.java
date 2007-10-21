package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;

import rf.myswing.IDJComboBox;
import rf.myswing.exception.LunghezzeArrayDiverse;
import rf.myswing.util.DoubleEditor;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.UtilityDBManager;
import rf.pegaso.db.exception.CodiceBarreInesistente;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.model.CarichiViewModel;
import rf.pegaso.db.model.CaricoModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Carico;
import rf.pegaso.db.tabelle.Documento;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.pegaso.gui.utility.ModificaQuantitaRiga;
import rf.utility.ControlloDati;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompleteTextComponent;
import rf.utility.gui.text.AutoCompletion;
import rf.utility.gui.text.UpperAutoCompleteDocument;
import rf.utility.gui.text.UpperTextDocument;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

// Referenced classes of package rf.pegaso.gui.gestione:
//            ArticoliAddMod, ArticoliGestione

public class CaricoGui extends JFrame {
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnInserisci)
				inserisci();
			else if (e.getSource() == btnElimina)
				eliminaArticolo();
			else if (e.getSource() == btnChiudi)
				dispose();
			else if (e.getSource() == btnModifica)
				modifica();
			else if (e.getSource() == btnEliminaCarico)
				eliminaCarico();
			else if (e.getSource() == btnAzzera)
				azzeraTuttiCampi();
			else if (e.getSource() == btnStampa)
				stampaTuttiCarichi();
		}

	}

	class MyComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cmbFornitori) {
				// int idfornitore =
				// ComboBoxUtil.estraiCodice((String)cmbFornitori.getSelectedItem());
				int idfornitore = new Integer(cmbFornitori.getIDSelectedItem())
						.intValue();
				caricaArticoliByIdFornitore(idfornitore);
				azzeraTesto();
			}
		}

	}

	public CaricoGui(Frame frame) {
		btnAzzera = null;
		btnChiudi = null;
		btnElimina = null;
		btnEliminaCarico = null;
		btnInserisci = null;
		btnModifica = null;
		carichiView = null;
		caricoModel = null;
		chkInsRapido = null;
		cmbFornitori = null;
		cmbProdotti = null;
		cmbTipoDocumento = null;
		idcarico = 0;
		jContentPane = null;
		jScrollPane = null;
		jScrollPane1 = null;
		jScrollPane2 = null;
		lblCodBarre = null;
		lblDataCarico = null;
		lblDescrizioneProdotto = null;
		lblFornitore = null;
		lblInsRapido = null;
		lblNote = null;
		lblNumDocumento = null;
		lblNumeroCarico = null;
		lblPrezzo = null;
		lblQta = null;
		lblTipoDocumento = null;
		lblUm = null;
		pnlCentrale = null;
		pnlNord = null;
		pnlProdotto = null;
		pnlSud = null;
		pnlViewCarichi = null;
		tblCarico = null;
		tblViewCarichi = null;
		tbp = null;
		txtCodBarre = null;
		dataDocumento = null;
		txtNote = null;
		txtNumDocumento = null;
		txtNumeroCarico = null;
		txtPrezzo = null;
		txtQta = null;
		txtUm = null;
		lblDataDocumento = null;
		btnNewArticolo = null;
		dataCarico = null;
		btnStampa = null;
		btnVisualizzaArt = null;
		pnlNord1 = null;
		pnlCentro = null;
		padre = frame;
		dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	private void salvaFattura() {
		String doc = cmbTipoDocumento.getSelectedItem().toString();
		if (doc.equalsIgnoreCase("")) {
			messaggioCampoMancante("Tipo di documento non selezionato");
			return;
		}
		if (txtNumDocumento.getText().equalsIgnoreCase("")) {
			messaggioCampoMancante("Numero documento non inserito");
			return;
		}
		Carico c = new Carico();
		try {
			c.setIdCarico((new Integer(txtNumeroCarico.getText())).intValue());
			c.setIdFornitore((new Integer(cmbFornitori.getIDSelectedItem())).intValue());
			c.setDataCarico(new Date(dataCarico.getDate().getTime()));
			c.setDataDocumento(new Date(dataDocumento.getDate().getTime()));
			c.setNumDocumento(txtNumDocumento.getText());
			c.setIdDocumento((new Integer(cmbTipoDocumento.getIDSelectedItem())).intValue());
			c.setOraCarico(new Time((new java.util.Date()).getTime()));
			c.setNote(txtNote.getText());
			if (!c.isInsert((new Integer(txtNumeroCarico.getText())).intValue()))
				c.insertCarico();
			else
				try {
					c.updateCarico();
				} catch (IDNonValido e) {
					e.printStackTrace();
				}
		} catch (Exception exception) {
		}
	}

	public void modificaArticolo() {
		if (tblCarico.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare i dati Articolo?", "AVVISO",
				0, 1);
		if (scelta != JOptionPane.YES_OPTION)
			return;

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.UPDATE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		
		int riga = tblCarico.getSelectedRow();
		String codbarre = (String) tblCarico.getValueAt(riga, 0);
		int id = -1;
		try {
			id = Articolo.idByCodiceBarre(codbarre);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", 0);
			e.printStackTrace();
		}
		if (id == -1) {
			JOptionPane.showMessageDialog(this, "Codice a barre non trovato",
					"RICERCA FALLITA", 1);
			return;
		}
		ArticoliAddMod a = new ArticoliAddMod(this, id, 1);
		a.setVisible(true);
		double prezzo = 0.0D;
		try {
			prezzo = Articolo.prezzoAcquistoByID(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", 0);
			e.printStackTrace();
		}
		Carico c = new Carico();
		idcarico = (new Integer(txtNumeroCarico.getText())).intValue();
		try {
			c.caricaDati(idcarico);
			c.updateArticolo(id, c.getQuantitaCaricata(id), prezzo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRORE", 0);
			e.printStackTrace();
		} catch (IDNonValido e) {
			e.printStackTrace();
		} catch (ResultSetVuoto e) {
			e.printStackTrace();
		}
		ricaricaTableCarico(idcarico);
	}

	protected void stampaTuttiCarichi() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'elenco carichi?", "AVVISO", 0,
				3);
		if (scelta != JOptionPane.YES_OPTION)
			return;
		try {
			JasperViewer.viewReport(
					JasperFillManager.fillReport(
							"report/elenco_carichi.jasper", null, dbm
									.getConnessione()), false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	protected void modificaRiga() {
		if (tblCarico.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		int riga = tblCarico.getSelectedRow();
		String codBarre = (String) tblCarico.getValueAt(riga, 0);
		int n = ((Long) tblCarico.getValueAt(riga, 4)).intValue();
		int qta[] = new int[1];
		qta[0] = n;
		ModificaQuantitaRiga mod = new ModificaQuantitaRiga(qta, this);
		mod.setVisible(true);

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.UPDATE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		
		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
			Carico c = new Carico();
			c.caricaDati((new Integer(txtNumeroCarico.getText())).intValue());
			String o = (String) tblCarico.getValueAt(riga, 5);
			Double d = new Double(0.0D);
			try {
				o = ControlloDati.eliminaPunti(o);
				o = ControlloDati.sostituisciVirgola(o);
				d = new Double(o);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			double prezzo = d.doubleValue();
			c.updateArticolo(a.getIdArticolo(), qta[0], prezzo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IDNonValido e) {
			e.printStackTrace();
		}
	}

	public void erroreCaricamentoDatiDB() {
		JOptionPane.showMessageDialog(this, "Errore caricamento dati db",
				"ERRORE", 0);
	}

	private void avvisoCodBarreInesistente() {
		JOptionPane.showMessageDialog(this,
				"Codice barre articolo inesistente", "Codice inesistente", 1);
	}

	private void azzeraCampi() {
		txtCodBarre.setText("");
		txtUm.setText("");
		txtQta.setText("");
		txtPrezzo.setText("");
		cmbProdotti.setSelectedIndex(0);
		cmbFornitori.setSelectedIndex(0);
	}

	private void azzeraTesto() {
		txtCodBarre.setText("");
		txtUm.setText("");
		txtQta.setText("");
		txtPrezzo.setText("");
	}

	private void azzeraTuttiCampi() {
		Carico c = new Carico();
		idcarico = c.getNewID();
		txtNumeroCarico.setText((new Integer(idcarico)).toString());
		dataCarico.setDate(new java.util.Date());
		txtNumDocumento.setText("");
		cmbTipoDocumento.setSelectedIndex(0);
		ricaricaTableCarico(idcarico);
		azzeraCampi();
	}

	private void caricaArticoli(JComboBox cmbProdotti) {
//		Articolo f = new Articolo();
//		String tmpArticoli[] = null;
//		String tmpCodici[] = null;
//		try {
//			cmbProdotti.removeAllItems();
//			cmbProdotti.addItem("");
//			String as[] = (String[]) f.allArticoli();
//			tmpArticoli = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpArticoli[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbProdotti).caricaIDAndOggetti(tmpCodici,
//					tmpArticoli);
//
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento articoli nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}
//		AutoCompletion.enable(cmbProdotti);
		
		Articolo f = new Articolo();
		try {
			String as[] = (String[]) f.allArticoli();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbProdotti).caricaNewValueComboBox(as, true);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		// per abilitare autocompletamento
		AutoCompletion.enable(cmbProdotti);
	}

	private void caricaArticoliByIdFornitore(int idfornitore) {
//		Articolo f = new Articolo();
//		cmbProdotti.removeAllItems();
//		cmbProdotti.addItem("");
//		String tmpArticoli[] = null;
//		String tmpCodici[] = null;
//		try {
//			String as[] = f.allArticoliByFornitore(idfornitore);
//			tmpArticoli = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpArticoli[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbProdotti).caricaIDAndOggetti(tmpCodici,
//					tmpArticoli);
//
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento articoli nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}
		
		Articolo f = new Articolo();
		try {
			String as[] = f.allArticoliByFornitore(idfornitore);
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbProdotti).caricaNewValueComboBox(as, true);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbProdotti);
	}

	private void caricaArticoloByCodBarre() {
		String codBarre = txtCodBarre.getText();
		if (codBarre.equalsIgnoreCase(""))
			return;
		Articolo a = new Articolo();
		try {
			if (a.findByCodBarre(codBarre)) {
				Fornitore f = new Fornitore();
				f.caricaDati(a.getIdFornitore());
				cmbFornitori.setSelectedItem(f.getNome());
				cmbProdotti.setSelectedItem(a.getDescrizione());
				txtUm.setText((new Integer(a.getUm())).toString());
				txtQta.setText((new Integer(1)).toString());
				txtPrezzo.setValue(new Double(a.getPrezzoAcquisto()));
				txtCodBarre.setText(codBarre);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (CodiceBarreInesistente e1) {
			avvisoCodBarreInesistente();
			e1.printStackTrace();
		}
	}

	private void caricaDati(Carico c) {
		txtNumeroCarico.setText((new Integer(c.getIdCarico())).toString());
		dataCarico.setDate(c.getDataCarico());
		txtNumDocumento.setText(c.getNumDocumento());
		dataDocumento.setDate(c.getDataDocumento());
		txtNote.setText(c.getNote());
		Documento d = new Documento();
		Fornitore f = new Fornitore();
		try {
			d.caricaDati(c.getIdDocumento());
			f.caricaDati(c.getIdFornitore());
		} catch (SQLException e) {
			messaggioErroreCampo("Errore caricamento dati db");
			e.printStackTrace();
		}
		
		cmbTipoDocumento.setSelectedItem(d.getTipo());
		cmbFornitori.setSelectedItem(f.getNome());
		ricaricaTableCarico(c.getIdCarico());
		tbp.setSelectedIndex(0);
	}

	private void caricaDatiArticolo() {
		int idarticolo = new Integer(cmbProdotti.getIDSelectedItem());
		Articolo a = new Articolo();
		try {
			a.caricaDati(idarticolo);
			txtCodBarre.setText(a.getCodBarre());
			txtUm.setText((new Integer(a.getUm())).toString());
			txtQta.setText("1");
			txtPrezzo.setValue(new Double(a.getPrezzoAcquisto()));
		} catch (SQLException e1) {
			erroreCaricamentoDatiDB();
			e1.printStackTrace();
		}
	}

	private void caricaDocumenti(JComboBox cmbDocumenti) {
//		Documento f = new Documento();
//		String tmpDocumenti[] = null;
//		String tmpCodici[] = null;
//		try {
//			cmbDocumenti.removeAllItems();
//			cmbDocumenti.addItem("");
//			String as[] = (String[]) f.allDocumenti();
//			tmpDocumenti = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpDocumenti[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbDocumenti).caricaIDAndOggetti(tmpCodici,
//					tmpDocumenti);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento documenti nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}
//		AutoCompletion.enable(cmbDocumenti);
		Documento f = new Documento();
		try {
			
			String as[] = (String[]) f.allDocumenti();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbDocumenti).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento documenti nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbDocumenti);
	}

	private void caricaFornitori(JComboBox cmbFornitori) {
//		Fornitore f = new Fornitore();
//		String tmpFornitori[] = null;
//		String tmpCodici[] = null;
//		try {
//			cmbFornitori.removeAllItems();
//			cmbFornitori.addItem("");
//			String as[] = (String[]) f.allFornitori();
//			tmpFornitori = new String[as.length];
//			tmpCodici = new String[as.length];
//			// carichiamo tutti i dati in due array
//			// da passre al combobox
//			for (int i = 0; i < as.length; i++) {
//				String tmp[] = as[i].split("-",2);
//				tmpFornitori[i] = tmp[1].trim();
//				tmpCodici[i] = tmp[0].trim();
//			}
//			((IDJComboBox) cmbFornitori).caricaIDAndOggetti(tmpCodici,
//					tmpFornitori);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(this,
//					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
//			e.printStackTrace();
//		} catch (LunghezzeArrayDiverse e) {
//			JOptionPane.showMessageDialog(this, "Errore lunghezza array",
//					"ERRORE LUNGHEZZA", 0);
//			e.printStackTrace();
//		}
//		AutoCompletion.enable(cmbFornitori);
		
		Fornitore f = new Fornitore();
		try {
			
			String as[] = (String[]) f.allFornitori();
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbFornitori).caricaNewValueComboBox(as, true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento fornitori nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbFornitori);
	}

	private void controlloAggPrezzo() {
		Articolo a = new Articolo();
		try {
			try {
				a.caricaDatiByCodBarre(txtCodBarre.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Id non valido", "AVVISO", 1);
			e.printStackTrace();
		}
		double pNuovo = 0.0D;
		if (txtPrezzo.getValue() instanceof Long)
			pNuovo = ((Long) txtPrezzo.getValue()).longValue();
		else
			pNuovo = ((Double) txtPrezzo.getValue()).doubleValue();
		if (!prezzoUguale(pNuovo, a.getIdArticolo())) {
			int scelta = JOptionPane.showConfirmDialog(
							this,
							"Vuoi aggiornare il prezzo del prodotto\ncon il prezzo corrente di acquisto?",
							"AVVISO DI AGGIORNAMENTO", 0);
			if (scelta == JOptionPane.YES_OPTION) {
				try {
					a.setPrezzoAcquisto(pNuovo);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Numero malformato",
							"NUMERO ERRATO", 0);
					e.printStackTrace();
				}
				try {
					a.updateArticolo();
				} catch (IDNonValido e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void eliminaArticolo() {
		if (tblCarico.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		if (tblCarico.getSelectedRow() <= -1) {
			int ok = JOptionPane.showConfirmDialog(this,
					"Sei sicuro si voler eliminare?", "AVVISO", 0, 1);
			if (ok !=JOptionPane.YES_OPTION)
				return;
		}
		
		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.DELETE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		
		int riga = tblCarico.getSelectedRow();
		TableColumn col = tblCarico.getColumn("codice_articolo");
		int colonna = col.getModelIndex();
		String codBarre = (String) tblCarico.getValueAt(riga, colonna);
		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IDNonValido e1) {
			e1.printStackTrace();
		}
		Carico c = new Carico();
		c.setIdCarico((new Integer(txtNumeroCarico.getText())).intValue());
		try {
			c.deleteArticolo(a.getIdArticolo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void eliminaCarico() {
		if (tblViewCarichi.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\neliminare il carico selezionato?",
				"AVVISO", 0, 1);
		if (scelta == JOptionPane.NO_OPTION || scelta==JOptionPane.CANCEL_OPTION || scelta==JOptionPane.CLOSED_OPTION)
			return;
		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.DELETE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		
		int riga = tblViewCarichi.getSelectedRow();
		int idcarico = ((Long) tblViewCarichi.getValueAt(riga, 0)).intValue();
		Carico c = new Carico();
		try {
			c.caricaDati(idcarico);
			c.deleteAllArticoliCaricati();
			c.deleteCarico(idcarico);
		} catch (IDNonValido e) {
			JOptionPane.showMessageDialog(this, "Valore idCliente errato",
					"ERRORE", 2);
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE", 2);
			e.printStackTrace();
		}
	}

	private JButton getBtnAzzera() {
		if (btnAzzera == null)
			try {
				btnAzzera = new JButton();
				btnAzzera.setBounds(new Rectangle(540, 40, 89, 29));
				btnAzzera.setText("Azzera");
				btnAzzera.addActionListener(new MyButtonListener());
			} catch (Throwable throwable) {
			}
		return btnAzzera;
	}

	private JButton getBtnChiudi() {
		if (btnChiudi == null)
			try {
				btnChiudi = new JButton();
				btnChiudi.setBounds(new Rectangle(540, 8, 89, 29));
				btnChiudi.setText("Chiudi");
			} catch (Throwable throwable) {
			}
		return btnChiudi;
	}

	private JButton getBtnElimina() {
		if (btnElimina == null)
			try {
				btnElimina = new JButton();
				btnElimina.setBounds(new Rectangle(504, 156, 116, 26));
				btnElimina.setText("Elimina Art.");
				btnElimina.addActionListener(myButtonListener);
			} catch (Throwable throwable) {
			}
		return btnElimina;
	}

	private JButton getBtnEliminaCarico() {
		if (btnEliminaCarico == null)
			try {
				btnEliminaCarico = new JButton();
				btnEliminaCarico.setText("Elimina");
				btnEliminaCarico.addActionListener(new MyButtonListener());
			} catch (Throwable throwable) {
			}
		return btnEliminaCarico;
	}

	private JButton getBtnInserisci() {
		if (btnInserisci == null)
			try {
				btnInserisci = new JButton();
				btnInserisci.setBounds(new Rectangle(384, 124, 116, 26));
				btnInserisci.setText("Inserisci");
			} catch (Throwable throwable) {
			}
		return btnInserisci;
	}

	private JButton getBtnModifica() {
		if (btnModifica == null)
			try {
				btnModifica = new JButton();
				btnModifica.setText("Modifica");
				btnModifica.addActionListener(new MyButtonListener());
			} catch (Throwable throwable) {
			}
		return btnModifica;
	}

	private JCheckBox getChkInsRapido() {
		if (chkInsRapido == null)
			try {
				chkInsRapido = new JCheckBox();
				chkInsRapido.setBounds(new Rectangle(232, 105, 18, 19));
			} catch (Throwable throwable) {
			}
		return chkInsRapido;
	}

	private JComboBox getCmbFornitori() {
		if (cmbFornitori == null)
			try {
				cmbFornitori = new IDJComboBox();
				cmbFornitori.setBounds(new Rectangle(68, 108, 561, 25));
			} catch (Throwable throwable) {
			}
		return cmbFornitori;
	}

	private JComboBox getCmbProdotti() {
		if (cmbProdotti == null)
			try {
				cmbProdotti = new IDJComboBox();
				cmbProdotti.setBounds(new Rectangle(148, 40, 473, 21));
			} catch (Throwable throwable) {
			}
		return cmbProdotti;
	}

	private JComboBox getCmbTipoDocumento() {
		if (cmbTipoDocumento == null)
			try {
				cmbTipoDocumento = new IDJComboBox();
				cmbTipoDocumento.setBounds(new Rectangle(108, 48, 273, 25));
			} catch (Throwable throwable) {
			}
		return cmbTipoDocumento;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTbp(), "Center");
		}
		return jContentPane;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null)
			try {
				jScrollPane = new JScrollPane();
				jScrollPane.setPreferredSize(new Dimension(453, 130));
				jScrollPane.setHorizontalScrollBarPolicy(30);
				jScrollPane.setViewportView(getTblCarico());
			} catch (Throwable throwable) {
			}
		return jScrollPane;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null)
			try {
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setBounds(new Rectangle(8, 128, 371, 56));
				jScrollPane1.setViewportView(getTxtNote());
			} catch (Throwable throwable) {
			}
		return jScrollPane1;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null)
			try {
				jScrollPane2 = new JScrollPane();
				jScrollPane2.setViewportView(getTblViewCarichi());
			} catch (Throwable throwable) {
			}
		return jScrollPane2;
	}

	private JPanel getPnlCentrale() {
		if (pnlCentrale == null)
			try {
				pnlCentrale = new JPanel();
				pnlCentrale.setLayout(new BorderLayout());
				pnlCentrale.add(getPnlNord(), "North");
				pnlCentrale.add(getPnlSud(), "Center");
			} catch (Throwable throwable) {
			}
		return pnlCentrale;
	}

	private JPanel getPnlNord() {
		if (pnlNord == null)
			try {
				lblDataDocumento = new JLabel();
				lblDataDocumento.setBounds(new Rectangle(200, 80, 101, 25));
				lblDataDocumento.setText("Data Documento");
				lblNumDocumento = new JLabel();
				lblNumDocumento.setBounds(new Rectangle(8, 80, 89, 25));
				lblNumDocumento.setText("N. Documento");
				lblFornitore = new JLabel();
				lblFornitore.setBounds(new Rectangle(8, 108, 57, 25));
				lblFornitore.setText("Fornitore");
				lblDataCarico = new JLabel();
				lblDataCarico.setBounds(new Rectangle(145, 20, 69, 25));
				lblDataCarico.setText("Data Carico");
				lblNumeroCarico = new JLabel();
				lblNumeroCarico.setBounds(new Rectangle(9, 20, 57, 25));
				lblNumeroCarico.setText("N\260 Carico");
				pnlNord = new JPanel();
				pnlNord.setLayout(null);
				pnlNord.setPreferredSize(new Dimension(1, 340));
				pnlNord.add(lblNumeroCarico, null);
				pnlNord.add(getTxtNumeroCarico(), null);
				pnlNord.add(lblDataCarico, null);
				pnlNord.add(lblFornitore, null);
				pnlNord.add(getCmbFornitori(), null);
				pnlNord.add(getPnlProdotto(), null);
				pnlNord.add(getBtnChiudi(), null);
				pnlNord.add(lblTipoDocumento, null);
				pnlNord.add(getCmbTipoDocumento(), null);
				pnlNord.add(lblNumDocumento, null);
				pnlNord.add(getTxtNumDocumento(), null);
				pnlNord.add(getBtnAzzera(), null);
				pnlNord.add(getDateChooserDocumento(), null);
				pnlNord.add(lblDataDocumento, null);
				pnlNord.add(getDataCarico(), null);
			} catch (Throwable throwable) {
			}
		return pnlNord;
	}

	private JPanel getPnlProdotto() {
		if (pnlProdotto == null)
			try {
				lblTipoDocumento = new JLabel();
				lblTipoDocumento.setText("Tipo Documento");
				lblTipoDocumento.setBounds(new Rectangle(8, 48, 97, 25));
				lblTipoDocumento.setHorizontalAlignment(2);
				lblInsRapido = new JLabel();
				lblInsRapido.setBounds(new Rectangle(257, 103, 121, 21));
				lblInsRapido.setText("Inserimento Rapido");
				lblNote = new JLabel();
				lblNote.setBounds(new Rectangle(8, 108, 111, 20));
				lblNote.setText("Note");
				lblPrezzo = new JLabel();
				lblPrezzo.setBounds(new Rectangle(124, 65, 74, 16));
				lblPrezzo.setHorizontalAlignment(0);
				lblPrezzo.setText("Prezzo");
				lblQta = new JLabel();
				lblQta.setBounds(new Rectangle(59, 65, 60, 16));
				lblQta.setHorizontalTextPosition(0);
				lblQta.setHorizontalAlignment(0);
				lblQta.setText("qt\340");
				lblUm = new JLabel();
				lblUm.setBounds(new Rectangle(8, 65, 44, 16));
				lblUm.setHorizontalAlignment(0);
				lblUm.setText("U.M.");
				lblDescrizioneProdotto = new JLabel();
				lblDescrizioneProdotto
						.setBounds(new Rectangle(148, 24, 82, 13));
				lblDescrizioneProdotto.setText("Descrizione");
				lblCodBarre = new JLabel();
				lblCodBarre.setBounds(new Rectangle(8, 24, 69, 13));
				lblCodBarre.setText("Codice");
				pnlProdotto = new JPanel();
				pnlProdotto.setLayout(null);
				pnlProdotto.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(0), "Dati prodotto", 0,
						0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
				pnlProdotto.setLocation(new Point(6, 140));
				pnlProdotto.setSize(new Dimension(633, 192));
				pnlProdotto.add(lblCodBarre, null);
				pnlProdotto.add(getTxtCodBarre(), null);
				pnlProdotto.add(lblDescrizioneProdotto, null);
				pnlProdotto.add(getCmbProdotti(), null);
				pnlProdotto.add(getBtnInserisci(), null);
				pnlProdotto.add(getTxtUm(), null);
				pnlProdotto.add(lblUm, null);
				pnlProdotto.add(lblQta, null);
				pnlProdotto.add(getTxtQta(), null);
				pnlProdotto.add(lblPrezzo, null);
				pnlProdotto.add(getTxtPrezzo(), null);
				pnlProdotto.add(getBtnElimina(), null);
				pnlProdotto.add(getJScrollPane1(), null);
				pnlProdotto.add(lblNote, null);
				pnlProdotto.add(getChkInsRapido(), null);
				pnlProdotto.add(lblInsRapido, null);
				pnlProdotto.add(getBtnNewArticolo(), null);
				pnlProdotto.add(getBtnNewArticolo1(), null);
			} catch (Throwable throwable) {
			}
		return pnlProdotto;
	}

	private JPanel getPnlSud() {
		if (pnlSud == null)
			try {
				pnlSud = new JPanel();
				pnlSud.setLayout(new BorderLayout());
				pnlSud.add(getJScrollPane(), "Center");
			} catch (Throwable throwable) {
			}
		return pnlSud;
	}

	private JPanel getPnlViewCarichi() {
		if (pnlViewCarichi == null)
			try {
				pnlViewCarichi = new JPanel();
				pnlViewCarichi.setLayout(new BorderLayout());
				pnlViewCarichi.setBorder(BorderFactory.createBevelBorder(0));
				pnlViewCarichi.add(getPnlNord1(), "North");
				pnlViewCarichi.add(getPnlCentro(), "Center");
			} catch (Throwable throwable) {
			}
		return pnlViewCarichi;
	}

	private JTable getTblCarico() {
		if (tblCarico == null)
			try {
				caricoModel = new CaricoModel(dbm, idcarico);
				dbm.addDBStateChange(caricoModel);
				tblCarico = new JXTable();
				tblCarico.setDefaultEditor(Double.class, new DoubleEditor());
				tblCarico.setSelectionMode(0);
				tblCarico.setModel(caricoModel);
				TableColumn col = tblCarico.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
				tblCarico.setAutoResizeMode(4);
				tblCarico.packAll();
				tblCarico.getTableHeader().setReorderingAllowed(false);
			} catch (Throwable throwable) {
			}
		return tblCarico;
	}

	private JXTable getTblViewCarichi() {
		if (tblViewCarichi == null)
			try {
				carichiView = new CarichiViewModel(dbm);
				dbm.addDBStateChange(carichiView);
				tblViewCarichi = new JXTable();
				tblViewCarichi.setSelectionMode(0);
				tblViewCarichi.setModel(carichiView);
				tblViewCarichi.setAutoResizeMode(4);
				tblViewCarichi.packAll();
				tblViewCarichi.getTableHeader().setReorderingAllowed(false);
			} catch (Throwable throwable) {
			}
		return tblViewCarichi;
	}

	private JTabbedPane getTbp() {
		if (tbp == null)
			try {
				tbp = new JTabbedPane();
				tbp.addTab("Carico merce", null, getPnlCentrale(), null);
				tbp.addTab("Visualizza carichi", null, getPnlViewCarichi(),
						null);
			} catch (Throwable throwable) {
			}
		return tbp;
	}

	private JTextField getTxtCodBarre() {
		if (txtCodBarre == null)
			try {
				txtCodBarre = new JTextField();
				AutoCompleteTextComponent complete = new AutoCompleteTextComponent(
						txtCodBarre, dbm, "articoli", "codbarre");
				dbm.addDBStateChange(complete);
				txtCodBarre.setDocument(new UpperAutoCompleteDocument(complete,
						true));
				txtCodBarre.setBounds(new Rectangle(8, 40, 137, 21));
				txtCodBarre.addFocusListener(new FocusAdapter() {

					public void focusLost(FocusEvent e) {
						caricaArticoloByCodBarre();
					}
				});
				txtCodBarre.addKeyListener(new KeyAdapter() {

					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == 10) {
							caricaArticoloByCodBarre();
							if (chkInsRapido.isSelected())
								inserisci();
						}
					}
				});
			} catch (Throwable throwable) {
			}
		return txtCodBarre;
	}

	private JDateChooser getDateChooserDocumento() {
		if (dataDocumento == null)
			try {
				dataDocumento = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataDocumento.setDate(new java.util.Date());
				dataDocumento.setBounds(new Rectangle(304, 80, 114, 25));
				JTextFieldDateEditor f=(JTextFieldDateEditor) dataDocumento.getDateEditor();
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent e) {
						JTextFieldDateEditor s=(JTextFieldDateEditor) dataDocumento.getDateEditor();
						s.setCaretPosition(0);
					}
				});
				
			} catch (Throwable throwable) {
			}
		return dataDocumento;
	}

	private JTextArea getTxtNote() {
		if (txtNote == null)
			try {
				txtNote = new JTextArea();
				txtNote.setLineWrap(true);
				txtNote.setDocument(new UpperTextDocument());
			} catch (Throwable throwable) {
			}
		return txtNote;
	}

	private JTextField getTxtNumDocumento() {
		if (txtNumDocumento == null)
			try {
				txtNumDocumento = new JTextField();
				txtNumDocumento.setBounds(new Rectangle(96, 80, 101, 25));
			} catch (Throwable throwable) {
			}
		return txtNumDocumento;
	}

	private JTextField getTxtNumeroCarico() {
		if (txtNumeroCarico == null)
			try {
				txtNumeroCarico = new JTextField();
				txtNumeroCarico.setBounds(new Rectangle(69, 20, 65, 25));
				txtNumeroCarico.setEditable(false);
				AutoCompleteTextComponent complete = new AutoCompleteTextComponent(
						txtNumeroCarico, dbm, "carichi", "idcarico");
				dbm.addDBStateChange(complete);
				txtNumeroCarico.setDocument(new UpperAutoCompleteDocument(
						complete, false));
			} catch (Throwable throwable) {
			}
		return txtNumeroCarico;
	}

	private JTextField getTxtPrezzo() {
		if (txtPrezzo == null)
			try {
				DecimalFormat formatPrice = new DecimalFormat();
				formatPrice.setMaximumFractionDigits(2);
				formatPrice.setMinimumFractionDigits(2);
				txtPrezzo = new JFormattedTextField(formatPrice);
				txtPrezzo.setBounds(new Rectangle(124, 82, 76, 20));
			} catch (Throwable throwable) {
			}
		return txtPrezzo;
	}

	private JTextField getTxtQta() {
		if (txtQta == null)
			try {
				txtQta = new JTextField();
				txtQta.setBounds(new Rectangle(59, 82, 60, 20));
				txtQta.addFocusListener(new FocusAdapter() {

					public void focusLost(FocusEvent e) {
						String numero = txtQta.getText();
						if (!ControlloDati.isNumero(numero)) {
							txtQta.selectAll();
							messaggioErroreCampo("Errore campo quantit\340");
						}
					}
				});
			} catch (Throwable throwable) {
			}
		return txtQta;
	}

	private JTextField getTxtUm() {
		if (txtUm == null)
			try {
				txtUm = new JTextField();
				txtUm.setBounds(new Rectangle(8, 82, 44, 20));
				txtUm.setEditable(false);
			} catch (Throwable throwable) {
			}
		return txtUm;
	}

	private void initialize() {
		Carico c = new Carico();
		idcarico = c.getNewID();
		setSize(650, 530);
		setDefaultCloseOperation(0);
		setTitle("Carico Merce");
		setResizable(true);
		setContentPane(getJContentPane());
		addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent e) {
				padre.setEnabled(true);
			}

			public void windowClosing(WindowEvent e) {
				padre.setEnabled(true);
			}
		});
		UtilGUI.centraFrame(this);
		txtNumeroCarico.setText((new Integer(idcarico)).toString());
		dataCarico.setDate(new java.util.Date());
		caricaFornitori(cmbFornitori);
		caricaArticoli(cmbProdotti);
		caricaDocumenti(cmbTipoDocumento);
		inizializzaListeners();
		
		
	}

	private void inizializzaListeners() {
		myComboBoxListener = new MyComboBoxListener();
		myButtonListener = new MyButtonListener();
		//cmbFornitori.addActionListener(myComboBoxListener);
		btnInserisci.addActionListener(myButtonListener);
		btnElimina.addActionListener(myButtonListener);
		btnChiudi.addActionListener(myButtonListener);
	}

	private void inserisci() {
		String doc = cmbTipoDocumento.getSelectedItem().toString();
		if (doc.equalsIgnoreCase("")) {
			messaggioCampoMancante("Tipo di documento non selezionato");
			return;
		}
		if (txtNumDocumento.getText().equalsIgnoreCase("")) {
			messaggioCampoMancante("Numero documento non inserito");
			return;
		}
		String codBarre = txtCodBarre.getText();
		String tmp = txtQta.getText();
		String tmpPrezzo = txtPrezzo.getText();

		// PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
		try {
			UtilityDBManager.getSingleInstance().backupDataBase(UtilityDBManager.INSERT);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "File di configurazione per backup\nmancante o danneggiato", "ERRORE FILE", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		// FINE PUNTO BACKUP
		
		if (codBarre.equals("") && tmp.equals("") && tmpPrezzo.equals("")) {
			salvaFattura();
			return;
		}
		if (codBarre.equalsIgnoreCase("")) {
			messaggioCampoMancante("Codice a barre non presente \nselezionare un prodotto");
			return;
		}
		if (tmp.equalsIgnoreCase("")) {
			messaggioCampoMancante("Inserire la quantit\340");
			return;
		}
		Carico c = new Carico();
		try {
			if (!c
					.isInsert((new Integer(txtNumeroCarico.getText()))
							.intValue())) {
				c.setIdCarico((new Integer(txtNumeroCarico.getText()))
						.intValue());
				c.setIdFornitore((new Integer(cmbFornitori.getIDSelectedItem())).intValue());
				c.setDataCarico(new Date(dataCarico.getDate().getTime()));
				c.setDataDocumento(new Date(dataDocumento.getDate().getTime()));
				c.setNumDocumento(txtNumDocumento.getText());
				c.setIdDocumento((new Integer(cmbTipoDocumento.getIDSelectedItem())).intValue());
				c.setOraCarico(new Time((new java.util.Date()).getTime()));
				c.setNote(txtNote.getText());
				c.insertCarico();
			} else {
				c.setIdCarico((new Integer(txtNumeroCarico.getText()))
						.intValue());
				c.setIdFornitore((new Integer(cmbFornitori.getIDSelectedItem())).intValue());
				c.setDataCarico(new Date(dataCarico.getDate().getTime()));
				c.setDataDocumento(new Date(dataDocumento.getDate().getTime()));
				c.setNumDocumento(txtNumDocumento.getText());
				c.setIdDocumento((new Integer( cmbTipoDocumento.getIDSelectedItem())).intValue());
				c.setOraCarico(new Time((new java.util.Date()).getTime()));
				c.setNote(txtNote.getText());
				try {
					c.updateCarico();
				} catch (IDNonValido e) {
					e.printStackTrace();
				}
			}
			controlloAggPrezzo();
			Articolo a = new Articolo();
			try {
				a.caricaDatiByCodBarre(txtCodBarre.getText());
			} catch (IDNonValido e) {
				e.printStackTrace();
			}
			c.setIdCarico((new Integer(txtNumeroCarico.getText())).intValue());
			if (Carico.codiceBarrePresenteInScarico(txtCodBarre.getText(),
					Integer.parseInt(txtNumeroCarico.getText()))) {
				c.caricaDati((new Integer(txtNumeroCarico.getText()))
						.intValue());
				int qta = 0;
				try {
					qta = c.getQuantitaCaricata(a.getIdArticolo());
				} catch (IDNonValido e) {
					JOptionPane.showMessageDialog(this,
							"Probabile Errore nel codice dell'Articolo",
							"ERRORE", 0);
					e.printStackTrace();
				} catch (ResultSetVuoto e) {
					JOptionPane
							.showMessageDialog(
									this,
									"Il ResultSet probabilmente non \ncontiene informazioni",
									"ERRORE", 0);
					e.printStackTrace();
				}
				double price = 0.0D;
				if (txtPrezzo.getValue() instanceof Double)
					price = ((Double) txtPrezzo.getValue()).doubleValue();
				else
					price = ((Long) txtPrezzo.getValue()).intValue();
				c.updateArticolo(a.getIdArticolo(), qta
						+ Integer.parseInt(txtQta.getText()), price);
			} else {
				double price = 0.0D;
				if (txtPrezzo.getValue() instanceof Double)
					price = ((Double) txtPrezzo.getValue()).doubleValue();
				else
					price = ((Long) txtPrezzo.getValue()).intValue();
				c.insertArticolo(a.getIdArticolo(), (new Integer(txtQta
						.getText())).intValue(), price);
			}
			azzeraCampi();
			tblCarico.packAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Errore nell'inserimento dinumeri", "NUMERO ERRATO", 0);
			e.printStackTrace();
		}
		
		
	}

	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO", 1);
	}

	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE", 0);
	}

	private void modifica() {
		if (tblViewCarichi.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler\nmodificare il carico selezionato?",
				"AVVISO", 0, 1);
		if (scelta !=JOptionPane.YES_OPTION)
			return;
		int riga = tblViewCarichi.getSelectedRow();
		int idcarico = ((Long) tblViewCarichi.getValueAt(riga, 0)).intValue();
		Carico c = new Carico();
		try {
			c.caricaDati(idcarico);
			caricaDati(c);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE", 2);
			e.printStackTrace();
		}
	}

	private boolean prezzoUguale(double pNuovo, int idArticolo) {
		Articolo a = new Articolo();
		try {
			a.caricaDati(idArticolo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double pAttuale = a.getPrezzoAcquisto();
		double prezzoNuovo = pNuovo;
		return pAttuale == prezzoNuovo;
	}

	private void ricaricaTableCarico(int idCarico) {
		caricoModel = null;
		try {
			caricoModel = new CaricoModel(dbm, idCarico);
		} catch (SQLException e) {
			messaggioErroreCampo("Errore caricamento dati dal db");
			e.printStackTrace();
		}
		dbm.addDBStateChange(caricoModel);
		tblCarico.setModel(caricoModel);
		TableColumn col = tblCarico.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
		tblCarico.packAll();
	}

	private JButton getBtnNewArticolo() {
		if (btnNewArticolo == null)
			try {
				btnNewArticolo = new JButton();
				btnNewArticolo.setBounds(new Rectangle(504, 124, 116, 26));
				btnNewArticolo.setText("Nuovo Art.");
				btnNewArticolo.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						newArticolo();
					}
				});
			} catch (Throwable throwable) {
			}
		return btnNewArticolo;
	}

	protected void newArticolo() {
		String codArticolo[] = new String[1];
		ArticoliAddMod add = new ArticoliAddMod(this, 0, codArticolo);
		add.setCloseOnOk(true);
		add.setVisible(true);
		txtCodBarre.setText(codArticolo[0]);
		caricaArticoloByCodBarre();
	}

	private JDateChooser getDataCarico() {
		if (dataCarico == null)
			try {
				dataCarico = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataCarico.setDate(new java.util.Date());
				dataCarico.setBounds(new Rectangle(216, 20, 112, 25));
				JTextFieldDateEditor f=(JTextFieldDateEditor) dataCarico.getDateEditor();
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent e) {
						JTextFieldDateEditor s=(JTextFieldDateEditor) dataCarico.getDateEditor();
						s.setCaretPosition(0);
					}
				});
				
			} catch (Throwable throwable) {
			}
		return dataCarico;
	}

	private JButton getBtnStampa() {
		if (btnStampa == null)
			try {
				btnStampa = new JButton();
				btnStampa.setText("Stampa");
				btnStampa.addActionListener(new MyButtonListener());
			} catch (Throwable throwable) {
			}
		return btnStampa;
	}

	private JButton getBtnNewArticolo1() {
		if (btnVisualizzaArt == null)
			try {
				btnVisualizzaArt = new JButton();
				btnVisualizzaArt.setBounds(new Rectangle(384, 156, 116, 26));
				btnVisualizzaArt.setText("Elenco Art.");
				btnVisualizzaArt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						visualizzaArticoli();
					}
				});
			} catch (Throwable throwable) {
			}
		return btnVisualizzaArt;
	}

	protected void visualizzaArticoli() {
		ArticoliGestione g = new ArticoliGestione();
		g.setVisible(true);
	}

	private JPanel getPnlNord1() {
		if (pnlNord1 == null)
			try {
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(0);
				pnlNord1 = new JPanel();
				pnlNord1.setLayout(flowLayout);
				pnlNord1.add(getBtnModifica(), null);
				pnlNord1.add(getBtnStampa(), null);
				pnlNord1.add(getBtnEliminaCarico(), null);
			} catch (Throwable throwable) {
			}
		return pnlNord1;
	}

	private JPanel getPnlCentro() {
		if (pnlCentro == null)
			try {
				pnlCentro = new JPanel();
				pnlCentro.setLayout(new BorderLayout());
				pnlCentro.add(getJScrollPane2(), "Center");
			} catch (Throwable throwable) {
			}
		return pnlCentro;
	}

	private static final long serialVersionUID = 1L;
	private JButton btnAzzera;
	private JButton btnChiudi;
	private JButton btnElimina;
	private JButton btnEliminaCarico;
	private JButton btnInserisci;
	private JButton btnModifica;
	private CarichiViewModel carichiView;
	private CaricoModel caricoModel;
	private JCheckBox chkInsRapido;
	private IDJComboBox cmbFornitori;
	private IDJComboBox cmbProdotti;
	private IDJComboBox cmbTipoDocumento;
	private DBManager dbm;
	private int idcarico;
	private JPanel jContentPane;
	private JScrollPane jScrollPane;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JLabel lblCodBarre;
	private JLabel lblDataCarico;
	private JLabel lblDescrizioneProdotto;
	private JLabel lblFornitore;
	private JLabel lblInsRapido;
	private JLabel lblNote;
	private JLabel lblNumDocumento;
	private JLabel lblNumeroCarico;
	private JLabel lblPrezzo;
	private JLabel lblQta;
	private JLabel lblTipoDocumento;
	private JLabel lblUm;
	private MyButtonListener myButtonListener;
	private MyComboBoxListener myComboBoxListener;
	private JPanel pnlCentrale;
	private JPanel pnlNord;
	private JPanel pnlProdotto;
	private JPanel pnlSud;
	private JPanel pnlViewCarichi;
	private JXTable tblCarico;
	private JXTable tblViewCarichi;
	private JTabbedPane tbp;
	private JTextField txtCodBarre;
	private JDateChooser dataDocumento;
	private JTextArea txtNote;
	private JTextField txtNumDocumento;
	private JTextField txtNumeroCarico;
	private JFormattedTextField txtPrezzo;
	private JTextField txtQta;
	private JTextField txtUm;
	private JLabel lblDataDocumento;
	private JButton btnNewArticolo;
	private JDateChooser dataCarico;
	private JButton btnStampa;
	private Frame padre;
	private JButton btnVisualizzaArt;
	private JPanel pnlNord1;
	private JPanel pnlCentro;

}