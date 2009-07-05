package rf.pegaso.gui.vendita.panel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rf.pegaso.db.tabelle.DettaglioOrdine;

public class TestPannelloRiepilogoVendita {

	public static void main(String args[]){

		JPanelRiepilogoVendita vendita=new JPanelRiepilogoVendita();
		JPanel contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(vendita, BorderLayout.CENTER);
		JFrame f=new JFrame("Test");
		f.setContentPane(contentPane);
		f.pack();
		f.setVisible(true);

		//aggiungiamo qualcosa per esempio
		DettaglioOrdine o=new DettaglioOrdine();
		o.setDescrizione("prova");
		o.setPrezzoVendita(10.0);
		o.setQta(5);
		o.setIva(20);
		vendita.addDettaglioOrdine(o, false);
	}

}
