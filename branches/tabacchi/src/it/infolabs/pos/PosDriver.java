/**
 * 
 */
package it.infolabs.pos;

/**
 * @author Rocco Fusella
 *
 */
public interface PosDriver {
	
	/**
	 * Apre la connessione verso il dispositivo POS
	 * @throws PosException quando la connessione non va a buon fine
	 */
	public void openDeviceConnection() throws PosException;
	
	/**
	 * Chiude la connessione con il device e rilascia tutte le risorse
	 * eventualmente occupate
	 */
	public void closeDeviceConnection();
	
	/**
	 * Avvia la stampa di un nuovo Ticket che può essere fiscale o no in base al 
	 * valore booleano passato
	 * @param fiscale è un valore booleano che sta ad indicare se la stampa deve essere di
	 * tipo fiscale oppure no  
	 * @throws PosException lanciata in caso di problemi di avvio ed inizializzazione del ticket
	 */
	public void startTicket(boolean fiscale) throws PosException;
	
	/**
	 * Ferma la stampa del ticket e chiude eventuali totali
	 */
	public void stopTicket();
	
	/**
	 * Avvia il cutter della stampante fiscale
	 */
	public void cutTicket();
	
	/**
	 * Aggiunge un oggetto TicketRow che rappresenta una riga dello scontrino
	 * e quindi di un articolo da aggiungere
	 */
	public void addTicketRow(TicketRow row);

}
