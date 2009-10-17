/**
 * 
 */
package it.infolabs.pos.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import rf.utility.MathUtility;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import it.infolabs.pos.PosDriver;
import it.infolabs.pos.PosException;
import it.infolabs.pos.Ticket;
import it.infolabs.pos.TicketRow;

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
	
	// properties con i comandi da eseguire
	private Properties props=null;
	
	// stream verso la seriale
	private OutputStream out=null;
	
	
	public RCHDriver(String commPort){
		this.commPort=commPort;
		
	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#closeDeviceConnection()
	 */
	@Override
	public void closeDeviceConnection() throws PosException {
		this.serialPort.close();
		try {
			this.out.flush();
			this.out.close();
		} catch (IOException e) {
			throw new PosException("Errore chiusura device",e);
		}
		
		this.open=false;

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#cutTicket()
	 */
	@Override
	public void cutTicket() throws PosException {
		String cut=props.getProperty("cutTicket", "");
		if(cut!=null && cut.isEmpty()){
			try {
				send(cut);
			} catch (IOException e) {
				throw new PosException("Errore invio comando CUT alla stampante",e);
			}
		}

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
				out=serialPort.getOutputStream();
				this.open=true;
			} catch (PortInUseException e) {
				throw new PosException("Porta in uso da un altra applicazione",e);
			} catch (UnsupportedCommOperationException e) {
				throw new PosException("Operazione Comm non supportata",e);
			} catch (IOException e) {
				throw new PosException("Problemi apertura stream",e);
			}
		}
		
		// ora carichiamo il file di properties con i comandi
		// e le varie configurazioni da fare
		props=new Properties();
		try {
			props.load(new FileInputStream("rchcommand.properties"));
		} catch (FileNotFoundException e) {
			throw new PosException("File di properties rchcommand non trovato",e);
		} catch (IOException e) {
			throw new PosException("Errore I/O causato dal file di properties",e);
		}
	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#printTicket(it.infolabs.pos.Ticket)
	 */
	@Override
	public void printTicket(Ticket ticket) throws PosException {
		Iterator<TicketRow> it=ticket.ticketRowIterator();
		while(it.hasNext()){
			TicketRow row=it.next();
			// aggiungiamo l'iva al prezzo
			double amount=MathUtility.percentualeDaAggiungere(row.getPrezzo(), row.getIva());
			//viene moltiplicato per 100 in quanto il registratore onda accetta
			//cifre senza virgola
			int price=(int)amount*100;
			StringBuffer sb=new StringBuffer();
			sb.append("=R/").append("$").append(price).append("/*").append(row.getQta());
			// invia il comando al registratore
			try {
				send(sb.toString());
			} catch (IOException e) {
				throw new PosException("Errore invio righe ticket alla stampante",e);
			}
		}// fine while
		

	}

	private void send(String cmd) throws IOException {
		out.write(cmd.getBytes());
		out.write("\n".getBytes());
		out.flush();
		
	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#startTicket()
	 */
	@Override
	public void startTicket() throws PosException {
		String start=props.getProperty("startTicket", "");
		if(start!=null && start.isEmpty()){
			try {
				send(start);
			} catch (IOException e) {
				throw new PosException("Errore invio dati verso la porta seriale",e);
			}
		}

	}

	/* (non-Javadoc)
	 * @see it.infolabs.pos.PosDriver#stopTicket()
	 */
	@Override
	public void stopTicket() throws PosException {
		String stop=props.getProperty("stopTicket", "");
		if(stop!=null && stop.isEmpty()){
			try {
				send(stop);
			} catch (IOException e) {
				throw new PosException("Errore invio dati verso la porta seriale",e);
			}
		}

	}

	@Override
	public boolean isOpenDeviceConnection() {
		return open;
	}

}
