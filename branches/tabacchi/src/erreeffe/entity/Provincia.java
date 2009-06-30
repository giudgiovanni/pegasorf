package erreeffe.entity;

// Generated 30-giu-2009 3.07.23 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Provincia generated by hbm2java
 */
public class Provincia implements java.io.Serializable {

	private long idprovincia;
	private String provincia;
	private String targa;
	private Set<Fornitori> fornitoris = new HashSet<Fornitori>(0);
	private Set<Clienti> clientis = new HashSet<Clienti>(0);

	public Provincia() {
	}

	public Provincia(long idprovincia, String provincia) {
		this.idprovincia = idprovincia;
		this.provincia = provincia;
	}

	public Provincia(long idprovincia, String provincia, String targa,
			Set<Fornitori> fornitoris, Set<Clienti> clientis) {
		this.idprovincia = idprovincia;
		this.provincia = provincia;
		this.targa = targa;
		this.fornitoris = fornitoris;
		this.clientis = clientis;
	}

	public long getIdprovincia() {
		return this.idprovincia;
	}

	public void setIdprovincia(long idprovincia) {
		this.idprovincia = idprovincia;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTarga() {
		return this.targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Set<Fornitori> getFornitoris() {
		return this.fornitoris;
	}

	public void setFornitoris(Set<Fornitori> fornitoris) {
		this.fornitoris = fornitoris;
	}

	public Set<Clienti> getClientis() {
		return this.clientis;
	}

	public void setClientis(Set<Clienti> clientis) {
		this.clientis = clientis;
	}

}
