package us.timinc.jsonifycraft.json.condition;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class CanGrowDescription extends WorldConditionDescription {
	@Override
	public boolean evaluate(EventContext eventContext) {
		IBlockState targetBlockState = eventContext.world.getBlockState(getPos(eventContext.getVariables()));

		if (targetBlockState.getBlock() instanceof IGrowable) {
			boolean retval = ((IGrowable) targetBlockState.getBlock()).canGrow(eventContext.world,
					getPos(eventContext.getVariables()), targetBlockState, eventContext.isRemote());
			log("%s can %sgrow.", PlaintextId.getBlockId(targetBlockState), retval ? "" : "not ");
			return retval;
		} else {
			log("%s is not a growable block.", PlaintextId.getBlockId(targetBlockState));
			return false;
		}
	}
}
