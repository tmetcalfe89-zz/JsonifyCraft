package us.timinc.jsonifycraft.event;

import java.util.*;

import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EventDescription {
	public HashMap<String, Integer> variables = new HashMap<>();
	public final World world;
	public final EntityPlayer player;
	public final BlockPos pos;
	public final BlockPos rtpos;

	public EventDescription(World world, BlockPos pos) {
		this(world, null, pos);
	}

	public EventDescription(World world, EntityPlayer player, BlockPos pos) {
		this(world, player, pos, null);
	}

	public EventDescription(World world, EntityPlayer player, BlockPos pos, BlockPos rtpos) {
		this.world = world;
		this.player = player;
		this.pos = pos;
		this.rtpos = rtpos;

		variables.put("event_x", pos.getX());
		variables.put("event_y", pos.getY());
		variables.put("event_z", pos.getZ());

		if (rtpos != null) {
			variables.put("event_rtx", rtpos.getX());
			variables.put("event_rty", rtpos.getY());
			variables.put("event_rtz", rtpos.getZ());
		}
	}

	public void addVariable(String name, int value) {
		variables.put(name, value);
	}
}
