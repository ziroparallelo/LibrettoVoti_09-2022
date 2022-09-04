package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVoti {
	
	//Devo centralizzare il punto di connessione tramite metodi
	
	//Devo utilizzare i preparedStatement: serio problemi di SQL Injection
	public void aggiungiVoto(String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/Libretto?user=root&password=rootroot";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			
			String sql = "INSERT INTO voti (nome, punti)"+
				    "VALUES (?, ?);";
//			String sql = "INSERT INTO voti (nome, punti)"+
//		    "VALUES ('"+nome+"','18');";
			
			PreparedStatement st = conn.prepareStatement(sql);
//			Statement st = conn.createStatement();
			
			
			st.setString(1, nome);
			st.setInt(2, punti);
			
			//Se non è select inserisco executeUpdate
			
			int res = st.executeUpdate();
//			int res = st.executeUpdate(sql);
			
			if(res==1) {
				System.out.println("Dato correttamente inserito");
				
			conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		
		 String url = "jdbc:mysql://localhost:3306/Libretto?user=root&password=rootroot";
		 
		 ProvaVoti provaVoti = new ProvaVoti();
		 provaVoti.aggiungiVoto("Economia Aziendale", 21);
		 
		 try {
			
			Connection conn = DriverManager.getConnection(url);
			
			Statement st = conn.createStatement();
			
			String sql = "SELECT * FROM Voti" ;
			ResultSet res = st.executeQuery(sql);
			
			
			while(res.next()) {
				String nome = res.getString("nome");
				int voto = res.getInt("punti");
				System.out.println(nome+": "+voto);
			}
			
			//Quando gli statement sono molti è buona norma chiuderli
			//Se chiudo la connessione è normale che chiudo anche lo statement,
			//ma quando ne ho di più magari potrei usarne altri e gli statement
			//che non mi servono potrebbero rimanere apertw (sempre durante la
			//connessione aperta)
			st.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
