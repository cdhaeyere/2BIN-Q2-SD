package ex7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class AcademieAvecDesistement {

	private Map<String, ArrayDeque<String>> instruments;
	private Set<String> desistements;

	public AcademieAvecDesistement(ArrayList<String> v) {
		instruments = new HashMap<>();
		desistements = new HashSet<>();
		for (String s : v) {
			instruments.put(s, new ArrayDeque<>());
		}
	}

	public void mettreEnAttente(String instrument, String nomEleve) {
		instruments.get(instrument).add(nomEleve);
		if (desistement(nomEleve)) {
			desistements.remove(nomEleve);
		}
	}

	public void desistement(String instrument, String eleve){
		desistements.add(eleve);
	}

	public boolean desistement(String eleve){
		return desistements.contains(eleve);
	}

	//supprime uniquement l'�l�ve de la file d'attente
	public String attribuerPlace(String instrument){
		while (!instruments.get(instrument).isEmpty()) {
			String eleve = instruments.get(instrument).poll();
			if (!desistement(eleve)) {
				return eleve;
			}
		}
		return null;
	}



}
