package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List;

public class Libretto {
	
	//Definire variabili tramite interfacce 
	//Nello specifico poi quando creo l'oggetto utilizzo quello che mi serve
	private List<Voto> voti;
	
	public Libretto() {
		this.voti = new ArrayList<Voto>();
	}
	
	//Meno sa una classe dell'altra e meglio è
	
	//Ho delegato alla classe il compito di aggiungere
	/**
	 * 
	 * @param v
	 * @return true se l'addizione è andata a buon fine
	 * , false altrimenti
	 */
	public boolean addVoto(Voto v) {
		if( !this.isDuplicato(v) && !this.isConflitto(v) ) {
			this.voti.add(v);
			return true;
		}
		return false;
		
	}
	
	//Inizialmente scrivo così perché ho un urgenza di testare
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto filtraPunti(int punti) {
		Libretto result = new Libretto();
		for(Voto v : this.voti) {
			if(v.getPunti() == punti)
				
				//Se avessi avuto dei controlli da fare
				//avrei ridelegato in questo modo l'aggiunta
				//del voto invece di inserire:
				//result.voti.add(v);
				result.addVoto(v);
		}
		return result;
	}
	//Devo documentare la funzione in caso di ricerca
	//che può creare problemi
	/**
	 * Restituisce il punteggio ottenuto all'esame di cui
	 * specifico il nome
	 * @param nome Nome dell'esame
	 * @return punteggio numerico, oppure (@code null) se l'esame non esiste
	 */
	public Integer puntiEsame(String nome) {
		for(Voto v: this.voti)
			if(v.getNome().compareTo(nome)==0)
				return v.getPunti();
//	    return -1;
		//Posso usare il meccanismo delle eccezioni se
		//non trovo il valore
		
		return null;
//			throw new IllegalArgumentException("Corso non trovato");
	}
	
	public boolean isDuplicato(Voto v) {
		for(Voto v1: this.voti)
			//Non è la classe che si occupa di confrontare
			//gli elementi, definisco equals e hashcode
			//solo per vedere se sono uguali
//			if((v1.getNome().equals(v.getNome()) && 
//					v1.getPunti() == v.getPunti())
//					return true;
			if(v1.equals(v))
				return true;
		return false;
					
	}
	
	public List<Voto> getVoti(){
		return this.voti;
	}
	
	
//	Uso i metodi che già ho
	public boolean isConflitto(Voto v) {
		Integer punti = this.puntiEsame(v.getNome());
		if (punti != null && punti != v.getPunti())
			return true;
		else
			return false;
	} 
	
	public Libretto votiPlus() {
		Libretto nuovo = new Libretto();
		
		//La variabile v sta puntando all'interno della Lista
		//NON E' UNA COPIA DI OGNI OGGETTO DI VOTI
		for(Voto v: this.voti) {
			int punti = v.getPunti();
			if(punti >= 24)
				punti += 2;
			else
				punti++;
			if(punti>30)
				punti = 30;
			
			//NOOO v non è una copia, ma andrei così a modificare
			//gli oggetti del libretto vecchio
			
//			v.setVoto(punti);
//			nuovo.addVoto(v);
			
			nuovo.addVoto(new Voto(v.getNome(), punti));
		}
		return nuovo;
	}
	
	public void cancellaVotiMinori(int punti) {
		for(int i =0; i<this.voti.size(); i++)
			if(this.voti.get(i).getPunti()<= punti)
				this.voti.remove(i);
	}
}
