package rf.pegaso.gui.vendita;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.gui.vendita.panel.JNumberEvent;
import rf.pegaso.gui.vendita.panel.JNumberEventListener;
import rf.pegaso.gui.vendita.panel.JNumberKeys;
import rf.pegaso.gui.vendita.panel.JPanelRiepilogoVendita;
import rf.utility.db.DBManager;
import rf.utility.gui.UtilGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.Date;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class VenditaAvanzata extends JFrame{

	private static final long serialVersionUID = 1L;
	private DBManager dbm = null;
	private JPanel jContentPane = null;
	private JPanel pnlEst = null;
	private JPanel pnlEstCentro = null;
	private javax.swing.JLabel m_jPrice;
	private javax.swing.JLabel m_jPor;
	
	// Queste tre variabili sono usate per controllare lo stato dell'applicazione
    private int m_iNumberStatus;
    private int m_iNumberStatusInput;
    private int m_iNumberStatusPor;
    // Questa variabile viene utilizzata come buffer dei caratteri inseriti
	private StringBuffer m_sBarcode;
	
	// Variabili numeriche
    private final static int NUMBERZERO = 0;
    private final static int NUMBERVALID = 1;
    
    private final static int NUMBER_INPUTZERO = 0;
    private final static int NUMBER_INPUTZERODEC = 1;
    private final static int NUMBER_INPUTINT = 2;
    private final static int NUMBER_INPUTDEC = 3; 
    private final static int NUMBER_PORZERO = 4; 
    private final static int NUMBER_PORZERODEC = 5; 
    private final static int NUMBER_PORINT = 6; 
    private final static int NUMBER_PORDEC = 7;
    
    private JPanelRiepilogoVendita pannelloRiepilogo = null;
    private JNumberKeys tastieraNumerica = null;
	private JButton btnCodBar = null; 
	
	public VenditaAvanzata(){
		dbm = DBManager.getIstanceSingleton();
		initialize();
	}
	
	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(){
		pannelloRiepilogo = new JPanelRiepilogoVendita();
		tastieraNumerica = new JNumberKeys();
		this.setSize(new Dimension(800, 600));
		this.setTitle("Vendita Avanzata");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Generated
		//this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				setEnabled(true);
			}
		});
		UtilGUI.centraFrame(this);
		stateToZero();
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
			jContentPane.add(getPnlEst(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	//evento generato dalla pressione di un tasto della tastiera numerica
	private void m_jNumberKeysKeyPerformed(JNumberEvent evt) {

		stateTransition(evt.getKey());

	}//GEN-LAST:event_m_jNumberKeysKeyPerformed
	
	//evento generato dalla pressione del bottono btnCodBar
	private void m_jEnterActionPerformed(java.awt.event.ActionEvent evt) {

        stateTransition('\n');

    }
	
	//inizializza o resetta le variabili iniziali di sistema
	 private void stateToZero(){
		 m_jPor.setText("");
		 m_jPrice.setText("");
		 m_sBarcode = new StringBuffer();

		 m_iNumberStatus = NUMBER_INPUTZERO;
		 m_iNumberStatusInput = NUMBERZERO;
		 m_iNumberStatusPor = NUMBERZERO;
	 }
	 
	private void stateTransition(char cTrans) {

        if (cTrans == '\n') {
            // Effettua la ricerca tramite codice a barre
            if (m_sBarcode.length() > 0) {            
                String sCode = m_sBarcode.toString();
                DettaglioOrdine dv = new DettaglioOrdine();
                dv.loadByCB(sCode);
                pannelloRiepilogo.addDettaglioOrdine(dv);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        } else {
            // otro caracter
            // Esto es para el codigo de barras...
            m_sBarcode.append(cTrans);

            // Pressione del tasto CE
            if (cTrans == '\u007f') { 
                stateToZero();

            } else if ((cTrans == '0') 
                    && (m_iNumberStatus == NUMBER_INPUTZERO)) {
                m_jPrice.setText("0");            
            } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                    && (m_iNumberStatus == NUMBER_INPUTZERO)) { 
                // Un numero entero
                m_jPrice.setText(Character.toString(cTrans));
                m_iNumberStatus = NUMBER_INPUTINT;    
                m_iNumberStatusInput = NUMBERVALID;
            } else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                       && (m_iNumberStatus == NUMBER_INPUTINT)) { 
                // Un numero entero
                m_jPrice.setText(m_jPrice.getText() + cTrans);

            } else if (cTrans == '.' && m_iNumberStatus == NUMBER_INPUTZERO) {
                m_jPrice.setText("0.");
                m_iNumberStatus = NUMBER_INPUTZERODEC;            
            } else if (cTrans == '.' && m_iNumberStatus == NUMBER_INPUTINT) {
                m_jPrice.setText(m_jPrice.getText() + ".");
                m_iNumberStatus = NUMBER_INPUTDEC;

            } else if ((cTrans == '0')
                       && (m_iNumberStatus == NUMBER_INPUTZERODEC || m_iNumberStatus == NUMBER_INPUTDEC)) { 
                // Un numero decimal
                m_jPrice.setText(m_jPrice.getText() + cTrans);
            } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                       && (m_iNumberStatus == NUMBER_INPUTZERODEC || m_iNumberStatus == NUMBER_INPUTDEC)) { 
                // Un numero decimal
                m_jPrice.setText(m_jPrice.getText() + cTrans);
                m_iNumberStatus = NUMBER_INPUTDEC;
                m_iNumberStatusInput = NUMBERVALID;

            } else if (cTrans == '*' 
                    && (m_iNumberStatus == NUMBER_INPUTINT || m_iNumberStatus == NUMBER_INPUTDEC)) {
                m_jPor.setText("x");
                m_iNumberStatus = NUMBER_PORZERO;            
            } else if (cTrans == '*' 
                    && (m_iNumberStatus == NUMBER_INPUTZERO || m_iNumberStatus == NUMBER_INPUTZERODEC)) {
                m_jPrice.setText("0");
                m_jPor.setText("x");
                m_iNumberStatus = NUMBER_PORZERO;       

            } else if ((cTrans == '0') 
                    && (m_iNumberStatus == NUMBER_PORZERO)) {
                m_jPor.setText("x0");            
            } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                    && (m_iNumberStatus == NUMBER_PORZERO)) { 
                // Un numero entero
                m_jPor.setText("x" + Character.toString(cTrans));
                m_iNumberStatus = NUMBER_PORINT;            
                m_iNumberStatusPor = NUMBERVALID;
            } else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                       && (m_iNumberStatus == NUMBER_PORINT)) { 
                // Un numero entero
                m_jPor.setText(m_jPor.getText() + cTrans);

            } else if (cTrans == '.' && m_iNumberStatus == NUMBER_PORZERO) {
                m_jPor.setText("x0.");
                m_iNumberStatus = NUMBER_PORZERODEC;            
            } else if (cTrans == '.' && m_iNumberStatus == NUMBER_PORINT) {
                m_jPor.setText(m_jPor.getText() + ".");
                m_iNumberStatus = NUMBER_PORDEC;

            } else if ((cTrans == '0')
                       && (m_iNumberStatus == NUMBER_PORZERODEC || m_iNumberStatus == NUMBER_PORDEC)) { 
                // Un numero decimal
                m_jPor.setText(m_jPor.getText() + cTrans);
            } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
                       && (m_iNumberStatus == NUMBER_PORZERODEC || m_iNumberStatus == NUMBER_PORDEC)) { 
                // Un numero decimal
                m_jPor.setText(m_jPor.getText() + cTrans);
                m_iNumberStatus = NUMBER_PORDEC;            
                m_iNumberStatusPor = NUMBERVALID;  
            
            } /*else if (cTrans == '\u00a7' 
                    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO) {
                // Scale button pressed and a number typed as a price
                if (m_App.getDeviceScale().existsScale() && m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits")) {
                    try {
                        Double value = m_App.getDeviceScale().readWeight();
                        if (value != null) {
                            addTicketLine(getInputProduct(), value.doubleValue(), includeTaxes(getInputValue()));
                        }
                    } catch (ScaleException e) {
                        Toolkit.getDefaultToolkit().beep();
                        new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noweight"), e).show(this);           
                        stateToZero(); 
                    }
                } else {
                    // No existe la balanza;
                    Toolkit.getDefaultToolkit().beep();
                }
            } else if (cTrans == '\u00a7' 
                    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
                // Scale button pressed and no number typed.
                int i = m_ticketlines.getSelectedIndex();
                if (i < 0){
                    Toolkit.getDefaultToolkit().beep();
                } else if (m_App.getDeviceScale().existsScale()) {
                    try {
                        Double value = m_App.getDeviceScale().readWeight();
                        if (value != null) {
                            TicketLineInfo oLine = m_oTicket.getLine(i);
                            oLine.setMultiply(value.doubleValue());
                            oLine.setPrice(Math.abs(oLine.getPrice()));
                            paintTicketLine(i, oLine);
                        }
                    } catch (ScaleException e) {
                        // Error de pesada.
                        Toolkit.getDefaultToolkit().beep();
                        new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noweight"), e).show(this);           
                        stateToZero(); 
                    }
                } else {
                    // No existe la balanza;
                    Toolkit.getDefaultToolkit().beep();
                }      
                
            
            }*/ 
            // Aumenta la qta di 1 del prodotto selezionato nel riepilogo
            // Prerequisiti: Le 2 label devono essere vuote
            else if (cTrans == '+' 
                    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
            	//ritorna l'indice della riga selezionata
                int i = pannelloRiepilogo.getIdSelectedItem();
                if (i < 0){
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    //carica il dettaglioOrdine attraverso la riga seleziona e modifica la qta
                	pannelloRiepilogo.aggiornaQtaSelectedItem(1);
                }

             // Diminuisce la qta di 1 del prodotto selezionato nel riepilogo
                // Prerequisiti: Le 2 label devono essere vuote
            } else if (cTrans == '-' 
                    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
            	//ritorna l'indice della riga selezionata
            	int i = pannelloRiepilogo.getIdSelectedItem();
                if (i < 0){
                    Toolkit.getDefaultToolkit().beep();
                } else {
                	//carica il dettaglioOrdine attraverso la riga seleziona e modifica la qta
                    pannelloRiepilogo.aggiornaQtaSelectedItem(-1);
                }

            // Incrementa la qta della riga selezionata nel riepilogo
            //    con il valore della 2° label
            } else if (cTrans == '+' 
                    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERVALID) {
                int i = pannelloRiepilogo.getIdSelectedItem();
                if (i < 0){
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    pannelloRiepilogo.aggiornaQtaSelectedItem(Integer.valueOf(m_jPor.getText()));
                }

            // Decrementa la qta della riga selezionata nel riepilogo
            // con la qta della seconda label
            } else if (cTrans == '-' 
                    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERVALID) {
                int i = pannelloRiepilogo.getIdSelectedItem();
                if (i < 0){
                    Toolkit.getDefaultToolkit().beep();
                } else {
                	pannelloRiepilogo.aggiornaQtaSelectedItem(-Integer.valueOf(m_jPor.getText()));
                }

            // Aggiunge una nuova riga nel ripilogo effettuando il controllo sui permessi
            }/* else if (cTrans == '+' 
                    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO
                    && m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits")) {
                addTicketLine(getInputProduct(), 1.0, includeTaxes(getInputValue()));

            //  // Aggiunge una nuova riga di storno nel ripilogo effettuando il controllo sui permessi
            } else if (cTrans == '-' 
                    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO
                    && m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits")) {
                addTicketLine(getInputProduct(), 1.0, -includeTaxes(getInputValue()));

            // Aggiunge una nuova riga con qta > di 1 nel ripilogo effettuando il controllo sui permessi
            } else if (cTrans == '+' 
                    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERVALID
                    && m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits")) {
                addTicketLine(getInputProduct(), getPorValue(), includeTaxes(getInputValue()));

            // Aggiunge una nuova riga di storno con qta > di 1 nel ripilogo effettuando il controllo sui permessi
            } else if (cTrans == '-' 
                    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERVALID
                    && m_App.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits")) {
                addTicketLine(getInputProduct(), getPorValue(), -includeTaxes(getInputValue()));

            // Calcola il totale e visualizza la finestra di pagamento.
            } else if (cTrans == ' ' || cTrans == '=') {
                if (m_oTicket.getLinesCount() > 0) {
                    
                    // Muestro el total
                    printTicket("Printer.TicketTotal");
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            // Elijo el pago, se asigna al ticket y devuelve el recurso de impresora seleccionado
                            String sresourcename = JPaymentSelect.showMessage(JPanelTicket.this, m_App, m_oTicket);

                            if (sresourcename != null) { // Han pulsado aceptar.

                                // Asigno los valores definitivos del ticket...
                                m_oTicket.setUser(m_App.getAppUserView().getUser().getUserInfo()); // El usuario que lo cobra
                                m_oTicket.setActiveCash(m_App.getActiveCashIndex());
                                m_oTicket.setDate(new Date()); // Le pongo la fecha de cobro

                                // Save the receipt and assign a receipt number
                                m_ticketsbag.saveTicket();
                                
                                // Print receipt.
                                printTicket(sresourcename);
                                
                                // Cancel edition of current receipt
                                m_ticketsbag.cancelTicket();                        
                            }
                        }
                    });
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }*/
        }
    }

	/**
	 * This method initializes pnlEst	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlEst() {
		if (pnlEst == null) {
			pnlEst = new JPanel();
			pnlEst.setLayout(new BorderLayout());
			pnlEst.setPreferredSize(new java.awt.Dimension(250, 578));
			tastieraNumerica.addJNumberEventListener(new JNumberEventListener() {
	            public void keyPerformed(JNumberEvent evt) {
	                m_jNumberKeysKeyPerformed(evt);
	            }
	        });
			pnlEst.add(tastieraNumerica, BorderLayout.NORTH);
			pnlEst.add(getPnlEstCentro(), BorderLayout.CENTER);
			pnlEst.add(pannelloRiepilogo, BorderLayout.SOUTH);
		}
		return pnlEst;
	}

	/**
	 * This method initializes pnlEstCentro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlEstCentro() {
		if (pnlEstCentro == null) {
			pnlEstCentro = new JPanel();
			pnlEstCentro.setLayout(null);
			//pnlEstCentro.setPreferredSize(new Dimension(0, 90));
			m_jPrice = new javax.swing.JLabel();
			m_jPrice.setBackground(java.awt.Color.white);
			m_jPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			m_jPrice.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
			m_jPrice.setOpaque(true);
			m_jPrice.setBounds(new Rectangle(10, 10, 80, 22));
			m_jPrice.setRequestFocusEnabled(false);
			m_jPor = new javax.swing.JLabel();
			m_jPor.setBackground(java.awt.Color.white);
			m_jPor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			m_jPor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
			m_jPor.setOpaque(true);
			//m_jPor.setPreferredSize(new java.awt.Dimension(22, 22));
			m_jPor.setBounds(new Rectangle(110, 10, 40, 22));
			m_jPor.setRequestFocusEnabled(false);
			pnlEstCentro.add(m_jPrice, null);
			pnlEstCentro.add(m_jPor, null);
			pnlEstCentro.add(getBtnCodBar(), null);
		}
		return pnlEstCentro;
	}

	/**
	 * This method initializes btnCodBar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCodBar() {
		if (btnCodBar == null) {
			btnCodBar = new JButton();
			String userDir = System.getProperty("user.dir");
			//btnCodBar.setIcon(new ImageIcon(userDir+File.separator+"\\resource\\calc\\barcode.png"));
			
			btnCodBar.setIcon(new ImageIcon("resource/calc/barcode.png"));
			btnCodBar.setFocusPainted(false);
			btnCodBar.setFocusable(false);
			btnCodBar.setRequestFocusEnabled(false);
			btnCodBar.setBounds(new Rectangle(180, 8, 50, 26));
	        btnCodBar.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                m_jEnterActionPerformed(evt);
	            }
	        });
		}
		return btnCodBar;
	}
}
