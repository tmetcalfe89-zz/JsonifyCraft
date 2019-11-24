package us.timinc.jsonifycraft.event;

import java.util.*;

import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;

public class EventProcessor {
	public static boolean process(EventContext eventContext, ReactorDescription[] events, String eventName) {
		if (events == null)
			return false;

		boolean didSomething = false;

		for (ReactorDescription event : events) {
			ReactorDescription reactor = event;
			JsonifyCraft.log("Evaluating %s on %s side.", eventName, eventContext.getSide());
			if (!reactor.event.equals(eventName)) {
				continue;
			}
			for (ReactionDescription reaction : reactor.reactions) {
				if (Arrays.stream(reaction.conditions).allMatch(e -> e.evaluate(eventContext))) {
					Arrays.stream(reaction.behaviors).forEach(e -> e.behave(eventContext));
					didSomething = true;
				}
			}
		}
		return didSomething;
	}
}
