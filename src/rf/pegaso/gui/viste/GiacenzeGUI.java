// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 25/06/2008 1.28.46
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   GiacenzeGUI.java

package rf.pegaso.gui.viste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import rf.pegaso.db.model.GiacenzeModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Giacenze;
import rf.pegaso.db.tabelle.Scarico;
import rf.utility.ControlloDati;
import rf.utility.date.DateManager;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class GiacenzeGUI extends JFrame {

	private class MyPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getSource() == dataInventario) {
					txtCodBarre.setText("");
					txtDescrizione.setText("");
					ricaricaAllGiacenze();
					calcolaTotali();
				}


		}

	}



	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi)
				dispose();
			else if (e.getSource() == btnStampaInventario)
				stampaInventario();
		}

	}
	private TableRowSorter<GiacenzeModel> sorter;

	public GiacenzeGUI(Frame frame) {
		btnChiudi = null;
		btnStampaInventario = null;
		idcarico = 0;
		jContentPane = null;
		jScrollPane = null;
		lblCodBarre = null;
		lblDescrizioneProdotto = null;
		lblImponibile = null;
		lblImposta = null;
		lblTotale = null;
		lblValoreDeposito = null;
		pnlNord = null;
		pnlProdotto = null;
		pnlSud = null;
		tblGiacenze = null;
		txtCodBarre = null;
		txtDescrizione = null;
		txtImponibile = null;
		txtImposta = null;
		txtTotale = null;
		dataInventario = null;
		dbm = DBManager.getIstanceSingleton();
		initialize();
	}

	public void azzeraCampi() {
		txtCodBarre.setText("");
		txtDescrizione.setText("");
	}

	public void erroreCaricamentoDatiDB() {
		JOptionPane.showMessageDialog(this, "Errore caricamento dati db",
				"ERRORE", 0);
	}

	private void avvisoCodBarreInesistente() {
		JOptionPane.showMessageDialog(this,
				"Codice barre articolo inesistente", "Codice inesistente", 1);
	}

	private void calcolaTotali() {
		double totImponibile = 0.0D;
		double totImposta = 0.0D;
		double tot = totImponibile + totImposta;
		try {
			totImponibile = Giacenze.getTotImponibileDaData(new java.sql.Date(dataInventario.getDate().getTime()));
			txtImponibile.setText(ControlloDati
					.convertDoubleToPrezzo(totImponibile));
		} catch (SQLException e) {
			messaggioErroreCampo("Errore nel calcolo dell'imponibile");
			e.printStackTrace();
		}
		try {
			totImposta = Giacenze.getTotImpostaDaData(new Date(dataInventario.getDate().getTime()));
			txtImposta.setText(ControlloDati.convertDoubleToPrezzo(totImposta));
		} catch (SQLException e) {
			messaggioErroreCampo("Errore calcolo totale imposta");
			e.printStackTrace();
		}
		tot = totImponibile + totImposta;
		txtTotale.setText(ControlloDati.convertDoubleToPrezzo(tot));
		txtTotaleVendita.setText(ControlloDati.convertDoubleToPrezzo(tot*1.1));
	}

	private void caricaArticoli(JComboBox cmbProdotti) {
		Articolo f = new Articolo();
		try {
			cmbProdotti.removeAllItems();
			cmbProdotti.addItem("");
			String as[] = (String[]) f.allArticoli();
			int i = 0;
			for (int j = as.length; i < j; i++) {
				String fornitore = as[i];
				cmbProdotti.addItem(fornitore);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE", 0);
			e.printStackTrace();
		}
	}

	private void caricaClienti(JComboBox cmbFornitori) {
		cmbFornitori.removeAll();
		cmbFornitori.addItem("0 - BANCO");
	}

	private void elimina() {
		if (tblGiacenze.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", 1);
			return;
		}
		int riga = tblGiacenze.getSelectedRow();
		String codBarre = (String) tblGiacenze.getValueAt(riga, 0);
		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IDNonValido e1) {
			e1.printStackTrace();
		}
		Scarico c = new Scarico();
		c.setIdScarico(c.getNewID() - 1);
		try {
			c.deleteArticolo(a.getIdArticolo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private JButton getBtnChiudi() {
		if (btnChiudi == null)
			try {
				btnChiudi = new JButton();
				btnChiudi.setText("Chiudi");
				btnChiudi.setBounds(new Rectangle(520, 12, 109, 40));
			} catch (Throwable throwable) {
			}
		return btnChiudi;
	}

	private JButton getBtnStampaInventario() {
		if (btnStampaInventario == null)
			try {
				btnStampaInventario = new JButton();
				btnStampaInventario.setBounds(new Rectangle(632, 12, 113, 41));
				btnStampaInventario
						.setText("<html><div align=\"center\">Stampa<br/>Inventario</html>");
				btnStampaInventario.addActionListener(new MyButtonListener());
			} catch (Throwable throwable) {
			}
		return btnStampaInventario;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPnlNord(), "North");
			jContentPane.add(getJScrollPane(), "Center");
			jContentPane.add(getPnlSud(), "South");
		}
		return jContentPane;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null)
			try {
				jScrollPane = new JScrollPane();
				jScrollPane.setPreferredSize(new Dimension(453, 350));
				jScrollPane.setViewportView(getTblGiacenze());
			} catch (Throwable throwable) {
			}
		return jScrollPane;
	}

	private JPanel getPnlNord() {
		if (pnlNord == null)
			try {
				pnlNord = new JPanel();
				pnlNord.setLayout(null);
				pnlNord.setPreferredSize(new Dimension(1, 110));
				pnlNord.add(getPnlProdotto(), null);
			} catch (Throwable throwable) {
			}
		return pnlNord;
	}

	private JPanel getPnlProdotto() {
		if (pnlProdotto == null)
			try {
				lblDescrizioneProdotto = new JLabel();
				lblDescrizioneProdotto
						.setBounds(new Rectangle(148, 24, 82, 13));
				lblDescrizioneProdotto.setText("Descrizione");
				lblCodBarre = new JLabel();
				lblCodBarre.setBounds(new Rectangle(8, 24, 98, 13));
				lblCodBarre.setText("Codice prodotto");
				pnlProdotto = new JPanel();
				pnlProdotto.setLayout(null);
				pnlProdotto
						.setBorder(BorderFactory.createTitledBorder(
								BorderFactory.createBevelBorder(0),
								"Filtro prodotti", 0, 0, new Font("Dialog", 1,
										12), new Color(51, 51, 51)));
				pnlProdotto.setLocation(new Point(8, 8));
				pnlProdotto.setSize(new Dimension(878, 92));
				pnlProdotto.add(lblCodBarre, null);
				pnlProdotto.add(getTxtCodBarre(), null);
				pnlProdotto.add(lblDescrizioneProdotto, null);
				pnlProdotto.add(getTxtDescrizione(), null);
				pnlProdotto.add(getBtnChiudi(), null);
				pnlProdotto.add(getBtnStampaInventario(), null);
				pnlProdotto.add(getDataInventario(), null);
				pnlProdotto.add(getBtnStampaInventarioFornitore(), null);
			} catch (Throwable throwable) {
			}
		return pnlProdotto;
	}

	private JPanel getPnlSud() {
		if (pnlSud == null)
			try {
				lblTotaleVendita = new JLabel();
				lblTotaleVendita.setBounds(new Rectangle(642, 10, 101, 25));
				lblTotaleVendita.setText("Totale Vendita €.");
				lblTotale = new JLabel();
				lblTotale.setBounds(new Rectangle(484, 10, 57, 25));
				lblTotale.setText("Totale \u20AC.");
				lblImposta = new JLabel();
				lblImposta.setBounds(new Rectangle(312, 10, 67, 25));
				lblImposta.setText("Imposta \u20AC.");
				lblImponibile = new JLabel();
				lblImponibile.setBounds(new Rectangle(128, 10, 77, 25));
				lblImponibile.setText("Imponibile \u20AC.");
				lblValoreDeposito = new JLabel();
				lblValoreDeposito.setBounds(new Rectangle(11, 10, 114, 25));
				lblValoreDeposito.setFont(new Font("Dialog", 1, 12));
				lblValoreDeposito.setText("Valore in deposito :");
				pnlSud = new JPanel();
				pnlSud.setLayout(null);
				pnlSud.setPreferredSize(new Dimension(0, 50));
				pnlSud.setBorder(BorderFactory.createBevelBorder(0));
				pnlSud.add(lblValoreDeposito, null);
				pnlSud.add(lblImponibile, null);
				pnlSud.add(getTxtImponibile(), null);
				pnlSud.add(lblImposta, null);
				pnlSud.add(lblTotale, null);
				pnlSud.add(getTxtImposta(), null);
				pnlSud.add(getTxtTotale(), null);
				pnlSud.add(lblTotaleVendita, null);
				pnlSud.add(getTxtTotaleVendita(), null);
				double totImponibile = Giacenze.getTotImponibile();
				double totImposta = (totImponibile / 100D) * 20D;
				double totale = totImponibile + totImposta;
				txtImponibile.setText(ControlloDati
						.convertDoubleToPrezzo(totImponibile));
				txtImposta.setText(ControlloDati
						.convertDoubleToPrezzo(totImposta));
				txtTotale.setText(ControlloDati.convertDoubleToPrezzo(totale));
			} catch (Throwable throwable) {
			}
		return pnlSud;
	}

	private JTable getTblGiacenze() {
		if (tblGiacenze == null)
			try {
				java.sql.Date data = new java.sql.Date(dataInventario.getDate()
						.getTime());
				GiacenzeModel modello = new GiacenzeModel(data);
				dbm.addDBStateChange(modello);
				tblGiacenze = new JTable(modello);
				sorter = new TableRowSorter<GiacenzeModel>(modello);
				tblGiacenze.setRowSorter(sorter);
				tblGiacenze.setSelectionMode(0);
				tblGiacenze.setAutoResizeMode(4);
				tblGiacenze.getTableHeader().setReorderingAllowed(false);

				TableColumn col = tblGiacenze.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);

				col = tblGiacenze.getColumn("um");
				col.setMinWidth(50);
				col.setMaxWidth(50);
				col.setPreferredWidth(50);
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
                col.setCellRenderer(renderer);

				col = tblGiacenze.getColumn("carico");
				col.setMinWidth(50);
				col.setMaxWidth(50);
				col.setPreferredWidth(50);
				renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
                col.setCellRenderer(renderer);

				col = tblGiacenze.getColumn("scarico");
				col.setMinWidth(50);
				col.setMaxWidth(50);
				col.setPreferredWidth(50);
				renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
                col.setCellRenderer(renderer);

				col = tblGiacenze.getColumn("deposito");
				col.setMinWidth(50);
				col.setMaxWidth(50);
				col.setPreferredWidth(50);
				renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(JLabel.CENTER);
                col.setCellRenderer(renderer);

				col = tblGiacenze.getColumn("prezzo_acquisto");
				col.setMinWidth(100);
				col.setMaxWidth(100);
				col.setPreferredWidth(100);
                DefaultTableCellRenderer price = new DefaultTableCellRenderer();
                price.setHorizontalAlignment(JLabel.RIGHT);
                col.setCellRenderer(price);
                col.setPreferredWidth(100);
                col.setMaxWidth(100);
                
                

				col = tblGiacenze.getColumn("prezzo_tot");
				col.setMinWidth(100);
				col.setMaxWidth(100);
				col.setPreferredWidth(100);
				price = new DefaultTableCellRenderer();
                price.setHorizontalAlignment(JLabel.RIGHT);
                col.setCellRenderer(price);

				col = tblGiacenze.getColumn("codice");
				col.setMinWidth(150);
				col.setMaxWidth(150);
				col.setPreferredWidth(150);







			} catch (Throwable e) {
				e.printStackTrace();
			}
		return tblGiacenze;
	}

	private JTextField getTxtCodBarre() {
		if (txtCodBarre == null)
			try {
				txtCodBarre = new JTextField();
				txtCodBarre.setBounds(new Rectangle(8, 40, 137, 21));
				txtCodBarre.setDocument(new UpperTextDocument());
				txtCodBarre.getDocument().addDocumentListener(
						new DocumentListener() {
							public void changedUpdate(DocumentEvent e) {
								newFilter();
							}

							public void insertUpdate(DocumentEvent e) {
								newFilter();
							}

							public void removeUpdate(DocumentEvent e) {
								newFilter();
							}
						});




//				txtCodBarre.addKeyListener(new KeyAdapter() {
//
//					public void keyReleased(KeyEvent e) {
//						try {
//							GiacenzeSearchCodiceModel g = new GiacenzeSearchCodiceModel(
//									dbm, txtCodBarre.getText());
//							tblGiacenze.setModel(g);
//							tblGiacenze.packAll();
//						} catch (SQLException e1) {
//							messaggioErroreCampo("Errore nella ricerca del codice");
//							e1.printStackTrace();
//						}
//					}
//
//				});
//				txtCodBarre.addFocusListener(new FocusAdapter() {
//
//					public void focusLost(FocusEvent e) {
//						txtCodBarre.setText("");
//						ricaricaAllGiacenze();
//					}
//
//				});
			} catch (Throwable throwable) {
			}
		return txtCodBarre;
	}


	private void newFilter() {
		RowFilter<GiacenzeModel, Object> rf = null;
		RowFilter<GiacenzeModel, Object> rf1 = null;
		RowFilter<GiacenzeModel, Object> rf2 = null;

		// If current expression doesn't parse, don't update.
		try {
			rf1 = RowFilter.regexFilter(txtDescrizione.getText(), 2);
			rf2 = RowFilter.regexFilter(txtCodBarre.getText(), 1);// la X
																		// indica
																		// la
																		// col.
																		// Autore
																		// nella
																		// tabella
			ArrayList<RowFilter<GiacenzeModel, Object>> filters = new ArrayList<RowFilter<GiacenzeModel, Object>>(
					2);
			filters.add(rf1);
			filters.add(rf2);
			rf = RowFilter.andFilter(filters);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	private JTextField getTxtDescrizione() {
		if (txtDescrizione == null)
			try {
				txtDescrizione = new JTextField();
				txtDescrizione.setBounds(new Rectangle(148, 40, 366, 21));
				txtDescrizione.setDocument(new UpperTextDocument());
				txtDescrizione.getDocument().addDocumentListener(
						new DocumentListener() {
							public void changedUpdate(DocumentEvent e) {
								newFilter();
							}

							public void insertUpdate(DocumentEvent e) {
								newFilter();
							}

							public void removeUpdate(DocumentEvent e) {
								newFilter();
							}
						});
			} catch (Throwable throwable) {
			}
		return txtDescrizione;
	}

	private JTextField getTxtImponibile() {
		if (txtImponibile == null)
			try {
				txtImponibile = new JTextField();
				txtImponibile.setBounds(new Rectangle(204, 10, 93, 25));
				txtImponibile.setEnabled(false);
				txtImponibile.setFont(new Font("Dialog", 1, 12));
				txtImponibile.setDisabledTextColor(Color.black);
				txtImponibile.setEditable(false);
			} catch (Throwable throwable) {
			}
		return txtImponibile;
	}

	private JTextField getTxtImposta() {
		if (txtImposta == null)
			try {
				txtImposta = new JTextField();
				txtImposta.setBounds(new Rectangle(380, 10, 93, 25));
				txtImposta.setEnabled(false);
				txtImposta.setFont(new Font("Dialog", 1, 12));
				txtImposta.setDisabledTextColor(Color.black);
				txtImposta.setEditable(false);
			} catch (Throwable throwable) {
			}
		return txtImposta;
	}

	private JTextField getTxtTotale() {
		if (txtTotale == null)
			try {
				txtTotale = new JTextField();
				txtTotale.setBounds(new Rectangle(540, 10, 93, 25));
				txtTotale.setEnabled(false);
				txtTotale.setFont(new Font("Dialog", 1, 12));
				txtTotale.setDisabledTextColor(Color.red);
				txtTotale.setEditable(false);
			} catch (Throwable throwable) {
			}
		return txtTotale;
	}

	private void initialize() {
		Scarico c = new Scarico();
		idcarico = c.getNewID();
		setSize(900, 550);
		setExtendedState(6);
		setTitle("Inventario Merce");
		setResizable(true);
		setContentPane(getJContentPane());
		UtilGUI.centraFrame(this);
		inizializzaListeners();
		calcolaTotali();
	}

	private void inizializzaListeners() {
		myButtonListener = new MyButtonListener();
		btnChiudi.addActionListener(myButtonListener);
	}

	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO", 1);
	}

	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE", 0);
	}

	private void ricaricaAllGiacenze() {
		GiacenzeModel modello = null;
		try {
			java.sql.Date data = new java.sql.Date(dataInventario.getDate()
					.getTime());
			modello=(GiacenzeModel)tblGiacenze.getModel();
			modello.setDataGiacenza(data);
		} catch (SQLException e1) {
			messaggioErroreCampo("Errore ricerca di tutte le giacenze");
			e1.printStackTrace();
		}

	}

	private void stampaInventario() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'inventario?", "AVVISO", 0, 3);
		if (scelta != 0)
			return;
		Map par = new HashMap();

		par.put("data_inventario", DateManager.convertDateToString("dd/MM/yyyy", dataInventario.getDate()));


		try {
			JasperViewer.viewReport(JasperFillManager.fillReport(
					"report/giacenzemagazzino.jasper", par, dbm
							.getConnessione()), false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
	private void stampaInventarioFornitore() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'inventario?", "AVVISO", 0, 3);
		if (scelta != 0)
			return;
		Map par = new HashMap();

		par.put("data_inventario", DateManager.convertDateToString("dd/MM/yyyy", dataInventario.getDate()));


		try {
			JasperViewer.viewReport(JasperFillManager.fillReport(
					"report/giacenzemagazzino_con_fornitori.jasper", par, dbm
							.getConnessione()), false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JDateChooser getDataInventario() {
		if (dataInventario == null)
			try {
				dataInventario = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataInventario.setDate(new java.util.Date());
				dataInventario.setBounds(new Rectangle(632, 60, 113, 20));
				dataInventario.addPropertyChangeListener(new MyPropertyChangeListener());
				JTextFieldDateEditor f = (JTextFieldDateEditor) dataInventario
						.getDateEditor();
				f.addFocusListener(new FocusAdapter() {

					public void focusGained(FocusEvent e) {
						JTextFieldDateEditor s = (JTextFieldDateEditor) dataInventario
								.getDateEditor();
						s.setCaretPosition(0);
					}

				});
			} catch (Throwable throwable) {
			}
		return dataInventario;
	}

	private static final long serialVersionUID = 1L;
	private JButton btnChiudi;
	private JButton btnStampaInventario;
	private DBManager dbm;
	private int idcarico;
	private JPanel jContentPane;
	private JScrollPane jScrollPane;
	private JLabel lblCodBarre;
	private JLabel lblDescrizioneProdotto;
	private JLabel lblImponibile;
	private JLabel lblImposta;
	private JLabel lblTotale;
	private JLabel lblValoreDeposito;
	private MyButtonListener myButtonListener;
	private JPanel pnlNord;
	private JPanel pnlProdotto;
	private JPanel pnlSud;
	private JTable tblGiacenze;
	private JTextField txtCodBarre;
	private JTextField txtDescrizione;
	private JTextField txtImponibile;
	private JTextField txtImposta;
	private JTextField txtTotale;
	private JDateChooser dataInventario;
	private JButton btnStampaInventarioFornitore = null;
	private JLabel lblTotaleVendita = null;
	private JTextField txtTotaleVendita = null;

	/**
	 * This method initializes btnStampaInventarioFornitore	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnStampaInventarioFornitore() {
		if (btnStampaInventarioFornitore == null) {
			btnStampaInventarioFornitore = new JButton();
			btnStampaInventarioFornitore.setBounds(new Rectangle(756, 12, 113, 41));
			btnStampaInventarioFornitore.setText("<html><div align=\"center\">Stampa<br/>Inven+Fornitore</html>");
			btnStampaInventarioFornitore
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							stampaInventarioFornitore(); // TODO Auto-generated Event stub actionPerformed()
						}
					});
		}
		return btnStampaInventarioFornitore;
	}

	/**
	 * This method initializes txtTotaleVendita	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTotaleVendita() {
		if (txtTotaleVendita == null) {
			txtTotaleVendita = new JTextField();
			txtTotaleVendita.setBounds(new Rectangle(743, 10, 96, 25));
			txtTotaleVendita.setFont(new Font("Dialog", 1, 12));
			txtTotaleVendita.setDisabledTextColor(Color.red);
			txtTotaleVendita.setEditable(false);
			txtTotaleVendita.setEnabled(false);
		}
		return txtTotaleVendita;
	}

}