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
import java.util.LinkedList;
import java.util.List;
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
	
	
	public RCHDriver(){
		
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
		// ora carichiamo il file di properties con i comandi
		// e le varie configurazioni da fare
		props=new Properties();
		try {
			props.load(new FileInputStream("rch.properties"));
		} catch (FileNotFoundException e) {
			throw new PosException("File di properties rch non trovato",e);
		} catch (IOException e) {
			throw new PosException("Errore I/O causato dal file di properties",e);
		}
		this.commPort=props.getProperty("name");
		
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
				int boundrate=new Integer(props.getProperty("bound", "9600"));
				int databits=new Integer(props.getProperty("databits", "8"));
				int stopbits=new Integer(props.getProperty("stopbits", "1"));
				int parity=new Integer(props.getProperty("parity", "0"));
				serialPort.setSerialPortParams(boundrate, databits,stopbits, parity);
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
			String reparto="";
			switch(row.getReparto()){
			case 0: reparto="=R";
			case 1: reparto="=R1";
			case 2: reparto="=R2";
			case 3: reparto="=R3";
			case 4: reparto="=R4";
			case 5: reparto="=R5";
			default: reparto="=R";
			}
			if(row.getQta()>1){
				sb.append(reparto).append("$").append(price).append("/*").append(row.getQta());
			}else{
				sb.append(reparto).append("$").append(price);
			}
			
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
	
	public List getAllCommPort(){
		// effettuiamo una enumerazione su tutte le porte disponibili
		List<String> elenco=new LinkedList<String>();
		Enumeration en = CommPortIdentifier.getPortIdentifiers();
		boolean trovato=false;
		while (en.hasMoreElements()) {
		  CommPortIdentifier tmp = (CommPortIdentifier)en.nextElement();
		  elenco.add(tmp.getName());
		}
		return elenco;
	}
	
	
	public static void main(String args[]){
		RCHDriver driver=new RCHDriver();
		List<String> elenco=driver.getAllCommPort();
		Iterator<String> it=elenco.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
