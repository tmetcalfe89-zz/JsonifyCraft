package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class OrDescription extends ConditionDescription {
	public ConditionDescription[] conditions = null;

	@Override
	public boolean evaluate(EventDescription event) {
		log("-=-or start-=-");
		boolean retval = Arrays.stream(conditions).anyMatch(e -> e.evaluate(event));
		log("-=-or end-=-");
		return retval;
	}
}
