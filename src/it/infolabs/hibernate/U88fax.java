package it.infolabs.hibernate;

// Generated 10-lug-2009 2.47.41 by Hibernate Tools 3.2.4.GA

/**
 * U88fax generated by hbm2java
 */
public class U88fax implements java.io.Serializable {

	private long id;
	private Integer kilogrammi;
	private Integer grammi;
	private String codiceAams;

	public U88fax() {
	}

	public U88fax(long id) {
		this.id = id;
	}

	public U88fax(long id, Integer kilogrammi, Integer grammi, String codiceAams) {
		this.id = id;
		this.kilogrammi = kilogrammi;
		this.grammi = grammi;
		this.codiceAams = codiceAams;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getKilogrammi() {
		return this.kilogrammi;
	}

	public void setKilogrammi(Integer kilogrammi) {
		this.kilogrammi = kilogrammi;
	}

	public Integer getGrammi() {
		return this.grammi;
	}

	public void setGrammi(Integer grammi) {
		this.grammi = grammi;
	}

	public String getCodiceAams() {
		return this.codiceAams;
	}

	public void setCodiceAams(String codiceAams) {
		this.codiceAams = codiceAams;
	}

}