package us.timinc.jsonifycraft.json.world;

import net.minecraft.block.*;
import us.timinc.jsonifycraft.world.*;

public class GrowingBlockDescription extends BlockDescription {
	public int stages = 15;

	@Override
	public Block createNewBlock() {
		return new GrowingBlock(this);
	}
}
