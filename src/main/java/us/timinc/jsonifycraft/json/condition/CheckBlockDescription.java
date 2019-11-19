package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class CheckBlockDescription extends WorldConditionDescription {
	public String block = "";
	public boolean includeLiquids = false;

	@Override
	public boolean evaluate(EventDescription event) {
		BlockPos pos = getPos(event.variables);
		String blockId = PlaintextId.getBlockId(event.world.getBlockState(pos));

		log("%s %s %s", blockId, (PlaintextId.matches(block, blockId) ? " matches " : " doesn't match "), block);

		return PlaintextId.matches(block, blockId);
	}

	@Override
	protected BlockPos getPos(HashMap<String, Integer> variables) {
		if (includeLiquids) {
			HashMap<String, Integer> tempVariables = new HashMap<>();
			tempVariables.put("event_x", variables.get("event_rtx"));
			tempVariables.put("event_y", variables.get("event_rty"));
			tempVariables.put("event_z", variables.get("event_rtz"));
			return new BlockPos(PlaintextCalculator.solve(tempVariables, pos_x),
					PlaintextCalculator.solve(tempVariables, pos_y), PlaintextCalculator.solve(tempVariables, pos_z));
		}

		return super.getPos(variables);
	}
}
