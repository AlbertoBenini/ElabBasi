package it.ablp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import it.ablp.StrSel;

public class RecuperoDati implements Serializable {
//dati utente
	private String user = "userlab05";
	private String passwd = "cinqueOP";

//dati driver
	private String url = "jdbc:postgresql://dbserver.scienze.univr.it/dblab05";
	private String driver = "org.postgresql.Driver";

//query	
	private String queryStrum = "SELECT s.cod, s.nome, d.nomed FROM strumento s FULL JOIN incarico d ON s.cod = d.cods";
	private  String queryDettStrum = "SELECT * FROM strumento WHERE cod = '";
	private String queryAllMan="SELECT * FROM Manutenzione";
	private String queryUtilCrono="SELECT U.DataIn, U.DataF, U.motivo, U.NomeD, U.resp FROM UtStr U, Strumento S WHERE U.CodS=S.Cod ORDER BY U.DataIn DESC";
	
	/*==========================costruttori====================================*/
	public RecuperoDati() throws ClassNotFoundException {
    Class.forName( driver );
 	}
	
	/*==========================metodi====================================*/
	/*======BEAN=====*/
		//bean strumenti + collocazione
		private Strumento makeStrumBean( ResultSet rs ) throws SQLException {
			Strumento bean = new Strumento();
			bean.setNome( rs.getString( "nome" ) );
			bean.setCod( rs.getString( "cod"));
			String stato = rs.getString( "nomed" );
			if(stato==null) stato = "disponibile";
			bean.setNomed( stato );
			return bean;
	  	}
	  	
	  	private StrSel makeStrumSelBean( ResultSet rs ) throws SQLException {
			StrSel bean = new StrSel();
			bean.setCod(rs.getString( "cod"));
			bean.setNome(rs.getString( "nome"));
			bean.setWatt(rs.getFloat( "watt"));
			bean.setDitta(rs.getString( "ditta"));
			bean.setModello(rs.getString( "modello"));
			bean.setCosto(rs.getFloat( "costo"));
			bean.setAnnoaq(rs.getInt( "annoaq"));
			return bean;
	  	}
	  	
	  	private UtilCrono makeUtilCronoBean(ResultSet rs) throws SQLException{
	  		UtilCrono bean=new UtilCrono();
	  		bean.setDatain(rs.getString("datain"));
	  		bean.setDataf(rs.getString("dataf"));
	  		bean.setMotivo(rs.getString("motivo"));
	  		bean.setNomed(rs.getString("nomed"));
	  		bean.setResp(rs.getString("resp"));
	  		return bean;
	  	}
	  	
	  	private Manap makeManapBean(ResultSet rs) throws SQLException{
	  	    Manap bean=new Manap();
    	  	bean.setCods(rs.getString( "cods"));
    		bean.setData(rs.getString( "data"));
	    	bean.setDurata(rs.getString( "durata"));
    		bean.setNumop(rs.getInt( "numop"));
    		bean.setIditta(rs.getString( "iditta"));
    		bean.setUrgenza(rs.getString( "urgenza"));
    		bean.setCosto(rs.getFloat( "costo"));
		    return bean;
	  	}

  	
  	/*=====FINE BEAN=====*/
  	
  	/*===RECUPERO DATI DA DATABASE===*/
  	public List<Manap> getManap() {
  			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Manap> result = new ArrayList<Manap>();
			try {
			  // tentativo di connessione al database
			  con = DriverManager.getConnection( url, user, passwd );
			  // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			  pstmt = con.prepareStatement( queryAllMan );
			  pstmt.clearParameters();
			  // imposto i parametri della query
			  //pstmt.setInt( 1, id );
			  // eseguo la query
			  rs = pstmt.executeQuery();
			  // memorizzo il risultato dell'interrogazione in Vector di Bean
			  if( rs.next() ) {
				result.add( makeManapBean( rs ) );
			  }

			} catch( SQLException sqle ) { // Catturo le eventuali eccezioni
			  sqle.printStackTrace();

			} finally { // alla fine chiudo la connessione.
			  try {
				con.close();
			  } catch( SQLException sqle1 ) {
				sqle1.printStackTrace();
			  }
			}
			return result;
  		}
  	
  		public StrSel getStrumSel(String cod) {
  			queryDettStrum = "SELECT * FROM strumento WHERE cod = '";
  			queryDettStrum = queryDettStrum+cod+"'";
  			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			StrSel result = null;
			try {
			  // tentativo di connessione al database
			  con = DriverManager.getConnection( url, user, passwd );
			  // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			  pstmt = con.prepareStatement( queryDettStrum );
			  pstmt.clearParameters();
			  // imposto i parametri della query
			  //pstmt.setInt( 1, id );
			  // eseguo la query
			  rs = pstmt.executeQuery();
			  // memorizzo il risultato dell'interrogazione in Vector di Bean
			  if( rs.next() ) {
				result = makeStrumSelBean( rs );
			  }

			} catch( SQLException sqle ) { // Catturo le eventuali eccezioni
			  sqle.printStackTrace();

			} finally { // alla fine chiudo la connessione.
			  try {
				con.close();
			  } catch( SQLException sqle1 ) {
				sqle1.printStackTrace();
			  }
			}
			return result;
  		}
  		
  		public List<UtilCrono> getUtilizzi() {
  			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<UtilCrono> result = new ArrayList<UtilCrono>();
			try {
			  // tentativo di connessione al database
			  con = DriverManager.getConnection( url, user, passwd );
			  // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			  pstmt = con.prepareStatement( queryUtilCrono );
			  pstmt.clearParameters();
			  // imposto i parametri della query
			  //pstmt.setInt( 1, id );
			  // eseguo la query
			  rs = pstmt.executeQuery();
			  // memorizzo il risultato dell'interrogazione in Vector di Bean
			  if( rs.next() ) {
				result.add( makeUtilCronoBean( rs ) );
			  }

			} catch( SQLException sqle ) { // Catturo le eventuali eccezioni
			  sqle.printStackTrace();

			} finally { // alla fine chiudo la connessione.
			  try {
				con.close();
			  } catch( SQLException sqle1 ) {
				sqle1.printStackTrace();
			  }
			}
			return result;
  		}
  	
	  	public List<Strumento> getStrumentiDatabase() {
		// dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Strumento> result = new ArrayList<Strumento>();

		try {
		  // tentativo di connessione al database
		  con = DriverManager.getConnection( url, user, passwd );
		  // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
		  stmt = con.createStatement();
		  // eseguo l'interrogazione desiderata
		  rs = stmt.executeQuery( queryStrum );
		  // memorizzo il risultato dell'interrogazione nel Vector
		  while( rs.next() ) {
		    result.add( makeStrumBean( rs ) );
		  }

		} catch( SQLException sqle ) { // catturo le eventuali eccezioni!
		  sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
		  try {
		    con.close();
		  } catch( SQLException sqle1 ) {
		    sqle1.printStackTrace();
		  }
		}
		return result;
	  	}
	  /*===RECUPERO DATI DA DATABASE FINE===*/
}
