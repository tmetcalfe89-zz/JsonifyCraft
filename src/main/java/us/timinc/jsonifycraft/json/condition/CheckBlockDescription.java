package us.timinc.jsonifycraft.json.condition;

import java.util.*;

import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class CheckBlockDescription extends WorldConditionDescription {
	public String block;
	public boolean includeLiquids = false;

	@Override
	public boolean evaluate(EventContext eventContext) {
		BlockPos pos = getPos(eventContext.getVariables());
		String blockId = PlaintextId.getBlockId(eventContext.getBlockState(pos));

		log("%s %s %s", blockId, (PlaintextId.matches(block, blockId) ? " matches " : " doesn't match "), block);

		return PlaintextId.matches(block, blockId);
	}
}
