package ex7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Academie {

	private HashMap<String, ArrayDeque<String>> instruments;

	public Academie(ArrayList<String> v) {
		instruments = new HashMap<>();
		for (String s : v) {
			instruments.put(s, new ArrayDeque<>());
		}
	}

	public void mettreEnAttente(String instrument, String nomEleve) {
		instruments.get(instrument).add(nomEleve);
	}

	// supprime uniquement l'�l�ve de la file d'attente et le renvoie
	// renvoie null s�il n�y a pas d��l�ve en attente pour cet instrument
	public String attribuerPlace(String instrument){
		return instruments.get(instrument).poll();
	}

}
