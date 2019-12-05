package us.timinc.jsonifycraft.json.behavior;

import java.util.*;

import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.mcutil.*;

public class ChangeRandomBlockDescription extends ChangeBlockDescription {
	public int range_x;
	public int range_y;
	public int range_z;
	public ConditionDescription[] conditions = {};
	public int attempts = 1;
	public boolean allowCenter = false;

	@Override
	public void behave(EventContext eventContext) {
		// Since we regenerate immediately if we pick the center position, we
		// must fail before trying if all ranges are 0. We would always pick the
		// center, and the randomPosition would never be picked.
		if ((range_x == 0) && (range_y == 0) && (range_z == 0) && !allowCenter) {
			log("At least one range must not be 0.");
			return;
		}

		// Find suitable position.
		int currentAttempts = 0;
		BlockPos centerPosition = getPos(eventContext);
		BlockPos randomPosition = null;
		boolean passed = false;

		do {
			do {
				randomPosition = getPos(eventContext).add(DiceRoller.roll(0 - range_x, range_x),
						DiceRoller.roll(0 - range_y, range_y), DiceRoller.roll(0 - range_z, range_z));
			} while (randomPosition.equals(centerPosition) && !allowCenter);
			eventContext.addTempPosition("rand", randomPosition);
			currentAttempts++;
			passed = Arrays.stream(conditions).allMatch(e -> e.evaluate(eventContext));
		} while ((currentAttempts < attempts) && !passed);

		// Change the block.
		if (passed) {
			BlockPos pos = getPos(eventContext);
			IBlockState prevState = eventContext.getBlockState(pos);

			log("Changing block at %s to %s.", randomPosition.toString(), block);
			if (age.isEmpty()) {
				eventContext.world.setBlockState(randomPosition, PlaintextId.getBlockStateFrom(block));
			} else {
				eventContext.addTemp("prev_age", prevState.getBlock().getMetaFromState(prevState));
				int newAge = PlaintextCalculator.solve(eventContext.getVariables(), age);
				eventContext.world.setBlockState(pos, PlaintextId.getBlockStateFrom(block + ":" + newAge));
			}
		} else {
			log("Could not find a suitable position for changerandomblock.");
		}

		// Clean up.
		eventContext.clearTemps();
	}
}
