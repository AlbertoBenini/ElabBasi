package it;
import it.ablp.Carico;
import it.ablp.Manutenzione;
import it.ablp.RecuperoDati;
import it.ablp.Sessione;
import it.ablp.Strumento;
import it.ablp.StrSel;
import it.ablp.UtilCrono;
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
  private List<UtilCrono> utilizzi;
  private Carico carico;
  private Manutenzione nuovaman;

  // === Methods ===============================================================

  public ProdView() {
    this.strumenti = null;
    this.pollo = null;
    this.manutenzione=null;
    this.utilizzi=null;
    this.carico=null;
    this.nuovaman=null;
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
 
 public List<UtilCrono> utilizzi(String cod){
	 if(this.ds!=null){
		 utilizzi=ds.getUtilizzi(cod);
	 }
	 return utilizzi;
 }
 
 public Carico carico(){
	 	 return carico;
 }
 
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
 
 public  String insman( String cods, String data, String durata, int numop, String iditta, String urgenza, float costo){
	 String pippo="orcoboia";
	//String resultquery=null;
	if(cods==null || data==null ||durata==null || numop==0 || iditta==null || urgenza==null || costo<=0.0f ) 
		return null;
	nuovaman.setCods(cods);
	nuovaman.setCosto(costo);
	nuovaman.setData(data);
	nuovaman.setDurata(durata);
	nuovaman.setIditta(iditta);
	nuovaman.setNumop(numop);
	nuovaman.setUrgenza(urgenza);
	//resultquery="INSERT INTO manutenzione Values '"+cods+"',"+"'"+data+"',"+"'"+durata+"',"+"'"+numop+"',"+"'"+iditta+"',"+"'"+urgenza+"',"+"'"+costo+")";
	ds.newManutenzione(nuovaman);
	return pippo;
 }
 
}
