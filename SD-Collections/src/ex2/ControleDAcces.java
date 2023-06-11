package ex2;

import java.util.HashMap;
import java.util.HashSet;

public class ControleDAcces {

	private HashSet<Employe> employesDansBatiment;
	private HashMap<Badge, Employe> employes;

	public ControleDAcces(){
		employesDansBatiment = new HashSet<>();
		employes = new HashMap<>();
	}

	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e) {
		if (employes.containsKey(b)) {
			throw new IllegalArgumentException("Badge déjà utilisé");
		}
		employes.put(b, e);
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b) {
		if (!employes.containsKey(b)) {
			throw new IllegalArgumentException("Badge inconnu");
		}
		employesDansBatiment.add(employes.get(b));
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b) {
		if (!employes.containsKey(b)) {
			throw new IllegalArgumentException("Badge inconnu");
		}
		employesDansBatiment.remove(employes.get(b));
	}

	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e) {
		return employesDansBatiment.contains(e);
	}

}
