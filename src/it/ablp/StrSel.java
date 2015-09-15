package it.ablp;
import java.io.Serializable;
public class StrSel implements Serializable{

// === Properties ============================================================
private String nome;
private String cod;
private float watt;
private String ditta;
private String modello;
private float costo;
private int annoaq;
/*debug*/
private int prova;

// === Methods ===============================================================

	public StrSel() {
		nome=null;
		cod=null;
		watt=0;
		ditta=null;
		modello=null;
		costo=0;
		annoaq=0;
		/*debug*/
		prova=0;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public String getCod() {
		return this.cod;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setDitta(String ditta) {
		this.ditta = ditta;
	}

	public String getDitta() {
		return this.ditta;
	}
	
	public void setModello (String modello) {
		this.modello = modello;
	}

	public String getModello() {
		return this.modello;
	}
	
	public void setWatt(float watt) {
		this.watt = watt;
	}

	public float getWatt() {
		return this.watt;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getCosto() {
		return this.costo;
	}
	
	public void setAnnoaq(int annoaq) {
		this.annoaq = annoaq;
	}

	public int getAnnoaq() {
		return this.annoaq;
	}
	
	/*debug*/
	public void setProva(int prova) {
		this.prova=prova;
	}
	
	public int getProva() {
		return this.prova;
	}
	/////////////////////
}
