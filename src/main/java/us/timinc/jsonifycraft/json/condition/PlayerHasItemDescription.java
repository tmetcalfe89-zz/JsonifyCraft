package us.timinc.jsonifycraft.json.condition;

import net.minecraft.entity.player.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class PlayerHasItemDescription extends ConditionDescription {
	public String item = "";

	@Override
	public boolean evaluate(EventContext event) {
		EntityPlayer player = event.player;
		boolean retval = player.inventory.hasItemStack(PlaintextId.createItemStackFrom(item, 1));
		log("Player does%s have %s in their inventory.", (retval ? "" : "n't"), item);
		return retval;
	}
}
