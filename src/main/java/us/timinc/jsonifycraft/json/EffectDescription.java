package us.timinc.jsonifycraft.json;

import net.minecraft.potion.*;
import us.timinc.jsonifycraft.*;

public class EffectDescription extends JsonDescription {
	public String name = "hunger";
	public int duration = 30;
	public int level = 0;

	public PotionEffect getPotionEffect() {
		return new PotionEffect(JsonifyCraft.REGISTRIES.getEffect(name), duration * 20, level);
	}
}
