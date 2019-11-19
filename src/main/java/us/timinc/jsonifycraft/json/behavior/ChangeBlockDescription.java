package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class ChangeBlockDescription extends WorldBehaviorDescription {
	public String block = "minecraft:air";
	public String age = "0";

	@Override
	public void behave(EventDescription event) {
		BlockPos pos = getPos(event);
		IBlockState prevState = event.world.getBlockState(pos);
		event.addVariable("prev_age", prevState.getBlock().getMetaFromState(prevState));
		int newAge = PlaintextCalculator.solve(event.variables, age);
		log("Changing block at %s to %s.", pos.toString(), block);
		event.world.setBlockState(pos, PlaintextId.getBlockStateFrom(block + ":" + newAge));
		event.variables.remove("prev_age");
	}
}