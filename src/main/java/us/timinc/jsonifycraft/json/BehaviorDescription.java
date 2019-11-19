package us.timinc.jsonifycraft.json;

import us.timinc.jsonifycraft.event.*;

public abstract class BehaviorDescription extends JsonDescription {
	public abstract void behave(EventDescription event);
}