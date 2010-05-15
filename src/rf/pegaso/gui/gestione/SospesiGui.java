package rf.pegaso.gui.gestione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import rf.pegaso.db.model.SospesiModel;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.UpperTextDocument;

public class SospesiGui extends JFrame implements TableModelListener {
	
	private static final long serialVersionUID = 1L;

	private DBManager dbm;

	private JPanel jContentPane = null;

	private JTabbedPane jTabbedPane = null;

	private SospesiModel modello;

	private JPanel pnlArticoliScaricati = null;

	private JScrollPane jScrollPane3 = null;

	private JTable tblArticoliSospesi = null;

	private JPanel jPanel1 = null;

	private JLabel lblImponibile = null;

	private JTextField txtImponibileTot = null;

	private JLabel lblImpostaTot = null;

	private JTextField txtImpostaTot = null;

	private JLabel lblTot = null;

	private JTextField txtTot = null;

	private JPanel pblBottoni = null;

	private JLabel lblAgio = null;

	private JTextField txtFldAgio = null;

	private JLabel lblNome = null;

	private JTextField txtRicercaNome = null;

	private JLabel lblCognome = null;

	private JTextField txtRicercaCognome = null;
	
	private TableRowSorter<SospesiModel> sorter;

	/**
	 * @param frame
	 * @param dbm
	 */
	public SospesiGui() {
		super();
		this.dbm = DBManager.getIstanceSingleton();
		initialize();
	}
	
	/**
	 *
	 */
	public void erroreCaricamentoDatiDB() {
		JOptionPane.showMessageDialog(this, "Errore caricamento dati db",
				"ERRORE", JOptionPane.ERROR_MESSAGE);
	}
	
	private void calcolaTotaliArticoliScaricati() {
		double imponibile = 0, imposta = 0, tot = 0, agio = 0;

		// Calcoliamo ora la parte totale dello scarico di tutti gli articoli.
		try {
			for(int i = 0; i < tblArticoliSospesi.getRowCount(); i++) {
				imponibile += ControlloDati.convertPrezzoToDouble((String)tblArticoliSospesi.getValueAt(i, 5));
				imposta += ControlloDati.convertPrezzoToDouble((String)tblArticoliSospesi.getValueAt(i, 6));
				agio += ControlloDati.convertPrezzoToDouble((String)tblArticoliSospesi.getValueAt(i, 7));
			}
			
			tot = imponibile + imposta;
			// impostiamo i campi
			txtImponibileTot.setText(ControlloDati
					.convertDoubleToPrezzo(imponibile));
			txtImpostaTot.setText(ControlloDati.convertDoubleToPrezzo(imposta));
			txtTot.setText(ControlloDati.convertDoubleToPrezzo(tot));
			txtFldAgio.setText(ControlloDati.convertDoubleToPrezzo(agio));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Probabile errore nei calcoli all'ingrosso", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

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
			jContentPane.setPreferredSize(new Dimension(700, 650)); // Generated
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER); // Generated
		}
		return jContentPane;
	}	

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {

		// impostiamo la finestra per ascoltare i tasti funzione da F1 in su
		// ed altri pulsanti
		InputMap im = this.getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");
		
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent a) {
				dispose();
			}

		});

		this.setResizable(true); // Generated
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		this.setTitle("Elenco Sospesi");
		this.setResizable(true);
		this.setContentPane(getJContentPane());

		this.setSize(new Dimension(850, 591));
		UtilGUI.centraFrame(this);


		// calcoliamo i totali di tutti gli articoli scaricati e
		// li inseriamo negli appositi textbox
		calcolaTotaliArticoliScaricati();
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			try {
				jTabbedPane = new JTabbedPane();
				jTabbedPane.addTab("Visualizza Fatture Sospese", null,
						getPnlArticoliScaricati(), null); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return jTabbedPane;
	}
	
	/**
	 * This method initializes pnlArticoliScaricati
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlArticoliScaricati() {
		if (pnlArticoliScaricati == null) {
			try {
				pnlArticoliScaricati = new JPanel();
				pnlArticoliScaricati.setLayout(new BorderLayout()); // Generated
				pnlArticoliScaricati.setBorder(BorderFactory.createEmptyBorder(
						0, 0, 0, 0)); // Generated
				pnlArticoliScaricati
						.add(getJScrollPane3(), BorderLayout.CENTER); // Generated
				pnlArticoliScaricati.add(getJPanel1(), BorderLayout.SOUTH); // Generated
				pnlArticoliScaricati.add(getPblBottoni(), BorderLayout.NORTH);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return pnlArticoliScaricati;
	}

	/**
	 * This method initializes jScrollPane3
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			try {
				jScrollPane3 = new JScrollPane();
				jScrollPane3.setPreferredSize(new Dimension(100, 100)); // Generated
				jScrollPane3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
						0)); // Generated
				jScrollPane3.setViewportView(getTblArticoliScaricati()); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes tblArticoliScaricati
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTblArticoliScaricati() {
		if (tblArticoliSospesi == null) {
			try {
				modello = new SospesiModel();
				sorter=new TableRowSorter<SospesiModel>(modello);
				tblArticoliSospesi = new JTable(modello);
				tblArticoliSospesi.setRowSorter(sorter);
				tblArticoliSospesi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tblArticoliSospesi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				Highlighter high = HighlighterFactory.createAlternateStriping();
//				tblArticoliSospesi.setHighlighters(high);
//				tblArticoliSospesi.setModel(modello);
//				tblArticoliSospesi.packAll();
				dbm.addDBStateChange(modello);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return tblArticoliSospesi;
	}

	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			try {
				GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
				gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
				gridBagConstraints21.gridy = 0;
				gridBagConstraints21.weightx = 1.0;
				gridBagConstraints21.gridx = 7;
				GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
				gridBagConstraints11.gridx = 6;
				gridBagConstraints11.gridy = 0;
				lblAgio = new JLabel();
				lblAgio.setText("Agio \u20AC");
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				gridBagConstraints5.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints5.gridx = 5; // Generated
				gridBagConstraints5.gridy = 0; // Generated
				gridBagConstraints5.weightx = 1.0; // Generated
				gridBagConstraints5.weighty = 0.0; // Generated
				gridBagConstraints5.fill = GridBagConstraints.VERTICAL; // Generated
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.anchor = GridBagConstraints.CENTER; // Generated
				gridBagConstraints4.gridx = 4; // Generated
				gridBagConstraints4.gridy = 0; // Generated
				gridBagConstraints4.insets = new Insets(0, 20, 0, 0); // Generated
				lblTot = new JLabel();
				lblTot.setText("Totale \u20AC"); // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints3.insets = new Insets(0, 2, 0, 0); // Generated
				gridBagConstraints3.gridx = 3; // Generated
				gridBagConstraints3.gridy = 0; // Generated
				gridBagConstraints3.weightx = 0.0; // Generated
				gridBagConstraints3.fill = GridBagConstraints.NONE; // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints2.gridx = 2; // Generated
				gridBagConstraints2.gridy = 0; // Generated
				gridBagConstraints2.insets = new Insets(0, 20, 0, 0); // Generated
				lblImpostaTot = new JLabel();
				lblImpostaTot.setText("Imposta tot. \u20AC"); // Generated
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.anchor = GridBagConstraints.WEST; // Generated
				gridBagConstraints1.insets = new Insets(0, 2, 0, 0); // Generated
				gridBagConstraints1.gridx = 1; // Generated
				gridBagConstraints1.gridy = 0; // Generated
				gridBagConstraints1.ipadx = 0; // Generated
				gridBagConstraints1.weightx = 0.0; // Generated
				gridBagConstraints1.fill = GridBagConstraints.VERTICAL; // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0; // Generated
				gridBagConstraints.insets = new Insets(0, 10, 0, 0); // Generated
				gridBagConstraints.gridy = 0; // Generated
				lblImponibile = new JLabel();
				lblImponibile.setText("Imponibile tot. \u20AC"); // Generated
				jPanel1 = new JPanel();
//				jPanel1.setLayout(new GridBagLayout()); // Generated
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				jPanel1.setLayout(flowLayout);
				jPanel1.setPreferredSize(new Dimension(0, 50)); // Generated
				jPanel1.setBorder(BorderFactory
						.createBevelBorder(BevelBorder.RAISED)); // Generated
				jPanel1.add(lblImponibile, null); // Generated
				jPanel1.add(getTxtImponibileTot(), null); // Generated
				jPanel1.add(lblImpostaTot, null); // Generated
				jPanel1.add(getTxtImpostaTot(), null); // Generated
				jPanel1.add(lblTot, null); // Generated
				jPanel1.add(getTxtTot(), null); // Generated
				jPanel1.add(lblAgio, null);
				jPanel1.add(getTxtFldAgio(), null);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return jPanel1;
	}

	/**
	 * This method initializes txtImponibileTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileTot() {
		if (txtImponibileTot == null) {
			try {
				txtImponibileTot = new JTextField();
				txtImponibileTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtImponibileTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImponibileTot.setEnabled(false); // Generated
				txtImponibileTot.setDisabledTextColor(Color.black); // Generated
				txtImponibileTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return txtImponibileTot;
	}

	/**
	 * This method initializes txtImpostaTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImpostaTot() {
		if (txtImpostaTot == null) {
			try {
				txtImpostaTot = new JTextField();
				txtImpostaTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtImpostaTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtImpostaTot.setEnabled(false); // Generated
				txtImpostaTot.setDisabledTextColor(Color.black); // Generated
				txtImpostaTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return txtImpostaTot;
	}

	/**
	 * This method initializes txtTot
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTot() {
		if (txtTot == null) {
			try {
				txtTot = new JTextField();
				txtTot.setPreferredSize(new Dimension(100, 20)); // Generated
				txtTot.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
				txtTot.setEnabled(false); // Generated
				txtTot.setDisabledTextColor(Color.red); // Generated
				txtTot.setEditable(false); // Generated
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return txtTot;
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		
	}

	/**
	 * This method initializes pblBottoni	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPblBottoni() {
		if (pblBottoni == null) {
			lblCognome = new JLabel();
			lblCognome.setText("Cognome");
			lblNome = new JLabel();
			lblNome.setText("Nome");
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pblBottoni = new JPanel();
			pblBottoni.setLayout(flowLayout);
			pblBottoni.setPreferredSize(new Dimension(100, 40));
			pblBottoni.add(lblNome, null);
			pblBottoni.add(getTxtRicercaNome(), null);
			pblBottoni.add(lblCognome, null);
			pblBottoni.add(getTxtRicercaCognome(), null);
		}
		return pblBottoni;
	}

	/**
	 * This method initializes txtFldAgio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldAgio() {
		if (txtFldAgio == null) {
			txtFldAgio = new JTextField();
			txtFldAgio.setPreferredSize(new Dimension(100, 20)); // Generated
			txtFldAgio.setFont(new Font("Dialog", Font.BOLD, 12)); // Generated
			txtFldAgio.setEnabled(false); // Generated
			txtFldAgio.setDisabledTextColor(Color.black); // Generated
			txtFldAgio.setEditable(false); // Generated
		}
		return txtFldAgio;
	}

	/**
	 * This method initializes txtRicercaNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRicercaNome() {
		if (txtRicercaNome == null) {
			txtRicercaNome = new JTextField();
			txtRicercaNome.setPreferredSize(new Dimension(150, 20));
			txtRicercaNome.setDocument(new UpperTextDocument());
			txtRicercaNome.getDocument().addDocumentListener(
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
		}
		return txtRicercaNome;
	}

	/**
	 * This method initializes txtRicercaCognome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRicercaCognome() {
		if (txtRicercaCognome == null) {
			txtRicercaCognome = new JTextField();
			txtRicercaCognome.setPreferredSize(new Dimension(150, 20));
			txtRicercaCognome.setDocument(new UpperTextDocument());
			//aggiungo gli ascoltatori
			txtRicercaCognome.getDocument().addDocumentListener(
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
		}
		return txtRicercaCognome;
	}
	
	private void newFilter() {
		RowFilter<SospesiModel, Object> rf = null;
        RowFilter<SospesiModel, Object> rf1 = null;
        RowFilter<SospesiModel, Object> rf2 = null;

        //If current expression doesn't parse, don't update.
        try {
            rf1 = RowFilter.regexFilter(txtRicercaCognome.getText(), 1);
            rf2 = RowFilter.regexFilter(txtRicercaNome.getText(), 0);//la X indica la col. Autore nella tabella
            ArrayList<RowFilter<SospesiModel,Object>> filters = new ArrayList<RowFilter<SospesiModel,Object>>(2);
            filters.add(rf1);
            filters.add(rf2);
            rf = RowFilter.andFilter(filters);
        }
        catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        calcolaTotaliArticoliScaricati();
    }
}