package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.json.*;

public abstract class WorldConditionDescription extends ConditionDescription {
	public String pos_x = "block_x";
	public String pos_y = "block_y";
	public String pos_z = "block_z";

	protected BlockPos getPos(HashMap<String, Integer> variables) {
		return new BlockPos(PlaintextCalculator.solve(variables, pos_x), PlaintextCalculator.solve(variables, pos_y),
				PlaintextCalculator.solve(variables, pos_z));
	}
}
