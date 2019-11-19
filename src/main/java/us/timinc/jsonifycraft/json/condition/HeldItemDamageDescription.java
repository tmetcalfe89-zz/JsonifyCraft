package us.timinc.jsonifycraft.json.condition;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class HeldItemDamageDescription extends ConditionDescription {
	public String hand = "main";
	public int fixed = -1;
	public int min = -1;
	public int max = -1;

	@Override
	public boolean evaluate(EventDescription event) {
		ItemStack heldItem = PlaintextHelp.getHeldItem(event.player, hand);
		int currentDamage = heldItem.getItemDamage();

		if ((fixed == -1) ? false : currentDamage != fixed) {
			log("Held item's damage is %s, not the target %s.", currentDamage, fixed);
			return false;
		}
		if ((min == -1) ? false : currentDamage < min) {
			log("Held item's damage is %s, less than target %s.", currentDamage, min);
			return false;
		}
		if ((max == -1) ? false : currentDamage > max) {
			log("Held item's damage is %s, greater than target %s.", currentDamage, max);
			return false;
		}

		log("Held item's damage is %s, within parameters.", currentDamage);
		return true;
	}
}
