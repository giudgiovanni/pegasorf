/**
 * 
 */
package it.infolabs.pos;

import gnu.io.PortInUseException;

/**
 * @author Rocco Fusella
 *
 */
public interface PosDriver {
	
	/**
	 * Apre la connessione verso il dispositivo POS
	 * @throws PosException quando la connessione non va a buon fine
	 * @throws PortInUseException 
	 */
	public void openDeviceConnection() throws PosException;
	
	/**
	 * Chiude la connessione con il device e rilascia tutte le risorse
	 * eventualmente occupate
	 * @throws PosException 
	 */
	public void closeDeviceConnection() throws PosException;
	
	/**
	 * Avvia la stampa di un nuovo Ticket
	 * @throws PosException lanciata in caso di problemi di avvio ed inizializzazione del ticket
	 */
	public void startTicket() throws PosException;
	
	/**
	 * Ferma la stampa del ticket e chiude eventuali totali
	 * @throws PosException 
	 */
	public void stopTicket() throws PosException;
	
	/**
	 * Avvia il cutter della stampante fiscale
	 * @throws PosException 
	 */
	public void cutTicket() throws PosException;
	
	/**
	 * Manda in stampa un ticket
	 * @param ticket lo scontrino da stampare
	 * @throws PosException 
	 */
	public void printTicket(Ticket ticket) throws PosException;
	
	/**
	 * Indica se la connessione al device è aperta oppure no
	 * @return
	 */
	public boolean isOpenDeviceConnection();

}
