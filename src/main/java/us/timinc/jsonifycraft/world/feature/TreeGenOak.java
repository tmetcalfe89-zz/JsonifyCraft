package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.mcutil.*;

public class TreeGenOak extends TreeGenAbstract {
	public TreeGenOak(boolean notify, GrowTreeDescription description) {
		super(notify, description);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int treeHeight = rand.nextInt(description.maxHeight - description.minHeight) + description.minHeight;
		boolean flag = true;

		if ((pos.getY() >= 1) && ((pos.getY() + treeHeight + 1) <= world.getHeight())) {
			if (!isSuitableLocation(world, pos, treeHeight))
				return false;
			else {
				BlockPos down = pos.down();
				IBlockState state = world.getBlockState(down);

				if (pos.getY() < (world.getHeight() - treeHeight - 1)) {
					state.getBlock().onPlantGrow(state, world, pos.down(), pos);

					generateLeaves(world, pos, treeHeight, world.rand);

					generateLogs(world, pos, treeHeight, world.rand);

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	@Override
	public boolean isSuitableLocation(World world, BlockPos pos, int treeHeight) {
		for (int j = pos.getY(); j <= (pos.getY() + 1 + treeHeight); ++j) {
			int k = 1;

			if (j == pos.getY()) {
				k = 0;
			}

			if (j >= ((pos.getY() + 1 + treeHeight) - 2)) {
				k = 2;
			}

			BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();

			for (int l = pos.getX() - k; l <= (pos.getX() + k); ++l) {
				for (int i1 = pos.getZ() - k; i1 <= (pos.getZ() + k); ++i1) {
					if ((j >= 0) && (j < world.getHeight())) {
						if (!isReplaceable(world, mutableblockpos.setPos(l, j, i1)))
							return false;
					} else
						return false;
				}
			}
		}
		return true;
	}

	@Override
	public void generateLeaves(World world, BlockPos pos, int treeHeight, Random rand) {
		for (int i3 = (pos.getY() - 3) + treeHeight; i3 <= (pos.getY() + treeHeight); ++i3) {
			int i4 = i3 - (pos.getY() + treeHeight);
			int j1 = 1 - (i4 / 2);

			for (int k1 = pos.getX() - j1; k1 <= (pos.getX() + j1); ++k1) {
				int l1 = k1 - pos.getX();

				for (int i2 = pos.getZ() - j1; i2 <= (pos.getZ() + j1); ++i2) {
					int j2 = i2 - pos.getZ();

					if ((Math.abs(l1) != j1) || (Math.abs(j2) != j1) || ((rand.nextInt(2) != 0) && (i4 != 0))) {
						BlockPos blockpos = new BlockPos(k1, i3, i2);
						IBlockState state = world.getBlockState(blockpos);

						if (state.getBlock().isAir(state, world, blockpos)
								|| state.getBlock().isLeaves(state, world, blockpos)
								|| (state.getMaterial() == Material.VINE)) {
							setBlockAndNotifyAdequately(world, blockpos,
									PlaintextId.getBlockStateFrom(description.leaf));
						}
					}
				}
			}
		}
	}

	@Override
	public void generateLogs(World world, BlockPos pos, int treeHeight, Random rand) {
		for (int j3 = 0; j3 < treeHeight; ++j3) {
			BlockPos upN = pos.up(j3);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)
					|| (state.getMaterial() == Material.VINE)) {
				setBlockAndNotifyAdequately(world, pos.up(j3), PlaintextId.getBlockStateFrom(description.log));
			}
		}
	}
}
