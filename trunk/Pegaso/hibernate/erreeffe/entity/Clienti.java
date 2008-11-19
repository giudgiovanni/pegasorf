package erreeffe.entity;
// Generated 19-nov-2008 20.32.14 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Clienti generated by hbm2java
 */
public class Clienti  implements java.io.Serializable {


     private long idcliente;
     private Date dataInserimento;
     private String nome;
     private String cognome;
     private String piva;
     private String codfisc;
     private String via;
     private String cap;
     private String citta;
     private String provincia;
     private String tel;
     private String cell;
     private String fax;
     private String email;
     private String website;
     private String note;
     private Date dataNascita;
     private String documento;
     private String numDoc;
     private Date rilasciatoIl;
     private String rilasciatoDa;
     private String natoA;
     private String intestazione;
     private String nazionalita;
     private Set<Ordini> ordinis = new HashSet<Ordini>(0);

    public Clienti() {
    }

	
    public Clienti(long idcliente, String nome, String cognome) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.cognome = cognome;
    }
    public Clienti(long idcliente, Date dataInserimento, String nome, String cognome, String piva, String codfisc, String via, String cap, String citta, String provincia, String tel, String cell, String fax, String email, String website, String note, Date dataNascita, String documento, String numDoc, Date rilasciatoIl, String rilasciatoDa, String natoA, String intestazione, String nazionalita, Set<Ordini> ordinis) {
       this.idcliente = idcliente;
       this.dataInserimento = dataInserimento;
       this.nome = nome;
       this.cognome = cognome;
       this.piva = piva;
       this.codfisc = codfisc;
       this.via = via;
       this.cap = cap;
       this.citta = citta;
       this.provincia = provincia;
       this.tel = tel;
       this.cell = cell;
       this.fax = fax;
       this.email = email;
       this.website = website;
       this.note = note;
       this.dataNascita = dataNascita;
       this.documento = documento;
       this.numDoc = numDoc;
       this.rilasciatoIl = rilasciatoIl;
       this.rilasciatoDa = rilasciatoDa;
       this.natoA = natoA;
       this.intestazione = intestazione;
       this.nazionalita = nazionalita;
       this.ordinis = ordinis;
    }
   
    public long getIdcliente() {
        return this.idcliente;
    }
    
    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }
    public Date getDataInserimento() {
        return this.dataInserimento;
    }
    
    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getPiva() {
        return this.piva;
    }
    
    public void setPiva(String piva) {
        this.piva = piva;
    }
    public String getCodfisc() {
        return this.codfisc;
    }
    
    public void setCodfisc(String codfisc) {
        this.codfisc = codfisc;
    }
    public String getVia() {
        return this.via;
    }
    
    public void setVia(String via) {
        this.via = via;
    }
    public String getCap() {
        return this.cap;
    }
    
    public void setCap(String cap) {
        this.cap = cap;
    }
    public String getCitta() {
        return this.citta;
    }
    
    public void setCitta(String citta) {
        this.citta = citta;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCell() {
        return this.cell;
    }
    
    public void setCell(String cell) {
        this.cell = cell;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Date getDataNascita() {
        return this.dataNascita;
    }
    
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
    public String getDocumento() {
        return this.documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNumDoc() {
        return this.numDoc;
    }
    
    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
    public Date getRilasciatoIl() {
        return this.rilasciatoIl;
    }
    
    public void setRilasciatoIl(Date rilasciatoIl) {
        this.rilasciatoIl = rilasciatoIl;
    }
    public String getRilasciatoDa() {
        return this.rilasciatoDa;
    }
    
    public void setRilasciatoDa(String rilasciatoDa) {
        this.rilasciatoDa = rilasciatoDa;
    }
    public String getNatoA() {
        return this.natoA;
    }
    
    public void setNatoA(String natoA) {
        this.natoA = natoA;
    }
    public String getIntestazione() {
        return this.intestazione;
    }
    
    public void setIntestazione(String intestazione) {
        this.intestazione = intestazione;
    }
    public String getNazionalita() {
        return this.nazionalita;
    }
    
    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }
    public Set<Ordini> getOrdinis() {
        return this.ordinis;
    }
    
    public void setOrdinis(Set<Ordini> ordinis) {
        this.ordinis = ordinis;
    }




}


