package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.mcutil.*;

public class TreeGenOak extends TreeGenAbstract {
	public TreeGenOak(Boolean notify, GrowTreeDescription description) {
		super(notify, description);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int treeHeight = rand.nextInt(description.maxHeight - description.minHeight) + description.minHeight
				+ (description.bonusHeight > 0 ? rand.nextInt(description.bonusHeight) : 0);
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

					if (description.vines)
						generateVines(world, pos, treeHeight, world.rand);

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	@Override
	public boolean isSuitableLocation(World world, BlockPos pos, int treeHeight) {
		for (int yi = pos.getY(); yi <= (pos.getY() + 1 + treeHeight); ++yi) {
			int distanceFromTop = yi - (pos.getY() + treeHeight);
			int leafSpan = Math.min(1 - (distanceFromTop / 2), 4);

			if (leafSpan > 4)
				continue;

			BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();

			for (int xi = pos.getX() - leafSpan; xi <= (pos.getX() + leafSpan); ++xi) {
				for (int zi = pos.getZ() - leafSpan; zi <= (pos.getZ() + leafSpan); ++zi) {
					if (Math.abs(xi - pos.getX()) + Math.abs(zi - pos.getZ()) > leafSpan)
						continue;

					if ((yi >= 0) && (yi < world.getHeight())) {
						if (!isReplaceable(world, mutableblockpos.setPos(xi, yi, zi)))
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
		for (int yi = (pos.getY() - (description.maxHeight - description.minHeight)) + treeHeight; yi <= (pos.getY()
				+ treeHeight); ++yi) {
			int distanceFromTop = yi - (pos.getY() + treeHeight);
			int leafSpan = 1 - (distanceFromTop / 2);

			if (leafSpan > 4)
				continue;

			for (int xi = pos.getX() - leafSpan; xi <= (pos.getX() + leafSpan); ++xi) {
				int distanceFromX = xi - pos.getX();

				for (int zi = pos.getZ() - leafSpan; zi <= (pos.getZ() + leafSpan); ++zi) {
					int distanceFromZ = zi - pos.getZ();
					if (Math.abs(distanceFromX) + Math.abs(distanceFromZ) <= leafSpan && distanceFromTop != 0) {
						BlockPos blockpos = new BlockPos(xi, yi, zi);
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
		for (int hi = 0; hi < treeHeight; ++hi) {
			BlockPos upN = pos.up(hi);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)
					|| (state.getMaterial() == Material.VINE)) {
				setBlockAndNotifyAdequately(world, pos.up(hi), PlaintextId.getBlockStateFrom(description.log));
			}
		}
	}

	public void generateVines(World world, BlockPos pos, int treeHeight, Random rand) {
		for (int hi = 0; hi < treeHeight; ++hi) {
			if (hi > 0) {
				if (world.isAirBlock(pos.add(-1, hi, 0))) {
					setBlockAndNotifyAdequately(world, pos.add(-1, hi, 0),
							Blocks.VINE.getDefaultState().withProperty(BlockVine.EAST, Boolean.valueOf(true)));
				}
			}
		}
	}
}
