package us.timinc.jsonifycraft.world;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.world.*;

public class GrowingBlock extends JsonedBlock implements IGrowable {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

	public GrowingBlock(BlockDescription blockJson) {
		super(blockJson);
		setDefaultState(blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return withAge(meta);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return getAge(state);
	}

	public IBlockState withAge(int age) {
		return getDefaultState().withProperty(getAgeProperty(), Integer.valueOf(age));
	}

	protected PropertyInteger getAgeProperty() {
		return AGE;
	}

	protected int getAge(IBlockState state) {
		return state.getValue(getAgeProperty()).intValue();
	}

	protected int getMaxAge() {
		return getJson().stages;
	}

	protected GrowingBlockDescription getJson() {
		return (GrowingBlockDescription) blockJson;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		int currentAge = state.getValue(GrowingBlock.AGE).intValue();
		return currentAge < (getMaxAge() - 1);
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return blockJson.hasFlag("bonemeal");
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int currentAge = state.getValue(GrowingBlock.AGE).intValue();
		JsonifyCraft.LOGGER.info("Aging from " + currentAge + " to " + (currentAge + 1) + ".");
		worldIn.setBlockState(pos, state.withProperty(GrowingBlock.AGE, Integer.valueOf(currentAge + 1)), 2);
	}
}
