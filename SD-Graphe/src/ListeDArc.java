import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListeDArc extends Graph{

	private final ArrayList<Flight> flights;

	public ListeDArc() {
		super();
		flights=new ArrayList<>();
	}

	@Override
	// Complexité: O(1)
	protected void ajouterSommet(Airport a) {
		return;
	}

	@Override
	// Complexité: O(1)
	protected void ajouterArc(Flight f) {
		flights.add(f);
	}

	@Override
	// Complexité: O(n)
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> result = new HashSet<>();
		for(Flight f : flights){
			if(f.getSource().equals(a)){
				result.add(f);
			}
		}
		return result;
	}

	@Override
	// Complexité: O(n)
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for(Flight f : flights){
			if((f.getSource().equals(a1) && f.getDestination().equals(a2)) || (f.getSource().equals(a2) && f.getDestination().equals(a1))) {
				return true;
			}
		}
		return false;
	}

}
