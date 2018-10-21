package javacaffè;

//import java.sql.Date;
import java.util.*;

public class Cliente {
	//proprietà
	String nome;
	boolean pagato;
	Date dataUltimoPag;
	
	//costruttore
	Cliente(String nome, boolean pagato, Date dataUltimoPag) {
		this.nome = nome;
		this.pagato = pagato;
		this.dataUltimoPag = dataUltimoPag;
	}
	
	//metodo scheda()	
	String scheda() {
		String scheda;
		scheda = "Nome: " + nome + "\n" +
				"Caffè Pagato: " + (pagato ? "SI" : "NO") + "\n" +
				"Data Ultimo Pagamento: " + dataUltimoPag + "\n";
		return scheda;
	}//end metodo scheda()
	
}//end class Cliente


