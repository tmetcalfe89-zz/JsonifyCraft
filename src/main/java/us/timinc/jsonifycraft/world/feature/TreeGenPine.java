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
		if (!((pos.getY() >= 1) && ((pos.getY() + treeHeight + 1) <= world.getHeight())))
			return false;

		BlockPos down = pos.down();
		IBlockState state = world.getBlockState(down);

		if (!(state.getBlock().canSustainPlant(state, world, down, net.minecraft.util.EnumFacing.UP,
				(net.minecraft.block.BlockSapling) Blocks.SAPLING)
				&& (pos.getY() < (world.getHeight() - treeHeight - 1))))
			return false;

		for (int yi = pos.getY(); yi <= (pos.getY() + 1 + treeHeight); ++yi) {
			int leafSpan = l;

			if ((yi - pos.getY()) < j)
				leafSpan = 0;

			for (int xi = pos.getX() - leafSpan; xi <= (pos.getX() + leafSpan); ++xi) {
				for (int zi = pos.getZ() - leafSpan; zi <= (pos.getZ() + leafSpan); ++zi) {
					if ((yi >= 0) && (yi < world.getHeight())) {
						BlockPos targetPos = new BlockPos(xi, yi, zi);
						state = world.getBlockState(targetPos);

						if (!state.getBlock().isAir(state, world, targetPos)
								&& !state.getBlock().isLeaves(state, world, targetPos))
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
		int i3 = rand.nextInt(2);
		int j3 = 1;
		int k3 = 0;

		for (int yi = 0; yi <= k; ++yi) {
			int yiInverted = (pos.getY() + treeHeight) - yi;

			for (int xi = pos.getX() - i3; xi <= (pos.getX() + i3); ++xi) {
				int distanceFromX = xi - pos.getX();

				for (int zi = pos.getZ() - i3; zi <= (pos.getZ() + i3); ++zi) {
					int distanceFromZ = zi - pos.getZ();

					if ((Math.abs(distanceFromX) != i3) || (Math.abs(distanceFromZ) != i3) || (i3 <= 0)) {
						BlockPos blockpos = new BlockPos(xi, yiInverted, zi);
						IBlockState state = world.getBlockState(blockpos);

						if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
							setBlockAndNotifyAdequately(world, blockpos,
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
		int logStartOffset = rand.nextInt(description.maxHeight - description.minHeight);

		for (int hi = 0; hi < (treeHeight - logStartOffset); ++hi) {
			BlockPos upN = pos.up(hi);
			IBlockState state = world.getBlockState(upN);

			if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
				setBlockAndNotifyAdequately(world, pos.up(hi), PlaintextId.getBlockStateFrom(description.log));
			}
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		treeHeight = rand.nextInt(description.maxHeight - description.minHeight) + description.minHeight;
		j = 1 + rand.nextInt(2);
		l = 2 + rand.nextInt(2);
		k = treeHeight - j;

		if (!isSuitableLocation(world, pos, treeHeight))
			return false;

		generateLeaves(world, pos, treeHeight, rand);
		generateLogs(world, pos, treeHeight, rand);

		return true;
	}

}
