package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class DamageHeldItemDescription extends BehaviorDescription {
	public String hand = "main";
	public int amount = 1;

	@Override
	public void behave(EventContext event) {
		if (event.isRemote())
			return;

		ItemStack heldItem = PlaintextHelp.getHeldItem(event.player, hand);
		log("Damaging item in %s hand by %s.", hand, amount);
		heldItem.damageItem(amount, event.player);
	}
}
