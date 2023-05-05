package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		return null; 
	}

	
	public Map<String,String> getUmiditaMediaPerOgniCitta(int mese){
		
		String sql = "SELECT Localita, AVG(umidita) AS umiditaMedia "
				+ "FROM situazione "
				+ "WHERE MONTH(DATA) = ? "
				+ "GROUP BY Localita"; 
		
		Map<String, String> risultato = new HashMap<>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,mese);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String citta = rs.getString("Localita"); 
				String umidita_media = rs.getString("umiditaMedia"); 
				risultato.put(citta, umidita_media); 
			}
			rs.close();
			st.close();
			conn.close();
			return risultato;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// ci facciamo una mappa bro...
	  
public List<Map<Integer, Integer>>   getValoriCittaUmidita(int mese){
		
		String sql = "SELECT Localita, Umidita , Data "
				+ "FROM situazione "
				+ "WHERE MONTH(DATA) = ? "
				+ "AND DAY(DATA) <= 15 "
				+ "AND DAY(DATA) >= 1 "
				+ "ORDER BY Localita, DATA ASC "; 
		
		List<Map<Integer, Integer>> risultato = new LinkedList<>(); 
		Map<Integer, Integer> torino = new HashMap<>(); 
		Map<Integer, Integer> genova = new HashMap<>();
		Map<Integer, Integer> milano = new HashMap<>();
		Integer cntT =0;
		Integer cntG =0;
		Integer cntM =0;


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,mese);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String citta = rs.getString("Localita"); 
				Integer umidita = rs.getInt("Umidita");
				if(citta.equals("Genova")){
					cntG ++; 
					genova.put(cntG, umidita); 
				}
				else if(citta.equals("Milano")) {
					cntM ++; 
					milano.put(cntM, umidita); 
				}
				else  {
					cntT ++; 
					torino.put(cntT, umidita);
				}	
			}
			risultato.add(genova); 
			risultato.add(milano); 
			risultato.add(torino); 
			rs.close();
			st.close();
			conn.close();
			return risultato;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

}
















