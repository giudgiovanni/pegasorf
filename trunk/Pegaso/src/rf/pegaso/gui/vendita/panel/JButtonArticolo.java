package rf.pegaso.gui.vendita.panel;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import rf.pegaso.db.tabelle.Icona;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private Icona icon = null;  //  @jve:decl-index=0:
	private int idArticolo = 0;
	
	
	public JButtonArticolo(int idArticolo) {
		super();
		this.idArticolo = idArticolo;
		initGUI();
	}
	
	private void initGUI() {
		try {
			
			setIcon(new ImageIcon(icon.getImgdefault()));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setMargin(new Insets(8, 14, 8, 14));
			
			icon = new Icona();
			icon.caricaDati(idArticolo);
			//this.addActionListener(new MyButtonListener());
			setPreferredSize(new Dimension(icon.getWidth(), icon.getHight()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private class MyButtonListener implements java.awt.event.ActionListener {
//		
//		private int idArticolo;
//		public MyButtonListener(int idArticolo){
//			this.idArticolo = idArticolo;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			
//		}
//		
//	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Icona getIcona() {
		return icon;
	}

	public void setIcona(Icona icon) {
		this.icon = icon;
	}
}
