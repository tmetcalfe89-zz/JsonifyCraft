package us.timinc.jsonifycraft.event;

import java.util.*;

import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EventContext {
	private HashMap<String, Integer> variables = new HashMap<>();
	private ArrayList<String> temps = new ArrayList<>();
	public World world;
	public EntityPlayer player;

	public EventContext(World world) {
		this.world = world;
	}

	public EventContext(World world, EntityPlayer player) {
		this.world = world;
		this.player = player;
	}

	public void addPosition(String prefix, BlockPos pos) {
		variables.put(String.format("%s_x", prefix), pos.getX());
		variables.put(String.format("%s_y", prefix), pos.getY());
		variables.put(String.format("%s_z", prefix), pos.getZ());
	}

	public void addTempPosition(String prefix, BlockPos pos) {
		temps.add(String.format("%s_x", pos.getX()));
		temps.add(String.format("%s_y", pos.getY()));
		temps.add(String.format("%s_z", pos.getZ()));
		addPosition(prefix, pos);
	}

	public String getSide() {
		return world.isRemote ? "client" : "server";
	}

	public IBlockState getBlockState(BlockPos pos) {
		return world.getBlockState(pos);
	}

	public void addVariable(String key, int value) {
		variables.put(key, value);
	}

	public int getVariable(String key) {
		return variables.get(key);
	}

	public HashMap<String, Integer> getVariables() {
		return variables;
	}

	public int addTemp(String key, int value) {
		temps.add(key);
		return variables.put(key, value);
	}

	public void clearTemps() {
		for (String tempKey : temps) {
			variables.remove(tempKey);
		}
		temps.clear();
	}

	public boolean isRemote() {
		return world.isRemote;
	}
}
