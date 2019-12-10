package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.mcutil.*;

public class TreeGenPine extends TreeGenAbstract {
	private int treeHeight;
	private int j;
	private int k;
	private int l;

	public TreeGenPine(Boolean notify, GrowTreeDescription description) {
		super(notify, description);
	}

	@Override
	public boolean isSuitableLocation(World world, BlockPos pos, int treeHeight) {
		for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + treeHeight; ++i1) {
			int j1;

			if (i1 - pos.getY() < j) {
				j1 = 0;
			} else {
				j1 = l;
			}

			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
				for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1; ++l1) {
					if (i1 >= 0 && i1 < world.getHeight()) {
						IBlockState state = world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1));

						if (!state.getBlock().isAir(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1)) && !state
								.getBlock().isLeaves(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void generateLeaves(World world, BlockPos pos, int treeHeight, Random rand) {
		int i3 = rand.nextInt(2);
		int j3 = 1;
		int k3 = 0;

		for (int l3 = 0; l3 <= k; ++l3) {
			int j4 = pos.getY() + treeHeight - l3;

			for (int i2 = pos.getX() - i3; i2 <= pos.getX() + i3; ++i2) {
				int j2 = i2 - pos.getX();

				for (int k2 = pos.getZ() - i3; k2 <= pos.getZ() + i3; ++k2) {
					int l2 = k2 - pos.getZ();

					if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
						BlockPos blockpos = new BlockPos(i2, j4, k2);
						IBlockState state = world.getBlockState(blockpos);

						if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
							this.setBlockAndNotifyAdequately(world, blockpos,
									PlaintextId.getBlockStateFrom(description.leaf));
						}
					}
				}
			}

			if (i3 >= j3) {
				i3 = k3;
				k3 = 1;
				++j3;

				if (j3 > l) {
					j3 = l;
				}
			} else {
				++i3;
			}
		}
	}

	@Override
	public void generateLogs(World world, BlockPos pos, int treeHeight, Random rand) {
		int i4 = rand.nextInt(3);

		for (int k4 = 0; k4 < treeHeight - i4; ++k4) {
			BlockPos upN = pos.up(k4);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
				this.setBlockAndNotifyAdequately(world, pos.up(k4), PlaintextId.getBlockStateFrom(description.log));
			}
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		treeHeight = rand.nextInt(description.maxHeight - description.minHeight) + description.minHeight;
		j = 1 + rand.nextInt(2);
		l = 2 + rand.nextInt(2);
		k = treeHeight - j;

		if (pos.getY() >= 1 && pos.getY() + treeHeight + 1 <= world.getHeight()) {
			if (!this.isSuitableLocation(world, pos, treeHeight)) {
				return false;
			} else {
				BlockPos down = pos.down();
				IBlockState state = world.getBlockState(down);

				if (state.getBlock().canSustainPlant(state, world, down, net.minecraft.util.EnumFacing.UP,
						(net.minecraft.block.BlockSapling) Blocks.SAPLING)
						&& pos.getY() < world.getHeight() - treeHeight - 1) {
					state.getBlock().onPlantGrow(state, world, down, pos);

					generateLeaves(world, pos, treeHeight, rand);

					generateLogs(world, pos, treeHeight, rand);

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

}
