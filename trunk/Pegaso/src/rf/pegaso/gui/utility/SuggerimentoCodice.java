package rf.pegaso.gui.utility;

import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.Articolo;
import rf.utility.gui.ComboBoxAutoComplete;
import rf.utility.gui.UtilGUI;

public class SuggerimentoCodice extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox cmbCodici = null;
	private DBManager dbm;
	private int[] idArticolo;
	private String[] codiceBarre;
	private JButton btnOK = null;
	private String[] codice;

	/**
	 * @param owner
	 */
	public SuggerimentoCodice(JDialog owner, DBManager dbm,String[] codice) {
		super(owner,true);
		this.dbm=dbm;
		this.codice=codice;
		initialize();
	}
	
	public SuggerimentoCodice(JFrame owner, DBManager dbm,String[] codice) {
		super(owner,true);
		this.dbm=dbm;
		this.codice=codice;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(264, 141);
		this.setTitle("Selezione codice articolo");  // Generated
		this.setContentPane(getJContentPane());
		caricaComboCodici();
		UtilGUI.centraDialog(this);
		
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));  // Generated
			jContentPane.add(getCmbCodici(), null);  // Generated
			jContentPane.add(getBtnOK(), null);  // Generated
		}
		return jContentPane;
	}
	
	private void caricaComboCodici(){
		Articolo f = new Articolo();
		String allCodici[] = null;

		try {
			allCodici = (String[]) f.allCodiciBarre();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(this, "Errore caricamento dati combobox articoli", "ERRORE", JOptionPane.ERROR_MESSAGE);
			
			e2.printStackTrace();
		}

		// questi due array li usiamo per tenere
		// traccia dei codici del fornitore in
		// base alla posizione che si trovano nel combo
		int size = allCodici.length;

		// Impostiamo e carichiamo i dati nel combobox
		cmbCodici.setEditable(true);
		cmbCodici.addItem("");
		for (int i = 0; i < size; i++) {
			
			cmbCodici.addItem(allCodici[i]);
		}
		// cambiamo l'edito del combo
		JTextComponent editor = (JTextComponent) cmbCodici.getEditor()
				.getEditorComponent();
		// editor.addKeyListener(new MyKeyListeners());
		new ComboBoxAutoComplete(cmbCodici,false);
		
		
	}

	/**
	 * This method initializes cmbCodici	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbCodici() {
		if (cmbCodici == null) {
			try {
				cmbCodici = new JComboBox();
				cmbCodici.setBounds(new Rectangle(8, 60, 241, 29));
				
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return cmbCodici;
	}

	/**
	 * This method initializes btnOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			try {
				btnOK = new JButton();
				btnOK.setBounds(new Rectangle(8, 12, 241, 37));  // Generated
				btnOK.setText("OK");  // Generated
				btnOK.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						ok();
					}
				});
			} catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return btnOK;
	}

	protected void ok() {
		String tmp=(String) cmbCodici.getSelectedItem();
		if(tmp.equalsIgnoreCase("") || tmp==null){
			JOptionPane.showMessageDialog(this, "Selezionare un codice a barre", "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.codice[0]=tmp;
		dispose();
		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
