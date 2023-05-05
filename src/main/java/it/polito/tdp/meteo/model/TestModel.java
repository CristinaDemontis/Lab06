package it.polito.tdp.meteo.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		//Map<String,String> list2 = m.getUmiditaMedia(12); 
		
	/*	for (String chiave : list2.keySet()) {
			System.out.format(chiave, " umidità media: " + list2.get(chiave) );
		} */
		
	//	System.out.println(m.getUmiditaMedia(12));
		
	//	System.out.println(m.trovaSequenza(5));
		

//		List<String> lista = new LinkedList<>(); 
//		
//		String[] array = {"t","t","t","g","g","t","m","m","m","m","t","t","t","t","t"}; 
//		
//		for(int i=0; i<array.length; i++) {
//			lista.add(array[i]);
//		}
//		
//		System.out.println(lista);
		
		//boolean TreGiorni = m.controllaTreGiorniDiFila(lista,5); 
		
		//System.out.println("flag 3 gg: "+TreGiorni); 
		
		//boolean seiGiorni = m.controlloSEIgiorni(lista, "t");
		
		//System.out.println("flag 6 gg: "+seiGiorni); 
		
//		for(int j=0; j<15; j++) {
//			boolean TreGiorni2 = m.controllaTreGiorniDiFila(lista,j);
//			System.out.println("flag giorno "+(j+1)+ ": "+TreGiorni2); 
//
//
//		}
		
		String prova = m.trovaSequenza(2); 
		System.out.println(prova);
		
//		List<Map<Integer, Integer>> dati = m.getValoriCittaUmiditaModel(2);
//		System.out.println(dati); 
//		System.out.println(lista); 
//
//		int costo = m.calcolaCosto(lista, dati); 
//		System.out.println(costo);

		
		
		
		
		
		

	}

}
