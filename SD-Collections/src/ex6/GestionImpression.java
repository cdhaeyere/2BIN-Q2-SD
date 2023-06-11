package ex6;

import java.util.TreeMap;

public class GestionImpression {

	private TreeMap<String, Impression> impressions;

	public void ajouterImpression(Impression impr){
		impressions.put(impr.getIdUtilisateur(), impr);
	}

	public Impression selectionnerImpression(){
		if (impressions.isEmpty()) {
			return null;
		}
		return impressions.firstEntry().getValue();
	}
}
