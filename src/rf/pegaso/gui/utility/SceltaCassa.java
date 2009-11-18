package rf.pegaso.gui.utility;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.gui.internalframe.VenditaInternalFrameOLD;
import rf.pegaso.gui.vendita.panel.JPanelRiepilogoVendita;
import rf.utility.db.DBManager;
import rf.utility.gui.ComboBoxAutoComplete;
import rf.utility.gui.UtilGUI;

public class SceltaCassa extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox cmbCodici = null;
	private VenditaInternalFrameOLD owner = null;
	private LinkedList<JPanelRiepilogoVendita> listaCasse;
	private JButton btnOK = null;

	/**
	 * @param owner
	 */
	public SceltaCassa(VenditaInternalFrameOLD owner, LinkedList<JPanelRiepilogoVendita> listaCasse) {
//		super(owner,true);
		this.owner = owner;
		this.listaCasse = listaCasse;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(264, 141);
		this.setTitle("Selezione Cassa");  // Generated
		this.setContentPane(getJContentPane());
		caricaComboCasse();
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
	
	private void caricaComboCasse(){
		try {
			cmbCodici.addItem("Nuova Cassa");
			for ( JPanelRiepilogoVendita cassa : listaCasse ){
				cmbCodici.addItem(cassa.getNome());
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Errore caricamento dati combobox casse", "ERRORE", JOptionPane.ERROR_MESSAGE);
			
			e2.printStackTrace();
		}
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
				cmbCodici.setBounds(new Rectangle(8, 12, 241, 29));
				cmbCodici.addKeyListener(new java.awt.event.KeyAdapter() {
					public void keyPressed(java.awt.event.KeyEvent e) {
						if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
							ok();
						}
					}
				});
				
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
				btnOK.setBounds(new Rectangle(8, 60, 241, 37));  // Generated
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
		this.owner.setPannelloRiepilogo(cmbCodici.getSelectedIndex());
//		this.numCassa = cmbCodici.getSelectedIndex();
		dispose();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
