package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class NotDescription extends ConditionDescription {
	ConditionDescription[] conditions = null;

	@Override
	public boolean evaluate(EventContext event) {
		log("-=-not start-=-");
		boolean retval = !Arrays.stream(conditions).anyMatch(e -> e.evaluate(event));
		log("-=-not end-=-");
		return retval;
	}
}
