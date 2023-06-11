import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MatriceDAdjacence extends Graph{

	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice= new Flight[0][0];
	private int nbAirport=0;

	public MatriceDAdjacence() {
		super();
		correspondanceAirportIndice= new HashMap<>();
		correspondanceIndiceAirport= new HashMap<>();
	}

	@Override
	// Complexité: O(1)
	protected void ajouterSommet(Airport a) {
		correspondanceAirportIndice.put(a, nbAirport);
		correspondanceIndiceAirport.put(nbAirport, a);
		nbAirport++;
		matrice = new Flight[nbAirport][nbAirport];
	}

	@Override
	// Complexité: O(1)
	protected void ajouterArc(Flight f) {
		int i = correspondanceAirportIndice.get(f.getSource());
		int j = correspondanceAirportIndice.get(f.getDestination());
		matrice[i][j] = f;
	}

	@Override
	// Complexité: O(n)
	public Set<Flight> arcsSortants(Airport a) {
		return Arrays.stream(matrice[correspondanceAirportIndice.get(a)]).filter(Objects::nonNull).collect(Collectors.toSet());
	}

	@Override
	// Complexité: O(n)
	public boolean sontAdjacents(Airport a1, Airport a2) {
		return matrice[correspondanceAirportIndice.get(a1)][correspondanceAirportIndice.get(a2)] != null
				|| matrice[correspondanceAirportIndice.get(a2)][correspondanceAirportIndice.get(a1)]
				!= null;
	}
}
