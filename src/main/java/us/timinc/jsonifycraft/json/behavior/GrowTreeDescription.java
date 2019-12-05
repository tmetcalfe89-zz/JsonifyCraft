package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.world.feature.*;

public class GrowTreeDescription extends WorldBehaviorDescription {
	public String leaf = "minecraft:leaves:0";
	public String log = "minecraft:log:0";
	public int minHeight = 5;
	public int maxHeight = 8;
	public boolean vines = false;
	public String treeType = "oak";
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

		WorldGenerator treeGenerator;
		switch (treeType) {
		case "oak":
			treeGenerator = new TreeGenOak(true, this);
			break;
		case "spruce":
			treeGenerator = new TreeGenPine(true, this);
			break;
		default:
			treeGenerator = new TreeGenOak(true, this);
			break;
		}

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
