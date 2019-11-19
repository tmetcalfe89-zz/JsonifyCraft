package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class GivePlayerItemDescription extends BehaviorDescription {
	public String item;
	public int count = 1;

	@Override
	public void behave(EventDescription event) {
		if (event.world.isRemote)
			return;

		ItemStack given = PlaintextId.createItemStackFrom(item, count);
		if (!event.player.inventory.addItemStackToInventory(given)) {
			event.player.dropItem(given, false);
		}
	}
}
