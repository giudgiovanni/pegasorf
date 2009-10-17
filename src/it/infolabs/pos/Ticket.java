package it.infolabs.pos;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import rf.utility.MathUtility;

public class Ticket {
	
	// scontrino di tipo fiscale
	public static final int FISCALE=0;
	
	// scontrino di tipo non fiscale e quindi da usare per esempio per le sigarette
	public static final int NON_FISCALE=1;
	
	// lista di tutte le righe da stampare dello scontrino
	private List<TicketRow> rows=null;
	
	// il tipo di scontrino
	private int type;
	
	/**
	 * Costruttore di default che inizializza lo scontrino a scontrino di tipo fiscale
	 */
	public Ticket(){
		type=FISCALE;
		rows=new LinkedList<TicketRow>();
	}
	
	/**
	 * Inizializza lo scontrino in base al tipo passato per 
	 * parametro.
	 * @param type
	 */
	public Ticket(int type){
		this.type=type;
		rows=new LinkedList<TicketRow>();
	}
	
	/**
	 * Ritorna un iteratore dei ticket row
	 * @return ListIterator
	 */
	public ListIterator<TicketRow> ticketRowIterator(){
		ListIterator<TicketRow> list=null;
		if(this.rows!=null){
			list=this.rows.listIterator();
			return list;
		}
		return list;
	}


	/**
	 * Aggiunge un oggetto TicketRow che altro non è che una riga da stampare
	 * @param row è la riga da inserire
	 * @return ritorna un booleano se il TicketRow è stato inserito correttamente
	 */
	public boolean addTicketRow(TicketRow row) {
		return rows.add(row);
	}

	/**
	 * Calcella l'intero contenuto della lista che contiene tutte le righe da stampare
	 */
	public void clearTickets() {
		rows.clear();
	}

	/**
	 * Controlla se un TicktRow è presente o no nella lista delle righe da 
	 * stampare ed in caso affermativo ritorna un valore booleano true altrimenti false
	 * @param row la riga da verificare se presente nella lista
	 * @return ritorna un valore booleno se presente o no.
	 */
	public boolean containsTicketRow(TicketRow row) {
		return rows.contains(row);
	}

	/**
	 * Ritorna un elemento TicketRow in base all'indice passato
	 * @param index è l'indice che indica la posizione in cui cercare. Se non presente ritorna null
	 * @return un oggetto TicketRow se presente altrimenti null
	 */
	public TicketRow get(int index) {
		return rows.get(index);
	}

	/**
	 * Controlla se la lista delle righe da stampare è vuota.
	 * @return un valore booleano
	 */
	public boolean isEmpty() {
		return rows.isEmpty();
	}

	/**
	 * Ritorna il numero di righe da stampare inserite
	 * @return ritorna un valore intero
	 */
	public int size() {
		return rows.size();
	}
	
	/**
	 * Ritorna il totale dello scontrino compreso di iva
	 * @return ritorna un valore float
	 */
	public float getTotaleTicket(){
		
		float totale=0f;
		ListIterator<TicketRow> li=rows.listIterator();
		while(li.hasNext()){
			TicketRow row=li.next();
			totale+=row.getQta()*MathUtility.percentualeDaAggiungere(row.getPrezzo(), row.getIva());
		}
		return totale;
		
	}
	
	
	

}
