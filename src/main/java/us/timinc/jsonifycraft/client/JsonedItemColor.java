package us.timinc.jsonifycraft.client;

import net.minecraft.client.renderer.color.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class JsonedItemColor implements IItemColor {
	public String[] colorizers = {};

	public JsonedItemColor(String[] colorizers) {
		this.colorizers = colorizers;
	}

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		if (tintIndex >= colorizers.length)
			return 16777215;

		switch (colorizers[tintIndex]) {
		case "oak":
			return ColorizerFoliage.getFoliageColorBasic();
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
