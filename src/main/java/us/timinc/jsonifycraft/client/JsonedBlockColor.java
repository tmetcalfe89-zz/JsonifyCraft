package us.timinc.jsonifycraft.client;

import net.minecraft.block.state.*;
import net.minecraft.client.renderer.color.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class JsonedBlockColor implements IBlockColor {
	public String[] colorizers = {};

	public JsonedBlockColor(String[] colorizers) {
		this.colorizers = colorizers;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		if (tintIndex >= colorizers.length)
			return 16777215;

		switch (colorizers[tintIndex]) {
		case "oak":
			return (worldIn != null) && (pos != null) ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos)
					: ColorizerFoliage.getFoliageColorBasic();
		case "spruce":
			return ColorizerFoliage.getFoliageColorPine();
		case "birch":
			return ColorizerFoliage.getFoliageColorBirch();
		}

		if (colorizers[tintIndex].startsWith("#"))
			return Integer.parseInt(colorizers[tintIndex].substring(1), 16);

		return 16777215;
	}
}
