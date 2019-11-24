package us.timinc.jsonifycraft.json.behavior;

import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public class DebugDescription extends BehaviorDescription {
	public String message = "";

	@Override
	public void behave(EventContext event) {
		if (event.isRemote())
			return;
		log("Debug: " + message);
	}
}