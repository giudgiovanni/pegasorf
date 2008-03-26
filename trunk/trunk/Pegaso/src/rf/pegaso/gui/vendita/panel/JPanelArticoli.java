package rf.pegaso.gui.vendita.panel;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JPanel;

import rf.pegaso.db.tabelle.Articolo;
import rf.pegaso.db.tabelle.Icona;

public class JPanelArticoli extends JPanel {

	private static final long serialVersionUID = 1L;
	private Vector m_Listeners = new Vector();

	/**
	 * This is the default constructor
	 */
	public JPanelArticoli() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridLayout());
	}
	
	public void caricaArticoli(LinkedList<Integer> articoli){
		try{
			Icona ico = new Icona();
			ico.caricaDati((Integer)articoli.get(1));
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(calcolaRighe(ico.getWidth()));
			gridLayout.setColumns(calcolaColonne(ico.getHight()));
			this.setLayout(gridLayout);
			for(Integer i : articoli){
				JButtonArticolo btnArticolo = new JButtonArticolo(i);
				btnArticolo.addActionListener(new MyButtonListener(i));
				this.add(btnArticolo, null);
			}
		}
		catch(SQLException exp){
			exp.printStackTrace();
		}
		
	}
	
	public void addJNumberEventListener(JNumberEventListener listener) {
        m_Listeners.add(listener);
    }
    public void removeJNumberEventListener(JNumberEventListener listener) {
        m_Listeners.remove(listener);
    }
	
	private class MyButtonListener implements java.awt.event.ActionListener {

		private int m_idArticolo;

		public MyButtonListener(int idArticolo){
			m_idArticolo = idArticolo;
		}
		public void actionPerformed(java.awt.event.ActionEvent evt) {
	           
            JNumberEvent oEv = new JNumberEvent(JPanelArticoli.this, '\0', m_idArticolo);            
            JNumberEventListener oListener;
            
            for (Enumeration e = m_Listeners.elements(); e.hasMoreElements();) {
                oListener = (JNumberEventListener) e.nextElement();
                oListener.keyPerformed(oEv);
            }
        }
	}

	private int calcolaRighe(int width){
		int righe = this.getWidth() / width;
		return righe;
	}

	private int calcolaColonne(int height){
		int colonne = this.getHeight() / height;
		return colonne;
	}
}
