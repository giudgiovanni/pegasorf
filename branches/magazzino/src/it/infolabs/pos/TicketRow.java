package it.infolabs.pos;

public class TicketRow {
	
	private long iva=0;
	
	private String descrizione;
	
	private float qta;
	
	private float prezzo;
	
	private int reparto;
	
	/**
	 * 
	 */
	public TicketRow() {
		super();
	}

	/**
	 * @param iva
	 * @param descrizione
	 * @param qta
	 * @param prezzo
	 */
	public TicketRow(int iva, String descrizione, float qta, float prezzo) {
		super();
		this.iva = iva;
		this.descrizione = descrizione;
		this.qta = qta;
		this.prezzo = prezzo;
	}

	public long getIva() {
		return iva;
	}

	public void setIva(long iva) {
		this.iva = iva;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getQta() {
		return qta;
	}

	public void setQta(float qta) {
		this.qta = qta;
	}

	/**
	 * Ritorna il prezzo del singola articolo senza iva e senza moltiplicare
	 * per la sua quantità di riga
	 * @return il prezzo in formato float
	 */
	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getReparto() {
		return reparto;
	}
	
	public void setReparto(int reparto){
		this.reparto=reparto;
	}
	
	
	

}
