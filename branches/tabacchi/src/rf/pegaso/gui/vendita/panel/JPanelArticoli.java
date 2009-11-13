package rf.pegaso.gui.vendita.panel;

import it.infolabs.hibernate.Articoli;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import rf.pegaso.db.tabelle.Articolo;

import java.awt.GridBagLayout;


public class JPanelArticoli extends JPanel{

	private static final long serialVersionUID = 1L;
	private Vector<JButtonEventListener> m_Listeners = new Vector<JButtonEventListener>();
	private JScrollPane jScrollPane = null;
	private JPanel pnlPulsanti = null;
	private int width;
	private int ncolonne;

	/**
	 * This is the default constructor
	 */
	public JPanelArticoli(int width) {
		super();
		this.width = width;
		this.ncolonne = width/140;
		initialize();
	}
	
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
		this.setVisible(true);
		this.add(getJScrollPane(), null);
	}
	
	public void caricaArticoli(LinkedList<Articoli> articoli){
		try{
			pnlPulsanti.removeAll();
//			pnlPulsanti.setLayout(new GridBagLayout());
			pnlPulsanti.setLayout(new FlowLayout());
			int nRiga = 0, nCol = 0;
			for (Articoli art : articoli){
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.gridx = nRiga;
				gridBagConstraints.insets = new Insets(10, 10, 10, 10);
				gridBagConstraints.gridy = nCol;
				JButtonArticolo btnArticolo = new JButtonArticolo(art);
				MyButtonListener btnListener = new MyButtonListener(art);
				btnArticolo.addActionListener(btnListener);
//				btnArticolo.setPreferredSize(new Dimension(100, 100));
//				pnlPulsanti.add(btnArticolo, gridBagConstraints);
				pnlPulsanti.add(btnArticolo);
				if ( nRiga == ncolonne ){
					nRiga = 0;
					nCol++;
				}
				else{
					nRiga++;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addJButtonEventListener(JButtonEventListener listener) {
        m_Listeners.add(listener);
    }
    public void removeJButtonEventListener(JButtonEventListener listener) {
        m_Listeners.remove(listener);
    }
	
	private class MyButtonListener implements java.awt.event.ActionListener {

		private Articoli m_articolo;

		public MyButtonListener(Articoli articolo){
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
			jScrollPane.setPreferredSize(new Dimension(width, 410));
//			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
			pnlPulsanti.setLayout(new FlowLayout());
			pnlPulsanti.setPreferredSize(new Dimension(width, 410));
		}
		return pnlPulsanti;
	}
}
