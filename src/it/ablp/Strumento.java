package it.ablp;
import java.io.Serializable;
public class Strumento implements Serializable{

// === Properties ============================================================
private String nome;
private String nomed;
private String cod;

// === Methods ===============================================================

	public Strumento() {
		nome=null;
		nomed=null;
		cod=null;
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

	public void setNomed(String nomed) {
		this.nomed = nomed;
	}

	public String getNomed() {
		return this.nomed;

	}
}
