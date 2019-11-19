package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.init.*;
import net.minecraft.util.math.*;
import us.timinc.grabbag.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.world.feature.*;

public class GrowMarbleTreeDescription extends WorldBehaviorDescription {
	public BagOfMarbles<String> logs;
	public BagOfMarbles<String> leaves;
	public int minHeight = 5;
	public int maxHeight = 8;

	private transient MarbleWorldGenTrees treeGen;

	@Override
	public void behave(EventDescription event) {
		if (treeGen == null) {
			treeGen = new MarbleWorldGenTrees(true, minHeight, maxHeight, logs, leaves, false);
		}

		BlockPos pos = getPos(event);
		log("Generating marble tree at %s.", pos);
		treeGen.generate(event.world, event.world.rand, pos);
	}
}
