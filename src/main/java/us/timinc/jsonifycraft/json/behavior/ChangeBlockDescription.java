package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class ChangeBlockDescription extends WorldBehaviorDescription {
	public String block = "minecraft:air";
	public String age = "";

	@Override
	public void behave(EventContext eventContext) {
		BlockPos pos = getPos(eventContext);
		IBlockState prevState = eventContext.getBlockState(pos);

		log("Changing block at %s to %s.", pos.toString(), block);
		if (age.isEmpty()) {
			eventContext.world.setBlockState(pos, PlaintextId.getBlockStateFrom(block));
		} else {
			eventContext.addTemp("prev_age", prevState.getBlock().getMetaFromState(prevState));
			int newAge = PlaintextCalculator.solve(eventContext.getVariables(), age);
			eventContext.world.setBlockState(pos, PlaintextId.getBlockStateFrom(block + ":" + newAge));
		}
		eventContext.clearTemps();
	}
}