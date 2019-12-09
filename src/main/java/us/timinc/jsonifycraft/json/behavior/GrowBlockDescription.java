package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import us.timinc.jsonifycraft.event.EventContext;

public class GrowBlockDescription extends WorldBehaviorDescription {
	@Override
	public void behave(EventContext event) {
		BlockPos pos = getPos(event);
		IBlockState blockState = event.getBlockState(pos);

		if (blockState.getBlock() instanceof IGrowable) {
			IGrowable growable = (IGrowable) blockState.getBlock();
			if (growable.canGrow(event.world, pos, blockState, event.world.isRemote)) {
				growable.grow(event.world, event.world.rand, pos, blockState);
			} else {
				log("Attempted to grow growable block that can no longer grow at %s.", pos.toString());
			}
		} else {
			log("Attempted to grow non-growable block at %s.", pos.toString());
		}
	}
}
