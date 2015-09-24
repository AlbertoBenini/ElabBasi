package it;
import it.ablp.Carico;
import it.ablp.Manutenzione;
import it.ablp.RecuperoDati;
import it.ablp.Sessione;
import it.ablp.Strumento;
import it.ablp.StrSel;
import it.ablp.Utilizzo;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
  private StrSel pollo;
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
    this.pollo = null;
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
      pollo = ds.getStrumSel(codice);
      }
    return "dettaglio";
  }
  
  public StrSel getPollo() {
    	return pollo;
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
 
 public void insman( String cods, String data, String durata, String numop, String iditta, String urgenza, String costo){
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
	if(!(cods==null || data==null || durata==null || numop==null || iditta==null || urgenza==null || costo==null)&&(urgenza=="bassa" || urgenza=="media"|| urgenza=="elevata")) {
		nuovaman.setCods(cods);
		nuovaman.setCosto(Float.parseFloat(costo));
		nuovaman.setData(data);
		nuovaman.setDurata(durata);
		nuovaman.setIditta(iditta);
		nuovaman.setNumop(Integer.parseInt(numop));
		nuovaman.setUrgenza(urgenza); //deve essere limitata a elevata media e bassa	
		ds.newManutenzione(nuovaman);
	}
	/*
	 * Aggiunto un else in modo da non mandare in vacca tutto se uno non mette un campo o non rispetta i valori sopra
	 */
	else return;
 }
 
 public void insutil( String cods, String datain, String dataf, String motivo, String resp, String nomed){
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
			ds.newUtilizzo(nuovoutil);
		}
		else return;
	 }
 
 public List<Manutenzione> openman(){
	 if(this.ds != null) {
		 openman=ds.getOpenman();
	 }
	 return openman;
 }
 
 public Manutenzione Editman(String cods, String data){
	 if(this.ds != null) {
		 editman=ds.setEditman(cods, data);
	 }
	 return editman;
 }
 
}
