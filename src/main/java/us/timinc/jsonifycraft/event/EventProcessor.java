package us.timinc.jsonifycraft.event;

import java.util.*;

import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;

public class EventProcessor {
	public static boolean process(EventDescription eventDescription, ReactorDescription[] events, String eventName) {
		if (events == null)
			return false;

		boolean didSomething = false;

		for (ReactorDescription event : events) {
			ReactorDescription reactor = event;
			JsonifyCraft.LOGGER.info(
					"Evaluating " + eventName + " on " + (eventDescription.world.isRemote ? "client" : "server") + ".");
			if (!reactor.event.equals(eventName)) {
				continue;
			}
			for (ReactionDescription reaction : reactor.reactions) {
				if (Arrays.stream(reaction.conditions).allMatch(e -> e.evaluate(eventDescription))) {
					Arrays.stream(reaction.behaviors).forEach(e -> e.behave(eventDescription));
					didSomething = true;
				}
			}
		}
		return didSomething;
	}
}
