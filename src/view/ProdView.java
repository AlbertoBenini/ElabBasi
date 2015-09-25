package view;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.RecuperoDati;
import model.Carico;
import model.Manutenzione;
import model.Sessione;
import model.StrSel;
import model.Strumento;
import model.Utilizzo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ManagedBean(name = "dati")
@SessionScoped
public class ProdView implements Serializable {

  // === Properties ============================================================

  private RecuperoDati ds;
  private List<Strumento> strumenti;
  private StrSel strumsel;
  private List<Manutenzione> manutenzione;  
  private List<Utilizzo> utilizzi;
  private Carico carico;
  private Manutenzione nuovaman;
  private Manutenzione editman;
  private Utilizzo nuovoutil;
  private List<Manutenzione> openman;
  

  
  // === Methods ===============================================================

  public ProdView() {
    this.strumenti = null;
    this.strumsel = null;
    this.manutenzione=null;
    this.utilizzi=null;
    this.carico=null;
    this.nuovaman=null;
    this.nuovoutil=null;
    this.openman=null;
    this.editman=null;
  }

  @PostConstruct
  public void initialize() {
    try {
    	this.ds = new RecuperoDati();
    } catch( ClassNotFoundException e ){
    	this.ds = null;
    }
  }
  
  public List<Strumento> getStrumenti() {
  	if(this.ds != null) {
  		strumenti = ds.getStrumentiDatabase();
  	}
  	return strumenti;
  }
  
  public String dettaglio(String codice) {
    if( this.ds != null ){
      strumsel = ds.getStrumSel(codice);
      }
    return "dettaglio";
  }
  
  public StrSel getStrumsel() {
    	return strumsel;
  }
 
  public List<Manutenzione> manutenzione(String cod){
	if(this.ds != null) {
    manutenzione=ds.getManutenzione(cod);
    }
      return manutenzione;
    }
 
 public List<Utilizzo> utilizzi(String cod){
	 if(this.ds!=null){
		 utilizzi=ds.getUtilizzi(cod);
	 }
	 return utilizzi;
 }
 
/* public Carico carico(){
	 	 return carico;
 }*/
 
 public String sessione(String nome, String password,String destinazione) {
	String url = "errore.jsf";
	String utente = ds.getUtente(nome,password);
	Sessione sessione = Sessione.getInstance();
	boolean esiste = sessione.setSessione(utente, password);
	if(esiste) {
		return destinazione;
	}	
	return url;
 }
 
 public String insman( String cods, String data, String durata, String numop, String iditta, String urgenza, String costo, String destinazione){
	this.nuovaman = new Manutenzione();
	/*
	 * To albi: La cosa che ti ha fatto perdere un pomeriggio e a me una serata è che 
	 * purtroppo gli oggetti tendono a dover essere inizializzati per poter essere usati.
	 * Vedi riga superiore al commento.
	 * Inoltre altro piccolo appunto personale, le funzioni chiamate da JSF salvo nuove scoperte
	 * Accettano come paramentro solo stringhe. Sembrerebbe.
	 * 
	 * TODO: Sistemare qua dentro in modo che siano effettuati controlli sugli input. Bisogna fare anche i controlli sui vincoli integrità
	 * 
	 * 	RIGUARDO I CONTROLLI: le uniche cose che specifica nella consegna sono di limitare i valori di urgenza a quelli sotto, quindi come controlli potremmo anche essere già a posto
	 * 
	 * C'est la vie. Per fare il melodrammatico. 
	 */
	if(!(cods==null || data==null || durata==null || numop==null || iditta==null || urgenza==null || costo==null)&&(urgenza.compareTo("bassa")==0|| urgenza.compareTo("media")==0|| urgenza.compareTo("elevata")==0)) {
		nuovaman.setCods(cods);
		nuovaman.setCosto(Float.parseFloat(costo));
		nuovaman.setData(data);
		nuovaman.setDurata(durata);
		nuovaman.setIditta(iditta);
		nuovaman.setNumop(Integer.parseInt(numop));
		nuovaman.setUrgenza(urgenza); //deve essere limitata a elevata media e bassa	
		boolean ok=ds.newManutenzione(nuovaman);
		if(ok)
			return destinazione;
	}
	/*
	 * Aggiunto un else in modo da non mandare in vacca tutto se uno non mette un campo o non rispetta i valori sopra
	 */
	 return "erroreInserimento.jsf";
 }
 
 public String insutil( String cods, String datain, String dataf, String motivo, String resp, String nomed, String destinazione){
		this.nuovoutil = new Utilizzo();
		/*
		 * To albi: La cosa che ti ha fatto perdere un pomeriggio e a me una serata è che 
		 * purtroppo gli oggetti tendono a dover essere inizializzati per poter essere usati.
		 * Vedi riga superiore al commento.
		 * Inoltre altro piccolo appunto personale, le funzioni chiamate da JSF salvo nuove scoperte
		 * Accettano come paramentro solo stringhe. Sembrerebbe.
		 * 
		 * TODO: Sistemare qua dentro in modo che siano effettuati controlli sugli input.
		 * C'est la vie. Per fare il melodrammatico. 
		 */
		if(!(cods==null || datain==null || dataf==null || motivo==null || resp==null || nomed==null)) {
			nuovoutil.setCods(cods);
			nuovoutil.setDatain(datain);
			nuovoutil.setDataf(dataf);
			nuovoutil.setMotivo(motivo);
			nuovoutil.setNomed(nomed);
			nuovoutil.setResp(resp);
			boolean ok=ds.newUtilizzo(nuovoutil);
			if(ok)
				return destinazione;
		}
		return "erroreInserimento.jsf";
	 }
 
 public List<Manutenzione> openman(){
	 if(this.ds != null) {
		 openman=ds.getOpenman();
	 }
	 return openman;
 }
 
 public String editman(String cods, String data){
	 if(this.ds != null) {
		 editman=ds.setEditman(cods, data);
	 }
	 return "manutenzione.jsf";
 }
 
 public Manutenzione getManCorrente() {
	 Manutenzione daTornare = null;
	 if(this.editman==null) {
		 this.editman = new Manutenzione();
		 this.editman.setCods("");
		 this.editman.setData("");
		 this.editman.setCosto(0);
		 this.editman.setDurata("");
		 this.editman.setIditta("");
		 this.editman.setNumop(0);
		 this.editman.setTesto("");
		 this.editman.setUrgenza("");
		 daTornare=this.editman;
	 } else {
		daTornare = this.editman;
		//this.editman = null;
		
		/*
		 * TODO:
		 * Mi è venuto in mente un modo per risolvere la questione Manutenzione
		 * che rimangono i dati dentro... Semplicemente quanto chiamiamo la pagina,
		 * se viene chiamata dal link settiamo l'oggetto vuoto, mentre
		 * se viene chiamata dalla pagina sessioni aperte riempiamo l'oggetto.
		 * Direi che è molto più semplice. Poi dobbiamo fare due parole sulla sessione...
		 * Cioè come facciamo a vedere se l'utente è loggato o no e quindi permettere l'inserimento?
		 * 
		 */
		
	 }
	 return daTornare;
 }
 
}
