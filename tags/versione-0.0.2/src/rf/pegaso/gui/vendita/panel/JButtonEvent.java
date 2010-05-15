package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articolo;
import it.infolabs.hibernate.Reparto;

import java.util.EventObject;


public class JButtonEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	private char m_cKey;
    private Articolo m_articolo;
    private Reparto m_reparto;
    
    public JButtonEvent(Object source, char cKey, Articolo articolo) {
        super(source);
        m_cKey = cKey;
        m_articolo = articolo;
    }
    
    public JButtonEvent(Object source, char cKey, Reparto reparto){
    	super(source);
        m_cKey = cKey;
        m_reparto = reparto;
    }
    
    public char getKey() {
        return m_cKey;
    }
    
    public Articolo getArticolo(){
    	return m_articolo;
    }
    
    public Reparto getReparto(){
    	return m_reparto;
    }
    
}
