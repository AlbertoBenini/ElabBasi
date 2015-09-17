package it;
import it.ablp.Manap;
import it.ablp.RecuperoDati;
import it.ablp.Strumento;
import it.ablp.StrSel;
import it.ablp.UtilCrono;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;


@ManagedBean(name = "dati")
@SessionScoped
public class ProdView implements Serializable {

  // === Properties ============================================================

  private RecuperoDati ds;
  private List<Strumento> strumenti;
  private StrSel pollo;
  private List<Manap> manutenzione;  
  private List<UtilCrono> utilizzi;

  // === Methods ===============================================================

  public ProdView() {
    this.strumenti = null;
    this.pollo = null;
    this.manutenzione=null;
    this.utilizzi=null;
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
 
  public List<Manap> manutenzione(String cod){
	if(this.ds != null) {
    manutenzione=ds.getManap(cod);
    }
      return manutenzione;
      }
 
 public List<UtilCrono> utilizzi(String cod){
	 if(this.ds!=null){
		 utilizzi=ds.getUtilizzi(cod);
	 }
	 return utilizzi;
 }
}
