package javacaffè;

import java.io.File;
import java.util.Scanner;

public class MainBar {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//leggo il percorso del file clienti.txt da un file di configurazione
		Scanner config = new Scanner(new File("src/javacaffè/config.txt"));
		String path = config.nextLine();
		
		Bar b = new Bar("Big Data Bar", path); //"src/javacaffè/clienti.txt"
		
		Scanner tastiera = new Scanner(System.in);
		String comando = "";
		
		while (!comando.equalsIgnoreCase("esci")) {
			String risposta = "";
			System.out.println("Inserisci un comando (digita help per l'elenco dei comandi disponibili):");
			comando = tastiera.nextLine();
			
			switch(comando.toLowerCase()) {
			case "schede":
				risposta = b.scheda();
				break;	
			case "roulette":
				risposta = b.chiPagaOggi();
				break;
			case "reset":
				risposta = b.resetPagato();
				break;
			case "update":
				System.out.println("Chi ha offerto il caffè oggi?: ");
				String whois = tastiera.nextLine();
				//System.out.println(whois);
				risposta = b.updPagato(whois);
				break;
			case "help":
				risposta = "- 'schede': fornisce l'elenco dei clienti del bar \n" +
			               "- 'roulette': estrae a sorte chi oggi offrirà il caffè a tutti \n" +
			               "- 'reset': resetta il giro caffè \n" +
			               "- 'update': aggiorna il giro caffè con chi ha offerto oggi \n" +
						   "- 'esci': termina il programma \n";
				break;
			}//end switch
			System.out.println(risposta);			
		}//end while
		config.close();
		tastiera.close();
	}

}
