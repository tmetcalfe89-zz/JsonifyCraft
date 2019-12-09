package us.timinc.jsonifycraft.world.feature;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;
import us.timinc.grabbag.*;
import us.timinc.mcutil.*;

public class MarbleWorldGenTrees extends WorldGenAbstractTree {
	private static final IBlockState DEFAULT_TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT,
			BlockPlanks.EnumType.OAK);
	private static final IBlockState DEFAULT_LEAF = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
			.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	/** The minimum height of a generated tree. */
	private int minTreeHeight;
	/** The maximum height of a generated tree. */
	private int maxTreeHeight;
	/** The metadata value of the wood to use in tree generation. */
	private BagOfMarbles<String> logs;
	/** The metadata value of the leaves to use in tree generation. */
	private BagOfMarbles<String> leaves;

	public MarbleWorldGenTrees(boolean notify) {
		super(notify);
	}

	public MarbleWorldGenTrees(boolean notify, int minTreeHeight, int maxTreeHeight, BagOfMarbles<String> logs,
			BagOfMarbles<String> leaves, boolean growVines) {
		super(notify);
		this.minTreeHeight = minTreeHeight;
		this.maxTreeHeight = maxTreeHeight;
		this.logs = logs;
		this.leaves = leaves;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		ArrayList<BlockPos> leafPositions = new ArrayList<>();
		ArrayList<String> leavesToPlace = new ArrayList<>();
		ArrayList<BlockPos> logPositions = new ArrayList<>();
		ArrayList<String> logsToPlace = new ArrayList<>();

		int i = rand.nextInt(maxTreeHeight - minTreeHeight) + minTreeHeight;
		boolean flag = true;

		IBlockState saplingState = worldIn.getBlockState(position);
		worldIn.setBlockToAir(position);

		if ((position.getY() >= 1) && ((position.getY() + i + 1) <= worldIn.getHeight())) {
			for (int j = position.getY(); j <= (position.getY() + 1 + i); ++j) {
				int k = 1;

				if (j == position.getY()) {
					k = 0;
				}

				if (j >= ((position.getY() + 1 + i) - 2)) {
					k = 2;
				}

				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; (l <= (position.getX() + k)) && flag; ++l) {
					for (int i1 = position.getZ() - k; (i1 <= (position.getZ() + k)) && flag; ++i1) {
						if ((j >= 0) && (j < worldIn.getHeight())) {
							if (!isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				worldIn.setBlockState(position, saplingState);
				return false;
			} else {
				IBlockState state = worldIn.getBlockState(position.down());

				if (position.getY() < (worldIn.getHeight() - i - 1)) {
					state.getBlock().onPlantGrow(state, worldIn, position.down(), position);
					for (int i3 = (position.getY() - 3) + i; i3 <= (position.getY() + i); ++i3) {
						int i4 = i3 - (position.getY() + i);
						int j1 = 1 - (i4 / 2);

						for (int k1 = position.getX() - j1; k1 <= (position.getX() + j1); ++k1) {
							int l1 = k1 - position.getX();

							for (int i2 = position.getZ() - j1; i2 <= (position.getZ() + j1); ++i2) {
								int j2 = i2 - position.getZ();

								if ((Math.abs(l1) != j1) || (Math.abs(j2) != j1)
										|| ((rand.nextInt(2) != 0) && (i4 != 0))) {
									BlockPos blockpos = new BlockPos(k1, i3, i2);
									state = worldIn.getBlockState(blockpos);

									if (state.getBlock().isAir(state, worldIn, blockpos)
											|| state.getBlock().isLeaves(state, worldIn, blockpos)
											|| (state.getMaterial() == Material.VINE)) {
										setBlockAndNotifyAdequately(worldIn, blockpos, DEFAULT_LEAF);
										leafPositions.add(blockpos);
									}
								}
							}
						}
					}

					// Generate leaves.
					leavesToPlace = leaves.grabMarbles(leafPositions.size());
					for (int ti = 0; ti < leavesToPlace.size(); ti++) {
						setBlockAndNotifyAdequately(worldIn, leafPositions.get(ti),
								PlaintextId.getBlockStateFrom(leavesToPlace.get(ti)));
					}

					for (int j3 = 0; j3 < i; ++j3) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);

						if (state.getBlock().isAir(state, worldIn, upN)
								|| state.getBlock().isLeaves(state, worldIn, upN)
								|| (state.getMaterial() == Material.VINE)) {
							setBlockAndNotifyAdequately(worldIn, position.up(j3), DEFAULT_TRUNK);
							logPositions.add(position.up(j3));
						}
					}

					// Generate logs.
					logsToPlace = logs.grabMarbles(logPositions.size());
					for (int ti = 0; ti < logsToPlace.size(); ti++) {
						setBlockAndNotifyAdequately(worldIn, logPositions.get(ti),
								PlaintextId.getBlockStateFrom(logsToPlace.get(ti)));
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}
}
