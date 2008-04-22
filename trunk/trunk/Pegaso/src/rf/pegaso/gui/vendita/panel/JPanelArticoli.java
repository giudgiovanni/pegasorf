package rf.pegaso.gui.vendita.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;


public class JPanelArticoli extends JPanel{

	private static final long serialVersionUID = 1L;
	private Vector<JNumberEventListener> m_Listeners = new Vector<JNumberEventListener>();
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
		//this.setSize(300, 200);
		//this.setLayout(new GridLayout());
		this.setVisible(true);
		this.add(getJScrollPane(), null);
	}
	
	public void caricaArticoli(LinkedList<Integer> articoli){
		try{
			pnlPulsanti.removeAll();
			GridLayout gridLayout = new GridLayout();
			pnlPulsanti.setSize(new Dimension(540, (articoli.size()*100/3)));
			gridLayout.setRows((articoli.size()/3)+1);
			gridLayout.setColumns(3);
			pnlPulsanti.setLayout(gridLayout);
			for(Integer i : articoli){
				JButtonArticolo btnArticolo = new JButtonArticolo(i);
				btnArticolo.addActionListener(new MyButtonListener(i));
				btnArticolo.setPreferredSize(new Dimension(120, 100));
				pnlPulsanti.add(btnArticolo, null);
			}
			pnlPulsanti.repaint();
			pnlPulsanti.validate();
		}
		catch(Exception exp){
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
            
            for (Enumeration<JNumberEventListener> e = m_Listeners.elements(); e.hasMoreElements();) {
                oListener = (JNumberEventListener) e.nextElement();
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
			jScrollPane.setPreferredSize(new Dimension(540, 450));
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
