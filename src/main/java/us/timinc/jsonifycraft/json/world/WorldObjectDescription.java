package us.timinc.jsonifycraft.json.world;

import java.util.*;

import us.timinc.jsonifycraft.json.*;

public abstract class WorldObjectDescription extends JsonDescription {
	public String name;
	public String creativeTab = "MISC";
	public ReactorDescription[] events = new ReactorDescription[0];

	public boolean hasEvent(String eventName) {
		if (events == null)
			return false;

		return Arrays.stream(events).anyMatch(e -> e.event.equals(eventName));
	}
}
