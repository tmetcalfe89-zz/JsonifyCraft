package us.timinc.jsonifycraft.json.world;

import net.minecraft.block.*;
import us.timinc.jsonifycraft.world.*;

public class RotatingBlockDescription extends BlockDescription {
	@Override
	public Block createNewBlock() {
		return new JsonedRotatingBlock(this);
	}
}
