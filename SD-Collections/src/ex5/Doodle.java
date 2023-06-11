package ex5;

import java.util.HashMap;
import java.util.Map;

public class Doodle {

	private PlageHoraire[] plages;
	private Map<String, boolean[]> disponibilitesParticipants;

	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		this.disponibilitesParticipants = new HashMap<>();
	}

	public void ajouterDisponibilites(String participant, boolean[] disponibilites) {
		if (disponibilites.length != plages.length) {
			throw new IllegalArgumentException();
		}
		disponibilitesParticipants.put(participant, disponibilites);
		for (int i = 0; i < disponibilites.length; i++) {
			if (disponibilites[i]) {
				PlageHoraire plage = plages[i];
				plage.setNbParticipantPresent(plage.getNbParticipantPresent() + 1);
			}
		}
	}

	public boolean estDisponible(String participant, PlageHoraire plage) {
		boolean[] disponibilites = disponibilitesParticipants.get(participant);
		if (disponibilites == null) {
			return false;
		}
		int indexPlage = getIndexPlage(plage);
		return disponibilites[indexPlage];
	}

	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		PlageHoraire meilleurePlage = null;
		int maximumParticipants = -1;
		int nbParticipants;
		for (PlageHoraire plage : plages) {
			nbParticipants = plage.getNbParticipantPresent();
			if (nbParticipants > maximumParticipants) {
				meilleurePlage = plage;
				maximumParticipants = nbParticipants;
			}
		}
		return meilleurePlage;
	}

	private int getIndexPlage(PlageHoraire plage) {
		for (int i = 0; i < plages.length; i++) {
			if (plages[i] == plage) {
				return i;
			}
		}
		throw new IllegalArgumentException("La plage horaire n'existe pas");
	}
}
