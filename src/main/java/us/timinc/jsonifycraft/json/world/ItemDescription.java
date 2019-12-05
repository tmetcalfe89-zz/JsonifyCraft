package us.timinc.jsonifycraft.json.world;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.world.*;

public class ItemDescription extends WorldObjectDescription {
	public int durability = 0;
	public int stacksize = 64;
	public String[] colorizers = {};

	private transient Item item = null;

	public Item getItem() {
		if (item == null) {
			item = new JsonedItem(this);
		}
		return item;
	}
}
