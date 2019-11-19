package us.timinc.jsonifycraft.json;

import us.timinc.jsonifycraft.event.*;

public abstract class ConditionDescription extends JsonDescription {
	public abstract boolean evaluate(EventDescription event);
}
