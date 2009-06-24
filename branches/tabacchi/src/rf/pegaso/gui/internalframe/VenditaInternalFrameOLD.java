package rf.pegaso.gui.internalframe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import rf.pegaso.gui.vendita.AlBanco;
import rf.pegaso.gui.vendita.DocumentoDiTrasporto;
import rf.pegaso.gui.vendita.Fattura;
import rf.pegaso.gui.vendita.FatturaImmediata;
import rf.pegaso.gui.vendita.VenditaAvanzata;

public class VenditaInternalFrameOLD extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnFattura = null;
	private JButton btnFatturaImmediata = null;
	private JButton btnAlBanco = null;
	private JButton btnDDT = null;
	private JButton btnVenditaSemplice = null;


	public VenditaInternalFrameOLD(JFrame padre) {
		initialize();
	}
					
	 
	private void initialize() {
		this.setSize(new Dimension(693, 355));  // Generated
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // Generated
		this.setTitle("Gestione Vendite");  // Generated
		this.setMaximizable(true); // Generated
		this.setIconifiable(true); // Generated
		this.setClosable(true); // Generated
		this.setContentPane(getJContentPane());
	}


	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			try {
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.gridx = 0;  // Generated
				gridBagConstraints4.insets = new Insets(10, 10, 10, 10);  // Generated
				gridBagConstraints4.gridy = 2;  // Generated
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.gridx = 1;  // Generated
				gridBagConstraints3.insets = new Insets(10, 10, 10, 10);  // Generated
				gridBagConstraints3.gridy = 1;  // Generated
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.gridx = 0;  // Generated
				gridBagConstraints2.insets = new Insets(10, 10, 10, 10);  // Generated
				gridBagConstraints2.gridy = 1;  // Generated
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.gridx = 1;  // Generated
				gridBagConstraints1.insets = new Insets(10, 10, 10, 10);  // Generated
				gridBagConstraints1.gridy = 0;  // Generated
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = 0;  // Generated
				gridBagConstraints.insets = new Insets(10, 10, 10, 10);  // Generated
				gridBagConstraints.gridy = 0;  // Generated
				jContentPane = new JPanel();
				jContentPane.setLayout(new GridBagLayout());  // Generated
				jContentPane.add(getBtnFattura(), gridBagConstraints);  // Generated
				jContentPane.add(getBtnFatturaImmediata(), gridBagConstraints1);  // Generated
				jContentPane.add(getBtnAlBanco(), gridBagConstraints2);  // Generated
				jContentPane.add(getBtnDDT(), gridBagConstraints3);  // Generated
				jContentPane.add(getBtnVenditaAvanzata(), gridBagConstraints4);
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return jContentPane;
	}


	/**
	 * This method initializes btnFattura	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnFattura() {
		if (btnFattura == null) {
			try {
				btnFattura = new JButton();
				btnFattura.setPreferredSize(new Dimension(120, 70));  // Generated
				btnFattura.setText("<html><center>Fattura</html>");  // Generated
				btnFattura.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apriFattura();
					}
				});
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return btnFattura;
	}
	
	/**
	 * This method initializes btnFatturaImmediata	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnFatturaImmediata() {
		if (btnFatturaImmediata == null) {
			try {
				btnFatturaImmediata = new JButton();
				btnFatturaImmediata.setPreferredSize(new Dimension(120, 70));  // Generated
				btnFatturaImmediata.setText("<html><center>Fattura<br>Immediata</html>");  // Generated
				btnFatturaImmediata.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apriFatturaImmediata();
					}
				});
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return btnFatturaImmediata;
	}
	
	/**
	 * This method initializes btnAlBanco
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAlBanco() {
		if (btnAlBanco == null) {
			try {
				btnAlBanco = new JButton();
				btnAlBanco.setPreferredSize(new Dimension(120, 70));  // Generated
				btnAlBanco.setText("<html><center>Al<br>Banco</html>");  // Generated
				btnAlBanco.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apriAlBanco();
					}
				});
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return btnAlBanco;
	}
	
	/**
	 * This method initializes btnDDT	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDDT() {
		if (btnDDT == null) {
			try {
				btnDDT = new JButton();
				btnDDT.setPreferredSize(new Dimension(120, 70));  // Generated
				btnDDT.setText("<html><center>Documento<br>di<br>Trasporto</html>");  // Generated
				btnDDT.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						apriDDT();
					}
				});
			} catch (java.lang.Throwable e) {
				e.printStackTrace();
			}
		}
		return btnDDT;
	}


	protected void apriFattura() {
		Fattura f = new Fattura();
		f.setVisible(true);
	}
	
	
	protected void apriFatturaImmediata(){
		FatturaImmediata fi = new FatturaImmediata();
		fi.setVisible(true);
	}
	
	protected void apriAlBanco(){
		AlBanco b = new AlBanco();
		b.setVisible(true);
	}

	protected void apriDDT(){
		DocumentoDiTrasporto ddt = new DocumentoDiTrasporto();
		ddt.setVisible(true);
	}
	
	protected void apriVenditaSemplice(){
		VenditaAvanzata va = new VenditaAvanzata();
		va.setVisible(true);
	}


	/**
	 * This method initializes btnVenditaAvanzata	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnVenditaAvanzata() {
		if (btnVenditaSemplice == null) {
			btnVenditaSemplice = new JButton();
			btnVenditaSemplice.setPreferredSize(new Dimension(120, 70));  // Generated
			btnVenditaSemplice.setText("<html><center>Vendita<br>Semplice</html>");  // Generated
			btnVenditaSemplice.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					apriVenditaSemplice();
				}
			});
		}
		return btnVenditaSemplice;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
