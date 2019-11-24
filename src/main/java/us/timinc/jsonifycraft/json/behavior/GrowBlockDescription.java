package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import us.timinc.jsonifycraft.event.*;

public class GrowBlockDescription extends WorldBehaviorDescription {
	public int stages = 1;

	@Override
	public void behave(EventContext event) {
		BlockPos pos = getPos(event);
		IBlockState iblockstate = event.getBlockState(pos);

		if (iblockstate.getBlock() instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) iblockstate.getBlock();

			for (int i = 0; i <= stages; i++) {
				if (igrowable.canGrow(event.world, pos, iblockstate, event.world.isRemote)) {
					log("Attempted to grow growable block that can no longer grow at %s.", pos.toString());
					if (!event.world.isRemote) {
						log("Growing block at %s.", pos.toString());
						igrowable.grow(event.world, event.world.rand, pos, iblockstate);
					}
				}
			}
		} else {
			log("Attempted to grow non-growable block at %s.", pos.toString());
		}
	}
}
