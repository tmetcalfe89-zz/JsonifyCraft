package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class GrowTreeDescription extends WorldBehaviorDescription {
	public String leaf = "minecraft:leaves:0";
	public String wood = "minecraft:log:0";
	public int height = 5;
	public boolean vines = false;

	private transient WorldGenTrees treeGenerator = null;

	@Override
	public void behave(EventDescription event) {
		if (event.world.isRemote)
			return;

		if (treeGenerator == null) {
			treeGenerator = new WorldGenTrees(true, height, PlaintextId.getBlockStateFrom(wood),
					PlaintextId.getBlockStateFrom(leaf), vines);
		}
		BlockPos pos = getPos(event);

		log("Growing tree at %s.", pos.toString());

		treeGenerator.generate(event.world, event.world.rand, pos);
	}
}
