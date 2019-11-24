package us.timinc.jsonifycraft.json.condition;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class RollDiceDescription extends ConditionDescription {
	public int sides = 6;
	public int target = 1;

	@Override
	public boolean evaluate(EventContext event) {
		int rolled = event.world.rand.nextInt(sides);
		boolean retval = rolled < target;
		log("Dice roll: %s/%s, target <%s.", rolled, sides, target);
		return retval;
	}
}
