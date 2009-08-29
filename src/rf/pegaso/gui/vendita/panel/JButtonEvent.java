package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articoli;

import java.util.EventObject;

import rf.pegaso.db.tabelle.Articolo;

public class JButtonEvent extends EventObject{

	private char m_cKey;
    private Articoli m_articolo;
    
    public JButtonEvent(Object source, char cKey, Articoli articolo) {
        super(source);
        m_cKey = cKey;
        m_articolo = articolo;
    }
    
    public char getKey() {
        return m_cKey;
    }
    
    public Articoli getArticolo(){
    	return m_articolo;
    }
    
}
