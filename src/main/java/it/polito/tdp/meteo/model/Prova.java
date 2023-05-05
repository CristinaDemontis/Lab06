//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//public List<String> cercaSequenza(List<String> parziale, int livello, List<String> citta, List<Map<Integer,Integer>> datiUmidita){ 
//			
//		if(parziale.size()==0) {
//				for(String s1: citta) {
//					parziale.add(s1);  
//			 		cercaSequenza(parziale, livello+1, citta, datiUmidita); 
//				}
//			}else {
//				for(String s: citta) {
//					boolean giorniConsecutivi = controllaTreGiorniDiFila(parziale, livello-1); 
//					boolean controllaSEIgiorni = controlloSEIgiorni(parziale, s);			    
//					
//					if(giorniConsecutivi && controllaSEIgiorni) {
//						if(parziale.size()==15) { // controllo costo (metodo)
//							List<String> soluzione = new LinkedList<>(parziale);
//							int costoSequenza = calcolaCosto(parziale, datiUmidita); 
//							if(costoSequenza < this.costoMigliore) {
//								this.costoMigliore = costoSequenza;
//								this.solMigliore = new LinkedList<>(parziale); 
//								return parziale;   // rivedere la posizione del return  
//							}
//							
//							
//						}else {	
//							parziale.add(s);
//																								
//						cercaSequenza(parziale, livello+1 , citta, datiUmidita);
//						parziale.remove(parziale.size()-1);     // back tracking																			
//					}																			
//				}else {
//					break; 
//				}
//				
//			}
//		}
//		
//		return null; 
//	}