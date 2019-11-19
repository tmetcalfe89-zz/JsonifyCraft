package us.timinc.jsonifycraft.json.world;

import net.minecraft.item.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;

public class FoodDescription extends ItemDescription {
	public int hunger = 1;
	public float saturation = 0.6F;
	public boolean wolfFood = false;
	public EffectDescription effect = null;
	public float effectChance = 1.0F;
	public boolean alwaysEdible = false;

	private transient ItemFood item = null;

	@Override
	public Item getItem() {
		if (item == null) {
			item = new ItemFood(hunger, saturation, wolfFood);

			item.setCreativeTab(JsonifyCraft.REGISTRIES.getCreativeTab(creativeTab));

			if (effect != null) {
				item.setPotionEffect(effect.getPotionEffect(), effectChance);
			}

			if (alwaysEdible) {
				item.setAlwaysEdible();
			}
		}

		return item;
	}
}
