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
		for (int yi = pos.getY(); yi <= pos.getY() + 1 + treeHeight; ++yi) {
			int leafSpan;

			if (yi - pos.getY() < j) {
				leafSpan = 0;
			} else {
				leafSpan = l;
			}

			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for (int xi = pos.getX() - leafSpan; xi <= pos.getX() + leafSpan; ++xi) {
				for (int zi = pos.getZ() - leafSpan; zi <= pos.getZ() + leafSpan; ++zi) {
					if (yi >= 0 && yi < world.getHeight()) {
						IBlockState state = world.getBlockState(blockpos$mutableblockpos.setPos(xi, yi, zi));

						if (!state.getBlock().isAir(state, world, blockpos$mutableblockpos.setPos(xi, yi, zi)) && !state
								.getBlock().isLeaves(state, world, blockpos$mutableblockpos.setPos(xi, yi, zi))) {
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
		int leafSpan = rand.nextInt(2);
		int someI = 1;
		int someII = 0;

		for (int hi = 0; hi <= k; ++hi) {
			int distanceFromTop = pos.getY() + treeHeight - hi;

			for (int xi = pos.getX() - leafSpan; xi <= pos.getX() + leafSpan; ++xi) {
				int distanceFromX = xi - pos.getX();

				for (int zi = pos.getZ() - leafSpan; zi <= pos.getZ() + leafSpan; ++zi) {
					int distanceFromZ = zi - pos.getZ();

					if (Math.abs(distanceFromX) != leafSpan || Math.abs(distanceFromZ) != leafSpan || leafSpan <= 0) {
						BlockPos blockpos = new BlockPos(xi, distanceFromTop, zi);
						IBlockState state = world.getBlockState(blockpos);

						if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
							this.setBlockAndNotifyAdequately(world, blockpos,
									PlaintextId.getBlockStateFrom(description.leaf));
						}
					}
				}
			}

			if (leafSpan >= someI) {
				leafSpan = someII;
				someII = 1;
				++someI;

				if (someI > l) {
					someI = l;
				}
			} else {
				++leafSpan;
			}
		}
	}

	@Override
	public void generateLogs(World world, BlockPos pos, int treeHeight, Random rand) {
		int randomHeightMod = rand.nextInt(description.maxHeight - description.minHeight);

		for (int hi = 0; hi < treeHeight - randomHeightMod; ++hi) {
			BlockPos upN = pos.up(hi);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
				this.setBlockAndNotifyAdequately(world, pos.up(hi), PlaintextId.getBlockStateFrom(description.log));
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
