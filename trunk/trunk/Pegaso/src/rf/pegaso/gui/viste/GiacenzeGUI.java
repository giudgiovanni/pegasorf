/**
 * 
 */
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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.model.GiacenzeModel;
import rf.pegaso.db.model.search.GiacenzeSearchCodiceModel;
import rf.pegaso.db.model.search.GiacenzeSearchDescModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Giacenze;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.utility.ControlloDati;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

/**
 * @author Hunter
 * 
 */
public class GiacenzeGUI extends JFrame {
	class MyButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnChiudi) {
				dispose();
			} else if (e.getSource() == btnStampaInventario) {
				stampaInventario();
			}

		}

	}

	private static final long serialVersionUID = 1L;

	private JButton btnChiudi = null;

	private JButton btnStampaInventario = null;

	private DBManager dbm;

	private int idcarico = 0;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JLabel lblCodBarre = null;

	private JLabel lblDescrizioneProdotto = null;

	private JLabel lblImponibile = null;

	private JLabel lblImposta = null;

	private JLabel lblTotale = null;
	private JLabel lblValoreDeposito = null;

	private MyButtonListener myButtonListener; // @jve:decl-index=0:

	private JPanel pnlNord = null;

	private JPanel pnlProdotto = null;

	private JPanel pnlSud = null;

	private JXTable tblGiacenze = null;

	private JTextField txtCodBarre = null;

	private JTextField txtDescrizione = null;

	private JTextField txtImponibile = null;

	private JTextField txtImposta = null;

	private JTextField txtTotale = null;

	private JDateChooser dataInventario = null;

	/**
	 * @param frame
	 * @param dbm
	 */
	public GiacenzeGUI(Frame frame) {
		this.dbm = DBManager.getIstanceSingleton();
		initialize();

	}

	/**
	 * 
	 */
	public void azzeraCampi() {
		txtCodBarre.setText("");
		txtDescrizione.setText("");

	}

	/**
	 * 
	 */
	public void erroreCaricamentoDatiDB() {
		JOptionPane.showMessageDialog(this, "Errore caricamento dati db",
				"ERRORE", JOptionPane.ERROR_MESSAGE);

	}

	private void avvisoCodBarreInesistente() {
		JOptionPane.showMessageDialog(this,
				"Codice barre articolo inesistente", "Codice inesistente",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 */
	private void calcolaTotali() {
		double totImponibile = 0.0;
		double totImposta = 0.0;
		double tot = totImponibile + totImposta;
		try {
			totImponibile = Giacenze.getTotImponibile( );
			txtImponibile.setText(ControlloDati
					.convertDoubleToPrezzo(totImponibile));
		} catch (SQLException e) {
			messaggioErroreCampo("Errore nel calcolo dell'imponibile");
			e.printStackTrace();
		}

		try {
			totImposta = Giacenze.getTotImposta( );
			txtImposta.setText(ControlloDati.convertDoubleToPrezzo(totImposta));
		} catch (SQLException e) {
			messaggioErroreCampo("Errore calcolo totale imposta");
			e.printStackTrace();
		}
		tot = totImponibile + totImposta;
		txtTotale.setText(ControlloDati.convertDoubleToPrezzo(tot));

	}

	/**
	 * @param cmbProdotti2
	 */
	private void caricaArticoli(JComboBox cmbProdotti) {
		Articolo f = new Articolo();
		try {
			cmbProdotti.removeAllItems();
			cmbProdotti.addItem("");
			for (String fornitore : (String[]) f.allArticoli()) {
				cmbProdotti.addItem(fornitore);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Errore caricamento articoli nel combobox", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	private void caricaClienti(JComboBox cmbFornitori) {
		/*
		 * Fornitore f=new Fornitore(dbm); try { //cmbClienti=new
		 * JComboBox(f.allFornitori()); cmbFornitori.removeAllItems();
		 * cmbFornitori.addItem(""); for (String fornitore :
		 * (String[])f.allFornitori()) { cmbFornitori.addItem(fornitore); } }
		 * catch (SQLException e) { JOptionPane.showMessageDialog(this, "Errore
		 * caricamento fornitori nel combobox", "ERRORE",
		 * JOptionPane.ERROR_MESSAGE); e.printStackTrace(); }
		 */

		cmbFornitori.removeAll();
		cmbFornitori.addItem("0 - BANCO");

	}

	/**
	 * 
	 */
	private void elimina() {
		if (tblGiacenze.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un righa",
					"AVVISO", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int riga = tblGiacenze.getSelectedRow();
		String codBarre = ((String) tblGiacenze.getValueAt(riga, 0));
		Articolo a = new Articolo();
		try {
			a.caricaDatiByCodBarre(codBarre);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IDNonValido e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scarico c = new Scarico( );
		c.setIdScarico(c.getNewID() - 1);
		try {
			c.deleteArticolo(a.getIdArticolo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
				btnChiudi.setText("Chiudi"); // Generated
				btnChiudi.setBounds(new Rectangle(520, 12, 109, 40)); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnStampaInventario
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampaInventario() {
		if (btnStampaInventario == null) {
			try {
				btnStampaInventario = new JButton();
				btnStampaInventario.setBounds(new Rectangle(632, 12, 113, 41)); // Generated
				btnStampaInventario
						.setText("<html><div align=\"center\">Stampa<br/>Inventario</html>"); // Generated
				btnStampaInventario.addActionListener(new MyButtonListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnStampaInventario;
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
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER); // Generated
			jContentPane.add(getPnlSud(), BorderLayout.SOUTH); // Generated
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
				jScrollPane.setPreferredSize(new Dimension(453, 350)); // Generated
				jScrollPane.setViewportView(getTblGiacenze()); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jScrollPane;
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
				pnlNord.setPreferredSize(new Dimension(1, 110)); // Generated
				pnlNord.add(getPnlProdotto(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlNord;
	}

	/**
	 * This method initializes pnlProdotto
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlProdotto() {
		if (pnlProdotto == null) {
			try {
				lblDescrizioneProdotto = new JLabel();
				lblDescrizioneProdotto
						.setBounds(new Rectangle(148, 24, 82, 13)); // Generated
				lblDescrizioneProdotto.setText("Descrizione"); // Generated
				lblCodBarre = new JLabel();
				lblCodBarre.setBounds(new Rectangle(8, 24, 98, 13)); // Generated
				lblCodBarre.setText("Codice prodotto"); // Generated
				pnlProdotto = new JPanel();
				pnlProdotto.setLayout(null); // Generated
				pnlProdotto.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Filtro prodotti", TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, new Font("Dialog",
								Font.BOLD, 12), new Color(51, 51, 51))); // Generated
				pnlProdotto.setLocation(new Point(8, 8)); // Generated
				pnlProdotto.setSize(new Dimension(766, 92)); // Generated
				pnlProdotto.add(lblCodBarre, null); // Generated
				pnlProdotto.add(getTxtCodBarre(), null); // Generated
				pnlProdotto.add(lblDescrizioneProdotto, null); // Generated
				pnlProdotto.add(getTxtDescrizione(), null); // Generated
				pnlProdotto.add(getBtnChiudi(), null); // Generated
				pnlProdotto.add(getBtnStampaInventario(), null); // Generated
				pnlProdotto.add(getDataInventario(), null); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlProdotto;
	}

	/**
	 * This method initializes pnlSud
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud() {
		if (pnlSud == null) {
			try {
				lblTotale = new JLabel();
				lblTotale.setBounds(new Rectangle(484, 10, 57, 25)); // Generated
				lblTotale.setText("Totale €."); // Generated
				lblImposta = new JLabel();
				lblImposta.setBounds(new Rectangle(312, 10, 67, 25)); // Generated
				lblImposta.setText("Imposta €."); // Generated
				lblImponibile = new JLabel();
				lblImponibile.setBounds(new Rectangle(128, 10, 77, 25)); // Generated
				lblImponibile.setText("Imponibile €."); // Generated
				lblValoreDeposito = new JLabel();
				lblValoreDeposito.setBounds(new Rectangle(11, 10, 114, 25)); // Generated
				lblValoreDeposito.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				lblValoreDeposito.setText("Valore in deposito :"); // Generated
				pnlSud = new JPanel();
				pnlSud.setLayout(null); // Generated
				pnlSud.setPreferredSize(new Dimension(0, 50)); // Generated
				pnlSud.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				pnlSud.add(lblValoreDeposito, null); // Generated
				pnlSud.add(lblImponibile, null); // Generated
				pnlSud.add(getTxtImponibile(), null); // Generated
				pnlSud.add(lblImposta, null); // Generated
				pnlSud.add(lblTotale, null); // Generated
				pnlSud.add(getTxtImposta(), null); // Generated
				pnlSud.add(getTxtTotale(), null);

				// Impotiamo i campi del pannello per visualizzare
				// i totali
				double totImponibile = Giacenze.getTotImponibile( );
				double totImposta = totImponibile / 100 * 20;
				double totale = totImponibile + totImposta;
				txtImponibile.setText(ControlloDati
						.convertDoubleToPrezzo(totImponibile));
				txtImposta.setText(ControlloDati
						.convertDoubleToPrezzo(totImposta));
				txtTotale.setText(ControlloDati.convertDoubleToPrezzo(totale));
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return pnlSud;
	}

	/**
	 * This method initializes tblGiacenze
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTblGiacenze() {
		if (tblGiacenze == null) {
			try {
				GiacenzeModel modello = new GiacenzeModel(dbm);
				dbm.addDBStateChange(modello);
				tblGiacenze = new JXTable();
				tblGiacenze
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblGiacenze.setModel(modello);
				tblGiacenze.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblGiacenze.packAll();
				tblGiacenze.getTableHeader().setReorderingAllowed(false);
				TableColumn col = tblGiacenze.getColumnModel().getColumn(0);
				col.setMinWidth(0);
				col.setMaxWidth(0);
				col.setPreferredWidth(0);
				
				col = tblGiacenze.getColumn("codice");
				DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.LEFT);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(150);
				col.setPreferredWidth(150);
				
				col = tblGiacenze.getColumn("um");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(60);
				col.setPreferredWidth(60);
				
				col = tblGiacenze.getColumn("carico");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(60);
				col.setPreferredWidth(60);
				
				col = tblGiacenze.getColumn("scarico");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(60);
				col.setPreferredWidth(60);
				
				col = tblGiacenze.getColumn("deposito");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.CENTER);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(80);
				col.setPreferredWidth(80);
				
				col = tblGiacenze.getColumn("prezzo_acquisto");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(100);
				col.setPreferredWidth(100);
				
				col = tblGiacenze.getColumn("prezzo_tot");
				cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				col.setCellRenderer(cellRenderer);
				col.setMinWidth(0);
				col.setMaxWidth(100);
				col.setPreferredWidth(100);

			} catch (java.lang.Throwable e) {
				try {
					PrintWriter p = new PrintWriter("5.txt");
					e.printStackTrace(p);
					p.flush();
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}

			}
		}
		return tblGiacenze;
	}

	/**
	 * This method initializes txtCodBarre
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtCodBarre() {
		if (txtCodBarre == null) {
			try {
				txtCodBarre = new JTextField();
				txtCodBarre.setBounds(new Rectangle(8, 40, 137, 21)); // Generated
				txtCodBarre.setDocument(new UpperTextDocument());
				txtCodBarre.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						try {
							GiacenzeSearchCodiceModel g = new GiacenzeSearchCodiceModel(
									dbm, txtCodBarre.getText());
							tblGiacenze.setModel(g);
							tblGiacenze.packAll();
						} catch (SQLException e1) {
							messaggioErroreCampo("Errore nella ricerca del codice");
							e1.printStackTrace();
						}
					}
				});
				txtCodBarre.addFocusListener(new java.awt.event.FocusAdapter() {
					@Override
					public void focusLost(java.awt.event.FocusEvent e) {
						txtCodBarre.setText("");
						ricaricaAllGiacenze();
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtCodBarre;
	}

	/**
	 * This method initializes txtDescrizione
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JTextField getTxtDescrizione() {
		if (txtDescrizione == null) {
			try {
				txtDescrizione = new JTextField();
				txtDescrizione.setBounds(new Rectangle(148, 40, 366, 21)); // Generated
				txtDescrizione.setDocument(new UpperTextDocument());
				txtDescrizione
						.addFocusListener(new java.awt.event.FocusAdapter() {
							@Override
							public void focusLost(java.awt.event.FocusEvent e) {
								txtDescrizione.setText("");
								ricaricaAllGiacenze();
							}
						});
				txtDescrizione.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						try {
							GiacenzeSearchDescModel g = new GiacenzeSearchDescModel(
									dbm, txtDescrizione.getText());
							tblGiacenze.setModel(g);
							tblGiacenze.packAll();

						} catch (SQLException e1) {
							messaggioErroreCampo("Errore nella ricerca del codice");
							e1.printStackTrace();
						}
					}
				});
				// txtDescrizione.addActionListener(new MyComboBoxListener());
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtDescrizione;

	}

	/**
	 * This method initializes txtImponibile
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibile() {
		if (txtImponibile == null) {
			try {
				txtImponibile = new JTextField();
				txtImponibile.setBounds(new Rectangle(204, 10, 93, 25)); // Generated
				txtImponibile.setEnabled(false); // Generated
				txtImponibile.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImponibile.setDisabledTextColor(Color.black); // Generated
				txtImponibile.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImponibile;
	}

	/**
	 * This method initializes txtImposta
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImposta() {
		if (txtImposta == null) {
			try {
				txtImposta = new JTextField();
				txtImposta.setBounds(new Rectangle(380, 10, 93, 25)); // Generated
				txtImposta.setEnabled(false); // Generated
				txtImposta.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImposta.setDisabledTextColor(Color.black); // Generated
				txtImposta.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtImposta;
	}

	/**
	 * This method initializes txtTotale
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotale() {
		if (txtTotale == null) {
			try {
				txtTotale = new JTextField();
				txtTotale.setBounds(new Rectangle(540, 10, 93, 25)); // Generated
				txtTotale.setEnabled(false); // Generated
				txtTotale.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtTotale.setDisabledTextColor(Color.red); // Generated
				txtTotale.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return txtTotale;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		Scarico c = new Scarico( );
		this.idcarico = c.getNewID();
		this.setSize(790, 550);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("Inventario Merce");
		this.setResizable(true);
		this.setContentPane(getJContentPane());
		UtilGUI.centraFrame(this);
		
		// inseriamo il carico nel db
		// inizializzo tutti i listener
		inizializzaListeners();
		calcolaTotali();

	}

	/**
	 * 
	 */
	private void inizializzaListeners() {
		myButtonListener = new MyButtonListener();

		// aggiungo il listener nei suoi oggetti.
		btnChiudi.addActionListener(myButtonListener);
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo) {
		JOptionPane.showMessageDialog(this, testo, "CAMPO VUOTO",
				JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * @param string
	 */
	private void messaggioErroreCampo(String testo) {
		JOptionPane.showMessageDialog(this, testo, "ERRORE",
				JOptionPane.ERROR_MESSAGE);

	}

	private void ricaricaAllGiacenze() {
		GiacenzeModel modello = null;
		try {
			modello = new GiacenzeModel(dbm);
		} catch (SQLException e1) {
			messaggioErroreCampo("Errore ricerca di tutte le giacenze");
			e1.printStackTrace();
		}
		tblGiacenze.setModel(modello);
		tblGiacenze.packAll();
	}

	private void stampaInventario() {
		int scelta = JOptionPane.showConfirmDialog(this,
				"Sei sicuro di voler stampare l'inventario?", "AVVISO",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (scelta != JOptionPane.YES_OPTION) {
			return;
		}
		Map par = new HashMap();
		par.put("data_inventario", dataInventario.getDate());
		try {
			JasperViewer.viewReport(JasperFillManager.fillReport(
					"report/giacenzemagazzino.jasper", par, this.dbm
							.getConnessione()), false);

		} catch (JRException e) {
			//
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes dataInventario
	 * 
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataInventario() {
		if (dataInventario == null) {
			try {
				//dataInventario = new JDateChooser("dd/MM/yyyy", false);
				dataInventario=new JDateChooser("dd/MM/yyyy","##/##/##",'_');
				dataInventario.setDate(new Date());
				dataInventario.setBounds(new Rectangle(632, 60, 113, 20)); // Generated
				JTextFieldDateEditor f=(JTextFieldDateEditor) dataInventario.getDateEditor();
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent e) {
						JTextFieldDateEditor s=(JTextFieldDateEditor) dataInventario.getDateEditor();
						s.setCaretPosition(0);
					}
				});

			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return dataInventario;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
