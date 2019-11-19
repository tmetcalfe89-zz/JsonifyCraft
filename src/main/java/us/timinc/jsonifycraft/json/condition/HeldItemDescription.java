package us.timinc.jsonifycraft.json.condition;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class HeldItemDescription extends ConditionDescription {
	public String hand = "MAIN";
	public String item;

	@Override
	public boolean evaluate(EventDescription event) {
		ItemStack heldItem = PlaintextHelp.getHeldItem(event.player, hand);
		String itemId = PlaintextId.getItemId(heldItem);
		boolean result = PlaintextId.matches(item, itemId);
		log("%s %s %s", itemId, (result ? " matches " : " doesn't match "), item);
		return result;
	}
}
