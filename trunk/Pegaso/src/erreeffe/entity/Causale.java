package erreeffe.entity;
// Generated 19-nov-2008 20.32.14 by Hibernate Tools 3.2.2.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Causale generated by hbm2java
 */
public class Causale  implements java.io.Serializable {


     private long idcausale;
     private String nome;
     private Set<Ordini> ordinis = new HashSet<Ordini>(0);

    public Causale() {
    }

	
    public Causale(long idcausale, String nome) {
        this.idcausale = idcausale;
        this.nome = nome;
    }
    public Causale(long idcausale, String nome, Set<Ordini> ordinis) {
       this.idcausale = idcausale;
       this.nome = nome;
       this.ordinis = ordinis;
    }
   
    public long getIdcausale() {
        return this.idcausale;
    }
    
    public void setIdcausale(long idcausale) {
        this.idcausale = idcausale;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Ordini> getOrdinis() {
        return this.ordinis;
    }
    
    public void setOrdinis(Set<Ordini> ordinis) {
        this.ordinis = ordinis;
    }




}


