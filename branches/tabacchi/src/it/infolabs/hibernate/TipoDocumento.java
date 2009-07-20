package it.infolabs.hibernate;

// Generated 21-lug-2009 1.39.59 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * TipoDocumento generated by hbm2java
 */
public class TipoDocumento implements java.io.Serializable {

	private long iddocumento;
	private String tipo;
	private String descrizione;
	private Set<Ordini> ordinis = new HashSet<Ordini>(0);
	private Set<Ordini> ordinis_1 = new HashSet<Ordini>(0);
	private Set<Carichi> carichis = new HashSet<Carichi>(0);
	private Set<Ordini> ordinis_2 = new HashSet<Ordini>(0);
	private Set<Carichi> carichis_1 = new HashSet<Carichi>(0);
	private Set<Carichi> carichis_2 = new HashSet<Carichi>(0);

	public TipoDocumento() {
	}

	public TipoDocumento(long iddocumento, String tipo) {
		this.iddocumento = iddocumento;
		this.tipo = tipo;
	}

	public TipoDocumento(long iddocumento, String tipo, String descrizione,
			Set<Ordini> ordinis, Set<Ordini> ordinis_1, Set<Carichi> carichis,
			Set<Ordini> ordinis_2, Set<Carichi> carichis_1,
			Set<Carichi> carichis_2) {
		this.iddocumento = iddocumento;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.ordinis = ordinis;
		this.ordinis_1 = ordinis_1;
		this.carichis = carichis;
		this.ordinis_2 = ordinis_2;
		this.carichis_1 = carichis_1;
		this.carichis_2 = carichis_2;
	}

	public long getIddocumento() {
		return this.iddocumento;
	}

	public void setIddocumento(long iddocumento) {
		this.iddocumento = iddocumento;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Ordini> getOrdinis() {
		return this.ordinis;
	}

	public void setOrdinis(Set<Ordini> ordinis) {
		this.ordinis = ordinis;
	}

	public Set<Ordini> getOrdinis_1() {
		return this.ordinis_1;
	}

	public void setOrdinis_1(Set<Ordini> ordinis_1) {
		this.ordinis_1 = ordinis_1;
	}

	public Set<Carichi> getCarichis() {
		return this.carichis;
	}

	public void setCarichis(Set<Carichi> carichis) {
		this.carichis = carichis;
	}

	public Set<Ordini> getOrdinis_2() {
		return this.ordinis_2;
	}

	public void setOrdinis_2(Set<Ordini> ordinis_2) {
		this.ordinis_2 = ordinis_2;
	}

	public Set<Carichi> getCarichis_1() {
		return this.carichis_1;
	}

	public void setCarichis_1(Set<Carichi> carichis_1) {
		this.carichis_1 = carichis_1;
	}

	public Set<Carichi> getCarichis_2() {
		return this.carichis_2;
	}

	public void setCarichis_2(Set<Carichi> carichis_2) {
		this.carichis_2 = carichis_2;
	}

}
