package rf.pegaso.gui.vendita.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import rf.pegaso.db.tabelle.Articolo;

import erreeffe.entity.Articoli;

import java.awt.GridBagLayout;


public class JPanelArticoli extends JPanel{

	private static final long serialVersionUID = 1L;
	private Vector<JButtonEventListener> m_Listeners = new Vector<JButtonEventListener>();
	private JScrollPane jScrollPane = null;
	private JPanel pnlPulsanti = null;

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
//		this.setSize(600, 600);
		//this.setLayout(new GridLayout());
		this.setVisible(true);
		this.add(getJScrollPane(), null);
	}
	
	public void caricaArticoli(LinkedList<Articolo> articoli){
		try{
			pnlPulsanti.removeAll();
			GridLayout gridLayout = new GridLayout();
			pnlPulsanti.setSize(new Dimension(600, (articoli.size()*100/4)));
			gridLayout.setRows((articoli.size()/4)+1);
			gridLayout.setColumns(3);
			pnlPulsanti.setLayout(gridLayout);
			for(Articolo art : articoli){
				JButtonArticolo btnArticolo = new JButtonArticolo(art);
				btnArticolo.addActionListener(new MyButtonListener(art));
				btnArticolo.setPreferredSize(new Dimension(100, 80));
				pnlPulsanti.add(btnArticolo, null);
			}
			pnlPulsanti.repaint();
			pnlPulsanti.validate();
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
		
	}
	
	public void addJButtonEventListener(JButtonEventListener listener) {
        m_Listeners.add(listener);
    }
    public void removeJButtonEventListener(JButtonEventListener listener) {
        m_Listeners.remove(listener);
    }
	
	private class MyButtonListener implements java.awt.event.ActionListener {

		private Articolo m_articolo;

		public MyButtonListener(Articolo articolo){
			m_articolo = articolo;
		}
		public void actionPerformed(java.awt.event.ActionEvent evt) {
	           
            JButtonEvent oEv = new JButtonEvent(JPanelArticoli.this, '\0', m_articolo);            
            JButtonEventListener oListener;
            
            for (Enumeration<JButtonEventListener> e = m_Listeners.elements(); e.hasMoreElements();) {
                oListener = (JButtonEventListener) e.nextElement();
                oListener.keyPerformed(oEv);
            }
        }
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setPreferredSize(new Dimension(470, 490));
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setViewportView(getPnlPulsanti());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes pnlPulsanti	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlPulsanti() {
		if (pnlPulsanti == null) {
			pnlPulsanti = new JPanel();
			pnlPulsanti.setLayout(new GridBagLayout());
		}
		return pnlPulsanti;
	}
}
