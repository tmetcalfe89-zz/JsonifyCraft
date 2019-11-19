package us.timinc.jsonifycraft.json.world;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import us.timinc.jsonifycraft.world.*;
import us.timinc.mcutil.*;

public class BlockDescription extends WorldObjectDescription {
	public String material = "GROUND";
	public float hardness = 3.5F;
	public float resistance = 5.0f;
	public float slipperiness = 0.6F;
	public float lightLevel = 0;
	public int lightOpacity = 0;
	public String boundingBox = "";
	public String harvester = "";
	public String soundType = "GROUND";
	public String drop = null;
	public String[] soils = null;

	protected transient Block block = null;
	private transient ItemBlock item = null;

	public final Block getBlock() {
		if (block == null) {
			block = createNewBlock();
		}
		return block;
	}

	public Block createNewBlock() {
		return new JsonedBlock(this);
	}

	public final ItemBlock getItem() {
		if (item == null) {
			item = new ItemBlock(getBlock());
		}
		return item;
	}

	public boolean hasSoil(IBlockState blockState) {
		return hasSoil(PlaintextId.getBlockId(blockState));
	}

	public boolean hasSoil(String blockId) {
		return soils != null ? Arrays.stream(soils).anyMatch(e -> PlaintextId.matches(e, blockId)) : true;
	}

	public AxisAlignedBB createBoundingBox() {
		String[] x = boundingBox.split(",");
		return new AxisAlignedBB(Integer.parseInt(x[0]) / 16.0F, Integer.parseInt(x[1]) / 16.0F,
				Integer.parseInt(x[2]) / 16.0F, Integer.parseInt(x[3]) / 16.0F, Integer.parseInt(x[4]) / 16.0F,
				Integer.parseInt(x[5]) / 16.0F);
	}
}
