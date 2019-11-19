package us.timinc.grabbag;

import java.util.*;

import us.timinc.calc.*;

public class BagOfMarbles<T> {
	public Marble<T>[] marbles = null;

	public T grabMarble() {
		int maxWeight = getMaxWeight();
		int weightRoll = DiceRoller.rollDice(maxWeight);
		int gi;
		for (gi = 0; (gi < marbles.length) && (weightRoll > 0); gi++) {
			weightRoll -= marbles[gi].weight;
		}
		return marbles[gi - 1].value;
	}

	public ArrayList<T> grabMarbles(int amount) {
		ArrayList<T> retval = new ArrayList<>();

		// Add minimums.
		for (Marble<T> marble : marbles) {
			for (int i = 0; (i < marble.min) && (retval.size() < amount); i++) {
				retval.add(marble.value);
			}
		}

		// Randomize for remainder.
		int maxWeight = getMaxWeight();
		for (int i = 0; i < (amount - retval.size()); i++) {
			int weightRoll = DiceRoller.rollDice(maxWeight);
			int gi;
			for (gi = 0; (gi < marbles.length) && (weightRoll > 0); gi++) {
				weightRoll -= marbles[gi].weight;
			}
			retval.add(marbles[gi - 1].value);
		}

		Collections.shuffle(retval);
		return retval;
	}

	public int getMaxWeight() {
		int accumulator = 0;
		for (Marble<T> marble : marbles) {
			accumulator += marble.weight;
		}
		return accumulator;
	}
}
