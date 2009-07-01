package rf.pegaso.gui.vendita.panel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import erreeffe.entity.Articoli;
import erreeffe.entity.ImmagineArticolo;
import erreeffe.entity.ImmagineArticoloHome;

import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.DettaglioArticolo;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private DettaglioArticolo dettArticolo = null;
	private ImmagineArticolo imgArticolo;
	private int idArticolo = 0;
	
	
	public JButtonArticolo(Articoli articolo) {
		super();
		this.idArticolo = idArticolo;
		imgArticolo.setArticoli(articolo);
		List<ImmagineArticolo> result = ImmagineArticoloHome.getInstance().findByExample(imgArticolo);
		if ( result.size() != 0 ){
			imgArticolo = result.get(0);
		}
		initGUI();
	}
	
	private void initGUI() {
		try {
			dettArticolo = new DettaglioArticolo();			
			this.setText(imgArticolo.getNome());
			this.setIcon(new ImageIcon(imgArticolo.getFile()));
			this.setVerticalTextPosition(AbstractButton.BOTTOM);
			this.setHorizontalTextPosition(AbstractButton.CENTER);
			//setIcon(new ImageIcon(dettArticolo.getImgdefault()));
//			this.setText(dettArticolo.getDescrizione());
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            //setMargin(new Insets(8, 14, 8, 14));
			//this.addActionListener(new MyButtonListener());
            setMinimumSize(new Dimension(90, 80));
			//setPreferredSize(new Dimension(100, 80));
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
