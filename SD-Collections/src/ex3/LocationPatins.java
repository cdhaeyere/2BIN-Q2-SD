package ex3;

import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class LocationPatins {

	private HashMap<Integer, Deque<Integer>> pointures;
	private HashMap<Integer, LocalTime> casiersOccupees;

	private final int[] lockers;

	public LocationPatins(int[] lockers) {
		pointures = new HashMap<>();
		casiersOccupees = new HashMap<>();
		for (int i = 0; i < lockers.length; i++) {
			if (lockers[i] < 33 || lockers[i] > 48)
				throw new IllegalArgumentException();
			if (!pointures.containsKey(lockers[i]))
				pointures.put(lockers[i], new ArrayDeque<>());
			pointures.get(lockers[i]).add(i);
		}

		this.lockers = lockers;
	}

	private static double prix(LocalTime date1, LocalTime date2) {
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();
		if (!pointures.containsKey(pointure))
			throw new IllegalArgumentException();
		if (pointures.get(pointure).isEmpty())
			return -1;
		int locker = pointures.get(pointure).removeFirst();
		casiersOccupees.put(locker, l);
		return locker;
	}

	public double libererCasier(int numeroCasier) {
		if (!casiersOccupees.containsKey(numeroCasier))
			throw new IllegalArgumentException();
		double p = prix(casiersOccupees.remove(numeroCasier), LocalTime.now());
		pointures.get(lockers[numeroCasier]).add(numeroCasier);
		return p;
	}
}
