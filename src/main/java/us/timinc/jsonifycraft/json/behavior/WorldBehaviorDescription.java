package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;

public abstract class WorldBehaviorDescription extends BehaviorDescription {
	public String pos_x = "block_x";
	public String pos_y = "block_y";
	public String pos_z = "block_z";

	protected BlockPos getPos(EventContext event) {
		return new BlockPos(PlaintextCalculator.solve(event.getVariables(), pos_x),
				PlaintextCalculator.solve(event.getVariables(), pos_y),
				PlaintextCalculator.solve(event.getVariables(), pos_z));
	}
}
