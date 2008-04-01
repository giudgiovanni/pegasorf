package rf.pegaso.gui.vendita.panel;

import java.awt.Insets;

import javax.swing.ImageIcon;
import rf.pegaso.db.tabelle.DettaglioArticolo;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private DettaglioArticolo dettArticolo = null;
	private int idArticolo = 0;
	
	
	public JButtonArticolo(int idArticolo) {
		super();
		this.idArticolo = idArticolo;
		initGUI();
	}
	
	private void initGUI() {
		try {
			dettArticolo = new DettaglioArticolo();
			dettArticolo.caricaDati(idArticolo, true);
			setIcon(new ImageIcon(dettArticolo.getImgdefault()));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setMargin(new Insets(8, 14, 8, 14));
			//this.addActionListener(new MyButtonListener());
			//setPreferredSize(new Dimension(icon.getWidth(), icon.getHight()));
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
}
