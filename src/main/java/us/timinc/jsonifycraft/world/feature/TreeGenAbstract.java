package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.properties.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;
import us.timinc.jsonifycraft.json.behavior.*;

public abstract class TreeGenAbstract extends WorldGenAbstractTree {

	protected GrowTreeDescription description;

	public TreeGenAbstract(Boolean notify, GrowTreeDescription description) {
		super(notify);
		this.description = description;
	}

	public abstract boolean isSuitableLocation(World world, BlockPos pos, int treeHeight);

	public abstract void generateLeaves(World world, BlockPos pos, int treeHeight, Random rand);

	public abstract void generateLogs(World world, BlockPos pos, int treeHeight, Random rand);

	public void addVine(World worldIn, BlockPos pos, PropertyBool prop) {
		setBlockAndNotifyAdequately(worldIn, pos,
				Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true)));
	}

	public void addHangingVine(World worldIn, BlockPos pos, PropertyBool prop) {
		this.addVine(worldIn, pos, prop);
		int i = description.minHeight - 1;

		for (BlockPos blockpos = pos.down(); worldIn.isAirBlock(blockpos) && i > 0; --i) {
			this.addVine(worldIn, blockpos, prop);
			blockpos = blockpos.down();
		}
	}
}