package it.ablp;
import java.io.Serializable;
public class Manap implements Serializable{

    private String cods;
    private String data;
    private String durata;
    private int numop;
    private String iditta;
    private String urgenza;
    private float costo;

	public Manap() {
		cods=null;
		data=null;
		durata=null;
		numop=0;
		iditta=null;
        urgenza=null;
        costo=0.0f;
	}
	
	
	public void setCods(String cods) {
		this.cods = cods;
	}
	
	public String getCods() {
		return this.cods;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return this.data;
	}
	
	public void setDurata(String durata) {
		this.durata = durata;
	}
	
	public String getDurata() {
		return this.durata;
	}
	
	public void setNumop(int numop) {
		this.numop = numop;
	}
	
	public int getNumop() {
		return this.numop;
	}
	
	public void setIditta(String iditta) {
		this.iditta = iditta;
	}
	
	public String getIditta() {
		return this.iditta;
	}
	
	public void setUrgenza(String urgenza) {
		this.urgenza = urgenza;
	}
	
	public String getUrgenza() {
		return this.urgenza;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public float getcosto() {
		return this.costo;
	}
	
	
	
	
	
}
