package it.polito.tdp.meteo.DAO;

import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.model.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args) {
		
		MeteoDAO dao = new MeteoDAO();

		List<Rilevamento> list = dao.getAllRilevamenti();
		Map<String,String> list2 = dao.getUmiditaMediaPerOgniCitta(01); 

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		/*
		for (Rilevamento r : list) {
			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		*/
		
	//	for (String chiave : list2.keySet()) {
		//	System.out.format(chiave, " umidità media: " + list2.get(chiave) );
		//}
//		System.out.println(dao.getAllRilevamentiLocalitaMese(1, "Genova"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, "Genova"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Milano"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Milano"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Torino"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Torino"));
		
		
		List<Map<Integer,Integer>> risultato = dao.getValoriCittaUmidita(2);
		
		for(Map<Integer,Integer> m: risultato) {
			System.out.println(m); 
		}
		

	}

}
