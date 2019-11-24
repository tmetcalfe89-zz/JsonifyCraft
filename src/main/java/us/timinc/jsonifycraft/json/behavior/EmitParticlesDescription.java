package us.timinc.jsonifycraft.json.behavior;

import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import us.timinc.calc.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.event.*;
import us.timinc.mcutil.*;

public class EmitParticlesDescription extends WorldBehaviorDescription {
	public String particle;
	public int count = 15;
	public String area = "out";

	@Override
	public void behave(EventContext event) {
		BlockPos pos = getPos(event);
		IBlockState targetBlock = event.getBlockState(pos);

		log("Emitting %s %s particles at %s.", count, particle, pos.toString());
		for (int i = 0; i < count; i++) {
			double[] particlePosition = getParticlePosition(pos, area);
			event.world.spawnParticle(JsonifyCraft.REGISTRIES.getParticleType(getParticleName()), particlePosition[0],
					particlePosition[1], particlePosition[2], (float) Math.random() * 0.02D,
					(float) Math.random() * 0.02D, (float) Math.random() * 0.02D,
					getParticleParam(targetBlock).isEmpty() ? 0
							: Item.getIdFromItem(
									PlaintextId.createItemStackFrom(getParticleParam(targetBlock), 1).getItem()));
		}
	}

	/**
	 * Gets the name of the particle to spawn.
	 *
	 * @return The name of the particle to spawn.
	 */
	public String getParticleName() {
		String[] splitParticleType = particle.split(":");
		return splitParticleType[0];
	}

	/**
	 * Gets the parameters needed to spawn this particle.
	 *
	 * @return The parameters needed to spawn this particle.
	 */
	public String getParticleParam(IBlockState backup) {
		String[] splitParticleType = particle.split(":");
		if (splitParticleType.length == 1)
			return PlaintextId.getBlockId(backup);
		return (splitParticleType[1] + ":" + splitParticleType[2] + ":" + splitParticleType[3]);
	}

	private double[] getParticlePosition(BlockPos targetPosition, String sideType) {
		double[] retval = new double[3];
		switch (sideType) {
		case "in":
			retval[0] = targetPosition.getX() + Math.random();
			retval[1] = targetPosition.getY() + Math.random();
			retval[2] = targetPosition.getZ() + Math.random();
			break;
		case "out":
			int side = DiceRoller.roll(0, 2);
			double[] sideFinder = { -0.1, 1.1 };
			retval[0] = targetPosition.getX() + (side == 0 ? sideFinder[DiceRoller.roll(0, 1)] : Math.random());
			retval[1] = targetPosition.getY() + (side == 1 ? sideFinder[DiceRoller.roll(0, 1)] : Math.random());
			retval[2] = targetPosition.getZ() + (side == 2 ? sideFinder[DiceRoller.roll(0, 1)] : Math.random());
			break;
		case "top":
			retval[0] = targetPosition.getX() + Math.random();
			retval[1] = targetPosition.getY() + 1.1;
			retval[2] = targetPosition.getZ() + Math.random();
			break;
		}
		return retval;
	}
}
