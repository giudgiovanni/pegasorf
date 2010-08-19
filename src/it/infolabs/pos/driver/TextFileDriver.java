package it.infolabs.pos.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import rf.utility.MathUtility;

import it.infolabs.pos.PosDriver;
import it.infolabs.pos.PosException;
import it.infolabs.pos.Ticket;
import it.infolabs.pos.TicketRow;

public class TextFileDriver implements PosDriver {
	
	// properties con i comandi da eseguire
	private Properties props=null;
	
	// Path della cartella dove salvare il file TXT che il Server RHC provvederˆ a stampare
	private String printerFolderPath = "";
	
	// Stringa testuale che rappresenta lo scontrino
	private String scontrino = "";
	
	public TextFileDriver() throws PosException {
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
		this.printerFolderPath = props.getProperty("printerFolderPath");
	}

	@Override
	public void closeDeviceConnection() throws PosException {
		// Inviare il File txt nella cartella di stampa		
		File f = new File(printerFolderPath + new Date().toString() + ".txt");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(scontrino.getBytes());
			fos.close();
		} catch (Exception e) {
			throw new PosException(e.getMessage());
		}
		return;
	}

	@Override
	public void cutTicket() throws PosException {
		String cut=props.getProperty("cutTicket", "");
		if(cut!=null && !cut.isEmpty()){
			scontrino = scontrino.concat(cut).concat("\n");
		}
	}

	@Override
	public boolean isOpenDeviceConnection() {
		return true;
	}

	@Override
	public void openDeviceConnection() throws PosException {
		return;
	}

	@Override
	public void printTicket(Ticket ticket) throws PosException {
		Iterator<TicketRow> it=ticket.ticketRowIterator();
		while(it.hasNext()){
			TicketRow row=it.next();
			// aggiungiamo l'iva al prezzo
			//double amount = row.getPrezzo() + MathUtility.percentualeDaAggiungere(row.getPrezzo(), row.getIva()); 
			//viene moltiplicato per 100 in quanto il registratore onda accetta
			//cifre senza virgola
			//int price=(int)(amount*100);
			int price = ((int)(row.getPrezzo() * 100)) + ((int)(MathUtility.percentualeDaAggiungere(row.getPrezzo(), row.getIva()) * 100));
			StringBuffer sb=new StringBuffer();
			String reparto="";
			int id = row.getReparto();
			switch(id){
			case 0: 
				reparto="=R";
				break;
			case 1: 
				reparto="=R1";
				break;
			case 2: 
				reparto="=R2";
				break;
			case 3: 
				reparto="=R3";
				break;
			case 4: 
				reparto="=R4";
				break;
			case 5: 
				reparto="=R5";
				break;
			default: 
				reparto="=R";
				break;
			}
			if(row.getQta()>1){
				sb.append(reparto).append("/$").append(price).append("/*").append(row.getQta());
			}else{
				sb.append(reparto).append("/$").append(price);
			}
			scontrino = scontrino.concat(sb.toString()).concat("\n");
		}// fine while
	}

	@Override
	public void startTicket() throws PosException {
		String start=props.getProperty("startTicket", "");
		if(start!=null && !start.isEmpty()){
			scontrino = scontrino.concat(start).concat("\n");
		}
	}

	@Override
	public void stopTicket() throws PosException {
		String stop=props.getProperty("stopTicket", "");
		if(stop!=null && !stop.isEmpty()){
			scontrino = scontrino.concat(stop).concat("\n");
		}
	}

}
