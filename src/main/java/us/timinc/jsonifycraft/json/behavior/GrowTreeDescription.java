package us.timinc.jsonifycraft.json.behavior;

import java.lang.reflect.*;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraftforge.event.terraingen.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.world.feature.*;

public class GrowTreeDescription extends WorldBehaviorDescription {
	public String leaf;
	public String log;
	public int minHeight = 5;
	public int maxHeight = 8;
	public boolean vines = false;
	public String treeType;
	public boolean growFromSapling = true;

	@Override
	public void behave(EventContext event) {
		if (event.world.isRemote)
			return;

		BlockPos pos = getPos(event);
		if (!growFromSapling) {
			pos = pos.up();
		}

		if (!TerrainGen.saplingGrowTree(event.world, event.world.rand, pos))
			return;

		TreeGenAbstract treeGenerator = null;
		try {
			treeGenerator = JsonifyCraft.REGISTRIES.getTreeGenerator(treeType)
					.getConstructor(Boolean.class, GrowTreeDescription.class).newInstance(true, this);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (treeGenerator == null)
			return;

		log("Growing tree at %s.", pos.toString());

		IBlockState currentState = event.getBlockState(pos);
		event.world.setBlockToAir(pos);
		boolean prevCaptureBlockSnapshots = event.world.captureBlockSnapshots;
		event.world.captureBlockSnapshots = false;
		if (!treeGenerator.generate(event.world, event.world.rand, pos)) {
			event.world.setBlockState(pos, currentState);
		}
		event.world.captureBlockSnapshots = prevCaptureBlockSnapshots;
	}
}
