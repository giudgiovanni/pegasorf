/**
 * 
 */
package it.infolabs.pos.driver;

import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import it.infolabs.pos.PosDriver;
import it.infolabs.pos.PosException;
import it.infolabs.pos.Ticket;

/**
 * @author Admin
 *
 */
public class RCHDriver implements PosDriver {
	
	
	// porta comm da utilizzare per la connessione
	private String commPort;
	
	// identificativo della porta comm aperta.
	private CommPortIdentifier id;
	
	// indica se la conneccione con device è aperta oppure no
	private boolean open;
	
	// porta seriale aperta per la comunicazione
	private SerialPort serialPort; 
	
	
	public RCHDriver(String commPort){
		this.commPort=commPort;
	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#closeDeviceConnection()
	 */
	@Override
	public void closeDeviceConnection() {
		this.serialPort.close();
		this.open=false;

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#cutTicket()
	 */
	@Override
	public void cutTicket() {
		System.out.println("CUT");

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#openDeviceConnection()
	 */
	@Override
	public void openDeviceConnection() throws PosException {
		// effettuiamo una enumerazione su tutte le porte disponibili
		Enumeration en = CommPortIdentifier.getPortIdentifiers();
		boolean trovato=false;
		while (en.hasMoreElements() && !trovato) {
		  CommPortIdentifier tmp = (CommPortIdentifier)en.nextElement();
		  if(tmp.getName().equalsIgnoreCase(commPort)){
			  this.id=tmp;
			  trovato=true;
		  }
		}
		if(!trovato){
			throw new PosException("Nessuna porta: "+commPort+" è stata trovata per la connessione");
		}else{
			// una volta trovata apriamo la connessione
			try {
				serialPort =(SerialPort) id.open("pegaso", 5000);
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				this.open=true;
			} catch (PortInUseException e) {
				throw new PosException("Porta in uso da un altra applicazione",e);
			} catch (UnsupportedCommOperationException e) {
				throw new PosException("Operazione Comm non supportata",e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#printTicket(it.infolabs.pos.Ticket)
	 */
	@Override
	public void printTicket(Ticket ticket) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#startTicket()
	 */
	@Override
	public void startTicket() throws PosException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#stopTicket()
	 */
	@Override
	public void stopTicket() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOpenDeviceConnection() {
		return open;
	}

}
