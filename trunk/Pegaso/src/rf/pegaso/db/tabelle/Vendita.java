package rf.pegaso.db.tabelle;

import java.util.Vector;

public class Vendita {
	
	private int codiceArticolo;
	private String codiceBarre;
	private int codiceVendita;
	private String descrizione;
	private long qta;
	private double prezzoAcquisto;
	private double prezzoVendita;
	private int iva;
	private int sconto;
	
	public Vendita() {
		this.codiceArticolo = 0;
		this.codiceBarre = "";
		this.codiceVendita = 0;
		this.descrizione = "";
		this.qta = 0;
		this.prezzoAcquisto = 0.0;
		this.prezzoVendita = 0.0;
		this.iva = 0;
		this.sconto = 0;
	}

	public int getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(int codice) {
		this.codiceArticolo = codice;
	}
	
	public int getCodiceVendita() {
		return codiceVendita;
	}

	public void setCodiceVendita(int codice) {
		this.codiceVendita = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getQta() {
		return qta;
	}

	public void setQta(Long long1) {
		this.qta = long1;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	
	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	
	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}
	
	public Vector<Object> trasformaInArray() {
		Vector<Object> v = new Vector<Object>();
		v.add(codiceArticolo);
		v.add(codiceBarre);
		v.add(descrizione);
		v.add(qta);
		v.add(prezzoVendita);
		if ( sconto == 0)
			v.add(prezzoVendita*qta);
		else
			v.add((prezzoVendita*qta)-(((prezzoVendita*qta)/100)*sconto));
		v.add(sconto);
		v.add(iva);
		return v;
	}

	public String getCodiceBarre() {
		return codiceBarre;
	}

	public void setCodiceBarre(String codiceBarre) {
		this.codiceBarre = codiceBarre;
	}

}
