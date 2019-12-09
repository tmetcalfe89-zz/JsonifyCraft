package us.timinc.jsonifycraft.world;

import java.util.*;

import javax.annotation.*;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.world.*;
import us.timinc.mcutil.*;

public class JsonedBlock extends Block {
	protected BlockDescription blockJson = null;
	private AxisAlignedBB boundingBox = null;

	public JsonedBlock(BlockDescription blockJson) {
		super(JsonifyCraft.REGISTRIES.getBlockMaterial(blockJson.material));

		this.blockJson = blockJson;

		setup();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (blockJson.hasFlag("leaves")) {
			doLeafParticles(worldIn, pos, rand);
		}
	}

	private void doLeafParticles(World worldIn, BlockPos pos, Random rand) {
		if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP)
				&& (rand.nextInt(15) == 1)) {
			double d0 = pos.getX() + rand.nextFloat();
			double d1 = pos.getY() - 0.05D;
			double d2 = pos.getZ() + rand.nextFloat();
			worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	private void setup() {
		setCreativeTab(JsonifyCraft.REGISTRIES.getCreativeTab(blockJson.creativeTab));
		setDefaultSlipperiness(blockJson.slipperiness);
		if ((blockJson.hardness < 0) || blockJson.hasFlag("unbreakable")) {
			setBlockUnbreakable();
		} else {
			setResistance(blockJson.resistance / 3.0F);
			setHardness(blockJson.hardness);
		}
		if (!blockJson.harvester.isEmpty()) {
			String[] splitHarvester = blockJson.harvester.split(",");
			setHarvestLevel(splitHarvester[0], Integer.parseInt(splitHarvester[1]));
		}
		setLightLevel(blockJson.lightLevel / 15);
		setLightOpacity(blockJson.lightOpacity);
		setSoundType(JsonifyCraft.REGISTRIES.getSoundType(blockJson.soundType));
		translucent = blockJson.hasFlag("transparent");
		setTickRandomly(blockJson.hasEvent("randomtick"));
	}

	/* Characteristics */

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (blockJson == null)
			return super.canPlaceBlockAt(worldIn, pos);

		return super.canPlaceBlockAt(worldIn, pos) && blockJson.hasSoil(worldIn.getBlockState(pos.down()));
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if ((blockJson == null) || blockJson.boundingBox.isEmpty())
			return super.getBoundingBox(state, source, pos);

		// Cache boundingBox.
		if (boundingBox == null) {
			boundingBox = blockJson.createBoundingBox();
		}
		return boundingBox;
	}

	@Override
	public int tickRate(World world) {
		return (blockJson.hasFlag("leaves", "falls") ? 2 : super.tickRate(world));
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		if (blockJson == null)
			return super.getCollisionBoundingBox(blockState, worldIn, pos);

		return blockJson.hasFlag("ghost") ? NULL_AABB : super.getCollisionBoundingBox(blockState, worldIn, pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		if (blockJson == null)
			return super.getItem(worldIn, pos, state);

		return blockJson.drop != null ? PlaintextId.createItemStackFrom(blockJson.drop, 1)
				: super.getItem(worldIn, pos, state);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		if (blockJson == null)
			return super.isBeaconBase(worldObj, pos, beacon);

		return blockJson.hasFlag("beaconbase");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		if (blockJson == null)
			return super.isFullCube(state);

		return !blockJson.hasFlag("ghost") && blockJson.boundingBox.isEmpty();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		if (blockJson == null)
			return super.isOpaqueCube(state);

		return !blockJson.hasFlag("transparent", "partial");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		if (blockJson == null)
			return super.getBlockLayer();

		return blockJson.hasFlag("transparent") ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
	}

	/* Events */

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (blockJson == null) {
			super.neighborChanged(state, world, pos, blockIn, fromPos);
			return;
		}

		world.scheduleUpdate(pos, this, tickRate(world));

		checkForFall(world, pos);
		checkForUproot(world, pos, state);

		EventContext eventContext = new EventContext(world);
		eventContext.addPosition("block", pos);
		eventContext.addPosition("neighbor", fromPos);

		EventProcessor.process(eventContext, blockJson.events, "neighborchanged");
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (blockJson == null) {
			super.onBlockAdded(world, pos, state);
			return;
		}

		world.scheduleUpdate(pos, this, tickRate(world));

		checkForFall(world, pos);

		EventContext eventContext = new EventContext(world);
		eventContext.addPosition("block", pos);

		EventProcessor.process(eventContext, blockJson.events, "blockadded");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if ((blockJson == null) || (hand == EnumHand.OFF_HAND))
			return super.onBlockActivated(world, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);

		world.scheduleUpdate(pos, this, tickRate(world));

		EventContext eventContext = new EventContext(world, playerIn);
		eventContext.addPosition("block", pos);
		eventContext.addPosition("player", playerIn.getPosition());

		EventProcessor.process(eventContext, blockJson.events, "playerinteractblock");

		return super.onBlockActivated(world, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void randomTick(World world, BlockPos blockPosition, IBlockState state, Random random) {
		if (blockJson == null) {
			super.randomTick(world, blockPosition, state, random);
			return;
		}

		world.scheduleUpdate(blockPosition, this, tickRate(world));

		EventContext eventContext = new EventContext(world);
		eventContext.addPosition("block", blockPosition);

		EventProcessor.process(eventContext, blockJson.events, "randomtick");

		super.randomTick(world, blockPosition, state, random);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (blockJson == null) {
			super.updateTick(world, pos, state, rand);
			return;
		}

		if (blockJson.hasFlag("leaves")) {
			checkLeaves(world, pos);
		}

		if (blockJson.hasFlag("falls")) {
			if (!world.isRemote) {
				makeBlockFall(world, pos);
			}
		}
	}

	private void checkLeaves(World worldIn, BlockPos pos) {
		int k = pos.getX();
		int l = pos.getY();
		int i1 = pos.getZ();
		int[] surroundings = new int[32768];

		if (!worldIn.isAreaLoaded(pos, 1))
			return; // Forge: prevent decaying leaves from updating neighbors
					// and loading unloaded chunks
		if (worldIn.isAreaLoaded(pos, 6)) // Forge: extend range from 5 to 6 to
											// account for neighbor checks in
											// world.markAndNotifyBlock ->
											// world.updateObservingBlocksAt
		{
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

			for (int i2 = -4; i2 <= 4; ++i2) {
				for (int j2 = -4; j2 <= 4; ++j2) {
					for (int k2 = -4; k2 <= 4; ++k2) {
						IBlockState iblockstate = worldIn
								.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
						Block block = iblockstate.getBlock();

						if (!block.canSustainLeaves(iblockstate, worldIn,
								blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
							if (block.isLeaves(iblockstate, worldIn,
									blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
								surroundings[((i2 + 16) * 1024) + ((j2 + 16) * 32) + k2 + 16] = -2;
							} else {
								surroundings[((i2 + 16) * 1024) + ((j2 + 16) * 32) + k2 + 16] = -1;
							}
						} else {
							surroundings[((i2 + 16) * 1024) + ((j2 + 16) * 32) + k2 + 16] = 0;
						}
					}
				}
			}

			for (int i3 = 1; i3 <= 4; ++i3) {
				for (int j3 = -4; j3 <= 4; ++j3) {
					for (int k3 = -4; k3 <= 4; ++k3) {
						for (int l3 = -4; l3 <= 4; ++l3) {
							if (surroundings[((j3 + 16) * 1024) + ((k3 + 16) * 32) + l3 + 16] == (i3 - 1)) {
								if (surroundings[(((j3 + 16) - 1) * 1024) + ((k3 + 16) * 32) + l3 + 16] == -2) {
									surroundings[(((j3 + 16) - 1) * 1024) + ((k3 + 16) * 32) + l3 + 16] = i3;
								}

								if (surroundings[((j3 + 16 + 1) * 1024) + ((k3 + 16) * 32) + l3 + 16] == -2) {
									surroundings[((j3 + 16 + 1) * 1024) + ((k3 + 16) * 32) + l3 + 16] = i3;
								}

								if (surroundings[((j3 + 16) * 1024) + (((k3 + 16) - 1) * 32) + l3 + 16] == -2) {
									surroundings[((j3 + 16) * 1024) + (((k3 + 16) - 1) * 32) + l3 + 16] = i3;
								}

								if (surroundings[((j3 + 16) * 1024) + ((k3 + 16 + 1) * 32) + l3 + 16] == -2) {
									surroundings[((j3 + 16) * 1024) + ((k3 + 16 + 1) * 32) + l3 + 16] = i3;
								}

								if (surroundings[((j3 + 16) * 1024) + ((k3 + 16) * 32) + ((l3 + 16) - 1)] == -2) {
									surroundings[((j3 + 16) * 1024) + ((k3 + 16) * 32) + ((l3 + 16) - 1)] = i3;
								}

								if (surroundings[((j3 + 16) * 1024) + ((k3 + 16) * 32) + l3 + 16 + 1] == -2) {
									surroundings[((j3 + 16) * 1024) + ((k3 + 16) * 32) + l3 + 16 + 1] = i3;
								}
							}
						}
					}
				}
			}
		}

		int l2 = surroundings[16912];

		if (l2 < 0) {
			destroy(worldIn, pos);
		}
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return blockJson.hasFlag("sustainleaves");
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return blockJson.hasFlag("leaves");
	}

	private void destroy(World worldIn, BlockPos pos) {
		dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
		worldIn.setBlockToAir(pos);
	}

	/* Processors */

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (blockJson == null) {
			super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
			return;
		}

		if (blockJson.drop != null) {
			spawnAsEntity(worldIn, pos, PlaintextId.createItemStackFrom(blockJson.drop, 1));
		} else {
			super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
		}
	}

	private void checkForFall(World worldIn, BlockPos pos) {
		if (blockJson.hasFlag("falls")) {
			worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
		}
	}

	private void checkForUproot(World worldIn, BlockPos pos, IBlockState state) {
		if (!blockJson.hasSoil(worldIn.getBlockState(pos.down()))) {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	private void makeBlockFall(World worldIn, BlockPos pos) {
		if ((worldIn.isAirBlock(pos.down()) || worldIn.getBlockState(pos.down()).getMaterial().isReplaceable())
				&& (pos.getY() >= 0)) {
			if (!worldIn.isRemote) {
				EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(),
						pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				worldIn.spawnEntity(entityfallingblock);
			}
		}
	}

	public boolean hasFlag(String... searchFlags) {
		return blockJson.hasFlag(searchFlags);
	}

	public String[] getColorizers() {
		return blockJson.colorizers;
	}
}
