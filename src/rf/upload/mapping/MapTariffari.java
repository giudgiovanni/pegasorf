package rf.upload.mapping;

public class MapTariffari {
	
	private String categoria;
	private String codice;
	private String denominazione;
	private double prezzoAlKilo;
	private double prezzoAlPezzo;
	private String tipoConfezione;
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getDenominazione() {
		return denominazione;
	}
	
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	
	public double getPrezzoAlKilo() {
		return prezzoAlKilo;
	}
	
	public void setPrezzoAlKilo(double prezzoAlKilo) {
		this.prezzoAlKilo = prezzoAlKilo;
	}
	
	public double getPrezzoAlPezzo() {
		return prezzoAlPezzo;
	}
	
	public void setPrezzoAlPezzo(double prezzoAlPezzo) {
		this.prezzoAlPezzo = prezzoAlPezzo;
	}
	
	public String getTipoConfezione() {
		return tipoConfezione;
	}
	
	public void setTipoConfezione(String tipoConfezione) {
		this.tipoConfezione = tipoConfezione;
	}

}
