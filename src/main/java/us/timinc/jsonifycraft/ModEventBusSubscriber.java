package us.timinc.jsonifycraft;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
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

		RayTraceResult raytraceresult = MinecraftUtil.rayTrace(event.getWorld(), event.getEntityPlayer(), true);
		BlockPos rtpos = raytraceresult.getBlockPos();

		processEvent(event.getWorld(), event.getEntityPlayer(), event.getPos(), rtpos, "iteminteractblock");
	}

	private static void processEvent(World world, EntityPlayer player, BlockPos pos, BlockPos rtpos, String eventName) {
		EventDescription eventDescription = new EventDescription(world, player, pos, rtpos);
		EventProcessor.process(eventDescription, DescriptionLoader.gameObjects.stream()
				.filter(e -> e instanceof ReactorDescription).toArray(ReactorDescription[]::new), eventName);
	}
}
