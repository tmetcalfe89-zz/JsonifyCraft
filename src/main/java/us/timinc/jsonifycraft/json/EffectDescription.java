package us.timinc.jsonifycraft.json;

import net.minecraft.potion.*;
import us.timinc.jsonifycraft.*;

public class EffectDescription extends JsonDescription {
	public String effect;
	public int duration = 30;
	public int level = 0;

	public PotionEffect getPotionEffect() {
		return new PotionEffect(JsonifyCraft.REGISTRIES.getEffect(effect), duration * 20, level);
	}
}
