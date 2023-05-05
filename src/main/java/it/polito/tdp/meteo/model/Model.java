package it.polito.tdp.meteo.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	
	private List<String> solMigliore; 
	private Integer costoMigliore; 
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	MeteoDAO dao; 
	Map<Citta, String> umiditaMediaPerUnDeterminatoMese; 

	public Model() {
		this.dao = new MeteoDAO(); 
		this.umiditaMediaPerUnDeterminatoMese = new HashMap<>();
		this.costoMigliore = 2000; 
		this.solMigliore = null; 
		

	}

	// of course you can change the String output with what you think works best
	public Map<String,String> getUmiditaMedia(int mese) {
		return this.dao.getUmiditaMediaPerOgniCitta(mese); 
	}
	
	public List<Map<Integer,Integer>> getValoriCittaUmiditaModel(int mese){
		return this.dao.getValoriCittaUmidita(mese);  
	}
	
	// possibile algoritmo 
	
	public String trovaSequenza(int mese) {
		List<String> citta = trovaListaCitta(); 
		List<String> parziale = new LinkedList<>(); 
		List<Map<Integer, Integer>> dati = this.dao.getValoriCittaUmidita(mese);
		cercaSequenza(parziale, 0, citta,dati);
		
		List<String> risultato = new LinkedList<>(this.solMigliore);
		String sol = "";
		for(String s: risultato) {
			sol = sol + s +" "; 
		}
	
		return "[ "+sol + "]" + " il costo ottimo è: " + this.costoMigliore; 
	}
	
	public void cercaSequenza(List<String> parziale, int livello, List<String> citta, List<Map<Integer,Integer>> datiUmidita){ 
			
		if(parziale.size()==0) {
				for(String s1: citta) {
					parziale.add(s1);  
			 		cercaSequenza(parziale, livello+1, citta, datiUmidita); 
				}
			}else {
				for(String s: citta) {
					boolean giorniConsecutivi = controllaTreGiorniDiFila(parziale, livello-1); 
					boolean controllaSEIgiorni = controlloSEIgiorni(parziale, s);
					
					if(giorniConsecutivi && controllaSEIgiorni) {
						if(parziale.size()==15) { 
							int costoSequenza = calcolaCosto(parziale, datiUmidita); 
							if(costoSequenza < this.costoMigliore) {
								this.costoMigliore = costoSequenza;
								this.solMigliore = new LinkedList<>(parziale); 
								return;   // rivedere la posizione del return  
							}
							
						}else {	
							int costoSequenza = calcolaCosto(parziale, datiUmidita); 
							if(costoSequenza>this.costoMigliore) {
								break; 
							}
							parziale.add(s);
																								
						cercaSequenza(parziale, livello+1 , citta, datiUmidita);
						parziale.remove(parziale.size()-1);     // back tracking																			
					}																			
				}else {
					break; 
				}
				
			}
		}
		
		return; 
	}
	
	public int calcolaCosto(List<String> parziale, List<Map<Integer,Integer>> dati) {
		int costo = 0;
		Map<Integer, Integer> genova = dati.get(0);
		Map<Integer, Integer> milano = dati.get(1);
		Map<Integer, Integer> torino = dati.get(2);

		for(int i=0; i<parziale.size(); i++) {
			if(i != parziale.size()-1) {
				String s1 = parziale.get(i); 
				String s2 = parziale.get(i+1); 

				if(!parziale.get(i).equals(parziale.get(i+1))) {
					costo = costo + 100; 
				}
			}
			
			if(parziale.get(i).equals("g")) {
				int umidita = genova.get(i+1);
				costo = costo + umidita; 
			}
			else if(parziale.get(i).equals("m")){
				int umidita = milano.get(i+1);
				costo = costo +umidita; 

			}
			else {
				int umidita = torino.get(i+1);
				costo = costo + umidita; 			
			}
		}
	return costo; 
	}
	
	
	
	
	
	
	
	
	public boolean controllaTreGiorniDiFila(List<String> lista, int i) { // i = livello 
		boolean possoCambiareCitta = false;
		
		//Se ho una sola città nella lista: va sempre bene
		if(i==0) {
			return true;
		}
		//Se ho 2 città nella lista
		else if(i==1) {
			if(lista.get(1).equals(lista.get(0))) {
				return true;
			}
			else {
				return false;
			}
		}
		//Se ho tre città nella lista
		else if(i==2) {
			if(lista.get(2).equals(lista.get(1))) { // dopo aggiungere un controllo 
				return true;
			}
			else {
				return false;
			}
		}
		//Se ho 4 città nella lista: va sempre bene
		else if(i==3) {
			return true;
			}
		//Se ho 5 città o più: faccio un controllo generale e
		//vedo se posso cambiare o no città
		else if(i>=4) {
			if (lista.get(i-1).equals(lista.get(i-2)) &&
					lista.get(i-2).equals(lista.get(i-3)) &&
					lista.get(i-3).equals(lista.get(i-4))) {
				possoCambiareCitta = true;
			}
			
			if (lista.get(i).equals(lista.get(i-1))) {
				return true;
			}
			else {
				if (possoCambiareCitta==true) {
					return true;
				}
				else {
					return false;
				}
			}
			
		}
			return false;
	}
	
	
	public boolean controlloSEIgiorni(List<String> parziale, String citta) {	
		int cnt=0; 
		for(String s: parziale) {
			if (s.equals(citta)) {
				cnt++; 
			}
		}
		if(cnt <= 6) {
			return true; 
		}
		else {
			return false; 
		}
	}
	
	// da implementare con la classe dao e il db
	public List<String> trovaListaCitta() {
		List<String> citta = new LinkedList<>(); 
		for(int i=0; i<3;i++) {
			if(i==0) {
				citta.add("g");
			}
			else if(i==1){
				citta.add("m");
			}else {
				citta.add("t"); 
			}
		}
		return citta; 
	}




}





















