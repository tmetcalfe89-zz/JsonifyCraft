package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public abstract class WorldBehaviorDescription extends BehaviorDescription {
	public String pos_x = "event_x";
	public String pos_y = "event_y";
	public String pos_z = "event_z";

	private transient BlockPos pos = null;

	protected BlockPos getPos(EventDescription event) {
		return new BlockPos(PlaintextCalculator.solve(event.variables, pos_x),
				PlaintextCalculator.solve(event.variables, pos_y), PlaintextCalculator.solve(event.variables, pos_z));
	}
}
