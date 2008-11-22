package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Aggiunta generated by hbm2java
 */
public class Aggiunta implements java.io.Serializable {

	private int id;
	private String descrizione;
	private Double prezzo;
	private String info;
	private Integer cat;
	private Set<Scelti> sceltis = new HashSet<Scelti>(0);

	public Aggiunta() {
	}

	public Aggiunta(int id) {
		this.id = id;
	}

	public Aggiunta(int id, String descrizione, Double prezzo, String info,
			Integer cat, Set<Scelti> sceltis) {
		this.id = id;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.info = info;
		this.cat = cat;
		this.sceltis = sceltis;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getCat() {
		return this.cat;
	}

	public void setCat(Integer cat) {
		this.cat = cat;
	}

	public Set<Scelti> getSceltis() {
		return this.sceltis;
	}

	public void setSceltis(Set<Scelti> sceltis) {
		this.sceltis = sceltis;
	}

}
