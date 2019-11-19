package us.timinc.jsonifycraft.json.condition;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class RollDiceDescription extends ConditionDescription {
	public int sides = 6;
	public int roll = 1;

	@Override
	public boolean evaluate(EventDescription event) {
		int rolled = event.world.rand.nextInt(sides);
		boolean retval = rolled < roll;
		log("Dice roll: %s/%s, target <%s.", rolled, sides, roll);
		return retval;
	}
}
