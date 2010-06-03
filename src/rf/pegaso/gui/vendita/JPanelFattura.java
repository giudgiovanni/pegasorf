package rf.pegaso.gui.vendita;

import javax.swing.JFrame;

import it.infolabs.hibernate.Aspetto;
import it.infolabs.hibernate.AspettoHome;
import it.infolabs.hibernate.Causale;
import it.infolabs.hibernate.CausaleHome;
import it.infolabs.hibernate.Cliente;
import it.infolabs.hibernate.ClienteHome;
import it.infolabs.hibernate.Pagamento;
import it.infolabs.hibernate.PagamentoHome;
import it.infolabs.hibernate.exception.FindAllEntityException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import rf.myswing.IDJComboBox;
import rf.pegaso.db.tabelle.DettaglioScarico;
import rf.pegaso.db.tabelle.Scarico;
import rf.pegaso.gui.gestione.ClientiAdd;
import rf.pegaso.gui.vendita.AspettoAdd;
import rf.pegaso.gui.vendita.CausaleAdd;
import rf.pegaso.gui.vendita.PagamentoAdd;
import rf.utility.ControlloDati;
import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;
import rf.utility.gui.text.AutoCompletion;

import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;

public class JPanelFattura extends JPanel {

	private static final long serialVersionUID = 1L;
	private DBManager dbm = null;
	private JButton btnChiudi = null;
	private JButton btnSalva = null;
	private JButton btnStampa = null;
	private JLabel lblNumero = null;
	private JTextField txtNumero = null;
	private JLabel lblData = null;
	private JDateChooser dataCorrente = null;
	private JLabel lblCliente = null;
	private IDJComboBox cmbClienti = null;
	private JButton btnNuovoCliente = null;
	private JLabel lblPagamento = null;
	private IDJComboBox cmbPagamento = null;
	private Vector<DettaglioScarico> carrello = null;
	private JDateChooser dataTrasporto = null;
	private JLabel lblDestinazione = null;
	private JLabel lblSpeseInc = null;
	private JLabel lblSpeseTr = null;
	private JTextField txtDestinazione = null;
	private JFormattedTextField txtSpeseInc = null;
	private JFormattedTextField txtSpeseTr = null;
	private JLabel lblDataTr = null;
	private JLabel lblOraTr = null;
	private JLabel lblColli = null;
	private JLabel lblPeso = null;
	private JFormattedTextField txtColli = null;
	private JFormattedTextField txtPeso = null;
	private JLabel lblCausale = null;
	private JLabel lblAspetto = null;
	private JLabel lblConsegna = null;
	private IDJComboBox cmbCausale = null;
	private IDJComboBox cmbAspetto = null;
	private JComboBox cmbConsegna = null;
	private JLabel lblPorto = null;
	private JComboBox cmbPorto = null;
	private JButton btnNuovoPagamento = null;
	private JButton btnNuovaCausale = null;
	private JButton btnNuovoAspetto = null;
	private JFormattedTextField txtOraTr = null;
	private JLabel lblPuntini = null;
	private JFormattedTextField txtMinTr = null;
	private boolean saved;
	private JButton btnAzzera = null;
	//usata per sapere se si effettua una modifca oppure no
	private boolean modifica;
	private JFrame padre = null;
	
	private JPanel pnlPulsanti = null;
	private JPanel pnlTipoDocumento = null;
	private JPanel pnlDati = null;
	private JRadioButton rbtnFattura = null;
	private JLabel lblRBFattura = null;
	private JLabel lblRBBolla = null;
	private JRadioButton rbtnBolla = null;

	public JPanelFattura(JFrame padre){
		this.dbm = DBManager.getIstanceSingleton();
		this.padre = padre;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		initializePanel();
		carrello = new Vector<DettaglioScarico>();
		DettaglioScarico dv = new DettaglioScarico();
		carrello.add(dv);
		this.setSize(new Dimension(800, 600));
		caricaClienti();
		caricaPagamento();
		caricaCausale();
		caricaAspetto();
		txtNumero.setText(String.valueOf(dbm.getNewID("scarico", "idordine")));

		Calendar c=Calendar.getInstance();
		txtOraTr.setText(String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
		txtMinTr.setText(String.valueOf(c.get(Calendar.MINUTE)));
		saved = false;
		modifica=false;
	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnAzzera){
				resetCampi();
			}
			else if ( e.getSource() == btnSalva )
				salva();
			else if ( e.getSource() == btnStampa )
				stampa();
			else if ( e.getSource() == btnNuovoCliente ){
				nuovoCliente();
				caricaClienti();
				cmbClienti.setSelectedItemByID((dbm.getNewID("clienti","idcliente") - 1));
			}
			else if ( e.getSource() == btnNuovoPagamento ){
				nuovoPagamento();
				caricaPagamento();
				cmbPagamento.setSelectedItemByID((dbm.getNewID("pagamento","idpagamento") - 1));
			}
			else if ( e.getSource() == btnNuovoAspetto ){
				nuovoAspetto();
				caricaAspetto();
				cmbAspetto.setSelectedItemByID((dbm.getNewID("aspetto","idaspetto") - 1));
			}
			else if ( e.getSource() == btnNuovaCausale ){
				nuovaCausale();
				caricaCausale();
				cmbCausale.setSelectedItemByID((dbm.getNewID("causale","idcausale") - 1));
			}
		}
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private void initializePanel() {
		
		this.setLayout(new BorderLayout());
		this.add(getPnlTipoDocumento(), BorderLayout.NORTH);
		this.add(getPnlDati(), BorderLayout.CENTER);
		this.add(getPnlPulsanti(), BorderLayout.SOUTH);
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private JPanel getPnlPulsanti(){
		if ( pnlPulsanti == null ){
			pnlPulsanti = new JPanel();
			pnlPulsanti.setPreferredSize(new Dimension(200, 60));
			pnlPulsanti.setLayout(null);
//			pnlPulsanti.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlPulsanti.add(getBtnChiudi(), null);
			pnlPulsanti.add(getBtnSalva(), null);
			pnlPulsanti.add(getBtnStampa(), null);
			pnlPulsanti.add(getBtnAzzera(), null);
		}
		return pnlPulsanti;
	}
	
	private JPanel getPnlTipoDocumento(){
		if ( pnlTipoDocumento == null ){
			lblRBBolla = new JLabel();
			lblRBBolla.setBounds(new Rectangle(120, 21, 35, 16));
			lblRBBolla.setText("Bolla");
			lblRBFattura = new JLabel();
			lblRBFattura.setBounds(new Rectangle(20, 21, 50, 16));
			lblRBFattura.setText("Fattura");
			pnlTipoDocumento = new JPanel();
			pnlTipoDocumento.setPreferredSize(new Dimension(200, 50));
			pnlTipoDocumento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tipo Documento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlTipoDocumento.setLayout(null);
			pnlTipoDocumento.add(lblRBFattura, null);
			pnlTipoDocumento.add(lblRBBolla, null);
			pnlTipoDocumento.add(getRbtnBolla(), null);
			pnlTipoDocumento.add(getRbtnFattura(), null);
		}
		return pnlTipoDocumento;
	}
	
	private JPanel getPnlDati(){
		if(pnlDati == null){
			lblPuntini = new JLabel();
			lblPuntini.setBounds(new Rectangle(280, 179, 10, 16));
			lblPuntini.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPuntini.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntini.setVerticalAlignment(SwingConstants.CENTER);
			lblPuntini.setText(":");
			lblPorto = new JLabel();
			lblPorto.setBounds(new Rectangle(290, 343, 40, 16));
			lblPorto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPorto.setHorizontalAlignment(SwingConstants.CENTER);
			lblPorto.setVerticalAlignment(SwingConstants.CENTER);
			lblPorto.setText("Porto");
			lblConsegna = new JLabel();
			lblConsegna.setBounds(new Rectangle(16, 343, 80, 16));
			lblConsegna.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
			lblConsegna.setVerticalAlignment(SwingConstants.CENTER);
			lblConsegna.setText("Consegna");
			lblAspetto = new JLabel();
			lblAspetto.setBounds(new Rectangle(16, 302, 80, 16));
			lblAspetto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblAspetto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAspetto.setVerticalAlignment(SwingConstants.CENTER);
			lblAspetto.setText("Aspetto");
			lblCausale = new JLabel();
			lblCausale.setBounds(new Rectangle(16, 261, 80, 16));
			lblCausale.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCausale.setHorizontalAlignment(SwingConstants.CENTER);
			lblCausale.setVerticalAlignment(SwingConstants.CENTER);
			lblCausale.setText("Causale");
			lblPeso = new JLabel();
			lblPeso.setBounds(new Rectangle(355, 179, 60, 16));
			lblPeso.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setVerticalAlignment(SwingConstants.CENTER);
			lblPeso.setText("Peso (Kg)");
			lblColli = new JLabel();
			lblColli.setBounds(new Rectangle(355, 138, 60, 16));
			lblColli.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblColli.setHorizontalAlignment(SwingConstants.CENTER);
			lblColli.setVerticalAlignment(SwingConstants.CENTER);
			lblColli.setText("Colli (Nr)");
			lblOraTr = new JLabel();
			lblOraTr.setBounds(new Rectangle(175, 179, 50, 16));
			lblOraTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblOraTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblOraTr.setVerticalAlignment(SwingConstants.CENTER);
			lblOraTr.setText("Ora Tr");
			lblDataTr = new JLabel();
			lblDataTr.setBounds(new Rectangle(175, 138, 50, 16));
			lblDataTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDataTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblDataTr.setVerticalAlignment(SwingConstants.CENTER);
			lblDataTr.setText("Data Tr");
			lblSpeseTr = new JLabel();
			lblSpeseTr.setBounds(new Rectangle(16, 179, 80, 16));
			lblSpeseTr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseTr.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setVerticalAlignment(SwingConstants.CENTER);
			lblSpeseTr.setText("Spese Tr");
			lblSpeseInc = new JLabel();
			lblSpeseInc.setBounds(new Rectangle(16, 138, 80, 16));
			lblSpeseInc.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblSpeseInc.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpeseInc.setText("Spese Inc");
			lblDestinazione = new JLabel();
			lblDestinazione.setBounds(new Rectangle(16, 97, 80, 16));
			lblDestinazione.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblDestinazione.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinazione.setText("Destinazione");
			lblPagamento = new JLabel();
			lblPagamento.setBounds(new Rectangle(16, 220, 80, 16));
			lblPagamento.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblPagamento.setHorizontalAlignment(SwingConstants.CENTER);
			lblPagamento.setText("Pagamento");
			lblCliente = new JLabel();
			lblCliente.setBounds(new Rectangle(16, 56, 80, 16));
			lblCliente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCliente.setText("Cliente");
			lblData = new JLabel();
			lblData.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblData.setBounds(new Rectangle(224, 15, 40, 16));
			lblData.setText("Data");
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(16, 15, 80, 16));
			lblNumero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumero.setText("Numero");
			pnlDati = new JPanel();
			pnlDati.setLayout(null);
			pnlDati.setPreferredSize(new Dimension(500, 600));
//			pnlDati.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlDati.add(lblNumero, null);
			pnlDati.add(getTxtNumero(), null);
			pnlDati.add(lblData, null);
			pnlDati.add(getDataCorrente(), null);
			pnlDati.add(lblCliente, null);
			pnlDati.add(getCmbClienti(), null);
			pnlDati.add(getBtnNuovoCliente(), null);
			pnlDati.add(lblPagamento, null);
			pnlDati.add(getCmbPagamento(), null);
			pnlDati.add(lblDestinazione, null);
			pnlDati.add(lblSpeseInc, null);
			pnlDati.add(lblSpeseTr, null);
			pnlDati.add(getTxtDestinazione(), null);
			pnlDati.add(getTxtSpeseInc(), null);
			pnlDati.add(getTxtSpeseTr(), null);
			pnlDati.add(lblDataTr, null);
			pnlDati.add(lblOraTr, null);
			pnlDati.add(getDataTrasporto(), null);
			pnlDati.add(lblColli, null);
			pnlDati.add(lblPeso, null);
			pnlDati.add(getTxtColli(), null);
			pnlDati.add(getTxtPeso(), null);
			pnlDati.add(lblCausale, null);
			pnlDati.add(lblAspetto, null);
			pnlDati.add(lblConsegna, null);
			pnlDati.add(getCmbCausale(), null);
			pnlDati.add(getCmbAspetto(), null);
			pnlDati.add(getCmbConsegna(), null);
			pnlDati.add(lblPorto, null);
			pnlDati.add(getCmbPorto(), null);
			pnlDati.add(getBtnNuovoPagamento(), null);
			pnlDati.add(getBtnNuovaCausale(), null);
			pnlDati.add(getBtnNuovoAspetto(), null);
			pnlDati.add(getTxtOraTr(), null);
			pnlDati.add(lblPuntini, null);
			pnlDati.add(getTxtMinTr(), null);
		}
		return pnlDati;
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
			btnChiudi.setBounds(new Rectangle(307, 9, 78, 26));
			btnChiudi.addActionListener(new MyButtonListener());
		}
		return btnChiudi;
	}

	/**
	 * This method initializes btnSalva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Salva");
			btnSalva.setBounds(new Rectangle(110, 9, 78, 26));
			btnSalva.addActionListener(new MyButtonListener());
		}
		return btnSalva;
	}

	/**
	 * This method initializes btnStampa
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnStampa() {
		if (btnStampa == null) {
			btnStampa = new JButton();
			btnStampa.setText("Stampa");
			btnStampa.setBounds(new Rectangle(209, 9, 78, 26));
			btnStampa.addActionListener(new MyButtonListener());
		}
		return btnStampa;
	}

	/**
	 * This method initializes txtNumero
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(117, 13, 100, 20));

		}
		return txtNumero;
	}

	private JDateChooser getDataCorrente() {
		if (dataCorrente == null)
			try {
				dataCorrente = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataCorrente.setDate(new java.util.Date());
				dataCorrente.setBounds(new Rectangle(288, 11, 112, 24));
			} catch (Throwable throwable) {
			}
		return dataCorrente;
	}

	private JDateChooser getDataTrasporto() {
		if (dataTrasporto == null)
			try {
				dataTrasporto = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
				dataTrasporto.setDate(new java.util.Date());
				dataTrasporto.setBounds(new Rectangle(235, 134, 100, 24));
			} catch (Throwable throwable) {
			}
		return dataTrasporto;
	}

	private void stampa(){

	}

	@SuppressWarnings("deprecation")
	private void salva(){

		//Salviamo i dati della fattura
		String num_fattura = txtNumero.getText();
		if (num_fattura.equalsIgnoreCase("")) {
			messaggioCampoMancante("Numero Fattura non presente.", "CAMPO VUOTO");
			return;
		}
		int ora = Integer.parseInt(txtOraTr.getText());
		int min = Integer.parseInt(txtMinTr.getText());
		if ( ora < 0 || ora >24 || min < 0 || min > 60 ){
			messaggioCampoMancante("Ora Trasporto mal formata.", "AVVISO");
			return;
		}
		int idCliente = 0;
		if ( cmbClienti.getIDSelectedItem() != null )
			idCliente = Integer.parseInt(cmbClienti.getIDSelectedItem());
		int idPagamento = 0;
		if ( cmbPagamento.getIDSelectedItem() != null )
			idPagamento = Integer.parseInt(cmbPagamento.getIDSelectedItem());
		int idCausale = 0;
		if ( cmbCausale.getIDSelectedItem() != null )
			idCausale = Integer.parseInt(cmbCausale.getIDSelectedItem());
		int idAspetto = 0;
		if ( cmbAspetto.getIDSelectedItem() != null )
			idAspetto = Integer.parseInt(cmbAspetto.getIDSelectedItem());
		double speseInc = 0.00;
		double speseTr = 0.00;
		int colli = 0;
		double peso = 0.00;
		try {
			if ( !txtSpeseInc.getText().equals("") )
				speseInc = ControlloDati.convertPrezzoToDouble(txtSpeseInc.getText());
			if ( !txtSpeseTr.getText().equals("") )
				speseTr = ControlloDati.convertPrezzoToDouble(txtSpeseTr.getText());
			if ( !txtColli.getText().equals("") )
				colli = Integer.parseInt(txtColli.getText());
			if ( !txtPeso.getText().equals("") )
				peso = ControlloDati.convertPrezzoToDouble(txtPeso.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final Time oraTr = new Time(ora, min, 0);

		Scarico sc = new Scarico();
		sc.setIdCliente(idCliente);
		sc.setDataScarico(new java.sql.Date(dataCorrente.getDate().getTime()));
		sc.setOraScarico(new Time(dataCorrente.getDate().getTime()));
		sc.setDocFiscale(1);
		sc.setNumDocumento(num_fattura);
		sc.setDataDocumento(new java.sql.Date(dataCorrente.getDate().getTime()));

		sc.setIdPagamento(idPagamento);
		sc.setIdCausale(idCausale);
		sc.setSpeseIncasso(speseInc);
		sc.setSpeseTrasporto(speseTr);
		sc.setDataTrasporto(new java.sql.Date(dataTrasporto.getDate().getTime()));
		sc.setOraTrasporto(oraTr);
		sc.setColli(colli);
		sc.setPeso(peso);
		sc.setConsegna((String)cmbConsegna.getSelectedItem());
		sc.setPorto((String)cmbPorto.getSelectedItem());
		sc.setDestDiversa(txtDestinazione.getText());
		sc.setIdAspetto(idAspetto);
		int er, er1 = -1;
		
		try{
			if( saved ){
				er = sc.updateScarico();
				
			}else
				er = sc.insertScarico();
				//v.salvaDatiInFattura();

			if ( er == -1 ){
				sc.deleteScarico(sc.getIdScarico());
				messaggioCampoMancante("Si è verificato un errore durante l'inserimento della fattura. Riprovare.", "ERRORE");
				return;
			}
			//salviamo i dettagli della fattura
			carrello.remove(0);
			for (DettaglioScarico dv : carrello) {
				if ( saved )
					er1 = dv.update();
				else
					er1 = dv.insert();
			}
			if ( er1 == -1 ){
				sc.deleteScarico(sc.getIdScarico());
				sc.deleteAllArticoliScaricati();
				messaggioCampoMancante("Si è verificato un errore durante l'inserimento della fattura. Riprovare.", "ERRORE");
				return;
			}
			if ( er == 1 && er1 == 1 )
				messaggioCampoMancante("Inserimento effettuato con successo", "ERRORE");
			saved = false;
		
		} catch (IDNonValido e) {
				e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//---------FINE ROCCO-----------------------------------------
		dbm.notifyDBStateChange();
		resetCampi();
		modifica=false;
	}

	private void resetCampi(){
		txtNumero.setText(String.valueOf(dbm.getNewID("ordini", "idordine")));
		caricaClienti();
		caricaPagamento();
		caricaCausale();
		caricaAspetto();
		txtDestinazione.setText("");
		txtSpeseInc.setText("0,00");
		txtSpeseTr.setText("0,00");
		txtColli.setText("0");
		txtPeso.setText("0,00");
		carrello.removeAllElements();
		DettaglioScarico v = new DettaglioScarico();
		carrello.add(v);
		
		saved = false;
	}

	private void nuovoCliente(){
		ClientiAdd add = new ClientiAdd(padre, dbm);
		add.setVisible(true);
	}

	private void nuovoPagamento(){
		PagamentoAdd add = new PagamentoAdd(padre);
		add.setVisible(true);
	}

	private void nuovoAspetto(){
		AspettoAdd add = new AspettoAdd(padre);
		add.setVisible(true);
	}

	private void nuovaCausale(){
		CausaleAdd add = new CausaleAdd(padre);
		add.setVisible(true);
	}

	/**
	 * @param string
	 */
	private void messaggioCampoMancante(String testo, String tipo) {
		JOptionPane.showMessageDialog(this, testo, tipo,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private IDJComboBox getCmbClienti(){
		if ( cmbClienti == null )
			try {
				cmbClienti = new IDJComboBox();
				cmbClienti.setBounds(new Rectangle(117, 51, 270, 26));
//				cmbClienti.getEditor().getEditorComponent().addFocusListener(new java.awt.event.FocusAdapter() {
//					public void focusLost(java.awt.event.FocusEvent e) {
//						if ( cmbClienti.getIDSelectedItem() != null ){
//							Cliente c = new Cliente();
//							try {
//								c.caricaDati(Integer.parseInt(cmbClienti.getIDSelectedItem()));
//							} catch (NumberFormatException e1) {
//								e1.printStackTrace();
//							} catch (SQLException e1) {
//								e1.printStackTrace();
//							}
//							String dest = "";
//							if ( c.getVia() != null )
//								dest += c.getVia()+" ";
//							if ( c.getCap() != null )
//								dest += c.getCap()+" ";
//							if ( c.getCitta() != null )
//								dest += c.getCitta()+" ";
//							try {
//								if ( c.getProvinciaToString() != null )
//									dest += c.getProvincia();
//							} catch (SQLException e1) {
//								e1.printStackTrace();
//							}
//							txtDestinazione.setText(dest);
//						}
//					}
//				});
			} catch (Throwable throwable) {
			}
		return cmbClienti;
	}

	/**
	 * This method initializes btnNuovoCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoCliente() {
		if (btnNuovoCliente == null) {
			btnNuovoCliente = new JButton();
			btnNuovoCliente.setBounds(new Rectangle(393, 51, 82, 26));
			btnNuovoCliente.setText("Nuovo");
			btnNuovoCliente.addActionListener(new MyButtonListener());
		}
		return btnNuovoCliente;
	}

	/**
	 * This method initializes cmbPagamento
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPagamento() {
		if (cmbPagamento == null) {
			cmbPagamento = new IDJComboBox();
			cmbPagamento.setBounds(new Rectangle(117, 216, 150, 26));
		}
		return cmbPagamento;
	}

	private void caricaClienti(){
		ClienteHome.getInstance().begin();
		try {
			List<Cliente> result = ClienteHome.getInstance().findAll();
			String as[] = new String[result.size()];
			for (int i = 0; i < result.size(); i++) {
				Cliente c = result.get(i);
				as[i] = c.getIdcliente() + " - " + c.getNome() + " " + c.getCognome();
			}
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbClienti).caricaNewValueComboBox(as, false);
		} catch (FindAllEntityException e) {
			messaggioCampoMancante("Errore caricamento clienti nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbClienti);
	}

	private void caricaPagamento(){
		PagamentoHome.getInstance().begin();
		try {
			List<Pagamento> result = PagamentoHome.getInstance().findAll();
			String as[] = new String[result.size()];
			for (int i = 0; i < result.size(); i++) {
				Pagamento p = result.get(i);
				as[i] = p.getIdpagamento() + " - " + p.getNome();
			}
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbPagamento).caricaNewValueComboBox(as, false);
		} catch (Exception e) {
			messaggioCampoMancante("Errore caricamento pagamenti nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbPagamento);
	}

	private void caricaCausale(){
		CausaleHome.getInstance().begin();
		try {
			List<Causale> result = CausaleHome.getInstance().findAll();
			String as[] = new String[result.size()];
			for (int i = 0; i < result.size(); i++) {
				Causale c = result.get(i);
				as[i] = c.getIdcausale() + " - " + c.getNome();
			}
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbCausale).caricaNewValueComboBox(as, false);
		} catch (Exception e) {
			messaggioCampoMancante("Errore caricamento causale nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbCausale);
	}

	private void caricaAspetto(){
		AspettoHome.getInstance().begin();
		try {
			List<Aspetto> result = AspettoHome.getInstance().findAll();
			String as[] = new String[result.size()];
			for (int i = 0; i < result.size(); i++) {
				Aspetto a = result.get(i);
				as[i] = a.getIdaspetto() + " - " + a.getNome();
			}
			// carichiamo tutti i dati in due array
			// da passre al combobox
			((IDJComboBox) cmbAspetto).caricaNewValueComboBox(as, false);
		} catch (Exception e) {
			messaggioCampoMancante("Errore caricamento aspetto nel combobox", "ERRORE");
			e.printStackTrace();
		}
		AutoCompletion.enable(cmbAspetto);
	}

	/**
	 * This method initializes txtDestinazione
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDestinazione() {
		if (txtDestinazione == null) {
			txtDestinazione = new JTextField();
			txtDestinazione.setBounds(new Rectangle(117, 95, 360, 20));
		}
		return txtDestinazione;
	}

	/**
	 * This method initializes txtSpeseInc
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtSpeseInc() {
		if (txtSpeseInc == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtSpeseInc = new JFormattedTextField(notaz);
			txtSpeseInc.setBounds(new Rectangle(117, 136, 55, 20));
		}
		return txtSpeseInc;
	}

	/**
	 * This method initializes txtSpeseTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtSpeseTr() {
		if (txtSpeseTr == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtSpeseTr = new JFormattedTextField(notaz);
			txtSpeseTr.setBounds(new Rectangle(117, 177, 55, 20));
		}
		return txtSpeseTr;
	}

	/**
	 * This method initializes txtColli
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtColli() {
		if (txtColli == null) {
			DecimalFormat notaz = new DecimalFormat( "#");
			txtColli = new JFormattedTextField(notaz);
			txtColli.setBounds(new Rectangle(425, 136, 50, 20));
		}
		return txtColli;
	}

	/**
	 * This method initializes txtPeso
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtPeso() {
		if (txtPeso == null) {
			DecimalFormat notaz = new DecimalFormat( "#,##0.00");
			txtPeso = new JFormattedTextField(notaz);
			txtPeso.setBounds(new Rectangle(425, 177, 50, 20));
		}
		return txtPeso;
	}

	/**
	 * This method initializes cmbCausale
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbCausale() {
		if (cmbCausale == null) {
			cmbCausale = new IDJComboBox();
			cmbCausale.setBounds(new Rectangle(117, 257, 150, 26));
		}
		return cmbCausale;
	}

	/**
	 * This method initializes cmbAspetto
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbAspetto() {
		if (cmbAspetto == null) {
			cmbAspetto = new IDJComboBox();
			cmbAspetto.setBounds(new Rectangle(117, 298, 150, 26));
		}
		return cmbAspetto;
	}

	/**
	 * This method initializes cmbConsegna
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbConsegna() {
		if (cmbConsegna == null) {
			Vector<String> v = new Vector<String>();
			v.add("Mittente");
			v.add("Destinatario");
			v.add("Vettore");
			cmbConsegna = new JComboBox(v);
			cmbConsegna.setBounds(new Rectangle(117, 339, 150, 26));
			cmbConsegna.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (cmbConsegna.getSelectedItem().equals("Vettore"))
						System.out.println("itemStateChanged()");
				}
			});
		}
		return cmbConsegna;
	}

	/**
	 * This method initializes cmbPorto
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbPorto() {
		if (cmbPorto == null) {
			Vector<String> v = new Vector<String>();
			v.add("Franco");
			v.add("Assegnato");
			cmbPorto = new JComboBox(v);
			cmbPorto.setBounds(new Rectangle(355, 339, 120, 26));
		}
		return cmbPorto;
	}

	/**
	 * This method initializes btnNuovoPagamento
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoPagamento() {
		if (btnNuovoPagamento == null) {
			btnNuovoPagamento = new JButton();
			btnNuovoPagamento.setBounds(new Rectangle(290, 216, 82, 26));
			btnNuovoPagamento.setText("Nuovo");
			btnNuovoPagamento.addActionListener(new MyButtonListener());
		}
		return btnNuovoPagamento;
	}

	/**
	 * This method initializes btnNuovaCausale
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovaCausale() {
		if (btnNuovaCausale == null) {
			btnNuovaCausale = new JButton();
			btnNuovaCausale.setBounds(new Rectangle(290, 257, 82, 26));
			btnNuovaCausale.setText("Nuova");
			btnNuovaCausale.addActionListener(new MyButtonListener());
		}
		return btnNuovaCausale;
	}

	/**
	 * This method initializes btnNuovoAspetto
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnNuovoAspetto() {
		if (btnNuovoAspetto == null) {
			btnNuovoAspetto = new JButton();
			btnNuovoAspetto.setBounds(new Rectangle(290, 298, 82, 26));
			btnNuovoAspetto.setText("Nuovo");
			btnNuovoAspetto.addActionListener(new MyButtonListener());
		}
		return btnNuovoAspetto;
	}

	/**
	 * This method initializes txtOraTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtOraTr() {
		if (txtOraTr == null) {
			DecimalFormat notaz = new DecimalFormat( "##00");
			txtOraTr = new JFormattedTextField(notaz);
			txtOraTr.setBounds(new Rectangle(235, 177, 40, 20));
		}
		return txtOraTr;
	}

	/**
	 * This method initializes txtMinTr
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getTxtMinTr() {
		if (txtMinTr == null) {
			DecimalFormat notaz = new DecimalFormat( "##00");
			txtMinTr = new JFormattedTextField(notaz);
			txtMinTr.setBounds(new Rectangle(295, 177, 40, 20));
		}
		return txtMinTr;
	}

	/**
	 * This method initializes btnAzzera
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnAzzera() {
		if (btnAzzera == null) {
			btnAzzera = new JButton();
			btnAzzera.setBounds(new Rectangle(15, 9, 78, 26));
			btnAzzera.setText("Azzera");
			btnAzzera.addActionListener(new MyButtonListener());
		}
		return btnAzzera;
	}

	/**
	 * This method initializes rbtnFattura	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbtnFattura() {
		if (rbtnFattura == null) {
			rbtnFattura = new JRadioButton();
			rbtnFattura.setBounds(new Rectangle(72, 16, 28, 23));
			rbtnFattura.setText("Fattura");
		}
		return rbtnFattura;
	}

	/**
	 * This method initializes rbtnBolla	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRbtnBolla() {
		if (rbtnBolla == null) {
			rbtnBolla = new JRadioButton();
			rbtnBolla.setBounds(new Rectangle(157, 16, 28, 23));
		}
		return rbtnBolla;
	}
}