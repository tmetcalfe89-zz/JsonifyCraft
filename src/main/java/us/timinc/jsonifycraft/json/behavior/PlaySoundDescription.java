package us.timinc.jsonifycraft.json.behavior;

import us.timinc.calc.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;

public class PlaySoundDescription extends WorldBehaviorDescription {
	public String sound;
	public String soundCategory;
	public float volume = 1.0F;
	public float pitch = 1.0F;
	public boolean distanceDelay = false;

	@Override
	public void behave(EventContext event) {
		log("Playing sound %s.", sound);
		event.world.playSound(PlaintextCalculator.solve(event.getVariables(), pos_x),
				PlaintextCalculator.solve(event.getVariables(), pos_y),
				PlaintextCalculator.solve(event.getVariables(), pos_z), JsonifyCraft.REGISTRIES.getSound(sound),
				JsonifyCraft.REGISTRIES.getSoundCategory(soundCategory), volume, pitch, distanceDelay);
	}
}
