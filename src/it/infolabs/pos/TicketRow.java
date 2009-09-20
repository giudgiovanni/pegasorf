package it.infolabs.pos;

public class TicketRow {
	
	private int iva=0;
	
	private String descrizione;
	
	private float qta;
	
	private float prezzo;
	
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

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
	

}
