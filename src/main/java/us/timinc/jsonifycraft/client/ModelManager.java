package us.timinc.jsonifycraft.client;

import java.util.*;

import net.minecraft.client.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ModelManager {
	public static void register(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerItemModels(ArrayList<Item> items) {
		items.stream().forEach(ModelManager::register);
	}
}
