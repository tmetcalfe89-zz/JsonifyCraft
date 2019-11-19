package us.timinc.jsonifycraft.world;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;
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
	}

	public boolean hasFlag(String string) {
		return itemJson.hasFlag(string);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if ((itemJson == null) || (hand != EnumHand.MAIN_HAND))
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

		RayTraceResult raytraceresult = rayTrace(worldIn, player, true);
		BlockPos rtpos = raytraceresult.getBlockPos();

		processEvent(worldIn, player, pos, rtpos, "iteminteractblock");

		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	private void processEvent(World world, EntityPlayer player, BlockPos pos, BlockPos rtpos, String eventName) {
		EventDescription eventDescription = new EventDescription(world, player, pos, rtpos);
		EventProcessor.process(eventDescription, itemJson.events, eventName);
	}
}
