package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articolo;
import it.infolabs.hibernate.ImmagineArticolo;
import it.infolabs.hibernate.Reparto;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import rf.pegaso.gui.utility.UtilityImage;

public class JButtonArticolo extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	
	private ImmagineArticolo imgArticolo;  //  @jve:decl-index=0:
	private Articolo articolo;
	private Reparto reparto;
	
	
	public JButtonArticolo(Articolo articolo) {
		super();
		this.articolo = articolo;
		initGUI();
	}
	
	public JButtonArticolo(Reparto reparto){
		super();
		this.reparto = reparto;
		initGUIRep();
	}
	
	private void initGUI() {
		try {
			if ( articolo.getImmagineArticolos().size() != 0 ){
				imgArticolo = (ImmagineArticolo) articolo.getImmagineArticolos().toArray()[0];
				this.setIcon(UtilityImage.resizeImage(new ImageIcon(imgArticolo.getFile()), 60, 60));
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
            FontMetrics met = this.getFontMetrics(getFont());            
            setPreferredSize(new Dimension(met.stringWidth(articolo.getDescrizione())+40, 100));            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initGUIRep() {
		try {
			this.setText(reparto.getDescrizione());
			this.setVerticalTextPosition(AbstractButton.BOTTOM);
			this.setHorizontalTextPosition(AbstractButton.CENTER);
			this.setFont(new Font("Dialog", Font.BOLD, 16));
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            FontMetrics met = this.getFontMetrics(getFont());            
            setPreferredSize(new Dimension(met.stringWidth(reparto.getDescrizione())+40, 100));            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getHeight() {
		return 100;
	};

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}
}
