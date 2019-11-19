package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.util.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;

public class PlaySoundDescription extends WorldBehaviorDescription {
	public String sound = "entity_ghast_warn";

	@Override
	public void behave(EventDescription event) {
		log("Playing sound %s.", sound);
		event.world.playSound(PlaintextCalculator.solve(event.variables, pos_x),
				PlaintextCalculator.solve(event.variables, pos_y), PlaintextCalculator.solve(event.variables, pos_z),
				JsonifyCraft.REGISTRIES.getSound(sound), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
	}
}
