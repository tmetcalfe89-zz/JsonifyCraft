package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;
import us.timinc.jsonifycraft.json.behavior.*;

public abstract class TreeGenAbstract extends WorldGenAbstractTree {

	protected GrowTreeDescription description;

	public TreeGenAbstract(boolean notify, GrowTreeDescription description) {
		super(notify);
		this.description = description;
	}

	public abstract boolean isSuitableLocation(World world, BlockPos pos, int treeHeight);

	public abstract void generateLeaves(World world, BlockPos pos, int treeHeight, Random rand);

	public abstract void generateLogs(World world, BlockPos pos, int treeHeight, Random rand);
}