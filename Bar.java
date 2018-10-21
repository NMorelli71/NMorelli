package javacaffè;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class Bar {
	//proprietà
	String nome;
	Cliente [] clienti;
	
	//costruttore1
	Bar(String nome, Cliente [] clienti) {
		this.nome = nome;
		this.clienti = clienti;
	}
	
	//costruttore2
	Bar(String nome, String path) throws Exception {
		this.nome = nome;
		
		Scanner dati = new Scanner(new File(path));
		int nCli = Integer.parseInt(dati.nextLine());
		//specifico il formato della stringa che contiene la data da leggere
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy"); 
		
		clienti = new Cliente [nCli];
		for (int i =0; i<clienti.length; i++) {
			clienti[i] = new Cliente (dati.nextLine(),
					                  dati.nextLine().equalsIgnoreCase("si"),
					                  data.parse(dati.nextLine()) //converto la stringa in data
					                  );
		}		
		dati.close();
	}//end costruttore2
	
	//metodo chiPagaOggi()
	String chiPagaOggi() {
		String who = "";
		boolean found = false;
		int random = 0;
		
		while (!found) {
			random = (int)(Math.random()*clienti.length);
			Cliente c = clienti[random];
			if (!c.pagato) {
				found = true;
				who = c.nome;
			}				
		}				
		return "Il vincitore della roulette caffè è... " + who + "!";
	}//end chiPagaOggi
	
	//metodo sOggi(): ritorna una stringa contenente la data di oggi nel formato specificato
	String sOggi() {
		String sOggi = "";
		//specifico il formato della stringa che contiene la data da scrivere
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	   
		//ricavo la data di oggi e la converto in stringa nel formato scelto
		Calendar c = Calendar.getInstance();
		Date oggi = c.getTime();
		sOggi = data.format(oggi);
		return sOggi;
	}//end sOggi
	
	//metodo updPagato(String nome)
	String updPagato (String pNome) throws Exception {
		String updPagato = "";
		//creo un oggetto di tipo FileWriter che scrive su un file
		FileWriter w;
		w = new FileWriter("src/javacaffè/clienti.txt");	
	   
		//creo un oggetto di tipo BufferedWriter che scrive stringhe
		BufferedWriter b;
		b = new BufferedWriter(w);
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		   
	    //nella 1^ riga del file scrivo il n° di clienti
	    b.write(clienti.length + "\n");
	 	for (int i=0; i<clienti.length; i++) {
			if (clienti[i].nome.equalsIgnoreCase(pNome)) {
				clienti[i].pagato = true;
				clienti[i].dataUltimoPag = Calendar.getInstance().getTime(); //data di oggi
			}
			if (i == (clienti.length-1)) { //se sto scrivendo l'ultima riga poi non non vado a capo
				b.write(clienti[i].nome + "\n" +
					   (clienti[i].pagato ? "si\n" : "no\n") +
					   data.format(clienti[i].dataUltimoPag));
				}
				else {
					b.write(clienti[i].nome + "\n" +
						   (clienti[i].pagato ? "si\n" : "no\n") +
						   data.format(clienti[i].dataUltimoPag) + "\n"); 
				  }
	 	}
		b.flush();
		b.close();
		updPagato = "Grazie " + pNome + " per aver offerto il caffè a tutti!";
		return updPagato;
	}
	
	//metodo resetPagato
    String resetPagato() throws Exception {
	   String resetPagato = "";
	   
	   //creo un oggetto di tipo FileWriter che scrive su un file
	   FileWriter w;
	   w = new FileWriter("src/javacaffè/clienti.txt");	
	   
	   //creo un oggetto di tipo BufferedWriter che scrive stringhe
	   BufferedWriter b;
	   b = new BufferedWriter(w);
	   
	   SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	   
	   //nella 1^ riga del file scrivo il n° di clienti
	   b.write(clienti.length + "\n");
	   
	   for (int i=0; i<clienti.length; i++) {
		  clienti[i].pagato = false;	
		  if (i == (clienti.length-1)) {
		  b.write(clienti[i].nome + "\n" +
				   "no" + "\n" +
				   data.format(clienti[i].dataUltimoPag));
		  }
		  else {
			  b.write(clienti[i].nome + "\n" +
					   "no" + "\n" +
					   data.format(clienti[i].dataUltimoPag) + "\n"); 
		  }
		}   
	   b.flush();
	   b.close();
	   resetPagato = "Inizia una nuova settimana lavorativa! Si riparte da zero!";
	   return resetPagato;
   	}//end resetPagato
	
	//metodo scheda()
	String scheda() {
		String scheda = "";
		
		for(int i=0; i<clienti.length; i++) {
			scheda += clienti[i].scheda() +
					"-----------------------------" + "\n";
		}
		scheda = "Nome Bar: " + nome + "\n\n" + scheda;
		return scheda;
		}//end scheda()

}// end class Bar
