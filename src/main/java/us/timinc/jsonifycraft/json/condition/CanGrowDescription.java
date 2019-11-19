package us.timinc.jsonifycraft.json.condition;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class CanGrowDescription extends WorldConditionDescription {
	@Override
	public boolean evaluate(EventDescription event) {
		IBlockState targetBlockState = event.world.getBlockState(getPos(event.variables));

		if (targetBlockState.getBlock() instanceof IGrowable) {
			boolean retval = ((IGrowable) targetBlockState.getBlock()).canGrow(event.world, event.pos, targetBlockState,
					event.world.isRemote);
			log("%s can %sgrow.", PlaintextId.getBlockId(targetBlockState), retval ? "" : "not ");
			return retval;
		} else {
			log("%s is not a growable block.", PlaintextId.getBlockId(targetBlockState));
			return false;
		}
	}
}
