package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class DropItemDescription extends WorldBehaviorDescription {
	public String item;
	public int count = 1;

	@Override
	public void behave(EventDescription event) {
		if (event.world.isRemote)
			return;

		ItemStack drop = PlaintextId.createItemStackFrom(item, count);
		BlockPos pos = getPos(event);
		log("Dropping %sx%s at %s.", item, count, pos);
		event.world.spawnEntity(new EntityItem(event.world, pos.getX(), pos.getY(), pos.getZ(), drop));
	}
}
