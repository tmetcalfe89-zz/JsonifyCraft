package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.jsonifycraft.world.*;
import us.timinc.mcutil.*;

public class DamageHeldItemDescription extends BehaviorDescription {
	public String hand = "main";
	public int amount = 1;

	@Override
	public void behave(EventDescription event) {
		ItemStack heldItem = PlaintextHelp.getHeldItem(event.player, hand);

		if ((heldItem.getItem() instanceof JsonedItem) && ((JsonedItem) heldItem.getItem()).hasFlag("indestructible")
				&& ((heldItem.getMaxDamage() - heldItem.getItemDamage()) == 0)) {
			log("Item in %s hand is indestructible.", hand);
			return;
		}
		log("Damaging item in %s hand by %s.", hand, amount);
		heldItem.damageItem(amount, event.player);
	}
}
