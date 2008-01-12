package rf.pegaso.gui.primanota;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import rf.myswing.util.QuantitaDisponibileEditorSQL;
import rf.pegaso.db.DBManager;
import rf.pegaso.db.UtilityDBManager;
import rf.pegaso.db.exception.ResultSetVuoto;
import rf.pegaso.db.model.PrimaNotaModelUscite;
import rf.pegaso.db.model.PrimanotaModelEntrate;
import rf.pegaso.db.model.ScaricoModel;
import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Carico;
import rf.pegaso.db.tabelle.Cliente;
import rf.pegaso.db.tabelle.Documento;
import rf.pegaso.db.tabelle.Fornitore;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.db.tabelle.exception.IDNonValido;
import rf.pegaso.gui.gestione.DocumentoAddMod;
import rf.utility.ControlloDati;
import rf.utility.gui.ComboBoxUtil;
import rf.utility.gui.UtilGUI;
import rf.utility.gui.text.AutoCompletion;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import rf.myswing.IDJComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import rf.utility.gui.text.UpperTextDocument;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Double;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;

public class PrimaNotaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JPanel pnlCentrale = null;

    private JPanel pnlNord = null;

    private JPanel pnlCentro = null;

    private JPanel pnlCentro1 = null;

    private JButton btnInserisci = null;

    private JButton Modifica = null;

    private JButton btnElimina = null;

    private JButton btnEsci = null;

    private JTabbedPane jTabbedPane = null;

    private JPanel pnlEntrate = null;

    private JPanel pnlUscite = null;

    private JScrollPane jScrollPane = null;

    private JXTable tblEntrate = null;

    private PrimanotaModelEntrate modello;

    private DecimalFormat formatPrice = null;

    private JScrollPane jScrollPane2 = null;

	private JXTable tblUscite = null;

	private PrimaNotaModelUscite modelloUscite = null;

	private JPanel pnlNord1 = null;

	private JDateChooser dataDocumento = null;

	private JLabel lblDataDocumento = null;

	private JLabel lblTipoDocumento = null;

	private IDJComboBox cmbTipoDocumento = null;

	private JLabel lblCliente = null;

	private IDJComboBox cmbClienti = null;

	private JPanel pnlNote = null;

	private JScrollPane jScrollPane1 = null;

	private JTextArea txtNote = null;

	private JLabel lblTot = null;

	private JFormattedTextField txtTotale = null;

	private DecimalFormat formatPrice1 = null;

	private JLabel lblNumDocumento = null;

	private JTextField txtNumDocumento = null;

	private JPanel pnlNord11 = null;

	private JDateChooser dataDocumento1 = null;

	private JLabel lblDataDocumento1 = null;

	private JLabel lblTipoDocumento1 = null;

	private IDJComboBox cmbTipoDocumento1 = null;

	private JLabel lblCliente1 = null;

	private IDJComboBox cmbClienti1 = null;

	private JPanel pnlNote1 = null;

	private JScrollPane jScrollPane11 = null;

	private JTextArea txtNote1 = null;

	private JLabel lblTot1 = null;

	private JFormattedTextField txtTotale1 = null;

	private DecimalFormat formatPrice11 = null;

	private JLabel lblNumDocumento1 = null;

	private JTextField txtNumDocumento1 = null;

	private JPanel pnlSud1 = null;

	private JLabel lblIngImponibile = null;

	private JTextField txtImponibileIng = null;

	private JLabel lblIngImposta = null;

	private JTextField txtImpostaIng = null;

	private JLabel lblTotIng = null;

	private JTextField txtTotaleIng = null;

	private JLabel lblIvaDocumento = null;

	private JFormattedTextField txtIvaDocumento = null;

	private NumberFormat formatPrezzoDocumento = null;

	/**
     * This is the default constructor
     */
    public PrimaNotaGUI() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
//    	 impostiamo la finestra per ascoltare i tasti funzione da F1 in su
		// ed altri pulsanti
		InputMap im = this.getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_CANCEL, 0), "CANC");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT+KeyEvent.VK_M, 0), "MOD");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");

		this.getRootPane().getActionMap().put("ENTER", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				int tabbed=jTabbedPane.getSelectedIndex();
                if(tabbed==0)
                    inserisciEntrata();
                //else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("CANC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				int tabbed=jTabbedPane.getSelectedIndex();
                if(tabbed==0)
                    eliminaEntrata();
                //else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("MOD", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				 int tabbed=jTabbedPane.getSelectedIndex();
                 if(tabbed==0)
                     modificaEntrata();
                 //else //inserisciUscita();
			}

		});
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				dispose();
			}

		});


        this.setSize(716, 600);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("Prima Nota");
        caricaClienti(cmbClienti);
        caricaDocumenti(cmbTipoDocumento);
        caricaFornitori(cmbClienti1);
        caricaDocumenti(cmbTipoDocumento1);
        UtilGUI.centraFrame(this);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
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
            jContentPane.add(getPnlCentrale(), BorderLayout.CENTER);
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
            pnlCentrale = new JPanel();
            pnlCentrale.setLayout(new BorderLayout());
            pnlCentrale.add(getPnlNord(), BorderLayout.NORTH);
            pnlCentrale.add(getPnlCentro(), BorderLayout.CENTER);
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
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(FlowLayout.LEFT);
            pnlNord = new JPanel();
            pnlNord.setLayout(flowLayout);
            pnlNord.setPreferredSize(new Dimension(0, 40));
            pnlNord.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            pnlNord.add(getBtnInserisci(), null);
            pnlNord.add(getModifica(), null);
            pnlNord.add(getBtnElimina(), null);
            pnlNord.add(getBtnEsci(), null);
        }
        return pnlNord;
    }

    /**
     * This method initializes pnlCentro
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlCentro() {
        if (pnlCentro == null) {
            pnlCentro = new JPanel();
            pnlCentro.setLayout(new BorderLayout());
            pnlCentro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            pnlCentro.add(getPnlCentro1(), BorderLayout.CENTER);
        }
        return pnlCentro;
    }

    /**
     * This method initializes pnlCentro1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlCentro1() {
        if (pnlCentro1 == null) {
            pnlCentro1 = new JPanel();
            pnlCentro1.setLayout(new BorderLayout());
            pnlCentro1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            pnlCentro1.add(getJTabbedPane(), BorderLayout.CENTER);
        }
        return pnlCentro1;
    }

    protected void eliminaEntrata() {
        if (tblEntrate.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(this, "Selezionare un righa",
                    "AVVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int scelta = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler\neliminare la riga selezionata?",
                "AVVISO", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (scelta != JOptionPane.YES_OPTION)
            return;

        // PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
        try {
            UtilityDBManager.getSingleInstance().backupDataBase(
                    UtilityDBManager.DELETE);
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

        int riga = tblEntrate.getSelectedRow();
        int idscarico = ((Long) tblEntrate.getValueAt(riga, 0)).intValue();
        Scarico c = new Scarico();

        try {
            c.caricaDati(idscarico);
            c.deleteScarico(idscarico);
        } catch (IDNonValido e) {
            JOptionPane.showMessageDialog(this, "Valore idCliente errato",
                    "ERRORE", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

    }

    /**
     * This method initializes btnInserisci
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnInserisci() {
        if (btnInserisci == null) {
            btnInserisci = new JButton();
            btnInserisci.setText("Inserisci (Invio)");
            btnInserisci.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int tabbed=jTabbedPane.getSelectedIndex();
                    if(tabbed==0)
                        inserisciEntrata();
                    else inserisciUscita();
                }
            });
        }
        return btnInserisci;
    }

    protected void inserisciUscita() {


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
        try {
            c.setIdCarico(DBManager.getIstanceSingleton().getNewID("ordini", "idordine"));
            c.setIdFornitore((new Integer(cmbClienti1.getIDSelectedItem())).intValue());
            c
                    .setDataCarico(new java.sql.Date(dataDocumento1.getDate()
                            .getTime()));
            c.setOraCarico((new Time(dataDocumento1.getDate().getTime())));
            c.setNote(txtNote1.getText());
            c.setDataDocumento(new java.sql.Date(dataDocumento1.getDate()
                    .getTime()));
            c.setNumDocumento(txtNumDocumento1.getText());
            c.setIdDocumento((new Integer(cmbTipoDocumento.getIDSelectedItem())).intValue());
            Object t=txtTotale1.getValue();
            if(t instanceof Double)
                c.setTotaleDocumentoIvato(((Double)txtTotale1.getValue()).doubleValue());
            else{
                long tmp=((Long)txtTotale1.getValue()).longValue();
                c.setTotaleDocumentoIvato(new Double(tmp).doubleValue());
            }

            c.setIdDocumento((new Integer(cmbTipoDocumento1.getIDSelectedItem())).intValue());
            c.insertCarico();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


	}

	/**
     * This method initializes Modifica
     *
     * @return javax.swing.JButton
     */
    private JButton getModifica() {
        if (Modifica == null) {
            Modifica = new JButton();
            Modifica.setText("Modifica");
            Modifica.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int tabbed=jTabbedPane.getSelectedIndex();
                    if(tabbed==0)
                        modificaEntrata();
                    else modificaUscita();
                }
            });
        }
        return Modifica;
    }

    protected void modificaEntrata() {
        if (tblEntrate.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(this, "Selezionare un righa",
                    "AVVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int riga = tblEntrate.getSelectedRow();
        int idscarico = ((Long) tblEntrate.getValueAt(riga, 0)).intValue();
        ModificaEntrata m=new ModificaEntrata(idscarico,this);
        m.setVisible(true);

    }

    protected void modificaUscita() {
        if (tblUscite.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(this, "Selezionare un righa",
                    "AVVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int riga = tblUscite.getSelectedRow();
        int idscarico = ((Long) tblUscite.getValueAt(riga, 0)).intValue();
        ModificaUscita m=new ModificaUscita(idscarico,this);
        m.setVisible(true);

    }

    /**
     * This method initializes btnElimina
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnElimina() {
        if (btnElimina == null) {
            btnElimina = new JButton();
            btnElimina.setText("Elimina (Canc)");
            btnElimina.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int tabbed=jTabbedPane.getSelectedIndex();
                    if(tabbed==0)
                        eliminaEntrata();
                    else eliminaUscita();
                }
            });
        }
        return btnElimina;
    }

    protected void eliminaUscita() {
    	 if (tblUscite.getSelectedRow() <= -1) {
             JOptionPane.showMessageDialog(this, "Selezionare un righa",
                     "AVVISO", JOptionPane.INFORMATION_MESSAGE);
             return;
         }

         int scelta = JOptionPane.showConfirmDialog(this,
                 "Sei sicuro di voler\neliminare la riga selezionata?",
                 "AVVISO", JOptionPane.YES_NO_OPTION,
                 JOptionPane.INFORMATION_MESSAGE);
         if (scelta != JOptionPane.YES_OPTION)
             return;

         // PUNTO DI BACKUP DA ATTIVARE DA CONFIGURAZIONI
         try {
             UtilityDBManager.getSingleInstance().backupDataBase(
                     UtilityDBManager.DELETE);
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

         int riga = tblUscite.getSelectedRow();
         int idscarico = ((Long) tblUscite.getValueAt(riga, 0)).intValue();
         Carico c = new Carico();

         try {
             c.caricaDati(idscarico);
             c.deleteCarico(idscarico);
         } catch (IDNonValido e) {
             JOptionPane.showMessageDialog(this, "Valore idCliente errato",
                     "ERRORE", JOptionPane.WARNING_MESSAGE);
             e.printStackTrace();
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Errore nel db", "ERRORE",
                     JOptionPane.WARNING_MESSAGE);
             e.printStackTrace();
         }

     }


	/**
     * This method initializes btnEsci
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnEsci() {
        if (btnEsci == null) {
            btnEsci = new JButton();
            btnEsci.setText("Esci (Esc)");
            btnEsci.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnEsci;
    }

    private void caricaFornitori(JComboBox cmbFornitori) {
    		cmbFornitori.removeAllItems();
			Fornitore f = new Fornitore();
			try {

				String as[] = (String[]) f.allFornitori();
				// carichiamo tutti i dati in due array
				// da passre al combobox
				((IDJComboBox) cmbFornitori).caricaNewValueComboBox(as, false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,
						"Errore caricamento fornitori nel combobox", "ERRORE", 0);
				e.printStackTrace();
			}
			AutoCompletion.enable(cmbFornitori);
		}

	/**
     * This method initializes jTabbedPane
     *
     * @return javax.swing.JTabbedPane
     */
    private JTabbedPane getJTabbedPane() {
        if (jTabbedPane == null) {
            jTabbedPane = new JTabbedPane();
            jTabbedPane.addTab("Entrate", null, getPnlEntrate(), null);
            jTabbedPane.addTab("Uscite", null, getPnlUscite(), null);

        }
        return jTabbedPane;
    }

    /**
     * This method initializes pnlEntrate
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlEntrate() {
        if (pnlEntrate == null) {
            pnlEntrate = new JPanel();
            pnlEntrate.setLayout(new BorderLayout());
            pnlEntrate.add(getJScrollPane(), BorderLayout.CENTER);
            pnlEntrate.add(getPnlNord1(), BorderLayout.NORTH);
            pnlEntrate.add(getPnlSud1(), BorderLayout.SOUTH);
        }
        return pnlEntrate;
    }

    /**
     * This method initializes pnlUscite
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlUscite() {
        if (pnlUscite == null) {
            pnlUscite = new JPanel();
            pnlUscite.setLayout(new BorderLayout());
            pnlUscite.add(getJScrollPane2(), BorderLayout.CENTER);
            pnlUscite.add(getPnlNord11(), BorderLayout.NORTH);
        }
        return pnlUscite;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            jScrollPane.setViewportView(getTblEntrate());
        }
        return jScrollPane;
    }

    /**
     * This method initializes tblEntrate
     *
     * @return javax.swing.JTable
     */
    private JTable getTblEntrate() {
        if (tblEntrate == null) {

            try {
                modello = new PrimanotaModelEntrate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            DBManager.getIstanceSingleton().addDBStateChange(modello);
            tblEntrate = new JXTable(modello);
            tblEntrate
                    .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // impostiamo le varie colonne
            TableColumn col = tblEntrate.getColumnModel().getColumn(0);
            col.setMinWidth(0);
            col.setMaxWidth(0);
            col.setPreferredWidth(0);

            col = tblEntrate.getColumn("totale_documento");
            DefaultTableCellRenderer prezzoColumnRenderer = new DefaultTableCellRenderer();
            prezzoColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);
            col.setCellRenderer(prezzoColumnRenderer);
            col.setPreferredWidth(40);

            col = tblEntrate.getColumn("data_documento");
            DefaultTableCellRenderer dataDoc = new DefaultTableCellRenderer();
            dataDoc.setHorizontalAlignment(JLabel.CENTER);
            col.setCellRenderer(dataDoc);
            col.setPreferredWidth(40);

            col = tblEntrate.getColumn("num_documento");
            DefaultTableCellRenderer numDoc = new DefaultTableCellRenderer();
            numDoc.setHorizontalAlignment(JLabel.CENTER);
            col.setCellRenderer(numDoc);
            col.setPreferredWidth(40);

            tblEntrate.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblEntrate.packAll();
            // per evitare che si possano spostare le colonne dalla
            // posizione originaria
            tblEntrate.getTableHeader().setReorderingAllowed(false);

        }
        return tblEntrate;
    }

    /**
     * @param string
     */
    private void messaggioCampoMancante(String testo, String tipo) {
        JOptionPane.showMessageDialog(this, testo, tipo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void caricaClienti(JComboBox cmbFornitori) {
        cmbFornitori.removeAllItems();
    	Cliente c = new Cliente();
        try {

            String as[] = (String[]) c.allClienti();
            // carichiamo tutti i dati in due array
            // da passre al combobox
            ((IDJComboBox) cmbClienti).caricaNewValueComboBox(as, false);
        } catch (SQLException e) {
            messaggioCampoMancante("Errore caricamento clienti nel combobox", "ERRORE");
            e.printStackTrace();
        }
        AutoCompletion.enable(cmbClienti);
    }


    private void caricaDocumenti(JComboBox cmbDocumenti) {

    	cmbDocumenti.removeAllItems();
            Documento f = new Documento();
            try {

                String as[] = (String[]) f.allDocumentiConDescrizione();
                // carichiamo tutti i dati in due array
                // da passre al combobox
                ((IDJComboBox) cmbDocumenti).caricaNewValueComboBox(as, false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Errore caricamento documenti nel combobox", "ERRORE", 0);
                e.printStackTrace();
            }
            AutoCompletion.enable(cmbDocumenti);
        }

    protected void nuovoDocumento() {
		DocumentoAddMod doc=new DocumentoAddMod(this,DBManager.getIstanceSingleton(),DocumentoAddMod.ADD);
		doc.setVisible(true);
		caricaDocumenti(cmbTipoDocumento);

	}

	/**
     * This method initializes formatPrice
     *
     * @return java.text.DecimalFormat
     */
    private DecimalFormat getFormatPrice() {
        if (formatPrice == null) {
            formatPrice = new DecimalFormat();
            formatPrice.setMinimumFractionDigits(2);
            formatPrice.setMaximumFractionDigits(2);
        }
        return formatPrice;
    }

    protected void inserisciEntrata() {


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

        Scarico c = new Scarico();
        try {
            c.setIdScarico(DBManager.getIstanceSingleton().getNewID("ordini", "idordine"));
            c.setIdCliente((new Integer(cmbClienti.getIDSelectedItem())).intValue());
            c
                    .setDataScarico(new java.sql.Date(dataDocumento.getDate()
                            .getTime()));
            c.setOraScarico((new Time(dataDocumento.getDate().getTime())));
            c.setNote(txtNote.getText());
            c.setDataDocumento(new java.sql.Date(dataDocumento.getDate()
                    .getTime()));
            c.setNumDocumento(txtNumDocumento.getText());
            c.setIdDocumento((new Integer(cmbTipoDocumento.getIDSelectedItem())).intValue());
            Object t=txtTotale.getValue();
            if(t instanceof Double)
                c.setTotaleDocumentoIvato(((Double)txtTotale.getValue()).doubleValue());
            else{
                long tmp=((Long)txtTotale.getValue()).longValue();
                c.setTotaleDocumentoIvato(new Double(tmp).doubleValue());
            }

            c.setIdDocumento((new Integer(cmbTipoDocumento.getIDSelectedItem())).intValue());
            c.setIvaDocumento(((Long)txtIvaDocumento.getValue()).intValue());
            c.insertScarico();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

	/**
	 * This method initializes jScrollPane2
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jScrollPane2.setViewportView(getTblUscite());
		}
		return jScrollPane2;
	}



	/**
	 * This method initializes tblUscite
	 *
	 * @return org.jdesktop.swingx.JXTable
	 */
	private JXTable getTblUscite() {
		if (tblUscite == null) {
			 try {
	                modelloUscite = new PrimaNotaModelUscite();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            DBManager.getIstanceSingleton().addDBStateChange(modelloUscite);
			tblUscite = new JXTable(modelloUscite);
			tblUscite.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblUscite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tblUscite;
	}

	/**
	 * This method initializes pnlNord1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord1() {
		if (pnlNord1 == null) {
			lblIvaDocumento = new JLabel();
			lblIvaDocumento.setBounds(new Rectangle(5, 95, 91, 21));
			lblIvaDocumento.setText("Iva Documento");
			lblNumDocumento = new JLabel();
			lblNumDocumento.setBounds(new Rectangle(6, 66, 91, 25));
			lblNumDocumento.setText("N° Documento");
			lblTot = new JLabel();
			lblTot.setBounds(new Rectangle(192, 66, 43, 25));
			lblTot.setText("Totale");
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(6, 36, 43, 25));
			lblCliente.setText("Cliente");
			lblTipoDocumento = new JLabel();
			lblTipoDocumento.setBounds(new Rectangle(234, 6, 97, 25));
			lblTipoDocumento.setText("Tipo Documento");
			lblTipoDocumento.setHorizontalAlignment(2);
			lblDataDocumento = new JLabel();
			lblDataDocumento.setBounds(new Rectangle(6, 6, 103, 25));
			lblDataDocumento.setText("Data Documento");
			pnlNord1 = new JPanel();
			pnlNord1.setLayout(null);
			pnlNord1.setPreferredSize(new Dimension(0, 130));
			pnlNord1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlNord1.add(getDataDocumento(), null);
			pnlNord1.add(lblDataDocumento, null);
			pnlNord1.add(lblTipoDocumento, null);
			pnlNord1.add(getCmbTipoDocumento(), null);
			pnlNord1.add(lblCliente, null);
			pnlNord1.add(getCmbClienti(), null);
			pnlNord1.add(getPnlNote(), null);
			pnlNord1.add(lblTot, null);
			pnlNord1.add(getTxtTotale(), null);
			pnlNord1.add(lblNumDocumento, null);
			pnlNord1.add(getTxtNumDocumento(), null);
			pnlNord1.add(lblIvaDocumento, null);
			pnlNord1.add(getTxtIvaDocumento(), null);
		}
		return pnlNord1;
	}

	/**
	 * This method initializes dataDocumento
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataDocumento() {
		if (dataDocumento == null) {
			dataDocumento = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataDocumento.setBounds(new Rectangle(114, 6, 115, 25));
			dataDocumento.setDate(new Date());
		}
		return dataDocumento;
	}

	/**
	 * This method initializes cmbTipoDocumento
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbTipoDocumento() {
		if (cmbTipoDocumento == null) {
			cmbTipoDocumento = new IDJComboBox();
			cmbTipoDocumento.setBounds(new Rectangle(336, 6, 347, 25));
		}
		return cmbTipoDocumento;
	}

	/**
	 * This method initializes cmbClienti
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbClienti() {
		if (cmbClienti == null) {
			cmbClienti = new IDJComboBox();
			cmbClienti.setBounds(new Rectangle(54, 36, 283, 25));
		}
		return cmbClienti;
	}

	/**
	 * This method initializes pnlNote
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNote() {
		if (pnlNote == null) {
			pnlNote = new JPanel();
			pnlNote.setLayout(new BorderLayout());
			pnlNote.setBounds(new Rectangle(342, 36, 343, 79));
			pnlNote.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlNote.add(getJScrollPane1(), java.awt.BorderLayout.CENTER);
		}
		return pnlNote;
	}

	/**
	 * This method initializes jScrollPane1
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTxtNote());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes txtNote
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote() {
		if (txtNote == null) {
			txtNote = new JTextArea();
		}
		return txtNote;
	}

	/**
	 * This method initializes formatPrice1
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getFormatPrice1() {
		if (formatPrice1 == null) {
			formatPrice1 = new DecimalFormat();
			formatPrice1.setMinimumFractionDigits(2);
			formatPrice1.setMaximumFractionDigits(2);
		}
		return formatPrice1;
	}

	/**
	 * This method initializes txtTotale
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtTotale() {
		if (txtTotale == null) {
			txtTotale = new JFormattedTextField(getFormatPrice1());
			txtTotale.setBounds(new Rectangle(240, 66, 97, 25));
			txtTotale.setDocument(new UpperTextDocument());
			txtTotale.setHorizontalAlignment(JTextField.RIGHT);
			txtTotale.setValue(new Double(0));
			txtTotale.setPreferredSize(new Dimension(100, 20));
		}
		return txtTotale;
	}

	/**
	 * This method initializes txtNumDocumento
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumDocumento() {
		if (txtNumDocumento == null) {
			txtNumDocumento = new JTextField();
			txtNumDocumento.setBounds(new Rectangle(102, 66, 85, 25));
		}
		return txtNumDocumento;
	}

	/**
	 * This method initializes pnlNord11
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNord11() {
		if (pnlNord11 == null) {
			lblNumDocumento1 = new JLabel();
			lblNumDocumento1.setBounds(new Rectangle(6, 66, 91, 25));
			lblNumDocumento1.setText("N° Documento");
			lblTot1 = new JLabel();
			lblTot1.setBounds(new Rectangle(192, 66, 43, 25));
			lblTot1.setText("Totale");
			lblCliente1 = new JLabel();
			lblCliente1.setBounds(new Rectangle(6, 36, 61, 25));
			lblCliente1.setText("Fornitore");
			lblTipoDocumento1 = new JLabel();
			lblTipoDocumento1.setBounds(new Rectangle(234, 6, 97, 25));
			lblTipoDocumento1.setText("Tipo Documento");
			lblTipoDocumento1.setHorizontalAlignment(2);
			lblDataDocumento1 = new JLabel();
			lblDataDocumento1.setBounds(new Rectangle(6, 6, 103, 25));
			lblDataDocumento1.setText("Data Documento");
			pnlNord11 = new JPanel();
			pnlNord11.setLayout(null);
			pnlNord11.setPreferredSize(new Dimension(0, 120));
			pnlNord11.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlNord11.add(getDataDocumento1(), null);
			pnlNord11.add(lblDataDocumento1, null);
			pnlNord11.add(lblTipoDocumento1, null);
			pnlNord11.add(getCmbTipoDocumento1(), null);
			pnlNord11.add(lblCliente1, null);
			pnlNord11.add(getCmbClienti1(), null);
			pnlNord11.add(getPnlNote1(), null);
			pnlNord11.add(lblTot1, null);
			pnlNord11.add(getTxtTotale1(), null);
			pnlNord11.add(lblNumDocumento1, null);
			pnlNord11.add(getTxtNumDocumento1(), null);
		}
		return pnlNord11;
	}

	/**
	 * This method initializes dataDocumento1
	 *
	 * @return com.toedter.calendar.JDateChooser
	 */
	private JDateChooser getDataDocumento1() {
		if (dataDocumento1 == null) {
			dataDocumento1 = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
			dataDocumento1.setBounds(new Rectangle(114, 6, 115, 25));
			dataDocumento1.setDate(new Date());
		}
		return dataDocumento1;
	}

	/**
	 * This method initializes cmbTipoDocumento1
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbTipoDocumento1() {
		if (cmbTipoDocumento1 == null) {
			cmbTipoDocumento1 = new IDJComboBox();
			cmbTipoDocumento1.setBounds(new Rectangle(336, 6, 347, 25));
		}
		return cmbTipoDocumento1;
	}

	/**
	 * This method initializes cmbClienti1
	 *
	 * @return rf.myswing.IDJComboBox
	 */
	private IDJComboBox getCmbClienti1() {
		if (cmbClienti1 == null) {
			cmbClienti1 = new IDJComboBox();
			cmbClienti1.setBounds(new Rectangle(73, 35, 308, 25));
		}
		return cmbClienti1;
	}

	/**
	 * This method initializes pnlNote1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlNote1() {
		if (pnlNote1 == null) {
			pnlNote1 = new JPanel();
			pnlNote1.setLayout(new BorderLayout());
			pnlNote1.setBounds(new Rectangle(388, 36, 297, 79));
			pnlNote1.setBorder(BorderFactory.createTitledBorder(null, "Note", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlNote1.add(getJScrollPane11(), java.awt.BorderLayout.CENTER);
		}
		return pnlNote1;
	}

	/**
	 * This method initializes jScrollPane11
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane11() {
		if (jScrollPane11 == null) {
			jScrollPane11 = new JScrollPane();
			jScrollPane11.setViewportView(getTxtNote1());
		}
		return jScrollPane11;
	}

	/**
	 * This method initializes txtNote1
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtNote1() {
		if (txtNote1 == null) {
			txtNote1 = new JTextArea();
		}
		return txtNote1;
	}

	/**
	 * This method initializes formatPrice11
	 *
	 * @return java.text.DecimalFormat
	 */
	private DecimalFormat getFormatPrice11() {
		if (formatPrice11 == null) {
			formatPrice11 = new DecimalFormat();
			formatPrice11.setMinimumFractionDigits(2);
			formatPrice11.setMaximumFractionDigits(2);
		}
		return formatPrice11;
	}

	/**
	 * This method initializes txtTotale1
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtTotale1() {
		if (txtTotale1 == null) {
			txtTotale1 = new JFormattedTextField(getFormatPrice11());
			txtTotale1.setBounds(new Rectangle(240, 66, 97, 25));
			txtTotale1.setDocument(new UpperTextDocument());
			txtTotale1.setHorizontalAlignment(JTextField.RIGHT);
			txtTotale1.setValue(new Double(0));
			txtTotale1.setPreferredSize(new Dimension(100, 20));
		}
		return txtTotale1;
	}

	/**
	 * This method initializes txtNumDocumento1
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumDocumento1() {
		if (txtNumDocumento1 == null) {
			txtNumDocumento1 = new JTextField();
			txtNumDocumento1.setBounds(new Rectangle(102, 66, 85, 25));
		}
		return txtNumDocumento1;
	}

	private void calcolaTotaliEntrate() {
		double imponibile = 0, imposta = 0, tot = 0;

		// Calcoliamo ora la parte totale dello scarico di tutti gli articoli.
		try {

			imponibile = Scarico.getTotAcquistoImponibileAllOrders();
			imposta = Scarico.getTotAcquistoImpostaAllOrders();
			tot = imponibile + imposta;

			// impostiamo i campi
			txtImponibileIng.setText(ControlloDati
					.convertDoubleToPrezzo(imponibile));
			txtImpostaIng.setText(ControlloDati.convertDoubleToPrezzo(imposta));
			txtTotaleIng.setText(ControlloDati.convertDoubleToPrezzo(tot));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"Probabile errore nei calcoli all'ingrosso", "ERRORE",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}


	/**
	 * This method initializes pnlSud1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlSud1() {
		if (pnlSud1 == null) {
			lblTotIng = new JLabel();
			lblTotIng.setBounds(new Rectangle(424, 8, 85, 25));
			lblTotIng.setText("Totale \u20ac.");
			lblIngImposta = new JLabel();
			lblIngImposta.setBounds(new Rectangle(236, 8, 89, 25));
			lblIngImposta.setText("Imposta \u20ac.");
			lblIngImponibile = new JLabel();
			lblIngImponibile.setBounds(new Rectangle(32, 8, 105, 25));
			lblIngImponibile.setText("Imponibile \u20ac.");
			pnlSud1 = new JPanel();
			pnlSud1.setLayout(null);
			pnlSud1.setPreferredSize(new Dimension(0, 40));
			pnlSud1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlSud1.add(lblIngImponibile, null);
			pnlSud1.add(getTxtImponibileIng(), null);
			pnlSud1.add(lblIngImposta, null);
			pnlSud1.add(getTxtImpostaIng(), null);
			pnlSud1.add(lblTotIng, null);
			pnlSud1.add(getTxtTotaleIng(), null);
		}
		return pnlSud1;
	}

	/**
	 * This method initializes txtImponibileIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImponibileIng() {
		if (txtImponibileIng == null) {
			txtImponibileIng = new JTextField();
			txtImponibileIng.setBounds(new Rectangle(136, 8, 93, 25));
			txtImponibileIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImponibileIng.setDisabledTextColor(Color.black);
			txtImponibileIng.setEditable(false);
			txtImponibileIng.setEnabled(false);
		}
		return txtImponibileIng;
	}

	/**
	 * This method initializes txtImpostaIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtImpostaIng() {
		if (txtImpostaIng == null) {
			txtImpostaIng = new JTextField();
			txtImpostaIng.setBounds(new Rectangle(324, 8, 93, 25));
			txtImpostaIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtImpostaIng.setDisabledTextColor(Color.black);
			txtImpostaIng.setEditable(false);
			txtImpostaIng.setEnabled(false);
		}
		return txtImpostaIng;
	}

	/**
	 * This method initializes txtTotaleIng
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtTotaleIng() {
		if (txtTotaleIng == null) {
			txtTotaleIng = new JTextField();
			txtTotaleIng.setBounds(new Rectangle(508, 8, 93, 25));
			txtTotaleIng.setFont(new Font("Dialog", Font.BOLD, 12));
			txtTotaleIng.setDisabledTextColor(Color.red);
			txtTotaleIng.setEditable(false);
			txtTotaleIng.setEnabled(false);
		}
		return txtTotaleIng;
	}

	/**
	 * This method initializes formatPrezzoDocumento
	 *
	 * @return java.text.DecimalFormat
	 */
	private NumberFormat getFormatPrezzoDocumento() {
		if (formatPrezzoDocumento == null) {
			formatPrezzoDocumento =NumberFormat.getInstance();
			formatPrezzoDocumento.setMinimumFractionDigits(0);
			formatPrezzoDocumento.setMaximumFractionDigits(0);
		}
		return formatPrezzoDocumento;
	}

	/**
	 * This method initializes txtIvaDocumento
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getTxtIvaDocumento() {
		if (txtIvaDocumento == null) {
			txtIvaDocumento = new JFormattedTextField(getFormatPrezzoDocumento());
			txtIvaDocumento.setBounds(new Rectangle(100, 95, 86, 21));
			txtIvaDocumento.setDocument(new UpperTextDocument());
			txtIvaDocumento.setHorizontalAlignment(JTextField.RIGHT);
			txtIvaDocumento.setValue(new Double(0));
			txtIvaDocumento.setPreferredSize(new Dimension(100, 20));
		}
		return txtIvaDocumento;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
