package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articolo;

import java.util.EventObject;


public class JButtonEvent extends EventObject{

	private char m_cKey;
    private Articolo m_articolo;
    
    public JButtonEvent(Object source, char cKey, Articolo articolo) {
        super(source);
        m_cKey = cKey;
        m_articolo = articolo;
    }
    
    public char getKey() {
        return m_cKey;
    }
    
    public Articolo getArticolo(){
    	return m_articolo;
    }
    
}
