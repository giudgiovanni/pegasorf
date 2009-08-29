package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articoli;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;


import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.ImmagineArticolo;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private ImmagineArticolo imgArticolo;
	private Articoli articolo;
	
	
	public JButtonArticolo(Articoli articolo) {
		super();
		this.articolo = articolo;
		initGUI();
	}
	
	private void initGUI() {
		try {
//			imgArticolo = articolo.getImmagineArticolos().toArray()[0];
//			imgArticolo.caricaDati();						
//			this.setText(imgArticolo.getNome());
			this.setText(articolo.getDescrizione());
//			this.setIcon(new ImageIcon(imgArticolo.getFile()));
			this.setVerticalTextPosition(AbstractButton.BOTTOM);
			this.setHorizontalTextPosition(AbstractButton.CENTER);
			this.setFont(new Font("Dialog", Font.BOLD, 16));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setPreferredSize(new Dimension(100, 100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Articoli getArticolo() {
		return articolo;
	}

	public void setArticolo(Articoli articolo) {
		this.articolo = articolo;
	}
}
