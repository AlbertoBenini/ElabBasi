package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sessione implements Serializable{
	private static Sessione istanza;
	private ArrayList<String[]> SessioniAttive;
	
	private Sessione() {
		SessioniAttive = new ArrayList<String[]> ();
	}
	
	public static Sessione getInstance() {
		if(istanza==null) {
			istanza = new Sessione();
		}
		
		return istanza;
	}
	
	public boolean setSessione(String nome, String password) {
		boolean esiste=false;
		if(nome!=null && nome.compareTo("")!=0) {
			SessioniAttive.add(creaVettore(nome,password));
			esiste = !esiste;
		}
		
		return esiste;
		
	}
	
	
	private String[] creaVettore(String nome, String password) {
		String[] dati = new String[2];
		dati[0] = nome;
		dati[1] = password;
		return dati;
	}
	
}
