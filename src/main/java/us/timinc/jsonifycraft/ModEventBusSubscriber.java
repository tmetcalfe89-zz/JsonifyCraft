package us.timinc.jsonifycraft;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
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
		BlockPos rtPos = null;
		if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
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
}
