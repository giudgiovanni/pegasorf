package rf.pegaso.gui.vendita.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;


import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.ImmagineArticolo;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private ImmagineArticolo imgArticolo;
	private Articolo articolo;
	
	
	public JButtonArticolo(Articolo articolo) {
		super();
		this.articolo = articolo;
		initGUI();
	}
	
	private void initGUI() {
		try {
			imgArticolo = new ImmagineArticolo(articolo);
			imgArticolo.caricaDati();						
			this.setText(imgArticolo.getNome());
//			this.setIcon(new ImageIcon(imgArticolo.getFile()));
			this.setVerticalTextPosition(AbstractButton.BOTTOM);
			this.setHorizontalTextPosition(AbstractButton.CENTER);
			this.setFont(new Font("Dialog", Font.BOLD, 24));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setMinimumSize(new Dimension(90, 80));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
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

}
