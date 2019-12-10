package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.mcutil.*;

public class TreeGenBirch extends TreeGenAbstract {

	public TreeGenBirch(Boolean notify, GrowTreeDescription description) {
		super(notify, description);
	}

	@Override
	public boolean isSuitableLocation(World world, BlockPos pos, int treeHeight) {
		if ((pos.getY() >= 1) && ((pos.getY() + treeHeight + 1) <= world.getHeight())) {
			for (int yi = pos.getY(); yi <= (pos.getY() + 1 + treeHeight); ++yi) {
				int leafSpan = 1;

				if (yi == pos.getY()) {
					leafSpan = 0;
				}

				if (yi >= ((pos.getY() + 1 + treeHeight) - 2)) {
					leafSpan = 2;
				}

				BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

				for (int xi = pos.getX() - leafSpan; xi <= (pos.getX() + leafSpan); ++xi) {
					for (int zi = pos.getZ() - leafSpan; zi <= (pos.getZ() + leafSpan); ++zi) {
						if ((yi >= 0) && (yi < world.getHeight())) {
							if (!isReplaceable(world, checkPos.setPos(xi, yi, zi)))
								return false;
						} else
							return false;
					}
				}
			}
		} else
			return false;
		return true;
	}

	@Override
	public void generateLeaves(World world, BlockPos pos, int treeHeight, Random rand) {
		for (int yi = (pos.getY() - (description.maxHeight - description.minHeight)) + treeHeight; yi <= (pos.getY()
				+ treeHeight); ++yi) {
			int distanceFromTop = yi - (pos.getY() + treeHeight);
			int leafSpan = 1 - (distanceFromTop / 2);

			for (int xi = pos.getX() - leafSpan; xi <= (pos.getX() + leafSpan); ++xi) {
				int distanceFromCenterX = xi - pos.getX();

				for (int zi = pos.getZ() - leafSpan; zi <= (pos.getZ() + leafSpan); ++zi) {
					int distanceFromCenterZ = zi - pos.getZ();

					if ((Math.abs(distanceFromCenterX) != leafSpan) || (Math.abs(distanceFromCenterZ) != leafSpan)
							|| ((rand.nextInt(2) != 0) && (distanceFromTop != 0))) {
						BlockPos blockpos = new BlockPos(xi, yi, zi);
						IBlockState state2 = world.getBlockState(blockpos);

						if (state2.getBlock().isAir(state2, world, blockpos)
								|| state2.getBlock().isAir(state2, world, blockpos)) {
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
		for (int yi = 0; yi < treeHeight; ++yi) {
			BlockPos upN = pos.up(yi);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
				setBlockAndNotifyAdequately(world, pos.up(yi), PlaintextId.getBlockStateFrom(description.log));
			}
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		int treeHeight = rand.nextInt(description.maxHeight - description.minHeight) + description.minHeight;
		if (!isSuitableLocation(world, position, treeHeight))
			return false;

		generateLeaves(world, position, treeHeight, rand);
		generateLogs(world, position, treeHeight, rand);
		return true;
	}

}
