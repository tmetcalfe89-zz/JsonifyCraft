package us.timinc.jsonifycraft.world;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.jsonifycraft.json.world.*;

public class JsonedItem extends Item {
	private ItemDescription itemJson;

	public JsonedItem(ItemDescription itemJson) {
		this.itemJson = itemJson;

		setup();
	}

	private void setup() {
		setCreativeTab(JsonifyCraft.REGISTRIES.getCreativeTab(itemJson.creativeTab));
		if (itemJson.durability > 0) {
			setMaxDamage(itemJson.durability);
		}
		setMaxStackSize(itemJson.stacksize);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if ((itemJson == null) || (hand != EnumHand.MAIN_HAND))
			return super.onItemUse(player, world, blockPos, hand, facing, hitX, hitY, hitZ);

		RayTraceResult raytraceresult = rayTrace(world, player, true);
		BlockPos raytracePos = raytraceresult.getBlockPos();

		EventContext eventContext = new EventContext(world);
		eventContext.addPosition("block", blockPos);
		eventContext.addPosition("rt", raytracePos);

		EventProcessor.process(eventContext, itemJson.events, "iteminteractblock");

		return super.onItemUse(player, world, blockPos, hand, facing, hitX, hitY, hitZ);
	}
}
