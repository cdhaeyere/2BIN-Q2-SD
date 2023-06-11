package ex1;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;

	private HashMap<Integer, ArrayDeque<Tache>> taches;

	public Ordonnancement(){
		taches = new HashMap<>();
		for (int i = 0; i < NIVEAU_PRIORITE_MAX; i++) {
			taches.put(i, new ArrayDeque<>());
		}
	}
	public void ajouterTache (String descriptif, int priorite){
		if (priorite > NIVEAU_PRIORITE_MAX || priorite < 1) {
			throw new IllegalArgumentException("Priorité invalide");
		}
		taches.get(priorite).add(new Tache(descriptif, priorite));
	}

	//renvoie la tache prioritaire
	//renvoie null si plus de tache presente
	public Tache attribuerTache(){
		for (int i = NIVEAU_PRIORITE_MAX; i > 0; i--) {
			if (!taches.get(i).isEmpty()) {
				return taches.get(i).remove();
			}
		}
		return null;
	}
}
