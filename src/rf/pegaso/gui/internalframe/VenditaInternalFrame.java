package rf.pegaso.gui.internalframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;



import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.gui.vendita.panel.JPanelRiepilogoVendita;
import rf.utility.ControlloDati;

import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VenditaInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlPulsantiFunzioni = null;
	private JButton btnRicercaAvanzata = null;
	private JTextField txtFieldRicerca = null;
	private JPanel pnlFunzioniCassa = null;
	private JPanelRiepilogoVendita pannelloCarrello = null;
	private JLabel lblCodiceProdotto = null;
	private JButton btnZero = null;
	private JButton btnUno = null;
	private JButton btnDue = null;
	private JButton btnTre = null;
	private JButton btnQuattro = null;
	private JButton btnCinque = null;
	private JButton btnSei = null;
	private JButton btnSette = null;
	private JButton btnOtto = null;
	private JButton btnNove = null;
	private JButton btnVirgola = null;
	private JButton btnDoppioZero = null;
	private JButton btnAzzera = null;
	private JButton btnMoltiplica = null;
	private JButton btnRep1 = null;
	private JButton btnRep2 = null;
	private JButton btnRep3 = null;
	private JButton btnRep4 = null;
	private JTextField txtFldImporto = null;
	private JLabel lblPer = null;
	private JTextField txtQta = null;
	
	private boolean tastieraCassaAttiva = false;
	private boolean inserimentoContanti = false;
	private JButton btnModifica = null;
	private JButton btnAnnullaVendita = null;
	private JButton btnStorno = null;
	private JButton btnInsManuale = null;
	private JPanel pnlContenitore = null;
	private JPanel pnlArticoli = null;
	private JTextField txtFldTotale = null;
	private JTextField txtFldContanti = null;
	private JTextField txtFldResto = null;
	private JButton jButton = null;
	private JButton btnElaboraScontrino = null;
	private JButton btnContanti = null;
	
	// Queste tre variabili sono usate per controllare lo stato dell'applicazione
    private int m_iNumberStatus;
    private int m_iNumberStatusInput;
	
	// Variabili numeriche
    private final static int NUMBERZERO = 0;
    private final static int NUMBERVALID = 1;
    
    private final static int NUMBER_INPUTZERO = 0;
    private final static int NUMBER_INPUTZERODEC = 1;
    private final static int NUMBER_INPUTINT = 2;
    private final static int NUMBER_INPUTDEC = 3;
    
    // Variabile che contiene l'importo digitato da tastiera
    private String importo = "";

	public VenditaInternalFrame(JFrame padre) {
		initialize();
	}

	private void initialize() {
		initializeKeyFunction();
		initializeCarrello();
		this.setSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setTitle("Vendita al Banco");
		this.setMaximizable(true);
		this.setClosable(true);
		this.setContentPane(getJContentPane());
	}
	
	private void initializeCarrello(){
		pannelloCarrello = new JPanelRiepilogoVendita();
		pannelloCarrello.setPreferredSize(new Dimension(575, 400));
		pannelloCarrello.setVisible(true);
//		scarico = new Scarico();
	}
	
	
	
	
	
	private void inserisciDaRepo(String repo){
		try {
			DettaglioOrdine dv = new DettaglioOrdine();
			dv.setDescrizione(repo);
			dv.setPrezzoVendita(ControlloDati.convertPrezzoToDouble(txtFldImporto.getText()));
			if ( txtQta.getText().trim().equals("")){
				dv.setQta(1);
			}
			else{
				dv.setQta(Double.valueOf(txtQta.getText().trim()));
			}
			dv.setIva(20);
			pannelloCarrello.addDettaglioOrdine(dv);
			txtFldTotale.setText(ControlloDati.convertDoubleToPrezzo(pannelloCarrello.getTotaleCarrello()));
			stateToZero();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void inserisciNelCarrello(String codeBarre){
		if ( txtFieldRicerca.getText() == null || txtFieldRicerca.getText().trim().equals("") ){
			// Scrivere messaggio campo codice prodotto non valido
		}
		else{
			DettaglioOrdine dv = new DettaglioOrdine();
			int esito = dv.loadByCB(codeBarre);
			if ( esito == 1 ){
				pannelloCarrello.addDettaglioOrdine(dv);
			}
			else{
				messaggioAVideo("Articolo non disponibile", "INFO");
			}
		}
	}
	
	private void elaboraScontrino(){
		if ( pannelloCarrello.registraScarico() ){
			messaggioAVideo("Vendita effettuata con successo", "INFO");
			resetGUI();
		}
		else{
			messaggioAVideo("Si e' verificato un errore durante la registrazione della vendita. Si prega di riprovare", "ERROR");
		}
	}
	
	//inizializza o resetta le variabili iniziali di sistema
	 private void stateToZero(){
		 txtQta.setText("");
		 txtFldImporto.setText("");
		 txtFldContanti.setText("");
		 txtFldResto.setText("");
		 m_iNumberStatus = NUMBER_INPUTZERO;
		 m_iNumberStatusInput = NUMBERZERO;
	 }
	 
	private void stateTransition(char cTrans) {
		try {
			// Il primo numero inserito e' zero
			if ((cTrans == '0') 
					&& (m_iNumberStatus == NUMBER_INPUTZERO)) {
				importo = "0";
//				if ( inserimentoContanti ){
//					txtFldContanti.setText("0");
//					double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//					txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//				}
//				else{
//					txtFldImporto.setText("0");
//				}
			} 
			// Il primo numero inserito e' diverso da zero, si setta lo stato parte iniziale intera (m_iNumberStatus = NUMBER_INPUTINT;) 
			else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
					&& (m_iNumberStatus == NUMBER_INPUTZERO)) { 
				// Un numero intero
				importo = Character.toString(cTrans);
//				if ( inserimentoContanti ){
//					txtFldContanti.setText(Character.toString(cTrans));
//					double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//					txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//				}
//				else{
//					txtFldImporto.setText(Character.toString(cTrans));
//				}
				m_iNumberStatus = NUMBER_INPUTINT;    
				m_iNumberStatusInput = NUMBERVALID;
			}
			// Se la parte iniziale e' intera, si accoda il nuovo numero inserito
			else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
					&& (m_iNumberStatus == NUMBER_INPUTINT)) { 
				// Un numero intero
				importo = importo + cTrans;
//				if ( inserimentoContanti ){
//					txtFldContanti.setText(txtFldContanti.getText() + cTrans);
//					double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//					txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//				}
//				else{
//					txtFldImporto.setText(txtFldImporto.getText() + cTrans);
//				}
			} 
			// Se il primo carattere digitato e' la virgola, si inserisce lo zero in testa, e si setta lo stato in zero iniziale con parte decimale
			else if (cTrans == ',' && m_iNumberStatus == NUMBER_INPUTZERO) {
				importo = "0,";
//				if ( inserimentoContanti ){
//					txtFldContanti.setText("0,");
//					double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//					txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//				}
//				else{
//					txtFldImporto.setText("0,");
//				}
				m_iNumberStatus = NUMBER_INPUTZERODEC;            
			} 
			// Se sono stati digitati dei numeri iniziali e si inserisce la virgola, lo stato cambia in intero iniziale e parte decimale
			else if (cTrans == ',' && m_iNumberStatus == NUMBER_INPUTINT) {
				importo = importo + ",";
//				if ( inserimentoContanti ){
//					txtFldContanti.setText(txtFldContanti.getText() + ",");
//					double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//					txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//				}
//				else{
//					txtFldImporto.setText(txtFldImporto.getText() + ",");
//				}
				m_iNumberStatus = NUMBER_INPUTDEC;
			} 
			// Si aggiunge il numero selezionato nella parte decimale del numero se i numeri inseriti non sono pi� di 2		
			else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
					&& (m_iNumberStatus == NUMBER_INPUTZERODEC || m_iNumberStatus == NUMBER_INPUTDEC)) { 
				String decimalPart = importo.substring(importo.indexOf(",") + 1);
				if ( decimalPart.length() < 2 ){
					importo = importo + cTrans;
				}
//				// Un numero decimal
//				String decimalPart;
//				if ( inserimentoContanti ){
//					decimalPart = txtFldContanti.getText().substring(txtFldContanti.getText().indexOf(",") + 1);
//					if ( decimalPart.length() < 2 ){
//						txtFldContanti.setText(txtFldContanti.getText() + cTrans);
//						double resto = ((ControlloDati.convertPrezzoToDouble(txtFldContanti.getText())) - pannelloCarrello.getTotaleCarrello());
//						txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(resto));
//					}
//				}
//				else{
//					decimalPart = txtFldImporto.getText().substring(txtFldImporto.getText().indexOf(",") + 1);
//					if ( decimalPart.length() < 2 ){
//						txtFldImporto.setText(txtFldImporto.getText() + cTrans);
//					}
//				}
				m_iNumberStatus = NUMBER_INPUTDEC;
				m_iNumberStatusInput = NUMBERVALID;
			}
			double d = ControlloDati.convertPrezzoToDouble(importo);
			if ( inserimentoContanti ){				
				txtFldContanti.setText(ControlloDati.convertDoubleToPrezzo(d));
				txtFldResto.setText(ControlloDati.convertDoubleToPrezzo(d - pannelloCarrello.getTotaleCarrello()));
			}
			else {
				txtFldImporto.setText(ControlloDati.convertDoubleToPrezzo(d));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void resetGUI(){
		// Visualizziamo l'elenco degli articoli se era stato selezionato il pannello funzioni cassa
		((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlArticoli");
		btnInsManuale.setSelected(false);
		tastieraCassaAttiva = false;
		inserimentoContanti = false;
		txtFieldRicerca.setText("");
		txtFldContanti.setText("");
		txtFldImporto.setText("");
		txtFldResto.setText("");
		txtFldTotale.setText("");
		txtQta.setText("");
	}
	
	private void messaggioAVideo(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnAzzera ){
				stateToZero();
			}
			else if ( e.getSource() == btnMoltiplica ){
				if ( !inserimentoContanti ){
					int qta = Integer.valueOf(txtQta.getText().equals("") ? "1" : txtQta.getText());
					txtQta.setText(String.valueOf(qta+1));
				}
			}
			else if ( e.getSource() == btnVirgola ){
				stateTransition(',');
			}
			else if ( e.getSource() == btnZero ){
				stateTransition('0');
			}
			else if ( e.getSource() == btnDoppioZero ){
				stateTransition('0');
			}
			else if ( e.getSource() == btnUno ){
				stateTransition('1');
			}
			else if ( e.getSource() == btnDue ){
				stateTransition('2');
			}
			else if ( e.getSource() == btnTre ){
				stateTransition('3');
			}
			else if ( e.getSource() == btnQuattro ){
				stateTransition('4'); 
			}
			else if ( e.getSource() == btnCinque ){
				stateTransition('5');
			}
			else if ( e.getSource() == btnSei ){
				stateTransition('6');
			}
			else if ( e.getSource() == btnSette ){
				stateTransition('7');
			}
			else if ( e.getSource() == btnOtto ){
				stateTransition('8'); 
			}
			else if ( e.getSource() == btnNove ){
				stateTransition('9');
			}
			else if ( e.getSource() == btnRep1 ){
				inserisciDaRepo("Reparto 1");
			}
			else if ( e.getSource() == btnRep2 ){
				inserisciDaRepo("Reparto 2");
			}
			else if ( e.getSource() == btnRep3 ){
				inserisciDaRepo("Reparto 3");
			}
			else if ( e.getSource() == btnRep4 ){
				inserisciDaRepo("Reparto 4");
			}
			else if ( e.getSource() == btnInsManuale ){
				if ( tastieraCassaAttiva ){
					((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlArticoli");
					btnInsManuale.setSelected(false);
					tastieraCassaAttiva = false;
					stateToZero();
				}
				else{
					((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlFunzioniCassa");
					btnInsManuale.setSelected(true);
					tastieraCassaAttiva = true;
					inserimentoContanti = false;
				}
			}
			else if ( e.getSource() == btnElaboraScontrino ){
				// Registriamo la vendita
				elaboraScontrino();
			}
			else if ( e.getSource() == btnContanti ){
				if ( !tastieraCassaAttiva ){
					((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlFunzioniCassa");
					btnInsManuale.setSelected(false);
					tastieraCassaAttiva = true;
				}
				inserimentoContanti = true;				
			}
			else if ( e.getSource() == btnStorno ){
				pannelloCarrello.stornoArticolo();
				txtFldTotale.setText(ControlloDati.convertDoubleToPrezzo(pannelloCarrello.getTotaleCarrello()));
			}
			else if ( e.getSource() == btnAnnullaVendita ){
				pannelloCarrello.azzeraCarrello();
				stateToZero();
			}
		}
	}
	
	private void initializeKeyFunction(){
		// impostiamo la finestra per ascoltare i tasti funzione da F1 in su
		// ed altri pulsanti
		InputMap im = this.getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "F4");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "F5");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0), ",");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "0");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "1");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "2");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "3");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "4");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "5");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "6");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "7");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "9");

		this.getRootPane().getActionMap().put("F1", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				elaboraScontrino();
			}
		});
		this.getRootPane().getActionMap().put("F2", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Contanti - Visualizzare calcolatrice");
			}
		});
		this.getRootPane().getActionMap().put("F3", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisciDaRepo("Reparto 1");
			}
		});
		this.getRootPane().getActionMap().put("F4", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisciDaRepo("Reparto 1");
			}
		});
		this.getRootPane().getActionMap().put("F5", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisciDaRepo("Reparto 1");
			}
		});		
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				resetGUI();
				doDefaultCloseAction();
			}
		});
		this.getRootPane().getActionMap().put(",", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition(',');
			}
		});
		this.getRootPane().getActionMap().put("0", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('0');
			}
		});
		this.getRootPane().getActionMap().put("1", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('1');
			}
		});
		this.getRootPane().getActionMap().put("2", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('2');
			}
		});
		this.getRootPane().getActionMap().put("3", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('3');
			}
		});
		this.getRootPane().getActionMap().put("4", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('4');
			}
		});
		this.getRootPane().getActionMap().put("5", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('5');
			}
		});
		this.getRootPane().getActionMap().put("6", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('6');
			}
		});
		this.getRootPane().getActionMap().put("7", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('7');
			}
		});
		this.getRootPane().getActionMap().put("8", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('8');
			}
		});
		this.getRootPane().getActionMap().put("9", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				stateTransition('9');
			}
		});
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
			jContentPane.add(getPnlPulsantiFunzioni(), BorderLayout.NORTH);
			jContentPane.add(pannelloCarrello, BorderLayout.WEST);
//			jContentPane.add(getPnlRiepilogo(), BorderLayout.WEST);
//			jContentPane.add(getPnlFunzioniCassa(), BorderLayout.EAST);
			jContentPane.add(getPnlContenitore(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlPulsantiFunzioni
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlPulsantiFunzioni() {
		if (pnlPulsantiFunzioni == null) {
			lblCodiceProdotto = new JLabel();
			lblCodiceProdotto.setBounds(new Rectangle(33, 34, 125, 16));
			lblCodiceProdotto.setText("Codice Prodotto");
			pnlPulsantiFunzioni = new JPanel();
			pnlPulsantiFunzioni.setLayout(null);
			pnlPulsantiFunzioni.setPreferredSize(new Dimension(1000, 150));
			pnlPulsantiFunzioni.add(getBtnRicercaAvanzata(), null);
			pnlPulsantiFunzioni.add(getTxtFieldRicerca(), null);
			pnlPulsantiFunzioni.add(lblCodiceProdotto, null);
			pnlPulsantiFunzioni.add(getBtnInsManuale(), null);
			pnlPulsantiFunzioni.add(getTxtFldTotale(), null);
			pnlPulsantiFunzioni.add(getTxtFldContanti(), null);
			pnlPulsantiFunzioni.add(getTxtFldResto(), null);
			pnlPulsantiFunzioni.add(getBtnElaboraScontrino(), null);
			pnlPulsantiFunzioni.add(getBtnContanti(), null);
		}
		return pnlPulsantiFunzioni;
	}

	/**
	 * This method initializes btnRicercaAvanzata
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRicercaAvanzata() {
		if (btnRicercaAvanzata == null) {
			btnRicercaAvanzata = new JButton();
			btnRicercaAvanzata.setBounds(new Rectangle(270, 25, 130, 50));
			btnRicercaAvanzata.setText("<html>Ricerca"+"<P>"+"Avanzata</html>");
		}
		return btnRicercaAvanzata;
	}

	/**
	 * This method initializes txtFieldRicerca
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtFieldRicerca() {
		if (txtFieldRicerca == null) {
			txtFieldRicerca = new JTextField();
			txtFieldRicerca.setBounds(new Rectangle(30, 62, 182, 28));
			txtFieldRicerca.addActionListener(new MyButtonListener());
			txtFieldRicerca.requestFocus();
			txtFieldRicerca.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
						inserisciNelCarrello(txtFieldRicerca.getText());
						txtFieldRicerca.setText("");
					}
				}
			});
		}
		return txtFieldRicerca;
	}

	/**
	 * This method initializes pnlFunzioniCassa
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlFunzioniCassa() {
		if (pnlFunzioniCassa == null) {
			lblPer = new JLabel();
			lblPer.setBounds(new Rectangle(220, 104, 15, 16));
			lblPer.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			lblPer.setText("X");
			pnlFunzioniCassa = new JPanel();
			pnlFunzioniCassa.setLayout(null);
			pnlFunzioniCassa.setPreferredSize(new Dimension(400, 618));
			pnlFunzioniCassa.setName("pnlFunzioniCassa");
			pnlFunzioniCassa.add(getBtnZero(), null);
			pnlFunzioniCassa.add(getBtnUno(), null);
			pnlFunzioniCassa.add(getBtnDue(), null);
			pnlFunzioniCassa.add(getBtnTre(), null);
			pnlFunzioniCassa.add(getBtnQuattro(), null);
			pnlFunzioniCassa.add(getBtnCinque(), null);
			pnlFunzioniCassa.add(getBtnSei(), null);
			pnlFunzioniCassa.add(getBtnSette(), null);
			pnlFunzioniCassa.add(getBtnOtto(), null);
			pnlFunzioniCassa.add(getBtnNove(), null);
			pnlFunzioniCassa.add(getBtnVirgola(), null);
			pnlFunzioniCassa.add(getBtnDoppioZero(), null);
			pnlFunzioniCassa.add(getBtnAzzera(), null);
			pnlFunzioniCassa.add(getBtnMoltiplica(), null);
			pnlFunzioniCassa.add(getBtnRep1(), null);
			pnlFunzioniCassa.add(getBtnRep2(), null);
			pnlFunzioniCassa.add(getBtnRep3(), null);
			pnlFunzioniCassa.add(getBtnRep4(), null);
			pnlFunzioniCassa.add(getTxtFldImporto(), null);
			pnlFunzioniCassa.add(lblPer, null);
			pnlFunzioniCassa.add(getTxtQta(), null);
			pnlFunzioniCassa.add(getBtnModifica(), null);
			pnlFunzioniCassa.add(getBtnAnnullaVendita(), null);
			pnlFunzioniCassa.add(getBtnStorno(), null);
		}
		return pnlFunzioniCassa;
	}

	/**
	 * This method initializes btnZero
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnZero() {
		if (btnZero == null) {
			btnZero = new JButton();
			btnZero.setBounds(new Rectangle(145, 292, 45, 29));
			btnZero.setText("0");
			btnZero.addActionListener(new MyButtonListener());
		}
		return btnZero;
	}

	/**
	 * This method initializes btnUno
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnUno() {
		if (btnUno == null) {
			btnUno = new JButton();
			btnUno.setBounds(new Rectangle(90, 253, 45, 29));
			btnUno.setText("1");
			btnUno.addActionListener(new MyButtonListener());
		}
		return btnUno;
	}

	/**
	 * This method initializes btnDue
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDue() {
		if (btnDue == null) {
			btnDue = new JButton();
			btnDue.setBounds(new Rectangle(145, 253, 45, 29));
			btnDue.setText("2");
			btnDue.addActionListener(new MyButtonListener());
		}
		return btnDue;
	}

	/**
	 * This method initializes btnTre
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnTre() {
		if (btnTre == null) {
			btnTre = new JButton();
			btnTre.setBounds(new Rectangle(200, 253, 45, 29));
			btnTre.setText("3");
			btnTre.addActionListener(new MyButtonListener());
		}
		return btnTre;
	}

	/**
	 * This method initializes btnQuattro
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnQuattro() {
		if (btnQuattro == null) {
			btnQuattro = new JButton();
			btnQuattro.setBounds(new Rectangle(90, 214, 45, 29));
			btnQuattro.setText("4");
			btnQuattro.addActionListener(new MyButtonListener());
		}
		return btnQuattro;
	}

	/**
	 * This method initializes btnCinque
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnCinque() {
		if (btnCinque == null) {
			btnCinque = new JButton();
			btnCinque.setBounds(new Rectangle(145, 214, 45, 29));
			btnCinque.setText("5");
			btnCinque.addActionListener(new MyButtonListener());
		}
		return btnCinque;
	}

	/**
	 * This method initializes btnSei
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSei() {
		if (btnSei == null) {
			btnSei = new JButton();
			btnSei.setBounds(new Rectangle(200, 214, 45, 29));
			btnSei.setText("6");
			btnSei.addActionListener(new MyButtonListener());
		}
		return btnSei;
	}

	/**
	 * This method initializes btnSette
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSette() {
		if (btnSette == null) {
			btnSette = new JButton();
			btnSette.setBounds(new Rectangle(90, 175, 45, 29));
			btnSette.setText("7");
			btnSette.addActionListener(new MyButtonListener());
		}
		return btnSette;
	}

	/**
	 * This method initializes btnOtto
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnOtto() {
		if (btnOtto == null) {
			btnOtto = new JButton();
			btnOtto.setBounds(new Rectangle(145, 175, 45, 29));
			btnOtto.setText("8");
			btnOtto.addActionListener(new MyButtonListener());
		}
		return btnOtto;
	}

	/**
	 * This method initializes btnNove
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNove() {
		if (btnNove == null) {
			btnNove = new JButton();
			btnNove.setBounds(new Rectangle(200, 175, 45, 29));
			btnNove.setText("9");
			btnNove.addActionListener(new MyButtonListener());
		}
		return btnNove;
	}

	/**
	 * This method initializes btnVirgola
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnVirgola() {
		if (btnVirgola == null) {
			btnVirgola = new JButton();
			btnVirgola.setBounds(new Rectangle(90, 292, 45, 29));
			btnVirgola.setText(",");
			btnVirgola.addActionListener(new MyButtonListener());
		}
		return btnVirgola;
	}

	/**
	 * This method initializes btnDoppioZero
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDoppioZero() {
		if (btnDoppioZero == null) {
			btnDoppioZero = new JButton();
			btnDoppioZero.setBounds(new Rectangle(200, 292, 45, 29));
			btnDoppioZero.setText("00");
			btnDoppioZero.addActionListener(new MyButtonListener());
		}
		return btnDoppioZero;
	}

	/**
	 * This method initializes btnAzzera
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnAzzera() {
		if (btnAzzera == null) {
			btnAzzera = new JButton();
			btnAzzera.setBounds(new Rectangle(75, 136, 100, 29));
			btnAzzera.setText("AZZERA");
			btnAzzera.addActionListener(new MyButtonListener());
		}
		return btnAzzera;
	}

	/**
	 * This method initializes btnMoltiplica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnMoltiplica() {
		if (btnMoltiplica == null) {
			btnMoltiplica = new JButton();
			btnMoltiplica.setBounds(new Rectangle(200, 136, 45, 29));
			btnMoltiplica.setText("X");
			btnMoltiplica.addActionListener(new MyButtonListener());
		}
		return btnMoltiplica;
	}

	/**
	 * This method initializes btnRep1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRep1() {
		if (btnRep1 == null) {
			btnRep1 = new JButton();
			btnRep1.setBounds(new Rectangle(255, 292, 75, 29));
			btnRep1.setText("Repo1");
			btnRep1.addActionListener(new MyButtonListener());
		}
		return btnRep1;
	}

	/**
	 * This method initializes btnRep2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRep2() {
		if (btnRep2 == null) {
			btnRep2 = new JButton();
			btnRep2.setBounds(new Rectangle(255, 253, 75, 29));
			btnRep2.setText("Repo2");
			btnRep2.addActionListener(new MyButtonListener());
		}
		return btnRep2;
	}

	/**
	 * This method initializes btnRep3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRep3() {
		if (btnRep3 == null) {
			btnRep3 = new JButton();
			btnRep3.setBounds(new Rectangle(255, 214, 75, 29));
			btnRep3.setText("Repo3");
			btnRep3.addActionListener(new MyButtonListener());
		}
		return btnRep3;
	}

	/**
	 * This method initializes btnRep4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRep4() {
		if (btnRep4 == null) {
			btnRep4 = new JButton();
			btnRep4.setBounds(new Rectangle(255, 175, 75, 29));
			btnRep4.setText("Repo4");
			btnRep4.addActionListener(new MyButtonListener());
		}
		return btnRep4;
	}

	/**
	 * This method initializes txtFldImporto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldImporto() {
		if (txtFldImporto == null) {
			txtFldImporto = new JTextField();
			txtFldImporto.setBounds(new Rectangle(75, 98, 130, 28));
			txtFldImporto.setEditable(false);
		}
		return txtFldImporto;
	}

	/**
	 * This method initializes txtQta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtQta() {
		if (txtQta == null) {
			txtQta = new JTextField();
			txtQta.setBounds(new Rectangle(250, 98, 60, 28));
			txtQta.setEditable(false);
		}
		return txtQta;
	}

	/**
	 * This method initializes btnModifica	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnModifica() {
		if (btnModifica == null) {
			btnModifica = new JButton();
			btnModifica.setBounds(new Rectangle(0, 175, 85, 29));
			btnModifica.setText("Modifica");
		}
		return btnModifica;
	}

	/**
	 * This method initializes btnAnnullaVendita	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAnnullaVendita() {
		if (btnAnnullaVendita == null) {
			btnAnnullaVendita = new JButton();
			btnAnnullaVendita.setBounds(new Rectangle(0, 253, 85, 29));
			btnAnnullaVendita.setText("Annulla");
			btnAnnullaVendita.addActionListener(new MyButtonListener());
		}
		return btnAnnullaVendita;
	}

	/**
	 * This method initializes btnStorno	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnStorno() {
		if (btnStorno == null) {
			btnStorno = new JButton();
			btnStorno.setBounds(new Rectangle(0, 214, 85, 29));
			btnStorno.setText("Storno");
			btnStorno.addActionListener(new MyButtonListener());
		}
		return btnStorno;
	}

	/**
	 * This method initializes btnInsManuale	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnInsManuale() {
		if (btnInsManuale == null) {
			btnInsManuale = new JButton();
			btnInsManuale.setBounds(new Rectangle(270, 80, 130, 50));
			btnInsManuale.setText("<html>Inserimento<P>Manuale</html>");
			btnInsManuale.addActionListener(new MyButtonListener());
		}
		return btnInsManuale;
	}

	/**
	 * This method initializes pnlContenitore	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlContenitore() {
		if (pnlContenitore == null) {
			pnlContenitore = new JPanel();
			pnlContenitore.setLayout(new CardLayout());
			pnlContenitore.setPreferredSize(new Dimension(400, 618));
			pnlContenitore.add(getPnlArticoli(), getPnlArticoli().getName());
			pnlContenitore.add(getPnlFunzioniCassa(), getPnlFunzioniCassa().getName());
		}
		return pnlContenitore;
	}

	/**
	 * This method initializes pnlArticoli	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlArticoli() {
		if (pnlArticoli == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			pnlArticoli = new JPanel();
			pnlArticoli.setLayout(new GridBagLayout());
			pnlArticoli.setName("pnlArticoli");
			pnlArticoli.add(getJButton(), gridBagConstraints);
		}
		return pnlArticoli;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldTotale() {
		if (txtFldTotale == null) {
			txtFldTotale = new JTextField();
			txtFldTotale.setBounds(new Rectangle(590, 30, 170, 80));
			txtFldTotale.setOpaque(true);
			txtFldTotale.setBackground(Color.ORANGE);
			txtFldTotale.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totale �",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, null, null));
		}
		return txtFldTotale;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldContanti() {
		if (txtFldContanti == null) {
			txtFldContanti = new JTextField();
			txtFldContanti.setBounds(new Rectangle(760, 30, 170, 40));
			txtFldContanti.setOpaque(true);
			txtFldContanti.setBackground(Color.decode("435445"));
			txtFldContanti.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contanti �",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, null, null));
		}
		return txtFldContanti;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFldResto() {
		if (txtFldResto == null) {
			txtFldResto = new JTextField();
			txtFldResto.setBounds(new Rectangle(760, 70, 170, 40));
			txtFldResto.setBackground(Color.decode("314467"));
			txtFldResto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resto �",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, null, null));
		}
		return txtFldResto;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Ciao");
		}
		return jButton;
	}

	/**
	 * This method initializes btlElaboraScontrino	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnElaboraScontrino() {
		if (btnElaboraScontrino == null) {
			btnElaboraScontrino = new JButton();
			btnElaboraScontrino.setBounds(new Rectangle(420, 25, 130, 50));
			btnElaboraScontrino.setText("<html>Stampa (F1)<P>Scontrino</html>");
			btnElaboraScontrino.addActionListener(new MyButtonListener());
			
		}
		return btnElaboraScontrino;
	}

	/**
	 * This method initializes btnContanti	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnContanti() {
		if (btnContanti == null) {
			btnContanti = new JButton();
			btnContanti.setBounds(new Rectangle(420, 80, 130, 50));
			btnContanti.setText("Contanti (F2)");
			btnContanti.addActionListener(new MyButtonListener());
		}
		return btnContanti;
	}

}