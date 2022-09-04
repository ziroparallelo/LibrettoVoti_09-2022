package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib = new Libretto();
		lib.addVoto(new Voto("Analisi 1", 30));
		lib.addVoto(new Voto("Fisica 1", 28));
		
		lib.addVoto(new Voto("Informatica", 25));
		lib.addVoto(new Voto("Algebra", 25));
		

		System.out.println(lib);
		
		Libretto lib25 = lib.filtraPunti(25);
		
		System.out.println(lib25);
		
		Voto voto1 = new Voto("Analisi 1", 25);
		
		if(lib.isDuplicato(voto1))
		System.out.println("Conflitto");
		else
			System.out.println("Non c'è duplicato");
	}

}
