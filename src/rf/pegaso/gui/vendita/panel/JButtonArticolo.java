package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articoli;
import it.infolabs.hibernate.ImmagineArticolo;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import rf.pegaso.gui.utility.UtilityImage;

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
			if ( articolo.getImmagineArticolos().size() != 0 ){
				imgArticolo = (ImmagineArticolo) articolo.getImmagineArticolos().toArray()[0];
				this.setIcon(UtilityImage.resizeImage(new ImageIcon(imgArticolo.getFile()), 50, 50));
			}
//			imgArticolo.caricaDati();						
//			this.setText(imgArticolo.getNome());
			this.setText(articolo.getDescrizione());
			this.setVerticalTextPosition(AbstractButton.BOTTOM);
			this.setHorizontalTextPosition(AbstractButton.CENTER);
			this.setFont(new Font("Dialog", Font.BOLD, 16));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setPreferredSize(new Dimension(articolo.getDescrizione().length()*15, 100));
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
