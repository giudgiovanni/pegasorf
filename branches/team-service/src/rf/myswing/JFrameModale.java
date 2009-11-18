package rf.myswing;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * JFrameModale estende JFrame aggiungendo la funzionalità "modale". Per
 * funzionalità "modale" intendo la capacità di bloccare il focus (mouse ed
 * evento chiusura window) sull'ultima finestra aperta con questa modalità.
 * 
 * Esiste già la classe JDialog che dispone del metodo setModal per impostare la
 * modalità modale. Ma questa classe presenta il limite della mancanza del
 * pulsante di ridimensionamento, che nelle applicazioni comuni è
 * particolarmente usato.
 * 
 * Per rendere un JFrame modale basta impostare una propria classe che estende
 * EventQueue per rintracciare messaggi di sistema (MouseEvent, WindowEvent)
 * indirizzati ad una finestra che non è quella desiderata e non inviarli alla
 * finestra destinatario. Questo è il compito della classe CFrameEventQueue del
 * seguente codice sorgente.
 * 
 * Bisogna ricordarsi, infine, di reimpostare la coda degli eventi, facendo un
 * pop del gestore corrente. Per questo ho fatto un ovverride del metodo
 * dispose() per poter chiamare il metodo pop del nuovo gestore della coda.
 * 
 * Per impostare una finestra come modale basta chiamare il metodo setModale
 * invece del metodo setVisible.
 * 
 * @author negus
 * @author negus@negusweb.it
 * 
 */
public class JFrameModale extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean m_modale = false;
	private CFrameEventQueue frameEventQueue = null;
	
	protected Window component = null;

	public JFrameModale(Window c) {
		
		
		super();
		this.component = c;
		component.setEnabled(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public JFrameModale(String titolo, Window c) {
		super(titolo);
		this.component=c;
		component.setEnabled(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setModale() {
		m_modale = true;
		frameEventQueue = new CFrameEventQueue(this);
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(frameEventQueue);
		super.setVisible(true);

		//setAlwaysOnTop(true);
	}
	
	

	/**
	 * Bisogna ricordarsi, infine, di reimpostare la coda degli eventi, facendo
	 * un pop del gestore corrente. Per questo ho fatto un ovverride del metodo
	 * dispose() per poter chiamare il metodo pop del nuovo gestore della coda.
	 */
	public void dispose() {
		if (m_modale) {
			if (frameEventQueue != null) {
				frameEventQueue.pop();
				frameEventQueue = null;
			}
		}
		component.setEnabled(true);
		super.dispose();
	}

	/**
	 * Per rendere un JFrame modale basta impostare una propria classe che
	 * estende EventQueue per rintracciare messaggi di sistema (MouseEvent,
	 * WindowEvent) indirizzati ad una finestra che non è quella desiderata e
	 * non inviarli alla finestra destinatario. Questo è il compito della classe
	 * CFrameEventQueue del seguente codice sorgente.
	 */
	private class CFrameEventQueue extends EventQueue {
		JFrame m_frameHandle;

		public CFrameEventQueue(JFrame frameInput) {
			m_frameHandle = frameInput;
		}

		public void pop() {
			super.pop();
		}

		public void dispatchEvent(AWTEvent awte) {
			boolean m_dispatch = true;

			if (EventQueue.isDispatchThread()) {
				if (awte.getSource() instanceof JFrame) {
					if (((JFrame) awte.getSource()) != m_frameHandle) {
						if (awte instanceof MouseEvent)
							m_dispatch = false;
						if (awte instanceof WindowEvent) {
							if (((WindowEvent) awte).getID() == WindowEvent.WINDOW_CLOSING)
								m_dispatch = false;
						}
					}
				}
			}

			if (m_dispatch)
				super.dispatchEvent(awte);
		}

	}

}