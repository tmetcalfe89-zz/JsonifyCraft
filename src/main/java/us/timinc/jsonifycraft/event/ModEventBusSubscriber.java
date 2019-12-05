package us.timinc.jsonifycraft.event;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.relauncher.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.client.*;
import us.timinc.jsonifycraft.world.*;
import us.timinc.mcutil.*;

@Mod.EventBusSubscriber
public class ModEventBusSubscriber {
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		JsonifyCraft.GAME_OBJECTS.registerBlocks(evt.getRegistry());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		JsonifyCraft.GAME_OBJECTS.registerItems(evt.getRegistry());
	}

	@SubscribeEvent
	public static void onInteraction(PlayerInteractEvent.RightClickBlock event) {
		if (!event.getEntityPlayer().canPlayerEdit(event.getPos(), event.getFace(),
				event.getEntityPlayer().getHeldItem(event.getHand())))
			return;

		if (event.getHand() == EnumHand.OFF_HAND)
			return;

		if (!Arrays.stream(DescriptionLoader.getReactors()).anyMatch(e -> e.event.equals("playerinteractblock")))
			return;

		RayTraceResult raytraceresult = MinecraftUtil.rayTrace(event.getWorld(), event.getEntityPlayer(), true);
		BlockPos rtPos = null;
		if ((raytraceresult != null) && (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)) {
			rtPos = raytraceresult.getBlockPos();
		}

		EventContext eventContext = new EventContext(event.getWorld(), event.getEntityPlayer());
		eventContext.addPosition("block", event.getPos());
		if (rtPos != null) {
			eventContext.addPosition("rt", rtPos);
		} else {
			eventContext.addPosition("rt", event.getPos());
		}

		EventProcessor.process(eventContext, DescriptionLoader.getReactors(), "playerinteractblock");
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onBlockColorsInit(ColorHandlerEvent.Block event) {
		DescriptionLoader.blocks.forEach(block -> {
			JsonedBlock jsonedBlock = (JsonedBlock) block;

			String[] colorizers = jsonedBlock.getColorizers();

			if (colorizers.length == 0)
				return;

			event.getBlockColors().registerBlockColorHandler(new JsonedBlockColor(colorizers), jsonedBlock);
		});
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onItemColorsInit(ColorHandlerEvent.Item event) {
		DescriptionLoader.items.forEach(item -> {
			String[] colorizers;

			if (item instanceof JsonedItem) {
				colorizers = ((JsonedItem) item).getColorizers();
			} else if (item instanceof ItemBlock) {
				colorizers = ((JsonedBlock) ((ItemBlock) item).getBlock()).getColorizers();
			} else
				return;

			if (colorizers.length == 0)
				return;

			event.getItemColors().registerItemColorHandler(new JsonedItemColor(colorizers), item);
		});
	}
}
