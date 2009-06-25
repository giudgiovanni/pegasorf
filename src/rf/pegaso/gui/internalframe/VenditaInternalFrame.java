package rf.pegaso.gui.internalframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;


import rf.pegaso.db.tabelle.DettaglioOrdine;
import rf.pegaso.gui.vendita.panel.JPanelRiepilogoVendita;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;

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
	
	private Vector<DettaglioOrdine> carrelloSpesa = null;
	private JButton btnModifica = null;
	private JButton btnAnnullaVendita = null;
	private JButton btnStorno = null;
	private JButton btnInsManuale = null;
	private JButton btnReso = null;
	private JLabel lblTotale = null;
	private JLabel lblResto = null;
	private JLabel lblContanti = null;
	private JPanel pnlContenitore = null;
	private JPanel pnlArticoli = null;

	public VenditaInternalFrame(JFrame padre) {
		initialize();
	}

	private void initialize() {
		initializeKeyFunction();
		initializeCarrello();
		this.setSize(new Dimension(1000, 700)); // Generated
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // Generated
		this.setTitle("Vendita al Banco"); // Generated
		this.setMaximizable(true); // Generated
		this.setIconifiable(true); // Generated
		this.setClosable(true); // Generated
		this.setContentPane(getJContentPane());
		((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlArticoli");
		
	}
	
	private void initializeCarrello(){
		carrelloSpesa = new Vector<DettaglioOrdine>();
		pannelloCarrello = new JPanelRiepilogoVendita();
		pannelloCarrello.setPreferredSize(new Dimension(575, 400));
		pannelloCarrello.setVisible(true);
//		scarico = new Scarico();
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

		this.getRootPane().getActionMap().put("F1", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisciDaRepo("Reparto 1");
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
		this.getRootPane().getActionMap().put("F2", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				inserisciDaRepo("Reparto 1");
			}
		});
		this.getRootPane().getActionMap().put("ESC", new AbstractAction() {
			public void actionPerformed(ActionEvent a) {
				dispose();
			}
		});
	}
	
	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == btnAzzera ){
				txtFldImporto.setText("");
				txtQta.setText("");
			}
			else if ( e.getSource() == btnMoltiplica ){
				int qta = Integer.valueOf(txtQta.getText().equals("") ? "1" : txtQta.getText());
				txtQta.setText(String.valueOf(qta+1));
			}
			else if ( e.getSource() == btnVirgola ){
				txtFldImporto.setText(txtFldImporto.getText().concat(","));
			}
			else if ( e.getSource() == btnZero ){
				txtFldImporto.setText(txtFldImporto.getText().concat("0")); 
			}
			else if ( e.getSource() == btnDoppioZero ){
				txtFldImporto.setText(txtFldImporto.getText().concat("00")); 
			}
			else if ( e.getSource() == btnUno ){
				txtFldImporto.setText(txtFldImporto.getText().concat("1")); 
			}
			else if ( e.getSource() == btnDue ){
				txtFldImporto.setText(txtFldImporto.getText().concat("2")); 
			}
			else if ( e.getSource() == btnTre ){
				txtFldImporto.setText(txtFldImporto.getText().concat("3")); 
			}
			else if ( e.getSource() == btnQuattro ){
				txtFldImporto.setText(txtFldImporto.getText().concat("4")); 
			}
			else if ( e.getSource() == btnCinque ){
				txtFldImporto.setText(txtFldImporto.getText().concat("5")); 
			}
			else if ( e.getSource() == btnSei ){
				txtFldImporto.setText(txtFldImporto.getText().concat("6")); 
			}
			else if ( e.getSource() == btnSette ){
				txtFldImporto.setText(txtFldImporto.getText().concat("7")); 
			}
			else if ( e.getSource() == btnOtto ){
				txtFldImporto.setText(txtFldImporto.getText().concat("8")); 
			}
			else if ( e.getSource() == btnNove ){
				txtFldImporto.setText(txtFldImporto.getText().concat("9")); 
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
				((CardLayout) pnlContenitore.getLayout()).show(pnlContenitore, "pnlFunzioniCassa");
			}
		}
	}
	
	private void inserisciDaRepo(String repo){
		DettaglioOrdine dv = new DettaglioOrdine();
		dv.setDescrizione(repo);
		dv.setPrezzoVendita(Double.valueOf(txtFldImporto.getText()));
		if ( txtQta.getText().trim().equals("")){
			dv.setQta(1);
		}
		else{
			dv.setQta(Double.valueOf(txtQta.getText().trim()));
		}
		dv.setIva(20);
		pannelloCarrello.addDettaglioOrdine(dv);
		lblTotale.setText(String.valueOf(pannelloCarrello.getTotaleCarrello()));
		txtFldImporto.setText("");
		txtQta.setText("");
	}
	
	private void inserisciNelCarrello(int code){
		if ( txtFieldRicerca.getText() == null || txtFieldRicerca.getText().trim().equals("") ){
			// Scrivere messaggio campo codice prodotto non valido
		}
		else{
			DettaglioOrdine dv = new DettaglioOrdine();
			dv.loadByID(code);
			pannelloCarrello.addDettaglioOrdine(dv);
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
			lblContanti = new JLabel();
			lblContanti.setBounds(new Rectangle(760, 30, 170, 40));
			lblContanti.setIcon(new ImageIcon("resource/contanti.gif"));
			lblResto = new JLabel();
			lblResto.setBounds(new Rectangle(760, 70, 170, 40));
			lblResto.setIcon(new ImageIcon("resource/resto.gif"));
			lblTotale = new JLabel();//"", new ImageIcon("resource/totale.gif"), SwingConstants.CENTER);
			lblTotale.setBounds(new Rectangle(590, 30, 170, 80));
//			lblTotale.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
//			lblTotale.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
//			lblTotale.setIcon(new ImageIcon("resource/totaleT.gif"));
			lblTotale.setOpaque(true);
			lblTotale.setBackground(Color.getHSBColor(1, 8, 8));
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
			pnlPulsantiFunzioni.add(lblTotale, null);
			pnlPulsantiFunzioni.add(lblResto, null);
			pnlPulsantiFunzioni.add(lblContanti, null);
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
			txtFieldRicerca.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						inserisciNelCarrello(Integer.parseInt(txtFieldRicerca.getText()));
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
			pnlFunzioniCassa.add(getBtnReso(), null);
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
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtFldImporto = new JFormattedTextField(notaz);
//			txtFldImporto = new JTextField();
			txtFldImporto.setBounds(new Rectangle(75, 98, 130, 28));
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
		}
		return btnInsManuale;
	}

	/**
	 * This method initializes btnReso	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnReso() {
		if (btnReso == null) {
			btnReso = new JButton();
			btnReso.setBounds(new Rectangle(0, 292, 85, 29));
			btnReso.setText("Reso");
		}
		return btnReso;
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
			pnlContenitore.add(getPnlFunzioniCassa(), getPnlFunzioniCassa().getName());
			pnlContenitore.add(getPnlArticoli(), getPnlArticoli().getName());
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
			pnlArticoli = new JPanel();
			pnlArticoli.setLayout(new GridBagLayout());
			pnlArticoli.setName("pnlArticoli");
		}
		return pnlArticoli;
	}

}
