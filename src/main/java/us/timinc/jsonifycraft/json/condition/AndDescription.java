package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class AndDescription extends ConditionDescription {
	ConditionDescription[] conditions = null;

	@Override
	public boolean evaluate(EventDescription event) {
		log("-=-and start-=-");
		boolean retval = Arrays.stream(conditions).allMatch(e -> e.evaluate(event));
		log("-=-and end-=-");
		return retval;
	}
}
